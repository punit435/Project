package com.enterio.hrms.policygroup.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.common.service.CommonServiceImpl;
import com.enterio.hrms.foundation.domain.Policy;
import com.enterio.hrms.foundation.domain.PolicyGroup;
import com.enterio.hrms.policygroup.dao.PolicyGroupDAO;
import com.enterio.util.DateUtil;
import com.enterio.util.FileEncryptionUtil;
import com.enterio.util.PropertyLoader;
import com.enterio.util.StringUtil;

public class PolicyGroupServiceImpl extends CommonServiceImpl implements Serializable,PolicyGroupService
{
	private static final long serialVersionUID = 1L;

	public List<PolicyGroup> getPolicyGroups(String companyUUID)
	{
		List<PolicyGroup> policyGroups = null;
		companyUUID = StringUtil.checkNull(companyUUID);
		
		if(companyUUID.length() > 0)
		{
			SqlSession session = super.getDBSession();	
			if(session != null)
			{
				PolicyGroupDAO policyGroupMapper = session.getMapper(PolicyGroupDAO.class);
				policyGroups = policyGroupMapper.getPolicyGroups(companyUUID);
			}
			super.closeDBSession(session);
		}
		
		return policyGroups;
	}

	public PolicyGroup getPolicyGroup(String policyGroupUUID) 
	{
		PolicyGroup	policyGroup = null;
		policyGroupUUID = StringUtil.checkNull(policyGroupUUID);

		if(policyGroupUUID.length() > 0)
		{
			SqlSession session = getDBSession();
			if(session != null)
			{
				PolicyGroupDAO policyGroupMapper = session.getMapper(PolicyGroupDAO.class);
				policyGroup = policyGroupMapper.getPolicyGroup(policyGroupUUID);
			}
			closeDBSession(session);
		}
		
		return policyGroup;
	}

	public PolicyGroup savePolicyGroup(PolicyGroup policyGroup, User createdBy)
	{
		if(policyGroup != null && createdBy != null)
		{
			SqlSession session = getDBSession();
			if(session != null && createdBy != null)
			{
				PolicyGroupDAO policyGroupMapper = session.getMapper(PolicyGroupDAO.class);
				if(StringUtil.checkNull(policyGroup.getUuid()).length() <= 0)
				{ 
					policyGroup.setCompany(createdBy.getCompany());
					policyGroup.setUuid(UUID.randomUUID().toString());
					policyGroup.setCreatedBy(createdBy);
					policyGroupMapper.insertPolicyGroup(policyGroup);
				}
				else
				{
					policyGroupMapper.updatePolicyGroup(policyGroup);
				}
				session.commit();
			}
			closeDBSession(session);
		}
		
		return policyGroup;
	}

	public boolean deletePolicyGroup(String policyGroupUUID)
	{
		boolean isPolicyGroupDeleted = false;
		policyGroupUUID = StringUtil.checkNull(policyGroupUUID);
		
		if(policyGroupUUID.length() > 0)
		{
			PolicyGroup policyGroup = getPolicyGroup(policyGroupUUID);
			if(policyGroup != null)
			{
				SqlSession session = super.getDBSession();
				if(session != null)
				{
					try
					{
						PolicyGroupDAO policyGroupMapper = session.getMapper(PolicyGroupDAO.class);
						policyGroupMapper.deletePolicyGroup(policyGroup);
						session.commit();
						isPolicyGroupDeleted = true;
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				super.closeDBSession(session);
			}
		}
		
		return isPolicyGroupDeleted;
	}

	public Policy getPolicy(String policyUUID) 
	{
		Policy policy = null;
		policyUUID = StringUtil.checkNull(policyUUID);

		if(policyUUID.length() > 0)
		{
			SqlSession session = getDBSession();
			if(session != null)
			{
				PolicyGroupDAO policyGroupMapper = session.getMapper(PolicyGroupDAO.class);
				policy = policyGroupMapper.getPolicy(policyUUID);
			}
			closeDBSession(session);
		}
		
		return policy;
	}

	public Policy savePolicy(Policy policy, String effectiveFrom, File file, String fileName, User createdBy)
	{
		effectiveFrom = StringUtil.checkNull(effectiveFrom);
		fileName = StringUtil.checkNull(fileName);

		if(policy != null && effectiveFrom.length() > 0 && createdBy != null)
		{
			SqlSession session = super.getDBSession();
			if(session != null)
			{
				PolicyGroupDAO policyGroupMapper = session.getMapper(PolicyGroupDAO.class);
				if(StringUtil.checkNull(policy.getUuid()).length() <= 0)
				{
					if(file != null && fileName.length() > 0)
					{
						String policyUUID = UUID.randomUUID().toString(); 
						policy.setUuid(policyUUID);
						policy.setCompany(createdBy.getCompany());
						policy.setEffectiveFrom(DateUtil.convertToDate(effectiveFrom));
						policy.setCreatedBy(createdBy);
						policy.setFile(fileName);
						policy.setRevisionNumber(1);
						policy.setGroup(getPolicyGroup(policy.getGroup().getUuid()));
						policyGroupMapper.insertPolicy(policy);
						
						String documentExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
						String filePath = PropertyLoader.getProperty("POLICY_DOCUMENT_UPLOAD") + File.separator +  policyUUID + documentExtension;
						try 
						{
							FileEncryptionUtil.encryptFile(file, filePath);
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
						}
					}
				}
				else
				{
					policy.setEffectiveFrom(DateUtil.convertToDate(effectiveFrom));
					policyGroupMapper.updatePolicy(policy);
				}
				session.commit();
			}
			super.closeDBSession(session);
		}
		
		return policy;
	}
	
	public boolean deletePolicy(String policyUUID)
	{
		boolean isPolicyDeleted = false;
		policyUUID = StringUtil.checkNull(policyUUID);
		
		if(policyUUID.length() > 0)
		{
			Policy policy = getPolicy(policyUUID);
			if(policy != null)
			{
				SqlSession session = super.getDBSession();
				if (session != null)
				{
					try
					{
						PolicyGroupDAO policyGroupMapper = session.getMapper(PolicyGroupDAO.class);
						policyGroupMapper.deletePolicy(policy);
						session.commit();
						isPolicyDeleted = true;
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				super.closeDBSession(session);
			}
		}
		
		return isPolicyDeleted;
	}
	
	public List<Policy> getPendingPolicies(String companyUUID, String userUUID)
	{
		List<Policy> policyList = null;
		companyUUID = StringUtil.checkNull(companyUUID);
		userUUID = StringUtil.checkNull(userUUID);
		
		if(companyUUID.length() > 0 && userUUID.length() > 0)
		{
			SqlSession session = super.getDBSession();	
			if(session != null)
			{
				PolicyGroupDAO policyGroupMapper = session.getMapper(PolicyGroupDAO.class);
				policyList = policyGroupMapper.getPendingPolicies(companyUUID);
			}
			super.closeDBSession(session);
		}
		
		return policyList;
	}
	
	public List<Policy> getCompletedPolicies(String companyUUID, String userUUID)
	{
		List<Policy> policyList = null;
		companyUUID = StringUtil.checkNull(companyUUID);
		userUUID = StringUtil.checkNull(userUUID);
		
		if(companyUUID.length() > 0 && userUUID.length() > 0)
		{
			SqlSession session = super.getDBSession();	
			if(session != null)
			{
				PolicyGroupDAO policyGroupMapper = session.getMapper(PolicyGroupDAO.class);
				policyList = policyGroupMapper.getPendingPolicies(companyUUID);
			}
			super.closeDBSession(session);
		}
		
		return policyList;
	}
}