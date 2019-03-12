package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Garmentstyleaccessorydetail;
import com.chori.entity.Orderinternalaccessorydetail;
import com.chori.entity.OrderinternalaccessorydetailId;

@Repository("orderinternalaccessorydetailDao")
public class OrderinternalaccessorydetailDaoImpl
		extends
		AbstractDaoImpl<Orderinternalaccessorydetail, OrderinternalaccessorydetailId>
		implements OrderinternalaccessorydetailDao {
	
	/**
	 * This function is used to get list Order internal accessory detail by order sheet no.
	 * @param ordersheetno
	 * @return List<Orderinternalaccessorydetail>
	 */
	@SuppressWarnings("unchecked")
	public List<Orderinternalaccessorydetail> getListOrderinternalaccessorydetailByOrderSheetNo(
			String ordersheetno) {
		logger.debug(String
				.format("getListOrderinternalaccessorydetailByOrderSheetNo in class: %s",
						getClass()));
		try {
			List<Orderinternalaccessorydetail> results = (List<Orderinternalaccessorydetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Orderinternalaccessorydetail where orderinternalaccessory.ordersheetno = '"
									+ ordersheetno + "'").list();
			logger.debug("getListOrderinternalaccessorydetailByOrderSheetNo successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListOrderinternalaccessorydetailByOrderSheetNo in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to getListOrderinternalaccessorydetailByAccessoryCodeAndFactoryCode
	 * @param ordersheetno
	 * @return List<Orderinternalaccessorydetail>
	 */
	@SuppressWarnings("unchecked")
	public List<Orderinternalaccessorydetail> getListOrderinternalaccessorydetailByAccessoryCodeAndFactoryCode(String accessoryCode, String factoryCode) {
		logger.debug(String
				.format("getListOrderinternalaccessorydetailByAccessoryCodeAndFactoryCode in class: %s",
						getClass()));
		try {
			List<Orderinternalaccessorydetail> results = (List<Orderinternalaccessorydetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Orderinternalaccessorydetail where accessory.accessorycode = '"
									+ accessoryCode + "' and orderinternalaccessory.factory.factorycode = '"
									+ factoryCode + "'").list();
			logger.debug("getListOrderinternalaccessorydetailByAccessoryCodeAndFactoryCode successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListOrderinternalaccessorydetailByAccessoryCodeAndFactoryCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
}
