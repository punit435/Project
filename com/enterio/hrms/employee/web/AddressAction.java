package com.enterio.hrms.employee.web;

import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.common.service.CommonService;
import com.enterio.hrms.employee.service.EmployeeService;
import com.enterio.hrms.foundation.domain.Employee;
import com.enterio.hrms.foundation.domain.EmployeeAddress;
import com.enterio.hrms.foundation.domain.State;
import com.enterio.init.StrutsActionSupport;
import com.enterio.util.StringUtil;

public class AddressAction extends StrutsActionSupport implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private String employeeUUID;
	private String currentAddressLine1;
	private String currentAddressLine2;
	private String currentAddressCity;
	private int currentAddressStateId;
	private String currentAddressPincode;
	private String permanentAddressLine1;
	private String permanentAddressLine2;
	private String permanentAddressCity;
	private int permanentAddressStateId;
	private String permanentAddressPincode;
	private Employee employeeObj;
	private EmployeeAddress currentAddress;
	private EmployeeAddress permanentAddress;
	private List<State> states;
	private EmployeeService employeeService;
	private CommonService hrmsCommonService;

	public String updateAddress()
	{
		employeeUUID = StringUtil.checkNull(employeeUUID);
		if(employeeUUID.length() > 0)
		{
			employeeObj = employeeService.getEmployee(employeeUUID);
			if(employeeObj != null)
			{
				states = getHrmsCommonService().getStates();				
				if(employeeObj.getAddresses() != null)
				{
					for(int i=0; i < employeeObj.getAddresses().size(); i++)
					{
						if(employeeObj.getAddresses().get(i).getType().toUpperCase().equals("CURRENT"))
						{
							currentAddress = employeeObj.getAddresses().get(i);
						}
						else if(employeeObj.getAddresses().get(i).getType().toUpperCase().equals("PERMANENT"))
						{
							permanentAddress = employeeObj.getAddresses().get(i);
						}
					}
				}
			}
		}
		
		return SUCCESS;
	}

	public String saveAdddress()
	{
		currentAddressLine1 = StringUtil.checkNull(currentAddressLine1);
		currentAddressLine2 = StringUtil.checkNull(currentAddressLine2);
		currentAddressCity = StringUtil.checkNull(currentAddressCity);
		currentAddressPincode = StringUtil.checkNull(currentAddressPincode);
		permanentAddressLine1 = StringUtil.checkNull(permanentAddressLine1);
		permanentAddressLine2 = StringUtil.checkNull(permanentAddressLine2);
		permanentAddressCity = StringUtil.checkNull(permanentAddressCity);
		permanentAddressPincode = StringUtil.checkNull(permanentAddressPincode);
		
		User sessionUser = super.getSessionUser();
		if(sessionUser != null && currentAddressLine1.length() > 0 && currentAddressCity.length() > 0 && currentAddressStateId > 0 && currentAddressPincode.length() > 0)
		{
			employeeObj = getEmployeeService().saveAddress(employeeUUID, sessionUser, currentAddressLine1, currentAddressLine2, currentAddressCity, currentAddressStateId, currentAddressPincode,
					permanentAddressLine1, permanentAddressLine2, permanentAddressCity, permanentAddressStateId, permanentAddressPincode);
			
			redirectURL =  "../Details/" + employeeObj.getUuid();
		}

		return SUCCESS;
	}

	public void setEmployeeUUID(String employeeUUID) {
		this.employeeUUID = employeeUUID;
	}
	public void setCurrentAddressLine1(String currentAddressLine1) {
		this.currentAddressLine1 = currentAddressLine1;
	}
	public void setCurrentAddressLine2(String currentAddressLine2) {
		this.currentAddressLine2 = currentAddressLine2;
	}
	public void setCurrentAddressCity(String currentAddressCity) {
		this.currentAddressCity = currentAddressCity;
	}
	public void setCurrentAddressStateId(int currentAddressStateId) {
		this.currentAddressStateId = currentAddressStateId;
	}
	public void setCurrentAddressPincode(String currentAddressPincode) {
		this.currentAddressPincode = currentAddressPincode;
	}
	public void setPermanentAddressLine1(String permanentAddressLine1) {
		this.permanentAddressLine1 = permanentAddressLine1;
	}
	public void setPermanentAddressLine2(String permanentAddressLine2) {
		this.permanentAddressLine2 = permanentAddressLine2;
	}
	public void setPermanentAddressCity(String permanentAddressCity) {
		this.permanentAddressCity = permanentAddressCity;
	}
	public void setPermanentAddressStateId(int permanentAddressStateId) {
		this.permanentAddressStateId = permanentAddressStateId;
	}
	public void setPermanentAddressPincode(String permanentAddressPincode) {
		this.permanentAddressPincode = permanentAddressPincode;
	}
	public Employee getEmployeeObj() {
		return employeeObj;
	}
	public EmployeeAddress getCurrentAddress() {
		return currentAddress;
	}
	public EmployeeAddress getPermanentAddress() {
		return permanentAddress;
	}
	public List<State> getStates() {
		return states;
	}
	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	public CommonService getHrmsCommonService() {
		return hrmsCommonService;
	}
	public void setHrmsCommonService(CommonService hrmsCommonService) {
		this.hrmsCommonService = hrmsCommonService;
	}
}