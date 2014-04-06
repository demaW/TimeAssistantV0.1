package com.java.task11.controller.dao.factory;

import com.java.task11.model.Team;

import java.util.List;

public interface TeamDAO {
	public Team getByPrimaryKey(int id) throws DAOException;

	public List<Team> selectAll() throws DAOException;

	public List<Team> select(String whereStatement) throws DAOException;

	public int update(Team obj) throws DAOException;

	public int insert(Team obj) throws DAOException;

	public int delete(Team obj) throws DAOException;

	public List<Team> getByEmployeeId(Integer employeeId) throws DAOException;

	public List<Team> getByProjectId(Integer projectId) throws DAOException;
}
