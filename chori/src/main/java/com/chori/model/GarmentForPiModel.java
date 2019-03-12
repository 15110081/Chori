package com.chori.model;

public class GarmentForPiModel {
	private String garmentstyle;
	private Integer garmentstylePcs;
	private Integer garmentstyleFaPcs;
	private Integer garmentstyleFpiPcs;
	
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
	
	@Override
	public String toString() {
		return "GarmentForPiModel [garmentstyle=" + garmentstyle
				+ ", garmentstylePcs=" + garmentstylePcs
				+ ", garmentstyleFaPcs=" + garmentstyleFaPcs
				+ ", garmentstyleFpiPcs=" + garmentstyleFpiPcs + "]";
	}
	
	public GarmentForPiModel(String garmentstyle, Integer garmentstyleFaPcs,
			Integer garmentstyleFpiPcs) {
		super();
		this.garmentstyle = garmentstyle;
		this.garmentstyleFaPcs = garmentstyleFaPcs;
		this.garmentstyleFpiPcs = garmentstyleFpiPcs;
	}
	public GarmentForPiModel() {
		super();
		// TODO Auto-generated constructor stub
	}
}
