package com.enterio.hrms.joiningchecklist.web;

import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.foundation.domain.JoiningChecklist;
import com.enterio.hrms.joiningchecklist.service.JoiningChecklistService;
import com.enterio.init.StrutsActionSupport;
import com.enterio.util.StringUtil;

public class JoiningChecklistAction extends StrutsActionSupport implements Serializable
{
	private static final long serialVersionUID = 892053067297383280L;
	private String joiningChecklistUUID;
	private List<JoiningChecklist> joiningChecklists;
	private JoiningChecklist joiningChecklist;
	private JoiningChecklistService joiningChecklistService;

	public String joiningChecklistList() 
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null && sessionUser.getCompany() != null)
		{
			joiningChecklists = getJoiningChecklistService().getJoiningChecklists(sessionUser.getCompany().getUuid());
		}

		return SUCCESS;
	}

	public String addJoiningChecklist() 
	{
		return SUCCESS;
	}

	public String editJoiningChecklist() 
	{
		joiningChecklistUUID = StringUtil.checkNull(joiningChecklistUUID);
		
		User sessionUser = super.getSessionUser();
		if(joiningChecklistUUID.length() > 0 && sessionUser != null)
		{
			joiningChecklist = getJoiningChecklistService().getJoiningChecklist(joiningChecklistUUID);
		}

		return SUCCESS;
	}
	
	public String saveJoiningChecklist() 
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null)
		{
			getJoiningChecklistService().saveJoiningChecklist(joiningChecklist, sessionUser);
			redirectURL = "JoiningChecklists";
		}
	
		return SUCCESS;
	}

	public String deleteJoiningChecklist() 
	{
		if(joiningChecklistUUID.length() > 0)
		{
			getJoiningChecklistService().deleteJoiningChecklist(joiningChecklistUUID);
	
			redirectURL = "JoiningChecklists";
		}
		
		return SUCCESS;
	}

	public void setJoiningChecklistUUID(String joiningChecklistUUID) {
		this.joiningChecklistUUID = joiningChecklistUUID;
	}
	public List<JoiningChecklist> getJoiningChecklists() {
		return joiningChecklists;
	}
	public JoiningChecklist getJoiningChecklist() {
		return joiningChecklist;
	}
	public void setJoiningChecklist(JoiningChecklist joiningChecklist) {
		this.joiningChecklist = joiningChecklist;
	}
	public JoiningChecklistService getJoiningChecklistService() {
		return joiningChecklistService;
	}
	public void setJoiningChecklistService(JoiningChecklistService joiningChecklistService) {
		this.joiningChecklistService = joiningChecklistService;
	}
}