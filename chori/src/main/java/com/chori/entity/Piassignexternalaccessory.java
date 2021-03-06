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
 * Piassignexternalaccessory generated by hbm2java
 */
@Entity
@Table(name = "piassignexternalaccessory")
public class Piassignexternalaccessory implements java.io.Serializable {

	private Integer piassignexternalaccessorycode;
	private Accessory accessory;
	private Color color;
	private Externalaccessorystock externalaccessorystock;
	private Garmentstyle garmentstyle;
	private Garmentstyleaccessorydetail garmentstyleaccessorydetail;
	private Orderexternalaccessory orderexternalaccessory;
	private Pi pi;
	private Pigriddetail pigriddetail;
	private User user;
	private Float estimateqty;
	private Float orderqty;
	private Float stockassignqty;
	private Float actualassignqty;
	private Float specificconsumption;
	private Float specificequivalent;
	private Date createdate;
	private Float unitprice;

	public Piassignexternalaccessory() {
	}

	public Piassignexternalaccessory(Accessory accessory, Color color, Externalaccessorystock externalaccessorystock,
			Garmentstyle garmentstyle, Garmentstyleaccessorydetail garmentstyleaccessorydetail,
			Orderexternalaccessory orderexternalaccessory, Pi pi, Pigriddetail pigriddetail, User user,
			Float estimateqty, Float orderqty, Float stockassignqty, Float actualassignqty, Float specificconsumption,
			Float specificequivalent, Date createdate, Float unitprice) {
		this.accessory = accessory;
		this.color = color;
		this.externalaccessorystock = externalaccessorystock;
		this.garmentstyle = garmentstyle;
		this.garmentstyleaccessorydetail = garmentstyleaccessorydetail;
		this.orderexternalaccessory = orderexternalaccessory;
		this.pi = pi;
		this.pigriddetail = pigriddetail;
		this.user = user;
		this.estimateqty = estimateqty;
		this.orderqty = orderqty;
		this.stockassignqty = stockassignqty;
		this.actualassignqty = actualassignqty;
		this.specificconsumption = specificconsumption;
		this.specificequivalent = specificequivalent;
		this.createdate = createdate;
		this.unitprice = unitprice;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "PIASSIGNEXTERNALACCESSORYCODE", unique = true, nullable = false)
	public Integer getPiassignexternalaccessorycode() {
		return this.piassignexternalaccessorycode;
	}

	public void setPiassignexternalaccessorycode(Integer piassignexternalaccessorycode) {
		this.piassignexternalaccessorycode = piassignexternalaccessorycode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCESSORYCODE")
	public Accessory getAccessory() {
		return this.accessory;
	}

	public void setAccessory(Accessory accessory) {
		this.accessory = accessory;
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
	@JoinColumn(name = "EXTERNALACCESSORYSTOCKCODE")
	public Externalaccessorystock getExternalaccessorystock() {
		return this.externalaccessorystock;
	}

	public void setExternalaccessorystock(Externalaccessorystock externalaccessorystock) {
		this.externalaccessorystock = externalaccessorystock;
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
	@JoinColumn(name = "GARMENTSTYLEACCESSORYDETAILCODE")
	public Garmentstyleaccessorydetail getGarmentstyleaccessorydetail() {
		return this.garmentstyleaccessorydetail;
	}

	public void setGarmentstyleaccessorydetail(Garmentstyleaccessorydetail garmentstyleaccessorydetail) {
		this.garmentstyleaccessorydetail = garmentstyleaccessorydetail;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDERSHEETNO")
	public Orderexternalaccessory getOrderexternalaccessory() {
		return this.orderexternalaccessory;
	}

	public void setOrderexternalaccessory(Orderexternalaccessory orderexternalaccessory) {
		this.orderexternalaccessory = orderexternalaccessory;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOTNUMBER")
	public Pi getPi() {
		return this.pi;
	}

	public void setPi(Pi pi) {
		this.pi = pi;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PIGRIDDETAIL")
	public Pigriddetail getPigriddetail() {
		return this.pigriddetail;
	}

	public void setPigriddetail(Pigriddetail pigriddetail) {
		this.pigriddetail = pigriddetail;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "ESTIMATEQTY", precision = 12, scale = 0)
	public Float getEstimateqty() {
		return this.estimateqty;
	}

	public void setEstimateqty(Float estimateqty) {
		this.estimateqty = estimateqty;
	}

	@Column(name = "ORDERQTY", precision = 12, scale = 0)
	public Float getOrderqty() {
		return this.orderqty;
	}

	public void setOrderqty(Float orderqty) {
		this.orderqty = orderqty;
	}

	@Column(name = "STOCKASSIGNQTY", precision = 12, scale = 0)
	public Float getStockassignqty() {
		return this.stockassignqty;
	}

	public void setStockassignqty(Float stockassignqty) {
		this.stockassignqty = stockassignqty;
	}

	@Column(name = "ACTUALASSIGNQTY", precision = 12, scale = 0)
	public Float getActualassignqty() {
		return this.actualassignqty;
	}

	public void setActualassignqty(Float actualassignqty) {
		this.actualassignqty = actualassignqty;
	}

	@Column(name = "SPECIFICCONSUMPTION", precision = 12, scale = 0)
	public Float getSpecificconsumption() {
		return this.specificconsumption;
	}

	public void setSpecificconsumption(Float specificconsumption) {
		this.specificconsumption = specificconsumption;
	}

	@Column(name = "SPECIFICEQUIVALENT", precision = 12, scale = 0)
	public Float getSpecificequivalent() {
		return this.specificequivalent;
	}

	public void setSpecificequivalent(Float specificequivalent) {
		this.specificequivalent = specificequivalent;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE", length = 19)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Column(name = "UNITPRICE", precision = 20, scale = 4)
	public Float getUnitprice() {
		return this.unitprice;
	}

	public void setUnitprice(Float unitprice) {
		this.unitprice = unitprice;
	}

}
