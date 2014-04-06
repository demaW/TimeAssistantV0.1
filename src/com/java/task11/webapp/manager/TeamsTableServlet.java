package com.java.task11.webapp.manager;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.ProjectService;
import com.java.task11.controller.service.TeamService;
import com.java.task11.controller.service.UserService;
import com.java.task11.model.Project;
import com.java.task11.model.Team;
import com.java.task11.model.User;
import com.java.task11.utils.ParameterUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nlelyak
 * @version 1.00 2014-04-02
 */
@WebServlet("/manager/teamsTable")
public class TeamsTableServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(TeamsTableServlet.class);
    private ProjectService projectService;
    private UserService userService;
    private TeamService teamService;
    private List<Project> projectList;
    private List<User> userList;
    private List<Team> teamList;

    @Override
    public void init() throws ServletException {
        projectService = new ProjectService();
        userService = new UserService();
        teamService = new TeamService();
        projectList = new ArrayList<>();
        userList = new ArrayList<>();
        teamList = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter("project_id") == null) {
                updateTable();
                request.setAttribute(ParameterUtils.ATTRIBUTE_PROJECTS_LIST, projectList);
                request.getRequestDispatcher("/pages/manager/teamsTable.jsp").forward(request, response);
            } else {
                int id = Integer.parseInt(request.getParameter("project_id"));
                Project project = projectService.getByID(id);

                teamList = teamService.getByProjectId(id);
                for (Team team : teamList) {
                    int emplId = team.getEmployeeId();
                    userList.add(userService.getByID(emplId));
                }
                request.setAttribute("teamProject", project);
                request.setAttribute("userList", userList);

                request.getRequestDispatcher("/pages/manager/teamInfo.jsp").forward(request, response);
            }
            return;
        } catch (Exception e) {
            log.error(e);
        }
        response.sendRedirect(ParameterUtils.PAGE_MANAGER_ERROR);
    }

    private void updateTable() {
        try {
            projectList = projectService.getListOfObjects();
        } catch (DAOException e) {
            log.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("delete") != null) {
            deleteMembers(request);
        }
        doGet(request, response);
    }

    private void deleteMembers(HttpServletRequest request) {
        try {
            String[] members = request.getParameterValues("checkedMembers");
            for (String memberId : members) {
                teamService.delete(Integer.parseInt(memberId), this);
            }
        } catch (DAOException e) {
            log.error(e);
        }
    }
}
