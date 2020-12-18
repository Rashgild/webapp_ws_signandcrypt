
package main.java.ru.genereted.v2.types.eln.mo.v01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * ������ ������ ������������������: ������ �� �����
 * 
 * <p>Java class for RowLNbySnils complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RowLNbySnils">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="lnCode" type="{http://www.fss.ru/integration/types/eln/v01}lnCode"/>
 *         &lt;element name="lnDate" type="{http://www.fss.ru/integration/types/eln/v01}simpleDate"/>
 *         &lt;element name="lnState" type="{http://www.fss.ru/integration/types/eln/v01}lnState"/>
 *         &lt;element name="lpuOgrn" type="{http://www.fss.ru/integration/types/organization/v01}OgrnType"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RowLNbySnils", propOrder = {

})
public class RowLNbySnils {

    @XmlElement(required = true)
    protected String lnCode;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lnDate;
    @XmlElement(required = true)
    protected String lnState;
    @XmlElement(required = true)
    protected String lpuOgrn;

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
     * Gets the value of the lnDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLnDate() {
        return lnDate;
    }

    /**
     * Sets the value of the lnDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLnDate(XMLGregorianCalendar value) {
        this.lnDate = value;
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
     * Gets the value of the lpuOgrn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLpuOgrn() {
        return lpuOgrn;
    }

    /**
     * Sets the value of the lpuOgrn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLpuOgrn(String value) {
        this.lpuOgrn = value;
    }

}
