package ru.rashgild.generated.v2.types.eln.v01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * ������ ������������������: ��� ������
 * 
 * <p>Java class for TreatFullPeriodMo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TreatFullPeriodMo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="treatChairman" type="{http://www.fss.ru/integration/types/eln/v01}fio" minOccurs="0"/>
 *         &lt;element name="treatChairmanRole" type="{http://www.fss.ru/integration/types/eln/v01}doctorRole" minOccurs="0"/>
 *         &lt;element name="treatPeriod">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.fss.ru/integration/types/eln/v01}TreatPeriod">
 *                 &lt;attribute ref="{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Id"/>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Id"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TreatFullPeriodMo", propOrder = {
    "treatChairman",
    "treatChairmanRole",
    "treatPeriod"
})
public class TreatFullPeriodMo {

    protected String treatChairman;
    protected String treatChairmanRole;
    @XmlElement(required = true)
    protected TreatFullPeriodMo.TreatPeriod treatPeriod;
    @XmlAttribute(name = "Id", namespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the treatChairman property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTreatChairman() {
        return treatChairman;
    }

    /**
     * Sets the value of the treatChairman property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTreatChairman(String value) {
        this.treatChairman = value;
    }

    /**
     * Gets the value of the treatChairmanRole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTreatChairmanRole() {
        return treatChairmanRole;
    }

    /**
     * Sets the value of the treatChairmanRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTreatChairmanRole(String value) {
        this.treatChairmanRole = value;
    }

    /**
     * Gets the value of the treatPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link TreatFullPeriodMo.TreatPeriod }
     *     
     */
    public TreatFullPeriodMo.TreatPeriod getTreatPeriod() {
        return treatPeriod;
    }

    /**
     * Sets the value of the treatPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link TreatFullPeriodMo.TreatPeriod }
     *     
     */
    public void setTreatPeriod(TreatFullPeriodMo.TreatPeriod value) {
        this.treatPeriod = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
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
    public void setId(String value) {
        this.id = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.fss.ru/integration/types/eln/v01}TreatPeriod">
     *       &lt;attribute ref="{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Id"/>
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class TreatPeriod
        extends ru.rashgild.generated.v2.types.eln.v01.TreatPeriod
    {

        @XmlAttribute(name = "Id", namespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        @XmlSchemaType(name = "ID")
        protected String id;

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
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
        public void setId(String value) {
            this.id = value;
        }

    }

}
