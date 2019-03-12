package com.chori.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class FactorycontactModel implements Serializable {
	private Integer factorycontactcode;
	private String factoryCode;
	private String creator;
	private String name;
	private String email;
	private String tel;
	private Date createdate;

	public Integer getFactorycontactcode() {
		return factorycontactcode;
	}

	public void setFactorycontactcode(Integer factorycontactcode) {
		this.factorycontactcode = factorycontactcode;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((factorycontactcode == null) ? 0 : factorycontactcode
						.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FactorycontactModel other = (FactorycontactModel) obj;
		if (factorycontactcode == null) {
			if (other.factorycontactcode != null)
				return false;
		} else if (!factorycontactcode.equals(other.factorycontactcode))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FactorycontactModel [factorycontactcode=" + factorycontactcode
				+ ", factoryCode=" + factoryCode + ", creator=" + creator
				+ ", name=" + name + ", email=" + email + ", createdate="
				+ createdate + "]";
	}

	public FactorycontactModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FactorycontactModel(Integer factorycontactcode, String factoryCode,
			String creator, String name, String email, Date createdate) {
		super();
		this.factorycontactcode = factorycontactcode;
		this.factoryCode = factoryCode;
		this.creator = creator;
		this.name = name;
		this.email = email;
		this.createdate = createdate;
	}

}
