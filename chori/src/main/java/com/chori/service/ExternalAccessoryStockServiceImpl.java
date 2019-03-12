package com.chori.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.AccessoryDao;
import com.chori.dao.ExternalAccessoryStockDao;
import com.chori.dao.FactoryDao;
import com.chori.dao.PIDao;
import com.chori.dao.PiassignexternalaccessoryDao;
import com.chori.entity.Accessory;
import com.chori.entity.Externalaccessorystock;
import com.chori.entity.Piassignexternalaccessory;
import com.chori.entity.Piassigninternalaccessories;
import com.chori.entity.Piassigninternalaccessoriesdetail;
import com.chori.model.ExternalAccessoryStockModel;
import com.chori.model.InternalAccessoriesToAssignModel;
import com.chori.model.PiassigninternalaccessoriesModel;

@Repository("externalAccessoryStockService")
public class ExternalAccessoryStockServiceImpl extends
		AbstractServiceImpl<Externalaccessorystock, String> implements
		ExternalAccessoryStockService {
	private ExternalAccessoryStockDao dao;

	@Autowired
	PiassignexternalaccessoryDao piassignexternalaccessoryDao;
	
	@Autowired
	AccessoryDao accessoryDao;
	
	@Autowired
	FactoryDao factoryDao;
	
	@Autowired
	PIDao pIDao;
	
	@Autowired
	public ExternalAccessoryStockServiceImpl(
			@Qualifier("externalAccessoryStockDao") AbstractDao<Externalaccessorystock, String> abstractDao) {
		super(abstractDao);
		this.dao = (ExternalAccessoryStockDao) abstractDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chori.service.ExternalaccessoryStockService#
	 * findExternalaccessorystockModelByOrdersheetno(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chori.service.ExternalAccessoryStockService#
	 * findExternalaccessorystockModelByOrdersheetno(java.lang.String)
	 */

	public boolean isExistExternalaccessorystockModelByAccessory(
			String accessoryCode) {
		log.info(String
				.format("findExternalaccessorystockModelById with param 'externalaccessorystockCode' in class: %s",
						getClass()));
		try {
			List<Externalaccessorystock> lstExternalaccessorystock = dao
					.getAll();
			for (Externalaccessorystock est : lstExternalaccessorystock) {
				if (((est.getOrderexternalaccessory()!=null && est.getOrderexternalaccessory().getAccessory()
						.getAccessorycode().equals(accessoryCode))
						|| (est.getAccessory()!=null && est.getAccessory().getAccessorycode().equals(accessoryCode)))
						&& est.getAvailableqty() > 0) {
					return true;
				}
			}

			log.debug("isExistExternalaccessorystockModelByAccessory successfully");
			return false;
		} catch (Exception e) {
			log.error(String
					.format("isExistExternalaccessorystockModelByAccessory with param 'externalaccessorystockCode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	@Override
	public float getinventoryquantity(String accessoryCode){
		return dao.getInventoryQuantity(accessoryCode);
	}
	
	@Override
	public float getEstimateQuantity(String lotNumber,String accessoryCode){
		return dao.getEstimateQuantity(lotNumber, accessoryCode);
	}
	
	@Override
	public float getActualAssignQuantity(String lotNumber,String accessoryCode){
		return dao.getActualAssignQuantity(lotNumber, accessoryCode);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chori.service.ExternalaccessoryStockService#
	 * isExternalaccessorystockExistedById(java.lang.String)
	 */

	public boolean isExternalaccessorystockExistedById(String ordersheetno) {
		if (null == dao.findByordersheetno(ordersheetno))
			return false;
		return true;
	}
	
	@Override
	public ExternalAccessoryStockModel getStockAvailableQuantityInformation(String accessoryCode,String factoryCode,String lotNumber){
		log.info(String.format("getStockAvailableQuantityInformation in class: %s",
				getClass()));
		try {
			log.debug("getStockAvailableQuantityInformation successful");
			ExternalAccessoryStockModel externalAccessoryStockModel = new ExternalAccessoryStockModel();
			externalAccessoryStockModel.setAccessoryname(accessoryCode);
			externalAccessoryStockModel.setAvailableqtyfromstock(getAvailableqtyfromstock(accessoryCode,factoryCode));
			externalAccessoryStockModel.setStockassignqty(getStockassignqty(accessoryCode,lotNumber));
			externalAccessoryStockModel.setActualassignqty(getActualAssignQuantity(lotNumber,accessoryCode));
			externalAccessoryStockModel.setShortageqty(calculateShortageQuantity(lotNumber,accessoryCode));
			return externalAccessoryStockModel;
		} catch (Exception e) {
			log.error(String.format(
					"getStockAvailableQuantityInformation in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	@Override
	public Float calculateShortageQuantity(String lotNumber, String accessoryCode)
	{
		Float shortageQty = (float) 0;
		Float actualAssignQty =	getActualAssignQuantity(lotNumber,accessoryCode);
		Float stockAssignQty = getStockassignqty(accessoryCode,lotNumber);
		Float orderQty = getOrderqty(accessoryCode,lotNumber);
		shortageQty = actualAssignQty - stockAssignQty - orderQty;
		if(shortageQty>0)
			return shortageQty;
		else
			return (float) 0;
	}
	
	public Double getAvailableqtyfromstock(String accessoryCode, String factoryCode)
	{
		Double availableqtyfromstock = (double) 0;
		List<Externalaccessorystock> lstExternalaccessorystock = dao.getAll();
		for(Externalaccessorystock externalaccessorystock : lstExternalaccessorystock){
				if(((externalaccessorystock.getOrderexternalaccessory()!=null 
						&&externalaccessorystock.getOrderexternalaccessory().getAccessory().getAccessorycode().equals(accessoryCode)
						&& externalaccessorystock.getOrderexternalaccessory().getFactory().getFactorycode().equals(factoryCode))
						|| (externalaccessorystock.getAccessory()!=null 
						&& externalaccessorystock.getAccessory().getAccessorycode().equals(accessoryCode)
						&& externalaccessorystock.getFactory().getFactorycode().equals(factoryCode)))
						)
				{
					availableqtyfromstock += externalaccessorystock.getAvailableqty();
				}
		}
		return availableqtyfromstock;
	}
	
	public Float getStockassignqty(String accessoryCode, String lotNumber)
	{
		List<Piassignexternalaccessory> lstPiassignexternalaccessory = piassignexternalaccessoryDao.getAll();
		for(Piassignexternalaccessory piassignexternalaccessory : lstPiassignexternalaccessory){
			if(piassignexternalaccessory.getAccessory().getAccessorycode().equals(accessoryCode)
					&& piassignexternalaccessory.getPi().getLotnumber().equals(lotNumber))
			{
				return piassignexternalaccessory.getStockassignqty()==null?0:piassignexternalaccessory.getStockassignqty();
			}
		}
		return (float) 0;
	}
	
	//get orderQty of a piassignexternalaccessory
	public Float getOrderqty(String accessoryCode, String lotNumber)
	{
		List<Piassignexternalaccessory> lstPiassignexternalaccessory = piassignexternalaccessoryDao.getAll();
		for(Piassignexternalaccessory piassignexternalaccessory : lstPiassignexternalaccessory){
			if(piassignexternalaccessory.getAccessory().getAccessorycode().equals(accessoryCode)
					&& piassignexternalaccessory.getPi().getLotnumber().equals(lotNumber))
			{
				return piassignexternalaccessory.getOrderqty()==null?0:piassignexternalaccessory.getOrderqty();
			}
		}
		return (float) 0;
	}
	
	//calculate qty when assign from stock
	@Override
	public Float saveAssignFromStock(String accessoryCode, String factoryCode, Float assignQty)
	{
		log.info(String.format("saveAssignFromStock in class: %s",
				getClass()));
		try {
			List<Externalaccessorystock> lstExternalaccessorystock = dao.getAll();
			for(Externalaccessorystock externalaccessorystock : lstExternalaccessorystock){
				if(((externalaccessorystock.getOrderexternalaccessory()!=null 
						&&externalaccessorystock.getOrderexternalaccessory().getAccessory().getAccessorycode().equals(accessoryCode)
						&& externalaccessorystock.getOrderexternalaccessory().getFactory().getFactorycode().equals(factoryCode))
						|| (externalaccessorystock.getAccessory()!=null 
						&& externalaccessorystock.getAccessory().getAccessorycode().equals(accessoryCode)
						&& externalaccessorystock.getFactory().getFactorycode().equals(factoryCode)))
						) {
					if(externalaccessorystock.getAvailableqty() >= assignQty) {
						Float minorAvailableAndAssignQty = externalaccessorystock.getAvailableqty() - assignQty;				
						if(minorAvailableAndAssignQty > 0) {
							externalaccessorystock.setAvailableqty(minorAvailableAndAssignQty);
							dao.update(externalaccessorystock);
							return (float) 0;
						}
						else {
							dao.delete(externalaccessorystock);
							return (float) 0;
						}
					}
					else {
						Float minorAvailableAndAssignQty = externalaccessorystock.getAvailableqty() - assignQty;
						dao.delete(externalaccessorystock);
						assignQty = Math.abs(minorAvailableAndAssignQty);
					}
				}
			}
			return assignQty;
		} catch (Exception e) {
			log.error(String.format(
					"saveAssignFromStock in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	//for update stockAssignQty field in Piassignexternalaccessory table when assign qty from stock
	@Override
	public boolean updateStockAssignQtyOfAnAssign(String accessoryCode, String lotNumber, Float stockAssignQty)
	{
		log.info(String.format("updateStockAssignQtyOfAnAssign in class: %s",
				getClass()));
		try {
			List<Piassignexternalaccessory> lstPiassignexternalaccessory = piassignexternalaccessoryDao.getAll();
			for(Piassignexternalaccessory piassignexternalaccessory : lstPiassignexternalaccessory)
			{
				if(piassignexternalaccessory.getAccessory().getAccessorycode().equals(accessoryCode)
					&& piassignexternalaccessory.getPi().getLotnumber().equals(lotNumber))
				{
					piassignexternalaccessory.setStockassignqty(stockAssignQty);
					piassignexternalaccessoryDao.update(piassignexternalaccessory);
				}
			}
			return true;
		} catch (Exception e) {
			log.error(String.format(
					"updateStockAssignQtyOfAnAssign in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	
	//for update stock table when edit stock assign quantity
	//add new row in stock table with availableqty = stockavailableqty
	@Override
	public boolean updateStockAvailableQtyWhenEdit(String accessoryCode, String lotNumber)
	{
		log.info(String.format("updateStockAssignQtyOfAnAssign in class: %s",
				getClass()));
		try {
			Externalaccessorystock externalaccessorystock;
			List<Piassignexternalaccessory> lstPiassignexternalaccessory = piassignexternalaccessoryDao.getAll();
			for(Piassignexternalaccessory piassignexternalaccessory : lstPiassignexternalaccessory)
			{
				if(piassignexternalaccessory.getAccessory().getAccessorycode().equals(accessoryCode)
					&& piassignexternalaccessory.getPi().getLotnumber().equals(lotNumber)
					&& piassignexternalaccessory.getStockassignqty() != null
					&& piassignexternalaccessory.getStockassignqty() > 0)
				{
					externalaccessorystock = new Externalaccessorystock();
					externalaccessorystock.setAvailableqty(piassignexternalaccessory.getStockassignqty());
					externalaccessorystock.setAccessory(accessoryDao.findById(accessoryCode));
					externalaccessorystock.setFactory(factoryDao.findById(pIDao.findById(lotNumber).getFactory().getFactorycode()));
					dao.save(externalaccessorystock);
					resetStockAssignQtyToZero(accessoryCode, lotNumber);
					//reset all StockAssignQty of externalaccessorystock = 0;
					return true;
				}
			}
			return true;
		} catch (Exception e) {
			log.error(String.format(
					"updateStockAssignQtyOfAnAssign in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	public void resetStockAssignQtyToZero(String accessoryCode, String lotNumber)
	{
		log.info(String.format("resetStockAssignQtyToZero in class: %s",
				getClass()));
		try {
			List<Piassignexternalaccessory> lstPiassignexternalaccessory = piassignexternalaccessoryDao.getAll();
			for(Piassignexternalaccessory piassignexternalaccessory : lstPiassignexternalaccessory)
			{
				if(piassignexternalaccessory.getAccessory().getAccessorycode().equals(accessoryCode)
					&& piassignexternalaccessory.getPi().getLotnumber().equals(lotNumber))
				{
					piassignexternalaccessory.setStockassignqty((float) 0);
					piassignexternalaccessoryDao.update(piassignexternalaccessory);
				}
			}
		} catch (Exception e) {
			log.error(String.format(
					"resetStockAssignQtyToZero in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
}
