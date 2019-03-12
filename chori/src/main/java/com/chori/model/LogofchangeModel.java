package com.chori.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
public class LogofchangeModel implements Serializable {
	private int logofchangecode;
	private String lotnumber;
	private String subject;
	private String fromemail;
	private String toemail;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createdate;
	private String attachfileurl1;
	private String attachfileurl2;
	private String attachfileurl3;
	private String attachfileurl4;
	private String attachfileurl5;
	private String attachfileurl6;
	private String attachfileurl7;
	private String attachfileurl8;
	private String attachfileurl9;
	private String attachfileurl10;
	private String ccmailstring;
	
	

	public int getLogofchangecode() {
		return logofchangecode;
	}

	public void setLogofchangecode(int logofchangecode) {
		this.logofchangecode = logofchangecode;
	}

	public String getLotnumber() {
		return lotnumber;
	}

	public void setLotnumber(String lotnumber) {
		this.lotnumber = lotnumber;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFromemail() {
		return fromemail;
	}

	public void setFromemail(String fromemail) {
		this.fromemail = fromemail;
	}

	public String getToemail() {
		return toemail;
	}

	public void setToemail(String toemail) {
		this.toemail = toemail;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getAttachfileurl1() {
		return attachfileurl1;
	}

	public void setAttachfileurl1(String attachfileurl1) {
		this.attachfileurl1 = attachfileurl1;
	}

	public String getAttachfileurl2() {
		return attachfileurl2;
	}

	public void setAttachfileurl2(String attachfileurl2) {
		this.attachfileurl2 = attachfileurl2;
	}

	public String getAttachfileurl3() {
		return attachfileurl3;
	}

	public void setAttachfileurl3(String attachfileurl3) {
		this.attachfileurl3 = attachfileurl3;
	}

	public String getAttachfileurl4() {
		return attachfileurl4;
	}

	public void setAttachfileurl4(String attachfileurl4) {
		this.attachfileurl4 = attachfileurl4;
	}

	public String getAttachfileurl5() {
		return attachfileurl5;
	}

	public void setAttachfileurl5(String attachfileurl5) {
		this.attachfileurl5 = attachfileurl5;
	}

	public String getAttachfileurl6() {
		return attachfileurl6;
	}

	public void setAttachfileurl6(String attachfileurl6) {
		this.attachfileurl6 = attachfileurl6;
	}

	public String getAttachfileurl7() {
		return attachfileurl7;
	}

	public void setAttachfileurl7(String attachfileurl7) {
		this.attachfileurl7 = attachfileurl7;
	}

	public String getAttachfileurl8() {
		return attachfileurl8;
	}

	public void setAttachfileurl8(String attachfileurl8) {
		this.attachfileurl8 = attachfileurl8;
	}

	public String getAttachfileurl9() {
		return attachfileurl9;
	}

	public void setAttachfileurl9(String attachfileurl9) {
		this.attachfileurl9 = attachfileurl9;
	}

	public String getAttachfileurl10() {
		return attachfileurl10;
	}

	public void setAttachfileurl10(String attachfileurl10) {
		this.attachfileurl10 = attachfileurl10;
	}

	public String getCcmailstring() {
		return ccmailstring;
	}

	public void setCcmailstring(String ccmailstring) {
		this.ccmailstring = ccmailstring;
	}

	public LogofchangeModel(int logofchangecode, String lotnumber, String subject, String fromemail, String toemail,
			Date createdate, String attachfileurl1, String attachfileurl2, String attachfileurl3, String attachfileurl4,
			String attachfileurl5, String attachfileurl6, String attachfileurl7, String attachfileurl8,
			String attachfileurl9, String attachfileurl10, String ccmailstring) {
		super();
		this.logofchangecode = logofchangecode;
		this.lotnumber = lotnumber;
		this.subject = subject;
		this.fromemail = fromemail;
		this.toemail = toemail;
		this.createdate = createdate;
		this.attachfileurl1 = attachfileurl1;
		this.attachfileurl2 = attachfileurl2;
		this.attachfileurl3 = attachfileurl3;
		this.attachfileurl4 = attachfileurl4;
		this.attachfileurl5 = attachfileurl5;
		this.attachfileurl6 = attachfileurl6;
		this.attachfileurl7 = attachfileurl7;
		this.attachfileurl8 = attachfileurl8;
		this.attachfileurl9 = attachfileurl9;
		this.attachfileurl10 = attachfileurl10;
		this.ccmailstring = ccmailstring;
	}

	public LogofchangeModel() {
		super();
	}

	@Override
	public String toString() {
		return "LogofchangeModel [logofchangecode=" + logofchangecode + ", lotnumber=" + lotnumber + ", subject="
				+ subject + ", fromemail=" + fromemail + ", toemail=" + toemail + ", createdate=" + createdate
				+ ", attachfileurl1=" + attachfileurl1 + ", attachfileurl2=" + attachfileurl2 + ", attachfileurl3="
				+ attachfileurl3 + ", attachfileurl4=" + attachfileurl4 + ", attachfileurl5=" + attachfileurl5
				+ ", attachfileurl6=" + attachfileurl6 + ", attachfileurl7=" + attachfileurl7 + ", attachfileurl8="
				+ attachfileurl8 + ", attachfileurl9=" + attachfileurl9 + ", attachfileurl10=" + attachfileurl10
				+ ", ccmailstring=" + ccmailstring + "]";
	}

	

}
