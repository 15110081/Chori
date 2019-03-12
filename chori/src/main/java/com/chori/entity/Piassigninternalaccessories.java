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
 * Piassigninternalaccessories generated by hbm2java
 */
@Entity
@Table(name = "piassigninternalaccessories")
public class Piassigninternalaccessories implements java.io.Serializable {

	private Integer piinternalaccessories;
	private Accessory accessory;
	private Pi pi;
	private User user;
	private Date createdate;
	private Set<Piassigninternalaccessoriesoforders> piassigninternalaccessoriesoforderses = new HashSet<Piassigninternalaccessoriesoforders>(
			0);
	private Set<Piassigninternalaccessoriesdetail> piassigninternalaccessoriesdetails = new HashSet<Piassigninternalaccessoriesdetail>(
			0);

	public Piassigninternalaccessories() {
	}

	public Piassigninternalaccessories(Accessory accessory, Pi pi, User user, Date createdate,
			Set<Piassigninternalaccessoriesoforders> piassigninternalaccessoriesoforderses,
			Set<Piassigninternalaccessoriesdetail> piassigninternalaccessoriesdetails) {
		this.accessory = accessory;
		this.pi = pi;
		this.user = user;
		this.createdate = createdate;
		this.piassigninternalaccessoriesoforderses = piassigninternalaccessoriesoforderses;
		this.piassigninternalaccessoriesdetails = piassigninternalaccessoriesdetails;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "PIINTERNALACCESSORIES", unique = true, nullable = false)
	public Integer getPiinternalaccessories() {
		return this.piinternalaccessories;
	}

	public void setPiinternalaccessories(Integer piinternalaccessories) {
		this.piinternalaccessories = piinternalaccessories;
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
	@JoinColumn(name = "LOTNUMBER")
	public Pi getPi() {
		return this.pi;
	}

	public void setPi(Pi pi) {
		this.pi = pi;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "piassigninternalaccessories")
	public Set<Piassigninternalaccessoriesoforders> getPiassigninternalaccessoriesoforderses() {
		return this.piassigninternalaccessoriesoforderses;
	}

	public void setPiassigninternalaccessoriesoforderses(
			Set<Piassigninternalaccessoriesoforders> piassigninternalaccessoriesoforderses) {
		this.piassigninternalaccessoriesoforderses = piassigninternalaccessoriesoforderses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "piassigninternalaccessories")
	public Set<Piassigninternalaccessoriesdetail> getPiassigninternalaccessoriesdetails() {
		return this.piassigninternalaccessoriesdetails;
	}

	public void setPiassigninternalaccessoriesdetails(
			Set<Piassigninternalaccessoriesdetail> piassigninternalaccessoriesdetails) {
		this.piassigninternalaccessoriesdetails = piassigninternalaccessoriesdetails;
	}

}
