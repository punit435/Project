package com.enterio.hrms.foundation.domain;

import java.io.Serializable;
import java.util.Date;

import com.enterio.foundation.domain.User;

public class CandidateVacancy  implements Serializable
{
	private static final long serialVersionUID = 3214521173429095166L;
	private int id;
	private Vacancy vacancy;
	private Candidate candidate;
	private CandidateStatus candidateStatus;
	private String selectionRemarks;
	private Date selectedOn;
	private User selectedBy;
	private Date assignedOn;
	private User assignedBy;
	private String rejectionRemarks;
	private Date rejectedOn;
	private User rejectedBy;
	private Date onholdOn;
	private User onholdBy;
	private String onholdRemarks;
	private String uuid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Vacancy getVacancy() {
		return vacancy;
	}
	public void setVacancy(Vacancy vacancy) {
		this.vacancy = vacancy;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public CandidateStatus getCandidateStatus() {
		return candidateStatus;
	}
	public void setCandidateStatus(CandidateStatus candidateStatus) {
		this.candidateStatus = candidateStatus;
	}
	public String getSelectionRemarks() {
		return selectionRemarks;
	}
	public void setSelectionRemarks(String selectionRemarks) {
		this.selectionRemarks = selectionRemarks;
	}
	public Date getSelectedOn() {
		return selectedOn;
	}
	public void setSelectedOn(Date selectedOn) {
		this.selectedOn = selectedOn;
	}
	public User getSelectedBy() {
		return selectedBy;
	}
	public void setSelectedBy(User selectedBy) {
		this.selectedBy = selectedBy;
	}
	public Date getAssignedOn() {
		return assignedOn;
	}
	public void setAssignedOn(Date assignedOn) {
		this.assignedOn = assignedOn;
	}
	public User getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(User assignedBy) {
		this.assignedBy = assignedBy;
	}
	public String getRejectionRemarks() {
		return rejectionRemarks;
	}
	public void setRejectionRemarks(String rejectionRemarks) {
		this.rejectionRemarks = rejectionRemarks;
	}
	public Date getRejectedOn() {
		return rejectedOn;
	}
	public void setRejectedOn(Date rejectedOn) {
		this.rejectedOn = rejectedOn;
	}
	public User getRejectedBy() {
		return rejectedBy;
	}
	public void setRejectedBy(User rejectedBy) {
		this.rejectedBy = rejectedBy;
	}
	public Date getOnholdOn() {
		return onholdOn;
	}
	public void setOnholdOn(Date onholdOn) {
		this.onholdOn = onholdOn;
	}
	public User getOnholdBy() {
		return onholdBy;
	}
	public void setOnholdBy(User onholdBy) {
		this.onholdBy = onholdBy;
	}
	public String getOnholdRemarks() {
		return onholdRemarks;
	}
	public void setOnholdRemarks(String onholdRemarks) {
		this.onholdRemarks = onholdRemarks;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}