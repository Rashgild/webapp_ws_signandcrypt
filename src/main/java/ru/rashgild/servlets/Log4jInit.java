package main.java.ru.rashgild.servlets;

import java.io.File;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

public class Log4jInit implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        String homeDir = event.getServletContext().getRealPath("/");
        File propertiesFile = new File(homeDir, "WEB-INF/log4j.properties");
        PropertyConfigurator.configure(propertiesFile.toString());
    }

    public void contextDestroyed(ServletContextEvent event) {
    }
}
