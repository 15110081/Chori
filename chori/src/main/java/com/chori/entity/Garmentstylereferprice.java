package com.chori.entity;
// Generated Dec 8, 2016 6:21:17 PM by Hibernate Tools 5.1.0.Beta1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Garmentstylereferprice generated by hbm2java
 */
@Entity
@Table(name = "garmentstylereferprice")
public class Garmentstylereferprice implements java.io.Serializable {

	private GarmentstylereferpriceId id;
	private Garmentstyle garmentstyle;
	private Type type;
	private Unit unit;
	private Float referprice;

	public Garmentstylereferprice() {
	}

	public Garmentstylereferprice(GarmentstylereferpriceId id, Garmentstyle garmentstyle, Type type) {
		this.id = id;
		this.garmentstyle = garmentstyle;
		this.type = type;
	}

	public Garmentstylereferprice(GarmentstylereferpriceId id, Garmentstyle garmentstyle, Type type, Unit unit,
			Float referprice) {
		this.id = id;
		this.garmentstyle = garmentstyle;
		this.type = type;
		this.unit = unit;
		this.referprice = referprice;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "garmentstylecode", column = @Column(name = "GARMENTSTYLECODE", nullable = false, length = 100)),
			@AttributeOverride(name = "typecode", column = @Column(name = "TYPECODE", nullable = false, length = 20)) })
	public GarmentstylereferpriceId getId() {
		return this.id;
	}

	public void setId(GarmentstylereferpriceId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GARMENTSTYLECODE", nullable = false, insertable = false, updatable = false)
	public Garmentstyle getGarmentstyle() {
		return this.garmentstyle;
	}

	public void setGarmentstyle(Garmentstyle garmentstyle) {
		this.garmentstyle = garmentstyle;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPECODE", nullable = false, insertable = false, updatable = false)
	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UNITCODE")
	public Unit getUnit() {
		return this.unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Column(name = "REFERPRICE", precision = 20, scale = 4)
	public Float getReferprice() {
		return this.referprice;
	}

	public void setReferprice(Float referprice) {
		this.referprice = referprice;
	}

}
