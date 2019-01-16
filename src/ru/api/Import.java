package ru.api;

import ru.ibs.fss.ln.ws.fileoperationsln.FileOperationsLn;
import ru.ibs.fss.ln.ws.fileoperationsln.FileOperationsLnImplService;
import ru.ibs.fss.ln.ws.fileoperationsln.FileOperationsLnUserGetLNDataOut;
import ru.my.helpers_operations.GlobalVariables;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/import")
public class Import {

    @GET
    public String getEln(String ogrn,
                         String eln,
                         String snils) throws Exception {

        System.setProperty("javax.net.ssl.trustStore", GlobalVariables.pathandnameSSL);//КОНФ
        System.setProperty("javax.net.ssl.trustStorePassword", GlobalVariables.passwordSSL);
        FileOperationsLnImplService service = new FileOperationsLnImplService();
        FileOperationsLn start = service.getFileOperationsLnPort();
        FileOperationsLnUserGetLNDataOut fget = start.getLNData(ogrn, eln, snils);
        return String.valueOf(fget);
    }
}
