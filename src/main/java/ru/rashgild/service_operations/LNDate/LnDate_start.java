package ru.rashgild.service_operations.LNDate;

import ru.rashgild.utils.CertificateUtils;
import ru.rashgild.utils.GlobalVariables;
import ru.rashgild.utils.XmlUtils;
import ru.rashgild.service_operations.newLNNumRange.NewLnNumRange_start;
import ru.rashgild.signAndCrypt.Encrypt;
import ru.rashgild.signAndCrypt.Sign;

import javax.xml.soap.*;
import java.security.cert.X509Certificate;

public class LnDate_start {

    public static SOAPMessage Start(SOAPMessage soapMsg)
    {
        try {
            NewLnNumRange_start.Create(soapMsg);
            soapMsg = Sign.signation();

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
