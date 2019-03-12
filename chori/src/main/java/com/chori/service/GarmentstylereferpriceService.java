package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Garmentstylereferprice;
import com.chori.entity.GarmentstylereferpriceId;
import com.chori.model.GarmentstyleModel;
import com.chori.model.GarmentstylereferpriceModel;

public interface GarmentstylereferpriceService extends
AbstractService<Garmentstylereferprice, GarmentstylereferpriceId>{
	public boolean addGarmentstylereferpriceForGarment(GarmentstyleModel garmentstyleModel,
			List<GarmentstylereferpriceModel> lstGarmentstylereferpriceModel);
	
	public List<GarmentstylereferpriceModel> getListGarmentstylereferpriceModelByGarmentstyleCode(String garmentStyleCode);
	
	public boolean editGarmentstylereferpriceForGarment(GarmentstyleModel garmentstyleModel,
			List<GarmentstylereferpriceModel> lstGarmentstylereferpriceModel);
	
	public boolean deleteGarmentstylereferpriceByGarment(GarmentstyleModel garmentstyleModel);
}
