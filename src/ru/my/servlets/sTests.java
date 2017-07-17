package ru.my.servlets;

import org.apache.log4j.Logger;
import ru.my.helpers_operations.SQL;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rkurbanov on 03.07.2017.
 */
@WebServlet("/sTests")
public class sTests  extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Logger logger=Logger.getLogger("simple");
        response.setContentType("text/html ;charset=UTF-8");
        PrintWriter out = response.getWriter();



        String id = request.getParameter("id =");

        ResultSet resultSet = SQL.Query("select * from patient where id= "+id);

        out.println("<html>");
        out.println("<head>" +
                " <script language=\"JavaScript\" type=\"text/javascript\" src=\"test.js\"></script> "+
                "  <meta charset=\"UTF-8\" />\n" +
                "  <title>SignAndCrypt</title>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> </head>");

        out.println("<body>");

        try {
            while (resultSet.next()) {
                out.println("<H1>"+resultSet.getString("firstname")+"</H1>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.println("</body>");
        out.println("</html>");
    }
}
