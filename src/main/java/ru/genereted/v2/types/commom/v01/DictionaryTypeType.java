
package main.java.ru.genereted.v2.types.commom.v01;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DictionaryTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DictionaryTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="INSURERS"/>
 *     &lt;enumeration value="SEDO_MESSAGES"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DictionaryTypeType")
@XmlEnum
public enum DictionaryTypeType {

    INSURERS,
    SEDO_MESSAGES;

    public String value() {
        return name();
    }

    public static DictionaryTypeType fromValue(String v) {
        return valueOf(v);
    }

}
