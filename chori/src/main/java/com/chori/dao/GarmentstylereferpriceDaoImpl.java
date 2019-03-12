package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Garmentstyleaccessorydetail;
import com.chori.entity.Garmentstylereferprice;
import com.chori.entity.GarmentstylereferpriceId;

@Repository("garmentstylereferpriceDao")
public class GarmentstylereferpriceDaoImpl extends
AbstractDaoImpl<Garmentstylereferprice, GarmentstylereferpriceId> implements
GarmentstylereferpriceDao {
	
	/**
	 * This function is used to get list Garmentstylereferprice by garment style code
	 * @param garmentStyleCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Garmentstylereferprice> getListGarmentstylereferpriceByGarmentstyleCode(
			String garmentStyleCode) {
		logger.debug(String
				.format("getListGarmentstylereferprice in class: %s",
						getClass()));
		try {
			List<Garmentstylereferprice> results = (List<Garmentstylereferprice>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Garmentstylereferprice where garmentstyle.garmentstylecode = '"
									+ garmentStyleCode + "'").list();
			logger.debug("getListGarmentstylereferprice successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListGarmentstylereferprice in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
}
