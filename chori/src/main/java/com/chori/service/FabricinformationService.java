package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Fabricinformation;
import com.chori.model.FabricinformationModel;
import com.chori.model.FabricinformationdetailModel;

public interface FabricinformationService extends
		AbstractService<Fabricinformation, String> {
	public List<FabricinformationModel> getAllFabricinformationModel();

	public boolean addNewFabricInformation(
			FabricinformationModel fim,
			List<FabricinformationdetailModel> listFabricinformationdetailModel,
			String creator);

	public boolean updateImageAfterAddNewFabricInformation(String fabricNo,
			String imgurl);

	public boolean isFabricInformationExistedByFabricNo(String fabricNo);

	public FabricinformationModel findFabricinformationModelById(String fabricNo);

	public List<FabricinformationdetailModel> getListFabricinformationdetailModelByFabricNo(
			String fabricNo);

	public boolean deleteFabInfo(String fabricNo);
	
	public boolean editFabricInformationJson(FabricinformationModel fabricInformationModel, List<FabricinformationdetailModel> listFabricinformationdetailModel, String editor);
}
