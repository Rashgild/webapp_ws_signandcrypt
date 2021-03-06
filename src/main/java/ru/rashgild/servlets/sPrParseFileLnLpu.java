package ru.rashgild.servlets;

import org.apache.log4j.Logger;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FIleOperationService;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FileOperationsLnService;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.InternalException;
import ru.rashgild.generated.v2.types.eln.mo.v01.*;
import ru.rashgild.generated.v2.types.eln.v01.Info;
import ru.rashgild.generated.v2.types.eln.v01.WSResult;
import ru.rashgild.service.DependencyInjection;
import ru.rashgild.utils.GlobalVariables;
import ru.rashgild.utils.SQL;
import ru.rashgild.utils.StoredQuery;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ru.rashgild.api.ApiUtils.isNotNullOrEmpty;

@WebServlet("/SetLnData")
public class sPrParseFileLnLpu extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Boolean isTest = Boolean.parseBoolean(request.getParameter("test"));
            long start = System.currentTimeMillis();
            response.setContentType("text/html ;charset=UTF-8");
            String id = request.getParameter("id");
            GlobalVariables.requestParam = id;
            request.setAttribute("id", id);

            boolean f = getLnHash(id, isTest);
            request.setAttribute("snils", f);
            if (f) {
                WSResult result = setRequest(isTest);
                List<Info.InfoRowset.InfoRow> rows = result.getInfo().getInfoRowset().getInfoRow();
                StringBuilder saveResult = new StringBuilder();
                String state = "", hash = "", status = "";

                if (rows != null && rows.size() > 0) {
                    for (Info.InfoRowset.InfoRow row : rows) {
                        state = row.getLnState();
                        hash = row.getLnHash();
                        if (state.equals("") && hash.equals("")) {
                            hash = GlobalVariables.hash;
                            state = GlobalVariables.state;
                        }
                        status = String.valueOf(row.getStatus());

                        request.setAttribute("row", row);
                        request.setAttribute("result", result);

                        saveResult.append(result.getMess());
                        try {
                            if (row.getErrors() != null && row.getErrors().getError().size() > 0) {
                                List<Info.InfoRowset.InfoRow.Errors.Error> errors = row.getErrors().getError();
                                request.setAttribute("errors", errors);
                                for (Info.InfoRowset.InfoRow.Errors.Error errs : errors) {
                                    saveResult.append(":".concat(errs.getErrMess()));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                request.setAttribute("time", ((System.currentTimeMillis() - start) / 1000));
                if (isNotNullOrEmpty(state) || isNotNullOrEmpty(hash)) {
                    SQL.sqlUpdIns(StoredQuery.updateStatusAndHash(state, hash, GlobalVariables.t_ELN));
                    if (status.equals("1")) {
                        SQL.sqlUpdIns(StoredQuery.updateDisRecord(id));
                        SQL.sqlUpdIns(StoredQuery.updateDisabilityDocument(id));
                    }
                }
                SQL.saveInBaseDate(saveResult.toString(), result.getStatus());
            }
            request.getRequestDispatcher("/WEB-INF/prParseFileLnLpu.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static WSResult setRequest(Boolean isTest) {
        FIleOperationService service = DependencyInjection.getImplementation(isTest);
        FileOperationsLnService start = service.getFileOperationsLnPort();
        Rowset rowset = new Rowset();
        PrParseFilelnlpuRequest prParseFilelnlpuRequest = new PrParseFilelnlpuRequest();
        PrParseFilelnlpuRequest.PXmlFile pXmlFile = new PrParseFilelnlpuRequest.PXmlFile();
        pXmlFile.setRowset(rowset);
        prParseFilelnlpuRequest.setPXmlFile(pXmlFile);
        try {
            return start.prParseFilelnlpu(prParseFilelnlpuRequest);
        } catch (InternalException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean getLnHash(String id, Boolean isTest) {
        Logger logger = Logger.getLogger("");
        logger.info("Get Hash");
        String snils = "", eln = "";
        ResultSet rs = SQL.select(StoredQuery.getLnHash(id));
        String hash = null;
        try {
            while (rs.next()) {
                hash = rs.getString("lasthash");
                System.out.println(GlobalVariables.hash + ">>>>from base hash");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (hash == null) {
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
                FIleOperationService service = DependencyInjection.getImplementation(isTest);
                FileOperationsLnService start = service.getFileOperationsLnPort();

                GetLNDataRequest request = new GetLNDataRequest();
                request.setOgrn(GlobalVariables.ogrnMo);
                request.setLnCode(eln);
                request.setSnils(snils);

                FileOperationsLnUserGetLNDataOut fileOperationsLnUserGetLNDataOut = start.getLNData(request);
                ResponseRow responseRow = fileOperationsLnUserGetLNDataOut.getData().getOutRowset().getResponseRow();
                GlobalVariables.hash = responseRow.getLnHash();
                System.out.println(GlobalVariables.hash + ">>>>has");
                GlobalVariables.state = responseRow.getLnState();

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
