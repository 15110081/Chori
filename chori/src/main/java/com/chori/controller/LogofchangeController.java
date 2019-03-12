package com.chori.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chori.model.LogofchangeModel;
import com.chori.model.PiModel;
import com.chori.service.LogofchangeService;

@Controller
@RequestMapping(value = "/")
public class LogofchangeController {
	private static final Log log = LogFactory.getLog(LogofchangeController.class);
	
	@Autowired
	LogofchangeService service;
	
	@Autowired
	MessageSource messageSource;
	
	
	@RequestMapping(value = "/logofchange/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllStatus() {
		log.info(String.format("getAllStatus in class %s", getClass()));
		try {
			log.debug("getting list of all log of change and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<LogofchangeModel> ls = service.getAllLogofchangeModel();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllLogOfChange successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getAllLogOfChange in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}
}
