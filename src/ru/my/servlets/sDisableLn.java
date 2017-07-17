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

import static ru.my.helpers_operations.SQL.Query;
import static ru.my.helpers_operations.StoredQuery.SaveNumber;

/**
 * Created by rkurbanov on 28.06.2017.
 */



@WebServlet("/sDisableLn")
public class sDisableLn  extends  HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Logger logger=Logger.getLogger("simple");
        logger.info("1) NewLnNum");
        response.setContentType("text/html ;charset=UTF-8");
        String ogrn = request.getParameter("ogrn");
        String lnCode = request.getParameter("lnCode");
        String snils = request.getParameter("snils");
        String reasonCode = request.getParameter("reasonCode");
        String reason = request.getParameter("reason");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>" +
                "  <meta charset=\"UTF-8\" />\n" +
                "  <title>SignAndCrypt</title>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> </head>");

        out.println("<body>");
        System.setProperty("javax.net.ssl.trustStore", GlobalVariables.pathandnameSSL);//КОНФ
        System.setProperty("javax.net.ssl.trustStorePassword", GlobalVariables.passwordSSL);

        FileOperationsLnImplService service = new  FileOperationsLnImplService();
        FileOperationsLn start = service.getFileOperationsLnPort();
        try {
            FileOperationsLnUserDisableLnOut fileOperationsLnUserDisableLnOut =  start.disableLn(ogrn,lnCode,snils,reasonCode,reason);
            out.println("<H1>"+fileOperationsLnUserDisableLnOut.getMESS()+"</H1>");
            out.println("<H1>1)"+fileOperationsLnUserDisableLnOut.getDATA()+"</H1>");
            out.println("<H1>1)"+fileOperationsLnUserDisableLnOut.getINFO()+"</H1>");
            out.println("<H1>1)"+fileOperationsLnUserDisableLnOut.getSTATUS()+"</H1>");
        } catch (SOAPException_Exception e) {
            e.printStackTrace();
        }

    }
}
