package com.enterio.hrms.department.service;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.common.service.CommonServiceImpl;
import com.enterio.hrms.department.dao.DepartmentDAO;
import com.enterio.hrms.foundation.domain.Department;
import com.enterio.util.StringUtil;

public class DepartmentServiceImpl extends CommonServiceImpl implements Serializable, DepartmentService
{
	private static final long serialVersionUID = 6724631639165245548L;

	public List<Department> getDepartments(String companyUUID)
	{
		List<Department> departmentList = null;
		companyUUID = StringUtil.checkNull(companyUUID);
		
		if(companyUUID.length() > 0)
		{
			SqlSession session = getDBSession();
			if(session != null)
			{
				DepartmentDAO departmentMapper = session.getMapper(DepartmentDAO.class);
				departmentList = departmentMapper.getDepartments(companyUUID);
			}
			closeDBSession(session);
		}
		
		return departmentList;
	}
	
	public Department getDepartment(String departmentUUID) 
	{
		Department department = null;
		departmentUUID = StringUtil.checkNull(departmentUUID);
		
		if(departmentUUID.length() > 0)
		{
			SqlSession session = super.getDBSession();
			if (session != null)
			{
				DepartmentDAO departmentMapper = session.getMapper(DepartmentDAO.class);
				department = departmentMapper.getDepartment(departmentUUID);
			}
			super.closeDBSession(session);
		}
		
		return department;
	}

	public Department saveDepartment(Department department, User createdBy) 
	{
		if(department != null && createdBy != null && createdBy.getCompany() != null)
		{
			SqlSession session = getDBSession();
			if(session != null) 
			{
				DepartmentDAO departmentMapper = session.getMapper(DepartmentDAO.class);
				if(StringUtil.checkNull(department.getUuid()).length() <= 0) 
				{
					department.setUuid(UUID.randomUUID().toString());
					department.setCompany(createdBy.getCompany());
					departmentMapper.insertDepartment(department);
				}
				else
				{
					departmentMapper.updateDepartment(department);
				}
				session.commit();
			}
			closeDBSession(session);
		}
		
		return department;
	}

	public boolean deleteDepartment(String departmentUUID)
	{
		boolean isDepartmentDeleted = false;
		departmentUUID = StringUtil.checkNull(departmentUUID);
		
		if(departmentUUID.length() > 0)
		{
			Department department = getDepartment(departmentUUID);
			if(department != null)
			{
				SqlSession session = super.getDBSession();
				if (session != null)
				{
					try
					{
						DepartmentDAO departmentMapper = session.getMapper(DepartmentDAO.class);
						departmentMapper.deleteDepartment(department);
						session.commit();
						isDepartmentDeleted = true;
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				super.closeDBSession(session);
			}
		}
		
		return isDepartmentDeleted;
	}
}