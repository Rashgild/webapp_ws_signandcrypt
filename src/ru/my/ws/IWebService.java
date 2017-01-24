package ru.my.ws;

import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;

/**
 * Created by rkurbanov on 16.09.16.
 */
@javax.jws.WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface IWebService {
    @WebMethod
    int GetCalculaton(int a);
}
