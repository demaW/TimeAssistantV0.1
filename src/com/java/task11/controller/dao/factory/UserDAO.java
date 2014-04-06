package com.java.task11.controller.dao.factory;

import com.java.task11.model.User;

import java.util.List;

public interface UserDAO {
	public User getByPrimaryKey(int id) throws DAOException;

	public List<User> selectAll() throws DAOException;

	public List<User> select(String whereStatement) throws DAOException;

	public int update(User obj) throws DAOException;

	public int insert(User obj) throws DAOException;

	public int delete(User obj) throws DAOException;

	public List<User> getByEmail(String email) throws DAOException;

	public List<User> getByPosition(String position) throws DAOException;
}
