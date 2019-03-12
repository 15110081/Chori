package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Fabricinformation;
import com.chori.model.FabricinformationModel;

public interface FabricAssignmentChecklistService extends
		AbstractService<Fabricinformation, String> {
	public List<FabricinformationModel> getAllFabricinformationModelByCustomerandFactory(String customerCode , String factoryCode);

//	public FabricinformationModel findFabricinformationModelByFabricNo(
//			String fabricno);
}
