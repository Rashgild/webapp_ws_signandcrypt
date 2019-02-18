package ru.my.entities;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Envelope")
@XmlAccessorType(XmlAccessType.NONE)
public class Envelope {

    @XmlAttribute(name = "xmlns", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
    private String soapenv;


    @XmlElement(name = "Header")
    private Header header;
    @XmlElement(name = "Body")
    private Body body;


    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @XmlRootElement(name = "Body")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Body {
        @XmlElement(name = "PrParseFileLnLpu")
        private List<PrParseFileLnLpu> prParseFileLnLpus;

        public List<PrParseFileLnLpu> getPrParseFileLnLpus() {
            return prParseFileLnLpus;
        }

        public void setPrParseFileLnLpus(List<PrParseFileLnLpu> prParseFileLnLpus) {
            this.prParseFileLnLpus = prParseFileLnLpus;
        }
    }

    @XmlRootElement(name = "Header")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Header {
        @XmlElement(name = "Security")
        private List<Security> securities;

        public List<Security> getSecurities() {
            return securities;
        }

        public void setSecurities(List<Security> securities) {
            this.securities = securities;
        }
    }
}
