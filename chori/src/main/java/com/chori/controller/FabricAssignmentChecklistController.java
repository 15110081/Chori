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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chori.model.FabricinformationModel;
import com.chori.model.PIAssignFabricDetailModel;
import com.chori.model.PiassignfabricModel;
import com.chori.service.FabricAssignmentChecklistService;
import com.chori.service.PIAssignFabricDetailService;

@Controller
@RequestMapping(value = "/")
public class FabricAssignmentChecklistController {

	private static final Log log = LogFactory.getLog(CustomerController.class);

	@Autowired
	FabricAssignmentChecklistService ser;

	@Autowired
	PIAssignFabricDetailService piAssignFabricDetailService;

	/**
	 * Return view page
	 * 
	 * @return
	 */

	@RequestMapping(value = "/listFabricAssignment", method = RequestMethod.GET)
	public ModelAndView listFabricAssignment(HttpServletResponse response) {
		log.info(String.format(
				"listFabricAssignment with param 'response' in class: %s",
				getClass()));
		try {
			log.debug("return listFabricAssignment view for request");
			response.setContentType("text/html");
			log.debug("listFabricAssignment successful");
			return new ModelAndView(
					"operation/fabricassignment/listFabricAssignment");
		} catch (Exception e) {
			log.error(String
					.format("listFabricAssignment with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method is used to get all fabric assignment in database and return a
	 * list customer in json
	 * 
	 * @return Map<String, Object>
	 */

	@RequestMapping(value = "/fabricassignment/list", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody

	public Map<String, Object> getAllFabricAssignment(@RequestBody FabricinformationModel fabricinformationModel) {
		log.info(String
				.format("getAllFabricAssignment in class %s", getClass()));
		try {
			log.debug("getting list of all Fabric assignment and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<FabricinformationModel> ls = ser
					.getAllFabricinformationModelByCustomerandFactory(
							fabricinformationModel.getCustomer(), fabricinformationModel.getFactory());
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllFabricAssignment successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllFabricAssignment in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

//	/**
//	 * This function find a fabric assignment by fabricno then return it as JSON
//	 * format
//	 * 
//	 * @param fabricno
//	 * @return JSON format of a fabricassignment
//	 */
//	@RequestMapping(value = "/fabricassignment/detail/{fabricno}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getFabricAssignmentDetail(
//			@PathVariable String fabricno) {
//		log.info(String.format(
//				"getFabricAssignmentDetail with param 'fabricno' in class: %s",
//				getClass()));
//		try {
//			log.debug("getting fabric assignment detail by its fabricno and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//
//			FabricinformationModel fabricinfomodel = ser
//					.findFabricinformationModelByFabricNo(fabricno);
//			result.put("currentfabricassignment", fabricinfomodel);
//			result.put("status", "ok");
//			log.debug("getFabricAssignmentDetail successfully!");
//			return result;
//		} catch (Exception e) {
//			log.error(String
//					.format("getFabricAssignmentDetail with param 'fabricno' in class %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}

	/**
	 * This function find a fabric assignment detail by fabricno then return it
	 * as JSON format
	 * 
	 * @param fabricno
	 * @return JSON format of a piassignfabricdetail
	 */
	@RequestMapping(value = "/piassignfabricdetail/detail", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPIAssignFabricAssignmentDetailByFabricNo(
			@RequestBody PIAssignFabricDetailModel piAssignFabricDetailModel) {
		log.info(String
				.format("getPIAssignFabricAssignmentDetailByFabricNo with param 'fabricno' in class: %s",
						getClass()));
		try {
			log.debug("getting PIAssignFabricAssignmentDetailByFabricNo detail by its fabricno and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("piassignfabricdetail", piAssignFabricDetailService
					.findPIAssignFabricDetailModelByFabricNo(piAssignFabricDetailModel.getFabricno()));
			result.put("status", "ok");
			log.debug("getPIAssignFabricAssignmentDetailByFabricNo successfully!");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getPIAssignFabricAssignmentDetailByFabricNo with param 'fabricno' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
