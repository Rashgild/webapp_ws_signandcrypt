
package ru.rashgild.generated.v2.types.commom.v01;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.v2.fss.integration.types.common.v01 package.
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

    private final static QName _DictionaryRequest_QNAME = new QName("http://www.fss.ru/integration/types/common/v01", "dictionaryRequest");
    private final static QName _DictionaryResponse_QNAME = new QName("http://www.fss.ru/integration/types/common/v01", "dictionaryResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.v2.fss.integration.types.common.v01
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DictionaryType }
     * 
     */
    public DictionaryType createDictionaryType() {
        return new DictionaryType();
    }

    /**
     * Create an instance of {@link DictionaryType.Items }
     * 
     */
    public DictionaryType.Items createDictionaryTypeItems() {
        return new DictionaryType.Items();
    }

    /**
     * Create an instance of {@link DictionaryType.Items.Item }
     * 
     */
    public DictionaryType.Items.Item createDictionaryTypeItemsItem() {
        return new DictionaryType.Items.Item();
    }

    /**
     * Create an instance of {@link DictionaryType.Fields }
     * 
     */
    public DictionaryType.Fields createDictionaryTypeFields() {
        return new DictionaryType.Fields();
    }

    /**
     * Create an instance of {@link DictionaryRequestType }
     * 
     */
    public DictionaryRequestType createDictionaryRequestType() {
        return new DictionaryRequestType();
    }

    /**
     * Create an instance of {@link Errors }
     * 
     */
    public Errors createErrors() {
        return new Errors();
    }

    /**
     * Create an instance of {@link WarnType }
     * 
     */
    public WarnType createWarnType() {
        return new WarnType();
    }

    /**
     * Create an instance of {@link EntityReferenceTypeEmptyId }
     * 
     */
    public EntityReferenceTypeEmptyId createEntityReferenceTypeEmptyId() {
        return new EntityReferenceTypeEmptyId();
    }

    /**
     * Create an instance of {@link PongType }
     * 
     */
    public PongType createPongType() {
        return new PongType();
    }

    /**
     * Create an instance of {@link BusinessErrorType }
     * 
     */
    public BusinessErrorType createBusinessErrorType() {
        return new BusinessErrorType();
    }

    /**
     * Create an instance of {@link ErrorType }
     * 
     */
    public ErrorType createErrorType() {
        return new ErrorType();
    }

    /**
     * Create an instance of {@link ProcessLinkType }
     * 
     */
    public ProcessLinkType createProcessLinkType() {
        return new ProcessLinkType();
    }

    /**
     * Create an instance of {@link EntityReferenceType }
     * 
     */
    public EntityReferenceType createEntityReferenceType() {
        return new EntityReferenceType();
    }

    /**
     * Create an instance of {@link Contact }
     * 
     */
    public Contact createContact() {
        return new Contact();
    }

    /**
     * Create an instance of {@link Period }
     * 
     */
    public Period createPeriod() {
        return new Period();
    }

    /**
     * Create an instance of {@link Dictionary }
     * 
     */
    public Dictionary createDictionary() {
        return new Dictionary();
    }

    /**
     * Create an instance of {@link PingType }
     * 
     */
    public PingType createPingType() {
        return new PingType();
    }

    /**
     * Create an instance of {@link ChainLinkType }
     * 
     */
    public ChainLinkType createChainLinkType() {
        return new ChainLinkType();
    }

    /**
     * Create an instance of {@link CompositeLinkType }
     * 
     */
    public CompositeLinkType createCompositeLinkType() {
        return new CompositeLinkType();
    }

    /**
     * Create an instance of {@link SystemErrorType }
     * 
     */
    public SystemErrorType createSystemErrorType() {
        return new SystemErrorType();
    }

    /**
     * Create an instance of {@link DictionaryType.Items.Item.Field }
     * 
     */
    public DictionaryType.Items.Item.Field createDictionaryTypeItemsItemField() {
        return new DictionaryType.Items.Item.Field();
    }

    /**
     * Create an instance of {@link DictionaryType.Fields.Field }
     * 
     */
    public DictionaryType.Fields.Field createDictionaryTypeFieldsField() {
        return new DictionaryType.Fields.Field();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DictionaryRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/common/v01", name = "dictionaryRequest")
    public JAXBElement<DictionaryRequestType> createDictionaryRequest(DictionaryRequestType value) {
        return new JAXBElement<DictionaryRequestType>(_DictionaryRequest_QNAME, DictionaryRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DictionaryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/common/v01", name = "dictionaryResponse")
    public JAXBElement<DictionaryType> createDictionaryResponse(DictionaryType value) {
        return new JAXBElement<DictionaryType>(_DictionaryResponse_QNAME, DictionaryType.class, null, value);
    }

}
