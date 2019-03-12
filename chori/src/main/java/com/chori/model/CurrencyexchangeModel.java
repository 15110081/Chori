package com.chori.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
public class CurrencyexchangeModel implements Serializable {
	private int currencyexchangecode;
	private String currencycodesource;
	private String currencycodedestination;
	private Double amount;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createdate;
	private Date exchangedate;

	public CurrencyexchangeModel(int currencyexchangecode,
			String currencycodesource, String currencycodedestination,
			Double amount, Date createdate, Date exchangedate) {
		super();
		this.currencyexchangecode = currencyexchangecode;
		this.currencycodesource = currencycodesource;
		this.currencycodedestination = currencycodedestination;
		this.amount = amount;
		this.createdate = createdate;
		this.exchangedate = exchangedate;
	}

	public CurrencyexchangeModel() {
		super();
	}

	public int getCurrencyexchangecode() {
		return currencyexchangecode;
	}

	public void setCurrencyexchangecode(int currencyexchangecode) {
		this.currencyexchangecode = currencyexchangecode;
	}

	public String getCurrencycodesource() {
		return currencycodesource;
	}

	public void setCurrencycodesource(String currencycodesource) {
		this.currencycodesource = currencycodesource;
	}

	public String getCurrencycodedestination() {
		return currencycodedestination;
	}

	public void setCurrencycodedestination(String currencycodedestination) {
		this.currencycodedestination = currencycodedestination;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double double1) {
		this.amount = double1;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getExchangedate() {
		return exchangedate;
	}

	public void setExchangedate(Date exchangedate) {
		this.exchangedate = exchangedate;
	}

	@Override
	public String toString() {
		return "CurrencyexchangeModel [currencyexchangecode="
				+ currencyexchangecode + ", currencycodesource="
				+ currencycodesource + ", currencycodedestination="
				+ currencycodedestination + ", amount=" + amount
				+ ", createdate=" + createdate + ", exchangedate="
				+ exchangedate + "]";
	}
}
