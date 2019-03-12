package com.chori.model;

import java.util.ArrayList;
import java.util.List;

public class AccessoryMultiFileBucket {
	List<FileBucket> files = new ArrayList<FileBucket>();

	public AccessoryMultiFileBucket() {
		files.add(new FileBucket());
		files.add(new FileBucket());
	}

	public List<FileBucket> getFiles() {
		return files;
	}

	public void setFiles(List<FileBucket> files) {
		this.files = files;
	}
}
