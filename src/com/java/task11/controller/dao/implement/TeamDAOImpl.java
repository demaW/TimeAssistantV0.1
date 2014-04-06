package com.java.task11.controller.dao.implement;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.TeamDAO;
import com.java.task11.model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeamDAOImpl implements TeamDAO {
	protected static List<String> pkColumns = new ArrayList<String>();
	protected static List<String> stdColumns = new ArrayList<String>();
	protected static List<String> allColumns = new ArrayList<String>();
	protected static String tableName = "team";

	static {
		pkColumns.add("id");
		stdColumns.add("employee_id");
		stdColumns.add("project_id");
		allColumns.addAll(pkColumns);
		allColumns.addAll(stdColumns);
	}

	protected Connection conn = null;

	public TeamDAOImpl() {
		this.conn = getConn();
	}

	public Team getByPrimaryKey(int id) throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			int pos = 1;
			ps = getConn().prepareStatement(
					DBUtil.select(tableName, allColumns, pkColumns));
			DBUtil.bind(ps, pos++, id);
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

	public List<Team> selectAll() throws DAOException {
		List<Team> ret = new ArrayList<Team>();
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

	public List<Team> select(String whereStatement) throws DAOException {
		List<Team> ret = new ArrayList<Team>();
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

	public int update(Team obj) throws DAOException {
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

	public int insert(Team obj) throws DAOException {
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

	public int delete(Team obj) throws DAOException {
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

	public List<Team> getByEmployeeId(Integer employeeId) throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Team> ret = new ArrayList<Team>();

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

	public List<Team> getByProjectId(Integer projectId) throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Team> ret = new ArrayList<Team>();

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

	protected int bindPrimaryKey(PreparedStatement ps, Team obj, int pos)
			throws SQLException {
		DBUtil.bind(ps, pos++, obj.getId());

		return pos;
	}

	protected int bindStdColumns(PreparedStatement ps, Team obj, int pos)
			throws SQLException {
		DBUtil.bind(ps, pos++, obj.getEmployeeId());
		DBUtil.bind(ps, pos++, obj.getProjectId());

		return pos;
	}

	protected Team fromResultSet(ResultSet rs) throws SQLException {
		Team obj = new Team();

		obj.setId(DBUtil.getInt(rs, "id"));
		obj.setEmployeeId(DBUtil.getInteger(rs, "employee_id"));
		obj.setProjectId(DBUtil.getInteger(rs, "project_id"));

		return obj;
	}

	protected Connection getConn() {
		return (conn == null) ? DBUtil.getConnection() : conn;
	}
}
