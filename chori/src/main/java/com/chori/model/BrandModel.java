package com.chori.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class BrandModel implements Serializable {
	private Integer brandcode;
	private String customerCode;
	private String brandname;
	private Date createdate;
	private String creator;

	public BrandModel() {
		super();
	}

	public BrandModel(Integer brandcode) {
		super();
		this.brandcode = brandcode;
	}

	public BrandModel(Integer brandcode, String customerCode, String brandname,
			Date createdate, String creator) {
		super();
		this.brandcode = brandcode;
		this.customerCode = customerCode;
		this.brandname = brandname;
		this.createdate = createdate;
		this.creator = creator;
	}

	public Integer getBrandcode() {
		return brandcode;
	}

	public void setBrandcode(Integer brandcode) {
		this.brandcode = brandcode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "BrandModel [brandcode=" + brandcode + ", customerCode="
				+ customerCode + ", brandname=" + brandname + ", createdate="
				+ createdate + ", creator=" + creator + "]";
	}

}
