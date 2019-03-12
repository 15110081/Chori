package com.chori.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.chori.model.SizeModel;
import com.chori.service.RoleService;
import com.chori.service.SizeService;

@Controller
@RequestMapping(value = "/")
public class SizeController {

	private static final Log log = LogFactory.getLog(SizeController.class);

	@Autowired
	SizeService ser;

	@Autowired
	RoleService roleSer;

	/**
	 * Return list view page for Size Management
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/Size" }, method = RequestMethod.GET)
	public String listSize() {
		log.info(String.format("listSize with param 'response' in class: %s",
				getClass()));
		try {
			LoginController.AssignCurrentUser();
			log.debug("return listSize view for request");
			log.debug("listSize successful");
			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Size Management")) {
				return "configuration/size/list";
			}
			return "login/accessDenied";
		} catch (Exception e) {
			log.error(String.format(
					"listSize with param 'response' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method is used to get all size in database
	 * 
	 * @return a list size in JSON format
	 */
	@RequestMapping(value = "/size/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllSize() {
		log.info(String.format("getAllSize in class %s", getClass()));
		try {
			log.debug("getting list of all size and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<SizeModel> ls = ser.getAllSizeModel();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllSize successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getAllSize in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method is used to get list size of a customer in JSON format
	 * 
	 * @return a list size of a customer in JSON format
	 */
	@RequestMapping(value = "/size/list/{customerCode}", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllSizeByCustomer(
			@PathVariable String customerCode) {
		log.info(String.format("getAllSizeByCustomer in class %s", getClass()));
		try {
			log.debug("getting list of all size and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<SizeModel> ls = ser.findSizeCodeByCustomerCode(customerCode);
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllSizeByCustomer successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllSizeByCustomer in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete a size
	 * 
	 * @param sizecode
	 * @return deleteStatus
	 */
	@RequestMapping(value = "/size/delete/{sizecode}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> deleteSize(@PathVariable Integer sizecode) {
		log.info(String.format("deleteSize with param 'sizecode' in class: %s",
				getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("deleteStatus", ser.deleteSizeById(sizecode));
			log.debug("deleteSize successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("deleteSize with param 'sizecode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function find a size by id then return it as JSON format
	 * 
	 * @param sizecode
	 * @return size detail in JSON format
	 */
	@RequestMapping(value = "/size/detail/{sizecode}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getSizeDetail(@PathVariable Integer sizecode) {
		log.info(String.format(
				"getSizeDetail with param 'sizecode' in class: %s", getClass()));
		try {
			log.debug("getting size detail by its {sizecode} and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("size", ser.findSizeModelById(sizecode));
			log.debug("getSizeDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getSizeDetail with param 'sizecode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function edit a size and update into database
	 * 
	 * @param sizeModel
	 * @return editStatus as JSON
	 */
	@ResponseBody
	@RequestMapping(value = "/size/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editSize(@RequestBody SizeModel sizeModel) {
		log.info(String.format("editSize with param 'sizeModel' in class: %s",
				getClass()));
		try {
			log.debug("edit 1 size and return edit status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("editStatus", ser.editSize(sizeModel));
			log.debug("editSize successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("editSize with param 'sizeModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a size with given sizeModel is existed on
	 * database.
	 * 
	 * @param sizeModel
	 * @return is existed status JSON value
	 */
	@RequestMapping(value = "/size/isExist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isSizeExistedById(
			@RequestBody SizeModel sizeModel) {
		log.info(String
				.format("isAccessoryConsumptionExist with param 'sizeModel' in class: %s",
						getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("isExisted", ser.isSizeExisted(sizeModel));
			log.debug("check isSizeExistedById successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isSizeExistedById with param 'sizeModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new size
	 * 
	 * @param sizeModel
	 * @return addStatus as JSON format
	 */
	@ResponseBody
	@RequestMapping(value = "/size/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addSize(@RequestBody SizeModel sizeModel) {
		log.info(String.format("addSize with param 'sizeModel' in class: %s",
				getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String userName = LoginController.currentUser;
			result.put("addStatus", ser.addSize(sizeModel, userName));
			log.debug("addSize successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"addSize with param 'sizeModel' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to duplicate all size of a customer
	 * 
	 * @param customerCodeFrom
	 *            ,customerCodeTo
	 * @return addStatus as JSON format
	 */
	@ResponseBody
	@RequestMapping(value = "/size/duplicate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> duplicateSize(@RequestBody SizeModel sizeModel) {
		log.info(String
				.format("duplicateSize with param 'customerCodeFrom,customerCodeTo' in class: %s",
						getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String userName = LoginController.currentUser;
			result.put("duplicateStatus", ser.duplicateSize(sizeModel.getCustomerCodeFrom(),
					sizeModel.getCustomerCodeTo(), userName));
			log.debug("duplicateSize successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("duplicateSize with param 'customerCodeFrom,customerCodeTo' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
