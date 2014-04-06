package com.java.task11.controller.dao.factory;

import java.sql.Date;
import java.util.List;

import com.java.task11.model.Hour;

public interface HourDAO {
	public List<Hour> selectAll() throws DAOException;

	public int update(Hour obj) throws DAOException;

	public int insert(Hour obj) throws DAOException;

	public int delete(Hour obj) throws DAOException;

	public List<Hour> getByUserId(Integer userId) throws DAOException;
	
	public List<Hour> getByDate(Date hours) throws DAOException;

}
