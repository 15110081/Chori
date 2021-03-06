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
 * Currency generated by hbm2java
 */
@Entity
@Table(name = "currency")
public class Currency implements java.io.Serializable {

	private String currencycode;
	private User user;
	private String name;
	private Date createdate;
	private Set<Currencyexchange> currencyexchangesForCurrencycodesource = new HashSet<Currencyexchange>(0);
	private Set<Currencyexchange> currencyexchangesForCurrencycodedestination = new HashSet<Currencyexchange>(0);
	private Set<Fabricinformation> fabricinformations = new HashSet<Fabricinformation>(0);
	private Set<Accessoryprice> accessoryprices = new HashSet<Accessoryprice>(0);

	public Currency() {
	}

	public Currency(String currencycode) {
		this.currencycode = currencycode;
	}

	public Currency(String currencycode, User user, String name, Date createdate,
			Set<Currencyexchange> currencyexchangesForCurrencycodesource,
			Set<Currencyexchange> currencyexchangesForCurrencycodedestination,
			Set<Fabricinformation> fabricinformations, Set<Accessoryprice> accessoryprices) {
		this.currencycode = currencycode;
		this.user = user;
		this.name = name;
		this.createdate = createdate;
		this.currencyexchangesForCurrencycodesource = currencyexchangesForCurrencycodesource;
		this.currencyexchangesForCurrencycodedestination = currencyexchangesForCurrencycodedestination;
		this.fabricinformations = fabricinformations;
		this.accessoryprices = accessoryprices;
	}

	@Id

	@Column(name = "CURRENCYCODE", unique = true, nullable = false, length = 20)
	public String getCurrencycode() {
		return this.currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE", length = 19)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "currencyByCurrencycodesource")
	public Set<Currencyexchange> getCurrencyexchangesForCurrencycodesource() {
		return this.currencyexchangesForCurrencycodesource;
	}

	public void setCurrencyexchangesForCurrencycodesource(
			Set<Currencyexchange> currencyexchangesForCurrencycodesource) {
		this.currencyexchangesForCurrencycodesource = currencyexchangesForCurrencycodesource;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "currencyByCurrencycodedestination")
	public Set<Currencyexchange> getCurrencyexchangesForCurrencycodedestination() {
		return this.currencyexchangesForCurrencycodedestination;
	}

	public void setCurrencyexchangesForCurrencycodedestination(
			Set<Currencyexchange> currencyexchangesForCurrencycodedestination) {
		this.currencyexchangesForCurrencycodedestination = currencyexchangesForCurrencycodedestination;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "currency")
	public Set<Fabricinformation> getFabricinformations() {
		return this.fabricinformations;
	}

	public void setFabricinformations(Set<Fabricinformation> fabricinformations) {
		this.fabricinformations = fabricinformations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "currency")
	public Set<Accessoryprice> getAccessoryprices() {
		return this.accessoryprices;
	}

	public void setAccessoryprices(Set<Accessoryprice> accessoryprices) {
		this.accessoryprices = accessoryprices;
	}

}
