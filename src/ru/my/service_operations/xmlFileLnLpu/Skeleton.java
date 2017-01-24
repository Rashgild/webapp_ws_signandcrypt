package ru.my.service_operations.xmlFileLnLpu;

import org.apache.log4j.Logger;
import ru.my.entities.*;
import ru.my.helpers_operations.GlobalVariables;
import ru.my.helpers_operations.SQL;

import javax.xml.bind.JAXBException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static ru.my.helpers_operations.GlobalVariables.ogrnMo;
import static ru.my.helpers_operations.GlobalVariables.t_ELN;

/**
 * Created by rkurbanov on 22.05.2017.
 */
public class Skeleton {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    protected static PrParseFileLnLpu Create(String sql1, String sql2) throws SQLException, ParseException {

        Logger logger=Logger.getLogger("simple");
        logger.info("Do first SQL-Request");
        ResultSet resultSet = SQL.Query(sql1);

        logger.info("Do second SQL-Request");
        ResultSet resultSet2 = SQL.Query(sql2);

        List<ROW> rows = new ArrayList<>();
        while (resultSet.next()) {
             GlobalVariables.t_ELN = resultSet.getString("LN_CODE");
            logger.info("ELN="+t_ELN);
            int per = 3;
            int DDID_1 = resultSet.getInt("DDID");


            ROW.LN_RESULT ln_result = new ROW.LN_RESULT();
            ln_result.setMseresult(resultSet.getString("MSE_RESULT"));
            ln_result.setOtherstatedt(resultSet.getString("other_state_dt"));

            List<TREAT_FULL_PERIOD> treat_full_periods = new ArrayList<>();
            while (resultSet2.next()) {

                String ln_ReturnDay;
                int DDID_2 = resultSet2.getInt("DDID");
                if (DDID_1 == DDID_2) {
                    TREAT_PERIOD treat_period = new TREAT_PERIOD();
                    treat_period.setTreatdt1(resultSet2.getString("TREAT_DT1"));
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




            if(!ln_result.getReturndatelpu().equals("") && ln_result.getReturndatelpu()!=null )
            {
                Calendar cal = Calendar.getInstance();
                cal.setTime(format.parse(ln_result.getReturndatelpu()));  //.parse(returnDate));
                cal.add(Calendar.DAY_OF_MONTH, 1);
                ln_result.setReturndatelpu(new java.sql.Date(cal.getTime().getTime()).toString());
            }
            //Если закрыт по причине "продолжает болеть"
            if(ln_result.getMseresult().equals("31") || ln_result.getMseresult().equals("37"))
            {
                ln_result.setNextlncode("000000000000");
            }else
            {
                ln_result.setNextlncode(resultSet.getString("NEXT_LN_CODE"));
            }

            if ( (ln_result.getMseresult() != null && !ln_result.getMseresult().equals(""))
                    || (ln_result.getReturndatelpu() !=null && !ln_result.getReturndatelpu().equals("")) ) {
                ln_result.setAttribId("ELN_"+t_ELN+"_2_doc");
            }
            List<ROW.LN_RESULT> ln_results = new ArrayList<>();


            ROW row = new ROW(); //новый экземпляр row

            //Заполняем
            ///Снилс приводм к нужному виду (без тире)
            String str[];
            String snils = resultSet.getString("SNILS");
            str = snils.split("-");
            snils = str[0] + str[1] + str[2];
            str = snils.split(" ");
            snils = str[0] + str[1];

            row.setIdDD(DDID_1);
            row.setAttribId("ELN_" + t_ELN);
            row.setSnils(snils);
            row.setSurname(resultSet.getString("SURNAME"));
            row.setName(resultSet.getString("NAME"));
            row.setPatronimic(resultSet.getString("PATRONIMIC"));
            row.setBozflag(resultSet.getInt("BOZ_FLAG"));
            row.setLpuemployer(resultSet.getString("LPU_EMPLOYER"));
            row.setLpuemplflag(resultSet.getInt("LPU_EMPL_FLAG"));
            row.setLncode(resultSet.getString("LN_CODE"));
            row.setPrevlncode(resultSet.getString("PREV_LN"));

            row.setPrimaryflag(resultSet.getInt("PRIMARY_FLAG"));
            int primaryFlag =  row.getPrimaryflag();
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

            row.setLnstate(resultSet.getString("LN_STATE"));


            //Если документ не закрыт, то даты нет
            if(row.getLnstate().equals("") || row.getLnstate() == null){
                ln_result.setReturndatelpu("");
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
        List<PrParseFileLnLpu> prParseFileLnLpus = new ArrayList<>();
        prParseFileLnLpus.add(prParseFilelnlpu);


        try {
            GlobalVariables.parser.saveObject(GlobalVariables.file, prParseFilelnlpu);
        } catch (JAXBException e) { e.printStackTrace();}

        // GlobalVariables.parser.saveObject("tempSceleton.xml", prParseFilelnlpu);
        return prParseFilelnlpu;

    }
}
