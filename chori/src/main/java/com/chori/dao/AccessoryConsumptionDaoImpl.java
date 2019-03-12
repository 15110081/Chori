package com.chori.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Accessoryconsumption;
import com.chori.entity.AccessoryconsumptionId;

@Repository("accessoryconsumptionDao")
public class AccessoryConsumptionDaoImpl extends
		AbstractDaoImpl<Accessoryconsumption, AccessoryconsumptionId> implements
		AccessoryConsumptionDao {
	/**
	 * This function is used to delete Accessory Consumption By Accessory Code
	 * @param accessoryCode
	 * @return
	 */
	public boolean deleteAccessoryConsumptionByAccessoryCode(
			String accessoryCode) {
		logger.debug(String
				.format("getListGarmentstyleaccessorydetailByGarmentStyleName in class: %s",
						getClass()));
		try {
			Query q = sessionFactory
					.getCurrentSession()
					.createQuery(
							"delete Accessoryconsumption where accessory.accessorycode = '"
									+ accessoryCode + "'");
			if(q.executeUpdate()>0)
				return true;
			else
				return false;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListGarmentstyleaccessorydetailByGarmentStyleName in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to get 1 AccessoryConsumption By FactoryCode And AccessoryCode
	 * @param factoryCode
	 * @param accessoryCode
	 * @return
	 */
	public Accessoryconsumption getAccessoryConsumptionByFactoryCodeAndAccessoryCode(
			String factoryCode, String accessoryCode) {
		logger.debug(String
				.format("getAccessoryConsumptionByFactoryCodeAndAccessoryCode in class: %s",
						getClass()));
		try {
			Accessoryconsumption accessoryconsumption= (Accessoryconsumption) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Accessoryconsumption where factory.factorycode = '"
									+ factoryCode + "' and accessory.accessorycode = '" + accessoryCode+"'").uniqueResult();
				return accessoryconsumption;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getAccessoryConsumptionByFactoryCodeAndAccessoryCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
}
