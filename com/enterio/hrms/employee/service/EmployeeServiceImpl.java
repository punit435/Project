package com.enterio.hrms.employee.service;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;

import com.enterio.foundation.domain.FinancialYear;
import com.enterio.foundation.domain.User;
import com.enterio.hrms.common.service.CommonServiceImpl;
import com.enterio.hrms.designation.service.DesignationService;
import com.enterio.hrms.documenttype.service.DocumentTypeService;
import com.enterio.hrms.employee.dao.EmployeeDAO;
import com.enterio.hrms.foundation.domain.DocumentType;
import com.enterio.hrms.foundation.domain.Employee;
import com.enterio.hrms.foundation.domain.EmployeeAddress;
import com.enterio.hrms.foundation.domain.EmployeeAttendance;
import com.enterio.hrms.foundation.domain.EmployeeDocument;
import com.enterio.hrms.foundation.domain.PaySlip;
import com.enterio.user.dao.UserDAO;
import com.enterio.user.service.UserService;
import com.enterio.util.CryptographyUtil;
import com.enterio.util.DateUtil;
import com.enterio.util.FileEncryptionUtil;
import com.enterio.util.PropertyLoader;
import com.enterio.util.StringUtil;

public class EmployeeServiceImpl extends CommonServiceImpl implements Serializable, EmployeeService
{
	private static final long serialVersionUID = 3778265303886811389L;
	private DesignationService designationService;
	private DocumentTypeService documentTypeService;
	private UserService userService;

