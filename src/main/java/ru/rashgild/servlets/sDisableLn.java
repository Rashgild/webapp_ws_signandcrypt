package main.java.ru.rashgild.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import main.java.ru.genereted.v1.fileoperationsln.ws.FileOperationsLn;
import main.java.ru.genereted.v1.fileoperationsln.ws.FileOperationsLnImplService;
import main.java.ru.genereted.v1.fileoperationsln.ws.FileOperationsLnUserDisableLnOut;
import main.java.ru.genereted.v1.fileoperationsln.ws.SOAPException_Exception;
import main.java.ru.rashgild.utils.GlobalVariables;

@WebServlet("/sDisableLn")
public class sDisableLn extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Logger logger = Logger.getLogger("simple");
        logger.info("1) NewLnNum");
        response.setContentType("text/html ;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String ogrn = request.getParameter("ogrn");
        String lnCode = request.getParameter("lnCode");
        String snils = request.getParameter("snils");
        String reasonCode = request.getParameter("reasonCode");
        String reason = request.getParameter("reason");

        System.out.println("Ogrn:" + ogrn + ",lncode:" + lnCode + ",snils:" + snils + ",reasonCode:" + reasonCode + ",reason:" + reason);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>" +
                "  <meta charset=\"UTF-8\" />\n" +
                "  <title>SignAndCrypt</title>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> </head>");

        out.println("<body>");
        System.setProperty("javax.net.ssl.trustStore", GlobalVariables.pathandnameSSL);//КОНФ
        System.setProperty("javax.net.ssl.trustStorePassword", GlobalVariables.passwordSSL);

        FileOperationsLnImplService service = new FileOperationsLnImplService();
        FileOperationsLn start = service.getFileOperationsLnPort();
        try {
            FileOperationsLnUserDisableLnOut fileOperationsLnUserDisableLnOut = start.disableLn(ogrn, lnCode, snils, reasonCode, reason);
            out.println("<H1>Статус:" + fileOperationsLnUserDisableLnOut.getMESS() + "</H1>");
        } catch (SOAPException_Exception e) {
            e.printStackTrace();
        }
        out.println("</body></html>");
    }
}
