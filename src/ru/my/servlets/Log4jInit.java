package ru.my.servlets;
import javax.servlet.*;
import java.io.File;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by rkurbanov on 23.05.2017.
 */


public class Log4jInit implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        String homeDir=event.getServletContext().getRealPath("/");
        File propertiesFile=new File(homeDir,"WEB-INF/log4j.properties");
        PropertyConfigurator.configure(propertiesFile.toString());
    }

    public void contextDestroyed(ServletContextEvent event) {}
}
