package ru.rashgild.api;

import org.apache.log4j.Logger;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FileOperationsLnService;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FileOperationsLnServiceImpl;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.InternalException;
import ru.rashgild.generated.v2.types.eln.mo.v01.FileOperationsLnUserGetLNDataOut;
import ru.rashgild.generated.v2.types.eln.mo.v01.GetLNDataRequest;
import ru.rashgild.utils.ResponseBuilderUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.io.PrintWriter;

@Path("/ForwardServlet")
public class Forward {

    @GET
    @Produces("text/html")
    public void doGet(@Context HttpServletRequest request,
                      @Context HttpServletResponse response) {
        try {
            Logger logger = Logger.getLogger("Forward");
            logger.info("1) ForwardServlet");
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

            FileOperationsLnUserGetLNDataOut fileOperationsLnUserGetLNDataOut;
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


    private static void forward(HttpServletRequest request, HttpServletResponse response, String forwardString) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        try {
            request.setCharacterEncoding("UTF-8");
            request.getRequestDispatcher(forwardString).forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
