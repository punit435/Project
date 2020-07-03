package com.enterio.hrms.documenttype.service;

import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.foundation.domain.DocumentType;

public interface DocumentTypeService extends Serializable
{
	public List<DocumentType> getDocumentTypes(String companyUUID);
	
	public DocumentType getDocumentType(String documentTypeUUID);
	
	public DocumentType saveDocumentType(DocumentType documentType, User createdBy);
	
	public boolean deleteDocumentType(String documentTypeUUID);
}