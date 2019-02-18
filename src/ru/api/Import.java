package ru.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ru.ibs.fss.ln.ws.fileoperationsln.FileOperationsLn;
import ru.ibs.fss.ln.ws.fileoperationsln.FileOperationsLnImplService;
import ru.ibs.fss.ln.ws.fileoperationsln.SOAPException_Exception;
import ru.my.helpers_operations.GlobalVariables;

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
