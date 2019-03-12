package com.chori.controller;

import java.io.File;
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

import com.chori.model.AccessoryGroupModel;
import com.chori.model.AccessoryModel;
import com.chori.model.FileBucket;
import com.chori.model.GarmentstyleModel;
import com.chori.model.GarmentstyleaccessorydetailModel;
import com.chori.model.GarmentstylereferpriceModel;
import com.chori.model.SizeModel;
import com.chori.service.GarmentstyleService;
import com.chori.service.GarmentstyleaccessorydetailService;
import com.chori.service.GarmentstylereferpriceService;
import com.chori.service.RoleService;

@Controller
@RequestMapping(value = "/")
public class GarmentstyleController {
	private static final Log log = LogFactory
			.getLog(GarmentstyleController.class);

	@Autowired
	GarmentstyleService service;

	@Autowired
	GarmentstyleaccessorydetailService garmentstyleaccessorydetailService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	RoleService roleSer;
	
	@Autowired
	GarmentstylereferpriceService garmentstylereferpriceSer;

	// Validator for garmentStyle
	@Autowired
	@Qualifier("garmentstyleValidator")
	private Validator garmentstyleValidator;

	@InitBinder("garmentstyleValidator")
	private void initBinder_GarmentstyleValidator(WebDataBinder binder) {
		binder.setValidator(garmentstyleValidator);
	}

	// validator for garmentStyleAccessoryDetail
	@Autowired
	@Qualifier("garmentstyleaccessorydetailValidator")
	private Validator garmentstyleaccessorydetailValidator;

	@InitBinder("garmentstyleaccessorydetailValidator")
	private void initBinder_GarmentstyleaccessorydetailValidator(
			WebDataBinder binder) {
		binder.setValidator(garmentstyleaccessorydetailValidator);
	}

