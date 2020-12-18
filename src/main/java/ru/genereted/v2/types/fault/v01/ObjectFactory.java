
package main.java.ru.genereted.v2.types.fault.v01;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.v2.fss.integration.types.fault.v01 package.
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

    private final static QName _BusinessFault_QNAME = new QName("http://www.fss.ru/integration/types/fault/v01", "BusinessFault");
    private final static QName _PovdBusinessFaultType_QNAME = new QName("http://www.fss.ru/integration/types/fault/v01", "povdBusinessFaultType");
    private final static QName _InvalidDataFault_QNAME = new QName("http://www.fss.ru/integration/types/fault/v01", "invalidDataFault");
    private final static QName _DigitalSignFault_QNAME = new QName("http://www.fss.ru/integration/types/fault/v01", "digitalSignFault");
    private final static QName _DecryptFault_QNAME = new QName("http://www.fss.ru/integration/types/fault/v01", "decryptFault");
    private final static QName _TmsFault_QNAME = new QName("http://www.fss.ru/integration/types/fault/v01", "tmsFault");
    private final static QName _InternalFault_QNAME = new QName("http://www.fss.ru/integration/types/fault/v01", "internalFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.v2.fss.integration.types.fault.v01
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InvalidDataFault }
     * 
     */
    public InvalidDataFault createInvalidDataFault() {
        return new InvalidDataFault();
    }

    /**
     * Create an instance of {@link PovdBusinessFaultType }
     * 
     */
    public PovdBusinessFaultType createPovdBusinessFaultType() {
        return new PovdBusinessFaultType();
    }

    /**
     * Create an instance of {@link DecryptFault }
     * 
     */
    public DecryptFault createDecryptFault() {
        return new DecryptFault();
    }

    /**
     * Create an instance of {@link InternalFault }
     * 
     */
    public InternalFault createInternalFault() {
        return new InternalFault();
    }

    /**
     * Create an instance of {@link DigitalSignFault }
     * 
     */
    public DigitalSignFault createDigitalSignFault() {
        return new DigitalSignFault();
    }

    /**
     * Create an instance of {@link TmsFault }
     * 
     */
    public TmsFault createTmsFault() {
        return new TmsFault();
    }

    /**
     * Create an instance of {@link CommonFault }
     * 
     */
    public CommonFault createCommonFault() {
        return new CommonFault();
    }

    /**
     * Create an instance of {@link InvalidDataFault.FaultList }
     * 
     */
    public InvalidDataFault.FaultList createInvalidDataFaultFaultList() {
        return new InvalidDataFault.FaultList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/fault/v01", name = "BusinessFault")
    public JAXBElement<Object> createBusinessFault(Object value) {
        return new JAXBElement<Object>(_BusinessFault_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PovdBusinessFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/fault/v01", name = "povdBusinessFaultType")
    public JAXBElement<PovdBusinessFaultType> createPovdBusinessFaultType(PovdBusinessFaultType value) {
        return new JAXBElement<PovdBusinessFaultType>(_PovdBusinessFaultType_QNAME, PovdBusinessFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidDataFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/fault/v01", name = "invalidDataFault")
    public JAXBElement<InvalidDataFault> createInvalidDataFault(InvalidDataFault value) {
        return new JAXBElement<InvalidDataFault>(_InvalidDataFault_QNAME, InvalidDataFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DigitalSignFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/fault/v01", name = "digitalSignFault")
    public JAXBElement<DigitalSignFault> createDigitalSignFault(DigitalSignFault value) {
        return new JAXBElement<DigitalSignFault>(_DigitalSignFault_QNAME, DigitalSignFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DecryptFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/fault/v01", name = "decryptFault")
    public JAXBElement<DecryptFault> createDecryptFault(DecryptFault value) {
        return new JAXBElement<DecryptFault>(_DecryptFault_QNAME, DecryptFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TmsFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/fault/v01", name = "tmsFault")
    public JAXBElement<TmsFault> createTmsFault(TmsFault value) {
        return new JAXBElement<TmsFault>(_TmsFault_QNAME, TmsFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InternalFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/fault/v01", name = "internalFault")
    public JAXBElement<InternalFault> createInternalFault(InternalFault value) {
        return new JAXBElement<InternalFault>(_InternalFault_QNAME, InternalFault.class, null, value);
    }

}
