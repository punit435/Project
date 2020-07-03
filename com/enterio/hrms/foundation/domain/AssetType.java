package com.enterio.hrms.foundation.domain;

import java.io.Serializable;

import com.enterio.foundation.domain.Company;

public class AssetType implements Serializable
{
	private static final long serialVersionUID = 6153158714033247311L;
	private int id;
	private String uuid;
	private String name;
	private boolean active;
	private Company company;
	
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
}