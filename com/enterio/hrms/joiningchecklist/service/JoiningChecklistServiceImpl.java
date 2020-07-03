package com.enterio.hrms.joiningchecklist.service;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.common.service.CommonServiceImpl;
import com.enterio.hrms.foundation.domain.JoiningChecklist;
import com.enterio.hrms.joiningchecklist.dao.JoiningChecklistDAO;
import com.enterio.util.StringUtil;

public class JoiningChecklistServiceImpl extends CommonServiceImpl implements Serializable, JoiningChecklistService
{
	private static final long serialVersionUID = 6724631639165245548L;

	public List<JoiningChecklist> getJoiningChecklists(String companyUUID)
	{
		List<JoiningChecklist> joiningCheckList = null;
		companyUUID = StringUtil.checkNull(companyUUID);
		
		if(companyUUID.length() > 0)
		{
			SqlSession session = getDBSession();
			if(session != null)
			{
				JoiningChecklistDAO joiningChecklistMapper = session.getMapper(JoiningChecklistDAO.class);
				joiningCheckList = joiningChecklistMapper.getJoiningChecklists(companyUUID);
			}
			closeDBSession(session);
		}
		
		return joiningCheckList;
	}
	
	public JoiningChecklist getJoiningChecklist(String joiningChecklistUUID) 
	{
		JoiningChecklist joiningChecklist = null;
		joiningChecklistUUID = StringUtil.checkNull(joiningChecklistUUID);
		
		if(joiningChecklistUUID.length() > 0)
		{
			SqlSession session = super.getDBSession();
			if (session != null)
			{
				JoiningChecklistDAO joiningChecklistMapper = session.getMapper(JoiningChecklistDAO.class);
				joiningChecklist = joiningChecklistMapper.getJoiningChecklist(joiningChecklistUUID);
			}
			super.closeDBSession(session);
		}
		
		return joiningChecklist;
	}

	public JoiningChecklist saveJoiningChecklist(JoiningChecklist joiningChecklist, User createdBy) 
	{
		if(joiningChecklist != null && createdBy != null && createdBy.getCompany() != null)
		{
			SqlSession session = getDBSession();
			if(session != null) 
			{
				JoiningChecklistDAO joiningChecklistMapper = session.getMapper(JoiningChecklistDAO.class);
				if(StringUtil.checkNull(joiningChecklist.getUuid()).length() <= 0) 
				{
					joiningChecklist.setUuid(UUID.randomUUID().toString());
					joiningChecklist.setCompany(createdBy.getCompany());
					joiningChecklistMapper.insertJoiningChecklist(joiningChecklist);
				}
				else
				{
					joiningChecklistMapper.updateJoiningChecklist(joiningChecklist);
				}
				session.commit();
			}
			closeDBSession(session);
		}
		
		return joiningChecklist;
	}

	public boolean deleteJoiningChecklist(String joiningChecklistUUID)
	{
		boolean isJoiningChecklistDeleted = false;
		joiningChecklistUUID = StringUtil.checkNull(joiningChecklistUUID);
		
		if(joiningChecklistUUID.length() > 0)
		{
			JoiningChecklist joiningChecklist = getJoiningChecklist(joiningChecklistUUID);
			if(joiningChecklist != null)
			{
				SqlSession session = super.getDBSession();
				if (session != null)
				{
					try
					{
						JoiningChecklistDAO joiningChecklistMapper = session.getMapper(JoiningChecklistDAO.class);
						joiningChecklistMapper.deleteJoiningChecklist(joiningChecklist);
						session.commit();
						isJoiningChecklistDeleted = true;
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				super.closeDBSession(session);
			}
		}
		
		return isJoiningChecklistDeleted;
	}
}