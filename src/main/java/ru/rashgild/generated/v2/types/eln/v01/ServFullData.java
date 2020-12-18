package ru.rashgild.generated.v2.types.eln.v01;

import ru.rashgild.generated.v2.types.eln.mo.v01.ResponseRow;
import ru.rashgild.generated.v2.types.eln.mo.v01.Rowset;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * ������ ������������������: �� ����� �� �������������
 * 
 * <p>Java class for ServFullData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServFullData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="servRelationCode" type="{http://www.fss.ru/integration/types/eln/v01}dictCode"/>
 *         &lt;element name="servDt1" type="{http://www.fss.ru/integration/types/eln/v01}simpleDate"/>
 *         &lt;element name="servDt2" type="{http://www.fss.ru/integration/types/eln/v01}simpleDate"/>
 *         &lt;element name="treatmentType" type="{http://www.fss.ru/integration/types/eln/v01}dictCode" minOccurs="0"/>
 *         &lt;element name="surname" type="{http://www.fss.ru/integration/types/eln/v01}surname" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.fss.ru/integration/types/eln/v01}name" minOccurs="0"/>
 *         &lt;element name="patronymic" type="{http://www.fss.ru/integration/types/eln/v01}patronymic" minOccurs="0"/>
 *         &lt;element name="birthday" type="{http://www.fss.ru/integration/types/eln/v01}simpleDate" minOccurs="0"/>
 *         &lt;element name="reason1" type="{http://www.fss.ru/integration/types/eln/v01}dictCode" minOccurs="0"/>
 *         &lt;element name="snils" type="{http://www.fss.ru/integration/types/person/v01}snils" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServFullData", propOrder = {
    "servRelationCode",
    "servDt1",
    "servDt2",
    "treatmentType",
    "surname",
    "name",
    "patronymic",
    "birthday",
    "reason1",
    "snils"
})
@XmlSeeAlso({
    Rowset.Row.ServData.ServFullData.class,
    ResponseRow.ServData.ServFullData.class
})
public class ServFullData {

    @XmlElement(required = true)
    protected String servRelationCode;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar servDt1;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar servDt2;
    protected String treatmentType;
    protected String surname;
    protected String name;
    protected String patronymic;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthday;
    protected String reason1;
    protected String snils;

    /**
     * Gets the value of the servRelationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServRelationCode() {
        return servRelationCode;
    }

    /**
     * Sets the value of the servRelationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServRelationCode(String value) {
        this.servRelationCode = value;
    }

    /**
     * Gets the value of the servDt1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getServDt1() {
        return servDt1;
    }

    /**
     * Sets the value of the servDt1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setServDt1(XMLGregorianCalendar value) {
        this.servDt1 = value;
    }

    /**
     * Gets the value of the servDt2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getServDt2() {
        return servDt2;
    }

    /**
     * Sets the value of the servDt2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setServDt2(XMLGregorianCalendar value) {
        this.servDt2 = value;
    }

    /**
     * Gets the value of the treatmentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTreatmentType() {
        return treatmentType;
    }

    /**
     * Sets the value of the treatmentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTreatmentType(String value) {
        this.treatmentType = value;
    }

    /**
     * Gets the value of the surname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the patronymic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Sets the value of the patronymic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatronymic(String value) {
        this.patronymic = value;
    }

    /**
     * Gets the value of the birthday property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthday() {
        return birthday;
    }

    /**
     * Sets the value of the birthday property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBirthday(XMLGregorianCalendar value) {
        this.birthday = value;
    }

    /**
     * Gets the value of the reason1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason1() {
        return reason1;
    }

    /**
     * Sets the value of the reason1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason1(String value) {
        this.reason1 = value;
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

}
