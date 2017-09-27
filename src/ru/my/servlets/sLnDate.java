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
import java.util.List;

import static ru.my.helpers_operations.SQL.Query;
import static ru.my.helpers_operations.StoredQuery.SaveNumber;

/**
 * Created by rkurbanov on 28.06.2017.
 */
@WebServlet("/sLnDate")
public class sLnDate extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Logger logger=Logger.getLogger("simple");
        logger.info("1) NewLnNum");
        response.setContentType("text/html ;charset=UTF-8");

        String ogrn = request.getParameter("ogrn");
        String eln = request.getParameter("eln");
        String snils = request.getParameter("snils");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>" +
                "  <meta charset=\"UTF-8\" />\n" +
                "  <title>SignAndCrypt</title>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> </head>");

        out.println("<body>");
        out.print("<H1> ogrn="+ogrn+"</H1>");
        out.print("<H1> eln="+eln+"</H1>");
        out.print("<H1> snils="+snils+"</H1>");

        System.setProperty("javax.net.ssl.trustStore",GlobalVariables.pathandnameSSL);//КОНФ
        System.setProperty("javax.net.ssl.trustStorePassword", GlobalVariables.passwordSSL);
        FileOperationsLnImplService service = new  FileOperationsLnImplService();
        FileOperationsLn start = service.getFileOperationsLnPort();
        try {

            FileOperationsLnUserGetLNDataOut fileOperationsLnUserGetLNDataOut = start.getLNData(ogrn,eln,snils);

            out.print("<H1> info="+fileOperationsLnUserGetLNDataOut.getINFO()+"</H1>");
            out.print("<H1> mess="+fileOperationsLnUserGetLNDataOut.getMESS()+"</H1>");
            out.print("<H1> status="+fileOperationsLnUserGetLNDataOut.getSTATUS()+"</H1>");

            out.print("<H1> name="+fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getNAME()+"</H1>");
            out.print("<H1> birthday="+fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getBIRTHDAY()+"</H1>");
            out.print("<H1> state="+fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getLNSTATE()+"</H1>");
            out.print("<H1> hash="+fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getLNHASH()+"</H1>");

            List<TREATFULLPERIOD> treatfullperiods = fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getTREATPERIODS().getTREATFULLPERIOD();

            ROW.LNRESULT lnresult = fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getLNRESULT();
            if(lnresult!=null){
                out.print("<H1> co: "+lnresult.getNEXTLNCODE()+"</H1>");
                out.print("<H1> doc role: "+lnresult.getOTHERSTATEDT()+"</H1>");
                out.print("<H1> doc role: "+lnresult.getMSERESULT()+"</H1>");
                out.print("<H1> выход на работу: "+lnresult.getRETURNDATELPU()+"</H1>");
            }
            for(TREATFULLPERIOD treatfullperiod: treatfullperiods){
                out.print("<H1> ______________________</H1>");
                out.print("<H1> Период: "+treatfullperiod.getTREATPERIOD().getTREATDT1()+"</H1>");
                out.print("<H1> Период2: "+treatfullperiod.getTREATPERIOD().getTREATDT2()+"</H1>");
                out.print("<H1> ВК: "+treatfullperiod.getTREATCHAIRMAN()+"</H1>");
                out.print("<H1> ВК роль: "+treatfullperiod.getTREATCHAIRMANROLE()+"</H1>");
                out.print("<H1> doc: "+treatfullperiod.getTREATPERIOD().getTREATDOCTOR()+"</H1>");
                out.print("<H1> doc role: "+treatfullperiod.getTREATPERIOD().getTREATDOCTORROLE()+"</H1>");
                out.print("<H1> ______________________</H1>");
            }

        } catch (SOAPException_Exception e) {
            e.printStackTrace();
        }


    }
}
