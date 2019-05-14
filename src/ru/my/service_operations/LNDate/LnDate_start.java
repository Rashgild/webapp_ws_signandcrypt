package ru.my.service_operations.LNDate;

import ru.my.utils.GlobalVariables;
import ru.my.utils.XmlUtils;
import ru.my.service_operations.newLNNumRange.NewLnNumRange_start;
import ru.my.signAndCrypt.Encrypt;
import ru.my.signAndCrypt.Sign;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;

public class LnDate_start {

    public static SOAPMessage Start(SOAPMessage soapMsg)
    {
        try {
            NewLnNumRange_start.Create(soapMsg);
            soapMsg= Sign.signation();

            XmlUtils.saveSoapToXml("GetLnDate.xml", soapMsg);
            GlobalVariables.Request = XmlUtils.soapMessageToString(soapMsg);
            MessageFactory mf = MessageFactory.newInstance();

            SOAPMessage NewMessg = mf.createMessage();
            NewMessg = Encrypt.CreateXMLAndEncrypt(NewMessg, "GetLnDate.xml");

            return NewMessg;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return soapMsg;
    }
}
