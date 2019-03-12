package com.chori.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.chori.model.CurrencyexchangeModel;
import com.chori.service.CurrencyexchangeService;
import com.chori.service.RoleService;

@Controller
@RequestMapping(value = "/")
public class CurrencyexchangeController {
	private static final Log log = LogFactory.getLog(CurrencyController.class);

	@Autowired
	CurrencyexchangeService ser;

	@Autowired
	RoleService roleSer;

	@Autowired
	@Qualifier("currencyexchangeValidator")
	private Validator splvalidator;

	@InitBinder("currencyexchangeValidator")
	private void initBinder_CurrencyExchangeValidator(WebDataBinder binder) {
		binder.setValidator(splvalidator);
	}

	/**
	 * Return view page
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/listCurrencyexchange" }, method = RequestMethod.GET)
	public ModelAndView listCurrencyexchange(HttpServletResponse response) {
		log.info(String.format("listCurrencyexchange in class: %s", getClass()));
		try {
			log.debug("return listCurrencyexchange view for request");
			response.setContentType("text/html");
			log.debug("listCurrencyexchange successful");
			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Currency Exchange")) {
				return new ModelAndView(
						"configuration/currencyexchange/listCurrencyexchange");
			}
			return new ModelAndView("login/accessDenied");

		} catch (Exception e) {
			log.error(String.format(
					"listCurrencyexchange in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get all currencyexchange in db and return json
	 * 
	 * @return
	 */
	@RequestMapping(value = "/currencyexchange/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllCurrencyexchange() {
		log.info(String
				.format("getAllCurrencyexchange in class %s", getClass()));
		try {
			log.debug("getting list of all currencyexchange and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<CurrencyexchangeModel> lst = ser.getAllCurrencyexchangeModel();
			result.put("status", "ok");
			result.put("list", lst);
			log.debug("getAllCurrencyexchange successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllCurrencyexchange in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new currencyexchange
	 * 
	 * @param currencyexchangeModel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/currencyexchange/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addNewCurrencyexchange(
			@Valid @RequestBody CurrencyexchangeModel currencyexchangeModel,
			BindingResult bindingResult) {
		log.info(String
				.format("addNewCurrencyexchange with params 'currencyexchangeModel' in class: %s",
						getClass()));

		try {
			log.debug("addNewCurrencyexchange and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("addStatus", ser.addCurrencyexchange(
					currencyexchangeModel, LoginController.currentUser));
			System.err.println(currencyexchangeModel);
			log.debug("addNewCurrencyexchange successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addNewCurrency with params 'currencyexchangeModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get detail of 1 currencyexchange
	 * 
	 * @param currencyexchangeCode
	 * @return
	 */
	@RequestMapping(value = "/currencyexchange/detail/{currencyexchangeCode}", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> get1CurrencyexchangeDetail(
			@PathVariable Integer currencyexchangeCode) {
		log.info(String.format("get1CurrencyDetail in class %s", getClass()));
		try {
			log.debug("get1CurrencyexchangeDetail and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("currencyexchange",
					ser.findCurrencyexchangeById(currencyexchangeCode));
			log.debug("get1CurrencyexchangeDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"get1CurrencyexchangeDetail in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit a currency exchange
	 * 
	 * @param currencyexchangeModel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/currencyexchange/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editCurrencyexchange(
			@Valid @RequestBody CurrencyexchangeModel currencyexchangeModel,
			BindingResult bindingResult) {
		log.info(String.format(
				"editCurrencyexchange with params 'cm' in class: %s",
				getClass()));

		try {
			log.debug("editCurrencyexchange and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");

			if (bindingResult.hasErrors()) {
				System.err.println("validation input currency invalid");
				result.put("editStatus", "invalid currencyexchange input");
				return result;
			}

			result.put("editStatus", ser.editCurrencyexchange(
					currencyexchangeModel, LoginController.currentUser));
			System.err.println(currencyexchangeModel);
			log.debug("editCurrencyexchange successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"editCurrency with params 'cm' in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete a currencyexchange
	 * 
	 * @param currencyCode
	 * @return
	 */
	@RequestMapping(value = "/currencyexchange/delete/{currencyexchangeCode}", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete1Currencyexchange(
			@PathVariable Integer currencyexchangeCode) {
		log.info(String.format("delete1Currencyexchange in class %s",
				getClass()));
		try {
			log.debug("delete1Currencyexchange and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("deleteStatus",
					ser.deleteCurrencyexchange(currencyexchangeCode));
			log.debug("delete1Currencyexchange successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"delete1Currencyexchange in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to check if a currency is existed
	 * 
	 * @param currencyCode
	 * @return
	 */
	@RequestMapping(value = "/currencyexchange/isExist/{currencyexchangeCode}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> isCurrencyexchangeExist(
			@PathVariable Integer currencyexchangeCode) {
		log.info(String
				.format("isCurrencyexchangeExist with param 'currencyexchangeCode' in class: %s",
						getClass()));
		try {
			log.debug("check if a currency with currencyexchangeCode is existed in DB and return as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("isExisted",
					ser.isCurrencyexchangeExistedById(currencyexchangeCode));
			log.debug("check isCurrencyexchangeExist successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isCurrencyexchangeExist with param 'currencyexchangeCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
