package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Accessoryconsumption;
import com.chori.entity.AccessoryconsumptionId;
import com.chori.model.AccessoryConsumptionModel;

public interface AccessoryConsumptionService extends
		AbstractService<Accessoryconsumption, AccessoryconsumptionId> {
	List<AccessoryConsumptionModel> getAllAccessoryConsumption();

	boolean deleteAccessoryConsumption(
			AccessoryconsumptionId accessoryconsumptionId);

	AccessoryConsumptionModel findAccessoryConsumptionModelById(
			AccessoryconsumptionId accessoryconsumptionId);

	boolean editAccessoryConsumption(
			AccessoryConsumptionModel accessoryConsumptionModel);

	boolean isAccessoryConsumptionExistedById(
			AccessoryconsumptionId accessoryconsumptionId);

	boolean addAccessoryConsumption(
			AccessoryConsumptionModel accessoryConsumptionModel, String userName);

	List<String> getAllFactoryHasConsumption();
	
	public AccessoryConsumptionModel findAccessoryConsumptionModelByFactoryCodeAndAccessoryCode(
			String factoryCode, String accessoryCode);
}