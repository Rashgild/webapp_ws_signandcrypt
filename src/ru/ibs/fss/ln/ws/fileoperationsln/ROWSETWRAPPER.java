
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
 *         &lt;element name="ROWSET" type="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}ROWSET" minOccurs="0"/>
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
        "rowset"
})
@XmlRootElement(name = "ROWSET_WRAPPER")
public class ROWSETWRAPPER {

    @XmlElement(name = "ROWSET")
    protected ROWSET rowset;

    /**
     * Gets the value of the rowset property.
     *
     * @return
     *     possible object is
     *     {@link ROWSET }
     *
     */
    public ROWSET getROWSET() {
        return rowset;
    }

    /**
     * Sets the value of the rowset property.
     *
     * @param value
     *     allowed object is
     *     {@link ROWSET }
     *
     */
    public void setROWSET(ROWSET value) {
        this.rowset = value;
    }

}
