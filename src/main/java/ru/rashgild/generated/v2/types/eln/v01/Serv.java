package ru.rashgild.generated.v2.types.eln.v01;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ������ ������������������: �� ����� �� �������������
 * 
 * <p>Java class for serv complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="serv">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="age" type="{http://www.fss.ru/integration/types/common/v01}countYear"/>
 *         &lt;element name="mm" type="{http://www.fss.ru/integration/types/common/v01}countMount"/>
 *         &lt;element name="days" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serv", propOrder = {
    "age",
    "mm",
    "days"
})
public class Serv {

    @XmlElement(required = true)
    protected BigDecimal age;
    @XmlElement(required = true)
    protected BigDecimal mm;
    protected int days;

    /**
     * Gets the value of the age property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAge() {
        return age;
    }

    /**
     * Sets the value of the age property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAge(BigDecimal value) {
        this.age = value;
    }

    /**
     * Gets the value of the mm property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMm() {
        return mm;
    }

    /**
     * Sets the value of the mm property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMm(BigDecimal value) {
        this.mm = value;
    }

    /**
     * Gets the value of the days property.
     * 
     */
    public int getDays() {
        return days;
    }

    /**
     * Sets the value of the days property.
     * 
     */
    public void setDays(int value) {
        this.days = value;
    }

}
