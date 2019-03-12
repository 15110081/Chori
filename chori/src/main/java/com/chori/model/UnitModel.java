package com.chori.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class UnitModel implements Serializable {
	private String unitcode;
	private String name;
	private Date createdate;

	public UnitModel() {
		super();
	}

	public UnitModel(String unitcode, String name, Date createdate) {
		super();
		this.unitcode = unitcode;
		this.name = name;
		this.createdate = createdate;
	}

	public String getUnitcode() {
		return unitcode;
	}

	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
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
				+ ((createdate == null) ? 0 : createdate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((unitcode == null) ? 0 : unitcode.hashCode());
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
		UnitModel other = (UnitModel) obj;
		if (createdate == null) {
			if (other.createdate != null)
				return false;
		} else if (!createdate.equals(other.createdate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (unitcode == null) {
			if (other.unitcode != null)
				return false;
		} else if (!unitcode.equals(other.unitcode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UnitModel [unitcode=" + unitcode + ", name=" + name
				+ ", createdate=" + createdate + "]";
	}

}