package com.java.task11.controller.service;

import java.sql.Date;
import java.util.List;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.DAOFactory;
import com.java.task11.model.Hour;

public class HourService implements IBaseService<Hour> {

	@Override
	public void save(Hour element) throws DAOException {
		DAOFactory.getInstance().getHourDAO().insert(element);
	}

	@Override
	public void update(Hour element) throws DAOException {
		DAOFactory.getInstance().getHourDAO().update(element);
	}

	@Override
	public void delete(Hour element) throws DAOException {
		DAOFactory.getInstance().getHourDAO().delete(element);
	}

	@Override
	public List<Hour> getListOfObjects() throws DAOException {
		return DAOFactory.getInstance().getHourDAO().selectAll();
	}

	public List<Hour> getByUserId(Integer userId) throws DAOException {
		return DAOFactory.getInstance().getHourDAO().getByUserId(userId);
	}

	@Override
	public Hour getByID(Integer id) throws DAOException {
		return null;
	}
	
	public List<Hour> getByDate(Date hours) throws DAOException {
		return DAOFactory.getInstance().getHourDAO().getByDate(hours);
	}
}
