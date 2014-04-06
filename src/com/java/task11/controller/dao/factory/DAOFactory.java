package com.java.task11.controller.dao.factory;


public abstract class DAOFactory {
	private static DAOFactory instance;

	public static DAOFactory getInstance() throws DAOException {
		try {
			if (null == instance) {
				instance = (DAOFactory) Class
						.forName("com.java.task11.controller.dao.implement.JDBCDAOFactory")
						.newInstance();
			}
		} catch (Exception e) {
			throw new DAOException("Could not create the DAOFactory instance", e);
		}

		return instance;
	}

	public abstract UserDAO getUserDAO();

	public abstract ProjectDAO getProjectDAO();

	public abstract RoleDAO getRoleDAO();

	public abstract TaskDAO getTaskDAO();

	public abstract TeamDAO getTeamDAO();
	
	public abstract HourDAO getHourDAO();

}
