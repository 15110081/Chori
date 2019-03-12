package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Garmentconsumptiondetail;

public interface GarmentConsumptionDetailDao extends
		AbstractDao<Garmentconsumptiondetail, Integer> {
	
	List<Garmentconsumptiondetail> getAllGarmentConsumptionValueByWidthCodeGarmentconsumptionCode(
			String widthcode , Integer garmentconsumptioncode);

}