	public DesignationService getDesignationService() {
		return designationService;
	}
	public void setDesignationService(DesignationService designationService) {
		this.designationService = designationService;
	}
	public DocumentTypeService getDocumentTypeService() {
		return documentTypeService;
	}
	public void setDocumentTypeService(DocumentTypeService documentTypeService) {
		this.documentTypeService = documentTypeService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<Employee> getEmployees(int companyId) 
	{
		List<Employee> employeeList = null;
		
		if(companyId > 0)
		{
			SqlSession session = super.getDBSession();	
			if (session != null)
			{
				EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
				employeeList=employeeMapper.getEmployees(companyId);
			
			}
			super.closeDBSession(session);
		}

		return employeeList;
	}
	
	public Employee getEmployee(String employeeUUID) 
	{
		Employee employee = null;
		employeeUUID = StringUtil.checkNull(employeeUUID);
		
		if(employeeUUID.length() > 0)
		{
			SqlSession session = getDBSession();
			if(session != null)
			{
				EmployeeDAO employeeMepper = session.getMapper(EmployeeDAO.class);
				employee = employeeMepper.getEmployeeByUUID(employeeUUID);
			}
			closeDBSession(session);
		}
		
		return employee;
	}
	
	public Employee saveEmployee(Employee employee, String dateOfBirth, String joiningDate, String probationEndDate, User createdBy) 
	{
		dateOfBirth = StringUtil.checkNull(dateOfBirth);
		joiningDate = StringUtil.checkNull(joiningDate);
		probationEndDate = StringUtil.checkNull(probationEndDate);

		if(dateOfBirth.length() > 0 && joiningDate.length() > 0 && probationEndDate.length() > 0)
		{
			SqlSession session = getDBSession();
			if(session != null && createdBy != null && createdBy.getCompany() != null)
			{
				employee.setCompany(createdBy.getCompany());
				employee.setDateOfBirth(DateUtil.convertToDate(dateOfBirth));
				employee.setJoiningDate(DateUtil.convertToDate(joiningDate));
				employee.setProbationEndDate(DateUtil.convertToDate(probationEndDate));
				employee.setDesignation(getDesignationService().getDesignation(employee.getDesignation().getUuid()));
				employee.setReportsTo(userService.getUser(employee.getReportsTo().getUuid()));
				employee.setCreatedBy(createdBy);
				
				EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
				if(StringUtil.checkNull(employee.getUuid()).length() <= 0)
				{
					employee.setUuid(UUID.randomUUID().toString());
					employeeMapper.insertEmployee(employee);
					session.commit();
					
//Create user access credentials
					User user = new User();
					user.setUuid(UUID.randomUUID().toString());
					user.setName(employee.getFirstName() + " " + employee.getLastName());
					user.setEmail(employee.getOfficialEmail());
					user.setPassword(CryptographyUtil.generateDigest("enterio"));
					user.setCompany(createdBy.getCompany());
					user.setEmployee(employee);
					user.setCreatedBy(createdBy);
				
					UserDAO userMapper = session.getMapper(UserDAO.class);
					userMapper.insertUser(user);
					session.commit();
				}
				else
				{
					UserDAO userMapper = session.getMapper(UserDAO.class);
					User employeeUser = userMapper.getUserByEmployeeUUID(employee.getUuid());
					if(employeeUser != null)
					{
						employeeUser.setName(employee.getFirstName() + " " + employee.getLastName());
						employeeUser.setEmail(employee.getOfficialEmail());
						
						userMapper.updateEmployeeUser(employeeUser);
						employeeMapper.updateEmployee(employee);
						session.commit();
					}
				}
			}
			closeDBSession(session);
		}
		
		return employee;
	}
	
	public Employee saveAddress(String employeeUUID, User updatedBy, String currentAddressLine1, String currentAddressLine2, String currentAddressCity, int currentAddressStateId,
			String currentAddressPincode, String permanentAddressLine1, String permanentAddressLine2, String permanentAddressCity, int permanentAddressStateId, 
			String permanentAddressPincode)
	{
		Employee employee = null;
		employeeUUID = StringUtil.checkNull(employeeUUID);
		currentAddressLine1 = StringUtil.checkNull(currentAddressLine1);
		currentAddressLine2 = StringUtil.checkNull(currentAddressLine2);
		currentAddressCity = StringUtil.checkNull(currentAddressCity);
		currentAddressPincode = StringUtil.checkNull(currentAddressPincode);
		permanentAddressLine1 = StringUtil.checkNull(permanentAddressLine1);
		permanentAddressLine2 = StringUtil.checkNull(permanentAddressLine2);
		permanentAddressCity = StringUtil.checkNull(permanentAddressCity);
		permanentAddressPincode = StringUtil.checkNull(permanentAddressPincode);
		
		if(employeeUUID.length() > 0 && updatedBy != null && currentAddressLine1.length() > 0 && currentAddressCity.length() > 0 && currentAddressStateId > 0 
				&& currentAddressPincode.length() > 0)
		{
			employee = getEmployee(employeeUUID);
			
			SqlSession session = super.getDBSession();
			if(employee != null && session != null)
			{
				EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
//Update Current Address				
				Map<String, Object> parameterMap = new HashMap<String, Object>();
				parameterMap.put("EmployeeUUID", employeeUUID);
				parameterMap.put("AddressType", "CURRENT");
				
				EmployeeAddress currentAddress = employeeMapper.getAddressByType(parameterMap);
				if(currentAddress == null)
				{
					currentAddress = new EmployeeAddress();
					currentAddress.setEmployee(employee);
				}
				
				currentAddress.setLine1(currentAddressLine1);
				currentAddress.setLine2(currentAddressLine2);
				currentAddress.setCity(currentAddressCity);
				currentAddress.setState(getState(currentAddressStateId));
				currentAddress.setPincode(currentAddressPincode);
				currentAddress.setType("CURRENT");
				
				if(currentAddress.getUuid() != null)
				{
					employeeMapper.updateAddress(currentAddress);
				}
				else
				{
					currentAddress.setUuid(UUID.randomUUID().toString());
					employeeMapper.insertAddress(currentAddress);
				}
				
//Update Permanent Address				
				parameterMap.clear();
				parameterMap.put("EmployeeUUID", employeeUUID);
				parameterMap.put("AddressType", "PERMANENT");
				
				EmployeeAddress permanentAddress = employeeMapper.getAddressByType(parameterMap);
				if(permanentAddress == null)
				{
					permanentAddress = new EmployeeAddress();
					permanentAddress.setEmployee(employee);
				}
				permanentAddress.setLine1(permanentAddressLine1);
				permanentAddress.setLine2(permanentAddressLine2);
				permanentAddress.setCity(permanentAddressCity);
				permanentAddress.setState(getState(permanentAddressStateId));
				permanentAddress.setPincode(permanentAddressPincode);
				permanentAddress.setType("PERMANENT");
				
				if(permanentAddress.getUuid() != null)
				{
					employeeMapper.updateAddress(permanentAddress);
				}
				else
				{
					permanentAddress.setUuid(UUID.randomUUID().toString());
					employeeMapper.insertAddress(permanentAddress);
				}
				session.commit();
			}
			super.closeDBSession(session);
		}
		
		return employee;
	}
	
	public Employee updateBankAccount(Employee employee)
	{
		SqlSession session = getDBSession();
		if(session != null && employee != null) 
		{
			EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
			employeeMapper.updateBankAccount(employee);
			session.commit();
		}
		closeDBSession(session);
		
		return employee;
	}
	
	public List<DocumentType> getPendingDocumentTypes(int companyId, String employeeUUID)
	{
		List<DocumentType> documentTypeList = null;
		employeeUUID = StringUtil.checkNull(employeeUUID);
		
		if(companyId > 0 && employeeUUID.length() >= 0)
		{
			SqlSession session = getDBSession();
			if(session != null) 
			{
				Map<String, Object> parameterMap = new HashMap<String, Object>();
				parameterMap.put("CompanyId", companyId);
				parameterMap.put("EmployeeUUID", employeeUUID);
			
				EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
				documentTypeList = employeeMapper.getPendingDocumentTypes(parameterMap);
			}
			closeDBSession(session);
		}
		
		return documentTypeList;
	}
	
	public EmployeeDocument getDocument(String documentUUID)
	{
		EmployeeDocument document = null;
		documentUUID = StringUtil.checkNull(documentUUID);
		
		if(documentUUID.length() > 0)
		{
			SqlSession session = getDBSession();
			if(session != null)
			{
				EmployeeDAO employeeMepper = session.getMapper(EmployeeDAO.class);
				document = employeeMepper.getDocumentByUUID(documentUUID);
			}
			closeDBSession(session);
		}
		
		
		return document;
	}

	public Employee saveDocument(String employeeUUID, String[] documentTypeId, File[] documentFile, String[] documentFileName, String [] documentNumber, User uploadedBy)
	{
		Employee employee = null;
		employeeUUID = StringUtil.checkNull(employeeUUID);
		
		if(employeeUUID.length() > 0 && documentTypeId != null && documentTypeId.length > 0)
		{
			employee = getEmployee(employeeUUID);
			if(employee != null)
			{
				SqlSession session = getDBSession();
				EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
				if(session != null && employee != null)
				{
					EmployeeDocument employeeDocument = null;
					for(int i = 0; i < documentTypeId.length; i++) 
					{
						employeeDocument = new EmployeeDocument();
						employeeDocument.setDocumentType(getDocumentTypeService().getDocumentType(documentTypeId[i]));
						employeeDocument.setDocumentNumber(documentNumber[i]);
						employeeDocument.setFile(documentFileName[i]);
						employeeDocument.setEmployee(employee);
						employeeDocument.setUuid(UUID.randomUUID().toString());
						employeeMapper.insertDocument(employeeDocument);
						
						String documentExtension = documentFileName[i].substring(documentFileName[i].lastIndexOf("."), documentFileName[i].length());
						String filePath = PropertyLoader.getProperty("EMPLOYEE_DOCUMENT_UPLOAD") + File.separator +  employeeDocument.getUuid() + documentExtension;
						try 
						{
							FileEncryptionUtil.encryptFile(documentFile[i], filePath);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					session.commit();
				}
				closeDBSession(session);
//Refresh employee object with document data				
				employee = getEmployee(employeeUUID);
			}
		}
		
		return employee;
	}
	
	public boolean deleteDocument(String documentUUID)
	{
		boolean isDocumentDeleted = false;
		documentUUID = StringUtil.checkNull(documentUUID);
		
		if(documentUUID.length() > 0)
		{
			EmployeeDocument document = getDocument(documentUUID);
			if(document != null)
			{
				SqlSession session = super.getDBSession();
				if (session != null)
				{
					try
					{
						String documentExtension = document.getFile().substring(document.getFile().lastIndexOf("."), document.getFile().length());
						String filePath = PropertyLoader.getProperty("EMPLOYEE_DOCUMENT_UPLOAD") + File.separator +  document.getUuid() + documentExtension;
						
						File file = new File(filePath);
						if(file.exists())
						{
							file.delete();
							
							EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
							employeeMapper.deleteDocument(document);
							session.commit();
							isDocumentDeleted = true;
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				super.closeDBSession(session);
			}
		}
		
		return isDocumentDeleted;
	}
	
	public void saveEmployeeAttendance(Employee employee, User sessionUser, EmployeeAttendance employeeAttendance) 
	{
		if( sessionUser != null )
		{
			SqlSession session = super.getDBSession();

			if (session != null)
			{
				EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
				if(employeeAttendance.getUuid() == null || employeeAttendance.getUuid().length() == 0)
				{
					/*Date attendanceDate = new Date();
					employeeAttendance.setAttendanceDate(attendanceDate);*/
					employeeAttendance.setUuid(UUID.randomUUID().toString());
					employeeAttendance.setEmployee(sessionUser.getEmployee());
					employeeAttendance.setCreatedBy(sessionUser);
					employeeMapper.insertEmployeeAttendance(employeeAttendance);
				}
				else
				{
					employeeMapper.updateEmployeeAttendance(employeeAttendance);
				}
				session.commit();
			}
			super.closeDBSession(session);
		}
	}
	
	public List<EmployeeAttendance> getAttendanceForEmployee(int employeeId)
	{
		List<EmployeeAttendance> employeeAttendanceList = null;

		SqlSession session = getDBSession();
		if(session != null)
		{
			EmployeeDAO employeeMepper = session.getMapper(EmployeeDAO.class);
			employeeAttendanceList = employeeMepper.getAttendenceForEmployee(employeeId);
		}
		super.closeDBSession(session);

		return employeeAttendanceList;
	}

	public void updateEmployeeAttendance(EmployeeAttendance employeeAttendance)
	{
		SqlSession session = getDBSession();
		if (session != null) 
		{
			EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
			employeeMapper.updateEmployeeAttendance(employeeAttendance);
			session.commit();
		}
		closeDBSession(session);
	}

	public List<EmployeeAttendance> getAttendanceForEmployeeByMonth(int month, String year, Employee employee) 
	{

		year = StringUtil.checkNull(year);
		List<EmployeeAttendance> employeeAttendanceList = null;

		if (month > 0 && year.length() > 0) 
		{
			SqlSession session = getDBSession();

			if (session != null)
			{
				int selectedYear = Integer.parseInt(year);

				Date firstDateofMonth = DateUtil.getFirstDateOfMonth(selectedYear, month);
				Date lastDateOfMonth = DateUtil.getLastDateOfMonth(selectedYear, month);

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("FirstDateofMonth", firstDateofMonth);
				map.put("LastDateOfMonth", lastDateOfMonth);
				map.put("EmployeeId", employee.getUuid());

				EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
				employeeAttendanceList = employeeMapper.getAttendanceByMonth(map);
			}
			closeDBSession(session);
		}

		return employeeAttendanceList;
	}

	public EmployeeAttendance getAttendanceByLatestDate(int employeeId)
	{
		EmployeeAttendance attendance = null;
		SqlSession session = getDBSession();

		if (session != null && employeeId > 0) 
		{
			EmployeeDAO employeeMepper = session.getMapper(EmployeeDAO.class);
			attendance = employeeMepper.getAttendanceByLatestDate(employeeId);
		}

		super.closeDBSession(session);

		return attendance;
	}
	
	public List<PaySlip> getPaySlipByEmployeeId(int employeeId, FinancialYear financialYear1, FinancialYear financialYear2)
	{
		List<PaySlip> paySlips = null;
		Map<String, Object> parameters = new HashMap<>();

		if (employeeId > 0 && financialYear1 != null && financialYear2 != null)
		{
			SqlSession session = super.getDBSession();
			if (session != null)
			{
				parameters.put("EmployeeId", employeeId);
				parameters.put("Year1", financialYear1.getName());
				parameters.put("Month1", 3);
				parameters.put("Year2", financialYear2.getName());
				parameters.put("Month2", 4);

				EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
				paySlips = employeeMapper.getPaySlips(parameters);
			}
			super.closeDBSession(session);
		}
		return paySlips;
	}
	
	public PaySlip getPaySlipDetails(int paySlipId, int employeeId) 
	{
		PaySlip paySlip = null;

		if (paySlipId > 0)
		{
			SqlSession session = getDBSession();

			if (session != null)
			{
				EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
				Map<String, Object> parameters = new HashMap<>();
				parameters.put("PaySlipId", paySlipId);
				parameters.put("EmployeeId", employeeId);

				paySlip = employeeMapper.getPaySlipForEmployee(parameters);
			}
			closeDBSession(session);
		}
		return paySlip;
	}
	
/*
	@Override
	public void saveEmployeeDocument(Employee employee, EmployeeDocument employeeDocument, User sessionUser, File file, String fileName) 
	{
		SqlSession session = getDBSession();
		
		if (session != null && employee != null) 
		{
			employee = this.getEmployee(employee.getUuid());
			employeeDocument.setEmployee(employee);
			EmployeeDAO employeeMapper = session.getMapper(EmployeeDAO.class);
			employeeDocument.setFile(fileName);

			if (employeeDocument.getUuid().length() <= 0) 
			{
				employeeDocument.setUuid(UUID.randomUUID().toString());
				employeeMapper.insertEmployeeDocument(employeeDocument);
			} 
			else 
			{
				employeeMapper.updateEmployeeDocument(employeeDocument);
			}
			session.commit();
			
			String documentExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			String filePath = PropertyLoader.getProperty("EMPLOYEE_DOCUMENT_UPLOAD") + File.separator +  employeeDocument.getId() + documentExtension;
			try 
			{
				FileEncryptionUtil.encryptFile(file, filePath);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		closeDBSession(session);
	}

*/
}