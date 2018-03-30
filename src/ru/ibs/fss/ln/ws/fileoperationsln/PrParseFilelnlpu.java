
package ru.ibs.fss.ln.ws.fileoperationsln;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prParseFilelnlpu complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="prParseFilelnlpu">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="request" type="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}PrParseFilelnlpuElement" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prParseFilelnlpu", propOrder = {
        "request"
})
public class PrParseFilelnlpu {

    protected PrParseFilelnlpuElement request;

    /**
     * Gets the value of the request property.
     *
     * @return
     *     possible object is
     *     {@link PrParseFilelnlpuElement }
     *
     */
    public PrParseFilelnlpuElement getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     *
     * @param value
     *     allowed object is
     *     {@link PrParseFilelnlpuElement }
     *
     */
    public void setRequest(PrParseFilelnlpuElement value) {
        this.request = value;
    }

}
