
package main.java.ru.genereted.v2.types.eln.v01;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * �������� ������� ���
 * 
 * <p>Java class for LnCodeList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LnCodeList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lnCode" type="{http://www.fss.ru/integration/types/eln/v01}lnCode" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LnCodeList", propOrder = {
    "lnCode"
})
public class LnCodeList {

    @XmlElement(required = true)
    protected List<String> lnCode;

    /**
     * Gets the value of the lnCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lnCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLnCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLnCode() {
        if (lnCode == null) {
            lnCode = new ArrayList<String>();
        }
        return this.lnCode;
    }

}
