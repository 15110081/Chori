package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Accessorysupplier;
import com.chori.model.AccessorySupplierModel;

public interface AccessorySupplierService extends
		AbstractService<Accessorysupplier, String> {

	public List<AccessorySupplierModel> getAllAccessorySupplierModel();

	public boolean addNewAccSup(AccessorySupplierModel am, String userName);

	public AccessorySupplierModel findAccessorySupModelById(String id);

	public boolean editAccSup(AccessorySupplierModel am, String userName);

	public boolean delete(String id);

	public boolean isAccSupExistedById(String id);
}