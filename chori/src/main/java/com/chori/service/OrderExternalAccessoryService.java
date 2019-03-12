package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Orderexternalaccessory;
import com.chori.model.OrderExternalAccessoryModel;

public interface OrderExternalAccessoryService extends
		AbstractService<Orderexternalaccessory, String> {

	boolean addOrderExternalAccessory(
			OrderExternalAccessoryModel orderExternalAccessoryModel,
			String creator);

	public boolean isOrderSheetNoExisted(String ordsheetno);

	public List<OrderExternalAccessoryModel> getAllOrderExternalAccessoryModels();

	public OrderExternalAccessoryModel findOrderExternalAccessoryModelById(
			String OrderSheetNo);

	public boolean editOrderExternalAccessory(
			OrderExternalAccessoryModel orderExternalAccessoryModel);
	
	public float getActualAssignQuantity(String accessoryCode,String lotNumber,String orderSheetNo);
	public void UpdateorderQuantity(String orderSheetNo,float orderQuantity);

	boolean updateOrderQtyOfAPiAssignExternalAccessory(String accessoryCode, String lotNumber, Integer orderQty,
			String orderSheetNo);
}