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
 * Size generated by hbm2java
 */
@Entity
@Table(name = "size")
public class Size implements java.io.Serializable {

	private Integer sizecode;
	private Customer customer;
	private Garmentkind garmentkind;
	private Type type;
	private User user;
	private String sizename;
	private Date createdate;
	private Set<Garmentstyleaccessorydetail> garmentstyleaccessorydetails = new HashSet<Garmentstyleaccessorydetail>(0);
	private Set<Piassigninternalaccessoriesdetail> piassigninternalaccessoriesdetails = new HashSet<Piassigninternalaccessoriesdetail>(
			0);
	private Set<Rfpidetail> rfpidetails = new HashSet<Rfpidetail>(0);
	private Set<Garmentconsumption> garmentconsumptions = new HashSet<Garmentconsumption>(0);
	private Set<Fpidetail> fpidetails = new HashSet<Fpidetail>(0);
	private Set<Pigriddetail> pigriddetails = new HashSet<Pigriddetail>(0);

	public Size() {
	}

	public Size(Customer customer, Garmentkind garmentkind, String sizename) {
		this.customer = customer;
		this.garmentkind = garmentkind;
		this.sizename = sizename;
	}

	public Size(Customer customer, Garmentkind garmentkind, Type type, User user, String sizename, Date createdate,
			Set<Garmentstyleaccessorydetail> garmentstyleaccessorydetails,
			Set<Piassigninternalaccessoriesdetail> piassigninternalaccessoriesdetails, Set<Rfpidetail> rfpidetails,
			Set<Garmentconsumption> garmentconsumptions, Set<Fpidetail> fpidetails, Set<Pigriddetail> pigriddetails) {
		this.customer = customer;
		this.garmentkind = garmentkind;
		this.type = type;
		this.user = user;
		this.sizename = sizename;
		this.createdate = createdate;
		this.garmentstyleaccessorydetails = garmentstyleaccessorydetails;
		this.piassigninternalaccessoriesdetails = piassigninternalaccessoriesdetails;
		this.rfpidetails = rfpidetails;
		this.garmentconsumptions = garmentconsumptions;
		this.fpidetails = fpidetails;
		this.pigriddetails = pigriddetails;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "SIZECODE", unique = true, nullable = false)
	public Integer getSizecode() {
		return this.sizecode;
	}

	public void setSizecode(Integer sizecode) {
		this.sizecode = sizecode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMERCODE", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GARMENTKINDCODE", nullable = false)
	public Garmentkind getGarmentkind() {
		return this.garmentkind;
	}

	public void setGarmentkind(Garmentkind garmentkind) {
		this.garmentkind = garmentkind;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPECODE")
	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "SIZENAME", nullable = false, length = 20)
	public String getSizename() {
		return this.sizename;
	}

	public void setSizename(String sizename) {
		this.sizename = sizename;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE", length = 19)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "size")
	public Set<Garmentstyleaccessorydetail> getGarmentstyleaccessorydetails() {
		return this.garmentstyleaccessorydetails;
	}

	public void setGarmentstyleaccessorydetails(Set<Garmentstyleaccessorydetail> garmentstyleaccessorydetails) {
		this.garmentstyleaccessorydetails = garmentstyleaccessorydetails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "size")
	public Set<Piassigninternalaccessoriesdetail> getPiassigninternalaccessoriesdetails() {
		return this.piassigninternalaccessoriesdetails;
	}

	public void setPiassigninternalaccessoriesdetails(
			Set<Piassigninternalaccessoriesdetail> piassigninternalaccessoriesdetails) {
		this.piassigninternalaccessoriesdetails = piassigninternalaccessoriesdetails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "size")
	public Set<Rfpidetail> getRfpidetails() {
		return this.rfpidetails;
	}

	public void setRfpidetails(Set<Rfpidetail> rfpidetails) {
		this.rfpidetails = rfpidetails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "size")
	public Set<Garmentconsumption> getGarmentconsumptions() {
		return this.garmentconsumptions;
	}

	public void setGarmentconsumptions(Set<Garmentconsumption> garmentconsumptions) {
		this.garmentconsumptions = garmentconsumptions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "size")
	public Set<Fpidetail> getFpidetails() {
		return this.fpidetails;
	}

	public void setFpidetails(Set<Fpidetail> fpidetails) {
		this.fpidetails = fpidetails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "size")
	public Set<Pigriddetail> getPigriddetails() {
		return this.pigriddetails;
	}

	public void setPigriddetails(Set<Pigriddetail> pigriddetails) {
		this.pigriddetails = pigriddetails;
	}

}