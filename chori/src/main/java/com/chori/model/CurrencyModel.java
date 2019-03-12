package com.chori.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CurrencyModel implements Serializable {
	private String currencycode;
	private String creator;
	private String name;
	private Date createdate;

	public String getCurrencycode() {
		return currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((currencycode == null) ? 0 : currencycode.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrencyModel other = (CurrencyModel) obj;
		if (currencycode == null) {
			if (other.currencycode != null)
				return false;
		} else if (!currencycode.equals(other.currencycode))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CurrencyModel [currencycode=" + currencycode + ", creator="
				+ creator + ", name=" + name + ", createdate=" + createdate
				+ "]";
	}

	public CurrencyModel(String currencycode, String creator, String name,
			Date createdate) {
		super();
		this.currencycode = currencycode;
		this.creator = creator;
		this.name = name;
		this.createdate = createdate;
	}

	public CurrencyModel() {
		super();
		// TODO Auto-generated constructor stub
	}
}
