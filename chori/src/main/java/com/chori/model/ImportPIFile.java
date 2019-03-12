package com.chori.model;

public class ImportPIFile {

	private String size;
	private Float qtypcs;
	private Float qtyctn;
	private String garmentstyle;
	private String color;
	private Float pcsdozen;
	private String barcode;
	private Float yardage;
	private String stylecode;
	
	public ImportPIFile(String size, Float qtypcs, Float qtyctn, String garmentstyle, String color, Float pcsdozen,
			String barcode, Float yardage, String stylecode) {
		super();
		this.size = size;
		this.qtypcs = qtypcs;
		this.qtyctn = qtyctn;
		this.garmentstyle = garmentstyle;
		this.color = color;
		this.pcsdozen = pcsdozen;
		this.barcode = barcode;
		this.yardage = yardage;
		this.stylecode = stylecode;
	}

	public ImportPIFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Float getQtypcs() {
		return qtypcs;
	}

	public void setQtypcs(Float qtypcs) {
		this.qtypcs = qtypcs;
	}

	public Float getQtyctn() {
		return qtyctn;
	}

	public void setQtyctn(Float qtyctn) {
		this.qtyctn = qtyctn;
	}

	public String getGarmentstyle() {
		return garmentstyle;
	}

	public void setGarmentstyle(String garmentstyle) {
		this.garmentstyle = garmentstyle;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Float getPcsdozen() {
		return pcsdozen;
	}

	public void setPcsdozen(Float pcsdozen) {
		this.pcsdozen = pcsdozen;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Float getYardage() {
		return yardage;
	}

	public void setYardage(Float yardage) {
		this.yardage = yardage;
	}

	public String getStylecode() {
		return stylecode;
	}

	public void setStylecode(String stylecode) {
		this.stylecode = stylecode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((garmentstyle == null) ? 0 : garmentstyle.hashCode());
		result = prime * result + ((pcsdozen == null) ? 0 : pcsdozen.hashCode());
		result = prime * result + ((qtyctn == null) ? 0 : qtyctn.hashCode());
		result = prime * result + ((qtypcs == null) ? 0 : qtypcs.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((stylecode == null) ? 0 : stylecode.hashCode());
		result = prime * result + ((yardage == null) ? 0 : yardage.hashCode());
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
		ImportPIFile other = (ImportPIFile) obj;
		if (barcode == null) {
			if (other.barcode != null)
				return false;
		} else if (!barcode.equals(other.barcode))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (garmentstyle == null) {
			if (other.garmentstyle != null)
				return false;
		} else if (!garmentstyle.equals(other.garmentstyle))
			return false;
		if (pcsdozen == null) {
			if (other.pcsdozen != null)
				return false;
		} else if (!pcsdozen.equals(other.pcsdozen))
			return false;
		if (qtyctn == null) {
			if (other.qtyctn != null)
				return false;
		} else if (!qtyctn.equals(other.qtyctn))
			return false;
		if (qtypcs == null) {
			if (other.qtypcs != null)
				return false;
		} else if (!qtypcs.equals(other.qtypcs))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (stylecode == null) {
			if (other.stylecode != null)
				return false;
		} else if (!stylecode.equals(other.stylecode))
			return false;
		if (yardage == null) {
			if (other.yardage != null)
				return false;
		} else if (!yardage.equals(other.yardage))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ImportPIFile [size=" + size + ", qtypcs=" + qtypcs + ", qtyctn=" + qtyctn + ", garmentstyle="
				+ garmentstyle + ", color=" + color + ", pcsdozen=" + pcsdozen + ", barcode=" + barcode + ", yardage="
				+ yardage + ", stylecode=" + stylecode + "]";
	}
	
	
	
}
