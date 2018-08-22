package ru.my.entities;

import javax.ws.rs.HEAD;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by rkurbanov on 25.05.2018.
 */


@XmlRootElement(name = "Envelope")
@XmlAccessorType(XmlAccessType.NONE)
/*@XmlType(name = "Envelope", propOrder = {
        "header","body"
})*/
public class Envelope {

    @XmlAttribute(name = "xmlns",namespace ="http://schemas.xmlsoap.org/soap/envelope/")
    private String soapenv;
  /*  @XmlAttribute(name = "ds", namespace = "http://www.w3.org/2000/09/xmldsig#")
    private String ds;
    @XmlAttribute(name = "fil", namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl")
    private String fil;
    @XmlAttribute(name = "wsse", namespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd")
    private String wsse;
    @XmlAttribute(name = "wsu", namespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd")
    private String wsu;
    @XmlAttribute(name = "xsd", namespace = "http://www.w3.org/2001/XMLSchema")
    private String xsd;
    @XmlAttribute(name = "xsi", namespace = "http://www.w3.org/2001/XMLSchema-instance")
    private String xsi;*/


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
    public static class Body{
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
    public static class Header{
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
