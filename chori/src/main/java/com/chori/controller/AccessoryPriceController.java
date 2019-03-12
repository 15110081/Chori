package com.chori.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.chori.model.AccessoryPriceModel;
import com.chori.service.AccessoryPriceService;
import com.chori.service.RoleService;

@Controller
@RequestMapping(value = "/")
public class AccessoryPriceController {
	private static final Log log = LogFactory
			.getLog(AccessoryPriceController.class);

	@Autowired
	AccessoryPriceService service;

	@Autowired
	RoleService roleSer;

	@Autowired
	@Qualifier("accessoryPriceValidator")
	private Validator validator;

	@InitBinder("accessoryPriceValidator")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	/**
	 * Return list view page
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/listAccessoryPrice" }, method = RequestMethod.GET)
	public String listAccessoryPrice() {
		log.info(String.format(
				"listAccessoryPrice with param 'response' in class: %s",
				getClass()));
		try {
			log.debug("return listAccessoryPrice view for request");
			log.debug("listAccessoryPrice successful");

			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Accessory Price")) {
				return "configuration/accessoryprice/listAccessoryPrice";
			}
			return "login/accessDenied";

		} catch (Exception e) {
			log.error(String
					.format("listAccessoryPrice with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method is used to get all accessory price in database and return a
	 * list accessory price in json
	 * 
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/accessoryprice/list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllAccessoryPrice() {
		log.info(String.format("getAllAccessoryPrice in class %s", getClass()));
		try {
			log.debug("getting list of all getAllAccessoryPrice and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<AccessoryPriceModel> ls = service.getAllAccessoryPrice();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllAccessoryPrice successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllAccessoryPrice in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new accessorycon price, return addStatus as
	 * JSON format
	 * 
	 * @param accessory
	 *            price
	 * @return status, addStatus as JSON format
	 */
	@ResponseBody
	@RequestMapping(value = "/accessoryprice/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addAccessoryPrice(
			@RequestBody AccessoryPriceModel accessorypriceMo) {
		log.info(String.format(
				"addAccessoryPrice with param 'accessoryPriceMo' in class: %s",
				getClass()));
		try {
			log.debug("add 1 accessory price and return edit width as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String userName = LoginController.currentUser;

//			accessorypriceMo.getFromdate().setHours(0);
//			accessorypriceMo.getFromdate().setMinutes(0);
//			accessorypriceMo.getFromdate().setSeconds(0);
			
//			if(accessorypriceMo.getTodate()!=null){
//				accessorypriceMo.getTodate().setHours(23);
//				accessorypriceMo.getTodate().setMinutes(59);
//				accessorypriceMo.getTodate().setSeconds(59);
//			}
			
			result.put("addAccessoryPrice",
			service.addAccessoryPrice(accessorypriceMo, userName));
			System.err.println(accessorypriceMo);
			log.debug("addAccessoryPrice successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addAccessoryPrice with param 'accessorypriceMo' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function find a accessorycon price by id then return it as JSON
	 * format
	 * 
	 * @param accessorypricecode
	 * @return JSON format of a AccessoryPrice
	 */
	@RequestMapping(value = "/accessoryprice/detail", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAccessoryPriceDetail(
			@RequestBody AccessoryPriceModel accessoryPriceModel) {
		log.info(String
				.format("getAccessoryPriceDetail with param 'accessorypriceCode' in class: %s",
						getClass()));
		try {
			log.debug("getting accessoryprice's detail by its accessorypriceCode and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			AccessoryPriceModel accessorypriceMo = service
					.findAccessoryPriceModelById(accessoryPriceModel.getAccessorypricecode());
			result.put("currentaccessoryprice", accessorypriceMo);
			result.put("status", "ok");
			log.debug("getAccessoryPriceDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getAccessoryPriceDetail with param 'accessorypriceCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function edit a accessoryprice and update into database
	 * 
	 * @param accessoryprice
	 * @return status, editStatus as JSON
	 */
	@ResponseBody
	@RequestMapping(value = "/accessoryprice/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editAccessoryPrice(
			@RequestBody AccessoryPriceModel accessorypriceMo) {
		log.info(String
				.format("editAccessoryPrice with param 'accessorypriceMo' in class: %s",
						getClass()));
		try {
			log.debug("edit 1 accessoryprice and return edit accessoryprice as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			
//			accessorypriceMo.getFromdate().setHours(0);
//			accessorypriceMo.getFromdate().setMinutes(0);
//			accessorypriceMo.getFromdate().setSeconds(0);
			
//			accessorypriceMo.getTodate().setHours(23);
//			accessorypriceMo.getTodate().setMinutes(59);
//			accessorypriceMo.getTodate().setSeconds(59);
			
			result.put("editAccessoryPrice",
					service.editAccessoryPrice(accessorypriceMo));
			log.debug("editAccessoryPrice successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("editAccessoryPrice param 'editAccessoryPrice' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete an accessoryconprice
	 * 
	 * @param accessorypriceCode
	 * @return status, deleteStatus as JSON
	 */
	@RequestMapping(value = "/accessoryprice/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> deleteAccessoryPrice(
			@RequestBody AccessoryPriceModel accessoryPriceModel) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "ok");
		try {
			service.deleteAccessoryprice(accessoryPriceModel.getAccessorypricecode());
			// service.deleteById(idRd);
			result.put("deleteAccessoryPrice", true);
		} catch (Exception e) {
			result.put("deleteAccessoryPrice", false);
		}
		return result;
	}
}
