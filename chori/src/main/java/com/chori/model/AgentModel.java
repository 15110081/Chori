package com.chori.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class AgentModel implements Serializable {
	private int agentcode;
	private String creator;
	private String shortname;
	private String longname;
	private String address;
	private String tel;
	private String fax;
	private String taxno;
	private String status;
	private String remark;
	private Date createdate;
	private Set<AgentcontactModel> agentcontactModellist = new HashSet<AgentcontactModel>();

	public AgentModel() {
		super();
	}

	public AgentModel(int agentcode) {
		super();
		this.agentcode = agentcode;
	}

	public AgentModel(int agentcode, String creator, String shortname,
			String longname, String address, String tel, String fax,
			String taxno, String status, String remark, Date createdate,
			Set<AgentcontactModel> agentcontactModellist) {
		super();
		this.agentcode = agentcode;
		this.creator = creator;
		this.shortname = shortname;
		this.longname = longname;
		this.address = address;
		this.tel = tel;
		this.fax = fax;
		this.taxno = taxno;
		this.status = status;
		this.remark = remark;
		this.createdate = createdate;
		this.agentcontactModellist = agentcontactModellist;
	}

	public int getAgentcode() {
		return agentcode;
	}

	public void setAgentcode(int agentcode) {
		this.agentcode = agentcode;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getLongname() {
		return longname;
	}

	public void setLongname(String longname) {
		this.longname = longname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTaxno() {
		return taxno;
	}

	public void setTaxno(String taxno) {
		this.taxno = taxno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Set<AgentcontactModel> getAgentcontactModellist() {
		return agentcontactModellist;
	}

	public void setAgentcontactModellist(
			Set<AgentcontactModel> agentcontactModellist) {
		this.agentcontactModellist = agentcontactModellist;
	}

	@Override
	public String toString() {
		return "AgentModel [agentcode=" + agentcode + ", creator=" + creator
				+ ", shortname=" + shortname + ", longname=" + longname
				+ ", address=" + address + ", tel=" + tel + ", fax=" + fax
				+ ", taxno=" + taxno + ", status=" + status + ", remark="
				+ remark + ", createdate=" + createdate
				+ ", agentcontactModellist=" + agentcontactModellist + "]";
	}

}
