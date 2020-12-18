
package main.java.ru.genereted.v1.fileoperationsln.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getNewLNNumRangeResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="getNewLNNumRangeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fileOperationsLnUserGetNewLNNumRangeOut" type="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}FileOperationsLnUser_getNewLNNumRange_Out" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getNewLNNumRangeResponse", propOrder = {
        "fileOperationsLnUserGetNewLNNumRangeOut"
})
public class GetNewLNNumRangeResponse {

    protected FileOperationsLnUserGetNewLNNumRangeOut fileOperationsLnUserGetNewLNNumRangeOut;

    /**
     * Gets the value of the fileOperationsLnUserGetNewLNNumRangeOut property.
     *
     * @return
     *     possible object is
     *     {@link FileOperationsLnUserGetNewLNNumRangeOut }
     *
     */
    public FileOperationsLnUserGetNewLNNumRangeOut getFileOperationsLnUserGetNewLNNumRangeOut() {
        return fileOperationsLnUserGetNewLNNumRangeOut;
    }

    /**
     * Sets the value of the fileOperationsLnUserGetNewLNNumRangeOut property.
     *
     * @param value
     *     allowed object is
     *     {@link FileOperationsLnUserGetNewLNNumRangeOut }
     *
     */
    public void setFileOperationsLnUserGetNewLNNumRangeOut(FileOperationsLnUserGetNewLNNumRangeOut value) {
        this.fileOperationsLnUserGetNewLNNumRangeOut = value;
    }

}
