package com.chori.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class AgentcontactModel implements Serializable {
	private Integer agentcontactcode;
	private Integer agentCode;
	private String creator;
	private String name;
	private String email;
	private String tel;
	private Date createdate;

	public AgentcontactModel() {
		super();
	}

	public AgentcontactModel(Integer agentcontactcode) {
		super();
		this.agentcontactcode = agentcontactcode;
	}

	public AgentcontactModel(Integer agentcontactcode, Integer agentCode,
			String creator, String name, String email, String tel,
			Date createdate) {
		super();
		this.agentcontactcode = agentcontactcode;
		this.agentCode = agentCode;
		this.creator = creator;
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.createdate = createdate;
	}

	public Integer getAgentcontactcode() {
		return agentcontactcode;
	}

	public void setAgentcontactcode(Integer agentcontactcode) {
		this.agentcontactcode = agentcontactcode;
	}

	public Integer getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(Integer agentCode) {
		this.agentCode = agentCode;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "AgentcontactModel [agentcontactcode=" + agentcontactcode
				+ ", agentCode=" + agentCode + ", creator=" + creator
				+ ", name=" + name + ", email=" + email + ", tel=" + tel
				+ ", createdate=" + createdate + "]";
	}

}
