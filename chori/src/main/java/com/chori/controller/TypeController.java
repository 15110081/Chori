package com.chori.controller;

import java.io.IOException;
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

import com.chori.model.TypeModel;
import com.chori.service.RoleService;
import com.chori.service.TypeService;

@RestController
@RequestMapping(value = "/")
public class TypeController {
	private static final Log log = LogFactory.getLog(TypeController.class);
	@Autowired
	TypeService service;
	@Autowired
	RoleService roleSer;
	/**
	 * Validator for type
	 */

	@Autowired
	@Qualifier("typeValidator")
	private Validator splvalidator;

	@InitBinder("typeValidator")
	private void initBinder_TypeValidator(WebDataBinder binder) {
		binder.setValidator(splvalidator);
	}

	/**
	 * This method is used to get all type in database and return a list type in
	 * json
	 * 
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/type/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllType() {
		log.info(String.format("getAllType in class %s", getClass()));
		try {
			log.debug("getting list of all type and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<TypeModel> ls = service.getAllTypeModel();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllType successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getAllType in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}

	}

	/**
	 * Return view page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listSizeGroup", method = RequestMethod.GET)
	public ModelAndView listType(HttpServletResponse response) {
		log.info(String.format("listtype with param 'response' in class: %s",
				getClass()));
		try {
			log.debug("return liststype view for request");
			response.setContentType("text/html");
			log.debug("liststype successful");
			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Type Management")) {
				return new ModelAndView("/configuration/type/list");
			}
			return new ModelAndView("login/accessDenied");
		} catch (Exception e) {
			log.error(String.format(
					"listType with param 'response' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}

	}

	/**
	 * This function find a type by type code then return it as JSON format
	 * 
	 * @param typecode
	 * @return JSON format of a type
	 */
	@RequestMapping(value = "/type/detail/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTypeDetail(@RequestBody TypeModel tm) {
		log.info(String.format(
				"getTypeDetail with param 'tm' in class: %s", getClass()));
		try {
			log.debug("getting type's detail by its typeCode and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			TypeModel ue = service.findTypeModelById(tm.getTypeCode());
			result.put("currentType", ue);
			result.put("status", "ok");
			log.debug("getTypeDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getTypeDetail with param 'typeCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to update an type
	 * 
	 * @param typeModel
	 * @param result
	 * @return
	 * @throws IOException
	 */

	@ResponseBody
	@RequestMapping(value = "/type/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editType(@RequestBody TypeModel typeModel) {
		log.info(String.format("editType with param 'type' in class: %s",
				getClass()));
		try {
			log.debug("edit 1 type and return edit type as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String userName = "admin";
			result.put("editType", service.editType(typeModel, userName));
			log.debug("editType successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"editType param 'type' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete 1 type
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/type/delete/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> deleteType(@RequestBody TypeModel tm) {
		log.info(String.format(
				"delete unit with param 'typeCode' in class: %s", getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("deleteType", service.deleteType(tm.getTypeCode()));
			log.debug("deleteType successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("delete type with param 'typeCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}

	/**
	 * This function handle request for add new Type
	 * 
	 * @param TypeModel
	 * @param result
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/type/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addType(@RequestBody TypeModel typeModel) {
		log.info(String.format("addType with param 'TypeModel' in class: %s",
				getClass()));
		try {
			log.debug("add 1 type and return edit type as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String userName = "admin";

			result.put("addType", service.addType(typeModel, userName));
			log.debug("addType successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"addType with param 'typeModel' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a type with given typeCode is existed on database.
	 * 
	 * @param typeCode
	 * @return JSON value
	 */
	@RequestMapping(value = "/type/isExist/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isTypeExist(@RequestBody TypeModel tm) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "ok");
		result.put("isExisted", service.isTypeExistedById(tm.getTypeCode()));
		return result;
	}

}
