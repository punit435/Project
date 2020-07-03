package com.enterio.hrms.foundation.domain;

import java.io.Serializable;

public class ConsultancyContact implements Serializable
{
	private static final long serialVersionUID = -7803068946011626318L;
	private int id;
	private String uuid;
	private String name;
	private String email;
	private String mobile;
	private String alternateMobile;
	private String landline;
	private String alternateLandline;
	private boolean active;
	private Consultancy consultancy;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAlternateMobile() {
		return alternateMobile;
	}
	public void setAlternateMobile(String alternateMobile) {
		this.alternateMobile = alternateMobile;
	}
	public String getLandline() {
		return landline;
	}
	public void setLandline(String landline) {
		this.landline = landline;
	}
	public String getAlternateLandline() {
		return alternateLandline;
	}
	public void setAlternateLandline(String alternateLandline) {
		this.alternateLandline = alternateLandline;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Consultancy getConsultancy() {
		return consultancy;
	}
	public void setConsultancy(Consultancy consultancy) {
		this.consultancy = consultancy;
	}
}