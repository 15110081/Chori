package com.chori.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chori.model.BrandModel;
import com.chori.model.PiModel;
import com.chori.service.BrandService;

@RestController
@RequestMapping(value="/")
public class BrandController {
	private static final Log log = LogFactory.getLog(BrandController.class);
	
	@Autowired
	BrandService service;
	
	@RequestMapping(value = "/brand/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllStatus() {
		log.info(String.format("getAllStatus in class %s", getClass()));
		try {
			log.debug("getting list of all brand and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<BrandModel> ls = service.getAllBrandModel();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllBrand successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getAllBrand in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}

	}
	
	
	@RequestMapping(value = "/brand/detail/{brandCode}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getBrandDetail(@PathVariable int brandCode) {
		log.info(String.format("getBrandDetail with param 'brandCode' in class: %s", getClass()));
		try {
			log.debug("getting brand's detail by its brandCode and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			BrandModel en = service.findBrandById(brandCode);
			result.put("currentbrand", en);
			result.put("status", "ok");
			log.debug("getbrandDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getBrandDetail with param 'lotNumber' in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}
}
