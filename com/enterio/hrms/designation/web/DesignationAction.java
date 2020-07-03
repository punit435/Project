package com.enterio.hrms.designation.web;

import java.io.Serializable;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.department.service.DepartmentService;
import com.enterio.hrms.designation.service.DesignationService;
import com.enterio.hrms.foundation.domain.Department;
import com.enterio.hrms.foundation.domain.Designation;
import com.enterio.init.StrutsActionSupport;
import com.enterio.util.StringUtil;

public class DesignationAction extends StrutsActionSupport implements Serializable
{
	private static final long serialVersionUID = -3283386632628919222L;
	private String departmentUUID;
	private String designationUUID;
	private Designation designation;
	private Department departmentObj;
	private DepartmentService departmentService;
	private DesignationService designationService;

	public String addDesignation() 
	{
		departmentUUID = StringUtil.checkNull(departmentUUID);
		
		if(departmentUUID.length() > 0)
		{
			departmentObj = getDepartmentService().getDepartment(departmentUUID);
		}
		
		return SUCCESS;
	}
	
	public String editDesignation() 
	{
		departmentUUID = StringUtil.checkNull(departmentUUID);
		designationUUID = StringUtil.checkNull(designationUUID);
		
		if(departmentUUID.length() > 0 && designationUUID.length() > 0)
		{
			departmentObj = getDepartmentService().getDepartment(departmentUUID);
			designation = getDesignationService().getDesignation(designationUUID);
		}
		
		return SUCCESS;
	}

	public String saveDesignation() 
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null)
		{
			getDesignationService().saveDesignation(designation, sessionUser);
			
			redirectURL = "../Department/Details/" + designation.getDepartment().getUuid();
		}
	
		return SUCCESS;
	}

	public String deleteDesignation() 
	{
		departmentUUID = StringUtil.checkNull(departmentUUID);
		designationUUID = StringUtil.checkNull(designationUUID);
		
		if(departmentUUID.length() > 0 && designationUUID.length() > 0)
		{
			getDesignationService().deleteDesignation(designationUUID);
			
			redirectURL = "../Department/Details/" + departmentUUID;
		}
		
		return SUCCESS;
	}

	public void setDepartmentUUID(String departmentUUID) {
		this.departmentUUID = departmentUUID;
	}
	public void setDesignationUUID(String designationUUID) {
		this.designationUUID = designationUUID;
	}
	public Department getDepartmentObj() {
		return departmentObj;
	}
	public Designation getDesignation() {
		return designation;
	}
	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	public DesignationService getDesignationService() {
		return designationService;
	}
	public void setDesignationService(DesignationService designationService) {
		this.designationService = designationService;
	}
	public DepartmentService getDepartmentService() {
		return departmentService;
	}
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
}