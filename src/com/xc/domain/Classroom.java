package com.xc.domain;

public class Classroom {
	private String cname;
	private int size;
	private int kind;

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	@Override
	public String toString() {
		return "Classroom [cname=" + cname + ", size=" + size + ", kind=" + kind + "]";
	}

}
