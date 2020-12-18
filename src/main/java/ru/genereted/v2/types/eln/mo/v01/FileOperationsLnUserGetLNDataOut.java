
package main.java.ru.genereted.v2.types.eln.mo.v01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import main.java.ru.genereted.v2.types.eln.v01.WSResult;


/**
 * ����� �� ������ ���
 * 
 * <p>Java class for FileOperationsLnUserGetLNDataOut complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FileOperationsLnUserGetLNDataOut">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fss.ru/integration/types/eln/v01}WSResult">
 *       &lt;sequence>
 *         &lt;element name="data" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.fss.ru/integration/types/eln/mo/v01}outRowset" minOccurs="0"/>
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
@XmlType(name = "FileOperationsLnUserGetLNDataOut", propOrder = {
    "data"
})
public class FileOperationsLnUserGetLNDataOut
    extends WSResult
{

    protected FileOperationsLnUserGetLNDataOut.Data data;

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link FileOperationsLnUserGetLNDataOut.Data }
     *     
     */
    public FileOperationsLnUserGetLNDataOut.Data getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileOperationsLnUserGetLNDataOut.Data }
     *     
     */
    public void setData(FileOperationsLnUserGetLNDataOut.Data value) {
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
     *         &lt;element ref="{http://www.fss.ru/integration/types/eln/mo/v01}outRowset" minOccurs="0"/>
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
        "outRowset"
    })
    public static class Data {

        protected OutRowset outRowset;

        /**
         * Gets the value of the outRowset property.
         * 
         * @return
         *     possible object is
         *     {@link OutRowset }
         *     
         */
        public OutRowset getOutRowset() {
            return outRowset;
        }

        /**
         * Sets the value of the outRowset property.
         * 
         * @param value
         *     allowed object is
         *     {@link OutRowset }
         *     
         */
        public void setOutRowset(OutRowset value) {
            this.outRowset = value;
        }

    }

}
