
package ru.rashgild.generated.v2.fss.integration.ws.eln.mo.v01;

import java.net.MalformedURLException;
import java.net.URL;
import javax.jws.HandlerChain;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 *
 */
@WebServiceClient(name = "FileOperationsLnService", targetNamespace = "http://www.fss.ru/integration/ws/eln/mo/v01", wsdlLocation = "https://docs-test.fss.ru/WSLnCryptoV20/FileOperationsLnService?WSDL")
@HandlerChain(file = "ru/rashgild/interceptor/handler-chain.xml")
public class FileOperationsLnServiceTestImpl extends Service implements FIleOperationService {

    private final static URL FILEOPERATIONSLNSERVICE_WSDL_LOCATION;
    private final static WebServiceException FILEOPERATIONSLNSERVICE_EXCEPTION;
    private final static QName FILEOPERATIONSLNSERVICE_QNAME = new QName("http://www.fss.ru/integration/ws/eln/mo/v01", "FileOperationsLnService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://docs-test.fss.ru/WSLnCryptoV20/FileOperationsLnService?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        FILEOPERATIONSLNSERVICE_WSDL_LOCATION = url;
        FILEOPERATIONSLNSERVICE_EXCEPTION = e;
    }

    public FileOperationsLnServiceTestImpl() {
        super(__getWsdlLocation(), FILEOPERATIONSLNSERVICE_QNAME);
    }

    public FileOperationsLnServiceTestImpl(WebServiceFeature... features) {
        super(__getWsdlLocation(), FILEOPERATIONSLNSERVICE_QNAME, features);
    }

    public FileOperationsLnServiceTestImpl(URL wsdlLocation) {
        super(wsdlLocation, FILEOPERATIONSLNSERVICE_QNAME);
    }

    public FileOperationsLnServiceTestImpl(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, FILEOPERATIONSLNSERVICE_QNAME, features);
    }

    public FileOperationsLnServiceTestImpl(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FileOperationsLnServiceTestImpl(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns FileOperationsLnService
     */
    @WebEndpoint(name = "FileOperationsLnPort")
    @Override
    public FileOperationsLnService getFileOperationsLnPort() {
        return super.getPort(new QName("http://www.fss.ru/integration/ws/eln/mo/v01", "FileOperationsLnPort"), FileOperationsLnService.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FileOperationsLnService
     */
    @WebEndpoint(name = "FileOperationsLnPort")
    public FileOperationsLnService getFileOperationsLnPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.fss.ru/integration/ws/eln/mo/v01", "FileOperationsLnPort"), FileOperationsLnService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (FILEOPERATIONSLNSERVICE_EXCEPTION!= null) {
            throw FILEOPERATIONSLNSERVICE_EXCEPTION;
        }
        return FILEOPERATIONSLNSERVICE_WSDL_LOCATION;
    }

}
