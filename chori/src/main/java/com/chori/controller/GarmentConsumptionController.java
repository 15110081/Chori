package com.chori.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.chori.model.GarmentConsumptionModel;
import com.chori.model.SizeModel;
import com.chori.model.WidthModel;
import com.chori.service.GarmentConsumptionService;
import com.chori.service.RoleService;

@Controller
@RequestMapping(value = "/")
public class GarmentConsumptionController {
	private static final Log log = LogFactory
			.getLog(GarmentConsumptionController.class);

	@Autowired
	GarmentConsumptionService ser;

	@Autowired
	RoleService roleSer;

	// @Autowired
	// GarmentConsumptionDetailService garmentConsumptionDetailService;

	/**
	 * Return view page for Garment Consumption
	 */
	@RequestMapping(value = { "/GarmentConsumption" }, method = RequestMethod.GET)
	public String listGarmentConsumption() {
		log.info(String.format(
				"listGarmentConsumption with param 'response' in class: %s",
				getClass()));
		try {
			LoginController.AssignCurrentUser();
			String path = "configuration/garmentconsumption/listGarmentConsumption";
			log.debug("return listGarmentConsumption view for request");
			log.debug("listGarmentConsumption successful");
			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Garment Consumption")) {
				return path;
			}
			return "login/accessDenied";
		} catch (Exception e) {
			log.error(String
					.format("listGarmentConsumption with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method is used to get all garmentconsumption in database
	 * 
	 * @return list garmentconsumption in JSON format
	 */
	@RequestMapping(value = "/garmentconsumption/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllGarmentConsumption() {
		log.info(String.format("getAllGarmentConsumption in class %s",
				getClass()));
		try {
			log.debug("getting list of All GarmentConsumption and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<GarmentConsumptionModel> ls = ser
					.getAllGarmentConsumptionModel();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllGarmentConsumption successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllGarmentConsumption in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method is used to get all garmentconsumption in database
	 * 
	 * @return list garmentconsumption in JSON format
	 */
	@RequestMapping(value = "/garmentconsumption/getsizebycustomerandgarmentstyle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> getSizeByCustomerAndGarmentStyle(@RequestBody SizeModel sizeModel) {
		log.info(String.format("getSizeByCustomerAndGarmentStyle in class %s",
				getClass()));
		try {
			log.debug("getting Size By Customer And Garment Style and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<SizeModel> ls = ser
					.getSizeByCustomerAndGarmentStyle(sizeModel.getCustomer(),sizeModel.getGarmentstyle());
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getSizeByCustomerAndGarmentStyle successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getSizeByCustomerAndGarmentStyle in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	// /**
	// * This method is used to add garmentconsumption in database
	// * @param garconModel
	// */
	// @ResponseBody
	// @RequestMapping(value = "/garmentconsumption/add", method =
	// RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes
	// = MediaType.APPLICATION_JSON_VALUE)
	// @ResponseStatus(HttpStatus.OK)
	// public Map<String, Object> addNewGarmentConsumption(@RequestBody
	// GarmentConsumptionModel garconModel) {
	// log.info(String.format("addNewGarmentConsumption with params 'fm' in class: %s",getClass()));
	// try {
	// log.debug("addNewGarmentConsumption and return status as json format");
	// Map<String, Object> result = new HashMap<String, Object>();
	// result.put("status", "ok");
	// String userName = LoginController.currentUser;
	// result.put("addStatus", ser.addNewGarmentConsumption(garconModel,
	// userName));
	// System.err.println(garconModel);
	// log.debug("addNewGarmentConsumption successfully");
	// return result;
	// } catch (Exception e) {
	// log.error(String.format("addNewGarmentConsumption with params 'fm' in class: %s has error: %s",getClass(),
	// e.getMessage()));
	// throw e;
	// }
	// }

	/**
	 * This method is used to get garmentconsumption detail by id in database
	 * 
	 * @param GarmentconsumptionCode
	 * @return detail of a garment consumption in JSON format
	 */
	@RequestMapping(value = "/garmentconsumption/detail/{garconCode}", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getGarmentConsumptionDetail(
			@PathVariable Integer garconCode) {
		log.info(String.format("getGarmentConsumptionDetail in class %s",
				getClass()));
		try {
			log.debug("getGarmentConsumptionDetail and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("garmentconsumption",
					ser.findGarmentConsumptionModelById(garconCode));
			log.debug("getGarmentConsumptionDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getGarmentConsumptionDetail in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method is used to edit garmentconsumption in database
	 * 
	 * @param garmentconsumptionModel
	 * @return edit status
	 */
	@ResponseBody
	@RequestMapping(value = "/garmentconsumption/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editGarmentConsumption(
			@RequestBody GarmentConsumptionModel garconModel) {
		log.info(String.format(
				"editGarmentConsumption with params 'fm' in class: %s",
				getClass()));
		try {
			log.debug("editGarmentConsumption and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("editStatus", ser.editGarmentConsumption(garconModel));
			log.debug("editGarmentConsumption successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("editGarmentConsumption with params 'fm' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete 1 garmentconsumption
	 * 
	 * @param garmentconsumptionCode
	 * @return delete status
	 */
	@RequestMapping(value = "/garmentconsumption/delete/{garmentconsumptionCode}", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteGarmentConsumption(
			@PathVariable Integer garmentconsumptionCode) {
		log.info(String.format("deleteGarmentConsumption in class %s",
				getClass()));
		try {
			log.debug("deleteGarmentConsumption and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("deleteStatus", ser.delete(garmentconsumptionCode));
			log.debug("deleteGarmentConsumption successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"deleteGarmentConsumption in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method is used to add garmentconsumption in database
	 * 
	 * @param garmentconsumptionModel
	 * @return add status
	 */
	@ResponseBody
	@RequestMapping(value = "/garmentconsumption/add2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addNewGarmentConsumption2(
			@RequestBody GarmentConsumptionModel garconModel) {
		log.info(String.format(
				"addNewGarmentConsumption with params 'fm' in class: %s",
				getClass()));
		try {
			log.debug("addNewGarmentConsumption and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String userName = LoginController.currentUser;
			result.put("addStatus",
					ser.addNewGarmentConsumption2(garconModel, userName));
			log.debug("addNewGarmentConsumption successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addNewGarmentConsumption with params 'fm' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a garment consumption is existed on database.
	 * 
	 * @param garmentconsumptionModel
	 * @return is existed status
	 */
	@RequestMapping(value = "/garmentconsumption/isExist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isGarmentConsumptionExisted(
			@RequestBody GarmentConsumptionModel garconModel) {
		log.info(String
				.format("isGarmentConsumptionExisted with param 'garconModel' in class: %s",
						getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("isExisted",
					ser.isGarmentConsumptionExisted(garconModel));
			log.debug("check isGarmentConsumptionExisted successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isGarmentConsumptionExisted with param 'garconModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function check if width table in DB have data
	 * @return is existed status
	 */
	@RequestMapping(value = "/garmentconsumption/isExistWidthData", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> isExistedWidthData() {
		log.info(String
				.format("isExistedWidthData in class: %s",
						getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("isExistedWidthData",ser.isExistedWidthData());
			log.debug("check isExistedWidthData successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isExistedWidthData ' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This method is used to add garmentconsumption by garment style in database
	 * @param garmentconsumptionModel
	 * @return add status
	 */
	@ResponseBody
	@RequestMapping(value = "/garmentconsumption/addbygarmentstyle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addNewGarmentConsumptionByGarmentStyle(
			@RequestBody GarmentConsumptionModel garconModel) {
		log.info(String.format(
				"addNewGarmentConsumptionByGarmentStyle with params 'fm' in class: %s",
				getClass()));
		try {
			log.debug("addNewGarmentConsumptionByGarmentStyle and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String userName = LoginController.currentUser;
			result.put("addStatus",
					ser.addNewGarmentConsumptionByGarmentStyle(garconModel, userName));
			log.debug("addNewGarmentConsumptionByGarmentStyle successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addNewGarmentConsumptionByGarmentStyle with params 'fm' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This method is used to get garment consumption code by customer and garment style
	 * @param garmentconsumptionModel
	 * @return add status
	 */
	@ResponseBody
	@RequestMapping(value = "/garmentconsumption/getgarconcodebycustomerandgarstyle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> getAllGarmentConCodeByCustomerAndGarmentStyle(
			@RequestBody GarmentConsumptionModel garconModel) {
		log.info(String.format(
				"getAllGarmentConCodeByCustomerAndGarmentStyle with params 'fm' in class: %s",
				getClass()));
		try {
			log.debug("getAllGarmentConCodeByCustomerAndGarmentStyle and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("garconcodelist",
					ser.getAllGarmentConCodeByCustomerAndGarmentStyle(garconModel));
			log.debug("getAllGarmentConCodeByCustomerAndGarmentStyle successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getAllGarmentConCodeByCustomerAndGarmentStyle with params 'fm' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This method is used to get all width in database and return a list width in json
	 * @return list width in DB
	 */

	@RequestMapping(value = "/garmentconsumption/widthlist", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllWidth() {
		log.info(String.format("getAllWidth in class %s", getClass()));
		try {
			log.debug("getting list of all width and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<WidthModel> ls = ser.getAllWidth();
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
	
	// /**
	// * This method is used to add garmentconsumption in database
	// * @param garconModel
	// */
	// @ResponseBody
	// @RequestMapping(value = "/garmentconsumptiondetail/add", method =
	// RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes
	// = MediaType.APPLICATION_JSON_VALUE)
	// @ResponseStatus(HttpStatus.OK)
	// public Map<String, Object> addNewGarmentConsumptionDetail(@RequestBody
	// GarmentConsumptionDetailModel garconDetailModel) {
	// log.info(String.format("addNewGarmentConsumption with params 'fm' in class: %s",getClass()));
	// try {
	// log.debug("addNewGarmentConsumptionDetail and return status as json format");
	// Map<String, Object> result = new HashMap<String, Object>();
	// result.put("status", "ok");
	// String userName = LoginController.currentUser;
	// result.put("addStatus",
	// garmentConsumptionDetailService.addNewGarmentConsumptionDetail(garconDetailModel,
	// userName));
	// System.err.println(garconDetailModel);
	// log.debug("addNewGarmentConsumptionDetail successfully");
	// return result;
	// } catch (Exception e) {
	// log.error(String.format("addNewGarmentConsumptionDetail with params 'fm' in class: %s has error: %s",getClass(),
	// e.getMessage()));
	// throw e;
	// }
	// }
}
