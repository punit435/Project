package com.enterio.hrms.foundation.domain;

import java.io.Serializable;

public class CandidateType implements Serializable
{
	private static final long serialVersionUID = 5434432267210250657L;
	private int id;
	private String name;
	
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
}