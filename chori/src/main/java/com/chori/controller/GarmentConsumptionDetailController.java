package com.chori.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.chori.model.GarmentConsumptionDetailModel;
import com.chori.service.GarmentConsumptionDetailService;

@Controller
@RequestMapping(value = "/")
public class GarmentConsumptionDetailController {
	private static final Log log = LogFactory
			.getLog(GarmentConsumptionDetailController.class);

	@Autowired
	GarmentConsumptionDetailService garmentConsumptionDetailService;

	/**
	 * This method is used to add garmentconsumption detail in database
	 * 
	 * @param garmentconsumptionDetailModel
	 * @return add status
	 */
	@ResponseBody
	@RequestMapping(value = "/garmentconsumptiondetail/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addNewGarmentConsumptionDetail(
			@RequestBody GarmentConsumptionDetailModel garconDetailModel) {
		log.info(String.format(
				"addNewGarmentConsumption with params 'fm' in class: %s",
				getClass()));
		try {
			log.debug("addNewGarmentConsumptionDetail and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String userName = LoginController.currentUser;
			result.put("addStatus",
					garmentConsumptionDetailService
							.addNewGarmentConsumptionDetail(garconDetailModel,
									userName));
			log.debug("addNewGarmentConsumptionDetail successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addNewGarmentConsumptionDetail with params 'fm' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function edit a garment consumption detail and update into database
	 * 
	 * @param garmentconsumptionDetailModel
	 * @return edit Status
	 */
	@ResponseBody
	@RequestMapping(value = "/garmentconsumptiondetail/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editGarmentConsumptionDetail(
			@RequestBody GarmentConsumptionDetailModel garconDetailModel) {
		log.info(String
				.format("editGarmentConsumptionDetail with param 'garconModel' in class: %s",
						getClass()));
		try {
			log.debug("edit 1 garcon detail and return edit status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("editStatus", garmentConsumptionDetailService
					.editGarmentConsumptionDetail(garconDetailModel));
			log.debug("editGarmentConsumptionDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("editGarmentConsumptionDetail with param 'garconModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This method is used to add garmentconsumption detail in database
	 * 
	 * @param garmentconsumptionDetailModel
	 * @return add status
	 */
	@ResponseBody
	@RequestMapping(value = "/garmentconsumptiondetail/addbygarmentstyle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addNewGarmentConsumptionDetailByGarmentStyle(@RequestBody GarmentConsumptionDetailModel garconDetailModel) {
		log.info(String.format(
				"addNewGarmentConsumptionDetailByGarmentStyle with params 'fm' in class: %s",
				getClass()));
		try {
			log.debug("addNewGarmentConsumptionDetailByGarmentStyle and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String userName = LoginController.currentUser;
			result.put("addStatus",garmentConsumptionDetailService.addNewGarmentConsumptionDetailByGarmentStyle(garconDetailModel,userName));
			log.debug("addNewGarmentConsumptionDetailByGarmentStyle successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addNewGarmentConsumptionDetailByGarmentStyle with params 'fm' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
