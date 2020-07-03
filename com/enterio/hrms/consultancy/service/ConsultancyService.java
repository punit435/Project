package com.enterio.hrms.consultancy.service;

import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.foundation.domain.Consultancy;
import com.enterio.hrms.foundation.domain.ConsultancyContact;

public interface ConsultancyService extends Serializable {
	
	public List<Consultancy> getConsultancies(String companyUUID);

	public Consultancy getConsultancy(String consultancyUUID);

	public Consultancy saveConsultancy(Consultancy consultancy, User createdBy);

	public boolean deleteConsultancy(String consultancyUUID);

	public ConsultancyContact getContact(String contactUUID);

	public ConsultancyContact saveContact(ConsultancyContact contact, User createdBy);

	public boolean deleteContact(String contactUUID);
}