package ru.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import ru.ibs.fss.ln.ws.fileoperationsln.*;
import ru.my.entities.*;
import ru.my.entities.ROW;
import ru.my.entities.ROWSET;
import ru.my.helpers_operations.GlobalVariables;
import ru.my.signAndCrypt.Sign;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static ru.api.ApiUtils.cretePostRequest;
import static ru.my.helpers_operations.GlobalVariables.*;
import static ru.my.helpers_operations.WorkWithXML.SaveSOAPToXML;
import static ru.my.helpers_operations.WorkWithXML.SoapMessageToString;
import static ru.my.helpers_operations.WorkWithXML.soapToDoc;
import static ru.my.service_operations.xmlFileLnLpu.PrParseFileLnLpu_start.calculateAge;
import static ru.my.signAndCrypt.Encrypt.CreateXMLAndEncrypt;

/** Created by rashgild on 24.04.2018. */
@Path("/sign")
public class SignAndCryptApi {

    @GET
    @Path("/sendRequest")
    @Produces("application/json;charset=UTF-8")
    public String sedrequest(@Context HttpServletRequest req,@Context HttpServletResponse response)
            throws Exception {

        System.setProperty("javax.net.ssl.trustStore",pathandnameSSL);
        System.setProperty("javax.net.ssl.trustStorePassword", passwordSSL);
        FileOperationsLnImplService service = new  FileOperationsLnImplService();
        FileOperationsLn start = service.getFileOperationsLnPort();
        ru.ibs.fss.ln.ws.fileoperationsln.ROWSET rowset = new ru.ibs.fss.ln.ws.fileoperationsln.ROWSET();

        rowset.setAuthor("ThisIsNewSender");
        PrParseFilelnlpuElement prParseFilelnlpuElement = new PrParseFilelnlpuElement();
        PrParseFilelnlpuElement.PXmlFile pXmlFile= new PrParseFilelnlpuElement.PXmlFile();
        pXmlFile.setROWSET(rowset);
        prParseFilelnlpuElement.setPXmlFile(pXmlFile);
        WSResult result =null;

        JSONObject resultJson = new JSONObject();

        try {
            result = start.prParseFilelnlpu(prParseFilelnlpuElement);

            resultJson.put("message",result.getMESS());
            resultJson.put("status",result.getSTATUS());
            resultJson.put("requestId",result.getREQUESTID());
            //resultJson.put("requestId",result.getINFO().getROWSET().getROW().get(0).getLNCODE());
            System.out.println("mssL>>>"+result.getMESS());

            List<INFO.ROWSET.ROW> rows = result.getINFO().getROWSET().getROW();
            if (rows != null && rows.size() > 0) {
                for (INFO.ROWSET.ROW row : rows) {

                    if(row.getLNCODE()!=null &&!row.getLNCODE().equals("")){
                        resultJson.put("hash",row.getLNHASH());
                        System.out.println("hash>>>"+row.getLNHASH());
                    }
                    resultJson.put("lncode",row.getLNCODE());
                    //
                    JSONArray jsonArray= new JSONArray();
                    try {
                        if (row.getERRORS()!=null && row.getERRORS().getERROR().size() > 0) {
                            List<INFO.ROWSET.ROW.ERRORS.ERROR> errors = row.getERRORS().getERROR();
                            for (INFO.ROWSET.ROW.ERRORS.ERROR errs : errors) {
                                JSONObject arrjs = new JSONObject();
                                arrjs.put("errmess",errs.getERRMESS()).put("errcode",errs.getERRCODE());
                                jsonArray.put(arrjs);
                                System.out.println("errmess:>>>>"+errs.getERRMESS());
                                System.out.println("errcode:>>>>"+errs.getERRCODE());
                            }

                            resultJson.put("errors",jsonArray);
                        }
                    }catch (Exception e){e.printStackTrace();}
                }
            }

        } catch (SOAPException_Exception e) {
            e.printStackTrace();
        }

        //System.out.println(resultJson);
        return resultJson.toString();
    }


