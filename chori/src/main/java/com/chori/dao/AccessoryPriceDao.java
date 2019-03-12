package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Accessoryprice;

public interface AccessoryPriceDao extends AbstractDao<Accessoryprice, Integer> {

	public List<Accessoryprice> getListAccessorypriceByAccessoryCodeandAccessorySuplierandOrderDate(
			String accessoryCode, String accessorySuplier);
	// public List<Accessoryprice>
	// getListAccessorypriceByAccessoryCodeandAccessorySuplier(
	// String accessoryCode, String accessorySuplier) ;
	public boolean isExistAccPriceByAccessoryCodeAndSupplier(
			String accessoryCode, String accessorySuplier);
}
