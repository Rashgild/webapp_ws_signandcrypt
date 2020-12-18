package main.java.ru.rashgild.service_operations.disableLn;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;

import main.java.ru.rashgild.utils.GlobalVariables;
import main.java.ru.rashgild.utils.XmlUtils;
import main.java.ru.rashgild.service_operations.newLNNumRange.NewLnNumRange_start;
import main.java.ru.rashgild.signAndCrypt.Encrypt;
import main.java.ru.rashgild.signAndCrypt.Sign;

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
            NewMessg = Encrypt.createXmlAndEncrypt(NewMessg, "DisableLn.xml");

            return NewMessg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soapMsg;
    }
}
