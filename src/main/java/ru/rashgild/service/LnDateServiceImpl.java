package ru.rashgild.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rashgild.utils.GlobalVariables;
import ru.rashgild.utils.XmlUtils;

import javax.xml.soap.*;

@Service
public class LnDateServiceImpl implements LnDateService {

    @Autowired
    private final SignService signService;

    public LnDateServiceImpl(SignService signService) {
        this.signService = signService;
    }

    @Override
    public SOAPMessage generateMessage(SOAPMessage soapMsg) throws SOAPException {
        createMessage(soapMsg);
        soapMsg = signService.sign();
        return soapMsg;
    }

    private void createMessage(SOAPMessage soapMessage) throws SOAPException {
        SOAPEnvelope soapEnv = soapMessage.getSOAPPart().getEnvelope();
        //SOAPHeader soapHeader = soapEnv.getHeader();
        //soapEnv.addHeader();
        //soapMessage

        if (soapEnv.getHeader() != null) {
            soapEnv.getHeader().detachNode();
        }
        SOAPHeader header = soapEnv.addHeader();
        soapEnv.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema");
        soapEnv.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema-instance");
        soapEnv.addNamespaceDeclaration("wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        soapEnv.addNamespaceDeclaration("wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");

        SOAPBody soapBody = soapEnv.getBody();
        Name name = soapEnv.createName("Id");
        soapBody.addAttribute(name, "OGRN_" + GlobalVariables.ogrnMo);
        soapMessage.saveChanges();

        XmlUtils.saveSoapToXml("tempSkeleton.xml", soapMessage);
    }
}
