//package com.chori.controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.chori.entity.FabricinformationdetailId;
//import com.chori.model.GarmentstyleModel;
//import com.chori.service.FabricinformationdetailService;
//import com.chori.model.FabricinformationdetailModel;
//
//@Controller
//@RequestMapping(value = "/")
//public class FabricinformationdetailController {
//	@Autowired
//	FabricinformationdetailService ser;
//
//	private static final Log log = LogFactory
//			.getLog(AccessoryConsumptionController.class);
//
//	/**
//	 * This function find a fabricinformationdetail by id then return it as JSON
//	 * format
//	 * 
//	 * @param fabricno
//	 *            ,colorcode
//	 * @return JSON format of a fabricinformationdetail
//	 */
//	@RequestMapping(value = "/fabricinformationdetail/yardinbl/{fabricno}/{colorcode}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getFabricInformationDetail(
//			@PathVariable String fabricno, @PathVariable String colorcode) {
//		log.info(String
//				.format("getFabricInformationDetail with param 'fabricno' and 'colorcode' in class: %s",
//						getClass()));
//		try {
//			log.debug("getting getFabricInformationDetail detail by its {fabricno}/{colorcode} and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			FabricinformationdetailId fabricinformationdetailId = new FabricinformationdetailId(
//					fabricno, colorcode);
//			result.put("status", "ok");
//			result.put("fabricinformationdetail",
//					ser.getYardInBL(fabricinformationdetailId));
//			result.put("inventoryremained",
//					ser.getInventoryRemained(fabricinformationdetailId));
//			result.put("availablequantity",
//					ser.getAvailableQty(fabricinformationdetailId));
//			log.debug("getFabricInformationDetail successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String
//					.format("getFabricInformationDetail with param 'fabricno' and 'colorcode' in class %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}
//	
//	/**
//	 * This function is used to getallGarment style in DB and return as json
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/fabricinformationdetail/list", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getAllGarmentstyle() {
//		log.info(String.format("getAllFabricinformationdetail in class %s", getClass()));
//		try {
//			log.debug("getting list of all fabricinformationdetail and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			List<FabricinformationdetailModel> ls = ser.getAllFabricinformationdetailModel();
//			result.put("status", "ok");
//			result.put("list", ls);
//			log.debug("get all fabricinformationdetail successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String.format(
//					"getAllFabricinformationdetail in class %s has error: %s", getClass(),
//					e.getMessage()));
//			throw e;
//		}
//	}
//	
//	@RequestMapping(value = "/fabricinformationdetail/list", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getRemainQuantity() {
//		log.info(String.format("getAllFabricinformationdetail in class %s", getClass()));
//		try {
//			log.debug("getting list of all fabricinformationdetail and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			List<FabricinformationdetailModel> ls = ser.getAllFabricinformationdetailModel();
//			result.put("status", "ok");
//			result.put("list", ls);
//			log.debug("get all fabricinformationdetail successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String.format(
//					"getAllFabricinformationdetail in class %s has error: %s", getClass(),
//					e.getMessage()));
//			throw e;
//		}
//	}
//}
