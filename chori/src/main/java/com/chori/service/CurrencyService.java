package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Currency;
import com.chori.model.CurrencyModel;

public interface CurrencyService extends AbstractService<Currency, String> {
	public List<CurrencyModel> getAllCurrencyModel();

	public boolean addNewCurrency(CurrencyModel currencyModel, String userName);

	public CurrencyModel findCurrencyModelById(String id);

	public boolean editCurrency(CurrencyModel currencyModel, String userName);

	public boolean deleteCurrency(String id);

	public boolean isCurrencyExistedById(String id);
}
