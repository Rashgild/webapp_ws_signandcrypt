
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


/**
 * <p>Java class for TREAT_FULL_PERIOD complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TREAT_FULL_PERIOD">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="TREAT_CHAIRMAN_ROLE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TREAT_CHAIRMAN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TREAT_PERIOD" type="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}TREAT_PERIOD"/>
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
@XmlType(name = "TREAT_FULL_PERIOD", propOrder = {

})
public class TREATFULLPERIOD {

    @XmlElement(name = "TREAT_CHAIRMAN_ROLE")
    protected String treatchairmanrole;
    @XmlElement(name = "TREAT_CHAIRMAN")
    protected String treatchairman;
    @XmlElement(name = "TREAT_PERIOD", required = true)
    protected TREATPERIOD treatperiod;
    @XmlAttribute(name = "Id", namespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the treatchairmanrole property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTREATCHAIRMANROLE() {
        return treatchairmanrole;
    }

    /**
     * Sets the value of the treatchairmanrole property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTREATCHAIRMANROLE(String value) {
        this.treatchairmanrole = value;
    }

    /**
     * Gets the value of the treatchairman property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTREATCHAIRMAN() {
        return treatchairman;
    }

    /**
     * Sets the value of the treatchairman property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTREATCHAIRMAN(String value) {
        this.treatchairman = value;
    }

    /**
     * Gets the value of the treatperiod property.
     *
     * @return
     *     possible object is
     *     {@link TREATPERIOD }
     *
     */
    public TREATPERIOD getTREATPERIOD() {
        return treatperiod;
    }

    /**
     * Sets the value of the treatperiod property.
     *
     * @param value
     *     allowed object is
     *     {@link TREATPERIOD }
     *
     */
    public void setTREATPERIOD(TREATPERIOD value) {
        this.treatperiod = value;
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
