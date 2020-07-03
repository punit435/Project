package com.enterio.hrms.consultancy.dao;

import java.io.Serializable;
import java.util.List;

import com.enterio.hrms.foundation.domain.Consultancy;
import com.enterio.hrms.foundation.domain.ConsultancyContact;

public interface ConsultancyDAO extends Serializable
{
	public List<Consultancy> getConsultancies(String companyUUID);
	
	public Consultancy getConsultancy(String consultancyUUID);
	
	public void insertConsultancy(Consultancy consultancy);
	
	public void updateConsultancy(Consultancy consultancy);
	
	public void deleteConsultancy(Consultancy consultancy);

	public ConsultancyContact getContact(String contactUUID);

	public void insertContact(ConsultancyContact consultancyContact);
	
	public void updateContact(ConsultancyContact consultancyContact);

	public void deleteContact(ConsultancyContact consultancyContact);
}