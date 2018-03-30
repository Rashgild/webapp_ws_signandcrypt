package ru.my.interceptor;


import org.apache.log4j.Logger;
import ru.my.helpers_operations.GlobalVariables;
import ru.my.helpers_operations.SQL;
import ru.my.helpers_operations.WorkWithXML;
import ru.my.service_operations.LNDate.LnDate_start;
import ru.my.service_operations.disableLn.DisableLn;
import ru.my.service_operations.existingLNNum.ExistingLNNumRange;
import ru.my.service_operations.newLNNum.NewLNNum;
import ru.my.service_operations.newLNNumRange.NewLnNumRange_start;
import ru.my.service_operations.xmlFileLnLpu.PrParseFileLnLpu_start;
import ru.my.signAndCrypt.VerifyAndDecrypt;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.IOException;
import java.util.Set;

//Created by rashgild on 19.11.2016.


public class Injecter implements SOAPHandler<SOAPMessageContext> {

    @Override
    public boolean handleMessage(SOAPMessageContext context) {

        Logger logger=Logger.getLogger("simple");
        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);


         try {
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

            if(WhatTheFunc(soapMsg)==3){
                logger.info("2.1) initialized NewLNNum_start!");
                soapMsg  = NewLNNum.Start(soapMsg);
            }

            if(WhatTheFunc(soapMsg)==4){
                logger.info("2.1) initialized getLNDate!");
                soapMsg  = LnDate_start.Start(soapMsg);
            }

            if(WhatTheFunc(soapMsg)==5){
                logger.info("2.1) initialized disableLn!");
                soapMsg  = DisableLn.Start(soapMsg);
            }

            if(WhatTheFunc(soapMsg)==6){
                logger.info("2.1) initialized ExistingLNNumRange!");
                soapMsg  = ExistingLNNumRange.Start(soapMsg);
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
            }catch (Exception e){e.printStackTrace();}


        }
             return true;
         } catch (Exception e) {
             SQL.SaveInBD("ErrorInSending",0);
             e.printStackTrace();
             return false;
         }
    }

    @Override
    public boolean handleFault(SOAPMessageContext context)
    {
        SOAPMessage msg = context.getMessage();
        Logger logger=Logger.getLogger("simple");
        logger.info(WorkWithXML.SoapMessageToString(msg));
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
            if (strdoc.contains("getNewLNNum")) {
                GlobalVariables.Type = "getNewLNNum";
                return 3;
            }
            if (strdoc.contains("getLNData")) {
                GlobalVariables.Type = "getLNData";
                return 4;
            }
            if (strdoc.contains("disableLn")) {
                GlobalVariables.Type = "disableLn";
                return 5;
            }
            if (strdoc.contains("ExistingLNNumRange")) {
                GlobalVariables.Type = "ExistingLNNumRange";
                return 6;
            }

        }catch (SOAPException e) {e.printStackTrace(); }
        return 0;
    }
}