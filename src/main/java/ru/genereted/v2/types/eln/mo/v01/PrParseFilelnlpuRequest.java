
package main.java.ru.genereted.v2.types.eln.mo.v01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * �������� ������ ���
 * 
 * <p>Java class for PrParseFilelnlpuRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PrParseFilelnlpuRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ogrn" type="{http://www.fss.ru/integration/types/organization/v01}OgrnType"/>
 *         &lt;element name="pXmlFile">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.fss.ru/integration/types/eln/mo/v01}rowset"/>
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
@XmlType(name = "PrParseFilelnlpuRequest", propOrder = {
    "ogrn",
    "pXmlFile"
})
public class PrParseFilelnlpuRequest {

    @XmlElement(required = true)
    protected String ogrn;
    @XmlElement(required = true)
    protected PrParseFilelnlpuRequest.PXmlFile pXmlFile;

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
     * Gets the value of the pXmlFile property.
     * 
     * @return
     *     possible object is
     *     {@link PrParseFilelnlpuRequest.PXmlFile }
     *     
     */
    public PrParseFilelnlpuRequest.PXmlFile getPXmlFile() {
        return pXmlFile;
    }

    /**
     * Sets the value of the pXmlFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrParseFilelnlpuRequest.PXmlFile }
     *     
     */
    public void setPXmlFile(PrParseFilelnlpuRequest.PXmlFile value) {
        this.pXmlFile = value;
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
     *         &lt;element ref="{http://www.fss.ru/integration/types/eln/mo/v01}rowset"/>
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
        "rowset"
    })
    public static class PXmlFile {

        @XmlElement(required = true)
        protected Rowset rowset;

        /**
         * Gets the value of the rowset property.
         * 
         * @return
         *     possible object is
         *     {@link Rowset }
         *     
         */
        public Rowset getRowset() {
            return rowset;
        }

        /**
         * Sets the value of the rowset property.
         * 
         * @param value
         *     allowed object is
         *     {@link Rowset }
         *     
         */
        public void setRowset(Rowset value) {
            this.rowset = value;
        }

    }

}
