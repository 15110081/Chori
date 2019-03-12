package com.chori.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class FactoryModel implements Serializable {
	private String factorycode;
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
	private Set<FactorycontactModel> factorycontactModellist = new HashSet<FactorycontactModel>();
	private Set<FactoryaccountinformationModel> factoryaccountinformationModelList = new HashSet<FactoryaccountinformationModel>();

	public String getFactorycode() {
		return factorycode;
	}

	public void setFactorycode(String factorycode) {
		this.factorycode = factorycode;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<FactorycontactModel> getFactorycontactModellist() {
		return factorycontactModellist;
	}

	public void setFactorycontactModellist(
			Set<FactorycontactModel> factorycontactModellist) {
		this.factorycontactModellist = factorycontactModellist;
	}

	public Set<FactoryaccountinformationModel> getFactoryaccountinformationModelList() {
		return factoryaccountinformationModelList;
	}

	public void setFactoryaccountinformationModelList(
			Set<FactoryaccountinformationModel> factoryaccountinformationModelList) {
		this.factoryaccountinformationModelList = factoryaccountinformationModelList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((factorycode == null) ? 0 : factorycode.hashCode());
		result = prime * result
				+ ((shortname == null) ? 0 : shortname.hashCode());
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
		FactoryModel other = (FactoryModel) obj;
		if (factorycode == null) {
			if (other.factorycode != null)
				return false;
		} else if (!factorycode.equals(other.factorycode))
			return false;
		if (shortname == null) {
			if (other.shortname != null)
				return false;
		} else if (!shortname.equals(other.shortname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FactoryModel [factorycode=" + factorycode + ", creator="
				+ creator + ", shortname=" + shortname + ", longname="
				+ longname + ", address=" + address + ", tel=" + tel + ", fax="
				+ fax + ", taxno=" + taxno + ", status=" + status
				+ ", createdate=" + createdate + ", remark=" + remark
				+ ", factorycontactModellist=" + factorycontactModellist
				+ ", factoryaccountinformationModelList="
				+ factoryaccountinformationModelList + "]";
	}

	public FactoryModel(String factorycode, String creator, String shortname,
			String longname, String address, String tel, String fax,
			String taxno, String status, Date createdate,
			Set<FactorycontactModel> factorycontactModellist) {
		super();
		this.factorycode = factorycode;
		this.creator = creator;
		this.shortname = shortname;
		this.longname = longname;
		this.address = address;
		this.tel = tel;
		this.fax = fax;
		this.taxno = taxno;
		this.status = status;
		this.createdate = createdate;
		this.factorycontactModellist = factorycontactModellist;
	}

	public FactoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
