package com.chori.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ColorAndQty implements Serializable {
	private String colorcode;
	private Integer pcs;
	private List<GarmentStyleAndQty> setGarmentStyleAndQty = new ArrayList<GarmentStyleAndQty>(
			0);

	public String getColorcode() {
		return colorcode;
	}

	public void setColorcode(String colorcode) {
		this.colorcode = colorcode;
	}

	public Integer getPcs() {
		return pcs;
	}

	public void setPcs(Integer pcs) {
		this.pcs = pcs;
	}

	public List<GarmentStyleAndQty> getSetGarmentStyleAndQty() {
		return setGarmentStyleAndQty;
	}

	public void setSetGarmentStyleAndQty(
			List<GarmentStyleAndQty> setGarmentStyleAndQty) {
		this.setGarmentStyleAndQty = setGarmentStyleAndQty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((colorcode == null) ? 0 : colorcode.hashCode());
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
		ColorAndQty other = (ColorAndQty) obj;
		if (colorcode == null) {
			if (other.colorcode != null)
				return false;
		} else if (!colorcode.equals(other.colorcode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ColorAndQty [colorcode=" + colorcode + ", pcs=" + pcs
				+ ", setGarmentStyleAndQty=" + setGarmentStyleAndQty + "]";
	}

	public ColorAndQty(String colorcode, Integer pcs,
			List<GarmentStyleAndQty> setGarmentStyleAndQty) {
		super();
		this.colorcode = colorcode;
		this.pcs = pcs;
		this.setGarmentStyleAndQty = setGarmentStyleAndQty;
	}

	public ColorAndQty() {
		super();
		// TODO Auto-generated constructor stub
	}
}
