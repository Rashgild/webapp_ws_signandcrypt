package ru.rashgild.generated.v2.types.sedo.v01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ������ ��� �������� �������� ���������
 * 
 * <p>Java class for PutMessageRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PutMessageRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fss.ru/integration/types/sedo/v01}CommonMessageRequest">
 *       &lt;sequence>
 *         &lt;element name="recipientId" type="{http://www.fss.ru/integration/types/sedo/v01}RecipientId"/>
 *         &lt;element name="messageType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PutMessageRequest", propOrder = {
    "recipientId",
    "messageType",
    "message"
})
public class PutMessageRequest
    extends CommonMessageRequest
{

    @XmlElement(required = true)
    protected String recipientId;
    @XmlElement(required = true)
    protected String messageType;
    @XmlElement(required = true)
    protected String message;

    /**
     * Gets the value of the recipientId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecipientId() {
        return recipientId;
    }

    /**
     * Sets the value of the recipientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecipientId(String value) {
        this.recipientId = value;
    }

    /**
     * Gets the value of the messageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * Sets the value of the messageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageType(String value) {
        this.messageType = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

}
