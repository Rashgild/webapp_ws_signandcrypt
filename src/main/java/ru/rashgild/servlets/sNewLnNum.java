package ru.rashgild.servlets;

import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FileOperationsLnService;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FileOperationsLnServiceImpl;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.InternalException;
import ru.rashgild.generated.v2.types.eln.mo.v01.FileOperationsLnUserGetNewLNNumOut;
import ru.rashgild.generated.v2.types.eln.mo.v01.GetNewLNNumRequest;
import ru.rashgild.utils.GlobalVariables;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static ru.rashgild.utils.SQL.sqlUpdIns;
import static ru.rashgild.utils.StoredQuery.SaveNumber;

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

        //System.setProperty("javax.net.ssl.trustStore", GlobalVariables.pathandnameSSL);
        //System.setProperty("javax.net.ssl.trustStorePassword", GlobalVariables.passwordSSL);

        FileOperationsLnServiceImpl service = new FileOperationsLnServiceImpl();
        FileOperationsLnService start = service.getFileOperationsLnPort();

        try {
            GetNewLNNumRequest getNewLNNumRangeRequest = new GetNewLNNumRequest();
            getNewLNNumRangeRequest.setOgrn(ogrn);
            FileOperationsLnUserGetNewLNNumOut lnNum = start.getNewLNNum(getNewLNNumRangeRequest);
            out.println("<H1>" + lnNum.getMess() + "</H1>");
            out.println("<H1> Получен номер: </H1>");
            out.println("<H1>1)" + lnNum.getData() + "</H1>");
            sqlUpdIns(SaveNumber(lnNum.getData()));

        } catch (InternalException e) {
            e.printStackTrace();
        }
    }
}
