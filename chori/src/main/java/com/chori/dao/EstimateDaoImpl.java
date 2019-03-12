package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Estimatetime;

@Repository("estimatetimeDao")
public class EstimateDaoImpl extends AbstractDaoImpl<Estimatetime, String>
		implements EstimatetimeDao {
	public Estimatetime findById(int id) {
		logger.info("getByID");

		try {
			logger.debug(String.format("getting %s instance with id: %s",
					getClass(), id));
			Estimatetime instance = (Estimatetime) getSession()
					.get(daoType, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get list failed", re);
			throw re;
		}
	}
}
