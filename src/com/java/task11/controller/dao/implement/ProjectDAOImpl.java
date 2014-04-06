package com.java.task11.controller.dao.implement;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.ProjectDAO;
import com.java.task11.model.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectDAOImpl implements ProjectDAO {
	protected static List<String> pkColumns = new ArrayList<String>();
	protected static List<String> stdColumns = new ArrayList<String>();
	protected static List<String> allColumns = new ArrayList<String>();
	protected static String tableName = "projects";

	static {
		pkColumns.add("id");
		stdColumns.add("description");
		stdColumns.add("notes");
		stdColumns.add("project_name");
		allColumns.addAll(pkColumns);
		allColumns.addAll(stdColumns);
	}

	protected Connection conn = null;

	public ProjectDAOImpl() {
		this.conn = getConn();
	}

	public Project getByPrimaryKey(int id) throws DAOException {
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

	public List<Project> selectAll() throws DAOException {
		List<Project> ret = new ArrayList<Project>();
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

	public List<Project> select(String whereStatement)
			throws DAOException {
		List<Project> ret = new ArrayList<Project>();
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

	public int update(Project obj) throws DAOException {
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

	public int insert(Project obj) throws DAOException {
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

	public int delete(Project obj) throws DAOException {
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

	public List<Project> getByProjectName(String projectName) throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Project> ret = new ArrayList<Project>();

		try {
			if (null == projectName) {
				ps = getConn()
						.prepareStatement(
								DBUtil.selectNull(
										tableName,
										allColumns,
										Arrays.asList("project_name")));
			} else {
				ps = getConn()
						.prepareStatement(
								DBUtil.select(
										tableName,
										allColumns,
										Arrays.asList("project_name")));
				DBUtil.bind(ps, 1, projectName);
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

	protected int bindPrimaryKey(PreparedStatement ps, Project obj, int pos)
			throws SQLException {
		DBUtil.bind(ps, pos++, obj.getId());

		return pos;
	}

	protected int bindStdColumns(PreparedStatement ps, Project obj, int pos)
			throws SQLException {
		DBUtil.bind(ps, pos++, obj.getDescription());
		DBUtil.bind(ps, pos++, obj.getNotes());
		DBUtil.bind(ps, pos++, obj.getProjectName());

		return pos;
	}

	protected Project fromResultSet(ResultSet rs) throws SQLException {
		Project obj = new Project();

		obj.setId(DBUtil.getInt(rs, "id"));
		obj.setDescription(DBUtil.getString(rs, "description"));
		obj.setNotes(DBUtil.getString(rs, "notes"));
		obj.setProjectName(DBUtil.getString(rs, "project_name"));

		return obj;
	}

	protected Connection getConn() {
		return (conn == null) ? DBUtil.getConnection() : conn;
	}
}
