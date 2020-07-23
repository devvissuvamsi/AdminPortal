package com.simplilearn.model;

public class Address {
	private int addressId,zip,objid;
	private String type,address1,address2,state,country;
	
	public Address(int addressId, int zip, int objid, String type, String address1, String address2, String state,
			String country) {
		super();
		this.addressId = addressId;
		this.zip = zip;
		this.objid = objid;
		this.type = type;
		this.address1 = address1;
		this.address2 = address2;
		this.state = state;
		this.country = country;
	}
	public Address(int zip, int objid, String type, String address1, String address2, String state, String country) {
		super();
		this.zip = zip;
		this.objid = objid;
		this.type = type;
		this.address1 = address1;
		this.address2 = address2;
		this.state = state;
		this.country = country;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public int getObjid() {
		return objid;
	}
	public void setObjid(int objid) {
		this.objid = objid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
