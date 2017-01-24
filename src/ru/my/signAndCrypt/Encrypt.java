package ru.my.signAndCrypt;

import org.apache.xml.security.encryption.EncryptedData;
import org.apache.xml.security.encryption.EncryptedKey;
import org.apache.xml.security.encryption.XMLCipher;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.keys.content.X509Data;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.CryptoPro.JCPxml.Consts;
import ru.CryptoPro.JCPxml.XmlInit;
import ru.my.helpers_operations.GlobalVariables;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.soap.*;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.security.cert.X509Certificate;

import static ru.my.helpers_operations.GlobalVariables.*;

/**
 * Created by rkurbanov on 10.11.16.
 */
public class Encrypt {

    /**
     * Зашифрование документа doc на sessionKey.
     * @param cert Сертификат, на котором проходит шифрование
     * @return шифрованный документ
     * @throws Exception ошибки шифрования
     */
    public  static Document StartEncrypt(X509Certificate cert , String nameFile) throws Exception
    {
        XmlInit.init();
        MessageFactory mf = MessageFactory.newInstance();
        SOAPMessage message = mf.createMessage();
        SOAPPart soapPart = message.getSOAPPart();

        //FileInputStream is = new FileInputStream("C:\\Documents and Settings\\rkurbanov\\IdeaProjects\\FSS_Client_test\\My.xml"); // ЕСЛИ берем из файла
        System.out.println(GlobalVariables.pathtosave+nameFile);
        FileInputStream is = new FileInputStream(GlobalVariables.pathtosave+nameFile); // ЕСЛИ берем из файла


        soapPart.setContent(new StreamSource(is));
        Document doc = message.getSOAPPart().getEnvelope().getOwnerDocument();

        //Создание случайного сессионного ключа.
        final KeyGenerator kg = KeyGenerator.getInstance("GOST28147");
        final SecretKey sessionKey = kg.generateKey();
        //SecretKey sessionKey = KeyGenerator.getInstance("GOST28147-89").generateKey();

        //Зашифрование сессионного ключа.
        EncryptedKey encryptedKey = wrapKey(doc, sessionKey, cert);
        //  EncryptedKey encryptedKey = wrapKey(doc, pk, cert);
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
     * @param doc xml документ
     * @param sessionKey случайный сессионный ключ.
     * @param cert сертификат
     * @return зашифрованный ключ
     * @throws Exception ошибки шифрования
     */
    public static EncryptedKey wrapKey(Document doc, SecretKey sessionKey,
                                       X509Certificate cert)
            throws Exception {

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
     * @param soapMessage перехваченное сообщение
     * @Out Файл "tempEncrypted.xml"
     */
    public static SOAPMessage CreateXMLAndEncrypt(SOAPMessage soapMessage, String nameFile)
            throws Exception {
        //Cоздание каркаса XML-Encrypt
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope soapEnv2 = soapMessage.getSOAPPart().getEnvelope();
        SOAPHeader soapHeader2 = soapEnv2.getHeader();
        SOAPBody soapBody2 = soapEnv2.getBody();
        SOAPElement EncryptedData = soapBody2.addChildElement("EncryptedData",null,"http://www.w3.org/2001/04/xmlenc#");
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
                StartEncrypt(Certificate.ExtractCertFromCertStore(
                        passwordCertStor,
                        aliasCert,
                        pathToCert), nameFile);
        //org.w3c.dom.Document doc = EncryptDecrypt.StartEncrypt2(Certificate.ExtractCertFromCertStore());

        //Передача данных в сформированный каркас
        NodeList nList = doc.getElementsByTagName("xenc:EncryptedData");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            org.w3c.dom.Node nNode = nList.item(temp);
            // System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                //  X509Certificate.addTextNode(eElement.getElementsByTagName("ds:X509Certificate").item(0).getTextContent());
                CipherValue.addTextNode(eElement.getElementsByTagName("xenc:CipherValue").item(0).getTextContent());
                CipherValue2.addTextNode(eElement.getElementsByTagName("xenc:CipherValue").item(1).getTextContent());
            }
        }

        X509Certificate.addTextNode(Certificate.certToBase64(Certificate.GetCertificateFromStorage(GlobalVariables.moAlias)));
        //сохранение в файл

        //Doc.SaveSOAPToXML("tempEncrypted.xml", soapMessage);
        return soapMessage;
    }
}

/**
 * Зашифрование документа doc на sessionKey.
 * @param doc документ, который будем шифровать
 * @param sessionKey сессионный ключ шифрования
 * @param encryptedKey зашифрованный sessionKey будет добавлен в документ
 * @return шифрованный документ
 * @throws Exception ошибки шифрования
 */
 /*
public static Document encrypt2(Document doc, SecretKey sessionKey,
                                EncryptedKey encryptedKey) throws Exception {
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
}*/
