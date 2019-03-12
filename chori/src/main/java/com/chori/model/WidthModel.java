package com.chori.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

public class WidthModel implements Serializable {

	private String widthcode;
	private String unitcode;
	private String creator;
	private Date createdate;
	private String widthvalues;
	private String unitname;

	public String getWidthcode() {
		return widthcode;
	}

	public void setWidthcode(String widthcode) {
		this.widthcode = widthcode;
	}

	public String getUnitcode() {
		return unitcode;
	}

	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getWidthvalues() {
		return widthvalues;
	}

	public void setWidthvalues(String widthvalues) {
		this.widthvalues = widthvalues;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public WidthModel(String widthcode, String unitcode, String creator,
			Date createdate, String widthvalues, String unitname) {
		super();
		this.widthcode = widthcode;
		this.unitcode = unitcode;
		this.creator = creator;
		this.createdate = createdate;
		this.widthvalues = widthvalues;
		this.unitname = unitname;
	}

	public WidthModel() {
		super();
	}

	@Override
	public String toString() {
		return "WidthModel [widthcode=" + widthcode + ", unitcode=" + unitcode
				+ ", creator=" + creator + ", createdate=" + createdate
				+ ", widthvalues=" + widthvalues + ", unitname=" + unitname
				+ "]";
	}
	
	public static Comparator<WidthModel> WidthModelComparatorByWidthCode = new Comparator<WidthModel>() {
		@Override
		public int compare(WidthModel w1, WidthModel w2) {
			
		String widthCode1 = w1.getWidthcode();
		String widthCode2 = w2.getWidthcode();
		
		//ascending order
		return widthCode1.compareToIgnoreCase(widthCode2);
		
		//descending order
		//return garWidth2.compareTo(garWidth1);
		}	
	};

}