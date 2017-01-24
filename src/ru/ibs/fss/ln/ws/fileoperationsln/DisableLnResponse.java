
package ru.ibs.fss.ln.ws.fileoperationsln;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for disableLnResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="disableLnResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FileOperationsLnUserDisableLnOut" type="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}FileOperationsLnUser_disableLn_Out" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "disableLnResponse", propOrder = {
    "fileOperationsLnUserDisableLnOut"
})
public class DisableLnResponse {

    @XmlElement(name = "FileOperationsLnUserDisableLnOut")
    protected FileOperationsLnUserDisableLnOut fileOperationsLnUserDisableLnOut;

    /**
     * Gets the value of the fileOperationsLnUserDisableLnOut property.
     * 
     * @return
     *     possible object is
     *     {@link FileOperationsLnUserDisableLnOut }
     *     
     */
    public FileOperationsLnUserDisableLnOut getFileOperationsLnUserDisableLnOut() {
        return fileOperationsLnUserDisableLnOut;
    }

    /**
     * Sets the value of the fileOperationsLnUserDisableLnOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileOperationsLnUserDisableLnOut }
     *     
     */
    public void setFileOperationsLnUserDisableLnOut(FileOperationsLnUserDisableLnOut value) {
        this.fileOperationsLnUserDisableLnOut = value;
    }

}
