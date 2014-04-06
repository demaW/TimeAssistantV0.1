package com.java.task11.webapp.manager;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.ProjectService;
import com.java.task11.model.Project;
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
import java.util.List;

/**
 * @author nlelyak
 * @version 1.00 2014-03-21
 */
@WebServlet("/manager/projectstable")
@MultipartConfig
public class ProjectsTableServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(ProjectsTableServlet.class);
    private ProjectService projectService;
    private List<Project> projects;

    public static final String ATTRIBUTE_TO_MODEL = "projects";
    public static final String PAGE_OK = "/pages/manager/projectsTable.jsp";

    @Override
    public void init() throws ServletException {
        projectService = new ProjectService();
        updateTable();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateTable();
            request.setAttribute(ATTRIBUTE_TO_MODEL, projects);
            request.getRequestDispatcher(PAGE_OK).forward(request, response);
            return;
        } catch (Exception e) {
            log.error(e);
        }
        response.sendRedirect("/manager/projectstable");
    }

    private void updateTable() {
        try {
            projects = projectService.getListOfObjects();
        } catch (DAOException e) {
            log.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(ParameterUtils.UTF_8);

        if (request.getParameter(ParameterUtils.PARAM_DELETE) != null) {
            deleteProject(request);
        } else if (request.getParameter(ParameterUtils.PARAM_UPDATE) != null) {
            updateProject(request);
        }
        doGet(request, response);
    }

    private void deleteProject(HttpServletRequest request) {
        try {
            String[] projects = request.getParameterValues(ParameterUtils.PARAM_PROJECTS_CHECKED);
            for (String projectId : projects) {
                projectService.delete(Integer.parseInt(projectId), this);
            }
        } catch (DAOException e) {
            log.error(e);
        }
    }

    private void updateProject(HttpServletRequest request) {
        try {
            int id = Integer.parseInt(request.getParameter(ParameterUtils.PARAM_UPDATE));
            Project project = projectService.getByID(id);

            String name = (!ValidationUtils.isNullOrEmpty(request.getParameter("project_name-" + id)))
                    ? request.getParameter("project_name-" + id) : project.getProjectName();
            String description = (!ValidationUtils.isNullOrEmpty(request.getParameter("project_description-" + id)))
                    ? request.getParameter("project_description-" + id) : project.getDescription();
            String notes = (!ValidationUtils.isNullOrEmpty(request.getParameter("project_notes-" + id)))
                    ? request.getParameter("project_name-" + id) : project.getNotes();

            project.setProjectName(name);
            project.setDescription(description);
            project.setNotes(notes);

            projectService.update(project);
        } catch (DAOException e) {
            log.error(e);
        }
    }
}
