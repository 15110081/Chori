package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Accessoryprice;

@Repository("accessorypriceDao")
public class AccessoryPriceDaoImpl extends
		AbstractDaoImpl<Accessoryprice, Integer> implements AccessoryPriceDao {
	@SuppressWarnings("unchecked")
	// public List<Accessoryprice>
	// getListAccessorypriceByAccessoryCodeandAccessorySuplierandOrderDate(
	// String accessoryCode, String accessorySuplier,Date orderDate) {
	// logger.debug(String.format("getListAccessorypriceByAccessoryCodeandAccessorySuplierandOrderDate in class: %s",
	// getClass()));
	// try {
	// List<Accessoryprice> results = (List<Accessoryprice>) sessionFactory
	// .getCurrentSession()
	// .createQuery(
	// "from Accessoryprice where accessory.accessorycode = '"
	// + accessoryCode
	// + "' and accessorysupplier.accsuppliercode = '"
	// + accessorySuplier +
	// "' and '"+orderDate+"' between fromdate and todate '").list();
	// logger.debug("getListAccessorypriceByAccessoryCodeandAccessorySuplierandOrderDate successfull, result size: "
	// + results.size());
	// return results;
	// } catch (RuntimeException re) {
	// logger.error(String.format(
	// "getListAccessorypriceByAccessoryCodeandAccessorySuplierandOrderDate in class %s has error: %s",
	// getClass(),
	// re.getMessage()));
	// throw re;
	// }
	// }
	public List<Accessoryprice> getListAccessorypriceByAccessoryCodeandAccessorySuplierandOrderDate(
			String accessoryCode, String accessorySuplier) {
		logger.debug(String
				.format("getListAccessorypriceByAccessoryCodeandAccessorySuplierandOrderDate in class: %s",
						getClass()));
		try {
			List<Accessoryprice> results = (List<Accessoryprice>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Accessoryprice where accessory.accessorycode = '"
									+ accessoryCode
									+ "' and accessorysupplier.accsuppliercode = '"
									+ accessorySuplier + "'").list();
			logger.debug("getListAccessorypriceByAccessoryCodeandAccessorySupliersuccessfull, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListAccessorypriceByAccessoryCodeandAccessorySuplier in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	public boolean isExistAccPriceByAccessoryCodeAndSupplier(
			String accessoryCode, String accessorySuplier) {
		logger.debug(String
				.format("getListAccessorypriceByAccessoryCodeandAccessorySuplierandOrderDate in class: %s",
						getClass()));
		try {
			int count = ((Long)getSession().createQuery("select count(*) from Accessoryprice where accessory.accessorycode = '"
									+ accessoryCode
									+ "' and accessorysupplier.accsuppliercode = '"
									+ accessorySuplier + "'").uniqueResult()).intValue();
			logger.debug("getListAccessorypriceByAccessoryCodeandAccessorySupliersuccessfull, result size: "
					+ count);
			if(count>0)
				return true;
			else
				return false;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListAccessorypriceByAccessoryCodeandAccessorySuplier in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
}
