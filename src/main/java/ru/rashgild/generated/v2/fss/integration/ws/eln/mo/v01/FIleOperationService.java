package ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01;

import javax.xml.ws.WebEndpoint;

public interface FIleOperationService {

    @WebEndpoint(name = "FileOperationsLnPort")
    FileOperationsLnService getFileOperationsLnPort();
}
