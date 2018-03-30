
package ru.ibs.fss.ln.ws.fileoperationsln;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.ibs.fss.ln.ws.fileoperationsln package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DisableLnResponse_QNAME = new QName("http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", "disableLnResponse");
    private final static QName _PrParseFilelnlpuResponse_QNAME = new QName("http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", "prParseFilelnlpuResponse");
    private final static QName _PrParseFilelnlpu_QNAME = new QName("http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", "prParseFilelnlpu");
    private final static QName _DisableLn_QNAME = new QName("http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", "disableLn");
    private final static QName _OUTROWSET_QNAME = new QName("http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", "OUT_ROWSET");
    private final static QName _GetExistingLNNumRange_QNAME = new QName("http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", "getExistingLNNumRange");
    private final static QName _GetNewLNNumRangeResponse_QNAME = new QName("http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", "getNewLNNumRangeResponse");
    private final static QName _GetNewLNNum_QNAME = new QName("http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", "getNewLNNum");
    private final static QName _GetNewLNNumResponse_QNAME = new QName("http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", "getNewLNNumResponse");
    private final static QName _SOAPException_QNAME = new QName("http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", "SOAPException");
    private final static QName _GetExistingLNNumRangeResponse_QNAME = new QName("http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", "getExistingLNNumRangeResponse");
    private final static QName _GetNewLNNumRange_QNAME = new QName("http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", "getNewLNNumRange");
    private final static QName _GetLNDataResponse_QNAME = new QName("http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", "getLNDataResponse");
    private final static QName _ROWSET_QNAME = new QName("http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", "ROWSET");
    private final static QName _INFO_QNAME = new QName("http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", "INFO");
    private final static QName _GetLNData_QNAME = new QName("http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", "getLNData");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.ibs.fss.ln.ws.fileoperationsln
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FileOperationsLnUserGetLNDataOut }
     *
     */
    public FileOperationsLnUserGetLNDataOut createFileOperationsLnUserGetLNDataOut() {
        return new FileOperationsLnUserGetLNDataOut();
    }

    /**
     * Create an instance of {@link FileOperationsLnUserDisableLnOut }
     *
     */
    public FileOperationsLnUserDisableLnOut createFileOperationsLnUserDisableLnOut() {
        return new FileOperationsLnUserDisableLnOut();
    }

    /**
     * Create an instance of {@link PrParseFilelnlpuElement }
     *
     */
    public PrParseFilelnlpuElement createPrParseFilelnlpuElement() {
        return new PrParseFilelnlpuElement();
    }

    /**
     * Create an instance of {@link ru.ibs.fss.ln.ws.fileoperationsln.ROW }
     *
     */
    public ru.ibs.fss.ln.ws.fileoperationsln.ROW createROW() {
        return new ru.ibs.fss.ln.ws.fileoperationsln.ROW();
    }

    /**
     * Create an instance of {@link INFO }
     *
     */
    public INFO createINFO() {
        return new INFO();
    }

    /**
     * Create an instance of {@link INFO.ROWSET }
     *
     */
    public INFO.ROWSET createINFOROWSET() {
        return new INFO.ROWSET();
    }

    /**
     * Create an instance of {@link INFO.ROWSET.ROW }
     *
     */
    public INFO.ROWSET.ROW createINFOROWSETROW() {
        return new INFO.ROWSET.ROW();
    }

    /**
     * Create an instance of {@link INFO.ROWSET.ROW.ERRORS }
     *
     */
    public INFO.ROWSET.ROW.ERRORS createINFOROWSETROWERRORS() {
        return new INFO.ROWSET.ROW.ERRORS();
    }

    /**
     * Create an instance of {@link ru.ibs.fss.ln.ws.fileoperationsln.ROWSET }
     *
     */
    public ru.ibs.fss.ln.ws.fileoperationsln.ROWSET createROWSET() {
        return new ru.ibs.fss.ln.ws.fileoperationsln.ROWSET();
    }

    /**
     * Create an instance of {@link ru.ibs.fss.ln.ws.fileoperationsln.ROWSET.ROW }
     *
     */
    public ru.ibs.fss.ln.ws.fileoperationsln.ROWSET.ROW createROWSETROW() {
        return new ru.ibs.fss.ln.ws.fileoperationsln.ROWSET.ROW();
    }

    /**
     * Create an instance of {@link DisableLn }
     *
     */
    public DisableLn createDisableLn() {
        return new DisableLn();
    }

    /**
     * Create an instance of {@link PrParseFilelnlpuResponse }
     *
     */
    public PrParseFilelnlpuResponse createPrParseFilelnlpuResponse() {
        return new PrParseFilelnlpuResponse();
    }

    /**
     * Create an instance of {@link PrParseFilelnlpu }
     *
     */
    public PrParseFilelnlpu createPrParseFilelnlpu() {
        return new PrParseFilelnlpu();
    }

    /**
     * Create an instance of {@link DisableLnResponse }
     *
     */
    public DisableLnResponse createDisableLnResponse() {
        return new DisableLnResponse();
    }

    /**
     * Create an instance of {@link GetNewLNNumResponse }
     *
     */
    public GetNewLNNumResponse createGetNewLNNumResponse() {
        return new GetNewLNNumResponse();
    }

    /**
     * Create an instance of {@link GetNewLNNumRangeResponse }
     *
     */
    public GetNewLNNumRangeResponse createGetNewLNNumRangeResponse() {
        return new GetNewLNNumRangeResponse();
    }

    /**
     * Create an instance of {@link GetNewLNNum }
     *
     */
    public GetNewLNNum createGetNewLNNum() {
        return new GetNewLNNum();
    }

    /**
     * Create an instance of {@link GetExistingLNNumRange }
     *
     */
    public GetExistingLNNumRange createGetExistingLNNumRange() {
        return new GetExistingLNNumRange();
    }

    /**
     * Create an instance of {@link OUTROWSET }
     *
     */
    public OUTROWSET createOUTROWSET() {
        return new OUTROWSET();
    }

    /**
     * Create an instance of {@link GetNewLNNumRange }
     *
     */
    public GetNewLNNumRange createGetNewLNNumRange() {
        return new GetNewLNNumRange();
    }

    /**
     * Create an instance of {@link SOAPException }
     *
     */
    public SOAPException createSOAPException() {
        return new SOAPException();
    }

    /**
     * Create an instance of {@link GetExistingLNNumRangeResponse }
     *
     */
    public GetExistingLNNumRangeResponse createGetExistingLNNumRangeResponse() {
        return new GetExistingLNNumRangeResponse();
    }

    /**
     * Create an instance of {@link ROWSETWRAPPER }
     *
     */
    public ROWSETWRAPPER createROWSETWRAPPER() {
        return new ROWSETWRAPPER();
    }

    /**
     * Create an instance of {@link GetLNData }
     *
     */
    public GetLNData createGetLNData() {
        return new GetLNData();
    }

    /**
     * Create an instance of {@link OUTROWSETWRAPPER }
     *
     */
    public OUTROWSETWRAPPER createOUTROWSETWRAPPER() {
        return new OUTROWSETWRAPPER();
    }

    /**
     * Create an instance of {@link GetLNDataResponse }
     *
     */
    public GetLNDataResponse createGetLNDataResponse() {
        return new GetLNDataResponse();
    }

    /**
     * Create an instance of {@link LnNumList }
     *
     */
    public LnNumList createLnNumList() {
        return new LnNumList();
    }

    /**
     * Create an instance of {@link FileOperationsLnUserGetNewLNNumOut }
     *
     */
    public FileOperationsLnUserGetNewLNNumOut createFileOperationsLnUserGetNewLNNumOut() {
        return new FileOperationsLnUserGetNewLNNumOut();
    }

    /**
     * Create an instance of {@link TREATFULLPERIOD }
     *
     */
    public TREATFULLPERIOD createTREATFULLPERIOD() {
        return new TREATFULLPERIOD();
    }

    /**
     * Create an instance of {@link FileOperationsLnUserGetNewLNNumRangeOut }
     *
     */
    public FileOperationsLnUserGetNewLNNumRangeOut createFileOperationsLnUserGetNewLNNumRangeOut() {
        return new FileOperationsLnUserGetNewLNNumRangeOut();
    }

    /**
     * Create an instance of {@link TREATPERIOD }
     *
     */
    public TREATPERIOD createTREATPERIOD() {
        return new TREATPERIOD();
    }

    /**
     * Create an instance of {@link WSResult }
     *
     */
    public WSResult createWSResult() {
        return new WSResult();
    }

    /**
     * Create an instance of {@link FileOperationsLnUserGetExistingLNNumRangeOut }
     *
     */
    public FileOperationsLnUserGetExistingLNNumRangeOut createFileOperationsLnUserGetExistingLNNumRangeOut() {
        return new FileOperationsLnUserGetExistingLNNumRangeOut();
    }

    /**
     * Create an instance of {@link FileOperationsLnUserGetLNDataOut.DATA }
     *
     */
    public FileOperationsLnUserGetLNDataOut.DATA createFileOperationsLnUserGetLNDataOutDATA() {
        return new FileOperationsLnUserGetLNDataOut.DATA();
    }

    /**
     * Create an instance of {@link FileOperationsLnUserDisableLnOut.DATA }
     *
     */
    public FileOperationsLnUserDisableLnOut.DATA createFileOperationsLnUserDisableLnOutDATA() {
        return new FileOperationsLnUserDisableLnOut.DATA();
    }

    /**
     * Create an instance of {@link PrParseFilelnlpuElement.PXmlFile }
     *
     */
    public PrParseFilelnlpuElement.PXmlFile createPrParseFilelnlpuElementPXmlFile() {
        return new PrParseFilelnlpuElement.PXmlFile();
    }

    /**
     * Create an instance of {@link ru.ibs.fss.ln.ws.fileoperationsln.ROW.HOSPITALBREACH }
     *
     */
    public ru.ibs.fss.ln.ws.fileoperationsln.ROW.HOSPITALBREACH createROWHOSPITALBREACH() {
        return new ru.ibs.fss.ln.ws.fileoperationsln.ROW.HOSPITALBREACH();
    }

    /**
     * Create an instance of {@link ru.ibs.fss.ln.ws.fileoperationsln.ROW.TREATPERIODS }
     *
     */
    public ru.ibs.fss.ln.ws.fileoperationsln.ROW.TREATPERIODS createROWTREATPERIODS() {
        return new ru.ibs.fss.ln.ws.fileoperationsln.ROW.TREATPERIODS();
    }

    /**
     * Create an instance of {@link ru.ibs.fss.ln.ws.fileoperationsln.ROW.LNRESULT }
     *
     */
    public ru.ibs.fss.ln.ws.fileoperationsln.ROW.LNRESULT createROWLNRESULT() {
        return new ru.ibs.fss.ln.ws.fileoperationsln.ROW.LNRESULT();
    }

    /**
     * Create an instance of {@link INFO.ROWSET.ROW.ERRORS.ERROR }
     *
     */
    public INFO.ROWSET.ROW.ERRORS.ERROR createINFOROWSETROWERRORSERROR() {
        return new INFO.ROWSET.ROW.ERRORS.ERROR();
    }

    /**
     * Create an instance of {@link ru.ibs.fss.ln.ws.fileoperationsln.ROWSET.ROW.HOSPITALBREACH }
     *
     */
    public ru.ibs.fss.ln.ws.fileoperationsln.ROWSET.ROW.HOSPITALBREACH createROWSETROWHOSPITALBREACH() {
        return new ru.ibs.fss.ln.ws.fileoperationsln.ROWSET.ROW.HOSPITALBREACH();
    }

    /**
     * Create an instance of {@link ru.ibs.fss.ln.ws.fileoperationsln.ROWSET.ROW.TREATPERIODS }
     *
     */
    public ru.ibs.fss.ln.ws.fileoperationsln.ROWSET.ROW.TREATPERIODS createROWSETROWTREATPERIODS() {
        return new ru.ibs.fss.ln.ws.fileoperationsln.ROWSET.ROW.TREATPERIODS();
    }

    /**
     * Create an instance of {@link ru.ibs.fss.ln.ws.fileoperationsln.ROWSET.ROW.LNRESULT }
     *
     */
    public ru.ibs.fss.ln.ws.fileoperationsln.ROWSET.ROW.LNRESULT createROWSETROWLNRESULT() {
        return new ru.ibs.fss.ln.ws.fileoperationsln.ROWSET.ROW.LNRESULT();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DisableLnResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", name = "disableLnResponse")
    public JAXBElement<DisableLnResponse> createDisableLnResponse(DisableLnResponse value) {
        return new JAXBElement<DisableLnResponse>(_DisableLnResponse_QNAME, DisableLnResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrParseFilelnlpuResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", name = "prParseFilelnlpuResponse")
    public JAXBElement<PrParseFilelnlpuResponse> createPrParseFilelnlpuResponse(PrParseFilelnlpuResponse value) {
        return new JAXBElement<PrParseFilelnlpuResponse>(_PrParseFilelnlpuResponse_QNAME, PrParseFilelnlpuResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrParseFilelnlpu }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", name = "prParseFilelnlpu")
    public JAXBElement<PrParseFilelnlpu> createPrParseFilelnlpu(PrParseFilelnlpu value) {
        return new JAXBElement<PrParseFilelnlpu>(_PrParseFilelnlpu_QNAME, PrParseFilelnlpu.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DisableLn }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", name = "disableLn")
    public JAXBElement<DisableLn> createDisableLn(DisableLn value) {
        return new JAXBElement<DisableLn>(_DisableLn_QNAME, DisableLn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OUTROWSET }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", name = "OUT_ROWSET")
    public JAXBElement<OUTROWSET> createOUTROWSET(OUTROWSET value) {
        return new JAXBElement<OUTROWSET>(_OUTROWSET_QNAME, OUTROWSET.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetExistingLNNumRange }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", name = "getExistingLNNumRange")
    public JAXBElement<GetExistingLNNumRange> createGetExistingLNNumRange(GetExistingLNNumRange value) {
        return new JAXBElement<GetExistingLNNumRange>(_GetExistingLNNumRange_QNAME, GetExistingLNNumRange.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNewLNNumRangeResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", name = "getNewLNNumRangeResponse")
    public JAXBElement<GetNewLNNumRangeResponse> createGetNewLNNumRangeResponse(GetNewLNNumRangeResponse value) {
        return new JAXBElement<GetNewLNNumRangeResponse>(_GetNewLNNumRangeResponse_QNAME, GetNewLNNumRangeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNewLNNum }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", name = "getNewLNNum")
    public JAXBElement<GetNewLNNum> createGetNewLNNum(GetNewLNNum value) {
        return new JAXBElement<GetNewLNNum>(_GetNewLNNum_QNAME, GetNewLNNum.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNewLNNumResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", name = "getNewLNNumResponse")
    public JAXBElement<GetNewLNNumResponse> createGetNewLNNumResponse(GetNewLNNumResponse value) {
        return new JAXBElement<GetNewLNNumResponse>(_GetNewLNNumResponse_QNAME, GetNewLNNumResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SOAPException }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", name = "SOAPException")
    public JAXBElement<SOAPException> createSOAPException(SOAPException value) {
        return new JAXBElement<SOAPException>(_SOAPException_QNAME, SOAPException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetExistingLNNumRangeResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", name = "getExistingLNNumRangeResponse")
    public JAXBElement<GetExistingLNNumRangeResponse> createGetExistingLNNumRangeResponse(GetExistingLNNumRangeResponse value) {
        return new JAXBElement<GetExistingLNNumRangeResponse>(_GetExistingLNNumRangeResponse_QNAME, GetExistingLNNumRangeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNewLNNumRange }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", name = "getNewLNNumRange")
    public JAXBElement<GetNewLNNumRange> createGetNewLNNumRange(GetNewLNNumRange value) {
        return new JAXBElement<GetNewLNNumRange>(_GetNewLNNumRange_QNAME, GetNewLNNumRange.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLNDataResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", name = "getLNDataResponse")
    public JAXBElement<GetLNDataResponse> createGetLNDataResponse(GetLNDataResponse value) {
        return new JAXBElement<GetLNDataResponse>(_GetLNDataResponse_QNAME, GetLNDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ru.ibs.fss.ln.ws.fileoperationsln.ROWSET }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", name = "ROWSET")
    public JAXBElement<ru.ibs.fss.ln.ws.fileoperationsln.ROWSET> createROWSET(ru.ibs.fss.ln.ws.fileoperationsln.ROWSET value) {
        return new JAXBElement<ru.ibs.fss.ln.ws.fileoperationsln.ROWSET>(_ROWSET_QNAME, ru.ibs.fss.ln.ws.fileoperationsln.ROWSET.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link INFO }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", name = "INFO")
    public JAXBElement<INFO> createINFO(INFO value) {
        return new JAXBElement<INFO>(_INFO_QNAME, INFO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLNData }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl", name = "getLNData")
    public JAXBElement<GetLNData> createGetLNData(GetLNData value) {
        return new JAXBElement<GetLNData>(_GetLNData_QNAME, GetLNData.class, null, value);
    }

}
