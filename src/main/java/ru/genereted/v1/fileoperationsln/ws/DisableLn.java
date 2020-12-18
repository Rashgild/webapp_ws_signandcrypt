
package main.java.ru.genereted.v1.fileoperationsln.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for disableLn complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="disableLn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ogrn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lnCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snils" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "disableLn", propOrder = {
        "ogrn",
        "lnCode",
        "snils",
        "reasonCode",
        "reason"
})
public class DisableLn {

    protected String ogrn;
    protected String lnCode;
    protected String snils;
    protected String reasonCode;
    protected String reason;

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

    /**
     * Gets the value of the lnCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLnCode() {
        return lnCode;
    }

    /**
     * Sets the value of the lnCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLnCode(String value) {
        this.lnCode = value;
    }

    /**
     * Gets the value of the snils property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSnils() {
        return snils;
    }

    /**
     * Sets the value of the snils property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSnils(String value) {
        this.snils = value;
    }

    /**
     * Gets the value of the reasonCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReasonCode() {
        return reasonCode;
    }

    /**
     * Sets the value of the reasonCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReasonCode(String value) {
        this.reasonCode = value;
    }

    /**
     * Gets the value of the reason property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReason(String value) {
        this.reason = value;
    }

}
