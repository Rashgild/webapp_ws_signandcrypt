
package main.java.ru.genereted.v1.fileoperationsln.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FileOperationsLnUser_getNewLNNumRange_Out complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="FileOperationsLnUser_getNewLNNumRange_Out">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}WSResult">
 *       &lt;sequence>
 *         &lt;element name="DATA" type="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}lnNumList"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileOperationsLnUser_getNewLNNumRange_Out", propOrder = {
        "data"
})
public class FileOperationsLnUserGetNewLNNumRangeOut
        extends WSResult
{

    @XmlElement(name = "DATA", required = true, nillable = true)
    protected LnNumList data;

    /**
     * Gets the value of the data property.
     *
     * @return
     *     possible object is
     *     {@link LnNumList }
     *
     */
    public LnNumList getDATA() {
        return data;
    }

    /**
     * Sets the value of the data property.
     *
     * @param value
     *     allowed object is
     *     {@link LnNumList }
     *
     */
    public void setDATA(LnNumList value) {
        this.data = value;
    }

}
