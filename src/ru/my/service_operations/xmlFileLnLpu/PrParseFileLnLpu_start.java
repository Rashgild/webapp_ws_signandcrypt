package ru.my.service_operations.xmlFileLnLpu;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import ru.my.entities.PrParseFileLnLpu;
import ru.my.entities.ROW;
import ru.my.entities.ROWSET;
import ru.my.entities.TREAT_FULL_PERIOD;
import ru.my.entities.TREAT_PERIOD;
import ru.my.signAndCrypt.Encrypt;
import ru.my.signAndCrypt.Sign;
import ru.my.utils.GlobalVariables;
import ru.my.utils.SQL;

import static ru.my.utils.GlobalVariables.DisabilityDocument_id;
import static ru.my.utils.GlobalVariables.cryptXMLFileName;
import static ru.my.utils.GlobalVariables.docAlias;
import static ru.my.utils.GlobalVariables.docPass;
import static ru.my.utils.GlobalVariables.moAlias;
import static ru.my.utils.GlobalVariables.moPass;
import static ru.my.utils.GlobalVariables.ogrnMo;
import static ru.my.utils.GlobalVariables.signXMLFileName;
import static ru.my.utils.GlobalVariables.t_ELN;
import static ru.my.utils.GlobalVariables.vkAlias;
import static ru.my.utils.GlobalVariables.vkPass;
import static ru.my.utils.StoredQuery.PrParse_Query1;
import static ru.my.utils.StoredQuery.PrParse_Query2;
import static ru.my.utils.XmlUtils.saveSoapToXml;
import static ru.my.utils.XmlUtils.soapMessageToString;

public class PrParseFileLnLpu_start {

