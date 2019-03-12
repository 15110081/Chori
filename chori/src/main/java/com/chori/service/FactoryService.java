package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Factory;
import com.chori.model.FactoryModel;

public interface FactoryService extends AbstractService<Factory, String> {
	public List<FactoryModel> getAllFactoryModel();

	public boolean addNewFactory(FactoryModel fm, String userName);

	public FactoryModel findFactoryModelById(String id);

	public boolean editFactory(FactoryModel fm, String userName);

	public boolean delete(String id);

	public boolean isFactoryExistedById(String id);
}
