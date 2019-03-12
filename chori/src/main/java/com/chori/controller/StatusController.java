//package com.chori.controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.chori.entity.StatusEntity;
//import com.chori.service.StatusService;
//
//@RestController
//@RequestMapping(value="/")
//public class StatusController {
//	private static final Log log = LogFactory.getLog(StatusController.class);
//	
//	@Autowired
//	StatusService service;
//	
//	@RequestMapping(value = "/status/list", produces = "application/json", method = RequestMethod.GET)
//    @ResponseBody
//	public Map<String, Object> getAllStatus() {
//		log.info(String.format("getAllStatus in class %s", getClass()));
//		try {
//			log.debug("getting list of all status and return json");
//	        Map<String, Object> result = new HashMap<String, Object>();
//	        List<StatusEntity> ls = service.getAllStatus();
//	        result.put("status", "ok");
//	        result.put("list", ls);
//	        log.debug("getAllStatus successful");
//	        return result;
//		}catch(Exception e){
//        	log.error(String.format("getAllStatus in class %s has error: %s", getClass(), e.getMessage()));
//        	throw e;
//        }
//
//        
//    }
//	
//	@RequestMapping(value="/liststatus",method = RequestMethod.GET)
//	public ModelAndView listStatus(HttpServletResponse response) {
//		log.info(String.format("liststatus with param 'response' in class: %s", getClass()));
//		try {
//			log.debug("return liststatus view for request");
//			response.setContentType("text/html");
//			log.debug("liststatus successful");
//			return new ModelAndView("configuration/status/list");
//		}catch(Exception e){
//			log.error(String.format("liststatus with param 'response' in class %s has error: %s", getClass(), e.getMessage()));
//			throw e;
//		}
//
//	}
//	
//	/**
//	 * This function find a Status by status code then return it as JSON format
//	 * @param statuscode
//	 * @return JSON format of a status
//	 */
//	@RequestMapping(value = "/status/detail/{statusCode}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String, Object> getStatusDetail(@PathVariable String statusCode) {
//		log.info(String.format("getStatusDetail with param 'statusCode' in class: %s", getClass()));
//		try{
//			log.debug("getting status's detail by its statusCode and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			StatusEntity en = service.findStatusEntityById(statusCode);
//			result.put("currentstatus", en);
//	        result.put("status", "ok");  
//	        log.debug("getStatusDetail successful");
//	        return result;
//		}catch(Exception e){
//			log.error(String.format("getStatusDetail with param 'statusCode' in class %s has error: %s", getClass(), e.getMessage()));
//			throw e;
//		}
//    }
//	
//	/**
//	 * This function edit a status and update into database
//	 * @param status
//	 * @return status, editStatus as JSON
//	 */
//	@ResponseBody
//    @RequestMapping(value = "/status/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    public Map<String, Object> editStatus(@RequestBody StatusEntity statusEn) {
//		log.info(String.format("editStatus with param 'order' in class: %s", getClass()));
//		try{
//			log.debug("edit 1 status and return edit status as json format");
//			Map<String, Object> result = new HashMap<String, Object>();
//	        result.put("status", "ok");
//	        result.put("editStatus", service.editStatus(statusEn));
//	        log.debug("editStatus successfully");
//	        return result;
//		}catch(Exception e){
//			log.error(String.format("editStatus with param 'order' in class %s has error: %s", getClass(), e.getMessage()));
//			throw e;
//		}
//    }
// }
