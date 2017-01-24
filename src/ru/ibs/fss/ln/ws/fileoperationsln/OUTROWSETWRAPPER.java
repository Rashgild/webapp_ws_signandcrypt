
package ru.ibs.fss.ln.ws.fileoperationsln;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="OUT_ROWSET" type="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}OUT_ROWSET" minOccurs="0"/>
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
    "outrowset"
})
@XmlRootElement(name = "OUT_ROWSET_WRAPPER")
public class OUTROWSETWRAPPER {

    @XmlElement(name = "OUT_ROWSET")
    protected OUTROWSET outrowset;

    /**
     * Gets the value of the outrowset property.
     * 
     * @return
     *     possible object is
     *     {@link OUTROWSET }
     *     
     */
    public OUTROWSET getOUTROWSET() {
        return outrowset;
    }

    /**
     * Sets the value of the outrowset property.
     * 
     * @param value
     *     allowed object is
     *     {@link OUTROWSET }
     *     
     */
    public void setOUTROWSET(OUTROWSET value) {
        this.outrowset = value;
    }

}
