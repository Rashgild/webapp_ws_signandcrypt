package ru.rashgild.generated.v2.types.eln.mo.v01;

import ru.rashgild.generated.v2.types.eln.v01.LnCodeList;
import ru.rashgild.generated.v2.types.eln.v01.WSResult;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * ����� �� ������ ������� ������� ���
 * 
 * <p>Java class for FileOperationsLnUserGetNewLNNumRangeOut complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FileOperationsLnUserGetNewLNNumRangeOut">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fss.ru/integration/types/eln/v01}WSResult">
 *       &lt;sequence>
 *         &lt;element name="data" type="{http://www.fss.ru/integration/types/eln/v01}LnCodeList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileOperationsLnUserGetNewLNNumRangeOut", propOrder = {
    "data"
})
public class FileOperationsLnUserGetNewLNNumRangeOut
    extends WSResult
{

    protected LnCodeList data;

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link LnCodeList }
     *     
     */
    public LnCodeList getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link LnCodeList }
     *     
     */
    public void setData(LnCodeList value) {
        this.data = value;
    }

}
