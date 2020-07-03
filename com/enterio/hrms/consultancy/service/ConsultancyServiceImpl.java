package com.enterio.hrms.consultancy.service;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.common.service.CommonServiceImpl;
import com.enterio.hrms.consultancy.dao.ConsultancyDAO;
import com.enterio.hrms.foundation.domain.Consultancy;
import com.enterio.hrms.foundation.domain.ConsultancyContact;
import com.enterio.util.StringUtil;

public class ConsultancyServiceImpl extends CommonServiceImpl implements Serializable, ConsultancyService
{
	private static final long serialVersionUID = -1409959852942236746L;

	public List<Consultancy> getConsultancies(String companyUUID)
	{
		List<Consultancy> consultancies = null;
		companyUUID = StringUtil.checkNull(companyUUID);
		
		if(companyUUID.length() > 0)
		{
			SqlSession session = super.getDBSession();	
			if (session != null)
			{
				ConsultancyDAO consultancyMapper = session.getMapper(ConsultancyDAO.class);
				consultancies = consultancyMapper.getConsultancies(companyUUID);
			}
			super.closeDBSession(session);
		}
		
		return consultancies;
	}

	public Consultancy getConsultancy(String consultancyUUID)
	{
		Consultancy	consultancy = null;
		consultancyUUID = StringUtil.checkNull(consultancyUUID);

		if(consultancyUUID.length() > 0)
		{
			SqlSession session = getDBSession();
			if(session != null)
			{
				ConsultancyDAO consultancyMapper = session.getMapper(ConsultancyDAO.class);
				consultancy = consultancyMapper.getConsultancy(consultancyUUID);
			}
			closeDBSession(session);
		}
		
		return consultancy;
	}

	public Consultancy saveConsultancy(Consultancy consultancy, User createdBy)
	{
		if(consultancy != null && createdBy != null)
		{
			SqlSession session = getDBSession();
			if(session != null && createdBy != null)
			{
				ConsultancyDAO consultancyMapper = session.getMapper(ConsultancyDAO.class);
				if(StringUtil.checkNull(consultancy.getUuid()).length() <= 0)
				{ 
					consultancy.setCompany(createdBy.getCompany());
					consultancy.setUuid(UUID.randomUUID().toString());
					consultancy.setCreatedBy(createdBy);
					consultancyMapper.insertConsultancy(consultancy);
				}
				else
				{
					consultancyMapper.updateConsultancy(consultancy);
				}
				session.commit();
			}
			closeDBSession(session);
		}
		
		return consultancy;
	}

	public boolean deleteConsultancy(String consultancyUUID)
	{
		boolean isConsultancyDeleted = false;
		consultancyUUID = StringUtil.checkNull(consultancyUUID);
		
		if(consultancyUUID.length() > 0)
		{
			Consultancy consultancy = getConsultancy(consultancyUUID);
			if(consultancy != null)
			{
				SqlSession session = super.getDBSession();
				if (session != null)
				{
					try
					{
						ConsultancyDAO consultancyMapper = session.getMapper(ConsultancyDAO.class);
						consultancyMapper.deleteConsultancy(consultancy);
						session.commit();
						isConsultancyDeleted = true;
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				super.closeDBSession(session);
			}
		}
		
		return isConsultancyDeleted;
	}
	
	public ConsultancyContact getContact(String contactUUID)
	{
		ConsultancyContact contact = null;
		contactUUID = StringUtil.checkNull(contactUUID);
	
		if(contactUUID.length() > 0)
		{
			SqlSession session = getDBSession();
			if(session != null)
			{
				ConsultancyDAO consultancyMapper = session.getMapper(ConsultancyDAO.class);
				contact = consultancyMapper.getContact(contactUUID);
			}
			closeDBSession(session);
		}
		
		return contact;
	}

	public ConsultancyContact saveContact(ConsultancyContact contact, User createdBy)
	{
		if(contact != null && createdBy != null)
		{
			SqlSession session = getDBSession();
			if(session != null)
			{
				ConsultancyDAO consultancyMapper = session.getMapper(ConsultancyDAO.class);
				if(StringUtil.checkNull(contact.getUuid()).length() <= 0) 
				{
					contact.setUuid(UUID.randomUUID().toString());
					contact.setConsultancy(getConsultancy(contact.getConsultancy().getUuid()));
					consultancyMapper.insertContact(contact);
				}
				else
				{
					consultancyMapper.updateContact(contact);
				}
				session.commit();
			}
			closeDBSession(session);
		}
		
		return contact;
	}

	public boolean deleteContact(String contactUUID)
	{
		boolean isContactDeleted = false;
		contactUUID = StringUtil.checkNull(contactUUID);
		
		if(contactUUID.length() > 0)
		{
			ConsultancyContact contact = getContact(contactUUID);
			if(contact != null)
			{
				SqlSession session = super.getDBSession();
				if (session != null)
				{
					try
					{
						ConsultancyDAO consultancyMapper = session.getMapper(ConsultancyDAO.class);
						consultancyMapper.deleteContact(contact);
						session.commit();
						isContactDeleted = true;
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				super.closeDBSession(session);
			}
		}
		
		return isContactDeleted;
	}
}