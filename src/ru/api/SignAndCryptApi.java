package ru.api;

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

import ru.my.entities.ROW;
import ru.my.entities.TREAT_FULL_PERIOD;
import ru.my.entities.TREAT_PERIOD;
import ru.my.helpers_operations.GlobalVariables;

import static ru.api.ApiUtils.cretePostRequest;
import static ru.api.ApiUtils.get;
import static ru.my.helpers_operations.GlobalVariables.setUp;
import static ru.my.helpers_operations.GlobalVariables.urlApi;

@Path("/sign")
public class SignAndCryptApi {

    @POST
    @Path("/getJSON")
    @Produces("text/html")
    public void getJSON(String data,
                        @Context HttpServletRequest req,
                        @Context HttpServletResponse response) throws IOException, ServletException, ParseException {

        ROW row = parseJson(data);
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
        int start = xml.indexOf(searchString) + searchString.length() + 1;
        StringBuilder sb = new StringBuilder();
        while (!search[start].equals("<")) {
            sb.append(search[start]);
            start++;
        }
        return sb.toString();
    }

    //Парс json -> возврат ROW (xml)
    private ROW parseJson(String json) {

        ROW row = new ROW();
        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();
        JsonArray treats = jparse.getAsJsonArray("data");

        for (JsonElement el : treats) {

            JsonObject jtreat = el.getAsJsonObject();
            String doctype = get(jtreat, "doctype");

            if (doctype.equals("close")) {

                String elncode = get(jtreat, "ddnum");
                String number = get(jtreat, "num");

                ROW.LN_RESULT ln_result = new ROW.LN_RESULT();
                ln_result.setAttribId("ELN_" + elncode + "_" + number + "_doc");
                ln_result.setMseresult(get(jtreat, "mse_result"));
                ln_result.setNextlncode(get(jtreat, "NEXT_LN_CODE"));
                ln_result.setOtherstatedt(get(jtreat, "other_state_dt"));

                ln_result.setReturndatelpu(get(jtreat, "returndt"));

                row.setDiagnos(get(jtreat, "ddid"));
                row.setLnhash(get(jtreat, "doctype"));

                System.out.println("getReturndatelpu>>>>>>" + ln_result.getReturndatelpu());
                String isClose = get(jtreat, "is_close");
                if (isClose.equals("1") && ln_result.getMseresult() != null) {
                    if (!ln_result.getMseresult().equals("31") && !ln_result.getMseresult().equals("37")) {

                        if (ln_result.getOtherstatedt() != null && !ln_result.getOtherstatedt().equals("")) {
                            ln_result.setReturndatelpu(null);
                        } else {
                          /* Calendar cal = Calendar.getInstance();
                           cal.setTime(format.parse(ln_result.getReturndatelpu()));
                           cal.add(Calendar.DAY_OF_MONTH, 1);
                           ln_result.setReturndatelpu(new java.sql.Date(cal.getTime().getTime()).toString());*/
                        }
                    } else { //Если закрыт по причине "продолжает болеть"
                        ln_result.setReturndatelpu(null);
                        ln_result.setNextlncode(get(jtreat, "next_ln_code"));
                    }

                } else if (!isClose.equals("1")) ln_result.setReturndatelpu(null);


                List<ROW.LN_RESULT> ln_results = new ArrayList<>();
                ln_results.add(ln_result);

                row.setBirthday(get(jtreat, "num"));
                row.setDiagnos(get(jtreat, "ddid"));
                row.setLnhash("doc");
                row.setLncode(elncode);
                row.setAttribId(number);
                row.setLnresult(ln_results);

            } else {
                String isexport = get(jtreat, "isexport");
                if (isexport.equals("t")) {
                    continue;
                }

                String elncode = get(jtreat, "ddnum");
                String number = get(jtreat, "num");

                TREAT_PERIOD treat_period = new TREAT_PERIOD();
                treat_period.setTreatdt1(get(jtreat, "treat_dt1"));
                treat_period.setTreatdt2(get(jtreat, "treat_dt2"));
                treat_period.setTreatdoctor(get(jtreat, "treat_doctor"));
                treat_period.setTreatdoctorrole(get(jtreat, "treat_doctor_role"));
                treat_period.setAttribId("ELN_" + elncode + "_" + number + "_doc");
                List<TREAT_PERIOD> treat_periods = new ArrayList<>();
                treat_periods.add(treat_period);

                TREAT_FULL_PERIOD treat_full_period = new TREAT_FULL_PERIOD();
                treat_full_period.setTreat_period(treat_periods);
                treat_full_period.setTreatchairman(get(jtreat, "treat_chairman"));
                treat_full_period.setTreatchairmanrole(get(jtreat, "treat_chairman_role"));
                if (treat_full_period.getTreatchairman() != null)
                    treat_full_period.setAttribIdVk("ELN_" + elncode + "_" + number + "_vk");

                List<TREAT_FULL_PERIOD> treat_full_periods = new ArrayList<>();
                treat_full_periods.add(treat_full_period);


                row.setBirthday(get(jtreat, "num"));
                row.setSnils(get(jtreat, "recordid"));
                row.setDiagnos(get(jtreat, "ddid"));
                row.setLncode(elncode);
                row.setAttribId(number);
                row.setLnhash(get(jtreat, "doctype"));
                row.setTREAT_PERIODS(treat_full_periods);
            }
        }
        return row;
    }

    private String createXML(ROW row) {

        setUp();
        String signThis = "";
        String number = row.getAttribId();
        String elnNumber = row.getLncode();
        String type = row.getLnhash();

        try {
            Document document = GlobalVariables.parser.ObjToSoap(row);
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

    private static String xmlTemplate = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:fil=\"http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
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
