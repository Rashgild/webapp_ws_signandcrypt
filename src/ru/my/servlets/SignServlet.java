package ru.my.servlets;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by rkurbanov on 23.04.2018.
 */

@WebServlet("/sign")
public class SignServlet  extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("test", "321123");
        this.getServletContext().getRequestDispatcher("/WEB-INF/test.jsp").forward(request, response);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

       /* response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        doPost(request,response);
        request.getRequestDispatcher("/WEB-INF/test.jsp").forward(request,response);*/
       doPost(request,response);
        processRequest(request,response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String id = "1233";

        request.setAttribute("data","{\"json\":\"jsonich\"}");
        response.getWriter().write(id.toString());
    }
}
