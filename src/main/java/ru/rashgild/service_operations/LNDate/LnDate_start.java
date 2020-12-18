package main.java.ru.rashgild.service_operations.LNDate;

import main.java.ru.rashgild.utils.CertificateUtils;
import main.java.ru.rashgild.utils.GlobalVariables;
import main.java.ru.rashgild.utils.XmlUtils;
import main.java.ru.rashgild.service_operations.newLNNumRange.NewLnNumRange_start;
import main.java.ru.rashgild.signAndCrypt.Encrypt;
import main.java.ru.rashgild.signAndCrypt.Sign;

import javax.xml.soap.*;
import java.security.cert.X509Certificate;

public class LnDate_start {

    public static SOAPMessage Start(SOAPMessage soapMsg)
    {
        try {
            NewLnNumRange_start.Create(soapMsg);
            soapMsg= Sign.signation();

            X509Certificate cert = CertificateUtils.getCertificateFromKeyStorage(GlobalVariables.moAlias);
            SOAPEnvelope soapEnvelope = soapMsg.getSOAPPart().getEnvelope();
            SOAPHeader header1 = soapEnvelope.getHeader();
            SOAPElement x509Certificate =  header1.addChildElement("X509Certificate", null, "http://www.w3.org/2000/09/xmldsig#");
            x509Certificate.addTextNode(CertificateUtils.certToBase64(cert));

            XmlUtils.saveSoapToXml("GetLnDate.xml", soapMsg);
            GlobalVariables.Request = XmlUtils.soapMessageToString(soapMsg);
            MessageFactory mf = MessageFactory.newInstance();

            SOAPMessage NewMessg = mf.createMessage();
            NewMessg = Encrypt.createXmlAndEncrypt(NewMessg, "GetLnDate.xml");

            return NewMessg;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return soapMsg;
    }
}
