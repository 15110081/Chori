package com.chori.model;

import java.io.Serializable;
import java.util.Date;

public class CtnrtypeModel implements Serializable {
	private String ctnrcode;
	private String description;
	private String creator;
	private Date createdate;

	public String getCtnrcode() {
		return ctnrcode;
	}

	public void setCtnrcode(String ctnrcode) {
		this.ctnrcode = ctnrcode;
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

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public CtnrtypeModel(String ctnrcode, String description, String creator,
			Date createdate) {
		super();
		this.ctnrcode = ctnrcode;
		this.description = description;
		this.creator = creator;
		this.createdate = createdate;
	}

	public CtnrtypeModel() {
		super();
	}

	@Override
	public String toString() {
		return "CtnrtypeModel [ctnrcode=" + ctnrcode + ", description="
				+ description + ", creator=" + creator + ", createdate="
				+ createdate + "]";
	}

}
