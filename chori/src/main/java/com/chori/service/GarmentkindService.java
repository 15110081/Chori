package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Garmentkind;
import com.chori.model.GarmentkindModel;

public interface GarmentkindService extends
		AbstractService<Garmentkind, String> {

	boolean isGarmentkindExistedById(String gmkCode);

	List<GarmentkindModel> getAllGarmentkind();

	boolean addGarmentkind(GarmentkindModel garmentkindMo, String username);

	GarmentkindModel findGarmentkindModelById(String garmentkindcode);

	boolean editGarmentkind(GarmentkindModel garmentkindModel);

	boolean deleteGarmentkind(String garmentkindcode);
}
