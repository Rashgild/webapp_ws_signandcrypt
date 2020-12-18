
package main.java.ru.genereted.v2.types.eln.mo.v01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import main.java.ru.genereted.v2.types.eln.v01.WSResult;


/**
 * ����� �� ������ ��� �� ����
 * 
 * <p>Java class for FileOperationsLnUserGetLNListByDateOut complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FileOperationsLnUserGetLNListByDateOut">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fss.ru/integration/types/eln/v01}WSResult">
 *       &lt;sequence>
 *         &lt;element name="data" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.fss.ru/integration/types/eln/mo/v01}outRowsetLNListbyDate" minOccurs="0"/>
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
@XmlType(name = "FileOperationsLnUserGetLNListByDateOut", propOrder = {
    "data"
})
public class FileOperationsLnUserGetLNListByDateOut
    extends WSResult
{

    protected FileOperationsLnUserGetLNListByDateOut.Data data;

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link FileOperationsLnUserGetLNListByDateOut.Data }
     *     
     */
    public FileOperationsLnUserGetLNListByDateOut.Data getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileOperationsLnUserGetLNListByDateOut.Data }
     *     
     */
    public void setData(FileOperationsLnUserGetLNListByDateOut.Data value) {
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
     *         &lt;element ref="{http://www.fss.ru/integration/types/eln/mo/v01}outRowsetLNListbyDate" minOccurs="0"/>
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
        "outRowsetLNListbyDate"
    })
    public static class Data {

        protected OutRowsetLNListbyDate outRowsetLNListbyDate;

        /**
         * Gets the value of the outRowsetLNListbyDate property.
         * 
         * @return
         *     possible object is
         *     {@link OutRowsetLNListbyDate }
         *     
         */
        public OutRowsetLNListbyDate getOutRowsetLNListbyDate() {
            return outRowsetLNListbyDate;
        }

        /**
         * Sets the value of the outRowsetLNListbyDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link OutRowsetLNListbyDate }
         *     
         */
        public void setOutRowsetLNListbyDate(OutRowsetLNListbyDate value) {
            this.outRowsetLNListbyDate = value;
        }

    }

}
