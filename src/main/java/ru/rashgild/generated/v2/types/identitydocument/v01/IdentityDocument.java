
package ru.rashgild.generated.v2.types.identitydocument.v01;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ������������� ��������
 * 
 * <p>Java class for identityDocument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="identityDocument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codeType" type="{http://www.fss.ru/integration/types/identityDocument/v01}code"/>
 *         &lt;element name="number" type="{http://www.fss.ru/integration/types/identityDocument/v01}number"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "identityDocument", propOrder = {
    "codeType",
    "number"
})
public class IdentityDocument {

    @XmlElement(required = true)
    protected BigInteger codeType;
    @XmlElement(required = true)
    protected String number;

    /**
     * Gets the value of the codeType property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCodeType() {
        return codeType;
    }

    /**
     * Sets the value of the codeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCodeType(BigInteger value) {
        this.codeType = value;
    }

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumber(String value) {
        this.number = value;
    }

}
