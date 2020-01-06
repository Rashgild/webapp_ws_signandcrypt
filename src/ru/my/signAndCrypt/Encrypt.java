package ru.my.signAndCrypt;

import java.io.FileInputStream;
import java.security.cert.X509Certificate;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.stream.StreamSource;

import org.apache.xml.security.encryption.EncryptedData;
import org.apache.xml.security.encryption.EncryptedKey;
import org.apache.xml.security.encryption.XMLCipher;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.keys.content.X509Data;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ru.CryptoPro.JCP.params.CryptParamsSpec;
import ru.CryptoPro.JCPxml.Consts;
import ru.CryptoPro.JCPxml.XmlInit;
import ru.my.utils.GlobalVariables;

import static ru.CryptoPro.JCP.JCP.GOST_EL_2012_256_NAME;
import static ru.CryptoPro.JCP.JCP.GOST_EL_2012_512_NAME;
import static ru.my.utils.GlobalVariables.aliasCert;
import static ru.my.utils.GlobalVariables.passwordCertStor;
import static ru.my.utils.GlobalVariables.pathToCert;
import static ru.my.utils.XmlUtils.saveSoapToXml;
import static ru.my.utils.XmlUtils.soapMessageToString;
import static ru.my.utils.XmlUtils.stringToSoap;

public class Encrypt {

    /**
     * Зашифрование документа doc на sessionKey.
     *
     * @param cert Сертификат, на котором проходит шифрование
     * @return шифрованный документ
     * @throws Exception ошибки шифрования
     */
    private static Document startEncrypt(X509Certificate cert, String nameFile) throws Exception {
        XmlInit.init();

        MessageFactory mf = MessageFactory.newInstance();
        SOAPMessage message = mf.createMessage();
        SOAPPart soapPart = message.getSOAPPart();

        FileInputStream is = new FileInputStream(GlobalVariables.pathtosave + nameFile);
        soapPart.setContent(new StreamSource(is));

        Document doc = message.getSOAPPart().getEnvelope().getOwnerDocument();

        //Создание случайного сессионного ключа.
        final KeyGenerator kg = KeyGenerator.getInstance("GOST28147");

        if (cert != null &&
                cert.getPublicKey().getAlgorithm().equals(GOST_EL_2012_256_NAME) ||
                cert.getPublicKey().getAlgorithm().equals(GOST_EL_2012_512_NAME)) {

            CryptParamsSpec spec = CryptParamsSpec.getInstance(CryptParamsSpec.Rosstandart_TC26_Z);
            kg.init(spec);
        }
        final SecretKey sessionKey = kg.generateKey();

        //Зашифрование сессионного ключа.
        EncryptedKey encryptedKey = wrapKey(doc, sessionKey, cert);
        //зашифрование документа
        Element element = doc.getDocumentElement();
        XMLCipher xmlCipher = XMLCipher.getInstance(Consts.URI_GOST_CIPHER);
        xmlCipher.init(XMLCipher.ENCRYPT_MODE, sessionKey);
        // добавляем шифрованный ключ.
        EncryptedData encryptedData = xmlCipher.getEncryptedData();
        KeyInfo keyInfo = new KeyInfo(doc);
        keyInfo.add(encryptedKey);
        encryptedData.setKeyInfo(keyInfo);
        //зашифрование документа
        xmlCipher.doFinal(doc, element, false);
        is.close();
        return doc;
    }


    /**
     * зашифрование сессионного ключа sessionKey и создание
     * EncryptedKey с сертификатом.
     *
     * @param doc        xml документ
     * @param sessionKey случайный сессионный ключ.
     * @param cert       сертификат
     * @return зашифрованный ключ
     * @throws Exception ошибки шифрования
     */
    private static EncryptedKey wrapKey(Document doc, SecretKey sessionKey, X509Certificate cert) throws Exception {
        XMLCipher keyCipher = XMLCipher.getInstance(Consts.URI_GOST_TRANSPORT);
        keyCipher.init(XMLCipher.WRAP_MODE, cert.getPublicKey());
        //создание KeyInfo с сертификатом
        KeyInfo certKeyInfo = new KeyInfo(doc);
        X509Data x509data = new X509Data(doc);
        x509data.addCertificate(cert);
        certKeyInfo.add(x509data);
        // зашифрование ключа
        EncryptedKey encryptedKey = keyCipher.encryptKey(doc, sessionKey);
        encryptedKey.setKeyInfo(certKeyInfo);
        return encryptedKey;
    }

