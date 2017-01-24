package ru.my.interceptor;


import org.w3c.dom.Document;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.IOException;
import java.util.Set;

/**
 * Created by rkurbanov on 10.11.16.
 */


public class Injecter implements SOAPHandler<SOAPMessageContext> {



    @Override
    public boolean handleMessage(SOAPMessageContext context) {

        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (isRequest) {

            SOAPMessage soapMsg = context.getMessage();
            try {
                soapMsg.writeTo(System.out);
            } catch (SOAPException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
          /*  //Запрос на отправку ЛН.
            if (WhatTheFunc(soapMsg) == 2)//
            {
                if(GlobalVariables.flag==1) {

                    soapMsg = XmlFileLnLpuArray.Mess();
                }
                if(GlobalVariables.flag==2) {
                    //soapMsg= XmlFileLnLpu.StartSetxmlFileLn();
                    try {
                        soapMsg = XmlFileLnLpuNew.Mess();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                //soapMsg= XmlFileLnLpu.StartSetxmlFileLn();
                //soapMsg.writeTo(System.out);
                GlobalVariables.Type="XmlFileLnLpu";

              //  for (int i = 0; i < 2 ; i++) {
                    System.out.println("Отсылаю!");
                    context.setMessage(soapMsg);
                //}
            }*/
        }
// TODO Работа с ответом
            if(!isRequest)// Если не реквест, значит респонз
            {
                System.out.println("Принимаю ответ!");
                SOAPMessage msg = context.getMessage();

                try {
                    msg.writeTo(System.out);
                } catch (SOAPException | IOException e) {
                    e.printStackTrace();
                }

                /*System.out.println("Принимаю ответ!");
                try {
                    SOAPMessage msg = context.getMessage(); // перехватываем респонз
                    msg = VerifyAndDecrypt.VerifyAndDecrypt(msg);

                   *//* long curTime = System.currentTimeMillis();
                    String curStringDate = new SimpleDateFormat("dd.MM.yyyy").format(curTime);
                    //msg.writeTo(System.out);
                    Doc.SaveSOAPToXML("Response"+curStringDate+".xml", msg);*//*

                   //TODO Ответ храним в переменной
                    GlobalVariables.Response = Doc.SoapMessageToString(msg);

                    //System.out.println("\n-----\n");
                    //msg.writeTo(System.out);


                        context.setMessage(msg);



                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context)
    {
        /*SOAPMessage msg = context.getMessage();
        try {
            System.out.println("\n--------------------\n " +
                    "Ошибка:" +
                    "\n--------------------");

            msg.writeTo(System.out);
            Doc.SaveSOAPToXML("Response"+System.currentTimeMillis(), msg);
            System.out.println("\n--------------------\n");

        } catch (SOAPException | IOException e) {
            e.printStackTrace();
        }*/
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }


    @Override
    public Set<QName> getHeaders() {
        return null;
    }
}