
package ru.ibs.fss.ln.ws.fileoperationslninternal;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.ibs.fss.ln.ws.fileoperationslninternal package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SIGNROWSET_QNAME = new QName("http://ru/ibs/fss/ln/ws/FileOperationsLnInternal.wsdl", "SIGN_ROWSET");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.ibs.fss.ln.ws.fileoperationslninternal
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SIGNROWSET }
     * 
     */
    public SIGNROWSET createSIGNROWSET() {
        return new SIGNROWSET();
    }

    /**
     * Create an instance of {@link SIGNROWSET.ROW }
     * 
     */
    public SIGNROWSET.ROW createSIGNROWSETROW() {
        return new SIGNROWSET.ROW();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIGNROWSET }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLnInternal.wsdl", name = "SIGN_ROWSET")
    public JAXBElement<SIGNROWSET> createSIGNROWSET(SIGNROWSET value) {
        return new JAXBElement<SIGNROWSET>(_SIGNROWSET_QNAME, SIGNROWSET.class, null, value);
    }

}
