package com.chori.dao;

import com.chori.AbstractDao;
import com.chori.entity.Currencyexchange;

public interface CurrencyexchangeDao extends
		AbstractDao<Currencyexchange, Integer> {
	public Currencyexchange findById(int id);
}
