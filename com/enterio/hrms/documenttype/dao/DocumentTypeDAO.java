package com.enterio.hrms.documenttype.dao;

import java.io.Serializable;
import java.util.List;

import com.enterio.hrms.foundation.domain.DocumentType;

public interface DocumentTypeDAO extends Serializable
{
	public List<DocumentType> getDocumentTypes(String companyUUID);
	
	public DocumentType getDocumentType(String documentTypeUUID);
	
	public void insertDocumentType(DocumentType documentType);
	
	public void updateDocumentType(DocumentType documentType);
	
	public void deleteDocumentType(DocumentType documentType);
}