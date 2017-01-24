package ru.my.ws;


import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by rkurbanov on 10.11.16.
 */
//@WebService(endpointInterface = "ru.my.ws.IWebService")
public class WebServiceIMPL // implements IWebService
{
    //@Override
    //@WebMethod
    public int GetCalculaton(int a)
    {
        a = a*a;
        System.out.println(a);
        return a;
    }
}
