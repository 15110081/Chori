package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Size;

public interface SizeDao extends AbstractDao<Size, Integer> {
	public List<Size> getListSizeByCustomer(String customercode,
			String garmentKindCode);
}