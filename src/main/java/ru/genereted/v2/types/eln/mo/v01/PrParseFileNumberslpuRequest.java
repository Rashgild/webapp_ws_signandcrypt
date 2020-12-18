
package main.java.ru.genereted.v2.types.eln.mo.v01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import main.java.ru.genereted.v2.types.eln.v01.LnCodeList;


/**
 * ������� ���������������� ������� ���
 * 
 * <p>Java class for PrParseFileNumberslpuRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PrParseFileNumberslpuRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ogrn" type="{http://www.fss.ru/integration/types/organization/v01}OgrnType"/>
 *         &lt;element name="pXmlNumFile">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.fss.ru/integration/types/eln/mo/v01}data"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrParseFileNumberslpuRequest", propOrder = {
    "ogrn",
    "pXmlNumFile"
})
public class PrParseFileNumberslpuRequest {

    @XmlElement(required = true)
    protected String ogrn;
    @XmlElement(required = true)
    protected PrParseFileNumberslpuRequest.PXmlNumFile pXmlNumFile;

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
     * Gets the value of the pXmlNumFile property.
     * 
     * @return
     *     possible object is
     *     {@link PrParseFileNumberslpuRequest.PXmlNumFile }
     *     
     */
    public PrParseFileNumberslpuRequest.PXmlNumFile getPXmlNumFile() {
        return pXmlNumFile;
    }

    /**
     * Sets the value of the pXmlNumFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrParseFileNumberslpuRequest.PXmlNumFile }
     *     
     */
    public void setPXmlNumFile(PrParseFileNumberslpuRequest.PXmlNumFile value) {
        this.pXmlNumFile = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element ref="{http://www.fss.ru/integration/types/eln/mo/v01}data"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "data"
    })
    public static class PXmlNumFile {

        @XmlElement(required = true)
        protected LnCodeList data;

        /**
         * Gets the value of the data property.
         * 
         * @return
         *     possible object is
         *     {@link LnCodeList }
         *     
         */
        public LnCodeList getData() {
            return data;
        }

        /**
         * Sets the value of the data property.
         * 
         * @param value
         *     allowed object is
         *     {@link LnCodeList }
         *     
         */
        public void setData(LnCodeList value) {
            this.data = value;
        }

    }

}
