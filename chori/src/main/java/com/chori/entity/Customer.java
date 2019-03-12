package com.chori.entity;
// Generated Dec 8, 2016 6:21:17 PM by Hibernate Tools 5.1.0.Beta1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Customer generated by hbm2java
 */
@Entity
@Table(name = "customer")
public class Customer implements java.io.Serializable {

	private String customercode;
	private User user;
	private String shortname;
	private String longname;
	private String address;
	private String tel;
	private String fax;
	private String taxno;
	private String status;
	private Date createdate;
	private String remark;
	private Set<Customeraccountinformation> customeraccountinformations = new HashSet<Customeraccountinformation>(0);
	private Set<Pi> pisForConsigneee = new HashSet<Pi>(0);
	private Set<Size> sizes = new HashSet<Size>(0);
	private Set<Fabricinformation> fabricinformations = new HashSet<Fabricinformation>(0);
	private Set<Garmentconsumption> garmentconsumptions = new HashSet<Garmentconsumption>(0);
	private Set<Pi> pisForCustomer1code = new HashSet<Pi>(0);
	private Set<Customercontact> customercontacts = new HashSet<Customercontact>(0);
	private Set<Garmentstyle> garmentstyles = new HashSet<Garmentstyle>(0);
	private Set<Brand> brands = new HashSet<Brand>(0);

	public Customer() {
	}

	public Customer(String customercode, String shortname) {
		this.customercode = customercode;
		this.shortname = shortname;
	}

	public Customer(String customercode, User user, String shortname, String longname, String address, String tel,
			String fax, String taxno, String status, Date createdate, String remark,
			Set<Customeraccountinformation> customeraccountinformations, Set<Pi> pisForConsigneee, Set<Size> sizes,
			Set<Fabricinformation> fabricinformations, Set<Garmentconsumption> garmentconsumptions,
			Set<Pi> pisForCustomer1code, Set<Customercontact> customercontacts, Set<Garmentstyle> garmentstyles,
			Set<Brand> brands) {
		this.customercode = customercode;
		this.user = user;
		this.shortname = shortname;
		this.longname = longname;
		this.address = address;
		this.tel = tel;
		this.fax = fax;
		this.taxno = taxno;
		this.status = status;
		this.createdate = createdate;
		this.remark = remark;
		this.customeraccountinformations = customeraccountinformations;
		this.pisForConsigneee = pisForConsigneee;
		this.sizes = sizes;
		this.fabricinformations = fabricinformations;
		this.garmentconsumptions = garmentconsumptions;
		this.pisForCustomer1code = pisForCustomer1code;
		this.customercontacts = customercontacts;
		this.garmentstyles = garmentstyles;
		this.brands = brands;
	}

	@Id

	@Column(name = "CUSTOMERCODE", unique = true, nullable = false, length = 50)
	public String getCustomercode() {
		return this.customercode;
	}

	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "SHORTNAME", nullable = false, length = 50)
	public String getShortname() {
		return this.shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	@Column(name = "LONGNAME", length = 100)
	public String getLongname() {
		return this.longname;
	}

	public void setLongname(String longname) {
		this.longname = longname;
	}

	@Column(name = "ADDRESS", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "TEL", length = 50)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "FAX", length = 50)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "TAXNO", length = 50)
	public String getTaxno() {
		return this.taxno;
	}

	public void setTaxno(String taxno) {
		this.taxno = taxno;
	}

	@Column(name = "STATUS", length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE", length = 19)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Column(name = "REMARK", length = 500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<Customeraccountinformation> getCustomeraccountinformations() {
		return this.customeraccountinformations;
	}

	public void setCustomeraccountinformations(Set<Customeraccountinformation> customeraccountinformations) {
		this.customeraccountinformations = customeraccountinformations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByConsigneee")
	public Set<Pi> getPisForConsigneee() {
		return this.pisForConsigneee;
	}

	public void setPisForConsigneee(Set<Pi> pisForConsigneee) {
		this.pisForConsigneee = pisForConsigneee;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<Size> getSizes() {
		return this.sizes;
	}

	public void setSizes(Set<Size> sizes) {
		this.sizes = sizes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<Fabricinformation> getFabricinformations() {
		return this.fabricinformations;
	}

	public void setFabricinformations(Set<Fabricinformation> fabricinformations) {
		this.fabricinformations = fabricinformations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<Garmentconsumption> getGarmentconsumptions() {
		return this.garmentconsumptions;
	}

	public void setGarmentconsumptions(Set<Garmentconsumption> garmentconsumptions) {
		this.garmentconsumptions = garmentconsumptions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByCustomer1code")
	public Set<Pi> getPisForCustomer1code() {
		return this.pisForCustomer1code;
	}

	public void setPisForCustomer1code(Set<Pi> pisForCustomer1code) {
		this.pisForCustomer1code = pisForCustomer1code;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<Customercontact> getCustomercontacts() {
		return this.customercontacts;
	}

	public void setCustomercontacts(Set<Customercontact> customercontacts) {
		this.customercontacts = customercontacts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<Garmentstyle> getGarmentstyles() {
		return this.garmentstyles;
	}

	public void setGarmentstyles(Set<Garmentstyle> garmentstyles) {
		this.garmentstyles = garmentstyles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<Brand> getBrands() {
		return this.brands;
	}

	public void setBrands(Set<Brand> brands) {
		this.brands = brands;
	}

}