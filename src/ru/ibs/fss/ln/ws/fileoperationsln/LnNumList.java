
package ru.ibs.fss.ln.ws.fileoperationsln;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for lnNumList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="lnNumList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LNNum" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lnNumList", propOrder = {
    "lnNum"
})
public class LnNumList {

    @XmlElement(name = "LNNum", nillable = true)
    protected List<String> lnNum;

    /**
     * Gets the value of the lnNum property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lnNum property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLNNum().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLNNum() {
        if (lnNum == null) {
            lnNum = new ArrayList<String>();
        }
        return this.lnNum;
    }

}
