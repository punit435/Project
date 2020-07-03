package com.enterio.hrms.documenttype.service;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.common.service.CommonServiceImpl;
import com.enterio.hrms.documenttype.dao.DocumentTypeDAO;
import com.enterio.hrms.foundation.domain.DocumentType;
import com.enterio.util.StringUtil;

public class DocumentTypeServiceImpl extends CommonServiceImpl implements Serializable, DocumentTypeService
{
	private static final long serialVersionUID = 4665481365727563235L;

	public List<DocumentType> getDocumentTypes(String companyUUID)
	{
		List<DocumentType> documentTypeList = null;
		companyUUID = StringUtil.checkNull(companyUUID);
		
		if(companyUUID.length() > 0)
		{
			SqlSession session = getDBSession();
			if(session != null)
			{
				DocumentTypeDAO documentTypeMapper = session.getMapper(DocumentTypeDAO.class);
				documentTypeList = documentTypeMapper.getDocumentTypes(companyUUID);
			}
			closeDBSession(session);
		}
		
		return documentTypeList;
	}
	
	public DocumentType getDocumentType(String documentTypeUUID)
	{
		DocumentType documentType = null;
		documentTypeUUID = StringUtil.checkNull(documentTypeUUID);
		
		if(documentTypeUUID.length() > 0)
		{
			SqlSession session = super.getDBSession();
			if (session != null)
			{
				DocumentTypeDAO documentTypeMapper = session.getMapper(DocumentTypeDAO.class);
				documentType = documentTypeMapper.getDocumentType(documentTypeUUID);
			}
			super.closeDBSession(session);
		}
		
		return documentType;
	}
	
	public DocumentType saveDocumentType(DocumentType documentType, User createdBy)
	{
		if(documentType != null && createdBy != null && createdBy.getCompany() != null)
		{
			SqlSession session = getDBSession();
			if(session != null) 
			{
				DocumentTypeDAO documentTypeMapper = session.getMapper(DocumentTypeDAO.class);
				if(StringUtil.checkNull(documentType.getUuid()).length() <= 0) 
				{
					documentType.setUuid(UUID.randomUUID().toString());
					documentType.setCompany(createdBy.getCompany());
					documentType.setCreatedBy(createdBy);
					documentTypeMapper.insertDocumentType(documentType);
				}
				else
				{
					documentTypeMapper.updateDocumentType(documentType);
				}
				session.commit();
			}
			closeDBSession(session);
		}
		
		return documentType;
	}
	
	public boolean deleteDocumentType(String documentTypeUUID)
	{
		boolean isDocumentTypeDeleted = false;
		documentTypeUUID = StringUtil.checkNull(documentTypeUUID);
		
		if(documentTypeUUID.length() > 0)
		{
			DocumentType documentType = getDocumentType(documentTypeUUID);
			if(documentType != null)
			{
				SqlSession session = super.getDBSession();
				if (session != null)
				{
					try
					{
						DocumentTypeDAO documentTypeMapper = session.getMapper(DocumentTypeDAO.class);
						documentTypeMapper.deleteDocumentType(documentType);
						session.commit();
						isDocumentTypeDeleted = true;
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				super.closeDBSession(session);
			}
		}
		
		return isDocumentTypeDeleted;
	}
}