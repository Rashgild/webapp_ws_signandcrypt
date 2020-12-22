package ru.rashgild.service_operations.newLNNumRange;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;

import ru.rashgild.utils.GlobalVariables;
import ru.rashgild.utils.XmlUtils;
import ru.rashgild.signAndCrypt.Encrypt;
import ru.rashgild.signAndCrypt.Sign;

import static ru.rashgild.utils.CertificateUtils.addCertificateToHeader;

public class NewLnNumRange_start {

    /**
     * Меняет перехваченное сообщение под нужный шаблон
     *
     * @param soapMsg передается из Инъектера
     */
    public static SOAPMessage Start(SOAPMessage soapMsg) {
        Logger logger = Logger.getLogger("simple");
        try {

            logger.info("CreateMessage:");
            Create(soapMsg);
            logger.info("SigningMessage");
            soapMsg = Sign.signation();
            soapMsg = addCertificateToHeader(soapMsg);

            XmlUtils.saveSoapToXml("GetNumSigned.xml", soapMsg);
            GlobalVariables.Request = XmlUtils.soapMessageToString(soapMsg);
            MessageFactory mf = MessageFactory.newInstance();

            SOAPMessage NewMessg = mf.createMessage();
            logger.info("EncryptingMessage");
            NewMessg = Encrypt.createXmlAndEncrypt(NewMessg, "GetNumSigned.xml");

            return NewMessg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soapMsg;
    }

    /**
     * GlobalVariables.ogrn!
     * Меняет перехваченное сообщение под нужный шаблон
     *
     * @param soapMessage перехваченное сообщение
     */
    public static void Create(SOAPMessage soapMessage)
            throws SOAPException {

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
