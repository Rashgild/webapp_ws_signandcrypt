package ru.rashgild.service;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import ru.rashgild.generated.v2.types.eln.mo.v01.PrParseFilelnlpuRequest;
import ru.rashgild.generated.v2.types.eln.mo.v01.Rowset;
import ru.rashgild.generated.v2.types.eln.v01.TreatFullPeriodMo;
import ru.rashgild.signAndCrypt.Encrypt;
import ru.rashgild.signAndCrypt.Sign;
import ru.rashgild.utils.CertificateUtils;
import ru.rashgild.utils.GlobalVariables;
import ru.rashgild.utils.SQL;

import javax.xml.bind.JAXBException;
import javax.xml.soap.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.X509Certificate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static ru.rashgild.utils.CertificateUtils.addCertificateToHeader;
import static ru.rashgild.utils.GlobalVariables.*;
import static ru.rashgild.utils.StoredQuery.prParseQuery1;
import static ru.rashgild.utils.StoredQuery.prParseQuery2;
import static ru.rashgild.utils.XmlUtils.saveSoapToXml;
import static ru.rashgild.utils.XmlUtils.soapMessageToString;

public class PrParseFileLnLpu {

    private static final String GOST_2001 = "GOST2001";
    private static final String GOST_2012 = "GOST2012";

