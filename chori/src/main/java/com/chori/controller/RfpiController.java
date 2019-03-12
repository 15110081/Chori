package com.chori.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chori.model.PIAssignFabricDetailModel;
import com.chori.service.PIAssignFabricDetailService;
import com.chori.service.RfpidetailService;

@Controller
@RequestMapping(value = "/")
public class RfpiController {
	private static final Log log = LogFactory.getLog(RfpiController.class);
	
	@Autowired
	private RfpidetailService rfpidetailService;
	
	@Autowired
	private PIAssignFabricDetailService piassignfabricdetailService;
	/**
	 * This function is used to return view page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewrfpi", method = RequestMethod.GET)
	public ModelAndView viewFpi(HttpServletResponse response) {
		log.info(String.format("viewrfpi with param 'response' in class: %s", getClass()));
		try {
			log.debug("return viewrfpi page for request");
			response.setContentType("text/html");
		
			log.debug("viewrfpi page successfully");
			// just add
			return new ModelAndView("operation/rfpi/viewrfpi");
		} catch (Exception e) {
			log.error(String.format("viewrfpi with param 'response' in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}

	}
	/**
	 * This function is used to return page view Rfpi With Grid Detail
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/viewRfpiWithGridDetail", method = RequestMethod.GET)
	public ModelAndView viewRfpiWithGridDetail(HttpServletResponse response) {
		log.info(String.format("viewRfpiWithGridDetail with param 'response' in class: %s", getClass()));
		try {
			log.debug("return viewRfpiWithGridDetail view for request");
			response.setContentType("text/html");
			log.debug("viewFpiWithGridDetail successful");
			return new ModelAndView("operation/rfpi/rfpiWithGrid");
		} catch (Exception e) {
			log.error(String.format("viewRfpiWithGridDetail with param 'response' in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function gets list of fabrics assigned to PI 
	 * 
	 * @param lotNo
	 *            
	 * @return list Fabric Assignment in JSON format
	 */
	@RequestMapping(value = "/listfabricassignedtopi/{lotNo}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getListFabricAssignedToPI( @PathVariable String lotNo) {
		log.info(String
				.format("getListFabricAssignedToPI with param 'lotNo' in class: %s",
						getClass()));
		try {
			log.debug("getListFabricAssignedToPI and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<PIAssignFabricDetailModel> ls = piassignfabricdetailService
					.getListPIAssignFabricDetail(lotNo);
			result.put("status", "ok"); 
			result.put("list", ls);
			
			log.debug("getListFabricAssignedToPI successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getListFabricAssignedToPI with param 'lotNo' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	@RequestMapping(value = "/rfpi/rfpiGridDetail/{lotNumber}/{version}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getRfpiGridDetail(@PathVariable String lotNumber, @PathVariable Integer version) {
		log.info(String.format("getRfpiGridDetail in class: %s", getClass()));
		try {
			log.debug("getting rfpi's grid detail by its lotNumber and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("rfpiGridDetail", rfpidetailService.getListRfpiDetailByLotNumberAndVersion(lotNumber, version));
			log.debug("getRfpiGridDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getRfpiGridDetail in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}
	
	@RequestMapping(value = "/rfpi/getListRfpiVersionByLotNumber/{lotNumber}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getListRfpiVersionByLotNumber(@PathVariable String lotNumber) {
		log.info(String.format("getListRfpiVersionByLotNumber in class: %s", getClass()));
		try {
			log.debug("getting fpi's grid detail by its lotNumber and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("listRfpiVersionByLotNumber", rfpidetailService.getListRfpiVersionByLotNumber(lotNumber));
			log.debug("getListRfpiVersionByLotNumber successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getListRfpiVersionByLotNumber in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}
	
}
