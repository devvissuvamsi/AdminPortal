package com.simplilearn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.simplilearn.model.User;

public class UserDAO {

	private static final String 
	insertUserSql = "insert into user(name,email) values(?,?)"
	,updateUserSql = "update user set name=?,email=? where id = ?"
	,deleteUserSql = "delete from user where id = ?"
	,selectUserSql = "select * from	users where id = ?"
	,selectAllUserSql = "select * from user"
	,selectAllUserByRoleKeySql = "select u.email,u.username,u.password,u.id,u.isactive from user u where exists (select 1 from user_role ur inner join role r on r.id = ur.role_id and r.key = ? and ur.user_id = u.id and  r.isactive = 1) and u.isactive = 1;"
	,selectUserByNameAndPass = "select u.email,u.username,u.password,u.id,u.isactive from user u where u.username = ? and u.password = ? and exists (select 1 from user_role ur inner join role r on r.id = ur.role_id and r.key = ? and r.isactive = 1 and ur.user_id = u.id) and u.isactive = 1;";
	
	private DbConnSingleton dbConnSingleton = DbConnSingleton.getInstance();
	private Connection conn;

	public UserDAO() {
		super();
	}
	
	public User findUser(User user) throws SQLException {
		this.conn = dbConnSingleton.getStoredMySqlConnection();
		User userlocal = new User();
		
		PreparedStatement statement = this.conn.prepareStatement(selectUserByNameAndPass);
		statement.setString(1,user.getUserName());
		statement.setString(2,user.getUserPass());
		statement.setString(3,"admin");
		
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			userlocal.setUserId(Integer.parseInt(result.getObject("id").toString()));
			userlocal.setUserName(result.getObject("username").toString());
			userlocal.setUserEmail(result.getObject("email").toString());
		}
		this.conn.close();
		return userlocal;
	}

	public void insertUser(User user) throws SQLException {
		conn = dbConnSingleton.getStoredMySqlConnection();
		PreparedStatement statement = conn.prepareStatement(insertUserSql);
		statement.setString(1,user.getUserName());
		statement.setString(2,user.getUserEmail());
		statement.executeUpdate();
		conn.close();
	}

	public boolean updateUser(User user) throws SQLException {
		conn = dbConnSingleton.getStoredMySqlConnection();
		PreparedStatement statement = conn.prepareStatement(updateUserSql);
		statement.setString(1,user.getUserName());
		statement.setString(2,user.getUserEmail());
		statement.setInt(3,user.getUserId());
		boolean result = statement.executeUpdate() > 0;
		conn.close();
		return result;
	}

	public boolean deleteUser(int id) throws SQLException {
		conn = dbConnSingleton.getStoredMySqlConnection();
		PreparedStatement statement = conn.prepareStatement(deleteUserSql);
		statement.setInt(1,id);
		boolean result = statement.executeUpdate() > 0;
		conn.close();
		return result;
	}

	public List<User> selectAllUser() throws SQLException {
		List<User> userList = new ArrayList<User>();
		
		conn = dbConnSingleton.getStoredMySqlConnection();
		PreparedStatement statement = conn.prepareStatement(selectAllUserSql);
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			User user = new User();
			user.setUserId(Integer.parseInt(result.getObject("id").toString()));
			user.setUserName(result.getObject("username").toString());
			user.setUserEmail(result.getObject("email").toString());
			userList.add(user);
		}
		return userList;
	}

	public List<User> selectAllUser(String roleKey) throws SQLException {
		List<User> userList = new ArrayList<User>();
		
		conn = dbConnSingleton.getStoredMySqlConnection();
		PreparedStatement statement = conn.prepareStatement(selectAllUserByRoleKeySql);
		statement.setString(1,roleKey);
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			User user = new User();
			user.setUserId(Integer.parseInt(result.getObject("id").toString()));
			user.setUserName(result.getObject("username").toString());
			user.setUserEmail(result.getObject("email").toString());
			userList.add(user);
		}
		return userList;
	}
	
	public User findUserById(int id) throws SQLException {
		User user = new User();
		
		conn = dbConnSingleton.getStoredMySqlConnection();
		PreparedStatement statement = conn.prepareStatement(selectUserSql);
		statement.setInt(1,id);
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			user.setUserId(result.getInt(1));
			user.setUserName(result.getString(2));
			user.setUserEmail(result.getString(3));
		}
		return user;
	}	
	
}
