package com.chori.controller;

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

import com.chori.entity.AccessoryconsumptionId;
import com.chori.model.AccessoryConsumptionModel;
import com.chori.model.ShippinglineModel;
import com.chori.service.AccessoryConsumptionService;
import com.chori.service.RoleService;

@Controller
@RequestMapping(value = "/")
public class AccessoryConsumptionController {

	@Autowired
	AccessoryConsumptionService ser;

	@Autowired
	@Qualifier("accessoryConsumptionValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@Autowired
	RoleService roleSer;

	private static final Log log = LogFactory
			.getLog(AccessoryConsumptionController.class);

	/**
	 * Return view page for list Accessory Consumption
	 */

	@RequestMapping(value = { "/listWastedPercentage" }, method = RequestMethod.GET)
	public String listAccessoryConsumption() {
		log.info(String.format(
				"listAccessoryConsumption with param 'response' in class: %s",
				getClass()));
		try {
			LoginController.AssignCurrentUser();
			log.debug("return listAccessoryConsumption view for request");
			log.debug("listAccessoryConsumption successful");
			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Accessory Consumption")) {
				return "configuration/accessoryconsumption/listAccessoryConsumption";
			}
			return "login/accessDenied";
		} catch (Exception e) {
			log.error(String
					.format("listAccessoryConsumption with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method is used to get all accessory consumption in database
	 * 
	 * @return a list accessory consumption in JSON format
	 */
	@RequestMapping(value = "/accessoryconsumption/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllAccessoryConsumption() {
		log.info(String.format("getAllAccessoryConsumption in class %s",
				getClass()));
		try {
			log.debug("getting list of all AccessoryConsumption and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<AccessoryConsumptionModel> ls = ser
					.getAllAccessoryConsumption();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllAccessoryConsumption successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllAccessoryConsumption in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete an accessoryconsumption
	 * 
	 * @param factorycode
	 *            ,accessorycode
	 * @return deleteStatus as JSON
	 */
	@RequestMapping(value = "/accessoryconsumption/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> deleteAccessoryConsumption(@RequestBody AccessoryConsumptionModel accessoryConsumptionModel) {
		log.info(String
				.format("deleteAccessoryConsumption with param 'accessoryConsumptionModel' in class: %s",
						getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			AccessoryconsumptionId accessoryconsumptionId = new AccessoryconsumptionId(
					accessoryConsumptionModel.getFactorycode(), accessoryConsumptionModel.getAccessorycode());
			result.put("status", "ok");
			result.put("deleteStatus",
					ser.deleteAccessoryConsumption(accessoryconsumptionId));
			log.debug("deleteAccessoryConsumption successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("deleteAccessoryConsumption with param 'accessoryConsumptionModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete an accessoryconsumption
	 * 
	 * @param factorycode
	 * @return deleteStatus as JSON
	 */
	@RequestMapping(value = "/accessoryconsumption/delete/{factorycode}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> deleteAccessoryConsumption(
			@PathVariable String factorycode) {
		log.info(String
				.format("deleteAccessoryConsumption with param 'factorycode' and 'accessorycode' in class: %s",
						getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			AccessoryconsumptionId accessoryconsumptionId = new AccessoryconsumptionId(
					factorycode, null);
			result.put("status", "ok");
			result.put("deleteStatus",
					ser.deleteAccessoryConsumption(accessoryconsumptionId));
			log.debug("deleteAccessoryConsumption successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("deleteAccessoryConsumption with param 'factorycode' and 'accessorycode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function find a accessoryconsumption by id then return it as JSON
	 * format
	 * 
	 * @param factorycode
	 *            ,accessorycode
	 * @return JSON format of a accessoryConsumption
	 */
	@RequestMapping(value = "/accessoryconsumption/detail", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getAccessoryConsumptionDetail(@RequestBody AccessoryConsumptionModel
			accessoryConsumptionModel) {
		log.info(String
				.format("getAccessoryConsumptionDetail with param 'accessoryConsumptionModel' in class: %s",
						getClass()));
		try {
			log.debug("getting accessoryconsumption detail by its {factorycode}/{accessorycode} and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			AccessoryconsumptionId accessoryconsumptionId = new AccessoryconsumptionId(
					accessoryConsumptionModel.getFactorycode(), accessoryConsumptionModel.getAccessorycode());
			result.put("status", "ok");
			result.put("accessoryconsumption", ser
					.findAccessoryConsumptionModelById(accessoryconsumptionId));
			log.debug("getAccessoryConsumptionDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getAccessoryConsumptionDetail with param 'accessoryConsumptionModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function edit a accessoryconsumption and update into database
	 * 
	 * @param accessoryconsumption
	 * @return status, editStatus as JSON
	 */
	@ResponseBody
	@RequestMapping(value = "/accessoryconsumption/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editAccessoryConsumption(
			@RequestBody AccessoryConsumptionModel accessoryConsumptionModel) {
		log.info(String
				.format("editAccessoryConsumption with param 'accessoryConsumptionModel' in class: %s",
						getClass()));
		try {
			log.debug("edit 1 accessoryconsumption and return edit status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("editStatus",
					ser.editAccessoryConsumption(accessoryConsumptionModel));
			log.debug("editAccessoryConsumption successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("editAccessoryConsumption with param 'accessoryConsumptionModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a accessoryconsumption with given factorycode,
	 * accessorycode is existed on database.
	 * 
	 * @param factorycode
	 *            , accessorycode
	 * @return JSON value
	 */
	@RequestMapping(value = "/accessoryconsumption/isExist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> isAccessoryConsumptionExist(
			@RequestBody AccessoryConsumptionModel accessoryConsumptionModel) {
		log.info(String
				.format("isAccessoryConsumptionExist with param 'accessoryConsumptionModel' in class: %s",
						getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			AccessoryconsumptionId accessoryconsumptionId = new AccessoryconsumptionId(
					accessoryConsumptionModel.getFactorycode(), accessoryConsumptionModel.getAccessorycode());
			result.put("status", "ok");
			result.put("isExisted", ser
					.isAccessoryConsumptionExistedById(accessoryconsumptionId));
			log.debug("check isAccessoryConsumptionExist successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isAccessoryConsumptionExist with param 'accessoryConsumptionModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new accessoryconsumption, return addStatus
	 * as JSON format
	 * 
	 * @param accessoryconsumption
	 * @return status, addStatus as JSON format
	 */
	@ResponseBody
	@RequestMapping(value = "/accessoryconsumption/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addAccessoryConsumption(
			@RequestBody AccessoryConsumptionModel accessoryConsumptionModel) {
		log.info(String
				.format("addAccessoryConsumption with param 'accessoryConsumptionModel' in class: %s",
						getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String userName = LoginController.currentUser;
			result.put("addStatus", ser.addAccessoryConsumption(
					accessoryConsumptionModel, userName));
			log.debug("addAccessoryConsumption successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addAccessoryConsumption with param 'accessoryConsumptionModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}
}
