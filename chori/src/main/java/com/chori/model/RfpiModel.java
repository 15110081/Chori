package com.chori.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class RfpiModel implements Serializable {
	private int rfpigrid;
	private int version;
	private String creator;
	private Date receiveddate;
	private Date createddate;
	private String customerconfirmationstatus;
	public RfpiModel() {
		super();
	}
	public RfpiModel(int rfpigrid) {
		super();
		this.rfpigrid = rfpigrid;
	}
	public RfpiModel(int rfpigrid, int version, String creator, Date receiveddate, Date createddate,
			String customerconfirmationstatus) {
		super();
		this.rfpigrid = rfpigrid;
		this.version = version;
		this.creator = creator;
		this.receiveddate = receiveddate;
		this.createddate = createddate;
		this.customerconfirmationstatus = customerconfirmationstatus;
	}
	public int getRfpigrid() {
		return rfpigrid;
	}
	public void setRfpigrid(int rfpigrid) {
		this.rfpigrid = rfpigrid;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
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
	@Override
	public String toString() {
		return "RfpiModel [rfpigrid=" + rfpigrid + ", version=" + version + ", creator=" + creator + ", receiveddate="
				+ receiveddate + ", createddate=" + createddate + ", customerconfirmationstatus="
				+ customerconfirmationstatus + "]";
	}	
}
