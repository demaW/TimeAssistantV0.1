package com.java.task11.webapp.manager;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.TaskService;
import com.java.task11.controller.service.TeamService;
import com.java.task11.controller.service.UserService;
import com.java.task11.model.Task;
import com.java.task11.model.User;
import com.java.task11.utils.ParameterUtils;
import com.java.task11.utils.ValidationUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author nlelyak
 * @version 1.00 2014-03-21
 */
@WebServlet("/manager/taskstable")
@MultipartConfig
public class TasksTableServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(TasksTableServlet.class);
    private TaskService taskService;
    private TeamService teamService;
    private UserService userService;
    private List<Task> tasks;
    private List<User> users;
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private String userId;

    public static final String ATTRIBUTE_TASKS_TO_MODEL = "tasksList";
    public static final String ATTRIBUTE_USERS_TO_MODEL = "usersList";
    public static final String ATTRIBUTE_USERS_ASSIGNED_TO_PROJECT = "assignedUsers";
    public static final String ATTRIBUTE_PROJECT_ID = "project_id";
    public static final String PAGE_OK = "/pages/manager/tasksTable.jsp";

    @Override
    public void init() throws ServletException {
        taskService = new TaskService();
        userService = new UserService();
        teamService = new TeamService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter(ATTRIBUTE_PROJECT_ID) != null) {
                userId = request.getParameter(ATTRIBUTE_PROJECT_ID);
                List<User> userList = userService.getUsersByProjectId(Integer.valueOf(userId));

                request.setAttribute(ATTRIBUTE_USERS_ASSIGNED_TO_PROJECT, userList);
                request.setAttribute(ATTRIBUTE_PROJECT_ID, userId);

                updateTable(request);
                if (!tasks.isEmpty()) {
                    request.setAttribute(ATTRIBUTE_TASKS_TO_MODEL, tasks);
                    request.setAttribute(ATTRIBUTE_USERS_TO_MODEL, users);
                    request.getRequestDispatcher(PAGE_OK).forward(request, response);
                    return;
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/manager/projectstable");
            }
        } catch (Exception e) {
            log.error(e);
        }
        response.sendRedirect("/manager/projectstable");
    }

    private void updateTable(HttpServletRequest request) {
        try {
            int id = Integer.parseInt(request.getParameter(ATTRIBUTE_PROJECT_ID));
            tasks = taskService.getByProjectId(id);
            users = userService.getListOfObjects();
        } catch (DAOException e) {
            log.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(ParameterUtils.UTF_8);
        if (request.getParameter(ParameterUtils.PARAM_DELETE) != null) {
            deleteTask(request);
        } else if (request.getParameter(ParameterUtils.PARAM_UPDATE) != null) {
            updateTask(request);
        }
        if (!userId.isEmpty()) {
            response.sendRedirect(request.getContextPath()+new StringBuffer().append("/manager/taskstable?")
                    .append(ATTRIBUTE_PROJECT_ID).append("=").append(userId).toString());
        }
    }

    private void updateTask(HttpServletRequest request) {
        try {
            int id = Integer.parseInt(request.getParameter(ParameterUtils.PARAM_UPDATE));
            Task task = taskService.getByID(id);

            String name = (!ValidationUtils.isNullOrEmpty(request.getParameter("name-" + id)))
                    ? request.getParameter("task_name-" + id) : task.getTitle();
            String description = (!ValidationUtils.isNullOrEmpty(request.getParameter("task_description-" + id)))
                    ? request.getParameter("task_description-" + id) : task.getDescription();
            int estimateTime = (ValidationUtils.isNumber(request.getParameter("estimate_time-" + id)))
                    ? Integer.parseInt(request.getParameter("estimate_time-" + id))
                    : task.getEstimateTime();
            Date startDate = (request.getParameter("start_date-" + id) != null)
                    ? new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("start_date-" + id))
                    : task.getStartDate();
            Date endDate = (request.getParameter("end_date-" + id) != null)
                    ? new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("end_date-" + id))
                    : task.getStartDate();

            task.setTitle(name);
            task.setDescription(description);
            task.setStartDate(startDate);
            task.setEndDate(endDate);
            task.setEstimateTime(estimateTime);

            taskService.update(task);
        } catch (DAOException | ParseException e) {
        	e.printStackTrace();
            log.error(e);
        }
    }

    private void deleteTask(HttpServletRequest request) {
        try {
            String[] tasks = request.getParameterValues(ParameterUtils.PARAM_TASKS_CHECKED);
            for (String taskId : tasks) {
                taskService.delete(Integer.parseInt(taskId), this);
                teamService.delete(Integer.parseInt(taskId), this);
            }
        } catch (DAOException e) {
            log.error(e);
        }
    }

}
