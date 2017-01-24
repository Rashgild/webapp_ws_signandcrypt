package ru.my.helpers_operations;

import org.apache.log4j.Logger;
import ru.my.entities.PrParseFileLnLpu;
import ru.my.helpers_operations.Jaxb.JaxbParser;
import ru.my.helpers_operations.Jaxb.Parser;

import java.io.File;
import java.util.ResourceBundle;

/**
 * Created by rkurbanov on 19.05.2017.
 */
public class GlobalVariables {

    //config GlobalVariables
    public static String dbhost = "";
    public static String dblogin = "";
    public static String dbpassword ="";
    public static String dbdriver ="";
    public static String passwordCertStor = "";
    public static String aliasCert = "";
    public static String pathToCert ="";
    public static String nameKstorage ="";
    public static String vkAlias = "";
    public static String vkPass = "";
    public static String docAlias ="";
    public static String docPass = "";
    public static String moAlias = "";
    public static String moPass ="";
    public static String ogrnMo="";
    public static String pathtosave="";
    public static String signXMLFileName="";
    public static String cryptXMLFileName="";



   /* public static void Configure(){

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
        logger.info("Конфигурация найдена и загружена");
    }*/


    //internal Global Temp Variables
    public static String t_ELN="";
    public static PrParseFileLnLpu prparse;
    public static String Request="";


    //parser
    public static Parser parser;
    public static File file;

    public static void setUp() throws Exception {
        parser = new JaxbParser();
        file = new File(pathtosave+"person.xml");
    }


}
