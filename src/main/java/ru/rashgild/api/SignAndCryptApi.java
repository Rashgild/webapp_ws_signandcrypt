package ru.rashgild.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import ru.rashgild.generated.v2.types.eln.mo.v01.Rowset;
import ru.rashgild.generated.v2.types.eln.v01.TreatFullPeriodMo;
import ru.rashgild.utils.GlobalVariables;

import static ru.rashgild.api.ApiUtils.cretePostRequest;
import static ru.rashgild.api.ApiUtils.get;
import static ru.rashgild.utils.GlobalVariables.setUp;
import static ru.rashgild.utils.GlobalVariables.urlApi;

@Path("/sign")
public class SignAndCryptApi {

    @POST
    @Path("/getJSON")
    @Produces("text/html")
    public void getJSON(String data,
                        @Context HttpServletRequest req,
                        @Context HttpServletResponse response) throws IOException, ServletException, ParseException {

        Rowset.Row row = parseJson(data);
        String xml = createXML(row);

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,origin, content-type, accept, authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("xml", xml);
        req.getServletContext().getRequestDispatcher("/webSignForm.jsp").forward(req, response);
    }

    @POST
    @Path("/getXML")
    @Produces("application/json;charset=UTF-8")
    public String getXML(String data,
                         @Context HttpServletRequest req,
                         @Context HttpServletResponse response) throws JSONException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        String xml = data;

        JSONObject jsonObject = new JSONObject();
        String counter = parseXML(xml, "<fil:BIRTHDAY>");
        if (counter.equals("2")) {
            jsonObject
                    .put("Code", "close");

        } else {
            jsonObject
                    .put("Code", parseXML(xml, "<fil:LN_HASH>"))
                    .put("AnotherId", parseXML(xml, "<fil:SNILS>"));
        }

        jsonObject
                .put("Certificate", parseXML(xml, "<X509Certificate>"))
                .put("SignatureValue", parseXML(xml, "<SignatureValue>"))
                .put("DigestValue", parseXML(xml, "<DigestValue>"))
                .put("ELN", parseXML(xml, "<fil:LN_CODE>"))
                .put("Counter", parseXML(xml, "<fil:BIRTHDAY>"))
                .put("DisabilityId", parseXML(xml, "<fil:DIAGNOS>"))
                .put("SignatureType", parseXML(xml, "SignatureMethod Algorithm=").contains("gostr34102012")
                        ? "gostr34102012"
                        : "gostr34102001");

