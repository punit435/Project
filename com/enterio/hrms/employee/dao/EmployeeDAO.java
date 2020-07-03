package com.enterio.hrms.employee.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.enterio.hrms.foundation.domain.DocumentType;
import com.enterio.hrms.foundation.domain.Employee;
import com.enterio.hrms.foundation.domain.EmployeeAddress;
import com.enterio.hrms.foundation.domain.EmployeeAttendance;
import com.enterio.hrms.foundation.domain.EmployeeDocument;
import com.enterio.hrms.foundation.domain.PaySlip;

public interface EmployeeDAO extends Serializable
{
	public List<Employee> getEmployees(int companyId);
	
	public Employee getEmployeeByUUID(String employeeUUID);

	public void insertEmployee(Employee employee);
	
	public void updateEmployee(Employee employee);

	public EmployeeAddress getAddressByType(Map<String, Object> parameters);
	
	public void insertAddress(EmployeeAddress address);

	public void updateAddress(EmployeeAddress address);
	
	public void updateBankAccount(Employee employee);

	public List<DocumentType> getPendingDocumentTypes(Map<String, Object> parameters);

	public EmployeeDocument getDocumentByUUID(String documentUUID);

	public void insertDocument(EmployeeDocument employeeDocument);
	
	public void deleteDocument(EmployeeDocument employeeDocument);
	
	public void insertEmployeeAttendance(EmployeeAttendance employeeAttendance);

	public void updateEmployeeAttendance(EmployeeAttendance employeeAttendance);

	public List<EmployeeAttendance> getAttendenceForEmployee(int employeeId);

	public EmployeeAttendance getAttendanceByLatestDate(int employeeId);
	
	public List<EmployeeAttendance> getAttendanceByMonth(Map<String, Object> data);
	
	public List<PaySlip> getPaySlips(Map<String, Object> parameters);

	public PaySlip getPaySlipForEmployee(Map<String, Object> parameters);
	
/*	

	public EmployeeAddress getEmployeeAddressForEmployee(Map<String, Object> parameterMap);
	
	public EmployeeAddress getEmployeeAddressByUUID(String uuid);

	public EmployeeDocument getEmployeeDocumentForEmployee(Map<String, Object> parameterMap);
	
	
	public void deleteEmployee(String uuid);
	

	public void insertEmployeeDocument(EmployeeDocument employeeDocument);

	public void updateEmployeeDocument(EmployeeDocument employeeDocument);

	public EmployeeAddress getEmployeeAddressByEmployeeID(int employeeId);
*/
}