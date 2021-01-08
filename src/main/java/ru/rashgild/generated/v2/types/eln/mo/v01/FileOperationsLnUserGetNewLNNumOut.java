package ru.rashgild.generated.v2.types.eln.mo.v01;

import ru.rashgild.generated.v2.types.eln.v01.WSResult;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ����� �� ������ ������ ���
 * 
 * <p>Java class for FileOperationsLnUserGetNewLNNumOut complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FileOperationsLnUserGetNewLNNumOut">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fss.ru/integration/types/eln/v01}WSResult">
 *       &lt;sequence>
 *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileOperationsLnUserGetNewLNNumOut", propOrder = {
    "data"
})
public class FileOperationsLnUserGetNewLNNumOut
    extends WSResult
{

    @XmlElement(required = true, nillable = true)
    protected String data;

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setData(String value) {
        this.data = value;
    }

}
