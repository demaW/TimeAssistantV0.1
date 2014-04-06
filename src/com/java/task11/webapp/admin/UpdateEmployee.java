package com.java.task11.webapp.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.RoleService;
import com.java.task11.controller.service.UserService;
import com.java.task11.model.User;
import com.java.task11.model.UserRole;
import com.java.task11.utils.EmailUtil;

/**
 * Servlet implementation class UpdateEmployee
 */
@WebServlet("/admin/updateemployee")
public class UpdateEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UpdateEmployee.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("notification") == null) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/admin/users");
			return;
		}
		int id = Integer.parseInt(request.getParameter("notification"));
		try {
			User userToEdit = new UserService().getByID(id);
			UserRole role = new RoleService().getByID(userToEdit.getRoleId());

			request.setAttribute("role", role);
			request.setAttribute("userToEdit", userToEdit);
		} catch (DAOException e) {
			log.error(e);
			e.printStackTrace();
		}

		request.getRequestDispatcher("/pages/admin/edituser.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("update") != null) {
			updateUser(request, response);
		} else if (request.getParameter("delete") != null) {
			deleteUser(request, response);
		}
	}

	private void deleteUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		UserService employeeService = new UserService();

		try {
			User user = employeeService.getByID(id);
			employeeService.delete(employeeService.getByID(id));
			if (request.getParameter("mailNotification") != null
					&& request.getParameter("mailNotification").equals("yes")) {
				String subject = "Your accuont was deleted";
				;
				String messageText = "Your account was deleted. for more information contact administrator or manager"
						+ "\n" + user.toString();
				sendUpdateMailNotification(user.getEmail(), subject,
						messageText);
			}
		} catch (DAOException e) {
			log.error(e);
			e.printStackTrace();
		}

		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath + "/admin/users");
	}

	private void updateUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String position = request.getParameter("position");
		Double salaryRate = Double.parseDouble(request
				.getParameter("salaryRate"));
		String role = request.getParameter("role");
		int roleId = 1;
		if (role.equals("user")) {
			roleId = 1;
		} else if (role.equals("manager")) {
			roleId = 2;
		} else if (role.equals("admin")) {
			roleId = 3;
		}

		UserService employeeService = new UserService();
		User user = null;
		try {
			user = employeeService.getByID(id);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}

		user.setId(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		if (password == null) {
			user.setEncryptedPassword(password);
		}
		user.setPosition(position);
		user.setRoleId(roleId);
		user.setSalaryRate(salaryRate);

		try {
			employeeService.update(user);
		} catch (DAOException e) {
			log.error(e);
			e.printStackTrace();
		}

		if (request.getParameter("mailNotification") != null
				&& request.getParameter("mailNotification").equals("yes")) {
			String messageText = "Your account was updated" + "\n"
					+ user.toString();
			String subject = "Udate account information";
			sendUpdateMailNotification(user.getEmail(), subject, messageText);
		}

		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath + "/admin/users");
	}

	private void sendUpdateMailNotification(String email, String subject,
			String messageText) {
		{

			EmailUtil emailUtil = new EmailUtil();
			emailUtil.sendMail(email, subject, messageText);
		}
	}

}
