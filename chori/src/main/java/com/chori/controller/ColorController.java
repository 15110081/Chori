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

import com.chori.model.ColorModel;
import com.chori.service.ColorService;
import com.chori.service.RoleService;

@RestController
@RequestMapping(value = "/")
public class ColorController {
	private static final Log log = LogFactory.getLog(ColorController.class);

	@Autowired
	ColorService service;

	@Autowired
	RoleService roleSer;

	@Autowired
	@Qualifier("colorValidator")
	private Validator splvalidator;

	@InitBinder("colorValidator")
	private void initBinder_ColorValidator(WebDataBinder binder) {
		binder.setValidator(splvalidator);
	}

	/**
	 * This method is used to get all color in database and return a list
	 * accessory consumption in json
	 * 
	 * @return Map<String, Object>
	 */

	@RequestMapping(value = "/color/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllColor() {
		log.info(String.format("getAllColor in class %s", getClass()));
		try {
			log.debug("getting list of all color and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<ColorModel> ls = service.getAllColor();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllColor successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getAllColor in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * Return list view page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listcolor", method = RequestMethod.GET)
	public ModelAndView listColor(HttpServletResponse response) {
		log.info(String.format("listcolor with param 'response' in class: %s",
				getClass()));
		try {
			log.debug("return listcolor view for request");
			response.setContentType("text/html");
			log.debug("listcolor successful");

			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Color Management")) {
				return new ModelAndView("configuration/color/list");
			}
			return new ModelAndView("login/accessDenied");

		} catch (Exception e) {
			log.error(String
					.format("listcolor with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete an color
	 * 
	 * @param clId
	 * @return status, deleteStatus as JSON
	 */
	@RequestMapping(value = "/color/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> deleteColor(@RequestBody ColorModel colorModel) {
		log.info(String.format("deleteColor in class %s", getClass()));
		try {
			log.debug("deleteColor and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("deleteColor", service.deleteColor(colorModel));
			log.debug("deleteColor successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("deleteColor in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a color with given colorCode is existed on
	 * database.
	 * 
	 * @param colorcode
	 * @return JSON value
	 */
	@RequestMapping(value = "/color/isExist/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isColorExist(@RequestBody ColorModel colorModel) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "ok");
		result.put("isExisted", service.isColorExistedById(colorModel.getColorcode()));
		return result;
	}

	/**
	 * This function is used to add new Color, return addColor as JSON format
	 * 
	 * @param color
	 * @return color, addColor as JSON format
	 */
	@ResponseBody
	@RequestMapping(value = "/color/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addColor(@RequestBody ColorModel colorMo) {
		log.info(String.format("addColor with param 'colorMo' in class: %s",
				getClass()));
		try {
			log.debug("add 1 color and return edit width as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String username = LoginController.currentUser;
			result.put("addColor", service.addColor(colorMo, username));
			log.debug("addColor successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"addColor with param 'colorMo' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function find a Color by colorid then return it as JSON format
	 * 
	 * @param colorid
	 * @return JSON format of a color
	 */
	@RequestMapping(value = "/color/detail", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getColorDetail(@RequestBody ColorModel colorModel) {
		log.info(String.format(
				"getColorDetail with param 'colorId' in class: %s", getClass()));
		try {
			log.debug("getting color's detail by its colorId and return json");
			Map<String, Object> result = new HashMap<String, Object>();

			ColorModel color = service.findColorModelById(colorModel.getColorcode());
			result.put("currentcolor", color);
			result.put("status", "ok");
			log.debug("getColorDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getColorDetail with param 'colorId' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function edit a color and update into database
	 * 
	 * @param color
	 * @return color, editColor as JSON
	 */
	@ResponseBody
	@RequestMapping(value = "/color/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editColor(@RequestBody ColorModel colorMo) {
		log.info(String.format(
				"editColor with param 'colorModel' in class: %s", getClass()));
		try {
			log.debug("edit 1 color and return edit color as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			
			result.put("status", "ok");
			result.put("editColor", service.editColor(colorMo));
			log.debug("editColor successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("editDestination param 'colorcode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
