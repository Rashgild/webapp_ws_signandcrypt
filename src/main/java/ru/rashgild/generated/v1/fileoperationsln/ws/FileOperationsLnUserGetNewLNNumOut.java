
package ru.rashgild.generated.v1.fileoperationsln.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FileOperationsLnUser_getNewLNNum_Out complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="FileOperationsLnUser_getNewLNNum_Out">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}WSResult">
 *       &lt;sequence>
 *         &lt;element name="DATA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileOperationsLnUser_getNewLNNum_Out", propOrder = {
        "data"
})
public class FileOperationsLnUserGetNewLNNumOut
        extends WSResult
{

    @XmlElement(name = "DATA", required = true, nillable = true)
    protected String data;

    /**
     * Gets the value of the data property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDATA() {
        return data;
    }

    /**
     * Sets the value of the data property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDATA(String value) {
        this.data = value;
    }

}
