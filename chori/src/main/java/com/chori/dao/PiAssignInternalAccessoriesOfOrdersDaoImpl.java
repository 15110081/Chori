package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Piassigninternalaccessoriesdetail;
import com.chori.entity.Piassigninternalaccessoriesoforders;

@Repository("piAssignInternalAccessoriesOfOrdersDao")
public class PiAssignInternalAccessoriesOfOrdersDaoImpl extends AbstractDaoImpl<Piassigninternalaccessoriesoforders, Integer> implements PiAssignInternalAccessoriesOfOrdersDao{

	/**
	 * This function is used to getListPiassigninternalaccessoriesofordersByPiassigninternalaccessories by Piassigninternalaccessories
	 */
	@SuppressWarnings("unchecked")
	public List<Piassigninternalaccessoriesoforders> getListPiassigninternalaccessoriesofordersByPiassigninternalaccessories(Integer Piassigninternalaccessories){
		logger.debug(String
				.format("getListPiassigninternalaccessoriesofordersByPiassigninternalaccessories in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessoriesoforders> results = (List<Piassigninternalaccessoriesoforders>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassigninternalaccessoriesoforders where piassigninternalaccessories.piinternalaccessories = '"
									+ Piassigninternalaccessories + "'").list();
			logger.debug("getListPiassigninternalaccessoriesofordersByPiassigninternalaccessories successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPiassigninternalaccessoriesofordersByPiassigninternalaccessories in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to getListPiassigninternalaccessoriesofordersByOrderSheetNoAndAccessoryCode by orderSheetNo,accessorycode
	 */
	@SuppressWarnings("unchecked")
	public List<Piassigninternalaccessoriesoforders> getListPiassigninternalaccessoriesofordersByOrderSheetNoAndAccessoryCode(
			String orderSheetNo, String accessoryCode) {
		logger.debug(String
				.format("getListPiassigninternalaccessoriesofordersByOrderSheetNoAndAccessoryCode in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessoriesoforders> results = (List<Piassigninternalaccessoriesoforders>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassigninternalaccessoriesoforders where orderinternalaccessory.ordersheetno = '"
									+ orderSheetNo
									+ "' and piassigninternalaccessories.accessory.accessorycode = '"
									+ accessoryCode + "'").list();
			logger.debug("getListPiassigninternalaccessoriesofordersByOrderSheetNoAndAccessoryCode successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPiassigninternalaccessoriesofordersByOrderSheetNoAndAccessoryCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to getListPiassigninternalaccessoriesofordersByOrderSheetNoAndAccessoryCodeAndLotnumber by orderSheetNo,accessorycode,lotnumber
	 */
	@SuppressWarnings("unchecked")
	public List<Piassigninternalaccessoriesoforders> getListPiassigninternalaccessoriesofordersByOrderSheetNoAndAccessoryCodeAndLotnumber(
			String orderSheetNo, String accessoryCode, String lotNumber) {
		logger.debug(String
				.format("getListPiassigninternalaccessoriesofordersByOrderSheetNoAndAccessoryCodeAndLotnumber in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessoriesoforders> results = (List<Piassigninternalaccessoriesoforders>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassigninternalaccessoriesoforders where orderinternalaccessory.ordersheetno = '"
									+ orderSheetNo
									+ "' and piassigninternalaccessories.pi.lotnumber = '"
									+ lotNumber
									+ "' and piassigninternalaccessories.accessory.accessorycode = '"
									+ accessoryCode + "'").list();
			logger.debug("getListPiassigninternalaccessoriesofordersByOrderSheetNoAndAccessoryCodeAndLotnumber successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPiassigninternalaccessoriesofordersByOrderSheetNoAndAccessoryCodeAndLotnumber in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to getListPiassigninternalaccessoriesofordersByOrderSheetNo by orderSheetNo
	 */
	@SuppressWarnings("unchecked")
	public List<Piassigninternalaccessoriesoforders> getListPiassigninternalaccessoriesofordersByOrderSheetNo(
			String orderSheetNo) {
		logger.debug(String
				.format("getListPiassigninternalaccessoriesofordersByOrderSheetNo in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessoriesoforders> results = (List<Piassigninternalaccessoriesoforders>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassigninternalaccessoriesoforders where orderinternalaccessory.ordersheetno = '"
									+ orderSheetNo + "'").list();
			logger.debug("getListPiassigninternalaccessoriesofordersByOrderSheetNo successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPiassigninternalaccessoriesofordersByOrderSheetNo in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to getListPiassigninternalaccessoriesofordersByLotNumber by lotNumber
	 */
	@SuppressWarnings("unchecked")
	public List<Piassigninternalaccessoriesoforders> getListPiassigninternalaccessoriesofordersByLotNumber(
			String lotNumber) {
		logger.debug(String
				.format("getListPiassigninternalaccessoriesofordersByLotNumber in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessoriesoforders> results = (List<Piassigninternalaccessoriesoforders>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassigninternalaccessoriesoforders where piassigninternalaccessories.pi.lotnumber = '"
									+ lotNumber + "'").list();
			logger.debug("getListPiassigninternalaccessoriesofordersByLotNumber successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPiassigninternalaccessoriesofordersByLotNumber in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to getListPiassigninternalaccessoriesofordersByOrderSheetNoAndPiassigninternalaccessories by orderSheetNo,piAssignInternalAccessories
	 */
	@SuppressWarnings("unchecked")
	public List<Piassigninternalaccessoriesoforders> getListPiassigninternalaccessoriesofordersByOrderSheetNoAndPiassigninternalaccessories(
			String orderSheetNo, Integer piAssignInternalAccessories) {
		logger.debug(String
				.format("getListPiassigninternalaccessoriesofordersByOrderSheetNoAndPiassigninternalaccessories in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessoriesoforders> results = (List<Piassigninternalaccessoriesoforders>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassigninternalaccessoriesoforders where orderinternalaccessory.ordersheetno = '"
									+ orderSheetNo
									+ "' and piassigninternalaccessories.piinternalaccessories = "
									+ piAssignInternalAccessories + "").list();
			logger.debug("getListPiassigninternalaccessoriesofordersByOrderSheetNoAndPiassigninternalaccessories successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPiassigninternalaccessoriesofordersByOrderSheetNoAndPiassigninternalaccessories in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
}
