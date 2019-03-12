package com.chori.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Piassigninternalaccessoriesdetail;

@Repository("piassigninternalaccessoriesdetailDao")
public class PiassigninternalaccessoriesdetailDaoImpl extends
		AbstractDaoImpl<Piassigninternalaccessoriesdetail, Integer> implements
		PiassigninternalaccessoriesdetailDao {
	
	/**
	 * This function is used to getListPiassigninternalaccessoriesdetailByLotNumber by lotnumber
	 */
	@SuppressWarnings("unchecked")
	public List<Piassigninternalaccessoriesdetail> getListPiassigninternalaccessoriesdetailByLotNumber(String lotnumber) {
		logger.debug(String
				.format("getListPiassigninternalaccessoriesdetailByLotNumber in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessoriesdetail> results = (List<Piassigninternalaccessoriesdetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassigninternalaccessoriesdetail where piassigninternalaccessories.pi.lotnumber = '"
									+ lotnumber+ "'").list();
			
//			Query query = sessionFactory
//					.getCurrentSession().createSQLQuery(
//					"CALL GetListPiassigninternalaccessoriesdetailByLotNumber(:lotNumber)")
//					.addEntity(Piassigninternalaccessoriesdetail.class)
//					.setParameter("lotNumber", lotnumber);			
//			List<Piassigninternalaccessoriesdetail> results = query.list();
			
			logger.debug("getListPiassigninternalaccessoriesdetailByLotNumber successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPiassigninternalaccessoriesdetailByLotNumber in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to getListPiassigninternalaccessoriesdetailByPiAssignInternalAccessoriesId by lotnumber,accessorycode
	 */
	@SuppressWarnings("unchecked")
	public List<Piassigninternalaccessoriesdetail> getListPiassigninternalaccessoriesdetailByPiAssignInternalAccessoriesId(
			String lotnumber, String accessorycode) {
		logger.debug(String
				.format("getListPiassigninternalaccessoriesdetailByPiAssignInternalAccessoriesId in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessoriesdetail> results = (List<Piassigninternalaccessoriesdetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassigninternalaccessoriesdetail where piassigninternalaccessories.pi.lotnumber = '"
									+ lotnumber
									+ "' and piassigninternalaccessories.accessory.accessorycode = '"
									+ accessorycode + "'").list();
			logger.debug("getListPiassigninternalaccessoriesdetailByPiAssignInternalAccessoriesId successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPiassigninternalaccessoriesdetailByPiAssignInternalAccessoriesId in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to getListPiassigninternalaccessoriesdetailByPiassigninternalaccessories by Piassigninternalaccessories
	 */
	@SuppressWarnings("unchecked")
	public List<Piassigninternalaccessoriesdetail> getListPiassigninternalaccessoriesdetailByPiassigninternalaccessories(Integer Piassigninternalaccessories){
		logger.debug(String
				.format("getListPiassigninternalaccessoriesdetailByPiassigninternalaccessories in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessoriesdetail> results = (List<Piassigninternalaccessoriesdetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassigninternalaccessoriesdetail where piassigninternalaccessories.piinternalaccessories = '"
									+ Piassigninternalaccessories + "'").list();
			logger.debug("getListPiassigninternalaccessoriesdetailByPiassigninternalaccessories successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPiassigninternalaccessoriesdetailByPiassigninternalaccessories in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to getListPiassigninternalaccessoriesdetailByColorCodeAndGarmentStyleCode by lotnumber,accessorycode
	 */
	@SuppressWarnings("unchecked")
	public List<Piassigninternalaccessoriesdetail> getListPiassigninternalaccessoriesdetailByColorCodeAndGarmentStyleCode(
			String colorCode, String garmentstyleCode) {
		logger.debug(String
				.format("getListPiassigninternalaccessoriesdetailByColorCodeAndGarmentStyleCode in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessoriesdetail> results = (List<Piassigninternalaccessoriesdetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassigninternalaccessoriesdetail where color.colorcode = '"
									+ colorCode
									+ "' and garmentstyle.garmentstylecode = '"
									+ garmentstyleCode + "'").list();
			logger.debug("getListPiassigninternalaccessoriesdetailByColorCodeAndGarmentStyleCode successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPiassigninternalaccessoriesdetailByColorCodeAndGarmentStyleCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to getListPiassigninternalaccessoriesdetailByColorCodeGarmentStyleCodeSizeCodeAndPiassigninternalaccessories by colorCode,garmentstyleCode,sizeCode,Piassigninternalaccessories
	 */
	@SuppressWarnings("unchecked")
	public List<Piassigninternalaccessoriesdetail> getListPiassigninternalaccessoriesdetailByColorCodeGarmentStyleCodeSizeCodeAndPiassigninternalaccessories(
			String colorCode, String garmentstyleCode, Integer sizeCode, Integer Piassigninternalaccessories) {
		logger.debug(String
				.format("getListPiassigninternalaccessoriesdetailByColorCodeGarmentStyleCodeSizeCodeAndPiassigninternalaccessories in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessoriesdetail> results = (List<Piassigninternalaccessoriesdetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassigninternalaccessoriesdetail where color.colorcode = '"
									+ colorCode
									+ "' and garmentstyle.garmentstylecode = '"
									+ garmentstyleCode
									+ "' and size.sizecode = "
									+ sizeCode
									+ " and piassigninternalaccessories.piinternalaccessories = "
									+ Piassigninternalaccessories+ "").list();
			logger.debug("getListPiassigninternalaccessoriesdetailByColorCodeGarmentStyleCodeSizeCodeAndPiassigninternalaccessories successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPiassigninternalaccessoriesdetailByColorCodeGarmentStyleCodeSizeCodeAndPiassigninternalaccessories in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
}
