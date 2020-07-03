package com.enterio.hrms.policygroup.dao;

import java.io.Serializable;
import java.util.List;

import com.enterio.hrms.foundation.domain.Policy;
import com.enterio.hrms.foundation.domain.PolicyGroup;

public interface PolicyGroupDAO extends Serializable
{
	public List<PolicyGroup> getPolicyGroups(String companyUUID);
	
	public PolicyGroup getPolicyGroup(String policyGroupUUID);

	public void insertPolicyGroup(PolicyGroup policyGroup);

	public void updatePolicyGroup(PolicyGroup policyGroup);

	public void deletePolicyGroup(PolicyGroup policyGroup);

	public Policy getPolicy(String policyUUID);
	
	public void insertPolicy(Policy policy);

	public void updatePolicy(Policy policy);

	public void deletePolicy(Policy policy);
	
	public List<Policy> getPendingPolicies(String companyUUID);
}