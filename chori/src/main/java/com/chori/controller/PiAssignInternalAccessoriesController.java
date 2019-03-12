package com.chori.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.chori.model.InternalAccessoriesToAssignModel;
import com.chori.model.PiAssignInternalAccessories_OrderChoseModel;
import com.chori.model.PiassigninternalaccessoriesModel;
import com.chori.model.PiassigninternalaccessoriesdetailModel;
import com.chori.service.PiassigninternalaccessoriesService;
import com.chori.service.PiassigninternalaccessoriesdetailService;
import com.chori.service.PiassigninternalaccessoriesofordersService;

@Controller
@RequestMapping(value="/")
public class PiAssignInternalAccessoriesController {
	
	@Autowired
	PiassigninternalaccessoriesService ser;
	
	@Autowired
	PiassigninternalaccessoriesdetailService detailService;
	
	@Autowired
	PiassigninternalaccessoriesofordersService piassigninternalaccessoriesofordersService;
	
	@Autowired
    MessageSource messageSource;
	
	private static final Log log = LogFactory.getLog(PiAssignInternalAccessoriesController.class);
	
	/**
	 * This method is used to get all accessory which has "Internal Kind" in database a
	 * @return list accessory in JSON
	 */
	@RequestMapping(value = "/assigninternalaccessory/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, Object> getAllInternalAccessoryToAssign(@RequestBody PiassigninternalaccessoriesModel piassigninternalaccessoriesModel) {
        log.info(String.format("getAllInternalAccessory in class %s", getClass()));
		try{
			log.debug("getting list of all getAllInternalAccessoryToAssign and return json");
        	Map<String, Object> result = new HashMap<String, Object>();
            List<InternalAccessoriesToAssignModel> ls = ser.getAllInternalAccessory(piassigninternalaccessoriesModel.getLotnumber(),piassigninternalaccessoriesModel.getFactorycode());
            result.put("status", "ok");
            result.put("list", ls);
            log.debug("getAllInternalAccessoryToAssign successful");
            return result;
        }catch(Exception e){
        	log.error(String.format("getAllInternalAccessoryToAssign in class %s has error: %s", getClass(), e.getMessage()));
        	throw e;
        }
    }
	
	/**
	 * This method is used to get all accessory which has "Internal Kind" in database a
	 * @return list accessory in JSON
	 */
	@RequestMapping(value = "/assignedinternalaccessoryofpi/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, Object> getInternalAccessoryWasAssignedOfPi(@RequestBody PiassigninternalaccessoriesModel piassigninternalaccessoriesModel) {
        log.info(String.format("getInternalAccessoryWasAssignedOfPi in class %s", getClass()));
		try{
			log.debug("getting list of all getInternalAccessoryWasAssignedOfPi and return json");
        	Map<String, Object> result = new HashMap<String, Object>();
            List<InternalAccessoriesToAssignModel> ls = ser.getAllInternalAccessoryOfPI(piassigninternalaccessoriesModel.getLotnumber(),piassigninternalaccessoriesModel.getFactorycode());
            result.put("status", "ok");
            result.put("list", ls);
            log.debug("getInternalAccessoryWasAssignedOfPi successful");
            return result;
        }catch(Exception e){
        	log.error(String.format("getInternalAccessoryWasAssignedOfPi in class %s has error: %s", getClass(), e.getMessage()));
        	throw e;
        }
    }
	
