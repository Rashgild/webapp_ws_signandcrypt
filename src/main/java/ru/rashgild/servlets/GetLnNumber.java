package ru.rashgild.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

/*        FileOperationsLnImplService service = new FileOperationsLnImplService();
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
        }*/
    }
}
