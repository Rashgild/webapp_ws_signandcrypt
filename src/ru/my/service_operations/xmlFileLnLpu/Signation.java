package ru.my.service_operations.xmlFileLnLpu;

import ru.my.entities.*;
import ru.my.signAndCrypt.Sign;
import javax.xml.soap.SOAPMessage;
import java.util.List;
import static ru.my.helpers_operations.GlobalVariables.*;
import static ru.my.helpers_operations.WorkWithXML.SaveSOAPToXML;

/**
 * Created by rkurbanov on 22.05.2017.
 */
public class Signation {

    protected static SOAPMessage Start(PrParseFileLnLpu prParseFileLnLpu, SOAPMessage message) throws Exception {

        List<ROW> rows = UnPack(prParseFileLnLpu);
        //org.apache.xml.security.Init.init();
        SaveSOAPToXML(signXMLFileName, message);
        for (int i = 0; i < rows.size(); i++) {

            System.out.println("Sing " + i + " message");

            t_ELN = rows.get(i).getLncode();

            System.out.println(t_ELN);
            System.out.println("Sign like MO with alias: " + moAlias);


            message = Sign.SignationByParametrs(
                    "http://eln.fss.ru/actor/mo/" + ogrnMo + "/" + rows.get(i).getAttribId(),
                    "#" + rows.get(i).getAttribId(), moAlias, moPass, t_ELN);

            SaveSOAPToXML(signXMLFileName, message);
            ROW.HOSPITAL_BREACH hospital_breach = new ROW.HOSPITAL_BREACH();
            ROW.LN_RESULT ln_result = new ROW.LN_RESULT();
            List<ROW.HOSPITAL_BREACH> hospital_breaches = rows.get(i).getHospitalbreach();
            List<ROW.LN_RESULT> ln_results = rows.get(i).getLnresult();
            ln_result = ln_results.get(0);
            hospital_breach = hospital_breaches.get(0);


            if (ln_result.getAttribId() != null) {
                System.out.println("Sign results");
                message = Sign.SignationByParametrs("http://eln.fss.ru/actor/doc/" + t_ELN + "_2_doc",
                        "#" + ln_result.getAttribId(), docAlias, docPass, t_ELN);
                SaveSOAPToXML(signXMLFileName, message);
            }


            if (hospital_breach.getAttributeId() != null) {
                System.out.println("Sign hospital brach");
                message = Sign.SignationByParametrs("http://eln.fss.ru/actor/doc/" + t_ELN + "_1_doc",
                        "#" + hospital_breach.getAttributeId(), docAlias, docPass, t_ELN);
                SaveSOAPToXML(signXMLFileName, message);
            }


            TREAT_FULL_PERIOD treat_full_period = new TREAT_FULL_PERIOD();
            List<TREAT_FULL_PERIOD> treat_full_periods = rows.get(i).getTREAT_PERIODS();
            TREAT_PERIOD treat_period = new TREAT_PERIOD();


            /**Sign VK*/
            for (int j = 0; j < treat_full_periods.size(); j++) {
                System.out.println(i + " message " + j + " vk");
                treat_full_period = treat_full_periods.get(j);
                if (treat_full_period.getAttribIdVk() != null) {
                    message = Sign.SignationByParametrs("http://eln.fss.ru/actor/doc/" + treat_full_period.getAttribIdVk(),
                            "#" + treat_full_period.getAttribIdVk(), vkAlias, vkPass, t_ELN);
                    SaveSOAPToXML(signXMLFileName, message);
                }

                List<TREAT_PERIOD> treat_periods1 = treat_full_periods.get(j).getTreat_period();

                /** doctors sign*/
                for (int k = 0; k < treat_periods1.size(); k++) {
                    treat_period = treat_periods1.get(k);
                    System.out.println(i + " message, vk " + j + " | " + k + " doc");
                    if (treat_period.getAttribId() != null) {
                        message = Sign.SignationByParametrs("http://eln.fss.ru/actor/doc/" + treat_period.getAttribId(),
                                "#" + treat_period.getAttribId(), docAlias, docPass, t_ELN);
                        SaveSOAPToXML(signXMLFileName, message);
                    }
                }
            }
        }
        return message;
    }


    /**
     * распаковщик объекта
     **/
    private static List<ROW> UnPack(PrParseFileLnLpu prParseFileLnLpu) {
        List<PrParseFileLnLpu.Reqest> reqests = prParseFileLnLpu.getRequests();
        List<PrParseFileLnLpu.Reqest.pXmlFile> pXmlFiles = reqests.get(0).getpXmlFiles();
        List<ROWSET> rowsets = pXmlFiles.get(0).getRowset();
        List<ROW> rows = rowsets.get(0).getRow();
        return rows;
    }
}
