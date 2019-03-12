package com.chori.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserModel implements Serializable {
	private String username;
	private int agentid;
	private String agentShortname;
	private String roleid;
	private String rolename;
	private String creator;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private String emailpassword;
	private String phone;
	private String status;
	private Boolean ccmailstring;
	private String newPassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAgentid() {
		return agentid;
	}

	public void setAgentid(int agentid) {
		this.agentid = agentid;
	}

	public String getAgentShortname() {
		return agentShortname;
	}

	public void setAgentShortname(String agentShortname) {
		this.agentShortname = agentShortname;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailpassword() {
		return emailpassword;
	}

	public void setEmailpassword(String emailpassword) {
		this.emailpassword = emailpassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Boolean getCcmailstring() {
		return ccmailstring;
	}

	public void setCcmailstring(Boolean ccmailstring) {
		this.ccmailstring = ccmailstring;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserModel other = (UserModel) obj;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserModel [username=" + username + ", agentid=" + agentid
				+ ", agentShortname=" + agentShortname + ", roleid=" + roleid
				+ ", rolename=" + rolename + ", creator=" + creator
				+ ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", email=" + email
				+ ", emailpassword=" + emailpassword + ", phone=" + phone
				+ ", status=" + status + ", ccmailstring=" + ccmailstring + "]";
	}

	public UserModel(String username, int agentid, String agentShortname,
			String roleid, String rolename, String creator, String password,
			String firstname, String lastname, String email,
			String emailpassword, String phone, String status) {
		super();
		this.username = username;
		this.agentid = agentid;
		this.agentShortname = agentShortname;
		this.roleid = roleid;
		this.rolename = rolename;
		this.creator = creator;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.emailpassword = emailpassword;
		this.phone = phone;
		this.status = status;
	}

}
