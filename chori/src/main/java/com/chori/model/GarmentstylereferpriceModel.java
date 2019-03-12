package com.chori.model;

public class GarmentstylereferpriceModel {
	private String garmentstylecode;
	private String typecode;
	private Float referprice;
	
	public String getGarmentstylecode() {
		return garmentstylecode;
	}
	public void setGarmentstylecode(String garmentstylecode) {
		this.garmentstylecode = garmentstylecode;
	}
	public String getTypecode() {
		return typecode;
	}
	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}
	public Float getReferprice() {
		return referprice;
	}
	public void setReferprice(Float referprice) {
		this.referprice = referprice;
	}
	
	@Override
	public String toString() {
		return "GarmentstylereferpriceModel [garmentstylecode="
				+ garmentstylecode + ", typecode=" + typecode + ", referprice="
				+ referprice + "]";
	}
	
	public GarmentstylereferpriceModel(String garmentstylecode,
			String typecode, Float referprice) {
		super();
		this.garmentstylecode = garmentstylecode;
		this.typecode = typecode;
		this.referprice = referprice;
	}
	public GarmentstylereferpriceModel() {
		super();
		// TODO Auto-generated constructor stub
	}
}
