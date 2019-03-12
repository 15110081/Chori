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
 * Piassigninternalaccessoriesdetail generated by hbm2java
 */
@Entity
@Table(name = "piassigninternalaccessoriesdetail")
public class Piassigninternalaccessoriesdetail implements java.io.Serializable {

	private Integer piinternalaccdetailcode;
	private Color color;
	private Garmentstyle garmentstyle;
	private Piassigninternalaccessories piassigninternalaccessories;
	private Size size;
	private Double assignquantity;
	private Date createdate;

	public Piassigninternalaccessoriesdetail() {
	}

	public Piassigninternalaccessoriesdetail(Color color, Garmentstyle garmentstyle,
			Piassigninternalaccessories piassigninternalaccessories, Size size, Double assignquantity,
			Date createdate) {
		this.color = color;
		this.garmentstyle = garmentstyle;
		this.piassigninternalaccessories = piassigninternalaccessories;
		this.size = size;
		this.assignquantity = assignquantity;
		this.createdate = createdate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "PIINTERNALACCDETAILCODE", unique = true, nullable = false)
	public Integer getPiinternalaccdetailcode() {
		return this.piinternalaccdetailcode;
	}

	public void setPiinternalaccdetailcode(Integer piinternalaccdetailcode) {
		this.piinternalaccdetailcode = piinternalaccdetailcode;
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
	@JoinColumn(name = "GARMENTSTYLECODE")
	public Garmentstyle getGarmentstyle() {
		return this.garmentstyle;
	}

	public void setGarmentstyle(Garmentstyle garmentstyle) {
		this.garmentstyle = garmentstyle;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PIINTERNALACCESSORIES")
	public Piassigninternalaccessories getPiassigninternalaccessories() {
		return this.piassigninternalaccessories;
	}

	public void setPiassigninternalaccessories(Piassigninternalaccessories piassigninternalaccessories) {
		this.piassigninternalaccessories = piassigninternalaccessories;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SIZECODE")
	public Size getSize() {
		return this.size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	@Column(name = "ASSIGNQUANTITY")
	public Double getAssignquantity() {
		return this.assignquantity;
	}

	public void setAssignquantity(Double assignquantity) {
		this.assignquantity = assignquantity;
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