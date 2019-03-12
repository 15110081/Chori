package com.chori.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class PigridModel implements Serializable {
	private int pigridCode;
	private String creator;
	private Date createdate;

	public int getPigridCode() {
		return pigridCode;
	}

	public void setPigridCode(int pigridCode) {
		this.pigridCode = pigridCode;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public PigridModel(int pigridCode, String creator, Date createdate) {
		super();
		this.pigridCode = pigridCode;
		this.creator = creator;
		this.createdate = createdate;
	}

	public PigridModel() {
		super();
	}

	@Override
	public String toString() {
		return "PigridModel [pigridCode=" + pigridCode + ", creator=" + creator
				+ ", createdate=" + createdate + "]";
	}

}
