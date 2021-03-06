package com.chori.entity;
// Generated Dec 8, 2016 6:21:17 PM by Hibernate Tools 5.1.0.Beta1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Garmentstyleaccessorydetail generated by hbm2java
 */
@Entity
@Table(name = "garmentstyleaccessorydetail")
public class Garmentstyleaccessorydetail implements java.io.Serializable {

	private Integer garmentstyleaccessorydetailcode;
	private Accessory accessory;
	private Garmentstyle garmentstyle;
	private Size size;
	private User user;
	private Float usedvalue;
	private Boolean isconfigbytype;
	private Date createdate;
	private Set<Piassignexternalaccessory> piassignexternalaccessories = new HashSet<Piassignexternalaccessory>(0);

	public Garmentstyleaccessorydetail() {
	}

	public Garmentstyleaccessorydetail(Accessory accessory, Garmentstyle garmentstyle, Size size, User user,
			Float usedvalue, Boolean isconfigbytype, Date createdate,
			Set<Piassignexternalaccessory> piassignexternalaccessories) {
		this.accessory = accessory;
		this.garmentstyle = garmentstyle;
		this.size = size;
		this.user = user;
		this.usedvalue = usedvalue;
		this.isconfigbytype = isconfigbytype;
		this.createdate = createdate;
		this.piassignexternalaccessories = piassignexternalaccessories;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "GARMENTSTYLEACCESSORYDETAILCODE", unique = true, nullable = false)
	public Integer getGarmentstyleaccessorydetailcode() {
		return this.garmentstyleaccessorydetailcode;
	}

	public void setGarmentstyleaccessorydetailcode(Integer garmentstyleaccessorydetailcode) {
		this.garmentstyleaccessorydetailcode = garmentstyleaccessorydetailcode;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "USEDVALUE", precision = 12, scale = 0)
	public Float getUsedvalue() {
		return this.usedvalue;
	}

	public void setUsedvalue(Float usedvalue) {
		this.usedvalue = usedvalue;
	}

	@Column(name = "ISCONFIGBYTYPE")
	public Boolean getIsconfigbytype() {
		return this.isconfigbytype;
	}

	public void setIsconfigbytype(Boolean isconfigbytype) {
		this.isconfigbytype = isconfigbytype;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE", length = 19)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garmentstyleaccessorydetail")
	public Set<Piassignexternalaccessory> getPiassignexternalaccessories() {
		return this.piassignexternalaccessories;
	}

	public void setPiassignexternalaccessories(Set<Piassignexternalaccessory> piassignexternalaccessories) {
		this.piassignexternalaccessories = piassignexternalaccessories;
	}

}
