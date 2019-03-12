package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Accessorygroup;
import com.chori.model.AccessoryGroupModel;

public interface AccessoryGroupService extends
		AbstractService<Accessorygroup, String> {

	/**
	 * This function check if there is an accessorygroup with acgrCode existed
	 * in database
	 * 
	 * @param acgrCode
	 * @return false if not existed, true if existed
	 */
	public boolean isAccessoryGroupExistedById(String acgrCode);

	/**
	 * This function is used get all AccessoryGroup
	 * 
	 * @return list<AccessoryGroupModel>
	 */
	public List<AccessoryGroupModel> getAllAccessoryGroup();

	/**
	 * This function is used to add new AccessoryGroup into database
	 * 
	 * @param accessoryGroupModel
	 * @return true if add successfully, false if have exception
	 */
	public boolean addAccessoryGroup(AccessoryGroupModel accessoryGroupModel,
			String username);

	/**
	 * This function find a AccessoryGroup by accessoryGroupCode.
	 * 
	 * @param accessoryGroupCode
	 * @return a AccessoryGroupModel
	 */
	public AccessoryGroupModel findAccessoryGroupById(String accessoryGroupCode);

	/**
	 * This function is used to edit accessoryGroup into database
	 * 
	 * @param AccessoryGroupModel
	 * @param userId
	 * @return true if edit successfully, false if have exception
	 */
	public boolean editAccessoryGroup(AccessoryGroupModel accessoryGroupModel);

	/**
	 * This function is used to delete an Accessorygroup in database.
	 * 
	 * @param accessoryGroupCode
	 * @return delete successfully =>true , else => false
	 */
	public boolean deleteAccessoryGroup(String accessoryGroupCode);

}