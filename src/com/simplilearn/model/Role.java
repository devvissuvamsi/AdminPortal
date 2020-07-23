package com.simplilearn.model;

public class Role {
	private int roleId;
	private String key, name;

	public Role(int roleId, String key, String name) {
		super();
		this.key = key;
		this.name = name;
		this.roleId = roleId;
	}

	public Role(String key, String name) {
		super();
		this.key = key;
		this.name = name;
	}

	public int getroleId() {
		return roleId;
	}

	public void setroleId(int roleId) {
		this.roleId = roleId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
