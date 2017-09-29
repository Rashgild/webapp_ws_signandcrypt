package ru.my.servlets;

import org.apache.log4j.Logger;
import ru.ibs.fss.ln.ws.fileoperationsln.*;
import ru.my.helpers_operations.GlobalVariables;
import ru.my.helpers_operations.SQL;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ru.my.helpers_operations.GlobalVariables.ogrnMo;

/**
 * Created by rkurbanov on 24.08.2017.
 */

@WebServlet("/sImportLNN")
public class sImportLNN extends HttpServlet {

    private void Out(HttpServletResponse response, String string){
        PrintWriter out = null;
        try {
            out = response.getWriter();

        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(string);
    }

    private boolean isExistNumber(String ELN){
        ResultSet rs =  SQL.Query("select id from disabilitydocument where number='"+ELN+"'");
        int idDoc=0;
        try {
            while (rs.next()){
                idDoc = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //idDoc>0 ? return true :return false;
        if (idDoc>0) return true; else return false;
    }

    private String checkLPU(String OGRN){
        ResultSet rs =  SQL.Query("Select id from mislpu where ogrn='"+OGRN+"'");
        String idLpu="";
        try {
            while (rs.next()){
                idLpu = rs.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(!idLpu.equals("")){
            return idLpu;
        }else {
            return "";
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            Logger logger = Logger.getLogger("simple");
            logger.info("1) NewLnNum");
            response.setContentType("text/html ;charset=UTF-8");

            String ogrn = request.getParameter("ogrn");
            String eln = request.getParameter("eln");
            String snils = request.getParameter("snils");
            String pid = request.getParameter("pid");

            String outString = "";
            outString += "<html><head><meta charset=\"UTF-8\"/><title>SignAndCrypt</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"></head><body>";

            PrintWriter out = response.getWriter();
            Out(response, outString);
            out.print("<H1> ogrn=" + ogrn + "</H1>");
            out.print("<H1> eln=" + eln + "</H1>");
            out.print("<H1> snils=" + snils + "</H1>");

            System.setProperty("javax.net.ssl.trustStore", GlobalVariables.pathandnameSSL);//КОНФ
            System.setProperty("javax.net.ssl.trustStorePassword", GlobalVariables.passwordSSL);
            FileOperationsLnImplService service = new FileOperationsLnImplService();
            FileOperationsLn start = service.getFileOperationsLnPort();
            try {

                FileOperationsLnUserGetLNDataOut fget = start.getLNData(ogrn, eln, snils);
                ru.ibs.fss.ln.ws.fileoperationsln.ROW row = fget.getDATA().getOUTROWSET().getROW().get(0);


                if (!isExistNumber(eln)) {
                    String idDisCase = SQL.Insert_returning("INSERT into disabilitycase (patient_id, createdate,createusername) values (" + pid + ", current_date, 'Importer') RETURNING id");
                    System.out.println("idDisCase>>" + idDisCase);

                    String insertDisDoc = "insert INTO disabilitydocument (";
                    String insertDisDovValues = "Values(";

                    String lpuOGRN = row.getLPUOGRN();
                    String AnotherLPUid = "";
                    if (ogrnMo.equals(lpuOGRN)) {
                        System.out.println("lpuOGRN>>>>" + lpuOGRN);
                    } else {
                        if (!checkLPU(lpuOGRN).equals("")) {
                            AnotherLPUid = checkLPU(lpuOGRN);
                        } else {
                            AnotherLPUid = SQL.Insert_returning("INSERT into mislpu (name, ogrn, printaddress) values ('" + row.getLPUNAME() + "','" + row.getLPUOGRN() + "', '" + row.getLPUADDRESS() + "') returning id;");
                        }
                        insertDisDoc += "anotherlpu_id,";
                        insertDisDovValues += AnotherLPUid + ",";
                    }

                    insertDisDoc += "number,";
                    insertDisDovValues += "'" + row.getLNCODE() + "',";
                    insertDisDoc += "issuedate,";
                    insertDisDovValues += "'" + row.getLNDATE().toString() + "',";
                    insertDisDoc += "noactuality,";
                    insertDisDovValues += "false,";
                    insertDisDoc += "patient_id,";
                    insertDisDovValues += pid + ",";

                    if (row.getREASON1() != null && row.getREASON1().equals("01")) {
                        insertDisDoc += "disabilityreason_id,";
                        insertDisDovValues += "1,";
                    }
                    insertDisDoc += "disabilitycase_id,";
                    insertDisDovValues += idDisCase + ",";
                    insertDisDoc += "primarity_id,";
                    insertDisDovValues += row.getPRIMARYFLAG() + ",";
                    insertDisDoc += "job,";
                    insertDisDovValues += "'" + row.getLPUEMPLOYER() + "',";

                    if (row.getHOSPITALDT1() != null && !row.getHOSPITALDT1().toString().equals("")) {
                        insertDisDoc += "hospitalizedfrom,";
                        insertDisDovValues += "'" + row.getHOSPITALDT1() + "',";
                    }
                    if (row.getHOSPITALDT2() != null && !row.getHOSPITALDT2().toString().equals("")) {
                        insertDisDoc += "hospitalizedto,";
                        insertDisDovValues += "'" + row.getHOSPITALDT2() + "',";
                    }


                    System.out.println("DIAG:>>>" + row.getDIAGNOS());
                    if (row.getDIAGNOS() != null && !row.getDIAGNOS().equals("")) {

                        String id10 = SQL.Insert_returning("select id from vocidc10 where code = '" + row.getDIAGNOS() + "'");
                        System.out.println("DIAG!!>>>"+id10);
                        if (id10!=null &&!id10.equals("")){
                        insertDisDoc += "idc10_id,";
                        insertDisDovValues += id10 + ",";
                        insertDisDoc += "idc10final_id,";
                        insertDisDovValues += id10 + ",";}
                    }

                    ROW.LNRESULT lnresult = row.getLNRESULT();
                    if (lnresult != null) {
                        insertDisDoc += "isclose,";
                        insertDisDovValues += "true,";
                        if (lnresult.getMSERESULT() != null && !lnresult.getMSERESULT().equals("")) {
                            String reasonId = SQL.Insert_returning("select id from vocdisabilitydocumentclosereason where codef = '" + lnresult.getMSERESULT() + "'");
                            insertDisDoc += "closereason_id,";
                            insertDisDovValues += reasonId + ",";
                        } else {
                            insertDisDoc += "closereason_id,";
                            insertDisDovValues += "1,";
                        }
               /* if(lnresult.getNEXTLNCODE()!=null && !lnresult.getNEXTLNCODE().equals("")){
                    insertDisDoc += "createdate,"; insertDisDovValues += "current_date,";
                }*/
                        if (lnresult.getOTHERSTATEDT() != null && !lnresult.getOTHERSTATEDT().equals("")) {
                            insertDisDoc += "otherclosedate,";
                            insertDisDovValues += "'" + lnresult.getOTHERSTATEDT() + "',";
                        }
                        if (lnresult.getRETURNDATELPU() != null && !lnresult.getRETURNDATELPU().equals("")) {
                            insertDisDoc += "beginworkdate,";
                            insertDisDovValues += "'" + lnresult.getRETURNDATELPU() + "',";
                        }
                    }
                    insertDisDoc += "createdate,";
                    insertDisDovValues += "current_date,";
                    insertDisDoc += "createusername,";
                    insertDisDovValues += "'Importer',";

                    insertDisDoc += "documenttype_id,";
                    insertDisDovValues += "1,";
                    insertDisDoc += "status_id)";
                    insertDisDovValues += "1) returning id";


                    System.out.println("DISABILITYDOC>>>"+insertDisDoc+insertDisDovValues);
                    String DisabilityDocumentId = SQL.Insert_returning(insertDisDoc + insertDisDovValues);


                    List<TREATFULLPERIOD> treatfullperiods = row.getTREATPERIODS().getTREATFULLPERIOD();


                    for (TREATFULLPERIOD treatfullperiod : treatfullperiods) {
                        String disbilityRecordHeader = "insert into disabilityrecord (", disabilityRecordBody = "values(";
                        String resultReq = "";
                        if (isNotNull(treatfullperiod.getTREATPERIOD().getTREATDT1())) {
                            disbilityRecordHeader += "datefrom,";
                            disabilityRecordBody += "'" + treatfullperiod.getTREATPERIOD().getTREATDT1() + "',";
                        }

                        if (isNotNull(treatfullperiod.getTREATPERIOD().getTREATDT2())) {
                            disbilityRecordHeader += "dateto,";
                            disabilityRecordBody += "'" + treatfullperiod.getTREATPERIOD().getTREATDT2() + "',";
                        }

                        if (isNotNull(treatfullperiod.getTREATPERIOD().getTREATDOCTORROLE())) {
                            disbilityRecordHeader += "docrole,";
                            disabilityRecordBody += "'" + treatfullperiod.getTREATPERIOD().getTREATDOCTORROLE() + "',";
                        }

                        if (isNotNull(treatfullperiod.getTREATPERIOD().getTREATDOCTOR())) {
                            disbilityRecordHeader += "docname,";
                            disabilityRecordBody += "'" + treatfullperiod.getTREATPERIOD().getTREATDOCTOR() + "',";
                        }

                        if (isNotNull(treatfullperiod.getTREATCHAIRMAN()) && isNotNull(treatfullperiod.getTREATCHAIRMANROLE())) {
                            disbilityRecordHeader += "vkname,";
                            disabilityRecordBody += "'" + treatfullperiod.getTREATCHAIRMAN() + "',";
                            disbilityRecordHeader += "vkrole,";
                            disabilityRecordBody += "'" + treatfullperiod.getTREATCHAIRMANROLE() + "',";
                        }

                        disbilityRecordHeader += "disabilitydocument_id,";
                        disabilityRecordBody += DisabilityDocumentId.toString() + ",";
                        disbilityRecordHeader += "isexport)";
                        disabilityRecordBody += "true);";
                        resultReq += disbilityRecordHeader + disabilityRecordBody;

                        System.out.println(resultReq);
                        SQL.SQL_UpdIns(resultReq);
                    }

                    //System.out.println(resultReq);
                    out.print("<H1> info=" + fget.getINFO() + "</H1>");
                    out.print("<H1> mess=" + fget.getMESS() + "</H1>");
                    out.print("<H1> status=" + fget.getSTATUS() + "</H1>");
                    out.print("</body></html>");
                }else {

                    request.getRequestDispatcher("/sLnDate").forward(request, response);
                }
            } catch(SOAPException_Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){e.printStackTrace();}
    }

    private static Boolean isNotNull(Object obj){
        if(obj!=null && !obj.equals("")) return true; else return false;
    }
}
