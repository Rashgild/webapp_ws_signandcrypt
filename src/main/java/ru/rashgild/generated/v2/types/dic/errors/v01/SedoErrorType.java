
package ru.rashgild.generated.v2.types.dic.errors.v01;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SedoErrorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SedoErrorType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="E_SEDO_0000"/>
 *     &lt;enumeration value="E_SEDO_1000"/>
 *     &lt;enumeration value="E_SEDO_1010"/>
 *     &lt;enumeration value="E_SEDO_1011"/>
 *     &lt;enumeration value="E_SEDO_1020"/>
 *     &lt;enumeration value="E_SEDO_1030"/>
 *     &lt;enumeration value="E_SEDO_1040"/>
 *     &lt;enumeration value="E_SEDO_1070"/>
 *     &lt;enumeration value="E_SEDO_1080"/>
 *     &lt;enumeration value="E_SEDO_1090"/>
 *     &lt;enumeration value="E_SEDO_2000"/>
 *     &lt;enumeration value="E_SEDO_3000"/>
 *     &lt;enumeration value="E_SEDO_3010"/>
 *     &lt;enumeration value="E_SEDO_3020"/>
 *     &lt;enumeration value="E_SEDO_3110"/>
 *     &lt;enumeration value="E_SEDO_3120"/>
 *     &lt;enumeration value="E_SEDO_3130"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SedoErrorType", namespace = "http://www.fss.ru/integration/types/dic/errors/v01")
@XmlEnum
public enum SedoErrorType {


    /**
     * ���������� ������
     * 
     */
    E_SEDO_0000,

    /**
     * ���� ����. ���������� ������
     * 
     */
    E_SEDO_1000,

    /**
     * ������ ��� ������� ������������ ���������
     * 
     */
    E_SEDO_1010,

    /**
     * �� ������ ���������� ������������ ��� ���������� ��������� ���������
     * 
     */
    E_SEDO_1011,

    /**
     * ������ ��� �������� �������
     * 
     */
    E_SEDO_1020,

    /**
     * ���������� ���� message �� �������� ������� � ������� base64
     * 
     */
    E_SEDO_1030,

    /**
     * ������ ��� ���������� ��������� ��������� � ������������ �������
     * 
     */
    E_SEDO_1040,

    /**
     * ������ ��� ���������� ���������� ��������� � ������������ �������
     * 
     */
    E_SEDO_1070,

    /**
     * ������ ������� ���������� ���������
     * 
     */
    E_SEDO_1080,

    /**
     * ������ ��� ������� ����������� ��������� ���������
     * 
     */
    E_SEDO_1090,

    /**
     * ���� ����. ���������� ������
     * 
     */
    E_SEDO_2000,

    /**
     * �� ����. ���������� ������
     * 
     */
    E_SEDO_3000,

    /**
     * ��������� ��������� �� �������
     * 
     */
    E_SEDO_3010,

    /**
     * �������� ������ ���������. ��������� �� ������������ XSD �����
     * 
     */
    E_SEDO_3020,

    /**
     * ��������� ������� ��� �������� �� ������� ���������
     * 
     */
    E_SEDO_3110,

    /**
     * ��������� ������� ��� ��������� �� ���������
     * 
     */
    E_SEDO_3120,

    /**
     * ��������� ������� �� ��� �������� �� ���������
     * 
     */
    E_SEDO_3130;

    public String value() {
        return name();
    }

    public static SedoErrorType fromValue(String v) {
        return valueOf(v);
    }

}
