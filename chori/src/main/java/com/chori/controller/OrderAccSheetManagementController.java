package com.chori.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Convert;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chori.dao.OrderExternalAccessoryDao;
import com.chori.dao.OrderInternalAccessoryDao;
import com.chori.model.AccessoryModel;
import com.chori.model.AccessoryPriceModel;
import com.chori.model.OrderExternalAccessoryModel;
import com.chori.model.OrderInternalAccessoryDetailModel;
import com.chori.model.OrderInternalAccessoryModel;
import com.chori.service.AccessoryPriceService;
import com.chori.service.AccessoryService;
import com.chori.service.AccessorySupplierService;
import com.chori.service.OrderExternalAccessoryService;
import com.chori.service.OrderInternalAccessoryService;
import com.chori.service.UserService;

@Controller
@RequestMapping(value = "/")
public class OrderAccSheetManagementController {
	private static final Log log = LogFactory
			.getLog(OrderAccSheetManagementController.class);
	
	@Autowired
	OrderExternalAccessoryService orderExternalAccessoryService;
	
	@Autowired
	OrderInternalAccessoryService orderInternalAccessoryService;
	
	@Autowired
	AccessoryPriceService accessoryPriceService;
	
	@Autowired
	AccessoryService accessoryService;
	
	@Autowired
	AccessorySupplierService accessorySupplierService;

	@Autowired
	UserService userSer;
	
	@Autowired
	private OrderInternalAccessoryDao orderInternalAccessoryDao;
	
	@Autowired
	private OrderExternalAccessoryDao orderExternalAccessoryDao;
	
	/**
	 * get list order accessories sheet management
	 * 
	 * @return result
	 */
	@RequestMapping(value = "/orderaccsheetmanagement/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllOrderAccSheetManagement() {
		log.info(String.format("getAllOrderAccSheetManagement in class %s", getClass()));
		try {
			log.debug("getAllOrderAccSheetManagement and return json");
			Map<String, Object> result = new HashMap<String, Object>();			
			result.put("status", "ok");
			result.put("listExternal", orderExternalAccessoryService.getAllOrderExternalAccessoryModels());
			result.put("listInternal", orderInternalAccessoryService.getAllOrderInternalAccessoryModels());
			log.debug("getAllOrderAccSheetManagement successful");
			return result;
		} catch (Exception e) {
			log.error(String.format(
					"getAllOrderAccSheetManagement in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}

	}
	
	/**
	 * Return view page
	 * 
	 * @return ModelAndView
	 */

	@RequestMapping(value = "/listOrderaccsheetmanagement", method = RequestMethod.GET)
	public ModelAndView listOrderAccSheetManagement(HttpServletResponse response) {
		log.info(String.format(
				"listOrderAccSheetManagement with param 'response' in class: %s",
				getClass()));
		try {
			log.debug("return listOrderAccSheetManagement view for request");
			response.setContentType("text/html");
			log.debug("listOrderAccSheetManagement successful");
			return new ModelAndView(
					"operation/orderaccsheetmanagement/orderAccSheetManagementList");
		} catch (Exception e) {
			log.error(String
					.format("listOrderAccSheetManagement with param 'response' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	
	/***
	 * This method is used to get detail of a accessory order sheet management (HÃ m find by id cho edit, gá»“m find by id vÃ  láº¥y list detail)
	 * 
	 * @param orderSheetNo
	 * @return detail of a orderaccsheetmanagement in JSON format
	 */
	@RequestMapping(value = "/orderaccsheetmanagement/detail/{orderSheetNo}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getOrderAccSheetDetail(
			@PathVariable String orderSheetNo) {
		log.info(String
				.format("getOrderAccSheetDetail with param 'orderSheetNo' in class: %s",
						getClass()));
		try {
			log.debug("getting detail of a accessory order sheet management by its orderSheetNo and return json");
			Map<String, Object> result = new HashMap<String, Object>();
			//find by id quan order sheet no
			OrderInternalAccessoryModel orderInternalAccessoryModel;
			OrderExternalAccessoryModel orderExternalAccessoryModel;
			List<AccessoryPriceModel> lstCurrencyCodeAndUnitPrice;
			if(orderInternalAccessoryDao.findById(orderSheetNo) != null){
				orderInternalAccessoryModel = orderInternalAccessoryService
						.findOrderInternalAccessoryModelById(orderSheetNo);		
				List<OrderInternalAccessoryDetailModel> lstOrderInternalAccessoryDetailModel = orderInternalAccessoryService.findListOrderInternalAccessoryDetailModelByOrderSheetNo(orderSheetNo);
				for (OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel : lstOrderInternalAccessoryDetailModel) {
					lstCurrencyCodeAndUnitPrice = accessoryPriceService.getListAccessorypriceModelByAccessoryCodeandAccessorySuplierandOrderDate2ndCase(orderInternalAccessoryDetailModel.getAccessorycode(), orderInternalAccessoryModel.getAccsuplierCode(),orderInternalAccessoryModel.getOrderDate()==null?new Date():orderInternalAccessoryModel.getOrderDate());
					for (AccessoryPriceModel accessoryPriceModel : lstCurrencyCodeAndUnitPrice) {
						orderInternalAccessoryDetailModel.setCurrency(accessoryPriceModel.getCurrencycode());
						orderInternalAccessoryDetailModel.setUnitprice(accessoryPriceModel.getUnitpriceperunit().doubleValue());
					}
				}
				result.put("OrderInternalAccessory", orderInternalAccessoryModel);
				result.put("lstOrderInternalAccessoryDetailModel", lstOrderInternalAccessoryDetailModel);
				result.put("isOrderInternal", true);
			}
			else if(orderExternalAccessoryDao.findById(orderSheetNo) != null){
				orderExternalAccessoryModel = orderExternalAccessoryService
						.findOrderExternalAccessoryModelById(orderSheetNo);		
				result.put("OrderExternalAccessory", orderExternalAccessoryModel);
				result.put("isOrderInternal", false);
			}
			
			result.put("status", "ok");	
			log.debug("getOrderAccSheetDetail successful");
			return result;
		} catch (Exception e) {
			log.error(String
					.format("getOrderAccSheetDetail with param 'orderSheetNo' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
