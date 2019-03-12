package com.chori.model;

import java.util.Date;

import com.chori.entity.User;

public class PackingguideModel {
	private String packingguidecode;
	private String creator;
	private String description;
	private Date createdate;
	public PackingguideModel(String packingguidecode, String creator, String description, Date createdate) {
		super();
		this.packingguidecode = packingguidecode;
		this.creator = creator;
		this.description = description;
		this.createdate = createdate;
	}
	
	public PackingguideModel() {
		super();
	}

	public String getPackingguidecode() {
		return packingguidecode;
	}

	public void setPackingguidecode(String packingguidecode) {
		this.packingguidecode = packingguidecode;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdate == null) ? 0 : createdate.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((packingguidecode == null) ? 0 : packingguidecode.hashCode());
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
		PackingguideModel other = (PackingguideModel) obj;
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
		if (packingguidecode == null) {
			if (other.packingguidecode != null)
				return false;
		} else if (!packingguidecode.equals(other.packingguidecode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PackingguideModel [packingguidecode=" + packingguidecode + ", creator=" + creator + ", description="
				+ description + ", createdate=" + createdate + "]";
	}
	
	
}
