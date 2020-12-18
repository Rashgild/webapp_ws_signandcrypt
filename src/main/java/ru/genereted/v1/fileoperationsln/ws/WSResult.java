
package main.java.ru.genereted.v1.fileoperationsln.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSResult complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="WSResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="REQUEST_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MESS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="INFO" type="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}INFO" minOccurs="0"/>
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
        "requestid",
        "status",
        "mess",
        "info"
})
@XmlSeeAlso({
        FileOperationsLnUserGetNewLNNumOut.class,
        FileOperationsLnUserGetNewLNNumRangeOut.class,
        FileOperationsLnUserDisableLnOut.class,
        FileOperationsLnUserGetLNDataOut.class,
        FileOperationsLnUserGetExistingLNNumRangeOut.class
})
public class WSResult {

    @XmlElement(name = "REQUEST_ID", required = true)
    protected String requestid;
    @XmlElement(name = "STATUS")
    protected int status;
    @XmlElement(name = "MESS")
    protected String mess;
    @XmlElement(name = "INFO")
    protected INFO info;

    /**
     * Gets the value of the requestid property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getREQUESTID() {
        return requestid;
    }

    /**
     * Sets the value of the requestid property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setREQUESTID(String value) {
        this.requestid = value;
    }

    /**
     * Gets the value of the status property.
     *
     */
    public int getSTATUS() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     */
    public void setSTATUS(int value) {
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
    public String getMESS() {
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
    public void setMESS(String value) {
        this.mess = value;
    }

    /**
     * Gets the value of the info property.
     *
     * @return
     *     possible object is
     *     {@link INFO }
     *
     */
    public INFO getINFO() {
        return info;
    }

    /**
     * Sets the value of the info property.
     *
     * @param value
     *     allowed object is
     *     {@link INFO }
     *
     */
    public void setINFO(INFO value) {
        this.info = value;
    }

}
