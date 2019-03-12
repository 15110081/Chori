package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Piassignexternalaccessory;

@Repository("piassignexternalaccessoryDao")
public class PiassignexternalaccessoryDaoImpl extends
		AbstractDaoImpl<Piassignexternalaccessory, Integer> implements
		PiassignexternalaccessoryDao {

	/**
	 * This function is used to check if a PI has already assigned external
	 * accessory
	 */
	public boolean isPiAssignedExternalAccessory(String lotNumber) {
		logger.debug(String.format(
				"isPiAssignedExternalAccessory in class: %s", getClass()));
		try {
			int count = ((Long) getSession().createQuery(
					"select count(*) from Piassignexternalaccessory where pi.lotnumber = '"
							+ lotNumber + "'").uniqueResult()).intValue();
			logger.debug("isPiAssignedExternalAccessory successfull!" + count);

			if (count > 0)
				return true;
			return false;
		} catch (RuntimeException re) {
			logger.error(String.format(
					"isPiAssignedExternalAccessory in class %s has error: %s",
					getClass(), re.getMessage()));
			throw re;
		}
	}

	/**
	 * This function is used to get list Pi assign external accessory by Lot
	 * Number
	 * 
	 * @param lotNumber
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Piassignexternalaccessory> getListPiassignexternalaccessoryByLotNumber(
			String lotNumber) {
		logger.debug(String.format(
				"getListPiassignexternalaccessoryByLotNumber in class: %s",
				getClass()));
		try {
			List<Piassignexternalaccessory> results = (List<Piassignexternalaccessory>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassignexternalaccessory where pi.lotnumber = '"
									+ lotNumber + "'").list();
			logger.debug("getListPiassignexternalaccessoryByLotNumber successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPiassignexternalaccessoryByLotNumber in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to get List Piassignexternalaccessory By LotNumber And AccessoryCode
	 * @param lotNumber
	 * @param accessoryCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Piassignexternalaccessory> getListPiassignexternalaccessoryByLotNumberAndAccessoryCode(
			String lotNumber, String accessoryCode) {
		logger.debug(String.format(
				"getListPiassignexternalaccessoryByLotNumberAndAccessoryCode in class: %s",
				getClass()));
		try {
			List<Piassignexternalaccessory> results = (List<Piassignexternalaccessory>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassignexternalaccessory where pi.lotnumber = '"
									+ lotNumber + "' and accessory.accessorycode = '" + accessoryCode+"'").list();
			logger.debug("getListPiassignexternalaccessoryByLotNumberAndAccessoryCode successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPiassignexternalaccessoryByLotNumberAndAccessoryCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
}
