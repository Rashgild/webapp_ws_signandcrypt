package ru.my.servlets;

import org.apache.log4j.Logger;
import ru.my.helpers_operations.GlobalVariables;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//Created by rashgild on 19.05.2017.

@WebServlet("/sShowConf")
public class sShowConf extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Logger logger=Logger.getLogger("simple");

        response.setContentType("text/html ;charset=UTF-8");
        //String id = request.getParameter("id");
        //GlobalVariables.requestParam = request.getParameter("id");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>" +
                " <script language=\"JavaScript\" type=\"text/javascript\" src=\"test.js\"></script> "+
                "  <meta charset=\"UTF-8\" />\n" +
                "  <title>SignAndCrypt</title>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> </head>");

        out.println("<body>");

        out.println("<b>Хост:</b>");
        out.println("<input style=\"font-size: 15px;\" type=\"text\" size=\"40\" value=\""+GlobalVariables.dbhost+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.dblogin+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.dbpassword+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.dbdriver+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.passwordCertStor+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.HDImageStorePath+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.aliasCert+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.pathToCert+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.nameKstorage+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.vkAlias+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.vkPass+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.docAlias+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.docPass+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.moAlias+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.moPass+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.ogrnMo+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.pathtosave+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.signXMLFileName+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.cryptXMLFileName+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.pathandnameSSL+"\"><br>");
        out.println("<input type=\"text\" size=\"40\" value=\""+GlobalVariables.passwordSSL+"\"><br>");

        out.println("<input type=\"button\" value=\"Применить\" onclick=\"func()\"/>");

        out.println("<input type=\"button\" value=\"123\" onclick=\"func1()\"/>");
out.println("<script type=\"text/javascript\">");
        out.println("function func(){ alert(123);}");
        out.println("</script>");

        out.println("</body>");
        out.println("</html>");

    }

}
