package com.chori.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FunctionModel implements Serializable {
	private String functionid;
	private String functionname;
	private String description;

	public String getFunctionid() {
		return functionid;
	}

	public void setFunctionid(String functionid) {
		this.functionid = functionid;
	}

	public String getFunctionname() {
		return functionname;
	}

	public void setFunctionname(String functionname) {
		this.functionname = functionname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((functionid == null) ? 0 : functionid.hashCode());
		result = prime * result
				+ ((functionname == null) ? 0 : functionname.hashCode());
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
		FunctionModel other = (FunctionModel) obj;
		if (functionid == null) {
			if (other.functionid != null)
				return false;
		} else if (!functionid.equals(other.functionid))
			return false;
		if (functionname == null) {
			if (other.functionname != null)
				return false;
		} else if (!functionname.equals(other.functionname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FunctionModel [functionid=" + functionid + ", functionname="
				+ functionname + ", description=" + description + "]";
	}

	public FunctionModel(String functionid, String functionname,
			String description) {
		super();
		this.functionid = functionid;
		this.functionname = functionname;
		this.description = description;
	}

	public FunctionModel() {
		super();
		// TODO Auto-generated constructor stub
	}
}
