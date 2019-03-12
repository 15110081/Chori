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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chori.model.CtnrtypeModel;
import com.chori.service.CtnrtypeService;
import com.chori.service.RoleService;

@RestController
@RequestMapping(value = "/")
public class CtnrtypeController {
	private static final Log log = LogFactory.getLog(CtnrtypeController.class);

	@Autowired
	CtnrtypeService service;

	@Autowired
	RoleService roleSer;

	/**
	 * This function is used to get all ctnrtype in db and return json
	 * 
	 * @return
	 */

	@RequestMapping(value = "/ctnrtype/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllCtnrtype() {
		log.info(String.format("getAllCtnrtype in class %s", getClass()));
		try {
			log.debug("getting list of all ctnrtype and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<CtnrtypeModel> ls = service.getAllCtnrtypeModel();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllCtnrtype successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getAllCtnrtype in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}

	}

	/**
	 * Return view page
	 * 
	 * @return
	 */

	@RequestMapping(value = "/listctnrtype", method = RequestMethod.GET)
	public ModelAndView listCtnrtype(HttpServletResponse response) {
		log.info(String.format(
				"listCtnrtype with param 'response' in class: %s", getClass()));
		try {
			log.debug("return listCtnrtype view for request");
			response.setContentType("text/html");
			log.debug("listCtnrtype successful");
			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Container Type")) {
				return new ModelAndView("configuration/ctnrtype/list");
			}
			return new ModelAndView("login/accessDenied");

		} catch (Exception e) {
			log.error(String
					.format("listCtnrtype with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}

	/**
	 * This function is used to get detail of 1 ctnrtype
	 * 
	 * @param ctnrtypeCode
	 * @return
	 */
	@RequestMapping(value = "/ctnrtype/detail", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCtnrtypeDetail(@RequestBody CtnrtypeModel ctnrtypeModel) {
		log.info(String.format(
				"getCtnrtypeDetail with param 'Ctnrcode' in class: %s", getClass()));
		try {
			log.debug("getting ctnrtype's detail by its Ctnrcode and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			CtnrtypeModel en = service.findCtnrtypeEntityById(ctnrtypeModel.getCtnrcode());
			result.put("currentctnrtype", en);
			result.put("status", "ok");
			log.debug("getCtnrtypeDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getCtnrtypeDetail with param 'Ctnrcode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit a ctnrtype exchange
	 * 
	 * @param CtnrtypeModel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/ctnrtype/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editCtnrtype(@RequestBody CtnrtypeModel ctnrtypeMod) {
		log.info(String.format("editCtnrtype with param 'order' in class: %s",
				getClass()));
		try {
			log.debug("edit 1 ctnrtype and return edit ctnrtype as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			String userName = LoginController.currentUser;
			result.put("status", "ok");
			result.put("editCtnrtype",
					service.editCtnrtype(ctnrtypeMod, userName));
			log.debug("editCtnrtype successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("editCtnrtype with param 'order' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to check if a ctnrtype is existed
	 * 
	 * @param ctnrtypeCode
	 * @return
	 */

	@RequestMapping(value = "/ctnrtype/isExist/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isCtnrtypeExist(@RequestBody CtnrtypeModel ctnrtypeMod) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "ok");
		result.put("isExisted", service.isCtnrtypeExistedById(ctnrtypeMod.getCtnrcode()));
		return result;
	}

	/**
	 * This function is used to add new ctnrtype
	 * 
	 * @param ctnrtypeMod
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/ctnrtype/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addCtnrtype(
			@RequestBody CtnrtypeModel ctnrtypeMod) {
		log.info(String.format("addCtnrtype with param 'ctnrtypeMod' in class: %s",
				getClass()));
		try {
			log.debug("add 1 ctnrtype and return edit ctnrtype as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			String userName = LoginController.currentUser;
			result.put("status", "ok");
			result.put("addCtnrtype",
					service.addCtnrtype(ctnrtypeMod, userName));
			log.debug("addCtnrtype successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"addCtnrtype with param 'ctnrtypeMod' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete a ctnrtype
	 * 
	 * @param ctnrtypeCode
	 * @return
	 */

	@RequestMapping(value = "/ctnrtype/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> deleteCtnrtype(@RequestBody CtnrtypeModel ctnrtypeModel) {
		log.info(String.format(
				"delete ctnrtype with param 'ctnrtypeCode' in class: %s",
				getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("deleteCtnrtype", service.deleteCtnrtype(ctnrtypeModel.getCtnrcode()));
			log.debug("delete ctnrtype successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("delete ctnrtype with param 'ctnrtypeCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}

}