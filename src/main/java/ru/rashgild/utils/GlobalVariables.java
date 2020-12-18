package ru.rashgild.utils;

import java.io.File;

import ru.rashgild.entities.PrParseFileLnLpu;
import ru.rashgild.utils.Jaxb.JaxbParser;
import ru.rashgild.utils.Jaxb.Parser;

public class GlobalVariables {

    public static String returningXml = "";
    public static String DefaultLPU = "";

    //config GlobalVariables
    public static String dbhost = "";
    public static String dblogin = "";
    public static String dbpassword = "";
    public static String dbdriver = "";
    public static String passwordCertStor = "";
    public static String aliasCert = "";
    public static String pathToCert = "";
    public static String nameKstorage = "";
    public static String vkAlias = "";
    public static String vkPass = "";
    public static String docAlias = "";
    public static String docPass = "";
    public static String moAlias = "";
    public static String moPass = "";
    public static String ogrnMo = "";
    public static String pathtosave = "";
    public static String signXMLFileName = "";
    public static String cryptXMLFileName = "";
    public static String HDImageStorePath = "";
    public static String pathandnameSSL = "";
    public static String passwordSSL = "";
    public static String country = "-";
    public static String urlApi = "";
    public static String innerApi = "";

    public static String docGost = "";
    public static String vkGost = "";
    public static String moGost = "";


    //internal Global Temp Variables
    public static String t_ELN = "";
    public static PrParseFileLnLpu prparse;
    public static String Request = "";
    public static String Response = "";
    public static String DisabilityDocument_id = "";
    public static String Type = "";

    public static String requestParam = "";
    public static Integer requestParam2 = 0;
    public static String hash = "";
    public static String state = "";

    //parser
    public static Parser parser;
    public static File file;

    public static void setUp() {
        parser = new JaxbParser();
        file = new File(pathtosave + "person.xml");
    }
}
