package com.chori.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
public class RoleModel implements Serializable {
	private String roleid;
	private String creator;
	private String rolename;
	private String description;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdate;
	ArrayList<FunctionModel> listFunction = new ArrayList<FunctionModel>();

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public ArrayList<FunctionModel> getListFunction() {
		return listFunction;
	}

	public void setListFunction(ArrayList<FunctionModel> listFunction) {
		this.listFunction = listFunction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleid == null) ? 0 : roleid.hashCode());
		result = prime * result
				+ ((rolename == null) ? 0 : rolename.hashCode());
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
		RoleModel other = (RoleModel) obj;
		if (roleid == null) {
			if (other.roleid != null)
				return false;
		} else if (!roleid.equals(other.roleid))
			return false;
		if (rolename == null) {
			if (other.rolename != null)
				return false;
		} else if (!rolename.equals(other.rolename))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RoleModel [roleid=" + roleid + ", creator=" + creator
				+ ", rolename=" + rolename + ", description=" + description
				+ ", createdate=" + createdate + "]";
	}

	public RoleModel(String roleid, String creator, String rolename,
			String description, Date createdate) {
		super();
		this.roleid = roleid;
		this.creator = creator;
		this.rolename = rolename;
		this.description = description;
		this.createdate = createdate;
	}

	public RoleModel() {
		super();
		// TODO Auto-generated constructor stub
	}
}
