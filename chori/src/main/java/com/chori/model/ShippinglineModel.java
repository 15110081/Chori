package com.chori.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ShippinglineModel {
	private String shippinglinecode;
	private String creator;
	private String shortname;
	private String longname;
	private String address;
	private String tel;
	private String fax;
	private String taxno;
	private String status;
	private Date createdate;
	private String remark;
	private Set<ShippinglineContactModel> shippinglinecontacts = new HashSet<ShippinglineContactModel>();

	public String getShippinglinecode() {
		return shippinglinecode;
	}

	public void setShippinglinecode(String shippinglinecode) {
		this.shippinglinecode = shippinglinecode;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getLongname() {
		return longname;
	}

	public void setLongname(String longname) {
		this.longname = longname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTaxno() {
		return taxno;
	}

	public void setTaxno(String taxno) {
		this.taxno = taxno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Set<ShippinglineContactModel> getShippinglinecontacts() {
		return shippinglinecontacts;
	}

	public void setShippinglinecontacts(
			Set<ShippinglineContactModel> shippinglinecontacts) {
		this.shippinglinecontacts = shippinglinecontacts;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ShippinglineModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShippinglineModel(String shippinglinecode, String creator,
			String shortname, String longname, String address, String tel,
			String fax, String taxno, String status, Date createdate,
			String remark, Set<ShippinglineContactModel> shippinglinecontacts) {
		super();
		this.shippinglinecode = shippinglinecode;
		this.creator = creator;
		this.shortname = shortname;
		this.longname = longname;
		this.address = address;
		this.tel = tel;
		this.fax = fax;
		this.taxno = taxno;
		this.status = status;
		this.createdate = createdate;
		this.remark = remark;
		this.shippinglinecontacts = shippinglinecontacts;
	}

}
