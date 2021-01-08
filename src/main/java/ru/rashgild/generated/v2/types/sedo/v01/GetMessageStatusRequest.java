
package ru.rashgild.generated.v2.types.sedo.v01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ������ �� ��������� ������� ��������� put-���������
 * 
 * <p>Java class for GetMessageStatusRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetMessageStatusRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fss.ru/integration/types/sedo/v01}CommonMessageRequest">
 *       &lt;sequence>
 *         &lt;element name="recipientId" type="{http://www.fss.ru/integration/types/sedo/v01}RecipientId"/>
 *         &lt;element name="uuids" type="{http://www.fss.ru/integration/types/sedo/v01}UuidList"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetMessageStatusRequest", propOrder = {
    "recipientId",
    "uuids"
})
public class GetMessageStatusRequest
    extends CommonMessageRequest
{

    @XmlElement(required = true)
    protected String recipientId;
    @XmlElement(required = true)
    protected UuidList uuids;

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
     * Gets the value of the uuids property.
     * 
     * @return
     *     possible object is
     *     {@link UuidList }
     *     
     */
    public UuidList getUuids() {
        return uuids;
    }

    /**
     * Sets the value of the uuids property.
     * 
     * @param value
     *     allowed object is
     *     {@link UuidList }
     *     
     */
    public void setUuids(UuidList value) {
        this.uuids = value;
    }

}
