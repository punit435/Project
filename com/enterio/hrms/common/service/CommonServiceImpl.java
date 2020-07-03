package com.enterio.hrms.common.service;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.enterio.foundation.domain.UserType;
import com.enterio.hrms.common.dao.CommonDao;
import com.enterio.hrms.foundation.domain.CandidateStatus;
import com.enterio.hrms.foundation.domain.CandidateType;
import com.enterio.hrms.foundation.domain.Gender;
import com.enterio.hrms.foundation.domain.InterviewStatus;
import com.enterio.hrms.foundation.domain.MaritalStatus;
import com.enterio.hrms.foundation.domain.State;
import com.enterio.hrms.foundation.domain.VacancyStatus;

public class CommonServiceImpl extends com.enterio.common.service.CommonServiceImpl implements Serializable, CommonService
{
	private static final long serialVersionUID = 7021811095482149555L;

	public List<UserType> getUserTypes()
	{
		List<UserType> userTypeList = null;
		
		SqlSession session = getDBSession();
		if (session != null)
		{
			CommonDao commonMapper = session.getMapper(CommonDao.class);
			userTypeList = commonMapper.getUserTypes();
		}
		closeDBSession(session);
		
		return userTypeList;
	}

	public List<CandidateType> getCandidateType()
	{
		List<CandidateType> candidateTypeList = null;
		
		SqlSession session = getDBSession();
		if (session != null)
		{
			CommonDao commonMapper = session.getMapper(CommonDao.class);
			candidateTypeList = commonMapper.getCandidateType();
		}
		closeDBSession(session);
		
		return candidateTypeList;
	}

	public List<CandidateStatus> getCandidateStatus()
	{
		List<CandidateStatus> candidateStatusList = null;
		
		SqlSession session = getDBSession();
		if (session != null)
		{
			CommonDao commonMapper = session.getMapper(CommonDao.class);
			candidateStatusList = commonMapper.getCandidateStatus();
		}
		closeDBSession(session);
		
		return candidateStatusList;
	}

	public List<VacancyStatus> getVacancyStatus()
	{
		List<VacancyStatus> vacancyStatusList = null;
		
		SqlSession session = getDBSession();
		if (session != null)
		{
			CommonDao commonMapper = session.getMapper(CommonDao.class);
			vacancyStatusList = commonMapper.getVacancyStatus();
		}
		closeDBSession(session);
		
		return vacancyStatusList;
	}

	public List<Gender> getGender()
	{
		List<Gender> genderList = null;
		
		SqlSession session = getDBSession();
		if (session != null)
		{
			CommonDao commonMapper = session.getMapper(CommonDao.class);
			genderList = commonMapper.getGender();
		}
		closeDBSession(session);
		
		return genderList;
	}

	public List<MaritalStatus> getMaritalStatus() 
	{
		List<MaritalStatus> maritalStatus = null;
		
		SqlSession session = getDBSession();
		if (session != null) 
		{
			CommonDao commonMapper = session.getMapper(CommonDao.class);
			maritalStatus = commonMapper.getMaritalStatus();
		}
		closeDBSession(session);
		
		return maritalStatus;
	}
	
	public List<State> getStates() 
	{
		List<State> states = null;
		
		SqlSession session = getDBSession();
		if (session != null)
		{
			CommonDao commonMapper = session.getMapper(CommonDao.class);
			states = commonMapper.getStates();
		}
		closeDBSession(session);
		
		return states;
	}

	public State getState(int stateId)
	{
		State state = null;
		
		if(stateId > 0)
		{
			SqlSession session = getDBSession();
			if (session != null)
			{
				CommonDao commonMapper = session.getMapper(CommonDao.class);
				state = commonMapper.getState(stateId);
			}
			closeDBSession(session);
		}
		
		return state;
	}

	public List<InterviewStatus> getInterviewStatus()
	{
		List<InterviewStatus> interviewStatusList = null;
		
		SqlSession session = getDBSession();
		if (session != null)
		{
			CommonDao commonMapper = session.getMapper(CommonDao.class);
			interviewStatusList = commonMapper.getInterviewStatus();
		}
		closeDBSession(session);
		
		return interviewStatusList;
	}
}