package com.chori.model;

import java.util.Date;

public class GarmentkindModel {
	private String garmentkindcode;
	private String creator;
	private String description;
	private Date createdate;

	public GarmentkindModel(String garmentkindcode, String creator,
			String description, Date createdate) {
		super();
		this.garmentkindcode = garmentkindcode;
		this.creator = creator;
		this.description = description;
		this.createdate = createdate;
	}

	public GarmentkindModel() {
		super();
	}

	public String getGarmentkindcode() {
		return garmentkindcode;
	}

	public void setGarmentkindcode(String garmentkindcode) {
		this.garmentkindcode = garmentkindcode;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "GarmentKindModel [garmentkindcode=" + garmentkindcode
				+ ", creator=" + creator + ", description=" + description
				+ ", createdate=" + createdate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdate == null) ? 0 : createdate.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((garmentkindcode == null) ? 0 : garmentkindcode.hashCode());
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
		GarmentkindModel other = (GarmentkindModel) obj;
		if (createdate == null) {
			if (other.createdate != null)
				return false;
		} else if (!createdate.equals(other.createdate))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (garmentkindcode == null) {
			if (other.garmentkindcode != null)
				return false;
		} else if (!garmentkindcode.equals(other.garmentkindcode))
			return false;
		return true;
	}

}
