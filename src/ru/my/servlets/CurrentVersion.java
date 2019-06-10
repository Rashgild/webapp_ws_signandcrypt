package ru.my.servlets;

import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CurrentVersion")
public class CurrentVersion extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        Calendar cal = Calendar.getInstance();
        String path = new CurrentVersion().getClass().getResource("/../../").getFile() + "version.txt";
        path = path.replace("%20", " ");
        cal.setTimeInMillis(Long.parseLong(ReadFile(path)) * 1000);
        String ver = "version by " + cal.get(Calendar.DAY_OF_MONTH) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.YEAR) + " (" + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ")";
        response.getWriter().write(ver);
    }

    public static String ReadFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(fileName)) {
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return stringBuilder.toString();
    }
}
