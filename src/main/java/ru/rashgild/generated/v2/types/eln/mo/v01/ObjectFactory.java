package ru.rashgild.generated.v2.types.eln.mo.v01;


import ru.rashgild.generated.v2.types.eln.v01.Info;
import ru.rashgild.generated.v2.types.eln.v01.LnCodeList;
import ru.rashgild.generated.v2.types.eln.v01.WSResult;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.v2.fss.integration.types.eln.mo.v01 package.
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

    private final static QName _OutRowsetLNListbySnils_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "outRowsetLNListbySnils");
    private final static QName _GetLNListBySnilsResponse_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "getLNListBySnilsResponse");
    private final static QName _GetNewLNNumRangeRequest_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "getNewLNNumRangeRequest");
    private final static QName _PrParseFileNumberslpuRequest_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "prParseFileNumberslpuRequest");
    private final static QName _GetNewLNNumResponse_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "getNewLNNumResponse");
    private final static QName _GetLNDataResponse_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "getLNDataResponse");
    private final static QName _DisableLnRequest_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "disableLnRequest");
    private final static QName _GetLNDataRequest_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "getLNDataRequest");
    private final static QName _GetNewLNNumRequest_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "getNewLNNumRequest");
    private final static QName _GetLNListByDateRequest_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "getLNListByDateRequest");
    private final static QName _OutRowsetLNListbyDate_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "outRowsetLNListbyDate");
    private final static QName _GetExistingLNNumRangeRequest_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "getExistingLNNumRangeRequest");
    private final static QName _PrParseFileNumberslpuResponse_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "prParseFileNumberslpuResponse");
    private final static QName _Data_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "data");
    private final static QName _GetLNListByDateResponse_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "getLNListByDateResponse");
    private final static QName _PrParseFilelnlpuRequest_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "prParseFilelnlpuRequest");
    private final static QName _DisableLnResponse_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "disableLnResponse");
    private final static QName _GetExistingLNNumRangeResponse_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "getExistingLNNumRangeResponse");
    private final static QName _GetNewLNNumRangeResponse_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "getNewLNNumRangeResponse");
    private final static QName _GetLNListBySnilsRequest_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "getLNListBySnilsRequest");
    private final static QName _Rowset_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "rowset");
    private final static QName _PrParseFilelnlpuResponse_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "prParseFilelnlpuResponse");
    private final static QName _Info_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "info");
    private final static QName _OutRowset_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "outRowset");
    private final static QName _RowsetRowLnHash_QNAME = new QName("http://www.fss.ru/integration/types/eln/mo/v01", "lnHash");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.v2.fss.integration.types.eln.mo.v01
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResponseRow }
     * 
     */
    public ResponseRow createResponseRow() {
        return new ResponseRow();
    }

    /**
     * Create an instance of {@link ResponseRow.ServData }
     * 
     */
    public ResponseRow.ServData createResponseRowServData() {
        return new ResponseRow.ServData();
    }

    /**
     * Create an instance of {@link Rowset }
     * 
     */
    public Rowset createRowset() {
        return new Rowset();
    }

    /**
     * Create an instance of {@link Rowset.Row }
     * 
     */
    public Rowset.Row createRowsetRow() {
        return new Rowset.Row();
    }

    /**
     * Create an instance of {@link Rowset.Row.ServData }
     * 
     */
    public Rowset.Row.ServData createRowsetRowServData() {
        return new Rowset.Row.ServData();
    }

    /**
     * Create an instance of {@link PrParseFileNumberslpuRequest }
     * 
     */
    public PrParseFileNumberslpuRequest createPrParseFileNumberslpuRequest() {
        return new PrParseFileNumberslpuRequest();
    }

    /**
     * Create an instance of {@link FileOperationsLnUserGetLNDataOut }
     * 
     */
    public FileOperationsLnUserGetLNDataOut createFileOperationsLnUserGetLNDataOut() {
        return new FileOperationsLnUserGetLNDataOut();
    }

    /**
     * Create an instance of {@link PrParseFilelnlpuRequest }
     * 
     */
    public PrParseFilelnlpuRequest createPrParseFilelnlpuRequest() {
        return new PrParseFilelnlpuRequest();
    }

    /**
     * Create an instance of {@link FileOperationsLnUserGetLNListBySnilsOut }
     * 
     */
    public FileOperationsLnUserGetLNListBySnilsOut createFileOperationsLnUserGetLNListBySnilsOut() {
        return new FileOperationsLnUserGetLNListBySnilsOut();
    }

    /**
     * Create an instance of {@link FileOperationsLnUserGetLNListByDateOut }
     * 
     */
    public FileOperationsLnUserGetLNListByDateOut createFileOperationsLnUserGetLNListByDateOut() {
        return new FileOperationsLnUserGetLNListByDateOut();
    }

    /**
     * Create an instance of {@link GetExistingLNNumRangeRequest }
     * 
     */
    public GetExistingLNNumRangeRequest createGetExistingLNNumRangeRequest() {
        return new GetExistingLNNumRangeRequest();
    }

    /**
     * Create an instance of {@link OutRowsetLNListbySnils }
     * 
     */
    public OutRowsetLNListbySnils createOutRowsetLNListbySnils() {
        return new OutRowsetLNListbySnils();
    }

    /**
     * Create an instance of {@link GetNewLNNumRangeRequest }
     * 
     */
    public GetNewLNNumRangeRequest createGetNewLNNumRangeRequest() {
        return new GetNewLNNumRangeRequest();
    }

    /**
     * Create an instance of {@link FileOperationsLnUserGetExistingLNNumRangeOut }
     * 
     */
    public FileOperationsLnUserGetExistingLNNumRangeOut createFileOperationsLnUserGetExistingLNNumRangeOut() {
        return new FileOperationsLnUserGetExistingLNNumRangeOut();
    }

    /**
     * Create an instance of {@link FileOperationsLnUserGetNewLNNumRangeOut }
     * 
     */
    public FileOperationsLnUserGetNewLNNumRangeOut createFileOperationsLnUserGetNewLNNumRangeOut() {
        return new FileOperationsLnUserGetNewLNNumRangeOut();
    }

    /**
     * Create an instance of {@link FileOperationsLnUserGetNewLNNumOut }
     * 
     */
    public FileOperationsLnUserGetNewLNNumOut createFileOperationsLnUserGetNewLNNumOut() {
        return new FileOperationsLnUserGetNewLNNumOut();
    }

    /**
     * Create an instance of {@link DisableLnRequest }
     * 
     */
    public DisableLnRequest createDisableLnRequest() {
        return new DisableLnRequest();
    }

    /**
     * Create an instance of {@link GetLNListBySnilsRequest }
     * 
     */
    public GetLNListBySnilsRequest createGetLNListBySnilsRequest() {
        return new GetLNListBySnilsRequest();
    }

    /**
     * Create an instance of {@link GetNewLNNumRequest }
     * 
     */
    public GetNewLNNumRequest createGetNewLNNumRequest() {
        return new GetNewLNNumRequest();
    }

    /**
     * Create an instance of {@link GetLNDataRequest }
     * 
     */
    public GetLNDataRequest createGetLNDataRequest() {
        return new GetLNDataRequest();
    }

    /**
     * Create an instance of {@link GetLNListByDateRequest }
     * 
     */
    public GetLNListByDateRequest createGetLNListByDateRequest() {
        return new GetLNListByDateRequest();
    }

    /**
     * Create an instance of {@link OutRowsetLNListbyDate }
     * 
     */
    public OutRowsetLNListbyDate createOutRowsetLNListbyDate() {
        return new OutRowsetLNListbyDate();
    }

    /**
     * Create an instance of {@link OutRowset }
     * 
     */
    public OutRowset createOutRowset() {
        return new OutRowset();
    }

    /**
     * Create an instance of {@link RowLNbySnils }
     * 
     */
    public RowLNbySnils createRowLNbySnils() {
        return new RowLNbySnils();
    }

    /**
     * Create an instance of {@link RowLNbyDate }
     * 
     */
    public RowLNbyDate createRowLNbyDate() {
        return new RowLNbyDate();
    }

    /**
     * Create an instance of {@link ResponseRow.TreatPeriods }
     * 
     */
    public ResponseRow.TreatPeriods createResponseRowTreatPeriods() {
        return new ResponseRow.TreatPeriods();
    }

    /**
     * Create an instance of {@link ResponseRow.ServData.ServFullData }
     * 
     */
    public ResponseRow.ServData.ServFullData createResponseRowServDataServFullData() {
        return new ResponseRow.ServData.ServFullData();
    }

    /**
     * Create an instance of {@link Rowset.Row.HospitalBreach }
     * 
     */
    public Rowset.Row.HospitalBreach createRowsetRowHospitalBreach() {
        return new Rowset.Row.HospitalBreach();
    }

    /**
     * Create an instance of {@link Rowset.Row.TreatPeriods }
     * 
     */
    public Rowset.Row.TreatPeriods createRowsetRowTreatPeriods() {
        return new Rowset.Row.TreatPeriods();
    }

    /**
     * Create an instance of {@link Rowset.Row.LnResult }
     * 
     */
    public Rowset.Row.LnResult createRowsetRowLnResult() {
        return new Rowset.Row.LnResult();
    }

    /**
     * Create an instance of {@link Rowset.Row.ServData.ServFullData }
     * 
     */
    public Rowset.Row.ServData.ServFullData createRowsetRowServDataServFullData() {
        return new Rowset.Row.ServData.ServFullData();
    }

    /**
     * Create an instance of {@link PrParseFileNumberslpuRequest.PXmlNumFile }
     * 
     */
    public PrParseFileNumberslpuRequest.PXmlNumFile createPrParseFileNumberslpuRequestPXmlNumFile() {
        return new PrParseFileNumberslpuRequest.PXmlNumFile();
    }

    /**
     * Create an instance of {@link FileOperationsLnUserGetLNDataOut.Data }
     * 
     */
    public FileOperationsLnUserGetLNDataOut.Data createFileOperationsLnUserGetLNDataOutData() {
        return new FileOperationsLnUserGetLNDataOut.Data();
    }

    /**
     * Create an instance of {@link PrParseFilelnlpuRequest.PXmlFile }
     * 
     */
    public PrParseFilelnlpuRequest.PXmlFile createPrParseFilelnlpuRequestPXmlFile() {
        return new PrParseFilelnlpuRequest.PXmlFile();
    }

    /**
     * Create an instance of {@link FileOperationsLnUserGetLNListBySnilsOut.Data }
     * 
     */
    public FileOperationsLnUserGetLNListBySnilsOut.Data createFileOperationsLnUserGetLNListBySnilsOutData() {
        return new FileOperationsLnUserGetLNListBySnilsOut.Data();
    }

    /**
     * Create an instance of {@link FileOperationsLnUserGetLNListByDateOut.Data }
     * 
     */
    public FileOperationsLnUserGetLNListByDateOut.Data createFileOperationsLnUserGetLNListByDateOutData() {
        return new FileOperationsLnUserGetLNListByDateOut.Data();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OutRowsetLNListbySnils }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "outRowsetLNListbySnils")
    public JAXBElement<OutRowsetLNListbySnils> createOutRowsetLNListbySnils(OutRowsetLNListbySnils value) {
        return new JAXBElement<OutRowsetLNListbySnils>(_OutRowsetLNListbySnils_QNAME, OutRowsetLNListbySnils.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FileOperationsLnUserGetLNListBySnilsOut }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "getLNListBySnilsResponse")
    public JAXBElement<FileOperationsLnUserGetLNListBySnilsOut> createGetLNListBySnilsResponse(FileOperationsLnUserGetLNListBySnilsOut value) {
        return new JAXBElement<FileOperationsLnUserGetLNListBySnilsOut>(_GetLNListBySnilsResponse_QNAME, FileOperationsLnUserGetLNListBySnilsOut.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNewLNNumRangeRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "getNewLNNumRangeRequest")
    public JAXBElement<GetNewLNNumRangeRequest> createGetNewLNNumRangeRequest(GetNewLNNumRangeRequest value) {
        return new JAXBElement<GetNewLNNumRangeRequest>(_GetNewLNNumRangeRequest_QNAME, GetNewLNNumRangeRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrParseFileNumberslpuRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "prParseFileNumberslpuRequest")
    public JAXBElement<PrParseFileNumberslpuRequest> createPrParseFileNumberslpuRequest(PrParseFileNumberslpuRequest value) {
        return new JAXBElement<PrParseFileNumberslpuRequest>(_PrParseFileNumberslpuRequest_QNAME, PrParseFileNumberslpuRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FileOperationsLnUserGetNewLNNumOut }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "getNewLNNumResponse")
    public JAXBElement<FileOperationsLnUserGetNewLNNumOut> createGetNewLNNumResponse(FileOperationsLnUserGetNewLNNumOut value) {
        return new JAXBElement<FileOperationsLnUserGetNewLNNumOut>(_GetNewLNNumResponse_QNAME, FileOperationsLnUserGetNewLNNumOut.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FileOperationsLnUserGetLNDataOut }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "getLNDataResponse")
    public JAXBElement<FileOperationsLnUserGetLNDataOut> createGetLNDataResponse(FileOperationsLnUserGetLNDataOut value) {
        return new JAXBElement<FileOperationsLnUserGetLNDataOut>(_GetLNDataResponse_QNAME, FileOperationsLnUserGetLNDataOut.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DisableLnRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "disableLnRequest")
    public JAXBElement<DisableLnRequest> createDisableLnRequest(DisableLnRequest value) {
        return new JAXBElement<DisableLnRequest>(_DisableLnRequest_QNAME, DisableLnRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLNDataRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "getLNDataRequest")
    public JAXBElement<GetLNDataRequest> createGetLNDataRequest(GetLNDataRequest value) {
        return new JAXBElement<GetLNDataRequest>(_GetLNDataRequest_QNAME, GetLNDataRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNewLNNumRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "getNewLNNumRequest")
    public JAXBElement<GetNewLNNumRequest> createGetNewLNNumRequest(GetNewLNNumRequest value) {
        return new JAXBElement<GetNewLNNumRequest>(_GetNewLNNumRequest_QNAME, GetNewLNNumRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLNListByDateRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "getLNListByDateRequest")
    public JAXBElement<GetLNListByDateRequest> createGetLNListByDateRequest(GetLNListByDateRequest value) {
        return new JAXBElement<GetLNListByDateRequest>(_GetLNListByDateRequest_QNAME, GetLNListByDateRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OutRowsetLNListbyDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "outRowsetLNListbyDate")
    public JAXBElement<OutRowsetLNListbyDate> createOutRowsetLNListbyDate(OutRowsetLNListbyDate value) {
        return new JAXBElement<OutRowsetLNListbyDate>(_OutRowsetLNListbyDate_QNAME, OutRowsetLNListbyDate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetExistingLNNumRangeRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "getExistingLNNumRangeRequest")
    public JAXBElement<GetExistingLNNumRangeRequest> createGetExistingLNNumRangeRequest(GetExistingLNNumRangeRequest value) {
        return new JAXBElement<GetExistingLNNumRangeRequest>(_GetExistingLNNumRangeRequest_QNAME, GetExistingLNNumRangeRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ru.rashgild.generated.v2.types.eln.v01.WSResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "prParseFileNumberslpuResponse")
    public JAXBElement<WSResult> createPrParseFileNumberslpuResponse(WSResult value) {
        return new JAXBElement<WSResult>(_PrParseFileNumberslpuResponse_QNAME, WSResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LnCodeList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "data")
    public JAXBElement<LnCodeList> createData(LnCodeList value) {
        return new JAXBElement<LnCodeList>(_Data_QNAME, LnCodeList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FileOperationsLnUserGetLNListByDateOut }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "getLNListByDateResponse")
    public JAXBElement<FileOperationsLnUserGetLNListByDateOut> createGetLNListByDateResponse(FileOperationsLnUserGetLNListByDateOut value) {
        return new JAXBElement<FileOperationsLnUserGetLNListByDateOut>(_GetLNListByDateResponse_QNAME, FileOperationsLnUserGetLNListByDateOut.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrParseFilelnlpuRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "prParseFilelnlpuRequest")
    public JAXBElement<PrParseFilelnlpuRequest> createPrParseFilelnlpuRequest(PrParseFilelnlpuRequest value) {
        return new JAXBElement<PrParseFilelnlpuRequest>(_PrParseFilelnlpuRequest_QNAME, PrParseFilelnlpuRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "disableLnResponse")
    public JAXBElement<WSResult> createDisableLnResponse(WSResult value) {
        return new JAXBElement<WSResult>(_DisableLnResponse_QNAME, WSResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FileOperationsLnUserGetExistingLNNumRangeOut }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "getExistingLNNumRangeResponse")
    public JAXBElement<FileOperationsLnUserGetExistingLNNumRangeOut> createGetExistingLNNumRangeResponse(FileOperationsLnUserGetExistingLNNumRangeOut value) {
        return new JAXBElement<FileOperationsLnUserGetExistingLNNumRangeOut>(_GetExistingLNNumRangeResponse_QNAME, FileOperationsLnUserGetExistingLNNumRangeOut.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FileOperationsLnUserGetNewLNNumRangeOut }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "getNewLNNumRangeResponse")
    public JAXBElement<FileOperationsLnUserGetNewLNNumRangeOut> createGetNewLNNumRangeResponse(FileOperationsLnUserGetNewLNNumRangeOut value) {
        return new JAXBElement<FileOperationsLnUserGetNewLNNumRangeOut>(_GetNewLNNumRangeResponse_QNAME, FileOperationsLnUserGetNewLNNumRangeOut.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLNListBySnilsRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "getLNListBySnilsRequest")
    public JAXBElement<GetLNListBySnilsRequest> createGetLNListBySnilsRequest(GetLNListBySnilsRequest value) {
        return new JAXBElement<GetLNListBySnilsRequest>(_GetLNListBySnilsRequest_QNAME, GetLNListBySnilsRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Rowset }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "rowset")
    public JAXBElement<Rowset> createRowset(Rowset value) {
        return new JAXBElement<Rowset>(_Rowset_QNAME, Rowset.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "prParseFilelnlpuResponse")
    public JAXBElement<WSResult> createPrParseFilelnlpuResponse(WSResult value) {
        return new JAXBElement<WSResult>(_PrParseFilelnlpuResponse_QNAME, WSResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Info }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "info")
    public JAXBElement<Info> createInfo(Info value) {
        return new JAXBElement<Info>(_Info_QNAME, Info.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OutRowset }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "outRowset")
    public JAXBElement<OutRowset> createOutRowset(OutRowset value) {
        return new JAXBElement<OutRowset>(_OutRowset_QNAME, OutRowset.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fss.ru/integration/types/eln/mo/v01", name = "lnHash", scope = Rowset.Row.class)
    public JAXBElement<String> createRowsetRowLnHash(String value) {
        return new JAXBElement<String>(_RowsetRowLnHash_QNAME, String.class, Rowset.Row.class, value);
    }

}
