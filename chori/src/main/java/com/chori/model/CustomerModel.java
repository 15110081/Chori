package com.chori.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class CustomerModel implements Serializable {
	private String customercode;
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
	private Set<CustomercontactModel> customercontactModellist = new HashSet<CustomercontactModel>();
	private Set<BrandModel> brandModellist = new HashSet<BrandModel>();
	private Set<CustomeraccountinformationModel> customeraccountinformationModellist = new HashSet<CustomeraccountinformationModel>();

	public CustomerModel() {
		super();
	}

	public CustomerModel(String customercode) {
		super();
		this.customercode = customercode;
	}

	public CustomerModel(
			String customercode,
			String creator,
			String shortname,
			String longname,
			String address,
			String tel,
			String fax,
			String taxno,
			String status,
			Date createdate,
			String remark,
			Set<CustomercontactModel> customercontactModellist,
			Set<BrandModel> brandModellist,
			Set<CustomeraccountinformationModel> customeraccountinformationModellist) {
		super();
		this.customercode = customercode;
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
		this.customercontactModellist = customercontactModellist;
		this.brandModellist = brandModellist;
		this.customeraccountinformationModellist = customeraccountinformationModellist;
	}

	public String getCustomercode() {
		return customercode;
	}

	public void setCustomercode(String customercode) {
		this.customercode = customercode;
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

	public Set<CustomercontactModel> getCustomercontactModellist() {
		return customercontactModellist;
	}

	public void setCustomercontactModellist(
			Set<CustomercontactModel> customercontactModellist) {
		this.customercontactModellist = customercontactModellist;
	}

	public Set<BrandModel> getBrandModellist() {
		return brandModellist;
	}

	public void setBrandModellist(Set<BrandModel> brandModellist) {
		this.brandModellist = brandModellist;
	}

	public Set<CustomeraccountinformationModel> getCustomeraccountinformationModellist() {
		return customeraccountinformationModellist;
	}

	public void setCustomeraccountinformationModellist(
			Set<CustomeraccountinformationModel> customeraccountinformationModellist) {
		this.customeraccountinformationModellist = customeraccountinformationModellist;
	}

	@Override
	public String toString() {
		return "CustomerModel [customercode=" + customercode + ", creator="
				+ creator + ", shortname=" + shortname + ", longname="
				+ longname + ", address=" + address + ", tel=" + tel + ", fax="
				+ fax + ", taxno=" + taxno + ", status=" + status
				+ ", createdate=" + createdate + ", remark=" + remark
				+ ", customercontactModellist=" + customercontactModellist
				+ ", brandModellist=" + brandModellist
				+ ", customeraccountinformationModellist="
				+ customeraccountinformationModellist + "]";
	}

}
