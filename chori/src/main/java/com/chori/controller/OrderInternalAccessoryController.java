package com.chori.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
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
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.chori.dao.OrderInternalAccessoryDao;
import com.chori.entity.Accessorysupplier;
import com.chori.entity.Orderinternalaccessory;
import com.chori.entity.User;
import com.chori.model.AccessoryModel;
import com.chori.model.AccessoryPriceModel;
import com.chori.model.OrderInternalAccessoryDetailModel;
import com.chori.model.OrderInternalAccessoryModel;
import com.chori.service.AccessoryPriceService;
import com.chori.service.AccessoryService;
import com.chori.service.AccessorySupplierService;
import com.chori.service.OrderInternalAccessoryService;
import com.chori.service.UserService;

@Controller
@RequestMapping(value = "/")
public class OrderInternalAccessoryController {
	private static final Log log = LogFactory
			.getLog(OrderInternalAccessoryController.class);

	@Autowired
	OrderInternalAccessoryService service;
	
	
	@Autowired
	AccessoryService accessoryService;
	
	@Autowired
	AccessoryPriceService accessoryPriceService;
	
	@Autowired
	UserService userSer;
	
	@Autowired
	AccessorySupplierService accessorySupplierService;

	private List<OrderInternalAccessoryDetailModel> lstOrderInternalAccessoryDetailModel = new ArrayList<OrderInternalAccessoryDetailModel>();
	
	//tÆ°Æ¡ng tá»± vs list add, Ä‘c dÃ¹ng cho edit
	List<OrderInternalAccessoryDetailModel> lstOrderInternalAccessoryDetailModelEditVer = new ArrayList<OrderInternalAccessoryDetailModel>();

