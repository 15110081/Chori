package com.chori.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CustomercontactModel implements Serializable {
	private Integer customercontactcode;
	private String customerCode;
	private String creator;
	private String name;
	private String email;
	private String tel;
	private Date createdate;

	public CustomercontactModel() {
		super();
	}

	public CustomercontactModel(Integer customercontactcode) {
		super();
		this.customercontactcode = customercontactcode;
	}

	public CustomercontactModel(Integer customercontactcode,
			String customerCode, String creator, String name, String email,
			String tel, Date createdate) {
		super();
		this.customercontactcode = customercontactcode;
		this.customerCode = customerCode;
		this.creator = creator;
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.createdate = createdate;
	}

	public Integer getCustomercontactcode() {
		return customercontactcode;
	}

	public void setCustomercontactcode(Integer customercontactcode) {
		this.customercontactcode = customercontactcode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "CustomercontactModel [customercontactcode="
				+ customercontactcode + ", customerCode=" + customerCode
				+ ", creator=" + creator + ", name=" + name + ", email="
				+ email + ", tel=" + tel + ", createdate=" + createdate + "]";
	}

}
