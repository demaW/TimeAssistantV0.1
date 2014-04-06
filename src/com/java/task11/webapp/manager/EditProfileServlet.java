package com.java.task11.webapp.manager;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.UserService;
import com.java.task11.model.User;
import com.java.task11.utils.ValidationUtils;
import org.apache.log4j.Logger;

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
@WebServlet("/manager/editProfile")
public class EditProfileServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(EditProfileServlet.class);
    private UserService userService;
    private User user;

    public static final String ATTRIBUTE_TO_JSP = "user";
    public static final String PAGE_OK = "/pages/manager/editProfile.jsp";
    public static final String PAGE_ERROR_URL = "error";

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            user = (User) request.getSession().getAttribute("user");
            request.setAttribute(ATTRIBUTE_TO_JSP, user);
            request.getRequestDispatcher(PAGE_OK).forward(request, response);
            return;
        } catch (Exception e) {
            log.error(e);
        }
        response.sendRedirect(PAGE_ERROR_URL);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("update") != null) {
            updateUser(request);
            doGet(request, response);
        }
    }

    private void updateUser(HttpServletRequest request) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("update"));

            String firstName = (!ValidationUtils.isNullOrEmpty(request.getParameter("first_name-" + id)))
                    ? request.getParameter("first_name-" + id) : user.getFirstName();
            String lastName = (!ValidationUtils.isNullOrEmpty(request.getParameter("last_name-" + id)))
                    ? request.getParameter("last_name-" + id) : user.getLastName();
            String email = (!ValidationUtils.isNullOrEmpty(request.getParameter("email-" + id))
                    && ValidationUtils.validEmail(request.getParameter("email-" + id)))
                    ? request.getParameter("email-" + id) : user.getEmail();
            if (!(userService.getByEmail(email) == null)) {
                email = user.getEmail();
            }
            String position = (!ValidationUtils.isNullOrEmpty(request.getParameter("position-" + id)))
                    ? request.getParameter("position-" + id) : user.getPosition();
//            System.out.printf("name: %s last name: %s email: %s position: %s%n", firstName, lastName, email, position);

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPosition(position);

            userService.update(user);
        } catch (DAOException e) {
            log.error(e);
        }
    }
}
