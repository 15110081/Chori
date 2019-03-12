package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Currencyexchange;
import com.chori.model.CurrencyexchangeModel;

public interface CurrencyexchangeService extends
		AbstractService<Currencyexchange, Integer> {

	public List<CurrencyexchangeModel> getAllCurrencyexchangeModel();

	public boolean deleteCurrencyexchange(int id);

	public CurrencyexchangeModel findCurrencyexchangeById(int currencyexCode);

	public boolean addCurrencyexchange(
			CurrencyexchangeModel currencyexchangeMod, String userName);

	public boolean isCurrencyexchangeExistedById(int currencyexchangeCode);

	public boolean editCurrencyexchange(
			CurrencyexchangeModel currencyexchangeModel, String userName);

}
