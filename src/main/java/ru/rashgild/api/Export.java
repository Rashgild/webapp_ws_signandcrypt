package ru.rashgild.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FIleOperationService;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FileOperationsLnService;
import ru.rashgild.generated.v2.types.eln.mo.v01.PrParseFilelnlpuRequest;
import ru.rashgild.generated.v2.types.eln.mo.v01.Rowset;
import ru.rashgild.generated.v2.types.eln.v01.Info;
import ru.rashgild.generated.v2.types.eln.v01.TreatFullPeriodMo;
import ru.rashgild.generated.v2.types.eln.v01.WSResult;
import ru.rashgild.service.DependencyInjection;
import ru.rashgild.signAndCrypt.Sign;
import ru.rashgild.utils.CertificateUtils;
import ru.rashgild.utils.GlobalVariables;
import ru.rashgild.utils.XmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static ru.rashgild.api.ApiUtils.*;
import static ru.rashgild.signAndCrypt.Encrypt.createXmlAndEncrypt;
import static ru.rashgild.utils.GlobalVariables.*;
import static ru.rashgild.utils.Utils.parseSnils;
import static ru.rashgild.utils.XmlUtils.saveSoapToXml;
import static ru.rashgild.utils.XmlUtils.soapMessageToString;


@Path("/export")
public class Export {

    private static final String ELN = "ELN_";

