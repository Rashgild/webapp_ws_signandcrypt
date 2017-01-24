
package ru.ibs.fss.ln.ws.fileoperationsln;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ROW complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ROW">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="SNILS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SURNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PATRONIMIC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BOZ_FLAG" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LPU_EMPLOYER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LPU_EMPL_FLAG" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LN_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PREV_LN_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PRIMARY_FLAG" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DUPLICATE_FLAG" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LN_DATE" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="LPU_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LPU_ADDRESS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LPU_OGRN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BIRTHDAY" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="GENDER" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="REASON1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="REASON2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="REASON3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DIAGNOS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PARENT_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DATE1" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="DATE2" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="VOUCHER_NO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VOUCHER_OGRN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SERV1_AGE" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SERV1_MM" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SERV1_RELATION_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SERV1_FIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SERV1_DT1" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="SERV1_DT2" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="SERV2_AGE" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SERV2_MM" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SERV2_RELATION_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SERV2_FIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SERV2_DT1" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="SERV2_DT2" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="PREGN12W_FLAG" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="HOSPITAL_DT1" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="HOSPITAL_DT2" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="HOSPITAL_BREACH" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="HOSPITAL_BREACH_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="HOSPITAL_BREACH_DT" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="MSE_DT1" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="MSE_DT2" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="MSE_DT3" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="MSE_INVALID_GROUP" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TREAT_PERIODS">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TREAT_FULL_PERIOD" type="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}TREAT_FULL_PERIOD" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="LN_RESULT" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="RETURN_DATE_LPU" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                   &lt;element name="MSE_RESULT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="OTHER_STATE_DT" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                   &lt;element name="NEXT_LN_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="LN_STATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LN_HASH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ROW", propOrder = {

})
public class ROW {

