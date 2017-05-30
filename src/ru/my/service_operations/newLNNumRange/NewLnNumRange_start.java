package ru.my.service_operations.newLNNumRange;

import ru.my.helpers_operations.GlobalVariables;
import ru.my.helpers_operations.WorkWithXML;
import ru.my.service_operations.xmlFileLnLpu.*;
import ru.my.signAndCrypt.Encrypt;
import ru.my.signAndCrypt.Sign;

import javax.xml.soap.*;
import java.io.IOException;

//Created by rashgild on 19.05.2017.

public class NewLnNumRange_start {

    /**
     * Меняет перехваченное сообщение под нужный шаблон
     * @param soapMsg передается из Инъектера
     */
    public static SOAPMessage Start(SOAPMessage soapMsg)
    {
        try {
            Create(soapMsg);
            soapMsg= Sign.Signation();
            WorkWithXML.SaveSOAPToXML("GetNumSigned.xml", soapMsg);
            GlobalVariables.Request = WorkWithXML.SoapMessageToString(soapMsg);
            MessageFactory mf = MessageFactory.newInstance();

            SOAPMessage NewMessg = mf.createMessage();
            NewMessg = Encrypt.CreateXMLAndEncrypt(NewMessg, "GetNumSigned.xml");

            return NewMessg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soapMsg;
    }

    /**
     * GlobalVariables.ogrn!
     * Меняет перехваченное сообщение под нужный шаблон
     * @param soapMessage перехваченное сообщение
     */
    private static void Create(SOAPMessage soapMessage)
            throws SOAPException, IOException {
        SOAPEnvelope soapEnv = soapMessage.getSOAPPart().getEnvelope();
        SOAPHeader soapHeader = soapEnv.getHeader();
        soapEnv.addHeader();
        //soapMessage
        soapEnv.addNamespaceDeclaration("xsi","http://www.w3.org/2001/XMLSchema");
        soapEnv.addNamespaceDeclaration("xsd","http://www.w3.org/2001/XMLSchema-instance");
        soapEnv.addNamespaceDeclaration("wsse","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        soapEnv.addNamespaceDeclaration("wsu","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");

        SOAPBody soapBody = soapEnv.getBody();
        Name name = soapEnv.createName("Id");
        soapBody.addAttribute(name, "OGRN_"+ GlobalVariables.ogrnMo);
        soapMessage.saveChanges();
        WorkWithXML.SaveSOAPToXML("tempSkeleton.xml", soapMessage);
    }


}
