
package ru.rashgild.generated.v2.types.commom.v01;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import ru.rashgild.generated.v2.types.dic.errors.v01.SystemErrorCodeType;


/**
 * <p>Java class for SystemErrorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SystemErrorType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fss.ru/integration/types/common/v01}BaseErrorType">
 *       &lt;sequence>
 *         &lt;element name="errorCode" type="{http://www.fss.ru/integration/types/dic/errors/v01}SystemErrorCodeType"/>
 *         &lt;element name="originatingError" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="trace" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SystemErrorType", propOrder = {
    "errorCode",
    "originatingError",
    "trace"
})
public class SystemErrorType
    extends BaseErrorType
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected SystemErrorCodeType errorCode;
    @XmlElement(required = true)
    protected String originatingError;
    protected List<String> trace;

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link SystemErrorCodeType }
     *     
     */
    public SystemErrorCodeType getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link SystemErrorCodeType }
     *     
     */
    public void setErrorCode(SystemErrorCodeType value) {
        this.errorCode = value;
    }

    /**
     * Gets the value of the originatingError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginatingError() {
        return originatingError;
    }

    /**
     * Sets the value of the originatingError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginatingError(String value) {
        this.originatingError = value;
    }

    /**
     * Gets the value of the trace property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trace property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrace().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTrace() {
        if (trace == null) {
            trace = new ArrayList<String>();
        }
        return this.trace;
    }

}
