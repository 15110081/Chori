package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Currency;

@Repository("currencyDao")
public class CurrencyDaoImpl extends AbstractDaoImpl<Currency, String>
		implements CurrencyDao {

}
