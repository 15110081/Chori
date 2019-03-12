package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Garmentconsumption;

public interface GarmentConsumptionDao extends
		AbstractDao<Garmentconsumption, Integer> {
	List<Garmentconsumption> getAllGarmentConsumptionCodeByGarmentstyleCodeCustomerCodeSizeCode(
			String garmentstylecode , String customercode , Integer sizecode);
	
}
