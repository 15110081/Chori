package com.chori.service;

import com.chori.AbstractService;
import com.chori.entity.Externalaccessorystock;
import com.chori.model.ExternalAccessoryStockModel;

public interface ExternalAccessoryStockService extends
		AbstractService<Externalaccessorystock, String> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chori.service.ExternalaccessoryStockService#
	 * findExternalaccessorystockModelByOrdersheetno(java.lang.String)
	 */
	boolean isExistExternalaccessorystockModelByAccessory(String accessoryCode);

	boolean isExternalaccessorystockExistedById(String ordersheetno);
	public float getinventoryquantity(String accessoryCode);
	public float getEstimateQuantity(String lotNumber,String accessoryCode);
	public float getActualAssignQuantity(String lotNumber,String accessoryCode);

	Float saveAssignFromStock(String accessoryCode, String factoryCode, Float assignQty);

	boolean updateStockAssignQtyOfAnAssign(String accessoryCode, String lotNumber, Float stockAssignQty);

	ExternalAccessoryStockModel getStockAvailableQuantityInformation(String accessoryCode, String factoryCode,
			String lotNumber);

	boolean updateStockAvailableQtyWhenEdit(String accessoryCode, String lotNumber);

	Float calculateShortageQuantity(String lotNumber, String accessoryCode);
}