package com.chori.model;

public class PiGridDetailModel {
	private Integer pigriddetail;
	private String color;
	private String garmentstyle;
	private Integer garmentstylePcs;
	private Integer garmentstyleFaPcs;
	private Integer pigrid;
	private Integer totalPcs;
	private Integer totalFaPcs;
	private String colorName;
	private Integer colorPcs;
	private Integer colorFaPcs;
	private String imgUrl1;
	private String imgUrl2;
	private String imgUrl3;
	private String imgUrl4;
	private String imgUrl5;
	private String typecode;
	private Integer typePcs;
	private Integer typeFaPcs;
	private Integer sizecode;
	private String sizename;
	private Integer pcs;
	private Integer favalue;
	private String barCode;

	public Integer getFavalue() {
		return favalue;
	}

	public void setFavalue(Integer favalue) {
		this.favalue = favalue;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public PiGridDetailModel() {
		super();
	}

	public PiGridDetailModel(Integer pigriddetail) {
		super();
		this.pigriddetail = pigriddetail;
	}

	public PiGridDetailModel(Integer pigriddetail, String color,
			String garmentstyle, Integer pigrid) {
		super();
		this.pigriddetail = pigriddetail;
		this.color = color;
		this.garmentstyle = garmentstyle;
		this.pigrid = pigrid;
	}

	public Integer getPigriddetail() {
		return pigriddetail;
	}

	public void setPigriddetail(Integer pigriddetail) {
		this.pigriddetail = pigriddetail;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getGarmentstyle() {
		return garmentstyle;
	}

	public void setGarmentstyle(String garmentstyle) {
		this.garmentstyle = garmentstyle;
	}

	public Integer getPigrid() {
		return pigrid;
	}

	public void setPigrid(Integer pigrid) {
		this.pigrid = pigrid;
	}

	public Integer getTotalPcs() {
		return totalPcs;
	}

	public void setTotalPcs(Integer totalPcs) {
		this.totalPcs = totalPcs;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public Integer getColorPcs() {
		return colorPcs;
	}

	public void setColorPcs(Integer colorPcs) {
		this.colorPcs = colorPcs;
	}

	public String getImgUrl1() {
		return imgUrl1;
	}

	public void setImgUrl1(String imgUrl1) {
		this.imgUrl1 = imgUrl1;
	}

	public String getImgUrl2() {
		return imgUrl2;
	}

	public void setImgUrl2(String imgUrl2) {
		this.imgUrl2 = imgUrl2;
	}

	public String getImgUrl3() {
		return imgUrl3;
	}

	public void setImgUrl3(String imgUrl3) {
		this.imgUrl3 = imgUrl3;
	}

	public String getImgUrl4() {
		return imgUrl4;
	}

	public void setImgUrl4(String imgUrl4) {
		this.imgUrl4 = imgUrl4;
	}

	public String getImgUrl5() {
		return imgUrl5;
	}

	public void setImgUrl5(String imgUrl5) {
		this.imgUrl5 = imgUrl5;
	}

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	public Integer getTypePcs() {
		return typePcs;
	}

	public void setTypePcs(Integer typePcs) {
		this.typePcs = typePcs;
	}

	public Integer getSizecode() {
		return sizecode;
	}

	public void setSizecode(Integer sizecode) {
		this.sizecode = sizecode;
	}

	public String getSizename() {
		return sizename;
	}

	public void setSizename(String sizename) {
		this.sizename = sizename;
	}

	public Integer getPcs() {
		return pcs;
	}

	public void setPcs(Integer pcs) {
		this.pcs = pcs;
	}

	public Integer getGarmentstylePcs() {
		return garmentstylePcs;
	}

	public void setGarmentstylePcs(Integer garmentstylePcs) {
		this.garmentstylePcs = garmentstylePcs;
	}
	
	public Integer getFaValue() {
		return favalue;
	}

	public void setFaValue(Integer favalue) {
		this.favalue = favalue;
	}

	public Integer getGarmentstyleFaPcs() {
		return garmentstyleFaPcs;
	}

	public void setGarmentstyleFaPcs(Integer garmentstyleFaPcs) {
		this.garmentstyleFaPcs = garmentstyleFaPcs;
	}

	public Integer getTotalFaPcs() {
		return totalFaPcs;
	}

	public void setTotalFaPcs(Integer totalFaPcs) {
		this.totalFaPcs = totalFaPcs;
	}

	public Integer getColorFaPcs() {
		return colorFaPcs;
	}

	public void setColorFaPcs(Integer colorFaPcs) {
		this.colorFaPcs = colorFaPcs;
	}

	public Integer getTypeFaPcs() {
		return typeFaPcs;
	}

	public void setTypeFaPcs(Integer typeFaPcs) {
		this.typeFaPcs = typeFaPcs;
	}

	@Override
	public String toString() {
		return "PiGridDetailModel [pigriddetail=" + pigriddetail + ", color=" + color + ", garmentstyle=" + garmentstyle
				+ ", garmentstylePcs=" + garmentstylePcs + ", garmentstyleFaPcs=" + garmentstyleFaPcs + ", pigrid="
				+ pigrid + ", totalPcs=" + totalPcs + ", totalFaPcs=" + totalFaPcs + ", colorName=" + colorName
				+ ", colorPcs=" + colorPcs + ", colorFaPcs=" + colorFaPcs + ", imgUrl1=" + imgUrl1 + ", imgUrl2="
				+ imgUrl2 + ", imgUrl3=" + imgUrl3 + ", imgUrl4=" + imgUrl4 + ", imgUrl5=" + imgUrl5 + ", typecode="
				+ typecode + ", typePcs=" + typePcs + ", typeFaPcs=" + typeFaPcs + ", sizecode=" + sizecode
				+ ", sizename=" + sizename + ", pcs=" + pcs + ", favalue=" + favalue + ", barCode=" + barCode + "]";
	}

	public PiGridDetailModel(Integer pigriddetail, String color, String garmentstyle, Integer garmentstylePcs,
			Integer garmentstyleFaPcs, Integer pigrid, Integer totalPcs, Integer totalFaPcs, String colorName,
			Integer colorPcs, Integer colorFaPcs, String imgUrl1, String imgUrl2, String imgUrl3, String imgUrl4,
			String imgUrl5, String typecode, Integer typePcs, Integer typeFaPcs, Integer sizecode, String sizename,
			Integer pcs, Integer favalue, String barCode) {
		super();
		this.pigriddetail = pigriddetail;
		this.color = color;
		this.garmentstyle = garmentstyle;
		this.garmentstylePcs = garmentstylePcs;
		this.garmentstyleFaPcs = garmentstyleFaPcs;
		this.pigrid = pigrid;
		this.totalPcs = totalPcs;
		this.totalFaPcs = totalFaPcs;
		this.colorName = colorName;
		this.colorPcs = colorPcs;
		this.colorFaPcs = colorFaPcs;
		this.imgUrl1 = imgUrl1;
		this.imgUrl2 = imgUrl2;
		this.imgUrl3 = imgUrl3;
		this.imgUrl4 = imgUrl4;
		this.imgUrl5 = imgUrl5;
		this.typecode = typecode;
		this.typePcs = typePcs;
		this.typeFaPcs = typeFaPcs;
		this.sizecode = sizecode;
		this.sizename = sizename;
		this.pcs = pcs;
		this.favalue = favalue;
		this.barCode = barCode;
	}

//	public Integer getFavalue() {
//		return favalue;
//	}
//
//	public void setFavalue(Integer favalue) {
//		this.favalue = favalue;
//	}


}
