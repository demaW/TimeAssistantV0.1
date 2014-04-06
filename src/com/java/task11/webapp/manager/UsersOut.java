package com.java.task11.webapp.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.UserService;
import com.java.task11.model.User;

/**
 * Servlet implementation class UsersOut
 */
@WebServlet("/manager/users")
public class UsersOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> usersOnly = new ArrayList<>();
		try {
			List<User> usersPre = new UserService().getListOfObjects();
			for (User user : usersPre) {
				if(user.getRoleId() == 1){
					usersOnly.add(user);
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		request.setAttribute("usersonly", usersOnly);
		request.getRequestDispatcher("/pages/manager/users.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
