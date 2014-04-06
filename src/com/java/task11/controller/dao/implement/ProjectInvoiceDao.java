package com.java.task11.controller.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.task11.model.ProjectInvoice;

public class ProjectInvoiceDao {

	public static final String SELECTINVOICE = "SELECT DISTINCT tasks.title, tasks.state, tasks.real_time, tasks.estimate_time, users.first_name, users.last_name, users.position, "
			+ "users.salary_rate FROM tasks  INNER JOIN users  ON (tasks.employee_id = users.id)"
			+ " WHERE tasks.project_id = ?";
	public static final String SELECTINVOICEFINISHED = "SELECT DISTINCT tasks.title, tasks.state, tasks.real_time, tasks.estimate_time, users.first_name, users.last_name, users.position, "
			+ "users.salary_rate FROM tasks  INNER JOIN users  ON (tasks.employee_id = users.id)"
			+ " WHERE tasks.project_id = ? and tasks.state = 'FINISHED'";
	public static final String SELECTINVOICEINPROGRESS = "SELECT DISTINCT tasks.title, tasks.state, tasks.real_time, tasks.estimate_time, users.first_name, users.last_name, users.position, "
			+ "users.salary_rate FROM tasks  INNER JOIN users  ON (tasks.employee_id = users.id)"
			+ " WHERE tasks.project_id = ? and tasks.state = 'IN Progress'";
	protected Connection conn = null;

	public ProjectInvoiceDao() {
		this.conn = getConn();
	}

	public List<ProjectInvoice> getByPojectId(int projectId, String statement) {
		List<ProjectInvoice> invoices = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = conn.prepareStatement(statement);
			preparedStatement.setInt(1, projectId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ProjectInvoice invoice = new ProjectInvoice();
				invoice.setTaskName(resultSet.getString("title"));
				invoice.setTaskState(resultSet.getString("state"));
				invoice.setFirstName(resultSet.getString("first_name"));
				invoice.setLastName(resultSet.getString("last_name"));
				invoice.setWorkedTime(Integer.parseInt(resultSet
						.getString("real_time")));
				invoice.setPlanedTime(Integer.parseInt(resultSet
						.getString("estimate_time")));
				invoice.setPosition(resultSet.getString("position"));
				invoice.setSalaryRate(Double.parseDouble(resultSet
						.getString("salary_rate")));
				invoice.setCosPerEmployee();
				invoice.setPlanedCostPerEmployee();
				invoices.add(invoice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(preparedStatement, resultSet);
		}

		return invoices;
	}
	
	
	

	protected Connection getConn() {
		return (conn == null) ? DBUtil.getConnection() : conn;
	}
}
