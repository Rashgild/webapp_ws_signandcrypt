package ru.rashgild.servlets;

import org.apache.log4j.Logger;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FileOperationsLnService;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FileOperationsLnServiceImpl;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.InternalException;
import ru.rashgild.generated.v2.types.eln.mo.v01.FileOperationsLnUserGetNewLNNumRangeOut;
import ru.rashgild.generated.v2.types.eln.mo.v01.GetNewLNNumRangeRequest;
import ru.rashgild.utils.GlobalVariables;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static ru.rashgild.utils.SQL.sqlUpdIns;
import static ru.rashgild.utils.StoredQuery.SaveNumber;

@WebServlet("/sNewLnNumRange")
public class sNewLnNumRange extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Logger logger = Logger.getLogger("simple");
        logger.info("1) NewLnNumRange");
        response.setContentType("text/html ;charset=UTF-8");

        String ogrn = request.getParameter("ogrn");
        int count = Integer.parseInt(request.getParameter("count"));
        GlobalVariables.requestParam = ogrn;
        GlobalVariables.requestParam2 = count;
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>" +
                "  <meta charset=\"UTF-8\" />\n" +
                "  <title>SignAndCrypt</title>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> </head>");
        out.println("<body>");
        out.print("<H1> ogrn=" + ogrn + "</H1>");
        out.print("<H1> count=" + count + "</H1>");

        FileOperationsLnServiceImpl service = new FileOperationsLnServiceImpl();
        FileOperationsLnService start = service.getFileOperationsLnPort();
        try {
            GetNewLNNumRangeRequest getNewLNNumRangeRequest = new GetNewLNNumRangeRequest();
            getNewLNNumRangeRequest.setCntLnNumbers(count);
            getNewLNNumRangeRequest.setOgrn(ogrn);
            FileOperationsLnUserGetNewLNNumRangeOut numRangeOut = start.getNewLNNumRange(getNewLNNumRangeRequest);
            out.println("<H1>" + numRangeOut.getMess() + "</H1>");
            List<String> lnNumbers = numRangeOut.getData().getLnCode();
            out.println("<H1> Получены номера: </H1>");
            int index = 0;
            for (String lnNumber : lnNumbers) {
                out.println("<H1>" + (++index) + ") " + lnNumber + "</H1>");
                sqlUpdIns(SaveNumber(lnNumber));
                index++;
            }

        } catch (InternalException e) {
            e.printStackTrace();
        }
        out.println("</body>");
        out.println("</html>");
    }
}
