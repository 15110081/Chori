package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Pi;

@Repository("pIDao")
public class PIDaoImpl extends AbstractDaoImpl<Pi, String> implements PIDao {

	/**
	 * This function is used to get PI By Lot Number
	 * @param lotNumber
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Pi> getPIByLotNumber(String lotNumber) {
		logger.debug(String.format(
				"getPIByLotNumber in class: %s", getClass()));
		try {
			List<Pi> results = (List<Pi>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Pi where lotnumber = '"
									+ lotNumber+ "'").list();
			logger.debug("getPIByLotNumber successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getPIByLotNumber in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to get PI By Lot Number
	 * @param garmentStyleName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Pi> getLotNumberByPIGridCode(Integer piGridCode) {
		logger.debug(String.format(
				"getLotNumberByPIGridCode in class: %s", getClass()));
		try {
			List<Pi> results = (List<Pi>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Pi where lotnumber = "
									+ piGridCode + "").list();
			logger.debug("getLotNumberByPIGridCode successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getLotNumberByPIGridCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
}
