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
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chori.model.FactoryModel;
import com.chori.model.FileBucket;
import com.chori.model.SignatureAddModel;
import com.chori.model.SignatureModel;
import com.chori.service.AccessoryordersignatureService;
import com.chori.service.RoleService;

@Controller
@RequestMapping(value = "/")
public class AccOrSignatureController {
	@Autowired
	AccessoryordersignatureService ser;

	@Autowired
	RoleService roleSer;

	@Autowired
	MessageSource messageSource;
	//
	// private static String UPLOAD_LOCATION="C:/Chori/SignatureImage/";

	private static final Log log = LogFactory
			.getLog(AccOrSignatureController.class);

	/**
	 * Return view page
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/listSignature" }, method = RequestMethod.GET)
	public String listSignaturePage(ModelMap model) {
		log.info(String.format("listSignaturePage with in class: %s",
				getClass()));
		try {
			log.debug("return listSignaturePage view for request");
			// response.setContentType("text/html");
			// assign userId when access /listRole url
			// currentUser= getPrincipal();
			log.debug("listSignaturePage successful");
			SignatureAddModel signatureAddModel = new SignatureAddModel();
			model.addAttribute("signatureAddModel", signatureAddModel);

			LoginController.AssignCurrentUser();
			if (roleSer.detectFunc4User(LoginController.currentUser,
					"Accessory Order Signature")) {
				return "configuration/signature/listSignature";
			}
			// return "login/accessDenied";
			return "login/accessDenied";

		} catch (Exception e) {
			log.error(String.format(
					"listSignaturePage in class %s has error: %s", getClass(),
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
	@RequestMapping(value = "/signature/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllSignature() {
		log.info(String.format("getAllSignature in class %s", getClass()));
		try {
			log.debug("getting list of all Signature and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<SignatureModel> ls = ser.getAllSignature();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllSignature successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllSignature in class %s has error: %s", getClass(),
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
	@RequestMapping(value = "/signature/delete", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete1Signature(
			@RequestBody SignatureModel signatureModel) {
		log.info(String.format("delete1Signature in class %s", getClass()));
		try {
			log.debug("delete1Signature and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			// List<FactoryModel> ls = ser.getAllFactoryModel();
			result.put("status", "ok");
			result.put("deleteStatus", ser.deleteSignature(signatureModel.getAccordersigncode()));
			log.debug("delete1Signature successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"delete1Signature in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	// @RequestMapping(value="/signature/addNewSignature", method =
	// RequestMethod.GET)
	// public String addNewSignature(ModelMap model) {
	// SignatureAddModel signatureAddModel = new SignatureAddModel();
	// model.addAttribute("signatureAddModel", signatureAddModel);
	// //System.err.println();
	// return "configuration/signature/addSignature";
	// }

	@RequestMapping(value = "/signature/addNewSignature", method = RequestMethod.POST)
	public String addNewSignaturePost(
			@Valid SignatureAddModel signatureAddModel, BindingResult result,
			HttpServletRequest request) throws IOException {

		if (result.hasErrors()) {
			System.out.println("validation errors in multi upload");
			return "redirect:/listSignature";
		} else {
			System.out.println("Fetching files"
					+ signatureAddModel.getFiles().size());
			List<String> fileNames = new ArrayList<String>();
			String handleFileName = "";

			// Now do something with file...
			for (FileBucket bucket : signatureAddModel.getFiles()) {
				String name = bucket.getFile().getOriginalFilename();
				if (name != null && name.length() > 0) {
					// FileCopyUtils.copy(bucket.getFile().getBytes(), new
					// File(UPLOAD_LOCATION +
					// bucket.getFile().getOriginalFilename()));
					File dir = new File(request
							.getSession()
							.getServletContext()
							.getRealPath(
									messageSource.getMessage(
											"UPLOAD_FILE", null,
											null)));					
//					File dir = new File(
//									messageSource.getMessage(
//											"UPLOAD_FILE", null,
//											null));
					
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
					handleFileName = name.substring(0, name.lastIndexOf("."))
							+ signatureAddModel.getAccordersigncode()
							+ new Date().getTime()
							+ name.substring(name.lastIndexOf("."));
					// FileCopyUtils.copy(bucket.getFile().getBytes(), new
					// File(request.getSession().getServletContext().getRealPath("/resources/chori/AccessoryImage/")
					// + handleFileName));
					FileCopyUtils.copy(
							bucket.getFile().getBytes(),
							new File(request
									.getSession()
									.getServletContext()
									.getRealPath(
											messageSource.getMessage(
													"UPLOAD_FILE",
													null, null))
									+ handleFileName));
					fileNames.add(handleFileName);
				} else {
					fileNames.add(null);
				}
			}
			signatureAddModel.setImgurl(fileNames.get(0));

			System.err.println(signatureAddModel);
			ser.addNewSignature(signatureAddModel, "admin");

			return "redirect:/listSignature";
		}

	}

	@RequestMapping(value = "/signature/detail", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get1SignatureModelDetail(
			@RequestBody SignatureModel signatureModel) {
		log.info(String.format("get1SignatureModelDetail in class %s",
				getClass()));
		try {
			log.debug("get1SignatureModelDetail and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("signature",
					ser.findSignatureModelById(signatureModel.getAccordersigncode()));
			log.debug("get1SignatureModelDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"get1SignatureModelDetail in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	@RequestMapping(value = "/signature/edit", method = RequestMethod.POST)
	public String editSignaturePost(@Valid SignatureAddModel signatureAddModel,
			BindingResult result, HttpServletRequest request)
			throws IOException {

		if (result.hasErrors()) {
			System.out.println("validation errors in multi upload");
			return "redirect:/listAccessory";
		} else {
			System.out.println("Fetching files"
					+ signatureAddModel.getFiles().size());
			List<String> fileNames = new ArrayList<String>(2);

			String handleFileName = "";

			// Now do something with file...
			for (FileBucket bucket : signatureAddModel.getFiles()) {
				String name = bucket.getFile().getOriginalFilename();
				if (name != null && name.length() > 0) {
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
					handleFileName = name.substring(0, name.lastIndexOf("."))
							+ signatureAddModel.getAccordersigncode()
							+ new Date().getTime()
							+ name.substring(name.lastIndexOf("."));
					FileCopyUtils.copy(
							bucket.getFile().getBytes(),
							new File(request
									.getSession()
									.getServletContext()
									.getRealPath(
											messageSource.getMessage(
													"UPLOAD_FILE",
													null, null))
									+ handleFileName));
					fileNames.add(handleFileName);
				} else {
					fileNames.add(null);
				}
			}
			if (fileNames.get(0) != null)
				signatureAddModel.setImgurl(fileNames.get(0));

			System.err.println(signatureAddModel);

			ser.editSignature(signatureAddModel, "admin");

			return "redirect:/listSignature";
		}
	}

	@RequestMapping(value = "/signature/isExist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isSignatureExist(
			@RequestBody SignatureModel signatureModel) {
		log.info(String.format(
				"isSignatureExist with param 'accessorycode' in class: %s",
				getClass()));
		try {
			log.debug("check if an signature with accordersigncode is existed in DB and return as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("isExisted",
					ser.isSignatureExistedById(signatureModel.getAccordersigncode()));
			log.debug("check isSignatureExist successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isSignatureExist with param 'accordersigncode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
