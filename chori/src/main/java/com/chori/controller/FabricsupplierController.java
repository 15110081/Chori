package com.chori.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.chori.model.FabricsupplierModel;
import com.chori.service.FabricsupplierService;
import com.chori.service.RoleService;

@Controller
@RequestMapping(value = "/")
public class FabricsupplierController {

	private static final Log log = LogFactory
			.getLog(FabricsupplierController.class);

	@Autowired
	FabricsupplierService ser;

	@Autowired
	RoleService roleSer;

	@Autowired
	@Qualifier("fabricsupplierValidator")
	private Validator fabricsupplierValidator;

	@InitBinder("fabricsupplierValidator")
	private void initBinder_FabricsupplierValidatorValidator(
			WebDataBinder binder) {
		binder.setValidator(fabricsupplierValidator);
	}

	/**
	 * Return view page
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/listFabricSupplier" }, method = RequestMethod.GET)
	public String listFabricSupplier() {
		log.info(String.format("listFabricSupplier with in class: %s",
				getClass()));
		try {
			log.debug("return listFabricSupplier view for request");

			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Fabric Supplier")) {
				log.debug("listCurrency successful");
				return "configuration/fabricSupplier/listFabricSupplier";
			}
			return "login/accessDenied";

		} catch (Exception e) {
			log.error(String.format(
					"listFabricSupplier in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method is used to get all role in database and return a list role in
	 * json
	 * 
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/fabricSupplier/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllFabricSupplier() {
		log.info(String.format("getAllFabricSupplier in class %s", getClass()));
		try {
			log.debug("getting list of all FabricSupplier and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<FabricsupplierModel> ls = ser.getAllFabricsupplierModel();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllFabricSupplier successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllFabricSupplier in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	// @ResponseBody
	// @RequestMapping(value = "/fabricSupplier/add", method =
	// RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes
	// = MediaType.APPLICATION_JSON_VALUE)
	// @ResponseStatus(HttpStatus.OK)
	// public Map<String, Object> addNewFabricSupplier(
	// @RequestBody FabricsupplierModel fm) {
	// log.info(String.format(
	// "addNewFabricSupplier with params 'fm' in class: %s",
	// getClass()));
	// try {
	// log.debug("addNewFabricSupplier and return status as json format");
	// Map<String, Object> result = new HashMap<String, Object>();
	// result.put("status", "ok");
	// result.put("addStatus", ser.addNewFabricSupplier(fm, "admin"));
	// // for (FunctionEntity functionEntity : lst) {
	// // System.err.println(functionEntity);
	// // }
	// // System.err.println("List size: " + lst.size());
	// System.err.println(fm);
	// log.debug("addNewFabricSupplier successfully");
	// return result;
	// } catch (Exception e) {
	// log.error(String
	// .format("addNewFabricSupplier with params 'fm' in class: %s has error: %s",
	// getClass(), e.getMessage()));
	// throw e;
	// }
	// }

	/**
	 * This function is used to add new fabric Supplier
	 * 
	 * @param fm
	 * @param bindingResult
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/fabricSupplier/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addNewFabricSupplier(
			@Valid @RequestBody FabricsupplierModel fm,
			BindingResult bindingResult) {
		log.info(String.format(
				"addNewFabricSupplier with params 'fm' in class: %s",
				getClass()));

		fabricsupplierValidator.validate(fm, bindingResult);

		try {
			log.debug("addNewFabricSupplier and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");

			if (bindingResult.hasErrors()) {
				System.err.println("validation input fabricSupplier invalid");
				result.put("addStatus", "invalid fabricSupplier input");
				return result;
			}

			result.put("addStatus", ser.addNewFabricSupplier(fm, "admin"));
			System.err.println(fm);
			log.debug("addNewFabricSupplier successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addNewFabricSupplier with params 'fm' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

//	@RequestMapping(value = "/fabricSupplier/detail/{fabricSupplierCode}", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> get1FabricSupplierDetail(
//			@PathVariable String fabricSupplierCode) {
//		log.info(String.format("get1FabricSupplierDetail in class %s",
//				getClass()));
//		try {
//			log.debug("get1FabricSupplierDetail and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			// List<FactoryModel> ls = ser.getAllFactoryModel();
//			result.put("status", "ok");
//			result.put("fabricSupplier",
//					ser.findFabricsupplierModelById(fabricSupplierCode));
//			log.debug("get1FabricSupplierDetail successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String.format(
//					"get1FabricSupplierDetail in class %s has error: %s",
//					getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	
	/**
	 * This function is used to find fabric supplier by its id
	 * @param fm
	 * @return
	 */
	@RequestMapping(value = "/fabricSupplier/detail", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get1FabricSupplierDetail(
			@RequestBody FabricsupplierModel fm) {
		log.info(String.format("get1FabricSupplierDetail in class %s",
				getClass()));
		try {
			log.debug("get1FabricSupplierDetail and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			// List<FactoryModel> ls = ser.getAllFactoryModel();
			result.put("status", "ok");
			result.put("fabricSupplier",
					ser.findFabricsupplierModelById(fm.getFabricsupcode()));
			log.debug("get1FabricSupplierDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"get1FabricSupplierDetail in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to update 1 Fabric Supplier
	 * 
	 * @param fsm
	 * @return
	 */
	// @ResponseBody
	// @RequestMapping(value = "/fabricSupplier/edit", method =
	// RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes
	// = MediaType.APPLICATION_JSON_VALUE)
	// @ResponseStatus(HttpStatus.OK)
	// public Map<String, Object> editFabricSupplier(
	// @RequestBody FabricsupplierModel fsm) {
	// log.info(String.format(
	// "editFabricSupplier with params 'fsm' in class: %s",
	// getClass()));
	// try {
	// log.debug("editFabricSupplier and return status as json format");
	// Map<String, Object> result = new HashMap<String, Object>();
	// result.put("status", "ok");
	// result.put("editStatus", ser.editFabricSupplier(fsm, "admin"));
	// System.err.println(fsm);
	// log.debug("editFabricSupplier successfully");
	// return result;
	// } catch (Exception e) {
	// log.error(String
	// .format("editFabricSupplier with params 'fsm' in class: %s has error: %s",
	// getClass(), e.getMessage()));
	// throw e;
	// }
	// }

	@ResponseBody
	@RequestMapping(value = "/fabricSupplier/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editFabricSupplier(
			@Valid @RequestBody FabricsupplierModel fsm,
			BindingResult bindingResult) {
		log.info(String
				.format("editFabricSupplier with params 'fsm' in class: %s",
						getClass()));

		fabricsupplierValidator.validate(fsm, bindingResult);

		try {
			log.debug("editFabricSupplier and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");

			if (bindingResult.hasErrors()) {
				System.err.println("validation input fabricSupplier invalid");
				result.put("editStatus", "invalid fabricSupplier input");
				return result;
			}

			result.put("editStatus", ser.editFabricSupplier(fsm, "admin"));
			System.err.println(fsm);
			log.debug("editFabricSupplier successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("editFabricSupplier with params 'fsm' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete 1 Fabric supplier
	 * 
	 * @param fabricSupplierCode
	 * @return
	 */
	@RequestMapping(value = "/fabricSupplier/delete", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete1FabricSupplier(
			@RequestBody FabricsupplierModel fm) {
		log.info(String.format("delete1FabricSupplier in class %s", getClass()));
		try {
			log.debug("delete1FabricSupplier and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			// List<FactoryModel> ls = ser.getAllFactoryModel();
			result.put("status", "ok");
			result.put("deleteStatus", ser.delete(fm.getFabricsupcode()));
			log.debug("delete1FabricSupplier successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"delete1FabricSupplier in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a fabric supplier is existed
	 * 
	 * @param fabricSupplierCode
	 * @return
	 */
	@RequestMapping(value = "/fabricSupplier/isExist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isFabricSupplierExist(
			@RequestBody FabricsupplierModel fm) {
		log.info(String
				.format("isFabricSupplierExist with param 'fabricSupplierCode' in class: %s",
						getClass()));
		try {
			log.debug("check if an fabric supplier with fabricSupplierCode is existed in DB and return as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("isExisted",
					ser.isFabricSupplierExistedById(fm.getFabricsupcode()));
			log.debug("check isFabricSupplierExist successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isFabricSupplierExist with param 'fabricSupplierCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
