package com.chori.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class FabricsupplierModel implements Serializable {
	private String fabricsupcode;
	private String creator;
	private String shortname;
	private String longname;
	private String address;
	private String tel;
	private String fax;
	private String taxno;
	private String status;
	private Boolean ischori;
	private String remark;
	private Date createdate;
	private Set<FabricsuppliercontactModel> fabricsuppliercontactModelList = new HashSet<FabricsuppliercontactModel>();

	public String getFabricsupcode() {
		return fabricsupcode;
	}

	public void setFabricsupcode(String fabricsupcode) {
		this.fabricsupcode = fabricsupcode;
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

	public Boolean getIschori() {
		return ischori;
	}

	public void setIschori(Boolean ischori) {
		this.ischori = ischori;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Set<FabricsuppliercontactModel> getFabricsuppliercontactModelList() {
		return fabricsuppliercontactModelList;
	}

	public void setFabricsuppliercontactModelList(
			Set<FabricsuppliercontactModel> fabricsuppliercontactModelList) {
		this.fabricsuppliercontactModelList = fabricsuppliercontactModelList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fabricsupcode == null) ? 0 : fabricsupcode.hashCode());
		result = prime * result
				+ ((longname == null) ? 0 : longname.hashCode());
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
		FabricsupplierModel other = (FabricsupplierModel) obj;
		if (fabricsupcode == null) {
			if (other.fabricsupcode != null)
				return false;
		} else if (!fabricsupcode.equals(other.fabricsupcode))
			return false;
		if (longname == null) {
			if (other.longname != null)
				return false;
		} else if (!longname.equals(other.longname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FabricsupplierModel [fabricsupcode=" + fabricsupcode
				+ ", creator=" + creator + ", shortname=" + shortname
				+ ", longname=" + longname + ", address=" + address + ", tel="
				+ tel + ", fax=" + fax + ", taxno=" + taxno + ", status="
				+ status + ", ischori=" + ischori + ", createdate="
				+ createdate + ", fabricsuppliercontactModelList="
				+ fabricsuppliercontactModelList + "]";
	}

	public FabricsupplierModel(String fabricsupcode, String creator,
			String shortname, String longname, String address, String tel,
			String fax, String taxno, String status, Boolean ischori,
			Date createdate,
			Set<FabricsuppliercontactModel> fabricsuppliercontactModelList) {
		super();
		this.fabricsupcode = fabricsupcode;
		this.creator = creator;
		this.shortname = shortname;
		this.longname = longname;
		this.address = address;
		this.tel = tel;
		this.fax = fax;
		this.taxno = taxno;
		this.status = status;
		this.ischori = ischori;
		this.createdate = createdate;
		this.fabricsuppliercontactModelList = fabricsuppliercontactModelList;
	}

	public FabricsupplierModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
