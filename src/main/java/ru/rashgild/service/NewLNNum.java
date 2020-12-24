package ru.rashgild.service;

import javax.xml.soap.SOAPMessage;

public class NewLNNum {

    public static SOAPMessage Start(SOAPMessage soapMsg){
        return NewLnNumRange.Start(soapMsg);
    }
}
