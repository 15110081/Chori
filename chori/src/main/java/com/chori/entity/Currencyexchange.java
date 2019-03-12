package com.chori.entity;
// Generated Dec 8, 2016 6:21:17 PM by Hibernate Tools 5.1.0.Beta1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Currencyexchange generated by hbm2java
 */
@Entity
@Table(name = "currencyexchange")
public class Currencyexchange implements java.io.Serializable {

	private Integer currencyexcode;
	private Currency currencyByCurrencycodesource;
	private Currency currencyByCurrencycodedestination;
	private Double amount;
	private Date exchangedate;
	private Date createdate;

	public Currencyexchange() {
	}

	public Currencyexchange(Currency currencyByCurrencycodesource, Currency currencyByCurrencycodedestination,
			Double amount, Date exchangedate, Date createdate) {
		this.currencyByCurrencycodesource = currencyByCurrencycodesource;
		this.currencyByCurrencycodedestination = currencyByCurrencycodedestination;
		this.amount = amount;
		this.exchangedate = exchangedate;
		this.createdate = createdate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "CURRENCYEXCODE", unique = true, nullable = false)
	public Integer getCurrencyexcode() {
		return this.currencyexcode;
	}

	public void setCurrencyexcode(Integer currencyexcode) {
		this.currencyexcode = currencyexcode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCYCODESOURCE")
	public Currency getCurrencyByCurrencycodesource() {
		return this.currencyByCurrencycodesource;
	}

	public void setCurrencyByCurrencycodesource(Currency currencyByCurrencycodesource) {
		this.currencyByCurrencycodesource = currencyByCurrencycodesource;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCYCODEDESTINATION")
	public Currency getCurrencyByCurrencycodedestination() {
		return this.currencyByCurrencycodedestination;
	}

	public void setCurrencyByCurrencycodedestination(Currency currencyByCurrencycodedestination) {
		this.currencyByCurrencycodedestination = currencyByCurrencycodedestination;
	}

	@Column(name = "AMOUNT", precision = 12, scale = 0)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXCHANGEDATE", length = 10)
	public Date getExchangedate() {
		return this.exchangedate;
	}

	public void setExchangedate(Date exchangedate) {
		this.exchangedate = exchangedate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE", length = 19)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

}