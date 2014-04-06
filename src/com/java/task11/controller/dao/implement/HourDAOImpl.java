package com.java.task11.controller.dao.implement;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.HourDAO;
import com.java.task11.model.Hour;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HourDAOImpl implements HourDAO {
	protected static List<String> pkColumns = new ArrayList<String>();
	protected static List<String> stdColumns = new ArrayList<String>();
	protected static List<String> allColumns = new ArrayList<String>();
	protected static String tableName = "hours";

	static {
		pkColumns.add("hoursId");
		stdColumns.add("userId");
		stdColumns.add("date");
		stdColumns.add("hours");
		allColumns.addAll(pkColumns);
		allColumns.addAll(stdColumns);
	}

	protected Connection conn = null;

	public HourDAOImpl() {
		this.conn = getConn();
	}

	public List<Hour> selectAll() throws DAOException {
		List<Hour> ret = new ArrayList<Hour>();
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

	public int update(Hour obj) throws DAOException {
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

	public int insert(Hour obj) throws DAOException {
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

	public int delete(Hour obj) throws DAOException {
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

	public List<Hour> getByUserId(Integer userId) throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Hour> ret = new ArrayList<Hour>();

		try {
			if (null == userId) {
				ps = getConn().prepareStatement(
						DBUtil.selectNull(tableName, allColumns,
								Arrays.asList("userId")));
			} else {
				ps = getConn().prepareStatement(
						DBUtil.selectGroupBy(tableName, Arrays.asList(
								"hoursId", "sum(hours) as hours", "date",
								"userId"), Arrays.asList("userId"), "date",
								"date"));
				DBUtil.bind(ps, 1, userId);
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

	public List<Hour> getByDate(Date hours) throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Hour> ret = new ArrayList<Hour>();

		try {
			if (null == hours) {
				ps = getConn().prepareStatement(
						DBUtil.selectNull(tableName, allColumns,
								Arrays.asList(new String[] { "date" })));
			} else {
				ps = getConn().prepareStatement(
						DBUtil.select(tableName, allColumns,
								Arrays.asList(new String[] { "date" })));
				DBUtil.bind(ps, 1, hours);
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
	
	protected int bindPrimaryKey(PreparedStatement ps, Hour obj, int pos)
			throws SQLException {
		DBUtil.bind(ps, pos++, obj.getHoursId());

		return pos;
	}

	protected int bindStdColumns(PreparedStatement ps, Hour obj, int pos)
			throws SQLException {
		DBUtil.bind(ps, pos++, obj.getUserId());
		DBUtil.bind(ps, pos++, obj.getDate());
		DBUtil.bind(ps, pos++, obj.getHours());

		return pos;
	}

	protected Hour fromResultSet(ResultSet rs) throws SQLException {
		Hour obj = new Hour();

		obj.setHoursId(DBUtil.getInt(rs, "hoursId"));
		obj.setUserId(DBUtil.getInt(rs, "userId"));
		obj.setDate(DBUtil.getDate(rs, "date"));
		obj.setHours(DBUtil.getInt(rs, "hours"));

		return obj;
	}

	protected Connection getConn() {
		return (conn == null) ? DBUtil.getConnection() : conn;
	}

}
