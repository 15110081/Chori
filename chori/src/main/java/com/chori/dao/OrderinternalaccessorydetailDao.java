package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Orderinternalaccessorydetail;
import com.chori.entity.OrderinternalaccessorydetailId;

public interface OrderinternalaccessorydetailDao
		extends
		AbstractDao<Orderinternalaccessorydetail, OrderinternalaccessorydetailId> {
	public List<Orderinternalaccessorydetail> getListOrderinternalaccessorydetailByOrderSheetNo(
			String ordersheetno);
	List<Orderinternalaccessorydetail> getListOrderinternalaccessorydetailByAccessoryCodeAndFactoryCode(String accessoryCode, String factoryCode);
}
