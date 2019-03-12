package com.chori.service;

import java.util.Date;
import java.util.List;

import com.chori.model.AccessoryPriceModel;

public interface AccessoryPriceService {

	List<AccessoryPriceModel> getAllAccessoryPrice();

	boolean addAccessoryPrice(AccessoryPriceModel accessorypriceMo,
			String userName);

	AccessoryPriceModel findAccessoryPriceModelById(Integer accessorypriceCode);

	boolean editAccessoryPrice(AccessoryPriceModel accessorypriceMo);

	boolean deleteAccessoryprice(int accessorypriceCode);



	public List<AccessoryPriceModel> getListAccessorypriceModelByAccessoryCodeandAccessorySuplierandOrderDate(
			String accessoryCode, String accessorySuplier, Date orderDate);

	public List<AccessoryPriceModel> getListAccessorypriceModelByAccessoryCodeandAccessorySuplierandOrderDate2ndCase(
			String accessoryCode, String accessorySuplier, Date orderDate);
}
