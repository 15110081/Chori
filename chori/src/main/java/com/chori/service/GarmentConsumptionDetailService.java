package com.chori.service;

import com.chori.AbstractService;
import com.chori.entity.Garmentconsumptiondetail;
import com.chori.model.GarmentConsumptionDetailModel;

public interface GarmentConsumptionDetailService extends
		AbstractService<Garmentconsumptiondetail, Integer> {
	boolean addNewGarmentConsumptionDetail(
			GarmentConsumptionDetailModel garconDetailModel, String userName);

	boolean editGarmentConsumptionDetail(
			GarmentConsumptionDetailModel garconDetailModel);

	boolean addNewGarmentConsumptionDetailByGarmentStyle(GarmentConsumptionDetailModel garconDetailModel,
			String userName);
}
