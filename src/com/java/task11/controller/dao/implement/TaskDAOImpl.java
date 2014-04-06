package com.java.task11.controller.dao.implement;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.TaskDAO;
import com.java.task11.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskDAOImpl implements TaskDAO {
	protected static List<String> pkColumns = new ArrayList<String>();
	protected static List<String> stdColumns = new ArrayList<String>();
	protected static List<String> allColumns = new ArrayList<String>();
	protected static String tableName = "tasks";

	static {
		pkColumns.add("task_id");
		stdColumns.add("estimate_time");
		stdColumns.add("real_time");
		stdColumns.add("state");
		stdColumns.add("description");
		stdColumns.add("title");
		stdColumns.add("employee_id");
		stdColumns.add("project_id");
		stdColumns.add("start_date");
		stdColumns.add("end_date");
		stdColumns.add("finished");
		allColumns.addAll(pkColumns);
		allColumns.addAll(stdColumns);
	}

	protected Connection conn = null;

	public TaskDAOImpl() {
		this.conn = getConn();
	}

	public Task getByPrimaryKey(int taskId) throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			int pos = 1;
			ps = getConn().prepareStatement(
					DBUtil.select(tableName, allColumns, pkColumns));
			DBUtil.bind(ps, pos++, taskId);
			rs = ps.executeQuery();

			if (rs.next()) {
				return fromResultSet(rs);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBUtil.close(ps, rs);
		}

		return null;
	}

	public List<Task> selectAll() throws DAOException {
		List<Task> ret = new ArrayList<Task>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConn().prepareStatement(
					DBUtil.select(tableName, allColumns));
			rs = ps.executeQuery();

			while (rs.next())
				ret.add(fromResultSet(rs));
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBUtil.close(ps, rs);
		}

		return ret;
	}

	public List<Task> select(String whereStatement) throws DAOException {
		List<Task> ret = new ArrayList<Task>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		if (!whereStatement.trim().toUpperCase().startsWith("WHERE")) {
			whereStatement = " WHERE " + whereStatement;
		} else if (!whereStatement.startsWith(" ")) {
			whereStatement = " " + whereStatement;
		}

		try {
			ps = getConn().prepareStatement(
					DBUtil.select(tableName, allColumns) + whereStatement);

			rs = ps.executeQuery();

			while (rs.next())
				ret.add(fromResultSet(rs));
		} catch (SQLException e) {
			throw new DAOException("Error in select(), table = " + tableName, e);
		} finally {
			DBUtil.close(ps, rs);
		}

		return ret;
	}

	public int update(Task obj) throws DAOException {
		PreparedStatement ps = null;
		int pos = 1;

		try {
			ps = getConn().prepareStatement(
					DBUtil.update(tableName, stdColumns, pkColumns));
			pos = bindStdColumns(ps, obj, pos);
			bindPrimaryKey(ps, obj, pos);

			int rowCount = ps.executeUpdate();

			if (rowCount != 1) {
				throw new DAOException("Error updating " + obj.getClass()
						+ " in " + tableName + ", affected rows = " + rowCount);
			}

			return rowCount;
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBUtil.close(ps, null);
		}
	}

	public int insert(Task obj) throws DAOException {
		PreparedStatement ps = null;
		int pos = 1;

		try {
			ps = getConn().prepareStatement(
					DBUtil.insert(tableName, pkColumns, stdColumns));
			pos = bindPrimaryKey(ps, obj, pos);
			bindStdColumns(ps, obj, pos);

			int rowCount = ps.executeUpdate();

			if (rowCount != 1) {
				throw new DAOException("Error inserting " + obj.getClass()
						+ " in " + tableName + ", affected rows = " + rowCount);
			}

			return rowCount;
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBUtil.close(ps, null);
		}
	}

	public int delete(Task obj) throws DAOException {
		PreparedStatement ps = null;

		try {
			ps = getConn()
					.prepareStatement(DBUtil.delete(tableName, pkColumns));
			bindPrimaryKey(ps, obj, 1);

			int rowCount = ps.executeUpdate();

			if (rowCount != 1) {
				throw new DAOException("Error deleting " + obj.getClass()
						+ " in " + tableName + ", affected rows = " + rowCount);
			}

			return rowCount;
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBUtil.close(ps, null);
		}
	}

	public List<Task> getByEmployeeId(Integer employeeId) throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Task> ret = new ArrayList<Task>();

		try {
			if (null == employeeId) {
				ps = getConn().prepareStatement(
						DBUtil.selectNull(tableName, allColumns,
								Arrays.asList("employee_id")));
			} else {
				ps = getConn().prepareStatement(
						DBUtil.select(tableName, allColumns,
								Arrays.asList("employee_id")));
				DBUtil.bind(ps, 1, employeeId);
			}

			rs = ps.executeQuery();

			while (rs.next())
				ret.add(fromResultSet(rs));
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBUtil.close(ps, rs);
		}

		return ret;
	}

	public List<Task> getByProjectId(Integer projectId) throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Task> ret = new ArrayList<Task>();

		try {
			if (null == projectId) {
				ps = getConn().prepareStatement(
						DBUtil.selectNull(tableName, allColumns,
								Arrays.asList("project_id")));
			} else {
				ps = getConn().prepareStatement(
						DBUtil.select(tableName, allColumns,
								Arrays.asList("project_id")));
				DBUtil.bind(ps, 1, projectId);
			}

			rs = ps.executeQuery();

			while (rs.next())
				ret.add(fromResultSet(rs));
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBUtil.close(ps, rs);
		}

		return ret;
	}
	
	public List<Task> searchByWordAndID(String searchWord, Integer employeeId)
			throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Task> ret = new ArrayList<Task>();

		try {
			if (null == employeeId) {
				ps = getConn().prepareStatement(
						DBUtil.selectNull(tableName, allColumns,
								Arrays.asList("employee_id")));
			} else {
				StringBuffer buf = new StringBuffer();
				buf.append("((title like '%").append(searchWord)
						.append("%') OR (description LIKE '%")
						.append(searchWord).append("%')) AND ");
				ps = getConn().prepareStatement(
						DBUtil.selectSearch(tableName, allColumns,
								Arrays.asList("employee_id"), buf.toString()));
				DBUtil.setStatementParameter(ps, 1, employeeId);
			}

			rs = ps.executeQuery();

			while (rs.next())
				ret.add(fromResultSet(rs));
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBUtil.close(ps, rs);
		}

		return ret;
	}
	
	public List<Task> searchByWord(String searchWord)
			throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Task> ret = new ArrayList<Task>();

		try {
				StringBuffer buf = new StringBuffer();
				buf.append("((title like '%").append(searchWord)
						.append("%') OR (description LIKE '%")
						.append(searchWord).append("%'))");
				ps = getConn().prepareStatement(
						DBUtil.selectSearch(tableName, allColumns,
								new ArrayList<String>(), buf.toString()));

			rs = ps.executeQuery();

			while (rs.next())
				ret.add(fromResultSet(rs));
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBUtil.close(ps, rs);
		}

		return ret;
	}

	protected int bindPrimaryKey(PreparedStatement ps, Task obj, int pos)
			throws SQLException {
		DBUtil.bind(ps, pos++, obj.getTaskId());

		return pos;
	}

	protected int bindStdColumns(PreparedStatement ps, Task obj, int pos)
			throws SQLException {
		DBUtil.bind(ps, pos++, obj.getEstimateTime());
		DBUtil.bind(ps, pos++, obj.getRealTime());
		DBUtil.bind(ps, pos++, obj.getState());
		DBUtil.bind(ps, pos++, obj.getDescription());
		DBUtil.bind(ps, pos++, obj.getTitle());
		DBUtil.bind(ps, pos++, obj.getEmployeeId());
		DBUtil.bind(ps, pos++, obj.getProjectId());
		DBUtil.bind(ps, pos++, obj.getStartDate());
		DBUtil.bind(ps, pos++, obj.getEndDate());
		DBUtil.bind(ps, pos++, obj.getFinished());

		return pos;
	}

	protected Task fromResultSet(ResultSet rs) throws SQLException {
		Task obj = new Task();

		obj.setTaskId(DBUtil.getInt(rs, "task_id"));
		obj.setEstimateTime(DBUtil.getInt(rs, "estimate_time"));
		obj.setRealTime(DBUtil.getInt(rs, "real_time"));
		obj.setState(DBUtil.getString(rs, "state"));
		obj.setDescription(DBUtil.getString(rs, "description"));
		obj.setTitle(DBUtil.getString(rs, "title"));
		obj.setEmployeeId(DBUtil.getInteger(rs, "employee_id"));
		obj.setProjectId(DBUtil.getInteger(rs, "project_id"));
		obj.setStartDate(DBUtil.getDate(rs, "start_date"));
		obj.setEndDate(DBUtil.getDate(rs, "end_date"));
		obj.setFinished(DBUtil.getDate(rs, "finished"));

		return obj;
	}

	protected Connection getConn() {
		return (conn == null) ? DBUtil.getConnection() : conn;
	}
}
