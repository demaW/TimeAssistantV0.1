package com.java.task11.controller.dao.implement;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.UserDAO;
import com.java.task11.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAOImpl implements UserDAO {
	protected static List<String> pkColumns = new ArrayList<String>();
	protected static List<String> stdColumns = new ArrayList<String>();
	protected static List<String> allColumns = new ArrayList<String>();
	protected static String tableName = "users";

	static {
		pkColumns.add("id");
		stdColumns.add("email");
		stdColumns.add("first_name");
		stdColumns.add("last_name");
		stdColumns.add("password");
		stdColumns.add("position");
		stdColumns.add("role_id");
		stdColumns.add("salary_rate");
		allColumns.addAll(pkColumns);
		allColumns.addAll(stdColumns);
	}

	protected Connection conn = null;

	public UserDAOImpl() {
		this.conn = getConn();
	}

	public User getByPrimaryKey(int id) throws DAOException {
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

	public List<User> selectAll() throws DAOException {
		List<User> ret = new ArrayList<User>();
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

	public List<User> select(String whereStatement) throws DAOException {
		List<User> ret = new ArrayList<User>();
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

	public int update(User obj) throws DAOException {
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

	public int insert(User obj) throws DAOException {
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

	public int delete(User obj) throws DAOException {
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

	public List<User> getByEmail(String email) throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> ret = new ArrayList<User>();

		try {
			if (null == email) {
				ps = getConn().prepareStatement(
						DBUtil.selectNull(tableName, allColumns,
								Arrays.asList("email")));
			} else {
				ps = getConn().prepareStatement(
						DBUtil.select(tableName, allColumns,
								Arrays.asList("email")));
				DBUtil.bind(ps, 1, email);
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

	public List<User> getByPosition(String position) throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> ret = new ArrayList<User>();

		try {
			if (null == position) {
				ps = getConn().prepareStatement(
						DBUtil.selectNull(tableName, allColumns,
								Arrays.asList("position")));
			} else {
				ps = getConn().prepareStatement(
						DBUtil.select(tableName, allColumns,
								Arrays.asList("position")));
				DBUtil.bind(ps, 1, position);
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

	protected int bindPrimaryKey(PreparedStatement ps, User obj, int pos)
			throws SQLException {
		DBUtil.bind(ps, pos++, obj.getId());

		return pos;
	}

	protected int bindStdColumns(PreparedStatement ps, User obj, int pos)
			throws SQLException {
		DBUtil.bind(ps, pos++, obj.getEmail());
		DBUtil.bind(ps, pos++, obj.getFirstName());
		DBUtil.bind(ps, pos++, obj.getLastName());
		DBUtil.bind(ps, pos++, obj.getPassword());
		DBUtil.bind(ps, pos++, obj.getPosition());
		DBUtil.bind(ps, pos++, obj.getRoleId());
		DBUtil.bind(ps, pos++, obj.getSalaryRate());

		return pos;
	}

	protected User fromResultSet(ResultSet rs) throws SQLException {
		User obj = new User();

		obj.setId(DBUtil.getInt(rs, "id"));
		obj.setEmail(DBUtil.getString(rs, "email"));
		obj.setFirstName(DBUtil.getString(rs, "first_name"));
		obj.setLastName(DBUtil.getString(rs, "last_name"));
		obj.setPassword(DBUtil.getString(rs, "password"));
		obj.setPosition(DBUtil.getString(rs, "position"));
		obj.setRoleId(DBUtil.getInteger(rs, "role_id"));
		obj.setSalaryRate(DBUtil.getDouble(rs, "salary_rate"));

		return obj;
	}

	protected Connection getConn() {
		return (conn == null) ? DBUtil.getConnection() : conn;
	}
}
