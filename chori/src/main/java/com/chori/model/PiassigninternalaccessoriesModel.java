package com.chori.model;

import java.util.Date;

public class PiassigninternalaccessoriesModel {
	private Integer piinternalaccessories;
	private String accessory;
	private String pi;
	private String user;
	private Date createdate;
	private Double inventoryQty;
	private Double availableQty;
	private String lotnumber;
	private String factorycode;

	public Integer getPiinternalaccessories() {
		return piinternalaccessories;
	}

	public void setPiinternalaccessories(Integer piinternalaccessories) {
		this.piinternalaccessories = piinternalaccessories;
	}

	public String getAccessory() {
		return accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

	public String getPi() {
		return pi;
	}

	public void setPi(String pi) {
		this.pi = pi;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Double getInventoryQty() {
		return inventoryQty;
	}

	public void setInventoryQty(Double inventoryQty) {
		this.inventoryQty = inventoryQty;
	}

	public Double getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(Double availableQty) {
		this.availableQty = availableQty;
	}
	
	public String getLotnumber() {
		return lotnumber;
	}

	public void setLotnumber(String lotnumber) {
		this.lotnumber = lotnumber;
	}

	public String getFactorycode() {
		return factorycode;
	}

	public void setFactorycode(String factorycode) {
		this.factorycode = factorycode;
	}

	public PiassigninternalaccessoriesModel(Integer piinternalaccessories,
			String accessory, String pi, String user, Date createdate,
			Double inventoryQty, Double availableQty) {
		super();
		this.piinternalaccessories = piinternalaccessories;
		this.accessory = accessory;
		this.pi = pi;
		this.user = user;
		this.createdate = createdate;
		this.inventoryQty = inventoryQty;
		this.availableQty = availableQty;
	}
	
	public PiassigninternalaccessoriesModel(Integer piinternalaccessories, String accessory, String pi, String user,
			Date createdate, Double inventoryQty, Double availableQty, String lotnumber, String factorycode) {
		super();
		this.piinternalaccessories = piinternalaccessories;
		this.accessory = accessory;
		this.pi = pi;
		this.user = user;
		this.createdate = createdate;
		this.inventoryQty = inventoryQty;
		this.availableQty = availableQty;
		this.lotnumber = lotnumber;
		this.factorycode = factorycode;
	}

	public PiassigninternalaccessoriesModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PiassigninternalaccessoriesModel [piinternalaccessories=" + piinternalaccessories + ", accessory="
				+ accessory + ", pi=" + pi + ", user=" + user + ", createdate=" + createdate + ", inventoryQty="
				+ inventoryQty + ", availableQty=" + availableQty + ", lotnumber=" + lotnumber + ", factorycode="
				+ factorycode + "]";
	}

}
