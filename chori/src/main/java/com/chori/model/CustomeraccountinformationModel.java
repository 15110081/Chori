package com.chori.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CustomeraccountinformationModel implements Serializable {
	private Integer customeraccountinfocode;
	private String customerCode;
	private String creator;
	private String bankname;
	private String bankbranch;
	private String accountnumber;
	private String address;
	private String swiftcode;
	private Date createdate;

	public CustomeraccountinformationModel() {
		super();
	}

	public CustomeraccountinformationModel(Integer customeraccountinfocode) {
		super();
		this.customeraccountinfocode = customeraccountinfocode;
	}

	public CustomeraccountinformationModel(Integer customeraccountinfocode,
			String customerCode, String creator, String bankname,
			String bankbranch, String accountnumber, String address,
			String swiftcode, Date createdate) {
		super();
		this.customeraccountinfocode = customeraccountinfocode;
		this.customerCode = customerCode;
		this.creator = creator;
		this.bankname = bankname;
		this.bankbranch = bankbranch;
		this.accountnumber = accountnumber;
		this.address = address;
		this.swiftcode = swiftcode;
		this.createdate = createdate;
	}

	public Integer getCustomeraccountinfocode() {
		return customeraccountinfocode;
	}

	public void setCustomeraccountinfocode(Integer customeraccountinfocode) {
		this.customeraccountinfocode = customeraccountinfocode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
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

	@Override
	public String toString() {
		return "CustomeraccountinformationModel [customeraccountinfocode="
				+ customeraccountinfocode + ", customerCode=" + customerCode
				+ ", creator=" + creator + ", bankname=" + bankname
				+ ", bankbranch=" + bankbranch + ", accountnumber="
				+ accountnumber + ", address=" + address + ", swiftcode="
				+ swiftcode + ", createdate=" + createdate + "]";
	}

}
