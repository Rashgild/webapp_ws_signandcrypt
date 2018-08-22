package ru.my.helpers_operations;

import org.apache.xml.security.c14n.Canonicalizer;
import org.w3c.dom.Document;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * Created by rkurbanov on 22.05.2017.
 */
public class WorkWithXML {

    public static String DocToString(Document doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }

    public static void writeDoc(Document doc, OutputStream out)
            throws TransformerException {
        // создание объекта копирования содержимого XML-документа в поток
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        // копирование содержимого XML-документа в поток
        transformer.transform(new DOMSource(doc), new StreamResult(out));
    }

    public byte[] documentToByte(Document document)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        org.apache.xml.security.utils.XMLUtils.outputDOM(document, baos, true);
        return baos.toByteArray();
    }

    /**
     * Сохранение Soap-сообщения в файл
     * @param FileName Имя сохраняемого файла
     * @param soapMessage Сообщение, которое требуется сохранить
     * @throws Exception e,
     * @throws IOException e
     */
    public static void SaveSOAPToXML( String FileName, SOAPMessage soapMessage)
            throws IOException, SOAPException {

        String strMsg = SoapMessageToString(soapMessage);//new String(out2.toByteArray());//.getBytes("UTF-16");
        try {

            Writer w = new OutputStreamWriter(new FileOutputStream(GlobalVariables.pathtosave+FileName), "UTF-8");
            w.write(strMsg);
            w.flush();
            w.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Конверт из w3.dom.Document в Soap-сообщение
     * @param doc конвертируемый документ
     * @return SOAPMessage
     */
    public static SOAPMessage DocToSOAP(Document doc) throws Exception {
        Canonicalizer c14n =
                Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_WITH_COMMENTS);
        byte[] canonicalMessage = c14n.canonicalizeSubtree(doc);
        ByteArrayInputStream in = new ByteArrayInputStream(canonicalMessage);
        MessageFactory factory = MessageFactory.newInstance();
        return factory.createMessage(null, in);
    }

    public static Document soapToDoc(SOAPMessage soapMsg)
            throws TransformerException, SOAPException, IOException {
      /*  MyByteArrayOutputStream baos = new MyByteArrayOutputStream();
        soapMsg.writeTo(baos);
        ByteArrayInputStream bais = baos.getByteArrayInputStream();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(bais);
        return(doc);*/

        Document doc = null;
      return doc;
    }

    public static String SoapMessageToString(SOAPMessage soapMessage)
    {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            soapMessage.writeTo(stream);
            String message = new String(stream.toByteArray(), "utf-8");
            return message;
        }
        catch (SOAPException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SOAPMessage stringToSoap(String soap){

        SOAPMessage soapMessage=null;

        try {
            InputStream is = new ByteArrayInputStream(soap.getBytes());
            soapMessage = MessageFactory.newInstance().createMessage(null, is);
        }catch (Exception e){
            e.printStackTrace();
        }
        return soapMessage;
    }
}
