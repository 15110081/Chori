package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Piassigninternalaccessories;
import com.chori.model.InternalAccessoriesToAssignModel;
import com.chori.model.PiAssignInternalAccessories_OrderChoseModel;
import com.chori.model.PiassigninternalaccessoriesModel;
import com.chori.model.PiassigninternalaccessoriesdetailModel;

public interface PiassigninternalaccessoriesService extends
		AbstractService<Piassigninternalaccessories, Integer> {
//  List<InternalAccessoriesToAssignModel> getAllInternalAccessory();
//	List<InternalAccessoriesToAssignModel> getAllInternalAccessory(
//			String lotnumber);

//	List<InternalAccessoriesToAssignModel> getAllInternalAccessoryOfPI(
//			String lotnumber);
	List<InternalAccessoriesToAssignModel> getAllInternalAccessory(
			String lotnumber,String factoryCode);
	
	List<InternalAccessoriesToAssignModel> getAllInternalAccessoryOfPI(
			String lotNumber, String factoryCode);

	boolean addPiassigninternalaccessories(
			PiassigninternalaccessoriesModel piassigninternalaccessoriesModel,
			String userName);

	List<PiassigninternalaccessoriesdetailModel> getPiassigninternalaccessoriesdetailModelByPiInternalAccessoryCode(
			Integer piInternalAccessories);

	boolean isPiassigninternalaccessoriesExisted(
			PiassigninternalaccessoriesModel piassigninternalaccessoriesModel);

	boolean deletePiAssignInternalAccessoriesById(
			Integer piAssignInternalAccessoriesId);

	Integer findPiAssignInternalAccessoriesIdByAccessoryCodeAndLotNumber(
			PiassigninternalaccessoriesModel piassigninternalaccessoriesModel);

	Integer findPiAssignInternalAccessoriesIdByAccessoryCodeAndLotNumber2(
			String lotnumber, String accessorycode);

	List<PiassigninternalaccessoriesdetailModel> findPiAssignInternalAccessoriesModelDetailByLotNumberAndAccessoryCode(
			String lotnumber, String accessorycode);

	List<PiassigninternalaccessoriesdetailModel> findPiAssignInternalAccessoriesDetailModel(
			String lotnumber);

	Integer findPiGridCodeOfAPi(String lotnumber);
	
	List<PiAssignInternalAccessories_OrderChoseModel> getOrderInternalListForAssign(String accessoryCode, String lotNo);
	
	List<PiAssignInternalAccessories_OrderChoseModel> getOrderInternalAccIsAssignedForAPI(String accessoryCode, String lotNo);
	
	Double getAvailableQuantityChooseFormOrder(String accessoryCode, String lotNo);
	
	Double GetAssignQuantity(Integer Piassigninternalaccessoriesdetail);

	String getSizeNameBySizeCode(Integer sizeCode);

	Double GetAssignQuantityInPIAssignIntAccOfOrders(Integer Piassigninternalaccessories);
}
