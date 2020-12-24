package ru.rashgild.service;

import org.apache.log4j.Logger;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FIleOperationService;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FileOperationsLnServiceImpl;
import ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01.FileOperationsLnServiceTestImpl;
import ru.rashgild.utils.SSLUtil;

public class DependencyInjection {

    public static FIleOperationService getImplementation(Boolean isTest) {
        Logger logger = Logger.getLogger("DependencyInjection");

        if (isTest != null && isTest) {
            logger.info("Test service running");
            SSLUtil.turnOffSslChecking();
            return new FileOperationsLnServiceTestImpl();
        } else {
            return new FileOperationsLnServiceImpl();
        }
    }
}
