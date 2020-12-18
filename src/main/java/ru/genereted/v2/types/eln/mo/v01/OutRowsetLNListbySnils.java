
package main.java.ru.genereted.v2.types.eln.mo.v01;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OutRowsetLNListbySnils complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OutRowsetLNListbySnils">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rowLNbySnils" type="{http://www.fss.ru/integration/types/eln/mo/v01}RowLNbySnils" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OutRowsetLNListbySnils", propOrder = {
    "rowLNbySnils"
})
public class OutRowsetLNListbySnils {

    protected List<RowLNbySnils> rowLNbySnils;

    /**
     * Gets the value of the rowLNbySnils property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rowLNbySnils property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRowLNbySnils().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RowLNbySnils }
     * 
     * 
     */
    public List<RowLNbySnils> getRowLNbySnils() {
        if (rowLNbySnils == null) {
            rowLNbySnils = new ArrayList<RowLNbySnils>();
        }
        return this.rowLNbySnils;
    }

}
