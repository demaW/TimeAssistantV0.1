package com.java.task11.webapp.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.RoleService;
import com.java.task11.controller.service.UserService;
import com.java.task11.model.User;
import com.java.task11.model.UserRole;

/**
 * Servlet implementation class OutEmployees
 */
@WebServlet("/admin/users")
public class OutEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;
<<<<<<< 03de9564aa2a01ee2979b0c85fa2546ee15f6065
	private static Logger log = Logger.getLogger(OutEmployees.class);
=======
	private static Logger log = Logger.getLogger(OutEmployees.class);      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutEmployees() {
        super();
    }
>>>>>>> 49dc31138aa78094f07b82ca218bf38f24271542

	/**
	 * @see HttpServlet#HttpServlet()
	 */
<<<<<<< 03de9564aa2a01ee2979b0c85fa2546ee15f6065
	public OutEmployees() {
		super();
=======
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	List<User> users = null;
	List<UserRole> roles = null;
	try {
		users = new UserService().getListOfObjects();
		roles = new RoleService().getListOfObjects();
	} catch (DAOException e) {
		log.error(e);
		e.printStackTrace();
>>>>>>> 49dc31138aa78094f07b82ca218bf38f24271542
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<User> users = null;
		List<UserRole> roles = null;
		try {
			users = new UserService().getListOfObjects();
			roles = new RoleService().getListOfObjects();
		} catch (DAOException e) {
			log.error(e);
			e.printStackTrace();
		}
		request.setAttribute("roles", roles);
		request.setAttribute("users", users);
		request.getRequestDispatcher("/pages/admin/users.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
