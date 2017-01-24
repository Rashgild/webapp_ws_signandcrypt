package ru.my.signAndCrypt;

import com.sun.org.apache.xml.internal.security.transforms.TransformationException;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.message.WSSecHeader;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.CryptoPro.JCPxml.xmldsig.JCPXMLDSigInit;
import ru.my.helpers_operations.GlobalVariables;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by rkurbanov on 17.05.2017.
 */
public class Sign {


    public static SOAPMessage SignationByParametrs(String Actor, String Refer, String Alias,
                                                   String Password,String ELN) throws Exception {



        ResourceBundle resource = ResourceBundle.getBundle("config");

        System.out.println("Get Cert");
        X509Certificate cert = Certificate.GetCertificateFromStorage(Alias);
        System.out.println("Get privateKey");
        PrivateKey privateKey = Certificate.GetPrivateKey(Password,Alias);

        //  Read frm file?
        //System.out.println("ready...");
        MessageFactory mf = MessageFactory.newInstance();
        SOAPMessage message;
        message = mf.createMessage();


        SOAPPart soapPart = message.getSOAPPart();

        //TODO What the path!?Send on config... If from file... mb from message?
        FileInputStream is = new FileInputStream(GlobalVariables.pathtosave+GlobalVariables.signXMLFileName);
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
        List<Transform> transformList = new ArrayList<Transform>();
        Transform transformC14N = fac.newTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS, (XMLStructure) null);
        transformList.add(transformC14N);
        //DOMSignContext signContext = new DOMSignContext(privateKey, token);
        // ref on sign data with hash alg on ГОСТ 34.11.
        // TODO refrence on sign element?.

        Reference ref = fac.newReference(Refer, fac.newDigestMethod ("http://www.w3.org/2001/04/xmldsig-more#gostr3411", null),
                transformList, null, null);

        //Задаём алгоритм подписи:
        SignedInfo si = fac.newSignedInfo( fac.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE,
                (C14NMethodParameterSpec) null), fac.newSignatureMethod("http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411",    null), Collections.singletonList(ref));
        //Создаём узел ds:KeyInfo с информацией о сертификате:
        KeyInfoFactory kif = fac.getKeyInfoFactory();
        //System.out.println("***"+cert);
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

        /*****-----*****/

        //cerVal.setTextContent(XPathAPI.selectSingleNode(keyE, "X509Certificate", keyE).getFirstChild().getNodeValue());

        org.w3c.dom.Node certi = XPathAPI.selectSingleNode(sigE, "//ds:X509Certificate", sigE);
        keyE.removeChild(XPathAPI.selectSingleNode(keyE, "//ds:X509Data", keyE));
        org.w3c.dom.Node str = keyE.appendChild(doc.createElementNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse:SecurityTokenReference"));
        Element strRef = (Element)str.appendChild(doc.createElementNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse:Reference"));
        strRef.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");

        strRef.setAttribute("URI", "#http://eln.fss.ru/actor/mo/"+GlobalVariables.ogrnMo+"/ELN_"+ELN);
        header.getSecurityHeader().appendChild(sigE);

        keyE = XPathAPI.selectSingleNode(sigE, "//wsse:Security", sigE);
        Element strRef2 = (Element)keyE.appendChild(doc.createElementNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wse:BinarySecurityToken"));
        strRef2.setAttribute("EncodingType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary");
        strRef2.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");

        strRef2.setAttribute("wsu:Id", "http://eln.fss.ru/actor/mo/"+GlobalVariables.ogrnMo+"/ELN_"+ELN);
        strRef2.setTextContent(certi.getFirstChild().getNodeValue());
        System.out.println("Возвращаю");
        return message;
    }


}
