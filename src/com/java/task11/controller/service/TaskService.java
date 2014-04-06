package com.java.task11.controller.service;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.DAOFactory;
import com.java.task11.model.Project;
import com.java.task11.model.Task;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;

import java.util.List;
import java.util.ListIterator;

public class TaskService implements IBaseService<Task> {
    private static Logger log = Logger.getLogger(TaskService.class);

	@Override
	public Task getByID(Integer id) throws DAOException {
		return DAOFactory.getInstance().getTaskDAO().getByPrimaryKey(id);
	}

	@Override
	public void save(Task element) throws DAOException {
		DAOFactory.getInstance().getTaskDAO().insert(element);
	}

	@Override
	public void update(Task element) throws DAOException {
		DAOFactory.getInstance().getTaskDAO().update(element);
	}

	@Override
	public void delete(Task element) throws DAOException {
		DAOFactory.getInstance().getTaskDAO().delete(element);
	}

	@Override
	public List<Task> getListOfObjects() throws DAOException {
		return DAOFactory.getInstance().getTaskDAO().selectAll();
	}

	public List<Task> getByEmployeeId(Integer id) throws DAOException {
		List<Task> tasks = DAOFactory.getInstance().getTaskDAO().getByEmployeeId(id);
		
		for (Task task : tasks) {
			Integer projectId = task.getProjectId();
			Project project = DAOFactory.getInstance().getProjectDAO().getByPrimaryKey(projectId);
			task.setProject(project);
		}
		
		return tasks;
	}
	
	public List<Task> getByProjectId(Integer projectId) throws DAOException {
		return DAOFactory.getInstance().getTaskDAO().getByProjectId(projectId);
	}
	
	public List<Task> getByEmployeeIdAndStatus(Integer id, String status) throws DAOException {
		List<Task> resultList = getByEmployeeId(id);
		
		for (ListIterator<Task> iterator = resultList.listIterator(); iterator
				.hasNext();) {
			if (!iterator.next().getState().equalsIgnoreCase(status)) {
				iterator.remove();
			}
		}
		
		return resultList;
	}
	
	public List<Task> searchByWord(String searchWord) throws DAOException{
		return DAOFactory.getInstance().getTaskDAO().searchByWord(searchWord);
	}
	
	public List<Task> searchByWordAndID(String searchWord, Integer employeeId)
			throws DAOException {
		return DAOFactory.getInstance().getTaskDAO().searchByWordAndID(searchWord, employeeId);
	}

    public void delete(int taskId, HttpServlet servlet) {
        try {
            Task task = getByID(taskId);
            delete(task, servlet);
        } catch (DAOException e) {
            log.error(e);
        }
    }

    public void delete(Task task, HttpServlet servlet) {
        try {
            delete(task);
        } catch (DAOException e) {
            log.error(e);
        }
    }
}
