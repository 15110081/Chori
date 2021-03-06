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
 * Factory generated by hbm2java
 */
@Entity
@Table(name = "factory")
public class Factory implements java.io.Serializable {

	private String factorycode;
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
	private Set<Fabricinformation> fabricinformations = new HashSet<Fabricinformation>(0);
	private Set<Orderinternalaccessory> orderinternalaccessories = new HashSet<Orderinternalaccessory>(0);
	private Set<Accessoryconsumption> accessoryconsumptions = new HashSet<Accessoryconsumption>(0);
	private Set<Externalaccessorystock> externalaccessorystocks = new HashSet<Externalaccessorystock>(0);
	private Set<Factoryaccountinformation> factoryaccountinformations = new HashSet<Factoryaccountinformation>(0);
	private Set<Garmentstyle> garmentstyles = new HashSet<Garmentstyle>(0);
	private Set<Factorycontact> factorycontacts = new HashSet<Factorycontact>(0);
	private Set<Orderexternalaccessory> orderexternalaccessories = new HashSet<Orderexternalaccessory>(0);
	private Set<Pi> pis = new HashSet<Pi>(0);

	public Factory() {
	}

	public Factory(String factorycode, String shortname) {
		this.factorycode = factorycode;
		this.shortname = shortname;
	}

	public Factory(String factorycode, User user, String shortname, String longname, String address, String tel,
			String fax, String taxno, String status, Date createdate, String remark,
			Set<Fabricinformation> fabricinformations, Set<Orderinternalaccessory> orderinternalaccessories,
			Set<Accessoryconsumption> accessoryconsumptions, Set<Externalaccessorystock> externalaccessorystocks,
			Set<Factoryaccountinformation> factoryaccountinformations, Set<Garmentstyle> garmentstyles,
			Set<Factorycontact> factorycontacts, Set<Orderexternalaccessory> orderexternalaccessories, Set<Pi> pis) {
		this.factorycode = factorycode;
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
		this.fabricinformations = fabricinformations;
		this.orderinternalaccessories = orderinternalaccessories;
		this.accessoryconsumptions = accessoryconsumptions;
		this.externalaccessorystocks = externalaccessorystocks;
		this.factoryaccountinformations = factoryaccountinformations;
		this.garmentstyles = garmentstyles;
		this.factorycontacts = factorycontacts;
		this.orderexternalaccessories = orderexternalaccessories;
		this.pis = pis;
	}

	@Id

	@Column(name = "FACTORYCODE", unique = true, nullable = false, length = 50)
	public String getFactorycode() {
		return this.factorycode;
	}

	public void setFactorycode(String factorycode) {
		this.factorycode = factorycode;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "factory")
	public Set<Fabricinformation> getFabricinformations() {
		return this.fabricinformations;
	}

	public void setFabricinformations(Set<Fabricinformation> fabricinformations) {
		this.fabricinformations = fabricinformations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "factory")
	public Set<Orderinternalaccessory> getOrderinternalaccessories() {
		return this.orderinternalaccessories;
	}

	public void setOrderinternalaccessories(Set<Orderinternalaccessory> orderinternalaccessories) {
		this.orderinternalaccessories = orderinternalaccessories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "factory")
	public Set<Accessoryconsumption> getAccessoryconsumptions() {
		return this.accessoryconsumptions;
	}

	public void setAccessoryconsumptions(Set<Accessoryconsumption> accessoryconsumptions) {
		this.accessoryconsumptions = accessoryconsumptions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "factory")
	public Set<Externalaccessorystock> getExternalaccessorystocks() {
		return this.externalaccessorystocks;
	}

	public void setExternalaccessorystocks(Set<Externalaccessorystock> externalaccessorystocks) {
		this.externalaccessorystocks = externalaccessorystocks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "factory")
	public Set<Factoryaccountinformation> getFactoryaccountinformations() {
		return this.factoryaccountinformations;
	}

	public void setFactoryaccountinformations(Set<Factoryaccountinformation> factoryaccountinformations) {
		this.factoryaccountinformations = factoryaccountinformations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "factory")
	public Set<Garmentstyle> getGarmentstyles() {
		return this.garmentstyles;
	}

	public void setGarmentstyles(Set<Garmentstyle> garmentstyles) {
		this.garmentstyles = garmentstyles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "factory")
	public Set<Factorycontact> getFactorycontacts() {
		return this.factorycontacts;
	}

	public void setFactorycontacts(Set<Factorycontact> factorycontacts) {
		this.factorycontacts = factorycontacts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "factory")
	public Set<Orderexternalaccessory> getOrderexternalaccessories() {
		return this.orderexternalaccessories;
	}

	public void setOrderexternalaccessories(Set<Orderexternalaccessory> orderexternalaccessories) {
		this.orderexternalaccessories = orderexternalaccessories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "factory")
	public Set<Pi> getPis() {
		return this.pis;
	}

	public void setPis(Set<Pi> pis) {
		this.pis = pis;
	}

}
