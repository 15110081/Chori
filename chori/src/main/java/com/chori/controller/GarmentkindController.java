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
import com.chori.service.GarmentkindService;
import com.chori.service.RoleService;

@RestController
@RequestMapping(value = "/")
public class GarmentkindController {
	private static final Log log = LogFactory
			.getLog(GarmentkindController.class);

	@Autowired
	GarmentkindService service;

	@Autowired
	RoleService roleSer;

	@Autowired
	@Qualifier("garmentkindValidator")
	private Validator splvalidator;

	@InitBinder("garmentkindValidator")
	private void initBinder_GarmentkindValidator(WebDataBinder binder) {
		binder.setValidator(splvalidator);
	}

	/**
	 * This method is used to get all garment kind in database and return a list
	 * garment kind in json
	 * 
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/garmentkind/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllGarmentkind() {
		log.info(String.format("getAllGarmentkind in class %s", getClass()));
		try {
			log.debug("getting list of all Garmentkind and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<GarmentkindModel> ls = service.getAllGarmentkind();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllGarmentkind successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllGarmentkind in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * Return list view page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listgarmentkind", method = RequestMethod.GET)
	public ModelAndView listGarmentkind(HttpServletResponse response) {
		log.info(String.format(
				"listgarmentkind with param 'response' in class: %s",
				getClass()));
		try {
			log.debug("return listgarmentkind view for request");
			response.setContentType("text/html");
			log.debug("listgarmentkind successful");

			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Garment Kind")) {
				return new ModelAndView(
						"configuration/garmentkind/listGarmentkind");
			}
			return new ModelAndView("login/accessDenied");

		} catch (Exception e) {
			log.error(String
					.format("listgarmentkind with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a garmentkind with given gmkCodeis existed on
	 * database.
	 * 
	 * @param gmkCode
	 * @return JSON value
	 */
	@RequestMapping(value = "/garmentkind/isExist/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isGarmentkindExist(@RequestBody GarmentkindModel garmentkindModel) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "ok");
		result.put("isExisted", service.isGarmentkindExistedById(garmentkindModel.getGarmentkindcode()));
		return result;
	}

	/**
	 * This function is used to add new garmentkind, return addStatus as JSON
	 * format
	 * 
	 * @param garmentkind
	 * @return status, addStatus as JSON format
	 */
	@ResponseBody
	@RequestMapping(value = "/garmentkind/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addGarmentkind(
			@RequestBody GarmentkindModel garmentkindMo) {
		log.info(String.format(
				"addGarmentkind with param 'garmentkindMo' in class: %s", getClass()));
		try {
			log.debug("add 1 garmentkind and return edit width as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String username = LoginController.currentUser;
			result.put("addGarmentkind",
					service.addGarmentkind(garmentkindMo, username));
			log.debug("addGarmentkind successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addGarmentkind with param 'garmentkindMo' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function find a garmentkind by garmentkindCode then return it as
	 * JSON format
	 * 
	 * @param garmentkindCode
	 * @return JSON format of a Garmentkind
	 */
	@RequestMapping(value = "/garmentkind/detail/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getGarmentkindDetail(
			@RequestBody GarmentkindModel garmentkindModel) {
		log.info(String
				.format("getGarmentkindDetail with param 'garmentkindCode' in class: %s",
						getClass()));
		try {
			log.debug("getting garmentkind's detail by its garmentkindCode and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			GarmentkindModel gmkMo = service
					.findGarmentkindModelById(garmentkindModel.getGarmentkindcode());
			result.put("currentgarmentkind", gmkMo);
			result.put("status", "ok");
			log.debug("getGarmentkindDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getGarmentkindDetail with param 'garmentkindCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function edit a garmentkind and update into database
	 * 
	 * @param garmentkind
	 * @return status, editStatus as JSON
	 */
	@ResponseBody
	@RequestMapping(value = "/garmentkind/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editGarmentkind(
			@RequestBody GarmentkindModel garmentkindModel) {
		log.info(String.format("editUnit with param 'garmentkindModel' in class: %s",
				getClass()));
		try {
			log.debug("edit 1 garmentkind and return edit garmentkind as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("editGarmentkind",
					service.editGarmentkind(garmentkindModel));
			log.debug("editGarmentkind successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"editGarmentkind param 'garmentkindModel' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete an garmentkind
	 * 
	 * @param gmkCode
	 * @return status, deleteStatus as JSON
	 */
	@RequestMapping(value = "/garmentkind/delete/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> deleteGarmentkind(@RequestBody GarmentkindModel garmentkindModel) {
		log.info(String.format("deleteGarmentkind in class %s", getClass()));
		try {
			log.debug("deleteGarmentkind and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("deleteGarmentkind", service.deleteGarmentkind(garmentkindModel.getGarmentkindcode()));
			log.debug("deleteGarmentkind successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"deleteGarmentkind in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}
}
