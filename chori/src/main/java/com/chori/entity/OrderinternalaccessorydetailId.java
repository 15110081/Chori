package com.chori.entity;
// Generated Dec 8, 2016 6:21:17 PM by Hibernate Tools 5.1.0.Beta1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OrderinternalaccessorydetailId generated by hbm2java
 */
@Embeddable
public class OrderinternalaccessorydetailId implements java.io.Serializable {

	private String accessorycode;
	private String ordersheetno;

	public OrderinternalaccessorydetailId() {
	}

	public OrderinternalaccessorydetailId(String accessorycode, String ordersheetno) {
		this.accessorycode = accessorycode;
		this.ordersheetno = ordersheetno;
	}

	@Column(name = "ACCESSORYCODE", nullable = false, length = 100)
	public String getAccessorycode() {
		return this.accessorycode;
	}

	public void setAccessorycode(String accessorycode) {
		this.accessorycode = accessorycode;
	}

	@Column(name = "ORDERSHEETNO", nullable = false, length = 50)
	public String getOrdersheetno() {
		return this.ordersheetno;
	}

	public void setOrdersheetno(String ordersheetno) {
		this.ordersheetno = ordersheetno;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OrderinternalaccessorydetailId))
			return false;
		OrderinternalaccessorydetailId castOther = (OrderinternalaccessorydetailId) other;

		return ((this.getAccessorycode() == castOther.getAccessorycode())
				|| (this.getAccessorycode() != null && castOther.getAccessorycode() != null
						&& this.getAccessorycode().equals(castOther.getAccessorycode())))
				&& ((this.getOrdersheetno() == castOther.getOrdersheetno())
						|| (this.getOrdersheetno() != null && castOther.getOrdersheetno() != null
								&& this.getOrdersheetno().equals(castOther.getOrdersheetno())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getAccessorycode() == null ? 0 : this.getAccessorycode().hashCode());
		result = 37 * result + (getOrdersheetno() == null ? 0 : this.getOrdersheetno().hashCode());
		return result;
	}

}
