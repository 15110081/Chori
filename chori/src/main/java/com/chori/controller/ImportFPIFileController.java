package com.chori.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chori.model.ExcelImportDemo;
import com.chori.model.ImportPIFile;
import com.chori.model.SizeModel;
import com.chori.service.ImportFpiFileService;
import com.chori.service.ImportPIFileService;

@Controller
@RequestMapping(value="/")
public class ImportFPIFileController {
	private static final Log log = LogFactory.getLog(BrandController.class);

	@Autowired
	ImportFpiFileService service;
    
	List<String> lstColor;
	List<String> lstGarmentStyle;
	List<SizeModel> lstSizeModel;
	List<String> lstSize;
	List<ArrayList<String>> lstValidate;
	List<ImportPIFile> lstImportPIFile;
	
//	@RequestMapping(value = "/importpifile", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getImportPiFile() throws Exception {
//		log.info(String.format("getImportPiFile in class %s", getClass()));
//		try {
//			log.debug("getting and return json");
//			
//		    String excelFilePath = "pi2.xlsx";
//		    ImportPIFileService reader = new ImportPIFileService();
//		    List<ImportPIFile> listPiGrid = reader.readPiGridFromExcelFile(excelFilePath);
//		    System.out.println(listPiGrid);
//			Map<String, Object> result = new HashMap<String, Object>();
//			result.put("status", "ok");
//			log.debug("getImportPiFile successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String.format("getAllBrand in class %s has error: %s", getClass(), e.getMessage()));
//			throw e;
//		}
//
//	}
	
