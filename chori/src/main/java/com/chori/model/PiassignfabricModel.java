package com.chori.model;

import java.util.Date;

public class PiassignfabricModel {
	private String lotnumber;
	private String fabricno;
	private String pi;
	private String user;
	private Date createdate;

	public PiassignfabricModel() {
		super();
	}

	public PiassignfabricModel(String lotnumber) {
		super();
		this.lotnumber = lotnumber;
	}

	public PiassignfabricModel(String lotnumber, String fabricno, String pi,
			String user, Date createdate) {
		super();
		this.lotnumber = lotnumber;
		this.fabricno = fabricno;
		this.pi = pi;
		this.user = user;
		this.createdate = createdate;
	}

	public PiassignfabricModel(String lotnumber, String fabricno) {
		super();
		this.lotnumber = lotnumber;
		this.fabricno = fabricno;
	}

	public String getLotnumber() {
		return lotnumber;
	}

	public void setLotnumber(String lotnumber) {
		this.lotnumber = lotnumber;
	}

	public String getFabricno() {
		return fabricno;
	}

	public void setFabricno(String fabricno) {
		this.fabricno = fabricno;
	}

	public String getPi() {
		return pi;
	}

	public void setPi(String pi) {
		this.pi = pi;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "PiassignfabricModel [lotnumber=" + lotnumber + ", fabricno="
				+ fabricno + ", pi=" + pi + ", user=" + user + ", createdate="
				+ createdate + "]";
	}
}
