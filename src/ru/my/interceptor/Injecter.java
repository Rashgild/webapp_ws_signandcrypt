package ru.my.interceptor;


import org.apache.log4j.Logger;
import ru.my.helpers_operations.GlobalVariables;
import ru.my.helpers_operations.WorkWithXML;
import ru.my.service_operations.newLNNumRange.NewLnNumRange_start;
import ru.my.service_operations.xmlFileLnLpu.PrParseFileLnLpu_start;
import ru.my.signAndCrypt.VerifyAndDecrypt;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;

//Created by rashgild on 19.11.2016.


public class Injecter implements SOAPHandler<SOAPMessageContext> {

    @Override
    public boolean handleMessage(SOAPMessageContext context) {

        Logger logger=Logger.getLogger("simple");
        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (isRequest) {
            logger.info("2) Intercept request!");
            SOAPMessage soapMsg = context.getMessage();
            if(WhatTheFunc(soapMsg)==1){
                logger.info("2.1) initialized PrParseFileLnLpu_start!");
                soapMsg  = PrParseFileLnLpu_start.Start(GlobalVariables.requestParam);
            }
            if(WhatTheFunc(soapMsg)==2){
                logger.info("2.1) initialized NewLNNumRange_start!");
                soapMsg  = NewLnNumRange_start.Start(soapMsg);
            }
            logger.info("Send Request!");
            context.setMessage(soapMsg);
        }

        if(!isRequest)
        {
            logger.info("Get Response");
            try {
                SOAPMessage msg = context.getMessage();
                msg = VerifyAndDecrypt.Start(msg);
                GlobalVariables.Response = WorkWithXML.SoapMessageToString(msg);
                context.setMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context)
    {
        SOAPMessage msg = context.getMessage();
        Logger logger=Logger.getLogger("simple");
        logger.error(WorkWithXML.SoapMessageToString(msg));
        return false;
    }

    @Override
    public void close(MessageContext context) {    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    private static int WhatTheFunc(SOAPMessage msg) {
        try {
            String strdoc = WorkWithXML.DocToString(msg.getSOAPPart().getEnvelope().getOwnerDocument());

            if (strdoc.contains("prParseFilelnlpu")) {
                GlobalVariables.Type = "prParseFilelnlpu";
                return 1;
            }
            if (strdoc.contains("getNewLNNumRange")) {
                GlobalVariables.Type = "getNewLNNumRange";
                return 2;
            }
        }catch (SOAPException e) {e.printStackTrace(); }
        return 0;
    }
}