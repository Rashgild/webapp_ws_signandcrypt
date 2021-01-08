package ru.rashgild.servlets;

import org.apache.log4j.Logger;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FIleOperationService;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FileOperationsLnService;
import ru.rashgild.generated.v2.types.eln.mo.v01.FileOperationsLnUserGetLNDataOut;
import ru.rashgild.generated.v2.types.eln.mo.v01.GetLNDataRequest;
import ru.rashgild.generated.v2.types.eln.mo.v01.ResponseRow;
import ru.rashgild.generated.v2.types.eln.v01.LnResult;
import ru.rashgild.generated.v2.types.eln.v01.TreatFullPeriodMo;
import ru.rashgild.service.DependencyInjection;
import ru.rashgild.utils.SQL;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.rashgild.utils.GlobalVariables.ogrnMo;

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
            Boolean isTest = Boolean.parseBoolean(request.getParameter("test"));

            String outString = "";
            outString += "<html><head><meta charset=\"UTF-8\"/><title>SignAndCrypt</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"></head><body>";

            PrintWriter out = response.getWriter();
            Out(response, outString);
            out.print("<H1> ogrn=" + ogrn + "</H1>");
            out.print("<H1> eln=" + eln + "</H1>");
            out.print("<H1> snils=" + snils + "</H1>");

            FIleOperationService service = DependencyInjection.getImplementation(isTest);
            FileOperationsLnService start = service.getFileOperationsLnPort();

            try {
                GetLNDataRequest request1 = new GetLNDataRequest();
                request1.setOgrn(ogrn);
                request1.setLnCode(eln);
                request1.setSnils(snils);

                FileOperationsLnUserGetLNDataOut fileOperationsLnUserGetLNDataOut = start.getLNData(request1);
                ResponseRow row = fileOperationsLnUserGetLNDataOut.getData().getOutRowset().getResponseRow();
                if (!isExistNumber(eln)) {
                    Map<String, String> SQLrequest = new HashMap<>();

                    SQL.sqlUpdIns("INSERT into disabilitycase (patient_id, createdate,createusername) values (" + pid + ", current_date, 'Importer')");
                    String idDisCase = SQL.insertReturning("select max(id) as id from disabilitycase");
                    String lpuOGRN = row.getLpuOgrn();

                    if (ogrnMo.equals(lpuOGRN)) {
                        System.out.println("lpuOGRN>>>>" + lpuOGRN);
                    } else {
                        SQLrequest.put("anotherlpuaddress", con(row.getLpuAddress()));
                        SQLrequest.put("anotherlpuogrn", con(row.getLpuOgrn()));
                        SQLrequest.put("anotherlpuname", con(row.getLpuName()));
                    }

                    if (row.getPrevLnCode() != null && !row.getPrevLnCode().isEmpty()) {
                        SQLrequest.put("pervelnnumber", con(row.getPrevLnCode()));
                        SQLrequest.put("anotherprevln", "'" + row.getPrevLnCode() + "'");
                    }

                    if(row.getPreviouslyIssuedCode() != null && !row.getPreviouslyIssuedCode().isEmpty()) {
                        SQLrequest.put("previouslyissuedcode", con(row.getPreviouslyIssuedCode()));
                    }

                    if (row.isDuplicateFlag()) {
                        SQLrequest.put("elnduplicate", con(String.valueOf(row.isDuplicateFlag())));
                    }

                    SQLrequest.put("number", con(row.getLnCode()));
                    SQLrequest.put("issuedate", con(row.getLnDate().toString()));
                    SQLrequest.put("noactuality", "false");
                    SQLrequest.put("patient_id", pid);

                    if (row.getReason1() != null && row.getReason1().equals("01")) {
                        SQLrequest.put("disabilityreason_id", "1");
                    }
                    SQLrequest.put("disabilitycase_id", idDisCase);

                    //if (row.getPRIMARYFLAG() == 1) {
                    SQLrequest.put("primarity_id", "1");
                    /*} else {
                        SQLrequest.put("primarity_id", "2");
                    }*/
                    //SQLrequest.put("job", "'" + row.getLPUEMPLOYER() + "'");

                    if (row.getHospitalDt1() != null && !row.getHospitalDt1().toString().isEmpty()) {
                        SQLrequest.put("hospitalizedfrom", "'" + row.getHospitalDt1().toString() + "'");
                    }
                    if (row.getHospitalDt2() != null && !row.getHospitalDt2().toString().isEmpty()) {
                        SQLrequest.put("hospitalizedto", "'" + row.getHospitalDt2().toString() + "'");
                    }

                    if (isNotNullOrEmpty(row.getDiagnos())) {
                        String id10 = SQL.insertReturning("select id from vocidc10 where code = '" + row.getDiagnos() + "'");
                        if (isNotNullOrEmpty(id10)) {
                            SQLrequest.put("idc10_id", id10);
                            SQLrequest.put("idc10final_id", id10);
                        } else {
                            SQLrequest.put("diagnos", "'" + row.getDiagnos() + "'");
                        }
                    }

                    LnResult lnresult = row.getLnResult();
                    if (lnresult != null) {
                        SQLrequest.put("isclose", "true");
                        if (lnresult.getMseResult() != null && !lnresult.getMseResult().isEmpty()) {
                            String reasonId = SQL.insertReturning("select id from vocdisabilitydocumentclosereason where codef = '" + lnresult.getMseResult() + "'");
                            SQLrequest.put("closereason_id", reasonId);
                        } else {
                            SQLrequest.put("closereason_id", "1");
                        }
                        if (lnresult.getOtherStateDt() != null && !lnresult.getOtherStateDt().isEmpty()) {
                            SQLrequest.put("otherclosedate", "'" + lnresult.getOtherStateDt() + "'");
                        }
                        if (lnresult.getReturnDateLpu() != null && !lnresult.getReturnDateLpu().equals("")) {
                            SQLrequest.put("beginworkdate", "'" + lnresult.getReturnDateLpu() + "'");
                        }
                    }
                    SQLrequest.put("createdate", "current_date");
                    SQLrequest.put("createusername", "'Importer'");
                    SQLrequest.put("documenttype_id", "1");
                    SQLrequest.put("closeexport", "false");

                    SQLrequest.put("status_id", "1");
                    String req = buildRequest(SQLrequest, "disabilitydocument");
                    SQL.sqlUpdIns(req);

                    String disabilityDocumentId = SQL.insertReturning("Select max(id) as id from disabilitydocument");
                    List<TreatFullPeriodMo> treatFullPeriods = row.getTreatPeriods().getTreatFullPeriod();
                    for (TreatFullPeriodMo treatFullPeriodMo : treatFullPeriods) {

                        TreatFullPeriodMo.TreatPeriod fullPeriod = treatFullPeriodMo.getTreatPeriod();
                        SQLrequest = new HashMap<>();
                        if (isNotNull(fullPeriod.getTreatDt1())) {
                            SQLrequest.put("datefrom", "'" + fullPeriod.getTreatDt1() + "'");
                        }

                        if (isNotNull(fullPeriod.getTreatDt2())) {
                            SQLrequest.put("dateto", "'" + fullPeriod.getTreatDt2() + "'");
                        }

                        if (isNotNull(fullPeriod.getTreatDoctorRole()) && isNotNull(fullPeriod.getTreatDoctor())) {
                            SQLrequest.put("docrole", "'" + fullPeriod.getTreatDoctorRole() + "'");
                            SQLrequest.put("docname", "'" + fullPeriod.getTreatDoctor() + "'");
                        }

                        if (isNotNull(treatFullPeriodMo.getTreatChairman()) && isNotNull(treatFullPeriodMo.getTreatChairmanRole())) {
                            SQLrequest.put("vkname", "'" + treatFullPeriodMo.getTreatChairman() + "'");
                            SQLrequest.put("vkrole", "'" + treatFullPeriodMo.getTreatChairmanRole() + "'");
                        }

                        SQLrequest.put("disabilitydocument_id", disabilityDocumentId);
                        SQLrequest.put("isexport", "true");
                        req = buildRequest(SQLrequest, "disabilityrecord");
                        SQL.sqlUpdIns(req);
                    }

                    out.print("<br>");
                    out.print("Создано! <a href=\"/riams/entityParentView-dis_case.do?id=" + idDisCase + "\">Перейти</a>");
                    out.print("<br>");
                    out.print("<H1> info=" + fileOperationsLnUserGetLNDataOut.getInfo() + "</H1>");
                    out.print("<H1> mess=" + fileOperationsLnUserGetLNDataOut.getMess() + "</H1>");
                    out.print("<H1> status=" + fileOperationsLnUserGetLNDataOut.getStatus() + "</H1>");
                    out.print("</body></html>");
                } else {
                    request.getRequestDispatcher("/sLnDate").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Boolean isNotNull(Object obj) {
        return obj != null && !obj.equals("");
    }

    private static Boolean isNotNullOrEmpty(String str) {
        return str != null && !str.isEmpty();
    }

    private static String buildRequest(Map<String, String> sqlRequest, String tableName) {
        StringBuilder header = new StringBuilder()
                .append("insert into ")
                .append(tableName)
                .append(" (");

        StringBuilder body = new StringBuilder().append(" values (");

        int size = sqlRequest.size(), i = 0;
        for (Map.Entry entry : sqlRequest.entrySet()) {

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
