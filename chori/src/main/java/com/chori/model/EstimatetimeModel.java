package com.chori.model;

import java.util.Date;

public class EstimatetimeModel {
	private int estimateCode;
	private int piconpletion;
	private int packingaccdelv;
	private int manuaccdelv;
	private Date createdate;
	private String creator;

	public EstimatetimeModel(int estimateCode, int piconpletion,
			int packingaccdelv, int manuaccdelv, Date createdate, String creator) {
		super();
		this.estimateCode = estimateCode;
		this.piconpletion = piconpletion;
		this.packingaccdelv = packingaccdelv;
		this.manuaccdelv = manuaccdelv;
		this.createdate = createdate;
		this.creator = creator;
	}

	public EstimatetimeModel() {
		super();
	}

	public int getEstimateCode() {
		return estimateCode;
	}

	public void setEstimateCode(int estimateCode) {
		this.estimateCode = estimateCode;
	}

	public int getPiconpletion() {
		return piconpletion;
	}

	public void setPiconpletion(int piconpletion) {
		this.piconpletion = piconpletion;
	}

	public int getPackingaccdelv() {
		return packingaccdelv;
	}

	public void setPackingaccdelv(int packingaccdelv) {
		this.packingaccdelv = packingaccdelv;
	}

	public int getManuaccdelv() {
		return manuaccdelv;
	}

	public void setManuaccdelv(int manuaccdelv) {
		this.manuaccdelv = manuaccdelv;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdate == null) ? 0 : createdate.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + estimateCode;
		result = prime * result + manuaccdelv;
		result = prime * result + packingaccdelv;
		result = prime * result + piconpletion;
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
		EstimatetimeModel other = (EstimatetimeModel) obj;
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
		if (estimateCode != other.estimateCode)
			return false;
		if (manuaccdelv != other.manuaccdelv)
			return false;
		if (packingaccdelv != other.packingaccdelv)
			return false;
		if (piconpletion != other.piconpletion)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EstimatetimeModel [estimateCode=" + estimateCode
				+ ", piconpletion=" + piconpletion + ", packingaccdelv="
				+ packingaccdelv + ", manuaccdelv=" + manuaccdelv
				+ ", createdate=" + createdate + ", creator=" + creator + "]";
	}

}
