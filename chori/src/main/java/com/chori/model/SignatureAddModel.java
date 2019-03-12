package com.chori.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chori.entity.User;

@SuppressWarnings("serial")
public class SignatureAddModel implements Serializable {
	private Integer accordersigncode;
	private User user;
	private String name;
	private String text1;
	private String text2;
	private String imgurl;
	private Date createdate;
	List<FileBucket> files = new ArrayList<FileBucket>();

	public Integer getAccordersigncode() {
		return accordersigncode;
	}

	public void setAccordersigncode(Integer accordersigncode) {
		this.accordersigncode = accordersigncode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public List<FileBucket> getFiles() {
		return files;
	}

	public void setFiles(List<FileBucket> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "SignatureAddModel [accordersigncode=" + accordersigncode
				+ ", user=" + user + ", name=" + name + ", text1=" + text1
				+ ", text2=" + text2 + ", imgurl=" + imgurl + ", createdate="
				+ createdate + ", files=" + files + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((accordersigncode == null) ? 0 : accordersigncode.hashCode());
		result = prime * result
				+ ((createdate == null) ? 0 : createdate.hashCode());
		result = prime * result + ((files == null) ? 0 : files.hashCode());
		result = prime * result + ((imgurl == null) ? 0 : imgurl.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((text1 == null) ? 0 : text1.hashCode());
		result = prime * result + ((text2 == null) ? 0 : text2.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		SignatureAddModel other = (SignatureAddModel) obj;
		if (accordersigncode == null) {
			if (other.accordersigncode != null)
				return false;
		} else if (!accordersigncode.equals(other.accordersigncode))
			return false;
		if (createdate == null) {
			if (other.createdate != null)
				return false;
		} else if (!createdate.equals(other.createdate))
			return false;
		if (files == null) {
			if (other.files != null)
				return false;
		} else if (!files.equals(other.files))
			return false;
		if (imgurl == null) {
			if (other.imgurl != null)
				return false;
		} else if (!imgurl.equals(other.imgurl))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (text1 == null) {
			if (other.text1 != null)
				return false;
		} else if (!text1.equals(other.text1))
			return false;
		if (text2 == null) {
			if (other.text2 != null)
				return false;
		} else if (!text2.equals(other.text2))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	public SignatureAddModel(Integer accordersigncode, User user, String name,
			String text1, String text2, String imgurl, Date createdate,
			List<FileBucket> files) {
		super();
		this.accordersigncode = accordersigncode;
		this.user = user;
		this.name = name;
		this.text1 = text1;
		this.text2 = text2;
		this.imgurl = imgurl;
		this.createdate = createdate;
		this.files = files;
	}

	public SignatureAddModel() {
		// files.add(new FileBucket());
		// files.add(new FileBucket());
	}
}
