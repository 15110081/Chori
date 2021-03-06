package com.chori.entity;
// Generated Dec 8, 2016 6:21:17 PM by Hibernate Tools 5.1.0.Beta1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Garmentconsumptiondetail generated by hbm2java
 */
@Entity
@Table(name = "garmentconsumptiondetail")
public class Garmentconsumptiondetail implements java.io.Serializable {

	private Integer garmentconsumptiondetailcode;
	private Garmentconsumption garmentconsumption;
	private User user;
	private Width width;
	private Float convalue;
	private Date createdate;

	public Garmentconsumptiondetail() {
	}

	public Garmentconsumptiondetail(Garmentconsumption garmentconsumption, User user, Width width, Float convalue,
			Date createdate) {
		this.garmentconsumption = garmentconsumption;
		this.user = user;
		this.width = width;
		this.convalue = convalue;
		this.createdate = createdate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "GARMENTCONSUMPTIONDETAILCODE", unique = true, nullable = false)
	public Integer getGarmentconsumptiondetailcode() {
		return this.garmentconsumptiondetailcode;
	}

	public void setGarmentconsumptiondetailcode(Integer garmentconsumptiondetailcode) {
		this.garmentconsumptiondetailcode = garmentconsumptiondetailcode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GARMENTCONSUMPTIONCODE")
	public Garmentconsumption getGarmentconsumption() {
		return this.garmentconsumption;
	}

	public void setGarmentconsumption(Garmentconsumption garmentconsumption) {
		this.garmentconsumption = garmentconsumption;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WIDTHCODE")
	public Width getWidth() {
		return this.width;
	}

	public void setWidth(Width width) {
		this.width = width;
	}

	@Column(name = "CONVALUE", precision = 12, scale = 0)
	public Float getConvalue() {
		return this.convalue;
	}

	public void setConvalue(Float convalue) {
		this.convalue = convalue;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE", length = 19)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

}
