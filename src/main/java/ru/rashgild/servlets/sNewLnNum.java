package main.java.ru.rashgild.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.ru.genereted.v1.fileoperationsln.ws.FileOperationsLn;
import main.java.ru.genereted.v1.fileoperationsln.ws.FileOperationsLnImplService;
import main.java.ru.genereted.v1.fileoperationsln.ws.FileOperationsLnUserGetNewLNNumOut;
import main.java.ru.genereted.v1.fileoperationsln.ws.SOAPException_Exception;
import main.java.ru.rashgild.utils.GlobalVariables;

import static main.java.ru.rashgild.utils.SQL.sqlUpdIns;
import static main.java.ru.rashgild.utils.StoredQuery.SaveNumber;

@WebServlet("/sNewLnNum")
public class sNewLnNum extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        out.print("<H1> ogrn=" + ogrn + "</H1>");

        System.setProperty("javax.net.ssl.trustStore", GlobalVariables.pathandnameSSL);
        System.setProperty("javax.net.ssl.trustStorePassword", GlobalVariables.passwordSSL);

        FileOperationsLnImplService service = null;
        FileOperationsLn start = null;
        try {
            service = new FileOperationsLnImplService();
            start = service.getFileOperationsLnPort();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileOperationsLnUserGetNewLNNumOut Num = start.getNewLNNum(ogrn);//getNewLNNumRange(ogrn,count);
            out.println("<H1>" + Num.getMESS() + "</H1>");
            out.println("<H1> Получен номер: </H1>");
            out.println("<H1>1)" + Num.getDATA() + "</H1>");
            sqlUpdIns(SaveNumber(Num.getDATA()));

        } catch (SOAPException_Exception e) {
            e.printStackTrace();
        }
    }
}
