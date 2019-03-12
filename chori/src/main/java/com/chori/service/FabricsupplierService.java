package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Fabricsupplier;
import com.chori.model.FabricsupplierModel;

public interface FabricsupplierService extends
		AbstractService<Fabricsupplier, String> {
	public List<FabricsupplierModel> getAllFabricsupplierModel();

	public boolean addNewFabricSupplier(FabricsupplierModel fm, String userName);

	public FabricsupplierModel findFabricsupplierModelById(String id);

	public boolean editFabricSupplier(FabricsupplierModel fsm, String userName);

	public boolean delete(String id);

	public boolean isFabricSupplierExistedById(String id);
}
