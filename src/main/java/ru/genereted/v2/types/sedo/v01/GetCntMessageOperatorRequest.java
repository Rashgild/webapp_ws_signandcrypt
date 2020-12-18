
package main.java.ru.genereted.v2.types.sedo.v01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * ������ �� ��������� ���������� ��������� ��� ���������
 * 
 * <p>Java class for GetCntMessageOperatorRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetCntMessageOperatorRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fss.ru/integration/types/sedo/v01}CommonMessageRequest">
 *       &lt;sequence>
 *         &lt;element name="operatorOgrn" type="{http://www.fss.ru/integration/types/organization/v01}ogrn"/>
 *         &lt;element name="messageDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetCntMessageOperatorRequest", propOrder = {
    "operatorOgrn",
    "messageDate"
})
public class GetCntMessageOperatorRequest
    extends CommonMessageRequest
{

    @XmlElement(required = true)
    protected String operatorOgrn;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar messageDate;

    /**
     * Gets the value of the operatorOgrn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorOgrn() {
        return operatorOgrn;
    }

    /**
     * Sets the value of the operatorOgrn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorOgrn(String value) {
        this.operatorOgrn = value;
    }

    /**
     * Gets the value of the messageDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMessageDate() {
        return messageDate;
    }

    /**
     * Sets the value of the messageDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMessageDate(XMLGregorianCalendar value) {
        this.messageDate = value;
    }

}
