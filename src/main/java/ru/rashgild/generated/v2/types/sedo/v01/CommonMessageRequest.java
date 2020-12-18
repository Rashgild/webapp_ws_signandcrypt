
package ru.rashgild.generated.v2.types.sedo.v01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommonMessageRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CommonMessageRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="systemInfo" type="{http://www.fss.ru/integration/types/sedo/v01}SystemInfo"/>
 *         &lt;element name="interactionType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommonMessageRequest", propOrder = {
    "systemInfo",
    "interactionType"
})
@XmlSeeAlso({
    GetCntMessageRequest.class,
    GetCntMessageOperatorRequest.class,
    GetMessageRequest.class,
    PutMessageRequest.class,
    GetMessageStatusRequest.class
})
public class CommonMessageRequest {

    @XmlElement(required = true)
    protected SystemInfo systemInfo;
    protected int interactionType;

    /**
     * Gets the value of the systemInfo property.
     * 
     * @return
     *     possible object is
     *     {@link SystemInfo }
     *     
     */
    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    /**
     * Sets the value of the systemInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SystemInfo }
     *     
     */
    public void setSystemInfo(SystemInfo value) {
        this.systemInfo = value;
    }

    /**
     * Gets the value of the interactionType property.
     * 
     */
    public int getInteractionType() {
        return interactionType;
    }

    /**
     * Sets the value of the interactionType property.
     * 
     */
    public void setInteractionType(int value) {
        this.interactionType = value;
    }

}
