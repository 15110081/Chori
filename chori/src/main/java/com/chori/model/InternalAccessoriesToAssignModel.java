package com.chori.model;

import java.util.Date;

public class InternalAccessoriesToAssignModel {
	private Integer piinternalaccessories;
	private String accessorycode;
	private String colorcode;
	private String name;
	private String kind;
	private String dimension;
	private String mode;
	private String imgurl1;
	private String imgurl2;
	private Date createdate;
	private String accessorygroupcode;
	private Double inventoryQty;
	private Double availableQty;
	private String piGarmentStyle;
	private String piColor;
	private Integer piAssignQty;
	private Integer sizecode;
	private String sizename;

	public String getSizename() {
		return sizename;
	}

	public void setSizename(String sizename) {
		this.sizename = sizename;
	}

	public Integer getSizecode() {
		return sizecode;
	}

	public void setSizecode(Integer sizecode) {
		this.sizecode = sizecode;
	}

	public Integer getPiinternalaccessories() {
		return piinternalaccessories;
	}

	public void setPiinternalaccessories(Integer piinternalaccessories) {
		this.piinternalaccessories = piinternalaccessories;
	}

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

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getAccessorygroupcode() {
		return accessorygroupcode;
	}

	public void setAccessorygroupcode(String accessorygroupcode) {
		this.accessorygroupcode = accessorygroupcode;
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

	public String getPiGarmentStyle() {
		return piGarmentStyle;
	}

	public void setPiGarmentStyle(String piGarmentStyle) {
		this.piGarmentStyle = piGarmentStyle;
	}

	public String getPiColor() {
		return piColor;
	}

	public void setPiColor(String piColor) {
		this.piColor = piColor;
	}

	public Integer getPiAssignQty() {
		return piAssignQty;
	}

	public void setPiAssignQty(Integer piAssignQty) {
		this.piAssignQty = piAssignQty;
	}

	public InternalAccessoriesToAssignModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InternalAccessoriesToAssignModel(Integer piinternalaccessories,
			String accessorycode, String colorcode, String name, String kind,
			String dimension, String mode, String imgurl1, String imgurl2,
			Date createdate, String accessorygroupcode, Double inventoryQty,
			Double availableQty) {
		super();
		this.piinternalaccessories = piinternalaccessories;
		this.accessorycode = accessorycode;
		this.colorcode = colorcode;
		this.name = name;
		this.kind = kind;
		this.dimension = dimension;
		this.mode = mode;
		this.imgurl1 = imgurl1;
		this.imgurl2 = imgurl2;
		this.createdate = createdate;
		this.accessorygroupcode = accessorygroupcode;
		this.inventoryQty = inventoryQty;
		this.availableQty = availableQty;
		
	}

	public InternalAccessoriesToAssignModel(Integer piinternalaccessories,
			String accessorycode, String colorcode, String name, String kind,
			String dimension, String mode, String imgurl1, String imgurl2,
			Date createdate, String accessorygroupcode, Double inventoryQty,
			Double availableQty, String piGarmentStyle, String piColor,
			Integer piAssignQty, Integer sizecode) {
		super();
		this.piinternalaccessories = piinternalaccessories;
		this.accessorycode = accessorycode;
		this.colorcode = colorcode;
		this.name = name;
		this.kind = kind;
		this.dimension = dimension;
		this.mode = mode;
		this.imgurl1 = imgurl1;
		this.imgurl2 = imgurl2;
		this.createdate = createdate;
		this.accessorygroupcode = accessorygroupcode;
		this.inventoryQty = inventoryQty;
		this.availableQty = availableQty;
		this.piGarmentStyle = piGarmentStyle;
		this.piColor = piColor;
		this.piAssignQty = piAssignQty;
		this.sizecode = sizecode;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InternalAccessoriesToAssignModel [piinternalaccessories=" + piinternalaccessories + ", accessorycode="
				+ accessorycode + ", colorcode=" + colorcode + ", name=" + name + ", kind=" + kind + ", dimension="
				+ dimension + ", mode=" + mode + ", imgurl1=" + imgurl1 + ", imgurl2=" + imgurl2 + ", createdate="
				+ createdate + ", accessorygroupcode=" + accessorygroupcode + ", inventoryQty=" + inventoryQty
				+ ", availableQty=" + availableQty + ", piGarmentStyle=" + piGarmentStyle + ", piColor=" + piColor
				+ ", piAssignQty=" + piAssignQty + ", sizecode=" + sizecode + ", sizename=" + sizename + "]";
	}

	public InternalAccessoriesToAssignModel(Integer piinternalaccessories, String accessorycode, String colorcode,
			String name, String kind, String dimension, String mode, String imgurl1, String imgurl2, Date createdate,
			String accessorygroupcode, Double inventoryQty, Double availableQty, String piGarmentStyle,
			String piColor, Integer piAssignQty, Integer sizecode, String sizename) {
		super();
		this.piinternalaccessories = piinternalaccessories;
		this.accessorycode = accessorycode;
		this.colorcode = colorcode;
		this.name = name;
		this.kind = kind;
		this.dimension = dimension;
		this.mode = mode;
		this.imgurl1 = imgurl1;
		this.imgurl2 = imgurl2;
		this.createdate = createdate;
		this.accessorygroupcode = accessorygroupcode;
		this.inventoryQty = inventoryQty;
		this.availableQty = availableQty;
		this.piGarmentStyle = piGarmentStyle;
		this.piColor = piColor;
		this.piAssignQty = piAssignQty;
		this.sizecode = sizecode;
		this.sizename = sizename;
	}



}
