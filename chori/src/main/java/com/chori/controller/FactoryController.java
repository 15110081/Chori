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

import com.chori.model.FactoryModel;
import com.chori.service.FactoryService;
import com.chori.service.RoleService;

@Controller
@RequestMapping(value = "/")
public class FactoryController {

	private static final Log log = LogFactory.getLog(FactoryController.class);

	@Autowired
	FactoryService ser;

	@Autowired
	RoleService roleSer;

	@Autowired
	@Qualifier("factoryValidator")
	private Validator factoryValidator;

	@InitBinder("factoryValidator")
	private void initBinder_FactoryValidator(WebDataBinder binder) {
		binder.setValidator(factoryValidator);
	}

	/**
	 * Return view page
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/listFactory" }, method = RequestMethod.GET)
	public String listFactory() {
		log.info(String.format(
				"listFactory with param 'response' in class: %s", getClass()));
		try {
			log.debug("return listFactory view for request");
			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Factory Management")) {
				log.debug("listFactory successful");
				return "configuration/factory/listFactory";
			}
			return "login/accessDenied";
		} catch (Exception e) {
			log.error(String
					.format("listFactory with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method is used to get all role in database and return a list role in
	 * json
	 * 
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/factory/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllFactory() {
		log.info(String.format("getAllFactory in class %s", getClass()));
		try {
			log.debug("getting list of all Factory and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<FactoryModel> ls = ser.getAllFactoryModel();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllFactory successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getAllFactory in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	// @ResponseBody
	// @RequestMapping(value = "/factory/add", method = RequestMethod.POST,
	// produces = MediaType.APPLICATION_JSON_VALUE, consumes =
	// MediaType.APPLICATION_JSON_VALUE)
	// @ResponseStatus(HttpStatus.OK)
	// public Map<String, Object> addNewFactory(
	// @RequestBody FactoryModel fm) {
	// log.info(String.format(
	// "addNewFactory with params 'fm' in class: %s",
	// getClass()));
	// try {
	// log.debug("addNewFactory and return status as json format");
	// Map<String, Object> result = new HashMap<String, Object>();
	// result.put("status", "ok");
	// result.put("addStatus", ser.addNewFactory(fm, "admin"));
	// // for (FunctionEntity functionEntity : lst) {
	// // System.err.println(functionEntity);
	// // }
	// // System.err.println("List size: " + lst.size());
	// System.err.println(fm);
	// log.debug("addNewFactory successfully");
	// return result;
	// } catch (Exception e) {
	// log.error(String
	// .format("addNewFactory with params 'fm' in class: %s has error: %s",
	// getClass(), e.getMessage()));
	// throw e;
	// }
	// }

	@ResponseBody
	@RequestMapping(value = "/factory/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addNewFactory(
			@Valid @RequestBody FactoryModel fm, BindingResult bindingResult) {
		log.info(String.format("addNewFactory with params 'fm' in class: %s",
				getClass()));

		factoryValidator.validate(fm, bindingResult);

		try {
			log.debug("addNewFactory and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");

			if (bindingResult.hasErrors()) {
				System.err.println("validation input factory invalid");
				result.put("addStatus", "invalid factory input");
				return result;
			}

			result.put("addStatus", ser.addNewFactory(fm, "admin"));
			System.err.println(fm);
			log.debug("addNewFactory successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addNewFactory with params 'fm' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

//	@RequestMapping(value = "/factory/detail/{factoryCode}", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> get1FactoryDetail(
//			@PathVariable String factoryCode) {
//		log.info(String.format("get1FactoryDetail in class %s", getClass()));
//		try {
//			log.debug("get1FactoryDetail and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			// List<FactoryModel> ls = ser.getAllFactoryModel();
//			result.put("status", "ok");
//			result.put("factory", ser.findFactoryModelById(factoryCode));
//			log.debug("get1FactoryDetail successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String.format(
//					"get1FactoryDetail in class %s has error: %s", getClass(),
//					e.getMessage()));
//			throw e;
//		}
//	}
	
	/**
	 * This function is used to find by id (post)
	 * @param fm
	 * @return
	 */
	@RequestMapping(value = "/factory/detail", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get1FactoryDetail(
			@RequestBody FactoryModel fm) {
		log.info(String.format("get1FactoryDetail in class %s", getClass()));
		try {
			log.debug("get1FactoryDetail and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			// List<FactoryModel> ls = ser.getAllFactoryModel();
			result.put("status", "ok");
			result.put("factory", ser.findFactoryModelById(fm.getFactorycode()));
			log.debug("get1FactoryDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"get1FactoryDetail in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	// @ResponseBody
	// @RequestMapping(value = "/factory/edit", method = RequestMethod.POST,
	// produces = MediaType.APPLICATION_JSON_VALUE, consumes =
	// MediaType.APPLICATION_JSON_VALUE)
	// @ResponseStatus(HttpStatus.OK)
	// public Map<String, Object> editFactory(
	// @RequestBody FactoryModel fm) {
	// log.info(String.format(
	// "editFactory with params 'fm' in class: %s",
	// getClass()));
	// try {
	// log.debug("editFactory and return status as json format");
	// Map<String, Object> result = new HashMap<String, Object>();
	// result.put("status", "ok");
	// result.put("editStatus", ser.editFactory(fm, "admin"));
	// System.err.println(fm);
	// log.debug("editFactory successfully");
	// return result;
	// } catch (Exception e) {
	// log.error(String
	// .format("editFactory with params 'fm' in class: %s has error: %s",
	// getClass(), e.getMessage()));
	// throw e;
	// }
	// }

	@ResponseBody
	@RequestMapping(value = "/factory/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editFactory(@Valid @RequestBody FactoryModel fm,
			BindingResult bindingResult) {
		log.info(String.format("editFactory with params 'fm' in class: %s",
				getClass()));

		factoryValidator.validate(fm, bindingResult);

		try {
			log.debug("editFactory and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");

			if (bindingResult.hasErrors()) {
				System.err.println("validation input factory invalid");
				result.put("editStatus", "invalid factory input");
				return result;
			}

			result.put("editStatus", ser.editFactory(fm, "admin"));
			System.err.println(fm);
			log.debug("editFactory successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"editFactory with params 'fm' in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete 1 factory
	 * 
	 * @param factoryCode
	 * @return
	 */
//	@RequestMapping(value = "/factory/delete/{factoryCode}", produces = "application/json", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> delete1Factory(@PathVariable String factoryCode) {
//		log.info(String.format("delete1Factory in class %s", getClass()));
//		try {
//			log.debug("delete1Factory and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			// List<FactoryModel> ls = ser.getAllFactoryModel();
//			result.put("status", "ok");
//			result.put("deleteStatus", ser.delete(factoryCode));
//			log.debug("delete1Factory successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String.format("delete1Factory in class %s has error: %s",
//					getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	
	@RequestMapping(value = "/factory/delete", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete1Factory(@RequestBody FactoryModel fm) {
		log.info(String.format("delete1Factory in class %s", getClass()));
		try {
			log.debug("delete1Factory and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			// List<FactoryModel> ls = ser.getAllFactoryModel();
			result.put("status", "ok");
			result.put("deleteStatus", ser.delete(fm.getFactorycode()));
			log.debug("delete1Factory successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("delete1Factory in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a factory is existed
	 * 
	 * @param factoryCode
	 * @return
	 */
//	@RequestMapping(value = "/factory/isExist/{factoryCode}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> isFactoryExist(@PathVariable String factoryCode) {
//		log.info(String.format(
//				"isFactoryExist with param 'factoryCode' in class: %s",
//				getClass()));
//		try {
//			log.debug("check if a factory with factoryCode is existed in DB and return as json format");
//			Map<String, Object> result = new HashMap<String, Object>();
//			result.put("status", "ok");
//			result.put("isExisted", ser.isFactoryExistedById(factoryCode));
//			log.debug("check isFactoryExist successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String
//					.format("isFactoryExist with param 'factoryCode' in class %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	
	@RequestMapping(value = "/factory/isExist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isFactoryExist(@RequestBody FactoryModel fm) {
		log.info(String.format(
				"isFactoryExist with param 'factoryCode' in class: %s",
				getClass()));
		try {
			log.debug("check if a factory with factoryCode is existed in DB and return as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("isExisted", ser.isFactoryExistedById(fm.getFactorycode()));
			log.debug("check isFactoryExist successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isFactoryExist with param 'factoryCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
