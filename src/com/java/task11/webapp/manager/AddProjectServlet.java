package com.java.task11.webapp.manager;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.ProjectService;
import com.java.task11.model.Project;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manager/addproject")
public class AddProjectServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(AddProjectServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/pages/manager/addproject.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProjectService projectService = new ProjectService();
        Project project = new Project();

        String projectName = request.getParameter("name");
        String description = request.getParameter("description");
        String notes = request.getParameter("notes");

        project.setProjectName(projectName);
        project.setDescription(description);
        project.setNotes(notes);

		try {
			projectService.save(project);
		} catch (DAOException e) {
            log.error(e);
		}
		
		response.sendRedirect(request.getContextPath()+"/manager/projectstable");
	}
		
}


