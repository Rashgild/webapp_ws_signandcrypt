
package ru.rashgild.generated.v2.types.commom.v01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProcessLinkType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProcessLinkType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="processUuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessLinkType", propOrder = {
    "processUuid"
})
public class ProcessLinkType {

    @XmlElement(required = true)
    protected String processUuid;

    /**
     * Gets the value of the processUuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessUuid() {
        return processUuid;
    }

    /**
     * Sets the value of the processUuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessUuid(String value) {
        this.processUuid = value;
    }

}
