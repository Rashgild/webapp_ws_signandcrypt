
package ru.rashgild.generated.v2.types.person.v01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for address complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="address">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="residence" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isFixPlaceResidence" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="placeRegistration" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isHomeless" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "address", propOrder = {
    "residence",
    "isFixPlaceResidence",
    "placeRegistration",
    "isHomeless"
})
public class Address {

    @XmlElement(required = true)
    protected String residence;
    protected boolean isFixPlaceResidence;
    @XmlElement(required = true)
    protected String placeRegistration;
    protected boolean isHomeless;

    /**
     * Gets the value of the residence property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResidence() {
        return residence;
    }

    /**
     * Sets the value of the residence property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResidence(String value) {
        this.residence = value;
    }

    /**
     * Gets the value of the isFixPlaceResidence property.
     * 
     */
    public boolean isIsFixPlaceResidence() {
        return isFixPlaceResidence;
    }

    /**
     * Sets the value of the isFixPlaceResidence property.
     * 
     */
    public void setIsFixPlaceResidence(boolean value) {
        this.isFixPlaceResidence = value;
    }

    /**
     * Gets the value of the placeRegistration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlaceRegistration() {
        return placeRegistration;
    }

    /**
     * Sets the value of the placeRegistration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlaceRegistration(String value) {
        this.placeRegistration = value;
    }

    /**
     * Gets the value of the isHomeless property.
     * 
     */
    public boolean isIsHomeless() {
        return isHomeless;
    }

    /**
     * Sets the value of the isHomeless property.
     * 
     */
    public void setIsHomeless(boolean value) {
        this.isHomeless = value;
    }

}