	/**
	 * Return mock view page for assign internal 
	 */
	@RequestMapping(value = { "/listassigninternal" }, method = RequestMethod.GET)
	public String listAssignInternal() {
		log.info(String.format("listAssignInternal with param 'response' in class: %s", getClass()));
		try{
			log.debug("return listAssignInternal view for request");
			log.debug("listAssignInternal successful");
			return "operation/assigninternalaccessories/assigninternalaccessories";
		}catch(Exception e){
			log.error(String.format("listAssignInternal with param 'response' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to assign internal accessories to pi
	 * @param piassigninternalaccessoriesModel
	 * @return addStatus as JSON format 
	 */
	@ResponseBody
    @RequestMapping(value = "/assigninternalaccessory/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> addPiassigninternalaccessoriesModel(@RequestBody ArrayList<PiassigninternalaccessoriesModel> lstPiassigninternalaccessoriesModel) {
		log.info(String.format("addPiassigninternalaccessoriesModel with param 'piassigninternalaccessoriesModel' in class: %s", getClass()));
		try{
	        Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");	
	        String userName= LoginController.currentUser;
	        for(PiassigninternalaccessoriesModel piassigninternalaccessoriesModel: lstPiassigninternalaccessoriesModel)
	        {
	        	ser.addPiassigninternalaccessories(piassigninternalaccessoriesModel, userName);
	        }
	        //result.put("addStatus", ser.addPiassigninternalaccessories(piassigninternalaccessoriesModel, userName));
	        log.debug("addPiassigninternalaccessoriesModel successful");
	        return result;
		}catch(Exception e){
			log.error(String.format("addPiassigninternalaccessoriesModel with param 'piassigninternalaccessoriesModel' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
    }
	
	/**
	 * This method is used to get pi assign accessory detail by piassignaccessorycode
	 * @return list pi assign accessory detail by piassignaccessorycode in JSON
	 */
	@RequestMapping(value = "/assigninternalaccessorydetail/list/{piassigninternalaccessorycode}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> getAssignInternalAccessorydetailByPiAssignAccessoryCode(@PathVariable Integer piassigninternalaccessorycode) {
        log.info(String.format("getAssignInternalAccessorydetailByPiAssignAccessoryCode in class %s", getClass()));
		try{
			log.debug("getting list of Assign Internal Accessory detail By PiAssignAccessoryCode and return json");
        	Map<String, Object> result = new HashMap<String, Object>();
            List<PiassigninternalaccessoriesdetailModel> ls = ser.getPiassigninternalaccessoriesdetailModelByPiInternalAccessoryCode(piassigninternalaccessorycode);
            result.put("status", "ok");
            result.put("list", ls);
            log.debug("getAssignInternalAccessorydetailByPiAssignAccessoryCode successful");
            return result;
        }catch(Exception e){
        	log.error(String.format("getAssignInternalAccessorydetailByPiAssignAccessoryCode in class %s has error: %s", getClass(), e.getMessage()));
        	throw e;
        }
    }
	
	/**
	 * This function check if a pi assign internal accessories with given piassigninternalaccessoriesModel is existed on database.
	 * @param piassigninternalaccessoriesModel
	 * @return is existed status in JSON format
	 */
	@RequestMapping(value = "/piassigninternalaccessories/isExist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> isPiassigninternalaccessoriesExistedById(@RequestBody PiassigninternalaccessoriesModel piassigninternalaccessoriesModel) {
		log.info(String.format("isPiassigninternalaccessoriesExistedById with param 'piassigninternalaccessoriesModel' in class: %s", getClass()));
		try{
			Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");
	        result.put("isExisted", ser.isPiassigninternalaccessoriesExisted(piassigninternalaccessoriesModel));
	        log.debug("check isSizeExistedById successful");
	        return result;
		}catch(Exception e){
			log.error(String.format("isPiassigninternalaccessoriesExistedById with param 'piassigninternalaccessoriesModel' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
    }
	
	/**
	 * This function is used to delete a piassigninternalaccessories
	 * @param accessorycode,lotnumber
	 * @return deleteStatus
	 */
	@RequestMapping(value = "/piassigninternalaccessories/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> deletePiAssignInternalAccessories(@RequestBody ArrayList<PiassigninternalaccessoriesModel> lstPiassigninternalaccessoriesModel) {
		log.info(String.format("deletePiAssignInternalAccessories with param 'accessorycode,lotnumber' in class: %s", getClass()));
		try{
			Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");
	        for(PiassigninternalaccessoriesModel piassigninternalaccessoriesModel : lstPiassigninternalaccessoriesModel)
	        {
		        Integer piAssignInternalAccessoriesId = ser.findPiAssignInternalAccessoriesIdByAccessoryCodeAndLotNumber(piassigninternalaccessoriesModel);
		        ser.deletePiAssignInternalAccessoriesById(piAssignInternalAccessoriesId);
	        }
	        //result.put("deleteStatus", ser.deletePiAssignInternalAccessoriesById(piAssignInternalAccessoriesId));
	        log.debug("deletePiAssignInternalAccessories successfully");
	        return result;
		}catch(Exception e){
			log.error(String.format("deletePiAssignInternalAccessories with param 'accessorycode,lotnumber' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
    }
	
	/**
	 * This method is used to get detail of a piassigninternalaccessories by lotnumber and accessorycode
	 * @return list accessory in JSON
	 */
	@RequestMapping(value = "/piassigninternalaccessorydetail/list/{lotnumber}/{accessorycode}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> getPiInternalAccessoriesDetail(@PathVariable String lotnumber,@PathVariable String accessorycode) {
        log.info(String.format("getPiInternalAccessoriesDetail in class %s", getClass()));
		try{
			log.debug("getting list of all getPiInternalAccessoriesDetail and return json");
        	Map<String, Object> result = new HashMap<String, Object>();
            List<PiassigninternalaccessoriesdetailModel> ls = ser.findPiAssignInternalAccessoriesModelDetailByLotNumberAndAccessoryCode(lotnumber, accessorycode);
            result.put("status", "ok");
            result.put("list", ls);
            log.debug("getPiInternalAccessoriesDetail successful");
            return result;
        }catch(Exception e){
        	log.error(String.format("getPiInternalAccessoriesDetail in class %s has error: %s", getClass(), e.getMessage()));
        	throw e;
        }
    }
	
	/**
	 * This function find a piassigninternalaccessoriesdetail by lotnumber and accessorycode then return it as JSON format
	 * @param lotnumber,accessorycode
	 * @return piassigninternalaccessories detail in JSON format
	 */
	@ResponseBody
	@RequestMapping(value = "/piassigninternalaccessoriesdetail/detail", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getPiAssignInternalAccessoriesDetailByLotNumberAndAccessoryCode(@RequestBody PiassigninternalaccessoriesModel piassigninternalaccessoriesModel) {
		log.info(String.format("getPiAssignInternalAccessoriesDetailByLotNumberAndAccessoryCode with param 'lotnumber,accessorycode' in class: %s", getClass()));
		try{
			log.debug("getting PiAssign Internal Accessories Detail By LotNumber And AccessoryCode by its {lotnumber,accessorycode} and return json");
			Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");
	        result.put("piassigninternalaccessoriesdetail", detailService.getAnAssignInternalAccessoryDetailOfAPi(piassigninternalaccessoriesModel.getLotnumber(), piassigninternalaccessoriesModel.getAccessory()));
	        log.debug("getPiAssignInternalAccessoriesDetailByLotNumberAndAccessoryCode successful");
	        return result;
		}catch(Exception e){
			log.error(String.format("getPiAssignInternalAccessoriesDetailByLotNumberAndAccessoryCode with param 'lotnumber,accessorycode' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
    }
	
	/**
	 * This function edit a piassigninternalaccessoriesdetailModel
	 * @param piassigninternalaccessoriesdetailModel
	 * @return editStatus as JSON
	 */
	@ResponseBody
    @RequestMapping(value = "/piassigninternalaccessoriesdetail/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> editPiassigninternalaccessoriesdetail(@RequestBody ArrayList<PiassigninternalaccessoriesdetailModel> lstPiassigninternalaccessoriesdetailModel) {
		log.info(String.format("editPiassigninternalaccessoriesdetail with param 'piassigninternalaccessoriesdetailModel' in class: %s", getClass()));
		try{
			log.debug("edit Piassigninternalaccessoriesdetail and return edit status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");
	        for(PiassigninternalaccessoriesdetailModel piassigninternalaccessoriesdetailModel : lstPiassigninternalaccessoriesdetailModel)
	        {
	        	detailService.editPiassigninternalaccessoriesdetail(piassigninternalaccessoriesdetailModel);
	        }	
	        result.put("editStatus",true);
	        log.debug("editPiassigninternalaccessoriesdetail successful");
	        return result;
		}catch(Exception e){
			log.error(String.format("editPiassigninternalaccessoriesdetail with param 'piassigninternalaccessoriesdetailModel' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
    }
	
	/**
	 * This method is used to get list of all order internal accessory which is suitable for assign (follow accessory)
	 * @param accessorycode,lotno
	 * @return list order (with its detail) in JSON
	 */
	@RequestMapping(value = "/assigninternalaccessory/list/orderlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, Object> getOrderInternalListForAssign(@RequestBody PiassigninternalaccessoriesModel piassigninternalaccessoriesModel) {
        log.info(String.format("getOrderInternalListForAssign in class %s", getClass()));
		try{
			log.debug("getting list of all getOrderInternalListForAssign and return json");
        	Map<String, Object> result = new HashMap<String, Object>();
            List<PiAssignInternalAccessories_OrderChoseModel> ls = ser.getOrderInternalListForAssign(piassigninternalaccessoriesModel.getAccessory(),piassigninternalaccessoriesModel.getLotnumber());
            result.put("availableQtyValue",ser.getAvailableQuantityChooseFormOrder(piassigninternalaccessoriesModel.getAccessory(),piassigninternalaccessoriesModel.getLotnumber()) );
            result.put("status", "ok");
            result.put("list", ls);
            log.debug("getOrderInternalListForAssign successful");
            return result;
        }catch(Exception e){
        	log.error(String.format("getOrderInternalListForAssign in class %s has error: %s", getClass(), e.getMessage()));
        	throw e;
        }
    }
	
	/**
	 * This method is used to get Available Quantity which is Chose Form Order
	 * @param accessorycode,lotno
	 * @return Available Quantity value
	 */
	@RequestMapping(value = "/assigninternalaccessory/orderlist/getavailablequantity/{accessorycode}/{lotno}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> getAvailableQuantityChooseFormOrder(@PathVariable String accessorycode, @PathVariable String lotno) {
        log.info(String.format("getAvailableQuantityChooseFormOrder in class %s", getClass()));
		try{
			log.debug("get Available Quantity Choose Form Order and return json");
        	Map<String, Object> result = new HashMap<String, Object>();
            List<PiAssignInternalAccessories_OrderChoseModel> ls = ser.getOrderInternalListForAssign(accessorycode,lotno);
            result.put("availableQtyValue",ser.getAvailableQuantityChooseFormOrder(accessorycode,lotno) );
            result.put("list", ls);
            log.debug("getAvailableQuantityChooseFormOrder successful");
            return result;
        }catch(Exception e){
        	log.error(String.format("getAvailableQuantityChooseFormOrder in class %s has error: %s", getClass(), e.getMessage()));
        	throw e;
        }
    }
	
	/**
	 * This method is used to get list of all order internal accessory which is assigned for a PI (follow accessory)
	 * @param accessorycode,lotno
	 * @return list order (with its detail) in JSON
	 */
	@RequestMapping(value = "/assigninternalaccessory/list/orderassigndetail/{accessorycode}/{lotno}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> getOrderInternalAccIsAssignedForAPI(@PathVariable String accessorycode, @PathVariable String lotno) {
        log.info(String.format("getOrderInternalListForAssign in class %s", getClass()));
		try{
			log.debug("getting list of all getOrderInternalListForAssign and return json");
        	Map<String, Object> result = new HashMap<String, Object>();
            List<PiAssignInternalAccessories_OrderChoseModel> ls = ser.getOrderInternalAccIsAssignedForAPI(accessorycode,lotno);
            result.put("status", "ok");
            result.put("list", ls);
            log.debug("getOrderInternalListForAssign successful");
            return result;
        }catch(Exception e){
        	log.error(String.format("getOrderInternalListForAssign in class %s has error: %s", getClass(), e.getMessage()));
        	throw e;
        }
    }
	
	/**
	 * This method is used to find PiAssignInternalAccessoriesId By AccessoryCode And LotNumber
	 * @return Id
	 */
	@RequestMapping(value = "/piassigninternalaccessory/findbyid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, Object> findPiAssignInternalAccessoriesId(@RequestBody PiassigninternalaccessoriesModel piassigninternalaccessoriesModel) {
        log.info(String.format("findPiAssignInternalAccessoriesId in class %s", getClass()));
		try{
			log.debug("find PiAssignInternalAccessoriesId and return json");
	        Map<String, Object> result = new HashMap<String, Object>();
	        result.put("id", piassigninternalaccessoriesofordersService.findPiAssignInternalAccessoriesIdByAccessoryCodeAndLotNumber2(piassigninternalaccessoriesModel.getLotnumber(), piassigninternalaccessoriesModel.getAccessory()));		
			//Integer result = piassigninternalaccessoriesofordersService.findPiAssignInternalAccessoriesIdByAccessoryCodeAndLotNumber2(lotnumber, accessorycode);
            log.debug("findPiAssignInternalAccessoriesId successful");
            return result;
        }catch(Exception e){
        	log.error(String.format("findPiAssignInternalAccessoriesId in class %s has error: %s", getClass(), e.getMessage()));
        	throw e;
        }
    }
	
	/**
	 * This function is used to assign internal accessories to pi
	 * @param piassigninternalaccessoriesModel
	 * @return addStatus as JSON format 
	 */
	@ResponseBody
    @RequestMapping(value = "/piassigninternalaccessoriesoforders/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> addPiAssignInternalAccessories_OrderChoseModel(@RequestBody PiAssignInternalAccessories_OrderChoseModel piAssignInternalAccessories_OrderChoseModel) {
		log.info(String.format("addPiAssignInternalAccessories_OrderChoseModel with param 'piAssignInternalAccessories_OrderChoseModel' in class: %s", getClass()));
		try{
	        Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");	
	        result.put("addStatus", piassigninternalaccessoriesofordersService.savePiAssignInternalAccessoriesOfOrders(piAssignInternalAccessories_OrderChoseModel));
	        log.debug("addPiAssignInternalAccessories_OrderChoseModel successful");
	        return result;
		}catch(Exception e){
			log.error(String.format("addPiAssignInternalAccessories_OrderChoseModel with param 'piAssignInternalAccessories_OrderChoseModel' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
    }
	
	
	/**
	 * This function is used to get Sum Of Assigned Quantity, to prevent edit  "order chose quantity" < quantity is assigned
	 * @param accessoryCode,lotnumber
	 * @return sum of assigned quantity
	 */
	@RequestMapping(value = "/piassigninternalaccessory/calculatesumofassignquantity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Double getSumOfAssignedQuantity(@RequestBody PiassigninternalaccessoriesModel piassigninternalaccessoriesModel) {
        log.info(String.format("getSumOfAssignedQuantity in class %s", getClass()));
		try{
			
			log.debug("find getSumOfAssignedQuantity and return json");
			Double result = piassigninternalaccessoriesofordersService.getSumOfAssignedQuantity(piassigninternalaccessoriesModel.getLotnumber(), piassigninternalaccessoriesModel.getAccessory());
            log.debug("getSumOfAssignedQuantity successful");
            return result;
        }catch(Exception e){
        	log.error(String.format("getSumOfAssignedQuantity in class %s has error: %s", getClass(), e.getMessage()));
        	throw e;
        }
    }
	
	
	/**
	 * This method is used to get sizename by sizecode
	 * @return list accessory in JSON
	 */
	@RequestMapping(value = "/assigninternalaccessory/getsizenamebysizecode/{sizecode}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> getSizeNameBySizeCode(@PathVariable Integer sizecode) {
        log.info(String.format("getSizeNameBySizeCode in class %s", getClass()));
		try{
			log.debug("getting SizeNameBySizeCodeand return json");
        	Map<String, Object> result = new HashMap<String, Object>();
            result.put("result",  ser.getSizeNameBySizeCode(sizecode));
            log.debug("getSizeNameBySizeCode successful");
            return result;
        }catch(Exception e){
        	log.error(String.format("getSizeNameBySizeCode in class %s has error: %s", getClass(), e.getMessage()));
        	throw e;
        }
    }
//	/**
//	 * This method is used to get GarmentStyle + Color of a PI by PiGridDetail
//	 * @return list GarmentStyle + Color in JSON
//	 */
//	@RequestMapping(value = "/assignedinternalaccessoryofpi22/list/{lotnumber}", produces = "application/json", method = RequestMethod.GET)
//    @ResponseBody
//	public Map<String, Object> getInternalAccessoryWasAssignedOfPi22(@PathVariable String lotnumber) {
//        log.info(String.format("getInternalAccessoryWasAssignedOfPi in class %s", getClass()));
//		try{
//			log.debug("getting list of all getInternalAccessoryWasAssignedOfPi and return json");
//        	Map<String, Object> result = new HashMap<String, Object>();
//            List<PiassigninternalaccessoriesdetailModel> ls = ser.findPiAssignInternalAccessoriesDetailModel(lotnumber);
//            result.put("status", "ok");
//            result.put("list", ls);
//            log.debug("getInternalAccessoryWasAssignedOfPi successful");
//            return result;
//        }catch(Exception e){
//        	log.error(String.format("getInternalAccessoryWasAssignedOfPi in class %s has error: %s", getClass(), e.getMessage()));
//        	throw e;
//        }
//    }
	
 }
