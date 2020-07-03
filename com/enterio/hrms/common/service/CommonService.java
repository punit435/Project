package com.enterio.hrms.common.service;

import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.UserType;
import com.enterio.hrms.foundation.domain.CandidateStatus;
import com.enterio.hrms.foundation.domain.CandidateType;
import com.enterio.hrms.foundation.domain.Gender;
import com.enterio.hrms.foundation.domain.InterviewStatus;
import com.enterio.hrms.foundation.domain.MaritalStatus;
import com.enterio.hrms.foundation.domain.State;
import com.enterio.hrms.foundation.domain.VacancyStatus;

public interface CommonService extends Serializable
{
	public List<UserType> getUserTypes();

	public List<CandidateType> getCandidateType();

	public List<CandidateStatus> getCandidateStatus();

	public List<VacancyStatus> getVacancyStatus();

	public List<Gender> getGender();

	public List<MaritalStatus> getMaritalStatus();

	public List<State> getStates();

	public State getState(int stateId);

	List<InterviewStatus> getInterviewStatus();
}