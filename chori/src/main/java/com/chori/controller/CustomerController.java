package com.chori.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.chori.model.CustomerModel;
import com.chori.service.CustomerService;
import com.chori.service.RoleService;

@Controller
@RequestMapping(value = "/")
public class CustomerController {

	private static final Log log = LogFactory.getLog(CustomerController.class);

	@Autowired
	CustomerService ser;

	@Autowired
	RoleService roleSer;

	@Autowired
	@Qualifier("customerValidator")
	private Validator splvalidator;

	@Autowired
	@Qualifier("customercontactValidator")
	private Validator splcontactvalidator;

	@InitBinder("customerValidator")
	private void initBinder_CustomerValidator(WebDataBinder binder) {
		binder.setValidator(splvalidator);
	}

	@InitBinder("customercontactValidator")
	private void initBinder_CustomerContactValidator(WebDataBinder binder) {
		binder.setValidator(splcontactvalidator);
	}

	/**
	 * Return view page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listCustomer", method = RequestMethod.GET)
	public ModelAndView listCustomer(HttpServletResponse response) {
		log.info(String.format(
				"listCustomer with param 'response' in class: %s", getClass()));
		try {
			log.debug("return listCustomer view for request");
			response.setContentType("text/html");
			log.debug("listCustomer successful");
			LoginController.AssignCurrentUser();
			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Customer Management")) {
				return new ModelAndView("configuration/customer/listCustomer");
			}
			// return "login/accessDenied";
			return new ModelAndView("login/accessDenied");
		} catch (Exception e) {
			log.error(String
					.format("listcustomer with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method is used to get all customer in database and return a list
	 * customer in json
	 * 
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/customer/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllCustomer() {
		log.info(String.format("getAllCustomer in class %s", getClass()));
		try {
			log.debug("getting list of all Customer and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<CustomerModel> ls = ser.getAllCustomerModel();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllCustomer successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getAllCustomer in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	// @RequestMapping(value = "/customer/delete/{customerCode}", method =
	// RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes
	// = MediaType.APPLICATION_JSON_VALUE)
	// @ResponseBody
	// public Map<String, Object> deleteCustomer(@PathVariable String
	// customerCode) {
	// log.info(String.format("deleteCustomer in class %s", getClass()));
	// try {
	// log.debug("deleteCustomer and return json");
	// Map<String, Object> result = new HashMap<String, Object>();
	//
	// result.put("status", "ok");
	// result.put("deleteStatus",ser.delete(customerCode));
	// log.debug("deleteCustomer successful");
	// return result;
	// } catch (Exception e) {
	// log.error(String.format("deleteCustomer in class %s has error: %s",
	// getClass(), e.getMessage()));
	// throw e;
	// }
	// }

	/**
	 * This function is used to add new customer, return addCustomer as JSON
	 * format
	 * 
	 * @param customer
	 * @return color, addCustomer as JSON format
	 */
	@ResponseBody
	@RequestMapping(value = "/customer/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addNewCustomer(@RequestBody CustomerModel cm) {
		log.info(String.format("addNewCustomer with params 'cm' in class: %s",
				getClass()));
		try {
			log.debug("addNewCustomer and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("addStatus", ser.addNewCustomer(cm, "admin"));
			System.err.println(cm);
			log.debug("addNewCustomer successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addNewCustomer with params 'cm' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used set view to display customer add
	 */

	// @RequestMapping(value = { "/addCustomer" }, method = RequestMethod.GET)
	// public ModelAndView handleRequest(HttpServletResponse response) {
	// response.setContentType("text/html");
	// //currentUser=getPrincipal();
	// return new ModelAndView("configuration/customer/add");
	// }
	/**
	 * This function find a Customer by customerCode then return it as JSON
	 * format
	 * 
	 * @param customerModel
	 * @return JSON format of a customer
	 */
//	@RequestMapping(value = "/customer/detail/{customerCode}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getCustomerDetail(
//			@PathVariable String customerCode) {
//		log.info(String.format(
//				"getCustomerDetail with param 'customerCode' in class: %s",
//				getClass()));
//		try {
//			log.debug("getting customer's detail by its customerCode and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//
//			CustomerModel customer = ser.findCustomerModelById(customerCode);
//			result.put("currentcustomer", customer);
//			result.put("status", "ok");
//			log.debug("getCustomerDetail successfully!");
//			return result;
//		} catch (Exception e) {
//			log.error(String
//					.format("getCustomerDetail with param 'customerCode' in class %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	
	@RequestMapping(value = "/customer/detail", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCustomerDetail(
			@RequestBody CustomerModel customerModel) {
		log.info(String.format(
				"getCustomerDetail with param 'customerCode' in class: %s",
				getClass()));
		try {
			log.debug("getting customer's detail by its customerCode and return json");
			Map<String, Object> result = new HashMap<String, Object>();

			CustomerModel customer = ser.findCustomerModelById(customerModel.getCustomercode());
			result.put("currentcustomer", customer);
			result.put("status", "ok");
			log.debug("getCustomerDetail successfully!");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getCustomerDetail with param 'customerCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function edit a customer and update into database
	 * 
	 * @param customer
	 * @return customer, editCustomer as JSON
	 */
	@ResponseBody
	@RequestMapping(value = "/customer/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editCustomer(@RequestBody CustomerModel cm) {
		log.info(String.format("editCustomer with params 'cm' in class: %s",
				getClass()));
		try {
			log.debug("editCustomer and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("editStatus", ser.editCustomer(cm, "admin"));
			System.err.println(cm);
			log.debug("editCustomer successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"editCustomer with params 'cm' in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}

	}

	@RequestMapping(value = "/customer/isExist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isCustomerExist(@RequestBody CustomerModel customerModel) {
		log.info(String.format(
				"isCustomerExist with param 'customerCode' in class: %s",
				getClass()));
		try {
			log.debug("check if an customer with customerModel is existed in DB and return as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("isExisted", ser.isCustomerExistedById(customerModel.getCustomercode()));
			log.debug("check isCustomerExist successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isCustomerExist with param 'customerCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
