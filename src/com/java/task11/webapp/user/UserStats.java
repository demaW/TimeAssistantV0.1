package com.java.task11.webapp.user;

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
import com.java.task11.controller.service.HourService;
import com.java.task11.controller.service.TaskService;
import com.java.task11.model.Hour;
import com.java.task11.model.Task;
import com.java.task11.model.User;

@WebServlet("/user/stats")
public class UserStats extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UserStats.class);

	public UserStats() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			Integer userId = user.getId();

			List<Task> finishedTasks = new TaskService()
					.getByEmployeeIdAndStatus(userId, "FINISHED");

			Integer summaryRealTime = new Integer(0);
			Integer summaryEstimateTime = new Integer(0);
			for (Task task : finishedTasks) {
				summaryRealTime += task.getRealTime();
				summaryEstimateTime += task.getEstimateTime();
			}

			request.setAttribute("estimate_time_summ", summaryEstimateTime);
			request.setAttribute("real_time_summ", summaryRealTime);

			List<Hour> hours = new HourService().getByUserId(userId);

			request.setAttribute("hours", hours);

			request.getRequestDispatcher("/pages/user/userStats.jsp").forward(
					request, response);

		} catch (DAOException e) {
			log.error(e);
		}
	}

}