    @XmlElement(name = "SNILS", required = true)
    protected String snils;
    @XmlElement(name = "SURNAME", required = true)
    protected String surname;
    @XmlElement(name = "NAME", required = true)
    protected String name;
    @XmlElement(name = "PATRONIMIC")
    protected String patronimic;
    @XmlElement(name = "BOZ_FLAG")
    protected int bozflag;
    @XmlElement(name = "LPU_EMPLOYER")
    protected String lpuemployer;
    @XmlElement(name = "LPU_EMPL_FLAG")
    protected int lpuemplflag;
    @XmlElement(name = "LN_CODE", required = true)
    protected String lncode;
    @XmlElement(name = "PREV_LN_CODE")
    protected String prevlncode;
    @XmlElement(name = "PRIMARY_FLAG")
    protected int primaryflag;
    @XmlElement(name = "DUPLICATE_FLAG")
    protected int duplicateflag;
    @XmlElement(name = "LN_DATE", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lndate;
    @XmlElement(name = "LPU_NAME", required = true)
    protected String lpuname;
    @XmlElement(name = "LPU_ADDRESS")
    protected String lpuaddress;
    @XmlElement(name = "LPU_OGRN", required = true)
    protected String lpuogrn;
    @XmlElement(name = "BIRTHDAY", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthday;
    @XmlElement(name = "GENDER")
    protected int gender;
    @XmlElement(name = "REASON1", required = true)
    protected String reason1;
    @XmlElement(name = "REASON2")
    protected String reason2;
    @XmlElement(name = "REASON3")
    protected String reason3;
    @XmlElement(name = "DIAGNOS")
    protected String diagnos;
    @XmlElement(name = "PARENT_CODE")
    protected String parentcode;
    @XmlElement(name = "DATE1", required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date1;
    @XmlElement(name = "DATE2", required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date2;
    @XmlElement(name = "VOUCHER_NO")
    protected String voucherno;
    @XmlElement(name = "VOUCHER_OGRN")
    protected String voucherogrn;
    @XmlElement(name = "SERV1_AGE", required = true, type = Integer.class, nillable = true)
    protected Integer serv1AGE;
    @XmlElement(name = "SERV1_MM", required = true, type = Integer.class, nillable = true)
    protected Integer serv1MM;
    @XmlElement(name = "SERV1_RELATION_CODE")
    protected String serv1RELATIONCODE;
    @XmlElement(name = "SERV1_FIO")
    protected String serv1FIO;
    @XmlElement(name = "SERV1_DT1", required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar serv1DT1;
    @XmlElement(name = "SERV1_DT2", required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar serv1DT2;
    @XmlElement(name = "SERV2_AGE", required = true, type = Integer.class, nillable = true)
    protected Integer serv2AGE;
    @XmlElement(name = "SERV2_MM", required = true, type = Integer.class, nillable = true)
    protected Integer serv2MM;
    @XmlElement(name = "SERV2_RELATION_CODE")
    protected String serv2RELATIONCODE;
    @XmlElement(name = "SERV2_FIO")
    protected String serv2FIO;
    @XmlElement(name = "SERV2_DT1", required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar serv2DT1;
    @XmlElement(name = "SERV2_DT2", required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar serv2DT2;
    @XmlElement(name = "PREGN12W_FLAG", required = true, type = Integer.class, nillable = true)
    protected Integer pregn12WFLAG;
    @XmlElement(name = "HOSPITAL_DT1", required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar hospitaldt1;
    @XmlElement(name = "HOSPITAL_DT2", required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar hospitaldt2;
    @XmlElement(name = "HOSPITAL_BREACH")
    protected ROW.HOSPITALBREACH hospitalbreach;
    @XmlElement(name = "MSE_DT1", required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar msedt1;
    @XmlElement(name = "MSE_DT2", required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar msedt2;
    @XmlElement(name = "MSE_DT3", required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar msedt3;
    @XmlElement(name = "MSE_INVALID_GROUP", required = true, type = Integer.class, nillable = true)
    protected Integer mseinvalidgroup;
    @XmlElement(name = "TREAT_PERIODS", required = true)
    protected ROW.TREATPERIODS treatperiods;
    @XmlElement(name = "LN_RESULT")
    protected ROW.LNRESULT lnresult;
    @XmlElement(name = "LN_STATE", required = true)
    protected String lnstate;
    @XmlElement(name = "LN_HASH")
    protected String lnhash;

    /**
     * Gets the value of the snils property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSNILS() {
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
    public void setSNILS(String value) {
        this.snils = value;
    }

    /**
     * Gets the value of the surname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSURNAME() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSURNAME(String value) {
        this.surname = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNAME() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNAME(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the patronimic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPATRONIMIC() {
        return patronimic;
    }

    /**
     * Sets the value of the patronimic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPATRONIMIC(String value) {
        this.patronimic = value;
    }

    /**
     * Gets the value of the bozflag property.
     * 
     */
    public int getBOZFLAG() {
        return bozflag;
    }

    /**
     * Sets the value of the bozflag property.
     * 
     */
    public void setBOZFLAG(int value) {
        this.bozflag = value;
    }

    /**
     * Gets the value of the lpuemployer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLPUEMPLOYER() {
        return lpuemployer;
    }

    /**
     * Sets the value of the lpuemployer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLPUEMPLOYER(String value) {
        this.lpuemployer = value;
    }

    /**
     * Gets the value of the lpuemplflag property.
     * 
     */
    public int getLPUEMPLFLAG() {
        return lpuemplflag;
    }

    /**
     * Sets the value of the lpuemplflag property.
     * 
     */
    public void setLPUEMPLFLAG(int value) {
        this.lpuemplflag = value;
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
     * Gets the value of the prevlncode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPREVLNCODE() {
        return prevlncode;
    }

    /**
     * Sets the value of the prevlncode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPREVLNCODE(String value) {
        this.prevlncode = value;
    }

    /**
     * Gets the value of the primaryflag property.
     * 
     */
    public int getPRIMARYFLAG() {
        return primaryflag;
    }

    /**
     * Sets the value of the primaryflag property.
     * 
     */
    public void setPRIMARYFLAG(int value) {
        this.primaryflag = value;
    }

    /**
     * Gets the value of the duplicateflag property.
     * 
     */
    public int getDUPLICATEFLAG() {
        return duplicateflag;
    }

    /**
     * Sets the value of the duplicateflag property.
     * 
     */
    public void setDUPLICATEFLAG(int value) {
        this.duplicateflag = value;
    }

    /**
     * Gets the value of the lndate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLNDATE() {
        return lndate;
    }

    /**
     * Sets the value of the lndate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLNDATE(XMLGregorianCalendar value) {
        this.lndate = value;
    }

    /**
     * Gets the value of the lpuname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLPUNAME() {
        return lpuname;
    }

    /**
     * Sets the value of the lpuname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLPUNAME(String value) {
        this.lpuname = value;
    }

    /**
     * Gets the value of the lpuaddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLPUADDRESS() {
        return lpuaddress;
    }

    /**
     * Sets the value of the lpuaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLPUADDRESS(String value) {
        this.lpuaddress = value;
    }

    /**
     * Gets the value of the lpuogrn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLPUOGRN() {
        return lpuogrn;
    }

    /**
     * Sets the value of the lpuogrn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLPUOGRN(String value) {
        this.lpuogrn = value;
    }

    /**
     * Gets the value of the birthday property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBIRTHDAY() {
        return birthday;
    }

    /**
     * Sets the value of the birthday property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBIRTHDAY(XMLGregorianCalendar value) {
        this.birthday = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     */
    public int getGENDER() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     */
    public void setGENDER(int value) {
        this.gender = value;
    }

    /**
     * Gets the value of the reason1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREASON1() {
        return reason1;
    }

    /**
     * Sets the value of the reason1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREASON1(String value) {
        this.reason1 = value;
    }

    /**
     * Gets the value of the reason2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREASON2() {
        return reason2;
    }

    /**
     * Sets the value of the reason2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREASON2(String value) {
        this.reason2 = value;
    }

    /**
     * Gets the value of the reason3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREASON3() {
        return reason3;
    }

    /**
     * Sets the value of the reason3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREASON3(String value) {
        this.reason3 = value;
    }

    /**
     * Gets the value of the diagnos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDIAGNOS() {
        return diagnos;
    }

    /**
     * Sets the value of the diagnos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDIAGNOS(String value) {
        this.diagnos = value;
    }

    /**
     * Gets the value of the parentcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPARENTCODE() {
        return parentcode;
    }

    /**
     * Sets the value of the parentcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPARENTCODE(String value) {
        this.parentcode = value;
    }

    /**
     * Gets the value of the date1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDATE1() {
        return date1;
    }

    /**
     * Sets the value of the date1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDATE1(XMLGregorianCalendar value) {
        this.date1 = value;
    }

    /**
     * Gets the value of the date2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDATE2() {
        return date2;
    }

    /**
     * Sets the value of the date2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDATE2(XMLGregorianCalendar value) {
        this.date2 = value;
    }

    /**
     * Gets the value of the voucherno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVOUCHERNO() {
        return voucherno;
    }

    /**
     * Sets the value of the voucherno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVOUCHERNO(String value) {
        this.voucherno = value;
    }

    /**
     * Gets the value of the voucherogrn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVOUCHEROGRN() {
        return voucherogrn;
    }

    /**
     * Sets the value of the voucherogrn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVOUCHEROGRN(String value) {
        this.voucherogrn = value;
    }

    /**
     * Gets the value of the serv1AGE property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSERV1AGE() {
        return serv1AGE;
    }

    /**
     * Sets the value of the serv1AGE property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSERV1AGE(Integer value) {
        this.serv1AGE = value;
    }

    /**
     * Gets the value of the serv1MM property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSERV1MM() {
        return serv1MM;
    }

    /**
     * Sets the value of the serv1MM property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSERV1MM(Integer value) {
        this.serv1MM = value;
    }

    /**
     * Gets the value of the serv1RELATIONCODE property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSERV1RELATIONCODE() {
        return serv1RELATIONCODE;
    }

    /**
     * Sets the value of the serv1RELATIONCODE property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSERV1RELATIONCODE(String value) {
        this.serv1RELATIONCODE = value;
    }

    /**
     * Gets the value of the serv1FIO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSERV1FIO() {
        return serv1FIO;
    }

    /**
     * Sets the value of the serv1FIO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSERV1FIO(String value) {
        this.serv1FIO = value;
    }

    /**
     * Gets the value of the serv1DT1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSERV1DT1() {
        return serv1DT1;
    }

    /**
     * Sets the value of the serv1DT1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSERV1DT1(XMLGregorianCalendar value) {
        this.serv1DT1 = value;
    }

    /**
     * Gets the value of the serv1DT2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSERV1DT2() {
        return serv1DT2;
    }

    /**
     * Sets the value of the serv1DT2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSERV1DT2(XMLGregorianCalendar value) {
        this.serv1DT2 = value;
    }

    /**
     * Gets the value of the serv2AGE property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSERV2AGE() {
        return serv2AGE;
    }

    /**
     * Sets the value of the serv2AGE property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSERV2AGE(Integer value) {
        this.serv2AGE = value;
    }

    /**
     * Gets the value of the serv2MM property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSERV2MM() {
        return serv2MM;
    }

    /**
     * Sets the value of the serv2MM property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSERV2MM(Integer value) {
        this.serv2MM = value;
    }

    /**
     * Gets the value of the serv2RELATIONCODE property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSERV2RELATIONCODE() {
        return serv2RELATIONCODE;
    }

    /**
     * Sets the value of the serv2RELATIONCODE property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSERV2RELATIONCODE(String value) {
        this.serv2RELATIONCODE = value;
    }

    /**
     * Gets the value of the serv2FIO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSERV2FIO() {
        return serv2FIO;
    }

    /**
     * Sets the value of the serv2FIO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSERV2FIO(String value) {
        this.serv2FIO = value;
    }

    /**
     * Gets the value of the serv2DT1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSERV2DT1() {
        return serv2DT1;
    }

    /**
     * Sets the value of the serv2DT1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSERV2DT1(XMLGregorianCalendar value) {
        this.serv2DT1 = value;
    }

    /**
     * Gets the value of the serv2DT2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSERV2DT2() {
        return serv2DT2;
    }

    /**
     * Sets the value of the serv2DT2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSERV2DT2(XMLGregorianCalendar value) {
        this.serv2DT2 = value;
    }

    /**
     * Gets the value of the pregn12WFLAG property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPREGN12WFLAG() {
        return pregn12WFLAG;
    }

    /**
     * Sets the value of the pregn12WFLAG property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPREGN12WFLAG(Integer value) {
        this.pregn12WFLAG = value;
    }

    /**
     * Gets the value of the hospitaldt1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getHOSPITALDT1() {
        return hospitaldt1;
    }

    /**
     * Sets the value of the hospitaldt1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setHOSPITALDT1(XMLGregorianCalendar value) {
        this.hospitaldt1 = value;
    }

    /**
     * Gets the value of the hospitaldt2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getHOSPITALDT2() {
        return hospitaldt2;
    }

    /**
     * Sets the value of the hospitaldt2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setHOSPITALDT2(XMLGregorianCalendar value) {
        this.hospitaldt2 = value;
    }

    /**
     * Gets the value of the hospitalbreach property.
     * 
     * @return
     *     possible object is
     *     {@link ROW.HOSPITALBREACH }
     *     
     */
    public ROW.HOSPITALBREACH getHOSPITALBREACH() {
        return hospitalbreach;
    }

    /**
     * Sets the value of the hospitalbreach property.
     * 
     * @param value
     *     allowed object is
     *     {@link ROW.HOSPITALBREACH }
     *     
     */
    public void setHOSPITALBREACH(ROW.HOSPITALBREACH value) {
        this.hospitalbreach = value;
    }

    /**
     * Gets the value of the msedt1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMSEDT1() {
        return msedt1;
    }

    /**
     * Sets the value of the msedt1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMSEDT1(XMLGregorianCalendar value) {
        this.msedt1 = value;
    }

    /**
     * Gets the value of the msedt2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMSEDT2() {
        return msedt2;
    }

    /**
     * Sets the value of the msedt2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMSEDT2(XMLGregorianCalendar value) {
        this.msedt2 = value;
    }

    /**
     * Gets the value of the msedt3 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMSEDT3() {
        return msedt3;
    }

    /**
     * Sets the value of the msedt3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMSEDT3(XMLGregorianCalendar value) {
        this.msedt3 = value;
    }

    /**
     * Gets the value of the mseinvalidgroup property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMSEINVALIDGROUP() {
        return mseinvalidgroup;
    }

    /**
     * Sets the value of the mseinvalidgroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMSEINVALIDGROUP(Integer value) {
        this.mseinvalidgroup = value;
    }

    /**
     * Gets the value of the treatperiods property.
     * 
     * @return
     *     possible object is
     *     {@link ROW.TREATPERIODS }
     *     
     */
    public ROW.TREATPERIODS getTREATPERIODS() {
        return treatperiods;
    }

    /**
     * Sets the value of the treatperiods property.
     * 
     * @param value
     *     allowed object is
     *     {@link ROW.TREATPERIODS }
     *     
     */
    public void setTREATPERIODS(ROW.TREATPERIODS value) {
        this.treatperiods = value;
    }

    /**
     * Gets the value of the lnresult property.
     * 
     * @return
     *     possible object is
     *     {@link ROW.LNRESULT }
     *     
     */
    public ROW.LNRESULT getLNRESULT() {
        return lnresult;
    }

    /**
     * Sets the value of the lnresult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ROW.LNRESULT }
     *     
     */
    public void setLNRESULT(ROW.LNRESULT value) {
        this.lnresult = value;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="HOSPITAL_BREACH_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="HOSPITAL_BREACH_DT" type="{http://www.w3.org/2001/XMLSchema}date"/>
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
        "hospitalbreachcode",
        "hospitalbreachdt"
    })
    public static class HOSPITALBREACH {

        @XmlElement(name = "HOSPITAL_BREACH_CODE", required = true)
        protected String hospitalbreachcode;
        @XmlElement(name = "HOSPITAL_BREACH_DT", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar hospitalbreachdt;

        /**
         * Gets the value of the hospitalbreachcode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHOSPITALBREACHCODE() {
            return hospitalbreachcode;
        }

        /**
         * Sets the value of the hospitalbreachcode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHOSPITALBREACHCODE(String value) {
            this.hospitalbreachcode = value;
        }

        /**
         * Gets the value of the hospitalbreachdt property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getHOSPITALBREACHDT() {
            return hospitalbreachdt;
        }

        /**
         * Sets the value of the hospitalbreachdt property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setHOSPITALBREACHDT(XMLGregorianCalendar value) {
            this.hospitalbreachdt = value;
        }

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
     *         &lt;element name="RETURN_DATE_LPU" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *         &lt;element name="MSE_RESULT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="OTHER_STATE_DT" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *         &lt;element name="NEXT_LN_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    public static class LNRESULT {

        @XmlElement(name = "RETURN_DATE_LPU", required = true, nillable = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar returndatelpu;
        @XmlElement(name = "MSE_RESULT")
        protected String mseresult;
        @XmlElement(name = "OTHER_STATE_DT", required = true, nillable = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar otherstatedt;
        @XmlElement(name = "NEXT_LN_CODE")
        protected String nextlncode;

        /**
         * Gets the value of the returndatelpu property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getRETURNDATELPU() {
            return returndatelpu;
        }

        /**
         * Sets the value of the returndatelpu property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setRETURNDATELPU(XMLGregorianCalendar value) {
            this.returndatelpu = value;
        }

        /**
         * Gets the value of the mseresult property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMSERESULT() {
            return mseresult;
        }

        /**
         * Sets the value of the mseresult property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMSERESULT(String value) {
            this.mseresult = value;
        }

        /**
         * Gets the value of the otherstatedt property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getOTHERSTATEDT() {
            return otherstatedt;
        }

        /**
         * Sets the value of the otherstatedt property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setOTHERSTATEDT(XMLGregorianCalendar value) {
            this.otherstatedt = value;
        }

        /**
         * Gets the value of the nextlncode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNEXTLNCODE() {
            return nextlncode;
        }

        /**
         * Sets the value of the nextlncode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNEXTLNCODE(String value) {
            this.nextlncode = value;
        }

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
     *         &lt;element name="TREAT_FULL_PERIOD" type="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}TREAT_FULL_PERIOD" maxOccurs="unbounded"/>
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
        "treatfullperiod"
    })
    public static class TREATPERIODS {

        @XmlElement(name = "TREAT_FULL_PERIOD", required = true)
        protected List<TREATFULLPERIOD> treatfullperiod;

        /**
         * Gets the value of the treatfullperiod property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the treatfullperiod property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTREATFULLPERIOD().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TREATFULLPERIOD }
         * 
         * 
         */
        public List<TREATFULLPERIOD> getTREATFULLPERIOD() {
            if (treatfullperiod == null) {
                treatfullperiod = new ArrayList<TREATFULLPERIOD>();
            }
            return this.treatfullperiod;
        }

    }

}
