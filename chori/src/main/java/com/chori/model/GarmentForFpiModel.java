package com.chori.model;

public class GarmentForFpiModel {
	private String garmentstyle;
	private Integer garmentstylePcs;
	private Integer garmentstyleFpiPcs;
	private Integer garmentstyleRfpiPcs;
	public GarmentForFpiModel() {
		super();
	}
	public GarmentForFpiModel(String garmentstyle) {
		super();
		this.garmentstyle = garmentstyle;
	}
	public GarmentForFpiModel(String garmentstyle, Integer garmentstylePcs, Integer garmentstyleFpiPcs,
			Integer garmentstyleRfpiPcs) {
		super();
		this.garmentstyle = garmentstyle;
		this.garmentstylePcs = garmentstylePcs;
		this.garmentstyleFpiPcs = garmentstyleFpiPcs;
		this.garmentstyleRfpiPcs = garmentstyleRfpiPcs;
	}
	public String getGarmentstyle() {
		return garmentstyle;
	}
	public void setGarmentstyle(String garmentstyle) {
		this.garmentstyle = garmentstyle;
	}
	public Integer getGarmentstylePcs() {
		return garmentstylePcs;
	}
	public void setGarmentstylePcs(Integer garmentstylePcs) {
		this.garmentstylePcs = garmentstylePcs;
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
	@Override
	public String toString() {
		return "GarmentForFpiModel [garmentstyle=" + garmentstyle + ", garmentstylePcs=" + garmentstylePcs
				+ ", garmentstyleFpiPcs=" + garmentstyleFpiPcs + ", garmentstyleRfpiPcs=" + garmentstyleRfpiPcs + "]";
	}
	
}
