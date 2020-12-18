package ru.rashgild.service_operations.newLNNum;

import ru.rashgild.service_operations.newLNNumRange.NewLnNumRange_start;

import javax.xml.soap.SOAPMessage;

public class NewLNNum {

    public static SOAPMessage Start(SOAPMessage soapMsg){
        return NewLnNumRange_start.Start(soapMsg);
    }
}
