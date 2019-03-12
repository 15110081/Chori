package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Piassignexternalaccessory;

public interface PiassignexternalaccessoryDao extends
		AbstractDao<Piassignexternalaccessory, Integer> {
	public boolean isPiAssignedExternalAccessory(String lotNumber);

	public List<Piassignexternalaccessory> getListPiassignexternalaccessoryByLotNumber(
			String lotNumber);
	
	public List<Piassignexternalaccessory> getListPiassignexternalaccessoryByLotNumberAndAccessoryCode(
			String lotNumber, String accessoryCode);
}
