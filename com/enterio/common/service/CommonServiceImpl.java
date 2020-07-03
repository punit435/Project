package com.enterio.common.service;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.enterio.common.dao.CommonDAO;
import com.enterio.foundation.domain.Bank;
import com.enterio.foundation.domain.FinancialYear;
import com.enterio.foundation.domain.Month;
import com.enterio.util.MybatisUtil;

public class CommonServiceImpl implements Serializable, CommonService
{
	private static final long serialVersionUID = 7021811095482149555L;
	
	public SqlSession getDBSession()
	{
		return MybatisUtil.getSessionFactory().openSession();
	}
	
	public void closeDBSession(SqlSession dbSession)
	{
		if(dbSession != null)
		{
			dbSession.close();
		}
	}
	
	public List<Bank> getBanks()
	{
		List<Bank> bankList = null;
		
		SqlSession session = getDBSession();
		if(session != null)
		{
			CommonDAO commonMapper = session.getMapper(CommonDAO.class);
			bankList = commonMapper.getBanks();
		}
		closeDBSession(session);
		
		return bankList;
	}
	
	public List<Month> getMonth() 
	{
		List<Month> months = null;

		SqlSession session = getDBSession();
		if (session != null)
		{
			CommonDAO commonMapper = session.getMapper(CommonDAO.class);
			months = commonMapper.getMonths();
		}
		closeDBSession(session);
		
		return months;
	}

	public Month getMonthByID(int monthId)
	{
		Month month =  null;
		SqlSession session = getDBSession();

		if (session != null)
		{
			CommonDAO commonMapper = session.getMapper(CommonDAO.class);
			month = commonMapper.getMonthByID(monthId);
		}
		closeDBSession(session);

		return month;
	}

	public List<FinancialYear> getYear()
	{
		List<FinancialYear> years=  null;

		SqlSession session = getDBSession();
		if (session != null)
		{
			CommonDAO commonMapper = session.getMapper(CommonDAO.class);
			years = commonMapper.getYears();
		}
		closeDBSession(session);
		
		return years;
	}

	public FinancialYear getYearById(int financialYearsId)
	{
		FinancialYear year =  null;

		SqlSession session = getDBSession();

		if (session != null)
		{
			CommonDAO commonMapper = session.getMapper(CommonDAO.class);
			year = commonMapper.getYearById(financialYearsId);
		}
		closeDBSession(session);

		return year;
	}
}