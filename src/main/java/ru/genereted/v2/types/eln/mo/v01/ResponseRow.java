
package main.java.ru.genereted.v2.types.eln.mo.v01;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import main.java.ru.genereted.v2.types.eln.v01.HospitalBreachInfo;
import main.java.ru.genereted.v2.types.eln.v01.LnResult;
import main.java.ru.genereted.v2.types.eln.v01.TreatFullPeriodMo;


/**
 * �������� ������ ������ ������������������ - ����� GetLNData
 * 
 * <p>Java class for ResponseRow complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseRow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="snils" type="{http://www.fss.ru/integration/types/person/v01}snils"/>
 *         &lt;element name="surname" type="{http://www.fss.ru/integration/types/eln/v01}surname"/>
 *         &lt;element name="name" type="{http://www.fss.ru/integration/types/eln/v01}name"/>
 *         &lt;element name="patronymic" type="{http://www.fss.ru/integration/types/eln/v01}patronymic" minOccurs="0"/>
 *         &lt;element name="lnCode" type="{http://www.fss.ru/integration/types/eln/v01}lnCode"/>
 *         &lt;element name="prevLnCode" type="{http://www.fss.ru/integration/types/eln/v01}lnCode" minOccurs="0"/>
 *         &lt;element name="primaryFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="duplicateFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lnDate" type="{http://www.fss.ru/integration/types/eln/v01}simpleDate"/>
 *         &lt;element name="idMo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lpuName" type="{http://www.fss.ru/integration/types/eln/v01}lpuName"/>
 *         &lt;element name="lpuAddress" type="{http://www.fss.ru/integration/types/eln/v01}lpuAddress" minOccurs="0"/>
 *         &lt;element name="lpuOgrn" type="{http://www.fss.ru/integration/types/organization/v01}OgrnType"/>
 *         &lt;element name="birthday" type="{http://www.fss.ru/integration/types/eln/v01}simpleDate"/>
 *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="reason1" type="{http://www.fss.ru/integration/types/eln/v01}dictCode" minOccurs="0"/>
 *         &lt;element name="reason2" type="{http://www.fss.ru/integration/types/eln/v01}dictCode" minOccurs="0"/>
 *         &lt;element name="diagnos" type="{http://www.fss.ru/integration/types/eln/v01}diagnosis" minOccurs="0"/>
 *         &lt;element name="date1" type="{http://www.fss.ru/integration/types/eln/v01}simpleDate" minOccurs="0"/>
 *         &lt;element name="date2" type="{http://www.fss.ru/integration/types/eln/v01}simpleDate" minOccurs="0"/>
 *         &lt;element name="voucherNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="voucherOgrn" type="{http://www.fss.ru/integration/types/organization/v01}OgrnType" minOccurs="0"/>
 *         &lt;element name="servData" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="servFullData" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;extension base="{http://www.fss.ru/integration/types/eln/v01}ServFullData">
 *                           &lt;sequence>
 *                             &lt;element name="diagnosis" type="{http://www.fss.ru/integration/types/eln/v01}diagnosis" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="pregn12wFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="hospitalDt1" type="{http://www.fss.ru/integration/types/eln/v01}simpleDate" minOccurs="0"/>
 *         &lt;element name="hospitalDt2" type="{http://www.fss.ru/integration/types/eln/v01}simpleDate" minOccurs="0"/>
 *         &lt;element name="hospitalBreach" type="{http://www.fss.ru/integration/types/eln/v01}HospitalBreachInfo" minOccurs="0"/>
 *         &lt;element name="mseDt1" type="{http://www.fss.ru/integration/types/eln/v01}simpleDate" minOccurs="0"/>
 *         &lt;element name="mseDt2" type="{http://www.fss.ru/integration/types/eln/v01}simpleDate" minOccurs="0"/>
 *         &lt;element name="mseDt3" type="{http://www.fss.ru/integration/types/eln/v01}simpleDate" minOccurs="0"/>
 *         &lt;element name="mseInvalidGroup" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="treatPeriods">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="treatFullPeriod" type="{http://www.fss.ru/integration/types/eln/v01}TreatFullPeriodMo" maxOccurs="3"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="lnResult" type="{http://www.fss.ru/integration/types/eln/v01}LnResult" minOccurs="0"/>
 *         &lt;element name="lnState" type="{http://www.fss.ru/integration/types/eln/v01}lnState"/>
 *         &lt;element name="lnHash" type="{http://www.fss.ru/integration/types/eln/v01}lnHash" minOccurs="0"/>
 *         &lt;element name="previouslyIssuedCode" type="{http://www.fss.ru/integration/types/eln/v01}lnCode" minOccurs="0"/>
 *         &lt;element name="writtenAgreementFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="intermittentMethodFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseRow", propOrder = {

})
public class ResponseRow {

    @XmlElement(required = true)
    protected String snils;
    @XmlElement(required = true)
    protected String surname;
    @XmlElement(required = true)
    protected String name;
    protected String patronymic;
    @XmlElement(required = true)
    protected String lnCode;
    protected String prevLnCode;
    protected boolean primaryFlag;
    protected boolean duplicateFlag;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lnDate;
    protected String idMo;
    @XmlElement(required = true)
    protected String lpuName;
    protected String lpuAddress;
    @XmlElement(required = true)
    protected String lpuOgrn;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthday;
    protected int gender;
    protected String reason1;
    protected String reason2;
    protected String diagnos;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date1;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date2;
    protected String voucherNo;
    protected String voucherOgrn;
    protected ResponseRow.ServData servData;
    @XmlElement(name = "pregn12wFlag")
    protected Boolean pregn12WFlag;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar hospitalDt1;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar hospitalDt2;
    protected HospitalBreachInfo hospitalBreach;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar mseDt1;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar mseDt2;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar mseDt3;
    protected Integer mseInvalidGroup;
    @XmlElement(required = true)
    protected ResponseRow.TreatPeriods treatPeriods;
    protected LnResult lnResult;
    @XmlElement(required = true)
    protected String lnState;
    protected String lnHash;
    protected String previouslyIssuedCode;
    protected Boolean writtenAgreementFlag;
    protected Boolean intermittentMethodFlag;

    /**
     * Gets the value of the snils property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnils() {
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
    public void setSnils(String value) {
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
    public String getSurname() {
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
    public void setSurname(String value) {
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
    public String getName() {
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
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the patronymic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Sets the value of the patronymic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatronymic(String value) {
        this.patronymic = value;
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
     * Gets the value of the prevLnCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrevLnCode() {
        return prevLnCode;
    }

    /**
     * Sets the value of the prevLnCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrevLnCode(String value) {
        this.prevLnCode = value;
    }

    /**
     * Gets the value of the primaryFlag property.
     * 
     */
    public boolean isPrimaryFlag() {
        return primaryFlag;
    }

    /**
     * Sets the value of the primaryFlag property.
     * 
     */
    public void setPrimaryFlag(boolean value) {
        this.primaryFlag = value;
    }

    /**
     * Gets the value of the duplicateFlag property.
     * 
     */
    public boolean isDuplicateFlag() {
        return duplicateFlag;
    }

    /**
     * Sets the value of the duplicateFlag property.
     * 
     */
    public void setDuplicateFlag(boolean value) {
        this.duplicateFlag = value;
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
     * Gets the value of the idMo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMo() {
        return idMo;
    }

    /**
     * Sets the value of the idMo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMo(String value) {
        this.idMo = value;
    }

    /**
     * Gets the value of the lpuName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLpuName() {
        return lpuName;
    }

    /**
     * Sets the value of the lpuName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLpuName(String value) {
        this.lpuName = value;
    }

    /**
     * Gets the value of the lpuAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLpuAddress() {
        return lpuAddress;
    }

    /**
     * Sets the value of the lpuAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLpuAddress(String value) {
        this.lpuAddress = value;
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

    /**
     * Gets the value of the birthday property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthday() {
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
    public void setBirthday(XMLGregorianCalendar value) {
        this.birthday = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     */
    public int getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     */
    public void setGender(int value) {
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
    public String getReason1() {
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
    public void setReason1(String value) {
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
    public String getReason2() {
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
    public void setReason2(String value) {
        this.reason2 = value;
    }

    /**
     * Gets the value of the diagnos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiagnos() {
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
    public void setDiagnos(String value) {
        this.diagnos = value;
    }

    /**
     * Gets the value of the date1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate1() {
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
    public void setDate1(XMLGregorianCalendar value) {
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
    public XMLGregorianCalendar getDate2() {
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
    public void setDate2(XMLGregorianCalendar value) {
        this.date2 = value;
    }

    /**
     * Gets the value of the voucherNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoucherNo() {
        return voucherNo;
    }

    /**
     * Sets the value of the voucherNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoucherNo(String value) {
        this.voucherNo = value;
    }

    /**
     * Gets the value of the voucherOgrn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoucherOgrn() {
        return voucherOgrn;
    }

    /**
     * Sets the value of the voucherOgrn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoucherOgrn(String value) {
        this.voucherOgrn = value;
    }

    /**
     * Gets the value of the servData property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseRow.ServData }
     *     
     */
    public ResponseRow.ServData getServData() {
        return servData;
    }

    /**
     * Sets the value of the servData property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseRow.ServData }
     *     
     */
    public void setServData(ResponseRow.ServData value) {
        this.servData = value;
    }

    /**
     * Gets the value of the pregn12WFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPregn12WFlag() {
        return pregn12WFlag;
    }

    /**
     * Sets the value of the pregn12WFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPregn12WFlag(Boolean value) {
        this.pregn12WFlag = value;
    }

    /**
     * Gets the value of the hospitalDt1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getHospitalDt1() {
        return hospitalDt1;
    }

    /**
     * Sets the value of the hospitalDt1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setHospitalDt1(XMLGregorianCalendar value) {
        this.hospitalDt1 = value;
    }

    /**
     * Gets the value of the hospitalDt2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getHospitalDt2() {
        return hospitalDt2;
    }

    /**
     * Sets the value of the hospitalDt2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setHospitalDt2(XMLGregorianCalendar value) {
        this.hospitalDt2 = value;
    }

    /**
     * Gets the value of the hospitalBreach property.
     * 
     * @return
     *     possible object is
     *     {@link HospitalBreachInfo }
     *     
     */
    public HospitalBreachInfo getHospitalBreach() {
        return hospitalBreach;
    }

    /**
     * Sets the value of the hospitalBreach property.
     * 
     * @param value
     *     allowed object is
     *     {@link HospitalBreachInfo }
     *     
     */
    public void setHospitalBreach(HospitalBreachInfo value) {
        this.hospitalBreach = value;
    }

    /**
     * Gets the value of the mseDt1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMseDt1() {
        return mseDt1;
    }

    /**
     * Sets the value of the mseDt1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMseDt1(XMLGregorianCalendar value) {
        this.mseDt1 = value;
    }

    /**
     * Gets the value of the mseDt2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMseDt2() {
        return mseDt2;
    }

    /**
     * Sets the value of the mseDt2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMseDt2(XMLGregorianCalendar value) {
        this.mseDt2 = value;
    }

    /**
     * Gets the value of the mseDt3 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMseDt3() {
        return mseDt3;
    }

    /**
     * Sets the value of the mseDt3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMseDt3(XMLGregorianCalendar value) {
        this.mseDt3 = value;
    }

    /**
     * Gets the value of the mseInvalidGroup property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMseInvalidGroup() {
        return mseInvalidGroup;
    }

    /**
     * Sets the value of the mseInvalidGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMseInvalidGroup(Integer value) {
        this.mseInvalidGroup = value;
    }

    /**
     * Gets the value of the treatPeriods property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseRow.TreatPeriods }
     *     
     */
    public ResponseRow.TreatPeriods getTreatPeriods() {
        return treatPeriods;
    }

    /**
     * Sets the value of the treatPeriods property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseRow.TreatPeriods }
     *     
     */
    public void setTreatPeriods(ResponseRow.TreatPeriods value) {
        this.treatPeriods = value;
    }

    /**
     * Gets the value of the lnResult property.
     * 
     * @return
     *     possible object is
     *     {@link LnResult }
     *     
     */
    public LnResult getLnResult() {
        return lnResult;
    }

    /**
     * Sets the value of the lnResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link LnResult }
     *     
     */
    public void setLnResult(LnResult value) {
        this.lnResult = value;
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
     * Gets the value of the previouslyIssuedCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreviouslyIssuedCode() {
        return previouslyIssuedCode;
    }

    /**
     * Sets the value of the previouslyIssuedCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreviouslyIssuedCode(String value) {
        this.previouslyIssuedCode = value;
    }

    /**
     * Gets the value of the writtenAgreementFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWrittenAgreementFlag() {
        return writtenAgreementFlag;
    }

    /**
     * Sets the value of the writtenAgreementFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWrittenAgreementFlag(Boolean value) {
        this.writtenAgreementFlag = value;
    }

    /**
     * Gets the value of the intermittentMethodFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIntermittentMethodFlag() {
        return intermittentMethodFlag;
    }

    /**
     * Sets the value of the intermittentMethodFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIntermittentMethodFlag(Boolean value) {
        this.intermittentMethodFlag = value;
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
     *         &lt;element name="servFullData" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;extension base="{http://www.fss.ru/integration/types/eln/v01}ServFullData">
     *                 &lt;sequence>
     *                   &lt;element name="diagnosis" type="{http://www.fss.ru/integration/types/eln/v01}diagnosis" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/extension>
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
        "servFullData"
    })
    public static class ServData {

        protected List<ResponseRow.ServData.ServFullData> servFullData;

        /**
         * Gets the value of the servFullData property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the servFullData property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getServFullData().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ResponseRow.ServData.ServFullData }
         * 
         * 
         */
        public List<ResponseRow.ServData.ServFullData> getServFullData() {
            if (servFullData == null) {
                servFullData = new ArrayList<ResponseRow.ServData.ServFullData>();
            }
            return this.servFullData;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;extension base="{http://www.fss.ru/integration/types/eln/v01}ServFullData">
         *       &lt;sequence>
         *         &lt;element name="diagnosis" type="{http://www.fss.ru/integration/types/eln/v01}diagnosis" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/extension>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "diagnosis"
        })
        public static class ServFullData
            extends main.java.ru.genereted.v2.types.eln.v01.ServFullData
        {

            protected String diagnosis;

            /**
             * Gets the value of the diagnosis property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDiagnosis() {
                return diagnosis;
            }

            /**
             * Sets the value of the diagnosis property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDiagnosis(String value) {
                this.diagnosis = value;
            }

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
     *         &lt;element name="treatFullPeriod" type="{http://www.fss.ru/integration/types/eln/v01}TreatFullPeriodMo" maxOccurs="3"/>
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
        "treatFullPeriod"
    })
    public static class TreatPeriods {

        @XmlElement(required = true)
        protected List<TreatFullPeriodMo> treatFullPeriod;

        /**
         * Gets the value of the treatFullPeriod property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the treatFullPeriod property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTreatFullPeriod().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TreatFullPeriodMo }
         * 
         * 
         */
        public List<TreatFullPeriodMo> getTreatFullPeriod() {
            if (treatFullPeriod == null) {
                treatFullPeriod = new ArrayList<TreatFullPeriodMo>();
            }
            return this.treatFullPeriod;
        }

    }

}
