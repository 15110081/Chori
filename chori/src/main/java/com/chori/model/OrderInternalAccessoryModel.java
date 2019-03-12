package com.chori.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderInternalAccessoryModel {
	private String orderSheetNo;
	private String accsuplierCode;
	private String accsuplierName;
	private String factoryCode;
	private String factoryName;
	private String creator;
	
	private Date orderDate;

	private Date estimatedevlDate;
	
	private Date actualdevlDate;
	private String status;
	private String remark;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date createDate;
	private String invoiceNumber;
	
	private List<OrderInternalAccessoryDetailModel> lstOrderInternalAccessoryDetailModel= new ArrayList<OrderInternalAccessoryDetailModel>();

	public OrderInternalAccessoryModel(String orderSheetNo,
			String accsuplierCode, String accsuplierName, String factoryCode,
			String factoryName, String creator, Date orderDate,
			Date estimatedevlDate, Date actualdevlDate, String status,
			String remark, Date createDate, String invoiceNumber) {
		super();
		this.orderSheetNo = orderSheetNo;
		this.accsuplierCode = accsuplierCode;
		this.accsuplierName = accsuplierName;
		this.factoryCode = factoryCode;
		this.factoryName = factoryName;
		this.creator = creator;
		this.orderDate = orderDate;
		this.estimatedevlDate = estimatedevlDate;
		this.actualdevlDate = actualdevlDate;
		this.status = status;
		this.remark = remark;
		this.createDate = createDate;
		this.invoiceNumber = invoiceNumber;
	}

	public OrderInternalAccessoryModel(String accsuplierCode,
			String accsuplierName, String factoryCode, String factoryName,
			String creator, Date orderDate, Date estimatedevlDate,
			Date actualdevlDate, String status, String remark, Date createDate,
			String invoiceNumber) {
		super();
		this.accsuplierCode = accsuplierCode;
		this.accsuplierName = accsuplierName;
		this.factoryCode = factoryCode;
		this.factoryName = factoryName;
		this.creator = creator;
		this.orderDate = orderDate;
		this.estimatedevlDate = estimatedevlDate;
		this.actualdevlDate = actualdevlDate;
		this.status = status;
		this.remark = remark;
		this.createDate = createDate;
		this.invoiceNumber = invoiceNumber;
	}

	public OrderInternalAccessoryModel(String orderSheetNo) {
		super();
		this.orderSheetNo = orderSheetNo;
	}

	public OrderInternalAccessoryModel() {
		super();
	}

	public String getOrderSheetNo() {
		return orderSheetNo;
	}

	public void setOrderSheetNo(String orderSheetNo) {
		this.orderSheetNo = orderSheetNo;
	}

	public String getAccsuplierCode() {
		return accsuplierCode;
	}

	public void setAccsuplierCode(String accsuplierCode) {
		this.accsuplierCode = accsuplierCode;
	}

	public String getAccsuplierName() {
		return accsuplierName;
	}

	public void setAccsuplierName(String accsuplierName) {
		this.accsuplierName = accsuplierName;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public List<OrderInternalAccessoryDetailModel> getLstOrderInternalAccessoryDetailModel() {
		return lstOrderInternalAccessoryDetailModel;
	}

	public void setLstOrderInternalAccessoryDetailModel(
			List<OrderInternalAccessoryDetailModel> lstOrderInternalAccessoryDetailModel) {
		this.lstOrderInternalAccessoryDetailModel = lstOrderInternalAccessoryDetailModel;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result
//				+ ((accsuplierCode == null) ? 0 : accsuplierCode.hashCode());
//		result = prime * result
//				+ ((accsuplierName == null) ? 0 : accsuplierName.hashCode());
//		result = prime * result
//				+ ((actualdevlDate == null) ? 0 : actualdevlDate.hashCode());
//		result = prime * result
//				+ ((createDate == null) ? 0 : createDate.hashCode());
//		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
//		result = prime
//				* result
//				+ ((estimatedevlDate == null) ? 0 : estimatedevlDate.hashCode());
//		result = prime * result
//				+ ((factoryCode == null) ? 0 : factoryCode.hashCode());
//		result = prime * result
//				+ ((factoryName == null) ? 0 : factoryName.hashCode());
//		result = prime * result
//				+ ((invoiceNumber == null) ? 0 : invoiceNumber.hashCode());
//		result = prime * result
//				+ ((orderDate == null) ? 0 : orderDate.hashCode());
//		result = prime * result
//				+ ((orderSheetNo == null) ? 0 : orderSheetNo.hashCode());
//		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
//		result = prime * result + ((status == null) ? 0 : status.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		OrderInternalAccessoryModel other = (OrderInternalAccessoryModel) obj;
//		if (accsuplierCode == null) {
//			if (other.accsuplierCode != null)
//				return false;
//		} else if (!accsuplierCode.equals(other.accsuplierCode))
//			return false;
//		if (accsuplierName == null) {
//			if (other.accsuplierName != null)
//				return false;
//		} else if (!accsuplierName.equals(other.accsuplierName))
//			return false;
//		if (actualdevlDate == null) {
//			if (other.actualdevlDate != null)
//				return false;
//		} else if (!actualdevlDate.equals(other.actualdevlDate))
//			return false;
//		if (createDate == null) {
//			if (other.createDate != null)
//				return false;
//		} else if (!createDate.equals(other.createDate))
//			return false;
//		if (creator == null) {
//			if (other.creator != null)
//				return false;
//		} else if (!creator.equals(other.creator))
//			return false;
//		if (estimatedevlDate == null) {
//			if (other.estimatedevlDate != null)
//				return false;
//		} else if (!estimatedevlDate.equals(other.estimatedevlDate))
//			return false;
//		if (factoryCode == null) {
//			if (other.factoryCode != null)
//				return false;
//		} else if (!factoryCode.equals(other.factoryCode))
//			return false;
//		if (factoryName == null) {
//			if (other.factoryName != null)
//				return false;
//		} else if (!factoryName.equals(other.factoryName))
//			return false;
//		if (invoiceNumber == null) {
//			if (other.invoiceNumber != null)
//				return false;
//		} else if (!invoiceNumber.equals(other.invoiceNumber))
//			return false;
//		if (orderDate == null) {
//			if (other.orderDate != null)
//				return false;
//		} else if (!orderDate.equals(other.orderDate))
//			return false;
//		if (orderSheetNo == null) {
//			if (other.orderSheetNo != null)
//				return false;
//		} else if (!orderSheetNo.equals(other.orderSheetNo))
//			return false;
//		if (remark == null) {
//			if (other.remark != null)
//				return false;
//		} else if (!remark.equals(other.remark))
//			return false;
//		if (status == null) {
//			if (other.status != null)
//				return false;
//		} else if (!status.equals(other.status))
//			return false;
//		return true;
//	}

	@Override
	public String toString() {
		return "OrderInternalAccessoryModel [orderSheetNo=" + orderSheetNo
				+ ", accsuplierCode=" + accsuplierCode + ", accsuplierName="
				+ accsuplierName + ", factoryCode=" + factoryCode
				+ ", factoryName=" + factoryName + ", creator=" + creator
				+ ", orderDate=" + orderDate + ", estimatedevlDate="
				+ estimatedevlDate + ", actualdevlDate=" + actualdevlDate
				+ ", status=" + status + ", remark=" + remark + ", createDate="
				+ createDate + ", invoiceNumber=" + invoiceNumber
				+ ", lstOrderInternalAccessoryDetailModel="
				+ lstOrderInternalAccessoryDetailModel + "]";
	}

}
