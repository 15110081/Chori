package com.chori.entity;
// Generated Dec 8, 2016 6:21:17 PM by Hibernate Tools 5.1.0.Beta1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Accessory generated by hbm2java
 */
@Entity
@Table(name = "accessory")
public class Accessory implements java.io.Serializable {

	private String accessorycode;
	private Accessorygroup accessorygroup;
	private Color color;
	private Unit unitByUnitcode;
	private Unit unitByContainerunitcode;
	private User user;
	private String accessorychoricode;
	private String name;
	private String kind;
	private String dimension;
	private String mode;
	private String imgurl1;
	private String imgurl2;
	private String imgurl3;
	private String imgurl4;
	private Date createdate;
	private Integer percontainer;
	private String status;
	private Set<Orderinternalaccessorydetail> orderinternalaccessorydetails = new HashSet<Orderinternalaccessorydetail>(
			0);
	private Set<Accessoryformdetail> accessoryformdetails = new HashSet<Accessoryformdetail>(0);
	private Set<Externalaccessorystock> externalaccessorystocks = new HashSet<Externalaccessorystock>(0);
	private Set<Piassignexternalaccessory> piassignexternalaccessories = new HashSet<Piassignexternalaccessory>(0);
	private Set<Accessoryprice> accessoryprices = new HashSet<Accessoryprice>(0);
	private Set<Orderexternalaccessory> orderexternalaccessories = new HashSet<Orderexternalaccessory>(0);
	private Set<Accessoryconsumption> accessoryconsumptions = new HashSet<Accessoryconsumption>(0);
	private Set<Piassigninternalaccessories> piassigninternalaccessorieses = new HashSet<Piassigninternalaccessories>(
			0);
	private Set<Garmentstyleaccessorydetail> garmentstyleaccessorydetails = new HashSet<Garmentstyleaccessorydetail>(0);

	public Accessory() {
	}

	public Accessory(String accessorycode) {
		this.accessorycode = accessorycode;
	}

	public Accessory(String accessorycode, Accessorygroup accessorygroup, Color color, Unit unitByUnitcode,
			Unit unitByContainerunitcode, User user, String accessorychoricode, String name, String kind,
			String dimension, String mode, String imgurl1, String imgurl2, String imgurl3, String imgurl4,
			Date createdate, Integer percontainer, String status,
			Set<Orderinternalaccessorydetail> orderinternalaccessorydetails,
			Set<Accessoryformdetail> accessoryformdetails, Set<Externalaccessorystock> externalaccessorystocks,
			Set<Piassignexternalaccessory> piassignexternalaccessories, Set<Accessoryprice> accessoryprices,
			Set<Orderexternalaccessory> orderexternalaccessories, Set<Accessoryconsumption> accessoryconsumptions,
			Set<Piassigninternalaccessories> piassigninternalaccessorieses,
			Set<Garmentstyleaccessorydetail> garmentstyleaccessorydetails) {
		this.accessorycode = accessorycode;
		this.accessorygroup = accessorygroup;
		this.color = color;
		this.unitByUnitcode = unitByUnitcode;
		this.unitByContainerunitcode = unitByContainerunitcode;
		this.user = user;
		this.accessorychoricode = accessorychoricode;
		this.name = name;
		this.kind = kind;
		this.dimension = dimension;
		this.mode = mode;
		this.imgurl1 = imgurl1;
		this.imgurl2 = imgurl2;
		this.imgurl3 = imgurl3;
		this.imgurl4 = imgurl4;
		this.createdate = createdate;
		this.percontainer = percontainer;
		this.status = status;
		this.orderinternalaccessorydetails = orderinternalaccessorydetails;
		this.accessoryformdetails = accessoryformdetails;
		this.externalaccessorystocks = externalaccessorystocks;
		this.piassignexternalaccessories = piassignexternalaccessories;
		this.accessoryprices = accessoryprices;
		this.orderexternalaccessories = orderexternalaccessories;
		this.accessoryconsumptions = accessoryconsumptions;
		this.piassigninternalaccessorieses = piassigninternalaccessorieses;
		this.garmentstyleaccessorydetails = garmentstyleaccessorydetails;
	}

	@Id

	@Column(name = "ACCESSORYCODE", unique = true, nullable = false, length = 100)
	public String getAccessorycode() {
		return this.accessorycode;
	}

