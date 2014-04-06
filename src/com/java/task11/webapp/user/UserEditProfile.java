package com.java.task11.webapp.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.UserService;
import com.java.task11.model.User;

@WebServlet("/user/userEditProfile")
public class UserEditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UserEditProfile.class);

	public UserEditProfile() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/pages/user/userEditProfile.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			UserService userService = new UserService();
			User user = (User) session.getAttribute("user");

			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			if (!password.isEmpty()) {
				user.setEncryptedPassword(password);
			}

			userService.update(user);

			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/user/userEditProfile");

		} catch (DAOException e) {
			log.error(e);
		}
	}

}
