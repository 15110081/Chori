package com.chori.entity;
// Generated Dec 8, 2016 6:21:17 PM by Hibernate Tools 5.1.0.Beta1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Brand generated by hbm2java
 */
@Entity
@Table(name = "brand")
public class Brand implements java.io.Serializable {

	private Integer brandcode;
	private Customer customer;
	private User user;
	private String brandname;
	private Date createdate;
	private Set<Pi> pis = new HashSet<Pi>(0);

	public Brand() {
	}

	public Brand(Customer customer, User user, String brandname, Date createdate, Set<Pi> pis) {
		this.customer = customer;
		this.user = user;
		this.brandname = brandname;
		this.createdate = createdate;
		this.pis = pis;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "BRANDCODE", unique = true, nullable = false)
	public Integer getBrandcode() {
		return this.brandcode;
	}

	public void setBrandcode(Integer brandcode) {
		this.brandcode = brandcode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMERCODE")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "BRANDNAME", length = 50)
	public String getBrandname() {
		return this.brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE", length = 19)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
	public Set<Pi> getPis() {
		return this.pis;
	}

	public void setPis(Set<Pi> pis) {
		this.pis = pis;
	}

}
