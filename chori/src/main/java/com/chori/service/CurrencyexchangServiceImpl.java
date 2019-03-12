package com.chori.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.CurrencyDao;
import com.chori.dao.CurrencyexchangeDao;
import com.chori.dao.UserDao;
import com.chori.entity.Currencyexchange;
import com.chori.model.CurrencyexchangeModel;

@Repository("currencyexchangeService")
public class CurrencyexchangServiceImpl extends AbstractServiceImpl<Currencyexchange, Integer>
		implements CurrencyexchangeService {
	private CurrencyexchangeDao dao;

	@Autowired
	private CurrencyDao currencyDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	public CurrencyexchangServiceImpl(
			@Qualifier("currencyexchangeDao") AbstractDao<Currencyexchange, Integer> abstractDao) {
		super(abstractDao);
		this.dao = (CurrencyexchangeDao) abstractDao;
	}

	/**
	 * This function find a Currencyexchangemodel by currencyexchangeCode.
	 * 
	 * @param currencyexchangeCode
	 * @return a CurrencyexchangeModel
	 */

	public CurrencyexchangeModel findCurrencyexchangeModelById(int currencyexchangeCode) {
		log.info(String.format("findCurrencyexchangeModelById with param 'currencyexchangeCode' in class: %s",
				getClass()));
		try {
			CurrencyexchangeModel currencyexMod = new CurrencyexchangeModel();
			Currencyexchange cr = dao.findById(currencyexchangeCode);
			currencyexMod.setCurrencyexchangecode(cr.getCurrencyexcode());
			currencyexMod.setCurrencycodedestination(cr.getCurrencyByCurrencycodesource().getCurrencycode());
			currencyexMod.setCurrencycodesource(cr.getCurrencyByCurrencycodedestination().getCurrencycode());
			currencyexMod.setAmount(cr.getAmount());
			currencyexMod.setExchangedate(cr.getExchangedate());
			currencyexMod.setCreatedate(cr.getCreatedate());

			log.debug("findCurrencyexchangeModelById successfully");
			return currencyexMod;
		} catch (Exception e) {
			log.error(String.format("findCurrencyexchangeModelById with param 'currencyexchangeCode' in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function edit a currencyexchange by currencyexchangeModel.
	 * 
	 * @param currencyexchangeModel
	 *            , userName
	 * @return edit => true; else => false
	 */

	public boolean editCurrencyexchange(CurrencyexchangeModel currencyexchangeModel, String userName) {
		log.info(String.format(
				"editCurrencyexchange with param 'currencyexchangeEn', 'userName' in class: %s",
				getClass()));
		try {
//			if (validateForeignKey(currencyexchangeModel.getCurrencycodesource(),
//					currencyexchangeModel.getCurrencycodedestination()) == true) {
				Currencyexchange cr = dao.findById(currencyexchangeModel.getCurrencyexchangecode());
				cr.setCurrencyexcode(currencyexchangeModel.getCurrencyexchangecode());
				cr.setExchangedate(currencyexchangeModel.getExchangedate());
				cr.setAmount(currencyexchangeModel.getAmount());
				cr.setCurrencyByCurrencycodesource(currencyDao.findById(currencyexchangeModel.getCurrencycodesource()));
				cr.setCurrencyByCurrencycodedestination(
						currencyDao.findById(currencyexchangeModel.getCurrencycodedestination()));
				dao.update(cr);
				log.debug("editCurrencyExchange successfully");
				return true;
//			}
//			return false;
		} catch (Exception e) {
			log.error(String.format(
					"editCurrencyexchange with param 'currencyexchangeEn', 'currencyexchangeCode' in class: %s has error: %s",
					getClass(), e.getMessage()));
			System.err.println(String.format(
					"editCurrencyexchange with param 'currencyexchangeEn', 'currencyexchangeCode' in class: %s has error: %s",
					getClass(), e.getMessage()));
			return false;
		}
	}

	/**
	 * This function is used get all Currencyexchange
	 * 
	 * @return list<CurrencyexchangeModel>
	 */
	public List<CurrencyexchangeModel> getAllCurrencyexchangeModel() {
		log.debug("in currencyexchange service list");
		try {
			List<Currencyexchange> lscur = dao.getAll();
			CurrencyexchangeModel curtm;
			List<CurrencyexchangeModel> lst = new ArrayList<CurrencyexchangeModel>();
			for (Currencyexchange cur : lscur) {
				curtm = new CurrencyexchangeModel();
				curtm.setCurrencyexchangecode(cur.getCurrencyexcode());
				curtm.setCurrencycodesource(cur.getCurrencyByCurrencycodesource().getCurrencycode());
				curtm.setCurrencycodedestination(cur.getCurrencyByCurrencycodedestination().getCurrencycode());
				curtm.setExchangedate(cur.getExchangedate());
				curtm.setCreatedate(cur.getCreatedate());
				curtm.setAmount(cur.getAmount());
				lst.add(curtm);
			}
			return lst;
		} catch (NullPointerException ne) {
			log.error("list service currencyexchange err: " + ne.getMessage());
		}
		return null;
	}

	/**
	 * This function delete a Currencyexchange by id.
	 * 
	 * @param id
	 * @return delete => true; else => false
	 */

	public boolean deleteCurrencyexchange(int id) {
		log.info(String.format("deleteCurrencyexchange with param 'id' in class: %s", getClass()));
		Currencyexchange cur = dao.findById(id);
		try {
			dao.delete(cur);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This function add a Currencyexchange by currencyexchangeMod.
	 * 
	 * @param CurrencyexchangeModel
	 * @return add => true; else => false
	 */

	public boolean addCurrencyexchange(CurrencyexchangeModel currencyexchangeMod, String userName) {
		log.info(String.format("addCurrencyexchange in class: %s", getClass()));
		try {
			if (validateForeignKey(currencyexchangeMod.getCurrencycodesource(),
					currencyexchangeMod.getCurrencycodedestination()) == true) {
				Currencyexchange cur = new Currencyexchange();
				cur.setAmount(currencyexchangeMod.getAmount());
				cur.setCreatedate(Calendar.getInstance().getTime());
				cur.setExchangedate(currencyexchangeMod.getExchangedate());
				cur.setCurrencyByCurrencycodesource(currencyDao.findById(currencyexchangeMod.getCurrencycodesource()));
				cur.setCurrencyByCurrencycodedestination(
						currencyDao.findById(currencyexchangeMod.getCurrencycodedestination()));

				List<CurrencyexchangeModel> lst = getAllCurrencyexchangeModel();
				for (CurrencyexchangeModel currencyexchangeModel : lst) {
					if (currencyexchangeModel.getCurrencycodesource()
							.equals(currencyexchangeMod.getCurrencycodesource())
							&& currencyexchangeModel.getCurrencycodedestination()
									.equals(currencyexchangeMod.getCurrencycodedestination())
							&& (currencyexchangeModel.getExchangedate()
									.getDate()) == ((currencyexchangeMod.getExchangedate().getDate()))) {
						return false;
					}
				}

				dao.save(cur);
				log.debug("add new currencyexchange into database successfully");
				return true;
			}
			return false;
		} catch (Exception e) {
			log.debug("add new currencyexchange into database fail");

			System.err.println(
					"add new Currencyexchange into database fail, method addCurrencyexchange(), class CurrencyexchangeService");
			return false;
		}
	}

	/**
	 * This function check esixted a Currencyexchange by currencyexchangeCode.
	 * 
	 * @param currencyexchangeCode
	 * @return existed => true; else => false
	 */

	public boolean isCurrencyexchangeExistedById(int currencyexchangeCode) {
		if (null == dao.findById(currencyexchangeCode))
			return false;
		return true;
	}

	/**
	 * This function find a Currencyexchange by currencyexCode.
	 * 
	 * @param currencyexCode
	 * @return a CurrencyexchangeModel
	 */

	@Override
	public CurrencyexchangeModel findCurrencyexchangeById(int currencyexCode) {
		log.info(String.format("findCurrencyExchangeCodeById with param 'currencyexCode' in class: %s", getClass()));
		try {
			CurrencyexchangeModel currencyexMod = new CurrencyexchangeModel();
			Currencyexchange cenr = dao.findById(currencyexCode);

			currencyexMod.setCurrencyexchangecode(cenr.getCurrencyexcode());
			currencyexMod.setCurrencycodesource(cenr.getCurrencyByCurrencycodesource().getCurrencycode());
			currencyexMod.setCurrencycodedestination(cenr.getCurrencyByCurrencycodedestination().getCurrencycode());
			currencyexMod.setAmount(cenr.getAmount());
			currencyexMod.setExchangedate(cenr.getExchangedate());
			currencyexMod.setCreatedate(cenr.getCreatedate());
			log.debug("findCurrencyExchangeCodeById successfully");
			return currencyexMod;
		} catch (Exception e) {
			log.error(
					String.format("findCurrencyExchangeCodeById with param 'currencyexCode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	public boolean validateForeignKey(String currencyCodeFrom, String currencyCodeTo) {
		log.debug("in CurrencyService at validateForeignKey method");
		try {
			if (currencyDao.findById(currencyCodeFrom) != null 
					&& currencyDao.findById(currencyCodeTo) != null) {
				return true;
			}
			return false;
		} catch (NullPointerException ne) {
			log.error("in CurrencyExchangeService at validateForeignKey method error: " + ne.getMessage());
			throw ne;
		}
	}

}
