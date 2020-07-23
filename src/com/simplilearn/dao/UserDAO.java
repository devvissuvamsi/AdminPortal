package com.simplilearn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.simplilearn.model.User;

public class UserDAO {

	private static final String 
	insertUserSql = "insert into users(name,email,country) values(?,?,?)"
	,updateUserSql = "update users set name=?,email=?,country=? where id = ?"
	,deleteUserSql = "delete from users where id = ?"
	,selectUserSql = "select * from	users where id = ?"
	,selectAllUserSql = "select * from users"
	,selectUserByNameAndPass = "select * from	user where username = ? and password = ?";
	
	private DbConnSingleton dbConnSingleton = DbConnSingleton.getInstance();
	private Connection conn;

	public UserDAO() {
		super();
	}
	
	public User findUser(User user) throws SQLException {
		this.conn = dbConnSingleton.getStoredMySqlConnection();
		User userlocal = new User();
		
		System.out.println("In findUser UserDAO file");
		
		PreparedStatement statement = this.conn.prepareStatement(selectUserByNameAndPass);
		statement.setString(1,user.getUserName());
		statement.setString(2,user.getUserPass());
		
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			userlocal.setUserId(result.getInt(1));
			userlocal.setUserName(result.getString(2));
			userlocal.setUserPass(result.getString(3));
			userlocal.setUserEmail(result.getString(4));
		}
		return userlocal;
	}	
	
}
