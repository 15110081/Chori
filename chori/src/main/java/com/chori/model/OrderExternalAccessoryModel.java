package com.chori.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderExternalAccessoryModel {
	private String orderSheetNo;
	private String lotNumber;
	private String accsuplierCode;
	private String factoryCode;
	private String accessoryCode;
	private String creator;
	private int orderQuantity;
	private int actualdevlQuantity;
	private float price;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date orderDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date estimatedevlDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date actualdevlDate;
	private String status;
	private String remark;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date createDate;
	private String factoryName;
	private String accsuplierName;
	private String accessoryName;
	private String paymentStatus;

	public String getOrderSheetNo() {
		return orderSheetNo;
	}

	public void setOrderSheetNo(String orderSheetNo) {
		this.orderSheetNo = orderSheetNo;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	public String getAccsuplierCode() {
		return accsuplierCode;
	}

	public void setAccsuplierCode(String accsuplierCode) {
		this.accsuplierCode = accsuplierCode;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getAccessoryCode() {
		return accessoryCode;
	}

	public void setAccessoryCode(String accessoryCode) {
		this.accessoryCode = accessoryCode;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public int getActualdevlQuantity() {
		return actualdevlQuantity;
	}

	public void setActualdevlQuantity(int actualdevlQuantity) {
		this.actualdevlQuantity = actualdevlQuantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getEstimatedevlDate() {
		return estimatedevlDate;
	}

	public void setEstimatedevlDate(Date estimatedevlDate) {
		this.estimatedevlDate = estimatedevlDate;
	}

	public Date getActualdevlDate() {
		return actualdevlDate;
	}

	public void setActualdevlDate(Date actualdevlDate) {
		this.actualdevlDate = actualdevlDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getAccsuplierName() {
		return accsuplierName;
	}

	public void setAccsuplierName(String accsuplierName) {
		this.accsuplierName = accsuplierName;
	}

	public String getAccessoryName() {
		return accessoryName;
	}

	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accessoryCode == null) ? 0 : accessoryCode.hashCode());
		result = prime * result
				+ ((accessoryName == null) ? 0 : accessoryName.hashCode());
		result = prime * result
				+ ((accsuplierCode == null) ? 0 : accsuplierCode.hashCode());
		result = prime * result
				+ ((accsuplierName == null) ? 0 : accsuplierName.hashCode());
		result = prime * result
				+ ((actualdevlDate == null) ? 0 : actualdevlDate.hashCode());
		result = prime * result + actualdevlQuantity;
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime
				* result
				+ ((estimatedevlDate == null) ? 0 : estimatedevlDate.hashCode());
		result = prime * result
				+ ((factoryCode == null) ? 0 : factoryCode.hashCode());
		result = prime * result
				+ ((factoryName == null) ? 0 : factoryName.hashCode());
		result = prime * result
				+ ((lotNumber == null) ? 0 : lotNumber.hashCode());
		result = prime * result
				+ ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + orderQuantity;
		result = prime * result
				+ ((orderSheetNo == null) ? 0 : orderSheetNo.hashCode());
		result = prime * result
				+ ((paymentStatus == null) ? 0 : paymentStatus.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		OrderExternalAccessoryModel other = (OrderExternalAccessoryModel) obj;
		if (accessoryCode == null) {
			if (other.accessoryCode != null)
				return false;
		} else if (!accessoryCode.equals(other.accessoryCode))
			return false;
		if (accessoryName == null) {
			if (other.accessoryName != null)
				return false;
		} else if (!accessoryName.equals(other.accessoryName))
			return false;
		if (accsuplierCode == null) {
			if (other.accsuplierCode != null)
				return false;
		} else if (!accsuplierCode.equals(other.accsuplierCode))
			return false;
		if (accsuplierName == null) {
			if (other.accsuplierName != null)
				return false;
		} else if (!accsuplierName.equals(other.accsuplierName))
			return false;
		if (actualdevlDate == null) {
			if (other.actualdevlDate != null)
				return false;
		} else if (!actualdevlDate.equals(other.actualdevlDate))
			return false;
		if (actualdevlQuantity != other.actualdevlQuantity)
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (estimatedevlDate == null) {
			if (other.estimatedevlDate != null)
				return false;
		} else if (!estimatedevlDate.equals(other.estimatedevlDate))
			return false;
		if (factoryCode == null) {
			if (other.factoryCode != null)
				return false;
		} else if (!factoryCode.equals(other.factoryCode))
			return false;
		if (factoryName == null) {
			if (other.factoryName != null)
				return false;
		} else if (!factoryName.equals(other.factoryName))
			return false;
		if (lotNumber == null) {
			if (other.lotNumber != null)
				return false;
		} else if (!lotNumber.equals(other.lotNumber))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderQuantity != other.orderQuantity)
			return false;
		if (orderSheetNo == null) {
			if (other.orderSheetNo != null)
				return false;
		} else if (!orderSheetNo.equals(other.orderSheetNo))
			return false;
		if (paymentStatus == null) {
			if (other.paymentStatus != null)
				return false;
		} else if (!paymentStatus.equals(other.paymentStatus))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderExternalAccessoryModel [orderSheetNo=" + orderSheetNo
				+ ", lotNumber=" + lotNumber + ", accsuplierCode="
				+ accsuplierCode + ", factoryCode=" + factoryCode
				+ ", accessoryCode=" + accessoryCode + ", creator=" + creator
				+ ", orderQuantity=" + orderQuantity + ", actualdevlQuantity="
				+ actualdevlQuantity + ", price=" + price + ", orderDate="
				+ orderDate + ", estimatedevlDate=" + estimatedevlDate
				+ ", actualdevlDate=" + actualdevlDate + ", status=" + status
				+ ", remark=" + remark + ", createDate=" + createDate
				+ ", factoryName=" + factoryName + ", accsuplierName="
				+ accsuplierName + ", accessoryName=" + accessoryName
				+ ", paymentStatus=" + paymentStatus + "]";
	}

}
