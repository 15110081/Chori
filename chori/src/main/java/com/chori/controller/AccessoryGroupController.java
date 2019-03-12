package com.chori.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.ModelAndView;

import com.chori.model.AccessoryGroupModel;
import com.chori.service.AccessoryGroupService;
import com.chori.service.RoleService;

@Controller
@RequestMapping(value = "/")
public class AccessoryGroupController {
	private static final Log log = LogFactory
			.getLog(AccessoryGroupController.class);

	@Autowired
	AccessoryGroupService service;
	@Autowired
	RoleService roleSer;
	/**
	 * Validator for accessoryGroup
	 */
	@Autowired
	@Qualifier("accessoryGroupValidator")
	private Validator splvalidator;

	@InitBinder("accessoryGroupValidator")
	private void initBinder_AccessoryGroupValidator(WebDataBinder binder) {
		binder.setValidator(splvalidator);
	}

	/**
	 * This method is used to get all AccessoryGroup in database and return a
	 * list accessory group in json
	 * 
	 * @return Map<String, Object>
	 */

	@RequestMapping(value = "/accessorygroup/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllAccessoryGroup() {
		log.info(String.format("getAllAccessoryGroup in class %s", getClass()));
		try {
			log.debug("getting list of all AccessoryGroup and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<AccessoryGroupModel> ls = service.getAllAccessoryGroup();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllAccessoryGroup successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllAccessoryGroup in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * Return list view page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listAccessoryGroup", method = RequestMethod.GET)
	public ModelAndView listAccessoryGroup(HttpServletResponse response) {
		log.info(String.format(
				"listAccessoryGroup with param 'response' in class: %s",
				getClass()));
		try {
			log.debug("return listAccessoryGroup view for request");
			response.setContentType("text/html");
			log.debug("listAccessoryGroup successful");
			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Accessory Group")) {
				return new ModelAndView("configuration/accessorygroup/list");
			}
			return new ModelAndView("login/accessDenied");

		} catch (Exception e) {
			log.error(String
					.format("listAccessoryGroup with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete an AccessoryGroup
	 * 
	 * @param asgrCode
	 * @return status, deleteAccessoryGroup as JSON
	 */
	@RequestMapping(value = "/accessorygroup/delete/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> deleteAccessoryGroup(
			@RequestBody AccessoryGroupModel agm) {
		log.info(String.format("deleteAccessoryGroup in class %s", getClass()));
		try {
			log.debug("deleteAccessoryGroup and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("deleteAccessoryGroup",
					service.deleteAccessoryGroup(agm.getAccessorygroupCode()));
			log.debug("deleteAccessoryGroup successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"deleteAccessoryGroup in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a AccessoryGroup with given asgrCode is existed on
	 * database.
	 * 
	 * @param accessoryGroupCode
	 * @return JSON value
	 */
	@RequestMapping(value = "/accessorygroup/isExist/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isAccessoryGroupExist(
			@RequestBody AccessoryGroupModel agm) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "ok");
		result.put("isExisted", service.isAccessoryGroupExistedById(agm.getAccessorygroupCode()));
		return result;
	}

	/**
	 * This function is used to add new AccessoryGroup, return addAccessoryGroup
	 * as JSON format
	 * 
	 * @param AccessoryGroup
	 * @return AccessoryGroup, addAccessoryGroup as JSON format
	 */
	@ResponseBody
	@RequestMapping(value = "/accessorygroup/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addAccessoryGroup(
			@RequestBody AccessoryGroupModel accessoryGroupModel) {
		log.info(String
				.format("addAccessoryGroup with param 'accessoryGroupModel' in class: %s",
						getClass()));
		try {
			log.debug("add 1 accessoryGroup and return edit accessoryGroup as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String username = "admin";
			result.put("addAccessoryGroup",
					service.addAccessoryGroup(accessoryGroupModel, username));
			log.debug("addAccessoryGroup successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addAccessoryGroup with param 'accessoryGroupModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function find a AccessoryGroup by asgrCode then return it as JSON
	 * format
	 * 
	 * @param asgrCode
	 * @return JSON format of a AccessoryGroup
	 */
	@RequestMapping(value = "/accessorygroup/detail/",  produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getAccessoryGroupDetail(
			@RequestBody AccessoryGroupModel agm) {
		log.info(String
				.format("getColorDetail with param 'asgrCode' in class: %s",
						getClass()));
		try {
			log.debug("getting color's detail by its colorId and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("currentAccessoryGroup",  service.findAccessoryGroupById(agm.getAccessorygroupCode()));
			result.put("status", "ok");
			log.debug("getAccessoryGroupDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getAccessoryGroupDetail with param 'asgrCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function edit a AccessoryGroup and update into database
	 * 
	 * @param AccessoryGroup
	 * @return AccessoryGroup, editAccessoryGroup as JSON
	 */
	@ResponseBody
	@RequestMapping(value = "/accessorygroup/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editAccessoryGroup(
			@RequestBody AccessoryGroupModel accessoryGroupModel) {
		log.info(String
				.format("editAccessoryGroup with param 'accessoryGroupModel' in class: %s",
						getClass()));
		try {
			log.debug("edit 1 AccessoryGroup and return edit AccessoryGroup as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("editAccessoryGroup",
					service.editAccessoryGroup(accessoryGroupModel));
			log.debug("editAccessoryGroup successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("editAccessoryGroup param 'accessoryGroupcode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}