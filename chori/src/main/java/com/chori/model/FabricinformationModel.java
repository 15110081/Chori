package com.chori.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
public class FabricinformationModel implements Serializable {
	private String fabricno;
	private String fabricitem;
	private String customer;
	private String fabricsupplier;
	private String factory;
	private String creator;
	private Boolean ischori;
	private String fabricinvoiceno;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date createdate;
	private String currencycode;
	private String currencyName;
	private String customerShortname;
	private String fabricsupplierShortname;
	private String factoryShortname;
	private String widthcode;
	private String widthvalues;
	private String component;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date estimatedelvdate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date actualdelvdate;
	private String fabricimgurl;
	private String remark;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date voucherreceiveddate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date vouchersentdate;
	
	private String listColor;
	private Double totalYard;

	public FabricinformationModel() {
		super();
	}

	public FabricinformationModel(String fabricno) {
		super();
		this.fabricno = fabricno;
	}

	public FabricinformationModel(String fabricno, String fabricitem,
			String customer, String fabricsupplier, String factory,
			String creator, Boolean ischori, String fabricinvoiceno,
			Date createdate) {
		super();
		this.fabricno = fabricno;
		this.fabricitem = fabricitem;
		this.customer = customer;
		this.fabricsupplier = fabricsupplier;
		this.factory = factory;
		this.creator = creator;
		this.ischori = ischori;
		this.fabricinvoiceno = fabricinvoiceno;
		this.createdate = createdate;
	}

	public FabricinformationModel(String fabricno, String fabricitem,
			String customer, String fabricsupplier, String factory,
			String creator, Boolean ischori, String fabricinvoiceno,
			Date createdate, String currencycode, String currencyName,
			String customerShortname, String fabricsupplierShortname,
			String factoryShortname, String widthcode, String widthvalues,
			String component, Date estimatedelvdate, Date actualdelvdate,
			String fabricimgurl, String remark) {
		super();
		this.fabricno = fabricno;
		this.fabricitem = fabricitem;
		this.customer = customer;
		this.fabricsupplier = fabricsupplier;
		this.factory = factory;
		this.creator = creator;
		this.ischori = ischori;
		this.fabricinvoiceno = fabricinvoiceno;
		this.createdate = createdate;
		this.currencycode = currencycode;
		this.currencyName = currencyName;
		this.customerShortname = customerShortname;
		this.fabricsupplierShortname = fabricsupplierShortname;
		this.factoryShortname = factoryShortname;
		this.widthcode = widthcode;
		this.widthvalues = widthvalues;
		this.component = component;
		this.estimatedelvdate = estimatedelvdate;
		this.actualdelvdate = actualdelvdate;
		this.fabricimgurl = fabricimgurl;
		this.remark = remark;
	}

	public String getFabricno() {
		return fabricno;
	}

	public void setFabricno(String fabricno) {
		this.fabricno = fabricno;
	}

	public String getFabricitem() {
		return fabricitem;
	}

	public void setFabricitem(String fabricitem) {
		this.fabricitem = fabricitem;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getFabricsupplier() {
		return fabricsupplier;
	}

	public void setFabricsupplier(String fabricsupplier) {
		this.fabricsupplier = fabricsupplier;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Boolean getIschori() {
		return ischori;
	}

	public void setIschori(Boolean ischori) {
		this.ischori = ischori;
	}

	public String getFabricinvoiceno() {
		return fabricinvoiceno;
	}

	public void setFabricinvoiceno(String fabricinvoiceno) {
		this.fabricinvoiceno = fabricinvoiceno;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getCurrencycode() {
		return currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCustomerShortname() {
		return customerShortname;
	}

	public void setCustomerShortname(String customerShortname) {
		this.customerShortname = customerShortname;
	}

	public String getFabricsupplierShortname() {
		return fabricsupplierShortname;
	}

	public void setFabricsupplierShortname(String fabricsupplierShortname) {
		this.fabricsupplierShortname = fabricsupplierShortname;
	}

	public String getFactoryShortname() {
		return factoryShortname;
	}

	public void setFactoryShortname(String factoryShortname) {
		this.factoryShortname = factoryShortname;
	}

	public String getWidthcode() {
		return widthcode;
	}

	public void setWidthcode(String widthcode) {
		this.widthcode = widthcode;
	}

	public String getWidthvalues() {
		return widthvalues;
	}

	public void setWidthvalues(String widthvalues) {
		this.widthvalues = widthvalues;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public Date getEstimatedelvdate() {
		return estimatedelvdate;
	}

	public void setEstimatedelvdate(Date estimatedelvdate) {
		this.estimatedelvdate = estimatedelvdate;
	}

	public Date getActualdelvdate() {
		return actualdelvdate;
	}

	public void setActualdelvdate(Date actualdelvdate) {
		this.actualdelvdate = actualdelvdate;
	}

	public String getFabricimgurl() {
		return fabricimgurl;
	}

	public void setFabricimgurl(String fabricimgurl) {
		this.fabricimgurl = fabricimgurl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getVoucherreceiveddate() {
		return voucherreceiveddate;
	}

	public void setVoucherreceiveddate(Date voucherreceiveddate) {
		this.voucherreceiveddate = voucherreceiveddate;
	}

	public Date getVouchersentdate() {
		return vouchersentdate;
	}

	public void setVouchersentdate(Date vouchersentdate) {
		this.vouchersentdate = vouchersentdate;
	}

	public String getListColor() {
		return listColor;
	}

	public void setListColor(String listColor) {
		this.listColor = listColor;
	}

	public Double getTotalYard() {
		return totalYard;
	}

	public void setTotalYard(Double totalYard) {
		this.totalYard = totalYard;
	}

	@Override
	public String toString() {
		return "FabricinformationModel [fabricno=" + fabricno + ", fabricitem="
				+ fabricitem + ", customer=" + customer + ", fabricsupplier="
				+ fabricsupplier + ", factory=" + factory + ", creator="
				+ creator + ", ischori=" + ischori + ", fabricinvoiceno="
				+ fabricinvoiceno + ", createdate=" + createdate
				+ ", currencycode=" + currencycode + ", currencyName="
				+ currencyName + ", customerShortname=" + customerShortname
				+ ", fabricsupplierShortname=" + fabricsupplierShortname
				+ ", factoryShortname=" + factoryShortname + ", widthcode="
				+ widthcode + ", widthvalues=" + widthvalues + ", component="
				+ component + ", estimatedelvdate=" + estimatedelvdate
				+ ", actualdelvdate=" + actualdelvdate + ", fabricimgurl="
				+ fabricimgurl + ", remark=" + remark
				+ ", voucherreceiveddate=" + voucherreceiveddate
				+ ", vouchersentdate=" + vouchersentdate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fabricno == null) ? 0 : fabricno.hashCode());
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
		FabricinformationModel other = (FabricinformationModel) obj;
		if (fabricno == null) {
			if (other.fabricno != null)
				return false;
		} else if (!fabricno.equals(other.fabricno))
			return false;
		return true;
	}

}
