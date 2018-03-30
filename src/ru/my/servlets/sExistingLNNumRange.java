package ru.my.servlets;

import org.apache.log4j.Logger;
import ru.ibs.fss.ln.ws.fileoperationsln.FileOperationsLn;
import ru.ibs.fss.ln.ws.fileoperationsln.FileOperationsLnImplService;
import ru.ibs.fss.ln.ws.fileoperationsln.FileOperationsLnUserGetExistingLNNumRangeOut;
import ru.ibs.fss.ln.ws.fileoperationsln.LnNumList;
import ru.my.helpers_operations.GlobalVariables;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by rkurbanov on 30.03.2018.
 */
@WebServlet("/sExistingLNNumRange")
public class sExistingLNNumRange extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Logger logger= Logger.getLogger("simple");
        logger.info("1) sExistingLNNumRange");
        response.setContentType("text/html ;charset=UTF-8");

        String ogrn = request.getParameter("ogrn");


        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>" +
                "  <meta charset=\"UTF-8\" />\n" +
                "  <title>SignAndCrypt</title>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> </head>");

        out.println("<body>");
        out.print("<H1> ogrn="+ogrn+"</H1>");


        System.setProperty("javax.net.ssl.trustStore", GlobalVariables.pathandnameSSL);//КОНФ
        System.setProperty("javax.net.ssl.trustStorePassword", GlobalVariables.passwordSSL);
        FileOperationsLnImplService service = new FileOperationsLnImplService();
        FileOperationsLn start = service.getFileOperationsLnPort();

        try {

            FileOperationsLnUserGetExistingLNNumRangeOut rangeOut = start.getExistingLNNumRange(ogrn);

            out.print("<H1> Инфо="+rangeOut.getINFO()+"</H1>");
            out.print("<H1> Сообщение="+rangeOut.getMESS()+"</H1>");
            out.print("<H1> Статус="+rangeOut.getSTATUS()+"</H1>");

            if(rangeOut.getDATA()!=null){
                LnNumList s = rangeOut.getDATA();
                List<String> lns = s.getLNNum();
                for (String ln:lns){
                    out.print("<H1> eln# "+ln+"</H1>");
                }
            }else {
                out.print("<H1> Нет данных по неиспользованным номерам </H1>");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        out.println("</body>");
        out.println("</html>");
    }
}
