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
 * Garmentconsumption generated by hbm2java
 */
@Entity
@Table(name = "garmentconsumption")
public class Garmentconsumption implements java.io.Serializable {

	private Integer garmentconsumptioncode;
	private Customer customer;
	private Garmentstyle garmentstyle;
	private Size size;
	private User user;
	private String description;
	private Date createdate;
	private Set<Garmentconsumptiondetail> garmentconsumptiondetails = new HashSet<Garmentconsumptiondetail>(0);

	public Garmentconsumption() {
	}

	public Garmentconsumption(Customer customer, Garmentstyle garmentstyle, Size size, User user, String description,
			Date createdate, Set<Garmentconsumptiondetail> garmentconsumptiondetails) {
		this.customer = customer;
		this.garmentstyle = garmentstyle;
		this.size = size;
		this.user = user;
		this.description = description;
		this.createdate = createdate;
		this.garmentconsumptiondetails = garmentconsumptiondetails;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "GARMENTCONSUMPTIONCODE", unique = true, nullable = false)
	public Integer getGarmentconsumptioncode() {
		return this.garmentconsumptioncode;
	}

	public void setGarmentconsumptioncode(Integer garmentconsumptioncode) {
		this.garmentconsumptioncode = garmentconsumptioncode;
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
	@JoinColumn(name = "GARMENTSTYLECODE")
	public Garmentstyle getGarmentstyle() {
		return this.garmentstyle;
	}

	public void setGarmentstyle(Garmentstyle garmentstyle) {
		this.garmentstyle = garmentstyle;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SIZECODE")
	public Size getSize() {
		return this.size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "DESCRIPTION", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE", length = 19)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garmentconsumption")
	public Set<Garmentconsumptiondetail> getGarmentconsumptiondetails() {
		return this.garmentconsumptiondetails;
	}

	public void setGarmentconsumptiondetails(Set<Garmentconsumptiondetail> garmentconsumptiondetails) {
		this.garmentconsumptiondetails = garmentconsumptiondetails;
	}

}
