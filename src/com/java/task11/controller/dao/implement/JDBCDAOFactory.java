package com.java.task11.controller.dao.implement;

import com.java.task11.controller.dao.factory.DAOFactory;
import com.java.task11.controller.dao.factory.HourDAO;
import com.java.task11.controller.dao.factory.ProjectDAO;
import com.java.task11.controller.dao.factory.RoleDAO;
import com.java.task11.controller.dao.factory.TaskDAO;
import com.java.task11.controller.dao.factory.TeamDAO;
import com.java.task11.controller.dao.factory.UserDAO;

public class JDBCDAOFactory extends DAOFactory {

	public UserDAO getUserDAO() {
		return new UserDAOImpl();
	}

	public ProjectDAO getProjectDAO() {
		return new ProjectDAOImpl();
	}

	public RoleDAO getRoleDAO() {
		return new RoleDAOImpl();
	}

	public TaskDAO getTaskDAO() {
		return new TaskDAOImpl();
	}

	public TeamDAO getTeamDAO() {
		return new TeamDAOImpl();
	}

	public HourDAO getHourDAO() {
		return new HourDAOImpl();
	}
}
