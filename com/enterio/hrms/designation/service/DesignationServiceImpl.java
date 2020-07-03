package com.enterio.hrms.designation.service;

import java.io.Serializable;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.common.service.CommonServiceImpl;
import com.enterio.hrms.department.service.DepartmentService;
import com.enterio.hrms.designation.dao.DesignationDAO;
import com.enterio.hrms.foundation.domain.Designation;
import com.enterio.util.StringUtil;

public class DesignationServiceImpl extends CommonServiceImpl implements Serializable, DesignationService
{
	private static final long serialVersionUID = -1848204956538455274L;
	private DepartmentService departmentService;
	
	public DepartmentService getDepartmentService() {
		return departmentService;
	}
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public Designation getDesignation(String designationUUID) 
	{
		Designation designation = null;
		designationUUID = StringUtil.checkNull(designationUUID);
	
		if(designationUUID.length() > 0)
		{
			SqlSession session = getDBSession();
			if(session != null)
			{
				DesignationDAO designationMapper = session.getMapper(DesignationDAO.class);
				designation = designationMapper.getDesignation(designationUUID);
			}
			closeDBSession(session);
		}
		
		return designation;
	}
	
	public Designation saveDesignation(Designation designation, User createdBy) 
	{
		if(designation != null && createdBy != null)
		{
			SqlSession session = getDBSession();
			if(session != null)
			{
				DesignationDAO designationMapper = session.getMapper(DesignationDAO.class);
				if(StringUtil.checkNull(designation.getUuid()).length() <= 0) 
				{
					designation.setUuid(UUID.randomUUID().toString());
					designation.setDepartment(getDepartmentService().getDepartment(designation.getDepartment().getUuid()));
					designationMapper.insertDesignation(designation);
				}
				else
				{
					designationMapper.updateDesignation(designation);
				}
				session.commit();
			}
			closeDBSession(session);
		}
		
		return designation;
	}

	public boolean deleteDesignation(String designationUUID) 
	{
		boolean isDesignationDeleted = false;
		designationUUID = StringUtil.checkNull(designationUUID);
		
		if(designationUUID.length() > 0)
		{
			Designation designation = getDesignation(designationUUID);
			if(designation != null)
			{
				SqlSession session = super.getDBSession();
				if (session != null)
				{
					try
					{
						DesignationDAO designationMapper = session.getMapper(DesignationDAO.class);
						designationMapper.deleteDesignation(designation);
						session.commit();
						isDesignationDeleted = true;
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				super.closeDBSession(session);
			}
		}
		
		return isDesignationDeleted;
	}
}