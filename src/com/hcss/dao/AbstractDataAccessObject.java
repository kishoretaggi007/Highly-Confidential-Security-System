package com.hcss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.hcss.utill.LoggerManager;

public class AbstractDataAccessObject {
	private static Connection mCon;
	private static Properties mProps;

	/**
	 * @return the props
	 */
	public static Properties getProperties() {
		return mProps;
	}

	/**
	 * @param props
	 *            application properties object
	 */
	public void setProperties(Properties aProps) {
		mProps = aProps;
	}

	public static Connection getConnection() {
		try {
			Properties aProps = getProperties();
			Class.forName(aProps.getProperty("driver"));
			mCon = DriverManager.getConnection(aProps.getProperty("url"),
					aProps.getProperty("duser"), aProps.getProperty("dpass"));
			System.out.println("Connection established");
		} catch (ClassNotFoundException cnfe) {
			LoggerManager.writeLogWarning(cnfe);
			System.out.println(cnfe);
		} catch (SQLException se) {
			LoggerManager.writeLogWarning(se);
		}
		return mCon;
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LoggerManager.writeLogSevere(e);
				e.printStackTrace();
			}
		}
	}

	public int getSequenceID(String tableName, String pkid) {
		int id = 0;
		try {
			mCon = getConnection();
			Statement st = mCon.createStatement();
			ResultSet rs = st.executeQuery("select max(" + pkid + ") from "
					+ tableName);
			if (rs.next())
				id = rs.getInt(1);
			id++;
		} catch (SQLException se) {
			LoggerManager.writeLogWarning(se);
		} catch (Exception e) {
			LoggerManager.writeLogWarning(e);
		} finally {
			try {
				mCon.close();
			} catch (SQLException se) {
				LoggerManager.writeLogWarning(se);
			} catch (Exception e) {
				LoggerManager.writeLogWarning(e);
			}
		}
		return id;
	}
}
