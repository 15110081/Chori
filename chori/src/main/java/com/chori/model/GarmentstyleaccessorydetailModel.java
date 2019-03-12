package com.chori.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

@SuppressWarnings("serial")
public class GarmentstyleaccessorydetailModel implements Serializable {

	private Integer garmentstyleaccessorydetailcode;
	private String accessorycode;
	private String accessoryName;
	private String garmentstylecode;
	private String garmentstyleDescription;
	private Integer sizecode;
	private String sizename;
	private String typecode;
	private String typeDescription;
	private String creator;
	private Float usedvalue;
	private Date createdate;

	public Integer getGarmentstyleaccessorydetailcode() {
		return garmentstyleaccessorydetailcode;
	}

	public void setGarmentstyleaccessorydetailcode(
			Integer garmentstyleaccessorydetailcode) {
		this.garmentstyleaccessorydetailcode = garmentstyleaccessorydetailcode;
	}

	public String getAccessorycode() {
		return accessorycode;
	}

	public void setAccessorycode(String accessorycode) {
		this.accessorycode = accessorycode;
	}

	public String getAccessoryName() {
		return accessoryName;
	}

	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}

	public String getGarmentstylecode() {
		return garmentstylecode;
	}

	public void setGarmentstylecode(String garmentstylecode) {
		this.garmentstylecode = garmentstylecode;
	}

	public String getGarmentstyleDescription() {
		return garmentstyleDescription;
	}

	public void setGarmentstyleDescription(String garmentstyleDescription) {
		this.garmentstyleDescription = garmentstyleDescription;
	}

	public Integer getSizecode() {
		return sizecode;
	}

	public void setSizecode(Integer sizecode) {
		this.sizecode = sizecode;
	}

	public String getSizename() {
		return sizename;
	}

	public void setSizename(String sizename) {
		this.sizename = sizename;
	}

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Float getUsedvalue() {
		return usedvalue;
	}

	public void setUsedvalue(Float usedvalue) {
		this.usedvalue = usedvalue;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((garmentstyleaccessorydetailcode == null) ? 0
						: garmentstyleaccessorydetailcode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GarmentstyleaccessorydetailModel other = (GarmentstyleaccessorydetailModel) obj;
		if (garmentstyleaccessorydetailcode == null) {
			if (other.garmentstyleaccessorydetailcode != null)
				return false;
		} else if (!garmentstyleaccessorydetailcode
				.equals(other.garmentstyleaccessorydetailcode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GarmentstyleaccessorydetailModel [garmentstyleaccessorydetailcode="
				+ garmentstyleaccessorydetailcode
				+ ", accessorycode="
				+ accessorycode
				+ ", accessoryName="
				+ accessoryName
				+ ", garmentstylecode="
				+ garmentstylecode
				+ ", garmentstyleDescription="
				+ garmentstyleDescription
				+ ", sizecode="
				+ sizecode
				+ ", sizename="
				+ sizename
				+ ", typecode="
				+ typecode
				+ ", typeDescription="
				+ typeDescription
				+ ", creater="
				+ creator
				+ ", usedvalue="
				+ usedvalue + ", createdate=" + createdate + "]";
	}

	public GarmentstyleaccessorydetailModel(
			Integer garmentstyleaccessorydetailcode, String accessorycode,
			String accessoryName, String garmentstylecode,
			String garmentstyleDescription, Integer sizecode, String sizename,
			String typecode, String typeDescription, String creater,
			Float usedvalue, Date createdate) {
		super();
		this.garmentstyleaccessorydetailcode = garmentstyleaccessorydetailcode;
		this.accessorycode = accessorycode;
		this.accessoryName = accessoryName;
		this.garmentstylecode = garmentstylecode;
		this.garmentstyleDescription = garmentstyleDescription;
		this.sizecode = sizecode;
		this.sizename = sizename;
		this.typecode = typecode;
		this.typeDescription = typeDescription;
		this.creator = creater;
		this.usedvalue = usedvalue;
		this.createdate = createdate;
	}

	public GarmentstyleaccessorydetailModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	// compare to sort by accessory name
	public static Comparator<GarmentstyleaccessorydetailModel> accessoryNameComparator = new Comparator<GarmentstyleaccessorydetailModel>() {

		@Override
		public int compare(GarmentstyleaccessorydetailModel o1,
				GarmentstyleaccessorydetailModel o2) {
			String accessoryName1 = o1.getAccessoryName();
			String accessoryName2 = o2.getAccessoryName();

			return accessoryName1.compareTo(accessoryName2);
		}
	};
}