        cretePostRequest(urlApi + "riams", "api/disabilitySign/getJson", jsonObject.toString());
        return jsonObject.toString();
    }

    private String parseXML(String xml, String searchString) {
        String[] search = xml.split("");
        int start = xml.indexOf(searchString) + searchString.length() ;
        StringBuilder sb = new StringBuilder();
        while (!search[start].equals("<")) {
            sb.append(search[start]);
            start++;
        }
        return sb.toString();
    }

    //Парс json -> возврат ROW (xml)
    private Rowset.Row parseJson(String json) {

        Rowset.Row row = new Rowset.Row();
        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();
        JsonArray treats = jparse.getAsJsonArray("data");

        for (JsonElement el : treats) {

            JsonObject jtreat = el.getAsJsonObject();
            String doctype = get(jtreat, "doctype");

            if (doctype.equals("close")) {

                String elncode = get(jtreat, "ddnum");
                String number = get(jtreat, "num");

                Rowset.Row.LnResult lnResult = new Rowset.Row.LnResult();
                lnResult.setId("ELN_" + elncode + "_" + number + "_doc");
                lnResult.setMseResult(get(jtreat, "mse_result"));
                lnResult.setNextLnCode(get(jtreat, "NEXT_LN_CODE"));
                lnResult.setOtherStateDt(get(jtreat, "other_state_dt"));

                lnResult.setReturnDateLpu(get(jtreat, "returndt"));

                row.setDiagnos(get(jtreat, "ddid"));
                row.setLnHash(get(jtreat, "doctype"));

                System.out.println("getReturndatelpu>>>>>>" + lnResult.getReturnDateLpu());
                String isClose = get(jtreat, "is_close");
                if (isClose.equals("1") && lnResult.getMseResult() != null) {
                    if (!lnResult.getMseResult().equals("31") && !lnResult.getMseResult().equals("37")) {

                        if (lnResult.getOtherStateDt() != null && !lnResult.getOtherStateDt().equals("")) {
                            lnResult.setReturnDateLpu(null);
                        } else {
                          /* Calendar cal = Calendar.getInstance();
                           cal.setTime(format.parse(ln_result.getReturndatelpu()));
                           cal.add(Calendar.DAY_OF_MONTH, 1);
                           ln_result.setReturndatelpu(new java.sql.Date(cal.getTime().getTime()).toString());*/
                        }
                    } else { //Если закрыт по причине "продолжает болеть"
                        lnResult.setReturnDateLpu(null);
                        lnResult.setNextLnCode(get(jtreat, "next_ln_code"));
                    }

                } else if (!isClose.equals("1")) lnResult.setReturnDateLpu(null);


                /*List<Rowset.Row.LnResult> lnResultList = new ArrayList<>();
                lnResultList.add(lnResult);*/

                row.setLnResult(lnResult);
                row.setBirthday(get(jtreat, "num"));
                row.setDiagnos(get(jtreat, "ddid"));
                row.setLnHash("doc");
                row.setLnCode(elncode);
                row.setId(number);


            } else {
                String isExport = get(jtreat, "isexport");
                if (isExport.equals("t")) {
                    continue;
                }

                String elncode = get(jtreat, "ddnum");
                String number = get(jtreat, "num");

                TreatFullPeriodMo.TreatPeriod treatPeriod = new TreatFullPeriodMo.TreatPeriod();
                //TREAT_PERIOD treat_period = new TREAT_PERIOD();
                treatPeriod.setTreatDt1(get(jtreat, "treat_dt1"));
                treatPeriod.setTreatDt2(get(jtreat, "treat_dt2"));
                treatPeriod.setTreatDoctor(get(jtreat, "treat_doctor"));
                treatPeriod.setTreatDoctorRole(get(jtreat, "treat_doctor_role"));
                treatPeriod.setId("ELN_" + elncode + "_" + number + "_doc");
                /*List<TreatFullPeriodMo.TreatPeriod> treat_periods = new ArrayList<>();
                treat_periods.add(treatPeriod);*/

                TreatFullPeriodMo treatFullPeriodMo = new TreatFullPeriodMo();
                //TREAT_FULL_PERIOD treat_full_period = new TREAT_FULL_PERIOD();
                treatFullPeriodMo.setTreatPeriod(treatPeriod);
                treatFullPeriodMo.setTreatChairman(get(jtreat, "treat_chairman"));
                treatFullPeriodMo.setTreatChairmanRole(get(jtreat, "treat_chairman_role"));
                if (treatFullPeriodMo.getTreatChairmanRole() != null) {
                    treatFullPeriodMo.setId("ELN_" + elncode + "_" + number + "_vk");
                }

                List<TreatFullPeriodMo> treatFullPeriodMoList = new ArrayList<>();
                treatFullPeriodMoList.add(treatFullPeriodMo);

                Rowset.Row.TreatPeriods treatPeriods = new Rowset.Row.TreatPeriods();
                treatPeriods.setTreatFullPeriod(treatFullPeriodMoList);

                row.setBirthday(get(jtreat, "num"));
                row.setSnils(get(jtreat, "recordid"));
                row.setDiagnos(get(jtreat, "ddid"));
                row.setLnCode(elncode);
                row.setId(number);
                row.setLnHash(get(jtreat, "doctype"));
                row.setTreatPeriods(treatPeriods);
            }
        }
        return row;
    }

    private String createXML(Rowset.Row row) {

        setUp();
        String signThis = "";
        String number = row.getId();
        String elnNumber = row.getLnCode();
        String type = row.getLnHash();

        try {
            Document document = GlobalVariables.parser.objToSoap(row);
            DOMSource domSource = new DOMSource(document);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);

            String xml = writer.toString();
            xml = xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");


            //System.out.println(">>>>"+type);
            signThis = xmlTemplate.replace("<replace>", xml);
            signThis = signThis.replace("<someAdd1>", "doc/" + "ELN_" + elnNumber + "_" + number + "_" + type); // doc/ELN_306742020070_3_doc
            signThis = signThis.replace("<someAdd2>", "ELN_" + elnNumber + "_" + number + "_" + type); // ELN_306742020070_3_doc
            signThis = signThis.replace("<someAdd3>", "ELN_" + elnNumber); // ELN_306742020070
            //System.out.println(signThis);

        } catch (JAXBException | ParserConfigurationException | IOException | TransformerException e) {
            e.printStackTrace();
        }

        return signThis;
    }

    private static String xmlTemplate = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:fil=\"http://www.fss.ru/integration/types/eln/mo/v01\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
            "<soapenv:Header>\n" +
            "<wsse:Security soapenv:actor=\"http://eln.fss.ru/actor/<someAdd1>\"\n" + // doc/ELN_306742020070_3_doc
            "xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">\n" +
            "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">\n" +
            "<SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411\"/><Reference URI=\"#<someAdd2>\"><Transforms><Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr3411\"/><DigestValue/></Reference></SignedInfo>\n" +
            "<SignatureValue/>\n" +
            "<KeyInfo/>\n" +
            "</Signature>\n" +
            "<wse:BinarySecurityToken xmlns:wse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\"\n" +
            "EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary \"\n" +
            "ValueType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3\" \n" +
            "wsu:Id=\"http://eln.fss.ru/actor/mo/1053000627690/<someAdd3>\"/>\n" +
            "</wsse:Security>\n" +
            "</soapenv:Header>\n" +
            "<soapenv:Body>\n" +
            "<replace>" +
            "</soapenv:Body>\n" +
            "</soapenv:Envelope>";
}
