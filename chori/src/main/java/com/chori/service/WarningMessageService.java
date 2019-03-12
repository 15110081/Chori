package com.chori.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chori.dao.AccessoryDao;
import com.chori.dao.EstimatetimeDao;
import com.chori.dao.OrderExternalAccessoryDao;
import com.chori.dao.PIDao;
import com.chori.entity.Estimatetime;
import com.chori.entity.Orderexternalaccessory;
import com.chori.entity.Pi;
import com.chori.model.OrderExternalAccessoryModel;
import com.chori.model.PiModel;

@Repository("warningmessageService")
public class WarningMessageService {
	
	@Autowired
	private PIDao piDao;
	
	@Autowired
	private EstimatetimeDao estimatetimeDao;
	
	@Autowired
	private OrderExternalAccessoryDao orderExternalAccessoryDao;
	
	@Autowired
	private AccessoryDao accessoryDao;
	
	protected final Log log = LogFactory.getLog(getClass());
	
	//pi estimate time of completion
	private Integer PIETC;
	//packing accessory estimate time of completion
	private Integer PAETC;
	//manufacturing accessory estimate time of completion
	private Integer MAETC;
	
	//get pi completion date
	public void getEstimateTimeOfCompletion() {
		log.info(String.format(
				"getPICompletionDate with param 'unitcode' in class: %s",
				getClass()));
		try {
			List<Estimatetime> lstEstTime = estimatetimeDao.getAll();
			for(Estimatetime esttime : lstEstTime)
			{
				PIETC = esttime.getPiconpletion();
				PAETC = esttime.getPackingaccdelv();
				MAETC = esttime.getManuaccdelv();
			}
			log.debug("getPICompletionDate successfully");
		} catch (Exception e) {
			log.error(String
					.format("getPICompletionDate with param 'unitCode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
//	//get pi completion date
//	public Integer getPICompletionDate() {
//		log.info(String.format(
//				"getPICompletionDate with param 'unitcode' in class: %s",
//				getClass()));
//		try {
//			List<Estimatetime> lstEstTime = estimatetimeDao.getAll();
//			for(Estimatetime esttime : lstEstTime)
//			{
//				return esttime.getPiconpletion();
//			}
//			log.debug("getPICompletionDate successfully");
//			return null;
//		} catch (Exception e) {
//			log.error(String
//					.format("getPICompletionDate with param 'unitCode' in class: %s has error: %s",
//							getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	
	//compare date when adding picompletiondate, if ETOC (EstimateTimeOfCompletion) + current date after or equals date(param) then true, else false
	public boolean compareDateWithETC(Date date, Integer etoc)
	{
		try {
			//convert date to calendar
			Calendar calendar1 = Calendar.getInstance(); 
			calendar1.setTime(date);
			Calendar calendarCurrentDatePlusETC = Calendar.getInstance(); 
			
			//date2 + PICD
			calendarCurrentDatePlusETC.add(Calendar.DATE, etoc);
			
			//calendar1 > currendate then...
			//..compareTo <0 then calendar1 before calendarCurrentDate, >0 then calendar1 after calendarCurrentDate
			if(calendar1.compareTo(Calendar.getInstance())>=0
					&& calendarCurrentDatePlusETC.compareTo(calendar1)>=0)
			{
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error(String
					.format("compareDate with param 'unitCode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}	
	}
	
	//get pi if its shipping date - current date > estimate of completion date
	public List<PiModel> getListPIForWarningMessage()
	{
 		log.info(String.format("getListPI in class: %s", getClass()));
		try{
			
			//if colorcode not existed then add to list
			List<Pi> lstPi = piDao.getAll();
			List<PiModel> lstPiModel = new ArrayList<PiModel>();
			PiModel piModel;
			
			//to get PIETC
			getEstimateTimeOfCompletion();
			
			//loop list
			for(Pi pi : lstPi)
			{
				if(compareDateWithETC(pi.getPiestshipdate(),PIETC)==true)
				{
					piModel = new PiModel();
					piModel.setLotnumber(pi.getLotnumber());
					piModel.setCustomer1(pi.getCustomerByCustomer1code().getCustomercode());
					piModel.setPiestshipdate(pi.getPiestshipdate());
					
					lstPiModel.add(piModel);
				}
			}
			return lstPiModel;
		} catch (Exception e) {
			log.error(String.format("getListPI  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("getListPI into database fail, class ImportPIFileService");
			throw e;
		}	
	}
	
	//get order external accessory if its accessory mode == packing &&  est delv date - current date > estimate of completion date
	public List<OrderExternalAccessoryModel> getPackingAccInListOrderExternalAccessoriesForWarningMessage()
	{
 		log.info(String.format("getPackingAccInListOrderExternalAccessoriesForWarningMessage in class: %s", getClass()));
		try{
			//if colorcode not existed then add to list
			List<Orderexternalaccessory> lstOrderexternalaccessory = orderExternalAccessoryDao.getAll();
			List<OrderExternalAccessoryModel> lstOrderExternalAccessoryModel = new ArrayList<OrderExternalAccessoryModel>();
			OrderExternalAccessoryModel orderExternalAccessoryModel;
			
			//to get PAETC
			getEstimateTimeOfCompletion();
			
			//loop list
			for(Orderexternalaccessory orderexternalaccessory : lstOrderexternalaccessory)
			{
				//checking condition
				if(accessoryDao.findById(orderexternalaccessory.getAccessory().getAccessorycode()).getMode().equals("Packing")
						&& compareDateWithETC(orderexternalaccessory.getEstimatedelvdate(),PAETC)==true)
				{
					orderExternalAccessoryModel = new OrderExternalAccessoryModel();
					orderExternalAccessoryModel.setOrderSheetNo(orderexternalaccessory.getOrdersheetno());
					orderExternalAccessoryModel.setAccessoryCode(orderexternalaccessory.getAccessory().getAccessorycode());
					orderExternalAccessoryModel.setFactoryCode(orderexternalaccessory.getFactory().getFactorycode());
					orderExternalAccessoryModel.setOrderDate(orderexternalaccessory.getOrderdate());
					orderExternalAccessoryModel.setOrderQuantity(orderexternalaccessory.getOrderquantity());
					orderExternalAccessoryModel.setEstimatedevlDate(orderexternalaccessory.getEstimatedelvdate());
					
					lstOrderExternalAccessoryModel.add(orderExternalAccessoryModel);
				}
			}
			return lstOrderExternalAccessoryModel;
		} catch (Exception e) {
			log.error(String.format("getPackingAccInListOrderExternalAccessoriesForWarningMessage  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("getPackingAccInListOrderExternalAccessoriesForWarningMessage into database fail, class ImportPIFileService");
			throw e;
		}	
	}
	
	//get order external accessory if its accessory mode == Manufacturing &&  est delv date - current date > estimate of completion date
	public List<OrderExternalAccessoryModel> getManufacturingAccInListOrderExternalAccessoriesForWarningMessage()
	{
 		log.info(String.format("getManufacturingAccInListOrderExternalAccessoriesForWarningMessage in class: %s", getClass()));
		try{
			//if colorcode not existed then add to list
			List<Orderexternalaccessory> lstOrderexternalaccessory = orderExternalAccessoryDao.getAll();
			List<OrderExternalAccessoryModel> lstOrderExternalAccessoryModel = new ArrayList<OrderExternalAccessoryModel>();
			OrderExternalAccessoryModel orderExternalAccessoryModel;
			
			//to get PAETC
			getEstimateTimeOfCompletion();
			
			//loop list
			for(Orderexternalaccessory orderexternalaccessory : lstOrderexternalaccessory)
			{
				//checking condition
				if(accessoryDao.findById(orderexternalaccessory.getAccessory().getAccessorycode()).getMode().equals("Manufacturing")
						&& compareDateWithETC(orderexternalaccessory.getEstimatedelvdate(),MAETC)==true)
				{
					orderExternalAccessoryModel = new OrderExternalAccessoryModel();
					orderExternalAccessoryModel.setOrderSheetNo(orderexternalaccessory.getOrdersheetno());
					orderExternalAccessoryModel.setAccessoryCode(orderexternalaccessory.getAccessory().getAccessorycode());
					orderExternalAccessoryModel.setFactoryCode(orderexternalaccessory.getFactory().getFactorycode());
					orderExternalAccessoryModel.setOrderDate(orderexternalaccessory.getOrderdate());
					orderExternalAccessoryModel.setOrderQuantity(orderexternalaccessory.getOrderquantity());
					orderExternalAccessoryModel.setEstimatedevlDate(orderexternalaccessory.getEstimatedelvdate());
					
					lstOrderExternalAccessoryModel.add(orderExternalAccessoryModel);
				}
			}
			return lstOrderExternalAccessoryModel;
		} catch (Exception e) {
			log.error(String.format("getManufacturingAccInListOrderExternalAccessoriesForWarningMessage  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("getManufacturingAccInListOrderExternalAccessoriesForWarningMessage into database fail, class ImportPIFileService");
			throw e;
		}	
	}
}
