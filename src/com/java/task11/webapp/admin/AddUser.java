package com.java.task11.webapp.admin;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.UserService;
import com.java.task11.model.User;
import com.java.task11.utils.ValidationErrors;
import com.java.task11.utils.ValidationUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

<<<<<<< 03de9564aa2a01ee2979b0c85fa2546ee15f6065
/**
 * Servlet implementation class AddUser
 */
@WebServlet("/admin/adduser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(AddUser.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUser() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/pages/admin/adduser.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String position = request.getParameter("position");
		String role = request.getParameter("role");
		Double salary = Double.parseDouble(request.getParameter("salaryRate"));
		int roleId = 1;
		if (role.equals("user")) {
			roleId = 1;
		} else if (role.equals("manager")) {
			roleId = 2;
		} else if (role.equals("admin")) {
			roleId = 3;
		}
		List<String> addingErrors = validateInputs(firstName, lastName, email,
				password, salary);

		if (addingErrors.size() > 0) {
			request.setAttribute("registrationErrors", addingErrors);
			request.getRequestDispatcher("/pages/admin/adduser.jsp").forward(
					request, response);
		} else {
			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setEncryptedPassword(password);
			user.setPosition(position);
			user.setRoleId(roleId);
			user.setSalaryRate(salary);

			UserService employeeService = new UserService();
			try {
				employeeService.save(user);
			} catch (DAOException e) {
				log.error(e);
				e.printStackTrace();
			}
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/admin/users");
		}
	}

	private List<String> validateInputs(String firstName, String lastName,
			String email, String password, Double salary) {
		List<String> registrationErrors = new ArrayList<>();
		String salaryS = String.valueOf(salary);
		if (ValidationUtils.isNullOrEmpty(firstName)) {
			registrationErrors.add(ValidationErrors.FIRST_NAME);
		}
		if (ValidationUtils.isNullOrEmpty(lastName)) {
			registrationErrors.add(ValidationErrors.LAST_NAME);
		}
		if (!ValidationUtils.validEmail(email)) {
			registrationErrors.add(ValidationErrors.EMAIL);
		}

		if (ValidationUtils.isNullOrEmpty(password)) {
			registrationErrors.add(ValidationErrors.PASSWORD);
		}
		if (ValidationUtils.isNullOrEmpty(salaryS)) {
			registrationErrors.add(ValidationErrors.NOT_EMPTY);
		}
		if (ValidationUtils.isNumber(salaryS)) {
			registrationErrors.add(ValidationErrors.SALARY);
		}
		if (!registrationErrors.isEmpty()) {
			log.error("add user validation fails");
		}
		return registrationErrors;
	}
=======
@WebServlet("/admin/adduser")
public class AddUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(AddUser.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/admin/adduser.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String position = request.getParameter("position");
        String role = request.getParameter("role");
        Double salary = Double.parseDouble(request.getParameter("salaryRate"));
        int roleId = 1;
        if (role.equals("user")) {
            roleId = 1;
        } else if (role.equals("manager")) {
            roleId = 2;
        } else if (role.equals("admin")) {
            roleId = 3;
        }
        List<String> addingErrors = validateInputs(firstName, lastName, email, password, salary);

        if (addingErrors.size() > 0) {
            request.setAttribute("registrationErrors", addingErrors);
            request.getRequestDispatcher("/pages/admin/adduser.jsp").forward(request, response);
        } else {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setEncryptedPassword(password);
            user.setPosition(position);
            user.setRoleId(roleId);
            user.setSalaryRate(salary);

            UserService employeeService = new UserService();
            try {
                employeeService.save(user);
            } catch (DAOException e) {
                log.error(e);
                e.printStackTrace();
            }
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/admin/users");
        }
    }

    private List<String> validateInputs(String firstName, String lastName, String email, String password, Double salary) {
        List<String> registrationErrors = new ArrayList<>();
        String salaryS = String.valueOf(salary);
        if (ValidationUtils.isNullOrEmpty(firstName)) {
            registrationErrors.add(ValidationErrors.FIRST_NAME);
        }
        if (ValidationUtils.isNullOrEmpty(lastName)) {
            registrationErrors.add(ValidationErrors.LAST_NAME);
        }
        if (!ValidationUtils.validEmail(email)) {
            registrationErrors.add(ValidationErrors.EMAIL);
        }

        if (ValidationUtils.isNullOrEmpty(password)) {
            registrationErrors.add(ValidationErrors.PASSWORD);
        }
        if (ValidationUtils.isNullOrEmpty(salaryS)) {
            registrationErrors.add(ValidationErrors.NOT_EMPTY);
        }
        if (ValidationUtils.isNumber(salaryS)) {
            registrationErrors.add(ValidationErrors.SALARY);
        }
        log.error("add user validation fails");
        return registrationErrors;
    }
>>>>>>> 49dc31138aa78094f07b82ca218bf38f24271542

}
