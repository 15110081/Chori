package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Piassigninternalaccessories;

@Repository("piassigninternalaccessoriesDao")
public class PiassigninternalaccessoriesDaoImpl extends
		AbstractDaoImpl<Piassigninternalaccessories, Integer> implements
		PiassigninternalaccessoriesDao {

	/**
	 * This function is used to get List PiAssignInternalAccessoriesByLotNumber by lotnumber
	 */
	@SuppressWarnings("unchecked")
	public List<Piassigninternalaccessories> getListPiAssignInternalAccessoriesByLotNumber(String lotnumber) {
		logger.debug(String
				.format("getListPiAssignInternalAccessoriesByLotNumber in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessories> results = (List<Piassigninternalaccessories>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassigninternalaccessories where pi.lotnumber = '"
									+ lotnumber + "'").list();
			logger.debug("getListPiAssignInternalAccessoriesByLotNumber successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPiAssignInternalAccessoriesByLotNumber in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to getListPiAssignInternalAccessoriesByAccessoryAndFactoryCode by factoryCode,accessorycode
	 */
	@SuppressWarnings("unchecked")
	public List<Piassigninternalaccessories> getListPiAssignInternalAccessoriesByAccessoryAndFactoryCode(String accessoryCode, String factoryCode) {
		logger.debug(String
				.format("getListPiAssignInternalAccessoriesByAccessoryAndFactoryCode in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessories> results = (List<Piassigninternalaccessories>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassigninternalaccessories where pi.factory.factorycode = '"
									+ factoryCode
									+ "' and accessory.accessorycode = '"
									+ accessoryCode + "'").list();
			logger.debug("getListPiAssignInternalAccessoriesByAccessoryAndFactoryCode successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPiAssignInternalAccessoriesByAccessoryAndFactoryCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to getListPiAssignInternalAccessoriesByAccessoryAndFactoryCode by lotNumber,accessorycode
	 */
	@SuppressWarnings("unchecked")
	public List<Piassigninternalaccessories> getListPiAssignInternalAccessoriesByAccessoryCodeAndLotNumber(String accessoryCode, String lotNumber) {
		logger.debug(String
				.format("getListPiAssignInternalAccessoriesByAccessoryCodeAndLotNumber in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessories> results = (List<Piassigninternalaccessories>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassigninternalaccessories where pi.lotnumber = '"
									+ lotNumber
									+ "' and accessory.accessorycode = '"
									+ accessoryCode + "'").list();
			logger.debug("getListPiAssignInternalAccessoriesByAccessoryCodeAndLotNumber successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPiAssignInternalAccessoriesByAccessoryCodeAndLotNumber in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to get List PiAssignInternalAccessoriesByLotNumber by AccessoryCode
	 */
	@SuppressWarnings("unchecked")
	public List<Piassigninternalaccessories> getListPiAssignInternalAccessoriesByAccessoryCode (String accessoryCode) {
		logger.debug(String
				.format("getListPiAssignInternalAccessoriesByAccessoryCode in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessories> results = (List<Piassigninternalaccessories>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassigninternalaccessories where accessory.accessorycode = '"
									+ accessoryCode + "'").list();
			logger.debug("getListPiAssignInternalAccessoriesByAccessoryCode successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPiAssignInternalAccessoriesByAccessoryCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
}
