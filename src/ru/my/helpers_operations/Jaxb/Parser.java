package ru.my.helpers_operations.Jaxb;

import org.w3c.dom.Document;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

/**
 * Created by rkurbanov on 05.12.2016.
 */
public interface Parser {
    Object getObject(File file, Class c) throws JAXBException;
    void saveObject(File file, Object o) throws JAXBException;
    Document ObjToSoap(Object o) throws JAXBException, IOException, ParserConfigurationException, TransformerException;
}
