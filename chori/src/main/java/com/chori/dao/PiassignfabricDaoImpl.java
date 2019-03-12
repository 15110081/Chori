package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Piassignfabric;
import com.chori.entity.PiassignfabricId;
import com.chori.entity.Piassignfabricdetail;

@Repository("piassignfabricDao")
public class PiassignfabricDaoImpl extends
		AbstractDaoImpl<Piassignfabric, PiassignfabricId> implements
		PiassignfabricDao {

	/**
	 * This function is used to get all pi assign fabric  by lotNumber
	 * 
	 * @param lotNumber
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Piassignfabric> getAllPIAssignFabricByLotNumber(
			String lotnumber) {
		logger.debug(String
				.format("getAllPIAssignFabricByLotNumber in class: %s",
						getClass()));
		try {
		
			List<Piassignfabric> results = (List<Piassignfabric>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassignfabric where id.lotnumber = '" + lotnumber 							
							 + "'").list();
			logger.debug("getAllPIAssignFabricByLotNumber successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getAllPIAssignFabricByLotNumber in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to get all pi assign fabric  by fabricno
	 * 
	 * @param fabricno
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Piassignfabric> getAllPIAssignFabricByFabricNo(
			String fabricno) {
		logger.debug(String
				.format("getAllPIAssignFabricByLotNumber in class: %s",
						getClass()));
		try {
		
			List<Piassignfabric> results = (List<Piassignfabric>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassignfabric where id.fabricno = '" + fabricno 							
							 + "'").list();
			logger.debug("getAllPIAssignFabricByLotNumber successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getAllPIAssignFabricByLotNumber in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to get all pi assign fabric  by lotnumber fabricno
	 * 
	 * @param lotnumber
	 * @param fabricno
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Piassignfabric> getAllPIAssignFabricByLotNumberFabricNo(
			String lotnumber , String fabricno) {
		logger.debug(String
				.format("getAllPIAssignFabricByLotNumberFabricNo in class: %s",
						getClass()));
		try {
		
			List<Piassignfabric> results = (List<Piassignfabric>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassignfabric where id.lotnumber = '" + lotnumber 	
							+ "' and id.fabricno = '" + fabricno
							 + "'").list();
			logger.debug("getAllPIAssignFabricByLotNumberFabricNo successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getAllPIAssignFabricByLotNumberFabricNo in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
}
