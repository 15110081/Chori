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
 * Garmentkind generated by hbm2java
 */
@Entity
@Table(name = "garmentkind")
public class Garmentkind implements java.io.Serializable {

	private String garmentkindcode;
	private User user;
	private String description;
	private Date createdate;
	private Set<Garmentstyle> garmentstyles = new HashSet<Garmentstyle>(0);
	private Set<Size> sizes = new HashSet<Size>(0);

	public Garmentkind() {
	}

	public Garmentkind(String garmentkindcode) {
		this.garmentkindcode = garmentkindcode;
	}

	public Garmentkind(String garmentkindcode, User user, String description, Date createdate,
			Set<Garmentstyle> garmentstyles, Set<Size> sizes) {
		this.garmentkindcode = garmentkindcode;
		this.user = user;
		this.description = description;
		this.createdate = createdate;
		this.garmentstyles = garmentstyles;
		this.sizes = sizes;
	}

	@Id

	@Column(name = "GARMENTKINDCODE", unique = true, nullable = false, length = 50)
	public String getGarmentkindcode() {
		return this.garmentkindcode;
	}

	public void setGarmentkindcode(String garmentkindcode) {
		this.garmentkindcode = garmentkindcode;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garmentkind")
	public Set<Garmentstyle> getGarmentstyles() {
		return this.garmentstyles;
	}

	public void setGarmentstyles(Set<Garmentstyle> garmentstyles) {
		this.garmentstyles = garmentstyles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garmentkind")
	public Set<Size> getSizes() {
		return this.sizes;
	}

	public void setSizes(Set<Size> sizes) {
		this.sizes = sizes;
	}

}
