
package main.java.ru.genereted.v2.types.eln.mo.v01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OutRowset complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OutRowset">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="responseRow" type="{http://www.fss.ru/integration/types/eln/mo/v01}ResponseRow"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OutRowset", propOrder = {
    "responseRow"
})
public class OutRowset {

    @XmlElement(required = true)
    protected ResponseRow responseRow;

    /**
     * Gets the value of the responseRow property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseRow }
     *     
     */
    public ResponseRow getResponseRow() {
        return responseRow;
    }

    /**
     * Sets the value of the responseRow property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseRow }
     *     
     */
    public void setResponseRow(ResponseRow value) {
        this.responseRow = value;
    }

}
