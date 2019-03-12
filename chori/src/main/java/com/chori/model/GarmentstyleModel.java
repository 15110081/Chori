package com.chori.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
public class GarmentstyleModel implements Serializable {

	private String garmentstylecode;
	private String customercode;
	private String customerShortname;
	private String factorycode;
	private String factoryShortname;
	private String garmentkindcode;
	private String garmentkindDescription;
	private String creator;
	private String description;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
	private Date createdate;
	private String imgurl1;
	private String imgurl2;
	private String imgurl3;
	private String imgurl4;
	private String imgurl5;
	private Float referprice;
	private String sewingguide;
	private String packingguide;
	private String currencycode;
	List<FileBucket> files = new ArrayList<FileBucket>();
	
	//biến lưu ảnh sẽ bị xóa
	private String imgUrlDelete;
	//chuyển qua post nên dùng thêm biến lưu garment style code sẽ copy
	private String oldGarmentstyleCode;
	
	//1 số biến cần dùng để getListSizeByGarmentAccessoryCustomer
	private String accessoryCode;
	//
	private String accessoryGroupCode;
	
	private String displayName;
	
	private List<GarmentstylereferpriceModel> GarmentstylereferpriceModelList = new ArrayList<GarmentstylereferpriceModel>();

	public String getGarmentstylecode() {
		return garmentstylecode;
	}

	public void setGarmentstylecode(String garmentstylecode) {
		this.garmentstylecode = garmentstylecode;
	}

	public String getCustomercode() {
		return customercode;
	}

	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}

	public String getCustomerShortname() {
		return customerShortname;
	}

	public void setCustomerShortname(String customerShortname) {
		this.customerShortname = customerShortname;
	}

	public String getFactorycode() {
		return factorycode;
	}

	public void setFactorycode(String factorycode) {
		this.factorycode = factorycode;
	}

	public String getFactoryShortname() {
		return factoryShortname;
	}

	public void setFactoryShortname(String factoryShortname) {
		this.factoryShortname = factoryShortname;
	}

	public String getGarmentkindcode() {
		return garmentkindcode;
	}

	public void setGarmentkindcode(String garmentkindcode) {
		this.garmentkindcode = garmentkindcode;
	}

	public String getGarmentkindDescription() {
		return garmentkindDescription;
	}

	public void setGarmentkindDescription(String garmentkindDescription) {
		this.garmentkindDescription = garmentkindDescription;
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

	public String getImgurl1() {
		return imgurl1;
	}

	public void setImgurl1(String imgurl1) {
		this.imgurl1 = imgurl1;
	}

	public String getImgurl2() {
		return imgurl2;
	}

	public void setImgurl2(String imgurl2) {
		this.imgurl2 = imgurl2;
	}

	public String getImgurl3() {
		return imgurl3;
	}

	public void setImgurl3(String imgurl3) {
		this.imgurl3 = imgurl3;
	}

	public String getImgurl4() {
		return imgurl4;
	}

	public void setImgurl4(String imgurl4) {
		this.imgurl4 = imgurl4;
	}

	public String getImgurl5() {
		return imgurl5;
	}

	public void setImgurl5(String imgurl5) {
		this.imgurl5 = imgurl5;
	}

	public Float getReferprice() {
		return referprice;
	}

	public void setReferprice(Float referprice) {
		this.referprice = referprice;
	}

	public String getSewingguide() {
		return sewingguide;
	}

	public void setSewingguide(String sewingguide) {
		this.sewingguide = sewingguide;
	}

	public String getPackingguide() {
		return packingguide;
	}

	public void setPackingguide(String packingguide) {
		this.packingguide = packingguide;
	}

	public String getCurrencycode() {
		return currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

	public List<FileBucket> getFiles() {
		return files;
	}

	public void setFiles(List<FileBucket> files) {
		this.files = files;
	}

	public String getImgUrlDelete() {
		return imgUrlDelete;
	}

	public void setImgUrlDelete(String imgUrlDelete) {
		this.imgUrlDelete = imgUrlDelete;
	}

	public String getOldGarmentstyleCode() {
		return oldGarmentstyleCode;
	}

	public void setOldGarmentstyleCode(String oldGarmentstyleCode) {
		this.oldGarmentstyleCode = oldGarmentstyleCode;
	}

	public String getAccessoryCode() {
		return accessoryCode;
	}

	public void setAccessoryCode(String accessoryCode) {
		this.accessoryCode = accessoryCode;
	}

	public String getAccessoryGroupCode() {
		return accessoryGroupCode;
	}

	public void setAccessoryGroupCode(String accessoryGroupCode) {
		this.accessoryGroupCode = accessoryGroupCode;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


	public List<GarmentstylereferpriceModel> getGarmentstylereferpriceModelList() {
		return GarmentstylereferpriceModelList;
	}

	public void setGarmentstylereferpriceModelList(
			List<GarmentstylereferpriceModel> garmentstylereferpriceModelList) {
		GarmentstylereferpriceModelList = garmentstylereferpriceModelList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime
				* result
				+ ((garmentstylecode == null) ? 0 : garmentstylecode.hashCode());
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
		GarmentstyleModel other = (GarmentstyleModel) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (garmentstylecode == null) {
			if (other.garmentstylecode != null)
				return false;
		} else if (!garmentstylecode.equals(other.garmentstylecode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GarmentstyleModel [garmentstylecode=" + garmentstylecode
				+ ", customercode=" + customercode + ", customerShortname="
				+ customerShortname + ", factorycode=" + factorycode
				+ ", factoryShortname=" + factoryShortname
				+ ", garmentkindcode=" + garmentkindcode
				+ ", garmentkindDescription=" + garmentkindDescription
				+ ", creator=" + creator + ", description=" + description
				+ ", createdate=" + createdate + ", imgurl1=" + imgurl1
				+ ", imgurl2=" + imgurl2 + ", imgurl3=" + imgurl3
				+ ", imgurl4=" + imgurl4 + ", imgurl5=" + imgurl5
				+ ", referprice=" + referprice + ", sewingguide=" + sewingguide
				+ ", packingguide=" + packingguide + ", currencycode="
				+ currencycode + ", files=" + files + ", imgUrlDelete="
				+ imgUrlDelete + "]";
	}

	public GarmentstyleModel(String garmentstylecode, String customercode,
			String customerShortname, String factorycode,
			String factoryShortname, String garmentkindcode,
			String garmentkindDescription, String creator, String description,
			Date createdate, String imgurl1, String imgurl2, String imgurl3,
			String imgurl4, String imgurl5, Float referprice,
			List<FileBucket> files) {
		super();
		this.garmentstylecode = garmentstylecode;
		this.customercode = customercode;
		this.customerShortname = customerShortname;
		this.factorycode = factorycode;
		this.factoryShortname = factoryShortname;
		this.garmentkindcode = garmentkindcode;
		this.garmentkindDescription = garmentkindDescription;
		this.creator = creator;
		this.description = description;
		this.createdate = createdate;
		this.imgurl1 = imgurl1;
		this.imgurl2 = imgurl2;
		this.imgurl3 = imgurl3;
		this.imgurl4 = imgurl4;
		this.imgurl5 = imgurl5;
		this.referprice = referprice;
		this.files = files;
	}

	public GarmentstyleModel() {
		super();
		// TODO Auto-generated constructor stub
	}
}
