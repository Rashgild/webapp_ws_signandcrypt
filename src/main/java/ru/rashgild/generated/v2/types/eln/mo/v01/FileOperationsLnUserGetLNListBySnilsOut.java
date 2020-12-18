package ru.rashgild.generated.v2.types.eln.mo.v01;

import ru.rashgild.generated.v2.types.eln.v01.WSResult;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ����� �� ������ ��� �� �����
 *
 * <p>Java class for FileOperationsLnUserGetLNListBySnilsOut complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="FileOperationsLnUserGetLNListBySnilsOut">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fss.ru/integration/types/eln/v01}WSResult">
 *       &lt;sequence>
 *         &lt;element name="Data" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.fss.ru/integration/types/eln/mo/v01}outRowsetLNListbySnils" minOccurs="0"/>
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
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileOperationsLnUserGetLNListBySnilsOut", propOrder = {
        "data"
})
public class FileOperationsLnUserGetLNListBySnilsOut
        extends WSResult {

    @XmlElement(name = "Data")
    protected FileOperationsLnUserGetLNListBySnilsOut.Data data;

    /**
     * Gets the value of the data property.
     *
     * @return possible object is
     * {@link FileOperationsLnUserGetLNListBySnilsOut.Data }
     */
    public FileOperationsLnUserGetLNListBySnilsOut.Data getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     *
     * @param value allowed object is
     *              {@link FileOperationsLnUserGetLNListBySnilsOut.Data }
     */
    public void setData(FileOperationsLnUserGetLNListBySnilsOut.Data value) {
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
     *         &lt;element ref="{http://www.fss.ru/integration/types/eln/mo/v01}outRowsetLNListbySnils" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "outRowsetLNListbySnils"
    })
    public static class Data {

        protected OutRowsetLNListbySnils outRowsetLNListbySnils;

        /**
         * Gets the value of the outRowsetLNListbySnils property.
         *
         * @return possible object is
         * {@link OutRowsetLNListbySnils }
         */
        public OutRowsetLNListbySnils getOutRowsetLNListbySnils() {
            return outRowsetLNListbySnils;
        }

        /**
         * Sets the value of the outRowsetLNListbySnils property.
         *
         * @param value allowed object is
         *              {@link OutRowsetLNListbySnils }
         */
        public void setOutRowsetLNListbySnils(OutRowsetLNListbySnils value) {
            this.outRowsetLNListbySnils = value;
        }

    }
}