	/**
	 * This method is used to get all order internal accessory in database
	 * 
	 * @return a list order internal accessory in JSON format
	 */
	@RequestMapping(value = "/orderinternalaccessory/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllOrderinternalAccessory() {
		log.info(String.format("getAllOrderinternalAccessory in class %s",
				getClass()));
		try {
			log.debug("getting list of all OrderinternalAccessory and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<OrderInternalAccessoryModel> ls = service
					.getAllOrderInternalAccessoryModels();
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllOrderinternalAccessory successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllOrderinternalAccessory in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * Return view page for list Order Internal Accessory
	 */
	@RequestMapping(value = "/listorderinternalaccessory", method = RequestMethod.GET)
	public ModelAndView listOrderInternalAccessory(HttpServletResponse response) {
		log.info(String.format(
				"orderinternalaccessory with param 'response' in class: %s",
				getClass()));
		try {
			log.debug("return orderinternalaccessory view for request");
			response.setContentType("text/html");
			log.debug("orderinternalaccessory successful");
			return new ModelAndView(
					"operation/orderinternalaccessory/listOrderInternalAccessory");
		} catch (Exception e) {
			log.error(String
					.format("listOrderInternalAccessory with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}

	/**
	 * Return view page for Create Order Internal Accessory
	 */
	@RequestMapping(value = "/createorderinternalaccessory", method = RequestMethod.GET)
	public ModelAndView createOrderInternalAccessory(
			HttpServletResponse response) {
		log.info(String.format(
				"orderinternalaccessory with param 'response' in class: %s",
				getClass()));
		try {
			log.debug("return orderinternalaccessory view for request");
			response.setContentType("text/html");
			log.debug("orderinternalaccessory successful");
			return new ModelAndView(
					"operation/orderinternalaccessory/createOrderInternalAccessory");
		} catch (Exception e) {
			log.error(String
					.format("orderinternalaccessory with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}

	/**
	 * This function is used to add new orderinternalaccessory, return addStatus
	 * as JSON format
	 * 
	 * @param orderinternalaccessory
	 * @return status, addStatus as JSON format
	 */
	@ResponseBody
	@RequestMapping(value = "/orderinternalaccessory/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> saveOrderInternalAccessory(
			@RequestBody OrderInternalAccessoryModel orderInternalAccessoryModel) {
		log.info(String
				.format("saveOrderInternalAccessory with param 'orderInternalAccessoryModel' in class: %s",
						getClass()));
		try {
			log.debug("add 1 OrderInternalAccessory and return edit width as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			String username = LoginController.currentUser;
			//
			lstOrderInternalAccessoryDetailModel.clear();
			List<OrderInternalAccessoryDetailModel> lst = orderInternalAccessoryModel.getLstOrderInternalAccessoryDetailModel();
			for (OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel : lst) {
				lstOrderInternalAccessoryDetailModel.add(orderInternalAccessoryDetailModel);
			}
			//
			result.put("saveOrderInternalAccessory", service
					.addOrderInternalAccessory(
							orderInternalAccessoryModel,
							lstOrderInternalAccessoryDetailModel,
							username));
			
//			System.err.println(orderInternalAccessoryModel);
//			System.out.println(orderInternalAccessoryModel.getOrderDate()+"bobo");
			log.debug("saveOrderInternalAccessory successfully");
			lstOrderInternalAccessoryDetailModel.clear();
			return result;
		} catch (Exception e) {
			log.error(String
					.format("saveOrderInternalAccessory with param 'orderInternalAccessoryModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method is used to get all internal accessory by accessory supplier code in database and show in check box for user to choose
	 * 
	 * @return a list internal accessory in JSON format
	 */
	@RequestMapping(value = "/orderinternalaccessory/listaccessory/{accessorysuppliercode}", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllInternalAccessory(@PathVariable String accessorysuppliercode) {
		log.info(String.format("getAllOrderinternalAccessory in class %s",
				getClass()));
		try {
			log.debug("getting list of all OrderinternalAccessory and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<AccessoryModel> ls = service.getAllInternalAccessoryBySupplier(accessorysuppliercode);
			
			boolean flagIsChecked= false;
			
			for (AccessoryModel accessoryModel : ls) {
				for (OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel : lstOrderInternalAccessoryDetailModel) {
					if(accessoryModel.getAccessorycode().equals(orderInternalAccessoryDetailModel.getAccessorycode())){
						flagIsChecked = true;
						break;
					}
				}
				
				//náº¿u cÃ³ tá»“n táº¡i trong báº£ng detail r thÃ¬ hiá»ƒn thá»‹ bÃªn báº£ng check box lÃ  cÃ³ check
				if(flagIsChecked){
					accessoryModel.setCheckedForOrderInternal(true);
				}else{
					accessoryModel.setCheckedForOrderInternal(false);
				}
				flagIsChecked = false;
			}
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllinternalAccessory successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllinternalAccessory in class %s has error: %s",
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
//	@ResponseBody
//	@RequestMapping(value = "/orderinternalaccessory/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseStatus(HttpStatus.OK)
//	public Map<String, Object> updateOrderInternalAccessory(
//			@RequestBody OrderInternalAccessoryModel orderInternalAccessoryModel) {
//		log.info(String
//				.format("updateOrderInternalAccessory with param 'orderExternalAccessoryModel' in class: %s",
//						getClass()));
//		try {
//			log.debug("add 1 OrderExternalAccessory and return edit width as json format");
//			Map<String, Object> result = new HashMap<String, Object>();
//			result.put("status", "ok");
//			result.put("editStatus", service
//					.editOrderInternalAccessory(orderInternalAccessoryModel));
//			log.debug("updateOrderInternalAccessory successfully");
//			return result;
//		} catch (Exception e) {
//			log.error(String
//					.format("updateOrderInternalAccessory with param 'orderInternalAccessoryModel' in class %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}

	/**
	 * This function is used to add new order internal accessory detail to show in view
	 * HÃ m dÃ¹ng Ä‘á»ƒ add cÃ¡c accessory Ä‘c check vÃ´ order, ko tráº£ vá»� list trÃªn View, chá»‰ add thÃ´i
	 * @param oiamd
	 * @param bindingResult
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orderinternalaccessoryDetail/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addNewOrderInternalAccessoryDetail(
			@Valid @RequestBody ArrayList<AccessoryModel> lst,
			BindingResult bindingResult) {
		log.info(String
				.format("addNewOrderInternalAccessoryDetail with params 'fm' in class: %s",
						getClass()));
		try {
			log.debug("addNewOrderInternalAccessoryDetail and return status as json format");
			OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel;

			for (AccessoryModel accessoryModel : lst) {
				orderInternalAccessoryDetailModel = new OrderInternalAccessoryDetailModel();
				orderInternalAccessoryDetailModel
						.setAccessorycode(accessoryModel.getAccessorycode());
				orderInternalAccessoryDetailModel
						.setAccessoryname(accessoryService.findById(
								accessoryModel.getAccessorycode()).getName());
				orderInternalAccessoryDetailModel.setUnit(accessoryService
						.findById(accessoryModel.getAccessorycode())
						.getUnitByUnitcode().getUnitcode());
				orderInternalAccessoryDetailModel.setImage(accessoryService.findById(
						accessoryModel.getAccessorycode()).getImgurl1());
				
				//get currency code
				List<AccessoryPriceModel> lstCurrencyCodeAndUnitPrice = accessoryPriceService.getListAccessorypriceModelByAccessoryCodeandAccessorySuplierandOrderDate2ndCase(accessoryModel.getAccessorycode(), accessoryModel.getAccessorysuppliercode(), (accessoryModel.getOrderDate()==null)?new Date():accessoryModel.getOrderDate());
				for (AccessoryPriceModel accessoryPriceModel : lstCurrencyCodeAndUnitPrice) {
					orderInternalAccessoryDetailModel.setCurrency(accessoryPriceModel.getCurrencycode());
					DecimalFormat df = new DecimalFormat("###.####");
					orderInternalAccessoryDetailModel.setUnitprice(Double.parseDouble(df.format(accessoryPriceModel.getUnitpriceperunit().doubleValue())));
				}
				
				lstOrderInternalAccessoryDetailModel
						.add(orderInternalAccessoryDetailModel);
			}
			System.out.println(lstOrderInternalAccessoryDetailModel);
			System.err.println("Json lstOrderInternalAccessoryDetailModel : "
					+ lstOrderInternalAccessoryDetailModel);
//			System.out.println(lst);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			log.debug("addNewOrderInternalAccessoryDetail successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addNewOrderInternalAccessoryDetail with params 'fm' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get list order internal accessory Detail when
	 * adding
	 * 
	 * Sau khi add xong bÃªn trÃªn thÃ¬ á»Ÿ Ä‘Ã¢y show list accessory nÃ y lÃªn view
	 * 
	 * @return
	 */
	@RequestMapping(value = "/orderinternalaccessoryDetailAdd/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllOrderInternalAccessoryDetailAdd() {
		log.info(String
				.format("getAllOrderInternalAccessoryDetailAdd in class %s",
						getClass()));
		try {
			log.debug("getting list of All Order Internal Accessory detail and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("listOrderInternalDetailAdd",
					lstOrderInternalAccessoryDetailModel);
			log.debug("getAllOrderInternalAccessoryDetailAdd successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getAllOrderInternalAccessoryDetailAdd in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to clear all model when cancel and save add order
	 * internal accessory
	 * 
	 * @return
	 */
	@RequestMapping(value = "/orderinternalaccessory/clearDataAfterCloseAddOrderInternalAccDetail", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> clearDataAfterCloseAddOrderInternalAccDetail() {
		log.info(String.format(
				"clearDataAfterCloseAddOrderInternalAccDetail in class %s",
				getClass()));
		try {
			log.debug("getting list of All OrderInternalAccessoryDetail and return json");
			Map<String, Object> result = new HashMap<String, Object>();

			lstOrderInternalAccessoryDetailModel.clear();
			lstOrderInternalAccessoryDetailModelEditVer.clear();
			result.put("status", "ok");
			result.put("deleteStatus", true);
			log.debug("clearDataAfterCloseAddOrderInternalAccDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("clearDataAfterCloseAddOrderInternalAccDetail in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * End adding part
	 */
	/***
	 * This method is used to get detail of a order internal accessory (HÃ m find by id cho edit, gá»“m find by id vÃ  láº¥y list detail)
	 * 
	 * @param orderSheetNo
	 * @return detail of a orderinternalaccessory in JSON format
	 */
	@RequestMapping(value = "/orderinternalaccessory/detail/{orderSheetNo}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getOrderInternalAccessoryDetail(
			@PathVariable String orderSheetNo) {
		log.info(String
				.format("getOrderInternalAccessoryDetail with param 'typeCode' in class: %s",
						getClass()));
		try {
			log.debug("getting type's detail by its typeCode and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<AccessoryPriceModel> lstCurrencyCodeAndUnitPrice;
			//find by id quan order sheet no
			OrderInternalAccessoryModel oi = service
					.findOrderInternalAccessoryModelById(orderSheetNo);
			//láº¥y list qua order sheet no.
			List<OrderInternalAccessoryDetailModel> lstOrderInternalAccessoryDetailModel = service.findListOrderInternalAccessoryDetailModelByOrderSheetNo(orderSheetNo);
			for (OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel : lstOrderInternalAccessoryDetailModel) {
				lstCurrencyCodeAndUnitPrice = accessoryPriceService.getListAccessorypriceModelByAccessoryCodeandAccessorySuplierandOrderDate2ndCase(orderInternalAccessoryDetailModel.getAccessorycode(), oi.getAccsuplierCode(),oi.getOrderDate()==null?new Date():oi.getOrderDate());
				for (AccessoryPriceModel accessoryPriceModel : lstCurrencyCodeAndUnitPrice) {
					orderInternalAccessoryDetailModel.setCurrency(accessoryPriceModel.getCurrencycode());
					DecimalFormat df = new DecimalFormat("###.####");
					if(orderInternalAccessoryDetailModel.getUnitprice()==null)
					orderInternalAccessoryDetailModel.setUnitprice(Double.parseDouble(df.format(accessoryPriceModel.getUnitpriceperunit().doubleValue())));
				}
			}
			
			//gÃ¡n list detail cho list chung
			lstOrderInternalAccessoryDetailModelEditVer = lstOrderInternalAccessoryDetailModel;
			//in ra test thá»­
			System.err.println(lstOrderInternalAccessoryDetailModelEditVer);
			
			result.put("currentorderinternalaccessory", oi);
			result.put("listOrderInternalAccessoryDetail", lstOrderInternalAccessoryDetailModel);
			result.put("status", "ok");
			log.debug("getOrderInternalAccessoryDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getOrderInternalAccessoryDetail with param 'orderSheetNo' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This method is used to get all internal accessory by accessory supplier code in database and show in check box for user to choose Edit ver
	 * 
	 * @return a list internal accessory in JSON format
	 */
	@RequestMapping(value = "/orderinternalaccessory/listaccessoryEditVer/{accessorysuppliercode}", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllInternalAccessoryEditVer(@PathVariable String accessorysuppliercode) {
		log.info(String.format("getAllOrderinternalAccessory in class %s",
				getClass()));
		try {
			log.debug("getting list of all OrderinternalAccessory and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			List<AccessoryModel> ls = service.getAllInternalAccessoryBySupplier(accessorysuppliercode);
			
			boolean flagIsChecked= false;
			
			for (AccessoryModel accessoryModel : ls) {
				for (OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel : lstOrderInternalAccessoryDetailModelEditVer) {
					if(accessoryModel.getAccessorycode().equals(orderInternalAccessoryDetailModel.getAccessorycode())){
						flagIsChecked = true;
						break;
					}
				}
				
				//náº¿u cÃ³ tá»“n táº¡i trong báº£ng detail r thÃ¬ hiá»ƒn thá»‹ bÃªn báº£ng check box lÃ  cÃ³ check
				if(flagIsChecked){
					accessoryModel.setCheckedForOrderInternal(true);
				}else{
					accessoryModel.setCheckedForOrderInternal(false);
				}
				flagIsChecked = false;
			}
			result.put("status", "ok");
			result.put("list", ls);
			log.debug("getAllinternalAccessory successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllinternalAccessory in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	
	/////
	@ResponseBody
	@RequestMapping(value = "/orderinternalaccessoryDetailEditVer/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> addNewOrderInternalAccessoryDetailEditVer(
			@Valid @RequestBody ArrayList<AccessoryModel> lst,
			BindingResult bindingResult) {
		log.info(String
				.format("addNewOrderInternalAccessoryDetail with params 'fm' in class: %s",
						getClass()));
		try {
			log.debug("addNewOrderInternalAccessoryDetail and return status as json format");
			OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel;
			
			//list add
			List<AccessoryModel> lstAdd= new ArrayList<AccessoryModel>();
			//list xÃ³a
			List<OrderInternalAccessoryDetailModel> lstDelete = new ArrayList<OrderInternalAccessoryDetailModel>();
			
			//biáº¿n kiá»ƒm tra tá»“n táº¡i
			boolean isExist1= false;
			//(táº¡o list add) láº·p qua list accessory gá»Ÿi vá»�
			for (AccessoryModel accessoryModel : lst) {
				for (OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel1 : lstOrderInternalAccessoryDetailModelEditVer) {
					//náº¿u p tá»­ trong list acc mÃ  ko tá»“n táº¡i trong detail thÃ¬ thÃªm vÃ o list add
					if(accessoryModel.getAccessorycode().equals(orderInternalAccessoryDetailModel1.getAccessorycode())){
						isExist1 = true;
						break;
					}
				}
				
				if(!isExist1){
					lstAdd.add(accessoryModel);
				}
				isExist1 = false;
			}
			
			//biáº¿n kiá»ƒm tra tá»“n táº¡i
			isExist1= false;
			//táº¡o list xÃ³a
			for (OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel1 : lstOrderInternalAccessoryDetailModelEditVer) {
				for (AccessoryModel accessoryModel : lst) {
					if(accessoryModel.getAccessorycode().equals(orderInternalAccessoryDetailModel1.getAccessorycode())){
						isExist1 = true;
						break;
					}
				}
				
				if(!isExist1){
					lstDelete.add(orderInternalAccessoryDetailModel1);
				}
				isExist1 = false;
			}
			
			//add vÃ´ tá»« list add
			for (AccessoryModel accessoryModel : lstAdd) {
				orderInternalAccessoryDetailModel = new OrderInternalAccessoryDetailModel();
				orderInternalAccessoryDetailModel
						.setAccessorycode(accessoryModel.getAccessorycode());
				orderInternalAccessoryDetailModel
						.setAccessoryname(accessoryService.findById(
								accessoryModel.getAccessorycode()).getName());
				orderInternalAccessoryDetailModel.setUnit(accessoryService
						.findById(accessoryModel.getAccessorycode())
						.getUnitByUnitcode().getUnitcode());
				orderInternalAccessoryDetailModel.setImage(accessoryService.findById(
						accessoryModel.getAccessorycode()).getImgurl1());
				
				//get currency code
				List<AccessoryPriceModel> lstCurrencyCodeAndUnitPrice = accessoryPriceService.getListAccessorypriceModelByAccessoryCodeandAccessorySuplierandOrderDate2ndCase(accessoryModel.getAccessorycode(), accessoryModel.getAccessorysuppliercode(),(accessoryModel.getOrderDate()==null)?new Date():accessoryModel.getOrderDate());
				for (AccessoryPriceModel accessoryPriceModel : lstCurrencyCodeAndUnitPrice) {
					orderInternalAccessoryDetailModel.setCurrency(accessoryPriceModel.getCurrencycode());
					DecimalFormat df = new DecimalFormat("###.####");
					orderInternalAccessoryDetailModel.setUnitprice(Double.parseDouble(df.format(accessoryPriceModel.getUnitpriceperunit().doubleValue())));
				}
				
				lstOrderInternalAccessoryDetailModelEditVer
						.add(orderInternalAccessoryDetailModel);
			}
			
			//xÃ³a tá»« list xÃ³a
			lstOrderInternalAccessoryDetailModelEditVer.removeAll(lstDelete);
			
			System.out.println(lstOrderInternalAccessoryDetailModelEditVer);
			System.err.println("Json lstOrderInternalAccessoryDetailModel : "
					+ lstOrderInternalAccessoryDetailModelEditVer);

			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			log.debug("addNewOrderInternalAccessoryDetail successfully");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("addNewOrderInternalAccessoryDetail with params 'fm' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	/////
	//HÃ m show view
	@RequestMapping(value = "/orderinternalaccessoryDetailAddEditVer/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllOrderInternalAccessoryDetailAddEditVer() {
		log.info(String
				.format("getAllOrderInternalAccessoryDetailAdd in class %s",
						getClass()));
		try {
			log.debug("getting list of All Order Internal Accessory detail and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			result.put("listOrderInternalDetailAdd",
					lstOrderInternalAccessoryDetailModelEditVer);
			log.debug("getAllOrderInternalAccessoryDetailAdd successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getAllOrderInternalAccessoryDetailAdd in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to edit order internal accessory
	 * | HÃ m Ä‘c dÃ¹ng Ä‘á»ƒ edit 1 order internal accessory
	 * @param orderInternalAccessoryModel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orderinternalaccessory/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editOrderInternalAccessory(
			@RequestBody OrderInternalAccessoryModel orderInternalAccessoryModel) {
		log.info(String
				.format("saveOrderInternalAccessory with param 'orderInternalAccessoryModel' in class: %s",
						getClass()));
		try {
			log.debug("add 1 OrderInternalAccessory and return edit width as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", "ok");
			//Láº¥y user hiá»‡n táº¡i (dÃ¹ng Ä‘á»ƒ add cÃ¡c detail náº¿u cÃ³)
			String username = LoginController.currentUser;
			//gÃ¡n list detail láº¥y vá»� tá»« java script
			lstOrderInternalAccessoryDetailModelEditVer.clear();
			List<OrderInternalAccessoryDetailModel> lst = orderInternalAccessoryModel.getLstOrderInternalAccessoryDetailModel();
			for (OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel : lst) {
				lstOrderInternalAccessoryDetailModelEditVer.add(orderInternalAccessoryDetailModel);
			}
			//tiáº¿n hÃ nh update
			result.put("saveOrderInternalAccessory", service
					.editOrderInternalAccessory(
							orderInternalAccessoryModel,
							lstOrderInternalAccessoryDetailModelEditVer,
							username));
			
//			System.err.println(orderInternalAccessoryModel);
//			System.err.println(lstOrderInternalAccessoryDetailModelEditVer);
			
			log.debug("saveOrderInternalAccessory successfully");
			lstOrderInternalAccessoryDetailModelEditVer.clear();
			return result;
		} catch (Exception e) {
			log.error(String
					.format("saveOrderInternalAccessory with param 'orderInternalAccessoryModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * Send email part
	 */
	private JavaMailSender mailSender;
	
	/**
	 * HÃ m validate email
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
	 * @return
	 */
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		//get log in user
		User currentUser = userSer.findById(LoginController.currentUser);
		
		// Using Gmail SMTP configuration.
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
//		mailSender.setUsername("bleachclone69@gmail.com");
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
	 * HÃ m Ä‘c dÃ¹ng Ä‘á»ƒ xá»­ lÃ½ send mail
	 * @param request
	 * @param attachFile
	 * @return
	 */
	@RequestMapping(value = "/sendmailForOrderInternalAccessory", method = RequestMethod.POST)
	public ModelAndView sendEmail(HttpServletRequest request,
			final @RequestParam CommonsMultipartFile attachFile) {

		try{
			// reads form input
			final String emailTo = request.getParameter("mailTo");
			final String subject = request.getParameter("subject");
			final String message = request.getParameter("message");
			final String CC = request.getParameter("CC");
			final String orderSheetNo = request.getParameter("orderSheetNo");

			// for logging
			System.out.println("emailTo: " + emailTo);
			System.out.println("subject: " + subject);
			System.out.println("message: " + message);
			System.out.println("CC: " + CC);
			System.out.println("attachFile: " + attachFile.getOriginalFilename());
			System.err.println("order sheet no: " + orderSheetNo);

			mailSender = getMailSender();
			
			mailSender.send(new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper messageHelper = new MimeMessageHelper(
							mimeMessage, true, "UTF-8");
					
					//email to part
					//khai bÃ¡o list email to
					List<String> lstEmailTo= new ArrayList<String>();
					//máº£ng nháº­n, tÃ¡ch cÃ¡c trÆ°á»�ng há»£p , hay ;
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
					
					//add email of accessory vào
					Orderinternalaccessory orderinternalaccessory = service.findById(orderSheetNo);
					String emailOfAccessorySupplier = accessorySupplierService.findById(orderinternalaccessory.getAccessorysupplier().getAccsuppliercode()).getEmail();
					System.err.println(emailOfAccessorySupplier);
					if(OrderInternalAccessoryController.isValidEmailAddress(emailOfAccessorySupplier)){
						lstEmailTo.add(emailOfAccessorySupplier);
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
					messageHelper.setText(message,true);
					
					//cc part
					List<String> lstCC= new ArrayList<String>();
					List<User> lstUser = userSer.getAll();
					//láº·p qua list táº¥t cáº£ user, náº¿u user nÃ o Ä‘c check lÃ  is CC thÃ¬ add vÃ o listCC
					for (User user : lstUser) {
						if(user.getCcmailstring()){
							lstCC.add(user.getEmail());
						}
					}
					
					//bá»� Ä‘i email cá»§a current user
					lstCC.remove(userSer.findById(LoginController.currentUser).getEmail());
					
					//láº¥y cc tá»« view
					String[] listCCfromView = null;//CC.split(";");
					if (CC.contains(";")) {
						listCCfromView = CC.split(";");
					} else if (CC.contains(",")) {
						listCCfromView = CC.split(",");
					} else {
						listCCfromView = CC.split(" ");
					}
					for (String string : listCCfromView) {
//						System.err.println(string.trim());
//						System.out.println(OrderInternalAccessoryController.isValidEmailAddress(string.trim()));
						//náº¿u lÃ  valid email thÃ¬ cho vÃ´ list
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
//					messageHelper.setCc(arg0);

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

					orderinternalaccessory.setStatus("Ordered");
					service.update(orderinternalaccessory);
					
				}
			});

			
//			return "configuration/sendmaildemo/successsendmail";
			ModelAndView mv = new ModelAndView("redirect:/listorderinternalaccessory");
			mv.addObject("sendMailStatus", "Success");
			return mv;
		}catch(Exception e){
			ModelAndView mv = new ModelAndView("redirect:/listorderinternalaccessory");
			mv.addObject("sendMailStatus", "Failed");
			return mv;
		}
	}
	
	/**
	 * This function is used to cancel order internal accessory
	 * @param orderInternalAccessoryModel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orderinternalaccessory/cancelOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> cancelOrderInternalAccessory(
			@RequestBody OrderInternalAccessoryModel orderInternalAccessoryModel) {
		log.info(String
				.format("cancelOrderInternalAccessory with param 'orderInternalAccessoryModel' in class: %s",
						getClass()));
		try {
			log.debug("cancelOrderInternalAccessory and return edit width as json format");
			Map<String, Object> result = new HashMap<String, Object>();
			
			//Láº¥y user hiá»‡n táº¡i (dÃ¹ng Ä‘á»ƒ add cÃ¡c detail náº¿u cÃ³)
			String username = LoginController.currentUser;
			//tiáº¿n hÃ nh update
			result.put("cancelStstus", service
					.cancelOrderInternalAccessory(
							orderInternalAccessoryModel,username));
			result.put("status", "ok");
			
			log.debug("saveOrderInternalAccessory successfully");
			lstOrderInternalAccessoryDetailModelEditVer.clear();
			return result;
		} catch (Exception e) {
			log.error(String
					.format("cancelOrderInternalAccessory with param 'orderInternalAccessoryModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	@ResponseBody
	@RequestMapping(value = "/sendemailOrderGeneralAccessory", method = RequestMethod.GET)
	public ModelAndView getPageSendEmail(ModelMap model, HttpServletResponse response) {
		log.info(String.format("getPageSendEmail with param 'response' in class: %s", getClass()));
		try {
			log.debug("return PageSendEmail view for request");
			response.setContentType("text/html");
			
			return new ModelAndView("operation/orderinternalaccessory/sendMail");
		} catch (Exception e) {
			log.error(String.format("getPageSendEmail with param 'response' in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}

	}
}
