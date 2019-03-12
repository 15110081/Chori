package com.chori.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chori.model.OrderExternalAccessoryModel;
import com.chori.model.PiModel;
import com.chori.service.WarningMessageService;

@Controller
@RequestMapping(value="/")
public class WarningMessageController {

	private static final Log log = LogFactory.getLog(BrandController.class);
	
	@Autowired
	WarningMessageService service;
	
	//get pi if it have warning message
	@RequestMapping(value = "/warningmessage/piwarningmessage", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getPIWarningMessage() {
		log.info(String.format("getPIWarningMessage in class %s", getClass()));
		try {
			log.debug("getting PI Warning Message and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			List<PiModel> lstPIModel = service.getListPIForWarningMessage();
			result.put("piwarningmessage", lstPIModel);
			log.debug("getPIWarningMessage successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getPIWarningMessage in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	//get packing guide order external accessory if it have warning message
	@RequestMapping(value = "/warningmessage/packingguideorderextaccwarningmessage", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getPackingGuideOrderExternalAccWarningMessage() {
		log.info(String.format("getPackingGuideOrderExternalAccWarningMessage in class %s", getClass()));
		try {
			log.debug("getting PackingGuideOrderExternalAccWarningMessage return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			List<OrderExternalAccessoryModel> lstOrderExternalAccessoryModel = service.getPackingAccInListOrderExternalAccessoriesForWarningMessage();
			result.put("packingguideorderextaccwarningmessage", lstOrderExternalAccessoryModel);
			log.debug("PackingGuideOrderExternalAccWarningMessage successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("PackingGuideOrderExternalAccWarningMessage in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
}
