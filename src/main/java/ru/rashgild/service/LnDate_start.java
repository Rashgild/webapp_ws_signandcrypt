package ru.rashgild.service;

import ru.rashgild.signAndCrypt.Encrypt;
import ru.rashgild.signAndCrypt.Sign;
import ru.rashgild.utils.GlobalVariables;
import ru.rashgild.utils.XmlUtils;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;

import static ru.rashgild.utils.CertificateUtils.addCertificateToHeader;

public class LnDate_start {

    public static SOAPMessage Start(SOAPMessage soapMsg) {
        try {
            NewLnNumRange.Create(soapMsg);
            soapMsg = Sign.signation();
            soapMsg = addCertificateToHeader(soapMsg);
            XmlUtils.saveSoapToXml("GetLnDate.xml", soapMsg);
            GlobalVariables.Request = XmlUtils.soapMessageToString(soapMsg);
            MessageFactory mf = MessageFactory.newInstance();

            SOAPMessage message = mf.createMessage();
            message = Encrypt.createXmlAndEncrypt(message, "GetLnDate.xml");

            return message;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return soapMsg;
    }
}
