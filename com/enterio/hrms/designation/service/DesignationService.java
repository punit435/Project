package com.enterio.hrms.designation.service;

import java.io.Serializable;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.foundation.domain.Designation;

public interface DesignationService extends Serializable
{
	public Designation getDesignation(String designationUUID);
	
	public Designation saveDesignation(Designation designation, User createdBy);

	public boolean deleteDesignation(String designationUUID);
}