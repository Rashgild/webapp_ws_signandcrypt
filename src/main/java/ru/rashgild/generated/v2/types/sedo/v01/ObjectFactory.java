
package ru.rashgild.generated.v2.types.sedo.v01;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.v2.fss.integration.types.sedo.v01 package.
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

    private final static QName _TransportUuid_QNAME = new QName("http://www.fss.ru/integration/types/sedo/v01", "transportUuid");
    private final static QName _GetCntMessageOperatorRequest_QNAME = new QName("http://www.fss.ru/integration/types/sedo/v01", "getCntMessageOperatorRequest");
    private final static QName _GetMessageRequest_QNAME = new QName("http://www.fss.ru/integration/types/sedo/v01", "getMessageRequest");
    private final static QName _MessageListResponse_QNAME = new QName("http://www.fss.ru/integration/types/sedo/v01", "messageListResponse");
    private final static QName _GetCntMessageRequest_QNAME = new QName("http://www.fss.ru/integration/types/sedo/v01", "getCntMessageRequest");
    private final static QName _SedoMessageResponse_QNAME = new QName("http://www.fss.ru/integration/types/sedo/v01", "sedoMessageResponse");
    private final static QName _SedoUuidResponse_QNAME = new QName("http://www.fss.ru/integration/types/sedo/v01", "sedoUuidResponse");
    private final static QName _PutMessageRequest_QNAME = new QName("http://www.fss.ru/integration/types/sedo/v01", "putMessageRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.v2.fss.integration.types.sedo.v01
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MessageList }
     * 
     */
    public MessageList createMessageList() {
        return new MessageList();
    }

    /**
     * Create an instance of {@link GetCntMessageRequest }
     * 
     */
    public GetCntMessageRequest createGetCntMessageRequest() {
        return new GetCntMessageRequest();
    }

    /**
     * Create an instance of {@link GetCntMessageOperatorRequest }
     * 
     */
    public GetCntMessageOperatorRequest createGetCntMessageOperatorRequest() {
        return new GetCntMessageOperatorRequest();
    }

    /**
     * Create an instance of {@link SedoUuidResponseType }
     * 
     */
    public SedoUuidResponseType createSedoUuidResponseType() {
        return new SedoUuidResponseType();
    }

    /**
     * Create an instance of {@link GetMessageRequest }
     * 
     */
    public GetMessageRequest createGetMessageRequest() {
        return new GetMessageRequest();
    }

    /**
     * Create an instance of {@link PutMessageRequest }
     * 
     */
    public PutMessageRequest createPutMessageRequest() {
        return new PutMessageRequest();
    }

    /**
     * Create an instance of {@link MessageListResponseType }
     * 
     */
    public MessageListResponseType createMessageListResponseType() {
        return new MessageListResponseType();
    }

    /**
     * Create an instance of {@link SedoMessageResponseType }
     * 
     */
    public SedoMessageResponseType createSedoMessageResponseType() {
        return new SedoMessageResponseType();
    }

    /**
     * Create an instance of {@link SystemInfo }
     * 
     */
    public SystemInfo createSystemInfo() {
        return new SystemInfo();
    }

    /**
     * Create an instance of {@link SedoMessage }
     * 
     */
    public SedoMessage createSedoMessage() {
        return new SedoMessage();
    }

    /**
     * Create an instance of {@link CommonMessageRequest }
     * 
     */
    public CommonMessageRequest createCommonMessageRequest() {
        return new CommonMessageRequest();
    }

    /**
     * Create an instance of {@link MessageInfo }
     * 
     */
    public MessageInfo createMessageInfo() {
        return new MessageInfo();
    }

    /**
     * Create an instance of {@link BroadcastMessageType }
     * 
     */
    public BroadcastMessageType createBroadcastMessageType() {
        return new BroadcastMessageType();
    }

    /**
     * Create an instance of {@link GetMessageStatusRequest }
     * 
     */
    public GetMessageStatusRequest createGetMessageStatusRequest() {
        return new GetMessageStatusRequest();
    }

    /**
     * Create an instance of {@link MessageStatusList }
     * 
     */
    public MessageStatusList createMessageStatusList() {
        return new MessageStatusList();
    }

    /**
     * Create an instance of {@link MessageStatus }
     * 
     */
    public MessageStatus createMessageStatus() {
        return new MessageStatus();
    }

    /**
     * Create an instance of {@link UuidList }
     * 
     */
    public UuidList createUuidList() {
        return new UuidList();
    }

    /**
     * Create an instance of {@link PutMessageRequestProcessor }
     * 
     */
    public PutMessageRequestProcessor createPutMessageRequestProcessor() {
        return new PutMessageRequestProcessor();
    }

    /**
     * Create an instance of {@link SedoUuid }
     * 
     */
    public SedoUuid createSedoUuid() {
        return new SedoUuid();
    }

    /**
     * Create an instance of {@link CommonClientMessage }
     * 
     */
    public CommonClientMessage createCommonClientMessage() {
        return new CommonClientMessage();
    }

    /**
     * Create an instance of {@link MessageList.BroadcastMessageList }
     * 
     */
    public MessageList.BroadcastMessageList createMessageListBroadcastMessageList() {
        return new MessageList.BroadcastMessageList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/sedo/v01", name = "transportUuid")
    public JAXBElement<String> createTransportUuid(String value) {
        return new JAXBElement<String>(_TransportUuid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCntMessageOperatorRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/sedo/v01", name = "getCntMessageOperatorRequest")
    public JAXBElement<GetCntMessageOperatorRequest> createGetCntMessageOperatorRequest(GetCntMessageOperatorRequest value) {
        return new JAXBElement<GetCntMessageOperatorRequest>(_GetCntMessageOperatorRequest_QNAME, GetCntMessageOperatorRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMessageRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/sedo/v01", name = "getMessageRequest")
    public JAXBElement<GetMessageRequest> createGetMessageRequest(GetMessageRequest value) {
        return new JAXBElement<GetMessageRequest>(_GetMessageRequest_QNAME, GetMessageRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageListResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/sedo/v01", name = "messageListResponse")
    public JAXBElement<MessageListResponseType> createMessageListResponse(MessageListResponseType value) {
        return new JAXBElement<MessageListResponseType>(_MessageListResponse_QNAME, MessageListResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCntMessageRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/sedo/v01", name = "getCntMessageRequest")
    public JAXBElement<GetCntMessageRequest> createGetCntMessageRequest(GetCntMessageRequest value) {
        return new JAXBElement<GetCntMessageRequest>(_GetCntMessageRequest_QNAME, GetCntMessageRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SedoMessageResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/sedo/v01", name = "sedoMessageResponse")
    public JAXBElement<SedoMessageResponseType> createSedoMessageResponse(SedoMessageResponseType value) {
        return new JAXBElement<SedoMessageResponseType>(_SedoMessageResponse_QNAME, SedoMessageResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SedoUuidResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/sedo/v01", name = "sedoUuidResponse")
    public JAXBElement<SedoUuidResponseType> createSedoUuidResponse(SedoUuidResponseType value) {
        return new JAXBElement<SedoUuidResponseType>(_SedoUuidResponse_QNAME, SedoUuidResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PutMessageRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/sedo/v01", name = "putMessageRequest")
    public JAXBElement<PutMessageRequest> createPutMessageRequest(PutMessageRequest value) {
        return new JAXBElement<PutMessageRequest>(_PutMessageRequest_QNAME, PutMessageRequest.class, null, value);
    }

}
