package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Garmentstyleaccessorydetail;

@Repository("garmentstyleaccessorydetailDao")
public class GarmentstyleaccessorydetailDaoImpl extends
		AbstractDaoImpl<Garmentstyleaccessorydetail, Integer> implements
		GarmentstyleaccessorydetailDao {

	/**
	 * This function is used to get list garmentStyleAccessoryDetail by Garment
	 * style Name
	 */
	@SuppressWarnings("unchecked")
	public List<Garmentstyleaccessorydetail> getListGarmentstyleaccessorydetailByGarmentStyleName(
			String garmentStyleName) {
		logger.debug(String
				.format("getListGarmentstyleaccessorydetailByGarmentStyleName in class: %s",
						getClass()));
		try {
			List<Garmentstyleaccessorydetail> results = (List<Garmentstyleaccessorydetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Garmentstyleaccessorydetail where garmentstyle.garmentstylecode = '"
									+ garmentStyleName + "'").list();
			logger.debug("getListGarmentstyleaccessorydetailByGarmentStyleName successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListGarmentstyleaccessorydetailByGarmentStyleName in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}

	/**
	 * This function is used to get list garmentStyleAccessoryDetail by Garment
	 * Style Name and accessoryName
	 * 
	 * @param garmentStyleName
	 * @param accessoryName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Garmentstyleaccessorydetail> getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName(
			String garmentStyleName, String accessoryName) {
		logger.debug(String
				.format("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName in class: %s",
						getClass()));
		try {
			List<Garmentstyleaccessorydetail> results = (List<Garmentstyleaccessorydetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Garmentstyleaccessorydetail where garmentstyle.garmentstylecode = '"
									+ garmentStyleName
									+ "' and accessory.accessorycode = '"
									+ accessoryName + "'").list();
			logger.debug("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}

	/**
	 * This function is used to get list Garmentstyleaccessorydetail By
	 * GarmentStyleName And AccessoryGroupName
	 * 
	 * @param garmentStyleName
	 * @param accessoryGroupName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Garmentstyleaccessorydetail> getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName(
			String garmentStyleName, String accessoryGroupName) {
		logger.debug(String
				.format("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName in class: %s",
						getClass()));
		try {
			List<Garmentstyleaccessorydetail> results = (List<Garmentstyleaccessorydetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Garmentstyleaccessorydetail where garmentstyle.garmentstylecode = '"
									+ garmentStyleName
									+ "' and accessory.accessorygroup.accessorygroupcode = '"
									+ accessoryGroupName + "'").list();
			logger.debug("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}

	/**
	 * This function is used to get list Garmentstyleaccessorydetail by
	 * garmentStyleCode and sizeCode H�m t�m Garmentstyleaccessorydetail qua
	 * garment style v� size
	 */
	@SuppressWarnings("unchecked")
	public List<Garmentstyleaccessorydetail> getListGarmentstyleaccessorydetailByGarmentStyleNameAndSize(
			String garmentStyleName, Integer sizecode) {
		logger.debug(String
				.format("getListGarmentstyleaccessorydetailByGarmentStyleNameAndSize in class: %s",
						getClass()));
		try {
			List<Garmentstyleaccessorydetail> results = (List<Garmentstyleaccessorydetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Garmentstyleaccessorydetail where garmentstyle.garmentstylecode = '"
									+ garmentStyleName
									+ "' and size.sizecode = " + sizecode)
					.list();
			logger.debug("getListGarmentstyleaccessorydetailByGarmentStyleNameAndSize successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListGarmentstyleaccessorydetailByGarmentStyleNameAndSize in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}

	/**
	 * This function is used to get List Garmentstyle accessory detail By
	 * GarmentStyleCode AccessoryCode And Size
	 */
	@SuppressWarnings("unchecked")
	public List<Garmentstyleaccessorydetail> getListGarmentstyleaccessorydetailByGarmentStyleCodeAccessoryCodeAndSize(
			String garmentStyleName, String accessoryCode, Integer sizecode) {
		logger.debug(String
				.format("getListGarmentstyleaccessorydetailByGarmentStyleCodeAccessoryCodeAndSize in class: %s",
						getClass()));
		try {
			List<Garmentstyleaccessorydetail> results = (List<Garmentstyleaccessorydetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Garmentstyleaccessorydetail where garmentstyle.garmentstylecode = '"
									+ garmentStyleName
									+ "' and size.sizecode = " + sizecode
									+ " and accessory.accessorycode = '"
									+ accessoryCode + "'").list();
			logger.debug("getListGarmentstyleaccessorydetailByGarmentStyleCodeAccessoryCodeAndSize successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListGarmentstyleaccessorydetailByGarmentStyleCodeAccessoryCodeAndSize in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
}
