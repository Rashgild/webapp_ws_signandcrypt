package ru.my.service_operations.disableLn;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;

import ru.my.utils.GlobalVariables;
import ru.my.utils.XmlUtils;
import ru.my.service_operations.newLNNumRange.NewLnNumRange_start;
import ru.my.signAndCrypt.Encrypt;
import ru.my.signAndCrypt.Sign;

public class DisableLn {

    /**
     * Меняет перехваченное сообщение под нужный шаблон
     *
     * @param soapMsg передается из Инъектера
     */
    public static SOAPMessage Start(SOAPMessage soapMsg) {
        try {
            NewLnNumRange_start.Create(soapMsg);
            soapMsg = Sign.signation();
            XmlUtils.saveSoapToXml("DisableLn.xml", soapMsg);
            GlobalVariables.Request = XmlUtils.soapMessageToString(soapMsg);
            MessageFactory mf = MessageFactory.newInstance();

            SOAPMessage NewMessg = mf.createMessage();
            NewMessg = Encrypt.CreateXMLAndEncrypt(NewMessg, "DisableLn.xml");

            return NewMessg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soapMsg;
    }
}
