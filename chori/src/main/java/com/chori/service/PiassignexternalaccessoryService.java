package com.chori.service;

import java.util.ArrayList;
import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Piassignexternalaccessory;
import com.chori.model.PiassignexternalaccessoryModel;

public interface PiassignexternalaccessoryService extends
		AbstractService<Piassignexternalaccessory, Integer> {
	public List<PiassignexternalaccessoryModel> getListPiassignexternalaccessoryModelWhenPressAssign(
			String lotnumber);

	public boolean add1stTimePiAssignExternalAccessory(
			ArrayList<PiassignexternalaccessoryModel> lstNotAssign,
			String creator);

	public boolean isPiAssignedExternalAccessory(String lotNumber);

	public List<PiassignexternalaccessoryModel> getListPiassignexternalaccessoryByLotNumber(
			String lotNumber);

	public List<PiassignexternalaccessoryModel> getListPiassignexternalaccessoryForEditByLotNumber(
			String lotNumber);

	public boolean editPiAssignExternalAccessory(
			ArrayList<PiassignexternalaccessoryModel> lstAssignOrNot,
			String modifier);
	
	public List<PiassignexternalaccessoryModel> getListPiassignexternalaccessoryByLotNumberAndAccessoryCode(
			String lotNumber, String accessoryCode);
	
	public boolean checkIfPiAssignedExternalAccessoryAlreadyHaveSpecificConsumption(String lotNumber, String accessoryCode);
	
	public Float getPiAssignedExternalAccessorySpecificConsumption(String lotNumber, String accessoryCode);
	
	public boolean editWastedPercentage(String lotNumber, String accessoryCode, Float wastedPercentage);
}
