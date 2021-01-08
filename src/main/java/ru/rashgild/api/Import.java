package ru.rashgild.api;

import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FileOperationsLnService;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FileOperationsLnServiceImpl;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.InternalException;
import ru.rashgild.generated.v2.types.eln.mo.v01.GetLNDataRequest;
import ru.rashgild.utils.GlobalVariables;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/import")
public class Import {

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String get(@QueryParam("ogrn") String ogrn,
                      @QueryParam("eln") String eln,
                      @QueryParam("snils") String snils) {

        FileOperationsLnServiceImpl service = new FileOperationsLnServiceImpl();
        FileOperationsLnService start = service.getFileOperationsLnPort();
        GetLNDataRequest request1 = new GetLNDataRequest();
        request1.setOgrn(ogrn);
        request1.setLnCode(eln);
        request1.setSnils(snils);
        try {
            start.getLNData(request1);
        } catch (InternalException e) {
            e.printStackTrace();
        }
        return GlobalVariables.returningXml;
    }
}