	/**
	 * This function is used to getallGarment style in DB and return as json
	 * 
	 * @return
	 */
	@RequestMapping(value = "/garmentstyle/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllGarmentstyle() {
		log.info(String.format("getAllGarmentstyle in class %s", getClass()));
		try {
			log.debug("getting list of all width and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<GarmentstyleModel> ls = service.getAllGarmentstyleModel();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("get all Garment style successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllGarmentstyle in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to return view page
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listGarmentstyle", method = RequestMethod.GET)
	public String listGarmentstyle(ModelMap model) {
		log.info(String.format(
				"listGarmentstyle with param 'model' in class: %s", getClass()));
		try {
			LoginController.AssignCurrentUser();

			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Garment Style")) {
				log.debug("listGarmentstyle successful");
				GarmentstyleModel garmentstyleModel1 = new GarmentstyleModel();
				GarmentstyleModel garmentstyleModel2 = new GarmentstyleModel();
				model.addAttribute("garmentstyleModel1", garmentstyleModel1);
				model.addAttribute("garmentstyleModel2", garmentstyleModel2);
				return "configuration/garmentstyle/listGarmentstyle";
			}
			return "login/accessDenied";
		} catch (Exception e) {
			log.error(String
					.format("listGarmentstyle with param 'model' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}

	/**
	 * This function find a garmentstyle by garmentstyleCode then return it as
	 * JSON format
	 *
	 * @param statuscode
	 * @return JSON format of a status
	 */
//	@RequestMapping(value = "/garmentstyle/detail/{garmentstyleCode}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getGarmentstyleDetail(
//			@PathVariable String garmentstyleCode) {
//		log.info(String
//				.format("getGarmentstyleDetail with param 'garmentstyleCode' in class: %s",
//						getClass()));
//		try {
//			log.debug("getting garment style's detail by its garmentstyleCode and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			GarmentstyleModel gsm = service
//					.findGarmentstyleModelById(garmentstyleCode);
//			result.put("currentgarmentstyle", gsm);
//			result.put("status", "ok");
//			log.debug("getGarmentstyleDetail successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String
//					.format("getGarmentstyleDetail with param 'garmentstyleCode' in class %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	
	@RequestMapping(value = "/garmentstyle/detail", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getGarmentstyleDetail(
			@RequestBody GarmentstyleModel garmentstyleModel) {
		log.info(String
				.format("getGarmentstyleDetail with param 'garmentstyleCode' in class: %s",
						getClass()));
		try {
			log.debug("getting garment style's detail by its garmentstyleCode and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			GarmentstyleModel gsm = service
					.findGarmentstyleModelById(garmentstyleModel.getGarmentstylecode());
			result.put("currentgarmentstyle", gsm);
			result.put("listGarmentstylereferprice", garmentstylereferpriceSer.getListGarmentstylereferpriceModelByGarmentstyleCode(garmentstyleModel.getGarmentstylecode()));
			result.put("status", "ok");
			log.debug("getGarmentstyleDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getGarmentstyleDetail with param 'garmentstyleCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new garment style into db
	 * 
	 * @param garmentstyleModel
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/garmentstyle/edit", method = RequestMethod.POST)
	public ModelAndView editGarmentstyle(@Valid GarmentstyleModel garmentstyleModel,
			BindingResult result, HttpServletRequest request) throws Exception {
		log.info(String.format(
				"editGarmentstyle with param 'garmentstyleModel' in class: %s",
				getClass()));

		garmentstyleValidator.validate(garmentstyleModel, result);

		try {
			if (result.hasErrors()) {
				System.out.println("validation errors in multi upload");
//				return "redirect:/listGarmentstyle";
				ModelAndView mv = new ModelAndView("redirect:/listGarmentstyle");
				mv.addObject("editResultStatus", "Failed");
				return mv;
			} else {
				System.out.println("Fetching files"
						+ garmentstyleModel.getFiles().size());
				List<String> fileNames = new ArrayList<String>(5);

				String handleFileName = "";

				// Now do something with file...
				for (FileBucket bucket : garmentstyleModel.getFiles()) {
					String name = bucket.getFile().getOriginalFilename();
					if (name != null && name.length() > 0) {

						if (messageSource.getMessage(
								"UPLOAD_FILE", null, null)
								.contains(":")) {
							File dir = new File(messageSource.getMessage(
									"UPLOAD_FILE", null, null));
							if (!dir.exists()) {
								dir.mkdirs();
							}
							System.err.println(messageSource.getMessage(
									"UPLOAD_FILE", null, null));
							// the name of the image will be save originalName+
							// new Date().getTime()+ getGarmentstylecode+
							// suffix.
							handleFileName = name.substring(0,
									name.lastIndexOf("."))
									+ garmentstyleModel.getGarmentstylecode()
									+ new Date().getTime()
									+ name.substring(name.lastIndexOf("."));
							FileCopyUtils.copy(
									bucket.getFile().getBytes(),
									new File(messageSource.getMessage(
											"UPLOAD_FILE", null,
											null)+"/"
											+ handleFileName));
							fileNames.add(handleFileName);
						} else {
							File dir = new File(
									request.getSession()
											.getServletContext()
											.getRealPath(
													messageSource
															.getMessage(
																	"UPLOAD_FILE",
																	null, null)));
							if (!dir.exists()) {
								dir.mkdirs();
							}
							System.err
									.println(request
											.getSession()
											.getServletContext()
											.getRealPath(
													messageSource
															.getMessage(
																	"UPLOAD_FILE",
																	null, null)));
							// the name of the image will be save originalName+
							// new Date().getTime()+ getGarmentstylecode+
							// suffix.
							handleFileName = name.substring(0,
									name.lastIndexOf("."))
									+ garmentstyleModel.getGarmentstylecode()
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
					garmentstyleModel.setImgurl1(fileNames.get(0));
				if (fileNames.get(1) != null)
					garmentstyleModel.setImgurl2(fileNames.get(1));
				if (fileNames.get(2) != null)
					garmentstyleModel.setImgurl3(fileNames.get(2));
				if (fileNames.get(3) != null)
					garmentstyleModel.setImgurl4(fileNames.get(3));
				if (fileNames.get(4) != null)
					garmentstyleModel.setImgurl5(fileNames.get(4));

				System.err.println(garmentstyleModel);

//				service.editGarmentstyle(garmentstyleModel, "admin");
				
				if(isDozenEditVer){
					garmentstyleModel.setReferprice(null);
					service.editGarmentstyle(garmentstyleModel, "admin");
					//gọi hàm save list 
					garmentstylereferpriceSer.editGarmentstylereferpriceForGarment(garmentstyleModel, lstGarmentstylereferpriceModelEditVer);
					lstGarmentstylereferpriceModelEditVer= null;
				}else if(!isDozenEditVer){
					service.editGarmentstyle(garmentstyleModel, "admin");
				}

//				return "redirect:/listGarmentstyle";
				ModelAndView mv = new ModelAndView("redirect:/listGarmentstyle");
				mv.addObject("editResultStatus", "Success");
				return mv;
			}
		} catch (Exception e) {
			log.error(String
					.format("editGarmentstyle with param 'garmentstyleModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to check if a garment style is existed
	 * 
	 * @param garmentstyleCode
	 * @return
	 */
	@RequestMapping(value = "/garmentstyle/isExist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isGarmentstyleExist(
			@RequestBody GarmentstyleModel garmentstyleModel) {
		log.info(String
				.format("isGarmentstyleExist with param 'garmentstyleCode' in class: %s",
						getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("isExisted",
					service.isGarmentStyleExistedById(garmentstyleModel.getGarmentstylecode()+"@@@"+garmentstyleModel.getCustomercode()));
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isGarmentstyleExist with param 'garmentstyleCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new garment style into db
	 * 
	 * @param garmentstyleModel
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/garmentstyle/add", method = RequestMethod.POST)
	public ModelAndView addGarmentstyle(@Valid GarmentstyleModel garmentstyleModel,
			BindingResult result, HttpServletRequest request) throws Exception {
		log.info(String.format(
				"addGarmentstyle with param 'garmentstyleModel' in class: %s",
				getClass()));

		garmentstyleValidator.validate(garmentstyleModel, result);

		try {
			if (result.hasErrors()) {
				System.out.println("validation errors in multi upload");
//				return "redirect:/listGarmentstyle";
				ModelAndView mv = new ModelAndView("redirect:/listGarmentstyle");
				mv.addObject("addResultStatus", "Failed");
				return mv;
			} else {
				System.out.println("Fetching files"
						+ garmentstyleModel.getFiles().size());
				List<String> fileNames = new ArrayList<String>(5);

				String handleFileName = "";

				// Now do something with file...
				for (FileBucket bucket : garmentstyleModel.getFiles()) {
					String name = bucket.getFile().getOriginalFilename();
					if (name != null && name.length() > 0) {

						if (messageSource.getMessage(
								"UPLOAD_FILE", null, null)
								.contains(":")) {
							File dir = new File(messageSource.getMessage(
									"UPLOAD_FILE", null, null));
							if (!dir.exists()) {
								dir.mkdirs();
							}
							System.err.println(messageSource.getMessage(
									"UPLOAD_FILE", null, null));
							// the name of the image will be save originalName+
							// new Date().getTime()+ getGarmentstylecode+
							// suffix.
							handleFileName = name.substring(0,
									name.lastIndexOf("."))
									+ garmentstyleModel.getGarmentstylecode()
									+ new Date().getTime()
									+ name.substring(name.lastIndexOf("."));
							FileCopyUtils.copy(
									bucket.getFile().getBytes(),
									new File(messageSource.getMessage(
											"UPLOAD_FILE", null,
											null)+"/"
											+ handleFileName));
							fileNames.add(handleFileName);
						} else {
							File dir = new File(
									request.getSession()
											.getServletContext()
											.getRealPath(
													messageSource
															.getMessage(
																	"UPLOAD_FILE",
																	null, null)));
							if (!dir.exists()) {
								dir.mkdirs();
							}
							System.err
									.println(request
											.getSession()
											.getServletContext()
											.getRealPath(
													messageSource
															.getMessage(
																	"UPLOAD_FILE",
																	null, null)));
							// the name of the image will be save originalName+
							// new Date().getTime()+ getGarmentstylecode+
							// suffix.
							handleFileName = name.substring(0,
									name.lastIndexOf("."))
									+ garmentstyleModel.getGarmentstylecode()
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

				garmentstyleModel.setImgurl1(fileNames.get(0));
				garmentstyleModel.setImgurl2(fileNames.get(1));
				garmentstyleModel.setImgurl3(fileNames.get(2));
				garmentstyleModel.setImgurl4(fileNames.get(3));
				garmentstyleModel.setImgurl5(fileNames.get(4));

				System.err.println(garmentstyleModel);

				if(isDozen){
					garmentstyleModel.setReferprice(null);
					service.addNewGarmentstyle(garmentstyleModel, "admin");
					//gọi hàm save list 
					garmentstylereferpriceSer.addGarmentstylereferpriceForGarment(garmentstyleModel, lstGarmentstylereferpriceModel);
					lstGarmentstylereferpriceModel= null;
				}else if(!isDozen){
					
					service.addNewGarmentstyle(garmentstyleModel, "admin");
				}
				
				
//				return "redirect:/listGarmentstyle";
				ModelAndView mv = new ModelAndView("redirect:/listGarmentstyle");
				mv.addObject("addResultStatus", "Success");
				return mv;
			}
		} catch (Exception e) {
			log.error(String
					.format("addGarmentstyle with param 'garmentstyleModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete a garment style in db and return result
	 * as json
	 * 
	 * @param garmentstyleCode
	 * @return
	 */
	@RequestMapping(value = "/garmentstyle/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> deleteGarmentstyle(
			@RequestBody GarmentstyleModel garmentstyleModel) {
		log.info(String
				.format("delete Garmentstyle with param 'garmentstyleCode' in class: %s",
						getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("deleteGarmentstyle",
					garmentstylereferpriceSer.deleteGarmentstylereferpriceByGarment(garmentstyleModel)&&service.deleteGarmentstyle(garmentstyleModel.getGarmentstylecode()));
			log.debug("delete garment style successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("delete garment style with param 'garmentstyleCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to copy a garment
	 * 
	 * @param oldGarmentstyleCode
	 * @param newGarmentstyleModel
	 * @return
	 */
//	@RequestMapping(value = "/garmentstyle/copy/{oldGarmentstyleCode}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> copyGarmentstyle(
//			@PathVariable String oldGarmentstyleCode,
//			@RequestBody GarmentstyleModel newGarmentstyleModel) {
//		log.info(String
//				.format("copyGarmentstyle with param 'garmentstyleCode', 'garmentstyleModel' in class: %s",
//						getClass()));
//		try {
//			log.debug("copyGarmentstyle with param 'garmentstyleCode', 'garmentstyleModel' and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			result.put("copyStatus", service.copyGarmentstyle(
//					newGarmentstyleModel, oldGarmentstyleCode, "admin"));
//			result.put("status", "ok");
//			log.debug("copyGarmentstyle successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String
//					.format("copyGarmentstyle with param 'garmentstyleCode', 'garmentstyleModel' in class %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	@RequestMapping(value = "/garmentstyle/copy", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> copyGarmentstyle(
			@RequestBody GarmentstyleModel newGarmentstyleModel) {
		log.info(String
				.format("copyGarmentstyle with param 'garmentstyleCode', 'garmentstyleModel' in class: %s",
						getClass()));
		try {
			log.debug("copyGarmentstyle with param 'garmentstyleCode', 'garmentstyleModel' and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("copyStatus", service.copyGarmentstyle(
					newGarmentstyleModel, newGarmentstyleModel.getOldGarmentstyleCode(), "admin"));
			result.put("status", "ok");
			log.debug("copyGarmentstyle successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("copyGarmentstyle with param 'garmentstyleCode', 'garmentstyleModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	// /**
	// * This function is used to check if a garment is existed
	// * @param garmentStyleCode
	// * @return
	// */
	// @RequestMapping(value = "/garmentstyle/isExist/{garmentStyleCode}",
	// produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	// @ResponseBody
	// public Map<String, Object> isGarmentStyleExist(@PathVariable String
	// garmentStyleCode) {
	// log.info(String.format("isGarmentStyleExist with param 'factoryCode' in class: %s",
	// getClass()));
	// try{
	// log.debug("check if a garment style with garmentStyleCode is existed in DB and return as json format");
	// Map<String, Object> result = new HashMap<String, Object>();
	// result.put("status", "ok");
	// result.put("isExisted",
	// service.isGarmentStyleExistedById(garmentStyleCode));
	// log.debug("check isGarmentStyleExist successful");
	// return result;
	// }catch(Exception e){
	// log.error(String.format("isGarmentStyleExist with param 'garmentStyleCode' in class %s has error: %s",
	// getClass(), e.getMessage()));
	// throw e;
	// }
	// }

	/**
	 * Accessory List Part
	 */

	/**
	 * This function is used to get accessory list for a garment
	 * 
	 * @return
	 */
//	@RequestMapping(value = "/garmentstyle/listAccessoryForGarment/{garmentStyleName}", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getListAccessoryForGarmentstyle(
//			@PathVariable String garmentStyleName) {
//		log.info(String.format("getListAccessoryForGarmentstyle in class %s",
//				getClass()));
//		try {
//			log.debug("getting getListAccessoryForGarmentstyle and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			List<AccessoryModel> ls = garmentstyleaccessorydetailService
//					.getListAccessoryForGarmentStyle(garmentStyleName);
//			result.put("status", "ok");
//			result.put("listAccessoryForGarmentstyle", ls);
//			log.debug("getListAccessoryForGarmentstyle successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String
//					.format("getListAccessoryForGarmentstyle in class %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	
	@RequestMapping(value = "/garmentstyle/listAccessoryForGarment", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListAccessoryForGarmentstyle(
			@RequestBody GarmentstyleModel newGarmentstyleModel) {
		log.info(String.format("getListAccessoryForGarmentstyle in class %s",
				getClass()));
		try {
			log.debug("getting getListAccessoryForGarmentstyle and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<AccessoryModel> ls = garmentstyleaccessorydetailService
					.getListAccessoryForGarmentStyle(newGarmentstyleModel.getGarmentstylecode());
			result.put("status", "ok");
			result.put("listAccessoryForGarmentstyle", ls);
			log.debug("getListAccessoryForGarmentstyle successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getListAccessoryForGarmentstyle in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get list size by Garment Accessory
	 * Customer
	 * 
	 * @param garmentStyleCode
	 * @param accessoryCode
	 * @param customerCode
	 * @return
	 */
//	@RequestMapping(value = "/garmentstyle/listSizeByGarmentAccessoryCustomer/{garmentStyleCode}/{accessoryCode}/{customerCode}/{garmentKindCode}", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getListSizeByGarmentAccessoryCustomer(
//			@PathVariable String garmentStyleCode,
//			@PathVariable String accessoryCode,
//			@PathVariable String customerCode,
//			@PathVariable String garmentKindCode) {
//		log.info(String
//				.format("getListSizeByGarmentAccessoryCustomer in class %s",
//						getClass()));
//		try {
//			log.debug("getting getListSizeByGarmentAccessoryCustomer and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			List<SizeModel> ls = garmentstyleaccessorydetailService
//					.getListSizeByGarmentAccessoryCustomer(garmentStyleCode,
//							accessoryCode, customerCode, garmentKindCode);
//			result.put("status", "ok");
//			result.put("lstSizeByGarmentAccessoryCustomer", ls);
//			log.debug("getListSizeByGarmentAccessoryCustomer successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String
//					.format("getListSizeByGarmentAccessoryCustomer in class %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	
	@RequestMapping(value = "/garmentstyle/listSizeByGarmentAccessoryCustomer", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListSizeByGarmentAccessoryCustomer(
			@RequestBody GarmentstyleModel garmentstyleModel) {
		log.info(String
				.format("getListSizeByGarmentAccessoryCustomer in class %s",
						getClass()));
		try {
			log.debug("getting getListSizeByGarmentAccessoryCustomer and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<SizeModel> ls = garmentstyleaccessorydetailService
					.getListSizeByGarmentAccessoryCustomer(garmentstyleModel.getGarmentstylecode(),
							garmentstyleModel.getAccessoryCode(), garmentstyleModel.getCustomercode(), garmentstyleModel.getGarmentkindcode());
			result.put("status", "ok");
			result.put("lstSizeByGarmentAccessoryCustomer", ls);
			log.debug("getListSizeByGarmentAccessoryCustomer successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getListSizeByGarmentAccessoryCustomer in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get list Garmentstyleaccessorydetail By
	 * GarmentStyleName And AccessoryName
	 * 
	 * @param garmentStyleCode
	 * @param accessoryCode
	 * @return
	 */
//	@RequestMapping(value = "/garmentstyle/listGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName/{garmentStyleCode}/{accessoryCode}", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName(
//			@PathVariable String garmentStyleCode,
//			@PathVariable String accessoryCode) {
//		log.info(String
//				.format("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName in class %s",
//						getClass()));
//		try {
//			log.debug("getting getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			List<GarmentstyleaccessorydetailModel> ls = garmentstyleaccessorydetailService
//					.getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName(
//							garmentStyleCode, accessoryCode);
//			result.put("status", "ok");
//			result.put(
//					"lstGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName",
//					ls);
//			log.debug("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String
//					.format("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName in class %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	
	@RequestMapping(value = "/garmentstyle/listGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName(
			@RequestBody GarmentstyleModel garmentstyleModel) {
		log.info(String
				.format("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName in class %s",
						getClass()));
		try {
			log.debug("getting getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<GarmentstyleaccessorydetailModel> ls = garmentstyleaccessorydetailService
					.getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName(
							garmentstyleModel.getGarmentstylecode(), garmentstyleModel.getAccessoryCode());
			result.put("status", "ok");
			result.put(
					"lstGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName",
					ls);
			log.debug("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get List Garmentstyleaccessorydetail By
	 * GarmentStyleName
	 * 
	 * @param garmentStyleCode
	 * @return
	 */
	@RequestMapping(value = "/garmentstyle/listGarmentstyleaccessorydetailByGarmentStyleName", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListGarmentstyleaccessorydetailByGarmentStyleName(
			@RequestBody GarmentstyleModel garmentstyleModel) {
		log.info(String
				.format("getListGarmentstyleaccessorydetailByGarmentStyleName in class %s",
						getClass()));
		try {
			log.debug("getting getListGarmentstyleaccessorydetailByGarmentStyleName and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<GarmentstyleaccessorydetailModel> ls = garmentstyleaccessorydetailService
					.getListGarmentstyleaccessorydetailByGarmentStyleName(garmentstyleModel.getGarmentstylecode());
			result.put("status", "ok");
			result.put("lstGarmentstyleaccessorydetailByGarmentStyleName", ls);
			log.debug("getListGarmentstyleaccessorydetailByGarmentStyleName successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getListGarmentstyleaccessorydetailByGarmentStyleName in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new garmentstyleaccessorydetail
	 * 
	 * @param GSADmodel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/garmentstyleaccessorydetail/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addNewGarmentstyleaccessorydetail(
			@Valid @RequestBody GarmentstyleaccessorydetailModel GSADmodel,
			BindingResult bindingResult) {
		log.info(String
				.format("addNewGarmentstyleaccessorydetail with params 'GSADmodel' in class: %s",
						getClass()));

		garmentstyleaccessorydetailValidator.validate(GSADmodel, bindingResult);

		try {
			log.debug("addNewGarmentstyleaccessorydetail and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");

			if (bindingResult.hasErrors()) {
				System.err
						.println("validation input garmentstyleaccessorydetail invalid");
				result.put("addStatus",
						"invalid garmentstyleaccessorydetail input");
				return result;
			}

			result.put("addStatus", garmentstyleaccessorydetailService
					.addNewGarmentstyleaccessorydetail(GSADmodel, "admin"));
			log.debug("addNewGarmentstyleaccessorydetail successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addNewGarmentstyleaccessorydetail with params 'fm' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * Ä�ANG FIX This function check if all size is assigned for
	 * garmentstyleaccessorydetail, to enable, disable add button
	 * 
	 * @param garmentCode
	 * @param accessoryCode
	 * @param customerCode
	 * @return
	 */
//	@RequestMapping(value = "/garmentstyleaccessorydetail/isAllSizeAlreadyAssigned/{garmentCode}/{accessoryCode}/{customerCode}/{garmentKindCode}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> isAllSizeAlreadyAssignedForAdd(
//			@PathVariable String garmentCode,
//			@PathVariable String accessoryCode,
//			@PathVariable String customerCode,
//			@PathVariable String garmentKindCode) {
//		log.info(String
//				.format("isGarmentstyleExist with param 'garmentstyleCode' in class: %s",
//						getClass()));
//		try {
//			Map<String, Object> result = new HashMap<String, Object>();
//			result.put("status", "ok");
//			result.put("isAllSizeAlreadyAssigned",
//					garmentstyleaccessorydetailService
//							.isAllSizeAlreadyAssigned(garmentCode,
//									accessoryCode, customerCode,
//									garmentKindCode));
//			return result;
//		} catch (Exception e) {
//			log.error(String
//					.format("isGarmentstyleExist with param 'garmentstyleCode' in class %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	
	@RequestMapping(value = "/garmentstyleaccessorydetail/isAllSizeAlreadyAssigned", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isAllSizeAlreadyAssignedForAdd(
			@RequestBody GarmentstyleModel garmentstyleModel) {
		log.info(String
				.format("isGarmentstyleExist with param 'garmentstyleCode' in class: %s",
						getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("isAllSizeAlreadyAssigned",
					garmentstyleaccessorydetailService
							.isAllSizeAlreadyAssigned(garmentstyleModel.getGarmentstylecode(),
									garmentstyleModel.getAccessoryCode(), garmentstyleModel.getCustomercode(),
									garmentstyleModel.getGarmentkindcode()));
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isGarmentstyleExist with param 'garmentstyleCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit a garment style accessory detail
	 * 
	 * @param GSADmodel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/garmentstyleaccessorydetail/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editGarmentstyleaccessorydetail(
			@Valid @RequestBody GarmentstyleaccessorydetailModel GSADmodel,
			BindingResult bindingResult) {
		log.info(String
				.format("editGarmentstyleaccessorydetail with params 'GSADmodel' in class: %s",
						getClass()));

		// garmentstyleaccessorydetailValidator.validate(GSADmodel,
		// bindingResult);

		try {
			log.debug("editGarmentstyleaccessorydetail and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");

			if (bindingResult.hasErrors()) {
				System.err
						.println("validation input garmentstyleaccessorydetail invalid");
				result.put("editStatus",
						"invalid garmentstyleaccessorydetail input");
				return result;
			}

			result.put("editStatus", garmentstyleaccessorydetailService
					.editGarmentstyleAccessoryDetail(GSADmodel, "admin"));
			log.debug("editGarmentstyleaccessorydetail successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("editGarmentstyleaccessorydetail with params 'fm' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete a garmentstyleaccessorydetail
	 * 
	 * @param id
	 * @return
	 */
//	@RequestMapping(value = "/garmentstyleaccessorydetail/delete/{id}", produces = "application/json", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> deleteGarmentstyleaccessorydetail(
//			@PathVariable Integer id) {
//		log.info(String.format("deleteGarmentstyleaccessorydetail in class %s",
//				getClass()));
//		try {
//			log.debug("deleteGarmentstyleaccessorydetail and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			// List<FactoryModel> ls = ser.getAllFactoryModel();
//			result.put("status", "ok");
//			result.put("deleteStatus",
//					garmentstyleaccessorydetailService.deleteGSAD(id));
//			log.debug("deleteGarmentstyleaccessorydetail successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String
//					.format("deleteGarmentstyleaccessorydetail in class %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	
	@RequestMapping(value = "/garmentstyleaccessorydetail/delete", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteGarmentstyleaccessorydetail(
			@RequestBody GarmentstyleaccessorydetailModel garmentstyleaccessorydetailModel) {
		log.info(String.format("deleteGarmentstyleaccessorydetail in class %s",
				getClass()));
		try {
			log.debug("deleteGarmentstyleaccessorydetail and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			// List<FactoryModel> ls = ser.getAllFactoryModel();
			result.put("status", "ok");
			result.put("deleteStatus",
					garmentstyleaccessorydetailService.deleteGSAD(garmentstyleaccessorydetailModel.getGarmentstyleaccessorydetailcode()));
			log.debug("deleteGarmentstyleaccessorydetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("deleteGarmentstyleaccessorydetail in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get list accessory group by garment style code
	 * to load into dropdown list HÃ m Ä‘c dÃ¹ng Ä‘á»ƒ láº¥y list accessoryGroup
	 * sau Ä‘Ã³ load vÃ o dropdown list bÃªn view.
	 * 
	 * @param garmentStyleCode
	 * @return
	 */
	@RequestMapping(value = "/garmentstyle/getListAccessoryGroupByGarmentStyleName", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListAccessoryGroupByGarmentStyleName(
			@RequestBody GarmentstyleModel garmentstyleModel) {
		log.info(String.format(
				"getListAccessoryGroupByGarmentStyleName in class %s",
				getClass()));
		try {
			log.debug("getting getListAccessoryGroupByGarmentStyleName and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<AccessoryGroupModel> ls = garmentstyleaccessorydetailService
					.getAccessoryGroupByGarmentStyleCode(garmentstyleModel.getGarmentstylecode());
			result.put("status", "ok");
			result.put("lstAccessoryGroupByGarmentStyleName", ls);
			log.debug("getListAccessoryGroupByGarmentStyleName successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getListAccessoryGroupByGarmentStyleName in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get list Garmentstyleaccessorydetail By
	 * GarmentStyleName And AccessoryGroupName
	 * 
	 * @param garmentStyleCode
	 * @param accessoryGroupCode
	 * @return
	 */
//	@RequestMapping(value = "/garmentstyle/listGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName/{garmentStyleCode}/{accessoryGroupCode}", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName(
//			@PathVariable String garmentStyleCode,
//			@PathVariable String accessoryGroupCode) {
//		log.info(String
//				.format("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName in class %s",
//						getClass()));
//		try {
//			log.debug("getting getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			// if user want to get all
//			if (accessoryGroupCode.equals("All")) {
//				List<GarmentstyleaccessorydetailModel> ls = garmentstyleaccessorydetailService
//						.getListGarmentstyleaccessorydetailByGarmentStyleName(garmentStyleCode);
//				result.put("status", "ok");
//				result.put(
//						"lstGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName",
//						ls);
//				log.debug("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName successful (case 'All')");
//				return result;
//			}
//			List<GarmentstyleaccessorydetailModel> ls = garmentstyleaccessorydetailService
//					.getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName(
//							garmentStyleCode, accessoryGroupCode);
//			result.put("status", "ok");
//			result.put(
//					"lstGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName",
//					ls);
//			log.debug("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String
//					.format("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName in class %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	
	@RequestMapping(value = "/garmentstyle/listGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName(
			@RequestBody GarmentstyleModel garmentstyleModel) {
		log.info(String
				.format("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName in class %s",
						getClass()));
		try {
			log.debug("getting getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			// if user want to get all
			if (garmentstyleModel.getAccessoryGroupCode().equals("All")) {
				List<GarmentstyleaccessorydetailModel> ls = garmentstyleaccessorydetailService
						.getListGarmentstyleaccessorydetailByGarmentStyleName(garmentstyleModel.getGarmentstylecode());
				result.put("status", "ok");
				result.put(
						"lstGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName",
						ls);
				log.debug("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName successful (case 'All')");
				return result;
			}
			List<GarmentstyleaccessorydetailModel> ls = garmentstyleaccessorydetailService
					.getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName(
							garmentstyleModel.getGarmentstylecode(), garmentstyleModel.getAccessoryGroupCode());
			result.put("status", "ok");
			result.put(
					"lstGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName",
					ls);
			log.debug("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if garment already has garmentStyleAccessoryDetail to
	 * enable or disable customer and factory
	 * 
	 * @param garmentstyleCode
	 * @return
	 */
	@RequestMapping(value = "/garmentstyle/isHasGarmentStyleAccessoryDetail", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isHasGarmentStyleAccessoryDetail(
			@RequestBody GarmentstyleModel garmentstyleModel) {
		log.info(String
				.format("isHasGarmentStyleAccessoryDetail with param 'garmentstyleCode' in class: %s",
						getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("isHasGarmentStyleAccessoryDetail",
					service.isHasGarmentStyleAccessoryDetail(garmentstyleModel.getGarmentstylecode()));
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isHasGarmentStyleAccessoryDetail with param 'garmentstyleCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to delete an image of garment style
	 * @param garmentstyleModel
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/garmentstyle/deleteImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> deleteImageGarmentStyle(@RequestBody GarmentstyleModel garmentstyleModel, HttpServletRequest request) {
		log.info(String.format("deleteImageGarmentStyle in class: %s",
				getClass()));

		try {
			log.debug("deleteImageGarmentStyle and return status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			
			if (messageSource.getMessage("UPLOAD_FILE",
					null, null).contains(":")) {
				File file = new File(messageSource.getMessage(
						"UPLOAD_FILE", null, null)+ File.separator
						+ garmentstyleModel.getImgUrlDelete());
				System.err.println(messageSource.getMessage(
						"UPLOAD_FILE", null, null)+ File.separator
						+ garmentstyleModel.getImgUrlDelete());
				result.put("deleteImageStatus", file.delete()&&service.deleteImageGarmentStyle(garmentstyleModel, LoginController.currentUser));
			}
			else{
				File file = new File(request
						.getSession()
						.getServletContext()
						.getRealPath(messageSource.getMessage("UPLOAD_FILE", null, null))+ garmentstyleModel.getImgUrlDelete());
				System.err.println(request
						.getSession()
						.getServletContext()
						.getRealPath(messageSource.getMessage("UPLOAD_FILE", null, null))+ garmentstyleModel.getImgUrlDelete());
				result.put("deleteImageStatus", file.delete()&&service.deleteImageGarmentStyle(garmentstyleModel, LoginController.currentUser));
			}
			
			
			log.debug("deleteImageGarmentStyle successfully");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"deleteImageGarmentStyle in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * Save price for dozen part
	 */
	//List để save list giá by dozen
	private List<GarmentstylereferpriceModel> lstGarmentstylereferpriceModel;
	private Boolean isDozen;
	
	@RequestMapping(value = "/garmentstyle/getLstGarmentstylereferpriceWhenAdd/{pcsOrDozen}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getLstGarmentstylereferpriceWhenAdd(@PathVariable String pcsOrDozen,
			@RequestBody ArrayList<GarmentstylereferpriceModel> lstFromView) {
		log.info(String
				.format("getGarmentstyleDetail with param 'garmentstyleCode' in class: %s",
						getClass()));
		try {
			log.debug("getting garment style's detail by its garmentstyleCode and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			//Khởi tạo lstGarmentstylereferpriceModel
			lstGarmentstylereferpriceModel = new ArrayList<GarmentstylereferpriceModel>();
			
			//nếu ng dùng chọn textbox dozen thì mới add các giá trị cho list Garmentstylereferprice
			if(pcsOrDozen.equals("dozen")){
				for (GarmentstylereferpriceModel garmentstylereferpriceModel : lstFromView) {
					lstGarmentstylereferpriceModel.add(garmentstylereferpriceModel);
				}
				isDozen = true;
			}else if(pcsOrDozen.equals("pcs")){
				isDozen = false;
			}
			
			
			result.put("saveStatus", "ok");
			
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getGarmentstyleDetail with param 'garmentstyleCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * Save price for dozen part (Edit part)
	 */
	private List<GarmentstylereferpriceModel> lstGarmentstylereferpriceModelEditVer;
	private Boolean isDozenEditVer;
	
	@RequestMapping(value = "/garmentstyle/getLstGarmentstylereferpriceWhenEdit/{pcsOrDozen}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getLstGarmentstylereferpriceWhenEdit(@PathVariable String pcsOrDozen,
			@RequestBody ArrayList<GarmentstylereferpriceModel> lstFromView) {
		log.info(String
				.format("getGarmentstyleDetail with param 'garmentstyleCode' in class: %s",
						getClass()));
		try {
			log.debug("getting garment style's detail by its garmentstyleCode and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			//Khởi tạo lstGarmentstylereferpriceModel
			lstGarmentstylereferpriceModelEditVer = new ArrayList<GarmentstylereferpriceModel>();
			
			//nếu ng dùng chọn textbox dozen thì mới add các giá trị cho list Garmentstylereferprice
			if(pcsOrDozen.equals("dozen")){
				for (GarmentstylereferpriceModel garmentstylereferpriceModel : lstFromView) {
					System.err.println(garmentstylereferpriceModel);
					lstGarmentstylereferpriceModelEditVer.add(garmentstylereferpriceModel);
				}
				isDozenEditVer = true;
			}else if(pcsOrDozen.equals("pcs")){
				isDozenEditVer = false;
			}
			
			
			result.put("saveStatus", "ok");
			
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getGarmentstyleDetail with param 'garmentstyleCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
