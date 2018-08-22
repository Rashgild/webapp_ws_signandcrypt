package ru.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.CryptoPro.JCPxml.xmldsig.JCPXMLDSigInit;
import ru.my.entities.*;
import ru.my.helpers_operations.GlobalVariables;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static ru.my.helpers_operations.GlobalVariables.*;
import static ru.my.helpers_operations.SQL.select;
import static ru.my.helpers_operations.WorkWithXML.SoapMessageToString;
import static ru.my.service_operations.xmlFileLnLpu.PrParseFileLnLpu_start.CreateSoapMessage;
import static ru.my.service_operations.xmlFileLnLpu.PrParseFileLnLpu_start.calculateAge;

/**
 * Created by rkurbanov on 10.04.2018.
 */
@Path("/jsongen")
public class JSONGen {

   /* @GET
    @Path("/{FileUID}")
    public Response getUsr(@PathParam("FileUID") String FileUID) throws IOException {
        String filepath ="C:\\tmp\\";
        File downloadFile = new File(filepath+FileUID+".jpg");
        return Response.ok(downloadFile, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + downloadFile.getName() + "\"" ) //optional
                .build();
    }*/

/*    @POST
    @Path("/getJSON")
    @Produces("text/html")
    //@Produces(MediaType.APPLICATION_JSON)
    public void getJSON(String data,@Context HttpServletRequest req, @Context HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("arr"+data);

        ROW row = parseJson(data);
        String xml = createXML(row,"doc");

        resp.setHeader("Access-Control-Allow-Origin", "*");
        //resp.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("test", xml);
        req.getServletContext().getRequestDispatcher("/webSignTest.jsp").forward(req, resp);
        //return data;
    }

    private ROW parseJson(String json){

        ROW row = new ROW();
        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();
        JsonArray treats = jparse.getAsJsonArray("data");
        for (JsonElement el : treats) {
            JsonObject jtreat = el.getAsJsonObject();
            String isexport = get(jtreat,"isexport");
            if(isexport.equals("t")){
                continue;
            }

            String elncode = get(jtreat,"ddnum");
            String number = get(jtreat,"num");

            TREAT_PERIOD treat_period = new TREAT_PERIOD();
            treat_period.setTreatdt1(get(jtreat,"treat_dt1"));
            treat_period.setTreatdt2(get(jtreat,"treat_dt2"));
            treat_period.setTreatdoctor(get(jtreat,"treat_doctor"));
            treat_period.setTreatdoctorrole(get(jtreat,"treat_doctor_role"));
            treat_period.setAttribId("ELN_"+elncode+"_"+number+"_doc");
            List<TREAT_PERIOD> treat_periods = new ArrayList<>();
            treat_periods.add(treat_period);

            TREAT_FULL_PERIOD treat_full_period = new TREAT_FULL_PERIOD();
            treat_full_period.setTreat_period(treat_periods);
            treat_full_period.setTreatchairman(get(jtreat,"treat_chairman"));
            treat_full_period.setTreatchairmanrole(get(jtreat,"treat_chairman_role"));
            if(treat_full_period.getTreatchairman()!=null) treat_full_period.setAttribIdVk("ELN_"+elncode+"_"+number+"_vk");

            List<TREAT_FULL_PERIOD> treat_full_periods = new ArrayList<>();
            treat_full_periods.add(treat_full_period);

            row.setLncode(elncode);
            row.setAttribId(number);
            row.setTREAT_PERIODS(treat_full_periods);
        }
        return row;
    }

    private String createXML(ROW row,String type){

        setUp();
        String signThis = "";
        String number = row.getAttribId();
        String elnNumber = "ELN_"+row.getLncode();
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

            signThis = xmlTemplate.replace("<replace>", xml);
            signThis = signThis.replace("<someAdd1>", type+"/" + elnNumber + "_" + number + "_"+type); // doc/ELN_306742020070_3_doc
            signThis = signThis.replace("<someAdd2>", elnNumber + "_" + number + "_"+type); // ELN_306742020070_3_doc
            signThis = signThis.replace("<someAdd3>", elnNumber); // ELN_306742020070
            System.out.println(signThis);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return signThis;
    }

    static String xmlTemplate = "" +
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:fil=\"http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
            "<soapenv:Header>\n" +
            "<wsse:Security soapenv:actor=\"http://eln.fss.ru/actor/<someAdd1>\"\n" + // doc/ELN_306742020070_3_doc
            "xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">\n" +
            "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">\n" +
            "<SignedInfo>\n" +
            "<CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>\n" +
            "<SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411\"/>\n" +
            "<Reference URI=\"#<someAdd2>\">\n" + //ELN_306742020070_3_doc
            "<Transforms>\n" +
            "<Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/>\n" +
            "</Transforms>\n" +
            "<DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr3411\"/>\n" +
            "<DigestValue/>\n" +
            "</Reference>\n" +
            "</SignedInfo>\n" +
            "<SignatureValue/>\n" +
            "<KeyInfo/>\n" +
            "</Signature>\n" +
            "<wse:BinarySecurityToken xmlns:wse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\"\n" +
            "EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary \"\n" +
            "ValueType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3\" \n" +
            "wsu:Id=\"http://eln.fss.ru/actor/mo/1053000627690/<someAdd3>\"/>\n" + //ELN_306742020070
            "</wsse:Security>\n" +
            "</soapenv:Header>\n" +
            "<soapenv:Body>\n" +
            "<replace>" +
            "</soapenv:Body>\n" +
            "</soapenv:Envelope>";*/


