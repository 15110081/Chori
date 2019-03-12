package com.chori.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TypeAndQty implements Serializable {
	private String typecode;
	private Integer pcs;

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	public Integer getPcs() {
		return pcs;
	}

	public void setPcs(Integer pcs) {
		this.pcs = pcs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((typecode == null) ? 0 : typecode.hashCode());
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
		TypeAndQty other = (TypeAndQty) obj;
		if (typecode == null) {
			if (other.typecode != null)
				return false;
		} else if (!typecode.equals(other.typecode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TypeAndQty [typecode=" + typecode + ", pcs=" + pcs + "]";
	}

	public TypeAndQty(String typecode, Integer pcs) {
		super();
		this.typecode = typecode;
		this.pcs = pcs;
	}

	public TypeAndQty() {
		super();
		// TODO Auto-generated constructor stub
	}
}
