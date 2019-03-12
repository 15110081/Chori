package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Accessory;
import com.chori.entity.Piassigninternalaccessoriesdetail;

@Repository("accessoryDao")
public class AccessoryDaoImpl extends AbstractDaoImpl<Accessory, String>
		implements AccessoryDao {

	/**
	 * This function is used to getListPiassigninternalaccessoriesdetailByPiAssignInternalAccessoriesId by lotnumber,accessorycode
	 */
	@SuppressWarnings("unchecked")
	public List<Accessory> getListAccessoryWithKindInternal() {
		logger.debug(String
				.format("getListAccessoryWithKindInternal in class: %s",
						getClass()));
		try {
			List<Accessory> results = (List<Accessory>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Accessory where kind = 'Internal'").list();
			logger.debug("getListAccessoryWithKindInternal successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListAccessoryWithKindInternal in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
}
