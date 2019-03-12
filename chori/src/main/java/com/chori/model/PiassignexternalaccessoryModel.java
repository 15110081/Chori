package com.chori.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
public class PiassignexternalaccessoryModel implements Serializable {

	private Integer piassignexternalaccessorycode;
	private String accessoryCode;
	private String accessoryName;
	//tổng estimate tính theo accessory
	private Float totalEstimateByAccessory;
	private Float totalEstimateFpiByAccessory;
	private Float totalEstimateRfpiByAccessory;
	private String colorcode;
	private String colorName;
	private Integer sizecode;
	private String sizename;
	private String garmentstylecode;
	private String garmentstyleName;
	private String lotnumber;
	private Integer pigriddetail;
	private String creator;
	private Float estimateqty;
	private Float orderqty;
	private Float actualqty;
	private Float specificconsumption;
	private Float specificequivalent;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date createdate;
	// Manufacturing or Packing
	private String mode;
	// Internal or external
	private String kind;
	// img to display
	private String imgurl = "";
	// accessorySupplier
	private String accsuppliercode;
	private String accsupplierShortname;
	// factory
	private String factorycode;
	private String factoryShortname;
	// pcs, est gross, gross unit
	private String unitPcs = "pcs";
	private Float estimateqtyGross;
	private String unitGross;

	private Float usedInventoryQty;
	private Integer orderQty;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date orderDate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date estimateDeliveryDate;
	private Integer actualQty;
	private String status;
	private String paymentStatus;

	// check is assign or not
	private boolean assign;

	private Integer garmentstyleaccessorydetailcode;
	
	//hiển thị type ra
	private String typecode;
	//hiển thị accessory supplier short name ra
	private String accessorySupplierShortname;
	
	//giá trị estimate cho fpi
	private Float estimateFpiQty;
	//giá trị estimate cho fpi
	private Float estimateRfpiQty;
	
	private String orderSheetNoAndStatus;
	
	//garment style display name
	private String garmentStyleDisplayName;

	public Integer getPiassignexternalaccessorycode() {
		return piassignexternalaccessorycode;
	}

	public void setPiassignexternalaccessorycode(
			Integer piassignexternalaccessorycode) {
		this.piassignexternalaccessorycode = piassignexternalaccessorycode;
	}

	public String getAccessoryCode() {
		return accessoryCode;
	}

	public void setAccessoryCode(String accessoryCode) {
		this.accessoryCode = accessoryCode;
	}

