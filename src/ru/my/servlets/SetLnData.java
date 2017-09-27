package ru.my.servlets;

import org.apache.log4j.Logger;
import ru.ibs.fss.ln.ws.fileoperationsln.*;
import ru.my.helpers_operations.GlobalVariables;
import ru.my.helpers_operations.SQL;
import ru.my.helpers_operations.StoredQuery;
import ru.my.service_operations.xmlFileLnLpu.PrParseFileLnLpu_start;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ru.my.helpers_operations.GlobalVariables.hash;
import static ru.my.helpers_operations.GlobalVariables.passwordSSL;
import static ru.my.helpers_operations.GlobalVariables.pathandnameSSL;

//Created by rashgild on 25.05.2017.

@WebServlet("/SetLnData")
public class SetLnData extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Logger logger=Logger.getLogger("");
        long start = System.currentTimeMillis();
        logger.info("1) SetLnData");
        response.setContentType("text/html ;charset=UTF-8");
        String id = request.getParameter("id");
        GlobalVariables.requestParam = id;

        getLnHash(id);

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>\n" +//
                "  <meta charset=\"UTF-8\" />\n" +
                "  <title>SignAndcrypt</title>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> " +
                " <style> .ex {\n" +
                "    border: 1px dashed #634f36;\n" +
                "    background: #ebff95;\n" +
                "    font-family: \"Courier New\", Courier, monospace;\n" +
                "    padding: 7px;\n" +
                "    margin: 0 0 1em;\n" +
                "    white-space: pre-wrap;\n" +
                "  }\n" +
                "\n" +
                ".ex2 {\n" +
                "  border: 1px dashed #634f36;\n" +
                "  background: #ffcbd5;\n" +
                "  font-family: \"Courier New\", Courier, monospace;\n" +
                "  padding: 7px;\n" +
                "  margin: 0 0 1em;\n" +
                "  white-space: pre-wrap;\n" +
                "} </style>" +
                "</head>");
        out.println("<body>");
        out.println("  <header>\n" +
                "    <img src=\"res/pic/FSSlogo-208x191.gif\" width=\"75\" height=\"50\" alt=\"\" />\n" +
                "    <img src=\"res/pic/medosLogo-200x200.png\" width=\"75\" height=\"50\" alt=\"\" />\n" +
                " <h1>Результаты отправки ЛН:</h1>\n" +
                "  </header>");
        WSResult result= setRequest();
        List<INFO.ROWSET.ROW> rows = result.getINFO().getROWSET().getROW();
        String saveResult="";
        String state ="",hash="",status="";
        try {
            for (INFO.ROWSET.ROW row : rows) {
                if (row.getSTATUS() == 0) {
                    out.println("<p class=\"ex2\"> <font size=\"4\" color=\"#2d2d2b\"> ");
                } else {
                    out.println("<p class=\"ex\"> <font size=\"4\" color=\"#2d2d2b\"> ");
                }

                state = row.getLNSTATE();
                hash = row.getLNHASH();

                out.println("Внутренний id: " + id + " <br>");
                out.println("№: " + row.getROWNO() + " <br>");
                out.println("Статус: " + row.getSTATUS() + " <br>");

                if (state != null && !state.equals("")) {
                    out.println("Состояние: " + state + "<br>");
                }
                out.println("Хэш: " + hash + "<br>");
                out.println("ЛН код: " + row.getLNCODE() + "<br>");
                out.println("Ответ: " + result.getMESS() + " <br>");

                logger.info("Внутренний id:" + id + " " +
                        " Статус:" + row.getROWNO() + " " +
                        " ЛН:" + row.getLNCODE() + " " +
                        " Ответ:" + result.getMESS());

                saveResult += result.getMESS();
                try {
                    if (row.getERRORS().getERROR().size() > 0) {
                        List<INFO.ROWSET.ROW.ERRORS.ERROR> errors = row.getERRORS().getERROR();
                        for (INFO.ROWSET.ROW.ERRORS.ERROR errs : errors) {
                            out.println("Ошибка: " + errs.getERRMESS() + " <br>");
                            logger.info("Ошибка: " + errs.getERRMESS() + " " + errs.getERRCODE());
                            saveResult += ":" + errs.getERRMESS();
                        }
                    }
                } catch (Exception e) {
                }
                out.println("</font>\n" +
                        "</p>");//+
            }
        }catch (Exception e){e.printStackTrace();}

        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        out.println("<H3> Время выполнения: "+timeConsumedMillis/1000+" сек</H3>");


        if(state != null && !state.equals("")  || hash!=null && !hash.equals("")){

            /*//GetVoc
            ResultSet resultSet = SQL.Query(StoredQuery.GetVoc(state));
            Integer vocId=0;
            try {
                while (resultSet.next()) {
                    vocId= resultSet.getInt("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
            System.out.println(state+"_"+hash+" "+GlobalVariables.t_ELN);

            System.out.println(state+"_"+hash+" "+GlobalVariables.t_ELN);
            //TODO disabilityRecord isExport = true;
            SQL.SQL_UpdIns(StoredQuery.SaveStatusAndHash(state,hash,GlobalVariables.t_ELN));
        }

        SQL.SaveInBD(saveResult,result.getSTATUS());

        out.println("</body>");
        out.println("</html>");
    }

    private static WSResult setRequest(){
        System.setProperty("javax.net.ssl.trustStore",pathandnameSSL);
        System.setProperty("javax.net.ssl.trustStorePassword", passwordSSL);
        FileOperationsLnImplService service = new  FileOperationsLnImplService();
        FileOperationsLn start = service.getFileOperationsLnPort();
        ROWSET rowset = new ROWSET();
        PrParseFilelnlpuElement prParseFilelnlpuElement = new PrParseFilelnlpuElement();
        PrParseFilelnlpuElement.PXmlFile pXmlFile= new PrParseFilelnlpuElement.PXmlFile();
        pXmlFile.setROWSET(rowset);
        prParseFilelnlpuElement.setPXmlFile(pXmlFile);
        WSResult result =null;
        try {
             result = start.prParseFilelnlpu(prParseFilelnlpuElement);
             return result;
        } catch (SOAPException_Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void getLnHash(String id){
        Logger logger=Logger.getLogger("");

        logger.info("Get Hash");

        String snils="",eln="";
        GlobalVariables.hash="";
        ResultSet resultSet = SQL.Query(StoredQuery.getLNandSnils(id));
        try {
            while (resultSet.next()) {
                snils = resultSet.getString("snils");
                eln =resultSet.getString("ln_code");
            }
        } catch (SQLException e) {e.printStackTrace();}


        String[] str = snils.split("-");
        snils = str[0] + str[1] + str[2];
        str = snils.split(" ");
        snils = str[0] + str[1];

        logger.info("Snils: "+snils);
        logger.info("Eln: "+eln);

        try {
            System.setProperty("javax.net.ssl.trustStore",GlobalVariables.pathandnameSSL);//КОНФ
            System.setProperty("javax.net.ssl.trustStorePassword", GlobalVariables.passwordSSL);
            FileOperationsLnImplService service = new  FileOperationsLnImplService();
            FileOperationsLn start = service.getFileOperationsLnPort();
            FileOperationsLnUserGetLNDataOut fileOperationsLnUserGetLNDataOut = start.getLNData(GlobalVariables.ogrnMo,eln,snils);
            GlobalVariables.hash  = fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getLNHASH();
            logger.info("Хэш: "+hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
