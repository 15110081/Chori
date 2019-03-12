package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Accessory;
import com.chori.model.AccessoryAddModel;
import com.chori.model.AccessoryModel;

public interface AccessoryService extends AbstractService<Accessory, String> {
	List<AccessoryModel> getAllAccessory();

	public boolean deleteAccessory(String id);

	public boolean addNewAccessory(AccessoryAddModel accessoryAddModel,
			String userId);

	public AccessoryModel findAccessoryModelById(String id);

	public boolean editAccessory(AccessoryAddModel accessoryAddModel,
			String userId);

	public boolean isAccessoryExistedById(String id);
	
	public boolean deleteImageAccessory(AccessoryAddModel accessoryAddModel,
			String userId);
}
