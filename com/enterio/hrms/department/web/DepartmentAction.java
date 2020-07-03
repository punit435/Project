package com.enterio.hrms.department.web;

import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.department.service.DepartmentService;
import com.enterio.hrms.foundation.domain.Department;
import com.enterio.init.StrutsActionSupport;
import com.enterio.util.StringUtil;

public class DepartmentAction extends StrutsActionSupport implements Serializable
{
	private static final long serialVersionUID = 892053067297383280L;
	private String departmentUUID;
	private List<Department> departments;
	private Department department;
	private DepartmentService departmentService;

	public String departmentList() 
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null && sessionUser.getCompany() != null)
		{
			departments = getDepartmentService().getDepartments(sessionUser.getCompany().getUuid());
		}

		return SUCCESS;
	}
	
	public String departmentDetails()
	{
		departmentUUID = StringUtil.checkNull(departmentUUID);
		if(departmentUUID.length() > 0)
		{
			department = getDepartmentService().getDepartment(departmentUUID);
		}
		
		return SUCCESS;
	}

	public String addDepartment() 
	{
		return SUCCESS;
	}

	public String editDepartment() 
	{
		User sessionUser = super.getSessionUser();
		departmentUUID = StringUtil.checkNull(departmentUUID);
		
		if(departmentUUID.length() > 0 && sessionUser != null)
		{
			department = getDepartmentService().getDepartment(departmentUUID);
		}

		return SUCCESS;
	}
	
	public String saveDepartment() 
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null)
		{
			getDepartmentService().saveDepartment(department, sessionUser);
			redirectURL = "Departments";
		}
	
		return SUCCESS;
	}

	public String deleteDepartment() 
	{
		departmentUUID = StringUtil.checkNull(departmentUUID);
		if(departmentUUID.length() > 0)
		{
			getDepartmentService().deleteDepartment(departmentUUID);
	
			redirectURL = "Departments";
		}
		
		return SUCCESS;
	}

	public void setDepartmentUUID(String departmentUUID) {
		this.departmentUUID = departmentUUID;
	}
	public List<Department> getDepartments() {
		return departments;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public DepartmentService getDepartmentService() {
		return departmentService;
	}
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
}