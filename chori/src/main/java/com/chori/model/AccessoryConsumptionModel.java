package com.chori.model;

import java.util.Date;

public class AccessoryConsumptionModel {

	private String factorycode;
	private String factoryshortname;
	private String accessorycode;
	private String accessoryshortname;
	private String username;
	private Float consumption;
	private Date createdate;

	public String getFactorycode() {
		return factorycode;
	}

	public void setFactorycode(String factorycode) {
		this.factorycode = factorycode;
	}

	public String getFactoryshortname() {
		return factoryshortname;
	}

	public void setFactoryshortname(String factoryshortname) {
		this.factoryshortname = factoryshortname;
	}

	public String getAccessorycode() {
		return accessorycode;
	}

	public void setAccessorycode(String accessorycode) {
		this.accessorycode = accessorycode;
	}

	public String getAccessoryshortname() {
		return accessoryshortname;
	}

	public void setAccessoryshortname(String accessoryshortname) {
		this.accessoryshortname = accessoryshortname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Float getConsumption() {
		return consumption;
	}

	public void setConsumption(Float consumption) {
		this.consumption = consumption;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public AccessoryConsumptionModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccessoryConsumptionModel(String factorycode, String accessorycode,
			Float consumption) {
		super();
		this.factorycode = factorycode;
		this.accessorycode = accessorycode;
		this.consumption = consumption;
	}

	public AccessoryConsumptionModel(String factorycode, String accessorycode,
			String username, Float consumption, Date createdate) {
		super();
		this.factorycode = factorycode;
		this.accessorycode = accessorycode;
		this.username = username;
		this.consumption = consumption;
		this.createdate = createdate;
	}

	public AccessoryConsumptionModel(String factorycode,
			String factoryshortname, String accessorycode,
			String accessoryshortname, String username, Float consumption,
			Date createdate) {
		super();
		this.factorycode = factorycode;
		this.factoryshortname = factoryshortname;
		this.accessorycode = accessorycode;
		this.accessoryshortname = accessoryshortname;
		this.username = username;
		this.consumption = consumption;
		this.createdate = createdate;
	}

}
