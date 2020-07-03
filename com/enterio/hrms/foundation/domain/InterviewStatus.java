package com.enterio.hrms.foundation.domain;

import java.io.Serializable;

import com.enterio.foundation.domain.Company;

public class InterviewStatus implements Serializable 
{	
	private static final long serialVersionUID = -4034301311593174518L;
	private int id;
	private String name;
	private Company company;
	private String uuid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}