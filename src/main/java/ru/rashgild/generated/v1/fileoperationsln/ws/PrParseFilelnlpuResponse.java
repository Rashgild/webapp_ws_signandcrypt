
package ru.rashgild.generated.v1.fileoperationsln.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prParseFilelnlpuResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="prParseFilelnlpuResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WSResult" type="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}WSResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prParseFilelnlpuResponse", propOrder = {
        "wsResult"
})
public class PrParseFilelnlpuResponse {

    @XmlElement(name = "WSResult")
    protected WSResult wsResult;

    /**
     * Gets the value of the wsResult property.
     *
     * @return
     *     possible object is
     *     {@link WSResult }
     *
     */
    public WSResult getWSResult() {
        return wsResult;
    }

    /**
     * Sets the value of the wsResult property.
     *
     * @param value
     *     allowed object is
     *     {@link WSResult }
     *
     */
    public void setWSResult(WSResult value) {
        this.wsResult = value;
    }

}