   /* @GET
    @Path("/sendParams")
    @Produces("text/html")
    public void sendParams(@Context HttpServletRequest req, @Context HttpServletResponse resp) throws IOException, ServletException {
        //System.out.println(data);

        String json = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" " +
                "xmlns:fil=\"http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" " +
                "xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" " +
                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
                "<soapenv:Header>" +
                "<wsse:Security soapenv:actor=\"http://eln.fss.ru/actor/doc/ELN_306742020070_3_doc\" " + //DOC * MO *//*
                "xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">" +
                "  <Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">" +
                "  <SignedInfo>" +
                "      <CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>" +
                "      <SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411\"/>" +
                "      <Reference URI=\"#ELN_306742020070_3_doc\">" + //REF
                "      <Transforms>" +
                "          <Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/>" +
                "      </Transforms>" +
                "      <DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr3411\"/>" +
                "      <DigestValue/>" +
                "      </Reference>" +
                "  </SignedInfo>" +
                "  <SignatureValue/>" +
                "  <KeyInfo/>" +
                "  </Signature>" +
                "<wse:BinarySecurityToken xmlns:wse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" " +
                "EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary\" " +
                "ValueType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3\" " +
                "wsu:Id=\"http://eln.fss.ru/actor/mo/1053000627690/ELN_306742020070\"/>" + //REF WSUID
                "</wsse:Security>" +
                "</soapenv:Header>" +
                "<soapenv:Body><fil:prParseFilelnlpu fil:xmlns=\"http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl\" xmlns:fil=\"http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\"><fil:request><fil:ogrn>1053000627690</fil:ogrn><fil:pXmlFile><fil:ROWSET fil:author=\"R.Kurbanov\" fil:email=\"Rashgild@gmail.com\" fil:phone=\"89608634440\" fil:software=\"SignAndcypt\" fil:version=\"1.0\" fil:version_software=\"2.0\"><fil:ROW wsu:Id=\"ELN_306742020070\" wsu:ddid=\"81627\"><fil:SNILS>03731405829</fil:SNILS><fil:SURNAME>ИВАНОВ</fil:SURNAME><fil:NAME>ИВАН</fil:NAME><fil:PATRONIMIC>АКИМОВИЧ</fil:PATRONIMIC><fil:BOZ_FLAG>0</fil:BOZ_FLAG><fil:LPU_EMPLOYER>ДЖОБС</fil:LPU_EMPLOYER><fil:LPU_EMPL_FLAG>1</fil:LPU_EMPL_FLAG><fil:LN_CODE>306742020070</fil:LN_CODE><fil:PRIMARY_FLAG>1</fil:PRIMARY_FLAG><fil:DUPLICATE_FLAG>0</fil:DUPLICATE_FLAG><fil:LN_DATE>2018-04-09</fil:LN_DATE><fil:LPU_NAME>ГБУЗ АО \"АЛЕКСАНДРО-МАРИИНСКАЯ ОБЛАСТНАЯ КЛИНИЧЕСКАЯ БОЛЬНИЦА \"</fil:LPU_NAME><fil:LPU_ADDRESS>АСТРАХАНЬ ТАТИЩЕВА 2</fil:LPU_ADDRESS><fil:LPU_OGRN>1053000627690</fil:LPU_OGRN><fil:BIRTHDAY>1940-07-07</fil:BIRTHDAY><fil:GENDER>0</fil:GENDER><fil:REASON1>01</fil:REASON1><fil:PARENT_CODE/><fil:VOUCHER_NO/><fil:VOUCHER_OGRN/><fil:PREGN12W_FLAG>null</fil:PREGN12W_FLAG><fil:HOSPITAL_BREACH/><fil:TREAT_PERIODS><fil:TREAT_FULL_PERIOD wsu:Id=\"ELN_306742020070_3_vk\"><fil:TREAT_CHAIRMAN_ROLE>Врач-колопроктолог</fil:TREAT_CHAIRMAN_ROLE><fil:TREAT_CHAIRMAN>ТИТОВА ЮЛИЯ ПАВЛОВНА</fil:TREAT_CHAIRMAN><fil:TREAT_PERIOD wsu:Id=\"ELN_306742020070_3_doc\"><fil:TREAT_DT1>2018-04-05</fil:TREAT_DT1><fil:TREAT_DT2>2018-04-09</fil:TREAT_DT2><fil:TREAT_DOCTOR_ROLE>Врач-колопроктолог</fil:TREAT_DOCTOR_ROLE><fil:TREAT_DOCTOR>ТИТОВА ЮЛИЯ ПАВЛОВНА</fil:TREAT_DOCTOR></fil:TREAT_PERIOD><fil:Export>false</fil:Export></fil:TREAT_FULL_PERIOD></fil:TREAT_PERIODS><fil:LN_RESULT><fil:MSE_RESULT/></fil:LN_RESULT><fil:LN_STATE>1</fil:LN_STATE></fil:ROW></fil:ROWSET></fil:pXmlFile></fil:request></fil:prParseFilelnlpu></soapenv:Body>" +
                "</soapenv:Envelope>";

        resp.setHeader("Access-Control-Allow-Origin", "*");
        //resp.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("test", json);
        req.getServletContext().getRequestDispatcher("/test.jsp").forward(req, resp);
    }*/

