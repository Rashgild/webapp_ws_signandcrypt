package ru.my.servlets;

import org.apache.log4j.Logger;
import ru.ibs.fss.ln.ws.fileoperationsln.*;
import ru.my.helpers_operations.GlobalVariables;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static ru.my.helpers_operations.SQL.SQL_UpdIns;
import static ru.my.helpers_operations.StoredQuery.SaveNumber;

/**
 * Created by rkurbanov on 28.06.2017.
 */
//C:\Program Files\Java\jre1.8.0_131\bin\client\jvm.dll
@WebServlet("/sNewLnNum")
public class sNewLnNum extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Logger logger=Logger.getLogger("simple");
        logger.info("1) NewLnNum");
        response.setContentType("text/html ;charset=UTF-8");
        String ogrn = request.getParameter("ogrn");

        GlobalVariables.requestParam = ogrn;

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>" +
                "  <meta charset=\"UTF-8\" />\n" +
                "  <title>SignAndCrypt</title>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> </head>");

        out.println("<body>");
        out.print("<H1> ogrn="+ogrn+"</H1>");

        System.setProperty("javax.net.ssl.trustStore",GlobalVariables.pathandnameSSL);//КОНФ
        logger.info("1) trustStore 1");
        System.setProperty("javax.net.ssl.trustStorePassword", GlobalVariables.passwordSSL);
        logger.info("1) trustStore 2");

        FileOperationsLnImplService service=null;
        FileOperationsLn start=null;
        try{
            service = new  FileOperationsLnImplService();
        logger.info("1) serv 2");
         start = service.getFileOperationsLnPort();
        logger.info("1) send 2");}catch (Exception e) {
            e.printStackTrace();
        }


        try {
            FileOperationsLnUserGetNewLNNumOut Num = start.getNewLNNum(ogrn);//getNewLNNumRange(ogrn,count);
            out.println("<H1>"+Num.getMESS()+"</H1>");
            out.println("<H1> Получен номер: </H1>");
            out.println("<H1>1)"+Num.getDATA()+"</H1>");
            SQL_UpdIns(SaveNumber(Num.getDATA()));

        } catch (SOAPException_Exception e) {
            e.printStackTrace();
        }
    }
}
