package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Garmentconsumption;
import com.chori.entity.Garmentconsumptiondetail;

@Repository("garmentconsumptiondetailDao")
public class GarmentConsumptionDetailDaoImpl extends
		AbstractDaoImpl<Garmentconsumptiondetail, Integer> implements
		GarmentConsumptionDetailDao {
	
	/**
	 * This function is used to get all garment consumption value by widthcode garmentconsumptioncode 
	 * 
	 * @param widthcode
	 * @param garmentconsumptioncode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Garmentconsumptiondetail> getAllGarmentConsumptionValueByWidthCodeGarmentconsumptionCode(
			String widthcode , Integer garmentconsumptioncode) {
		logger.debug(String
				.format("getAllGarmentConsumptionValueByWidthCodeGarmentconsumptionCode in class: %s",
						getClass()));
		try {
		
			List<Garmentconsumptiondetail> results = (List<Garmentconsumptiondetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Garmentconsumptiondetail where width.widthcode = '" + widthcode 
							+ "' and garmentconsumption.garmentconsumptioncode = " + garmentconsumptioncode 
							+ "").list();
			logger.debug("getAllGarmentConsumptionValueByWidthCodeGarmentconsumptionCode successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getAllGarmentConsumptionValueByWidthCodeGarmentconsumptionCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}

}
