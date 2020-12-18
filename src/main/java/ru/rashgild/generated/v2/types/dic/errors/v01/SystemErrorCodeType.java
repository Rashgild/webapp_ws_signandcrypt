
package ru.rashgild.generated.v2.types.dic.errors.v01;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SystemErrorCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SystemErrorCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="E0010"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SystemErrorCodeType", namespace = "http://www.fss.ru/integration/types/dic/errors/v01")
@XmlEnum
public enum SystemErrorCodeType {


    /**
     * ���������� ������ �������
     * 
     */
    @XmlEnumValue("E0010")
    E_0010("E0010");
    private final String value;

    SystemErrorCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SystemErrorCodeType fromValue(String v) {
        for (SystemErrorCodeType c: SystemErrorCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
