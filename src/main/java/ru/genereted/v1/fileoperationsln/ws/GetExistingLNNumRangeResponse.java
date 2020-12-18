
package main.java.ru.genereted.v1.fileoperationsln.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getExistingLNNumRangeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getExistingLNNumRangeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FileOperationsLnUserGetExistingLNNumRangeOut" type="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}FileOperationsLnUser_getExistingLNNumRange_Out" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getExistingLNNumRangeResponse", propOrder = {
    "fileOperationsLnUserGetExistingLNNumRangeOut"
})
public class GetExistingLNNumRangeResponse {

    @XmlElement(name = "FileOperationsLnUserGetExistingLNNumRangeOut")
    protected FileOperationsLnUserGetExistingLNNumRangeOut fileOperationsLnUserGetExistingLNNumRangeOut;

    /**
     * Gets the value of the fileOperationsLnUserGetExistingLNNumRangeOut property.
     * 
     * @return
     *     possible object is
     *     {@link FileOperationsLnUserGetExistingLNNumRangeOut }
     *     
     */
    public FileOperationsLnUserGetExistingLNNumRangeOut getFileOperationsLnUserGetExistingLNNumRangeOut() {
        return fileOperationsLnUserGetExistingLNNumRangeOut;
    }

    /**
     * Sets the value of the fileOperationsLnUserGetExistingLNNumRangeOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileOperationsLnUserGetExistingLNNumRangeOut }
     *     
     */
    public void setFileOperationsLnUserGetExistingLNNumRangeOut(FileOperationsLnUserGetExistingLNNumRangeOut value) {
        this.fileOperationsLnUserGetExistingLNNumRangeOut = value;
    }

}
