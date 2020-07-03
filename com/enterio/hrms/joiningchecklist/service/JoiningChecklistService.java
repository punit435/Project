package com.enterio.hrms.joiningchecklist.service;

import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.foundation.domain.JoiningChecklist;

public interface JoiningChecklistService extends Serializable
{
	public List<JoiningChecklist> getJoiningChecklists(String companyUUID);

	public JoiningChecklist getJoiningChecklist(String joiningChecklistUUID);

	public JoiningChecklist saveJoiningChecklist(JoiningChecklist joiningChecklist, User createdBy);
	
	public boolean deleteJoiningChecklist(String joiningChecklistUUID);
}