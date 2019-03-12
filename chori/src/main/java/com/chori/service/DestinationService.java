package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Destination;
import com.chori.model.DestinationModel;

public interface DestinationService extends
		AbstractService<Destination, String> {

	List<DestinationModel> getAllDestination();

	boolean addDestination(DestinationModel destinationMo, String username);

	DestinationModel findDestinationModelById(String destinationcode);

	boolean editDestiantion(DestinationModel destinationMo);

	boolean isDestinationExistedById(String destinationcode);

	boolean deleteDestination(DestinationModel destinationModel);

}
