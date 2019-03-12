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
 * Garmentstyle generated by hbm2java
 */
@Entity
@Table(name = "garmentstyle")
public class Garmentstyle implements java.io.Serializable {

	private String garmentstylecode;
	private Customer customer;
	private Factory factory;
	private Garmentkind garmentkind;
	private User user;
	private String description;
	private Date createdate;
	private String imgurl1;
	private String imgurl2;
	private String imgurl3;
	private String imgurl4;
	private String imgurl5;
	private String sewingguide;
	private String packingguide;
	private String referpriceunit;
	private Float referprice;
	private String currencycode;
	private Set<Garmentstylereferprice> garmentstylereferprices = new HashSet<Garmentstylereferprice>(0);
	private Set<Garmentstyleaccessorydetail> garmentstyleaccessorydetails = new HashSet<Garmentstyleaccessorydetail>(0);
	private Set<Piassignfabricdetail> piassignfabricdetails = new HashSet<Piassignfabricdetail>(0);
	private Set<Piassigninternalaccessoriesdetail> piassigninternalaccessoriesdetails = new HashSet<Piassigninternalaccessoriesdetail>(
			0);
	private Set<Fpidetail> fpidetails = new HashSet<Fpidetail>(0);
	private Set<Garmentconsumption> garmentconsumptions = new HashSet<Garmentconsumption>(0);
	private Set<Rfpidetail> rfpidetails = new HashSet<Rfpidetail>(0);
	private Set<Piassignexternalaccessory> piassignexternalaccessories = new HashSet<Piassignexternalaccessory>(0);
	private Set<Pigriddetail> pigriddetails = new HashSet<Pigriddetail>(0);

	public Garmentstyle() {
	}

	public Garmentstyle(String garmentstylecode) {
		this.garmentstylecode = garmentstylecode;
	}

	public Garmentstyle(String garmentstylecode, Customer customer, Factory factory, Garmentkind garmentkind, User user,
			String description, Date createdate, String imgurl1, String imgurl2, String imgurl3, String imgurl4,
			String imgurl5, String sewingguide, String packingguide, String referpriceunit, Float referprice,
			String currencycode, Set<Garmentstylereferprice> garmentstylereferprices,
			Set<Garmentstyleaccessorydetail> garmentstyleaccessorydetails,
			Set<Piassignfabricdetail> piassignfabricdetails,
			Set<Piassigninternalaccessoriesdetail> piassigninternalaccessoriesdetails, Set<Fpidetail> fpidetails,
			Set<Garmentconsumption> garmentconsumptions, Set<Rfpidetail> rfpidetails,
			Set<Piassignexternalaccessory> piassignexternalaccessories, Set<Pigriddetail> pigriddetails) {
		this.garmentstylecode = garmentstylecode;
		this.customer = customer;
		this.factory = factory;
		this.garmentkind = garmentkind;
		this.user = user;
		this.description = description;
		this.createdate = createdate;
		this.imgurl1 = imgurl1;
		this.imgurl2 = imgurl2;
		this.imgurl3 = imgurl3;
		this.imgurl4 = imgurl4;
		this.imgurl5 = imgurl5;
		this.sewingguide = sewingguide;
		this.packingguide = packingguide;
		this.referpriceunit = referpriceunit;
		this.referprice = referprice;
		this.currencycode = currencycode;
		this.garmentstylereferprices = garmentstylereferprices;
		this.garmentstyleaccessorydetails = garmentstyleaccessorydetails;
		this.piassignfabricdetails = piassignfabricdetails;
		this.piassigninternalaccessoriesdetails = piassigninternalaccessoriesdetails;
		this.fpidetails = fpidetails;
		this.garmentconsumptions = garmentconsumptions;
		this.rfpidetails = rfpidetails;
		this.piassignexternalaccessories = piassignexternalaccessories;
		this.pigriddetails = pigriddetails;
	}

	@Id

	@Column(name = "GARMENTSTYLECODE", unique = true, nullable = false, length = 103)
	public String getGarmentstylecode() {
		return this.garmentstylecode;
	}

	public void setGarmentstylecode(String garmentstylecode) {
		this.garmentstylecode = garmentstylecode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMERCODE")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FACTORYCODE")
	public Factory getFactory() {
		return this.factory;
	}

