package com.java.task11.controller.dao.factory;

import com.java.task11.model.Project;

import java.util.List;

public interface ProjectDAO {

	public Project getByPrimaryKey(int id) throws DAOException;

	public List<Project> selectAll() throws DAOException;

	public List<Project> select(String whereStatement) throws DAOException;

	public int update(Project obj) throws DAOException;

	public int insert(Project obj) throws DAOException;

	public int delete(Project obj) throws DAOException;

	public List<Project> getByProjectName(String projectName) throws DAOException;
}
