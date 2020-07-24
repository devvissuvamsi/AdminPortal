package com.simplilearn.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.sql.SQLException;

public class DbConnSingleton {

	// static member holds only one instance of the JDBCSingleton class.
	private static DbConnSingleton conn;

	// private constructor
	private DbConnSingleton() {
	}

	public Connection getStoredMySqlConnection() {
		return getMySqlConnection();
	}

	public static Properties loadPropertiesFile() throws Exception {
		Properties prop = new Properties();
		System.out.println(new File(".").getAbsolutePath());
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("database.properties");
		InputStream in = new FileInputStream("database.properties");
		prop.load(in);
		in.close();
		return prop;
	}

	// Now we are providing global point of access.
	public static DbConnSingleton getInstance() {
		if (conn == null) {
			conn = new DbConnSingleton();
		}
		return conn;
	}

	// to get the connection from methods like insert, view etc.
	private static Connection getMySqlConnection() {
		Connection con = null;
		try {
			/*
			 * Properties prop = loadPropertiesFile(); String driverClass =
			 * prop.getProperty("MYSQLJDBC.driver"); String url =
			 * prop.getProperty("MYSQLJDBC.url"); String username =
			 * prop.getProperty("MYSQLJDBC.username"); String password =
			 * prop.getProperty("MYSQLJDBC.password");
			 */

			String driverClass = "com.mysql.cj.jdbc.Driver", url = "jdbc:mysql://localhost:3306/adminportal",
					username = "root", password = "Password123";

			Class.forName(driverClass);
			con = DriverManager.getConnection(url, username, password);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			return con;
		} finally {
			try {
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
