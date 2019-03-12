package com.chori.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class FactoryaccountinformationModel implements Serializable {
	private Integer factoryaccountinfocode;
	private String factoryCode;
	private String creator;
	private String bankname;
	private String bankbranch;
	private String accountnumber;
	private String address;
	private String swiftcode;
	private Date createdate;

	public Integer getFactoryaccountinfocode() {
		return factoryaccountinfocode;
	}

	public void setFactoryaccountinfocode(Integer factoryaccountinfocode) {
		this.factoryaccountinfocode = factoryaccountinfocode;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBankbranch() {
		return bankbranch;
	}

	public void setBankbranch(String bankbranch) {
		this.bankbranch = bankbranch;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSwiftcode() {
		return swiftcode;
	}

	public void setSwiftcode(String swiftcode) {
		this.swiftcode = swiftcode;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result
	// + ((factoryCode == null) ? 0 : factoryCode.hashCode());
	// result = prime
	// * result
	// + ((factoryaccountinfocode == null) ? 0
	// : factoryaccountinfocode.hashCode());
	// return result;
	// }
	// @Override
	// public boolean equals(Object obj) {
	// if (this == obj)
	// return true;
	// if (obj == null)
	// return false;
	// if (getClass() != obj.getClass())
	// return false;
	// FactoryaccountinformationModel other = (FactoryaccountinformationModel)
	// obj;
	// if (factoryCode == null) {
	// if (other.factoryCode != null)
	// return false;
	// } else if (!factoryCode.equals(other.factoryCode))
	// return false;
	// if (factoryaccountinfocode == null) {
	// if (other.factoryaccountinfocode != null)
	// return false;
	// } else if (!factoryaccountinfocode.equals(other.factoryaccountinfocode))
	// return false;
	// return true;
	// }

	@Override
	public String toString() {
		return "FactoryaccountinformationModel [factoryaccountinfocode="
				+ factoryaccountinfocode + ", factoryCode=" + factoryCode
				+ ", creator=" + creator + ", bankname=" + bankname
				+ ", bankbranch=" + bankbranch + ", accountnumber="
				+ accountnumber + ", address=" + address + ", swiftcode="
				+ swiftcode + ", createdate=" + createdate + "]";
	}

	public FactoryaccountinformationModel(Integer factoryaccountinfocode,
			String factoryCode, String creator, String bankname,
			String bankbranch, String accountnumber, String address,
			String swiftcode, Date createdate) {
		super();
		this.factoryaccountinfocode = factoryaccountinfocode;
		this.factoryCode = factoryCode;
		this.creator = creator;
		this.bankname = bankname;
		this.bankbranch = bankbranch;
		this.accountnumber = accountnumber;
		this.address = address;
		this.swiftcode = swiftcode;
		this.createdate = createdate;
	}

	public FactoryaccountinformationModel() {
		super();
		// TODO Auto-generated constructor stub
	}
}
