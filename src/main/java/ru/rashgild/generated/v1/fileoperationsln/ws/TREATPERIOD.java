
package ru.rashgild.generated.v1.fileoperationsln.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TREAT_PERIOD complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TREAT_PERIOD">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="TREAT_DT1" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="TREAT_DT2" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="TREAT_DOCTOR_ROLE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TREAT_DOCTOR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *       &lt;attribute ref="{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Id"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TREAT_PERIOD", propOrder = {

})
public class TREATPERIOD {

    @XmlElement(name = "TREAT_DT1", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar treatdt1;
    @XmlElement(name = "TREAT_DT2", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar treatdt2;
    @XmlElement(name = "TREAT_DOCTOR_ROLE", required = true)
    protected String treatdoctorrole;
    @XmlElement(name = "TREAT_DOCTOR", required = true)
    protected String treatdoctor;
    @XmlAttribute(name = "Id", namespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the treatdt1 property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getTREATDT1() {
        return treatdt1;
    }

    /**
     * Sets the value of the treatdt1 property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setTREATDT1(XMLGregorianCalendar value) {
        this.treatdt1 = value;
    }

    /**
     * Gets the value of the treatdt2 property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getTREATDT2() {
        return treatdt2;
    }

    /**
     * Sets the value of the treatdt2 property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setTREATDT2(XMLGregorianCalendar value) {
        this.treatdt2 = value;
    }

    /**
     * Gets the value of the treatdoctorrole property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTREATDOCTORROLE() {
        return treatdoctorrole;
    }

    /**
     * Sets the value of the treatdoctorrole property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTREATDOCTORROLE(String value) {
        this.treatdoctorrole = value;
    }

    /**
     * Gets the value of the treatdoctor property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTREATDOCTOR() {
        return treatdoctor;
    }

    /**
     * Sets the value of the treatdoctor property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTREATDOCTOR(String value) {
        this.treatdoctor = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setId(String value) {
        this.id = value;
    }

}
