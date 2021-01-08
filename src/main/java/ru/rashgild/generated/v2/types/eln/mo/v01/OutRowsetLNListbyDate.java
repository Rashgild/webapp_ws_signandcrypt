package ru.rashgild.generated.v2.types.eln.mo.v01;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OutRowsetLNListbyDate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OutRowsetLNListbyDate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rowLNbyDate" type="{http://www.fss.ru/integration/types/eln/mo/v01}RowLNbyDate" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OutRowsetLNListbyDate", propOrder = {
    "rowLNbyDate"
})
public class OutRowsetLNListbyDate {

    protected List<RowLNbyDate> rowLNbyDate;

    /**
     * Gets the value of the rowLNbyDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rowLNbyDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRowLNbyDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RowLNbyDate }
     * 
     * 
     */
    public List<RowLNbyDate> getRowLNbyDate() {
        if (rowLNbyDate == null) {
            rowLNbyDate = new ArrayList<RowLNbyDate>();
        }
        return this.rowLNbyDate;
    }

}
