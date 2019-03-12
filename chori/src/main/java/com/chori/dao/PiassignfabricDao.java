package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Piassignfabric;
import com.chori.entity.PiassignfabricId;

public interface PiassignfabricDao extends
		AbstractDao<Piassignfabric, PiassignfabricId> {

	List<Piassignfabric> getAllPIAssignFabricByLotNumber(
			String lotnumber);
	
	List<Piassignfabric> getAllPIAssignFabricByFabricNo(
			String fabricno);
	
	List<Piassignfabric> getAllPIAssignFabricByLotNumberFabricNo(
			String lotnumber , String fabricno);
	
}
