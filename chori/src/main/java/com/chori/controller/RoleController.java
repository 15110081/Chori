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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.chori.model.RoleModel;
import com.chori.service.RoleService;

@Controller
@RequestMapping(value = "/")
public class RoleController {

	private static final Log log = LogFactory.getLog(RoleController.class);

	@Autowired
	RoleService ser;

	@Autowired
	RoleService roleSer;

	/**
	 * Return view page
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/listRole" }, method = RequestMethod.GET)
	public String listRole() {
		log.info(String.format("listRole with param 'response' in class: %s",
				getClass()));
		try {
			log.debug("return listRole view for request");

			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Groups & Functions")) {
				log.debug("listRole successful");
				return "configuration/role/roleList";
			}
			return "login/accessDenied";
		} catch (Exception e) {
			log.error(String.format(
					"listRole with param 'response' in class %s has error: %s",
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
	@RequestMapping(value = "/role/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllRole() {
		log.info(String.format("getAllRole in class %s", getClass()));
		try {
			log.debug("getting list of all Role and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<RoleModel> ls = ser.getAllRoleModel();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllRole successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getAllRole in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function return role info and its List of all function that belong
	 * 
	 * @param roleId
	 * @return json format
	 */
	@RequestMapping(value = "/role/detailAndListFunc", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getRoleDetailAndItsListFunction(
			@RequestBody RoleModel roleModel) {
		log.info(String
				.format("getRoleDetailAndItsListFunction with param 'roleId' in class: %s",
						getClass()));
		try {
			log.debug("get 1 Role's detail, its List Function and return as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("role", ser.findRoleModelById(roleModel.getRoleid()));
			result.put("listFunction", ser.listAllFuncOfRoleById(roleModel.getRoleid()));
			log.debug("getRoleDetailAndItsListFunction successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getRoleDetailAndItsListFunction with param 'roleId' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method is used to assign Functions for Role
	 * 
	 * @param roleId
	 * @param lst
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "/role/AssignFuncForRole/{roleId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseStatus(HttpStatus.OK)
//	public Map<String, Object> assignFunc4Role(@PathVariable String roleId,
//			@RequestBody ArrayList<FunctionModel> lst) {
//		log.info(String.format(
//				"assignFunc4Role with params 'roleId', 'lst' in class: %s",
//				getClass()));
//		try {
//			log.debug("assign function for role and return assignStatus as json format");
//			Map<String, Object> result = new HashMap<String, Object>();
//			result.put("status", "ok");
//			result.put("assignStatus", ser.assignFunctionForRole(roleId, lst));
//			for (FunctionModel functionEntity : lst) {
//				System.err.println(functionEntity);
//			}
//			System.err.println("List size: " + lst.size());
//			log.debug("assignFunc4Role successfully");
//			return result;
//		} catch (Exception e) {
//			log.error(String
//					.format("assignFunc4Role with params 'roleId', 'lst' in class: %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	
	@ResponseBody
	@RequestMapping(value = "/role/AssignFuncForRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> assignFunc4Role(
			@RequestBody RoleModel role) {
		log.info(String.format(
				"assignFunc4Role with params 'roleId', 'lst' in class: %s",
				getClass()));
		try {
			log.debug("assign function for role and return assignStatus as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("assignStatus", ser.assignFunctionForRole(role.getRoleid(), role.getListFunction()));
//			for (FunctionModel functionEntity : lst) {
//				System.err.println(functionEntity);
//			}
//			System.err.println("List size: " + lst.size());
			log.debug("assignFunc4Role successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("assignFunc4Role with params 'roleId', 'lst' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new role
	 * 
	 * @param roleModel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/role/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addNewRole(@RequestBody RoleModel roleModel) {
		log.info(String.format(
				"addNewRole with params 'roleModel' in class: %s", getClass()));

		System.err.println(roleModel);

		try {
			log.debug("addNewRole and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("addStatus", ser.addNewRole(roleModel, "admin"));
			log.debug("addNewRole successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addNewRole with params 'roleModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a role is existed
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/role/isExist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isRoleExist(@RequestBody RoleModel roleModel) {
		log.info(String.format("isRoleExist with param 'roleId' in class: %s",
				getClass()));
		try {
			log.debug("check if a role with roleId is existed in DB and return as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("isExisted", ser.isRoleExistedById(roleModel.getRoleid()));
			log.debug("check isRoleExist successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("isRoleExist in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete a role
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/role/delete", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteRole(@RequestBody RoleModel roleModel) {
		log.info(String.format("deleteRole in class %s", getClass()));
		try {
			log.debug("deleteRole and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("deleteStatus", ser.deleteRole(roleModel.getRoleid()));
			log.debug("deleteRole successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("deleteRole in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get detail of a role
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/role/detail", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getRoleDetail(@RequestBody RoleModel roleModel) {
		log.info(String.format("getRoleDetail in class %s", getClass()));
		try {
			log.debug("getRoleDetail and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("role", ser.findRoleModelById(roleModel.getRoleid()));
			log.debug("getRoleDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getRoleDetail in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit a role
	 * 
	 * @param roleModel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/role/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editRole(@RequestBody RoleModel roleModel) {
		log.info(String.format("editRole with params 'roleModel' in class: %s",
				getClass()));

		System.err.println(roleModel);

		try {
			log.debug("editRole and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("editStatus", ser.editRole(roleModel, "admin"));
			log.debug("editRole successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("editRole with params 'roleModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
