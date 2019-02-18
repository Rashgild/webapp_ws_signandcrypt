package ru.my.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ForwardServlet")
public class ForwardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String forwardLink = request.getParameter("Link").trim();
        forward(request, response, forwardLink);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    public static void forward(HttpServletRequest request, HttpServletResponse response, String forwardString) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        try {
            request.setCharacterEncoding("UTF-8");
            request.getRequestDispatcher(forwardString).forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}