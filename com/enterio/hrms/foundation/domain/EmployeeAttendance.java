package com.enterio.hrms.foundation.domain;

import java.io.Serializable;
import java.util.Date;

import com.enterio.foundation.domain.User;

public class EmployeeAttendance implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int id;
	private String uuid;
	private String latitude;
	private String longitude;
	private Date attendanceDate;
	private Date timeStamp;
	private Date createdOn;
	private Employee employee;
	private User createdBy;
	private AttendanceType attendanceType;
	
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
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Date getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public AttendanceType getAttendanceType() {
		return attendanceType;
	}
	public void setAttendanceType(AttendanceType attendanceType) {
		this.attendanceType = attendanceType;
	}
}