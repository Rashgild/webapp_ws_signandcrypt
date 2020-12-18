package ru.rashgild.generated.v2.types.eln.v01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * ������ ������������������
 * 
 * <p>Java class for TreatPeriod complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TreatPeriod">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="treatDt1" type="{http://www.fss.ru/integration/types/eln/v01}simpleDate"/>
 *         &lt;element name="treatDt2" type="{http://www.fss.ru/integration/types/eln/v01}simpleDate" minOccurs="0"/>
 *         &lt;element name="idDoctor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="treatDoctorRole" type="{http://www.fss.ru/integration/types/eln/v01}doctorRole" minOccurs="0"/>
 *         &lt;element name="treatDoctor" type="{http://www.fss.ru/integration/types/eln/v01}fio" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TreatPeriod", propOrder = {

})
@XmlSeeAlso({
    TreatFullPeriodMo.TreatPeriod.class
})
public class TreatPeriod {

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar treatDt1;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar treatDt2;
    protected String idDoctor;
    protected String treatDoctorRole;
    protected String treatDoctor;

    /**
     * Gets the value of the treatDt1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTreatDt1() {
        return treatDt1;
    }

    /**
     * Sets the value of the treatDt1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTreatDt1(XMLGregorianCalendar value) {
        this.treatDt1 = value;
    }

    /**
     * Gets the value of the treatDt2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTreatDt2() {
        return treatDt2;
    }

    /**
     * Sets the value of the treatDt2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTreatDt2(XMLGregorianCalendar value) {
        this.treatDt2 = value;
    }

    /**
     * Gets the value of the idDoctor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdDoctor() {
        return idDoctor;
    }

    /**
     * Sets the value of the idDoctor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDoctor(String value) {
        this.idDoctor = value;
    }

    /**
     * Gets the value of the treatDoctorRole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTreatDoctorRole() {
        return treatDoctorRole;
    }

    /**
     * Sets the value of the treatDoctorRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTreatDoctorRole(String value) {
        this.treatDoctorRole = value;
    }

    /**
     * Gets the value of the treatDoctor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTreatDoctor() {
        return treatDoctor;
    }

    /**
     * Sets the value of the treatDoctor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTreatDoctor(String value) {
        this.treatDoctor = value;
    }

}
