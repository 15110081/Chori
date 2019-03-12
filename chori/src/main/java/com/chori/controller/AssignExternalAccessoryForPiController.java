package com.chori.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.chori.model.PiModel;
import com.chori.model.PiassignexternalaccessoryModel;
import com.chori.service.AccessoryConsumptionService;
import com.chori.service.PiassignexternalaccessoryService;

@Controller
@RequestMapping(value = "/")
public class AssignExternalAccessoryForPiController {

	private static final Log log = LogFactory
			.getLog(AssignExternalAccessoryForPiController.class);

	@Autowired
	private PiassignexternalaccessoryService ser;
	
	@Autowired
	private AccessoryConsumptionService accessoryConsumptionSer;

	/**
	 * This function return demo view page for pi assign external accessory
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/listAssignExternalAccessoryForPi" }, method = RequestMethod.GET)
	public String listAssignExternalAccessoryForPi() {
		log.info(String.format("listAssignExternalAccessoryForPi in class: %s",
				getClass()));
		try {
			log.debug("return listAssignExternalAccessoryForPi view for request");
			log.debug("listAssignExternalAccessoryForPi successful");
			return "operation/assignExternalAccessoryForPi/listAssignExternalAccessoryForPi";
		} catch (Exception e) {
			log.error(String
					.format("listAssignExternalAccessoryForPi in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get list Assign External Accessory 1st Time Hàm
	 * dùng để lấy list assign external accessory lần đầu (show lên dialog)
	 * 
	 * @param lotNumber
	 * @return
	 */
//	@RequestMapping(value = "/listAssignExternalAccessory1stTime/{lotNumber}", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> get1ListAssignExternalAccessory1stTime(
//			@PathVariable String lotNumber) {
//		log.info(String.format(
//				"get1ListAssignExternalAccessory1stTime in class %s",
//				getClass()));
//		try {
//			log.debug("get1ListAssignExternalAccessory1stTime and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			result.put("status", "ok");
//			result.put(
//					"listAssignExternalAccessory1stTime",
//					ser.getListPiassignexternalaccessoryModelWhenPressAssign(lotNumber));
//			log.debug("get1ListAssignExternalAccessory1stTime successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String
//					.format("get1ListAssignExternalAccessory1stTime in class %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	//Chuyển sang post
	@RequestMapping(value = "/listAssignExternalAccessory1stTime", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get1ListAssignExternalAccessory1stTime(
			@RequestBody PiModel piModel) {
		log.info(String.format(
				"get1ListAssignExternalAccessory1stTime in class %s",
				getClass()));
		try {
			log.debug("get1ListAssignExternalAccessory1stTime and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put(
					"listAssignExternalAccessory1stTime",
					ser.getListPiassignexternalaccessoryModelWhenPressAssign(piModel.getLotnumber()));
			log.debug("get1ListAssignExternalAccessory1stTime successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("get1ListAssignExternalAccessory1stTime in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to check if a PI with lot No is already assign
	 * external accessory Hàm kiểm tra xem pi đc assign external accessory 1 lần
	 * hay chưa?
	 * 
	 * @param lotNumber
	 * @return
	 */
//	@RequestMapping(value = "/assignExternalAccessory/isAssigned/{lotNumber}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> isPiAssignedExternalAccessory(
//			@PathVariable String lotNumber) {
//		log.info(String.format("isPiAssignedExternalAccessory in class: %s",
//				getClass()));
//		try {
//			log.debug("check if a pi is assigned external acc and return as json format");
//			Map<String, Object> result = new HashMap<String, Object>();
//			result.put("status", "ok");
//			result.put("isAssigned",
//					ser.isPiAssignedExternalAccessory(lotNumber));
//			log.debug("check isPiAssignedExternalAccessory successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String.format(
//					"isPiAssignedExternalAccessory in class %s has error: %s",
//					getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	//Chuyển sang post
	@RequestMapping(value = "/assignExternalAccessory/isAssigned", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isPiAssignedExternalAccessory(
			@RequestBody PiModel piModel) {
		log.info(String.format("isPiAssignedExternalAccessory in class: %s",
				getClass()));
		try {
			log.debug("check if a pi is assigned external acc and return as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("isAssigned",
					ser.isPiAssignedExternalAccessory(piModel.getLotnumber()));
			log.debug("check isPiAssignedExternalAccessory successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"isPiAssignedExternalAccessory in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new pi assign external accessory for pi 1st
	 * time Hàm add new pi assign external accessory lần đầu tiên
	 * 
	 * @param lstNotAssign
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/assignExternalAccessory/add1stTime", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> add1stTimePiAssignExternalAccessory(
			@RequestBody ArrayList<PiassignexternalaccessoryModel> lstNotAssign) {
		log.info(String.format(
				"add1stTimePiAssignExternalAccessory in class: %s", getClass()));
		try {
			log.debug("add1stTimePiAssignExternalAccessory and return assignStatus as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("add1stTimeStatus", ser
					.add1stTimePiAssignExternalAccessory(lstNotAssign, "admin"));
			// for (FunctionModel functionEntity : lst) {
			// System.err.println(functionEntity);
			// }
			// System.err.println("List size: "+lst.size());
			log.debug("add1stTimePiAssignExternalAccessory successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("add1stTimePiAssignExternalAccessory in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get list pi assign external accessory in pi
	 * after assign, edit assign Hàm show list pi assign external accessory trên
	 * trang pi
	 * 
	 * @param lotNumber
	 * @return
	 */
//	@RequestMapping(value = "/listPiassignexternalaccessoryByLotNumber/{lotNumber}", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getListPiassignexternalaccessoryByLotNumber(
//			@PathVariable String lotNumber) {
//		log.info(String.format(
//				"getListPiassignexternalaccessoryByLotNumber in class %s",
//				getClass()));
//		try {
//			log.debug("getListPiassignexternalaccessoryByLotNumber and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			result.put("status", "ok");
//			result.put("listPiassignexternalaccessoryByLotNumber",
//					ser.getListPiassignexternalaccessoryByLotNumber(lotNumber));
//			log.debug("getListPiassignexternalaccessoryByLotNumber successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String
//					.format("getListPiassignexternalaccessoryByLotNumber in class %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	//Chuyển sang post
	@RequestMapping(value = "/listPiassignexternalaccessoryByLotNumber", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListPiassignexternalaccessoryByLotNumber(
			@RequestBody PiModel piModel) {
		log.info(String.format(
				"getListPiassignexternalaccessoryByLotNumber in class %s",
				getClass()));
		try {
			log.debug("getListPiassignexternalaccessoryByLotNumber and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("listPiassignexternalaccessoryByLotNumber",
					ser.getListPiassignexternalaccessoryByLotNumber(piModel.getLotnumber()));
			log.debug("getListPiassignexternalaccessoryByLotNumber successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getListPiassignexternalaccessoryByLotNumber in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get list Pi assign external accessory For Edit
	 * By Lot Number to load into dialog (after 1st add)
	 * 
	 * @param lotNumber
	 * @return
	 */
//	@RequestMapping(value = "/listPiassignexternalaccessoryForEditByLotNumber/{lotNumber}", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getListPiassignexternalaccessoryForEditByLotNumber(
//			@PathVariable String lotNumber) {
//		log.info(String
//				.format("getListPiassignexternalaccessoryForEditByLotNumber in class %s",
//						getClass()));
//		try {
//			log.debug("getListPiassignexternalaccessoryForEditByLotNumber and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			result.put("status", "ok");
//			result.put(
//					"listPiassignexternalaccessoryForEditByLotNumber",
//					ser.getListPiassignexternalaccessoryForEditByLotNumber(lotNumber));
//			log.debug("getListPiassignexternalaccessoryForEditByLotNumber successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String
//					.format("getListPiassignexternalaccessoryForEditByLotNumber in class %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	//Chuyển thành post
	@RequestMapping(value = "/listPiassignexternalaccessoryForEditByLotNumber", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListPiassignexternalaccessoryForEditByLotNumber(
			@RequestBody PiModel piModel) {
		log.info(String
				.format("getListPiassignexternalaccessoryForEditByLotNumber in class %s",
						getClass()));
		try {
			log.debug("getListPiassignexternalaccessoryForEditByLotNumber and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put(
					"listPiassignexternalaccessoryForEditByLotNumber",
					ser.getListPiassignexternalaccessoryForEditByLotNumber(piModel.getLotnumber()));
			log.debug("getListPiassignexternalaccessoryForEditByLotNumber successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getListPiassignexternalaccessoryForEditByLotNumber in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit Pi Assign External Accessory | Hàm dùng để
	 * edit lại list assign external accessory
	 * 
	 * @param lstAssignOrNot
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/assignExternalAccessory/editPiAssignExternalAccessory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editPiAssignExternalAccessory(
			@RequestBody ArrayList<PiassignexternalaccessoryModel> lstAssignOrNot) {
		log.info(String.format("editPiAssignExternalAccessory in class: %s",
				getClass()));
		try {
			log.debug("editPiAssignExternalAccessory and return assignStatus as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("editStatus",
					ser.editPiAssignExternalAccessory(lstAssignOrNot, "admin"));
			log.debug("editPiAssignExternalAccessory successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"editPiAssignExternalAccessory in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to get Accessory's Wasted Percentage And List Pi assignexternal accessory By LotNumber FactoryCode And AccessoryCode
	 * @param lotNumber
	 * @param factoryCode
	 * @param accessoryCode
	 * @return
	 */
//	@RequestMapping(value = "/getAccessoryWastedPercentageAndListPiassignexternalaccessoryByLotNumberFactoryCodeAndAccessoryCode/{lotNumber}/{factoryCode}/{accessoryCode}", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getAccessoryWastedPercentageAndListPiassignexternalaccessoryByLotNumberFactoryCodeAndAccessoryCode(
//			@PathVariable String lotNumber, @PathVariable String factoryCode, @PathVariable String accessoryCode) {
//		log.info(String.format(
//				"getAccessoryWastedPercentageAndListPiassignexternalaccessoryByLotNumberFactoryCodeAndAccessoryCode in class %s",
//				getClass()));
//		try {
//			log.debug("getAccessoryWastedPercentageAndListPiassignexternalaccessoryByLotNumberFactoryCodeAndAccessoryCode and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			result.put("status", "ok");
//			//kiểm tra xem có specific consumption chưa? nếu có r thì lấy, ko thì lấy bên config
//			if(ser.checkIfPiAssignedExternalAccessoryAlreadyHaveSpecificConsumption(lotNumber,accessoryCode))
//				result.put("wastedPercentage", ser.getPiAssignedExternalAccessorySpecificConsumption(lotNumber,accessoryCode));
//			else
//				result.put("wastedPercentage", accessoryConsumptionSer.findAccessoryConsumptionModelByFactoryCodeAndAccessoryCode(factoryCode,accessoryCode).getConsumption());
//			result.put("listPiassignexternalaccessoryByLotNumberFactoryCodeAndAccessoryCode",
//					ser.getListPiassignexternalaccessoryByLotNumberAndAccessoryCode(lotNumber, accessoryCode));
//			log.debug("getAccessoryWastedPercentageAndListPiassignexternalaccessoryByLotNumberFactoryCodeAndAccessoryCode successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String
//					.format("getAccessoryWastedPercentageAndListPiassignexternalaccessoryByLotNumberFactoryCodeAndAccessoryCode in class %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	//Chuyển thành post
	@RequestMapping(value = "/getAccessoryWastedPercentageAndListPiassignexternalaccessoryByLotNumberFactoryCodeAndAccessoryCode", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAccessoryWastedPercentageAndListPiassignexternalaccessoryByLotNumberFactoryCodeAndAccessoryCode(
			@RequestBody PiassignexternalaccessoryModel piassignexternalaccessoryModel) {
		log.info(String.format(
				"getAccessoryWastedPercentageAndListPiassignexternalaccessoryByLotNumberFactoryCodeAndAccessoryCode in class %s",
				getClass()));
		try {
			log.debug("getAccessoryWastedPercentageAndListPiassignexternalaccessoryByLotNumberFactoryCodeAndAccessoryCode and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			//kiểm tra xem có specific consumption chưa? nếu có r thì lấy, ko thì lấy bên config
			if(ser.checkIfPiAssignedExternalAccessoryAlreadyHaveSpecificConsumption(piassignexternalaccessoryModel.getLotnumber(),piassignexternalaccessoryModel.getAccessoryCode()))
				result.put("wastedPercentage", ser.getPiAssignedExternalAccessorySpecificConsumption(piassignexternalaccessoryModel.getLotnumber(),piassignexternalaccessoryModel.getAccessoryCode()));
			else
				result.put("wastedPercentage", accessoryConsumptionSer.findAccessoryConsumptionModelByFactoryCodeAndAccessoryCode(piassignexternalaccessoryModel.getFactorycode(),piassignexternalaccessoryModel.getAccessoryCode()).getConsumption());
			result.put("listPiassignexternalaccessoryByLotNumberFactoryCodeAndAccessoryCode",
					ser.getListPiassignexternalaccessoryByLotNumberAndAccessoryCode(piassignexternalaccessoryModel.getLotnumber(), piassignexternalaccessoryModel.getAccessoryCode()));
			log.debug("getAccessoryWastedPercentageAndListPiassignexternalaccessoryByLotNumberFactoryCodeAndAccessoryCode successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getAccessoryWastedPercentageAndListPiassignexternalaccessoryByLotNumberFactoryCodeAndAccessoryCode in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to edit WastedPercentage
	 * @param lotNumber
	 * @param accessoryCode
	 * @param wastedPercentage
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "/assignExternalAccessory/editWastedPercentage/{lotNumber}/{accessoryCode}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseStatus(HttpStatus.OK)
//	public Map<String, Object> editWastedPercentage(
//			@PathVariable String lotNumber, @PathVariable String accessoryCode, @RequestBody PiassignexternalaccessoryModel piassignexternalaccessoryModel) {
//		log.info(String.format("editWastedPercentage in class: %s",
//				getClass()));
//		try {
//			log.debug("editWastedPercentage and return assignStatus as json format");
//			Map<String, Object> result = new HashMap<String, Object>();
//			result.put("status", "ok");
//			result.put("editStatus",
//					ser.editWastedPercentage(lotNumber,accessoryCode,piassignexternalaccessoryModel.getSpecificconsumption()));
//			log.debug("editWastedPercentage successfully");
//			return result;
//		} catch (Exception e) {
//			log.error(String.format(
//					"editWastedPercentage in class: %s has error: %s",
//					getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	//Chuyển thành post
	@ResponseBody
	@RequestMapping(value = "/assignExternalAccessory/editWastedPercentage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editWastedPercentage(
			@RequestBody PiassignexternalaccessoryModel piassignexternalaccessoryModel) {
		log.info(String.format("editWastedPercentage in class: %s",
				getClass()));
		try {
			log.debug("editWastedPercentage and return assignStatus as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("editStatus",
					ser.editWastedPercentage(piassignexternalaccessoryModel.getLotnumber(),piassignexternalaccessoryModel.getAccessoryCode(),piassignexternalaccessoryModel.getSpecificconsumption()));
			log.debug("editWastedPercentage successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"editWastedPercentage in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
}
