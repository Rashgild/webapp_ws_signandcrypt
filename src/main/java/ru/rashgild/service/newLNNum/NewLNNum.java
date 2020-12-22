package ru.rashgild.service.newLNNum;

import ru.rashgild.service.newLNNumRange.NewLnNumRange_start;

import javax.xml.soap.SOAPMessage;

public class NewLNNum {

    public static SOAPMessage Start(SOAPMessage soapMsg){
        return NewLnNumRange_start.Start(soapMsg);
    }
}
