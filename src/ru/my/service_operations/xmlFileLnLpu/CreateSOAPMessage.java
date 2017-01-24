package ru.my.service_operations.xmlFileLnLpu;

import org.w3c.dom.Document;
import ru.my.entities.PrParseFileLnLpu;
import ru.my.helpers_operations.GlobalVariables;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by rkurbanov on 22.05.2017.
 */
public class CreateSOAPMessage {

    protected static SOAPMessage Create(PrParseFileLnLpu prParseFileLnLpu){

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        SOAPMessage message = null;
        try {
            db = dbf.newDocumentBuilder();
            Document document = db.newDocument();
            document= GlobalVariables.parser.ObjToSoap(prParseFileLnLpu);
            MessageFactory mf = MessageFactory.newInstance();
            message  = mf.createMessage();
            SOAPPart soapPart = message.getSOAPPart();
            SOAPEnvelope soapEnv = message.getSOAPPart().getEnvelope();
            SOAPHeader soapHeader = soapEnv.getHeader();
            SOAPBody soapBody = soapEnv.getBody();
            soapBody.addDocument(document);

            soapEnv.addNamespaceDeclaration("ds","http://www.w3.org/2000/09/xmldsig#");
            soapEnv.addNamespaceDeclaration("wsse","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
            soapEnv.addNamespaceDeclaration("wsu","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
            soapEnv.addNamespaceDeclaration("xsd","http://www.w3.org/2001/XMLSchema");
            soapEnv.addNamespaceDeclaration("xsi","http://www.w3.org/2001/XMLSchema-instance");
            soapEnv.addNamespaceDeclaration("fil","http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl");

            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;

    }
}
