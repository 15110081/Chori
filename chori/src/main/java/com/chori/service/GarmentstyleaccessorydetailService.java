package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Garmentstyleaccessorydetail;
import com.chori.model.AccessoryGroupModel;
import com.chori.model.AccessoryModel;
import com.chori.model.GarmentstyleaccessorydetailModel;
import com.chori.model.SizeModel;

public interface GarmentstyleaccessorydetailService extends
		AbstractService<Garmentstyleaccessorydetail, Integer> {
	public List<AccessoryModel> getListAccessoryForGarmentStyle(
			String garmentStyleName);

	// public List<SizeModel> getListSizeByGarmentAccessoryCustomer(String
	// garmentCode, String accessoryCode, String customerCode);
	public List<SizeModel> getListSizeByGarmentAccessoryCustomer(
			String garmentCode, String accessoryCode, String customerCode,
			String garmentKindCode);

	public List<GarmentstyleaccessorydetailModel> getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName(
			String garmentStyleName, String accessoryName);

	public List<GarmentstyleaccessorydetailModel> getListGarmentstyleaccessorydetailByGarmentStyleName(
			String garmentStyleName);

	public boolean addNewGarmentstyleaccessorydetail(
			GarmentstyleaccessorydetailModel model, String userName);

	public boolean isAllSizeAlreadyAssigned(String garmentCode,
			String accessoryCode, String customerCode, String garmentKindCode);

	public boolean editGarmentstyleAccessoryDetail(
			GarmentstyleaccessorydetailModel model, String userName);

	public boolean deleteGSAD(Integer id);

	public List<AccessoryGroupModel> getAccessoryGroupByGarmentStyleCode(
			String garmentStyleCode);

	public List<GarmentstyleaccessorydetailModel> getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName(
			String garmentStyleName, String accessoryGroupName);
}
