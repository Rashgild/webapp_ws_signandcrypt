package ru.my.signAndCrypt;

import org.apache.xml.security.encryption.XMLCipher;
import org.apache.xml.security.utils.EncryptionConstants;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.CryptoPro.JCPxml.xmldsig.JCPXMLDSigInit;
import ru.my.helpers_operations.GlobalVariables;
import ru.my.helpers_operations.WorkWithXML;

import javax.xml.crypto.KeySelector;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.*;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.X509Certificate;
import java.util.Iterator;

import static ru.my.helpers_operations.GlobalVariables.aliasCert;
import static ru.my.helpers_operations.GlobalVariables.passwordCertStor;
import static ru.my.helpers_operations.GlobalVariables.pathToCert;

public class VerifyAndDecrypt {

    public static SOAPMessage Start(SOAPMessage soapMessage)
    {
        try {
            WorkWithXML.saveSoapToXml("CryptedResponse.xml",soapMessage); // сохраняем в файл
            org.w3c.dom.Document doc = StartDecrypt(Certificate.GetCertificateFromStorage(GlobalVariables.moAlias),
                    Certificate.GetPrivateKey(GlobalVariables.moPass,GlobalVariables.moAlias)); // расшифровываем на нашем открытом ключе

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            NodeList originalRoot = doc.getDocumentElement().getElementsByTagName("S:Envelope"); //Удаляем лишние теги(коируем исключая внешние <soap-env:envelope>
            Element element = (Element) originalRoot.item(0);
            Document copiedDocument = db.newDocument();
            org.w3c.dom.Node copiedRoot = copiedDocument.importNode(element, true);
            copiedDocument.appendChild(copiedRoot);

            soapMessage = WorkWithXML.docToSoap(copiedDocument); //конвертируем Документ в Месседж

            if(!verify(soapMessage, Certificate.ExtractCertFromCertStore(passwordCertStor,aliasCert,pathToCert))) // verify
            {System.out.println("Сообщение не прошло проверку подписи!");}

            return soapMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soapMessage;
    }

    private static Document StartDecrypt(X509Certificate cert, PrivateKey key) throws Exception
    {
        JCPXMLDSigInit.init();
        MessageFactory mf = MessageFactory.newInstance();
        SOAPMessage message2 = mf.createMessage();
        SOAPPart soapPart2 = message2.getSOAPPart();
        FileInputStream is2 = new FileInputStream(GlobalVariables.pathtosave+"CryptedResponse.xml"); // ЕСЛИ берем из файла
        soapPart2.setContent(new StreamSource(is2));
        Document doc2 = message2.getSOAPPart().getEnvelope().getOwnerDocument();

        XMLCipher xmlCipher = XMLCipher.getInstance();
        xmlCipher.init(XMLCipher.DECRYPT_MODE, null);

        Element encryptedDataElement = (Element) doc2.getElementsByTagNameNS(
                EncryptionConstants.EncryptionSpecNS,
                EncryptionConstants._TAG_ENCRYPTEDDATA).item(0);

        if (key != null)
            xmlCipher.setKEK(key);

        xmlCipher.doFinal(doc2, encryptedDataElement);
        is2.close();
        return doc2;
    }

    //проверка подписи
    public static boolean verify(SOAPMessage message, X509Certificate cert) throws Exception {

        SOAPHeader header = message.getSOAPHeader();
        Document doc = header.getOwnerDocument();
        SOAPBody soapBody = message.getSOAPBody();
        Attr idAttr = soapBody.getAttributeNode("wsu:Id");
        soapBody.setIdAttributeNode(idAttr, true);

        final Element wssecontext = doc.createElementNS(null, "namespaceContext");
        wssecontext.setAttributeNS("http://www.w3.org/2000/ds/", "ds:wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");

        Iterator allHeaders = header.examineAllHeaderElements();
        Element binarySecurityTokenElement = null;
        SOAPHeaderElement headerElement;

        while (allHeaders.hasNext()) {
            headerElement = (SOAPHeaderElement)allHeaders.next();
            if  ("http://eln.fss.ru/actor/fss/ca/1027739443236".equals(headerElement.getActor())) {
                binarySecurityTokenElement = (Element) headerElement.getElementsByTagName("wsse:BinarySecurityToken").item(0);
                break;
            }
        }
        if (binarySecurityTokenElement == null) {
            System.out.println("Автор-хидер не найден!");
            return false;
        }
        if (cert == null) {
            throw new Exception("Сертификат не найден!");
        }//else System.out.println("Сертификат найден.");
        NodeList nl = doc.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "Signature");
        if (nl.getLength() == 0) {
            throw new Exception("Не найден элемент Signature.");
        }//else System.out.println("Элемент Signature найден!");

        DOMValidateContext valContext = new DOMValidateContext(KeySelector.singletonKeySelector(cert.getPublicKey()), nl.item(0));

        Provider xmlDSigProvider = new ru.CryptoPro.JCPxml.dsig.internal.dom.XMLDSigRI();
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", xmlDSigProvider);

        XMLSignature signature = fac.unmarshalXMLSignature(valContext);
        return signature.validate(valContext);
    }
}
