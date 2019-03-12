package com.chori.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderInternalAccessoryDetailModel {
	private String accessorycode;
	private String accessoryname;
	private String ordersheetno;
	private String user;
	private String unit;
	private Double unitprice;
	private String currency;
	private Double orderquantity;
	private Double actualdelvquantity;
	private Double price;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date createdate;
	private String image;
	private Date orderDate;

	

	public OrderInternalAccessoryDetailModel() {
		super();
	}

	public OrderInternalAccessoryDetailModel(String accessorycode,
			String accessoryname, String ordersheetno, String user,
			String unit, Double unitprice, String currency,
			Double orderquantity, Double actualdelvquantity, Double price,
			Date createdate) {
		super();
		this.accessorycode = accessorycode;
		this.accessoryname = accessoryname;
		this.ordersheetno = ordersheetno;
		this.user = user;
		this.unit = unit;
		this.unitprice = unitprice;
		this.currency = currency;
		this.orderquantity = orderquantity;
		this.actualdelvquantity = actualdelvquantity;
		this.price = price;
		this.createdate = createdate;
	}

	public String getAccessorycode() {
		return accessorycode;
	}

	public void setAccessorycode(String accessorycode) {
		this.accessorycode = accessorycode;
	}

	public String getAccessoryname() {
		return accessoryname;
	}

	public void setAccessoryname(String accessoryname) {
		this.accessoryname = accessoryname;
	}

	public String getOrdersheetno() {
		return ordersheetno;
	}

	public void setOrdersheetno(String ordersheetno) {
		this.ordersheetno = ordersheetno;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getOrderquantity() {
		return orderquantity;
	}

	public void setOrderquantity(Double orderquantity) {
		this.orderquantity = orderquantity;
	}

	public Double getActualdelvquantity() {
		return actualdelvquantity;
	}

	public void setActualdelvquantity(Double actualdelvquantity) {
		this.actualdelvquantity = actualdelvquantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double double1) {
		this.price = double1;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result
//				+ ((accessorycode == null) ? 0 : accessorycode.hashCode());
//		result = prime * result
//				+ ((accessoryname == null) ? 0 : accessoryname.hashCode());
//		result = prime
//				* result
//				+ ((actualdelvquantity == null) ? 0 : actualdelvquantity
//						.hashCode());
//		result = prime * result
//				+ ((createdate == null) ? 0 : createdate.hashCode());
//		result = prime * result
//				+ ((currency == null) ? 0 : currency.hashCode());
//		result = prime * result
//				+ ((orderquantity == null) ? 0 : orderquantity.hashCode());
//		result = prime * result
//				+ ((ordersheetno == null) ? 0 : ordersheetno.hashCode());
//		result = prime * result + ((price == null) ? 0 : price.hashCode());
//		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
//		result = prime * result
//				+ ((unitprice == null) ? 0 : unitprice.hashCode());
//		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
//		OrderInternalAccessoryDetailModel other = (OrderInternalAccessoryDetailModel) obj;
//		if (accessorycode == null) {
//			if (other.accessorycode != null)
//				return false;
//		} else if (!accessorycode.equals(other.accessorycode))
//			return false;
//		if (accessoryname == null) {
//			if (other.accessoryname != null)
//				return false;
//		} else if (!accessoryname.equals(other.accessoryname))
//			return false;
//		if (actualdelvquantity == null) {
//			if (other.actualdelvquantity != null)
//				return false;
//		} else if (!actualdelvquantity.equals(other.actualdelvquantity))
//			return false;
//		if (createdate == null) {
//			if (other.createdate != null)
//				return false;
//		} else if (!createdate.equals(other.createdate))
//			return false;
//		if (currency == null) {
//			if (other.currency != null)
//				return false;
//		} else if (!currency.equals(other.currency))
//			return false;
//		if (orderquantity == null) {
//			if (other.orderquantity != null)
//				return false;
//		} else if (!orderquantity.equals(other.orderquantity))
//			return false;
//		if (ordersheetno == null) {
//			if (other.ordersheetno != null)
//				return false;
//		} else if (!ordersheetno.equals(other.ordersheetno))
//			return false;
//		if (price == null) {
//			if (other.price != null)
//				return false;
//		} else if (!price.equals(other.price))
//			return false;
//		if (unit == null) {
//			if (other.unit != null)
//				return false;
//		} else if (!unit.equals(other.unit))
//			return false;
//		if (unitprice == null) {
//			if (other.unitprice != null)
//				return false;
//		} else if (!unitprice.equals(other.unitprice))
//			return false;
//		if (user == null) {
//			if (other.user != null)
//				return false;
//		} else if (!user.equals(other.user))
//			return false;
//		return true;
//	}

	@Override
	public String toString() {
		return "OrderInternalAccessoryDetailModel [accessorycode="
				+ accessorycode + ", accessoryname=" + accessoryname
				+ ", ordersheetno=" + ordersheetno + ", user=" + user
				+ ", unit=" + unit + ", unitprice=" + unitprice + ", currency="
				+ currency + ", orderquantity=" + orderquantity
				+ ", actualdelvquantity=" + actualdelvquantity + ", price="
				+ price + ", createdate=" + createdate + ", image=" + image
				+ ", orderDate=" + orderDate + "]";
	}

}
