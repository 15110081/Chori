package com.chori.entity;
// Generated Dec 8, 2016 6:21:17 PM by Hibernate Tools 5.1.0.Beta1

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Accessoryformdetail generated by hbm2java
 */
@Entity
@Table(name = "accessoryformdetail")
public class Accessoryformdetail implements java.io.Serializable {

	private AccessoryformdetailId id;
	private Accessory accessory;
	private Accessoryform accessoryform;
	private User user;
	private Date createdate;

	public Accessoryformdetail() {
	}

	public Accessoryformdetail(AccessoryformdetailId id, Accessory accessory, Accessoryform accessoryform) {
		this.id = id;
		this.accessory = accessory;
		this.accessoryform = accessoryform;
	}

	public Accessoryformdetail(AccessoryformdetailId id, Accessory accessory, Accessoryform accessoryform, User user,
			Date createdate) {
		this.id = id;
		this.accessory = accessory;
		this.accessoryform = accessoryform;
		this.user = user;
		this.createdate = createdate;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "accessorycode", column = @Column(name = "ACCESSORYCODE", nullable = false, length = 100)),
			@AttributeOverride(name = "accessoryformcode", column = @Column(name = "ACCESSORYFORMCODE", nullable = false, length = 20)) })
	public AccessoryformdetailId getId() {
		return this.id;
	}

	public void setId(AccessoryformdetailId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCESSORYCODE", nullable = false, insertable = false, updatable = false)
	public Accessory getAccessory() {
		return this.accessory;
	}

	public void setAccessory(Accessory accessory) {
		this.accessory = accessory;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCESSORYFORMCODE", nullable = false, insertable = false, updatable = false)
	public Accessoryform getAccessoryform() {
		return this.accessoryform;
	}

	public void setAccessoryform(Accessoryform accessoryform) {
		this.accessoryform = accessoryform;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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
