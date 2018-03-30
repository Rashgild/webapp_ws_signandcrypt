
package ru.ibs.fss.ln.ws.fileoperationsln;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrParseFilelnlpuElement complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="PrParseFilelnlpuElement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ogrn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pXmlFile">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ROWSET" type="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}ROWSET" minOccurs="0"/>
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
@XmlType(name = "PrParseFilelnlpuElement", propOrder = {
        "ogrn",
        "pXmlFile"
})
public class PrParseFilelnlpuElement {

    @XmlElement(required = true, nillable = true)
    protected String ogrn;
    @XmlElement(required = true, nillable = true)
    protected PrParseFilelnlpuElement.PXmlFile pXmlFile;

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
     *     {@link PrParseFilelnlpuElement.PXmlFile }
     *
     */
    public PrParseFilelnlpuElement.PXmlFile getPXmlFile() {
        return pXmlFile;
    }

    /**
     * Sets the value of the pXmlFile property.
     *
     * @param value
     *     allowed object is
     *     {@link PrParseFilelnlpuElement.PXmlFile }
     *
     */
    public void setPXmlFile(PrParseFilelnlpuElement.PXmlFile value) {
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
     *         &lt;element name="ROWSET" type="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}ROWSET" minOccurs="0"/>
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

        @XmlElement(name = "ROWSET")
        protected ROWSET rowset;

        /**
         * Gets the value of the rowset property.
         *
         * @return
         *     possible object is
         *     {@link ROWSET }
         *
         */
        public ROWSET getROWSET() {
            return rowset;
        }

        /**
         * Sets the value of the rowset property.
         *
         * @param value
         *     allowed object is
         *     {@link ROWSET }
         *
         */
        public void setROWSET(ROWSET value) {
            this.rowset = value;
        }

    }

}
