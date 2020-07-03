package com.enterio.hrms.foundation.domain;

import java.io.Serializable;
import java.util.Date;

import com.enterio.foundation.domain.User;

public class Candidate  implements Serializable
{
	private static final long serialVersionUID = 4304813504418234298L;
	private int id;
	private String uuid;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String mobile;
	private String email;
	private String alternateEmail;
	private String alternateMobile;
	private int experience;
	private String skills;
	private String referredByName;
	private String file;
	private String selectionRemarks;
	private String rejectionRemarks;
	private String onholdRemarks;
	private Date createdOn;
	private User createdBy;
	private VacancyStatus vacancyStatus;
	private Consultancy consultancy;
	private ConsultancyContact consultancyContact;
	private CandidateType candidateType;
	private Gender gender;
	private User selectedBy;
	private User assignedBy;
	private User rejectedBy;
	private User onholdBy;
	private Vacancy vacancy;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAlternateEmail() {
		return alternateEmail;
	}
	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}
	public String getAlternateMobile() {
		return alternateMobile;
	}
	public void setAlternateMobile(String alternateMobile) {
		this.alternateMobile = alternateMobile;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getReferredByName() {
		return referredByName;
	}
	public void setReferredByName(String referredByName) {
		this.referredByName = referredByName;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getSelectionRemarks() {
		return selectionRemarks;
	}
	public void setSelectionRemarks(String selectionRemarks) {
		this.selectionRemarks = selectionRemarks;
	}
	public String getRejectionRemarks() {
		return rejectionRemarks;
	}
	public void setRejectionRemarks(String rejectionRemarks) {
		this.rejectionRemarks = rejectionRemarks;
	}
	
	public String getOnholdRemarks() {
		return onholdRemarks;
	}
	public void setOnholdRemarks(String onholdRemarks) {
		this.onholdRemarks = onholdRemarks;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public VacancyStatus getVacancyStatus() {
		return vacancyStatus;
	}
	public void setVacancyStatus(VacancyStatus vacancyStatus) {
		this.vacancyStatus = vacancyStatus;
	}
	public Consultancy getConsultancy() {
		return consultancy;
	}
	public void setConsultancy(Consultancy consultancy) {
		this.consultancy = consultancy;
	}
	public ConsultancyContact getConsultancyContact() {
		return consultancyContact;
	}
	public void setConsultancyContact(ConsultancyContact consultancyContact) {
		this.consultancyContact = consultancyContact;
	}
	public CandidateType getCandidateType() {
		return candidateType;
	}
	public void setCandidateType(CandidateType candidateType) {
		this.candidateType = candidateType;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public User getSelectedBy() {
		return selectedBy;
	}
	public void setSelectedBy(User selectedBy) {
		this.selectedBy = selectedBy;
	}
	public User getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(User assignedBy) {
		this.assignedBy = assignedBy;
	}
	public User getRejectedBy() {
		return rejectedBy;
	}
	public void setRejectedBy(User rejectedBy) {
		this.rejectedBy = rejectedBy;
	}
	public User getOnholdBy() {
		return onholdBy;
	}
	public void setOnholdBy(User onholdBy) {
		this.onholdBy = onholdBy;
	}
	public Vacancy getVacancy() {
		return vacancy;
	}
	public void setVacancy(Vacancy vacancy) {
		this.vacancy = vacancy;
	}
}