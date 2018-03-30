
package ru.ibs.fss.ln.ws.fileoperationsln;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for INFO complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="INFO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ROWSET" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ROW" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;all>
 *                             &lt;element name="ROW_NO" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                             &lt;element name="LN_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="LN_HASH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="LN_STATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="ERRORS" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="ERROR" maxOccurs="unbounded">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;all>
 *                                                 &lt;element name="ERR_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="ERR_MESS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                               &lt;/all>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/all>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
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
@XmlType(name = "INFO", propOrder = {
        "rowset"
})
public class INFO {

    @XmlElement(name = "ROWSET")
    protected INFO.ROWSET rowset;

    /**
     * Gets the value of the rowset property.
     *
     * @return
     *     possible object is
     *     {@link INFO.ROWSET }
     *
     */
    public INFO.ROWSET getROWSET() {
        return rowset;
    }

    /**
     * Sets the value of the rowset property.
     *
     * @param value
     *     allowed object is
     *     {@link INFO.ROWSET }
     *
     */
    public void setROWSET(INFO.ROWSET value) {
        this.rowset = value;
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
     *         &lt;element name="ROW" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;all>
     *                   &lt;element name="ROW_NO" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *                   &lt;element name="LN_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="LN_HASH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="LN_STATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="ERRORS" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="ERROR" maxOccurs="unbounded">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;all>
     *                                       &lt;element name="ERR_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="ERR_MESS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                     &lt;/all>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/all>
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
    @XmlType(name = "", propOrder = {
            "row"
    })
    public static class ROWSET {

        @XmlElement(name = "ROW", required = true)
        protected List<INFO.ROWSET.ROW> row;

        /**
         * Gets the value of the row property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the row property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getROW().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link INFO.ROWSET.ROW }
         *
         *
         */
        public List<INFO.ROWSET.ROW> getROW() {
            if (row == null) {
                row = new ArrayList<INFO.ROWSET.ROW>();
            }
            return this.row;
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
         *       &lt;all>
         *         &lt;element name="ROW_NO" type="{http://www.w3.org/2001/XMLSchema}integer"/>
         *         &lt;element name="LN_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="LN_HASH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="LN_STATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="ERRORS" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="ERROR" maxOccurs="unbounded">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;all>
         *                             &lt;element name="ERR_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="ERR_MESS" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                           &lt;/all>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/all>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {

        })
        public static class ROW {

            @XmlElement(name = "ROW_NO", required = true, nillable = true)
            protected BigInteger rowno;
            @XmlElement(name = "LN_CODE", required = true)
            protected String lncode;
            @XmlElement(name = "LN_HASH")
            protected String lnhash;
            @XmlElement(name = "LN_STATE")
            protected String lnstate;
            @XmlElement(name = "STATUS")
            protected int status;
            @XmlElement(name = "ERRORS")
            protected INFO.ROWSET.ROW.ERRORS errors;

            /**
             * Gets the value of the rowno property.
             *
             * @return
             *     possible object is
             *     {@link BigInteger }
             *
             */
            public BigInteger getROWNO() {
                return rowno;
            }

            /**
             * Sets the value of the rowno property.
             *
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *
             */
            public void setROWNO(BigInteger value) {
                this.rowno = value;
            }

            /**
             * Gets the value of the lncode property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getLNCODE() {
                return lncode;
            }

            /**
             * Sets the value of the lncode property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setLNCODE(String value) {
                this.lncode = value;
            }

            /**
             * Gets the value of the lnhash property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getLNHASH() {
                return lnhash;
            }

            /**
             * Sets the value of the lnhash property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setLNHASH(String value) {
                this.lnhash = value;
            }

            /**
             * Gets the value of the lnstate property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getLNSTATE() {
                return lnstate;
            }

            /**
             * Sets the value of the lnstate property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setLNSTATE(String value) {
                this.lnstate = value;
            }

            /**
             * Gets the value of the status property.
             *
             */
            public int getSTATUS() {
                return status;
            }

            /**
             * Sets the value of the status property.
             *
             */
            public void setSTATUS(int value) {
                this.status = value;
            }

            /**
             * Gets the value of the errors property.
             *
             * @return
             *     possible object is
             *     {@link INFO.ROWSET.ROW.ERRORS }
             *
             */
            public INFO.ROWSET.ROW.ERRORS getERRORS() {
                return errors;
            }

            /**
             * Sets the value of the errors property.
             *
             * @param value
             *     allowed object is
             *     {@link INFO.ROWSET.ROW.ERRORS }
             *
             */
            public void setERRORS(INFO.ROWSET.ROW.ERRORS value) {
                this.errors = value;
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
             *         &lt;element name="ERROR" maxOccurs="unbounded">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;all>
             *                   &lt;element name="ERR_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="ERR_MESS" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                 &lt;/all>
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
            @XmlType(name = "", propOrder = {
                    "error"
            })
            public static class ERRORS {

                @XmlElement(name = "ERROR", required = true)
                protected List<INFO.ROWSET.ROW.ERRORS.ERROR> error;

                /**
                 * Gets the value of the error property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the error property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getERROR().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link INFO.ROWSET.ROW.ERRORS.ERROR }
                 *
                 *
                 */
                public List<INFO.ROWSET.ROW.ERRORS.ERROR> getERROR() {
                    if (error == null) {
                        error = new ArrayList<INFO.ROWSET.ROW.ERRORS.ERROR>();
                    }
                    return this.error;
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
                 *       &lt;all>
                 *         &lt;element name="ERR_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="ERR_MESS" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *       &lt;/all>
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 *
                 *
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {

                })
                public static class ERROR {

                    @XmlElement(name = "ERR_CODE", required = true)
                    protected String errcode;
                    @XmlElement(name = "ERR_MESS", required = true)
                    protected String errmess;

                    /**
                     * Gets the value of the errcode property.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getERRCODE() {
                        return errcode;
                    }

                    /**
                     * Sets the value of the errcode property.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setERRCODE(String value) {
                        this.errcode = value;
                    }

                    /**
                     * Gets the value of the errmess property.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getERRMESS() {
                        return errmess;
                    }

                    /**
                     * Sets the value of the errmess property.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setERRMESS(String value) {
                        this.errmess = value;
                    }

                }

            }

        }

    }

}
