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

import com.chori.model.ShippinglineModel;
import com.chori.service.RoleService;
import com.chori.service.ShippinglineService;

@Controller
@RequestMapping(value = "/")
public class ShippinglineController {
	private static final Log log = LogFactory
			.getLog(ShippinglineController.class);

	@Autowired
	RoleService roleSer;

	@Autowired
	ShippinglineService ser;

	@Autowired
	@Qualifier("shippingLineValidator")
	private Validator splvalidator;

	@Autowired
	@Qualifier("shippingLineContactValidator")
	private Validator splcontactvalidator;

	@InitBinder("shippingLineValidator")
	private void initBinder_ShippingLineValidator(WebDataBinder binder) {
		binder.setValidator(splvalidator);
	}

	@InitBinder("shippingLineContactValidator")
	private void initBinder_ShippingLineContactValidator(WebDataBinder binder) {
		binder.setValidator(splcontactvalidator);
	}

	// @InitBinder
	// private void initBinder_ShippingLineContactValidator(WebDataBinder
	// binder) {
	// binder.setValidator(splcontactvalidator);
	// }

	/**
	 * Return view page for Shipping line
	 */
	@RequestMapping(value = { "/Shippingline" }, method = RequestMethod.GET)
	public String listShippingline() {
		log.info(String.format(
				"listShippingline with param 'response' in class: %s",
				getClass()));
		try {
			LoginController.AssignCurrentUser();
			String path = "configuration/shippingline/listShippingline";
			log.debug("return listShippingline view for request");
			log.debug("listShippingline successful");
			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Shipping Line")) {
				return path;
			}
			return "login/accessDenied";
		} catch (Exception e) {
			log.error(String
					.format("listShippingline with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method is used to get all shippingline in database
	 * 
	 * @return list Shipping Line in JSON format
	 */
	@RequestMapping(value = "/shippingline/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllShippingLine() {
		log.info(String.format("getAllShippingLine in class %s", getClass()));
		try {
			log.debug("getting list of all ShippingLine and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<ShippinglineModel> ls = ser.getAllShippinglineModel();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllShippingLine successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllShippingLine in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/***
	 * This method is used to add new Shipping Line
	 * 
	 * @param splModel
	 * @return add status
	 */
	@ResponseBody
	@RequestMapping(value = "/shippingline/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addNewShippingLine(
			@RequestBody ShippinglineModel splModel) {
		log.info(String.format(
				"addNewShippingLine with params 'fm' in class: %s", getClass()));
		try {
			log.debug("addNewShippingLine and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String userName = LoginController.currentUser;
			result.put("addStatus", ser.addNewShippingLine(splModel, userName));
			System.err.println(splModel);
			log.debug("addNewShippingLine successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addNewShippingLine with params 'fm' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/***
	 * This method is used to get detail of a Shipping Line
	 * 
	 * @param shippinglineCode
	 * @return detail of a Shipping Line in JSON format
	 */
	@RequestMapping(value = "/shippingline/detail", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> getShippingLineDetail(
			@RequestBody ShippinglineModel splModel) {
		log.info(String.format("getShippingLineDetail in class %s", getClass()));
		try {
			log.debug("getShippingLineDetail and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("shippingline",
					ser.findShippingLineModelById(splModel.getShippinglinecode()));
			log.debug("getShippingLineDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getShippingLineDetail in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/***
	 * This method is used to edit detail of a Shipping Line
	 * 
	 * @param splModel
	 * @return edit status
	 */
	@ResponseBody
	@RequestMapping(value = "/shippingline/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editShippingline(
			@RequestBody ShippinglineModel splModel) {
		log.info(String.format(
				"editShippingline with params 'fm' in class: %s", getClass()));
		try {
			log.debug("editShippingline and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String userName = LoginController.currentUser;
			result.put("editStatus", ser.editShippingLine(splModel, userName));
			System.err.println(splModel);
			log.debug("editShippingline successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("editShippingline with params 'fm' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete a shippingline
	 * 
	 * @param shippinglineCode
	 * @return delete status
	 */
	@RequestMapping(value = "/shippingline/delete", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteShippingline(
			@RequestBody ShippinglineModel splModel) {
		log.info(String.format("deleteShippingline in class %s", getClass()));
		try {
			log.debug("deleteShippingline and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("deleteStatus", ser.delete(splModel.getShippinglinecode()));
			log.debug("deleteShippingline successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"deleteShippingline in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}
}