    @POST
    @Path("/exportDisabilityDocument")
    public String getJsonDisabilityDoc(String data,
                                       @QueryParam("isTest") Boolean isTest,
                                       @Context HttpServletRequest request,
                                       @Context HttpServletResponse response) throws Exception {

        System.out.println("exportDisabilityDocument");
        SOAPMessage message = createDisabilityXml(data);
        try {
            String mess = soapMessageToString(message);
            GlobalVariables.Request = createXmlAndEncrypt(mess);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendRequest(isTest);
    }

    private String sendRequest(Boolean isTest) {
        JSONObject resultJson = new JSONObject();

        try {
            FIleOperationService service = DependencyInjection.getImplementation(isTest);
            FileOperationsLnService start = service.getFileOperationsLnPort();

            Rowset rowset = createTestData();
            rowset.setAuthor("ThisIsNewSender");

            PrParseFilelnlpuRequest.PXmlFile pXmlFile = new PrParseFilelnlpuRequest.PXmlFile();
            pXmlFile.setRowset(rowset);
            PrParseFilelnlpuRequest request = new PrParseFilelnlpuRequest();
            request.setOgrn("1053000627690");
            request.setPXmlFile(pXmlFile);

            WSResult result = null;
            try {
                result = start.prParseFilelnlpu(request);
            } catch (Exception exc) {
                exc.printStackTrace();
            }


            resultJson.put("message", result.getMess());
            resultJson.put("status", result.getStatus());
            resultJson.put("requestId", result.getRequestId());

            List<Info.InfoRowset.InfoRow> rows = result.getInfo().getInfoRowset().getInfoRow();
            if (rows != null && rows.size() > 0) {
                for (Info.InfoRowset.InfoRow row : rows) {

                    if (row.getLnCode() != null && !row.getLnCode().isEmpty()) {
                        resultJson.put("hash", row.getLnHash());
                    }

                    if (row.getLnState() != null && !row.getLnState().isEmpty()) {
                        resultJson.put("lnstate", row.getLnState());
                    }
                    resultJson.put("lncode", row.getLnCode());
                    JSONArray jsonArray = new JSONArray();
                    try {
                        if (row.getErrors() != null && !row.getErrors().getError().isEmpty()) {
                            List<Info.InfoRowset.InfoRow.Errors.Error> errors = row.getErrors().getError();
                            for (Info.InfoRowset.InfoRow.Errors.Error errs : errors) {
                                JSONObject arrjs = new JSONObject();
                                arrjs.put("errmess", errs.getErrMess()).put("errcode", errs.getErrCode());
                                jsonArray.put(arrjs);
                            }
                            resultJson.put("errors", jsonArray);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJson.toString();
    }

    private SOAPMessage createDisabilityXml(String json) throws Exception {
        int per = 3;
        String head = "";
        JsonParser parser = new JsonParser();
        JsonObject jparsr = parser.parse(json).getAsJsonObject();
        JsonArray treats = jparsr.getAsJsonArray("treats");
        JsonArray signclose = jparsr.getAsJsonArray("close");
        String StartPeriod = null;
        int DDID_1 = 0;

        JsonObject jrow = parser.parse(json).getAsJsonObject();
        GlobalVariables.t_ELN = get(jrow, "ln_code");
        DDID_1 = Integer.parseInt(get(jrow, "ddid"));

        String snils = parseSnils(get(jrow, "snils"));
        String elnPrefix = ELN + t_ELN;


        Rowset.Row row = new Rowset.Row();
        row.setId(elnPrefix);
        row.setSnils(snils);
        row.setSurname(get(jrow, "surname"));
        row.setName(get(jrow, "name"));
        row.setPatronymic(get(jrow, "patronimic"));
        row.setLnCode(get(jrow, "ln_code"));
        row.setPrimaryFlag(getBoolean(jrow, "primary_flag"));
        row.setPreviouslyIssuedCode(getOrNull(jrow, "previously_issued_code"));
        row.setDuplicateFlag(Boolean.parseBoolean(get(jrow, "duplicate_flag")));
        row.setLnDate(getDate(jrow, "ln_date"));
        //row.setIdMo("0");
        row.setLpuName(isNotNullOrEmpty(get(jrow, "lpu_name")) ? get(jrow, "lpu_name") : "ГБУЗ АО АМОКБ");
        row.setLpuAddress(isNotNullOrEmpty(get(jrow, "lpu_address")) ? get(jrow, "lpu_address") : "ГДЕ-ТО");
        row.setLpuOgrn(get(jrow, "lpu_ogrn").isEmpty() ? ogrnMo : get(jrow, "lpu_ogrn"));
        row.setBirthday(getDate(jrow, "birthday"));
        row.setGender(Integer.parseInt(get(jrow, "gender")));
        row.setReason1(get(jrow, "reason1"));
        row.setReason2(get(jrow, "reason2"));
        row.setDiagnos(get(jrow, "diagnos"));
        row.setDate1(getDate(jrow, "date1"));
        row.setDate2(getDate(jrow, "date2"));

        row.setPregn12WFlag(getBoolean(jrow, "pregn12w_flag"));
        row.setHospitalDt1(getDate(jrow, "hospital_dt1"));
        row.setHospitalDt2(getDate(jrow, "hospital_dt2"));
        row.setLnState(get(jrow, "ln_state"));
        row.setWrittenAgreementFlag(true);
        row.setIntermittentMethodFlag(false);

        if (isNotNullOrEmpty(get(jrow, "prev_ln"))) {
            row.setPrevLnCode(get(jrow, "prev_ln"));
        }

        if (isNotNullOrEmpty(get(jrow, "voucher_no")) && isNotNullOrEmpty(get(jrow, "voucher_ogrn"))) {
            row.setVoucherNo(get(jrow, "voucher_no"));
            row.setVoucherOgrn(get(jrow, "voucher_ogrn"));
        }

        if (!get(jrow, "ln_hash").equals("null")) {
            row.setLnHash(get(jrow, "ln_hash"));
        }

        /*row.setMseDt1(getDate(jrow, "mse_dt1"));
        row.setMseDt2(getDate(jrow, "mse_dt2"));
        row.setMseDt3(getDate(jrow, "mse_dt3"));
        String inv = get(jrow, "mse_invalid_group");
        if (inv != null && !inv.equals("")) {
            row.setMseInvalidGroup(Integer.valueOf(inv));
        }*/

        /** HOSPITAL_BREACH **/
        if (isNotNullOrEmpty(get(jrow, "hospital_breach_code"))) {
            //Row.HOSPITAL_BREACH hospital_breach = new Row.HOSPITAL_BREACH();
            Rowset.Row.HospitalBreach hospitalBreach = new Rowset.Row.HospitalBreach();
            //hospital_breach.setHospitalBreachDt(get(jrow, "hospital_breach_code"));
            //hospital_breach.setHospitalBreachDt(get(jrow, "hospital_breach_dt"));
            if (hospitalBreach.getHospitalBreachCode() != null) {
                hospitalBreach.setId(elnPrefix + "_1_doc");
            }
            //List<Rowset.Row.HospitalBreach> hospital_breaches = new ArrayList<>();
            //hospital_breaches.add(hospital_breach);
            row.setHospitalBreach(hospitalBreach);
        }

        /** TREAT */
        List<TreatFullPeriodMo> treatFullPeriodMoList = new ArrayList<>();
        for (JsonElement el : treats) {
            JsonObject jtreat = el.getAsJsonObject();
            String isexport = get(jtreat, "isexport");
            int DDID_2 = Integer.parseInt(get(jtreat, "ddid"));

            if (DDID_1 == DDID_2) {
                TreatFullPeriodMo.TreatPeriod treatPeriod = new TreatFullPeriodMo.TreatPeriod();
                treatPeriod.setTreatDt1(get(jtreat, "treat_dt1"));
                if (StartPeriod == null) {
                    StartPeriod = get(jtreat, "treat_dt1");
                }

                treatPeriod.setTreatDt2(get(jtreat, "treat_dt2"));
                treatPeriod.setTreatDoctorRole(get(jtreat, "treat_doctor_role"));
                treatPeriod.setTreatDoctor(get(jtreat, "treat_doctor"));

                String counterdoc = get(jtreat, "counterdoc");
                String countervk = get(jtreat, "countervk");
                treatPeriod.setId(elnPrefix + "_" + per + "_doc");

                TreatFullPeriodMo treat_period = new TreatFullPeriodMo();
                treat_period.setTreatChairmanRole(get(jtreat, "treat_chairman_role"));
                treat_period.setTreatChairman(get(jtreat, "treat_chairman"));

                if (treat_period.getTreatChairmanRole() != null && !treat_period.getTreatChairmanRole().equals("")) {
                    treat_period.setId(elnPrefix + "_" + per + "_vk");
                }

                if (isexport == null || isexport.equals("") || (!isexport.equals("true") && !isexport.equals("t"))) {
                    if (treat_period.getTreatChairmanRole() != null && !treat_period.getTreatChairmanRole().equals("")) {
                        head += createHead(
                                get(jtreat, "certvk"),
                                get(jtreat, "digvk"),
                                get(jtreat, "signvk"),
                                elnPrefix,
                                GlobalVariables.ogrnMo,
                                get(jtreat, "countervk"),
                                "vk",
                                get(jtreat, "typesignvk"));
                    }
                    head += createHead(
                            get(jtreat, "certdoc"),
                            get(jtreat, "digdoc"),
                            get(jtreat, "signdoc"),
                            elnPrefix,
                            GlobalVariables.ogrnMo,
                            get(jtreat, "counterdoc"),
                            "doc",
                            get(jtreat, "typesigndoc"));
                }

                treat_period.setTreatPeriod(treatPeriod);
                treatFullPeriodMoList.add(treat_period);
                per++;
            }
        }
        Rowset.Row.TreatPeriods treatPeriods = new Rowset.Row.TreatPeriods();
        treatPeriods.setTreatFullPeriod(treatFullPeriodMoList);
        row.setTreatPeriods(treatPeriods);


        //Rowset.Row.ServData servData = new Rowset.Row.ServData();
        //row.setServData(servData);
        /*row.setServ1AGE(get(jrow, "serv1_age"));
        row.setServ1RELATIONCODE(get(jrow, "serv1_relation_code"));
        row.setServ1FIO(get(jrow, "serv1_fio"));
        row.setServ2AGE(get(jrow, "serv2_age"));
        row.setServ2RELATIONCODE(get(jrow, "serv2_relation_code"));
        row.setServ2FIO(get(jrow, "serv2_fio"));*/

       /*
        if(get(jrow, "serv1_age"))
        try {
            if (row.getServ1AGE() != null && !row.getServ1AGE().equals("")) {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = new java.sql.Date(format.parse(row.getServ1AGE()).getTime());
                Date date2 = new java.sql.Date(format.parse(StartPeriod).getTime());

                String years = calculateAge(date2, date1, 0);
                row.setServ1AGE(years);
                if (Integer.parseInt(years) < 1) {
                    row.setServ1MM(Integer.valueOf(calculateAge(date2, date1, 2)));
                }
            }

            if (row.getServ2AGE() != null && !row.getServ2AGE().equals("")) {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = new java.sql.Date(format.parse(row.getServ2AGE()).getTime());
                Date date2 = new java.sql.Date(format.parse(StartPeriod).getTime());

                row.setServ2AGE(calculateAge(date2, date1, 0));
                row.setServ2MM(Integer.valueOf(calculateAge(date2, date1, 2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /** LN_RESULT */
        boolean isClose = (get(jrow, "is_close")).equals("1");
        if (isClose) {
            Rowset.Row.LnResult lnResult = new Rowset.Row.LnResult();
            lnResult.setMseResult(get(jrow, "mse_result"));
            lnResult.setOtherStateDt(get(jrow, "other_state_dt"));

            lnResult.setId(elnPrefix + "_2_doc");
            for (JsonElement el : signclose) {

                JsonObject jtreat = el.getAsJsonObject();
                //Для даты выхода на работу
                lnResult.setReturnDateLpu(get(jtreat, "returndt"));
                head += createHead(
                        get(jtreat, "certclose"),
                        get(jtreat, "digclose"),
                        get(jtreat, "signclose"),
                        elnPrefix,
                        "1053000627690",
                        get(jtreat, "counterclose"),
                        "doc",
                        get(jtreat, "typesignclose"));
            }


            if (lnResult.getMseResult() != null) {
                if (!lnResult.getMseResult().equals("31") && !lnResult.getMseResult().equals("37")) {

                    if (lnResult.getOtherStateDt() != null && !lnResult.getOtherStateDt().equals("")) {
                        lnResult.setReturnDateLpu(null);
                    } else {
                        lnResult.setReturnDateLpu(getReturnWorkDate(lnResult.getReturnDateLpu()));
                    }
                } else {
                    lnResult.setReturnDateLpu(null);
                    lnResult.setNextLnCode(isNotNullOrEmpty(get(jrow, "next_ln_code")) ? "0" : get(jrow, "next_ln_code"));
                }
            } /*else if (!isClose) {
                lnResult.setReturnDateLpu(null);
            }*/
            row.setLnResult(lnResult);
        }

        /** ---- */

        //rows.add(row);
        Rowset rowset = new Rowset();
        //rowset.setAuthor("Rashgild");
        //rowset.setEmail("rashgild@gmail.com");
        //rowset.setPhone("9999");
        rowset.setVersion("2.0");
        rowset.setSoftware("sign-and-crypt");
        rowset.setVersionSoftware("2.0");
        rowset.getRow().add(row);
        //rowset.setRow(rows);

        //rowset = createTestData();

        PrParseFilelnlpuRequest.PXmlFile pXmlFile = new PrParseFilelnlpuRequest.PXmlFile();
        pXmlFile.setRowset(rowset);
        PrParseFilelnlpuRequest prParseFilelnlpu = new PrParseFilelnlpuRequest();
        prParseFilelnlpu.setOgrn(ogrnMo);
        prParseFilelnlpu.setPXmlFile(pXmlFile);

        String finalXml = createXml(prParseFilelnlpu).replace("[Head]", head);

        System.getProperty("console.encoding", "utf-8");
        InputStream is = new ByteArrayInputStream(finalXml.getBytes());
        SOAPMessage message = MessageFactory.newInstance().createMessage(null, is);

        saveSoapToXml("MyTempFile.xml", message);

        message = Sign.signationByParametrs(message,
                "http://eln.fss.ru/actor/mo/" + ogrnMo + "/" + row.getId(),
                "#" + row.getId(), moAlias, moPass, t_ELN);

        X509Certificate cert = CertificateUtils.getCertificateFromKeyStorage(GlobalVariables.moAlias);
        SOAPEnvelope soapEnvelope = message.getSOAPPart().getEnvelope();
        SOAPHeader header1 = soapEnvelope.getHeader();
        SOAPElement x509Certificate = header1.addChildElement("X509Certificate", null, "http://www.w3.org/2000/09/xmldsig#");
        x509Certificate.addTextNode(CertificateUtils.certToBase64(cert));

        return message;
    }

    private String createHead(String cert, String dig, String sig, String eln, String ogrn, String counter, String type, String signatureType) {
        String head = "<wsse:Security soapenv:actor=\"http://eln.fss.ru/actor/doc/[ELN]_[COUNTER]_[TYPE]\" " +
                "xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">" +
                "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">" +
                "<SignedInfo>" +
                "<CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>" +
                "<SignatureMethod Algorithm=\"[SIGNATURE_TYPE]\"/>" +
                "<Reference URI=\"#[ELN]_[COUNTER]_[TYPE]\">" +
                "<Transforms>" +
                "<Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>" +
                "</Transforms>" +
                "<DigestMethod Algorithm=\"[DIGEST_TYPE]\"/>" +
                "<DigestValue>[digest]</DigestValue></Reference></SignedInfo>" +
                "<SignatureValue>[signature]</SignatureValue>" +
                "<KeyInfo><wsse:SecurityTokenReference><wsse:Reference URI=\"#http://eln.fss.ru/actor/mo/[OGRN]/[ELN]\" ValueType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3\"/>" +
                "</wsse:SecurityTokenReference></KeyInfo>" +
                "</Signature>" +
                "<wse:BinarySecurityToken xmlns:wse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" " +
                "EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary\" " +
                "ValueType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3\" " +
                "wsu:Id=\"http://eln.fss.ru/actor/mo/[OGRN]/[ELN]\">[cert]</wse:BinarySecurityToken></wsse:Security>";

        return head
                .replace("[cert]", cert)
                .replace("[digest]", dig)
                .replace("[signature]", sig)
                .replace("[OGRN]", ogrn)
                .replace("[ELN]", eln)
                .replace("[COUNTER]", counter)
                .replace("[TYPE]", type)
                .replace("[SIGNATURE_TYPE]",
                        GOST_34102001.equals(signatureType)
                                ? GOST_34102001_LINK
                                : GOST_34102012_LINK_256)
                .replace("[DIGEST_TYPE]",
                        GOST_34102001.equals(signatureType)
                                ? GOST_34102001_LINK_SHORT
                                : GOST_34102012_LINK_SHORT_252);
    }

    private final String GOST_34102001 = "gostr34102001";
    private final String GOST_34102001_LINK = "http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411";
    private final String GOST_34102001_LINK_SHORT = "http://www.w3.org/2001/04/xmldsig-more#gostr3411";
    private final String GOST_34102012_LINK_256 = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34102012-gostr34112012-256";
    private final String GOST_34102012_LINK_SHORT_252 = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34112012-256";
    private final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    private String createXml(PrParseFilelnlpuRequest prParseFilelnlpu) {
        setUp();
        String signThis = "";
        String xmlTemplate = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:fil=\"http://www.fss.ru/integration/types/eln/mo/v01\" " +
                "xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" " +
                "xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" " +
                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
                "<soapenv:Header>[Head]</soapenv:Header><soapenv:Body>[Body]</soapenv:Body></soapenv:Envelope>";
        try {

            Document document = GlobalVariables.parser.objToSoap(prParseFilelnlpu);
            DOMSource domSource = new DOMSource(document);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);

            String xml = writer.toString();
            xml = xml.replace(XML_HEAD, "");
            signThis = xmlTemplate.replace("[Body]", xml);

        } catch (JAXBException | IOException | TransformerException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return signThis;
    }

    private String getReturnWorkDate(String date) {
        return String.valueOf(LocalDate.parse(date).plusDays(1));
    }

    private Rowset createTestData() {
        Rowset.Row row = new Rowset.Row();

        row.setId("ELN_" + t_ELN);
        row.setUnconditional(false);
        row.setSnils("12345394243");
        row.setName("ТЕСТ");
        row.setSurname("ТЕСТ");
        row.setPatronymic("ТЕСТ");
        row.setLnCode(t_ELN);
        row.setPrimaryFlag(true);//row.setPrimaryFlag("1");
        row.setDuplicateFlag(false);//row.setPrimaryFlag("1");
        row.setLnDate("2020-08-24");
        row.setLpuName("ГБУЗ ЦРБ №1");
        row.setLpuAddress("Г.НИЖНИЙ НОВГОРОД, УЛ. ГОРЬКОГО 117");
        row.setLpuOgrn("1021900520410");
        row.setBirthday("2020-08-24");
        row.setGender(1);
        row.setReason1("");
        row.setReason2("");
        row.setDiagnos("");
        row.setDate1(null);
        row.setDate2(null);
        row.setMseDt1(null);
        row.setMseDt2(null);
        row.setMseDt3(null);
        row.setMseInvalidGroup(null);
        row.setLnState("20");
        row.setLnHash("3BE67F22A716F7922E63E01838DD3409");
        row.setWrittenAgreementFlag(true);

        Rowset.Row.ServData.ServFullData servFullData = new Rowset.Row.ServData.ServFullData();
        servFullData.setServRelationCode("39");
        servFullData.setServDt1("2020-08-24");
        servFullData.setServDt2("2020-08-24");
        servFullData.setTreatmentType("0");
        servFullData.setName("ТЕСТ");
        servFullData.setSurname("ТЕСТ");
        servFullData.setPatronymic("ТЕСТ");
        servFullData.setBirthday("2020-08-24");
        servFullData.setReason1("09");
        servFullData.setSnils("12345394243");
        servFullData.setDiagnosis("A00.9");

        List<Rowset.Row.ServData.ServFullData> servFullDataList = new ArrayList<>();
        servFullDataList.add(servFullData);
        Rowset.Row.ServData servData = new Rowset.Row.ServData();
        servData.setServFullData(servFullDataList);

        row.setServData(servData);

        // Периоды
        TreatFullPeriodMo treatFullPeriodMo = new TreatFullPeriodMo();

        TreatFullPeriodMo.TreatPeriod treatPeriod = new TreatFullPeriodMo.TreatPeriod();
        treatPeriod.setTreatDoctor("ГАБЕЕВА ИЧ");
        treatPeriod.setTreatDoctorRole("ПЕДИАТР");
        treatPeriod.setTreatDt1("2020-08-20");
        treatPeriod.setTreatDt2("2020-08-20");
        treatFullPeriodMo.setTreatChairman("");
        treatFullPeriodMo.setTreatChairmanRole("");
        treatFullPeriodMo.setTreatPeriod(treatPeriod);

        List<TreatFullPeriodMo> fullPeriodMoList = new ArrayList<>();
        fullPeriodMoList.add(treatFullPeriodMo);

        Rowset.Row.TreatPeriods treatPeriods = new Rowset.Row.TreatPeriods();
        treatPeriods.setTreatFullPeriod(fullPeriodMoList);
        row.setTreatPeriods(treatPeriods);

        List<Rowset.Row> rowsList = new ArrayList<>();
        rowsList.add(row);
        Rowset rowset = new Rowset();
        rowset.setRow(rowsList);
        rowset.setAuthor("Test");
        rowset.setEmail("rashgild@gmail.com");
        rowset.setPhone("9999");
        rowset.setVersion("2.0");
        rowset.setSoftware("fss");
        rowset.setVersionSoftware("2.0");
        return rowset;
    }
}
