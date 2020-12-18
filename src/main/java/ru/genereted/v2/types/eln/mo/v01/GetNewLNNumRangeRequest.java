
package main.java.ru.genereted.v2.types.eln.mo.v01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ������ ������� ������� ���
 * 
 * <p>Java class for GetNewLNNumRangeRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetNewLNNumRangeRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ogrn" type="{http://www.fss.ru/integration/types/organization/v01}OgrnType"/>
 *         &lt;element name="cntLnNumbers" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetNewLNNumRangeRequest", propOrder = {
    "ogrn",
    "cntLnNumbers"
})
public class GetNewLNNumRangeRequest {

    @XmlElement(required = true)
    protected String ogrn;
    protected int cntLnNumbers;

    /**
     * Gets the value of the ogrn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOgrn() {
        return ogrn;
    }

    /**
     * Sets the value of the ogrn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOgrn(String value) {
        this.ogrn = value;
    }

    /**
     * Gets the value of the cntLnNumbers property.
     * 
     */
    public int getCntLnNumbers() {
        return cntLnNumbers;
    }

    /**
     * Sets the value of the cntLnNumbers property.
     * 
     */
    public void setCntLnNumbers(int value) {
        this.cntLnNumbers = value;
    }

}
