package com.chori.model;

import java.util.Date;

public class SizeModel {

	private Integer sizecode;
	private String customer;
	private String garmentkind;
	private String garmentstyle;
	private String type;
	private String creator;
	private String sizename;
	private Date createdate;
	private boolean isAssignedForGarmentAccessory;
	private String customerCodeFrom;
	private String customerCodeTo;

	public SizeModel() {
		super();
	}
	
	public SizeModel(Integer sizecode, String customer, String garmentkind, String type, String creator,
			String sizename, Date createdate, boolean isAssignedForGarmentAccessory, String customerCodeFrom,
			String customerCodeTo) {
		super();
		this.sizecode = sizecode;
		this.customer = customer;
		this.garmentkind = garmentkind;
		this.type = type;
		this.creator = creator;
		this.sizename = sizename;
		this.createdate = createdate;
		this.isAssignedForGarmentAccessory = isAssignedForGarmentAccessory;
		this.customerCodeFrom = customerCodeFrom;
		this.customerCodeTo = customerCodeTo;
	}

	public SizeModel(Integer sizecode, String customer, String garmentkind, String garmentstyle, String type,
			String creator, String sizename, Date createdate, boolean isAssignedForGarmentAccessory,
			String customerCodeFrom, String customerCodeTo) {
		super();
		this.sizecode = sizecode;
		this.customer = customer;
		this.garmentkind = garmentkind;
		this.garmentstyle = garmentstyle;
		this.type = type;
		this.creator = creator;
		this.sizename = sizename;
		this.createdate = createdate;
		this.isAssignedForGarmentAccessory = isAssignedForGarmentAccessory;
		this.customerCodeFrom = customerCodeFrom;
		this.customerCodeTo = customerCodeTo;
	}

	public String getGarmentstyle() {
		return garmentstyle;
	}

	public void setGarmentstyle(String garmentstyle) {
		this.garmentstyle = garmentstyle;
	}
	
	public String getCustomerCodeFrom() {
		return customerCodeFrom;
	}

	public void setCustomerCodeFrom(String customerCodeFrom) {
		this.customerCodeFrom = customerCodeFrom;
	}

	public String getCustomerCodeTo() {
		return customerCodeTo;
	}

	public void setCustomerCodeTo(String customerCodeTo) {
		this.customerCodeTo = customerCodeTo;
	}

	public Integer getSizecode() {
		return sizecode;
	}

	public void setSizecode(Integer sizecode) {
		this.sizecode = sizecode;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getGarmentkind() {
		return garmentkind;
	}

	public void setGarmentkind(String garmentkind) {
		this.garmentkind = garmentkind;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getSizename() {
		return sizename;
	}

	public void setSizename(String sizename) {
		this.sizename = sizename;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public boolean isAssignedForGarmentAccessory() {
		return isAssignedForGarmentAccessory;
	}

	public void setAssignedForGarmentAccessory(
			boolean isAssignedForGarmentAccessory) {
		this.isAssignedForGarmentAccessory = isAssignedForGarmentAccessory;
	}

	@Override
	public String toString() {
		return "SizeModel [sizecode=" + sizecode + ", customer=" + customer + ", garmentkind=" + garmentkind
				+ ", garmentstyle=" + garmentstyle + ", type=" + type + ", creator=" + creator + ", sizename="
				+ sizename + ", createdate=" + createdate + ", isAssignedForGarmentAccessory="
				+ isAssignedForGarmentAccessory + ", customerCodeFrom=" + customerCodeFrom + ", customerCodeTo="
				+ customerCodeTo + "]";
	}

}
