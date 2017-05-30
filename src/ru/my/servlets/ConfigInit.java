package ru.my.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ru.my.helpers_operations.GlobalVariables;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.util.ResourceBundle;

import static ru.my.helpers_operations.GlobalVariables.*;

//Created by rashgild on 19.05.2017.

public class ConfigInit implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        Configure();
    }

    public void contextDestroyed(ServletContextEvent event) {}

    private static void Configure(){

        Logger logger=Logger.getLogger("simple");

        ResourceBundle resource = ResourceBundle.getBundle("config");

        dbhost = resource.getString("dbhost");
        dblogin = resource.getString("dblogin");
        dbpassword = resource.getString("dbpassword");
        dbdriver = resource.getString("dbdriver");

        passwordCertStor = resource.getString("passwordCertStor");
        aliasCert = resource.getString("aliasCert");
        pathToCert = resource.getString("pathToCert");
        nameKstorage = resource.getString("nameKstorage");

        vkAlias = resource.getString("vkAlias");
        vkPass = resource.getString("vkPass");
        docAlias = resource.getString("docAlias");
        docPass = resource.getString("docPass");
        moAlias = resource.getString("moAlias");
        moPass = resource.getString("moPass");

        ogrnMo = resource.getString("ogrnMo");
        pathtosave = resource.getString("pathtosave");
        signXMLFileName = resource.getString("signXMLFileName");

        cryptXMLFileName = resource.getString("cryptXMLFileName");

        pathandnameSSL= resource.getString("pathandnameSSL");
        passwordSSL= resource.getString("passwordSSL");
        HDImageStorePath=resource.getString("HDImageStorePath");
        logger.info("Конфигурация найдена и загружена");
    }
}