    /**
     * 1)Создает формирует каркас для XML-Encryption;
     * 2)Шифрует подписанный документ;
     * 3)Помещает данные шифрование в созданный каркас;
     * 4)Сохраняет в файл
     *
     * @param soapMessage перехваченное сообщение
     */
    public static SOAPMessage createXmlAndEncrypt(SOAPMessage soapMessage, String nameFile)
            throws Exception {

        //Cоздание каркаса XML-Encrypt
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope soapEnv2 = soapMessage.getSOAPPart().getEnvelope();
        SOAPHeader soapHeader2 = soapEnv2.getHeader();
        SOAPBody soapBody2 = soapEnv2.getBody();
        SOAPElement EncryptedData = soapBody2.addChildElement("EncryptedData", null, "http://www.w3.org/2001/04/xmlenc#");
        Name name = soapEnv2.createName("Type");
        EncryptedData.addAttribute(name, "http://www.w3.org/2001/04/xmlenc#Element");
        SOAPElement EncryptionMethod = EncryptedData.addChildElement("EncryptionMethod"); // {
        name = soapEnv2.createName("Algorithm");
        EncryptionMethod.addAttribute(name, "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gost28147"); // /
        SOAPElement KeyInfo = EncryptedData.addChildElement("KeyInfo", null, "http://www.w3.org/2000/09/xmldsig#"); // {
        SOAPElement EncryptedKey = KeyInfo.addChildElement("EncryptedKey", null, "http://www.w3.org/2001/04/xmlenc#"); // {
        SOAPElement EncryptionMethod2 = EncryptedKey.addChildElement("EncryptionMethod"); // {
        name = soapEnv2.createName("Algorithm");
        EncryptionMethod2.addAttribute(name, "urn:ietf:params:xml:ns:cpxmlsec:algorithms:transport-gost2001"); // /
        SOAPElement KeyInfo2 = EncryptedKey.addChildElement("KeyInfo", null, "http://www.w3.org/2000/09/xmldsig#"); // {
        SOAPElement X509Data = KeyInfo2.addChildElement("X509Data"); // {
        SOAPElement X509Certificate = X509Data.addChildElement("X509Certificate"); // /
        SOAPElement CipherData = EncryptedKey.addChildElement("CipherData"); // {
        SOAPElement CipherValue = CipherData.addChildElement("CipherValue"); // /
        SOAPElement CipherData2 = EncryptedData.addChildElement("CipherData"); // {
        SOAPElement CipherValue2 = CipherData2.addChildElement("CipherValue"); // /

        //Шифрование подписанного документа
        org.w3c.dom.Document doc =
                startEncrypt(Certificate.ExtractCertFromCertStore(
                        passwordCertStor,
                        aliasCert,
                        pathToCert), nameFile);

        //Передача данных в сформированный каркас
        NodeList nList = doc.getElementsByTagName("xenc:EncryptedData");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            org.w3c.dom.Node nNode = nList.item(temp);
            if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                //  X509Certificate.addTextNode(eElement.getElementsByTagName("ds:X509Certificate").item(0).getTextContent());
                CipherValue.addTextNode(eElement.getElementsByTagName("xenc:CipherValue").item(0).getTextContent());
                CipherValue2.addTextNode(eElement.getElementsByTagName("xenc:CipherValue").item(1).getTextContent());
            }
        }

        X509Certificate.addTextNode(Certificate.certToBase64(Certificate.GetCertificateFromStorage(GlobalVariables.moAlias)));
        //сохранение в файл
        saveSoapToXml("Encrypted.xml", soapMessage);

        return soapMessage;
    }


    //TODO Убрать Encrypt

    /**
     * Зашифрование документа doc на sessionKey.
     *
     * @param cert Сертификат, на котором проходит шифрование
     * @return шифрованный документ
     * @throws Exception ошибки шифрования
     */
    private static Document StartEncrypt2(X509Certificate cert, String strMess) throws Exception {
        XmlInit.init();

        SOAPMessage message = stringToSoap(strMess);
        Document doc = message.getSOAPPart().getEnvelope().getOwnerDocument();

        //Создание случайного сессионного ключа.
        final KeyGenerator kg = KeyGenerator.getInstance("GOST28147");
        final SecretKey sessionKey = kg.generateKey();
        //Зашифрование сессионного ключа.
        EncryptedKey encryptedKey = wrapKey(doc, sessionKey, cert);
        //зашифрование документа
        Element element = doc.getDocumentElement();
        XMLCipher xmlCipher = XMLCipher.getInstance(Consts.URI_GOST_CIPHER);
        xmlCipher.init(XMLCipher.ENCRYPT_MODE, sessionKey);
        // добавляем шифрованный ключ.
        EncryptedData encryptedData = xmlCipher.getEncryptedData();
        KeyInfo keyInfo = new KeyInfo(doc);
        keyInfo.add(encryptedKey);
        encryptedData.setKeyInfo(keyInfo);
        //зашифрование документа
        xmlCipher.doFinal(doc, element, false);

        return doc;
    }


    public static String createXmlAndEncrypt(String message)
            throws Exception {

        //Cоздание каркаса XML-Encrypt
        MessageFactory mf = MessageFactory.newInstance();
        SOAPMessage soapMessage = mf.createMessage();
        SOAPEnvelope soapEnv = soapMessage.getSOAPPart().getEnvelope();
        SOAPBody soapBody = soapEnv.getBody();
        SOAPElement EncryptedData = soapBody.addChildElement("EncryptedData", null, "http://www.w3.org/2001/04/xmlenc#");
        Name name = soapEnv.createName("Type");
        EncryptedData.addAttribute(name, "http://www.w3.org/2001/04/xmlenc#Element");
        SOAPElement EncryptionMethod = EncryptedData.addChildElement("EncryptionMethod");
        name = soapEnv.createName("Algorithm");
        EncryptionMethod.addAttribute(name, "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gost28147");
        SOAPElement KeyInfo = EncryptedData.addChildElement("KeyInfo", null, "http://www.w3.org/2000/09/xmldsig#");
        SOAPElement EncryptedKey = KeyInfo.addChildElement("EncryptedKey", null, "http://www.w3.org/2001/04/xmlenc#");
        SOAPElement EncryptionMethod2 = EncryptedKey.addChildElement("EncryptionMethod");
        name = soapEnv.createName("Algorithm");
        EncryptionMethod2.addAttribute(name, "urn:ietf:params:xml:ns:cpxmlsec:algorithms:transport-gost2001");
        SOAPElement X509Data = KeyInfo.addChildElement("X509Data");
        SOAPElement X509Certificate = X509Data.addChildElement("X509Certificate");
        SOAPElement CipherData = EncryptedKey.addChildElement("CipherData");
        SOAPElement CipherValue = CipherData.addChildElement("CipherValue");
        SOAPElement CipherData2 = EncryptedData.addChildElement("CipherData");
        SOAPElement CipherValue2 = CipherData2.addChildElement("CipherValue");

        org.w3c.dom.Document doc =
                StartEncrypt2(Certificate.ExtractCertFromCertStore(
                        passwordCertStor,
                        aliasCert,
                        pathToCert), message);

        //Передача данных в сформированный каркас
        NodeList nList = doc.getElementsByTagName("xenc:EncryptedData");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            org.w3c.dom.Node nNode = nList.item(temp);
            if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                CipherValue.addTextNode(eElement.getElementsByTagName("xenc:CipherValue").item(0).getTextContent());
                CipherValue2.addTextNode(eElement.getElementsByTagName("xenc:CipherValue").item(1).getTextContent());
            }
        }

        //TODO delGlobal
        X509Certificate.addTextNode(Certificate.certToBase64(Certificate.GetCertificateFromStorage(GlobalVariables.moAlias)));

        return soapMessageToString(soapMessage);
    }
}