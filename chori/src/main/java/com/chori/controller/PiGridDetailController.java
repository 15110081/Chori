package com.chori.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.ModelAndView;

import com.chori.model.PiGridDetailModel;
import com.chori.model.WidthModel;
import com.chori.service.PigriddetailService;

@Controller
@RequestMapping(value = "/")
public class PiGridDetailController {
	@Autowired
	PigriddetailService ser;
	private static final Log log = LogFactory
			.getLog(PiGridDetailController.class);

	/**
	 * This method is used to get all pi grid detail in database
	 * 
	 * @return a list pi grid detail in JSON format
	 */
	@RequestMapping(value = "/pigriddetail/getallbypigrid/{pigridCode}", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllPiGridDetail(@PathVariable int pigridCode) {
		log.info(String.format("getAllPiGridDetail in class %s", getClass()));
		try {
			log.debug("getting list of all getAllPiGridDetail and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<PiGridDetailModel> ls = ser.getAllPiGridDetailModelbyPigridCode(pigridCode);
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllPiGridDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllPiGridDetail in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}
	@RequestMapping(value = "/pigriddetail/detail", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPigriddetail(@RequestBody PiGridDetailModel pgdm) {
		log.info(String.format(
				"getPigriddetail with param 'PiGridDetailModel' in class: %s", getClass()));
		try {
			log.debug("getting width's detail by its widthId and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			PiGridDetailModel pg = ser.findPiGridDetailModelById(pgdm.getPigriddetail());
			result.put("currentPigriddetail", pg);
			result.put("PiGridDetail", "ok");
			log.debug("getPigriddetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getPigriddetail with param 'PiGridDetailModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/pigriddetail/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editPigriddetail(@RequestBody PiGridDetailModel pgdm) {
		log.info(String.format("editPigriddetail with param 'PiGridDetailModel' in class: %s",
				getClass()));
		try {
			log.debug("edit 1 PiGridDetail and return edit PiGridDetail as json format");
			Map<String, Object> result = new HashMap<String, Object>();
//			String userName = LoginController.currentUser;
			result.put("PiGridDetail", "ok");
			result.put("editPigriddetail", ser.editPigriddetail(pgdm));
			log.debug("editPigriddetail successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"editWidth with param 'PiGridDetailModel' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/pigriddetail/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addPiGridDetail(@RequestBody PiGridDetailModel pgdm) {
		log.info(String.format("addPiGridDetail with param 'PiGridDetail' in class: %s",
				getClass()));
		try {
			log.debug("add 1 PiGridDetail and return edit PiGridDetail as json format");
			Map<String, Object> result = new HashMap<String, Object>();
//			String userName = LoginController.currentUser;
			result.put("PiGridDetail", "ok");
			result.put("addPiGridDetail", ser.addPigriddetail(pgdm));
			log.debug("addPiGridDetail successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"addPiGridDetail with param 'PiGridDetail' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	@RequestMapping(value = "/listpigriddetail", method = RequestMethod.GET)
	public ModelAndView listPiGridDetail(HttpServletResponse response) {
		log.info(String
				.format("listpigriddetail with param 'response' in class: %s",
						getClass()));
		try {
			log.debug("return listpigriddetail view for request");
			response.setContentType("text/html");
			log.debug("listpigriddetail successful");
			return new ModelAndView("operation/pigriddetail/pigriddetail");
		} catch (Exception e) {
			log.error(String
					.format("listpigriddetail with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}
	@ResponseBody
	@RequestMapping(value = "/pigriddetail/deleteAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> deleteAllbyPigrid(@RequestBody Integer pigridCode) {
		log.info(String.format("deleteAllbyPigrid with param 'pigridCode' in class: %s",
				getClass()));
		try {
			log.debug("add 1 PiGridDetail and return edit PiGridDetail as json format");
			Map<String, Object> result = new HashMap<String, Object>();
//			String userName = LoginController.currentUser;
			result.put("PiGridDetail", "ok");
			result.put("deleteAllbyPigrid", ser.deleteAllbyPigridCode(pigridCode));
			log.debug("deleteAllbyPigrid successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"deleteAllbyPigrid with param 'PiGrid' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	@ResponseBody
	@RequestMapping(value = "/pigriddetail/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> deletePigriddetail(@RequestBody PiGridDetailModel pgdm) {
		log.info(String.format("deletePigriddetail with param 'pigriddetailCode' in class: %s",
				getClass()));
		try {
			log.debug("add 1 PiGridDetail and return edit PiGridDetail as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("PiGridDetail", "ok");
			result.put("deletePigriddetail", ser.deletePigriddetail(pgdm.getPigriddetail()));
			log.debug("deletePigriddetail successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"deletePigriddetail with param 'pigriddetailCode' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

}
