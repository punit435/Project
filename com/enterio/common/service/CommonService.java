package com.enterio.common.service;

import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.Bank;
import com.enterio.foundation.domain.FinancialYear;
import com.enterio.foundation.domain.Month;

public interface CommonService extends Serializable
{
	public List<Bank> getBanks();
	
	public List<Month> getMonth();
	
	public Month getMonthByID(int monthId);
	
	public List<FinancialYear> getYear();
	
	public FinancialYear getYearById(int financialYearsId);
}