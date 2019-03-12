package com.chori.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.chori.model.CurrencyModel;
import com.chori.service.CurrencyService;
import com.chori.service.RoleService;

@Controller
@RequestMapping(value = "/")
public class CurrencyController {
	private static final Log log = LogFactory.getLog(CurrencyController.class);

	@Autowired
	CurrencyService ser;

	@Autowired
	RoleService roleSer;

	// @Autowired
	// @Qualifier("currencyValidator")
	// private Validator currencyValidator;
	//
	// @InitBinder("currencyValidator")
	// private void initBinder_CurrencyValidator(WebDataBinder binder) {
	// binder.setValidator(currencyValidator);
	// }

	/**
	 * Return view page
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/listCurrency" }, method = RequestMethod.GET)
	public String listCurrency() {
		log.info(String.format("listCurrency in class: %s", getClass()));
		try {
			log.debug("return listCurrency view for request");
			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Currency Management")) {
				log.debug("listCurrency successful");
				return "configuration/currency/listCurrency";
			}
			return "login/accessDenied";

		} catch (Exception e) {
			log.error(String.format("listCurrency in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get all currency in db and return json
	 * 
	 * @return
	 */
	@RequestMapping(value = "/currency/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllCurrency() {
		log.info(String.format("getAllCurrency in class %s", getClass()));
		try {
			log.debug("getting list of all currency and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<CurrencyModel> lst = ser.getAllCurrencyModel();
			result.put("status", "ok");
			result.put("list", lst);
			log.debug("getAllCurrency successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getAllCurrency in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new currency
	 * 
	 * @param currencyModel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/currency/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addNewCurrency(
			@Valid @RequestBody CurrencyModel currencyModel,
			BindingResult bindingResult) {
		log.info(String.format(
				"addNewCurrency with params 'currencyModel' in class: %s",
				getClass()));

		// currencyValidator.validate(currencyModel, bindingResult);

		try {
			log.debug("addNewCurrency and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");

			if (bindingResult.hasErrors()) {
				System.err.println("validation input currency invalid");
				result.put("addStatus", "invalid currency input");
				return result;
			}

			result.put("addStatus", ser.addNewCurrency(currencyModel, "admin"));
			System.err.println(currencyModel);
			log.debug("addNewCurrency successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addNewCurrency with params 'currencyModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get detail of 1 currency
	 * 
	 * @param currencyCode
	 * @return
	 */
	@RequestMapping(value = "/currency/detail", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get1CurrencyDetail(
			@RequestBody CurrencyModel currencyModel) {
		log.info(String.format("get1CurrencyDetail in class %s", getClass()));
		try {
			log.debug("get1CurrencyDetail and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("currency", ser.findCurrencyModelById(currencyModel.getCurrencycode()));
			log.debug("get1CurrencyDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"get1CurrencyDetail in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit a currency
	 * 
	 * @param currencyModel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/currency/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editCurrency(
			@Valid @RequestBody CurrencyModel currencyModel,
			BindingResult bindingResult) {
		log.info(String.format("editCurrency with params 'fm' in class: %s",
				getClass()));

		// currencyValidator.validate(currencyModel, bindingResult);

		try {
			log.debug("editCurrency and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");

			if (bindingResult.hasErrors()) {
				System.err.println("validation input currency invalid");
				result.put("editStatus", "invalid currency input");
				return result;
			}

			result.put("editStatus", ser.editCurrency(currencyModel, "admin"));
			System.err.println(currencyModel);
			log.debug("editCurrency successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"editCurrency with params 'fm' in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete a currency
	 * 
	 * @param currencyCode
	 * @return
	 */
	@RequestMapping(value = "/currency/delete", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete1Currency(@RequestBody CurrencyModel currencyModel) {
		log.info(String.format("delete1Currency in class %s", getClass()));
		try {
			log.debug("delete1Currency and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("deleteStatus", ser.deleteCurrency(currencyModel.getCurrencycode()));
			log.debug("delete1Currency successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"delete1Currency in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to check if a currency is existed
	 * 
	 * @param currencyCode
	 * @return
	 */
	@RequestMapping(value = "/currency/isExist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isCurrencyExist(@RequestBody CurrencyModel currencyModel) {
		log.info(String.format(
				"isCurrencyExist with param 'factoryCode' in class: %s",
				getClass()));
		try {
			log.debug("check if a currency with currencyCode is existed in DB and return as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("isExisted", ser.isCurrencyExistedById(currencyModel.getCurrencycode()));
			log.debug("check isCurrencyExist successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isCurrencyExist with param 'factoryCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
