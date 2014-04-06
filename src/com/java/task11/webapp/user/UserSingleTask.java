package com.java.task11.webapp.user;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.HourService;
import com.java.task11.controller.service.ProjectService;
import com.java.task11.controller.service.TaskService;
import com.java.task11.model.Hour;
import com.java.task11.model.Task;
import com.java.task11.model.User;

//import javax.websocket.Session;

@WebServlet("/user/task")
public class UserSingleTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UserSingleTask.class);

	public UserSingleTask() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			Integer taskId = Integer.parseInt(request.getParameter("task_id"));

			Task task = new TaskService().getByID(taskId);
			task.setProject(new ProjectService().getByID(task.getProjectId()));

			request.setAttribute("task", task);
			request.getRequestDispatcher("/pages/user/userSingleTask.jsp")
					.forward(request, response);

		} catch (NumberFormatException | DAOException e) {
			log.error(e);
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/user/tasks");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			TaskService taskService = new TaskService();
			HourService hourService = new HourService();

			Integer taskId = Integer.parseInt(request.getParameter("task_id"));
			String timeString = request.getParameter("realTime");
			Integer time = Integer.parseInt(timeString);
			String isFinished = request.getParameter("finished");

			Task task = taskService.getByID(taskId);

			if (isFinished == null) {
				task.setState("IN Progress");
			} else {
				task.setState("FINISHED");
			}

			java.sql.Date dateNow = new Date(Calendar.getInstance()
					.getTimeInMillis());
			task.setFinished(dateNow);

			if (time > task.getRealTime()) {
				Integer statsTime = time - task.getRealTime();

				Hour hour = new Hour();
				hour.setDate(dateNow);
				hour.setHours(statsTime);
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("user");
				Integer userId = user.getId();
				hour.setUserId(userId);

				hourService.save(hour);
			}

			task.setRealTime(time);
			taskService.update(task);

			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/user/tasks");

		} catch (NumberFormatException | DAOException e) {
			log.error(e);
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/user/tasks");
		}
	}

}
