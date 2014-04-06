package com.java.task11.webapp.manager;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.ProjectService;
import com.java.task11.controller.service.TaskService;
import com.java.task11.controller.service.TeamService;
import com.java.task11.controller.service.UserService;
import com.java.task11.model.Project;
import com.java.task11.model.Task;
import com.java.task11.model.Team;
import com.java.task11.model.User;
import com.java.task11.utils.EmailUtil;
import com.java.task11.utils.ValidationErrors;
import com.java.task11.utils.ValidationUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/manager/addTask")
public class AddTaskServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(AddTaskServlet.class);
    private UserService userService;
    private List<User> usersList;
    private List<Project> projectList;
    private Integer projectId;
    private static final String DATE_FORMAT = "MM/dd/yy";

    public static final String PAGE_ADD_TASK = "/pages/manager/addTask.jsp";
    public static final String PAGE_SEE_TASKS = "/pages/manager/tasksTable.jsp";

    @Override
    public void init() throws ServletException {
        try {
            userService = new UserService();
            ProjectService projectService = new ProjectService();
            projectList = projectService.getListOfObjects();
        } catch (DAOException e) {
            log.error(e);
        }
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            if (request.getParameter("project_id") != null) {
                projectId = Integer.parseInt(request.getParameter("project_id"));
                usersList = userService.getUsersByProjectId(projectId);

                request.setAttribute("project_id", projectId);
            } else {
                usersList = userService.getListOfObjects();
            }

            if (!projectList.isEmpty()) {
                request.setAttribute("projectList", projectList);
            }

            request.setAttribute("users_in_project", usersList);
            request.getRequestDispatcher(PAGE_ADD_TASK).forward(request, response);
        } catch (NumberFormatException | DAOException e) {
			log.error(e);
            request.getRequestDispatcher(PAGE_SEE_TASKS).forward(request, response);
        }

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Task createTask = new Task();
        Team createTeam = new Team();
        try {
            int projectId = Integer.parseInt(request.getParameter("project_id"));
            int userId = Integer.parseInt(request.getParameter("user_id"));
            String name = request.getParameter("title");
            String description = request.getParameter("description");
            Integer estimateTime = Integer.parseInt(request.getParameter("estimate_time"));
            Date startDate = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("startDate"));
            Date endDate = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("endDate"));


            List<String> addTaskErrors = validateInputs(name, description, estimateTime, startDate, endDate);

            if (addTaskErrors.size() > 0) {
                request.setAttribute("addTaskErrors", addTaskErrors);
                request.getRequestDispatcher("/pages/manager/addTask.jsp").forward(request, response);
            } else {
                createTask.setTitle(name);
                createTask.setDescription(description);
                createTask.setEmployeeId(userId);
                createTask.setEstimateTime(estimateTime);
                createTask.setProjectId(projectId);
                createTask.setStartDate(startDate);
                createTask.setEndDate(endDate);
                createTask.setState("NEW");
                createTask.setRealTime(0);

                createTeam.setProjectId(projectId);
                createTeam.setEmployeeId(userId);

                new TaskService().save(createTask);
                new TeamService().save(createTeam);
            }
        } catch (Exception e) {
            log.error(e);
		}
        if (request.getParameter("mailNotification") != null
				&& request.getParameter("mailNotification").equals("yes")){
        	int userId = Integer.parseInt(request.getParameter("user_id"));
        	sendTaskMailNotification(userId, createTask);
       
        }
	
		String contextPath = request.getContextPath();
		//redirect to projects page
        if (projectId != null) {
            response.sendRedirect(contextPath+"/manager/taskstable?project_id=" + projectId);
            return;
        }
//        request.getRequestDispatcher("/pages/manager/projectsTable.jsp").forward(request, response);
        response.sendRedirect(contextPath+"/manager/projectstable");
    }

    private List<String> validateInputs(String name, String description, Integer estimateTime, Date startDate, Date endDate) {
        List<String> addTaskErrors = new ArrayList<>();
        if (ValidationUtils.isNullOrEmpty(name)) {
            addTaskErrors.add(ValidationErrors.TASK_NAME);
        }
        if (ValidationUtils.isNullOrEmpty(description)) {
            addTaskErrors.add(ValidationErrors.TASK_DESCRIPTION);
        }
        if (!ValidationUtils.isNumber(estimateTime + "")) {
            addTaskErrors.add(ValidationErrors.ESTIMATE_TIME);
        }

        return addTaskErrors;
    }

    private void sendTaskMailNotification(int userId, Task task){
		 {
			User user=null;
			try {
				user = new  UserService().getByID(userId);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			String subject = "New task assigned";
			String messageText = "Your have new task" + "\n"
					+"Project name"+ task.getProject()+ "\n"
					+ "Task name:" + task.getTitle()+"\n"
					+"Description:" +task.getDescription();
			EmailUtil emailUtil = new EmailUtil();
			emailUtil.sendMail(user.getEmail(),subject, messageText);
		}
	}

}
