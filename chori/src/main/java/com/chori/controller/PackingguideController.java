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

import com.chori.model.GarmentkindModel;
import com.chori.model.PackingguideModel;
import com.chori.service.GarmentkindService;
import com.chori.service.PackingguideService;
import com.chori.service.RoleService;

@RestController
@RequestMapping(value = "/")
public class PackingguideController {
	private static final Log log = LogFactory
			.getLog(GarmentkindController.class);

	@Autowired
	PackingguideService service;

	@Autowired
	RoleService roleSer;
	
	/**
	 * This method is used to get all packkinguide in database and return a list
	 * packing guide in json
	 * 
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/packingguide/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllPackingguide() {
		log.info(String.format("getAllPackingguide in class %s", getClass()));
		try {
			log.debug("getting list of all Packingguide and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<PackingguideModel> ls = service.getAllPackingguide();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllPackingguide successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllPackingguide in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}
	/**
	 * Return list view page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listpackingguide", method = RequestMethod.GET)
	public ModelAndView listPackingguide(HttpServletResponse response) {
		log.info(String.format(
				"listpackingguide with param 'response' in class: %s",
				getClass()));
		try {
			log.debug("return listpackingguide view for request");
			response.setContentType("text/html");
			log.debug("listpackingguide successful");

			LoginController.AssignCurrentUser();
			
			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Packing Guide")) {
				return new ModelAndView(
						"configuration/packingguide/listPackingguide");
			}
			return new ModelAndView("login/accessDenied");

		} catch (Exception e) {
			log.error(String
					.format("listpackingguide with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function check if a packingguide with given pkgCodeis existed on
	 * database.
	 * 
	 * @param pkgCode
	 * @return JSON value
	 */
	@RequestMapping(value = "/packingguide/isExist/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isPackingguideExist(@RequestBody PackingguideModel packingguideModel) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "ok");
		result.put("isExisted", service.isPackkinguideExistedById(packingguideModel.getPackingguidecode()));
		return result;
	}
	
	/**
	 * This function is used to add new packingguide, return addStatus as JSON
	 * format
	 * 
	 * @param packingguide
	 * @return status, addStatus as JSON format
	 */
	@ResponseBody
	@RequestMapping(value = "/packingguide/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addPackingguide(
			@RequestBody PackingguideModel packingguideModel) {
		log.info(String.format(
				"addPackingguide with param 'packingguideModel' in class: %s", getClass()));
		try {
			log.debug("add 1 addPackingguide and return edit width as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String username = LoginController.currentUser;
			result.put("addPackingguide",
					service.addPackingguide(packingguideModel, username));
			log.debug("addPackingguide successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addPackingguide with param 'packingguideModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function find a packingguide by pkgCode then return it as
	 * JSON format
	 * 
	 * @param pkgCode
	 * @return JSON format of a packingguide
	 */
	@RequestMapping(value = "/packingguide/detail", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPackingguideDetail(
			@RequestBody PackingguideModel packingguideModel) {
		log.info(String
				.format("getPackingguideDetail with param 'packingguideModel' in class: %s",
						getClass()));
		try {
			log.debug("getting packingguide's detail by its packingguideModel and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			PackingguideModel packingguide = service
					.findPackingguideModelById(packingguideModel.getPackingguidecode());
			result.put("currentpackingguide", packingguide);
			result.put("status", "ok");
			log.debug("getPackingguideDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getPackingguideDetail with param 'packingguideModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function edit a packingguide and update into database
	 * 
	 * @param packingguide
	 * @return status, editStatus as JSON
	 */
	@ResponseBody
	@RequestMapping(value = "/packingguide/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editPackingguide(
			@RequestBody PackingguideModel packingguideModel) {
		log.info(String.format("editpackingguide with param 'packingguideModel' in class: %s",
				getClass()));
		try {
			log.debug("edit 1 packingguide and return edit packingguide as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("editPackingguide",
					service.editPackingguide(packingguideModel));
			log.debug("editGarmentkind successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"editPackingguide param 'packingguideModel' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete an packingguide
	 * 
	 * @param pkgCode
	 * @return status, deleteStatus as JSON
	 */
	@RequestMapping(value = "/packingguide/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> deletePackingguide(@RequestBody PackingguideModel packingguideModel) {
		log.info(String.format("deletePackingguide in class %s", getClass()));
		try {
			log.debug("deletePackingguide and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("deletePackingguide", service.deletePackingguide(packingguideModel));
			log.debug("deletePackingguide successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"deletePackingguide in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}
	

}
