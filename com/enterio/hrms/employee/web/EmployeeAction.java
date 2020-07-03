package com.enterio.hrms.employee.web;

import java.io.Serializable;
import java.util.List;

import com.enterio.common.service.CommonService;
import com.enterio.foundation.domain.Bank;
import com.enterio.foundation.domain.User;
import com.enterio.hrms.department.service.DepartmentService;
import com.enterio.hrms.employee.service.EmployeeService;
import com.enterio.hrms.foundation.domain.Department;
import com.enterio.hrms.foundation.domain.Employee;
import com.enterio.hrms.foundation.domain.EmployeeAddress;
import com.enterio.hrms.foundation.domain.MaritalStatus;
import com.enterio.init.StrutsActionSupport;
import com.enterio.util.DateUtil;
import com.enterio.util.StringUtil;

public class EmployeeAction extends StrutsActionSupport implements Serializable 
{
	private static final long serialVersionUID = 5337420708656983271L;
	private String employeeUUID;
	private String dateOfBirthInput;
	private String joiningDateInput;
	private String probationEndDateInput;
	private Employee employee;
	private EmployeeAddress currentAddress;
	private EmployeeAddress permanentAddress;
	private List<Department> departments;
	private List<MaritalStatus> maritalStatuses;
	private List<Bank> banks;
	private List<Employee> employees;
	private CommonService commonService;
	private com.enterio.hrms.common.service.CommonService hrmsCommonService;
	private DepartmentService departmentService;
	private EmployeeService employeeService;

	public String employeeList() 
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null && sessionUser.getCompany().getId() > 0)
		{
			employees= employeeService.getEmployees(sessionUser.getCompany().getId());
		}

		return SUCCESS;
	}
	
	public String employeeDetails()
	{
		employeeUUID = StringUtil.checkNull(employeeUUID);
		if(employeeUUID.length() > 0)
		{
			employee = employeeService.getEmployee(employeeUUID);
			if(employee != null)
			{
				if(employee.getAddresses() != null)
				{
					for(int i=0; i < employee.getAddresses().size(); i++)
					{
						if(employee.getAddresses().get(i).getType().toUpperCase().equals("CURRENT"))
						{
							currentAddress = employee.getAddresses().get(i);
						}
						else if(employee.getAddresses().get(i).getType().toUpperCase().equals("PERMANENT"))
						{
							permanentAddress = employee.getAddresses().get(i);
						}
					}
				}
			}
		}
		
		return SUCCESS;
	}

	
	public String addEmployee() 
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null && sessionUser.getCompany().getId() > 0)
		{
			departments = getDepartmentService().getDepartments(sessionUser.getCompany().getUuid());
			maritalStatuses = getHrmsCommonService().getMaritalStatus();
		}
		
		return SUCCESS;
	}
	
	public String editEmployee()
	{
		User sessionUser = super.getSessionUser();
		employeeUUID = StringUtil.checkNull(employeeUUID);
		
		if(sessionUser != null && sessionUser.getCompany().getId() > 0 && employeeUUID.length() > 0)
		{
			departments = getDepartmentService().getDepartments(sessionUser.getCompany().getUuid());
			maritalStatuses = getHrmsCommonService().getMaritalStatus();
			employee = getEmployeeService().getEmployee(employeeUUID);
			if(employee != null)
			{
				dateOfBirthInput = DateUtil.convertToDateString(employee.getDateOfBirth());
				joiningDateInput = DateUtil.convertToDateString(employee.getJoiningDate());
				probationEndDateInput = DateUtil.convertToDateString(employee.getProbationEndDate());
			}
		}
		
		return SUCCESS;
	}

	public String saveEmployee() 
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null)
		{
			employee = getEmployeeService().saveEmployee(employee, dateOfBirthInput, joiningDateInput, probationEndDateInput, sessionUser);
			redirectURL = "Details/" + employee.getUuid();
		}
		
		return SUCCESS;
	}
	
	public String updateBankAccount()
	{
		employeeUUID = StringUtil.checkNull(employeeUUID);
		if (employeeUUID != null && employeeUUID.length() > 0)
		{
			employee = getEmployeeService().getEmployee(employeeUUID);
			if (employee != null)
			{
				banks = getCommonService().getBanks();
			}
		}
		
		return SUCCESS;
	}
	
	public String saveBankAccount()
	{
		User sessionUser = super.getSessionUser();
		if (sessionUser != null)
		{
			employee = getEmployeeService().updateBankAccount(employee);
			redirectURL = "../Details/" + employee.getUuid();
		}
		
		return SUCCESS;
	}
	
/*	
	public String deleteEmployee() {
		if (uuid.length() > 0) {
			getEmployeeService().deleteEmployee(uuid);
			redirectURL = "EmployeeList";
		}
		return SUCCESS;
	}
*/
	
	public void setEmployeeUUID(String employeeUUID) {
		this.employeeUUID = employeeUUID;
	}
	public String getDateOfBirthInput() {
		return dateOfBirthInput;
	}
	public void setDateOfBirthInput(String dateOfBirthInput) {
		this.dateOfBirthInput = dateOfBirthInput;
	}
	public String getJoiningDateInput() {
		return joiningDateInput;
	}
	public void setJoiningDateInput(String joiningDateInput) {
		this.joiningDateInput = joiningDateInput;
	}
	public String getProbationEndDateInput() {
		return probationEndDateInput;
	}
	public void setProbationEndDateInput(String probationEndDateInput) {
		this.probationEndDateInput = probationEndDateInput;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public EmployeeAddress getCurrentAddress() {
		return currentAddress;
	}
	public EmployeeAddress getPermanentAddress() {
		return permanentAddress;
	}
	public List<Department> getDepartments() {
		return departments;
	}
	public List<MaritalStatus> getMaritalStatuses() {
		return maritalStatuses;
	}
	public List<Bank> getBanks() {
		return banks;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public CommonService getCommonService() {
		return commonService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	public com.enterio.hrms.common.service.CommonService getHrmsCommonService() {
		return hrmsCommonService;
	}
	public void setHrmsCommonService(com.enterio.hrms.common.service.CommonService hrmsCommonService) {
		this.hrmsCommonService = hrmsCommonService;
	}
	public DepartmentService getDepartmentService() {
		return departmentService;
	}
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
}