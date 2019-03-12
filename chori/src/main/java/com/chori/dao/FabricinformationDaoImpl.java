package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Fabricinformation;

@Repository("fabricinformationDao")
public class FabricinformationDaoImpl extends
		AbstractDaoImpl<Fabricinformation, String> implements
		FabricinformationDao {
	
	@SuppressWarnings("unchecked")
	public List<Fabricinformation> getListFabricInformationByCustomerCode(
			String customerCode) {
		logger.debug(String
				.format("getListFabricInformationByCustomerCode in class: %s",
						getClass()));
		try {
			List<Fabricinformation> results = (List<Fabricinformation>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Fabricinformation where customer.customercode = '"
									+ customerCode +"'" ).list();
			logger.debug("getListFabricInformationByCustomerCode successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListFabricInformationByCustomerCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Fabricinformation> getAllFabricInformationByCustomerCodeFactoryCode(
			String customerCode , String factoryCode) {
		logger.debug(String
				.format("getListFabricInformationByCustomerCodeFactoryCode in class: %s",
						getClass()));
		try {
			List<Fabricinformation> results = (List<Fabricinformation>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Fabricinformation where customer.customercode = '"
									+ customerCode
									+ "' and factory.factorycode= '" + factoryCode
									+"'" ).list();
			logger.debug("getListFabricInformationByCustomerCodeFactoryCode successfully, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListFabricInformationByCustomerCodeFactoryCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
}
