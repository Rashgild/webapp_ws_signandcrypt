
package main.java.ru.genereted.v2.types.sedo.v01;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import main.java.ru.genereted.v2.types.commom.v01.ErrorType;


/**
 * ������������ ��� ���������� ��������� ��������� ��� ������ ������
 * 
 * <p>Java class for SedoMessageResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SedoMessageResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="result" type="{http://www.fss.ru/integration/types/sedo/v01}SedoMessage"/>
 *         &lt;element name="errorList" type="{http://www.fss.ru/integration/types/common/v01}ErrorType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SedoMessageResponseType", propOrder = {
    "result",
    "errorList"
})
public class SedoMessageResponseType {

    protected SedoMessage result;
    protected List<ErrorType> errorList;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link SedoMessage }
     *     
     */
    public SedoMessage getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link SedoMessage }
     *     
     */
    public void setResult(SedoMessage value) {
        this.result = value;
    }

    /**
     * Gets the value of the errorList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errorList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrorList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ErrorType }
     * 
     * 
     */
    public List<ErrorType> getErrorList() {
        if (errorList == null) {
            errorList = new ArrayList<ErrorType>();
        }
        return this.errorList;
    }

}
