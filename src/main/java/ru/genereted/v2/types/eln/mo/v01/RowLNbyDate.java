
package main.java.ru.genereted.v2.types.eln.mo.v01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ������ ������ ������������������: ������ �� ����
 * 
 * <p>Java class for RowLNbyDate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RowLNbyDate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="lnCode" type="{http://www.fss.ru/integration/types/eln/v01}lnCode"/>
 *         &lt;element name="lnState" type="{http://www.fss.ru/integration/types/eln/v01}lnState"/>
 *         &lt;element name="snils" type="{http://www.fss.ru/integration/types/person/v01}snils"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RowLNbyDate", propOrder = {

})
public class RowLNbyDate {

    @XmlElement(required = true)
    protected String lnCode;
    @XmlElement(required = true)
    protected String lnState;
    @XmlElement(required = true)
    protected String snils;

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
     * Gets the value of the lnState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLnState() {
        return lnState;
    }

    /**
     * Sets the value of the lnState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLnState(String value) {
        this.lnState = value;
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