    /**
     * @param SQL
     * @return
     * SQL="select num1,num2 from numbers" was returning list of lists with num1 and num2
     */
    public static List<List<String>> getResul2t(String SQL){
        try {
            ResultSet resultSet = select(SQL);
            int columnCount = resultSet.getMetaData().getColumnCount();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            /* for (int j = 1; j <= columnCount; j++) {
                headers.add(rsmd.getColumnName(j));
            }*/
            List<String> list = new ArrayList<>();
            List<List<String>> lists = new ArrayList<>();
            while (resultSet.next()) {
                list = new ArrayList<>();
                for (int j = 1; j <= columnCount; j++) {
                    String s = resultSet.getString(j);
                    list.add(s);
                }
                lists.add(list);
            }
            return lists;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }



    /**
     * @param SQL-request
     * @return
     * SQL="select num1 as Num1,num2 as Num2 from numbers" was returning json
     */
    public static JSONArray getResultJsonArray(String SQL){
        try {
            ResultSet resultSet = select(SQL);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            JSONArray array = new JSONArray();
            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                for (int j = 1; j <= resultSet.getMetaData().getColumnCount(); j++) {
                    jsonObject.put(rsmd.getColumnName(j),resultSet.getString(j));
                }
                array.put(jsonObject);
            }
            return array;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    /**
     * @param SQL-request
     * @return
     * SQL="select num1 as Num1,num2 as Num2 from numbers" was returning json
     */
    public static JSONObject getResultJsonObj(String SQL){
        try {

            ResultSet resultSet = select(SQL);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            JSONObject jsonObject = new JSONObject();
            while (resultSet.next()) {
                for (int j = 1; j <= resultSet.getMetaData().getColumnCount(); j++) {
                    jsonObject.put(rsmd.getColumnName(j),resultSet.getString(j));
                }
            }
            return jsonObject;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    @GET
    @Path("/postjson")
    @Produces("application/json;charset=UTF-8")
    public String createJson(@QueryParam("disabilityId") String disabilityId) throws JSONException {

        String default_lpu="0";
        String sqlDefLPU= "select s.keyvalue from softconfig s where s.key='DEFAULT_LPU'";

        ResultSet resultSet = select(sqlDefLPU);
        try {
            while (resultSet.next()) {
                default_lpu= resultSet.getString("keyvalue");
                System.out.println(default_lpu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

             String sql1 ="select\n" +
                    "dd.issuedate as ISSUEDATE,\n" +
                    "dd.id as DDID,\n" +
                    "dd.patient_id as DD_PAT,\n" +
                    "dc.patient_id as DC_PAT,\n" +
                    "p.snils as SNILS,\n" +
                    "p.lastname as SURNAME,\n" +
                    "p.firstname as NAME,\n" +
                    "p.middlename as PATRONIMIC\n" + //***
                    ",case when (dc.placementservice is not null or dc.placementservice ='1') then '1' else '0' end as BOZ_FLAG\n" +
                    ",dd.job as LPU_EMPLOYER\n" +
                    ",case when (dd.workcombotype_id is null) then '1' else '0' end as LPU_EMPL_FLAG\n" +
                    ",dd.number as LN_CODE\n" +
                    ",case when dd.pervelnnumber is not null then dd.pervelnnumber else (select dd2.number from disabilitydocument dd2 where dd2.id = dd.prevdocument_id) end as PREV_LN\n" +
                    ",case when (vddp.code ='2') then '0' else '1' end as PRIMARY_FLAG\n" +
                    ",case when dd.elnduplicate is not null or dd.elnduplicate = '1' then '1' else case when (select count(a.id) from disabilitydocument a where a.duplicate_id=dd.id) >0 then '1' else '0'end  end as DUPLICATE_FLAG\n" +
                    ",dd.issuedate as LN_DATE\n" +
                    ",case when dd.anotherlpu_id is not null then dd.anotherlpuname else lpu.name end as LPU_NAME\n" +
                    ",case when dd.anotherlpu_id is not null then dd.anotherlpuaddress else lpu.printaddress end as LPU_ADDRESS\n" +
                    ",case when dd.anotherlpu_id is not null then dd.anotherlpuogrn else ''||lpu.ogrn end as LPU_OGRN\n" +
                    ",p.birthday as BIRTHDAY\n" +
                    ",case when sex.omccode = '1' then '0' else '1' end as GENDER\n" +
                    ",vdr.codef as REASON1\n" +
                    ",vdr2.code as REASON2\n" +
                    ",vdr3.code as REASON3\n" +
                    ",mkb.code as DIAGNOS\n" +
                    ",dd.mainworkdocumentnumber as PARENT_CODE\n" +
                    ",dd.sanatoriumdatefrom as DATE1\n" +
                    ",dd.sanatoriumdateto as DATE2\n" +
                    ",dd.sanatoriumticketnumber as VOUCHER_NO\n" +
                    ",dd.sanatoriumogrn as VOUCHER_OGRN\n" +
                    ",case when p1.id is not null and p1.id!=p.id then to_char(p1.birthday,'yyyy-MM-dd') else to_char(p12.birthday,'yyyy-MM-dd') end as SERV1_AGE\n" +
                    ",case when p1.id is not null and p1.id!=p.id then vkr1.code else vkr1.oppositeRoleCode end as SERV1_RELATION_CODE\n" +
                    ",case when p1.id is not null and p1.id!=p.id then p1.lastname||' '||p1.firstname||' '||p1.middlename else p12.lastname||' '||p12.firstname||' '||p12.middlename end as SERV1_FIO\n" +
                    ",case when p2.id is not null and p2.id!=p.id then to_char(p2.birthday,'yyyy-MM-dd') else to_char(p22.birthday,'yyyy-MM-dd') end as SERV2_AGE\n" +
                    ",case when p2.id is not null and p2.id!=p.id then vkr2.code else vkr2.oppositeRoleCode end as SERV2_RELATION_CODE\n" +
                    ",case when p2.id is not null and p2.id!=p.id then p2.lastname||' '||p2.firstname||' '||p2.middlename else p22.lastname||' '||p22.firstname||' '||p22.middlename end as SERV2_FIO\n" +
                    ",case when dc.earlypregnancyregistration is null then 'null' else case when dc.earlypregnancyregistration = '0' then '0' else '1' end end as PREGN12W_FLAG\n" +
                    ",dd.hospitalizedfrom as HOSPITAL_DT1\n" +
                    ",dd.hospitalizedto as HOSPITAL_DT2\n" +
                    ",vddcr.name as CLOSE_REASON\n" +
                    ",mss.assignmentdate as MSE_DT1\n" +
                    ",mss.registrationdate as MSE_DT2\n" +
                    ",mss.examinationdate as MSE_DT3\n" +
                    ",vi.code as MSE_INVALID_GROUP\n" +
                    ",dd.status_id as LN_STATE\n" +
                    ",rvr.datefrom as HOSPITAL_BREACH_DT\n" +
                    ",vrvr.codef as HOSPITAL_BREACH_CODE\n" +
                    ",coalesce(vddcr.codef,'') as MSE_RESULT\n" +
                    ",dd.otherclosedate as other_state_dt\n" +
                    ",dd3.number as NEXT_LN_CODE\n" +
                    ",Case when dd.isClose = '1' then '1' else '0' end as IS_CLOSE\n" +
                    "from disabilitydocument dd\n" +
                    "left join vocdisabilitydocumentclosereason vddcr on vddcr.id = dd.closereason_id\n" +
                    "left join disabilitydocument dd3 on dd3.prevdocument_id=dd.id\n" +
                    "left join regimeviolationrecord rvr on rvr.disabilitydocument_id = dd.id\n" +
                    "left join vocregimeviolationtype vrvr on vrvr.id = rvr.regimeviolationtype_id\n" +
                    "left join disabilitycase dc on dc.id=dd.disabilitycase_id\n" +
                    "left join patient p on p.id=dc.patient_id\n" +
                    "left join vocdisabilitydocumentprimarity vddp on vddp.id=dd.primarity_id\n" +
                    "left join vocsex sex on sex.id=p.sex_id\n" +
                    "left join vocdisabilityreason vdr on vdr.id=dd.disabilityreason_id\n" +
                    "left join vocdisabilityreason2 vdr2 on vdr2.id=dd.disabilityreason2_id\n" +
                    "left join vocdisabilityreason vdr3 on vdr3.id=dd.disabilityreasonchange_id\n" +
                    "left join vocidc10 mkb on mkb.id=dd.idc10final_id\n" +
                    "left join kinsman k1 on k1.id=dc.nursingperson1_id\n" +
                    "left join vockinsmanrole vkr1 on vkr1.id=k1.kinsmanrole_id\n" +
                    "left join patient p1 on p1.id=k1.kinsman_id\n" +
                    "left join patient p12 on p12.id=k1.person_id\n" +
                    "left join kinsman k2 on k2.id=dc.nursingperson2_id\n" +
                    "left join vockinsmanrole vkr2 on vkr2.id=k2.kinsmanrole_id\n" +
                    "left join patient p2 on p2.id=k2.kinsman_id\n" +
                    "left join patient p22 on p22.id=k2.person_id\n" +
                    "left join medsoccommission mss on mss.disabilitydocument_id=dd.id\n" +
                    "left join vocinvalidity vi on vi.id=mss.invalidity_id\n" +
                    "left join mislpu lpu on lpu.id="+default_lpu+
                    "left join mislpu anlpu on anlpu.id = dd.anotherlpu_id\n" +
                    "where\n" +
                    "p.snils is not null and p.snils != ''\n" +
                    "and dd.id ="+disabilityId;

            String sql2 = "select\n" +
                    "dd.id as DDID,    \n" +
                    "p.lastname as SURNAME,\n" +
                    "p.firstname as NAME,  \n" +
                    "p.middlename as PATRONIMIC\n" +
                    ",disrec.datefrom as TREAT_DT1 \n" +
                    ",disrec.dateto as TREAT_DT2\n" +
                    ",case when disrec.docrole is null then vwf.name else disrec.docrole end as TREAT_DOCTOR_ROLE\n" +
                    ",case when disrec.docname is null then docname.lastname ||' '|| docname.firstname ||' '|| docname.middlename else disrec.docname end as TREAT_DOCTOR \n" +
                    ",case when disrec.vkrole is null then vwf2.name else disrec.vkrole end as TREAT_CHAIRMAN_ROLE\n" +
                    ",case when disrec.vkname is null then vkname.lastname ||' '|| vkname.firstname ||' '|| vkname.middlename else disrec.vkname end as TREAT_CHAIRMAN\n" +
                    ",disrec.isexport as isexport\n" +
                    "from disabilitydocument dd\n" +
                    "left join disabilitycase dc on dc.id=dd.disabilitycase_id \n" +
                    "left join patient p on p.id=dc.patient_id left join disabilityrecord disrec on disrec.disabilitydocument_id = dd.id\n" +
                    "left join workfunction wf on wf.id = disrec.workfunction_id \n" +
                    "left join worker w on w.id = wf.worker_id\n" +
                    "left join patient docname on docname.id = w.person_id \n" +
                    "left join VocWorkFunction vwf on vwf.id = wf.workFunction_id\n" +
                    "left join workfunction wf2 on wf2.id = disrec.workfunctionadd_id\n" +
                    "left join worker w2 on w2.id = wf2.worker_id\n" +
                    "left join patient vkname on vkname.id = w2.person_id\n" +
                    "left join VocWorkFunction vwf2 on vwf2.id = wf2.workFunction_id\n" +
                    "where dd.id = "+disabilityId+"\n" +
                    "order by treat_dt1 asc \n";

            JSONObject body = getResultJsonObj(sql1);
            body.put("treats",getResultJsonArray(sql2));

        return body.toString();
    }


 /*   @GET
    @Path("/html")
    //@Produces(MediaType.TEXT_HTML)
    @Produces("text/html")
    public String getAllEmployee() {
        String html = "<HTML> <body> blah blah </body> </html>";
        return html;
    }*/

/*
    @GET
    @Path("/test")
    @Produces("text/html")
    public void//HttpServletRequest
    test(@Context HttpServletRequest req, @Context HttpServletResponse resp) throws IOException, ServletException {
        //System.out.println(data);
        resp.setHeader("Access-Control-Allow-Origin", "*");
        //resp.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("test", "321123");
        req.getServletContext().getRequestDispatcher("/webSignTest.jsp").forward(req, resp);
    }
*/

    @GET
    @Path("/getJson")
    //@Produces("application/json;charset=UTF-8")
    //@Produces({"application/xml", "application/json"})
    @Produces( "application/xml" )
    public String getJson() throws JSONException, ParseException, JAXBException, IOException, SOAPException {

        PrParseFileLnLpu prParseFilelnlpu = null;//parseJson();
        SOAPMessage message =null;
        try {
           /* setUp();
            //GlobalVariables.parser.saveObject(GlobalVariables.file, prParseFilelnlpu);
            Document document= GlobalVariables.parser.ObjToSoap(prParseFilelnlpu);
            MessageFactory mf = MessageFactory.newInstance();
            message = mf.createMessage();

            s = SoapMessageToString(message);
            s = s.replace("SOAP-ENV","soapenv");
            InputStream is = new ByteArrayInputStream(s.getBytes());
            SOAPMessage request = MessageFactory.newInstance().createMessage(null, is);
            SOAPEnvelope soapEnv = request.getSOAPPart().getEnvelope();
            SOAPBody soapBody = soapEnv.getBody();
            soapBody.addDocument(document);
            soapEnv.addNamespaceDeclaration("ds","http://www.w3.org/2000/09/xmldsig#");
            soapEnv.addNamespaceDeclaration("wsse","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
            soapEnv.addNamespaceDeclaration("wsu","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
            soapEnv.addNamespaceDeclaration("xsd","http://www.w3.org/2001/XMLSchema");
            soapEnv.addNamespaceDeclaration("xsi","http://www.w3.org/2001/XMLSchema-instance");
            soapEnv.addNamespaceDeclaration("fil","http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl");


            JCPXMLDSigInit.init();
            Canonicalizer canon = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            request.writeTo(out);
            byte canonXmlBytes[] = canon.canonicalize(out.toByteArray());
            String canonXmlString = new String(canonXmlBytes);

            is = new ByteArrayInputStream(canonXmlString.getBytes());
            request = MessageFactory.newInstance().createMessage(null, is);
            System.out.println(canonXmlString);*/


            message = CreateSoapMessage(prParseFilelnlpu);
            //message.writeTo(System.out);


            JCPXMLDSigInit.init();
            Canonicalizer canon = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            message.writeTo(out);
            byte canonXmlBytes[] = canon.canonicalize(out.toByteArray());
            String canonXmlString = new String(canonXmlBytes);

            System.out.println(canonXmlString);



       return  SoapMessageToString(message);

           // return   CreateSoapMessage(prParseFilelnlpu);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (CanonicalizationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (InvalidCanonicalizerException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*String json = "{\n" +
            "  \"birthday\": \"1966-03-24\",\n" +
            "  \"reason1\": \"01\",\n" +
            "  \"ln_date\": \"2018-01-05\",\n" +
            "  \"boz_flag\": \"0\",\n" +
            "  \"surname\": \"СЕРЕЧЕНКО\",\n" +
            "  \"lpu_name\": \"ГБУЗ АО \\\"АЛЕКСАНДРО-МАРИИНСКАЯ ОБЛАСТНАЯ КЛИНИЧЕСКАЯ БОЛЬНИЦА \\\"\",\n" +
            "  \"lpu_employer\": \"ООО ЧОО ФОРТ ЮГ\",\n" +
            "  \"treats\": [\n" +
            "    {\n" +
            "      \"treat_doctor\": \"ОСТАПЕНКО ТАТЬЯНА АНАТОЛЬЕВНА\",\n" +
            "      \"patronimic\": \"ФЕДОРОВИЧ\",\n" +
            "      \"treat_chairman\": \"ТИТОВА ЮЛИЯ ПАВЛОВНА\",\n" +
            "      \"treat_chairman_role\": \"Врач-колопроктолог\",\n" +
            "      \"name\": \"АНДРЕЙ\",\n" +
            "      \"ddid\": \"80033\",\n" +
            "      \"treat_dt1\": \"2017-12-24\",\n" +
            "      \"treat_dt2\": \"2018-01-05\",\n" +
            "      \"surname\": \"СЕРЕЧЕНКО\",\n" +
            "      \"treat_doctor_role\": \"Врач-невролог\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"treat_doctor\": \"ОСТАПЕНКО ТАТЬЯНА АНАТОЛЬЕВНА\",\n" +
            "      \"patronimic\": \"ФЕДОРОВИЧ\",\n" +
            "      \"name\": \"АНДРЕЙ\",\n" +
            "      \"ddid\": \"80033\",\n" +
            "      \"treat_dt1\": \"2018-01-06\",\n" +
            "      \"treat_dt2\": \"2018-01-06\",\n" +
            "      \"surname\": \"СЕРЕЧЕНКО\",\n" +
            "      \"treat_doctor_role\": \"Врач-невролог\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"is_close\": \"0\",\n" +
            "  \"primary_flag\": \"1\",\n" +
            "  \"name\": \"АНДРЕЙ\",\n" +
            "  \"voucher_no\": \"\",\n" +
            "  \"gender\": \"0\",\n" +
            "  \"ln_state\": \"1\",\n" +
            "  \"lpu_ogrn\": \"1053000627690\",\n" +
            "  \"dc_pat\": \"645598\",\n" +
            "  \"voucher_ogrn\": \"\",\n" +
            "  \"lpu_empl_flag\": \"1\",\n" +
            "  \"ln_code\": \"285115169530\",\n" +
            "  \"dd_pat\": \"645598\",\n" +
            "  \"parent_code\": \"\",\n" +
            "  \"patronimic\": \"ФЕДОРОВИЧ\",\n" +
            "  \"lpu_address\": \"АСТРАХАНЬ ТАТИЩЕВА 2\",\n" +
            "  \"hospital_dt1\": \"2017-12-24\",\n" +
            "  \"hospital_dt2\": \"2018-01-05\",\n" +
            "  \"mse_result\": \"\",\n" +
            "  \"issuedate\": \"2018-01-05\",\n" +
            "  \"ddid\": \"80033\",\n" +
            "  \"duplicate_flag\": \"0\",\n" +
            "  \"snils\": \"162-937-211 76\",\n" +
            "  \"pregn12w_flag\": \"null\",\n" +
            "  \"diagnos\": \"I63.3\"\n" +
            "}";
*/
    String json = "{\n" +
            "  \"birthday\": \"1940-07-07\",\n" +
            "  \"reason1\": \"01\",\n" +
            "  \"ln_date\": \"2018-04-09\",\n" +
            "  \"boz_flag\": \"0\",\n" +
            "  \"surname\": \"ИВАНОВ\",\n" +
            "  \"lpu_name\": \"ГБУЗ АО \\\"АЛЕКСАНДРО-МАРИИНСКАЯ ОБЛАСТНАЯ КЛИНИЧЕСКАЯ БОЛЬНИЦА \\\"\",\n" +
            "  \"lpu_employer\": \"ДЖОБС\",\n" +
            "  \"treats\": [\n" +
            "    {\n" +
            "      \"treat_doctor\": \"ТИТОВА ЮЛИЯ ПАВЛОВНА\",\n" +
            "      \"patronimic\": \"АКИМОВИЧ\",\n" +
            "      \"treat_chairman\": \"ТИТОВА ЮЛИЯ ПАВЛОВНА\",\n" +
            "      \"treat_chairman_role\": \"Врач-колопроктолог\",\n" +
            "      \"name\": \"ИВАН\",\n" +
            "      \"ddid\": \"81627\",\n" +
            "      \"treat_dt1\": \"2018-04-05\",\n" +
            "      \"treat_dt2\": \"2018-04-09\",\n" +
            "      \"surname\": \"ИВАНОВ\",\n" +
            "      \"isexport\": \"t\",\n" +
            "      \"treat_doctor_role\": \"Врач-колопроктолог\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"is_close\": \"0\",\n" +
            "  \"primary_flag\": \"1\",\n" +
            "  \"name\": \"ИВАН\",\n" +
            "  \"voucher_no\": \"\",\n" +
            "  \"gender\": \"0\",\n" +
            "  \"ln_state\": \"1\",\n" +
            "  \"lpu_ogrn\": \"1053000627690\",\n" +
            "  \"dc_pat\": \"73200\",\n" +
            "  \"voucher_ogrn\": \"\",\n" +
            "  \"lpu_empl_flag\": \"1\",\n" +
            "  \"ln_code\": \"306742020070\",\n" +
            "  \"parent_code\": \"\",\n" +
            "  \"patronimic\": \"АКИМОВИЧ\",\n" +
            "  \"lpu_address\": \"АСТРАХАНЬ ТАТИЩЕВА 2\",\n" +
            "  \"mse_result\": \"\",\n" +
            "  \"issuedate\": \"2018-04-09\",\n" +
            "  \"ddid\": \"81627\",\n" +
            "  \"duplicate_flag\": \"0\",\n" +
            "  \"snils\": \"037-314-058 29\",\n" +
            "  \"pregn12w_flag\": \"null\"\n" +
            "}";
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    protected SOAPMessage modifyMessage( String soapProtocol, InputStream inputStream ) throws SOAPException
    {
        MessageFactory messageFactory = MessageFactory.newInstance( soapProtocol );
        StreamSource messageSource = new StreamSource( inputStream );
        SOAPMessage message = messageFactory.createMessage();
        SOAPPart soapPart = message.getSOAPPart();
        soapPart.setContent( messageSource );
        message.saveChanges();
        return message;
    }

   /* private PrParseFileLnLpu parseJson() throws ParseException {

        //get(jrow,"lpu_ogrn")
        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();
        JsonArray treats = jparse.getAsJsonArray("treats");

        List<TREAT_FULL_PERIOD> treat_full_periods = new ArrayList<>();
        List<ROW> rows = new ArrayList<>();
        int periodNum = 3;
        String StartPeriod=null;
        int DDID_1=0;

        JsonObject jrow = parser.parse(json).getAsJsonObject();
        GlobalVariables.t_ELN =get(jrow,"ln_code");
        DDID_1 = Integer.parseInt(get(jrow,"ddid"));

        ROW.LN_RESULT ln_result = new ROW.LN_RESULT();
        Boolean  isClose=  (get(jrow,"is_close")).equals("1")?true:false;
        ln_result.setMseresult(get(jrow,"mse_result"));
        ln_result.setOtherstatedt(get(jrow,"other_state_dt"));

        //Если документ не закрыт, то даты нет
        if(isClose && ln_result.getMseresult()!=null) {
            if (!ln_result.getMseresult().equals("31") && !ln_result.getMseresult().equals("37")){

                if (ln_result.getOtherstatedt()!= null && !ln_result.getOtherstatedt().equals("")){
                    ln_result.setReturndatelpu(null);
                }else {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(format.parse(ln_result.getReturndatelpu()));
                    cal.add(Calendar.DAY_OF_MONTH, 1);
                    ln_result.setReturndatelpu(new java.sql.Date(cal.getTime().getTime()).toString());
                }
            } else { //Если закрыт по причине "продолжает болеть"
                ln_result.setReturndatelpu(null);
                ln_result.setNextlncode(get(jrow,"next_ln_code"));
            }
            ln_result.setAttribId("ELN_"+t_ELN+"_2_doc");
        }else if(!isClose) ln_result.setReturndatelpu(null);


        List<ROW.LN_RESULT> ln_results = new ArrayList<>();
        ROW row = new ROW();
        String str[];
        String snils = get(jrow,"snils");
        str = snils.split("-");
        snils = str[0] + str[1] + str[2];
        str = snils.split(" ");
        snils = str[0] + str[1];

        row.setIdDD(DDID_1);
        row.setAttribId("ELN_" + t_ELN);
        row.setSnils(snils);
        row.setSurname(get(jrow,"surname"));
        row.setName(get(jrow,"name"));
        row.setPatronimic(get(jrow,"patronimic"));
        row.setBozflag(Integer.parseInt(get(jrow,"boz_flag")));
        row.setLpuemployer(get(jrow,"lpu_employer"));
        row.setLpuemplflag(Integer.parseInt(get(jrow,"lpu_empl_flag")));
        row.setLncode(get(jrow,"ln_code"));
        row.setPrevlncode(get(jrow,"prev_ln"));
        row.setPrimaryflag(Integer.parseInt(get(jrow,"primary_flag")));
        row.setDuplicateflag(Integer.parseInt(get(jrow,"duplicate_flag")));
        row.setLndate(get(jrow,"ln_date"));
        row.setLpuname(get(jrow,"lpu_name"));
        row.setLpuaddress(get(jrow,"lpu_address"));
        row.setLpuogrn(get(jrow,"lpu_ogrn"));
        row.setBirthday(get(jrow,"birthday"));
        row.setGender(Integer.parseInt(get(jrow,"gender")));
        row.setReason1(get(jrow,"reason1"));
        row.setReason2(get(jrow,"reason2"));
        row.setReason3(get(jrow,"reason3"));
        row.setDiagnos(get(jrow,"diagnos"));
        row.setParentcode(get(jrow,"parent_code"));
        row.setDate1(get(jrow,"date1"));
        row.setDate2(get(jrow,"date2"));
        row.setVoucherno(get(jrow,"voucher_no"));
        row.setVoucherogrn(get(jrow,"voucher_ogrn"));
        row.setServ1AGE(get(jrow,"serv1_age"));
        row.setServ1RELATIONCODE(get(jrow,"serv1_relation_code"));
        row.setServ1FIO(get(jrow,"serv1_age"));
        row.setServ2AGE(get(jrow,"serv2_age"));
        row.setServ2RELATIONCODE(get(jrow,"serv2_relation_code"));
        row.setServ2FIO(get(jrow,"serv2_fio"));
        row.setPregn12WFLAG(get(jrow,"pregn12_flag"));
        row.setHospitaldt1(get(jrow,"hospital_dt1"));
        row.setHospitaldt2(get(jrow,"hospital_dt2"));
        row.setMsedt1(get(jrow,"mse_dt1"));
        row.setMsedt2(get(jrow,"mse_dt2"));
        row.setMsedt3(get(jrow,"mse_dt3"));

        String inv = get(jrow,"mse_invalid_group");
        if(inv!=null && !inv.equals("")){
            row.setMseinvalidgroup(Integer.valueOf(inv));
        }
        row.setLnstate(get(jrow,"ln_state"));


       *//* DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date2= new java.sql.Date(format.parse(StartPeriod).getTime());

            if(row.getServ1AGE()!=null && !row.getServ1AGE().equals("")) {
                row.setServ1AGE(calculateAge(date2,new java.sql.Date(format.parse(row.getServ1AGE()).getTime()),0));
                row.setServ1MM(Integer.valueOf(calculateAge(date2,new java.sql.Date(format.parse(row.getServ1AGE()).getTime()),2)));
            }

            if(row.getServ2AGE()!=null && !row.getServ2AGE().equals("")) {
                row.setServ2AGE(calculateAge(date2,new java.sql.Date(format.parse(row.getServ2AGE()).getTime()),0));
                row.setServ2MM(Integer.valueOf(calculateAge(date2,new java.sql.Date(format.parse(row.getServ2AGE()).getTime()),2)));
            }*//*

        try {
            if(row.getServ1AGE()!=null && !row.getServ1AGE().equals("")) {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 =  new java.sql.Date(format.parse(row.getServ1AGE()).getTime());
                Date date2 = new java.sql.Date(format.parse(StartPeriod).getTime());

                System.out.println(StartPeriod);
                row.setServ1AGE(calculateAge(date2,date1,0));
                row.setServ1MM(Integer.valueOf(calculateAge(date2,date1,2)));
            }

            if(row.getServ2AGE()!=null && !row.getServ2AGE().equals("")) {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 =  new java.sql.Date(format.parse(row.getServ2AGE()).getTime());
                Date date2 = new java.sql.Date(format.parse(StartPeriod).getTime());

                row.setServ2AGE(calculateAge(date2,date1,0));
                row.setServ2MM(Integer.valueOf(calculateAge(date2,date1,2)));
            }
        }catch (Exception e){ e.printStackTrace();}

        ln_results.add(ln_result);
        row.setLnresult(ln_results);



        *//** HOSPITAL_BREACH **//*
        if(get(jrow,"hospital_breach_code")!=null) {
            ROW.HOSPITAL_BREACH hospital_breach = new ROW.HOSPITAL_BREACH();
            hospital_breach.setHospitalbreachcode(get(jrow, "hospital_breach_code"));
            hospital_breach.setHospitalbreachdt(get(jrow, "hospital_breach_dt"));
            if (hospital_breach.getHospitalbreachcode() != null) {
                hospital_breach.setAttributeId("ELN_" + t_ELN + "_1_doc");
            }
            List<ROW.HOSPITAL_BREACH> hospital_breaches = new ArrayList<>();
            hospital_breaches.add(hospital_breach);
            row.setHospitalbreach(hospital_breaches);
        }

        *//** TREAT *//*
        for (JsonElement el : treats) {

            JsonObject jtreat = el.getAsJsonObject();

            String isexport = get(jtreat,"isexport");
            int DDID_2 = Integer.parseInt(get(jtreat,"ddid"));

            if (DDID_1 == DDID_2) {
                TREAT_PERIOD treat_period = new TREAT_PERIOD();
                treat_period.setTreatdt1(get(jtreat,"treat_dt1"));
                if (StartPeriod==null) {
                    StartPeriod= get(jtreat,"treat_dt1");
                }

                treat_period.setTreatdt2(get(jtreat,"treat_dt2"));

                treat_period.setTreatdoctorrole(get(jtreat,"treat_doctor_role"));
                treat_period.setTreatdoctor(get(jtreat,"treat_doctor"));
                treat_period.setAttribId("ELN_" + t_ELN + "_" + periodNum + "_doc");

                List<TREAT_PERIOD> treat_periods = new ArrayList<>();
                treat_periods.add(treat_period);
                TREAT_FULL_PERIOD treat_full_period = new TREAT_FULL_PERIOD();
                treat_full_period.setTreatchairmanrole(get(jtreat,"treat_chairman_role"));
                treat_full_period.setTreatchairman(get(jtreat,"treat_chairman"));
                if (treat_full_period.getTreatchairmanrole() != null && !treat_full_period.getTreatchairmanrole().equals("")) {
                    treat_full_period.setAttribIdVk("ELN_" + t_ELN + "_" + periodNum + "_vk");
                }

                System.out.println("ISEXPORT>>>>>>>"+isexport);
                if(isexport!=null && !isexport.equals("") && (isexport.equals("true") || isexport.equals("t"))){
                    treat_full_period.setExport("true");
                }else {
                    treat_full_period.setExport("false");
                }

                treat_full_period.setTreat_period(treat_periods);
                treat_full_periods.add(treat_full_period);
                periodNum++;
            }
        }

        row.setTREAT_PERIODS(treat_full_periods);
        rows.add(row);

        ROWSET rowset = new ROWSET();
        rowset.setAuthor("R.Kurbanov");
        rowset.setEmail("Rashgild@gmail.com");
        rowset.setPhone("89608634440");
        rowset.setSoftware("SignAndCrypt");
        rowset.setVersion("1.1");
        rowset.setVersionSoftware("2.0");
        rowset.setRow(rows);
        List<ROWSET> rowsets = new ArrayList<>();
        rowsets.add(rowset);
        PrParseFileLnLpu.Reqest.pXmlFile pXmlFile = new PrParseFileLnLpu.Reqest.pXmlFile();
        pXmlFile.setRowset(rowsets);
        List<PrParseFileLnLpu.Reqest.pXmlFile> pXmlFiles = new ArrayList<>();
        pXmlFiles.add(pXmlFile);
        PrParseFileLnLpu.Reqest request = new PrParseFileLnLpu.Reqest();
        request.setOgrn(ogrnMo);
        request.setpXmlFiles(pXmlFiles);
        List<PrParseFileLnLpu.Reqest> reqests = new ArrayList<>();
        reqests.add(request);
        PrParseFileLnLpu prParseFilelnlpu = new PrParseFileLnLpu();
        prParseFilelnlpu.setFil("http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl");
        prParseFilelnlpu.setRequests(reqests);

        try {
        setUp();

            GlobalVariables.parser.saveObject(GlobalVariables.file, prParseFilelnlpu);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return prParseFilelnlpu;
    }*/


    private String get(JsonObject obj, String name){
        if(obj.has(name)){
            if(obj!=null && obj.get(name)!=null && !obj.get(name).getAsString().equals("")){
               return obj.get(name).getAsString();
            }
        }
        return null;
    }




   /* private void parseJson(){

        int DDID_1 = resultSet.getInt("DDID");
        ROW.LN_RESULT ln_result = new ROW.LN_RESULT();
        Boolean  isClose=  resultSet.getString("IS_CLOSE").equals("1")?true:false;
        ln_result.setMseresult(resultSet.getString("MSE_RESULT"));
        ln_result.setOtherstatedt(resultSet.getString("other_state_dt"));

        List<TREAT_FULL_PERIOD> treat_full_periods = new ArrayList<>();

        //for(){
        String isexport = resultSet2.getString("isexport");
        int DDID_2 = resultSet2.getInt("DDID");

        if(DDID_1==DDID_2){
            TREAT_PERIOD treat_period = new TREAT_PERIOD();
            treat_period.setTreatdt1(resultSet2.getString("TREAT_DT1"));
            if (StartPeriod==null) {
                StartPeriod= resultSet2.getString("TREAT_DT1");
            }
            treat_period.setTreatdt2(resultSet2.getString("TREAT_DT2"));
            ln_result.setReturndatelpu(resultSet2.getString("TREAT_DT2"));//берем день выхода на работу
            treat_period.setTreatdoctorrole(resultSet2.getString("TREAT_DOCTOR_ROLE"));
            treat_period.setTreatdoctor(resultSet2.getString("TREAT_DOCTOR"));
            treat_period.setAttribId("ELN_" + t_ELN + "_" + per + "_doc");

            List<TREAT_PERIOD> treat_periods = new ArrayList<>();
            treat_periods.add(treat_period);
            TREAT_FULL_PERIOD treat_full_period = new TREAT_FULL_PERIOD();
            treat_full_period.setTreatchairmanrole(resultSet2.getString("TREAT_CHAIRMAN_ROLE"));
            treat_full_period.setTreatchairman(resultSet2.getString("TREAT_CHAIRMAN"));
            if (treat_full_period.getTreatchairmanrole() != null) {
                treat_full_period.setAttribIdVk("ELN_" + t_ELN + "_" + per + "_vk");
            }

            //TODO А это надо?
            System.out.println("ISEXPORT>>>>>>>"+isexport);
            if(isexport!=null && (isexport.equals("true") || isexport.equals("t"))){
                treat_full_period.setExport("true");
            }else {
                treat_full_period.setExport("false");
            }


            treat_full_period.setTreat_period(treat_periods);
            treat_full_periods.add(treat_full_period);
            per++;

        }
        //}
    }*/

}
/* public static String PrParse_Query2(String disabilityId){
      return
  }*/