package com.pojo;

import java.util.Date;

public class Attendance {
	private Integer kid;
	private String attendance_day;
	private String attendance_every;
	private Date attendance_time;
	private String exception_explain;
	private String approval_opinion;
	private Date opinion_time;

	public Integer getKid() {
		return kid;
	}

	public void setKid(Integer kid) {
		this.kid = kid;
	}

	public String getAttendance_day() {
		return attendance_day;
	}

	public void setAttendance_day(String attendance_day) {
		this.attendance_day = attendance_day;
	}

	public String getAttendance_every() {
		return attendance_every;
	}

	public void setAttendance_every(String attendance_every) {
		this.attendance_every = attendance_every;
	}

	public Date getAttendance_time() {
		return attendance_time;
	}

	public void setAttendance_time(Date attendance_time) {
		this.attendance_time = attendance_time;
	}

	public String getException_explain() {
		return exception_explain;
	}

	public void setException_explain(String exception_explain) {
		this.exception_explain = exception_explain;
	}

	public String getApproval_opinion() {
		return approval_opinion;
	}

	public void setApproval_opinion(String approval_opinion) {
		this.approval_opinion = approval_opinion;
	}

	public Date getOpinion_time() {
		return opinion_time;
	}

	public void setOpinion_time(Date opinion_time) {
		this.opinion_time = opinion_time;
	}

}
