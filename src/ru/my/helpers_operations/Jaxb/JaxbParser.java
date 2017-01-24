package ru.my.helpers_operations.Jaxb;

import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by rkurbanov on 05.12.2016.
 */
public class JaxbParser implements Parser {
    @Override
    public Object getObject(File file, Class c) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(file);

        return object;
    }

    @Override
    public void saveObject(File file, Object o) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(o.getClass());
        Marshaller marshaller = context.createMarshaller();

        //SOAPMessage soapMessage = (SOAPMessage) context.createMarshaller();

        marshaller.marshal(o,file);
    }

    @Override
    public Document ObjToSoap(Object o) throws JAXBException, IOException, ParserConfigurationException, TransformerException {

        /*JAXBContext context = JAXBContext.newInstance(o.getClass());
        Marshaller marshaller = context.createMarshaller();*/

        JAXBContext context = JAXBContext.newInstance(o.getClass());

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(o, document);

        //writeDoc(document,System.out);
        return document;
    }

    public static void writeDoc(Document doc, OutputStream out)
            throws TransformerException {
        // создание объекта копирования содержимого XML-документа в поток
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        // копирование содержимого XML-документа в поток
        transformer.transform(new DOMSource(doc), new StreamResult(out));
    }
}
