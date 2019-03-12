package com.chori.model;

import org.springframework.web.multipart.MultipartFile;

public class ExcelImportDemo {
	
	private String lotnumber;
	private Integer option;
	private MultipartFile file;
	
	public Integer getOption() {
		return option;
	}

	public void setOption(Integer option) {
		this.option = option;
	}

	public String getLotnumber() {
		return lotnumber;
	}

	public void setLotnumber(String lotnumber) {
		this.lotnumber = lotnumber;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
