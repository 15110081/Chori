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
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chori.model.UnitModel;
import com.chori.service.RoleService;
import com.chori.service.UnitService;

@RestController
@RequestMapping(value = "/")
public class UnitController {

	private static final Log log = LogFactory.getLog(UnitController.class);

	@Autowired
	UnitService service;
	@Autowired
	RoleService roleSer;
	/**
	 * Validator for unit
	 */
	@Autowired
	@Qualifier("unitValidator")
	private Validator splvalidator;

	@InitBinder("unitValidator")
	private void initBinder_UnitValidator(WebDataBinder binder) {
		binder.setValidator(splvalidator);
	}

	/**
	 * Return view page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/unit/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllUnit() {
		log.info(String.format("getAllUnit in class %s", getClass()));
		try {
			log.debug("getting list of all unit and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<UnitModel> ls = service.getAllUnitModel();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllunit successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getAllunit in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}

	}

	/**
	 * This method is used to get all unit in database and return a list unit in
	 * json
	 * 
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/listunit", method = RequestMethod.GET)
	public ModelAndView listUnit(HttpServletResponse response) {
		log.info(String.format("listunit with param 'response' in class: %s",
				getClass()));
		try {
			log.debug("return listsunit view for request");
			response.setContentType("text/html");
			log.debug("liststatus successful");
			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Unit Management")) {
				return new ModelAndView("/configuration/unit/list");
			}
			return new ModelAndView("login/accessDenied");
		} catch (Exception e) {
			log.error(String
					.format("liststatus with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}

	/**
	 * This function find a unit by unitCode then return it as JSON format
	 * 
	 * @param unitCode
	 * @return JSON format of a unit
	 */
	@RequestMapping(value = "/unit/detail/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUnitDetail(@RequestBody UnitModel um) {
		
		log.info(String.format(
				"getUnitDetail with param 'unitCode' in class: %s", getClass()));
		try {
			log.debug("getting unit's detail by its unitCode and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			
			result.put("currentunit",  service.findUnitModelById(um.getUnitcode()));
			result.put("status", "ok");
			log.debug("getStatusDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getUnitDetail with param 'unitCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function edit a unit and update into database
	 * 
	 * @param unit
	 * @return unit, editUnit as JSON
	 */
	@ResponseBody
	@RequestMapping(value = "/unit/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editUnit(@RequestBody UnitModel unitEn) {
		log.info(String.format("editUnit with param 'unit' in class: %s",
				getClass()));
		try {
			log.debug("edit 1 status and return edit status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("editUnit", service.editUnit(unitEn));
			log.debug("editUnit successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"editUnit param 'unit' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete 1 unit
	 * 
	 * @param unitCode
	 * @return
	 */
	@RequestMapping(value = "/unit/delete/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> deleteUnit(@RequestBody UnitModel um) {
		log.info(String.format(
				"delete unit with param 'unitcode' in class: %s", getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("deleteUnit", service.deleteUnit(um.getUnitcode()));
			log.debug("deleteunit successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("delete unit with param 'unitId' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}

	/**
	 * This function add a unit and update into database
	 */
	@ResponseBody
	@RequestMapping(value = "/unit/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addUnit(@RequestBody UnitModel unitEn) {
		log.info(String.format("addUnit with param 'order' in class: %s",
				getClass()));
		try {
			log.debug("add 1 unit and return edit width as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("addUnit", service.addUnit(unitEn));
			log.debug("addUnit successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"addWidth with param 'order' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a Unit with given UnitCode is existed on database.
	 * 
	 * @param UnitCode
	 * @return JSON value
	 */
	@RequestMapping(value = "/unit/isExist/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isUnitExist(@RequestBody UnitModel um) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "ok");
		result.put("isExisted", service.isUnitExistedById(um.getUnitcode()));
		return result;
	}

}
