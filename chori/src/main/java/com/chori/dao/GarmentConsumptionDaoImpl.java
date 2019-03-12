package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Garmentconsumption;
import com.chori.entity.Piassignfabricdetail;

@Repository("garmentconsumptionDao")
public class GarmentConsumptionDaoImpl extends
		AbstractDaoImpl<Garmentconsumption, Integer> implements GarmentConsumptionDao {
	
	/**
	 * This function is used to get all garment consumption code by  garmentstylecode customercode sizecode	
	 * 
	 * @param garmentstylecode
	 * @param customercode
	 * @param sizecode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Garmentconsumption> getAllGarmentConsumptionCodeByGarmentstyleCodeCustomerCodeSizeCode(
			String garmentstylecode , String customercode , Integer sizecode) {
		logger.debug(String
				.format("getAllGarmentConsumptionCodeByGarmentstyleCodeCustomerCodeSizeCode in class: %s",
						getClass()));
		try {
		
			List<Garmentconsumption> results = (List<Garmentconsumption>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Garmentconsumption where garmentstyle.garmentstylecode = '" + garmentstylecode 
							+ "' and customer.customercode = '" + customercode 
							+ "' and size.sizecode = " + sizecode 
							+ "").list();
			logger.debug("getAllGarmentConsumptionCodeByGarmentstyleCodeCustomerCodeSizeCode successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getAllGarmentConsumptionCodeByGarmentstyleCodeCustomerCodeSizeCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
}
