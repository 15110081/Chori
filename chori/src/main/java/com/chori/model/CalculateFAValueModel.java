package com.chori.model;

public class CalculateFAValueModel {
	private String lotNo;
	private String garmentstyleCode;
	private String colorCode;
	private Integer sizeCode;
	private String customerCode;
	private String fabricNo;
	public CalculateFAValueModel() {
		super();
	}
	public CalculateFAValueModel(String lotNo, String garmentstyleCode, String colorCode, Integer sizeCode,
			String customerCode, String fabricNo) {
		super();
		this.lotNo = lotNo;
		this.garmentstyleCode = garmentstyleCode;
		this.colorCode = colorCode;
		this.sizeCode = sizeCode;
		this.customerCode = customerCode;
		this.fabricNo = fabricNo;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getGarmentstyleCode() {
		return garmentstyleCode;
	}
	public void setGarmentstyleCode(String garmentstyleCode) {
		this.garmentstyleCode = garmentstyleCode;
	}
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	public Integer getSizeCode() {
		return sizeCode;
	}
	public void setSizeCode(Integer sizeCode) {
		this.sizeCode = sizeCode;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getFabricNo() {
		return fabricNo;
	}
	public void setFabricNo(String fabricNo) {
		this.fabricNo = fabricNo;
	}
	@Override
	public String toString() {
		return "CalculateFAValueModel [lotNo=" + lotNo + ", garmentstyleCode=" + garmentstyleCode + ", colorCode="
				+ colorCode + ", sizeCode=" + sizeCode + ", customerCode=" + customerCode + ", fabricNo=" + fabricNo
				+ "]";
	}
	
}
