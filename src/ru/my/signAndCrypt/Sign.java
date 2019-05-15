package ru.my.signAndCrypt;

import java.io.FileInputStream;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.apache.ws.security.message.WSSecHeader;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import ru.my.utils.GlobalVariables;

public class Sign {

    public static SOAPMessage signationByParametrs(SOAPMessage soapMessage, String Actor, String Refer, String Alias,
                                                   String Password, String ELN) throws Exception {

        Logger logger = Logger.getLogger("");
        X509Certificate cert = Certificate.GetCertificateFromStorage(Alias);
        PrivateKey privateKey = Certificate.GetPrivateKey(Password, Alias);
        MessageFactory mf = MessageFactory.newInstance();
        logger.info("signing>>>>>>>>>>>>>");
        try {
            Document doc = soapMessage.getSOAPPart().getEnvelope().getOwnerDocument();

            WSSecHeader header = new WSSecHeader();
            header.setActor(Actor); ///TODO: name actor (sender?)
            header.setMustUnderstand(false);

            header.insertSecurityHeader(soapMessage.getSOAPPart().getEnvelope().getOwnerDocument());
            // Elem of sign.
            Element token = header.getSecurityHeader();
            // loading prov.
            Provider xmlDSigProvider = new ru.CryptoPro.JCPxml.dsig.internal.dom.XMLDSigRI();
            //add transf on doc and point SignedInfo (согласно методическим рекомендациям СМЭВ)
            final Transforms transforms = new Transforms(doc);
            transforms.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", xmlDSigProvider);
            //Преобразования над узлом ds:SignedInfo:
            List<Transform> transformList = new ArrayList<>();
            Transform transformC14N = fac.newTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS, (XMLStructure) null);
            transformList.add(transformC14N);
            //DOMSignContext signContext = new DOMSignContext(privateKey, token);
            // ref on sign data with hash alg on ГОСТ 34.11.
            // TODO refrence on sign element?.

            Reference ref = fac.newReference(Refer, fac.newDigestMethod("http://www.w3.org/2001/04/xmldsig-more#gostr3411", null),
                    transformList, null, null);

            //Задаём алгоритм подписи:
            SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE,
                    (C14NMethodParameterSpec) null), fac.newSignatureMethod("http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411", null), Collections.singletonList(ref));
            //Создаём узел ds:KeyInfo с информацией о сертификате:
            KeyInfoFactory kif = fac.getKeyInfoFactory();

            X509Data x509d = kif.newX509Data(Collections.singletonList(cert));
            KeyInfo ki = kif.newKeyInfo(Collections.singletonList(x509d));
            //Подписываем данные в элементе token:
            XMLSignature sig = fac.newXMLSignature(si, ki); //
            DOMSignContext signContext = new DOMSignContext(privateKey, token);  //

            sig.sign(signContext);  // EXC
            //Следующий этап — поместить узел ds:Signature и сертификат (X509Certificate) в узел wsse:Security,
            //причём сертификат нужно удалить из ds:KeyInfo и оставить там ссылку на wsse:BinarySecurityToken с сертификатом:
            // Узел подписи Signature.
            Element sigE = (Element) XPathAPI.selectSingleNode(signContext.getParent(), "//ds:Signature");
            // Блок данных KeyInfo.
            org.w3c.dom.Node keyE = XPathAPI.selectSingleNode(sigE, "//ds:KeyInfo", sigE);
            // Элемент SenderCertificate, который должен содержать сертификат.
            Element cerVal = (Element) XPathAPI.selectSingleNode(token, "//*[@wsu:Id='SenderCertificate']");


            //cerVal.setTextContent(XPathAPI.selectSingleNode(keyE, "X509Certificate", keyE).getFirstChild().getNodeValue());
            org.w3c.dom.Node certi = XPathAPI.selectSingleNode(sigE, "//ds:X509Certificate", sigE);
            keyE.removeChild(XPathAPI.selectSingleNode(keyE, "//ds:X509Data", keyE));
            org.w3c.dom.Node str = keyE.appendChild(doc.createElementNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse:SecurityTokenReference"));
            Element strRef = (Element) str.appendChild(doc.createElementNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse:Reference"));
            strRef.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");

            strRef.setAttribute("URI", "#http://eln.fss.ru/actor/mo/" + GlobalVariables.ogrnMo + "/ELN_" + ELN);
            header.getSecurityHeader().appendChild(sigE);

            keyE = XPathAPI.selectSingleNode(sigE, "//wsse:Security", sigE);
            Element strRef2 = (Element) keyE.appendChild(doc.createElementNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wse:BinarySecurityToken"));
            strRef2.setAttribute("EncodingType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary");
            strRef2.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");
            strRef2.setAttribute("wsu:Id", "http://eln.fss.ru/actor/mo/" + GlobalVariables.ogrnMo + "/ELN_" + ELN);
            strRef2.setTextContent(certi.getFirstChild().getNodeValue());

            return soapMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String DIG_GOST_2001 = "http://www.w3.org/2001/04/xmldsig-more#gostr3411";
    private static final String DIG_GOST_2012 = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34112012-256";
    private static final String SIGN_GOST_2001 = "http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411";
    private static final String SIGN_GOST_2012 = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34102012-gostr34112012-256";

    private static final String GOST_2001 = "GOST2001";

    public static SOAPMessage signationByParametrs(String Actor, String Refer, String Alias,
                                                   String Password, String ELN, String gost) throws Exception {

        Logger logger = Logger.getLogger("");
        X509Certificate cert = Certificate.GetCertificateFromStorage(Alias);
        PrivateKey privateKey = Certificate.GetPrivateKey(Password, Alias);
        MessageFactory mf = MessageFactory.newInstance();
        SOAPMessage message;
        message = mf.createMessage();

        logger.info("100) Go");
        SOAPPart soapPart = message.getSOAPPart();
        //TODO What the path!?Send on config... If from file... mb from message?
        FileInputStream is = new FileInputStream(GlobalVariables.pathtosave + GlobalVariables.signXMLFileName);
        soapPart.setContent(new StreamSource(is));
        message.getSOAPPart().getEnvelope().addNamespaceDeclaration("ds", "http://www.w3.org/2000/09/xmldsig#");
        Document doc = message.getSOAPPart().getEnvelope().getOwnerDocument();

        //header for incl info about sign:
        WSSecHeader header = new WSSecHeader();

        //"http://eln.fss.ru/actor/mo/1037726008110/ELN_126876309382"
        header.setActor(Actor); ///TODO: name actor (sender?)
        header.setMustUnderstand(false);

        header.insertSecurityHeader(message.getSOAPPart().getEnvelope().getOwnerDocument());
        // Elem of sign.
        Element token = header.getSecurityHeader();
        // loading prov.
        Provider xmlDSigProvider = new ru.CryptoPro.JCPxml.dsig.internal.dom.XMLDSigRI();
        //add transf on doc and point SignedInfo (согласно методическим рекомендациям СМЭВ)
        final Transforms transforms = new Transforms(doc);
        transforms.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", xmlDSigProvider);
        //Преобразования над узлом ds:SignedInfo:
        List<Transform> transformList = new ArrayList<>();
        Transform transformC14N = fac.newTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS, (XMLStructure) null);
        transformList.add(transformC14N);
        //DOMSignContext signContext = new DOMSignContext(privateKey, token);
        // ref on sign data with hash alg on ГОСТ 34.11.
        // TODO refrence on sign element?.

        Reference ref = fac.newReference(Refer, fac.newDigestMethod(gost.equals(GOST_2001) ? DIG_GOST_2001 : DIG_GOST_2012, null),
                transformList, null, null);

        //Задаём алгоритм подписи:
        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE,
                (C14NMethodParameterSpec) null), fac.newSignatureMethod(gost.equals(GOST_2001) ? SIGN_GOST_2001 : SIGN_GOST_2012, null), Collections.singletonList(ref));
        //Создаём узел ds:KeyInfo с информацией о сертификате:
        KeyInfoFactory kif = fac.getKeyInfoFactory();

        X509Data x509d = kif.newX509Data(Collections.singletonList(cert));
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(x509d));
        //Подписываем данные в элементе token:
        XMLSignature sig = fac.newXMLSignature(si, ki); //
        DOMSignContext signContext = new DOMSignContext(privateKey, token);  //

        sig.sign(signContext);  // EXC
        //Следующий этап — поместить узел ds:Signature и сертификат (X509Certificate) в узел wsse:Security,
        //причём сертификат нужно удалить из ds:KeyInfo и оставить там ссылку на wsse:BinarySecurityToken с сертификатом:
        // Узел подписи Signature.
        Element sigE = (Element) XPathAPI.selectSingleNode(signContext.getParent(), "//ds:Signature");
        // Блок данных KeyInfo.
        org.w3c.dom.Node keyE = XPathAPI.selectSingleNode(sigE, "//ds:KeyInfo", sigE);
        // Элемент SenderCertificate, который должен содержать сертификат.
        Element cerVal = (Element) XPathAPI.selectSingleNode(token, "//*[@wsu:Id='SenderCertificate']");

        //cerVal.setTextContent(XPathAPI.selectSingleNode(keyE, "X509Certificate", keyE).getFirstChild().getNodeValue());
        org.w3c.dom.Node certi = XPathAPI.selectSingleNode(sigE, "//ds:X509Certificate", sigE);
        keyE.removeChild(XPathAPI.selectSingleNode(keyE, "//ds:X509Data", keyE));
        org.w3c.dom.Node str = keyE.appendChild(doc.createElementNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse:SecurityTokenReference"));
        Element strRef = (Element) str.appendChild(doc.createElementNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse:Reference"));
        strRef.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");

        strRef.setAttribute("URI", "#http://eln.fss.ru/actor/mo/" + GlobalVariables.ogrnMo + "/ELN_" + ELN);
        header.getSecurityHeader().appendChild(sigE);

        keyE = XPathAPI.selectSingleNode(sigE, "//wsse:Security", sigE);
        Element strRef2 = (Element) keyE.appendChild(doc.createElementNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wse:BinarySecurityToken"));
        strRef2.setAttribute("EncodingType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary");
        strRef2.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");
        strRef2.setAttribute("wsu:Id", "http://eln.fss.ru/actor/mo/" + GlobalVariables.ogrnMo + "/ELN_" + ELN);
        strRef2.setTextContent(certi.getFirstChild().getNodeValue());
        return message;
    }


    public static SOAPMessage signation() {

        try {
            X509Certificate cert = Certificate.GetCertificateFromStorage(GlobalVariables.moAlias);
            PrivateKey privateKey = Certificate.GetPrivateKey(GlobalVariables.moPass, GlobalVariables.moAlias);

            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage message = mf.createMessage();  // ЕСЛИ берем из файла
            SOAPPart soapPart = message.getSOAPPart();
            FileInputStream is = new FileInputStream(GlobalVariables.pathtosave + "tempSkeleton.xml"); // ЕСЛИ берем из файла
            soapPart.setContent(new StreamSource(is)); // ЕСЛИ берем из файла
            message.getSOAPPart().getEnvelope().addNamespaceDeclaration("ds", "http://www.w3.org/2000/09/xmldsig#");
            Document doc = message.getSOAPPart().getEnvelope().getOwnerDocument();
            //Добавляем заголовки для помещения информации о подписи:
            WSSecHeader header = new WSSecHeader();

            header.setActor("http://eln.fss.ru/actor/mo/OGRN_" + GlobalVariables.ogrnMo);
            header.setMustUnderstand(false);

            header.insertSecurityHeader(message.getSOAPPart().getEnvelope().getOwnerDocument());
            // Элемент подписи.
            Element token = header.getSecurityHeader();

            // Загрузка провайдера.
            Provider xmlDSigProvider = new ru.CryptoPro.JCPxml.dsig.internal.dom.XMLDSigRI();
            //logger.debug("xmlDSigProvider: {}", xmlDSigProvider);

            //Добавляем описание преобразований над документом и узлом SignedInfo согласно методическим рекомендациям СМЭВ.
            final Transforms transforms = new Transforms(doc);
            transforms.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", xmlDSigProvider);


            //Преобразования над узлом ds:SignedInfo:
            List<Transform> transformList = new ArrayList<>();
            Transform transformC14N = fac.newTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS, (XMLStructure) null);
            transformList.add(transformC14N);

            //TODO!
            // Ссылка на подписываемые данные с алгоритмом хеширования ГОСТ 34.11.
            Reference ref = fac.newReference("#OGRN_" + GlobalVariables.ogrnMo, fac.newDigestMethod(DIG_GOST_2012, null),
                    transformList, null, null);
            /*Reference ref = fac.newReference("#OGRN_" + GlobalVariables.ogrnMo, fac.newDigestMethod("http://www.w3.org/2001/04/xmldsig-more#gostr3411", null),
                    transformList, null, null);*/

            //Задаём алгоритм подписи:
            SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE,
                    (C14NMethodParameterSpec) null), fac.newSignatureMethod(SIGN_GOST_2012, null), Collections.singletonList(ref));
            //Создаём узел ds:KeyInfo с информацией о сертификате:
            KeyInfoFactory kif = fac.getKeyInfoFactory();
            //System.out.println("***"+cert);
            X509Data x509d = kif.newX509Data(Collections.singletonList(cert));
            KeyInfo ki = kif.newKeyInfo(Collections.singletonList(x509d));
            //Подписываем данные в элементе token:
            javax.xml.crypto.dsig.XMLSignature sig = fac.newXMLSignature(si, ki); //
            DOMSignContext signContext = new DOMSignContext(privateKey, token);  //
            sig.sign(signContext);  // EXC
            //Следующий этап — поместить узел ds:Signature и сертификат (X509Certificate) в узел wsse:Security,
            //причём сертификат нужно удалить из ds:KeyInfo и оставить там ссылку на wsse:BinarySecurityToken с сертификатом:
            // Узел подписи Signature.
            Element sigE = (Element) XPathAPI.selectSingleNode(signContext.getParent(), "//ds:Signature");
            // Блок данных KeyInfo.
            Node keyE = XPathAPI.selectSingleNode(sigE, "//ds:KeyInfo", sigE);
            // Элемент SenderCertificate, который должен содержать сертификат.
            Element cerVal = (Element) XPathAPI.selectSingleNode(token, "//*[@wsu:Id='SenderCertificate']");


            Node certi = XPathAPI.selectSingleNode(sigE, "//ds:X509Certificate", sigE);
            keyE.removeChild(XPathAPI.selectSingleNode(keyE, "//ds:X509Data", keyE));
            Node str = keyE.appendChild(doc.createElementNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse:SecurityTokenReference"));
            Element strRef = (Element) str.appendChild(doc.createElementNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse:Reference"));
            strRef.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");
            strRef.setAttribute("URI", "http://eln.fss.ru/actor/mo/1023000855020");
            header.getSecurityHeader().appendChild(sigE);

            keyE = XPathAPI.selectSingleNode(sigE, "//wsse:Security", sigE);
            Element strRef2 = (Element) keyE.appendChild(doc.createElementNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wse:BinarySecurityToken"));
            strRef2.setAttribute("EncodingType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary");
            strRef2.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");
            strRef2.setAttribute("wsu:Id", "http://eln.fss.ru/actor/mo/1023000855020");
            strRef2.setTextContent(certi.getFirstChild().getNodeValue());

            return message;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
