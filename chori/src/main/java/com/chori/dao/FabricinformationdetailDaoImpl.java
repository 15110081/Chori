package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Fabricinformationdetail;
import com.chori.entity.FabricinformationdetailId;

@Repository("fabricinformationdetailDao")
public class FabricinformationdetailDaoImpl extends
		AbstractDaoImpl<Fabricinformationdetail, FabricinformationdetailId>
		implements FabricinformationdetailDao {

	/**
	 * This function is used to get list Fabric information detail by fabric no
	 * 
	 * @param fabricNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Fabricinformationdetail> getListFabricinformationdetailByFabricNo(
			String fabricNo) {
		logger.debug(String.format(
				"getListFabricinformationdetailByFabricNo in class: %s",
				getClass()));
		try {
			List<Fabricinformationdetail> results = (List<Fabricinformationdetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Fabricinformationdetail where id.fabricno = '"
									+ fabricNo + "'").list();
			logger.debug("getListFabricinformationdetailByFabricNo successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListFabricinformationdetailByFabricNo in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to get all fabric information detail by fabricno
	 * 
	 * @param fabricno
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Fabricinformationdetail> getAllFabricInformationDetailByFabricNo(
			 String fabricno) {
		logger.debug(String
				.format("getAllFabricInformationDetailByFabricNo in class: %s",
						getClass()));
		try {
		
			List<Fabricinformationdetail> results = (List<Fabricinformationdetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Fabricinformationdetail where id.fabricno = '" + fabricno 	
							 + "'").list();
			logger.debug("getAllFabricInformationDetailByFabricNo successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getAllFabricInformationDetailByFabricNo in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
}
