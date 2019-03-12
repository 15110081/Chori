package com.chori.dao;

import com.chori.AbstractDao;
import com.chori.entity.Externalaccessorystock;

public interface ExternalAccessoryStockDao extends
		AbstractDao<Externalaccessorystock, String> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chori.dao.ExternalAccessStockDao#findById(int)
	 */
	public Externalaccessorystock findByordersheetno(String ordersheetno);
	public float getInventoryQuantity(String accessoryCode);
	public float getEstimateQuantity(String lotNumber,String accessoryCode);
	public float getActualAssignQuantity(String lotNumber,String accessoryCode);
	
}