	public void setFactory(Factory factory) {
		this.factory = factory;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GARMENTKINDCODE")
	public Garmentkind getGarmentkind() {
		return this.garmentkind;
	}

	public void setGarmentkind(Garmentkind garmentkind) {
		this.garmentkind = garmentkind;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "DESCRIPTION", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE", length = 19)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
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

	@Column(name = "IMGURL5", length = 500)
	public String getImgurl5() {
		return this.imgurl5;
	}

	public void setImgurl5(String imgurl5) {
		this.imgurl5 = imgurl5;
	}

	@Column(name = "SEWINGGUIDE", length = 500)
	public String getSewingguide() {
		return this.sewingguide;
	}

	public void setSewingguide(String sewingguide) {
		this.sewingguide = sewingguide;
	}

	@Column(name = "PACKINGGUIDE", length = 500)
	public String getPackingguide() {
		return this.packingguide;
	}

	public void setPackingguide(String packingguide) {
		this.packingguide = packingguide;
	}

	@Column(name = "REFERPRICEUNIT", length = 20)
	public String getReferpriceunit() {
		return this.referpriceunit;
	}

	public void setReferpriceunit(String referpriceunit) {
		this.referpriceunit = referpriceunit;
	}

	@Column(name = "REFERPRICE", precision = 20, scale = 4)
	public Float getReferprice() {
		return this.referprice;
	}

	public void setReferprice(Float referprice) {
		this.referprice = referprice;
	}

	@Column(name = "CURRENCYCODE", length = 20)
	public String getCurrencycode() {
		return this.currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garmentstyle")
	public Set<Garmentstylereferprice> getGarmentstylereferprices() {
		return this.garmentstylereferprices;
	}

	public void setGarmentstylereferprices(Set<Garmentstylereferprice> garmentstylereferprices) {
		this.garmentstylereferprices = garmentstylereferprices;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garmentstyle")
	public Set<Garmentstyleaccessorydetail> getGarmentstyleaccessorydetails() {
		return this.garmentstyleaccessorydetails;
	}

	public void setGarmentstyleaccessorydetails(Set<Garmentstyleaccessorydetail> garmentstyleaccessorydetails) {
		this.garmentstyleaccessorydetails = garmentstyleaccessorydetails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garmentstyle")
	public Set<Piassignfabricdetail> getPiassignfabricdetails() {
		return this.piassignfabricdetails;
	}

	public void setPiassignfabricdetails(Set<Piassignfabricdetail> piassignfabricdetails) {
		this.piassignfabricdetails = piassignfabricdetails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garmentstyle")
	public Set<Piassigninternalaccessoriesdetail> getPiassigninternalaccessoriesdetails() {
		return this.piassigninternalaccessoriesdetails;
	}

	public void setPiassigninternalaccessoriesdetails(
			Set<Piassigninternalaccessoriesdetail> piassigninternalaccessoriesdetails) {
		this.piassigninternalaccessoriesdetails = piassigninternalaccessoriesdetails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garmentstyle")
	public Set<Fpidetail> getFpidetails() {
		return this.fpidetails;
	}

	public void setFpidetails(Set<Fpidetail> fpidetails) {
		this.fpidetails = fpidetails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garmentstyle")
	public Set<Garmentconsumption> getGarmentconsumptions() {
		return this.garmentconsumptions;
	}

	public void setGarmentconsumptions(Set<Garmentconsumption> garmentconsumptions) {
		this.garmentconsumptions = garmentconsumptions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garmentstyle")
	public Set<Rfpidetail> getRfpidetails() {
		return this.rfpidetails;
	}

	public void setRfpidetails(Set<Rfpidetail> rfpidetails) {
		this.rfpidetails = rfpidetails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garmentstyle")
	public Set<Piassignexternalaccessory> getPiassignexternalaccessories() {
		return this.piassignexternalaccessories;
	}

	public void setPiassignexternalaccessories(Set<Piassignexternalaccessory> piassignexternalaccessories) {
		this.piassignexternalaccessories = piassignexternalaccessories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garmentstyle")
	public Set<Pigriddetail> getPigriddetails() {
		return this.pigriddetails;
	}

	public void setPigriddetails(Set<Pigriddetail> pigriddetails) {
		this.pigriddetails = pigriddetails;
	}

}