	public void setAccessorycode(String accessorycode) {
		this.accessorycode = accessorycode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCESSORYGROUPCODE")
	public Accessorygroup getAccessorygroup() {
		return this.accessorygroup;
	}

	public void setAccessorygroup(Accessorygroup accessorygroup) {
		this.accessorygroup = accessorygroup;
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
	@JoinColumn(name = "UNITCODE")
	public Unit getUnitByUnitcode() {
		return this.unitByUnitcode;
	}

	public void setUnitByUnitcode(Unit unitByUnitcode) {
		this.unitByUnitcode = unitByUnitcode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTAINERUNITCODE")
	public Unit getUnitByContainerunitcode() {
		return this.unitByContainerunitcode;
	}

	public void setUnitByContainerunitcode(Unit unitByContainerunitcode) {
		this.unitByContainerunitcode = unitByContainerunitcode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "ACCESSORYCHORICODE", length = 50)
	public String getAccessorychoricode() {
		return this.accessorychoricode;
	}

	public void setAccessorychoricode(String accessorychoricode) {
		this.accessorychoricode = accessorychoricode;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "KIND", length = 50)
	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	@Column(name = "DIMENSION", length = 100)
	public String getDimension() {
		return this.dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	@Column(name = "MODE", length = 50)
	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	@Column(name = "IMGURL1", length = 500)
	public String getImgurl1() {
		return this.imgurl1;
	}

	public void setImgurl1(String imgurl1) {
		this.imgurl1 = imgurl1;
	}

	@Column(name = "IMGURL2", length = 500)
	public String getImgurl2() {
		return this.imgurl2;
	}

	public void setImgurl2(String imgurl2) {
		this.imgurl2 = imgurl2;
	}

	@Column(name = "IMGURL3", length = 500)
	public String getImgurl3() {
		return this.imgurl3;
	}

	public void setImgurl3(String imgurl3) {
		this.imgurl3 = imgurl3;
	}

	@Column(name = "IMGURL4", length = 500)
	public String getImgurl4() {
		return this.imgurl4;
	}

	public void setImgurl4(String imgurl4) {
		this.imgurl4 = imgurl4;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE", length = 19)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Column(name = "PERCONTAINER")
	public Integer getPercontainer() {
		return this.percontainer;
	}

	public void setPercontainer(Integer percontainer) {
		this.percontainer = percontainer;
	}

	@Column(name = "STATUS", length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accessory")
	public Set<Orderinternalaccessorydetail> getOrderinternalaccessorydetails() {
		return this.orderinternalaccessorydetails;
	}

	public void setOrderinternalaccessorydetails(Set<Orderinternalaccessorydetail> orderinternalaccessorydetails) {
		this.orderinternalaccessorydetails = orderinternalaccessorydetails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accessory")
	public Set<Accessoryformdetail> getAccessoryformdetails() {
		return this.accessoryformdetails;
	}

	public void setAccessoryformdetails(Set<Accessoryformdetail> accessoryformdetails) {
		this.accessoryformdetails = accessoryformdetails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accessory")
	public Set<Externalaccessorystock> getExternalaccessorystocks() {
		return this.externalaccessorystocks;
	}

	public void setExternalaccessorystocks(Set<Externalaccessorystock> externalaccessorystocks) {
		this.externalaccessorystocks = externalaccessorystocks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accessory")
	public Set<Piassignexternalaccessory> getPiassignexternalaccessories() {
		return this.piassignexternalaccessories;
	}

	public void setPiassignexternalaccessories(Set<Piassignexternalaccessory> piassignexternalaccessories) {
		this.piassignexternalaccessories = piassignexternalaccessories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accessory")
	public Set<Accessoryprice> getAccessoryprices() {
		return this.accessoryprices;
	}

	public void setAccessoryprices(Set<Accessoryprice> accessoryprices) {
		this.accessoryprices = accessoryprices;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accessory")
	public Set<Orderexternalaccessory> getOrderexternalaccessories() {
		return this.orderexternalaccessories;
	}

	public void setOrderexternalaccessories(Set<Orderexternalaccessory> orderexternalaccessories) {
		this.orderexternalaccessories = orderexternalaccessories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accessory")
	public Set<Accessoryconsumption> getAccessoryconsumptions() {
		return this.accessoryconsumptions;
	}

	public void setAccessoryconsumptions(Set<Accessoryconsumption> accessoryconsumptions) {
		this.accessoryconsumptions = accessoryconsumptions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accessory")
	public Set<Piassigninternalaccessories> getPiassigninternalaccessorieses() {
		return this.piassigninternalaccessorieses;
	}

	public void setPiassigninternalaccessorieses(Set<Piassigninternalaccessories> piassigninternalaccessorieses) {
		this.piassigninternalaccessorieses = piassigninternalaccessorieses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accessory")
	public Set<Garmentstyleaccessorydetail> getGarmentstyleaccessorydetails() {
		return this.garmentstyleaccessorydetails;
	}

	public void setGarmentstyleaccessorydetails(Set<Garmentstyleaccessorydetail> garmentstyleaccessorydetails) {
		this.garmentstyleaccessorydetails = garmentstyleaccessorydetails;
	}

}
