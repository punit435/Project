package com.enterio.hrms.foundation.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.enterio.foundation.domain.Bank;
import com.enterio.foundation.domain.Company;
import com.enterio.foundation.domain.User;

public class Employee implements Serializable
{
	private static final long serialVersionUID = 1159533916641041260L;
	private int id;
	private String uuid;
	private String code;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String personalEmail;
	private String officialEmail;
	private String mobile;
	private String annualCTC;
	private String fatherName;
	private String motherMaidenName;
	private boolean addressSame;
	private Date joiningDate;
	private Date probationEndDate;
	private Date resignationDate;
	private Date relieveingDate;
	private boolean bankAccountNew;
	private String bankAccountNumber;
	private String bankBranch;
	private String branchIFSC;
	private String nameInBankAccount;
	private Date createdOn;
	private User createdBy;
	private Company company;
	private Candidate candidate;
	private Designation designation;
	private Bank bank;
	private MaritalStatus maritalStatus;
	private String spouseName;
	private String spouseMobile;
	private String spouseEmployer;
	private String emergencyContactName;
	private String emergencyContactRelation;
	private String emergencyContactMobile;
	private String emergencyContactAlternatePhone;
	private User reportsTo;
	private List<EmployeeAddress> addresses;
	private List<EmployeeDocument> documents;
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getPersonalEmail() {
		return personalEmail;
	}
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}
	public String getOfficialEmail() {
		return officialEmail;
	}
	public void setOfficialEmail(String officialEmail) {
		this.officialEmail = officialEmail;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAnnualCTC() {
		return annualCTC;
	}
	public void setAnnualCTC(String annualCTC) {
		this.annualCTC = annualCTC;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherMaidenName() {
		return motherMaidenName;
	}
	public void setMotherMaidenName(String motherMaidenName) {
		this.motherMaidenName = motherMaidenName;
	}
	public boolean isAddressSame() {
		return addressSame;
	}
	public void setAddressSame(boolean addressSame) {
		this.addressSame = addressSame;
	}
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	public Date getProbationEndDate() {
		return probationEndDate;
	}
	public void setProbationEndDate(Date probationEndDate) {
		this.probationEndDate = probationEndDate;
	}
	public Date getResignationDate() {
		return resignationDate;
	}
	public void setResignationDate(Date resignationDate) {
		this.resignationDate = resignationDate;
	}
	public Date getRelieveingDate() {
		return relieveingDate;
	}
	public void setRelieveingDate(Date relieveingDate) {
		this.relieveingDate = relieveingDate;
	}
	public boolean isBankAccountNew() {
		return bankAccountNew;
	}
	public void setBankAccountNew(boolean bankAccountNew) {
		this.bankAccountNew = bankAccountNew;
	}
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getBranchIFSC() {
		return branchIFSC;
	}
	public void setBranchIFSC(String branchIFSC) {
		this.branchIFSC = branchIFSC;
	}
	public String getNameInBankAccount() {
		return nameInBankAccount;
	}
	public void setNameInBankAccount(String nameInBankAccount) {
		this.nameInBankAccount = nameInBankAccount;
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
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public Designation getDesignation() {
		return designation;
	}
	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public String getSpouseMobile() {
		return spouseMobile;
	}
	public void setSpouseMobile(String spouseMobile) {
		this.spouseMobile = spouseMobile;
	}
	public String getSpouseEmployer() {
		return spouseEmployer;
	}
	public void setSpouseEmployer(String spouseEmployer) {
		this.spouseEmployer = spouseEmployer;
	}
	public String getEmergencyContactName() {
		return emergencyContactName;
	}
	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}
	public String getEmergencyContactRelation() {
		return emergencyContactRelation;
	}
	public void setEmergencyContactRelation(String emergencyContactRelation) {
		this.emergencyContactRelation = emergencyContactRelation;
	}
	public String getEmergencyContactMobile() {
		return emergencyContactMobile;
	}
	public void setEmergencyContactMobile(String emergencyContactMobile) {
		this.emergencyContactMobile = emergencyContactMobile;
	}
	public String getEmergencyContactAlternatePhone() {
		return emergencyContactAlternatePhone;
	}
	public void setEmergencyContactAlternatePhone(String emergencyContactAlternatePhone) {
		this.emergencyContactAlternatePhone = emergencyContactAlternatePhone;
	}
	public User getReportsTo() {
		return reportsTo;
	}
	public void setReportsTo(User reportsTo) {
		this.reportsTo = reportsTo;
	}
	public List<EmployeeAddress> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<EmployeeAddress> addresses) {
		this.addresses = addresses;
	}
	public List<EmployeeDocument> getDocuments() {
		return documents;
	}
	public void setDocuments(List<EmployeeDocument> documents) {
		this.documents = documents;
	}
}