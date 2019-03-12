package com.chori.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.ModelAndView;

import com.chori.model.AccessorySupplierModel;
import com.chori.service.AccessorySupplierService;
import com.chori.service.RoleService;

@Controller
@RequestMapping(value = "/")
public class AccessorySupplierController {

	private static final Log log = LogFactory
			.getLog(AccessorySupplierController.class);

	@Autowired
	AccessorySupplierService accsupser;

	@Autowired
	RoleService roleSer;

	@Autowired
	@Qualifier("accessorysupplierValidator")
	private Validator splvalidator;

	@Autowired
	@Qualifier("accessorysuppliercontactValidator")
	private Validator splcontactvalidator;

	@InitBinder("accessorysupplierValidator")
	private void initBinder_CustomerValidator(WebDataBinder binder) {
		binder.setValidator(splvalidator);
	}

	@InitBinder("accessorysuppliercontactValidator")
	private void initBinder_CustomerContactValidator(WebDataBinder binder) {
		binder.setValidator(splcontactvalidator);
	}

	/**
	 * Return view page
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/listaccessorysupplier" }, method = RequestMethod.GET)
	public ModelAndView listAccessorySupplier(HttpServletResponse response) {
		log.info(String.format(
				"listFactory with param 'response' in class: %s", getClass()));
		try {
			log.debug("return listAccessorySupplier view for request");
			response.setContentType("text/html");
			Map<String, Object> result = new HashMap<String, Object>();
			List<AccessorySupplierModel> ls = accsupser
					.getAllAccessorySupplierModel();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("listAccessorySupplier successful");
			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Accessory Supplier")) {
				return new ModelAndView(
						"configuration/accessorysupplier/listAccSup");
			}
			return new ModelAndView("login/accessDenied");
		} catch (Exception e) {
			log.error(String
					.format("listAccessorySupplier with param 'response' in class %s has error: %s",
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
	@RequestMapping(value = "/accessorysupplier/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllAccessorySupplier() {
		log.info(String.format("getAllAccessorySupplier in class %s",
				getClass()));
		try {
			log.debug("getting list of all AccessorySupplier and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<AccessorySupplierModel> ls = accsupser
					.getAllAccessorySupplierModel();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllAccessorySupplier successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllAccessorySupplier in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function add a accessorysupplier into database
	 * 
	 * @param accessorysupplier
	 * @return accessorysupplier, addAccessorysupplier as JSON
	 */

	@ResponseBody
	@RequestMapping(value = "/accessorysupplier/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addNewAccessorySupplier(@Valid 
			@RequestBody AccessorySupplierModel am, BindingResult bindingResult) {
		log.info(String.format(
				"addNewAccessorySupplier with params 'am' in class: %s",
				getClass()));
		
		splvalidator.validate(am, bindingResult);
		
		try {
			log.debug("addNewAccessorySupplier and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			
			if (bindingResult.hasErrors()) {
				System.err.println("validation input accessory invalid");
				result.put("addStatus", "invalid");
				return result;
			}
			
			result.put("addStatus",
					accsupser.addNewAccSup(am, LoginController.currentUser));

			System.err.println(am);
			log.debug("addNewAccessorySupplier successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addNewAccessorySupplier with params 'am' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function find a accessorysupplier by accsupplierCode
	 * 
	 * @param accsupplierCode
	 * @return status as JSON
	 */

	@RequestMapping(value = "/accessorysupplier/detail", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAccessorySupplierDetail(
			@RequestBody AccessorySupplierModel accupModel) {
		log.info(String.format("getAccessoryDetail in class %s", getClass()));
		try {
			log.debug("get1FactoryDetail and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("accessorysupplier",
					accsupser.findAccessorySupModelById(accupModel.getAccessorysuppliercode()));
			log.debug("getAccessoryDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAccessoryDetail in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function edit a accessorysupplier and update into database
	 * 
	 * @param accessorysupplier
	 * @return status as JSON
	 */
	@ResponseBody
	@RequestMapping(value = "/accessorysupplier/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editAccessorySupplier(@Valid 
			@RequestBody AccessorySupplierModel am, BindingResult bindingResult) {
		log.info(String.format(
				"editAccessorySupplier with params 'am' in class: %s",
				getClass()));
		
		splvalidator.validate(am, bindingResult);
		
		try {
			log.debug("editAccessorySupplier and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			
			if (bindingResult.hasErrors()) {
				System.err.println("validation input accessory invalid");
				result.put("editStatus", "invalid");
				return result;
			}
			
			result.put("editStatus",
					accsupser.editAccSup(am, LoginController.currentUser));
			System.err.println(am);
			log.debug("editAccessorySupplier successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("editAccessorySupplier with params 'am' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}

	/**
	 * This function delete a accessorysupplier by accsupplierCode
	 * 
	 * @param accsupplierCode
	 * @return status as JSON
	 */

	@RequestMapping(value = "/accessorysupplier/delete", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteAccessorySupplier(
			@RequestBody AccessorySupplierModel accsupModel) {
		log.info(String.format("deleteAccessorySupplier in class %s",
				getClass()));
		try {
			log.debug("deleteAccessorySupplier and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("deleteStatus", accsupser.delete(accsupModel.getAccessorysuppliercode()));
			log.debug("deleteAccessorySupplier successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"deleteAccessorySupplier in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete 1 accessorysupplier
	 * 
	 * @param accsupplierCode
	 * @return
	 */

	@RequestMapping(value = "/accessorysupplier/isExist/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isAccSupExist(
			@RequestBody AccessorySupplierModel accsupModel) {
		log.info(String.format(
				"isAccSupExist with param 'accsupCode' in class: %s",
				getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("isExisted",
					accsupser.isAccSupExistedById(accsupModel.getAccessorysuppliercode()));
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isAccSupExist with param 'accsupCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

}