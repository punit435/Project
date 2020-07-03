package com.enterio.hrms.consultancy.web;

import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.consultancy.service.ConsultancyService;
import com.enterio.hrms.foundation.domain.Consultancy;
import com.enterio.init.StrutsActionSupport;
import com.enterio.util.StringUtil;

public class ConsultancyAction extends StrutsActionSupport implements Serializable
{
	private static final long serialVersionUID = -4387407585634954585L;
	private String consultancyUUID;
	private List<Consultancy> consultancies;
	private Consultancy consultancy;
	private ConsultancyService consultancyService;

	public String consultancyList() 
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null && sessionUser.getCompany() != null)
		{
			consultancies = getConsultancyService().getConsultancies(sessionUser.getCompany().getUuid());
		}

		return SUCCESS;
	}

	public String consultancyDetails()
	{
		consultancyUUID = StringUtil.checkNull(consultancyUUID);
		if(consultancyUUID.length() > 0)
		{
			consultancy = getConsultancyService().getConsultancy(consultancyUUID);
		}
		
		return SUCCESS;
	}
	
	public String addConsultancy() 
	{
		return SUCCESS;
	}
	
	public String editConsultancy() 
	{
		consultancyUUID = StringUtil.checkNull(consultancyUUID);
		if(consultancyUUID.length() > 0)
		{
			consultancy = getConsultancyService().getConsultancy(consultancyUUID);
		}

		return SUCCESS;
	}

	public String saveConsultancy() 
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null)
		{
			consultancy = getConsultancyService().saveConsultancy(consultancy, sessionUser);
			redirectURL = "Consultancies";
		}

		return SUCCESS;
	}

	public String deleteConsultancy() 
	{
		consultancyUUID = StringUtil.checkNull(consultancyUUID);
		if(consultancyUUID.length() > 0)
		{
			getConsultancyService().deleteConsultancy(consultancyUUID);
			redirectURL = "Consultancies";
		}

		return SUCCESS;
	}

	public void setConsultancyUUID(String consultancyUUID) {
		this.consultancyUUID = consultancyUUID;
	}
	public List<Consultancy> getConsultancies() {
		return consultancies;
	}
	public Consultancy getConsultancy() {
		return consultancy;
	}
	public void setConsultancy(Consultancy consultancy) {
		this.consultancy = consultancy;
	}
	public ConsultancyService getConsultancyService() {
		return consultancyService;
	}
	public void setConsultancyService(ConsultancyService consultancyService) {
		this.consultancyService = consultancyService;
	}
}