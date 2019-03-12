package com.chori.model;

public class ExternalAccessoryStockModel {
	private int externalaccessorystockCode;
	private String orderSheetNo;
	private String accessoryname;
	private String unit;
	private Double availablequantity;
	private Double inventoryquantity;
	private Double assignquantity;
	private Double availableqtyfromstock;
	private Float stockassignqty;
	private String accessorycode;
	private Float actualassignqty;
	private Float shortageqty;

	public ExternalAccessoryStockModel(int externalaccessorystockCode, String orderSheetNo, String accessoryname,
			String unit, Double availablequantity, Double inventoryquantity, Double assignquantity,
			Double availableqtyfromstock, Float stockassignqty, String accessorycode, Float actualassignqty,
			Float shortageqty) {
		super();
		this.externalaccessorystockCode = externalaccessorystockCode;
		this.orderSheetNo = orderSheetNo;
		this.accessoryname = accessoryname;
		this.unit = unit;
		this.availablequantity = availablequantity;
		this.inventoryquantity = inventoryquantity;
		this.assignquantity = assignquantity;
		this.availableqtyfromstock = availableqtyfromstock;
		this.stockassignqty = stockassignqty;
		this.accessorycode = accessorycode;
		this.actualassignqty = actualassignqty;
		this.shortageqty = shortageqty;
	}

	public ExternalAccessoryStockModel() {

	}

	public Float getStockassignqty() {
		return stockassignqty;
	}
	public void setStockassignqty(Float float1) {
		this.stockassignqty = float1;
	}
	public Double getAvailableqtyfromstock() {
		return availableqtyfromstock;
	}
	public void setAvailableqtyfromstock(Double availableqtyfromstock) {
		this.availableqtyfromstock = availableqtyfromstock;
	}
	public int getExternalaccessorystockCode() {
		return externalaccessorystockCode;
	}
	public void setExternalaccessorystockCode(int externalaccessorystockCode) {
		this.externalaccessorystockCode = externalaccessorystockCode;
	}
	public String getOrderSheetNo() {
		return orderSheetNo;
	}
	public void setOrderSheetNo(String orderSheetNo) {
		this.orderSheetNo = orderSheetNo;
	}
	public String getAccessoryname() {
		return accessoryname;
	}
	public void setAccessoryname(String accessoryname) {
		this.accessoryname = accessoryname;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Double getAvailablequantity() {
		return availablequantity;
	}
	public void setAvailablequantity(Double availablequantity) {
		this.availablequantity = availablequantity;
	}
	public Double getInventoryquantity() {
		return inventoryquantity;
	}
	public void setInventoryquantity(Double inventoryquantity) {
		this.inventoryquantity = inventoryquantity;
	}
	public Double getAssignquantity() {
		return assignquantity;
	}
	public void setAssignquantity(Double assignquantity) {
		this.assignquantity = assignquantity;
	}
	
	public String getAccessorycode() {
		return accessorycode;
	}
	
	public void setAccessorycode(String accessorycode) {
		this.accessorycode = accessorycode;
	}
	
	public Float getActualassignqty() {
		return actualassignqty;
	}

	public void setActualassignqty(Float actualassignqty) {
		this.actualassignqty = actualassignqty;
	}

	public Float getShortageqty() {
		return shortageqty;
	}

	public void setShortageqty(Float shortageqty) {
		this.shortageqty = shortageqty;
	}

	@Override
	public String toString() {
		return "ExternalAccessoryStockModel [externalaccessorystockCode=" + externalaccessorystockCode
				+ ", orderSheetNo=" + orderSheetNo + ", accessoryname=" + accessoryname + ", unit=" + unit
				+ ", availablequantity=" + availablequantity + ", inventoryquantity=" + inventoryquantity
				+ ", assignquantity=" + assignquantity + ", availableqtyfromstock=" + availableqtyfromstock
				+ ", stockassignqty=" + stockassignqty + ", accessorycode=" + accessorycode + ", actualassignqty="
				+ actualassignqty + ", shortageqty=" + shortageqty + "]";
	}
}
