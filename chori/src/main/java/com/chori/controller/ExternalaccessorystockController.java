package com.chori.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.chori.entity.User;
import com.chori.model.ExternalAccessoryStockModel;
import com.chori.service.ExternalAccessoryStockService;
import com.chori.service.UserService;

@RestController
@RequestMapping(value = "/")
public class ExternalaccessorystockController {
	private static final Log log = LogFactory
			.getLog(ExternalaccessorystockController.class);
	@Autowired
	ExternalAccessoryStockService service;

	@Autowired
	UserService userSer;
	/**
	 * This function check if a externalaccessorystock with given ordersheetno
	 * is existed on database.
	 * 
	 * @param ordersheetno
	 * @return JSON value
	 */
	@RequestMapping(value = "/externalaccessorystock/isExist/{accessoryCode}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> isExternalAccessoryStockExist(
			@PathVariable String accessoryCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "ok");
		result.put("isExisted", service.isExistExternalaccessorystockModelByAccessory(accessoryCode));
		return result;
	}

	@RequestMapping(value = "/externalaccessorystock", method = RequestMethod.GET)
	public ModelAndView Stock(HttpServletResponse response) {
		log.info(String
				.format("listorderexternalaccessory with param 'response' in class: %s",
						getClass()));
		try {
			log.debug("return listorderexternalaccessory view for request");
			response.setContentType("text/html");
			log.debug("liststype successful");
			return new ModelAndView("operation/orderexternalaccessory/stock");
		} catch (Exception e) {
			log.error(String
					.format("externalaccessorytock with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}
	
	@ResponseBody
	@RequestMapping(value = "/externalaccessorytock/getInventoryQuantity/{accessoryCode}", method = RequestMethod.GET)
	public float GetInventoryQuantity(HttpServletResponse response,@PathVariable String accessoryCode) {
		log.info(String
				.format("listorderexternalaccessory with param 'accessoryCode' in class: %s",
						getClass()));
		try {
			log.debug("listorderexternalaccessory request");
			return service.getinventoryquantity(accessoryCode);
		} catch (Exception e) {
			log.error(String
					.format("listorderexternalaccessory with param 'accessoryCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}		
	} 
	
	@ResponseBody
	@RequestMapping(value = "/externalaccessorytock/getEstimateQuantity/{lotNumber}/{accessoryCode}", method = RequestMethod.GET)
	public float GetEstimateQuantity(HttpServletResponse response,@PathVariable String lotNumber,@PathVariable String accessoryCode) {
		log.info(String
				.format("GetEstimateQuantity with param 'response' in class: %s",
						getClass()));		
		try {
			log.debug("GetEstimateQuantity request");
			return service.getEstimateQuantity(lotNumber, accessoryCode);
		} catch (Exception e) {
			log.error(String
					.format("GetEstimateQuantity in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}	
	} 
	
	@ResponseBody
	@RequestMapping(value = "/externalaccessorytock/getActualAssignQuantity/{lotNumber}/{accessoryCode}", method = RequestMethod.GET)
	public float GetActualAssignQuantity(@PathVariable String lotNumber,@PathVariable String accessoryCode) {
		log.info(String
				.format("GetActualAssignQuantity with param 'response' in class: %s",
						getClass()));		
		try {
			log.debug("GetActualAssignQuantity request");
			return service.getActualAssignQuantity(lotNumber, accessoryCode);
		} catch (Exception e) {
			log.error(String
					.format("GetActualAssignQuantity in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}	
	} 
	
	@ResponseBody
	@RequestMapping(value = "/externalaccessorystock/getShortageQuantity/{lotNumber}/{accessoryCode}", method = RequestMethod.GET)
	public float GetShortageQuantity(@PathVariable String lotNumber,@PathVariable String accessoryCode) {
		log.info(String
				.format("GetShortageQuantity with param 'response' in class: %s",
						getClass()));		
		try {
			log.debug("GetShortageQuantity request");
			return service.calculateShortageQuantity(lotNumber, accessoryCode);
		} catch (Exception e) {
			log.error(String
					.format("GetShortageQuantity in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}	
	} 
	
	@ResponseBody
	@RequestMapping(value = "/externalaccessorytock/getStockAvailableQuantityInformation/{accessoryCode}/{factoryCode}/{lotNumber}", method = RequestMethod.GET)
	public ExternalAccessoryStockModel getStockAvailableQuantityInformation(@PathVariable String accessoryCode,@PathVariable String factoryCode,@PathVariable String lotNumber) {
		log.info(String
				.format("getStockAvailableQuantityInformation with param 'response' in class: %s",
						getClass()));		
		try {
			log.debug("getStockAvailableQuantityInformation request");
			return service.getStockAvailableQuantityInformation(accessoryCode,factoryCode,lotNumber);
		} catch (Exception e) {
			log.error(String
					.format("getStockAvailableQuantityInformation in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}	
	}
	
	@ResponseBody
	@RequestMapping(value = "/externalaccessorytock/saveAssignFromStock/{accessoryCode}/{factoryCode}/{assignQty}", method = RequestMethod.GET)
	public Float saveAssignFromStock(@PathVariable String accessoryCode, @PathVariable String factoryCode, @PathVariable Float assignQty) {
		log.info(String
				.format("saveAssignFromStock with param 'response' in class: %s",
						getClass()));		
		try {
			log.debug("saveAssignFromStock request");
			return service.saveAssignFromStock(accessoryCode,factoryCode,assignQty);
		} catch (Exception e) {
			log.error(String
					.format("saveAssignFromStock in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}	
	}
	
	@ResponseBody
	@RequestMapping(value = "/externalaccessorytock/updateStockAssignQtyOfAnAssign/{accessoryCode}/{lotNumber}/{stockAssignQty}", method = RequestMethod.GET)
	public boolean updateStockAssignQtyOfAnAssign(@PathVariable String accessoryCode, @PathVariable String lotNumber, @PathVariable Float stockAssignQty) {
		log.info(String
				.format("updateStockAssignQtyOfAnAssign with param 'response' in class: %s",
						getClass()));		
		try {
			log.debug("updateStockAssignQtyOfAnAssign request");
			return service.updateStockAssignQtyOfAnAssign(accessoryCode,lotNumber,stockAssignQty);
		} catch (Exception e) {
			log.error(String
					.format("updateStockAssignQtyOfAnAssign in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}	
	}
	
	@ResponseBody
	@RequestMapping(value = "/externalaccessorytock/updateStockAvailableQtyWhenEdit/{accessoryCode}/{lotNumber}", method = RequestMethod.GET)
	public boolean updateStockAvailableQtyWhenEdit(@PathVariable String accessoryCode, @PathVariable String lotNumber) {
		log.info(String
				.format("updateStockAvailableQtyWhenEdit with param 'response' in class: %s",
						getClass()));		
		try {
			log.debug("updateStockAvailableQtyWhenEdit request");
			return service.updateStockAvailableQtyWhenEdit(accessoryCode,lotNumber);
		} catch (Exception e) {
			log.error(String
					.format("updateStockAvailableQtyWhenEdit in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}	
	}
	
	
	/**
	 * Send email part
	 */
	private JavaMailSender mailSender;
	
	/**
	 * Hàm validate email
	 * @param email
	 * @return
	 */
	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}
	
	/**
	 * Hàm khởi tạo, lấy thông tin của ng gởi email
	 * @return
	 */
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		//get log in user
		User currentUser = userSer.findById(LoginController.currentUser);
		
		// Using Gmail SMTP configuration.
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername(currentUser.getEmail());
		mailSender.setPassword(currentUser.getEmailpassword());

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");

		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}
	
	/**
	 * This function is used to handle send mail request
	 * Hàm đc dùng để xử lý send mail
	 * @param request
	 * @param attachFile
	 * @return
	 */
	@RequestMapping(value = "/sendmailForOrderExternalAccessoryInStock", method = RequestMethod.POST)
	public ModelAndView sendEmail(HttpServletRequest request,
			final @RequestParam CommonsMultipartFile attachFile) {

		try{
			// reads form input
			final String emailTo = request.getParameter("mailTo");
			final String subject = request.getParameter("subject");
			final String message = request.getParameter("message");
			final String CC = request.getParameter("CC");

			// for logging
			System.out.println("emailTo: " + emailTo);
			System.out.println("subject: " + subject);
			System.out.println("message: " + message);
			System.out.println("CC: " + CC);
			System.out.println("attachFile: " + attachFile.getOriginalFilename());

			mailSender = getMailSender();
			
			mailSender.send(new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper messageHelper = new MimeMessageHelper(
							mimeMessage, true, "UTF-8");
					
					//email to part
					//khai báo list email to
					List<String> lstEmailTo= new ArrayList<String>();
					//mảng nhận, tách các trường hợp , hay ;
					String[] listEmailToFromView = null;
					if (emailTo.contains(";")) {
						listEmailToFromView = emailTo.split(";");
					} else if (emailTo.contains(",")) {
						listEmailToFromView = emailTo.split(",");
					} else {
						listEmailToFromView = emailTo.split(" ");
					}
					for (String string : listEmailToFromView) {
						if(OrderInternalAccessoryController.isValidEmailAddress(string.trim()))
							lstEmailTo.add(string.trim());
					}
					
					if(lstEmailTo.size()>0){
						String[] arrayEmailTo = new String[lstEmailTo.size()];
						for(int i=0;i<lstEmailTo.size();++i){
							arrayEmailTo[i]= lstEmailTo.get(i);
						}
						messageHelper.setTo(arrayEmailTo);
					}
					//end email to part
					
					messageHelper.setSubject(subject);
					//send html content
					messageHelper.setText(message, true);
					//messageHelper.setText(message);
					
					//cc part
					List<String> lstCC= new ArrayList<String>();
					List<User> lstUser = userSer.getAll();
					//lặp qua list tất cả user, nếu user nào đc check là is CC thì add vào listCC
					for (User user : lstUser) {
						if(user.getCcmailstring()){
							lstCC.add(user.getEmail());
						}
					}
					
					//bỏ đi email của current user
					lstCC.remove(userSer.findById(LoginController.currentUser).getEmail());
					
					//lấy cc từ view
					String[] listCCfromView = null;//CC.split(";");
					if (CC.contains(";")) {
						listCCfromView = CC.split(";");
					} else if (CC.contains(",")) {
						listCCfromView = CC.split(",");
					} else {
						listCCfromView = CC.split(" ");
					}
					for (String string : listCCfromView) {
						//nếu là valid email thì cho vô list
						if(OrderInternalAccessoryController.isValidEmailAddress(string.trim()))
							lstCC.add(string.trim());
					}
					
					if(lstCC.size()>0){
						String[] arrayCC = new String[lstCC.size()];
						for(int i=0;i<lstCC.size();++i){
							arrayCC[i]= lstCC.get(i);
						}
						messageHelper.setCc(arrayCC);
					}

					// determines if there is an upload file, attach it to the
					// e-mail
					String attachName = attachFile.getOriginalFilename();
					if (!attachName.equals("")) {

						messageHelper.addAttachment(attachName,
								new InputStreamSource() {
									public InputStream getInputStream()
											throws IOException {
										return attachFile.getInputStream();
									}
								});
					}

				}
			});

//			return "configuration/sendmaildemo/successsendmail";
			ModelAndView mv = new ModelAndView("redirect:/externalaccessorystock");
			mv.addObject("sendMailStatus", "Success");
			return mv;
		}catch(Exception e){
			ModelAndView mv = new ModelAndView("redirect:/externalaccessorystock");
			mv.addObject("sendMailStatus", "Failed");
			return mv;
		}
	}
}
