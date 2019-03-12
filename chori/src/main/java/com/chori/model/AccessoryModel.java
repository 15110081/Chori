package com.chori.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
public class AccessoryModel implements Serializable {
	private String accessorycode;
	private String colorcode;
	private String unitcode;
	private String userName;
	private String name;
	private String kind;
	private String dimension;
	private String mode;
	private String imgurl1;
	private String imgurl2;
	private String imgurl3;
	private String imgurl4;
	private Date createdate;
	private String containerUnitCode;
	private Integer percontainer;
	private String accessorygroupcode;
	// prop check is assign for garment style
	private boolean isAssignedForGarment;

	private String status;
	private String accessorychoricode;
	private String accessorysuppliercode;
	
	// prop check is Checked For Order Internal
	private boolean isCheckedForOrderInternal;
	//order date for order internal accessory
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date orderDate; 

	public String getAccessorycode() {
		return accessorycode;
	}

	public void setAccessorycode(String accessorycode) {
		this.accessorycode = accessorycode;
	}

	public String getColorcode() {
		return colorcode;
	}

	public void setColorcode(String colorcode) {
		this.colorcode = colorcode;
	}

	public String getUnitcode() {
		return unitcode;
	}

	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
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

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public boolean isAssignedForGarment() {
		return isAssignedForGarment;
	}

	public void setAssignedForGarment(boolean isAssignedForGarment) {
		this.isAssignedForGarment = isAssignedForGarment;
	}

	public String getContainerUnitCode() {
		return containerUnitCode;
	}

	public void setContainerUnitCode(String containerUnitCode) {
		this.containerUnitCode = containerUnitCode;
	}

	public Integer getPercontainer() {
		return percontainer;
	}

	public void setPercontainer(Integer percontainer) {
		this.percontainer = percontainer;
	}

	public String getAccessorygroupcode() {
		return accessorygroupcode;
	}

	public void setAccessorygroupcode(String accessorygroupcode) {
		this.accessorygroupcode = accessorygroupcode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccessorychoricode() {
		return accessorychoricode;
	}

	public void setAccessorychoricode(String accessorychoricode) {
		this.accessorychoricode = accessorychoricode;
	}

	public AccessoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAccessorysuppliercode() {
		return accessorysuppliercode;
	}

	public void setAccessorysuppliercode(String accessorysuppliercode) {
		this.accessorysuppliercode = accessorysuppliercode;
	}

	public boolean isCheckedForOrderInternal() {
		return isCheckedForOrderInternal;
	}

	public void setCheckedForOrderInternal(boolean isCheckedForOrderInternal) {
		this.isCheckedForOrderInternal = isCheckedForOrderInternal;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public AccessoryModel(String accessorycode, String colorcode,
			String unitcode, String userName, String name, String kind,
			String dimension, String mode, String imgurl1, String imgurl2,
			Date createdate) {
		super();
		this.accessorycode = accessorycode;
		this.colorcode = colorcode;
		this.unitcode = unitcode;
		this.userName = userName;
		this.name = name;
		this.kind = kind;
		this.dimension = dimension;
		this.mode = mode;
		this.imgurl1 = imgurl1;
		this.imgurl2 = imgurl2;
		this.createdate = createdate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accessorycode == null) ? 0 : accessorycode.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		AccessoryModel other = (AccessoryModel) obj;
		if (accessorycode == null) {
			if (other.accessorycode != null)
				return false;
		} else if (!accessorycode.equals(other.accessorycode))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AccessoryModel [accessorycode=" + accessorycode
				+ ", colorcode=" + colorcode + ", unitcode=" + unitcode
				+ ", userName=" + userName + ", name=" + name + ", kind="
				+ kind + ", dimension=" + dimension + ", mode=" + mode
				+ ", imgurl1=" + imgurl1 + ", imgurl2=" + imgurl2
				+ ", imgurl3=" + imgurl3 + ", imgurl4=" + imgurl4
				+ ", createdate=" + createdate + ", containerUnitCode="
				+ containerUnitCode + ", percontainer=" + percontainer
				+ ", accessorygroupcode=" + accessorygroupcode
				+ ", isAssignedForGarment=" + isAssignedForGarment
				+ ", status=" + status + ", accessorychoricode="
				+ accessorychoricode + ", accessorysuppliercode="
				+ accessorysuppliercode + ", isCheckedForOrderInternal="
				+ isCheckedForOrderInternal + ", orderDate=" + orderDate + "]";
	}

}
