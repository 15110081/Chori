package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Size;

@Repository("sizeDao")
public class SizeDaoImpl extends AbstractDaoImpl<Size, Integer> implements
		SizeDao {

	/**
	 * This function is used to get list size by customer code and garment kind
	 * code
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Size> getListSizeByCustomer(String customercode,
			String garmentKindCode) {
		logger.debug(String.format("getListSizeByCustomer in class: %s",
				getClass()));
		try {
			List<Size> results = (List<Size>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Size where customer.customercode = '"
									+ customercode
									+ "' and garmentkind.garmentkindcode = '"
									+ garmentKindCode + "'").list();
			logger.debug("getListSizeByCustomer successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String.format(
					"getListSizeByCustomer in class %s has error: %s",
					getClass(), re.getMessage()));
			throw re;
		}
	}
}
