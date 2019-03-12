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
 * Pi generated by hbm2java
 */
@Entity
@Table(name = "pi")
public class Pi implements java.io.Serializable {

	private String lotnumber;
	private Brand brand;
	private Customer customerByConsigneee;
	private Customer customerByCustomer1code;
	private Destination destination;
	private Factory factory;
	private Packingguide packingguide;
	private Pigrid pigrid;
	private User user;
	private Boolean noneorderaccessories;
	private Date pireceiveddate;
	private Date piestshipdate;
	private String piattachedfileurl;
	private String status;
	private String sewingguideurl;
	private String packingguideurl;
	private String remark;
	private Date createdate;
	private String shippingstatus;
	private Date mfgstarteddate;
	private Date mfgfinisheddate;
	private Set<Piassignfabric> piassignfabrics = new HashSet<Piassignfabric>(0);
	private Set<Rfpi> rfpis = new HashSet<Rfpi>(0);
	private Set<Piassignexternalaccessory> piassignexternalaccessories = new HashSet<Piassignexternalaccessory>(0);
	private Set<Piassigninternalaccessories> piassigninternalaccessorieses = new HashSet<Piassigninternalaccessories>(
			0);
	private Set<Logofchange> logofchanges = new HashSet<Logofchange>(0);
	private Set<Fpi> fpis = new HashSet<Fpi>(0);

	public Pi() {
	}

	public Pi(String lotnumber) {
		this.lotnumber = lotnumber;
	}

	public Pi(String lotnumber, Brand brand, Customer customerByConsigneee, Customer customerByCustomer1code,
			Destination destination, Factory factory, Packingguide packingguide, Pigrid pigrid, User user,
			Boolean noneorderaccessories, Date pireceiveddate, Date piestshipdate, String piattachedfileurl,
			String status, String sewingguideurl, String packingguideurl, String remark, Date createdate,
			String shippingstatus, Date mfgstarteddate, Date mfgfinisheddate, Set<Piassignfabric> piassignfabrics,
			Set<Rfpi> rfpis, Set<Piassignexternalaccessory> piassignexternalaccessories,
			Set<Piassigninternalaccessories> piassigninternalaccessorieses, Set<Logofchange> logofchanges,
			Set<Fpi> fpis) {
		this.lotnumber = lotnumber;
		this.brand = brand;
		this.customerByConsigneee = customerByConsigneee;
		this.customerByCustomer1code = customerByCustomer1code;
		this.destination = destination;
		this.factory = factory;
		this.packingguide = packingguide;
		this.pigrid = pigrid;
		this.user = user;
		this.noneorderaccessories = noneorderaccessories;
		this.pireceiveddate = pireceiveddate;
		this.piestshipdate = piestshipdate;
		this.piattachedfileurl = piattachedfileurl;
		this.status = status;
		this.sewingguideurl = sewingguideurl;
		this.packingguideurl = packingguideurl;
		this.remark = remark;
		this.createdate = createdate;
		this.shippingstatus = shippingstatus;
		this.mfgstarteddate = mfgstarteddate;
		this.mfgfinisheddate = mfgfinisheddate;
		this.piassignfabrics = piassignfabrics;
		this.rfpis = rfpis;
		this.piassignexternalaccessories = piassignexternalaccessories;
		this.piassigninternalaccessorieses = piassigninternalaccessorieses;
		this.logofchanges = logofchanges;
		this.fpis = fpis;
	}

	@Id

	@Column(name = "LOTNUMBER", unique = true, nullable = false, length = 50)
	public String getLotnumber() {
		return this.lotnumber;
	}

