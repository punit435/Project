package com.enterio.hrms.consultancy.web;

import java.io.Serializable;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.consultancy.service.ConsultancyService;
import com.enterio.hrms.foundation.domain.Consultancy;
import com.enterio.hrms.foundation.domain.ConsultancyContact;
import com.enterio.init.StrutsActionSupport;
import com.enterio.util.StringUtil;

public class ContactAction extends StrutsActionSupport implements Serializable
{
	private static final long serialVersionUID = -8026996167258709376L;
	private String consultancyUUID;
	private String contactUUID;
	private ConsultancyContact contact;
	private Consultancy consultancyObj;
	private ConsultancyService consultancyService;

	public String addContact() 
	{
		consultancyUUID = StringUtil.checkNull(consultancyUUID);
		
		if(consultancyUUID.length() > 0)
		{
			consultancyObj = getConsultancyService().getConsultancy(consultancyUUID);
		}
		
		return SUCCESS;
	}
	
	public String editContact() 
	{
		consultancyUUID = StringUtil.checkNull(consultancyUUID);
		contactUUID = StringUtil.checkNull(contactUUID);
System.out.println("consultancyUUID: " + consultancyUUID);
System.out.println("contactUUID: " + contactUUID);

		if(consultancyUUID.length() > 0 && contactUUID.length() > 0)
		{
			consultancyObj = getConsultancyService().getConsultancy(consultancyUUID);
			contact = getConsultancyService().getContact(contactUUID);
System.out.println("contact: " + contact);			
		}

		return SUCCESS;
	}

	public String saveContact() 
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null)
		{
			getConsultancyService().saveContact(contact, sessionUser);
			
			redirectURL = "../Details/" + contact.getConsultancy().getUuid();
		}
	
		return SUCCESS;
	}

	public String deleteContact() 
	{
		consultancyUUID = StringUtil.checkNull(consultancyUUID);
		contactUUID = StringUtil.checkNull(contactUUID);
		
		if(consultancyUUID.length() > 0 && contactUUID.length() > 0)
		{
			getConsultancyService().deleteContact(contactUUID);
			
			redirectURL = "../Details/" + consultancyUUID;
		}
		
		return SUCCESS;
	}
	
	public void setConsultancyUUID(String consultancyUUID) {
		this.consultancyUUID = consultancyUUID;
	}
	public void setContactUUID(String contactUUID) {
		this.contactUUID = contactUUID;
	}
	public Consultancy getConsultancyObj() {
		return consultancyObj;
	}
	public ConsultancyContact getContact() {
		return contact;
	}
	public void setContact(ConsultancyContact contact) {
		this.contact = contact;
	}
	public ConsultancyService getConsultancyService() {
		return consultancyService;
	}
	public void setConsultancyService(ConsultancyService consultancyService) {
		this.consultancyService = consultancyService;
	}
}