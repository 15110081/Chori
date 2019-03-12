package com.chori.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AccessoryPriceModel {
	private Integer accessorypricecode;
	private String accessorycode;
	private String mode;
	private String name;
	private String unitcode;
	private String accessorysuppliercode;
	private String accessorysuppliername;
	private String currencycode;
	private String creator;
	private Date fromdate;
	private Date todate;
	private Float unitpriceperunit;
	private Date createdate;
	private String status;
	private String accessorygroupcode;
	private String remark;

	public AccessoryPriceModel(Integer accessorypricecode,
			String accessorycode, String mode, String name, String unitcode,
			String accessorysuppliercode, String accessorysuppliername,
			String currencycode, String creator, Date fromdate, Date todate,
			Float unitpriceperunit, Date createdate, String status,
			String accessorygroupcode, String remark) {
		super();
		this.accessorypricecode = accessorypricecode;
		this.accessorycode = accessorycode;
		this.mode = mode;
		this.name = name;
		this.unitcode = unitcode;
		this.accessorysuppliercode = accessorysuppliercode;
		this.accessorysuppliername = accessorysuppliername;
		this.currencycode = currencycode;
		this.creator = creator;
		this.fromdate = fromdate;
		this.todate = todate;
		this.unitpriceperunit = unitpriceperunit;
		this.createdate = createdate;
		this.status = status;
		this.accessorygroupcode = accessorygroupcode;
		this.remark = remark;
	}

	public AccessoryPriceModel(String accessorycode, String mode, String name,
			String unitcode, String accessorysuppliercode,
			String accessorysuppliername, String currencycode, String creator,
			Date fromdate, Date todate, Float unitpriceperunit,
			Date createdate, String status, String accessorygroupcode,
			String remark) {
		super();
		this.accessorycode = accessorycode;
		this.mode = mode;
		this.name = name;
		this.unitcode = unitcode;
		this.accessorysuppliercode = accessorysuppliercode;
		this.accessorysuppliername = accessorysuppliername;
		this.currencycode = currencycode;
		this.creator = creator;
		this.fromdate = fromdate;
		this.todate = todate;
		this.unitpriceperunit = unitpriceperunit;
		this.createdate = createdate;
		this.status = status;
		this.accessorygroupcode = accessorygroupcode;
		this.remark = remark;
	}

	public AccessoryPriceModel(Integer accessorypricecode) {
		super();
		this.accessorypricecode = accessorypricecode;
	}

	public AccessoryPriceModel() {
		super();
	}

	public Integer getAccessorypricecode() {
		return accessorypricecode;
	}

	public void setAccessorypricecode(Integer accessorypricecode) {
		this.accessorypricecode = accessorypricecode;
	}

	public String getAccessorycode() {
		return accessorycode;
	}

	public void setAccessorycode(String accessorycode) {
		this.accessorycode = accessorycode;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnitcode() {
		return unitcode;
	}

	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}

	public String getAccessorysuppliercode() {
		return accessorysuppliercode;
	}

	public void setAccessorysuppliercode(String accessorysuppliercode) {
		this.accessorysuppliercode = accessorysuppliercode;
	}

	public String getAccessorysuppliername() {
		return accessorysuppliername;
	}

	public void setAccessorysuppliername(String accessorysuppliername) {
		this.accessorysuppliername = accessorysuppliername;
	}

	public String getCurrencycode() {
		return currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getFromdate() {
		return fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public Date getTodate() {
		return todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	public Float getUnitpriceperunit() {
		return unitpriceperunit;
	}

	public void setUnitpriceperunit(Float unitpriceperunit) {
		this.unitpriceperunit = unitpriceperunit;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus() {
		Date tmp = new Date();
		Date tmp2 = new Date();
		tmp2.setTime(tmp.getTime() - 1 * 24 * 60 * 60 * 1000);

		if (this.getFromdate().before(new Date()) && this.getTodate() == null) {
			this.status = "Non-Expired";
		} else if (this.getFromdate().before(new Date())
				&& this.getTodate().after(tmp2)) {
			this.status = "Non-Expired";
		} else
			this.status = "Expired";

	}

	public String getAccessorygroupcode() {
		return accessorygroupcode;
	}

	public void setAccessorygroupcode(String accessorygroupcode) {
		this.accessorygroupcode = accessorygroupcode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accessorycode == null) ? 0 : accessorycode.hashCode());
		result = prime
				* result
				+ ((accessorygroupcode == null) ? 0 : accessorygroupcode
						.hashCode());
		result = prime
				* result
				+ ((accessorypricecode == null) ? 0 : accessorypricecode
						.hashCode());
		result = prime
				* result
				+ ((accessorysuppliercode == null) ? 0 : accessorysuppliercode
						.hashCode());
		result = prime
				* result
				+ ((accessorysuppliername == null) ? 0 : accessorysuppliername
						.hashCode());
		result = prime * result
				+ ((createdate == null) ? 0 : createdate.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result
				+ ((currencycode == null) ? 0 : currencycode.hashCode());
		result = prime * result
				+ ((fromdate == null) ? 0 : fromdate.hashCode());
		result = prime * result + ((mode == null) ? 0 : mode.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((todate == null) ? 0 : todate.hashCode());
		result = prime * result
				+ ((unitcode == null) ? 0 : unitcode.hashCode());
		result = prime
				* result
				+ ((unitpriceperunit == null) ? 0 : unitpriceperunit.hashCode());
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
		AccessoryPriceModel other = (AccessoryPriceModel) obj;
		if (accessorycode == null) {
			if (other.accessorycode != null)
				return false;
		} else if (!accessorycode.equals(other.accessorycode))
			return false;
		if (accessorygroupcode == null) {
			if (other.accessorygroupcode != null)
				return false;
		} else if (!accessorygroupcode.equals(other.accessorygroupcode))
			return false;
		if (accessorypricecode == null) {
			if (other.accessorypricecode != null)
				return false;
		} else if (!accessorypricecode.equals(other.accessorypricecode))
			return false;
		if (accessorysuppliercode == null) {
			if (other.accessorysuppliercode != null)
				return false;
		} else if (!accessorysuppliercode.equals(other.accessorysuppliercode))
			return false;
		if (accessorysuppliername == null) {
			if (other.accessorysuppliername != null)
				return false;
		} else if (!accessorysuppliername.equals(other.accessorysuppliername))
			return false;
		if (createdate == null) {
			if (other.createdate != null)
				return false;
		} else if (!createdate.equals(other.createdate))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (currencycode == null) {
			if (other.currencycode != null)
				return false;
		} else if (!currencycode.equals(other.currencycode))
			return false;
		if (fromdate == null) {
			if (other.fromdate != null)
				return false;
		} else if (!fromdate.equals(other.fromdate))
			return false;
		if (mode == null) {
			if (other.mode != null)
				return false;
		} else if (!mode.equals(other.mode))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
		if (todate == null) {
			if (other.todate != null)
				return false;
		} else if (!todate.equals(other.todate))
			return false;
		if (unitcode == null) {
			if (other.unitcode != null)
				return false;
		} else if (!unitcode.equals(other.unitcode))
			return false;
		if (unitpriceperunit == null) {
			if (other.unitpriceperunit != null)
				return false;
		} else if (!unitpriceperunit.equals(other.unitpriceperunit))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AccessoryPriceModel [accessorypricecode=" + accessorypricecode
				+ ", accessorycode=" + accessorycode + ", mode=" + mode
				+ ", name=" + name + ", unitcode=" + unitcode
				+ ", accessorysuppliercode=" + accessorysuppliercode
				+ ", accessorysuppliername=" + accessorysuppliername
				+ ", currencycode=" + currencycode + ", creator=" + creator
				+ ", fromdate=" + fromdate + ", todate=" + todate
				+ ", unitpriceperunit=" + unitpriceperunit + ", createdate="
				+ createdate + ", status=" + status + ", accessorygroupcode="
				+ accessorygroupcode + ", remark=" + remark + "]";
	}

}
