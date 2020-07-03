package com.enterio.hrms.department.dao;

import java.io.Serializable;
import java.util.List;

import com.enterio.hrms.foundation.domain.Department;

public interface DepartmentDAO extends Serializable
{
	public List<Department> getDepartments(String companyUUID);
	
	public Department getDepartment(String departmentUUID);
	
	public void insertDepartment(Department department);
	
	public void updateDepartment(Department department);
	
	public void deleteDepartment(Department department);
}