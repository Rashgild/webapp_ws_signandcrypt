package ru.rashgild.servlets;

import ru.rashgild.utils.GlobalVariables;
import ru.rashgild.utils.SQL;
import ru.rashgild.utils.StoredQuery;
import ru.rashgild.utils.UTF8Control;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import static ru.rashgild.utils.GlobalVariables.*;

public class ConfigInit implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        Configure();
    }

    public void contextDestroyed(ServletContextEvent event) {
    }

    public static void Configure() {
        ResourceBundle resource = ResourceBundle.getBundle("application", new UTF8Control());
        dbhost = resource.getString("dbhost");
        dblogin = resource.getString("dblogin");
        dbpassword = resource.getString("dbpassword");
        dbdriver = resource.getString("dbdriver");

        passwordCertStor = resource.getString("passwordCertStor");
        aliasCert = resource.getString("aliasCert");
        aliasTestCert = resource.getString("aliasTestCert");
        if (!aliasTestCert.isEmpty()) {
            aliasCert = aliasTestCert;
        }

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
        HDImageStorePath = resource.getString("HDImageStorePath");
        urlApi = resource.getString("urlApi");
        innerApi = resource.getString("innerApi");

        docGost = resource.getString("docGost");
        vkGost = resource.getString("vkGost");
        moGost = resource.getString("moGost");

        ResultSet resultSet = SQL.select(StoredQuery.getDefultLPU());
        try {
            while (resultSet.next()) {
                GlobalVariables.DefaultLPU = resultSet.getString("keyvalue");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void downloadFile(String URL, String savePath) {
        try {
            java.net.URL url = new URL(URL);
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            FileOutputStream fis = new FileOutputStream(savePath);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = bis.read(buffer, 0, 1024)) != -1) {
                fis.write(buffer, 0, count);
            }
            fis.close();
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getUnixTime() {
        Date now = new Date();
        Long longTime = new Long(now.getTime() / 1000);
        return longTime.intValue();
    }

    private static void create() {
        File file = new File("version.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {
                out.print(getUnixTime());
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
