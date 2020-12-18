
package main.java.ru.genereted.v2.types.eln.v01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * �������������� ��������� �� ������
 * 
 * <p>Java class for Info complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Info">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="infoRowset" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="infoRow" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;all>
 *                             &lt;element name="rowNo" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                             &lt;element name="lnCode" type="{http://www.fss.ru/integration/types/eln/v01}lnCode"/>
 *                             &lt;element name="lnHash" type="{http://www.fss.ru/integration/types/eln/v01}lnHash" minOccurs="0"/>
 *                             &lt;element name="lnState" type="{http://www.fss.ru/integration/types/eln/v01}lnState" minOccurs="0"/>
 *                             &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="errors" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="error" maxOccurs="unbounded">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;all>
 *                                                 &lt;element name="errCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="errMess" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "Info", propOrder = {
    "infoRowset"
})
public class Info {

    protected Info.InfoRowset infoRowset;

    /**
     * Gets the value of the infoRowset property.
     * 
     * @return
     *     possible object is
     *     {@link Info.InfoRowset }
     *     
     */
    public Info.InfoRowset getInfoRowset() {
        return infoRowset;
    }

    /**
     * Sets the value of the infoRowset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Info.InfoRowset }
     *     
     */
    public void setInfoRowset(Info.InfoRowset value) {
        this.infoRowset = value;
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
     *         &lt;element name="infoRow" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;all>
     *                   &lt;element name="rowNo" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *                   &lt;element name="lnCode" type="{http://www.fss.ru/integration/types/eln/v01}lnCode"/>
     *                   &lt;element name="lnHash" type="{http://www.fss.ru/integration/types/eln/v01}lnHash" minOccurs="0"/>
     *                   &lt;element name="lnState" type="{http://www.fss.ru/integration/types/eln/v01}lnState" minOccurs="0"/>
     *                   &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="errors" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="error" maxOccurs="unbounded">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;all>
     *                                       &lt;element name="errCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="errMess" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "infoRow"
    })
    public static class InfoRowset {

        @XmlElement(required = true)
        protected List<Info.InfoRowset.InfoRow> infoRow;

        /**
         * Gets the value of the infoRow property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the infoRow property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInfoRow().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Info.InfoRowset.InfoRow }
         * 
         * 
         */
        public List<Info.InfoRowset.InfoRow> getInfoRow() {
            if (infoRow == null) {
                infoRow = new ArrayList<Info.InfoRowset.InfoRow>();
            }
            return this.infoRow;
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
         *         &lt;element name="rowNo" type="{http://www.w3.org/2001/XMLSchema}integer"/>
         *         &lt;element name="lnCode" type="{http://www.fss.ru/integration/types/eln/v01}lnCode"/>
         *         &lt;element name="lnHash" type="{http://www.fss.ru/integration/types/eln/v01}lnHash" minOccurs="0"/>
         *         &lt;element name="lnState" type="{http://www.fss.ru/integration/types/eln/v01}lnState" minOccurs="0"/>
         *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="errors" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="error" maxOccurs="unbounded">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;all>
         *                             &lt;element name="errCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="errMess" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        public static class InfoRow {

            @XmlElement(required = true, nillable = true)
            protected BigInteger rowNo;
            @XmlElement(required = true)
            protected String lnCode;
            protected String lnHash;
            protected String lnState;
            protected int status;
            protected Info.InfoRowset.InfoRow.Errors errors;

            /**
             * Gets the value of the rowNo property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getRowNo() {
                return rowNo;
            }

            /**
             * Sets the value of the rowNo property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setRowNo(BigInteger value) {
                this.rowNo = value;
            }

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
             * Gets the value of the lnHash property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLnHash() {
                return lnHash;
            }

            /**
             * Sets the value of the lnHash property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLnHash(String value) {
                this.lnHash = value;
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
             * Gets the value of the status property.
             * 
             */
            public int getStatus() {
                return status;
            }

            /**
             * Sets the value of the status property.
             * 
             */
            public void setStatus(int value) {
                this.status = value;
            }

            /**
             * Gets the value of the errors property.
             * 
             * @return
             *     possible object is
             *     {@link Info.InfoRowset.InfoRow.Errors }
             *     
             */
            public Info.InfoRowset.InfoRow.Errors getErrors() {
                return errors;
            }

            /**
             * Sets the value of the errors property.
             * 
             * @param value
             *     allowed object is
             *     {@link Info.InfoRowset.InfoRow.Errors }
             *     
             */
            public void setErrors(Info.InfoRowset.InfoRow.Errors value) {
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
             *         &lt;element name="error" maxOccurs="unbounded">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;all>
             *                   &lt;element name="errCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="errMess" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            public static class Errors {

                @XmlElement(required = true)
                protected List<Info.InfoRowset.InfoRow.Errors.Error> error;

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
                 *    getError().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link Info.InfoRowset.InfoRow.Errors.Error }
                 * 
                 * 
                 */
                public List<Info.InfoRowset.InfoRow.Errors.Error> getError() {
                    if (error == null) {
                        error = new ArrayList<Info.InfoRowset.InfoRow.Errors.Error>();
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
                 *         &lt;element name="errCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="errMess" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
                public static class Error {

                    @XmlElement(required = true)
                    protected String errCode;
                    @XmlElement(required = true)
                    protected String errMess;

                    /**
                     * Gets the value of the errCode property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getErrCode() {
                        return errCode;
                    }

                    /**
                     * Sets the value of the errCode property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setErrCode(String value) {
                        this.errCode = value;
                    }

                    /**
                     * Gets the value of the errMess property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getErrMess() {
                        return errMess;
                    }

                    /**
                     * Sets the value of the errMess property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setErrMess(String value) {
                        this.errMess = value;
                    }

                }

            }

        }

    }

}
