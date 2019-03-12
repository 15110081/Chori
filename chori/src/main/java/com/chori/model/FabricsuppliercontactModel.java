package com.chori.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class FabricsuppliercontactModel implements Serializable {
	private Integer fabricsuppliercontactcode;
	private String fabricsupplierCode;
	private String creator;
	private String name;
	private String email;
	private Date createdate;
	private String tel;

	public Integer getFabricsuppliercontactcode() {
		return fabricsuppliercontactcode;
	}

	public void setFabricsuppliercontactcode(Integer fabricsuppliercontactcode) {
		this.fabricsuppliercontactcode = fabricsuppliercontactcode;
	}

	public String getFabricsupplierCode() {
		return fabricsupplierCode;
	}

	public void setFabricsupplierCode(String fabricsupplierCode) {
		this.fabricsupplierCode = fabricsupplierCode;
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
				+ ((fabricsuppliercontactcode == null) ? 0
						: fabricsuppliercontactcode.hashCode());
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
		FabricsuppliercontactModel other = (FabricsuppliercontactModel) obj;
		if (fabricsuppliercontactcode == null) {
			if (other.fabricsuppliercontactcode != null)
				return false;
		} else if (!fabricsuppliercontactcode
				.equals(other.fabricsuppliercontactcode))
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
		return "FabricsuppliercontactModel [fabricsuppliercontactcode="
				+ fabricsuppliercontactcode + ", fabricsupplierCode="
				+ fabricsupplierCode + ", creator=" + creator + ", name="
				+ name + ", email=" + email + ", createdate=" + createdate
				+ "]";
	}

	public FabricsuppliercontactModel(Integer fabricsuppliercontactcode,
			String fabricsupplierCode, String creator, String name,
			String email, Date createdate) {
		super();
		this.fabricsuppliercontactcode = fabricsuppliercontactcode;
		this.fabricsupplierCode = fabricsupplierCode;
		this.creator = creator;
		this.name = name;
		this.email = email;
		this.createdate = createdate;
	}

	public FabricsuppliercontactModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
