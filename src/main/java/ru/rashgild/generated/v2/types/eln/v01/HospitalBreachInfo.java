package ru.rashgild.generated.v2.types.eln.v01;

import ru.rashgild.generated.v2.types.eln.mo.v01.Rowset;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * ���������� � ���������� � ����������
 * 
 * <p>Java class for HospitalBreachInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HospitalBreachInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hospitalBreachCode" type="{http://www.fss.ru/integration/types/eln/v01}dictCode"/>
 *         &lt;element name="hospitalBreachDt" type="{http://www.fss.ru/integration/types/eln/v01}simpleDate"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HospitalBreachInfo", propOrder = {
    "hospitalBreachCode",
    "hospitalBreachDt"
})
@XmlSeeAlso({
    Rowset.Row.HospitalBreach.class
})
public class HospitalBreachInfo {

    @XmlElement(required = true)
    protected String hospitalBreachCode;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected String hospitalBreachDt;

    /**
     * Gets the value of the hospitalBreachCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHospitalBreachCode() {
        return hospitalBreachCode;
    }

    /**
     * Sets the value of the hospitalBreachCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHospitalBreachCode(String value) {
        this.hospitalBreachCode = value;
    }

    /**
     * Gets the value of the hospitalBreachDt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHospitalBreachDt() {
        return hospitalBreachDt;
    }

    /**
     * Sets the value of the hospitalBreachDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHospitalBreachDt(String value) {
        this.hospitalBreachDt = value;
    }

}
