package ru.rashgild.service.existingLNNum;

import ru.rashgild.service.newLNNumRange.NewLnNumRange_start;
import ru.rashgild.signAndCrypt.Encrypt;
import ru.rashgild.signAndCrypt.Sign;
import ru.rashgild.utils.GlobalVariables;
import ru.rashgild.utils.XmlUtils;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;

public class ExistingLNNumRange {

    /**
     * Меняет перехваченное сообщение под нужный шаблон
     *
     * @param soapMsg передается из Инъектера
     */
    public static SOAPMessage Start(SOAPMessage soapMsg) {
        try {
            NewLnNumRange_start.Create(soapMsg);
            soapMsg = Sign.signation();

            XmlUtils.saveSoapToXml("ExistingLNNumRange.xml", soapMsg);
            GlobalVariables.Request = XmlUtils.soapMessageToString(soapMsg);
            MessageFactory mf = MessageFactory.newInstance();

            SOAPMessage NewMessg = mf.createMessage();
            NewMessg = Encrypt.createXmlAndEncrypt(NewMessg, "ExistingLNNumRange.xml");

            return NewMessg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soapMsg;
    }
}
