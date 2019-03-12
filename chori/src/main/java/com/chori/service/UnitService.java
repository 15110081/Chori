package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Unit;
import com.chori.model.UnitModel;

public interface UnitService extends AbstractService<Unit, String> {

	public List<UnitModel> getAllUnitModel();

	/**
	 * This function find a StatusEntity by id.
	 * 
	 * @param StatusCode
	 * @return a Status
	 */
	public UnitModel findUnitModelById(String unitcode);

	/**
	 * This function is used to edit Status into database
	 * 
	 * @param StatusEn
	 * @param statusCode
	 * @return true if edit successfully, false if have exception
	 */
	public boolean editUnit(UnitModel unitEn);

	/**
	 * This function is used to delete an order in database.
	 * 
	 * @param orderId
	 * @return delete successfully =>true , else => false
	 */
	public boolean deleteUnit(String unitCode);

	public boolean addUnit(UnitModel unitEn);

	public boolean isUnitExistedById(String unitCode);

}