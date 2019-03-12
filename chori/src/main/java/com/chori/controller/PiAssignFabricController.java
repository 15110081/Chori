package com.chori.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.xs.LSInputList;
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

import com.chori.entity.PiassignfabricId;
import com.chori.model.CalculateFAValueModel;
import com.chori.model.PIAssignFabricDetailModel;
import com.chori.model.PiassignfabricModel;
import com.chori.model.PiassigninternalaccessoriesModel;
import com.chori.service.PIAssignFabricDetailService;
import com.chori.service.PiassignfabricService;
import com.chori.service.PigriddetailService;

@Controller
@RequestMapping(value = "/")
public class PiAssignFabricController {

	@Autowired
	PiassignfabricService ser;
	
	@Autowired
	PigriddetailService pigriddetailSer;

	@Autowired
	PIAssignFabricDetailService pIAssignFabricDetailDetailService;
	private static final Log log = LogFactory
			.getLog(AccessoryConsumptionController.class);

	/**
	 * This function is used to assign Fabric To Pi, return assignfabric as JSON
	 * format
	 * 
	 * @param assignfabric
	 * @return status, assignfabric as JSON format
	 */
//	@ResponseBody
//	@RequestMapping(value = "/piassignfabric/assign", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseStatus(HttpStatus.OK)
//	public Map<String, Object> AssignFabricToPi(
//			@RequestBody PiassignfabricModel piassignfabricModel) {
//		log.info(String
//				.format("AssignFabricToPi with param 'piassignfabricModel' in class: %s",
//						getClass()));
//		try {
//			Map<String, Object> result = new HashMap<String, Object>();
//			result.put("status", "ok");
//			String userName = LoginController.currentUser;
//			result.put("addStatus",
//					ser.AssignFabricToPi(piassignfabricModel, userName));
//			log.debug("AssignFabricToPi successfully");
//			return result;
//		} catch (Exception e) {
//			log.error(String
//					.format("AssignFabricToPi with param 'piassignfabricModel' in class %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	
	@ResponseBody
	@RequestMapping(value = "/piassignfabric/assign", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> AssignFabricToPi(
			@RequestBody ArrayList<PiassignfabricModel> piassignfabricModel) {
		log.info(String
				.format("AssignFabricToPi with param 'piassignfabricModel' in class: %s",
						getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String userName = LoginController.currentUser;
			
			for (PiassignfabricModel piassignfabricModel2 : piassignfabricModel) {
				ser.AssignFabricToPi(piassignfabricModel2, userName);
			}
			result.put("addStatus",true);
			log.debug("AssignFabricToPi successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("AssignFabricToPi with param 'piassignfabricModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a piassignfabric with given piassignfabricModel is
	 * existed on database.
	 * 
	 * @param piassignfabricModel
	 * @return is existed status JSON value
	 */
	@RequestMapping(value = "/piassignfabric/isExist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isPiAssignFabricExistedById(
			@RequestBody PiassignfabricModel piassignfabricModel) {
		log.info(String
				.format("isPiAssignFabricExistedById with param 'sizeModel' in class: %s",
						getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			PiassignfabricId piassignfabricId = new PiassignfabricId();
			piassignfabricId.setFabricno(piassignfabricModel.getFabricno());
			piassignfabricId.setLotnumber(piassignfabricModel.getLotnumber());
			result.put("status", "ok");
			result.put("isExisted",
					ser.IsExistedPiAssignFabric(piassignfabricId));
			log.debug("check isPiAssignFabricExistedById successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isPiAssignFabricExistedById with param 'piassignfabricModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function get list for Fabric Assignment Quantity Form
	 * 
	 * @param lotNo
	 *            , fabricNo
	 * @return list Fabric Assignment in JSON format
	 */
	@RequestMapping(value = "/listfabricassignmentquantity", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListFabricAssignmentQuantity(
			@RequestBody PIAssignFabricDetailModel piAssignFabricDetailModel) {
		log.info(String
				.format("isPiAssignFabricExistedById with param 'fabricNo,lotNo' in class: %s",
						getClass()));
		try {
			log.debug("getting list FabricAssignmentQuantity and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<PIAssignFabricDetailModel> ls = pIAssignFabricDetailDetailService
					.getListFabricAssignmentQuantity(piAssignFabricDetailModel.getFabricno(), piAssignFabricDetailModel.getLotnumber());
			result.put("status", "ok"); 
			result.put("list", ls);
			
			log.debug("getListFabricAssignmentQuantity successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getListFabricAssignmentQuantity with param 'fabricNo,lotNo' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	/**
	 * This function is used to add,edit or delete FabricAssignmentQuantityToPIAssignFabricDetail, return addStatus
	 * as JSON format
	 * 
	 * @param piassignfabricdetail
	 * @return status, addStatus as JSON format
	 */

	
	@ResponseBody
	@RequestMapping(value = "/piassignfabricdetail/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> savePIAssignFabricDetail(
			@RequestBody ArrayList<PIAssignFabricDetailModel> listPiAssignFabricDetailModel) {
		log.info(String
				.format("savePIAssignFabricDetail with param 'piAssignFabricDetailModel' in class: %s",
						getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");			
			String userName = LoginController.currentUser;
			
//			ArrayList<PIAssignFabricDetailModel> listPiAssignFabricDetailModel1;
			for (PIAssignFabricDetailModel piAssignFabricDetailModel2 : listPiAssignFabricDetailModel) {
				
				pIAssignFabricDetailDetailService
				.saveFabricAssignmentQuantityToPIAssignFabricDetail(piAssignFabricDetailModel2, userName);			
				
			}
//			if(!listPiAssignFabricDetailModel.isEmpty())
//			{
				pIAssignFabricDetailDetailService
				.saveFAValue(listPiAssignFabricDetailModel.get(listPiAssignFabricDetailModel.size()-1).getLotnumber());
//			}
			
//			
//			result.put("saveStatus", pIAssignFabricDetailDetailService
//					.saveFabricAssignmentQuantityToPIAssignFabricDetail(piAssignFabricDetailModel, userName));	
//			result.put("saveStatus", pIAssignFabricDetailDetailService
//					.saveFAValue(piAssignFabricDetailModel.getLotnumber()));	
			log.debug("savePIAssignFabricDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("savePIAssignFabricDetail with param 'piAssignFabricDetailModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}
	
	
	/**
	 * This function check if a piassignfabricdetail with given piassignfabricdetailcode is
	 * existed on database.
	 * 
	 * @param piassignfabricdetailcode
	 * @return is existed status JSON value
	 */
	@RequestMapping(value = "/piassignfabricdetail/isExist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isPiAssignFabricDetailExistedById(
			@RequestBody PIAssignFabricDetailModel piAssignFabricDetailModel) {
		log.info(String
				.format("isPiAssignFabricDetailExistedById with param 'piassignfabricdetailcode' in class: %s",
						getClass()));
		try {
			Map<String, Object> result = new HashMap<String, Object>();			
			result.put("status", "ok");
			result.put("isExisted",
					pIAssignFabricDetailDetailService.isExistedByPIAssignFabricDetailCode(piAssignFabricDetailModel));
			log.debug("check isPiAssignFabricDetailExistedById successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("isPiAssignFabricDetailExistedById with param 'piassignfabricdetailcode' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to delete a PIAssignFabric
	 * @param  lotNo,  fabricNo
	 * @return deleteStatus
	 */
	@RequestMapping(value = "/piassignfabric/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> deletePIAssignFabric(@RequestBody ArrayList<PiassignfabricModel> piassignfabricModel) {
		log.info(String.format("deletePIAssignFabric with param 'lotNo' , 'fabricNo' in class: %s", getClass()));
		try{
			Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");   
	        
	        for (PiassignfabricModel piassignfabricModel2 : piassignfabricModel) {
	        	pIAssignFabricDetailDetailService.deletePIAssignFabricByLotNoAndFabricNo(piassignfabricModel2);
	        	pIAssignFabricDetailDetailService
				.saveFAValue(piassignfabricModel2.getLotnumber());
			}
	        result.put("deleteStatus", true);
	        log.debug("deletePIAssignFabric successfully");
	        return result;
		}catch(Exception e){
			log.error(String.format("deletePIAssignFabric with param 'lotNo' , 'fabricNo' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
    }
}
