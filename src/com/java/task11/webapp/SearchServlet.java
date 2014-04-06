package com.java.task11.webapp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.TaskService;
import com.java.task11.model.Task;
import com.java.task11.model.User;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Integer userId = user.getId();

		List<Task> foundedTasks;
		try {
			String searchValue = request.getParameter("search");

			if (user.getRoleId() == 1) {
				foundedTasks = new TaskService().searchByWordAndID(searchValue,
						userId);
				request.setAttribute("tasks", foundedTasks);
				request.getRequestDispatcher("/pages/user/userTasks.jsp")
						.forward(request, response);
			} else {
				foundedTasks = new TaskService().searchByWord(searchValue);
				request.setAttribute("tasksList", foundedTasks);
				request.getRequestDispatcher("/pages/manager/tasksTable.jsp")
						.forward(request, response);
			}

		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
