package com.chori.service;

import com.chori.AbstractService;
import com.chori.entity.Piassignfabric;
import com.chori.entity.PiassignfabricId;
import com.chori.model.PiassignfabricModel;

public interface PiassignfabricService extends
		AbstractService<Piassignfabric, PiassignfabricId> {

	PiassignfabricModel findPiassignfabricModelById(
			PiassignfabricId piassignfabricId);

	boolean AssignFabricToPi(PiassignfabricModel piassignfabricModel,
			String userName);

	boolean IsExistedPiAssignFabric(PiassignfabricId piassignfabricId);

}
