package ru.my.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.ibs.fss.ln.ws.fileoperationsln.FileOperationsLn;
import ru.ibs.fss.ln.ws.fileoperationsln.FileOperationsLnImplService;
import ru.ibs.fss.ln.ws.fileoperationsln.PrParseFilelnlpuElement;
import ru.ibs.fss.ln.ws.fileoperationsln.ROWSET;
import ru.ibs.fss.ln.ws.fileoperationsln.SOAPException_Exception;
import ru.ibs.fss.ln.ws.fileoperationsln.WSResult;

@WebServlet("/GetLnNumber")
public class GetLnNumber extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html ;charset=UTF-8");
        Integer id = Integer.valueOf(request.getParameter("id"));

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>\n\n" +
                "  <meta charset=\"UTF-8\" />\n" +
                "  <title>SignAndCrypt</title>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> </head>");

        out.println("<body>");
        out.print("<H1> id=" + id + "</H1>");
        out.println("</body>");
        out.println("</html>");

        System.setProperty("javax.net.ssl.trustStore", "C:/cacerts1");//КОНФ
        System.setProperty("javax.net.ssl.trustStorePassword", "123456");

        FileOperationsLnImplService service = new FileOperationsLnImplService();
        FileOperationsLn start = service.getFileOperationsLnPort();

        ROWSET rowset = new ROWSET();
        PrParseFilelnlpuElement prParseFilelnlpuElement = new PrParseFilelnlpuElement();
        PrParseFilelnlpuElement.PXmlFile pXmlFile = new PrParseFilelnlpuElement.PXmlFile();
        pXmlFile.setROWSET(rowset);
        prParseFilelnlpuElement.setPXmlFile(pXmlFile);
        try {
            WSResult result = start.prParseFilelnlpu(prParseFilelnlpuElement);

        } catch (SOAPException_Exception e) {
            e.printStackTrace();
        }
    }
}
