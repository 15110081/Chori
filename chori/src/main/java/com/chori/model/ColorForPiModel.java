package com.chori.model;

/**
 * Class dùng để lưu color code, số lượng fa, fpi
 * @author Bleach
 *
 */
public class ColorForPiModel {
	private String colorName;
	private Integer colorPcs;
	private Integer colorFaPcs;
	private Integer colorFpiPcs;
	
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
	
	@Override
	public String toString() {
		return "ColorForPiModel [colorName=" + colorName + ", colorPcs="
				+ colorPcs + ", colorFaPcs=" + colorFaPcs + ", colorFpiPcs="
				+ colorFpiPcs + "]";
	}
	
	public ColorForPiModel(String colorName, Integer colorFaPcs,
			Integer colorFpiPcs) {
		super();
		this.colorName = colorName;
		this.colorFaPcs = colorFaPcs;
		this.colorFpiPcs = colorFpiPcs;
	}
	public ColorForPiModel() {
		super();
		// TODO Auto-generated constructor stub
	}
}
