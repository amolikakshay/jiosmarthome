package com.jio.smarthome.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	
	@JsonProperty("fname")
	private String fname;
	@JsonProperty("lname")
	private String lname;
	@JsonProperty("mobile")
	private long mobile;
	@JsonProperty("email")
	private String email;
	
	private int id;
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
