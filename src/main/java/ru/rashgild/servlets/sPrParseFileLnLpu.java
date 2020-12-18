package main.java.ru.rashgild.servlets;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import main.java.ru.rashgild.utils.GlobalVariables;
import main.java.ru.rashgild.utils.SQL;
import main.java.ru.rashgild.utils.StoredQuery;
import main.java.ru.genereted.v1.fileoperationsln.ws.*;

import static main.java.ru.rashgild.utils.GlobalVariables.*;

@WebServlet("/SetLnData")
public class sPrParseFileLnLpu extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            long start = System.currentTimeMillis();
            response.setContentType("text/html ;charset=UTF-8");
            String id = request.getParameter("id");
            GlobalVariables.requestParam = id;
            request.setAttribute("id", id);

            boolean f = getLnHash(id);
            request.setAttribute("snils", f);
            if (f) {
                WSResult result = setRequest();
                List<INFO.ROWSET.ROW> rows = result.getINFO().getROWSET().getROW();
                StringBuilder saveResult = new StringBuilder();
                String state = "", hash = "", status = "";

                if (rows != null && rows.size() > 0) {
                    for (INFO.ROWSET.ROW row : rows) {

                        state = row.getLNSTATE();
                        hash = row.getLNHASH();
                        if (state.equals("") && hash.equals("")) {
                            hash = GlobalVariables.hash;
                            state = GlobalVariables.state;
                        }
                        status = String.valueOf(row.getSTATUS());

                        request.setAttribute("row", row);
                        request.setAttribute("result", result);

                        saveResult.append(result.getMESS());
                        try {
                            if (row.getERRORS() != null && row.getERRORS().getERROR().size() > 0) {
                                List<INFO.ROWSET.ROW.ERRORS.ERROR> errors = row.getERRORS().getERROR();
                                request.setAttribute("errors", errors);
                                for (INFO.ROWSET.ROW.ERRORS.ERROR errs : errors) {
                                    saveResult.append(":".concat(errs.getERRMESS()));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                request.setAttribute("time", ((System.currentTimeMillis() - start) / 1000));
                if (state != null && !state.equals("") || hash != null && !hash.equals("")) {
                    SQL.sqlUpdIns(StoredQuery.SaveStatusAndHash(state, hash, GlobalVariables.t_ELN));
                    if (status.equals("1")) {
                        SQL.sqlUpdIns(StoredQuery.updateDisRecord(id));
                    }
                }
                SQL.saveInBaseDate(saveResult.toString(), result.getSTATUS());
            }
            request.getRequestDispatcher("/WEB-INF/prParseFileLnLpu.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static WSResult setRequest() {
        System.setProperty("javax.net.ssl.trustStore", pathandnameSSL);
        System.setProperty("javax.net.ssl.trustStorePassword", passwordSSL);
        FileOperationsLnImplService service = new FileOperationsLnImplService();
        FileOperationsLn start = service.getFileOperationsLnPort();
        ROWSET rowset = new ROWSET();
        PrParseFilelnlpuElement prParseFilelnlpuElement = new PrParseFilelnlpuElement();
        PrParseFilelnlpuElement.PXmlFile pXmlFile = new PrParseFilelnlpuElement.PXmlFile();
        pXmlFile.setROWSET(rowset);
        prParseFilelnlpuElement.setPXmlFile(pXmlFile);
        WSResult result = null;
        try {
            result = start.prParseFilelnlpu(prParseFilelnlpuElement);
            return result;
        } catch (SOAPException_Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static boolean getLnHash(String id) {
        Logger logger = Logger.getLogger("");
        logger.info("Get Hash");
        String snils = "", eln = "";
        ResultSet rs = SQL.select(StoredQuery.getLnHash(id));
        String hash = null;
        try {
            while (rs.next()){
                hash = rs.getString("lasthash");
                System.out.println(GlobalVariables.hash + ">>>>from base hash");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(hash==null) {
            ResultSet resultSet = SQL.select(StoredQuery.getLNandSnils(id));
            try {
                while (resultSet.next()) {
                    snils = resultSet.getString("snils");
                    eln = resultSet.getString("ln_code");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (snils == null || snils.length() <= 0) return false;
            snils = snils.replace(" ", "").replace("-", "");
            logger.info("Snils: " + snils);
            logger.info("Eln: " + eln);
            try {
                System.setProperty("javax.net.ssl.trustStore", GlobalVariables.pathandnameSSL);
                System.setProperty("javax.net.ssl.trustStorePassword", GlobalVariables.passwordSSL);
                FileOperationsLnImplService service = new FileOperationsLnImplService();
                FileOperationsLn start = service.getFileOperationsLnPort();
                FileOperationsLnUserGetLNDataOut fileOperationsLnUserGetLNDataOut = start.getLNData(GlobalVariables.ogrnMo, eln, snils);
                for (ROW row : fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW()) {
                    GlobalVariables.hash = row.getLNHASH();
                    System.out.println(GlobalVariables.hash + ">>>>has");
                    GlobalVariables.state = row.getLNSTATE();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        } else {
            GlobalVariables.hash = hash;
            return true;
        }
    }
}
