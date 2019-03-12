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
 * Accessorygroup generated by hbm2java
 */
@Entity
@Table(name = "accessorygroup")
public class Accessorygroup implements java.io.Serializable {

	private String accessorygroupcode;
	private User user;
	private String description;
	private Date createdate;
	private Set<Accessory> accessories = new HashSet<Accessory>(0);

	public Accessorygroup() {
	}

	public Accessorygroup(String accessorygroupcode) {
		this.accessorygroupcode = accessorygroupcode;
	}

	public Accessorygroup(String accessorygroupcode, User user, String description, Date createdate,
			Set<Accessory> accessories) {
		this.accessorygroupcode = accessorygroupcode;
		this.user = user;
		this.description = description;
		this.createdate = createdate;
		this.accessories = accessories;
	}

	@Id

	@Column(name = "ACCESSORYGROUPCODE", unique = true, nullable = false, length = 100)
	public String getAccessorygroupcode() {
		return this.accessorygroupcode;
	}

	public void setAccessorygroupcode(String accessorygroupcode) {
		this.accessorygroupcode = accessorygroupcode;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accessorygroup")
	public Set<Accessory> getAccessories() {
		return this.accessories;
	}

	public void setAccessories(Set<Accessory> accessories) {
		this.accessories = accessories;
	}

}
