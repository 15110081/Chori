package com.chori.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class AccessorySupplierModel implements Serializable {
	private String accessorysuppliercode;
	private String creator;
	private String shortname;
	private String longname;
	private String address;
	private String tel;
	private String fax;
	private String taxno;
	private String email;
	private String status;
	private String remark;
	private Date createdate;
	private Set<AccessorySuppliercontactModel> accsupcontactModellist = new HashSet<AccessorySuppliercontactModel>();

	public String getAccessorysuppliercode() {
		return accessorysuppliercode;
	}

	public void setAccessorysuppliercode(String accessorysuppliercode) {
		this.accessorysuppliercode = accessorysuppliercode;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Set<AccessorySuppliercontactModel> getAccsupcontactModellist() {
		return accsupcontactModellist;
	}

	public void setAccsupcontactModellist(
			Set<AccessorySuppliercontactModel> accsupcontactModellist) {
		this.accsupcontactModellist = accsupcontactModellist;
	}

	public AccessorySupplierModel() {
		super();
	}

	public AccessorySupplierModel(String accessorysuppliercode, String creator,
			String shortname, String longname, String address, String tel,
			String fax, String taxno, String email, String status,
			String remark, Date createdate,
			Set<AccessorySuppliercontactModel> accsupcontactModellist) {
		super();
		this.accessorysuppliercode = accessorysuppliercode;
		this.creator = creator;
		this.shortname = shortname;
		this.longname = longname;
		this.address = address;
		this.tel = tel;
		this.fax = fax;
		this.taxno = taxno;
		this.email = email;
		this.status = status;
		this.remark = remark;
		this.createdate = createdate;
		this.accsupcontactModellist = accsupcontactModellist;
	}

	@Override
	public String toString() {
		return "AccessorySupplierModel [accessorysuppliercode="
				+ accessorysuppliercode + ", creator=" + creator
				+ ", shortname=" + shortname + ", longname=" + longname
				+ ", address=" + address + ", tel=" + tel + ", fax=" + fax
				+ ", taxno=" + taxno + ", email=" + email + ", status="
				+ status + ", remark=" + remark + ", createdate=" + createdate
				+ ", accsupcontactModellist=" + accsupcontactModellist + "]";
	}
}
