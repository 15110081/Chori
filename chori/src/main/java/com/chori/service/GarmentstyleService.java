package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Garmentstyle;
import com.chori.model.GarmentstyleModel;

public interface GarmentstyleService extends
		AbstractService<Garmentstyle, String> {
	public List<GarmentstyleModel> getAllGarmentstyleModel();

	public boolean isGarmentStyleExistedById(String id);

	public boolean addNewGarmentstyle(GarmentstyleModel garmentstyleModel,
			String userName);

	public GarmentstyleModel findGarmentstyleModelById(String id);

	public boolean deleteGarmentstyle(String id);

	public boolean editGarmentstyle(GarmentstyleModel garmentstyleModel,
			String userName);

	public boolean copyGarmentstyle(GarmentstyleModel newGarmentstyleModel,
			String oldGarmentStyleCode, String userName);

	public boolean isHasGarmentStyleAccessoryDetail(String garmentStyleCode);
	
	public boolean deleteImageGarmentStyle(GarmentstyleModel garmentstyleModel,
			String userId);
}
