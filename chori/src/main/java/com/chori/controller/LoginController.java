package com.chori.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	private static final Log log = LogFactory.getLog(LoginController.class);
	public static String currentUser = "";

	/**
	 * This function is used to redirect user to access denied page if user dont
	 * have role to access an url.
	 * 
	 * @param model
	 * @param response
	 * @return access denied view
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public ModelAndView accessDeniedPage(ModelMap model,
			HttpServletResponse response) {
		log.info(String.format(
				"accessDeniedPage with params 'model', 'response' in class %s",
				getClass()));
		try {
			response.setContentType("text/html");
			model.addAttribute("user", getPrincipal());
			log.debug("accessDeniedPage successful");
			return new ModelAndView("login/accessDenied");
		} catch (Exception e) {
			log.error(String
					.format("accessDeniedPage with params 'model', 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to return login view to user.
	 * 
	 * @param response
	 * @return login view
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(HttpServletResponse response) {
		log.info(String.format("loginPage with param 'response' in class %s",
				getClass()));
		try {
			response.setContentType("text/html");
			log.debug("loginPage successful");
			return new ModelAndView("login/login");
		} catch (Exception e) {
			log.error(String
					.format("loginPage with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to logout current logged-in user.
	 * 
	 * @param request
	 * @param response
	 * @return login view
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request,
			HttpServletResponse response) {
		log.info(String.format(
				"logoutPage with param 'request', 'response' in class %s",
				getClass()));
		try {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(request, response,
						auth);
			}
			log.debug("logoutPage successful");
			return "redirect:/login?logout";
		} catch (Exception e) {
			log.error(String
					.format("logoutPage with param 'request', 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function return userId of the current login user
	 * 
	 * @return userid
	 */
	private String getPrincipal() {
		log.info(String.format("getPrincipal in class: %s", getClass()));
		try {
			log.debug("get current login user and return it's username");
			String userName = null;
			Object principal = SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();

			if (principal instanceof UserDetails) {
				userName = ((UserDetails) principal).getUsername();
			} else {
				userName = principal.toString();
			}
			System.out.println(userName);
			log.debug("getPrincipal successfully");
			return userName;
		} catch (Exception e) {
			log.error(String.format("getPrincipal in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	@RequestMapping(value = "/isLoggedIn", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> isLoggedIn() {
		Map<String, Object> result = new HashMap<String, Object>();
		String userName = null;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
			result.put("isLoggedIn", true);
			result.put("userName", userName);
			currentUser = userName;
		} else {
			result.put("isLoggedIn", false);
		}
		return result;
	}

	public static Map<String, Object> AssignCurrentUser() {
		Map<String, Object> result = new HashMap<String, Object>();
		String userName = null;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
			result.put("isLoggedIn", true);
			result.put("userName", userName);
			currentUser = userName;
		} else {
			result.put("isLoggedIn", false);
		}
		return result;
	}
}
