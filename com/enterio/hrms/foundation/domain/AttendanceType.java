package com.enterio.hrms.foundation.domain;

import java.io.Serializable;

public class AttendanceType implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private boolean isActive;

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
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}