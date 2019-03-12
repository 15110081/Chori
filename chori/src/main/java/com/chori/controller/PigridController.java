package com.chori.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.chori.dao.PIDao;
import com.chori.dao.PigridDao;
import com.chori.dao.UserDao;
import com.chori.entity.Pi;
import com.chori.entity.Pigrid;
import com.chori.model.PiModel;
import com.chori.model.PigridModel;
import com.chori.service.PigridService;

@Controller
@RequestMapping(value = "/")
public class PigridController {
	@Autowired
	PigridService ser;
	@Autowired 
	UserDao userDao;
	@Autowired
	PIDao piDao;
	@Autowired
	PigridDao dao;
	
	
	private static final Log log = LogFactory
			.getLog(PigridController.class);

	
	@ResponseBody
	@RequestMapping(value = "/pigrid/add", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addPiGrid() {
		log.info(String.format("addpigrid with param 'pigrid' in class: %s",
				getClass()));
		try {
			log.debug("add 1 PiGrid and return edit PiGrid as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			Pigrid pg=new Pigrid();
			pg.setUser(userDao.findById(LoginController.currentUser));
			pg.setCreatedate(new Date());
			
			result.put("pigrid", "ok");
			result.put("addPiGrid", ser.addPigrid(pg));
			result.put("pigridcode",pg.getPigridcode());
			log.debug("addPiGrid successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"addPiGrid with param 'PiGrid' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	@ResponseBody
	@RequestMapping(value = "/pigrid/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> deletepigrid(@RequestBody int pigridCode) {
		log.info(String.format("deletepigrid with param 'pigridCode' in class: %s",
				getClass()));
		try {
			log.debug("add 1 pigrid and return edit pigrid as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("pigrid", "ok");
			result.put("deletepigrid", ser.deletePigrid(pigridCode));
			log.debug("deletepigrid successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"deletepigrid with param 'pigridCode' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/pigrid/checkPigrid/{lotNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> checkPigrid(@PathVariable String lotNumber) {
		log.info(String.format("checkPigrid with param 'lotNumber' in class: %s",
				getClass()));
		try {
			log.debug("check 1 pigrid and return edit pigrid as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("check", "ok");
			result.put("checkPigrid", ser.checkPigridcode(lotNumber));
			log.debug("checkPigrid successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"checkPigrid with param 'lotNumber' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/pigrid/editPi", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> EditPi(@RequestBody PiModel pm) {
		log.info(String.format("addpigrid with param 'pigrid' in class: %s",
				getClass()));
		try {
			log.debug("add 1 PiGrid and return edit PiGrid as json format");
			Map<String, Object> result = new HashMap<String, Object>();
//			Pigrid pg=new Pigrid();
//			pg.setUser(userDao.findById(LoginController.currentUser));
//			pg.setCreatedate(new Date());
//			
//			result.put("pigrid", "ok");
//			result.put("addPiGrid", ser.addPigrid(pg));
//			result.put("pigridcode",pg.getPigridcode());
//			log.debug("addPiGrid successfully");
			Pi pi=piDao.findById(pm.getLotnumber());
			pi.setPigrid(dao.findById(pm.getPigridcode()));
			piDao.update(pi);
			
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"addPiGrid with param 'PiGrid' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
}
