package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Fabricinformationdetail;
import com.chori.entity.FabricinformationdetailId;
import com.chori.entity.Fpidetail;
import com.chori.entity.Piassignfabric;
import com.chori.entity.Piassignfabricdetail;

@Repository("pIAssignFabricDetailDao")
public class PIAssignFabricDetailDaoImpl extends
		AbstractDaoImpl<Piassignfabricdetail, Integer> implements
		PIAssignFabricDetailDao {

	@Override
	public Fabricinformationdetail findById(FabricinformationdetailId fabricinformationdetailId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * This function is used to get all pi assign fabric detail code by lotNumber fabricNo	
	 * 
	 * @param lotNumber
	 * @param fabricNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Piassignfabricdetail> getAllPIAssignFabricDetailByLotNumberFabricNo(
			 String lotnumber , String fabricno) {
		logger.debug(String
				.format("getAllPIAssignFabricDetailByLotNumberFabricNo in class: %s",
						getClass()));
		try {
		
			List<Piassignfabricdetail> results = (List<Piassignfabricdetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassignfabricdetail where piassignfabric.id.lotnumber = '" + lotnumber 
							+ "' and piassignfabric.id.fabricno = '" + fabricno 
							+ "'").list();
			logger.debug("getAllPIAssignFabricDetailByLotNumberFabricNo successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getAllPIAssignFabricDetailByLotNumberFabricNo in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	
	/**
	 * This function is used to get all pi assign fabric detail code by  colorCode garmentstyleCode lotNumber fabricNo	
	 * 
	 * @param colorCode
	 * @param garmentstyleCode
	 * @param lotNumber
	 * @param fabricNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Piassignfabricdetail> getAllPIAssignFabricDetailCodeByColorGarmentstyleLotNumberFabricNo(
			String colorcode, String garmentstylecode , String lotnumber , String fabricno) {
		logger.debug(String
				.format("getAllPIAssignFabricDetailCodeByColorGarmentstyleLotNumberFabricNo in class: %s",
						getClass()));
		try {
		
			List<Piassignfabricdetail> results = (List<Piassignfabricdetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassignfabricdetail where color.colorcode = '" + colorcode 
							+ "' and garmentstyle.garmentstylecode = '" + garmentstylecode 
							+ "' and piassignfabric.id.lotnumber = '" + lotnumber 
							+ "' and piassignfabric.id.fabricno = '" + fabricno + "'").list();
			logger.debug("getAllPIAssignFabricDetailCodeByColorGarmentstyleLotNumberFabricNo successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getAllPIAssignFabricDetailCodeByColorGarmentstyleLotNumberFabricNo in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to get all pi assign fabric detail code by  colorCode lotNumber fabricNo	
	 * 
	 * @param colorCode
	 * @param lotNumber
	 * @param fabricNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Piassignfabricdetail> getAllPIAssignFabricDetailCodeByColorLotNumberFabricNo(
			String colorcode, String lotnumber , String fabricno) {
		logger.debug(String
				.format("getAllPIAssignFabricDetailCodeByColorLotNumberFabricNo in class: %s",
						getClass()));
		try {
		
			List<Piassignfabricdetail> results = (List<Piassignfabricdetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassignfabricdetail where color.colorcode = '" + colorcode 
							+ "' and piassignfabric.id.lotnumber = '" + lotnumber 
							+ "' and piassignfabric.id.fabricno = '" + fabricno + "'").list();
			logger.debug("getAllPIAssignFabricDetailCodeByColorLotNumberFabricNo successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getAllPIAssignFabricDetailCodeByColorLotNumberFabricNo in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to get all available quantity by  colorCode fabricNo
	 * 
	 * @param colorCode
	 * @param fabricNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Piassignfabricdetail> getAllAvailableQuantityByColorFabricNo(
			String colorcode, String fabricno) {
		logger.debug(String
				.format("getAllAvailableQuantityByColorFabricNo in class: %s",
						getClass()));
		try {
		
			List<Piassignfabricdetail> results = (List<Piassignfabricdetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassignfabricdetail where color.colorcode = '" + colorcode 							
							+ "' and piassignfabric.id.fabricno = '" + fabricno + "'").list();
			logger.debug("getAllAvailableQuantityByColorFabricNo successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getAllAvailableQuantityByColorFabricNo in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to get all pi assign fabric detail by lotNumber
	 * 
	 * @param lotNumber
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Piassignfabricdetail> getAllPIAssignFabricDetailByLotNumber(
			String lotNumber) {
		logger.debug(String
				.format("getAllPIAssignFabricDetailByLotNumber in class: %s",
						getClass()));
		try {
		
			List<Piassignfabricdetail> results = (List<Piassignfabricdetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassignfabricdetail where piassignfabric.id.lotnumber = '" + lotNumber 							
							 + "'").list();
			logger.debug("getAllPIAssignFabricDetailByLotNumber successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getAllPIAssignFabricDetailByLotNumber in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to get shipping status Yes in PI	
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Piassignfabricdetail> getShippingStatusEqualYes(
			 String lotnumber ) {
		logger.debug(String
				.format("getShippingStatusEqualYes in class: %s",
						getClass()));
		try {
		
			List<Piassignfabricdetail> results = (List<Piassignfabricdetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassignfabricdetail where piassignfabric.pi.lotnumber = '" + lotnumber 
							+ "' and piassignfabric.pi.shippingstatus = 'Yes'").list();
			logger.debug("getShippingStatusEqualYes successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getShippingStatusEqualYes in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}

}
