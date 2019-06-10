package ru.my.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ru.ibs.fss.ln.ws.fileoperationsln.FileOperationsLn;
import ru.ibs.fss.ln.ws.fileoperationsln.FileOperationsLnImplService;
import ru.ibs.fss.ln.ws.fileoperationsln.FileOperationsLnUserGetLNDataOut;
import ru.ibs.fss.ln.ws.fileoperationsln.ROW;
import ru.ibs.fss.ln.ws.fileoperationsln.SOAPException_Exception;
import ru.ibs.fss.ln.ws.fileoperationsln.TREATFULLPERIOD;
import ru.my.utils.GlobalVariables;
import ru.my.utils.SQL;

import static ru.my.utils.GlobalVariables.ogrnMo;

@WebServlet("/sImportLNN")
public class sImportLNN extends HttpServlet {

    private void Out(HttpServletResponse response, String string) {
        PrintWriter out = null;
        try {
            out = response.getWriter();

        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(string);
    }

    private boolean isExistNumber(String ELN) {
        ResultSet rs = SQL.select("select id from disabilitydocument where number='" + ELN + "'");
        int idDoc = 0;
        try {
            while (rs.next()) {
                idDoc = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //idDoc>0 ? return true :return false;
        if (idDoc > 0) return true;
        else return false;
    }

    private String checkLPU(String OGRN, String name, String address) {
        ResultSet rs = SQL.select("Select id from mislpu where ogrn='" + OGRN + "' and name='" + name + "' and printaddress='" + address + "'");
        String idLpu = "";
        try {
            while (rs.next()) {
                idLpu = rs.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!idLpu.equals("")) {
            return idLpu;
        } else {
            return "";
        }
    }

    private String con(String str) {
        return "'".concat(str.concat("'"));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            Logger logger = Logger.getLogger("simple");
            logger.info("Start Import>>>");
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
                    Map<String, String> SQLrequest = new HashMap<String, String>();

                    SQL.sqlUpdIns("INSERT into disabilitycase (patient_id, createdate,createusername) values (" + pid + ", current_date, 'Importer')");
                    String idDisCase = SQL.insertReturning("select max(id) as id from disabilitycase");
                    String lpuOGRN = row.getLPUOGRN();

                    if (ogrnMo.equals(lpuOGRN)) {
                        System.out.println("lpuOGRN>>>>" + lpuOGRN);

                    } else {
                        SQLrequest.put("anotherlpuaddress", con(row.getLPUADDRESS()));
                        SQLrequest.put("anotherlpuogrn", con(row.getLPUOGRN()));
                        SQLrequest.put("anotherlpuname", con(row.getLPUNAME()));
                    }

                    if (row.getPREVLNCODE() != null && !row.getPREVLNCODE().equals("")) {
                        SQLrequest.put("pervelnnumber", con(row.getPREVLNCODE()));
                    }

                    if (row.getDUPLICATEFLAG() != 0) {
                        SQLrequest.put("elnduplicate", con(String.valueOf(row.getDUPLICATEFLAG())));
                    }
                    SQLrequest.put("number", con(row.getLNCODE()));
                    SQLrequest.put("issuedate", con(row.getLNDATE().toString()));
                    SQLrequest.put("noactuality", "false");
                    SQLrequest.put("patient_id", pid);

                    if (row.getREASON1() != null && row.getREASON1().equals("01")) {
                        SQLrequest.put("disabilityreason_id", "1");
                    }
                    SQLrequest.put("disabilitycase_id", idDisCase);


                    if (row.getPREVLNCODE() != null && !row.getPREVLNCODE().equals("")) {
                        SQLrequest.put("anotherprevln", "'" + row.getPREVLNCODE() + "'");
                    }
                    if (row.getPRIMARYFLAG() == 1) {
                        SQLrequest.put("primarity_id", "1");
                    } else {
                        SQLrequest.put("primarity_id", "2");
                    }
                    SQLrequest.put("job", "'" + row.getLPUEMPLOYER() + "'");

                    if (row.getHOSPITALDT1() != null && !row.getHOSPITALDT1().toString().equals("")) {
                        SQLrequest.put("hospitalizedfrom", "'" + row.getHOSPITALDT1() + "'");
                    }
                    if (row.getHOSPITALDT2() != null && !row.getHOSPITALDT2().toString().equals("")) {
                        SQLrequest.put("hospitalizedto", "'" + row.getHOSPITALDT2() + "'");
                    }

                    if (row.getDIAGNOS() != null && !row.getDIAGNOS().equals("")) {
                        String id10 = SQL.insertReturning("select id from vocidc10 where code = '" + row.getDIAGNOS() + "'");
                        if (id10 != null && !id10.equals("")) {
                            SQLrequest.put("idc10_id", id10);
                            SQLrequest.put("idc10final_id", id10);
                        } else {
                            SQLrequest.put("diagnos", "'" + row.getDIAGNOS() + "'");
                        }
                    }

                    ROW.LNRESULT lnresult = row.getLNRESULT();
                    if (lnresult != null) {
                        SQLrequest.put("isclose", "true");
                        if (lnresult.getMSERESULT() != null && !lnresult.getMSERESULT().equals("")) {
                            String reasonId = SQL.insertReturning("select id from vocdisabilitydocumentclosereason where codef = '" + lnresult.getMSERESULT() + "'");
                            SQLrequest.put("closereason_id", reasonId);
                        } else {
                            SQLrequest.put("closereason_id", "1");
                        }
                        if (lnresult.getOTHERSTATEDT() != null && !lnresult.getOTHERSTATEDT().equals("")) {
                            SQLrequest.put("otherclosedate", "'" + lnresult.getOTHERSTATEDT() + "'");
                        }
                        if (lnresult.getRETURNDATELPU() != null && !lnresult.getRETURNDATELPU().equals("")) {
                            SQLrequest.put("beginworkdate", "'" + lnresult.getRETURNDATELPU() + "'");
                        }
                    }
                    SQLrequest.put("createdate", "current_date");
                    SQLrequest.put("createusername", "'Importer'");
                    SQLrequest.put("documenttype_id", "1");
                    SQLrequest.put("closeexport","false");

                    SQLrequest.put("status_id", "1");
                    String req = buildRequest(SQLrequest, "disabilitydocument");
                    SQL.sqlUpdIns(req);
                    String DisabilityDocumentId = SQL.insertReturning("Select max(id) as id from disabilitydocument");
                    List<TREATFULLPERIOD> treatfullperiods = row.getTREATPERIODS().getTREATFULLPERIOD();
                    for (TREATFULLPERIOD treatfullperiod : treatfullperiods) {

                        SQLrequest = new HashMap<>();
                        if (isNotNull(treatfullperiod.getTREATPERIOD().getTREATDT1())) {
                            SQLrequest.put("datefrom", "'" + treatfullperiod.getTREATPERIOD().getTREATDT1() + "'");
                        }

                        if (isNotNull(treatfullperiod.getTREATPERIOD().getTREATDT2())) {
                            SQLrequest.put("dateto", "'" + treatfullperiod.getTREATPERIOD().getTREATDT2() + "'");
                        }

                        if (isNotNull(treatfullperiod.getTREATPERIOD().getTREATDOCTORROLE())) {
                            SQLrequest.put("docrole", "'" + treatfullperiod.getTREATPERIOD().getTREATDOCTORROLE() + "'");
                        }

                        if (isNotNull(treatfullperiod.getTREATPERIOD().getTREATDOCTOR())) {
                            SQLrequest.put("docname", "'" + treatfullperiod.getTREATPERIOD().getTREATDOCTOR() + "'");
                        }

                        if (isNotNull(treatfullperiod.getTREATCHAIRMAN()) && isNotNull(treatfullperiod.getTREATCHAIRMANROLE())) {
                            SQLrequest.put("vkname", "'" + treatfullperiod.getTREATCHAIRMAN() + "'");
                            SQLrequest.put("vkrole", "'" + treatfullperiod.getTREATCHAIRMANROLE() + "'");
                        }

                        SQLrequest.put("disabilitydocument_id", DisabilityDocumentId.toString());
                        SQLrequest.put("isexport", "true");
                        req = buildRequest(SQLrequest, "disabilityrecord");
                        SQL.sqlUpdIns(req);
                    }

                    out.print("<br>");
                    out.print("Создано! <a href=\"/riams/entityParentView-dis_case.do?id=" + idDisCase + "\">Перейти</a>");
                    out.print("<br>");
                    out.print("<H1> info=" + fget.getINFO() + "</H1>");
                    out.print("<H1> mess=" + fget.getMESS() + "</H1>");
                    out.print("<H1> status=" + fget.getSTATUS() + "</H1>");
                    out.print("</body></html>");
                } else {
                    request.getRequestDispatcher("/sLnDate").forward(request, response);
                }
            } catch (SOAPException_Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Boolean isNotNull(Object obj) {
        return obj != null && !obj.equals("");
    }

    private static String buildRequest(Map<String, String> SQLrequest, String tablename) {
        StringBuilder header = new StringBuilder().append("insert into " + tablename + " (");
        StringBuilder body = new StringBuilder().append(" values (");

        int size = SQLrequest.size(), i = 0;
        for (Map.Entry entry : SQLrequest.entrySet()) {

            header.append(entry.getKey());
            body.append(entry.getValue());
            i++;
            if (i < size) {
                header.append(",");
                body.append(",");
            }
        }
        header.append(")");
        body.append(")");

        return (header.append(body)).toString();
    }
}
