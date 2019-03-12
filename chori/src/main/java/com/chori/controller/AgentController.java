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

import com.chori.model.AgentModel;
import com.chori.model.FactoryModel;
import com.chori.service.AgentService;
import com.chori.service.RoleService;

@Controller
@RequestMapping(value = "/")
public class AgentController {

	private static final Log log = LogFactory.getLog(AgentController.class);

	@Autowired
	AgentService ser;

	@Autowired
	RoleService roleSer;

	@Autowired
	@Qualifier("agentValidator")
	private Validator splvalidator;

	@InitBinder("agentValidator")
	private void initBinder_AgentValidator(WebDataBinder binder) {
		binder.setValidator(splvalidator);
	}

	/**
	 * This method is used to get all agent in database and return a list agent
	 * in json
	 * 
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/agent/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllAgent() {
		log.info(String.format("getAllAgent in class %s", getClass()));
		try {
			log.debug("getting list of all agent and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<AgentModel> ls = ser.getAllAgentModel();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllAgent successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getAllAgent in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * Return view page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/Office", method = RequestMethod.GET)
	public ModelAndView listAgent(HttpServletResponse response) {
		log.info(String.format("listAgent with param 'response' in class: %s",
				getClass()));
		try {
			log.debug("return listAgent view for request");
			response.setContentType("text/html");
			log.debug("listAgent successful");
			LoginController.AssignCurrentUser();
			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Agent Management")) {
				return new ModelAndView("configuration/agent/listAgent");
			}
			// return "login/accessDenied";
			return new ModelAndView("login/accessDenied");

		} catch (Exception e) {
			log.error(String
					.format("listAgent with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new agent, return addAgent as JSON format
	 * 
	 * @param agent
	 * @return agent, addAgent as JSON format
	 */
	@ResponseBody
	@RequestMapping(value = "/agent/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addNewAgent(@RequestBody AgentModel am) {
		log.info(String.format("addNewAgent with param 'am' in class: %s",
				getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String userName = LoginController.currentUser;
			result.put("addStatus", ser.addNewAgent(am, userName));
			System.err.println(am);
			log.debug("addNewAgent successfully!");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"addNewAgent with param 'am' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function find a Agent by agentCode then return it as JSON format
	 * 
	 * @param agentCode
	 * @return JSON format of a agent
	 */
	@RequestMapping(value = "/agent/detail", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAgentDetail(@RequestBody AgentModel agentModel) {
		log.info(String.format(
				"getAgentDetail with param 'agentCode' in class: %s",
				getClass()));
		try {
			log.debug("getting agent's detail by its agentCode and return json");
			Map<String, Object> result = new HashMap<String, Object>();

			AgentModel agent = ser.findAgentModelById(agentModel.getAgentcode());
			result.put("currentagent", agent);
			result.put("status", "ok");
			log.debug("getAgentDetail successfully!");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getAgentDetail with param 'agentCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function edit a agent and update into database
	 * 
	 * @param agent
	 * @return agent, editAgent as JSON
	 */
	@ResponseBody
	@RequestMapping(value = "/agent/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editAgent(@RequestBody AgentModel am) {
		log.info(String.format("editAgent with params 'am' in class: %s",
				getClass()));
		try {
			log.debug("editAgent and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String userName = LoginController.currentUser;
			result.put("editStatus", ser.editAgent(am, "admin"));
			System.err.println(am);
			log.debug("editAgent successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"editAgent with params 'am' in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	@RequestMapping(value = "/agent/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> deleteAgent(@RequestBody AgentModel agentModel) {
		log.info(String.format("deleteAgent in class %s", getClass()));
		try {
			log.debug("deleteAgent and return json");
			Map<String, Object> result = new HashMap<String, Object>();

			result.put("status", "ok");
			result.put("deleteStatus", ser.deleteAgent(agentModel.getAgentcode()));
			log.debug("deleteAgent successfully!");
			return result;
		} catch (Exception e) {
			log.error(String.format("deleteAgent in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	@RequestMapping(value = "/agent/isExist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isAgentExist(@RequestBody AgentModel agentModel) {
		log.info(String.format(
				"isAgentExist with param 'shortName' in class: %s", getClass()));
		try {
			log.debug("check if an agent with shortName is existed in DB and return as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("isExisted", ser.isAgentExistedByShortname(agentModel.getShortname()));
			log.debug("check isAgentExist successfully!");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isAgentExist with param 'shortName' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
