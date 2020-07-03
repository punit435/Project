package com.enterio.hrms.employee.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.FinancialYear;
import com.enterio.foundation.domain.User;
import com.enterio.hrms.foundation.domain.DocumentType;
import com.enterio.hrms.foundation.domain.Employee;
import com.enterio.hrms.foundation.domain.EmployeeAttendance;
import com.enterio.hrms.foundation.domain.EmployeeDocument;
import com.enterio.hrms.foundation.domain.PaySlip;

public interface EmployeeService extends Serializable
{
	public List<Employee> getEmployees(int companyId);
	
	public Employee getEmployee(String employeeUUID);

	public Employee saveEmployee(Employee employee, String dateOfBirth, String joiningDate, String probationEndDate, User createdBy);

	public Employee saveAddress(String employeeUUID, User updatedBy, String currentAddressLine1, String currentAddressLine2, String currentAddressCity, int currentAddressStateId,
			String currentAddressPincode, String permanentAddressLine1, String permanentAddressLine2, String permanentAddressCity, int permanentAddressStateId, 
			String permanentAddressPincode);
	
	public Employee updateBankAccount(Employee employee);
	
	public List<DocumentType> getPendingDocumentTypes(int companyId, String employeeUUID);
	
	public EmployeeDocument getDocument(String documentUUID);
	
	public Employee saveDocument(String employeeUUID, String[] documentTypeId, File[] documentFile, String[] documentFileName, String[] documentNumber, User uploadedBy);
	
	public boolean deleteDocument(String documentUUID);
	
	public void saveEmployeeAttendance(Employee employee, User sesssionUser, EmployeeAttendance employeeAttendance);

	public List<EmployeeAttendance> getAttendanceForEmployee(int employeeId);

	public EmployeeAttendance getAttendanceByLatestDate(int employeeId);

	void updateEmployeeAttendance(EmployeeAttendance employeeAttendance);

	public List<EmployeeAttendance> getAttendanceForEmployeeByMonth(int month, String year,Employee employee);

	public List<PaySlip> getPaySlipByEmployeeId(int id, FinancialYear financialYear1, FinancialYear financialYear2);

	public PaySlip getPaySlipDetails(int paySlipId, int employeeId);
}