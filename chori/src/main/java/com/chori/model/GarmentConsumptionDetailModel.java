package com.chori.model;

import java.util.Comparator;
import java.util.Date;

public class GarmentConsumptionDetailModel{

	private Integer garmentconsumptiondetailcode;
	private Integer garmentconsumption;
	private String creator;
	private String width;
	private Float convalue;
	private Date createdate;

	public Integer getGarmentconsumptiondetailcode() {
		return garmentconsumptiondetailcode;
	}

	public void setGarmentconsumptiondetailcode(
			Integer garmentconsumptiondetailcode) {
		this.garmentconsumptiondetailcode = garmentconsumptiondetailcode;
	}

	public Integer getGarmentconsumption() {
		return garmentconsumption;
	}

	public void setGarmentconsumption(Integer garmentconsumption) {
		this.garmentconsumption = garmentconsumption;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public Float getConvalue() {
		return convalue;
	}

	public void setConvalue(Float convalue) {
		this.convalue = convalue;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public GarmentConsumptionDetailModel(Integer garmentconsumptiondetailcode,
			Integer garmentconsumption, String creator, String width,
			Float convalue, Date createdate) {
		super();
		this.garmentconsumptiondetailcode = garmentconsumptiondetailcode;
		this.garmentconsumption = garmentconsumption;
		this.creator = creator;
		this.width = width;
		this.convalue = convalue;
		this.createdate = createdate;
	}

	public GarmentConsumptionDetailModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "GarmentConsumptionDetailModel [garmentconsumptiondetailcode="
				+ garmentconsumptiondetailcode + ", garmentconsumption="
				+ garmentconsumption + ", creator=" + creator + ", width="
				+ width + ", convalue=" + convalue + ", createdate="
				+ createdate + "]";
	}


	public static Comparator<GarmentConsumptionDetailModel> GarmentConsumptionDetailModelComparatorByWidth = new Comparator<GarmentConsumptionDetailModel>() {
		@Override
		public int compare(GarmentConsumptionDetailModel gar1, GarmentConsumptionDetailModel gar2) {
			
		String garWidth1 = gar1.getWidth();
		String garWidth2 = gar2.getWidth();
		
		//ascending order
		return garWidth1.compareToIgnoreCase(garWidth2);
		
		//descending order
		//return garWidth2.compareTo(garWidth1);
		}	
	};

}
