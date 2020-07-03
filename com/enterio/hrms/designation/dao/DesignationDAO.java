package com.enterio.hrms.designation.dao;

import java.io.Serializable;

import com.enterio.hrms.foundation.domain.Designation;

public interface DesignationDAO extends Serializable
{
	public Designation getDesignation(String userUUID);
	
	public void insertDesignation(Designation designation);
	
	public void updateDesignation(Designation designation);
	
	public void deleteDesignation(Designation designation);
}