package ru.my.servlets;

import org.apache.log4j.Logger;
import ru.ibs.fss.ln.ws.fileoperationsln.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//Created by rashgild on 19.05.2017.

@WebServlet("/GetLnNumber")
public class GetLnNumber extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {


        Logger logger=Logger.getLogger("simple");
        //logger.info("Servlet");

        //Logger log = (Logger)getServletContext().getAttribute("log4");
        //log.info("Enter to Servlet");
        response.setContentType("text/html ;charset=UTF-8");
        //String ogrn = request.getParameter("ogrn");
        Integer id = Integer.valueOf(request.getParameter("id"));


        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>\n" +
                "  \n" +
                "  <meta charset=\"UTF-8\" />\n" +
                "  <title>SignAndCrypt</title>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> </head>");

        out.println("<body>");
        out.print("<H1> id="+id+"</H1>");



        out.println("</body>");
        out.println("</html>");

//TODO CACERTS
        System.setProperty("javax.net.ssl.trustStore","C:/cacerts1");//КОНФ
        System.setProperty("javax.net.ssl.trustStorePassword", "123456");

        FileOperationsLnImplService service = new  FileOperationsLnImplService();
        FileOperationsLn start = service.getFileOperationsLnPort();


        ROWSET rowset = new ROWSET();

        PrParseFilelnlpuElement prParseFilelnlpuElement = new PrParseFilelnlpuElement();
        PrParseFilelnlpuElement.PXmlFile pXmlFile= new PrParseFilelnlpuElement.PXmlFile();
        pXmlFile.setROWSET(rowset);
        prParseFilelnlpuElement.setPXmlFile(pXmlFile);
        try {
            WSResult result = start.prParseFilelnlpu(prParseFilelnlpuElement);

        } catch (SOAPException_Exception e) {
            e.printStackTrace();
        }


     /*   try {
        PrParseFileLnLpu_start.Start(id);
    } catch (Exception e) {
        e.printStackTrace();
    }*/


    }


  /*  public static void SaveSOAPToXML( String FileName, SOAPMessage soapMessage)
            throws IOException, SOAPException {

        String strMsg = SoapMessageToString(soapMessage);//new String(out2.toByteArray());//.getBytes("UTF-16");
        try {

            Writer w = new OutputStreamWriter(new FileOutputStream(FileName), "UTF-8");
            w.write(strMsg);
            w.flush();
            w.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String SoapMessageToString(SOAPMessage soapMessage)
    {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            soapMessage.writeTo(stream);
            String message = new String(stream.toByteArray(), "utf-8");
            return message;
        }
        catch (SOAPException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
