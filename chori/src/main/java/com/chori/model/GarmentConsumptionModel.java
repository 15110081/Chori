package com.chori.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class GarmentConsumptionModel {

	private Integer garmentconsumptioncode;
	private String customer;
	private String garmentstyle;
	private String garmentstyledisplayname;
	private Integer size;
	private String creator;
	private String description;
	private Date createdate;
	private String sizename;
	private String kind;
	private String type;
	private Set<GarmentConsumptionDetailModel> garmentconsumptiondetails = new LinkedHashSet<GarmentConsumptionDetailModel>();

	public Integer getGarmentconsumptioncode() {
		return garmentconsumptioncode;
	}

	public void setGarmentconsumptioncode(Integer garmentconsumptioncode) {
		this.garmentconsumptioncode = garmentconsumptioncode;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getGarmentstyle() {
		return garmentstyle;
	}

	public void setGarmentstyle(String garmentstyle) {
		this.garmentstyle = garmentstyle;
	}

	public String getGarmentstyledisplayname() {
		return garmentstyledisplayname;
	}

	public void setGarmentstyledisplayname(String garmentstyledisplayname) {
		this.garmentstyledisplayname = garmentstyledisplayname;
	}
	
	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Set<GarmentConsumptionDetailModel> getGarmentconsumptiondetails() {
		return garmentconsumptiondetails;
	}

	public void setGarmentconsumptiondetails(
			Set<GarmentConsumptionDetailModel> garmentconsumptiondetails) {
		this.garmentconsumptiondetails = garmentconsumptiondetails;
	}

	public String getSizename() {
		return sizename;
	}

	public void setSizename(String sizename) {
		this.sizename = sizename;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public GarmentConsumptionModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GarmentConsumptionModel(Integer garmentconsumptioncode,
			String customer, String garmentstyle, Integer size, String creator,
			String description, Date createdate, String sizename, String kind,
			String type,
			Set<GarmentConsumptionDetailModel> garmentconsumptiondetails) {
		super();
		this.garmentconsumptioncode = garmentconsumptioncode;
		this.customer = customer;
		this.garmentstyle = garmentstyle;
		this.size = size;
		this.creator = creator;
		this.description = description;
		this.createdate = createdate;
		this.sizename = sizename;
		this.kind = kind;
		this.type = type;
		this.garmentconsumptiondetails = garmentconsumptiondetails;
	}

	public GarmentConsumptionModel(Integer garmentconsumptioncode, String customer, String garmentstyle,
			String garmentstyledisplayname, Integer size, String creator, String description, Date createdate,
			String sizename, String kind, String type, Set<GarmentConsumptionDetailModel> garmentconsumptiondetails) {
		super();
		this.garmentconsumptioncode = garmentconsumptioncode;
		this.customer = customer;
		this.garmentstyle = garmentstyle;
		this.garmentstyledisplayname = garmentstyledisplayname;
		this.size = size;
		this.creator = creator;
		this.description = description;
		this.createdate = createdate;
		this.sizename = sizename;
		this.kind = kind;
		this.type = type;
		this.garmentconsumptiondetails = garmentconsumptiondetails;
	}

	@Override
	public String toString() {
		return "GarmentConsumptionModel [garmentconsumptioncode=" + garmentconsumptioncode + ", customer=" + customer
				+ ", garmentstyle=" + garmentstyle + ", garmentstyledisplayname=" + garmentstyledisplayname + ", size="
				+ size + ", creator=" + creator + ", description=" + description + ", createdate=" + createdate
				+ ", sizename=" + sizename + ", kind=" + kind + ", type=" + type + ", garmentconsumptiondetails="
				+ garmentconsumptiondetails + "]";
	}

}
