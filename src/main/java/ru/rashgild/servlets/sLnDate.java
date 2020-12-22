package ru.rashgild.servlets;

import org.apache.log4j.Logger;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FileOperationsLnService;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FileOperationsLnServiceImpl;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.InternalException;
import ru.rashgild.generated.v2.types.eln.mo.v01.FileOperationsLnUserGetLNDataOut;
import ru.rashgild.generated.v2.types.eln.mo.v01.GetLNDataRequest;
import ru.rashgild.utils.ResponseBuilderUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sLnDate")
public class sLnDate extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Logger logger = Logger.getLogger("sLnDate");
            logger.info("1) sLnDate");
            response.setContentType("text/html ;charset=UTF-8");

            String ogrn = request.getParameter("ogrn");
            String eln = request.getParameter("eln");
            String snils = request.getParameter("snils");

            snils = snils.replace("-", "").replace(" ", "");
            PrintWriter out = response.getWriter();
            out.print(ResponseBuilderUtil.createHtmlTemplate(ogrn, eln, snils));

            FileOperationsLnServiceImpl service_service = new FileOperationsLnServiceImpl();
            FileOperationsLnService start = service_service.getFileOperationsLnPort();
            GetLNDataRequest request1 = new GetLNDataRequest();
            request1.setOgrn(ogrn);
            request1.setLnCode(eln);
            request1.setSnils(snils);

            FileOperationsLnUserGetLNDataOut fileOperationsLnUserGetLNDataOut = new FileOperationsLnUserGetLNDataOut();
            try {
                fileOperationsLnUserGetLNDataOut = start.getLNData(request1);
                String responseOut = ResponseBuilderUtil.createResponseLnData(fileOperationsLnUserGetLNDataOut);
                out.print(responseOut);
            } catch (InternalException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
