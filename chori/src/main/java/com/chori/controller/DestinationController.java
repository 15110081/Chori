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

import com.chori.model.DestinationModel;
import com.chori.service.DestinationService;
import com.chori.service.RoleService;

@RestController
@RequestMapping(value = "/")
public class DestinationController {
	private static final Log log = LogFactory
			.getLog(DestinationController.class);

	@Autowired
	DestinationService service;

	@Autowired
	RoleService roleSer;

	@Autowired
	@Qualifier("destinationValidator")
	private Validator splvalidator;

	@InitBinder("destinationValidator")
	private void initBinder_DestinationValidator(WebDataBinder binder) {
		binder.setValidator(splvalidator);
	}

	/**
	 * This method is used to get all destination in database and return a list
	 * destination in json
	 * 
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/destination/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllDestination() {
		log.info(String.format("getAllDestination in class %s", getClass()));
		try {
			log.debug("getting list of all Destination and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<DestinationModel> ls = service.getAllDestination();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllDestination successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllDestination in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * Return list view page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listdestination", method = RequestMethod.GET)
	public ModelAndView listDestination(HttpServletResponse response) {
		log.info(String.format(
				"listdestination with param 'response' in class: %s",
				getClass()));
		try {
			log.debug("return listdestination view for request");
			response.setContentType("text/html");
			log.debug("listdestination successful");

			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Destination Management")) {
				return new ModelAndView(
						"configuration/destination/listDestination");
			}
			return new ModelAndView("login/accessDenied");

		} catch (Exception e) {
			log.error(String
					.format("listdestination with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new destination, return addStatus as JSON
	 * format
	 * 
	 * @param destination
	 * @return status, addStatus as JSON format
	 */
	@ResponseBody
	@RequestMapping(value = "/destination/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addDestination(
			@RequestBody DestinationModel destinationMo) {
		log.info(String.format(
				"addDestination with param 'destinationMo' in class: %s", getClass()));
		try {
			log.debug("add 1 destination and return edit width as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String username = LoginController.currentUser;
			result.put("addDestination",
					service.addDestination(destinationMo, username));
			log.debug("addDestination successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addDestination with param 'destinationMo' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function find a destination by destinationcode then return it as
	 * JSON format
	 * 
	 * @param destinationcode
	 * @return JSON format of a Destination
	 */
	@RequestMapping(value = "/destination/detail", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDestinationDetail(
			@RequestBody DestinationModel destinationModel) {
		log.info(String
				.format("getDestinationDetail with param 'destinationcode' in class: %s",
						getClass()));
		try {
			log.debug("getting destination's detail by its destinationcode and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			DestinationModel destinationMo = service
					.findDestinationModelById(destinationModel.getDestinationcode());
			result.put("currentdestination", destinationMo);
			result.put("status", "ok");
			log.debug("getDestinationDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getDestinationDetail with param 'destinationcode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function edit a destination and update into database
	 * 
	 * @param destination
	 * @return status, editStatus as JSON
	 */
	@ResponseBody
	@RequestMapping(value = "/destination/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editDestination(
			@RequestBody DestinationModel destinationMo) {
		log.info(String.format(
				"editDestination with param 'destinationcode' in class: %s",
				getClass()));
		try {
			log.debug("edit 1 destination and return edit destination as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("editDestination",
					service.editDestiantion(destinationMo));
			log.debug("editDestination successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("editDestination param 'destinationcode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete an destination
	 * 
	 * @param destination
	 * @return status, deleteStatus as JSON
	 */
	@RequestMapping(value = "/destination/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> deleteDestination(
			@RequestBody DestinationModel destinationModel) {
		log.info(String.format("deleteDestination in class %s", getClass()));
		try {
			log.debug("deleteDestination and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("deleteDestination",
					service.deleteDestination(destinationModel));
			log.debug("deleteDestination successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"deleteDestination in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a destination with given destination is existed on
	 * database.
	 * 
	 * @param destinationcode
	 * @return JSON value
	 */
	@RequestMapping(value = "/destination/isExist/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isDestinationExist(
			@RequestBody DestinationModel destinationModel) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "ok");
		result.put("isExisted",
				service.isDestinationExistedById(destinationModel.getDestinationcode()));
		return result;
	}

}
