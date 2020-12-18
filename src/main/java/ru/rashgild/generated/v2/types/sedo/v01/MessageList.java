
package ru.rashgild.generated.v2.types.sedo.v01;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ����� �� ������ � ���������� ���������
 * 
 * <p>Java class for MessageList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MessageList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="messageCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="messageList" type="{http://www.fss.ru/integration/types/sedo/v01}MessageInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="broadcastMessageList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="broadcastMessage" type="{http://www.fss.ru/integration/types/sedo/v01}BroadcastMessageType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageList", propOrder = {
    "messageCount",
    "messageList",
    "broadcastMessageList"
})
public class MessageList {

    protected Integer messageCount;
    protected List<MessageInfo> messageList;
    protected MessageList.BroadcastMessageList broadcastMessageList;

    /**
     * Gets the value of the messageCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMessageCount() {
        return messageCount;
    }

    /**
     * Sets the value of the messageCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMessageCount(Integer value) {
        this.messageCount = value;
    }

    /**
     * Gets the value of the messageList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messageList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessageList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageInfo }
     * 
     * 
     */
    public List<MessageInfo> getMessageList() {
        if (messageList == null) {
            messageList = new ArrayList<MessageInfo>();
        }
        return this.messageList;
    }

    /**
     * Gets the value of the broadcastMessageList property.
     * 
     * @return
     *     possible object is
     *     {@link MessageList.BroadcastMessageList }
     *     
     */
    public MessageList.BroadcastMessageList getBroadcastMessageList() {
        return broadcastMessageList;
    }

    /**
     * Sets the value of the broadcastMessageList property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageList.BroadcastMessageList }
     *     
     */
    public void setBroadcastMessageList(MessageList.BroadcastMessageList value) {
        this.broadcastMessageList = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="broadcastMessage" type="{http://www.fss.ru/integration/types/sedo/v01}BroadcastMessageType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "broadcastMessage"
    })
    public static class BroadcastMessageList {

        @XmlElement(required = true)
        protected List<BroadcastMessageType> broadcastMessage;

        /**
         * Gets the value of the broadcastMessage property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the broadcastMessage property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBroadcastMessage().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BroadcastMessageType }
         * 
         * 
         */
        public List<BroadcastMessageType> getBroadcastMessage() {
            if (broadcastMessage == null) {
                broadcastMessage = new ArrayList<BroadcastMessageType>();
            }
            return this.broadcastMessage;
        }

    }

}
