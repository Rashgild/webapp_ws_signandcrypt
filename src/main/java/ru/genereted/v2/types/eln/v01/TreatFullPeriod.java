
package main.java.ru.genereted.v2.types.eln.v01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ������ ������������������: ��� ������
 * 
 * <p>Java class for TreatFullPeriod complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TreatFullPeriod">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="treatChairman" type="{http://www.fss.ru/integration/types/eln/v01}fio" minOccurs="0"/>
 *         &lt;element name="treatChairmanRole" type="{http://www.fss.ru/integration/types/eln/v01}doctorRole" minOccurs="0"/>
 *         &lt;element name="treatPeriod" type="{http://www.fss.ru/integration/types/eln/v01}TreatPeriod"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TreatFullPeriod", propOrder = {
    "treatChairman",
    "treatChairmanRole",
    "treatPeriod"
})
public class TreatFullPeriod {

    protected String treatChairman;
    protected String treatChairmanRole;
    @XmlElement(required = true)
    protected TreatPeriod treatPeriod;

    /**
     * Gets the value of the treatChairman property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTreatChairman() {
        return treatChairman;
    }

    /**
     * Sets the value of the treatChairman property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTreatChairman(String value) {
        this.treatChairman = value;
    }

    /**
     * Gets the value of the treatChairmanRole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTreatChairmanRole() {
        return treatChairmanRole;
    }

    /**
     * Sets the value of the treatChairmanRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTreatChairmanRole(String value) {
        this.treatChairmanRole = value;
    }

    /**
     * Gets the value of the treatPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link TreatPeriod }
     *     
     */
    public TreatPeriod getTreatPeriod() {
        return treatPeriod;
    }

    /**
     * Sets the value of the treatPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link TreatPeriod }
     *     
     */
    public void setTreatPeriod(TreatPeriod value) {
        this.treatPeriod = value;
    }

}
