package com.simplilearn.model;

public class User {
	private int userId;
	private String userName,userPass,userEmail;
	private Address userAddress;
	private Role userRole;
	
	public User(int userId, String userName, String userPass, String userEmail, Address userAddress, Role userRole) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPass = userPass;
		this.userEmail = userEmail;
		this.userAddress = userAddress;
		this.userRole = userRole;
	}
	public User() {
		super();
	}
	public User(String userName, String userPass) {
		super();
		this.userName = userName;
		this.userPass = userPass;
	}
	public User(String userName, String email,String userPass) {
		super();
		this.userName = userName;
		this.userEmail = email;
		this.userPass = userPass;
	}	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Address getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(Address userAddress) {
		this.userAddress = userAddress;
	}
	public Role getUserRole() {
		return userRole;
	}
	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}
}
