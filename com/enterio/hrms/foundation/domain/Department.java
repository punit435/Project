package com.enterio.hrms.foundation.domain;

import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.Company;

public class Department implements Serializable
{
	private static final long serialVersionUID = 7211969971834436063L;
	private int id;
	private String uuid;
	private String name;
	private Company company;
	private boolean active;
	private List<Designation> designations;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public List<Designation> getDesignations() {
		return designations;
	}
	public void setDesignations(List<Designation> designations) {
		this.designations = designations;
	}
}