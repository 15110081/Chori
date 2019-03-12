package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Orderinternalaccessory;
import com.chori.model.AccessoryModel;
import com.chori.model.OrderInternalAccessoryDetailModel;
import com.chori.model.OrderInternalAccessoryModel;

public interface OrderInternalAccessoryService extends
		AbstractService<Orderinternalaccessory, String> {

	 boolean addOrderInternalAccessory(OrderInternalAccessoryModel orderInternalAccessoryModel,
				List<OrderInternalAccessoryDetailModel> listOrderInternalAccessoryDetailModel,
				String creator);

	List<OrderInternalAccessoryModel> getAllOrderInternalAccessoryModels();

//	List<AccessoryModel> getAllInternalAccessory();

	OrderInternalAccessoryModel findOrderInternalAccessoryModelById(
			String OrderSheetNo);

	boolean editOrderInternalAccessory(
			OrderInternalAccessoryModel orderInternalAccessoryModel);
	
	List<OrderInternalAccessoryDetailModel> getAllOrderInternalAccessoryDetailModelsByAccessoryCode(
			String accessoryCode);
	public List<AccessoryModel> getAllInternalAccessoryBySupplier(String accessorySupplier);
	
	public List<OrderInternalAccessoryDetailModel> findListOrderInternalAccessoryDetailModelByOrderSheetNo(String OrderSheetNo);
	
	public boolean editOrderInternalAccessory(OrderInternalAccessoryModel orderInternalAccessoryModel,
			List<OrderInternalAccessoryDetailModel> listOrderInternalAccessoryDetailModel, String creator);
	
	public boolean cancelOrderInternalAccessory(OrderInternalAccessoryModel orderInternalAccessoryModel,
			String editor);
}
