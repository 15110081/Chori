package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Packingguide;
import com.chori.model.PackingguideModel;

public interface PackingguideService extends AbstractService<Packingguide, String> {
	
	public boolean isPackkinguideExistedById(String pkgCode);
	public List<PackingguideModel> getAllPackingguide();
	
	public boolean addPackingguide(PackingguideModel packingguideModel,
			String username);
	
	public PackingguideModel findPackingguideModelById(String packingguicode);
	public boolean editPackingguide(PackingguideModel packingguideModel);
	public boolean deletePackingguide(PackingguideModel packingguideModel);

}
