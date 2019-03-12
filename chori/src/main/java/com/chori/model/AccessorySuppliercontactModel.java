package com.chori.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class AccessorySuppliercontactModel implements Serializable {
	private Integer accessorycontact;
	private String accessoryCode;
	private String creator;
	private String name;
	private String email;
	private String telephone;
	private Date createdate;

	public Integer getAccessorycontact() {
		return accessorycontact;
	}

	public void setAccessorycontact(Integer accessorycontact) {
		this.accessorycontact = accessorycontact;
	}

	public String getAccessoryCode() {
		return accessoryCode;
	}

	public void setAccessoryCode(String accessoryCode) {
		this.accessoryCode = accessoryCode;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public AccessorySuppliercontactModel(Integer accessorycontact,
			String accessoryCode, String creator, String name, String email,
			String telephone, Date createdate) {
		super();
		this.accessorycontact = accessorycontact;
		this.accessoryCode = accessoryCode;
		this.creator = creator;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.createdate = createdate;
	}

	public AccessorySuppliercontactModel() {
		super();
	}

	@Override
	public String toString() {
		return "AccessorySuppliercontactModel [accessorycontact="
				+ accessorycontact + ", accessoryCode=" + accessoryCode
				+ ", creator=" + creator + ", name=" + name + ", email="
				+ email + ", telephone=" + telephone + ", createdate="
				+ createdate + "]";
	}

}
