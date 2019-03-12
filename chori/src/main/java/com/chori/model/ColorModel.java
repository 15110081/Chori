package com.chori.model;

import java.util.Date;

public class ColorModel {
	private String colorcode;
	private String creator;
	private String description;
	private Date createdate;

	public ColorModel(String colorcode, String creator, String description,
			Date create) {
		super();
		this.colorcode = colorcode;
		this.creator = creator;
		this.description = description;
		this.createdate = createdate;
	}

	public ColorModel() {
		super();
	}

	public String getColorcode() {
		return colorcode;
	}

	public void setColorcode(String colorcode) {
		this.colorcode = colorcode;
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
		return "ColorModel [colorcode=" + colorcode + ", creator=" + creator
				+ ", description=" + description + ", createdate=" + createdate
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((colorcode == null) ? 0 : colorcode.hashCode());
		result = prime * result
				+ ((createdate == null) ? 0 : createdate.hashCode());
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
		ColorModel other = (ColorModel) obj;
		if (colorcode == null) {
			if (other.colorcode != null)
				return false;
		} else if (!colorcode.equals(other.colorcode))
			return false;
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
		return true;
	}

}
