package com.chori.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
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

import com.chori.model.AccessoryAddModel;
import com.chori.model.AccessoryModel;
import com.chori.model.FileBucket;
import com.chori.service.AccessoryService;
import com.chori.service.RoleService;

@Controller
@RequestMapping(value = "/")
public class AccessoryController {

	@Autowired
	AccessoryService ser;

	@Autowired
	RoleService roleSer;

	@Autowired
	MessageSource messageSource;

	/**
	 * Validator for accessory
	 */
	@Autowired
	@Qualifier("accessoryValidator")
	private Validator accessoryValidator;

	@InitBinder("accessoryValidator")
	private void initBinder_AccessoryValidator(WebDataBinder binder) {
		binder.setValidator(accessoryValidator);
	}

	// private static String UPLOAD_LOCATION="C:/Chori/AccessoryImage/";

	// @Autowired
	// ServletContext context;

	private static final Log log = LogFactory.getLog(AccessoryController.class);

	/**
	 * Return view page
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/listAccessory" }, method = RequestMethod.GET)
	public String listAccessoryPage(ModelMap model) {
		log.info(String.format("listAccessoryPage with in class: %s",
				getClass()));
		try {
			log.debug("return listAccessoryPage view for request");
			log.debug("listAccessoryPage successful");

			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Accessory Information")) {
				AccessoryAddModel accessoryAddModel = new AccessoryAddModel();
				AccessoryAddModel accessoryAddModel2 = new AccessoryAddModel();
				model.addAttribute("accessoryAddModel", accessoryAddModel);
				model.addAttribute("accessoryAddModel2", accessoryAddModel2);
				return "configuration/accessory/listAccessory";
			}
			return "login/accessDenied";

			// AccessoryAddModel accessoryAddModel = new AccessoryAddModel();
			// AccessoryAddModel accessoryAddModel2 = new AccessoryAddModel();
			// model.addAttribute("accessoryAddModel", accessoryAddModel);
			// model.addAttribute("accessoryAddModel2", accessoryAddModel2);
			// return "configuration/accessory/listAccessory";
		} catch (Exception e) {
			log.error(String.format(
					"listAccessoryPage in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method is used to get all accessory consumption in database and
	 * return a list accessory consumption in json
	 * 
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/accessory/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllAccessory() {
		log.info(String.format("getAllAccessory in class %s", getClass()));
		try {
			log.debug("getting list of all Accessory and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<AccessoryModel> ls = ser.getAllAccessory();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllAccessory successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllAccessory in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete 1 Accessory
	 * 
	 * @param Accessory
	 * @return
	 */
	@RequestMapping(value = "/accessory/delete", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete1Accessory(
			@RequestBody AccessoryModel accessoryModel) {
		log.info(String.format("delete1Accessory in class %s", getClass()));
		try {
			log.debug("delete1FabricSupplier and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			// List<FactoryModel> ls = ser.getAllFactoryModel();
			result.put("status", "ok");
			result.put("deleteStatus", ser.deleteAccessory(accessoryModel.getAccessorycode()));
			log.debug("delete1Accessory successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"delete1Accessory in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	// /**
	// * This function return view for add new Accessory
	// * @param model
	// * @return
	// */
	// @RequestMapping(value="/accessory/addNewAccessory", method =
	// RequestMethod.GET)
	// public String addNewAccessory(ModelMap model) {
	// AccessoryAddModel accessoryAddModel = new AccessoryAddModel();
	// model.addAttribute("accessoryAddModel", accessoryAddModel);
	// //System.err.println();
	// return "configuration/accessory/addAccessory";
	// }

	// /**
	// * This function handle request for add new Accessory
	// * @param accessoryAddModel
	// * @param result
	// * @return
	// * @throws IOException
	// */
	// @RequestMapping(value="/accessory/addNewAccessory", method =
	// RequestMethod.POST)
	// public String addNewAccessoryPost(@Valid AccessoryAddModel
	// accessoryAddModel, BindingResult result) throws IOException {
	//
	// //accessoryValidator.validate(accessoryAddModel, result);
	//
	// if (result.hasErrors()) {
	// System.out.println("validation errors in multi upload");
	// return "redirect:/listAccessory";
	// } else {
	// System.out.println("Fetching files"+accessoryAddModel.getFiles().size());
	// List<String> fileNames= new ArrayList<String>(2);
	//
	// //Now do something with file...
	// for(FileBucket bucket : accessoryAddModel.getFiles()){
	// String name = bucket.getFile().getOriginalFilename();
	// if (name != null && name.length() > 0) {
	// //FileCopyUtils.copy(bucket.getFile().getBytes(), new
	// File(UPLOAD_LOCATION + bucket.getFile().getOriginalFilename()));
	// FileCopyUtils.copy(bucket.getFile().getBytes(), new File(UPLOAD_LOCATION
	// + bucket.getFile().getOriginalFilename()));
	// fileNames.add(bucket.getFile().getOriginalFilename());
	// }else{
	// fileNames.add(null);
	// }
	// }
	//
	// accessoryAddModel.setImgurl1(fileNames.get(0));
	// accessoryAddModel.setImgurl2(fileNames.get(1));
	//
	// System.err.println(accessoryAddModel);
	//
	// ser.addNewAccessory(accessoryAddModel, "admin");
	//
	// return "redirect:/listAccessory";
	// }
	// }

	// /**
	// * This function handle request for add new Accessory
	// * @param accessoryAddModel
	// * @param result
	// * @return
	// * @throws IOException
	// */
	// @RequestMapping(value="/accessory/addNewAccessory", method =
	// RequestMethod.POST)
	// public String addNewAccessoryPost(@Valid AccessoryAddModel
	// accessoryAddModel, BindingResult result, HttpServletRequest request)
	// throws IOException {
	//
	// accessoryValidator.validate(accessoryAddModel, result);
	//
	// if (result.hasErrors()) {
	// System.out.println("validation errors in multi upload");
	// return "redirect:/listAccessory";
	// } else {
	// System.out.println("Fetching files"+accessoryAddModel.getFiles().size());
	// List<String> fileNames= new ArrayList<String>(2);
	//
	// //Now do something with file...
	// for(FileBucket bucket : accessoryAddModel.getFiles()){
	// String name = bucket.getFile().getOriginalFilename();
	// if (name != null && name.length() > 0) {
	// File dir = new
	// File(request.getSession().getServletContext().getRealPath("/resources/chori/AccessoryImage/"));
	// if (!dir.exists()) {
	// dir.mkdirs();
	// }
	// System.err.println(request.getSession().getServletContext().getRealPath("/resources/chori/AccessoryImage/"));
	// FileCopyUtils.copy(bucket.getFile().getBytes(), new
	// File(request.getSession().getServletContext().getRealPath("/resources/chori/AccessoryImage/")
	// + bucket.getFile().getOriginalFilename()));
	// //
	// System.out.println(request.getSession().getServletContext().getRealPath("/resources/chori/AccessoryImage/"));
	// fileNames.add(bucket.getFile().getOriginalFilename());
	// }else{
	// fileNames.add(null);
	// }
	// }
	//
	// accessoryAddModel.setImgurl1(fileNames.get(0));
	// accessoryAddModel.setImgurl2(fileNames.get(1));
	//
	// System.err.println(accessoryAddModel);
	//
	// ser.addNewAccessory(accessoryAddModel, "admin");
	//
	// return "redirect:/listAccessory";
	// }
	// }

	/**
	 * This function handle request for add new Accessory
	 * 
	 * @param accessoryAddModel
	 * @param result
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/accessory/addNewAccessory", method = RequestMethod.POST)
	public ModelAndView addNewAccessoryPost(
			@Valid AccessoryAddModel accessoryAddModel, BindingResult result,
			HttpServletRequest request) throws IOException {

		accessoryValidator.validate(accessoryAddModel, result);

		if (result.hasErrors()) {
			System.out.println("validation errors in multi upload");
//			return "redirect:/listAccessory";
			ModelAndView mv = new ModelAndView("redirect:/listAccessory");
			mv.addObject("addResultStatus", "Failed");
			return mv;
		} else {
			System.out.println("Fetching files"
					+ accessoryAddModel.getFiles().size());
			List<String> fileNames = new ArrayList<String>(4);

			String handleFileName = "";

			// Now do something with file...
			for (FileBucket bucket : accessoryAddModel.getFiles()) {
				String name = bucket.getFile().getOriginalFilename();
				if (name != null && name.length() > 0) {

					if (messageSource.getMessage("UPLOAD_FILE",
							null, null).contains(":")) {
						File dir = new File(messageSource.getMessage(
								"UPLOAD_FILE", null, null));
						if (!dir.exists()) {
							dir.mkdirs();
						}
						System.err.println(messageSource.getMessage(
								"UPLOAD_FILE", null, null));
						// the name of the image will be save originalName+ new
						// Date().getTime()+ accessoryCode+ suffix.
						handleFileName = name.substring(0,
								name.lastIndexOf("."))
								+ accessoryAddModel.getAccessorycode()
								+ new Date().getTime()
								+ name.substring(name.lastIndexOf("."));
						FileCopyUtils.copy(
								bucket.getFile().getBytes(),
								new File(messageSource.getMessage(
										"UPLOAD_FILE", null, null)+File.separator
										+ handleFileName));
						fileNames.add(handleFileName);
					} else {
						// File dir = new
						// File(request.getSession().getServletContext().getRealPath("/resources/chori/AccessoryImage/"));
						File dir = new File(request
								.getSession()
								.getServletContext()
								.getRealPath(
										messageSource.getMessage(
												"UPLOAD_FILE", null,
												null)));
						if (!dir.exists()) {
							dir.mkdirs();
						}
						System.err.println(request
								.getSession()
								.getServletContext()
								.getRealPath(
										messageSource.getMessage(
												"UPLOAD_FILE", null,
												null)));
						// the name of the image will be save originalName+ new
						// Date().getTime()+ accessoryCode+ suffix.
						handleFileName = name.substring(0,
								name.lastIndexOf("."))
								+ accessoryAddModel.getAccessorycode()
								+ new Date().getTime()
								+ name.substring(name.lastIndexOf("."));
						// FileCopyUtils.copy(bucket.getFile().getBytes(), new
						// File(request.getSession().getServletContext().getRealPath("/resources/chori/AccessoryImage/")
						// + handleFileName));
						FileCopyUtils
								.copy(bucket.getFile().getBytes(),
										new File(
												request.getSession()
														.getServletContext()
														.getRealPath(
																messageSource
																		.getMessage(
																				"UPLOAD_FILE",
																				null,
																				null))
														+ handleFileName));
						fileNames.add(handleFileName);
					}
				} else {
					fileNames.add(null);
				}
			}

			accessoryAddModel.setImgurl1(fileNames.get(0));
			accessoryAddModel.setImgurl2(fileNames.get(1));
			accessoryAddModel.setImgurl3(fileNames.get(2));
			accessoryAddModel.setImgurl4(fileNames.get(3));

			System.err.println(accessoryAddModel);

			ser.addNewAccessory(accessoryAddModel, "admin");

//			return "redirect:/listAccessory";
			ModelAndView mv = new ModelAndView("redirect:/listAccessory");
			mv.addObject("addResultStatus", "Success");
			return mv;
		}
	}

	/**
	 * This function is used to get detail of 1 accessory
	 * 
	 * @param accessorycode
	 * @return
	 */
	@RequestMapping(value = "/accessory/detail", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get1AccessoryModelDetail(
			@RequestBody AccessoryModel accessoryModel) {
		log.info(String.format("get1AccessoryModelDetail in class %s",
				getClass()));
		try {
			log.debug("get1AccessoryModelDetail and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("accessory", ser.findAccessoryModelById(accessoryModel.getAccessorycode()));
			log.debug("get1AccessoryModelDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"get1AccessoryModelDetail in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	// /**
	// * This function is used to update an accessory
	// * @param accessoryAddModel
	// * @param result
	// * @return
	// * @throws IOException
	// */
	// @RequestMapping(value="/accessory/edit", method = RequestMethod.POST)
	// public String editAccessoryPost(@Valid AccessoryAddModel
	// accessoryAddModel, BindingResult result) throws IOException {
	//
	// accessoryValidator.validate(accessoryAddModel, result);
	//
	// if (result.hasErrors()) {
	// System.out.println("validation errors in multi upload");
	// return "redirect:/listAccessory";
	// } else {
	// System.out.println("Fetching files"+accessoryAddModel.getFiles().size());
	// List<String> fileNames= new ArrayList<String>(2);
	//
	// //Now do something with file...
	// for(FileBucket bucket : accessoryAddModel.getFiles()){
	// String name = bucket.getFile().getOriginalFilename();
	// if (name != null && name.length() > 0) {
	// FileCopyUtils.copy(bucket.getFile().getBytes(), new File(UPLOAD_LOCATION
	// + bucket.getFile().getOriginalFilename()));
	// fileNames.add(bucket.getFile().getOriginalFilename());
	// }else{
	// fileNames.add(null);
	// }
	// }
	// if(fileNames.get(0)!=null)
	// accessoryAddModel.setImgurl1(fileNames.get(0));
	// if(fileNames.get(1)!=null)
	// accessoryAddModel.setImgurl2(fileNames.get(1));
	//
	// System.err.println(accessoryAddModel);
	//
	// ser.editAccessory(accessoryAddModel, "admin");
	//
	// return "redirect:/listAccessory";
	// }
	// }

	/**
	 * This function is used to update an accessory
	 * 
	 * @param accessoryAddModel
	 * @param result
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/accessory/edit", method = RequestMethod.POST)
	public ModelAndView editAccessoryPost(@Valid AccessoryAddModel accessoryAddModel,
			BindingResult result, HttpServletRequest request)
			throws IOException {

		accessoryValidator.validate(accessoryAddModel, result);

		if (result.hasErrors()) {
			System.out.println("validation errors in multi upload");
//			return "redirect:/listAccessory";
			ModelAndView mv = new ModelAndView("redirect:/listAccessory");
			mv.addObject("editResultStatus", "Failed");
			return mv;
		} else {
			System.out.println("Fetching files"
					+ accessoryAddModel.getFiles().size());
			List<String> fileNames = new ArrayList<String>(4);

			String handleFileName = "";

			// Now do something with file...
			for (FileBucket bucket : accessoryAddModel.getFiles()) {
				String name = bucket.getFile().getOriginalFilename();
				if (name != null && name.length() > 0) {

					if (messageSource.getMessage("UPLOAD_FILE",
							null, null).contains(":")) {
						File dir = new File(messageSource.getMessage(
								"UPLOAD_FILE", null, null));
						if (!dir.exists()) {
							dir.mkdirs();
						}
						// the name of the image will be save originalName+ new
						// Date().getTime()+ accessoryCode+ suffix.
						handleFileName = name.substring(0,
								name.lastIndexOf("."))
								+ accessoryAddModel.getAccessorycode()
								+ new Date().getTime()
								+ name.substring(name.lastIndexOf("."));
						FileCopyUtils.copy(
								bucket.getFile().getBytes(),
								new File(messageSource.getMessage(
										"UPLOAD_FILE", null, null)+ File.separator
										+ handleFileName));
						fileNames.add(handleFileName);
					} else {
						File dir = new File(request
								.getSession()
								.getServletContext()
								.getRealPath(
										messageSource.getMessage(
												"UPLOAD_FILE", null,
												null)));
						if (!dir.exists()) {
							dir.mkdirs();
						}
						// the name of the image will be save originalName+ new
						// Date().getTime()+ accessoryCode+ suffix.
						handleFileName = name.substring(0,
								name.lastIndexOf("."))
								+ accessoryAddModel.getAccessorycode()
								+ new Date().getTime()
								+ name.substring(name.lastIndexOf("."));
						FileCopyUtils
								.copy(bucket.getFile().getBytes(),
										new File(
												request.getSession()
														.getServletContext()
														.getRealPath(
																messageSource
																		.getMessage(
																				"UPLOAD_FILE",
																				null,
																				null))
														+ handleFileName));
						fileNames.add(handleFileName);
					}
				} else {
					fileNames.add(null);
				}
			}
			if (fileNames.get(0) != null)
				accessoryAddModel.setImgurl1(fileNames.get(0));
			if (fileNames.get(1) != null)
				accessoryAddModel.setImgurl2(fileNames.get(1));
			if (fileNames.get(2) != null)
				accessoryAddModel.setImgurl3(fileNames.get(2));
			if (fileNames.get(3) != null)
				accessoryAddModel.setImgurl4(fileNames.get(3));

			System.err.println(accessoryAddModel);

			ser.editAccessory(accessoryAddModel, "admin");

//			return "redirect:/listAccessory";
			ModelAndView mv = new ModelAndView("redirect:/listAccessory");
			mv.addObject("editResultStatus", "Success");
			return mv;
		}
	}

	/**
	 * This function check if an accessory is existed
	 * 
	 * @param accessorycode
	 * @return
	 */
	@RequestMapping(value = "/accessory/isExist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isAccessoryExist(
			@RequestBody AccessoryModel accessoryModel) {
		log.info(String.format(
				"isAccessoryExist with param 'accessorycode' in class: %s",
				getClass()));
		try {
			log.debug("check if an accessory with accessorycode is existed in DB and return as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("isExisted", ser.isAccessoryExistedById(accessoryModel.getAccessorycode()));
			log.debug("check isAccessoryExist successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isAccessoryExist with param 'accessorycode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to delete 1 image of accessory
	 * @param accessoryAddModel
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/accessory/deleteImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> deleteImageAccessory(@RequestBody AccessoryAddModel accessoryAddModel, HttpServletRequest request) {
		log.info(String.format("deleteImageAccessory in class: %s",
				getClass()));

		try {
			log.debug("deleteImageAccessory and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			
			if (messageSource.getMessage("UPLOAD_FILE",
					null, null).contains(":")) {
				File file = new File(messageSource.getMessage(
						"UPLOAD_FILE", null, null)+ File.separator
						+ accessoryAddModel.getImgUrlDelete());
				System.err.println(messageSource.getMessage(
						"UPLOAD_FILE", null, null)+ File.separator
						+ accessoryAddModel.getImgUrlDelete());
				result.put("deleteImageStatus", file.delete()&&ser.deleteImageAccessory(accessoryAddModel, LoginController.currentUser));
			}
			else{
				File file = new File(request
						.getSession()
						.getServletContext()
						.getRealPath(messageSource.getMessage("UPLOAD_FILE", null, null))+ accessoryAddModel.getImgUrlDelete());
				System.err.println(request
						.getSession()
						.getServletContext()
						.getRealPath(messageSource.getMessage("UPLOAD_FILE", null, null))+ accessoryAddModel.getImgUrlDelete());
				result.put("deleteImageStatus", file.delete()&&ser.deleteImageAccessory(accessoryAddModel, LoginController.currentUser));
			}
			
			log.debug("deleteImageAccessory successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"deleteImageAccessory in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
}
