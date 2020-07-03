package com.enterio.hrms.employee.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.documenttype.service.DocumentTypeService;
import com.enterio.hrms.employee.service.EmployeeService;
import com.enterio.hrms.foundation.domain.DocumentType;
import com.enterio.hrms.foundation.domain.Employee;
import com.enterio.hrms.foundation.domain.EmployeeDocument;
import com.enterio.init.StrutsActionSupport;
import com.enterio.util.FileEncryptionUtil;
import com.enterio.util.PropertyLoader;
import com.enterio.util.StringUtil;

public class DocumentAction extends StrutsActionSupport implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private String documentUUID;
	private String employeeUUID;
	private String[] documentTypeId;
	private File[] documentFile;
	private String[] documentFileFileName;
	private String[] documentNumber;
	private Employee employeeObj;
	private List<DocumentType> documentTypes;
	private EmployeeService employeeService;
	private DocumentTypeService documentTypeService;

	public String uploadDocument()
	{
		employeeUUID = StringUtil.checkNull(employeeUUID);
		User sessionUser = super.getSessionUser();
		
		if(employeeUUID != null && employeeUUID.length() > 0 && sessionUser != null)
		{
			employeeObj = getEmployeeService().getEmployee(employeeUUID);
			if(employeeObj != null)
			{
				documentTypes = getEmployeeService().getPendingDocumentTypes(sessionUser.getCompany().getId(), employeeUUID);
			}
		}
		
		return SUCCESS;
	}
	
	public String saveDocument()
	{
		employeeUUID = StringUtil.checkNull(employeeUUID);
		User sessionUser = super.getSessionUser();
		
		if(employeeUUID != null && employeeUUID.length() > 0 && sessionUser != null)
		{
			if(documentTypeId != null && documentTypeId.length > 0)
			{
				getEmployeeService().saveDocument(employeeUUID, documentTypeId, documentFile, documentFileFileName, documentNumber, sessionUser);
				redirectURL = "../Details/" + employeeUUID;
			}
		}
		
		return SUCCESS;
	}
	
	public String deleteDocument()
	{
		employeeUUID = StringUtil.checkNull(employeeUUID);
		documentUUID = StringUtil.checkNull(documentUUID);
		
		if(employeeUUID.length() > 0 && documentUUID.length() > 0)
		{
			boolean isDocumentDeleted = getEmployeeService().deleteDocument(documentUUID);
			if(isDocumentDeleted)
			{
				redirectURL = "../Details/" + employeeUUID;
			}
		}
		
		return SUCCESS;
	}
	
	public String downloadDocument() throws Exception
	{
		documentUUID = StringUtil.checkNull(documentUUID);
		
		if(documentUUID.length() > 0)
		{
			EmployeeDocument document = getEmployeeService().getDocument(documentUUID);
			if(document != null)
			{
				String documentExtension = document.getFile().substring(document.getFile().lastIndexOf("."), document.getFile().length());
				String filePath = PropertyLoader.getProperty("EMPLOYEE_DOCUMENT_UPLOAD") + File.separator +  document.getUuid() + documentExtension;
				
				File file = new File(filePath);
				if(file != null && file.exists())
				{
					byte[] data = FileEncryptionUtil.decryptFile(file); 
					if(data != null && data.length > 0)
					{
						String mimeType = "";
						mimeType = servletContext.getMimeType(file.getAbsolutePath());					

						response.reset();
						response.setContentType(mimeType);
						response.setHeader("Content-Disposition" ,"attachment; filename=" + document.getFile());

						ByteArrayOutputStream baos = new ByteArrayOutputStream();
					    baos.write(data);
						
					    OutputStream out = response.getOutputStream();
					    baos.writeTo(out);
						baos.close();
						out.flush();
						out.close();
					}
				}
			}
		}
		
		return NONE;
	}

	public void setDocumentUUID(String documentUUID) {
		this.documentUUID = documentUUID;
	}
	public void setEmployeeUUID(String employeeUUID) {
		this.employeeUUID = employeeUUID;
	}
	public void setDocumentTypeId(String[] documentTypeId) {
		this.documentTypeId = documentTypeId;
	}
	public void setDocumentFile(File[] documentFile) {
		this.documentFile = documentFile;
	}
	public void setDocumentFileFileName(String[] documentFileFileName) {
		this.documentFileFileName = documentFileFileName;
	}
	public void setDocumentNumber(String[] documentNumber) {
		this.documentNumber = documentNumber;
	}
	public Employee getEmployeeObj() {
		return employeeObj;
	}
	public List<DocumentType> getDocumentTypes() {
		return documentTypes;
	}
	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	public DocumentTypeService getDocumentTypeService() {
		return documentTypeService;
	}
	public void setDocumentTypeService(DocumentTypeService documentTypeService) {
		this.documentTypeService = documentTypeService;
	}
}