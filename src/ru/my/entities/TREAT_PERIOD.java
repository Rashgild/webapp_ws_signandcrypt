package ru.my.entities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "TREAT_FULL_PERIOD")
@XmlType(propOrder = {"treatdt1", "treatdt2", "treatdoctorrole", "treatdoctor"})
public class TREAT_PERIOD {
    protected String AttribId;
    protected String treatdt1;
    protected String treatdt2;
    protected String treatdoctorrole;
    protected String treatdoctor;

    public String getAttribId() {
        return AttribId;
    }

    @XmlAttribute(name = "Id", namespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd")
    public void setAttribId(String attribId) {
        this.AttribId = attribId;
    }

    public String getTreatdt1() {
        return treatdt1;
    }

    @XmlElement(name = "TREAT_DT1", required = true)
    public void setTreatdt1(String treatdt1) {
        this.treatdt1 = treatdt1;
    }

    public String getTreatdt2() {
        return treatdt2;
    }

    @XmlElement(name = "TREAT_DT2", required = true)
    public void setTreatdt2(String treatdt2) {
        this.treatdt2 = treatdt2;
    }

    public String getTreatdoctorrole() {
        return treatdoctorrole;
    }

    @XmlElement(name = "TREAT_DOCTOR_ROLE", required = true)
    public void setTreatdoctorrole(String treatdoctorrole) {
        this.treatdoctorrole = treatdoctorrole;
    }

    public String getTreatdoctor() {
        return treatdoctor;
    }

    @XmlElement(name = "TREAT_DOCTOR", required = true)
    public void setTreatdoctor(String treatdoctor) {
        this.treatdoctor = treatdoctor;
    }
}
