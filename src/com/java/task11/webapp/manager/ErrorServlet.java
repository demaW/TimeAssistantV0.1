package com.java.task11.webapp.manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author nlelyak
 * @version 1.00 2014-03-30
 */
@WebServlet("/manager/error")
public class ErrorServlet extends HttpServlet {
    public static final String ERROR_PAGE_LOCATION = "/pages/errorPage.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(ERROR_PAGE_LOCATION).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
