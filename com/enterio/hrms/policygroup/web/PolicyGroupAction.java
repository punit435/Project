package com.enterio.hrms.policygroup.web;

import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.foundation.domain.PolicyGroup;
import com.enterio.hrms.policygroup.service.PolicyGroupService;
import com.enterio.init.StrutsActionSupport;
import com.enterio.util.StringUtil;

public class PolicyGroupAction extends StrutsActionSupport implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String policyGroupUUID;
	private List<PolicyGroup> policyGroups;
	private PolicyGroup policyGroup;
	private PolicyGroupService policyGroupService;
	
	public String policyGroupList()
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null && sessionUser.getCompany() != null)
		{
			policyGroups = getPolicyGroupService().getPolicyGroups(sessionUser.getCompany().getUuid());
		}
		
		return SUCCESS;
	}
	
	public String policyGroupDetails()
	{
		policyGroupUUID = StringUtil.checkNull(policyGroupUUID);
		if(policyGroupUUID.length() > 0)
		{
			policyGroup = policyGroupService.getPolicyGroup(policyGroupUUID);
		}
		
		return SUCCESS;
	}

	public String addPolicyGroup()
	{
		return SUCCESS;
	}
	
	public String editPolicyGroup()
	{
		policyGroupUUID = StringUtil.checkNull(policyGroupUUID);
		if(policyGroupUUID.length() > 0)
		{
			policyGroup = getPolicyGroupService().getPolicyGroup(policyGroupUUID);
		}
		
		return SUCCESS;
	}
	
	public String savePolicyGroup() 
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null)
		{
			policyGroup = getPolicyGroupService().savePolicyGroup(policyGroup, sessionUser);
			redirectURL = "PolicyGroups";
		}
		
		return SUCCESS;
	}
	
	public String deletePolicyGroup()
	{
		policyGroupUUID = StringUtil.checkNull(policyGroupUUID);
		if(policyGroupUUID.length() > 0)
		{
			getPolicyGroupService().deletePolicyGroup(policyGroupUUID);
			redirectURL = "PolicyGroups";
		}
		
		return SUCCESS;
	}
	
	public void setPolicyGroupUUID(String policyGroupUUID) {
		this.policyGroupUUID = policyGroupUUID;
	}
	public List<PolicyGroup> getPolicyGroups() {
		return policyGroups;
	}
	public PolicyGroup getPolicyGroup() {
		return policyGroup;
	}
	public void setPolicyGroup(PolicyGroup policyGroup) {
		this.policyGroup = policyGroup;
	}
	public PolicyGroupService getPolicyGroupService() {
		return policyGroupService;
	}
	public void setPolicyGroupService(PolicyGroupService policyGroupService) {
		this.policyGroupService = policyGroupService;
	}
}