	public String getAccessoryName() {
		return accessoryName;
	}

	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}

	public String getColorcode() {
		return colorcode;
	}

	public void setColorcode(String colorcode) {
		this.colorcode = colorcode;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
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

	public String getGarmentstylecode() {
		return garmentstylecode;
	}

	public void setGarmentstylecode(String garmentstylecode) {
		this.garmentstylecode = garmentstylecode;
	}

	public String getGarmentstyleName() {
		return garmentstyleName;
	}

	public void setGarmentstyleName(String garmentstyleName) {
		this.garmentstyleName = garmentstyleName;
	}

	public String getLotnumber() {
		return lotnumber;
	}

	public void setLotnumber(String lotnumber) {
		this.lotnumber = lotnumber;
	}

	public Integer getPigriddetail() {
		return pigriddetail;
	}

	public void setPigriddetail(Integer pigriddetail) {
		this.pigriddetail = pigriddetail;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Float getEstimateqty() {
		return estimateqty;
	}

	public void setEstimateqty(Float estimateqty) {
		this.estimateqty = estimateqty;
	}

	public Float getOrderqty() {
		return orderqty;
	}

	public void setOrderqty(Float orderqty) {
		this.orderqty = orderqty;
	}

	public Float getActualqty() {
		return actualqty;
	}

	public void setActualqty(Float actualqty) {
		this.actualqty = actualqty;
	}

	public Float getSpecificconsumption() {
		return specificconsumption;
	}

	public void setSpecificconsumption(Float specificconsumption) {
		this.specificconsumption = specificconsumption;
	}

	public Float getSpecificequivalent() {
		return specificequivalent;
	}

	public void setSpecificequivalent(Float specificequivalent) {
		this.specificequivalent = specificequivalent;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getAccsuppliercode() {
		return accsuppliercode;
	}

	public void setAccsuppliercode(String accsuppliercode) {
		this.accsuppliercode = accsuppliercode;
	}

	public String getAccsupplierShortname() {
		return accsupplierShortname;
	}

	public void setAccsupplierShortname(String accsupplierShortname) {
		this.accsupplierShortname = accsupplierShortname;
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

	public String getUnitPcs() {
		return unitPcs;
	}

	public void setUnitPcs(String unitPcs) {
		this.unitPcs = unitPcs;
	}

	public Float getEstimateqtyGross() {
		return estimateqtyGross;
	}

	public void setEstimateqtyGross(Float estimateqtyGross) {
		this.estimateqtyGross = estimateqtyGross;
	}

	public String getUnitGross() {
		return unitGross;
	}

	public void setUnitGross(String unitGross) {
		this.unitGross = unitGross;
	}

	public Float getUsedInventoryQty() {
		return usedInventoryQty;
	}

	public void setUsedInventoryQty(Float usedInventoryQty) {
		this.usedInventoryQty = usedInventoryQty;
	}

	public Integer getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getEstimateDeliveryDate() {
		return estimateDeliveryDate;
	}

	public void setEstimateDeliveryDate(Date estimateDeliveryDate) {
		this.estimateDeliveryDate = estimateDeliveryDate;
	}

	public Integer getActualQty() {
		return actualQty;
	}

	public void setActualQty(Integer actualQty) {
		this.actualQty = actualQty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public boolean isAssign() {
		return assign;
	}

	public void setAssign(boolean assign) {
		this.assign = assign;
	}

	public Integer getGarmentstyleaccessorydetailcode() {
		return garmentstyleaccessorydetailcode;
	}

	public void setGarmentstyleaccessorydetailcode(
			Integer garmentstyleaccessorydetailcode) {
		this.garmentstyleaccessorydetailcode = garmentstyleaccessorydetailcode;
	}

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	public String getAccessorySupplierShortname() {
		return accessorySupplierShortname;
	}

	public void setAccessorySupplierShortname(String accessorySupplierShortname) {
		this.accessorySupplierShortname = accessorySupplierShortname;
	}

	public Float getEstimateFpiQty() {
		return estimateFpiQty;
	}

	public void setEstimateFpiQty(Float estimateFpiQty) {
		this.estimateFpiQty = estimateFpiQty;
	}

	public Float getEstimateRfpiQty() {
		return estimateRfpiQty;
	}

	public void setEstimateRfpiQty(Float estimateRfpiQty) {
		this.estimateRfpiQty = estimateRfpiQty;
	}

	public Float getTotalEstimateByAccessory() {
		return totalEstimateByAccessory;
	}

	public void setTotalEstimateByAccessory(Float totalEstimateByAccessory) {
		this.totalEstimateByAccessory = totalEstimateByAccessory;
	}

	public Float getTotalEstimateFpiByAccessory() {
		return totalEstimateFpiByAccessory;
	}

	public void setTotalEstimateFpiByAccessory(Float totalEstimateFpiByAccessory) {
		this.totalEstimateFpiByAccessory = totalEstimateFpiByAccessory;
	}

	public Float getTotalEstimateRfpiByAccessory() {
		return totalEstimateRfpiByAccessory;
	}

	public void setTotalEstimateRfpiByAccessory(Float totalEstimateRfpiByAccessory) {
		this.totalEstimateRfpiByAccessory = totalEstimateRfpiByAccessory;
	}

	public String getOrderSheetNoAndStatus() {
		return orderSheetNoAndStatus;
	}

	public void setOrderSheetNoAndStatus(String orderSheetNoAndStatus) {
		this.orderSheetNoAndStatus = orderSheetNoAndStatus;
	}

	public String getGarmentStyleDisplayName() {
		return garmentStyleDisplayName;
	}

	public void setGarmentStyleDisplayName(String garmentStyleDisplayName) {
		this.garmentStyleDisplayName = garmentStyleDisplayName;
	}

	public static Comparator<PiassignexternalaccessoryModel> getAccessoryNameComparator() {
		return accessoryNameComparator;
	}

	public static void setAccessoryNameComparator(
			Comparator<PiassignexternalaccessoryModel> accessoryNameComparator) {
		PiassignexternalaccessoryModel.accessoryNameComparator = accessoryNameComparator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((piassignexternalaccessorycode == null) ? 0
						: piassignexternalaccessorycode.hashCode());
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
		PiassignexternalaccessoryModel other = (PiassignexternalaccessoryModel) obj;
		if (piassignexternalaccessorycode == null) {
			if (other.piassignexternalaccessorycode != null)
				return false;
		} else if (!piassignexternalaccessorycode
				.equals(other.piassignexternalaccessorycode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PiassignexternalaccessoryModel [piassignexternalaccessorycode="
				+ piassignexternalaccessorycode + ", accessoryCode="
				+ accessoryCode + ", accessoryName=" + accessoryName
				+ ", colorcode=" + colorcode + ", colorName=" + colorName
				+ ", sizecode=" + sizecode + ", sizename=" + sizename
				+ ", garmentstylecode=" + garmentstylecode
				+ ", garmentstyleName=" + garmentstyleName + ", lotnumber="
				+ lotnumber + ", pigriddetail=" + pigriddetail + ", creator="
				+ creator + ", estimateqty=" + estimateqty + ", orderqty="
				+ orderqty + ", actualqty=" + actualqty
				+ ", specificconsumption=" + specificconsumption
				+ ", specificequivalent=" + specificequivalent
				+ ", createdate=" + createdate + ", mode=" + mode + ", kind="
				+ kind + ", imgurl=" + imgurl + ", accsuppliercode="
				+ accsuppliercode + ", accsupplierShortname="
				+ accsupplierShortname + ", factorycode=" + factorycode
				+ ", factoryShortname=" + factoryShortname + ", unitPcs="
				+ unitPcs + ", estimateqtyGross=" + estimateqtyGross
				+ ", unitGross=" + unitGross + ", usedInventoryQty="
				+ usedInventoryQty + ", orderQty=" + orderQty + ", orderDate="
				+ orderDate + ", estimateDeliveryDate=" + estimateDeliveryDate
				+ ", actualQty=" + actualQty + ", status=" + status
				+ ", paymentStatus=" + paymentStatus + ", assign=" + assign
				+ ", garmentstyleaccessorydetailcode="
				+ garmentstyleaccessorydetailcode + ", typecode=" + typecode
				+ ", accessorySupplierShortname=" + accessorySupplierShortname
				+ "]";
	}

	public PiassignexternalaccessoryModel(
			Integer piassignexternalaccessorycode, String accessoryCode,
			String accessoryName, String colorcode, String colorName,
			Integer sizecode, String sizename, String garmentstylecode,
			String garmentstyleName, String lotnumber, Integer pigriddetail,
			String creator, Float estimateqty, Float orderqty, Float actualqty,
			Float specificconsumption, Float specificequivalent, Date createdate) {
		super();
		this.piassignexternalaccessorycode = piassignexternalaccessorycode;
		this.accessoryCode = accessoryCode;
		this.accessoryName = accessoryName;
		this.colorcode = colorcode;
		this.colorName = colorName;
		this.sizecode = sizecode;
		this.sizename = sizename;
		this.garmentstylecode = garmentstylecode;
		this.garmentstyleName = garmentstyleName;
		this.lotnumber = lotnumber;
		this.pigriddetail = pigriddetail;
		this.creator = creator;
		this.estimateqty = estimateqty;
		this.orderqty = orderqty;
		this.actualqty = actualqty;
		this.specificconsumption = specificconsumption;
		this.specificequivalent = specificequivalent;
		this.createdate = createdate;
	}

	public PiassignexternalaccessoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	// compare to sort by accessory Name
	public static Comparator<PiassignexternalaccessoryModel> accessoryNameComparator = new Comparator<PiassignexternalaccessoryModel>() {

		@Override
		public int compare(PiassignexternalaccessoryModel o1,
				PiassignexternalaccessoryModel o2) {
			String accessoryName1 = o1.getAccessoryName();
			String accessoryName2 = o2.getAccessoryName();

			return accessoryName1.compareTo(accessoryName2);
		}
	};
}
