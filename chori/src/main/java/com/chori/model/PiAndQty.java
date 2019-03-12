package com.chori.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class PiAndQty implements Serializable {
	private String lotnumber;
	private Integer pigridcode;
	private Integer pcs;
	private List<ColorAndQty> setColorAndQty = new ArrayList<ColorAndQty>(0);

	public String getLotnumber() {
		return lotnumber;
	}

	public void setLotnumber(String lotnumber) {
		this.lotnumber = lotnumber;
	}

	public Integer getPigridcode() {
		return pigridcode;
	}

	public void setPigridcode(Integer pigridcode) {
		this.pigridcode = pigridcode;
	}

	public Integer getPcs() {
		return pcs;
	}

	public void setPcs(Integer pcs) {
		this.pcs = pcs;
	}

	public List<ColorAndQty> getSetColorAndQty() {
		return setColorAndQty;
	}

	public void setSetColorAndQty(List<ColorAndQty> setColorAndQty) {
		this.setColorAndQty = setColorAndQty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((lotnumber == null) ? 0 : lotnumber.hashCode());
		result = prime * result
				+ ((pigridcode == null) ? 0 : pigridcode.hashCode());
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
		PiAndQty other = (PiAndQty) obj;
		if (lotnumber == null) {
			if (other.lotnumber != null)
				return false;
		} else if (!lotnumber.equals(other.lotnumber))
			return false;
		if (pigridcode == null) {
			if (other.pigridcode != null)
				return false;
		} else if (!pigridcode.equals(other.pigridcode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PiAndQty [lotnumber=" + lotnumber + ", pigridcode="
				+ pigridcode + ", pcs=" + pcs + ", setColorAndQty="
				+ setColorAndQty + "]";
	}

	public PiAndQty(String lotnumber, Integer pigridcode, Integer pcs,
			List<ColorAndQty> setColorAndQty) {
		super();
		this.lotnumber = lotnumber;
		this.pigridcode = pigridcode;
		this.pcs = pcs;
		this.setColorAndQty = setColorAndQty;
	}

	public PiAndQty() {
		super();
		// TODO Auto-generated constructor stub
	}
}
