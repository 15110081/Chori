package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Fabricinformationdetail;
import com.chori.entity.FabricinformationdetailId;
import com.chori.entity.Piassignfabricdetail;

public interface PIAssignFabricDetailDao extends
		AbstractDao<Piassignfabricdetail, Integer> {

	Fabricinformationdetail findById(FabricinformationdetailId fabricinformationdetailId);
	
	List<Piassignfabricdetail> getAllPIAssignFabricDetailByLotNumberFabricNo(
			 String lotnumber , String fabricno);

	List<Piassignfabricdetail> getAllPIAssignFabricDetailCodeByColorGarmentstyleLotNumberFabricNo(
			String colorcode, String garmentstylecode , String lotnumber , String fabricno);
	
	List<Piassignfabricdetail> getAllPIAssignFabricDetailCodeByColorLotNumberFabricNo(
			String colorcode, String lotnumber , String fabricno);
	
	List<Piassignfabricdetail> getAllAvailableQuantityByColorFabricNo(
			String colorcode, String fabricno);
	
	List<Piassignfabricdetail> getAllPIAssignFabricDetailByLotNumber(
			String lotNumber);
	
	List<Piassignfabricdetail> getShippingStatusEqualYes(
			 String lotnumber );
}
