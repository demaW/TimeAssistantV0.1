package com.java.task11.controller.dao.factory;

import com.java.task11.model.Task;

import java.util.List;

public interface TaskDAO {
	public Task getByPrimaryKey(int taskId) throws DAOException;

	public List<Task> selectAll() throws DAOException;

	public List<Task> select(String whereStatement) throws DAOException;

	public int update(Task obj) throws DAOException;

	public int insert(Task obj) throws DAOException;

	public int delete(Task obj) throws DAOException;

	public List<Task> getByEmployeeId(Integer employeeId) throws DAOException;

	public List<Task> getByProjectId(Integer projectId) throws DAOException;

	public List<Task> searchByWord(String searchWord) throws DAOException;

	public List<Task> searchByWordAndID(String searchWord, Integer employeeId)
			throws DAOException;
}
