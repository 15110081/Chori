package com.chori.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chori.service.FpidetailService;



@Controller
@RequestMapping(value = "/")
public class FpiController {
	private static final Log log = LogFactory.getLog(FpiController.class);

	@Autowired
	private FpidetailService fpidetailService;
	
	/**
	 * This function is used to return view page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewfpi", method = RequestMethod.GET)
	public ModelAndView viewFpi(HttpServletResponse response) {
		log.info(String.format("viewFpi with param 'response' in class: %s", getClass()));
		try {
			log.debug("return viewFpiPage view for request");
			response.setContentType("text/html");
		
			log.debug("viewFpiPage successful");
			// just add
			return new ModelAndView("operation/fpi/view");
		} catch (Exception e) {
			log.error(String.format("viewFpi with param 'response' in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}

	}
	
	/**
	 * This function is used to return page view Fpi With Grid Detail
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/viewFpiWithGridDetail", method = RequestMethod.GET)
	public ModelAndView viewFpiWithGridDetail(HttpServletResponse response) {
		log.info(String.format("viewFpiWithGridDetail with param 'response' in class: %s", getClass()));
		try {
			log.debug("return viewFpiWithGridDetail view for request");
			response.setContentType("text/html");
			log.debug("viewFpiWithGridDetail successful");
			return new ModelAndView("operation/fpi/fpiWithGrid");
		} catch (Exception e) {
			log.error(String.format("viewFpiWithGridDetail with param 'response' in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}
	
	@RequestMapping(value = "/fpi/fpiGridDetail/{lotNumber}/{version}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getFpiGridDetail(@PathVariable String lotNumber, @PathVariable Integer version) {
		log.info(String.format("getFpiGridDetail in class: %s", getClass()));
		try {
			log.debug("getting fpi's grid detail by its lotNumber and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("fpiGridDetail", fpidetailService.getListFpiDetailByLotNumberAndVersion(lotNumber, version));
			log.debug("getFpiGridDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getFpiGridDetail in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}
	
	@RequestMapping(value = "/fpi/getListFpiVersionByLotNumber/{lotNumber}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getListFpiVersionByLotNumber(@PathVariable String lotNumber) {
		log.info(String.format("getListFpiVersionByLotNumber in class: %s", getClass()));
		try {
			log.debug("getting fpi's grid detail by its lotNumber and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("listFpiVersionByLotNumber", fpidetailService.getListFpiVersionByLotNumber(lotNumber));
			log.debug("getListFpiVersionByLotNumber successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getListFpiVersionByLotNumber in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}
}
