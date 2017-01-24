
package ru.ibs.fss.ln.ws.fileoperationsln;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getLNDataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getLNDataResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FileOperationsLnUserGetLNDataOut" type="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}FileOperationsLnUser_getLNData_Out" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLNDataResponse", propOrder = {
    "fileOperationsLnUserGetLNDataOut"
})
public class GetLNDataResponse {

    @XmlElement(name = "FileOperationsLnUserGetLNDataOut")
    protected FileOperationsLnUserGetLNDataOut fileOperationsLnUserGetLNDataOut;

    /**
     * Gets the value of the fileOperationsLnUserGetLNDataOut property.
     * 
     * @return
     *     possible object is
     *     {@link FileOperationsLnUserGetLNDataOut }
     *     
     */
    public FileOperationsLnUserGetLNDataOut getFileOperationsLnUserGetLNDataOut() {
        return fileOperationsLnUserGetLNDataOut;
    }

    /**
     * Sets the value of the fileOperationsLnUserGetLNDataOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileOperationsLnUserGetLNDataOut }
     *     
     */
    public void setFileOperationsLnUserGetLNDataOut(FileOperationsLnUserGetLNDataOut value) {
        this.fileOperationsLnUserGetLNDataOut = value;
    }

}
