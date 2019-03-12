//package com.chori.controller;
//
//import java.util.Set;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.chori.model.Function;
//import com.chori.service.FunctionService;
//import com.chori.service.UserService;
//
//@Controller
//public class TestController {
//	
//	private static final Log log = LogFactory.getLog(TestController.class);
//	
//	@Autowired
//	UserService userSer;
//	
//	@Autowired
//	FunctionService funcSer;
//	
//	@RequestMapping(value = { "/test/vaoTrangSearchPI" }, method = RequestMethod.GET)
//	public String testHandleRequest() {
//		if(detectFunc4User(getPrincipal(), "FT1")){
//			return "test/vaoTrangSearchPI";
//		}
//		return "login/accessDenied";
//	}
//	
//	@RequestMapping(value = { "/test/vaoTrangDanhSachShipment" }, method = RequestMethod.GET)
//	public String testHandleRequest1() {
//		if(detectFunc4User(getPrincipal(), "FT2")){
//			return "test/vaoTrangDanhSachShipment";
//		}
//		return "login/accessDenied";
//	}
//	
//	/**
//	 * This method return true if user have function to access page
//	 * @param userName
//	 * @param functionId
//	 * @return
//	 */
//	private boolean detectFunc4User(String userName, String functionId){
//		Set<Function> lst= userSer.findById(userName).getRole().getFunctions();
//		
//		Function func= funcSer.findById(functionId);
//		
//		boolean flag= false;
//		
//		for (Function function : lst) {
//			if(function.getFunctionId().equals(func.getFunctionId())){
//				flag= true;
//			}
//		}
//		
//		if(flag){
//			return true;
//		}
//		
//		return false;
//	}
//	
//	/**
//	 * This function return userId of the current login user
//	 * @return userid
//	 */
//	private String getPrincipal(){
//		log.info(String.format("getPrincipal in class: %s", getClass()));
//		try{
//			log.debug("get current login user and return it's username");
//			String userName = null;
//	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//	 
//	        if (principal instanceof UserDetails) {
//	            userName = ((UserDetails)principal).getUsername();
//	        } else {
//	            userName = principal.toString();
//	        }
//	        System.out.println(userName);
//	        log.debug("getPrincipal successfully");
//	        return userName;
//		}catch(Exception e){
//			log.error(String.format("getPrincipal in class: %s has error: %s", getClass(), e.getMessage()));
//			throw e;
//		}
//    }
// }
