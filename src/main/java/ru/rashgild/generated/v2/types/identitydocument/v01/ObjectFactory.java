
package ru.rashgild.generated.v2.types.identitydocument.v01;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.v2.fss.integration.types.identitydocument.v01 package.
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

    private final static QName _IdentityDocument_QNAME = new QName("http://www.fss.ru/integration/types/identityDocument/v01", "identityDocument");
    private final static QName _CodeType_QNAME = new QName("http://www.fss.ru/integration/types/identityDocument/v01", "codeType");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.v2.fss.integration.types.identitydocument.v01
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IdentityDocument }
     * 
     */
    public IdentityDocument createIdentityDocument() {
        return new IdentityDocument();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdentityDocument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/identityDocument/v01", name = "identityDocument")
    public JAXBElement<IdentityDocument> createIdentityDocument(IdentityDocument value) {
        return new JAXBElement<IdentityDocument>(_IdentityDocument_QNAME, IdentityDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/identityDocument/v01", name = "codeType")
    public JAXBElement<BigInteger> createCodeType(BigInteger value) {
        return new JAXBElement<BigInteger>(_CodeType_QNAME, BigInteger.class, null, value);
    }

}
