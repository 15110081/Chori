package com.chori.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.chori.entity.Logofchange;
import com.chori.entity.User;
import com.chori.model.LogofchangeModel;
import com.chori.model.PiFabricListModel;
import com.chori.model.PiModel;
import com.chori.model.LogofchangeModel;
import com.chori.service.LogofchangeService;
import com.chori.service.PiService;
import com.chori.service.PigriddetailService;
import com.chori.service.RoleService;
import com.chori.service.UserService;

@Controller
@RequestMapping(value = "/")
public class PiController {
	private static final Log log = LogFactory.getLog(PiController.class);
	@Autowired
	PiService service;

	@Autowired
	LogofchangeService logofchangeService;

	@Autowired
	RoleService roleSer;

	@Autowired
	private PigriddetailService pigriddetailService;

	@Autowired
	UserService userSer;

	@Autowired
	MessageSource messageSource;

	// @Autowired
	// @Qualifier("piValidator")
	// private Validator piValidator;
	//
	// @InitBinder("piValidator")
	// private void initBinder_PiValidator(WebDataBinder binder) {
	// binder.setValidator(piValidator);
	// }

	/**
	 * This function is used to get list of all PI in db
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pi/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllStatus() {
		log.info(String.format("getAllStatus in class %s", getClass()));
		try {
			log.debug("getting list of all pi and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<PiModel> ls = service.getAllPiModel();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllPi successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getAllPi in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to return view page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listpi", method = RequestMethod.GET)
	public ModelAndView listAccessoryPage(ModelMap model, HttpServletResponse response) {
		log.info(String.format("listPi with param 'response' in class: %s", getClass()));
		try {
			log.debug("return listPiPage view for request");
			response.setContentType("text/html");
			// assign userId when access /listRole url
			// currentUser= getPrincipal();
			log.debug("listPiPage successful");
			// just add
			PiModel piModel = new PiModel();
			model.addAttribute("piModel", piModel);
			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser, "PI Information")) {
				return new ModelAndView("operation/pi/list");
			}
			return new ModelAndView("login/accessDenied");
		} catch (Exception e) {
			log.error(String.format("listPi with param 'response' in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}

	}

	/**
	 * This function is used to get a PI detail by lot No
	 *
	 * @param lotNumber
	 * @return
	 */
	@RequestMapping(value = "/pi/detail", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPiDetail(@RequestBody PiModel piModel) {
		log.info(String.format("getPiDetail with param 'lotNumber' in class: %s", getClass()));
		try {
			log.debug("getting pi's detail by its lotNumber and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			PiModel en = service.findPiById(piModel.getLotnumber());
			result.put("currentpi", en);
			// result.put("piGridDetail",
			// pigriddetailService.getListPiGridDetailModelByLotNo(lotNumber));
			result.put("status", "ok");
			log.debug("getPiDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getPiDetail with param 'lotNumber' in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get pi grid detail by lot number
	 * 
	 * @param lotNumber
	 * @return
	 */
	@RequestMapping(value = "/pi/piGridDetail/{lotNumber}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getPiGridDetail(@PathVariable String lotNumber) {
		log.info(String.format("getPiGridDetail with param 'lotNumber' in class: %s", getClass()));
		try {
			log.debug("getting pi's grid detail by its lotNumber and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("piGridDetail", pigriddetailService.getListPiGridDetailModelByLotNo(lotNumber));
			log.debug("getPiGridDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getPiGridDetail with param 'lotNumber' in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function edit a pi and update into database
	 * 
	 * @param status
	 * @return status, editStatus as JSON
	 */
	// @ResponseBody
	// @RequestMapping(value = "/pi/edit", method = RequestMethod.POST, produces
	// = MediaType.APPLICATION_JSON_VALUE, consumes =
	// MediaType.APPLICATION_JSON_VALUE)
	// @ResponseStatus(HttpStatus.OK)
	// public Map<String, Object> editPi(@RequestBody PiModel piMod) {
	// log.info(String.format("editPi with param 'order' in class: %s",
	// getClass()));
	// try {
	// log.debug("edit 1 pi and return edit pi as json format");
	// Map<String, Object> result = new HashMap<String, Object>();
	// String userName = "admin";
	// result.put("status", "ok");
	// result.put("editPi", service.editPi(piMod, userName));
	// log.debug("editPi successfully");
	// return result;
	// } catch (Exception e) {
	// log.error(String.format("editPi with param 'order' in class %s has error:
	// %s",
	// getClass(), e.getMessage()));
	// throw e;
	// }
	// }

	@RequestMapping(value = "/pi/edit", method = RequestMethod.POST)
	public ModelAndView editPi(@Valid PiModel piModel, BindingResult result, HttpServletRequest request)
			throws IOException {

		try {
//			if (result.hasErrors()) {
//				System.out.println("validation errors in multi upload");
//				// return "redirect:/listGarmentstyle";
//				ModelAndView mv = new ModelAndView("redirect:/listpi");
//				mv.addObject("editResultStatus", "Failed");
//				return mv;
//			} else {
				System.out.println("Fetching files" + piModel.getPiattached().getFile().getSize());
//				System.out.println("Fetching files" + piModel.getPackingguide().getFile().getSize());
//				System.out.println("Fetching files" + piModel.getSewingguide().getFile().getSize());
				String handleFileName = "";

				// Now do something with file...
//				String packingguide = piModel.getPackingguide().getFile().getOriginalFilename();
				String piattached = piModel.getPiattached().getFile().getOriginalFilename();
//				String sewingguide = piModel.getSewingguide().getFile().getOriginalFilename();

//				if (packingguide != null && packingguide.length() > 0) {
//
//					File dir = new File(request.getSession().getServletContext()
//							.getRealPath(messageSource.getMessage("packingGuideLocation", null, null)));
//					if (!dir.exists()) {
//						dir.mkdirs();
//					}
//					System.err.println(request.getSession().getServletContext()
//							.getRealPath(messageSource.getMessage("packingGuideLocation", null, null)));
//					// the name of the file will be save originalName+ new
//					// Date().getTime()+ accessoryCode+ suffix.
//					handleFileName = packingguide.substring(0, packingguide.lastIndexOf(".")) + piModel.getLotnumber()
//							+ new Date().getTime() + packingguide.substring(packingguide.lastIndexOf("."));
//					FileCopyUtils
//							.copy(piModel.getPackingguide().getFile().getBytes(),
//									new File(request.getSession().getServletContext()
//											.getRealPath(messageSource.getMessage("packingGuideLocation", null, null))
//											+ handleFileName));
//					piModel.setPackingguidename(handleFileName);
//				} else {
//					piModel.setPackingguidename(null);
//				}
				if (piattached != null && piattached.length() > 0) {

					File dir = new File(request.getSession().getServletContext()
							.getRealPath(messageSource.getMessage("piattachFileLocation", null, null)));
					if (!dir.exists()) {
						dir.mkdirs();
					}
					System.err.println(request.getSession().getServletContext()
							.getRealPath(messageSource.getMessage("piattachFileLocation", null, null)));
					// the name of the file will be save originalName+ new
					// Date().getTime()+ accessoryCode+ suffix.
					handleFileName = piattached.substring(0, piattached.lastIndexOf(".")) + piModel.getLotnumber()
							+ new Date().getTime() + piattached.substring(piattached.lastIndexOf("."));
					FileCopyUtils
							.copy(piModel.getPiattached().getFile().getBytes(),
									new File(request.getSession().getServletContext()
											.getRealPath(messageSource.getMessage("piattachFileLocation", null, null))
											+ handleFileName));
					piModel.setPiattachedfilename(handleFileName);
				} else {
					piModel.setPiattachedfilename(null);
				}
//				if (sewingguide != null && sewingguide.length() > 0) {
//					File dir = new File(request.getSession().getServletContext()
//							.getRealPath(messageSource.getMessage("manufactureGuideLocation", null, null)));
//					if (!dir.exists()) {
//						dir.mkdirs();
//					}
//					System.err.println(request.getSession().getServletContext()
//							.getRealPath(messageSource.getMessage("manufactureGuideLocation", null, null)));
//					// the name of the file will be save originalName+ new
//					// Date().getTime()+ accessoryCode+ suffix.
//					handleFileName = sewingguide.substring(0, sewingguide.lastIndexOf(".")) + piModel.getLotnumber()
//							+ new Date().getTime() + sewingguide.substring(sewingguide.lastIndexOf("."));
//					FileCopyUtils.copy(piModel.getSewingguide().getFile().getBytes(),
//							new File(request.getSession().getServletContext()
//									.getRealPath(messageSource.getMessage("manufactureGuideLocation", null, null))
//									+ handleFileName));
//					piModel.setSewingguidename(handleFileName);
//				} else {
//					piModel.setSewingguidename(null);
//				}

				System.err.println(piModel);
				if(piModel.getPiestshipdate()!=null)
					piModel.getPiestshipdate().setHours(7);
				if(piModel.getPireceiveddate()!=null)
					piModel.getPireceiveddate().setHours(7);
				if(piModel.getMfgfinisheddate()!=null)
					piModel.getMfgfinisheddate().setHours(7);
				if(piModel.getMfgstarteddate()!=null)
					piModel.getMfgstarteddate().setHours(7);
				service.editPi(piModel, LoginController.currentUser);
				ModelAndView mv = new ModelAndView("redirect:/listpi");
				mv.addObject("editResultStatus", "Success");
				return mv;
//			}
		} catch (Exception e) {
			log.error(
					String.format("editPi with param 'piModel' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to check if a lot no user input is existed in
	 * database.
	 * 
	 * @param lotNumber
	 * @return
	 */
	@RequestMapping(value = "/pi/isExist/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isPiExist(@RequestBody PiModel piModel) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "ok");
		result.put("isExisted", service.isPiExistedById(piModel.getLotnumber()));
		return result;
	}

	@RequestMapping(value = "/pi/add", method = RequestMethod.POST)
	public ModelAndView addNewFile(@Valid PiModel piModel, BindingResult result, HttpServletRequest request)
			throws IOException {
		log.info(String.format("addPi with param 'piModel' in class: %s", getClass()));
		try {
//			if (result.hasErrors()) {
//				System.out.println("validation errors in multi upload");
//				System.err.println(piModel);
//				// return "redirect:/listGarmentstyle";
//				ModelAndView mv = new ModelAndView("redirect:/listpi");
//				mv.addObject("addResultStatus", "Failed");
//				return mv;
//			} else {

				System.out.println("Fetching files" + piModel.getPiattached().getFile().getSize());

				String handleFileName = "";
				String piattached = piModel.getPiattached().getFile().getOriginalFilename();

				if (piattached != null && piattached.length() > 0) {

					File dir = new File(request.getSession().getServletContext()
							.getRealPath(messageSource.getMessage("piattachFileLocation", null, null)));
					if (!dir.exists()) {
						dir.mkdirs();
					}
					System.err.println(request.getSession().getServletContext()
							.getRealPath(messageSource.getMessage("piattachFileLocation", null, null)));
					// the name of the file will be save originalName+ new
					// Date().getTime()+ accessoryCode+ suffix.
					handleFileName = piattached.substring(0, piattached.lastIndexOf(".")) + piModel.getLotnumber()
							+ new Date().getTime() + piattached.substring(piattached.lastIndexOf("."));
					FileCopyUtils
							.copy(piModel.getPiattached().getFile().getBytes(),
									new File(request.getSession().getServletContext()
											.getRealPath(messageSource.getMessage("piattachFileLocation", null, null))
											+ handleFileName));
					piModel.setPiattachedfilename(handleFileName);
				} else {
					piModel.setPiattachedfilename(null);
				}

				System.err.println(piModel+"bobo");
//				System.out.println(piModel.getPiestshipdate());
//				if(piModel.getPiestshipdate()!=null)
//					piModel.getPiestshipdate().setHours(7);
//				if(piModel.getPireceiveddate()!=null)
//					piModel.getPireceiveddate().setHours(7);
//				if(piModel.getMfgfinisheddate()!=null)
//					piModel.getMfgfinisheddate().setHours(7);
//				if(piModel.getMfgstarteddate()!=null)
//					piModel.getMfgstarteddate().setHours(7);
				service.addPi(piModel, LoginController.currentUser);
				ModelAndView mv = new ModelAndView("redirect:/listpi");
				mv.addObject("addResultStatus", "Success");
				return mv;
//			}
		} catch (Exception e) {
			
			log.error(
					String.format("addPi with param 'piModel' in class %s has error: %s", getClass(), e.getMessage()));
			ModelAndView mv = new ModelAndView("redirect:/listpi");
			mv.addObject("addResultStatus", "Failed");
			throw e;
		}
	}

	/**
	 * This function is used to delete 1 Pi
	 * 
	 * @param Pi
	 * @return
	 */
	@RequestMapping(value = "/pi/delete", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deletePi(@RequestBody PiModel piModel) {
		log.info(String.format("deletePI in class %s", getClass()));
		try {
			log.debug("deletePI and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("deleteStatus", service.deletePi(piModel));
			log.debug("deletePI successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("deletePI in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get list of all PI in db
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pi/fabric/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getFabricListForAPi() {
		log.info(String.format("getFabricListForAPi in class %s", getClass()));
		try {
			log.debug("getting list of all pi and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<PiFabricListModel> ls = service.getFabricList();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getFabricListForAPi successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getFabricListForAPi in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * Send email part
	 */
	private JavaMailSender mailSender;

	/**
	 * HÃ m validate email
	 * 
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
	 * HÃ m khá»Ÿi táº¡o, láº¥y thÃ´ng tin cá»§a ng gá»Ÿi email
	 * 
	 * @return
	 */
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		// get log in user
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
	 * This function is used to handle send mail request HÃ m Ä‘c dÃ¹ng Ä‘á»ƒ
	 * xá»­ lÃ½ send mail
	 * 
	 * @param request
	 * @param attachFile
	 * @return
	 */
	@RequestMapping(value = "/sendmailForLogOfChange", method = RequestMethod.POST)
	public ModelAndView sendEmail(HttpServletRequest request, final @RequestParam CommonsMultipartFile attachFile1,
			final @RequestParam CommonsMultipartFile attachFile2, final @RequestParam CommonsMultipartFile attachFile3,
			final @RequestParam CommonsMultipartFile attachFile4, final @RequestParam CommonsMultipartFile attachFile5,
			final @RequestParam CommonsMultipartFile attachFile6, final @RequestParam CommonsMultipartFile attachFile7,
			final @RequestParam CommonsMultipartFile attachFile8, final @RequestParam CommonsMultipartFile attachFile9,
			final @RequestParam CommonsMultipartFile attachFile10) {

		try {
			// reads form input
			final String emailTo = request.getParameter("mailTo");
			final String subject = request.getParameter("subject");
			final String message = request.getParameter("message");
			final String CC = request.getParameter("CC");
			final String lotNum = request.getParameter("txtLotNumberSendMail");
			
			// for logging
			System.out.println("emailTo: " + emailTo);
			System.out.println("subject: " + subject);
			System.out.println("message: " + message);
			System.out.println("CC: " + CC);
			System.out.println("attachFile1: " + attachFile1.getOriginalFilename());
			System.out.println("attachFile2: " + attachFile2.getOriginalFilename());
			System.out.println("attachFile3: " + attachFile3.getOriginalFilename());
			System.out.println("attachFile4: " + attachFile4.getOriginalFilename());
			System.out.println("attachFile5: " + attachFile5.getOriginalFilename());
			System.out.println("attachFile6: " + attachFile6.getOriginalFilename());
			System.out.println("attachFile7: " + attachFile7.getOriginalFilename());
			System.out.println("attachFile8: " + attachFile8.getOriginalFilename());
			System.out.println("attachFile9: " + attachFile9.getOriginalFilename());
			System.out.println("attachFile10: " + attachFile10.getOriginalFilename());

			mailSender = getMailSender();

			mailSender.send(new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					messageHelper.setTo(emailTo);
					messageHelper.setSubject(subject);
					messageHelper.setText(message);

					// cc part
					List<String> lstCC = new ArrayList<String>();
					List<User> lstUser = userSer.getAll();
					// láº·p qua list táº¥t cáº£ user, náº¿u user nÃ o Ä‘c check
					// lÃ  is CC thÃ¬ add vÃ o listCC
					for (User user : lstUser) {
						if (user.getCcmailstring()) {
							lstCC.add(user.getEmail());
						}
					}

					// bá»� Ä‘i email cá»§a current user
					lstCC.remove(userSer.findById(LoginController.currentUser).getEmail());

					// láº¥y cc tá»« view
					String[] listCCfromView = CC.split(";");
					for (String string : listCCfromView) {
						// System.err.println(string.trim());
						// System.out.println(OrderInternalAccessoryController.isValidEmailAddress(string.trim()));
						// náº¿u lÃ  valid email thÃ¬ cho vÃ´ list
						if (PiController.isValidEmailAddress(string.trim()))
							lstCC.add(string.trim());
					}
					String lstSaveCC = "";
					if (lstCC.size() > 0) {
						String[] arrayCC = new String[lstCC.size()];
						for (int i = 0; i < lstCC.size(); ++i) {
							arrayCC[i] = lstCC.get(i);
							lstSaveCC += arrayCC[i] +",";
						}
						messageHelper.setCc(arrayCC);
					}
					// messageHelper.setCc(arg0);

					// determines if there is an upload file, attach it to the
					// e-mail
					// File 1
					String attachName1 = attachFile1.getOriginalFilename();
					if (!attachName1.equals("")) {

						messageHelper.addAttachment(attachName1, new InputStreamSource() {
							public InputStream getInputStream() throws IOException {
								return attachFile1.getInputStream();
							}
						});
					}
					// File 2
					String attachName2 = attachFile2.getOriginalFilename();
					if (!attachName2.equals("")) {

						messageHelper.addAttachment(attachName2, new InputStreamSource() {
							public InputStream getInputStream() throws IOException {
								return attachFile2.getInputStream();
							}
						});
					}
					// File 3
					String attachName3 = attachFile3.getOriginalFilename();
					if (!attachName3.equals("")) {

						messageHelper.addAttachment(attachName3, new InputStreamSource() {
							public InputStream getInputStream() throws IOException {
								return attachFile3.getInputStream();
							}
						});
					}
					// File 4
					String attachName4 = attachFile4.getOriginalFilename();
					if (!attachName4.equals("")) {

						messageHelper.addAttachment(attachName4, new InputStreamSource() {
							public InputStream getInputStream() throws IOException {
								return attachFile4.getInputStream();
							}
						});
					}

					// File 5
					String attachName5 = attachFile5.getOriginalFilename();
					if (!attachName5.equals("")) {

						messageHelper.addAttachment(attachName5, new InputStreamSource() {
							public InputStream getInputStream() throws IOException {
								return attachFile5.getInputStream();
							}
						});
					}
					// File 6
					String attachName6 = attachFile6.getOriginalFilename();
					if (!attachName6.equals("")) {

						messageHelper.addAttachment(attachName6, new InputStreamSource() {
							public InputStream getInputStream() throws IOException {
								return attachFile6.getInputStream();
							}
						});
					}
					// File 7
					String attachName7 = attachFile7.getOriginalFilename();
					if (!attachName1.equals("")) {

						messageHelper.addAttachment(attachName7, new InputStreamSource() {
							public InputStream getInputStream() throws IOException {
								return attachFile7.getInputStream();
							}
						});
					}
					// File 8
					String attachName8 = attachFile8.getOriginalFilename();
					if (!attachName8.equals("")) {

						messageHelper.addAttachment(attachName8, new InputStreamSource() {
							public InputStream getInputStream() throws IOException {
								return attachFile8.getInputStream();
							}
						});
					}
					// File 9
					String attachName9 = attachFile9.getOriginalFilename();
					if (!attachName9.equals("")) {

						messageHelper.addAttachment(attachName9, new InputStreamSource() {
							public InputStream getInputStream() throws IOException {
								return attachFile9.getInputStream();
							}
						});
					}
					// File 10
					String attachName10 = attachFile10.getOriginalFilename();
					if (!attachName10.equals("")) {

						messageHelper.addAttachment(attachName10, new InputStreamSource() {
							public InputStream getInputStream() throws IOException {
								return attachFile10.getInputStream();
							}
						});
					}
					// End Files
					// }
				
					LogofchangeModel logofchangeModel = new LogofchangeModel();

					logofchangeModel.setSubject(subject);
					logofchangeModel.setToemail(emailTo);
					logofchangeModel.setLotnumber(lotNum);
					logofchangeModel.setFromemail(userSer.findById(LoginController.currentUser).getEmail());
					logofchangeModel.setCcmailstring(lstSaveCC);
					logofchangeModel.setAttachfileurl1(attachFile1.getOriginalFilename());
					logofchangeModel.setAttachfileurl2(attachFile2.getOriginalFilename());
					logofchangeModel.setAttachfileurl3(attachFile3.getOriginalFilename());
					logofchangeModel.setAttachfileurl4(attachFile4.getOriginalFilename());
					logofchangeModel.setAttachfileurl5(attachFile5.getOriginalFilename());
					logofchangeModel.setAttachfileurl6(attachFile6.getOriginalFilename());
					logofchangeModel.setAttachfileurl7(attachFile7.getOriginalFilename());
					logofchangeModel.setAttachfileurl8(attachFile8.getOriginalFilename());
					logofchangeModel.setAttachfileurl9(attachFile9.getOriginalFilename());
					logofchangeModel.setAttachfileurl10(attachFile10.getOriginalFilename());
					

					logofchangeService.addLogofchange(logofchangeModel, LoginController.currentUser);
				}
				
				
				
			});
			
			

			// return "configuration/sendmaildemo/successsendmail";
			ModelAndView mv = new ModelAndView("redirect:/listpi");
			mv.addObject("sendMailStatus", "Success");
			// logofchangeService.addLogofchange(logofchangeModel,
			// LoginController.currentUser);
			return mv;
		} catch (Exception e) {
			ModelAndView mv = new ModelAndView("redirect:/listpi");
			mv.addObject("sendMailStatus", "Failed");
			return mv;
		}

	}

	/**
	 * This function is used to get list pi grid detail (color group)
	 * 
	 * @param lotNumber
	 * @return
	 */
	@RequestMapping(value = "/pi/piGridDetailGroupedByColor", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPiGridDetailGroupedByColor(@RequestBody PiModel piModel) {
		log.info(String.format("getPiGridDetailGroupedByColor with param 'lotNumber' in class: %s", getClass()));
		try {
			log.debug("getting pi's grid detail by its lotNumber and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			//kiểm tra xem có assign fabric chưa? nếu chưa thì trả về list chưa có fa.
			boolean isAssignedFabric = pigriddetailService.isAssignFabricYet(piModel.getLotnumber());
			if(isAssignedFabric){
				//nếu assign r (có fa r)
				result.put("assignedFabric", isAssignedFabric);
				result.put("piGridDetail", pigriddetailService.getListPiGridDetail(piModel.getLotnumber()));
			}else{
				result.put("assignedFabric", isAssignedFabric);
				result.put("piGridDetail", pigriddetailService.getListPiGridDetailNotAssignFabric(piModel.getLotnumber()));
			}
			log.debug("getPiGridDetailGroupedByColor successful");
			return result;
		} catch (Exception e) {
			log.error(String.format("getPiGridDetailGroupedByColor with param 'lotNumber' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
}