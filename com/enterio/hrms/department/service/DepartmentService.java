package com.enterio.hrms.department.service;

import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.foundation.domain.Department;

public interface DepartmentService extends Serializable
{
	public List<Department> getDepartments(String companyUUID);

	public Department getDepartment(String departmentUUID);

	public Department saveDepartment(Department department, User createdBy);
	
	public boolean deleteDepartment(String departmentUUID);
}