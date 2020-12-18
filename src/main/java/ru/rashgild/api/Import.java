package ru.rashgild.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ru.rashgild.generated.v1.fileoperationsln.ws.FileOperationsLn;
import ru.rashgild.generated.v1.fileoperationsln.ws.FileOperationsLnImplService;
import ru.rashgild.generated.v1.fileoperationsln.ws.SOAPException_Exception;
import ru.rashgild.utils.GlobalVariables;

@Path("/import")
public class Import {

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String get(@QueryParam("ogrn") String ogrn,
                      @QueryParam("eln") String eln,
                      @QueryParam("snils") String snils) {

        System.setProperty("javax.net.ssl.trustStore", GlobalVariables.pathandnameSSL);
        System.setProperty("javax.net.ssl.trustStorePassword", GlobalVariables.passwordSSL);
        FileOperationsLnImplService service = new FileOperationsLnImplService();
        FileOperationsLn start = service.getFileOperationsLnPort();
        try {
            start.getLNData(ogrn, eln, snils);
        } catch (SOAPException_Exception e) {
            e.printStackTrace();
        }
        return GlobalVariables.returningXml;
    }
}
