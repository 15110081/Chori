package com.chori.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chori.model.FunctionModel;
import com.chori.service.FunctionService;

@RestController
@RequestMapping(value = "/")
public class FunctionController {

	@Autowired
	FunctionService ser;

	private static final Log log = LogFactory.getLog(FunctionController.class);

	/**
	 * This method is used to get all role in database and return a list role in
	 * json
	 * 
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/function/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllFunction() {
		log.info(String.format("getAllFunction in class: %s", getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			List<FunctionModel> ls = ser.getAllFunctionModel();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllFunction successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllFunction in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}
}
