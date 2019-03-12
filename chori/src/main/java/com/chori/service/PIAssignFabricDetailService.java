package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.FabricinformationdetailId;
import com.chori.entity.PiassignfabricId;
import com.chori.entity.Piassignfabricdetail;
import com.chori.model.PIAssignFabricDetailModel;
import com.chori.model.PiassignfabricModel;

public interface PIAssignFabricDetailService extends
		AbstractService<Piassignfabricdetail, Integer> {

	public PIAssignFabricDetailModel findPIAssignFabricDetailModelById(
			Integer id);

	public List<PIAssignFabricDetailModel> findPIAssignFabricDetailModelByFabricNo(
			String FabricNo);

	// public List<PIAssignFabricDetailModel>
	// getPIAssignFabricDetailByLotNo(String lotNo);
	List<PIAssignFabricDetailModel> getListFabricAssignmentQuantity(
			String fabricNo, String lotNo);
	
	Double getYardInBL(FabricinformationdetailId fabricinformationdetailId);

	Double getInventoryRemained(
			FabricinformationdetailId fabricinformationdetailId , PIAssignFabricDetailModel piAssignFabricDetailModel);

	Double getAvailableQty(
			FabricinformationdetailId fabricinformationdetailId);
	
	public Double getUsingValue(
			FabricinformationdetailId fabricinformationdetailId);
	
//	boolean addFabricAssignmentQuantityToPIAssignFabricDetail(
//			PIAssignFabricDetailModel piAssignFabricDetailModel);
	
	boolean addFabricAssignmentQuantityToPIAssignFabricDetail(
			PIAssignFabricDetailModel piAssignFabricDetailModel,String userName);
	
	boolean editFabricAssignmentQuantityToPIAssignFabricDetail(
			PIAssignFabricDetailModel piAssignFabricDetailModel);
	
//	boolean saveFabricAssignmentQuantityToPIAssignFabricDetail (
//			PIAssignFabricDetailModel piAssignFabricDetailModel);
	
	boolean saveFabricAssignmentQuantityToPIAssignFabricDetail (
			PIAssignFabricDetailModel piAssignFabricDetailModel, String userName);
	
	boolean isExistedByPIAssignFabricDetailCode(
			PIAssignFabricDetailModel piAssignFabricDetailModel);
	
	boolean deletePIAssignFabricDetailByLotNoAndFabricNo(
			String fabricNo , String lotNo);
	
	boolean deletePIAssignFabricByLotNoAndFabricNo(PiassignfabricModel
			piassignfabricModel);

	boolean deletePIAssignFabricDetailByLotNoFabricNoColorAndGarment(String lotNo,String fabricNo, String colorCode, String garmentStyleCode);
	
	List<PIAssignFabricDetailModel> getListPIAssignFabricDetail(String lotNumber);
	
	List<PiassignfabricId> getAllPiassignfabricIDByLotNumber(String lotNumber);
	
	List<Piassignfabricdetail> getPiAssignFabricDetail(PiassignfabricId piassignfabricId);

	boolean saveFAValue(String lotNumber);
}
