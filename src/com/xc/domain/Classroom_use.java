package com.xc.domain;

import java.sql.Date;

public class Classroom_use {
	private int recordid;
	private String uid;
	private String cname;
	private Date date;
	private String week;
	private int timeid;
	private String application;
	private int islonguse;
	private int status;

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public int getRecordid() {
		return recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTimeid() {
		return timeid;
	}

	public void setTimeid(int timeid) {
		this.timeid = timeid;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public int getIslonguse() {
		return islonguse;
	}

	public void setIslonguse(int islonguse) {
		this.islonguse = islonguse;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Classroom_use [recordid=" + recordid + ", uid=" + uid + ", cname=" + cname + ", date=" + date
				+ ", timeid=" + timeid + ", application=" + application + ", islonguse=" + islonguse + ", status="
				+ status + "]";
	}

}
