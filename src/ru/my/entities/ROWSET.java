package ru.my.entities;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by rkurbanov on 05.12.2016.
 */
@XmlRootElement(name = "ROWSET")
@XmlAccessorType(XmlAccessType.FIELD)
public class ROWSET {

    @XmlAttribute(name = "version", namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl")
    private String version;
    @XmlAttribute(name = "software", namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl")
    private String software;
    @XmlAttribute(name = "version_software", namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl")
    private String versionSoftware;
    @XmlAttribute(name = "phone", namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl")
    private String phone;
    @XmlAttribute(name = "email", namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl")
    private String email;
    @XmlAttribute(name = "author", namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl")
    private String author;
    @XmlElement(name = "ROW", required = true)
    private List<ROW> row;

    public List<ROW> getRow() {
        return row;
    }
    public void setRow(List<ROW> row) {
        this.row = row;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getSoftware() {
        return software;
    }
    public void setSoftware(String software) {
        this.software = software;
    }
    public String getVersionSoftware() {
        return versionSoftware;
    }
    public void setVersionSoftware(String versionSoftware) {
        this.versionSoftware = versionSoftware;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        String res = "ROWSET{" +
                "ROW{";
        res += "}}";
        return res;
    }
}
