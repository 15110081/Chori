package com.chori.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class FpiModel implements Serializable {
	private int fpicode;
	private String creator;
	private Date receiveddate;
	private Date createddate;
	private String customerconfirmationstatus;

	public int getFpicode() {
		return fpicode;
	}

	public void setFpicode(int fpicode) {
		this.fpicode = fpicode;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getReceiveddate() {
		return receiveddate;
	}

	public void setReceiveddate(Date receiveddate) {
		this.receiveddate = receiveddate;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public String getCustomerconfirmationstatus() {
		return customerconfirmationstatus;
	}

	public void setCustomerconfirmationstatus(String customerconfirmationstatus) {
		this.customerconfirmationstatus = customerconfirmationstatus;
	}

	public FpiModel(int fpicode, String creator, Date receiveddate,
			Date createddate, String customerconfirmationstatus) {
		super();
		this.fpicode = fpicode;
		this.creator = creator;
		this.receiveddate = receiveddate;
		this.createddate = createddate;
		this.customerconfirmationstatus = customerconfirmationstatus;
	}

	public FpiModel() {
		super();
	}

	@Override
	public String toString() {
		return "FpiModel [fpicode=" + fpicode + ", creator=" + creator
				+ ", receiveddate=" + receiveddate + ", createddate="
				+ createddate + ", customerconfirmationstatus="
				+ customerconfirmationstatus + "]";
	}

}
