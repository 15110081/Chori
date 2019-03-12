package com.chori.model;

public class TypeForFpiModel {
	private String typecode;
	private Integer typePcs;
	private Integer typeFpiPcs;
	private Integer typeRfpiPcs;
	public TypeForFpiModel() {
		super();
	}
	public TypeForFpiModel(String typecode) {
		super();
		this.typecode = typecode;
	}
	public TypeForFpiModel(String typecode, Integer typePcs, Integer typeFpiPcs, Integer typeRfpiPcs) {
		super();
		this.typecode = typecode;
		this.typePcs = typePcs;
		this.typeFpiPcs = typeFpiPcs;
		this.typeRfpiPcs = typeRfpiPcs;
	}
	public String getTypecode() {
		return typecode;
	}
	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}
	public Integer getTypePcs() {
		return typePcs;
	}
	public void setTypePcs(Integer typePcs) {
		this.typePcs = typePcs;
	}
	public Integer getTypeFpiPcs() {
		return typeFpiPcs;
	}
	public void setTypeFpiPcs(Integer typeFpiPcs) {
		this.typeFpiPcs = typeFpiPcs;
	}
	public Integer getTypeRfpiPcs() {
		return typeRfpiPcs;
	}
	public void setTypeRfpiPcs(Integer typeRfpiPcs) {
		this.typeRfpiPcs = typeRfpiPcs;
	}
	@Override
	public String toString() {
		return "TypeForFpiModel [typecode=" + typecode + ", typePcs=" + typePcs + ", typeFpiPcs=" + typeFpiPcs
				+ ", typeRfpiPcs=" + typeRfpiPcs + "]";
	}	
	
}
