package com.enterio.hrms.foundation.domain;

import java.io.Serializable;
import java.util.Date;

import com.enterio.foundation.domain.User;

public class Interview implements Serializable
{
	private static final long serialVersionUID = 2573765759302568217L;
	private int id;
	private String uuid;
	private Date interviewDate;
	private String interviewTime;
	private String roundName;
	private Date scheduledOn;
	private User scheduledBy;
	private User interviewedBy;
	private String remarks;
	private Candidate candidate;
	private InterviewStatus status;

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
	public Date getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}
	public String getInterviewTime() {
		return interviewTime;
	}
	public void setInterviewTime(String interviewTime) {
		this.interviewTime = interviewTime;
	}
	public String getRoundName() {
		return roundName;
	}
	public void setRoundName(String roundName) {
		this.roundName = roundName;
	}
	public Date getScheduledOn() {
		return scheduledOn;
	}
	public void setScheduledOn(Date scheduledOn) {
		this.scheduledOn = scheduledOn;
	}
	public User getScheduledBy() {
		return scheduledBy;
	}
	public void setScheduledBy(User scheduledBy) {
		this.scheduledBy = scheduledBy;
	}
	public User getInterviewedBy() {
		return interviewedBy;
	}
	public void setInterviewedBy(User interviewedBy) {
		this.interviewedBy = interviewedBy;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public InterviewStatus getStatus() {
		return status;
	}
	public void setStatus(InterviewStatus status) {
		this.status = status;
	}
}