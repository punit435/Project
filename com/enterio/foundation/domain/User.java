package com.enterio.foundation.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.enterio.hrms.foundation.domain.Department;
import com.enterio.hrms.foundation.domain.Employee;

public class User implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private int id;
	private String uuid;
	private String name;
	private String email;
	private String password;
	private String authToken;
	private Date tokenExpiresOn;
	private boolean active;
	private boolean reset;
	private Date createdOn;
	private Date lastLoggedIn;
	private User createdBy;
	private Company company;
	private Employee employee;
	private List<Department> departments;
	private List<UserModule> modules;
	Map<String, String> moduleUserType;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public Date getTokenExpiresOn() {
		return tokenExpiresOn;
	}
	public void setTokenExpiresOn(Date tokenExpiresOn) {
		this.tokenExpiresOn = tokenExpiresOn;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isReset() {
		return reset;
	}
	public void setReset(boolean reset) {
		this.reset = reset;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getLastLoggedIn() {
		return lastLoggedIn;
	}
	public void setLastLoggedIn(Date lastLoggedIn) {
		this.lastLoggedIn = lastLoggedIn;
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public List<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	public List<UserModule> getModules() {
		return modules;
	}
	public void setModules(List<UserModule> modules) {
		this.modules = modules;
	}
	public Map<String, String> getModuleUserType() {
		return moduleUserType;
	}
	public void setModuleUserType(Map<String, String> moduleUserType) {
		this.moduleUserType = moduleUserType;
	}
}