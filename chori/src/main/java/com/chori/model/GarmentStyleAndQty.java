package com.chori.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class GarmentStyleAndQty implements Serializable {
	private String garmentstylecode;
	private Integer pcs;
	private List<TypeAndQty> setTypeAndQty = new ArrayList<TypeAndQty>(0);

	public String getGarmentstylecode() {
		return garmentstylecode;
	}

	public void setGarmentstylecode(String garmentstylecode) {
		this.garmentstylecode = garmentstylecode;
	}

	public Integer getPcs() {
		return pcs;
	}

	public void setPcs(Integer pcs) {
		this.pcs = pcs;
	}

	public List<TypeAndQty> getSetSizeAndQty() {
		return setTypeAndQty;
	}

	public void setSetSizeAndQty(List<TypeAndQty> setSizeAndQty) {
		this.setTypeAndQty = setSizeAndQty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((garmentstylecode == null) ? 0 : garmentstylecode.hashCode());
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
		GarmentStyleAndQty other = (GarmentStyleAndQty) obj;
		if (garmentstylecode == null) {
			if (other.garmentstylecode != null)
				return false;
		} else if (!garmentstylecode.equals(other.garmentstylecode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GarmentStyleAndQty [garmentstylecode=" + garmentstylecode
				+ ", pcs=" + pcs + ", setSizeAndQty=" + setTypeAndQty + "]";
	}

	public GarmentStyleAndQty(String garmentstylecode, Integer pcs,
			List<TypeAndQty> setSizeAndQty) {
		super();
		this.garmentstylecode = garmentstylecode;
		this.pcs = pcs;
		this.setTypeAndQty = setSizeAndQty;
	}

	public GarmentStyleAndQty() {
		super();
		// TODO Auto-generated constructor stub
	}
}
