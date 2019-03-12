package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Fabricinformationdetail;
import com.chori.entity.FabricinformationdetailId;
import com.chori.model.FabricinformationdetailModel;;

public interface FabricinformationdetailService extends
		AbstractService<Fabricinformationdetail, FabricinformationdetailId> {
	Double getYardInBL(FabricinformationdetailId fabricinformationdetailId);

	Double getInventoryRemained(
			FabricinformationdetailId fabricinformationdetailId);

	Double getAvailableQty(FabricinformationdetailId fabricinformationdetailId);
	public List<FabricinformationdetailModel> getAllFabricinformationdetailModel();
}
