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

import com.chori.model.WidthModel;
import com.chori.service.RoleService;
import com.chori.service.WidthService;

@RestController
@RequestMapping(value = "/")
public class WidthController {
	private static final Log log = LogFactory.getLog(WidthController.class);

	@Autowired
	WidthService service;

	@Autowired
	RoleService roleSer;

	@Autowired
	@Qualifier("widthValidator")
	private Validator splvalidator;

	@InitBinder("widthValidator")
	private void initBinder_WidthValidator(WebDataBinder binder) {
		binder.setValidator(splvalidator);
	}

	/**
	 * This method is used to get all width in database and return a list width
	 * in json
	 * 
	 * @return Map<String, Object>
	 */

	@RequestMapping(value = "/width/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllWidth() {
		log.info(String.format("getAllWidth in class %s", getClass()));
		try {
			log.debug("getting list of all width and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<WidthModel> ls = service.getAllWidthModel();
			result.put("width", "ok");
			result.put("list", ls);
			log.debug("getAllWidth successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getAllWidth in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}

	}

	/**
	 * Return view page
	 * 
	 * @return
	 */

	@RequestMapping(value = "/listwidth", method = RequestMethod.GET)
	public ModelAndView listWidth(HttpServletResponse response) {
		log.info(String.format("listwidth with param 'response' in class: %s",
				getClass()));
		try {
			log.debug("return listwidth view for request");
			response.setContentType("text/html");
			log.debug("listwidth successful");
			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Width Management")) {
				return new ModelAndView("configuration/width/list");
			}
			return new ModelAndView("login/accessDenied");
		} catch (Exception e) {
			log.error(String
					.format("listwidth with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}

	/**
	 * This function is used to get detail of 1 width
	 * 
	 * @param widthMod
	 * @return
	 */
	@RequestMapping(value = "/width/detail", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getWidthDetail(@RequestBody WidthModel widthMod) {
		log.info(String.format(
				"getWidthDetail with param 'widthId' in class: %s", getClass()));
		try {
			log.debug("getting width's detail by its widthId and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			WidthModel en = service
					.findWidthEntityById(widthMod.getWidthcode());
			result.put("currentwidth", en);
			result.put("width", "ok");
			log.debug("getWidthDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getWidthDetail with param 'widthId' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to update an width
	 * 
	 * @param widthMod
	 * @param result
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/width/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editWidth(@RequestBody WidthModel widthMod) {
		log.info(String.format("editWidth with param 'widthMod' in class: %s",
				getClass()));
		try {
			log.debug("edit 1 width and return edit width as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			String userName = LoginController.currentUser;
			result.put("width", "ok");
			result.put("editWidth", service.editWidth(widthMod, userName));
			log.debug("editWidth successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"editWidth with param 'widthMod' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if an width is existed
	 * 
	 * @param widthCode
	 * @return
	 */
	@RequestMapping(value = "/width/isExist/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isWidthExist(@RequestBody WidthModel widthMod) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "ok");
		result.put("isExisted", service.isWidthExistedById(widthMod.getWidthcode()));
		return result;
	}

	/**
	 * This function handle request for add new Width
	 * 
	 * @param widthAddModel
	 * @param result
	 * @return
	 * @throws IOException
	 */

	@ResponseBody
	@RequestMapping(value = "/width/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addWidth(@RequestBody WidthModel widthMod) {
		log.info(String.format("addWidth with param 'widthMod' in class: %s",
				getClass()));
		try {
			log.debug("add 1 width and return add width as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			String userName = LoginController.currentUser;
			result.put("width", "ok");
			result.put("addWidth", service.addWidth(widthMod, userName));
			log.debug("addWidth successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"addWidth with param 'order' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete 1 width
	 * 
	 * @param widthMod
	 * @return
	 */

	@RequestMapping(value = "/width/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, Object> deleteWidth(@RequestBody WidthModel widthMod) {
		log.info(String.format(
				"delete width with param 'widthMod' in class: %s", getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("width", "ok");
			result.put("deleteWidth", service.deleteWidth(widthMod));
			log.debug("delete width successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("delete width with param 'widthMod' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}

}