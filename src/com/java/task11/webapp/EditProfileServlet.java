package com.java.task11.webapp;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.UserService;
import com.java.task11.model.User;
import com.java.task11.utils.ValidationUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author nlelyak
 * @version 1.00 2014-03-14
 */
@WebServlet("/edit")
@MultipartConfig
public class EditProfileServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(EditProfileServlet.class);
    private UserService employeeService;
    private User user;

    @Override
    public void init() throws ServletException {
        employeeService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("update"));
        try {
			user = employeeService.getByID(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.setAttribute("user", user);
        request.getRequestDispatcher("/pages/editProfile.jsp").forward(request, response);
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
        int id = Integer.parseInt(request.getParameter("update"));
        User user;
		try {
			user = employeeService.getByID(id);
		

        String firstName = (!ValidationUtils.isNullOrEmpty(request.getParameter("first_name-" + id)))
                ? request.getParameter("first_name-" + id) : user.getFirstName();
        String lastName = (!ValidationUtils.isNullOrEmpty(request.getParameter("last_name-" + id)))
                ? request.getParameter("last_name-" + id) : user.getLastName();
        String email = (!ValidationUtils.isNullOrEmpty(request.getParameter("email-" + id))
                && ValidationUtils.validEmail(request.getParameter("email-" + id)))
                ? request.getParameter("email-" + id) : user.getEmail();
        if (!employeeService.getByEmail(email).equals(null)) {
            email = user.getEmail();
        }
        String position = (!ValidationUtils.isNullOrEmpty(request.getParameter("position-" + id)))
                ? request.getParameter("position-" + id) : user.getPosition();
		
        

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPosition(position);

        employeeService.update(user);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}


