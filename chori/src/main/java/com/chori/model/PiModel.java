package com.chori.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PiModel {
	private String lotnumber;
	private String destinationcode;
	private String country;
	private String factorycode;
	private String customer1;
	private String consignee;
	private String creator;
	private int brandcode;
	private int fpicode;
	private int pigridcode;
	private boolean noneorderaccessories;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date pireceiveddate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date piestshipdate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date mfgstarteddate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date mfgfinisheddate;
	private String piattachedfilename;
	private String status;
	private String shipmentstatus;
	private String sewingguidename;
	private String packingguidename;
	private String remark;
	private String packingguidecode;
	private Date createdate;

	FileBucket packingguide = new FileBucket();
	FileBucket sewingguide = new FileBucket();
	FileBucket piattached = new FileBucket();

	
	public String getLotnumber() {
		return lotnumber;
	}

	public void setLotnumber(String lotnumber) {
		this.lotnumber = lotnumber;
	}

	public String getShipmentstatus() {
		return shipmentstatus;
	}

	public void setShipmentstatus(String shipmentstatus) {
		this.shipmentstatus = shipmentstatus;
	}

	public String getDestinationcode() {
		return destinationcode;
	}

	public void setDestinationcode(String destinationcode) {
		this.destinationcode = destinationcode;
	}

	public String getFactorycode() {
		return factorycode;
	}

	public void setFactorycode(String factorycode) {
		this.factorycode = factorycode;
	}

	public String getCustomer1() {
		return customer1;
	}

	public void setCustomer1(String customer1) {
		this.customer1 = customer1;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public int getBrandcode() {
		return brandcode;
	}

	public void setBrandcode(int brandcode) {
		this.brandcode = brandcode;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public int getFpicode() {
		return fpicode;
	}

	public void setFpicode(int fpicode) {
		this.fpicode = fpicode;
	}

	public int getPigridcode() {
		return pigridcode;
	}

	public void setPigridcode(int pigridcode) {
		this.pigridcode = pigridcode;
	}

	public boolean isNoneorderaccessories() {
		return noneorderaccessories;
	}

	public void setNoneorderaccessories(boolean noneorderaccessories) {
		this.noneorderaccessories = noneorderaccessories;
	}

	public Date getPireceiveddate() {
		return pireceiveddate;
	}

	public void setPireceiveddate(Date pireceiveddate) {
		this.pireceiveddate = pireceiveddate;
	}

	public Date getPiestshipdate() {
		return piestshipdate;
	}

	public void setPiestshipdate(Date piestshipdate) {
		this.piestshipdate = piestshipdate;
	}

	public Date getMfgstarteddate() {
		return mfgstarteddate;
	}

	public void setMfgstarteddate(Date mfgstarteddate) {
		this.mfgstarteddate = mfgstarteddate;
	}

	public Date getMfgfinisheddate() {
		return mfgfinisheddate;
	}

	public void setMfgfinisheddate(Date mfgfinisheddate) {
		this.mfgfinisheddate = mfgfinisheddate;
	}

	public String getPiattachedfilename() {
		return piattachedfilename;
	}

	public void setPiattachedfilename(String piattachedfilename) {
		this.piattachedfilename = piattachedfilename;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSewingguidename() {
		return sewingguidename;
	}

	public void setSewingguidename(String sewingguidename) {
		this.sewingguidename = sewingguidename;
	}

	public String getPackingguidename() {
		return packingguidename;
	}

	public void setPackingguidename(String packingguidename) {
		this.packingguidename = packingguidename;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public FileBucket getPackingguide() {
		return packingguide;
	}

	public void setPackingguide(FileBucket packingguide) {
		this.packingguide = packingguide;
	}

	public FileBucket getSewingguide() {
		return sewingguide;
	}

	public void setSewingguide(FileBucket sewingguide) {
		this.sewingguide = sewingguide;
	}

	public FileBucket getPiattached() {
		return piattached;
	}

	public void setPiattached(FileBucket piattached) {
		this.piattached = piattached;
	}

	
	public PiModel() {
	}

	public String getPackingguidecode() {
		return packingguidecode;
	}

	public void setPackingguidecode(String packingguidecode) {
		this.packingguidecode = packingguidecode;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
	

	public PiModel(String lotnumber, String destinationcode, String country, String factorycode, String customer1,
			String consignee, String creator, int brandcode, int fpicode, int pigridcode, boolean noneorderaccessories,
			Date pireceiveddate, Date piestshipdate, Date mfgstarteddate, Date mfgfinisheddate,
			String piattachedfilename, String status, String shipmentstatus, String sewingguidename,
			String packingguidename, String remark, String packingguidecode, Date createdate, FileBucket packingguide,
			FileBucket sewingguide, FileBucket piattached) {
		super();
		this.lotnumber = lotnumber;
		this.destinationcode = destinationcode;
		this.country = country;
		this.factorycode = factorycode;
		this.customer1 = customer1;
		this.consignee = consignee;
		this.creator = creator;
		this.brandcode = brandcode;
		this.fpicode = fpicode;
		this.pigridcode = pigridcode;
		this.noneorderaccessories = noneorderaccessories;
		this.pireceiveddate = pireceiveddate;
		this.piestshipdate = piestshipdate;
		this.mfgstarteddate = mfgstarteddate;
		this.mfgfinisheddate = mfgfinisheddate;
		this.piattachedfilename = piattachedfilename;
		this.status = status;
		this.shipmentstatus = shipmentstatus;
		this.sewingguidename = sewingguidename;
		this.packingguidename = packingguidename;
		this.remark = remark;
		this.packingguidecode = packingguidecode;
		this.createdate = createdate;
		this.packingguide = packingguide;
		this.sewingguide = sewingguide;
		this.piattached = piattached;
	}

	@Override
	public String toString() {
		return "PiModel [lotnumber=" + lotnumber + ", destinationcode=" + destinationcode + ", country=" + country
				+ ", factorycode=" + factorycode + ", customer1=" + customer1 + ", consignee=" + consignee
				+ ", creator=" + creator + ", brandcode=" + brandcode + ", fpicode=" + fpicode + ", pigridcode="
				+ pigridcode + ", noneorderaccessories=" + noneorderaccessories + ", pireceiveddate=" + pireceiveddate
				+ ", piestshipdate=" + piestshipdate + ", mfgstarteddate=" + mfgstarteddate + ", mfgfinisheddate="
				+ mfgfinisheddate + ", piattachedfilename=" + piattachedfilename + ", status=" + status
				+ ", shipmentstatus=" + shipmentstatus + ", sewingguidename=" + sewingguidename + ", packingguidename="
				+ packingguidename + ", remark=" + remark + ", packingguidecode=" + packingguidecode + ", createdate="
				+ createdate + ", packingguide=" + packingguide + ", sewingguide=" + sewingguide + ", piattached="
				+ piattached + "]";
	}



	
}