	@RequestMapping(value = "/importfpifile", method = RequestMethod.GET)
	public String importExcelFile(ModelMap model) {
		ExcelImportDemo excelImportDemo = new ExcelImportDemo();
		model.addAttribute("excelImportDemo", excelImportDemo);
		return "operation/fpi/importFpi";
	}
	
	
	@RequestMapping(value = "/importfpifile", method = RequestMethod.POST)
	public String addNewAccessoryPost(@Valid ExcelImportDemo excelImportDemo,
			BindingResult result, HttpServletRequest request)
			throws IOException {

		// accessoryValidator.validate(accessoryAddModel, result);

		if (result.hasErrors()) {
			System.out.println("validation errors in multi upload");
			return "operation/pi/importPIError";
		} 
		else {
			System.out.println("Fetching files" + excelImportDemo.getFile());
			
			File dir = new File(request.getSession().getServletContext()
					.getRealPath("/resources/chori/AccessoryImage/"));
			if (!dir.exists()) {
				dir.mkdirs();
			}
			FileCopyUtils.copy(excelImportDemo.getFile().getBytes(),
					new File(request.getSession().getServletContext()
							.getRealPath("/resources/chori/AccessoryImage/")
							+ excelImportDemo.getFile().getOriginalFilename()));
			System.out.println(request.getSession().getServletContext()
					.getRealPath("/resources/chori/AccessoryImage/"));
			
			// phần excel
			try {
				@SuppressWarnings("unused")
				String fileName = excelImportDemo.getFile()
						.getOriginalFilename();

				FileInputStream file = new FileInputStream(new File(request
						.getSession().getServletContext()
						.getRealPath("/resources/chori/AccessoryImage/")
						+ excelImportDemo.getFile().getOriginalFilename()));
						
			    String excelFilePath = request
						.getSession().getServletContext()
						.getRealPath("/resources/chori/AccessoryImage/")
						+ excelImportDemo.getFile().getOriginalFilename();
			    //System.err.println(excelFilePath);
			    
			    //get current user
			    String userName = LoginController.currentUser;
//			    List<ImportPIFile> listPiGrid = reader.readPiGridFromExcelFile(excelFilePath);
			    
//			    //get error
			    boolean bool= false;
			    bool = service.readFpiGridFromExcelFile(excelFilePath, excelImportDemo.getLotnumber());
//				for(ImportPIFile x : lstImportPIFile)
//				{
//					System.out.println("s: " + x.getSize());
//					System.out.println("qtypcs: " +x.getQtypcs());
//					System.out.println("garment: " + x.getGarmentstyle());
//					System.out.println("clor: " +x.getColor());
//					
//				}			
//				
//				lstColor = new ArrayList<String>();
//				lstGarmentStyle = new ArrayList<String>();
//				lstSize = new ArrayList<String>();
//				lstSizeModel = new ArrayList<SizeModel>();
//				lstValidate = new ArrayList<ArrayList<String>>();
//				
//				lstColor = service.validateColorResult(lstImportPIFile);
//				lstGarmentStyle = service.validateGarmentStyleResult(lstImportPIFile);
//				lstSizeModel = service.validateSizeResult(lstImportPIFile, excelImportDemo.getLotnumber());
//				lstValidate = service.validateGeneralResult(lstImportPIFile, excelImportDemo.getLotnumber());
//
//			    //call save function to save data into DB	
//			    service.savePIGridInformationByImportFile(excelFilePath,excelImportDemo.getLotnumber(),userName,excelImportDemo.getOption());
			    		
			    //System.out.println(listPiGrid);
				file.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// end phần excel
			return "operation/pi/importPISuccess";
		}
	}
	
//	/**
//	 * This method is used to get all size in database
//	 * 
//	 * @return a list size in JSON format
//	 */
//	@RequestMapping(value = "/importpifile/errorlist", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getErrorListWhenImportPiFile() {
//		log.info(String.format("getErrorListWhenImportPiFile in class %s", getClass()));
//		try {
//			log.debug("getting Error List When Import Pi File and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			//List<ArrayList<String>> ls = service.validateGeneralResult(lstImportPiFile, lotNumber);
//			result.put("status", "ok");
//			result.put("list", lstValidate);
//			log.debug("getErrorListWhenImportPiFile successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String.format("getErrorListWhenImportPiFile in class %s has error: %s",
//					getClass(), e.getMessage()));
//			throw e;
//		}
//	}
//	
//	@RequestMapping(value = "/importpifile/colorerrorlist", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getColorErrorListWhenImportPiFile() {
//		log.info(String.format("getColorErrorListWhenImportPiFile in class %s", getClass()));
//		try {
//			log.debug("getting Color Error List When Import Pi File and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			result.put("status", "ok");
//			result.put("colorerrorlist", lstColor);
//			log.debug("getColorErrorListWhenImportPiFile successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String.format("getColorErrorListWhenImportPiFile in class %s has error: %s",
//					getClass(), e.getMessage()));
//			throw e;
//		}
//	}
//	
//	@RequestMapping(value = "/importpifile/garmentstyleerrorlist", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getGarmentStyleErrorListWhenImportPiFile() {
//		log.info(String.format("getGarmentStyleErrorListWhenImportPiFile in class %s", getClass()));
//		try {
//			log.debug("getting Error List When Import Pi File and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			//List<ArrayList<String>> ls = service.validateGeneralResult(lstImportPiFile, lotNumber);
//			result.put("status", "ok");
//			result.put("garmentstyleerrorlist", lstGarmentStyle);
//			log.debug("getGarmentStyleErrorListWhenImportPiFile successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String.format("getGarmentStyleErrorListWhenImportPiFile in class %s has error: %s",
//					getClass(), e.getMessage()));
//			throw e;
//		}
//	}
//
//	@RequestMapping(value = "/importpifile/sizeerrorlist", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getSizeErrorListWhenImportPiFile() {
//		log.info(String.format("getSizeErrorListWhenImportPiFile in class %s", getClass()));
//		try {
//			log.debug("getting Error List When Import Pi File and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			result.put("status", "ok");
//			result.put("sizeerrorlist", lstSizeModel);
//			log.debug("getSizeErrorListWhenImportPiFile successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String.format("getSizeErrorListWhenImportPiFile in class %s has error: %s",
//					getClass(), e.getMessage()));
//			throw e;
//		}
//	}
//	
//	@RequestMapping(value = "/importpifile/lstvalidatestatus", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getListValidateStatus() {
//		log.info(String.format("getListValidateStatus in class %s", getClass()));
//		try {
//			log.debug("getting List Validate Status and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			result.put("status", service.lstValidateStatus());
//			log.debug("getListValidateStatus successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String.format("getListValidateStatus in class %s has error: %s",
//					getClass(), e.getMessage()));
//			throw e;
//		}
//	}
//	
//	//function to check if a pi that had pi grid or not, if yes return true, else false
//	@RequestMapping(value = "/importpifile/checkpigridisexistedornot/{lotNumber}", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> checkIfPiGridCodeIsExistedInAPi(@PathVariable String lotNumber) {
//		log.info(String.format("checkIfPiGridCodeIsExistedInAPi in class %s", getClass()));
//		try {
//			log.debug("checking Pi Grid Code Is Existed In A Pi and return json");
//			Map<String, Object> result = new HashMap<String, Object>();
//			result.put("status", service.checkIfPiGridCodeIsExistedInAPi(lotNumber));
//			log.debug("checkIfPiGridCodeIsExistedInAPi successful");
//			return result;
//		} catch (Exception e) {
//			log.error(String.format("checkIfPiGridCodeIsExistedInAPi in class %s has error: %s",
//					getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	
	
}
