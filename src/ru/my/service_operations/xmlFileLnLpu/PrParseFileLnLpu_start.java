package ru.my.service_operations.xmlFileLnLpu;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import ru.my.entities.*;
import ru.my.helpers_operations.GlobalVariables;
import ru.my.helpers_operations.SQL;
import ru.my.signAndCrypt.Encrypt;
import ru.my.signAndCrypt.Sign;

import javax.xml.bind.JAXBException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static ru.my.helpers_operations.GlobalVariables.*;
import static ru.my.helpers_operations.StoredQuery.PrParse_Query1;
import static ru.my.helpers_operations.StoredQuery.PrParse_Query2;
import static ru.my.helpers_operations.WorkWithXML.SaveSOAPToXML;
import static ru.my.helpers_operations.WorkWithXML.SoapMessageToString;


 //* Created by rashgild on 19.05.2017.


public class PrParseFileLnLpu_start {
    public static SOAPMessage Start(String disabilityId){

        Logger logger=Logger.getLogger("simple");
        DisabilityDocument_id = disabilityId;
        GlobalVariables.setUp();

        logger.info("1)Formation skeleton");
        PrParseFileLnLpu prParseFileLnLpu = null;
        try {
            prParseFileLnLpu = CreateSkeleton(PrParse_Query1(disabilityId),PrParse_Query2(disabilityId));
        } catch (SQLException | ParseException e) { logger.error(e); }
        GlobalVariables.prparse = prParseFileLnLpu;

        logger.info("2)Create message");
        SOAPMessage message = CreateSoapMessage(prParseFileLnLpu);

        logger.info("3)Singing");
        try {
            message = Signation(prParseFileLnLpu,message);
        } catch (Exception e) {logger.error(e);}

        logger.info("3.5) Prepatre request");
        GlobalVariables.Request = SoapMessageToString(message);

        logger.info("4) Crypting");
        try {
            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage CryptedMessage = mf.createMessage();
            CryptedMessage = Encrypt.CreateXMLAndEncrypt(CryptedMessage, signXMLFileName);
            SaveSOAPToXML(cryptXMLFileName,CryptedMessage);

            return CryptedMessage;
        } catch (Exception e) { logger.error(e);}

        return message;
    }

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static PrParseFileLnLpu CreateSkeleton(String sql1, String sql2) throws SQLException, ParseException {

        Logger logger=Logger.getLogger("simple");
        ResultSet resultSet = SQL.Query(sql1);
        ResultSet resultSet2 = SQL.Query(sql2);

        String StartPeriod=null,EndPeriod="";
        List<ROW> rows = new ArrayList<>();
        while (resultSet.next()) {
            GlobalVariables.t_ELN = resultSet.getString("LN_CODE");
            logger.info("ELN="+t_ELN);
            int per = 3;
            int DDID_1 = resultSet.getInt("DDID");

            ROW.LN_RESULT ln_result = new ROW.LN_RESULT();
            Boolean  isClose=  resultSet.getString("IS_CLOSE").equals("1")?true:false;
            ln_result.setMseresult(resultSet.getString("MSE_RESULT"));
            ln_result.setOtherstatedt(resultSet.getString("other_state_dt"));

            List<TREAT_FULL_PERIOD> treat_full_periods = new ArrayList<>();
            while (resultSet2.next()) {

                //TODO CheckIT!
                String isexport = resultSet2.getString("isexport");
                 int DDID_2 = resultSet2.getInt("DDID");
                if (DDID_1 == DDID_2) {
                    TREAT_PERIOD treat_period = new TREAT_PERIOD();
                    treat_period.setTreatdt1(resultSet2.getString("TREAT_DT1"));
                    if (StartPeriod==null) {
                        StartPeriod= resultSet2.getString("TREAT_DT1");
                    }
                    //EndPeriod = resultSet2.getString("TREAT_DT2");
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
            }
            resultSet2.beforeFirst(); // возврат курсора в начало
            ROW.HOSPITAL_BREACH hospital_breach = new ROW.HOSPITAL_BREACH();
            hospital_breach.setHospitalbreachcode(resultSet.getString("HOSPITAL_BREACH_CODE"));
            hospital_breach.setHospitalbreachdt(resultSet.getString("HOSPITAL_BREACH_DT"));
            if (hospital_breach.getHospitalbreachcode() != null) {
                hospital_breach.setAttributeId("ELN_" + t_ELN + "_1_doc");
            }
            List<ROW.HOSPITAL_BREACH> hospital_breaches = new ArrayList<>();
            hospital_breaches.add(hospital_breach);

            //32|33

            //Если документ не закрыт, то даты нет
            if(isClose && ln_result.getMseresult()!=null) {
                if (!ln_result.getMseresult().equals("31") && !ln_result.getMseresult().equals("37")){

                    if (ln_result.getOtherstatedt()!= null && !ln_result.getOtherstatedt().equals("")){
                    //    ln_result.setReturndatelpu(ln_result.getOtherstatedt());
                        ln_result.setReturndatelpu(null);
                    }else {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(format.parse(ln_result.getReturndatelpu()));  //.parse(returnDate));
                    cal.add(Calendar.DAY_OF_MONTH, 1);
                    ln_result.setReturndatelpu(new java.sql.Date(cal.getTime().getTime()).toString());
                    }
                } else { //Если закрыт по причине "продолжает болеть"
                    ln_result.setReturndatelpu(null);
                    ln_result.setNextlncode(resultSet.getString("NEXT_LN_CODE"));
                }
                ln_result.setAttribId("ELN_"+t_ELN+"_2_doc");
            }else if(!isClose) ln_result.setReturndatelpu(null);


            logger.info("Закрыт: "+isClose+"" +
                    " Дата выхода на работу:"+ln_result.getReturndatelpu()+"");
                    //" MSE_RESULT:"+ln_result.getMseresult());


            List<ROW.LN_RESULT> ln_results = new ArrayList<>();
            ROW row = new ROW();
            String str[];
            String snils = resultSet.getString("SNILS");
            str = snils.split("-");
            snils = str[0] + str[1] + str[2];
            str = snils.split(" ");
            snils = str[0] + str[1];

            row.setIdDD(DDID_1);
            row.setAttribId("ELN_" + t_ELN);
            row.setSnils(snils);

            if(!GlobalVariables.hash.equals("")) {
                row.setLnhash(GlobalVariables.hash);
            }
            row.setSurname(resultSet.getString("SURNAME"));
            row.setName(resultSet.getString("NAME"));
            row.setPatronimic(resultSet.getString("PATRONIMIC"));
            row.setBozflag(resultSet.getInt("BOZ_FLAG"));
            row.setLpuemployer(resultSet.getString("LPU_EMPLOYER"));
            row.setLpuemplflag(resultSet.getInt("LPU_EMPL_FLAG"));
            row.setLncode(resultSet.getString("LN_CODE"));
            row.setPrevlncode(resultSet.getString("PREV_LN"));


            row.setPrimaryflag(resultSet.getInt("PRIMARY_FLAG"));
            //int primaryFlag =  row.getPrimaryflag();
            row.setDuplicateflag(resultSet.getInt("DUPLICATE_FLAG"));
            row.setLndate(resultSet.getString("LN_DATE"));
            row.setLpuname(resultSet.getString("LPU_NAME"));
            row.setLpuaddress(resultSet.getString("LPU_ADDRESS"));
            row.setLpuogrn(resultSet.getString("LPU_OGRN"));
            row.setBirthday(resultSet.getString("BIRTHDAY"));
            row.setGender(resultSet.getInt("GENDER"));
            row.setReason1(resultSet.getString("REASON1"));
            row.setReason2(resultSet.getString("REASON2"));
            row.setReason3(resultSet.getString("REASON3"));
            row.setDiagnos(resultSet.getString("DIAGNOS"));
            row.setParentcode(resultSet.getString("PARENT_CODE"));
            row.setDate1(resultSet.getString("DATE1"));
            row.setDate2(resultSet.getString("DATE2"));
            row.setVoucherno(resultSet.getString("VOUCHER_NO"));
            row.setVoucherogrn(resultSet.getString("VOUCHER_OGRN"));
            row.setServ1AGE(resultSet.getString("SERV1_AGE"));

            row.setServ1RELATIONCODE(resultSet.getString("SERV1_RELATION_CODE"));
            row.setServ1FIO(resultSet.getString("SERV1_FIO"));
            row.setServ2AGE(resultSet.getString("SERV2_AGE"));
            row.setServ2RELATIONCODE(resultSet.getString("SERV2_RELATION_CODE"));
            row.setServ2FIO(resultSet.getString("SERV2_FIO"));
            row.setPregn12WFLAG(resultSet.getString("PREGN12W_FLAG"));
            row.setHospitaldt1(resultSet.getString("HOSPITAL_DT1"));
            row.setHospitaldt2(resultSet.getString("HOSPITAL_DT2"));
            row.setMsedt1(resultSet.getString("MSE_DT1"));
            row.setMsedt2(resultSet.getString("MSE_DT2"));
            row.setMsedt3(resultSet.getString("MSE_DT3"));

            String inv = resultSet.getString("MSE_INVALID_GROUP");
            if(inv!=null && !inv.equals("")){
            row.setMseinvalidgroup(Integer.valueOf(inv));
            }
            row.setLnstate(resultSet.getString("LN_STATE"));



            try {
                if(row.getServ1AGE()!=null && !row.getServ1AGE().equals("")) {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date date1 =  new java.sql.Date(format.parse(row.getServ1AGE()).getTime());
                    Date date2 = new java.sql.Date(format.parse(StartPeriod).getTime());

                    System.out.println(StartPeriod);
                    row.setServ1AGE(calculateAge(date2,date1,0));
                    row.setServ1MM(Integer.valueOf(calculateAge(date2,date1,2)));
                    //row.setServ1DT1(StartPeriod);
                    //row.setServ1DT2(EndPeriod);
                }

                if(row.getServ2AGE()!=null && !row.getServ2AGE().equals("")) {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date date1 =  new java.sql.Date(format.parse(row.getServ2AGE()).getTime());
                    Date date2 = new java.sql.Date(format.parse(StartPeriod).getTime());

                    row.setServ2AGE(calculateAge(date2,date1,0));
                    row.setServ2MM(Integer.valueOf(calculateAge(date2,date1,2)));
                   /* row.setServ1AGE(String.valueOf(calcYear(date1, date2)));
                    row.setServ2MM(calcMonth(date1, date2));*/
                    //row.setServ2DT1(StartPeriod);
                    //row.setServ2DT2(EndPeriod);
                }
            }catch (Exception e){ e.printStackTrace();}

            ln_results.add(ln_result);
            row.setLnresult(ln_results);
            row.setHospitalbreach(hospital_breaches);
            row.setTREAT_PERIODS(treat_full_periods);
            rows.add(row);
        }

        ROWSET rowset = new ROWSET();
        rowset.setAuthor("R.Kurbanov");
        rowset.setEmail("Rashgild@gmail.com");
        rowset.setPhone("89608634440");
        rowset.setSoftware("SignAndcypt");
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
        //List<PrParseFileLnLpu> prParseFileLnLpus = new ArrayList<>();
        //prParseFileLnLpus.add(prParseFilelnlpu);

        try {
            GlobalVariables.parser.saveObject(GlobalVariables.file, prParseFilelnlpu);
        } catch (JAXBException e) { e.printStackTrace();}

        return prParseFilelnlpu;
    }
    private static int calcMonth(Date aBirthDate, Date aFromDate){
        Calendar birth = Calendar.getInstance();
        birth.setTime(aBirthDate);
        Calendar from = Calendar.getInstance();
        from.setTime(aFromDate);

        int month = from.get(Calendar.MONTH) - birth.get(Calendar.MONTH);
        if(month<0) month=(month*-1);
        return month;
    }
    private static int calcYear(Date aBirthDate, Date aFromDate){
        Calendar birth = Calendar.getInstance();
        birth.setTime(aBirthDate);
        Calendar from = Calendar.getInstance();
        from.setTime(aFromDate);

        int year = from.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        if(year<0) year=(year*-1);
        return year;
    }

    public static String calculateAge(java.util.Date aDateFrom,java.util.Date aDateTo, int aFormat){
        /**
         * 0 - вернуть кол-во лет
         *  1 -
         *  2- вернуть кол-во месяцев
         */
        if (aDateFrom.getTime()<aDateTo.getTime()) return "Дата "+aDateFrom+" не может быть позже даты "+aDateTo ;
        Calendar d1 = Calendar.getInstance();
        Calendar d2 = Calendar.getInstance();
        d2.setTime(aDateTo);
        d1.setTime(aDateFrom);
        int year1 = d1.get(Calendar.YEAR) ; int year2 = d2.get(Calendar.YEAR) ;
        int month1 = d1.get(Calendar.MONTH) ; int month2 = d2.get(Calendar.MONTH) ;
        int day1 = d1.get(Calendar.DAY_OF_MONTH) ; int day2 = d2.get(Calendar.DAY_OF_MONTH) ;
        int day = day1 - day2 ;
        int tst;
        int month ;
        tst=0 ;
        if (day<0){
            Calendar d3 = Calendar.getInstance() ;
            d3.setTime(d1.getTime()) ;
            d3.set(Calendar.MONTH, month2) ;
            day=d3.getActualMaximum(Calendar.DAY_OF_MONTH)+day;tst=1 ;
        }
        month=month1-month2-tst;
        tst=0 ;
        if (month<0) {
            month=12+month ;
            tst=1 ;
        }
        int year=year1-year2-tst ;
        if (aFormat==0) return new StringBuilder().append(year).toString() ;
        if (aFormat==2) return new StringBuilder().append(month).toString() ;
        if (aFormat==1) return new StringBuilder().append(year)
                .append(".").append(month)
                .append(".").append(day).toString() ;
        String dy=(year==1)?" год ":
                ((year==0||year>4)?" лет ":" года ") ;
        String dm=(month==1)?" месяц ":
                (((month>1)&&(month<5))?" месяца ":" месяцев " );
        String dd = (day>4?" дней ":
                ((day==0||(day>10&&(day<15)))?" дней ":((day==1)?" день ":" дня ")));


        return new StringBuilder().append(year)
                .append(dy).append(month)
                .append(dm).append(day)
                .append(dd).toString() ;
    }

    private static SOAPMessage CreateSoapMessage(PrParseFileLnLpu prParseFileLnLpu){

        SOAPMessage message = null;
        try {

            Document document= GlobalVariables.parser.ObjToSoap(prParseFileLnLpu);
            MessageFactory mf = MessageFactory.newInstance();
            message  = mf.createMessage();


            String s = SoapMessageToString(message);
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

            return request;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    private static SOAPMessage Signation(PrParseFileLnLpu prParseFileLnLpu, SOAPMessage message) throws Exception {

        Logger logger=Logger.getLogger("");
        //logger.info("Signation");
        //logger.info(GlobalVariables.HDImageStorePath);

        List<ROW> rows = UnPack(prParseFileLnLpu);
        //org.apache.xml.security.Init.init();
        SaveSOAPToXML(signXMLFileName, message);
        for (ROW row:rows) {

            t_ELN = row.getLncode();
            //logger.info("SignationByParametrs");
            message = Sign.SignationByParametrs(
                    "http://eln.fss.ru/actor/mo/" + ogrnMo + "/" + row.getAttribId(),
                    "#" + row.getAttribId(), moAlias, moPass, t_ELN);
            SaveSOAPToXML(signXMLFileName, message);

            List<ROW.HOSPITAL_BREACH> hospital_breaches = row.getHospitalbreach();
            List<ROW.LN_RESULT> ln_results = row.getLnresult();
            ROW.LN_RESULT ln_result = ln_results.get(0);
            ROW.HOSPITAL_BREACH hospital_breach = hospital_breaches.get(0);

            if (ln_result.getAttribId() != null) {
                //System.out.println("Sign results");
                message = Sign.SignationByParametrs("http://eln.fss.ru/actor/doc/" + t_ELN + "_2_doc",
                        "#" + ln_result.getAttribId(), docAlias, docPass, t_ELN);
                SaveSOAPToXML(signXMLFileName, message);
            }

            if (hospital_breach.getAttributeId() != null) {
                //System.out.println("Sign hospital brach");
                message = Sign.SignationByParametrs("http://eln.fss.ru/actor/doc/" + t_ELN + "_1_doc",
                        "#" + hospital_breach.getAttributeId(), docAlias, docPass, t_ELN);
                SaveSOAPToXML(signXMLFileName, message);
            }
            TREAT_FULL_PERIOD treat_full_period;
            List<TREAT_FULL_PERIOD> treat_full_periods = row.getTREAT_PERIODS();
            TREAT_PERIOD treat_period;

            for(TREAT_FULL_PERIOD treat_full_per: treat_full_periods){

                treat_full_period = treat_full_per;
                if (treat_full_period.getAttribIdVk() != null && treat_full_period.getExport().equals("false")) {
                    message = Sign.SignationByParametrs("http://eln.fss.ru/actor/doc/" + treat_full_period.getAttribIdVk(),
                            "#" + treat_full_period.getAttribIdVk(), vkAlias, vkPass, t_ELN);
                    SaveSOAPToXML(signXMLFileName, message);
                }
                List<TREAT_PERIOD> treat_periods1 = treat_full_period.getTreat_period();

                for (TREAT_PERIOD num:treat_periods1) {
                      treat_period =num;
                    if (treat_period.getAttribId() != null && treat_full_period.getExport().equals("false")) {
                        message = Sign.SignationByParametrs("http://eln.fss.ru/actor/doc/" + treat_period.getAttribId(),
                                "#" + treat_period.getAttribId(), docAlias, docPass, t_ELN);
                        SaveSOAPToXML(signXMLFileName, message);
                    }
                }
            }
        }
        return message;
    }
    /**
     * распаковщик объекта
     **/
    private static List<ROW> UnPack(PrParseFileLnLpu prParseFileLnLpu) {
        List<PrParseFileLnLpu.Reqest> reqests = prParseFileLnLpu.getRequests();
        List<PrParseFileLnLpu.Reqest.pXmlFile> pXmlFiles = reqests.get(0).getpXmlFiles();
        List<ROWSET> rowsets = pXmlFiles.get(0).getRowset();
        return rowsets.get(0).getRow();
    }
}
