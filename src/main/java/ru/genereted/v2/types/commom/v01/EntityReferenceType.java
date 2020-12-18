
package main.java.ru.genereted.v2.types.commom.v01;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EntityReferenceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EntityReferenceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entityVersId" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="entityId" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="subsystemId" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="subsystemEntityId" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntityReferenceType", propOrder = {
    "entityVersId",
    "entityId",
    "subsystemId",
    "subsystemEntityId"
})
public class EntityReferenceType {

    @XmlElement(required = true)
    protected BigDecimal entityVersId;
    @XmlElement(required = true)
    protected BigDecimal entityId;
    @XmlElement(required = true)
    protected BigDecimal subsystemId;
    protected BigDecimal subsystemEntityId;

    /**
     * Gets the value of the entityVersId property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEntityVersId() {
        return entityVersId;
    }

    /**
     * Sets the value of the entityVersId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEntityVersId(BigDecimal value) {
        this.entityVersId = value;
    }

    /**
     * Gets the value of the entityId property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEntityId() {
        return entityId;
    }

    /**
     * Sets the value of the entityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEntityId(BigDecimal value) {
        this.entityId = value;
    }

    /**
     * Gets the value of the subsystemId property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSubsystemId() {
        return subsystemId;
    }

    /**
     * Sets the value of the subsystemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSubsystemId(BigDecimal value) {
        this.subsystemId = value;
    }

    /**
     * Gets the value of the subsystemEntityId property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSubsystemEntityId() {
        return subsystemEntityId;
    }

    /**
     * Sets the value of the subsystemEntityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSubsystemEntityId(BigDecimal value) {
        this.subsystemEntityId = value;
    }

}
