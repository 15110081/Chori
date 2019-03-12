package com.chori.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.CurrencyDao;
import com.chori.dao.UserDao;
import com.chori.entity.Currency;
import com.chori.model.CurrencyModel;

@Service("currencyService")
public class CurrencyServiceImpl extends AbstractServiceImpl<Currency, String>
		implements CurrencyService {
	private CurrencyDao dao;

	@Autowired
	private UserDao userDao;

	@Autowired
	public CurrencyServiceImpl(
			@Qualifier("currencyDao") AbstractDao<Currency, String> abstractDao) {
		super(abstractDao);
		this.dao = (CurrencyDao) abstractDao;
	}

	/**
	 * This function is used to get all currency model
	 */
	public List<CurrencyModel> getAllCurrencyModel() {
		log.info(String.format("getAllCurrencyModel in class: %s", getClass()));
		try {
			log.debug("get all currency in DB after that return a list Currency Model");
			List<Currency> lstCurrency = dao.getAll();

			CurrencyModel currencyModel;
			List<CurrencyModel> lst = new ArrayList<CurrencyModel>();

			for (Currency currency : lstCurrency) {

				currencyModel = new CurrencyModel();
				currencyModel.setCurrencycode(currency.getCurrencycode());
				currencyModel.setCreatedate(currency.getCreatedate());
				currencyModel.setCreator(currency.getUser() == null ? ""
						: currency.getUser().getUsername());
				currencyModel.setName(currency.getName());

				lst.add(currencyModel);
			}
			log.debug("getAllCurrencyModel successfully");
			return lst;
		} catch (Exception e) {
			log.error(String.format(
					"getAllCurrencyModel in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new currency
	 * 
	 * @param currencyModel
	 * @param userName
	 * @return
	 */
	public boolean addNewCurrency(CurrencyModel currencyModel, String userName) {
		log.info(String
				.format("addNewCurrency with params 'currencyModel', 'userName' in class: %s",
						getClass()));
		try {
			Currency currency = new Currency();
			currency.setCurrencycode(currencyModel.getCurrencycode());
			currency.setName(currencyModel.getName());
			currency.setUser(userDao.findById(userName));
			currency.setCreatedate(new Date());

			dao.save(currency);
			System.err.println("Save currency successful");

			return true;
		} catch (Exception e) {
			log.error(String
					.format("addNewCurrency with params 'currencyModel', 'userName' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function find 1 currency by id
	 */
	public CurrencyModel findCurrencyModelById(String id) {
		log.info(String.format(
				"findCurrencyModelById with params 'id' in class: %s",
				getClass()));
		try {
			Currency currency = dao.findById(id);
			CurrencyModel currencyModel = new CurrencyModel();

			currencyModel.setCurrencycode(currency.getCurrencycode());
			currencyModel.setCreatedate(currency.getCreatedate());
			currencyModel.setCreator(currency.getUser() == null ? "" : currency
					.getUser().getUsername());
			currencyModel.setName(currency.getName());

			return currencyModel;
		} catch (Exception e) {
			log.error(String
					.format("findCurrencyModelById with params 'id' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit a currency
	 * 
	 * @param currencyModel
	 * @param userName
	 * @return
	 */
	public boolean editCurrency(CurrencyModel currencyModel, String userName) {
		log.info(String
				.format("editCurrency with params 'currencyModel', 'userName' in class: %s",
						getClass()));
		try {
			Currency currency = dao.findById(currencyModel.getCurrencycode());
			currency.setCurrencycode(currencyModel.getCurrencycode());
			currency.setName(currencyModel.getName());

			dao.update(currency);
			System.err.println("update currency successful");

			return true;
		} catch (Exception e) {
			log.error(String
					.format("editCurrency with params 'currencyModel', 'userName' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete currency
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteCurrency(String id) {
		log.info(String.format("deleteCurrency with params 'id' in class: %s",
				getClass()));
		try {
			Currency currency = dao.findById(id);
			dao.delete(currency);

			return true;
		} catch (Exception e) {
			log.error(String
					.format("deleteCurrency with params 'id' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a currency is existed
	 * 
	 * @param id
	 * @return
	 */
	public boolean isCurrencyExistedById(String id) {
		log.info(String.format(
				"isCurrencyExistedById with param 'id' in class: %s",
				getClass()));
		try {
			if (null == dao.findById(id))
				return false;
			return true;
		} catch (Exception e) {
			log.error(String
					.format("isCurrencyExistedById with param 'id' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
