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
 * Fabricsupplier generated by hbm2java
 */
@Entity
@Table(name = "fabricsupplier")
public class Fabricsupplier implements java.io.Serializable {

	private String fabricsupcode;
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
	private Set<Fabricsuppliercontact> fabricsuppliercontacts = new HashSet<Fabricsuppliercontact>(0);

	public Fabricsupplier() {
	}

	public Fabricsupplier(String fabricsupcode, String shortname) {
		this.fabricsupcode = fabricsupcode;
		this.shortname = shortname;
	}

	public Fabricsupplier(String fabricsupcode, User user, String shortname, String longname, String address,
			String tel, String fax, String taxno, String status, Date createdate, String remark,
			Set<Fabricinformation> fabricinformations, Set<Fabricsuppliercontact> fabricsuppliercontacts) {
		this.fabricsupcode = fabricsupcode;
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
		this.fabricsuppliercontacts = fabricsuppliercontacts;
	}

	@Id

	@Column(name = "FABRICSUPCODE", unique = true, nullable = false, length = 50)
	public String getFabricsupcode() {
		return this.fabricsupcode;
	}

	public void setFabricsupcode(String fabricsupcode) {
		this.fabricsupcode = fabricsupcode;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fabricsupplier")
	public Set<Fabricinformation> getFabricinformations() {
		return this.fabricinformations;
	}

	public void setFabricinformations(Set<Fabricinformation> fabricinformations) {
		this.fabricinformations = fabricinformations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fabricsupplier")
	public Set<Fabricsuppliercontact> getFabricsuppliercontacts() {
		return this.fabricsuppliercontacts;
	}

	public void setFabricsuppliercontacts(Set<Fabricsuppliercontact> fabricsuppliercontacts) {
		this.fabricsuppliercontacts = fabricsuppliercontacts;
	}

}
