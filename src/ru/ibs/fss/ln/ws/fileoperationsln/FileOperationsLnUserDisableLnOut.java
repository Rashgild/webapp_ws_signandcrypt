
package ru.ibs.fss.ln.ws.fileoperationsln;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FileOperationsLnUser_disableLn_Out complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FileOperationsLnUser_disableLn_Out">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}WSResult">
 *       &lt;sequence>
 *         &lt;element name="DATA" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="OUT_ROWSET" type="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}OUT_ROWSET" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileOperationsLnUser_disableLn_Out", propOrder = {
    "data"
})
public class FileOperationsLnUserDisableLnOut
    extends WSResult
{

    @XmlElement(name = "DATA")
    protected FileOperationsLnUserDisableLnOut.DATA data;

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link FileOperationsLnUserDisableLnOut.DATA }
     *     
     */
    public FileOperationsLnUserDisableLnOut.DATA getDATA() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileOperationsLnUserDisableLnOut.DATA }
     *     
     */
    public void setDATA(FileOperationsLnUserDisableLnOut.DATA value) {
        this.data = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="OUT_ROWSET" type="{http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl}OUT_ROWSET" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "outrowset"
    })
    public static class DATA {

        @XmlElement(name = "OUT_ROWSET")
        protected OUTROWSET outrowset;

        /**
         * Gets the value of the outrowset property.
         * 
         * @return
         *     possible object is
         *     {@link OUTROWSET }
         *     
         */
        public OUTROWSET getOUTROWSET() {
            return outrowset;
        }

        /**
         * Sets the value of the outrowset property.
         * 
         * @param value
         *     allowed object is
         *     {@link OUTROWSET }
         *     
         */
        public void setOUTROWSET(OUTROWSET value) {
            this.outrowset = value;
        }

    }

}
