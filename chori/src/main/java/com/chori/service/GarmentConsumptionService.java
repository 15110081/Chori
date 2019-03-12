package com.chori.service;

import java.util.List;

import com.chori.entity.Garmentconsumption;
import com.chori.model.GarmentConsumptionModel;
import com.chori.model.SizeModel;
import com.chori.model.WidthModel;

public interface GarmentConsumptionService {
	List<GarmentConsumptionModel> getAllGarmentConsumptionModel();

	// boolean addNewGarmentConsumption(GarmentConsumptionModel garconModel,
	// String userName);
	GarmentConsumptionModel findGarmentConsumptionModelById(Integer id);

	boolean editGarmentConsumption(GarmentConsumptionModel garconModel);

	boolean delete(Integer id);

	boolean addNewGarmentConsumption2(GarmentConsumptionModel garconModel,
			String userName);

	boolean isGarmentConsumptionExisted(GarmentConsumptionModel garconModel);
	
	boolean isGarmentConsumptionExisted(Garmentconsumption garconParam);

	String getTypeByCustomerAndKindAndSize(String customercode,
			String garmentkindcode, String sizecode);

	boolean isExistedWidthData();

	List<SizeModel> getSizeByCustomerAndGarmentStyle(String customercode, String garmentstylecode);

	boolean addNewGarmentConsumptionByGarmentStyle(GarmentConsumptionModel garconModel, String userName);

	List<Integer> getAllGarmentConCodeByCustomerAndGarmentStyle(GarmentConsumptionModel garconModel);

	List<WidthModel> getAllWidth();
	
	
	
}
