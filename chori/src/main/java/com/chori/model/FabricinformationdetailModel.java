package com.chori.model;

import java.util.Date;

public class FabricinformationdetailModel {
	private String fabricno;
	private String colorcode;
	private String colorName;
	private String user;
	private Float unitprice;
	private Double yardinbl;
	private String imgurl;
	private Date createdate;

	public String getFabricno() {
		return fabricno;
	}

	public void setFabricno(String fabricno) {
		this.fabricno = fabricno;
	}

	public String getColorcode() {
		return colorcode;
	}

	public void setColorcode(String colorcode) {
		this.colorcode = colorcode;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Float getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(Float unitprice) {
		this.unitprice = unitprice;
	}

	public Double getYardinbl() {
		return yardinbl;
	}

	public void setYardinbl(Double yardinbl) {
		this.yardinbl = yardinbl;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public FabricinformationdetailModel(String fabricno, String colorcode,
			String user, Float unitprice, Double yardinbl, String imgurl,
			Date createdate) {
		super();
		this.fabricno = fabricno;
		this.colorcode = colorcode;
		this.user = user;
		this.unitprice = unitprice;
		this.yardinbl = yardinbl;
		this.imgurl = imgurl;
		this.createdate = createdate;
	}

	public FabricinformationdetailModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "FabricinformationdetailModel [fabricno=" + fabricno
				+ ", colorcode=" + colorcode + ", user=" + user
				+ ", unitprice=" + unitprice + ", yardinbl=" + yardinbl
				+ ", imgurl=" + imgurl + ", createdate=" + createdate + "]";
	}

}
