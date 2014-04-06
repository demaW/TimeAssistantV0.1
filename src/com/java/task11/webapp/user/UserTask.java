package com.java.task11.webapp.user;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.TaskService;
import com.java.task11.model.Task;
import com.java.task11.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

@WebServlet("/user/tasks")
public class UserTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UserTask.class);

	public UserTask() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			User loggedInUser = (User) session.getAttribute("user");
			Integer userId = loggedInUser.getId();

			List<Task> tasks = new TaskService().getByEmployeeId(userId);

			String filterStatusValue = request.getParameter("status");
			if (filterStatusValue != null) {
				tasks = filterResults(tasks, filterStatusValue);
			}

			request.setAttribute("tasks", tasks);
			request.getRequestDispatcher("/pages/user/userTasks.jsp").forward(
					request, response);
		} catch (DAOException e) {
			log.error(e);
		}
	}

	protected List<Task> filterResults(List<Task> tasks, String status) {

		for (ListIterator<Task> iterator = tasks.listIterator(); iterator
				.hasNext();) {
			if (!iterator.next().getState().equalsIgnoreCase(status)) {
				iterator.remove();
			}
		}

		return tasks;
	}

}
