package com.chori.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.chori.entity.User;
import com.chori.model.AccessoryPriceModel;
import com.chori.model.OrderExternalAccessoryModel;
import com.chori.service.AccessoryPriceService;
import com.chori.service.OrderExternalAccessoryService;
import com.chori.service.UserService;

@Controller
@RequestMapping(value = "/")
public class OrderExternalAccessoryController {
	private static final Log log = LogFactory
			.getLog(OrderExternalAccessoryController.class);

	@Autowired
	OrderExternalAccessoryService service;
	@Autowired
	AccessoryPriceService accessoryService;

	@Autowired
	UserService userSer;
	/***
	 * This method is used to save orderexternalaccessory
	 * 
	 * @param orderExternalAccessoryModel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orderexternalaccessory/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> saveOrderExternalAccessory(
			@RequestBody OrderExternalAccessoryModel orderExternalAccessoryModel) {
		log.info(String
				.format("saveOrderExternalAccessory with param 'orderExternalAccessoryModel' in class: %s",
						getClass()));
		try {
			log.debug("add 1 OrderExternalAccessory and return edit width as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String username = "admin";
			result.put("saveOrderExternalAccessory", service
					.addOrderExternalAccessory(orderExternalAccessoryModel,
							username));
			log.debug("saveOrderExternalAccessory successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("saveOrderExternalAccessory with param 'orderExternalAccessoryModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/orderexternalaccessory/updateOrderQtyOfAPiAssignExternalAccessory/{accessoryCode}/{lotNumber}/{orderQty}/{orderSheetNo}", method = RequestMethod.GET)
	public boolean updateOrderQtyOfAPiAssignExternalAccessory(@PathVariable String accessoryCode, @PathVariable String lotNumber, @PathVariable Integer orderQty, @PathVariable String orderSheetNo) {
		log.info(String
				.format("updateOrderQtyOfAPiAssignExternalAccessory with param 'response' in class: %s",
						getClass()));		
		try {
			log.debug("updateOrderQtyOfAPiAssignExternalAccessory request");
			return service.updateOrderQtyOfAPiAssignExternalAccessory(accessoryCode,lotNumber,orderQty,orderSheetNo);
		} catch (Exception e) {
			log.error(String
					.format("updateOrderQtyOfAPiAssignExternalAccessory in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}	
	}
	
	/**
	 * This function find a accessoryprice by
	 * accessoryCode,accessorySuplier,orderDate then return it as JSON format
	 * 
	 * @param accessoryCode
	 *            ,accessorySuplier,orderDate
	 * @return JSON format of a accessoryConsumption
	 */
	@RequestMapping(value = "/orderexternalaccessory/get/{accessoryCode}/{accessorySuplier}/{orderDate}", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getListAccessoryPriceByAccessoryCodeAndAccessorySuplierAndOrderDate(
			@PathVariable String accessoryCode,
			@PathVariable String accessorySuplier, @PathVariable Date orderDate) {
		log.info(String
				.format("getListAccessoryPriceByAccessoryCodeAndAccessorySuplierAndOrderDate in class %s",
						getClass()));
		try {
			log.debug("getting getListAccessoryPriceByAccessoryCodeAndAccessorySuplierAndOrderDate and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<AccessoryPriceModel> ls = accessoryService
					.getListAccessorypriceModelByAccessoryCodeandAccessorySuplierandOrderDate(
							accessoryCode, accessorySuplier, orderDate);
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getListSizeByGarmentAccessoryCustomer successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getListAccessoryPriceByAccessoryCodeAndAccessorySuplierAndOrderDate in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	@RequestMapping(value = "/orderexternalaccessory/{lotNumber}/{accessoryCode}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView listType(HttpServletResponse response,
			@PathVariable String lotNumber, @PathVariable String accessoryCode) {
		log.info(String
				.format("listorderexternalaccessory with param 'response' in class: %s",
						getClass()));
		try {
			log.debug("return listorderexternalaccessory view for request");
			response.setContentType("text/html");
			log.debug("liststype successful");
			return new ModelAndView(
					"operation/orderexternalaccessory/orderextacc");
		} catch (Exception e) {
			log.error(String
					.format("listorderexternalaccessory with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}

	/**
	 * This function check if a orderexternalaccessory with given OrderSheetNo
	 * is existed on database.
	 * 
	 * @param ordSheetNo
	 * @return JSON value
	 */
	@RequestMapping(value = "/orderexternalaccessory/isExist/{ordSheetNo}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> isTypeExist(@PathVariable String ordSheetNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "ok");
		result.put("isExisted", service.isOrderSheetNoExisted(ordSheetNo));
		return result;
	}

	/**
	 * Return view page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/orderexternalaccessory/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllOrderexternalAccessory() {
		log.info(String.format("getAllType in class %s", getClass()));
		try {
			log.debug("getting list of all type and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<OrderExternalAccessoryModel> ls = service
					.getAllOrderExternalAccessoryModels();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllOrderexternalAccessory successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllOrderexternalAccessory in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}

	}

	/**
	 * This method is used to get all orderexternalaccessory in database and
	 * return a list orderexternalaccessory in json
	 * 
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/listorderexternalaccessory", method = RequestMethod.GET)
	public ModelAndView listOrderexternalaccessory(HttpServletResponse response) {
		log.info(String
				.format("listorderexternalaccessory with param 'response' in class: %s",
						getClass()));
		try {
			log.debug("return listorderexternalaccessory view for request");
			response.setContentType("text/html");
			log.debug("listorderexternalaccessory successful");
			return new ModelAndView("operation/orderexternalaccessory/list");
		} catch (Exception e) {
			log.error(String
					.format("listorderexternalaccessory with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}

	// Method to show dialog stock
	@RequestMapping(value = "/stockorderexternalaccessory", method = RequestMethod.GET)
	public ModelAndView showDialogStock(HttpServletResponse response) {
		log.info(String
				.format("listorderexternalaccessory with param 'response' in class: %s",
						getClass()));
		try {
			log.debug("return showDialogStock view for request");
			response.setContentType("text/html");
			log.debug("showDialogStock successful");
			return new ModelAndView("operation/orderexternalaccessory/stock");
		} catch (Exception e) {
			log.error(String
					.format("showDialogStock with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}

	/***
	 * This method is used to edit detail of a orderexternalaccessory
	 * 
	 * @param orderExternalAccessoryModel
	 * @return edit status
	 */
	@ResponseBody
	@RequestMapping(value = "/orderexternalaccessory/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> updateOrderExternalAccessory(
			@RequestBody OrderExternalAccessoryModel orderExternalAccessoryModel) {
		log.info(String
				.format("updateOrderExternalAccessory with param 'orderExternalAccessoryModel' in class: %s",
						getClass()));
		try {
			log.debug("add 1 OrderExternalAccessory and return edit width as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			// String username = "admin";
			result.put("editStatus", service
					.editOrderExternalAccessory(orderExternalAccessoryModel));
			log.debug("updateOrderExternalAccessory successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("updateOrderExternalAccessory with param 'orderExternalAccessoryModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/***
	 * This method is used to get detail of a orderexternalaccessory
	 * 
	 * @param orderSheetNo
	 * @return detail of a orderexternalaccessory in JSON format
	 */
	@RequestMapping(value = "/orderexternalaccessory/detail/{orderSheetNo}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> OrderexternalAccessoryDetail(
			@PathVariable String orderSheetNo) {
		log.info(String
				.format("getOrderexternalAccessoryDetail with param 'typeCode' in class: %s",
						getClass()));
		try {
			log.debug("getting type's detail by its typeCode and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			OrderExternalAccessoryModel ue = service
					.findOrderExternalAccessoryModelById(orderSheetNo);
			result.put("current", ue);
			result.put("status", "ok");
			log.debug("getOrderexternalAccessoryDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getOrderexternalAccessoryDetail with param 'orderSheetNo' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	@ResponseBody
	@RequestMapping(value = "/orderexternalaccessory/getActualAssignQuantity/{accessoryCode}/{lotNumber}/{orderSheetNo}", method = RequestMethod.GET)
	public float GetActualAssignQuantity(@PathVariable String accessoryCode,@PathVariable String lotNumber,@PathVariable String orderSheetNo) {
		log.info(String
				.format("GetActualAssignQuantity with param 'response' in class: %s",
						getClass()));		
		try {
			log.debug("GetActualAssignQuantity request");
			return service.getActualAssignQuantity(accessoryCode,lotNumber,orderSheetNo);
		} catch (Exception e) {
			log.error(String
					.format("GetActualAssignQuantity in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}	
	} 
	
	
	@ResponseBody
	@RequestMapping(value = "/orderexternalaccessory/updateorderquantity/{orderSheetNo}/{orderQuantity}", method = RequestMethod.GET)
	public void UpdateorderQuantity(@PathVariable String orderSheetNo,@PathVariable float orderQuantity) {
		log.info(String
				.format("UpdateorderQuantity with param 'response' in class: %s",
						getClass()));		
		try {
			log.debug("UpdateorderQuantity request");
			service.UpdateorderQuantity(orderSheetNo, orderQuantity);
		} catch (Exception e) {
			log.error(String
					.format("UpdateorderQuantity in class %s has error: %s",
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
	@RequestMapping(value = "/sendmailForOrderExternalAccessory", method = RequestMethod.POST)
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
			ModelAndView mv = new ModelAndView("redirect:/listorderexternalaccessory");
			mv.addObject("sendMailStatus", "Success");
			return mv;
		}catch(Exception e){
			ModelAndView mv = new ModelAndView("redirect:/listorderexternalaccessory");
			mv.addObject("sendMailStatus", "Failed");
			return mv;
		}
	}
}
