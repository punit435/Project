package com.enterio.hrms.joiningchecklist.dao;

import java.io.Serializable;
import java.util.List;

import com.enterio.hrms.foundation.domain.JoiningChecklist;

public interface JoiningChecklistDAO extends Serializable
{
	public List<JoiningChecklist> getJoiningChecklists(String companyUUID);

	public JoiningChecklist getJoiningChecklist(String joiningChecklistUUID);

	public void insertJoiningChecklist(JoiningChecklist joiningChecklist);
	
	public void updateJoiningChecklist(JoiningChecklist joiningChecklist);
	
	public void deleteJoiningChecklist(JoiningChecklist joiningChecklist);
}