
package main.java.ru.genereted.v1.fileoperationsln.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getNewLNNum complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="getNewLNNum">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ogrn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getNewLNNum", propOrder = {
        "ogrn"
})
public class GetNewLNNum {

    protected String ogrn;

    /**
     * Gets the value of the ogrn property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOgrn() {
        return ogrn;
    }

    /**
     * Sets the value of the ogrn property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOgrn(String value) {
        this.ogrn = value;
    }

}
