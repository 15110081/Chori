package com.chori.model;

public class PiAssignInternalAccessories_OrderChoseModel {

	private String orderSheetNo;
	private String accsuplierCode;
	private String factoryCode;
	private String accessorycode;
	private Double orderquantity;
	private Double actualdelvquantity;
	private Double price;
	private Double availableQty;
	private Double assignQty;
	private Integer piInternalAccessories;
	
	public Integer getPiInternalAccessories() {
		return piInternalAccessories;
	}
	public void setPiInternalAccessories(Integer piInternalAccessories) {
		this.piInternalAccessories = piInternalAccessories;
	}
	public Double getAssignQty() {
		return assignQty;
	}
	public void setAssignQty(Double assignQty) {
		this.assignQty = assignQty;
	}
	public Double getAvailableQty() {
		return availableQty;
	}
	public void setAvailableQty(Double availableQty) {
		this.availableQty = availableQty;
	}
	public String getOrderSheetNo() {
		return orderSheetNo;
	}
	public void setOrderSheetNo(String orderSheetNo) {
		this.orderSheetNo = orderSheetNo;
	}
	public String getAccsuplierCode() {
		return accsuplierCode;
	}
	public void setAccsuplierCode(String accsuplierCode) {
		this.accsuplierCode = accsuplierCode;
	}
	public String getFactoryCode() {
		return factoryCode;
	}
	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}
	public String getAccessorycode() {
		return accessorycode;
	}
	public void setAccessorycode(String accessorycode) {
		this.accessorycode = accessorycode;
	}
	public Double getOrderquantity() {
		return orderquantity;
	}
	public void setOrderquantity(Double orderquantity) {
		this.orderquantity = orderquantity;
	}
	public Double getActualdelvquantity() {
		return actualdelvquantity;
	}
	public void setActualdelvquantity(Double actualdelvquantity) {
		this.actualdelvquantity = actualdelvquantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double double1) {
		this.price = double1;
	}
	public PiAssignInternalAccessories_OrderChoseModel(String orderSheetNo, String accsuplierCode, String factoryCode,
			String accessorycode, Double orderquantity, Double actualdelvquantity, Double price, Double availableQty
			, Double assignQty, Integer piInternalAccessories) {
		super();
		this.orderSheetNo = orderSheetNo;
		this.accsuplierCode = accsuplierCode;
		this.factoryCode = factoryCode;
		this.accessorycode = accessorycode;
		this.orderquantity = orderquantity;
		this.actualdelvquantity = actualdelvquantity;
		this.price = price;
		this.availableQty = availableQty;
		this.assignQty = assignQty;
		this.piInternalAccessories = piInternalAccessories;
	}
	public PiAssignInternalAccessories_OrderChoseModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PiAssignInternalAccessories_OrderChoseModel [orderSheetNo=" + orderSheetNo + ", accsuplierCode="
				+ accsuplierCode + ", factoryCode=" + factoryCode + ", accessorycode=" + accessorycode
				+ ", orderquantity=" + orderquantity + ", actualdelvquantity=" + actualdelvquantity + ", price=" + price
				+ ", availableQty=" + availableQty + ", assignQty=" + assignQty + ", piInternalAccessories="
				+ piInternalAccessories + "]";
	}
	
	
}