    public static SOAPMessage start(String disabilityId) {
        Logger logger = Logger.getLogger("PrParseFileLnLpu_start");
        DisabilityDocument_id = disabilityId;
        GlobalVariables.setUp();
        logger.info("1)Formation skeleton");
        PrParseFilelnlpuRequest prParseFilelnlpuRequest = null;
        try {
            prParseFilelnlpuRequest = createSkeleton(prParseQuery1(disabilityId), prParseQuery2(disabilityId));
        } catch (SQLException | ParseException e) {
            logger.error(e);
        }
        GlobalVariables.prparse = prParseFilelnlpuRequest;
        logger.info("2)Create message");
        SOAPMessage message = createSoapMessage(prParseFilelnlpuRequest);
        logger.info("3)Signing");

        try {
            message = signDocument(prParseFilelnlpuRequest, message);
            message = addCertificateToHeader(message);
        } catch (Exception e) {
            logger.error(e);
        }

        logger.info("3.5) Prepare request");
        GlobalVariables.Request = soapMessageToString(message);

        logger.info("4) Crypting");
        try {
            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage CryptedMessage = mf.createMessage();
            CryptedMessage = Encrypt.createXmlAndEncrypt(CryptedMessage, signXMLFileName);
            saveSoapToXml(cryptXMLFileName, CryptedMessage);

            return CryptedMessage;
        } catch (Exception e) {
            logger.error(e);
        }

        return message;
    }

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private static PrParseFilelnlpuRequest createSkeleton(String sql1, String sql2) throws SQLException, ParseException {
        Logger logger = Logger.getLogger("simple");
        ResultSet resultSet = SQL.select(sql1);
        ResultSet resultSet2 = SQL.select(sql2);

        String StartPeriod = null;
        List<Rowset.Row> rows = new ArrayList<>();

        while (resultSet.next()) {
            GlobalVariables.t_ELN = resultSet.getString("LN_CODE");
            logger.info("ELN=" + t_ELN);
            int per = 3;
            int DDID_1 = resultSet.getInt("DDID");

            Rowset.Row.LnResult ln_result = new Rowset.Row.LnResult();
            boolean isClose = resultSet.getString("IS_CLOSE").equals("1");
            ln_result.setMseResult(resultSet.getString("MSE_RESULT"));
            ln_result.setOtherStateDt(resultSet.getString("other_state_dt"));

            List<TreatFullPeriodMo> treat_full_periods = new ArrayList<>();
            while (resultSet2.next()) {
                //TODO CheckIT!
                String isExport = resultSet2.getString("isexport");
                int DDID_2 = resultSet2.getInt("DDID");
                if (DDID_1 == DDID_2) {
                    TreatFullPeriodMo.TreatPeriod treatPeriod = new TreatFullPeriodMo.TreatPeriod();
                    treatPeriod.setTreatDt1(resultSet2.getString("TREAT_DT1"));
                    if (StartPeriod == null) {
                        StartPeriod = resultSet2.getString("TREAT_DT1");
                    }
                    //EndPeriod = resultSet2.getString("TREAT_DT2");
                    treatPeriod.setTreatDt2(resultSet2.getString("TREAT_DT2"));
                    ln_result.setReturnDateLpu(resultSet2.getString("TREAT_DT2"));//берем день выхода на работу
                    treatPeriod.setTreatDoctorRole(resultSet2.getString("TREAT_DOCTOR_ROLE"));
                    treatPeriod.setTreatDoctor(resultSet2.getString("TREAT_DOCTOR"));
                    treatPeriod.setId("ELN_" + t_ELN + "_" + per + "_doc");

                    TreatFullPeriodMo treatFullPeriodMo = new TreatFullPeriodMo();

                    treatFullPeriodMo.setTreatChairmanRole(resultSet2.getString("TREAT_CHAIRMAN_ROLE"));
                    treatFullPeriodMo.setTreatChairman(resultSet2.getString("TREAT_CHAIRMAN"));
                    if (treatFullPeriodMo.getTreatChairmanRole() != null) {
                        treatFullPeriodMo.setId("ELN_" + t_ELN + "_" + per + "_vk");
                    }

                    //TODO А это надо?
                    /*System.out.println("ISEXPORT>>>>>>>" + isExport);
                    if (isExport != null && (isExport.equals("true") || isExport.equals("t"))) {
                        treatFullPeriodMo.setExport("true");
                    } else {
                        treatFullPeriodMo.setExport("false");
                    }*/

                    treatFullPeriodMo.setTreatPeriod(treatPeriod);
                    treat_full_periods.add(treatFullPeriodMo);
                    per++;
                }
            }
            Rowset.Row.TreatPeriods treatFullPeriod = new Rowset.Row.TreatPeriods();
            treatFullPeriod.setTreatFullPeriod(treat_full_periods);

            resultSet2.beforeFirst(); // возврат курсора в начало
            Rowset.Row.HospitalBreach hospitalBreach = new Rowset.Row.HospitalBreach();
            hospitalBreach.setHospitalBreachCode(resultSet.getString("HOSPITAL_BREACH_CODE"));
            hospitalBreach.setHospitalBreachDt(resultSet.getString("HOSPITAL_BREACH_DT"));
            if (hospitalBreach.getHospitalBreachCode() != null) {
                hospitalBreach.setId("ELN_" + t_ELN + "_1_doc");
            }

            //Если документ не закрыт, то даты нет
            if (isClose && ln_result.getMseResult() != null) {
                if (!ln_result.getMseResult().equals("31") && !ln_result.getMseResult().equals("37")) {

                    if (ln_result.getOtherStateDt() != null && !ln_result.getOtherStateDt().equals("")) {
                        ln_result.setReturnDateLpu(null);
                    } else {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(format.parse(ln_result.getReturnDateLpu()));
                        cal.add(Calendar.DAY_OF_MONTH, 1);
                        ln_result.setReturnDateLpu(new java.sql.Date(cal.getTime().getTime()).toString());
                    }
                } else { //Если закрыт по причине "продолжает болеть"
                    ln_result.setReturnDateLpu(null);
                    ln_result.setNextLnCode(resultSet.getString("NEXT_LN_CODE"));
                }
                ln_result.setId("ELN_" + t_ELN + "_2_doc");
            } else if (!isClose) ln_result.setReturnDateLpu(null);

            logger.info("Закрыт: " + isClose + "" +
                    " Дата выхода на работу:" + ln_result.getReturnDateLpu() + "");

            Rowset.Row row = new Rowset.Row();

            String snils = resultSet.getString("SNILS");
            snils = splitSnils(snils);

            //row.setIdDD(DDID_1);
            row.setHospitalDt2("ELN_" + t_ELN);
            row.setSnils(snils);

            if (!GlobalVariables.hash.isEmpty()) {
                row.setLnHash(GlobalVariables.hash);
            }
            row.setSurname(resultSet.getString("SURNAME"));
            row.setName(resultSet.getString("NAME"));
            row.setPatronymic(resultSet.getString("PATRONIMIC"));

            //row.setBozflag(resultSet.getInt("BOZ_FLAG"));
            //row.setLpuemployer(resultSet.getString("LPU_EMPLOYER"));
            //row.setLpuemplflag(resultSet.getInt("LPU_EMPL_FLAG"));
            //row.setPa(resultSet.getString("PARENT_CODE"));
            row.setLnCode(resultSet.getString("LN_CODE"));
            row.setPrevLnCode(resultSet.getString("PREV_LN"));
            row.setPrimaryFlag(resultSet.getBoolean("PRIMARY_FLAG"));
            row.setPreviouslyIssuedCode(resultSet.getString("PREVIOUS_ISSUE_CODE"));
            row.setDuplicateFlag(resultSet.getBoolean("DUPLICATE_FLAG"));
            row.setLnDate(resultSet.getString("LN_DATE"));
            row.setLpuName(resultSet.getString("LPU_NAME"));
            row.setLpuAddress(resultSet.getString("LPU_ADDRESS"));
            row.setLpuOgrn(resultSet.getString("LPU_OGRN"));
            row.setBirthday(resultSet.getString("BIRTHDAY"));
            row.setGender(resultSet.getInt("GENDER"));
            row.setReason1(resultSet.getString("REASON1"));
            row.setReason2(resultSet.getString("REASON2"));
            String diag = resultSet.getString("DIAGNOS");
            row.setDiagnos(diag == null ? "0000000000" : diag);
            row.setDate1(resultSet.getString("DATE1"));
            row.setDate2(resultSet.getString("DATE2"));
            //row.setVoucherNo(resultSet.getString("VOUCHER_NO"));
            //row.setVoucherOgrn(resultSet.getString("VOUCHER_OGRN"));

            //row.setPregn12WFlag(resultSet.getBoolean("PREGN12W_FLAG"));
            row.setHospitalDt1(resultSet.getString("HOSPITAL_DT1"));
            row.setHospitalDt2(resultSet.getString("HOSPITAL_DT2"));
            row.setMseDt1(resultSet.getString("MSE_DT1"));
            row.setMseDt2(resultSet.getString("MSE_DT2"));
            row.setMseDt3(resultSet.getString("MSE_DT3"));
            row.setWrittenAgreementFlag(true);

            String inv = resultSet.getString("MSE_INVALID_GROUP");
            if (inv != null && !inv.isEmpty()) {
                row.setMseInvalidGroup(Integer.valueOf(inv));
            }
            row.setLnState(resultSet.getString("LN_STATE"));

            //TODO проверить что тут творится
           /* Rowset.Row.ServData.ServFullData servFullData = new Rowset.Row.ServData.ServFullData();
            servFullData.setServRelationCode(resultSet.getString("SERV1_RELATION_CODE"));
            servFullData.setBirthday(resultSet.getString("SERV1_AGE"));
            servFullData.setName(resultSet.getString("SERV1_FIO"));
            servFullData.setSurname(resultSet.getString("SERV1_FIO"));
            servFullData.setPatronymic(resultSet.getString("SERV1_FIO"));

            List<Rowset.Row.ServData.ServFullData> servFullDataList = new ArrayList<>();
            servFullDataList.add(servFullData);
            Rowset.Row.ServData servData = new Rowset.Row.ServData();
            servData.setServFullData(servFullDataList);

            row.setServData(servData);*/
            row.setLnResult(ln_result);

            if (hospitalBreach.getId() != null) {
                row.setHospitalBreach(hospitalBreach);
            }

            row.setTreatPeriods(treatFullPeriod);
            row.setId("ELN_" + t_ELN);
            rows.add(row);
        }

        Rowset rowset = new Rowset();
        //rowset.setAuthor("R.Kurbanov");
        //rowset.setEmail("Rashgild@gmail.com");
        //rowset.setPhone("89608634440");
        rowset.setSoftware("SignAndCrypt");
        rowset.setVersion("2.0");
        rowset.setVersionSoftware("2.0");
        rowset.setRow(rows);
        /*List<Rowset> rowsets = new ArrayList<>();
        rowsets.add(rowset);*/

        PrParseFilelnlpuRequest.PXmlFile pXmlFile = new PrParseFilelnlpuRequest.PXmlFile();
        pXmlFile.setRowset(rowset);
        //*List<PrParseFilelnlpuRequest.Reqest.pXmlFile> pXmlFiles = new ArrayList<>();
        //pXmlFiles.add(pXmlFile);

        PrParseFilelnlpuRequest prParseFilelnlpu = new PrParseFilelnlpuRequest();
        prParseFilelnlpu.setPXmlFile(pXmlFile);
        prParseFilelnlpu.setOgrn(ogrnMo);

        try {
            GlobalVariables.parser.saveObject(GlobalVariables.file, prParseFilelnlpu);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return prParseFilelnlpu;
    }

    private static String splitSnils(String snils) {
        String str[];
        str = snils.split("-");
        snils = str[0] + str[1] + str[2];
        str = snils.split(" ");
        return str[0] + str[1];
    }

    private static String getReason(String reason) {
        if (reason != null) {
            switch (reason) {
                case "1":
                    return "01";
                case "2":
                    return "02";
                case "3":
                    return "03";
                case "4":
                    return "04";
                case "5":
                    return "05";
                case "6":
                    return "06";
                case "7":
                    return "07";
                case "8":
                    return "08";
                case "9":
                    return "09";
                default:
                    return null;
            }
        }
        return null;
    }

    private static SOAPMessage createSoapMessage(PrParseFilelnlpuRequest prParseFilelnlpuRequest) {
        SOAPMessage message = null;
        try {
            Document document = GlobalVariables.parser.objToSoap(prParseFilelnlpuRequest);
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
            soapEnv.addNamespaceDeclaration("fil", "http://www.fss.ru/integration/types/eln/mo/v01");

            return request;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    private static SOAPMessage signDocument(PrParseFilelnlpuRequest prParseFilelnlpuRequest, SOAPMessage message) throws Exception {
        List<Rowset.Row> rows = unPack(prParseFilelnlpuRequest);
        saveSoapToXml(signXMLFileName, message);
        for (Rowset.Row row : rows) {
            t_ELN = row.getLnCode();
            message = Sign.signationByParametrs(
                    "http://eln.fss.ru/actor/mo/" + ogrnMo + "/" + row.getId(),
                    "#" + row.getId(), moAlias, moPass, t_ELN, moGost.equals("2012") ? GOST_2012 : GOST_2001);
            saveSoapToXml(signXMLFileName, message);

            Rowset.Row.HospitalBreach hospitalBreach = row.getHospitalBreach();
            Rowset.Row.LnResult lnResult = row.getLnResult();

            if (lnResult != null && lnResult.getId() != null) {
                message = Sign.signationByParametrs("http://eln.fss.ru/actor/doc/" + t_ELN + "_2_doc",
                        "#" + lnResult.getId(), docAlias, docPass, t_ELN, docGost.equals("2012") ? GOST_2012 : GOST_2001);
                saveSoapToXml(signXMLFileName, message);
            }

            if (hospitalBreach != null && hospitalBreach.getId() != null) {
                message = Sign.signationByParametrs("http://eln.fss.ru/actor/doc/" + t_ELN + "_1_doc",
                        "#" + hospitalBreach.getId(), docAlias, docPass, t_ELN, docGost.equals("2012") ? GOST_2012 : GOST_2001);
                saveSoapToXml(signXMLFileName, message);
            }

            Rowset.Row.TreatPeriods treat_full_periods = row.getTreatPeriods();
            for (TreatFullPeriodMo treatFullPeriodMo : treat_full_periods.getTreatFullPeriod()) {
                if (treatFullPeriodMo != null && treatFullPeriodMo.getId() != null) {
                    message = Sign.signationByParametrs("http://eln.fss.ru/actor/doc/" + treatFullPeriodMo.getId(),
                            "#" + treatFullPeriodMo.getId(), vkAlias, vkPass, t_ELN, vkGost.equals("2012") ? GOST_2012 : GOST_2001);
                    saveSoapToXml(signXMLFileName, message);
                }

                if (treatFullPeriodMo != null) {
                    TreatFullPeriodMo.TreatPeriod treatPeriod = treatFullPeriodMo.getTreatPeriod();
                    if (treatPeriod != null && treatPeriod.getId() != null) {
                        message = Sign.signationByParametrs("http://eln.fss.ru/actor/doc/" + treatPeriod.getId(),
                                "#" + treatPeriod.getId(), docAlias, docPass, t_ELN, docGost.equals("2012") ? GOST_2012 : GOST_2001);
                        saveSoapToXml(signXMLFileName, message);
                    }
                }
            }

            X509Certificate cert = CertificateUtils.getCertificateFromKeyStorage(GlobalVariables.moAlias);
            SOAPEnvelope soapEnvelope = message.getSOAPPart().getEnvelope();
            SOAPHeader header1 = soapEnvelope.getHeader();
            SOAPElement x509Certificate = header1.addChildElement("X509Certificate", null, "http://www.w3.org/2000/09/xmldsig#");
            x509Certificate.addTextNode(CertificateUtils.certToBase64(cert));
            saveSoapToXml(signXMLFileName, message);
        }
        return message;
    }

    /**
     * распаковщик объекта.
     *
     * @param prParseFilelnlpuRequest prParseFileLnLpu
     * @return List<ROW>
     */
    private static List<Rowset.Row> unPack(PrParseFilelnlpuRequest prParseFilelnlpuRequest) {
        PrParseFilelnlpuRequest.PXmlFile pXmlFiles = prParseFilelnlpuRequest.getPXmlFile();
        Rowset rowset = pXmlFiles.getRowset();
        return rowset.getRow();
    }
}
