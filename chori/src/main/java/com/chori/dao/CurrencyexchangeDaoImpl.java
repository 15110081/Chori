package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Currencyexchange;

@Repository("currencyexchangeDao")
public class CurrencyexchangeDaoImpl extends
		AbstractDaoImpl<Currencyexchange, Integer> implements
		CurrencyexchangeDao {
	public Currencyexchange findById(int id) {
		logger.info("getByID");

		try {
			logger.debug(String.format("getting %s instance with id: %s",
					getClass(), id));
			Currencyexchange instance = (Currencyexchange) getSession().get(
					daoType, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get list failed", re);
			throw re;
		}
	}
}