    @POST
    @Path("/exportDisabilityDocument")
    //@Produces("application/json;charset=UTF-8")
    public String getJsonDisabilityDoc(String data,
                        @Context HttpServletRequest req,
                        @Context HttpServletResponse response) throws Exception {

        SOAPMessage message=createDisabilityXml(data);
        SaveSOAPToXML(signXMLFileName, message);
        try{

            String mess = SoapMessageToString(message);
            GlobalVariables.Request = CreateXMLAndEncrypt(mess);
        }catch (Exception e){
            e.printStackTrace();
        }

       return sedrequest(req,response);
    }


    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private String getReturnWorkDate(String date){
        Calendar cal = Calendar.getInstance();

        try {
            cal.setTime(format.parse(date));
            cal.add(Calendar.DAY_OF_MONTH, 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new java.sql.Date(cal.getTime().getTime()).toString();
    }

    private SOAPMessage createDisabilityXml(String json) throws Exception {
        int per = 3;
        //System.out.println(json);
        String head="";
        JsonParser parser =new JsonParser();
        JsonObject jparsr = parser.parse(json).getAsJsonObject();
        JsonArray treats = jparsr.getAsJsonArray("treats");
        JsonArray signclose = jparsr.getAsJsonArray("close");
        String StartPeriod=null;
        List<TREAT_FULL_PERIOD> treat_full_periods = new ArrayList<>();
        List<ROW> rows = new ArrayList<>();
        int DDID_1=0;

        JsonObject jrow = parser.parse(json).getAsJsonObject();
        GlobalVariables.t_ELN =get(jrow,"ln_code");
        DDID_1 = Integer.parseInt(get(jrow,"ddid"));

        ROW.LN_RESULT ln_result = new ROW.LN_RESULT();
        Boolean  isClose=  (get(jrow,"is_close")).equals("1")?true:false;
        ln_result.setMseresult(get(jrow,"mse_result"));
        ln_result.setOtherstatedt(get(jrow,"other_state_dt"));


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
        row.setLnhash(get(jrow,"ln_hash"));

        String inv = get(jrow,"mse_invalid_group");
        if(inv!=null && !inv.equals("")){
            row.setMseinvalidgroup(Integer.valueOf(inv));
        }
        row.setLnstate(get(jrow,"ln_state"));

        try {
            if(row.getServ1AGE()!=null && !row.getServ1AGE().equals("")) {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 =  new java.sql.Date(format.parse(row.getServ1AGE()).getTime());
                Date date2 = new java.sql.Date(format.parse(StartPeriod).getTime());

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

        /** HOSPITAL_BREACH **/
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


        /** TREAT */
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
                String counterdoc = get(jtreat,"counterdoc");
                String countervk = get(jtreat,"countervk");
                treat_period.setAttribId("ELN_" + t_ELN + "_" + per + "_doc");

                List<TREAT_PERIOD> treat_periods = new ArrayList<>();
                treat_periods.add(treat_period);
                TREAT_FULL_PERIOD treat_full_period = new TREAT_FULL_PERIOD();
                treat_full_period.setTreatchairmanrole(get(jtreat,"treat_chairman_role"));
                treat_full_period.setTreatchairman(get(jtreat,"treat_chairman"));

                if (treat_full_period.getTreatchairmanrole() != null && !treat_full_period.getTreatchairmanrole().equals("")) {
                    treat_full_period.setAttribIdVk("ELN_" + t_ELN + "_" + per + "_vk");

                }

                //System.out.println("ISEXPORT>>>>>>>"+isexport);
                if(isexport!=null && !isexport.equals("") && (isexport.equals("true") || isexport.equals("t"))){
                   // treat_full_period.setExport("true");
                }else {
                  //  treat_full_period.setExport("false");

                    if (treat_full_period.getTreatchairmanrole() != null && !treat_full_period.getTreatchairmanrole().equals("")) {
                        head += createHead(
                                get(jtreat,"certvk"),
                                get(jtreat,"digvk"),
                                get(jtreat,"signvk"),"ELN_"+t_ELN,"1053000627690",
                                get(jtreat,"countervk"),"vk");
                    }
                    head += createHead(
                            get(jtreat,"certdoc"),
                            get(jtreat,"digdoc"),
                            get(jtreat,"signdoc"),"ELN_"+t_ELN,"1053000627690",
                            get(jtreat,"counterdoc"),"doc");
                }

                treat_full_period.setTreat_period(treat_periods);
                treat_full_periods.add(treat_full_period);
                per++;
            }
        }


        /** LN_RESULT */


        if(isClose){
            ln_result.setAttribId("ELN_"+t_ELN+"_2_doc");

            for (JsonElement el : signclose) {

                JsonObject jtreat = el.getAsJsonObject();
                //Для даты выхода на работу
                ln_result.setReturndatelpu(get(jtreat,"returndt"));
                head += createHead(
                        get(jtreat, "certclose"),
                        get(jtreat, "digclose"),
                        get(jtreat, "signclose"), "ELN_" + t_ELN, "1053000627690",
                        get(jtreat, "counterclose"), "doc");
            }
        }
        System.out.println("getReturndatelpu>>>>>>>>>>"+ln_result.getReturndatelpu());

        if(isClose && ln_result.getMseresult()!=null) {
            if (!ln_result.getMseresult().equals("31") && !ln_result.getMseresult().equals("37")){

                if (ln_result.getOtherstatedt()!= null && !ln_result.getOtherstatedt().equals("")){
                    ln_result.setReturndatelpu(null);
                }else {
                    ln_result.setReturndatelpu(getReturnWorkDate(ln_result.getReturndatelpu()));
                }
            } else { //Если закрыт по причине "продолжает болеть"
                ln_result.setReturndatelpu(null);
                ln_result.setNextlncode(get(jrow,"next_ln_code"));
            }
        }else if(!isClose) ln_result.setReturndatelpu(null);

        /** ---- */
        row.setTREAT_PERIODS(treat_full_periods);
        rows.add(row);

        ROWSET rowset = new ROWSET();
        rowset.setAuthor("R.Kurbanov");
        rowset.setEmail("Rashgild@gmail.com");
        rowset.setPhone("89608634440");
        rowset.setSoftware("SignAndCrypt");
        rowset.setVersion("1.0");
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





        String finalXml = createXml(prParseFilelnlpu).replace("[Head]",head);
        //String finalXml = createXml(prParseFilelnlpu).replace("[Head]","");

        GlobalVariables.pathtosave="C:/SignAndCrypt/Temp/";
        GlobalVariables.signXMLFileName="person.xml";


        InputStream is = new ByteArrayInputStream(finalXml.getBytes());
        SOAPMessage message = MessageFactory.newInstance().createMessage(null, is);


        //SaveSOAPToXML(signXMLFileName, message);

        message = Sign.SignationByParametrs(
                message,
                "http://eln.fss.ru/actor/mo/" + ogrnMo + "/" + row.getAttribId(),
                "#" + row.getAttribId(),
                moAlias, moPass, t_ELN);

        //SaveSOAPToXML(signXMLFileName, message);

        /*SaveSOAPToXML(signXMLFileName, message);
        message = Sign.SignationByParametrs("http://eln.fss.ru/actor/doc/ELN_306742026285_4_vk",
                "#ELN_306742026285_4_vk", vkAlias, vkPass, t_ELN);
        SaveSOAPToXML(signXMLFileName, message);
        message = Sign.SignationByParametrs("http://eln.fss.ru/actor/doc/ELN_306742026285_4_doc",
                "#ELN_306742026285_4_doc", vkAlias, vkPass, t_ELN);
        SaveSOAPToXML(signXMLFileName, message);*/

      //message.writeTo(System.out);

      /*  if(!Verify(message, Certificate.ExtractCertFromCertStore(passwordCertStor,aliasCert,pathToCert))){
            System.out.println("!!!");
        }*/



        return message;
        //return SoapMessageToString(message);
    }

    private String createHead(String cert, String dig,String sig, String eln, String ogrn,String counter,String type){
        String head = "<wsse:Security soapenv:actor=\"http://eln.fss.ru/actor/doc/[ELN]_[COUNTER]_[TYPE]\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\"><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411\"/><Reference URI=\"#[ELN]_[COUNTER]_[TYPE]\"><Transforms><Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr3411\"/><DigestValue>[digest]</DigestValue></Reference></SignedInfo><SignatureValue>[signature]</SignatureValue><KeyInfo><wsse:SecurityTokenReference><wsse:Reference URI=\"#http://eln.fss.ru/actor/mo/[OGRN]/[ELN]\" ValueType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3\"/></wsse:SecurityTokenReference></KeyInfo></Signature><wse:BinarySecurityToken xmlns:wse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary\" ValueType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3\" wsu:Id=\"http://eln.fss.ru/actor/mo/[OGRN]/[ELN]\">[cert]</wse:BinarySecurityToken></wsse:Security>";
        return head
                .replace("[cert]",cert)
                .replace("[digest]",dig)
                .replace("[signature]",sig)
                .replace("[OGRN]",ogrn)
                .replace("[ELN]",eln)
                .replace("[COUNTER]",counter)
                .replace("[TYPE]",type);
    }
    private String createXml(PrParseFileLnLpu prParseFilelnlpu){

        setUp();
        String signThis = "";
        String xmlTemplate="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:fil=\"http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Header>[Head]</soapenv:Header><soapenv:Body>[Body]</soapenv:Body></soapenv:Envelope>";
        try {
            Document document = GlobalVariables.parser.ObjToSoap(prParseFilelnlpu);
            DOMSource domSource = new DOMSource(document);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);

            String xml = writer.toString();
            xml = xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");

            signThis = xmlTemplate.replace("[Body]", xml);

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

    @POST
    @Path("/getJSON")
    @Produces("text/html")
    public void getJSON(String data,
                        @Context HttpServletRequest req,
                        @Context HttpServletResponse response) throws IOException, ServletException, ParseException {

        System.out.println(data);
        ROW row = parseJson(data);
        String xml = createXML(row);

        response.setHeader("Access-Control-Allow-Origin", "*");
        //response.setHeader("Access-Control-Allow-Origin", "http://192.168.2.45:8800");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,origin, content-type, accept, authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS, HEAD");


        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("test", xml);
        req.getServletContext().getRequestDispatcher("/webSignTest.jsp").forward(req, response);
    }

    @POST
    @Path("/getXML")
    @Produces("application/json;charset=UTF-8")
    public String getXML(String data,
                         @Context HttpServletRequest req,
                         @Context HttpServletResponse response)
            throws IOException, ServletException, JSONException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods","POST");
        String xml = data;
        System.out.println(data);

        JSONObject jsonObject = new JSONObject();
        String counter = parseXML(xml,"<fil:BIRTHDAY>");
        if(counter.equals("2")){
            jsonObject
                    .put("Code","close");

        }else {
            jsonObject
                    .put("Code",parseXML(xml,"<fil:LN_HASH>"))
                    .put("AnotherId",parseXML(xml,"<fil:SNILS>"));
        }

        jsonObject
                .put("Certificate", parseXML(xml,"<X509Certificate>"))
                .put("SignatureValue",parseXML(xml,"<SignatureValue>"))
                .put("DigestValue",parseXML(xml,"<DigestValue>"))
                .put("ELN",parseXML(xml,"<fil:LN_CODE>"))
                .put("Counter",parseXML(xml,"<fil:BIRTHDAY>"))
                .put("DisabilityId",parseXML(xml,"<fil:DIAGNOS>"));


        System.out.println(jsonObject.toString());

        //TODO meods endpoint
        cretePostRequest("http://192.168.2.45:8800/riams","api/disabilitySign/getJson" ,jsonObject.toString());
        return jsonObject.toString();
    }


    public static String parseXML(String xml,String searchString){
        String[] search = xml.split("");
        int start = xml.indexOf(searchString) + searchString.length()+1;
        StringBuilder sb = new StringBuilder();
        while (!search[start].equals("<")){
            sb.append(search[start]);
            start++;
        }
        return sb.toString();
    }


    private ROW parseJson(String json) throws ParseException {

        System.out.println(json);
        ROW row = new ROW();
        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();
        JsonArray treats = jparse.getAsJsonArray("data");
        for (JsonElement el : treats) {

            JsonObject jtreat = el.getAsJsonObject();
            String doctype =  get(jtreat,"doctype");

            if(doctype.equals("close")){

               String elncode = get(jtreat,"ddnum");
               String number = get(jtreat,"num");

               ROW.LN_RESULT ln_result = new ROW.LN_RESULT();
               ln_result.setAttribId("ELN_"+elncode+"_"+number+"_doc");
               ln_result.setMseresult(get(jtreat,"mse_result"));
               ln_result.setNextlncode(get(jtreat,"NEXT_LN_CODE"));
               ln_result.setOtherstatedt(get(jtreat,"other_state_dt"));

               ln_result.setReturndatelpu(get(jtreat,"returndt"));

               row.setDiagnos(get(jtreat,"ddid"));
               row.setLnhash(get(jtreat,"doctype"));

                System.out.println("getReturndatelpu>>>>>>"+ln_result.getReturndatelpu());
               String isClose = get(jtreat,"is_close");
               if(isClose.equals("1") && ln_result.getMseresult()!=null) {
                   if (!ln_result.getMseresult().equals("31") && !ln_result.getMseresult().equals("37")){

                       if (ln_result.getOtherstatedt()!= null && !ln_result.getOtherstatedt().equals("")){
                           ln_result.setReturndatelpu(null);
                       }else {
                          /* Calendar cal = Calendar.getInstance();
                           cal.setTime(format.parse(ln_result.getReturndatelpu()));
                           cal.add(Calendar.DAY_OF_MONTH, 1);
                           ln_result.setReturndatelpu(new java.sql.Date(cal.getTime().getTime()).toString());*/
                       }
                   } else { //Если закрыт по причине "продолжает болеть"
                       ln_result.setReturndatelpu(null);
                       ln_result.setNextlncode(get(jtreat,"next_ln_code"));
                   }

               }else if(!isClose.equals("1")) ln_result.setReturndatelpu(null);


               List<ROW.LN_RESULT> ln_results = new ArrayList<>();
               ln_results.add(ln_result);

                row.setBirthday(get(jtreat,"num"));
                row.setDiagnos(get(jtreat,"ddid"));
                row.setLnhash("doc");
                row.setLncode(elncode);
                row.setAttribId(number);
                row.setLnresult(ln_results);

           }else {
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


               row.setBirthday(get(jtreat,"num"));
               row.setSnils(get(jtreat,"recordid"));
               row.setDiagnos(get(jtreat,"ddid"));
               row.setLncode(elncode);
               row.setAttribId(number);
               row.setLnhash(get(jtreat,"doctype"));
               row.setTREAT_PERIODS(treat_full_periods);
           }
        }
        return row;
    }

    private String createXML(ROW row){

        setUp();
        String signThis = "";
        String number = row.getAttribId();
        //String elnNumber = "ELN_"+row.getLncode();
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
            signThis = signThis.replace("<someAdd1>", "doc/" + "ELN_"+elnNumber + "_" + number + "_"+type); // doc/ELN_306742020070_3_doc
            signThis = signThis.replace("<someAdd2>", "ELN_"+elnNumber + "_" + number + "_"+type); // ELN_306742020070_3_doc
            signThis = signThis.replace("<someAdd3>", "ELN_"+elnNumber); // ELN_306742020070
            //System.out.println(signThis);

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

   static String xmlTemplate = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:fil=\"http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
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
   //static String xmlTemplate ="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:fil=\"http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Header><wsse:Security soapenv:actor=\"http://eln.fss.ru/actor/<someAdd1> xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\"><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411\"/><Reference URI=\"#<someAdd2>\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr3411\"/><DigestValue/></Reference></SignedInfo><SignatureValue/><KeyInfo/></Signature><wse:BinarySecurityToken xmlns:wse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary \" ValueType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3\" wsu:Id=\"http://eln.fss.ru/actor/mo/1053000627690/<someAdd3>\"/></wsse:Security></soapenv:Header><soapenv:Body><replace></soapenv:Body></soapenv:Envelope>";

    private String get(JsonObject obj, String name){
        if(obj.has(name)){
            if(obj!=null && obj.get(name)!=null && !obj.get(name).getAsString().equals("")){
                return obj.get(name).getAsString();
            }
        }
        return null;
    }


}
