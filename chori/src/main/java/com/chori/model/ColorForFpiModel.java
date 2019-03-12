package com.chori.model;

public class ColorForFpiModel {
	private String colorName;
	private Integer colorPcs;
	private Integer colorFpiPcs;
	private Integer colorRfpiPcs;
	public ColorForFpiModel() {
		super();
	}
	public ColorForFpiModel(String colorName, Integer colorPcs, Integer colorFpiPcs, Integer colorRfpiPcs) {
		super();
		this.colorName = colorName;
		this.colorPcs = colorPcs;
		this.colorFpiPcs = colorFpiPcs;
		this.colorRfpiPcs = colorRfpiPcs;
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
	@Override
	public String toString() {
		return "ColorForFpiModel [colorName=" + colorName + ", colorPcs=" + colorPcs + ", colorFpiPcs=" + colorFpiPcs
				+ ", colorRfpiPcs=" + colorRfpiPcs + "]";
	}
	
}
