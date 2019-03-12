package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Pigriddetail;
import com.chori.model.CalculateFAValueModel;
import com.chori.model.PiGridDetailModel;

public interface PigriddetailService extends
		AbstractService<Pigriddetail, Integer> {
	public List<PiGridDetailModel> getAllPiGridDetailModel();

	public List<PiGridDetailModel> getListPiGridDetailModelByLotNo(
			String lotNumber);
	Integer calculateFAValue(CalculateFAValueModel calculatefavalueModel);

	Integer findPIGridCodeByLotNo(String lotNo);

	Double getTotalPcs(String lotNo, String garmentstyleCode, String colorCode);

	Float convertPcsToPercent(String lotNo, String garmentstyleCode, String colorCode, Integer sizeCode);

	Integer findGarmentconsumptionCode(String garmentstyleCode, String customerCode, Integer sizeCode);

	Float getConsumptionValue(String garmentstyleCode, String customerCode, Integer sizeCode, String fabricNo);

	Integer getAssignQtyByLotnoGarmentstyleCodeColorCodeFabricNo(String lotNo, String colorCode,
			String garmentstyleCode, String fabricNo);
	public List<PiGridDetailModel> getListPiGridDetail(String lotNumber);
	
	public boolean isAssignFabricYet(String lotnumber);
	
	public List<PiGridDetailModel> getListPiGridDetailNotAssignFabric(String lotNumber);
	public boolean addPigriddetail(PiGridDetailModel pgdm);
	public boolean editPigriddetail(PiGridDetailModel pgdm);
	public PiGridDetailModel findPiGridDetailModelById(int pigriddetailCode);
	public boolean deleteAllbyPigridCode(int pigridCode);
	public boolean deletePigriddetail(int pigriddetailCode);
	public List<PiGridDetailModel> getAllPiGridDetailModelbyPigridCode(int pigridcode);
}
