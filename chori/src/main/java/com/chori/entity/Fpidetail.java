package com.chori.entity;
// Generated Dec 8, 2016 6:21:17 PM by Hibernate Tools 5.1.0.Beta1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Fpidetail generated by hbm2java
 */
@Entity
@Table(name = "fpidetail")
public class Fpidetail implements java.io.Serializable {

	private Integer fpidetailcode;
	private Color color;
	private Fpi fpi;
	private Garmentstyle garmentstyle;
	private Size size;
	private Integer fpivalue;

	public Fpidetail() {
	}

	public Fpidetail(Color color, Fpi fpi, Garmentstyle garmentstyle, Size size, Integer fpivalue) {
		this.color = color;
		this.fpi = fpi;
		this.garmentstyle = garmentstyle;
		this.size = size;
		this.fpivalue = fpivalue;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "FPIDETAILCODE", unique = true, nullable = false)
	public Integer getFpidetailcode() {
		return this.fpidetailcode;
	}

	public void setFpidetailcode(Integer fpidetailcode) {
		this.fpidetailcode = fpidetailcode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COLORCODE")
	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FPICODE")
	public Fpi getFpi() {
		return this.fpi;
	}

	public void setFpi(Fpi fpi) {
		this.fpi = fpi;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GARMENTSTYLECODE")
	public Garmentstyle getGarmentstyle() {
		return this.garmentstyle;
	}

	public void setGarmentstyle(Garmentstyle garmentstyle) {
		this.garmentstyle = garmentstyle;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SIZECODE")
	public Size getSize() {
		return this.size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	@Column(name = "FPIVALUE")
	public Integer getFpivalue() {
		return this.fpivalue;
	}

	public void setFpivalue(Integer fpivalue) {
		this.fpivalue = fpivalue;
	}

}
