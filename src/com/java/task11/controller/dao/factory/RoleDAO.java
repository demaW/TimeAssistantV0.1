package com.java.task11.controller.dao.factory;

import com.java.task11.model.UserRole;

import java.util.List;

public interface RoleDAO {
	public UserRole getByPrimaryKey(int roleId) throws DAOException;

	public List<UserRole> selectAll() throws DAOException;

	public List<UserRole> select(String whereStatement) throws DAOException;

	public int update(UserRole obj) throws DAOException;

	public int insert(UserRole obj) throws DAOException;

	public int delete(UserRole obj) throws DAOException;
}
