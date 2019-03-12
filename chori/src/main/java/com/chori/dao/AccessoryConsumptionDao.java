package com.chori.dao;

import com.chori.AbstractDao;
import com.chori.entity.Accessoryconsumption;
import com.chori.entity.AccessoryconsumptionId;

public interface AccessoryConsumptionDao extends
		AbstractDao<Accessoryconsumption, AccessoryconsumptionId> {
	public boolean deleteAccessoryConsumptionByAccessoryCode(
			String accessoryCode);
	
	public Accessoryconsumption getAccessoryConsumptionByFactoryCodeAndAccessoryCode(
			String factoryCode, String accessoryCode);
}