    public static SOAPMessage Start(String disabilityId) {

        Logger logger = Logger.getLogger("simple");
        DisabilityDocument_id = disabilityId;
        GlobalVariables.setUp();

        logger.info("1)Formation skeleton");
        PrParseFileLnLpu prParseFileLnLpu = null;
        try {
            prParseFileLnLpu = createSkeleton(PrParse_Query1(disabilityId), PrParse_Query2(disabilityId));
        } catch (SQLException | ParseException e) {
            logger.error(e);
        }
        GlobalVariables.prparse = prParseFileLnLpu;

        logger.info("2)Create message");
        SOAPMessage message = createSoapMessage(prParseFileLnLpu);

        logger.info("3)Singing");
        try {
            message = signation(prParseFileLnLpu, message);
        } catch (Exception e) {
            logger.error(e);
        }

        logger.info("3.5) Prepatre request");
        GlobalVariables.Request = soapMessageToString(message);

        logger.info("4) Crypting");
        try {
            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage CryptedMessage = mf.createMessage();
            CryptedMessage = Encrypt.CreateXMLAndEncrypt(CryptedMessage, signXMLFileName);
            saveSoapToXml(cryptXMLFileName, CryptedMessage);

            return CryptedMessage;
        } catch (Exception e) {
            logger.error(e);
        }

        return message;
    }

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private static PrParseFileLnLpu createSkeleton(String sql1, String sql2) throws SQLException, ParseException {

        Logger logger = Logger.getLogger("simple");
        ResultSet resultSet = SQL.select(sql1);
        ResultSet resultSet2 = SQL.select(sql2);

        String StartPeriod = null;
        List<ROW> rows = new ArrayList<>();

        while (resultSet.next()) {
            GlobalVariables.t_ELN = resultSet.getString("LN_CODE");
            logger.info("ELN=" + t_ELN);
            int per = 3;
            int DDID_1 = resultSet.getInt("DDID");

            ROW.LN_RESULT ln_result = new ROW.LN_RESULT();
            boolean isClose = resultSet.getString("IS_CLOSE").equals("1");
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
                    if (StartPeriod == null) {
                        StartPeriod = resultSet2.getString("TREAT_DT1");
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
                    System.out.println("ISEXPORT>>>>>>>" + isexport);
                    if (isexport != null && (isexport.equals("true") || isexport.equals("t"))) {
                        treat_full_period.setExport("true");
                    } else {
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

            //Если документ не закрыт, то даты нет
            if (isClose && ln_result.getMseresult() != null) {
                if (!ln_result.getMseresult().equals("31") && !ln_result.getMseresult().equals("37")) {

                    if (ln_result.getOtherstatedt() != null && !ln_result.getOtherstatedt().equals("")) {
                        ln_result.setReturndatelpu(null);
                    } else {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(format.parse(ln_result.getReturndatelpu()));
                        cal.add(Calendar.DAY_OF_MONTH, 1);
                        ln_result.setReturndatelpu(new java.sql.Date(cal.getTime().getTime()).toString());
                    }
                } else { //Если закрыт по причине "продолжает болеть"
                    ln_result.setReturndatelpu(null);
                    ln_result.setNextlncode(resultSet.getString("NEXT_LN_CODE"));
                }
                ln_result.setAttribId("ELN_" + t_ELN + "_2_doc");
            } else if (!isClose) ln_result.setReturndatelpu(null);

            logger.info("Закрыт: " + isClose + "" +
                    " Дата выхода на работу:" + ln_result.getReturndatelpu() + "");

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

            if (!GlobalVariables.hash.equals("")) {
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
            row.setDuplicateflag(resultSet.getInt("DUPLICATE_FLAG"));
            row.setLndate(resultSet.getString("LN_DATE"));
            row.setLpuname(resultSet.getString("LPU_NAME"));
            row.setLpuaddress(resultSet.getString("LPU_ADDRESS"));
            row.setLpuogrn(resultSet.getString("LPU_OGRN"));
            row.setBirthday(resultSet.getString("BIRTHDAY"));
            row.setGender(resultSet.getInt("GENDER"));

            String reason = resultSet.getString("REASON1");
            if (reason.equals("1")) reason = "01";
            if (reason.equals("2")) reason = "02";
            if (reason.equals("3")) reason = "03";
            if (reason.equals("4")) reason = "04";
            if (reason.equals("5")) reason = "05";
            if (reason.equals("6")) reason = "06";
            if (reason.equals("7")) reason = "07";
            if (reason.equals("8")) reason = "08";
            if (reason.equals("9")) reason = "09";

            row.setReason1(reason);
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
            if (inv != null && !inv.equals("")) {
                row.setMseinvalidgroup(Integer.valueOf(inv));
            }
            row.setLnstate(resultSet.getString("LN_STATE"));

            try {
                if (row.getServ1AGE() != null && !row.getServ1AGE().equals("")) {
                    LocalDate dateFrom = LocalDate.parse(row.getServ1AGE());
                    LocalDate dateTo = LocalDate.parse(StartPeriod);
                    int years = calculateCurrentYears(dateFrom,dateTo);
                    if (years<1) {
                        row.setServ1MM(calculateCurrentMonths(dateFrom, dateTo));
                    }else {
                        row.setServ1AGE(String.valueOf(years));
                    }
                }

                if (row.getServ2AGE() != null && !row.getServ2AGE().equals("")) {
                    LocalDate dateFrom = LocalDate.parse(row.getServ2AGE());
                    LocalDate dateTo = LocalDate.parse(StartPeriod);
                    int years = calculateCurrentYears(dateFrom,dateTo);
                    if (years<1) {
                        row.setServ2MM(calculateCurrentMonths(dateFrom, dateTo));
                    }else {
                        row.setServ2AGE(String.valueOf(years));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

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
        rowset.setSoftware("SignAndCrypt");
        rowset.setVersion("1.1");
        rowset.setVersionSoftware("1.1");
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
            GlobalVariables.parser.saveObject(GlobalVariables.file, prParseFilelnlpu);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return prParseFilelnlpu;
    }

    private static int calculateCurrentYears(LocalDate dateFrom, LocalDate dateTo){
        return (int) ChronoUnit.YEARS.between(dateFrom, dateTo);
    }

    private static int calculateCurrentMonths(LocalDate dateFrom, LocalDate dateTo){
        return (int) ChronoUnit.MONTHS.between(dateFrom, dateTo);
    }

    public static String calculateAge(java.util.Date aDateFrom, java.util.Date aDateTo, int aFormat) {
        /**
         * 0 - вернуть кол-во лет
         *  1 -
         *  2- вернуть кол-во месяцев
         */
        if (aDateFrom.getTime() < aDateTo.getTime())
            return "Дата " + aDateFrom + " не может быть позже даты " + aDateTo;
        Calendar d1 = Calendar.getInstance();
        Calendar d2 = Calendar.getInstance();
        d2.setTime(aDateTo);
        d1.setTime(aDateFrom);
        int year1 = d1.get(Calendar.YEAR);
        int year2 = d2.get(Calendar.YEAR);
        int month1 = d1.get(Calendar.MONTH);
        int month2 = d2.get(Calendar.MONTH);
        int day1 = d1.get(Calendar.DAY_OF_MONTH);
        int day2 = d2.get(Calendar.DAY_OF_MONTH);
        int day = day1 - day2;
        int tst;
        int month;
        tst = 0;
        if (day < 0) {
            Calendar d3 = Calendar.getInstance();
            d3.setTime(d1.getTime());
            d3.set(Calendar.MONTH, month2);
            day = d3.getActualMaximum(Calendar.DAY_OF_MONTH) + day;
            tst = 1;
        }
        month = month1 - month2 - tst;
        tst = 0;
        if (month < 0) {
            month = 12 + month;
            tst = 1;
        }
        int year = year1 - year2 - tst;
        if (aFormat == 0) {
            return String.valueOf(year);
        }
        if (aFormat == 2) {
            return String.valueOf(month);
        }
        if (aFormat == 1) {
            return String.valueOf(year) +
                    "." + month +
                    "." + day;
        }
        String dy = (year == 1) ? " год " :
                ((year == 0 || year > 4) ? " лет " : " года ");
        String dm = (month == 1) ? " месяц " :
                (((month > 1) && (month < 5)) ? " месяца " : " месяцев ");
        String dd = (day > 4 ? " дней " :
                ((day == 0 || (day > 10 && (day < 15))) ? " дней " : ((day == 1) ? " день " : " дня ")));


        return String.valueOf(year) +
                dy + month +
                dm + day +
                dd;
    }

    private static SOAPMessage createSoapMessage(PrParseFileLnLpu prParseFileLnLpu) {

        SOAPMessage message = null;
        try {

            Document document = GlobalVariables.parser.objToSoap(prParseFileLnLpu);
            MessageFactory mf = MessageFactory.newInstance();
            message = mf.createMessage();


            String s = soapMessageToString(message);
            s = s.replace("SOAP-ENV", "soapenv");
            InputStream is = new ByteArrayInputStream(s.getBytes());
            SOAPMessage request = MessageFactory.newInstance().createMessage(null, is);

            SOAPEnvelope soapEnv = request.getSOAPPart().getEnvelope();
            SOAPBody soapBody = soapEnv.getBody();
            soapBody.addDocument(document);
            soapEnv.addNamespaceDeclaration("ds", "http://www.w3.org/2000/09/xmldsig#");
            soapEnv.addNamespaceDeclaration("wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
            soapEnv.addNamespaceDeclaration("wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
            soapEnv.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
            soapEnv.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
            soapEnv.addNamespaceDeclaration("fil", "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl");

            return request;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    private static final String gost2001="GOST2001";
    private static final String gost2012="GOST2012";

    private static SOAPMessage signation(PrParseFileLnLpu prParseFileLnLpu, SOAPMessage message) throws Exception {
            List<ROW> rows = unPack(prParseFileLnLpu);
            saveSoapToXml(signXMLFileName, message);
            for (ROW row : rows) {

                t_ELN = row.getLncode();
                message = Sign.signationByParametrs(
                        "http://eln.fss.ru/actor/mo/" + ogrnMo + "/" + row.getAttribId(),
                        "#" + row.getAttribId(), moAlias, moPass, t_ELN, gost2012);
                saveSoapToXml(signXMLFileName, message);

                List<ROW.HOSPITAL_BREACH> hospital_breaches = row.getHospitalbreach();
                List<ROW.LN_RESULT> ln_results = row.getLnresult();
                ROW.LN_RESULT ln_result = ln_results.get(0);
                ROW.HOSPITAL_BREACH hospital_breach = hospital_breaches.get(0);

                if (ln_result.getAttribId() != null) {
                    message = Sign.signationByParametrs("http://eln.fss.ru/actor/doc/" + t_ELN + "_2_doc",
                            "#" + ln_result.getAttribId(), docAlias, docPass, t_ELN, gost2001);
                    saveSoapToXml(signXMLFileName, message);
                }

                if (hospital_breach.getAttributeId() != null) {
                    message = Sign.signationByParametrs("http://eln.fss.ru/actor/doc/" + t_ELN + "_1_doc",
                            "#" + hospital_breach.getAttributeId(), docAlias, docPass, t_ELN, gost2001);
                    saveSoapToXml(signXMLFileName, message);
                }
                TREAT_FULL_PERIOD treat_full_period;
                List<TREAT_FULL_PERIOD> treat_full_periods = row.getTREAT_PERIODS();
                TREAT_PERIOD treat_period;

                for (TREAT_FULL_PERIOD treat_full_per : treat_full_periods) {
                    treat_full_period = treat_full_per;

                    if (treat_full_period.getAttribIdVk() != null && treat_full_period.getExport().equals("false")) {
                        message = Sign.signationByParametrs("http://eln.fss.ru/actor/doc/" + treat_full_period.getAttribIdVk(),
                                "#" + treat_full_period.getAttribIdVk(), vkAlias, vkPass, t_ELN, gost2001);
                        saveSoapToXml(signXMLFileName, message);
                    }
                    List<TREAT_PERIOD> treat_periods1 = treat_full_period.getTreat_period();

                    for (TREAT_PERIOD num : treat_periods1) {
                        treat_period = num;

                        if (treat_period.getAttribId() != null && treat_full_period.getExport().equals("false")) {
                            message = Sign.signationByParametrs("http://eln.fss.ru/actor/doc/" + treat_period.getAttribId(),
                                    "#" + treat_period.getAttribId(), docAlias, docPass, t_ELN, gost2001);
                            saveSoapToXml(signXMLFileName, message);
                        }
                    }
                }
            }
            return message;
    }

    /**
     * распаковщик объекта.
     *
     * @param prParseFileLnLpu prParseFileLnLpu
     * @return List<ROW>
     */
    private static List<ROW> unPack(PrParseFileLnLpu prParseFileLnLpu) {
        List<PrParseFileLnLpu.Reqest> reqests = prParseFileLnLpu.getRequests();
        List<PrParseFileLnLpu.Reqest.pXmlFile> pXmlFiles = reqests.get(0).getpXmlFiles();
        List<ROWSET> rowsets = pXmlFiles.get(0).getRowset();
        return rowsets.get(0).getRow();
    }
}
