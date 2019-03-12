package com.chori.model;

public class FpidetailModel {
	private Integer fpidetailcode;
	private Integer fpicode;
	private Integer totalFaPcs;
	private Integer totalFpiPcs;
	private String colorName;
	private Integer colorFaPcs;
	private Integer colorFpiPcs;
	private String garmentstyle;
	private Integer garmentstyleFaPcs;
	private Integer garmentstyleFpiPcs;
	private String imgUrl;
	private String typecode;
	private Integer typeFaPcs;
	private Integer typeFpiPcs;
	private Integer sizecode;
	private String sizename;
	private Integer fpiPcs;
	private Integer faPcs;
	
	public Integer getFpidetailcode() {
		return fpidetailcode;
	}
	public void setFpidetailcode(Integer fpidetailcode) {
		this.fpidetailcode = fpidetailcode;
	}
	public Integer getFpicode(){
		return fpicode;
	}
	public void setFpicode(Integer fpicode){
		this.fpicode = fpicode;
	}
	public Integer getTotalFaPcs() {
		return totalFaPcs;
	}
	public void setTotalFaPcs(Integer totalFaPcs) {
		this.totalFaPcs = totalFaPcs;
	}
	public Integer getTotalFpiPcs() {
		return totalFpiPcs;
	}
	public void setTotalFpiPcs(Integer totalFpiPcs) {
		this.totalFpiPcs = totalFpiPcs;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public Integer getColorFaPcs() {
		return colorFaPcs;
	}
	public void setColorFaPcs(Integer colorFaPcs) {
		this.colorFaPcs = colorFaPcs;
	}
	public Integer getColorFpiPcs() {
		return colorFpiPcs;
	}
	public void setColorFpiPcs(Integer colorFpiPcs) {
		this.colorFpiPcs = colorFpiPcs;
	}
	public String getGarmentstyle() {
		return garmentstyle;
	}
	public void setGarmentstyle(String garmentstyle) {
		this.garmentstyle = garmentstyle;
	}
	public Integer getGarmentstyleFaPcs() {
		return garmentstyleFaPcs;
	}
	public void setGarmentstyleFaPcs(Integer garmentstyleFaPcs) {
		this.garmentstyleFaPcs = garmentstyleFaPcs;
	}
	public Integer getGarmentstyleFpiPcs() {
		return garmentstyleFpiPcs;
	}
	public void setGarmentstyleFpiPcs(Integer garmentstyleFpiPcs) {
		this.garmentstyleFpiPcs = garmentstyleFpiPcs;
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
	public Integer getTypeFaPcs() {
		return typeFaPcs;
	}
	public void setTypeFaPcs(Integer typeFaPcs) {
		this.typeFaPcs = typeFaPcs;
	}
	public Integer getTypeFpiPcs() {
		return typeFpiPcs;
	}
	public void setTypeFpiPcs(Integer typeFpiPcs) {
		this.typeFpiPcs = typeFpiPcs;
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
	public Integer getFaPcs() {
		return faPcs;
	}
	public void setFaPcs(Integer faPcs) {
		this.faPcs = faPcs;
	}
	
	public FpidetailModel(Integer fpidetailcode, Integer fpicode, Integer totalFaPcs,
			Integer totalFpiPcs, String colorName, Integer colorFaPcs,
			Integer colorFpiPcs, String garmentstyle,
			Integer garmentstyleFaPcs, Integer garmentstyleFpiPcs,
			String imgUrl, String typecode, Integer typeFaPcs,
			Integer typeFpiPcs, Integer sizecode, String sizename,
			Integer fpiPcs, Integer faPcs) {
		super();
		this.fpidetailcode = fpidetailcode;
		this.fpicode = fpicode;
		this.totalFaPcs = totalFaPcs;
		this.totalFpiPcs = totalFpiPcs;
		this.colorName = colorName;
		this.colorFaPcs = colorFaPcs;
		this.colorFpiPcs = colorFpiPcs;
		this.garmentstyle = garmentstyle;
		this.garmentstyleFaPcs = garmentstyleFaPcs;
		this.garmentstyleFpiPcs = garmentstyleFpiPcs;
		this.imgUrl = imgUrl;
		this.typecode = typecode;
		this.typeFaPcs = typeFaPcs;
		this.typeFpiPcs = typeFpiPcs;
		this.sizecode = sizecode;
		this.sizename = sizename;
		this.fpiPcs = fpiPcs;
		this.faPcs = faPcs;
	}
	public FpidetailModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "FpidetailModel [fpidetailcode=" + fpidetailcode + ", fpicode=" + fpicode + ", totalFaPcs=" + totalFaPcs
				+ ", totalFpiPcs=" + totalFpiPcs + ", colorName=" + colorName + ", colorFaPcs=" + colorFaPcs
				+ ", colorFpiPcs=" + colorFpiPcs + ", garmentstyle=" + garmentstyle + ", garmentstyleFaPcs="
				+ garmentstyleFaPcs + ", garmentstyleFpiPcs=" + garmentstyleFpiPcs + ", imgUrl=" + imgUrl
				+ ", typecode=" + typecode + ", typeFaPcs=" + typeFaPcs + ", typeFpiPcs=" + typeFpiPcs + ", sizecode="
				+ sizecode + ", sizename=" + sizename + ", fpiPcs=" + fpiPcs + ", faPcs=" + faPcs + "]";
	}
		
}
