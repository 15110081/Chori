package com.chori.model;

import java.util.Date;

public class PiassigninternalaccessoriesdetailModel {
	private Integer piinternalaccdetailcode;
	private String color;
	private String garmentstyle;
	private Integer piassigninternalaccessories;
	private Double assignquantity;
	private Date createdate;
	private Integer sizecode;
	private Float usedvalue;
	private Integer pcs;
	private Double recommendQty;

	public PiassigninternalaccessoriesdetailModel(Integer piinternalaccdetailcode, String color, String garmentstyle,
			Integer piassigninternalaccessories, Double assignquantity, Date createdate, Integer sizecode,
			Float usedvalue, Integer pcs, Double recommendQty) {
		super();
		this.piinternalaccdetailcode = piinternalaccdetailcode;
		this.color = color;
		this.garmentstyle = garmentstyle;
		this.piassigninternalaccessories = piassigninternalaccessories;
		this.assignquantity = assignquantity;
		this.createdate = createdate;
		this.sizecode = sizecode;
		this.usedvalue = usedvalue;
		this.pcs = pcs;
		this.recommendQty = recommendQty;
	}

	public PiassigninternalaccessoriesdetailModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getPcs() {
		return pcs;
	}

	public void setPcs(Integer pcs) {
		this.pcs = pcs;
	}

	public Double getRecommendQty() {
		return recommendQty;
	}

	public void setRecommendQty(Double recommendQty) {
		this.recommendQty = recommendQty;
	}

	public Integer getPiinternalaccdetailcode() {
		return piinternalaccdetailcode;
	}

	public void setPiinternalaccdetailcode(Integer piinternalaccdetailcode) {
		this.piinternalaccdetailcode = piinternalaccdetailcode;
	}
	
	public Integer getSizecode() {
		return sizecode;
	}

	public void setSizecode(Integer sizecode) {
		this.sizecode = sizecode;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getGarmentstyle() {
		return garmentstyle;
	}

	public void setGarmentstyle(String garmentstyle) {
		this.garmentstyle = garmentstyle;
	}

	public Integer getPiassigninternalaccessories() {
		return piassigninternalaccessories;
	}

	public void setPiassigninternalaccessories(
			Integer piassigninternalaccessories) {
		this.piassigninternalaccessories = piassigninternalaccessories;
	}

	public Double getAssignquantity() {
		return assignquantity;
	}

	public void setAssignquantity(Double assignquantity) {
		this.assignquantity = assignquantity;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Float getUsedvalue() {
		return usedvalue;
	}

	public void setUsedvalue(Float usedvalue) {
		this.usedvalue = usedvalue;
	}

	@Override
	public String toString() {
		return "PiassigninternalaccessoriesdetailModel [piinternalaccdetailcode=" + piinternalaccdetailcode + ", color="
				+ color + ", garmentstyle=" + garmentstyle + ", piassigninternalaccessories="
				+ piassigninternalaccessories + ", assignquantity=" + assignquantity + ", createdate=" + createdate
				+ ", sizecode=" + sizecode + ", usedvalue=" + usedvalue + ", pcs=" + pcs + ", recommendQty="
				+ recommendQty + "]";
	}

}
