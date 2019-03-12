package com.chori.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class PIAssignFabricDetailModel implements Serializable {
	private Integer piassignfabricdetailcode;
	private String lotnumber;
	private String fabricno;
	private String color;
	private Integer sizecode;
	private String piassignfabric;
	private Integer assignquantity;
	private float assignqtypercent;
	private String garmentstyle;
	private Date createdate;
	private String shipping;
	private Double yardinbl;
	private Double inventoryremained;
	private Double availableassign;
	private Double usingvalue;
	private Integer maxQtyPercentValue;
	private Double sumAssignQtyAndAvailableQtyToCalPercent;
	

	public PIAssignFabricDetailModel() {
		super();
	}

	public PIAssignFabricDetailModel(Integer piassignfabricdetailcode) {
		super();
		this.piassignfabricdetailcode = piassignfabricdetailcode;
	}
	
	public PIAssignFabricDetailModel(Integer piassignfabricdetailcode, String lotnumber, String fabricno, String color,
			Integer sizecode, String piassignfabric, Integer assignquantity, float assignqtypercent,
			String garmentstyle, Date createdate, String shipping, Double yardinbl, Double inventoryremained,
			Double availableassign, Double usingvalue, Integer maxQtyPercentValue,
			Double sumAssignQtyAndAvailableQtyToCalPercent) {
		super();
		this.piassignfabricdetailcode = piassignfabricdetailcode;
		this.lotnumber = lotnumber;
		this.fabricno = fabricno;
		this.color = color;
		this.sizecode = sizecode;
		this.piassignfabric = piassignfabric;
		this.assignquantity = assignquantity;
		this.assignqtypercent = assignqtypercent;
		this.garmentstyle = garmentstyle;
		this.createdate = createdate;
		this.shipping = shipping;
		this.yardinbl = yardinbl;
		this.inventoryremained = inventoryremained;
		this.availableassign = availableassign;
		this.usingvalue = usingvalue;
		this.maxQtyPercentValue = maxQtyPercentValue;
		this.sumAssignQtyAndAvailableQtyToCalPercent = sumAssignQtyAndAvailableQtyToCalPercent;
	}

	public Double getSumAssignQtyAndAvailableQtyToCalPercent() {
		return sumAssignQtyAndAvailableQtyToCalPercent;
	}

	public void setSumAssignQtyAndAvailableQtyToCalPercent(Double sumAssignQtyAndAvailableQtyToCalPercent) {
		this.sumAssignQtyAndAvailableQtyToCalPercent = sumAssignQtyAndAvailableQtyToCalPercent;
	}
	
	public Integer getPiassignfabricdetailcode() {
		return piassignfabricdetailcode;
	}

	public void setPiassignfabricdetailcode(Integer piassignfabricdetailcode) {
		this.piassignfabricdetailcode = piassignfabricdetailcode;
	}

	public String getLotnumber() {
		return lotnumber;
	}

	public void setLotnumber(String lotnumber) {
		this.lotnumber = lotnumber;
	}

	public String getFabricno() {
		return fabricno;
	}

	public void setFabricno(String fabricno) {
		this.fabricno = fabricno;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getSizecode() {
		return sizecode;
	}

	public void setSizecode(Integer sizecode) {
		this.sizecode = sizecode;
	}

	public String getPiassignfabric() {
		return piassignfabric;
	}

	public void setPiassignfabric(String piassignfabric) {
		this.piassignfabric = piassignfabric;
	}

	public Integer getAssignquantity() {
		return assignquantity;
	}

	public void setAssignquantity(Integer assignquantity) {
		this.assignquantity = assignquantity;
	}

	public float getAssignqtypercent() {
		return assignqtypercent;
	}

	public void setAssignqtypercent(float assignqtypercent) {
		this.assignqtypercent = assignqtypercent;
	}

	public String getGarmentstyle() {
		return garmentstyle;
	}

	public void setGarmentstyle(String garmentstyle) {
		this.garmentstyle = garmentstyle;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public Double getYardinbl() {
		return yardinbl;
	}

	public void setYardinbl(Double yardinbl) {
		this.yardinbl = yardinbl;
	}

	public Double getInventoryremained() {
		return inventoryremained;
	}

	public void setInventoryremained(Double inventoryremained) {
		this.inventoryremained = inventoryremained;
	}

	public Double getAvailableassign() {
		return availableassign;
	}

	public void setAvailableassign(Double availableassign) {
		this.availableassign = availableassign;
	}

	public Double getUsingvalue() {
		return usingvalue;
	}

	public void setUsingvalue(Double usingvalue) {
		this.usingvalue = usingvalue;
	}

	public Integer getMaxQtyPercentValue() {
		return maxQtyPercentValue;
	}

	public void setMaxQtyPercentValue(Integer maxQtyPercentValue) {
		this.maxQtyPercentValue = maxQtyPercentValue;
	}

	@Override
	public String toString() {
		return "PIAssignFabricDetailModel [piassignfabricdetailcode=" + piassignfabricdetailcode + ", lotnumber="
				+ lotnumber + ", fabricno=" + fabricno + ", color=" + color + ", sizecode=" + sizecode
				+ ", piassignfabric=" + piassignfabric + ", assignquantity=" + assignquantity + ", assignqtypercent="
				+ assignqtypercent + ", garmentstyle=" + garmentstyle + ", createdate=" + createdate + ", shipping="
				+ shipping + ", yardinbl=" + yardinbl + ", inventoryremained=" + inventoryremained
				+ ", availableassign=" + availableassign + ", usingvalue=" + usingvalue + ", maxQtyPercentValue="
				+ maxQtyPercentValue + ", sumAssignQtyAndAvailableQtyToCalPercent="
				+ sumAssignQtyAndAvailableQtyToCalPercent + "]";
	}
}
