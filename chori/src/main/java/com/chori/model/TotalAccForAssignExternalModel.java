package com.chori.model;

public class TotalAccForAssignExternalModel {
	private String accessoryCode;
	private Float estimateqty;
	private Float estimateFpiQty;
	private Float estimateRFpiQty;
	
	public String getAccessoryCode() {
		return accessoryCode;
	}
	public void setAccessoryCode(String accessoryCode) {
		this.accessoryCode = accessoryCode;
	}
	public Float getEstimateqty() {
		return estimateqty;
	}
	public void setEstimateqty(Float estimateqty) {
		this.estimateqty = estimateqty;
	}
	public Float getEstimateFpiQty() {
		return estimateFpiQty;
	}
	public void setEstimateFpiQty(Float estimateFpiQty) {
		this.estimateFpiQty = estimateFpiQty;
	}
	public Float getEstimateRFpiQty() {
		return estimateRFpiQty;
	}
	public void setEstimateRFpiQty(Float estimateRFpiQty) {
		this.estimateRFpiQty = estimateRFpiQty;
	}
	@Override
	public String toString() {
		return "TotalAccForAssignExternalModel [accessoryCode=" + accessoryCode
				+ ", estimateqty=" + estimateqty + ", estimateFpiQty="
				+ estimateFpiQty + ", estimateRFpiQty=" + estimateRFpiQty + "]";
	}
	public TotalAccForAssignExternalModel(String accessoryCode,
			Float estimateqty, Float estimateFpiQty, Float estimateRFpiQty) {
		super();
		this.accessoryCode = accessoryCode;
		this.estimateqty = estimateqty;
		this.estimateFpiQty = estimateFpiQty;
		this.estimateRFpiQty = estimateRFpiQty;
	}
	public TotalAccForAssignExternalModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
