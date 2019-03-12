package com.chori.model;

public class PiFabricListModel {
	
	private String fabricno;
	private String colorcode;
	private Double inventoryremained;
	private Double totalremained;
	
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
	public Double getInventoryremained() {
		return inventoryremained;
	}
	public void setInventoryremained(Double inventoryremained) {
		this.inventoryremained = inventoryremained;
	}
	public Double getTotalremained() {
		return totalremained;
	}
	public void setTotalremained(Double totalremained) {
		this.totalremained = totalremained;
	}
	public PiFabricListModel(String fabricno, String colorcode, Double inventoryremained, Double totalremained) {
		super();
		this.fabricno = fabricno;
		this.colorcode = colorcode;
		this.inventoryremained = inventoryremained;
		this.totalremained = totalremained;
	}
	public PiFabricListModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PiFabricListModel [fabricno=" + fabricno + ", colorcode=" + colorcode + ", inventoryremained="
				+ inventoryremained + ", totalremained=" + totalremained + "]";
	}
	
	
}
