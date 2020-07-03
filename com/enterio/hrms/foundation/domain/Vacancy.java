package com.enterio.hrms.foundation.domain;

import java.io.Serializable;
import java.util.Date;

import com.enterio.foundation.domain.User;

public class Vacancy implements Serializable
{
	private static final long serialVersionUID = 500924293218959835L;
	private int id;
	private String uuid;
	private int positions;
	private int experienceMin;
	private int experienceMax;
	private String jobDescription;
	private String skills;
	private String location;
	private boolean active;
	private Date createdOn;
	private User createdBy;
	private Department department;
	private Designation designation;
	private VacancyStatus status;
	
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
	public int getPositions() {
		return positions;
	}
	public void setPositions(int positions) {
		this.positions = positions;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getExperienceMin() {
		return experienceMin;
	}
	public void setExperienceMin(int experienceMin) {
		this.experienceMin = experienceMin;
	}
	public int getExperienceMax() {
		return experienceMax;
	}
	public void setExperienceMax(int experienceMax) {
		this.experienceMax = experienceMax;
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
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Designation getDesignation() {
		return designation;
	}
	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	public VacancyStatus getStatus() {
		return status;
	}
	public void setStatus(VacancyStatus status) {
		this.status = status;
	}
}