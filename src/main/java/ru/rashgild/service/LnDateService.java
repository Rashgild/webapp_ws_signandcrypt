package ru.rashgild.service;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public interface LnDateService {
    SOAPMessage generateMessage(SOAPMessage soapMsg) throws SOAPException;
}
