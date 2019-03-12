package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Garmentstyleaccessorydetail;

public interface GarmentstyleaccessorydetailDao extends
		AbstractDao<Garmentstyleaccessorydetail, Integer> {
	public List<Garmentstyleaccessorydetail> getListGarmentstyleaccessorydetailByGarmentStyleName(
			String garmentStyleName);

	public List<Garmentstyleaccessorydetail> getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName(
			String garmentStyleName, String accessoryName);

	public List<Garmentstyleaccessorydetail> getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName(
			String garmentStyleName, String accessoryGroupName);

	public List<Garmentstyleaccessorydetail> getListGarmentstyleaccessorydetailByGarmentStyleNameAndSize(
			String garmentStyleName, Integer sizecode);

	public List<Garmentstyleaccessorydetail> getListGarmentstyleaccessorydetailByGarmentStyleCodeAccessoryCodeAndSize(
			String garmentStyleName, String accessoryCode, Integer sizecode);
}
