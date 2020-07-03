package com.enterio.hrms.foundation.domain;

import java.io.Serializable;
import java.sql.Date;

import com.enterio.foundation.domain.Company;
import com.enterio.foundation.domain.User;

public class DocumentType implements Serializable
{	
	private static final long serialVersionUID = -3330436211563757199L;
	private int id;
	private String uuid;
	private String name;
	private boolean active;
	private Date createdOn;
	private User createdBy;
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
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
}