package com.chori.model;

import java.util.Date;

public class AccessoryGroupModel {
	private String accessorygroupCode;
	private String description;
	private String creator;
	private Date createdDate;

	public String getAccessorygroupCode() {
		return accessorygroupCode;
	}

	public void setAccessorygroupCode(String accessorygroupCode) {
		this.accessorygroupCode = accessorygroupCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public AccessoryGroupModel(String accessorygroupCode, String description,
			String creator, Date createdDate) {
		super();
		this.accessorygroupCode = accessorygroupCode;
		this.description = description;
		this.creator = creator;
		this.createdDate = createdDate;
	}

	public AccessoryGroupModel() {
		super();
	}

	@Override
	public String toString() {
		return "AccessoryGroup [accessorygroupCode=" + accessorygroupCode
				+ ", description=" + description + ", creator=" + creator
				+ ", createdDate=" + createdDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((accessorygroupCode == null) ? 0 : accessorygroupCode
						.hashCode());
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
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
		AccessoryGroupModel other = (AccessoryGroupModel) obj;
		if (accessorygroupCode == null) {
			if (other.accessorygroupCode != null)
				return false;
		} else if (!accessorygroupCode.equals(other.accessorygroupCode))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
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
		return true;
	}

}
