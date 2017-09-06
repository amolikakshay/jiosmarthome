package com.jio.smarthome.dao;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Test implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3921646245590019657L;
	private String name;
	
//	public Test(@JsonProperty("name") String name) {
//		this.name = name;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
