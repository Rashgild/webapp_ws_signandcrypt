
package main.java.ru.genereted.v2.types.commom.v01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import main.java.ru.genereted.v2.types.dic.errors.v01.BusinessErrorCodeType;


/**
 * <p>Java class for BusinessErrorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessErrorType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fss.ru/integration/types/common/v01}BaseErrorType">
 *       &lt;sequence>
 *         &lt;element name="errorCode" type="{http://www.fss.ru/integration/types/dic/errors/v01}BusinessErrorCodeType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessErrorType", propOrder = {
    "errorCode"
})
public class BusinessErrorType
    extends BaseErrorType
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected BusinessErrorCodeType errorCode;

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessErrorCodeType }
     *     
     */
    public BusinessErrorCodeType getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessErrorCodeType }
     *     
     */
    public void setErrorCode(BusinessErrorCodeType value) {
        this.errorCode = value;
    }

}
