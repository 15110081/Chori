package com.chori.model;

public class TypeForPiModel {
	private String typecode;
	private Integer typePcs;
	private Integer typeFaPcs;
	private Integer typeFpiPcs;
	
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
	public Integer getTypeFaPcs() {
		return typeFaPcs;
	}
	public void setTypeFaPcs(Integer typeFaPcs) {
		this.typeFaPcs = typeFaPcs;
	}
	public Integer getTypeFpiPcs() {
		return typeFpiPcs;
	}
	public void setTypeFpiPcs(Integer typeFpiPcs) {
		this.typeFpiPcs = typeFpiPcs;
	}
	
	@Override
	public String toString() {
		return "TypeForPiModel [typecode=" + typecode + ", typePcs=" + typePcs
				+ ", typeFaPcs=" + typeFaPcs + ", typeFpiPcs=" + typeFpiPcs
				+ "]";
	}
	
	public TypeForPiModel(String typecode, Integer typeFaPcs, Integer typeFpiPcs) {
		super();
		this.typecode = typecode;
		this.typeFaPcs = typeFaPcs;
		this.typeFpiPcs = typeFpiPcs;
	}
	public TypeForPiModel() {
		super();
		// TODO Auto-generated constructor stub
	}
}
