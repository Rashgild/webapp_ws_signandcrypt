package main.java.ru.rashgild.entities;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "ROW")
@XmlType(propOrder = {"treatchairmanrole", "treatchairman", "treat_period", "export"})
public class TREAT_FULL_PERIOD {

    protected String AttribIdVk;
    protected String treatchairmanrole;
    protected String treatchairman;
    protected String export;

    public String getExport() {
        return export;
    }

    @XmlElement(name = "Export", required = true)
    public void setExport(String export) {
        this.export = export;
    }

    protected List<TREAT_PERIOD> treat_period;

    public List<TREAT_PERIOD> getTreat_period() {
        return treat_period;
    }

    @XmlElement(name = "TREAT_PERIOD", required = true)
    public void setTreat_period(List<TREAT_PERIOD> treat_period) {
        this.treat_period = treat_period;
    }

    public String getAttribIdVk() {
        return AttribIdVk;
    }

    @XmlAttribute(name = "Id", namespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    public void setAttribIdVk(String attribIdVk) {
        this.AttribIdVk = attribIdVk;
    }

    public String getTreatchairmanrole() {
        return treatchairmanrole;
    }

    @XmlElement(name = "TREAT_CHAIRMAN_ROLE", required = true)
    public void setTreatchairmanrole(String treatchairmanrole) {
        this.treatchairmanrole = treatchairmanrole;
    }

    public String getTreatchairman() {
        return treatchairman;
    }

    @XmlElement(name = "TREAT_CHAIRMAN", required = true)
    public void setTreatchairman(String treatchairman) {
        this.treatchairman = treatchairman;
    }
}
