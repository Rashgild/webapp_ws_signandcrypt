package ru.my.service_operations.LNDate;

import ru.my.helpers_operations.GlobalVariables;
import ru.my.helpers_operations.WorkWithXML;
import ru.my.service_operations.newLNNumRange.NewLnNumRange_start;
import ru.my.signAndCrypt.Encrypt;
import ru.my.signAndCrypt.Sign;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;

/**
 * Created by rkurbanov on 28.06.2017.
 */
public class LnDate_start {

    public static SOAPMessage Start(SOAPMessage soapMsg)
    {

        try {
            NewLnNumRange_start.Create(soapMsg);
            soapMsg= Sign.Signation();
            WorkWithXML.SaveSOAPToXML("GetLnDate.xml", soapMsg);
            GlobalVariables.Request = WorkWithXML.SoapMessageToString(soapMsg);
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
