package com.xc.domain;

public class User {
	private String uid;
	private String upassword;
	private String uemail;
	private String deptname;
	private int identify;
	private int status;
	private String activecode;
	private double updatetime;

	@Override
	public String toString() {
		return "User [uid=" + uid + ", upassword=" + upassword + ", uemail=" + uemail + ", deptname=" + deptname
				+ ", identify=" + identify + ", status=" + status + ", activecode=" + activecode + ", updatetime="
				+ updatetime + "]";
	}

	public String getActivecode() {
		return activecode;
	}

	public void setActivecode(String activecode) {
		this.activecode = activecode;
	}

	public double getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(double updatetime) {
		this.updatetime = updatetime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIdentify() {
		return identify;
	}

	public void setIdentify(int identify) {
		this.identify = identify;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

}
