package com.enterio.hrms.policygroup.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.foundation.domain.Policy;
import com.enterio.hrms.foundation.domain.PolicyGroup;

public interface PolicyGroupService extends Serializable
{
	public List<PolicyGroup> getPolicyGroups(String companyUUID);

	public PolicyGroup getPolicyGroup(String policyGroupUUID);

	public PolicyGroup savePolicyGroup(PolicyGroup policyGroup, User createdBy);

	public boolean deletePolicyGroup(String policyGroupUUID);

	public Policy getPolicy(String policyUUID);
	
	public Policy savePolicy(Policy policy, String effectiveFrom, File file, String fileName, User createdBy);

	public boolean deletePolicy(String policyUUID);
	
	public List<Policy> getPendingPolicies(String companyUUID, String userUUID);
	
	public List<Policy> getCompletedPolicies(String companyUUID, String userUUID);
}