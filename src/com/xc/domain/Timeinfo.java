package com.xc.domain;

public class Timeinfo {
	private int timeid;
	private String peroid;

	@Override
	public String toString() {
		return "Timeinfo [timeid=" + timeid + ", peroid=" + peroid + "]";
	}

	public int getTimeid() {
		return timeid;
	}

	public void setTimeid(int timeid) {
		this.timeid = timeid;
	}

	public String getPeroid() {
		return peroid;
	}

	public void setPeroid(String peroid) {
		this.peroid = peroid;
	}

}
