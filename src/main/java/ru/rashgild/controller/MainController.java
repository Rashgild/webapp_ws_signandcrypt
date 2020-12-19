package ru.rashgild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FileOperationsLnService;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FileOperationsLnServiceImpl;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.InternalException;
import ru.rashgild.generated.v2.types.eln.mo.v01.FileOperationsLnUserGetLNDataOut;
import ru.rashgild.generated.v2.types.eln.mo.v01.GetLNDataRequest;
import ru.rashgild.service.CertificateService;
import ru.rashgild.service.CertificateServiceImpl;
import ru.rashgild.utils.GlobalVariables;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import static ru.rashgild.servlets.ConfigInit.Configure;
import static ru.rashgild.utils.GlobalVariables.*;

@Controller
public class MainController {

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @Autowired
    private CertificateService service;


    @RequestMapping(value = "test", method = RequestMethod.GET)
    public void get() throws Exception {
        /*sun.util.logging.PlatformLogger platformLogger = PlatformLogger.getLogger("java.util.prefs");
        platformLogger.setLevel(PlatformLogger.Level.OFF);*/
        //Preferences.systemRoot();

       // ru.CryptoPro.JCP.tools.License license =new License();

        //ru.CryptoPro.JCSP.JCSPLicense -serial "serial_number" -company "company_name" -storea
        Configure();
        X509Certificate cert = service.extractCertificateFromCertStore(passwordCertStor, aliasCert, pathToCert);
       service.getPrivateKey(GlobalVariables.moPass, GlobalVariables.moAlias);
        System.out.println(cert);
    }


    @RequestMapping(value = "sLnDate", method = RequestMethod.GET)
    public void getLn(@RequestParam("ogrn") String ogrn,
                      @RequestParam("eln") String eln,
                      @RequestParam("snils") String snils) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException {

        Configure();
        System.out.println(GlobalVariables.dbhost + ">>>>>>>>>>>>>>>>>>>>>>");
        snils = snils.replace("-", "").replace(" ", "");

        FileOperationsLnServiceImpl service_service = new FileOperationsLnServiceImpl();
        FileOperationsLnService start = service_service.getFileOperationsLnPort();

        GetLNDataRequest request1 = new GetLNDataRequest();
        request1.setOgrn(ogrn);
        request1.setLnCode(eln);
        request1.setSnils(snils);

        FileOperationsLnUserGetLNDataOut fileOperationsLnUserGetLNDataOut = new FileOperationsLnUserGetLNDataOut();
        try {
            fileOperationsLnUserGetLNDataOut = start.getLNData(request1);
        } catch (InternalException e) {
            e.printStackTrace();
        }

        System.out.println(fileOperationsLnUserGetLNDataOut.getInfo());
        System.out.println(fileOperationsLnUserGetLNDataOut.getMess());
        System.out.println(fileOperationsLnUserGetLNDataOut.getStatus());
        System.out.println(fileOperationsLnUserGetLNDataOut.getData().getOutRowset().getResponseRow().getLnState());

    }
}