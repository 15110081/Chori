package com.chori.model;

public class RfpidetailModel {
	private Integer rfpidetailcode;
	private Integer totalFpiPcs;
	private Integer totalRfpiPcs;
	private String colorName;
	private Integer colorFpiPcs;
	private Integer colorRfpiPcs;
	private String garmentstyle;
	private Integer garmentstyleFpiPcs;
	private Integer garmentstyleRfpiPcs;
	private String imgUrl;
	private String typecode;
	private Integer typeFpiPcs;
	private Integer typeRfpiPcs;
	private Integer sizecode;
	private String sizename;
	private Integer fpiPcs;
	private Integer rfpiPcs;
	public RfpidetailModel() {
		super();
	}
	public RfpidetailModel(Integer fpidetailcode) {
		super();
		this.rfpidetailcode = fpidetailcode;
	}
	public RfpidetailModel(Integer rfpidetailcode, Integer totalFpiPcs, Integer totalRfpiPcs, String colorName,
			Integer colorFpiPcs, Integer colorRfpiPcs, String garmentstyle, Integer garmentstyleFpiPcs,
			Integer garmentstyleRfpiPcs, String imgUrl, String typecode, Integer typeFpiPcs, Integer typeRfpiPcs,
			Integer sizecode, String sizename, Integer fpiPcs, Integer rfpiPcs) {
		super();
		this.rfpidetailcode = rfpidetailcode;
		this.totalFpiPcs = totalFpiPcs;
		this.totalRfpiPcs = totalRfpiPcs;
		this.colorName = colorName;
		this.colorFpiPcs = colorFpiPcs;
		this.colorRfpiPcs = colorRfpiPcs;
		this.garmentstyle = garmentstyle;
		this.garmentstyleFpiPcs = garmentstyleFpiPcs;
		this.garmentstyleRfpiPcs = garmentstyleRfpiPcs;
		this.imgUrl = imgUrl;
		this.typecode = typecode;
		this.typeFpiPcs = typeFpiPcs;
		this.typeRfpiPcs = typeRfpiPcs;
		this.sizecode = sizecode;
		this.sizename = sizename;
		this.fpiPcs = fpiPcs;
		this.rfpiPcs = rfpiPcs;
	}
	public Integer getRfpidetailcode() {
		return rfpidetailcode;
	}
	public void setRfpidetailcode(Integer rfpidetailcode) {
		this.rfpidetailcode = rfpidetailcode;
	}
	public Integer getTotalFpiPcs() {
		return totalFpiPcs;
	}
	public void setTotalFpiPcs(Integer totalFpiPcs) {
		this.totalFpiPcs = totalFpiPcs;
	}
	public Integer getTotalRfpiPcs() {
		return totalRfpiPcs;
	}
	public void setTotalRfpiPcs(Integer totalRfpiPcs) {
		this.totalRfpiPcs = totalRfpiPcs;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public Integer getColorFpiPcs() {
		return colorFpiPcs;
	}
	public void setColorFpiPcs(Integer colorFpiPcs) {
		this.colorFpiPcs = colorFpiPcs;
	}
	public Integer getColorRfpiPcs() {
		return colorRfpiPcs;
	}
	public void setColorRfpiPcs(Integer colorRfpiPcs) {
		this.colorRfpiPcs = colorRfpiPcs;
	}
	public String getGarmentstyle() {
		return garmentstyle;
	}
	public void setGarmentstyle(String garmentstyle) {
		this.garmentstyle = garmentstyle;
	}
	public Integer getGarmentstyleFpiPcs() {
		return garmentstyleFpiPcs;
	}
	public void setGarmentstyleFpiPcs(Integer garmentstyleFpiPcs) {
		this.garmentstyleFpiPcs = garmentstyleFpiPcs;
	}
	public Integer getGarmentstyleRfpiPcs() {
		return garmentstyleRfpiPcs;
	}
	public void setGarmentstyleRfpiPcs(Integer garmentstyleRfpiPcs) {
		this.garmentstyleRfpiPcs = garmentstyleRfpiPcs;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getTypecode() {
		return typecode;
	}
	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}
	public Integer getTypeFpiPcs() {
		return typeFpiPcs;
	}
	public void setTypeFpiPcs(Integer typeFpiPcs) {
		this.typeFpiPcs = typeFpiPcs;
	}
	public Integer getTypeRfpiPcs() {
		return typeRfpiPcs;
	}
	public void setTypeRfpiPcs(Integer typeRfpiPcs) {
		this.typeRfpiPcs = typeRfpiPcs;
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
	public Integer getFpiPcs() {
		return fpiPcs;
	}
	public void setFpiPcs(Integer fpiPcs) {
		this.fpiPcs = fpiPcs;
	}
	public Integer getRfpiPcs() {
		return rfpiPcs;
	}
	public void setRfpiPcs(Integer rfpiPcs) {
		this.rfpiPcs = rfpiPcs;
	}
	@Override
	public String toString() {
		return "RfpidetailModel [rfpidetailcode=" + rfpidetailcode + ", totalFpiPcs=" + totalFpiPcs + ", totalRfpiPcs="
				+ totalRfpiPcs + ", colorName=" + colorName + ", colorFpiPcs=" + colorFpiPcs + ", colorRfpiPcs="
				+ colorRfpiPcs + ", garmentstyle=" + garmentstyle + ", garmentstyleFpiPcs=" + garmentstyleFpiPcs
				+ ", garmentstyleRfpiPcs=" + garmentstyleRfpiPcs + ", imgUrl=" + imgUrl + ", typecode=" + typecode
				+ ", typeFpiPcs=" + typeFpiPcs + ", typeRfpiPcs=" + typeRfpiPcs + ", sizecode=" + sizecode
				+ ", sizename=" + sizename + ", fpiPcs=" + fpiPcs + ", rfpiPcs=" + rfpiPcs + "]";
	}

	
}
