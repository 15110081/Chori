package com.chori.dao;

import com.chori.AbstractDao;
import com.chori.entity.Orderexternalaccessory;

public interface OrderExternalAccessoryDao extends
		AbstractDao<Orderexternalaccessory, String> {
	public float getActualAssignQuantity(String accessoryCode,String lotNumber,String orderSheetNo);
	
	public void  UpdateOrderQuantity(String orderSheetNo,float orderQuantity);

}