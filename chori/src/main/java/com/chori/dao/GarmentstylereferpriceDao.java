package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Garmentstylereferprice;
import com.chori.entity.GarmentstylereferpriceId;

public interface GarmentstylereferpriceDao extends
AbstractDao<Garmentstylereferprice, GarmentstylereferpriceId>{
	public List<Garmentstylereferprice> getListGarmentstylereferpriceByGarmentstyleCode(
			String garmentStyleCode);
}
