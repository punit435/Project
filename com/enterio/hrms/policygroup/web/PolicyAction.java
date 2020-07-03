package com.enterio.hrms.policygroup.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.foundation.domain.Policy;
import com.enterio.hrms.foundation.domain.PolicyGroup;
import com.enterio.hrms.policygroup.service.PolicyGroupService;
import com.enterio.init.StrutsActionSupport;
import com.enterio.util.DateUtil;
import com.enterio.util.FileEncryptionUtil;
import com.enterio.util.PropertyLoader;
import com.enterio.util.StringUtil;

public class PolicyAction extends StrutsActionSupport implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String policyGroupUUID;
	private String policyUUID;
	private String effectiveFromInput;
	private List<PolicyGroup> policyGroups;
	private List<Policy> policies;
	private PolicyGroup policyGroupObj;
	private Policy policy;
	private File file;
	private String fileFileName;
	private PolicyGroupService policyGroupService;
	
	public String policyDetails()
	{
		policyUUID = StringUtil.checkNull(policyUUID);
		
		if(policyUUID.length() > 0)
		{
			policy = getPolicyGroupService().getPolicy(policyUUID);
		}
		
		return SUCCESS;
	}
	
	public String addPolicy()
	{
		policyGroupUUID = StringUtil.checkNull(policyGroupUUID);
		if(policyGroupUUID.length() > 0)
		{
			policyGroupObj = getPolicyGroupService().getPolicyGroup(policyGroupUUID);
		}
		
		return SUCCESS;
	}
	
	public String editPolicy()
	{
		policyGroupUUID = StringUtil.checkNull(policyGroupUUID);
		policyUUID = StringUtil.checkNull(policyUUID);
		
		if(policyGroupUUID.length() > 0 && policyUUID.length() > 0)
		{
			policyGroupObj = getPolicyGroupService().getPolicyGroup(policyGroupUUID);
			policy = getPolicyGroupService().getPolicy(policyUUID);
			if(policy != null && policy.getEffectiveFrom() != null)
			{
				effectiveFromInput = DateUtil.convertToDateString(policy.getEffectiveFrom());
			}
		}
		
		return SUCCESS;
	}
	
	public String savePolicy()
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null && policy != null)
		{
			policy = getPolicyGroupService().savePolicy(policy, effectiveFromInput, file, fileFileName, sessionUser);
			redirectURL =  "../PolicyGroup/Details/" + policy.getGroup().getUuid();
		}
				
		return SUCCESS;
	}
	
	public String deletePolicy() 
	{
		policyGroupUUID = StringUtil.checkNull(policyGroupUUID);
		policyUUID = StringUtil.checkNull(policyUUID);
		
		if(policyGroupUUID.length() > 0 && policyUUID.length() > 0)
		{
			getPolicyGroupService().deletePolicy(policyUUID);
			redirectURL = "../PolicyGroup/Details/" + policyGroupUUID;
		}
		
		return SUCCESS;
	}

	public String viewDocument() throws Exception
	{
		policyUUID = StringUtil.checkNull(policyUUID);
		
		if(policyUUID.length() > 0)
		{
			policy = getPolicyGroupService().getPolicy(policyUUID);
			if(policy != null)
			{
				byte[] data = getDocumentDataForPolicy(policy);
				if(data != null && data.length > 0)
				{
					response.reset();
					response.addHeader("Content-Disposition", "inline; filename=" + policy.getFile());
					
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					baos.write(data);
					
					OutputStream out = response.getOutputStream();
					baos.writeTo(out);
					baos.close();
					out.flush();
					out.close();
				}
			}
			
			return NONE;
		}
		
		return SUCCESS;
	}
	
	private byte[] getDocumentDataForPolicy(Policy policy) throws Exception
	{
		byte[] fileData = null;
		if(policy != null)
		{
			File file = getDocumentFileForPolicy(policy);
			if(file != null && file.exists())
			{
				fileData = FileEncryptionUtil.decryptFile(file);
			}
		}
		
		return fileData;
	}
	
	private File getDocumentFileForPolicy(Policy policy)
	{
		File file = null;
		if(policy != null)
		{
			String fileName = StringUtil.checkNull(policy.getFile());
			if(fileName.length() > 0)
			{
				String documentExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
				String filePath = PropertyLoader.getProperty("POLICY_DOCUMENT_UPLOAD") + File.separator +  policyUUID + documentExtension;
				file = new File(filePath);
			}
		}
		
		return file;
	}
	
	public String pendingPolicies()
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null && sessionUser.getCompany() != null)
		{
			policies = getPolicyGroupService().getPendingPolicies(sessionUser.getCompany().getUuid(), sessionUser.getUuid());
		}
		
		return SUCCESS;
	}
	
	public String completedPolicies()
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null)
		{
			
		}
		
		return SUCCESS;
	}
	
	public void setPolicyGroupUUID(String policyGroupUUID) {
		this.policyGroupUUID = policyGroupUUID;
	}
	public void setPolicyUUID(String policyUUID) {
		this.policyUUID = policyUUID;
	}
	public String getEffectiveFromInput() {
		return effectiveFromInput;
	}
	public void setEffectiveFromInput(String effectiveFromInput) {
		this.effectiveFromInput = effectiveFromInput;
	}
	public List<PolicyGroup> getPolicyGroups() {
		return policyGroups;
	}
	public List<Policy> getPolicies() {
		return policies;
	}
	public PolicyGroup getPolicyGroupObj() {
		return policyGroupObj;
	}
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public PolicyGroupService getPolicyGroupService() {
		return policyGroupService;
	}
	public void setPolicyGroupService(PolicyGroupService policyGroupService) {
		this.policyGroupService = policyGroupService;
	}
}