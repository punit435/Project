package com.enterio.hrms.documenttype.web;

import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.documenttype.service.DocumentTypeService;
import com.enterio.hrms.foundation.domain.DocumentType;
import com.enterio.init.StrutsActionSupport;
import com.enterio.util.StringUtil;

public class DocumentTypeAction  extends StrutsActionSupport implements Serializable 
{
	private static final long serialVersionUID = 7516602311512398023L;
	private String documentTypeUUID;
	private List<DocumentType> documentTypes;
	private DocumentType documentType;
	private DocumentTypeService documentTypeService;

	public String documentTypeList()
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null && sessionUser.getCompany() != null)
		{
			documentTypes = getDocumentTypeService().getDocumentTypes(sessionUser.getCompany().getUuid());
		}
		
		return SUCCESS;
	}

	public String addDocumentType()
	{
		return SUCCESS;
	}

	public String editDocumentType()
	{
		documentTypeUUID = StringUtil.checkNull(documentTypeUUID);
		if(documentTypeUUID.length() > 0)
		{
			documentType = getDocumentTypeService().getDocumentType(documentTypeUUID);
		}
			
		return SUCCESS;
	}
	
	public String saveDocumentType() 
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null)
		{
			documentType = getDocumentTypeService().saveDocumentType(documentType, sessionUser);
			
			redirectURL = "DocumentTypes";
		}
		
		return SUCCESS;
	}	

	public String deleteDocumentType()
	{
		documentTypeUUID = StringUtil.checkNull(documentTypeUUID);
		if(documentTypeUUID.length() > 0)
		{
			getDocumentTypeService().deleteDocumentType(documentTypeUUID);
			redirectURL = "DocumentTypes";
		}
		return SUCCESS;
	}

	public void setDocumentTypeUUID(String documentTypeUUID) {
		this.documentTypeUUID = documentTypeUUID;
	}
	public List<DocumentType> getDocumentTypes() {
		return documentTypes;
	}
	public DocumentType getDocumentType() {
		return documentType;
	}
	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}
	public DocumentTypeService getDocumentTypeService() {
		return documentTypeService;
	}
	public void setDocumentTypeService(DocumentTypeService documentTypeService) {
		this.documentTypeService = documentTypeService;
	}
}