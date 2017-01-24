
package ru.ibs.fss.ln.ws.fileoperationsln;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getNewLNNumResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getNewLNNumResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fileOperationsLnUserGetNewLNNumOut" type="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}FileOperationsLnUser_getNewLNNum_Out" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getNewLNNumResponse", propOrder = {
    "fileOperationsLnUserGetNewLNNumOut"
})
public class GetNewLNNumResponse {

    protected FileOperationsLnUserGetNewLNNumOut fileOperationsLnUserGetNewLNNumOut;

    /**
     * Gets the value of the fileOperationsLnUserGetNewLNNumOut property.
     * 
     * @return
     *     possible object is
     *     {@link FileOperationsLnUserGetNewLNNumOut }
     *     
     */
    public FileOperationsLnUserGetNewLNNumOut getFileOperationsLnUserGetNewLNNumOut() {
        return fileOperationsLnUserGetNewLNNumOut;
    }

    /**
     * Sets the value of the fileOperationsLnUserGetNewLNNumOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileOperationsLnUserGetNewLNNumOut }
     *     
     */
    public void setFileOperationsLnUserGetNewLNNumOut(FileOperationsLnUserGetNewLNNumOut value) {
        this.fileOperationsLnUserGetNewLNNumOut = value;
    }

}
