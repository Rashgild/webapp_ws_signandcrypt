
package main.java.ru.genereted.v2.types.eln.v01;

import main.java.ru.genereted.v2.types.eln.mo.v01.Rowset;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * ���������� � �������� ���
 * 
 * <p>Java class for LnResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LnResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="returnDateLpu" type="{http://www.fss.ru/integration/types/eln/v01}simpleDate" minOccurs="0"/>
 *         &lt;element name="mseResult" type="{http://www.fss.ru/integration/types/eln/v01}dictCode" minOccurs="0"/>
 *         &lt;element name="otherStateDt" type="{http://www.fss.ru/integration/types/eln/v01}simpleDate" minOccurs="0"/>
 *         &lt;element name="nextLnCode" type="{http://www.fss.ru/integration/types/eln/v01}lnCode" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LnResult", propOrder = {

})
@XmlSeeAlso({
    Rowset.Row.LnResult.class
})
public class LnResult {

    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar returnDateLpu;
    protected String mseResult;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar otherStateDt;
    protected String nextLnCode;

    /**
     * Gets the value of the returnDateLpu property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReturnDateLpu() {
        return returnDateLpu;
    }

    /**
     * Sets the value of the returnDateLpu property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReturnDateLpu(XMLGregorianCalendar value) {
        this.returnDateLpu = value;
    }

    /**
     * Gets the value of the mseResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMseResult() {
        return mseResult;
    }

    /**
     * Sets the value of the mseResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMseResult(String value) {
        this.mseResult = value;
    }

    /**
     * Gets the value of the otherStateDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOtherStateDt() {
        return otherStateDt;
    }

    /**
     * Sets the value of the otherStateDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOtherStateDt(XMLGregorianCalendar value) {
        this.otherStateDt = value;
    }

    /**
     * Gets the value of the nextLnCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextLnCode() {
        return nextLnCode;
    }

    /**
     * Sets the value of the nextLnCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextLnCode(String value) {
        this.nextLnCode = value;
    }

}
