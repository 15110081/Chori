package com.chori.service;

import java.util.List;

import com.chori.model.ShippinglineModel;

public interface ShippinglineService {
	List<ShippinglineModel> getAllShippinglineModel();

	boolean addNewShippingLine(ShippinglineModel splModel, String userName);

	ShippinglineModel findShippingLineModelById(String id);

	boolean editShippingLine(ShippinglineModel splModel, String userName);

	boolean delete(String id);
}
