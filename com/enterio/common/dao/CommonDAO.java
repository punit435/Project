package com.enterio.common.dao;

import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.Bank;
import com.enterio.foundation.domain.FinancialYear;
import com.enterio.foundation.domain.Month;

public interface CommonDAO extends Serializable
{
	public List<Bank> getBanks();
	
	public List<Month> getMonths();

	public List<FinancialYear> getYears();

	public Month getMonthByID(int monthId);

	public FinancialYear getYearById(int financialYearsId);
}