package ru.rashgild.servlets;

import org.apache.log4j.Logger;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FIleOperationService;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FileOperationsLnService;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.InternalException;
import ru.rashgild.generated.v2.types.eln.mo.v01.DisableLnRequest;
import ru.rashgild.generated.v2.types.eln.v01.WSResult;
import ru.rashgild.service.DependencyInjection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/sDisableLn")
public class sDisableLn extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Logger logger = Logger.getLogger("simple");
        logger.info("1) sDisableLn");
        response.setContentType("text/html ;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String ogrn = request.getParameter("ogrn");
        String lnCode = request.getParameter("lnCode");
        String snils = request.getParameter("snils");
        String reasonCode = request.getParameter("reasonCode");
        String reason = request.getParameter("reason");
        Boolean isTest = Boolean.parseBoolean(request.getParameter("test"));

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>" +
                "  <meta charset=\"UTF-8\" />\n" +
                "  <title>SignAndCrypt</title>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> </head>");

        out.println("<body>");

        FIleOperationService service = DependencyInjection.getImplementation(isTest);
        FileOperationsLnService start = service.getFileOperationsLnPort();
        try {
            DisableLnRequest disableLnRequest = new DisableLnRequest();
            disableLnRequest.setLnCode(lnCode);
            disableLnRequest.setOgrn(ogrn);
            disableLnRequest.setSnils(snils);
            disableLnRequest.setReason(reason);
            disableLnRequest.setReasonCode(reasonCode);
            WSResult wsResult = start.disableLn(disableLnRequest);
            out.println("<H1>Статус:" + wsResult.getMess() + "</H1>");
        } catch (InternalException e) {
            e.printStackTrace();
        }
        out.println("</body></html>");
    }
}
