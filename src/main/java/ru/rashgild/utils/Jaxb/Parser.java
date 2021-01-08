package ru.rashgild.utils.Jaxb;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

public interface Parser {

    Object getObject(File file, Class c) throws JAXBException;

    void saveObject(File file, Object o) throws JAXBException;

    Document objToSoap(Object o) throws JAXBException, IOException, ParserConfigurationException, TransformerException;
}
