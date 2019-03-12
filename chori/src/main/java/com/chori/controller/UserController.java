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

import com.chori.model.UserModel;
import com.chori.service.RoleService;
import com.chori.service.UserService;

@Controller
@RequestMapping(value = "/")
public class UserController {

	private static final Log log = LogFactory.getLog(UserController.class);

	@Autowired
	UserService ser;

	@Autowired
	RoleService roleSer;

	@Autowired
	@Qualifier("userValidator")
	private Validator userValidator;

	@InitBinder("userValidator")
	private void initBinder_UserValidator(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

	@RequestMapping(value = { "/userProfile" }, method = RequestMethod.GET)
	public String testHandleRequest() {
		return "configuration/user/userProfile";
	}

	/**
	 * This function find a Role by id then return it as json format
	 * 
	 * @param roleId
	 * @return json format of a role
	 */
	@RequestMapping(value = "/user/getUserDetail", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserDetail() {
		log.info(String.format("getUserDetail in class: %s", getClass()));
		try {
			log.debug("getting role's detail by its roleId and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("user",
					ser.findUserModelById(LoginController.currentUser));
			log.debug("getRoleDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getUserDetail in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if current password is match
	 * 
	 * @param um
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/isCurrentPasswordMatch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> isCurrentMatch(@RequestBody UserModel um) {
		log.info(String.format("isCurrentMatch with params 'um' in class: %s",
				getClass()));
		try {
			log.debug("isCurrentMatch and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");

			result.put("isCurrentPasswordMatch", ser.isCurrentMatch(um));
			System.err.println(um);
			log.debug("isCurrentMatch successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isCurrentMatch with params 'fm' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit current user password
	 * 
	 * @param um
	 * @param bindingResult
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/editPassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editUserPassword(
			@RequestBody UserModel um) {
		log.info(String.format(
				"editUserPassword with params 'userModel' in class: %s",
				getClass()));

		System.err.println(um);

		try {
			log.debug("editUserPassword and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");

			result.put("editPasswordStatus",
					ser.editUserPassword(um, "admin", um.getNewPassword()));
			System.err.println(um);
			log.debug("editUserPassword successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("editUserPassword with params 'userModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/user/editUserProfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editUserProfile(
			@Valid @RequestBody UserModel um, BindingResult bindingResult) {
		log.info(String.format(
				"editUserProfile with params 'userModel' in class: %s",
				getClass()));

		userValidator.validate(um, bindingResult);

		System.err.println(um);

		try {
			log.debug("editUserProfile and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");

			if (bindingResult.hasErrors()) {
				System.err.println("validation input user invalid");
				result.put("editStatus", "invalid user input");
				return result;
			}

			result.put("editStatus", ser.editUserProfile(um, "admin"));
			System.err.println(um);
			log.debug("editUserProfile successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("editUserProfile with params 'userModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * Return view page
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/listUser" }, method = RequestMethod.GET)
	public String listUser() {
		log.info(String.format("listUser with param 'response' in class: %s",
				getClass()));
		try {
			log.debug("return listUser view for request");
			log.debug("listUser successful");

			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser,
					"User Management")) {
				return "configuration/user/listUser";
			}
			return "login/accessDenied";
		} catch (Exception e) {
			log.error(String.format(
					"listUser with param 'response' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method is used to get all user in database and return a list user in
	 * json
	 * 
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/user/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllUser() {
		log.info(String.format("getAllUser in class %s", getClass()));
		try {
			log.debug("getting list of all User and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<UserModel> ls = ser.getAllUserModel();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllUser successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getAllUser in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new User
	 * 
	 * @param um
	 * @return
	 */
	// @ResponseBody
	// @RequestMapping(value = "/user/add", method = RequestMethod.POST,
	// produces = MediaType.APPLICATION_JSON_VALUE, consumes =
	// MediaType.APPLICATION_JSON_VALUE)
	// @ResponseStatus(HttpStatus.OK)
	// public Map<String, Object> addNewUser(
	// @RequestBody UserModel um) {
	// log.info(String.format(
	// "addNewUser with params 'fm' in class: %s",
	// getClass()));
	// try {
	// log.debug("addNewUser and return status as json format");
	// Map<String, Object> result = new HashMap<String, Object>();
	// result.put("status", "ok");
	// result.put("addStatus", ser.addNewUser(um, "admin"));
	// System.err.println(um);
	// log.debug("addNewUser successfully");
	// return result;
	// } catch (Exception e) {
	// log.error(String
	// .format("addNewUser with params 'fm' in class: %s has error: %s",
	// getClass(), e.getMessage()));
	// throw e;
	// }
	// }

	@ResponseBody
	@RequestMapping(value = "/user/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addNewUser(@Valid @RequestBody UserModel um,
			BindingResult bindingResult) {
		log.info(String.format("addNewUser with params 'fm' in class: %s",
				getClass()));

		userValidator.validate(um, bindingResult);

		System.err.println(um);

		try {
			log.debug("addNewUser and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");

			if (bindingResult.hasErrors()) {
				System.err.println("validation input user invalid");
				result.put("addStatus", "invalid");
				return result;
			}

			result.put("addStatus", ser.addNewUser(um, "admin"));
			System.err.println(um);
			log.debug("addNewUser successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"addNewUser with params 'fm' in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get detail of 1 user.
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/user/detail", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get1UserDetail(@RequestBody UserModel um) {
		log.info(String.format("get1UserDetail in class %s", getClass()));
		try {
			log.debug("get1UserDetail and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("user", ser.findUserModelById(um.getUsername()));
			log.debug("get1UserDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("get1UserDetail in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit 1 user
	 * 
	 * @param userModel
	 * @return
	 */
	// @ResponseBody
	// @RequestMapping(value = "/user/edit", method = RequestMethod.POST,
	// produces = MediaType.APPLICATION_JSON_VALUE, consumes =
	// MediaType.APPLICATION_JSON_VALUE)
	// @ResponseStatus(HttpStatus.OK)
	// public Map<String, Object> editUser(
	// @RequestBody UserModel userModel) {
	// log.info(String.format(
	// "editUser with params 'userModel' in class: %s",
	// getClass()));
	// try {
	// log.debug("editUser and return status as json format");
	// Map<String, Object> result = new HashMap<String, Object>();
	// result.put("status", "ok");
	// result.put("editStatus", ser.editUser(userModel, "admin"));
	// System.err.println(userModel);
	// log.debug("editUser successfully");
	// return result;
	// } catch (Exception e) {
	// log.error(String
	// .format("editUser with params 'userModel' in class: %s has error: %s",
	// getClass(), e.getMessage()));
	// throw e;
	// }
	// }

	@ResponseBody
	@RequestMapping(value = "/user/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editUser(@Valid @RequestBody UserModel um,
			BindingResult bindingResult) {
		log.info(String.format("editUser with params 'userModel' in class: %s",
				getClass()));

		userValidator.validate(um, bindingResult);

		System.err.println(um);

		try {
			log.debug("editUser and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");

			if (bindingResult.hasErrors()) {
				System.err.println("validation input user invalid");
				result.put("editStatus", "invalid");
				return result;
			}

			result.put("editStatus", ser.editUser(um, "admin"));
			System.err.println(um);
			log.debug("editUser successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("editUser with params 'userModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete 1 user
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/user/delete", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete1User(@RequestBody UserModel um) {
		log.info(String.format("delete1User in class %s", getClass()));
		try {
			log.debug("delete1User and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("deleteStatus", ser.delete(um.getUsername()));
			log.debug("delete1User successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("delete1User in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a user is existed
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/user/isExist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isUserExist(@RequestBody UserModel um) {
		log.info(String.format(
				"isUserExist with param 'username' in class: %s", getClass()));
		try {
			log.debug("check if a user with username is existed in DB and return as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("isExisted", ser.isUserExistedById(um.getUsername()));
			log.debug("check isUserExist successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isUserExist with param 'username' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