	public void setLotnumber(String lotnumber) {
		this.lotnumber = lotnumber;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANDCODE")
	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONSIGNEEE")
	public Customer getCustomerByConsigneee() {
		return this.customerByConsigneee;
	}

	public void setCustomerByConsigneee(Customer customerByConsigneee) {
		this.customerByConsigneee = customerByConsigneee;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER1CODE")
	public Customer getCustomerByCustomer1code() {
		return this.customerByCustomer1code;
	}

	public void setCustomerByCustomer1code(Customer customerByCustomer1code) {
		this.customerByCustomer1code = customerByCustomer1code;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DESTINATIONCODE")
	public Destination getDestination() {
		return this.destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
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
	@JoinColumn(name = "PACKINGGUIDE")
	public Packingguide getPackingguide() {
		return this.packingguide;
	}

	public void setPackingguide(Packingguide packingguide) {
		this.packingguide = packingguide;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PIGRIDCODE")
	public Pigrid getPigrid() {
		return this.pigrid;
	}

	public void setPigrid(Pigrid pigrid) {
		this.pigrid = pigrid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "NONEORDERACCESSORIES")
	public Boolean getNoneorderaccessories() {
		return this.noneorderaccessories;
	}

	public void setNoneorderaccessories(Boolean noneorderaccessories) {
		this.noneorderaccessories = noneorderaccessories;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PIRECEIVEDDATE", length = 19)
	public Date getPireceiveddate() {
		return this.pireceiveddate;
	}

	public void setPireceiveddate(Date pireceiveddate) {
		this.pireceiveddate = pireceiveddate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PIESTSHIPDATE", length = 19)
	public Date getPiestshipdate() {
		return this.piestshipdate;
	}

	public void setPiestshipdate(Date piestshipdate) {
		this.piestshipdate = piestshipdate;
	}

	@Column(name = "PIATTACHEDFILEURL", length = 500)
	public String getPiattachedfileurl() {
		return this.piattachedfileurl;
	}

	public void setPiattachedfileurl(String piattachedfileurl) {
		this.piattachedfileurl = piattachedfileurl;
	}

	@Column(name = "STATUS", length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "SEWINGGUIDEURL", length = 500)
	public String getSewingguideurl() {
		return this.sewingguideurl;
	}

	public void setSewingguideurl(String sewingguideurl) {
		this.sewingguideurl = sewingguideurl;
	}

	@Column(name = "PACKINGGUIDEURL", length = 500)
	public String getPackingguideurl() {
		return this.packingguideurl;
	}

	public void setPackingguideurl(String packingguideurl) {
		this.packingguideurl = packingguideurl;
	}

	@Column(name = "REMARK", length = 500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE", length = 19)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Column(name = "SHIPPINGSTATUS", length = 20)
	public String getShippingstatus() {
		return this.shippingstatus;
	}

	public void setShippingstatus(String shippingstatus) {
		this.shippingstatus = shippingstatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MFGSTARTEDDATE", length = 19)
	public Date getMfgstarteddate() {
		return this.mfgstarteddate;
	}

	public void setMfgstarteddate(Date mfgstarteddate) {
		this.mfgstarteddate = mfgstarteddate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MFGFINISHEDDATE", length = 19)
	public Date getMfgfinisheddate() {
		return this.mfgfinisheddate;
	}

	public void setMfgfinisheddate(Date mfgfinisheddate) {
		this.mfgfinisheddate = mfgfinisheddate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pi")
	public Set<Piassignfabric> getPiassignfabrics() {
		return this.piassignfabrics;
	}

	public void setPiassignfabrics(Set<Piassignfabric> piassignfabrics) {
		this.piassignfabrics = piassignfabrics;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pi")
	public Set<Rfpi> getRfpis() {
		return this.rfpis;
	}

	public void setRfpis(Set<Rfpi> rfpis) {
		this.rfpis = rfpis;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pi")
	public Set<Piassignexternalaccessory> getPiassignexternalaccessories() {
		return this.piassignexternalaccessories;
	}

	public void setPiassignexternalaccessories(Set<Piassignexternalaccessory> piassignexternalaccessories) {
		this.piassignexternalaccessories = piassignexternalaccessories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pi")
	public Set<Piassigninternalaccessories> getPiassigninternalaccessorieses() {
		return this.piassigninternalaccessorieses;
	}

	public void setPiassigninternalaccessorieses(Set<Piassigninternalaccessories> piassigninternalaccessorieses) {
		this.piassigninternalaccessorieses = piassigninternalaccessorieses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pi")
	public Set<Logofchange> getLogofchanges() {
		return this.logofchanges;
	}

	public void setLogofchanges(Set<Logofchange> logofchanges) {
		this.logofchanges = logofchanges;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pi")
	public Set<Fpi> getFpis() {
		return this.fpis;
	}

	public void setFpis(Set<Fpi> fpis) {
		this.fpis = fpis;
	}

}
