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

import com.chori.model.EstimatetimeModel;
import com.chori.service.EstimatetimeService;
import com.chori.service.RoleService;

@RestController
@RequestMapping(value = "/")
public class EstimatetimeController {
	private static final Log log = LogFactory
			.getLog(EstimatetimeController.class);
	@Autowired
	EstimatetimeService service;
	@Autowired
	RoleService roleSer;
	/**
	 * Validator for estimatetime
	 */
	@Autowired
	@Qualifier("estimatetimeValidator")
	private Validator splvalidator;

	@InitBinder("estimatetimeValidator")
	private void initBinder_EstimatetimeValidator(WebDataBinder binder) {
		binder.setValidator(splvalidator);
	}

	/**
	 * This function is used to get detail of 1 estimatetime
	 * 
	 * @param estCode
	 * @return
	 */
	@RequestMapping(value = "/estimatetime/detail/{estCode}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getEstimatetimeDetail(@PathVariable int estCode) {
		log.info(String.format("getDetail with param 'estCode' in class: %s",
				getClass()));
		try {
			log.debug("getting width's detail by its estCode and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			EstimatetimeModel est = service.findEstimatetimeModelById(estCode);
			result.put("currentEstimatetime", est);
			result.put("status", "ok");
			log.debug("getEstimatetimeDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getEstimatetimeDetail with param 'estCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to update an estimatime
	 * 
	 * @param EstimatetimeModel
	 * @param result
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/estimatetime/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editEstimatetime(
			@RequestBody EstimatetimeModel estm) {
		log.info(String.format(
				"editWidth with param 'EstimatetimeModel' in class: %s",
				getClass()));
		try {
			log.debug("edit 1 estimatetime and return edit EstimatetimeModel as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			String userName = "admin";
			result.put("status", "ok");
			result.put("editEstimatetime",
					service.editEstimatetime(estm, userName));
			log.debug("editEstimatetime successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("editEstimatetime with param 'EstimatetimeModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method is used to get all estimatetime in database and return a list
	 * estimatetime in json
	 * 
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/estimatetime/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllEstimatetime() {
		log.info(String.format("getAllUnit in class %s", getClass()));
		try {
			log.debug("getting list of all unit and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<EstimatetimeModel> ls = service.getAllEstimatetimeModel();
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
	 * Return view page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listEstimatetime", method = RequestMethod.GET)
	public ModelAndView listEstimatetime(HttpServletResponse response) {
		log.info(String.format(
				"listEstimatetime with param 'response' in class: %s",
				getClass()));
		try {
			log.debug("return listEstimatetime view for request");
			response.setContentType("text/html");
			log.debug("listEstimatetime successful");
			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser,
					"ETOC Management")) {
				return new ModelAndView("configuration/estimatetime/list");
			}
			return new ModelAndView("login/accessDenied");

		} catch (Exception e) {
			log.error(String
					.format("listEstimatetime with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}
}
