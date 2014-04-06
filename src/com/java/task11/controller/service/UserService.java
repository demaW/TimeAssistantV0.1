package com.java.task11.controller.service;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.DAOFactory;
import com.java.task11.model.Team;
import com.java.task11.model.User;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IBaseService<User> {

	@Override
	public User getByID(Integer id) throws DAOException {
		return DAOFactory.getInstance().getUserDAO().getByPrimaryKey(id);
	}

	@Override
	public void save(User element) throws DAOException {
		DAOFactory.getInstance().getUserDAO().insert(element);
	}

	@Override
	public void update(User element) throws DAOException {
		DAOFactory.getInstance().getUserDAO().update(element);
	}

	@Override
	public void delete(User element) throws DAOException {
		DAOFactory.getInstance().getUserDAO().delete(element);
	}

	@Override
	public List<User> getListOfObjects() throws DAOException {
		return DAOFactory.getInstance().getUserDAO().selectAll();
	}

	public User getByEmail(String email) throws DAOException {
		if (DAOFactory.getInstance().getUserDAO().getByEmail(email).isEmpty()) {
			return null;
		} else {
			return DAOFactory.getInstance().getUserDAO().getByEmail(email).get(0);
		}
	}

	public List<User> getByPosition(String position) throws DAOException {
		return DAOFactory.getInstance().getUserDAO().getByPosition(position);
	}

	public List<User> getUsersByProjectId(Integer project_id) throws DAOException {
		List<Team> team = DAOFactory.getInstance().getTeamDAO().getByProjectId(project_id);
		List<User> usersInProject = new ArrayList<>();

		for (Team singleTeam : team) {
			Integer userId = singleTeam.getEmployeeId();
			User user = DAOFactory.getInstance().getUserDAO().getByPrimaryKey(userId);
			usersInProject.add(user);
		}

		return usersInProject;
	}

    public void delete(int userId, HttpServlet servlet) throws DAOException {
        User user = getByID(userId);
        delete(user, servlet);
    }

    public void delete(User user, HttpServlet servlet) throws DAOException {
        delete(user);
    }
}
