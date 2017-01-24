
package ru.ibs.fss.ln.ws.fileoperationslninternal;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SIGN_ROWSET complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SIGN_ROWSET">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ROW" maxOccurs="unbounded" form="qualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *                   &lt;element name="ACTOR" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *                   &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}int" form="qualified"/>
 *                   &lt;element name="TYPE" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *                   &lt;element name="MESS" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *                   &lt;element name="SIGNATURE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *                   &lt;element name="CERT" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *                   &lt;element name="SUBJECT_COMMON_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *                   &lt;element name="SUBJECT_TITLE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *                   &lt;element name="SUBJECT_ORGANIZATION_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *                   &lt;element name="SUBJECT_OGRN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *                   &lt;element name="SUBJECT_INN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *                   &lt;element name="SUBJECT_FIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *                   &lt;element name="DATE_NOT_BEFORE" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0" form="qualified"/>
 *                   &lt;element name="DATE_NOT_AFTER" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0" form="qualified"/>
 *                   &lt;element name="ISSUER_COMMON_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *                   &lt;element name="ISSUER_ORGANIZATION_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
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
@XmlType(name = "SIGN_ROWSET", propOrder = {
    "row"
})
public class SIGNROWSET {

    @XmlElement(name = "ROW", required = true)
    protected List<SIGNROWSET.ROW> row;

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
     * {@link SIGNROWSET.ROW }
     * 
     * 
     */
    public List<SIGNROWSET.ROW> getROW() {
        if (row == null) {
            row = new ArrayList<SIGNROWSET.ROW>();
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
     *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
     *         &lt;element name="ACTOR" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
     *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}int" form="qualified"/>
     *         &lt;element name="TYPE" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
     *         &lt;element name="MESS" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
     *         &lt;element name="SIGNATURE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
     *         &lt;element name="CERT" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
     *         &lt;element name="SUBJECT_COMMON_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
     *         &lt;element name="SUBJECT_TITLE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
     *         &lt;element name="SUBJECT_ORGANIZATION_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
     *         &lt;element name="SUBJECT_OGRN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
     *         &lt;element name="SUBJECT_INN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
     *         &lt;element name="SUBJECT_FIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
     *         &lt;element name="DATE_NOT_BEFORE" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0" form="qualified"/>
     *         &lt;element name="DATE_NOT_AFTER" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0" form="qualified"/>
     *         &lt;element name="ISSUER_COMMON_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
     *         &lt;element name="ISSUER_ORGANIZATION_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
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

        @XmlElement(name = "ID", required = true)
        protected String id;
        @XmlElement(name = "ACTOR", required = true)
        protected String actor;
        @XmlElement(name = "STATUS")
        protected int status;
        @XmlElement(name = "TYPE", required = true)
        protected String type;
        @XmlElement(name = "MESS", required = true)
        protected String mess;
        @XmlElement(name = "SIGNATURE")
        protected String signature;
        @XmlElement(name = "CERT", required = true)
        protected String cert;
        @XmlElement(name = "SUBJECT_COMMON_NAME")
        protected String subjectcommonname;
        @XmlElement(name = "SUBJECT_TITLE")
        protected String subjecttitle;
        @XmlElement(name = "SUBJECT_ORGANIZATION_NAME")
        protected String subjectorganizationname;
        @XmlElement(name = "SUBJECT_OGRN")
        protected String subjectogrn;
        @XmlElement(name = "SUBJECT_INN")
        protected String subjectinn;
        @XmlElement(name = "SUBJECT_FIO")
        protected String subjectfio;
        @XmlElement(name = "DATE_NOT_BEFORE")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar datenotbefore;
        @XmlElement(name = "DATE_NOT_AFTER")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar datenotafter;
        @XmlElement(name = "ISSUER_COMMON_NAME")
        protected String issuercommonname;
        @XmlElement(name = "ISSUER_ORGANIZATION_NAME")
        protected String issuerorganizationname;

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getID() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setID(String value) {
            this.id = value;
        }

        /**
         * Gets the value of the actor property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getACTOR() {
            return actor;
        }

        /**
         * Sets the value of the actor property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setACTOR(String value) {
            this.actor = value;
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
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTYPE() {
            return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTYPE(String value) {
            this.type = value;
        }

        /**
         * Gets the value of the mess property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMESS() {
            return mess;
        }

        /**
         * Sets the value of the mess property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMESS(String value) {
            this.mess = value;
        }

        /**
         * Gets the value of the signature property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSIGNATURE() {
            return signature;
        }

        /**
         * Sets the value of the signature property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSIGNATURE(String value) {
            this.signature = value;
        }

        /**
         * Gets the value of the cert property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCERT() {
            return cert;
        }

        /**
         * Sets the value of the cert property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCERT(String value) {
            this.cert = value;
        }

        /**
         * Gets the value of the subjectcommonname property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSUBJECTCOMMONNAME() {
            return subjectcommonname;
        }

        /**
         * Sets the value of the subjectcommonname property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSUBJECTCOMMONNAME(String value) {
            this.subjectcommonname = value;
        }

        /**
         * Gets the value of the subjecttitle property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSUBJECTTITLE() {
            return subjecttitle;
        }

        /**
         * Sets the value of the subjecttitle property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSUBJECTTITLE(String value) {
            this.subjecttitle = value;
        }

        /**
         * Gets the value of the subjectorganizationname property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSUBJECTORGANIZATIONNAME() {
            return subjectorganizationname;
        }

        /**
         * Sets the value of the subjectorganizationname property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSUBJECTORGANIZATIONNAME(String value) {
            this.subjectorganizationname = value;
        }

        /**
         * Gets the value of the subjectogrn property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSUBJECTOGRN() {
            return subjectogrn;
        }

        /**
         * Sets the value of the subjectogrn property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSUBJECTOGRN(String value) {
            this.subjectogrn = value;
        }

        /**
         * Gets the value of the subjectinn property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSUBJECTINN() {
            return subjectinn;
        }

        /**
         * Sets the value of the subjectinn property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSUBJECTINN(String value) {
            this.subjectinn = value;
        }

        /**
         * Gets the value of the subjectfio property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSUBJECTFIO() {
            return subjectfio;
        }

        /**
         * Sets the value of the subjectfio property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSUBJECTFIO(String value) {
            this.subjectfio = value;
        }

        /**
         * Gets the value of the datenotbefore property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDATENOTBEFORE() {
            return datenotbefore;
        }

        /**
         * Sets the value of the datenotbefore property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDATENOTBEFORE(XMLGregorianCalendar value) {
            this.datenotbefore = value;
        }

        /**
         * Gets the value of the datenotafter property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDATENOTAFTER() {
            return datenotafter;
        }

        /**
         * Sets the value of the datenotafter property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDATENOTAFTER(XMLGregorianCalendar value) {
            this.datenotafter = value;
        }

        /**
         * Gets the value of the issuercommonname property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getISSUERCOMMONNAME() {
            return issuercommonname;
        }

        /**
         * Sets the value of the issuercommonname property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setISSUERCOMMONNAME(String value) {
            this.issuercommonname = value;
        }

        /**
         * Gets the value of the issuerorganizationname property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getISSUERORGANIZATIONNAME() {
            return issuerorganizationname;
        }

        /**
         * Sets the value of the issuerorganizationname property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setISSUERORGANIZATIONNAME(String value) {
            this.issuerorganizationname = value;
        }

    }

}
