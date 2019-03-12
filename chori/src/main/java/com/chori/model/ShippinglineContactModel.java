package com.chori.model;

import java.util.Date;

public class ShippinglineContactModel {
	private Integer shippinglinecontactcode;
	private String shippinglinecode;
	private String creator;
	private String name;
	private String email;
	private String tel;

	private Date createdate;

	public Integer getShippinglinecontactcode() {
		return shippinglinecontactcode;
	}

	public void setShippinglinecontactcode(Integer shippinglinecontactcode) {
		this.shippinglinecontactcode = shippinglinecontactcode;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public ShippinglineContactModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShippinglineContactModel(Integer shippinglinecontactcode,
			String shippinglinecode, String creator, String name, String email,
			Date createdate, String tel) {
		super();
		this.shippinglinecontactcode = shippinglinecontactcode;
		this.shippinglinecode = shippinglinecode;
		this.creator = creator;
		this.tel = tel;
		this.name = name;
		this.email = email;
		this.createdate = createdate;
	}

}
