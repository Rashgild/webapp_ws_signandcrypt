
package main.java.ru.genereted.v2.types.eln.v01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import main.java.ru.genereted.v2.types.eln.mo.v01.FileOperationsLnUserGetExistingLNNumRangeOut;
import main.java.ru.genereted.v2.types.eln.mo.v01.FileOperationsLnUserGetLNDataOut;
import main.java.ru.genereted.v2.types.eln.mo.v01.FileOperationsLnUserGetLNListByDateOut;
import main.java.ru.genereted.v2.types.eln.mo.v01.FileOperationsLnUserGetLNListBySnilsOut;
import main.java.ru.genereted.v2.types.eln.mo.v01.FileOperationsLnUserGetNewLNNumOut;
import main.java.ru.genereted.v2.types.eln.mo.v01.FileOperationsLnUserGetNewLNNumRangeOut;


/**
 * ��������� ��������� �������
 * 
 * <p>Java class for WSResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="mess" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="info" type="{http://www.fss.ru/integration/types/eln/v01}Info" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSResult", propOrder = {
    "requestId",
    "status",
    "mess",
    "info"
})
@XmlSeeAlso({
    FileOperationsLnUserGetLNListByDateOut.class,
    FileOperationsLnUserGetLNListBySnilsOut.class,
    FileOperationsLnUserGetLNDataOut.class,
    FileOperationsLnUserGetExistingLNNumRangeOut.class,
    FileOperationsLnUserGetNewLNNumRangeOut.class,
    FileOperationsLnUserGetNewLNNumOut.class
})
public class WSResult {

    @XmlElement(required = true)
    protected String requestId;
    protected Integer status;
    protected String mess;
    protected Info info;

    /**
     * Gets the value of the requestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the value of the requestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

    /**
     * Gets the value of the mess property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMess() {
        return mess;
    }

    /**
     * Sets the value of the mess property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMess(String value) {
        this.mess = value;
    }

    /**
     * Gets the value of the info property.
     * 
     * @return
     *     possible object is
     *     {@link Info }
     *     
     */
    public Info getInfo() {
        return info;
    }

    /**
     * Sets the value of the info property.
     * 
     * @param value
     *     allowed object is
     *     {@link Info }
     *     
     */
    public void setInfo(Info value) {
        this.info = value;
    }

}
