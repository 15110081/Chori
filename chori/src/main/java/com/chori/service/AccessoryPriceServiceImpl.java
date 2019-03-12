package com.chori.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.AccessoryDao;
import com.chori.dao.AccessoryPriceDao;
import com.chori.dao.AccessorySupplierDao;
import com.chori.dao.CurrencyDao;
import com.chori.dao.UnitDao;
import com.chori.dao.UserDao;
import com.chori.entity.Accessoryprice;
import com.chori.model.AccessoryPriceModel;

@Repository("accessorypriceService")
public class AccessoryPriceServiceImpl extends
		AbstractServiceImpl<Accessoryprice, Integer> implements
		AccessoryPriceService {

	private AccessoryPriceDao dao;

	@Autowired
	private UnitDao unitDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private AccessoryDao accessoryDao;

	@Autowired
	private AccessorySupplierDao accessorysupplierDao;

	@Autowired
	private CurrencyDao currencyDao;

	@Autowired
	public AccessoryPriceServiceImpl(
			@Qualifier("accessorypriceDao") AbstractDao<Accessoryprice, Integer> abstractDao) {
		super(abstractDao);
		this.dao = (AccessoryPriceDao) abstractDao;
	}

	/**
	 * This function is used get all AccessoryPrice
	 * 
	 * @return list<AccessorypriceModel>
	 */
	public List<AccessoryPriceModel> getAllAccessoryPrice() {
		log.info(String.format("getAllAccessoryPrice in class: %s", getClass()));
		try {
			List<Accessoryprice> lstAccessoryPrice = dao.getAll();
			AccessoryPriceModel accessorypriceMo;
			List<AccessoryPriceModel> lstaccessorypriceModel = new ArrayList<AccessoryPriceModel>();
			for (Accessoryprice acc : lstAccessoryPrice) {

				accessorypriceMo = new AccessoryPriceModel();

				accessorypriceMo.setAccessorypricecode(acc
						.getAccessorypricecode());
				accessorypriceMo.setAccessorycode(acc.getAccessory()
						.getAccessorycode());
				accessorypriceMo.setName(acc.getAccessory().getName());
				accessorypriceMo.setMode(acc.getAccessory().getMode());
				accessorypriceMo.setUnitcode(acc.getAccessory()
						.getUnitByUnitcode().getUnitcode());
				accessorypriceMo.setAccessorysuppliercode(acc
						.getAccessorysupplier().getAccsuppliercode());
				accessorypriceMo.setAccessorysuppliername(acc
						.getAccessorysupplier().getShortname());
				accessorypriceMo.setUnitpriceperunit(acc.getUnitpriceperunit() == null ? 0 : acc.getUnitpriceperunit());
				accessorypriceMo.setCurrencycode(acc.getCurrency()
						.getCurrencycode());
				accessorypriceMo.setFromdate(acc.getFromdate());

				accessorypriceMo.setTodate(acc.getTodate() == null ? null : acc
						.getTodate());
				accessorypriceMo.setCreator(acc.getUser() == null ? "" : acc
						.getUser().getUsername());
				accessorypriceMo.setCreatedate(acc.getCreatedate());
				accessorypriceMo.setStatus();
				accessorypriceMo.setAccessorygroupcode(acc.getAccessory()
						.getAccessorygroup().getAccessorygroupcode());
				accessorypriceMo.setRemark(acc.getRemark() == null ? "" : acc
						.getRemark());
				lstaccessorypriceModel.add(accessorypriceMo);
			}
			
			log.debug("getAllAccessoryPrice successful");
			return lstaccessorypriceModel;
		} catch (Exception e) {
			log.error(String.format(
					"getAllAccessoryPrice in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new AccessoryPrice into database
	 * 
	 * @param userId
	 * @return true if add successfully, false if have exception
	 */
	public boolean addAccessoryPrice(AccessoryPriceModel accessorypriceMo,
			String userName) {
		log.info(String.format("addAccessoryPrice in class: %s", getClass()));
		try {
			//validate whenall foreign key is existed
			if(validateAccessoryPriceForeignKey(accessorypriceMo.getAccessorycode()
					, accessorypriceMo.getAccessorysuppliercode()
					, accessorypriceMo.getCurrencycode()) == true)
			{
				//allow to add new accessory price
				Accessoryprice acc = new Accessoryprice();

				acc.setAccessorypricecode(accessorypriceMo.getAccessorypricecode());
				acc.setAccessory(accessoryDao.findById(accessorypriceMo
						.getAccessorycode()));
				acc.setAccessorysupplier(accessorysupplierDao
						.findById(accessorypriceMo.getAccessorysuppliercode()));
				acc.setFromdate(accessorypriceMo.getFromdate());
				acc.setTodate(accessorypriceMo.getTodate() == null ? null
						: accessorypriceMo.getTodate());
				acc.setUnitpriceperunit(accessorypriceMo.getUnitpriceperunit());
				acc.setCurrency(currencyDao.findById(accessorypriceMo
						.getCurrencycode()));
				acc.setUser(userDao.findById(userName));
				acc.setRemark(accessorypriceMo.getRemark());
				acc.setCreatedate(Calendar.getInstance().getTime());

				List<AccessoryPriceModel> lsAcc = getAllAccessoryPrice();
				if (acc.getTodate() == null) {
					for (AccessoryPriceModel accessoryPriceModel : lsAcc) {
						if (addWhenAccessoryPriceToDateIsNull(accessoryPriceModel,
								acc)
								&& checkAccCodeAndAccSupplierCodeEqual(
										accessoryPriceModel, acc)) {

							if (accessoryPriceModel.getTodate() == null
									&& acc.getFromdate().after(
											accessoryPriceModel.getFromdate())) {
								accessoryPriceModel.setTodate(new DateTime(acc
										.getFromdate()).minusDays(1).toDate());
								editAccessoryPrice(accessoryPriceModel);
								dao.save(acc);
								return true;
							}
							
							
						} else if (!addWhenAccessoryPriceToDateIsNull(
								accessoryPriceModel, acc)
								&& checkAccCodeAndAccSupplierCodeEqual(
										accessoryPriceModel, acc)) {
							return false;
						}
					}
				} else {
					for (AccessoryPriceModel accessoryPriceModel : lsAcc) {
						if (addWhenAccessoryPriceToDateIsNotNull(
								accessoryPriceModel, acc)
								&& checkAccCodeAndAccSupplierCodeEqual(
										accessoryPriceModel, acc)) {

							if (accessoryPriceModel.getTodate() == null
									&& acc.getFromdate().after(
											accessoryPriceModel.getFromdate())) {
								accessoryPriceModel.setTodate(new DateTime(acc
										.getFromdate()).minusDays(1).toDate());
								editAccessoryPrice(accessoryPriceModel);
								dao.save(acc);
								return true;
							}
							
						} else if (!addWhenAccessoryPriceToDateIsNotNull(
								accessoryPriceModel, acc)
								&& checkAccCodeAndAccSupplierCodeEqual(
										accessoryPriceModel, acc)) {
							return false;
						}
					}
				}
				dao.save(acc);
				log.debug("add new accessory price into database successfully");
				return true;
			}
			return false;
		} catch (Exception e) {
			log.debug("add new accessory price  into database fail");
			System.err
					.println("add new accessory price  into database fail, method book(), class AccessorypriceService");
			System.err.println(e.toString());
			return false;
		}
	}

	/**
	 * This function find 1 accessorypriceMo by accessorypriceCode
	 * 
	 * @param accessorypriceCode
	 * @return
	 */
	public AccessoryPriceModel findAccessoryPriceModelById(
			Integer accessorypriceCode) {
		log.info(String
				.format("findAccessoryPriceModelById with param 'accessorypriceCode' in class: %s",
						getClass()));
		try {
			AccessoryPriceModel accessorypriceMo = new AccessoryPriceModel();
			Accessoryprice acc = dao.findById(accessorypriceCode);

			accessorypriceMo.setAccessorypricecode(acc.getAccessorypricecode());
			accessorypriceMo.setAccessorycode(acc.getAccessory()
					.getAccessorycode());
			accessorypriceMo.setName(acc.getAccessory().getName());
			accessorypriceMo.setMode(acc.getAccessory().getMode());
			accessorypriceMo.setUnitcode(acc.getAccessory().getUnitByUnitcode()
					.getUnitcode());
			accessorypriceMo.setAccessorysuppliercode(acc
					.getAccessorysupplier().getAccsuppliercode());
			accessorypriceMo.setAccessorysuppliername(acc
					.getAccessorysupplier().getShortname());
			accessorypriceMo.setUnitpriceperunit(acc.getUnitpriceperunit() == null ? 0 : acc.getUnitpriceperunit());
			accessorypriceMo.setCurrencycode(acc.getCurrency()
					.getCurrencycode());
			accessorypriceMo.setFromdate(acc.getFromdate());
			accessorypriceMo.setTodate(acc.getTodate() == null ? null : acc
					.getTodate());
			accessorypriceMo.setCreator(acc.getUser().getUsername());
			accessorypriceMo.setCreatedate(acc.getCreatedate());
			accessorypriceMo.setRemark(acc.getRemark());
			log.debug("findAccessoryPriceModelById successfully");
			return accessorypriceMo;
		} catch (Exception e) {
			log.error(String
					.format("findAccessoryPriceModelById with param 'accessorypriceCode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to update an accessoryprice
	 * 
	 * @param accessorypriceMo
	 * @param userId
	 * @return
	 */

	public boolean editAccessoryPrice(AccessoryPriceModel accessorypriceMo) {
		log.info(String
				.format("editAccessoryPrice with param 'accessorypriceMo' in class: %s",
						getClass()));
		try {
			if(validateAccessoryPriceForeignKey(accessorypriceMo.getAccessorycode()
					, accessorypriceMo.getAccessorysuppliercode()
					, accessorypriceMo.getCurrencycode()) == true)
			{

				Accessoryprice acc = dao.findById(accessorypriceMo
						.getAccessorypricecode());
				acc.setAccessorypricecode(accessorypriceMo.getAccessorypricecode());
				acc.setAccessory(accessoryDao.findById(accessorypriceMo
						.getAccessorycode()));
				acc.setAccessorysupplier(accessorysupplierDao
						.findById(accessorypriceMo.getAccessorysuppliercode()));
				acc.setFromdate(accessorypriceMo.getFromdate());
				acc.setTodate(accessorypriceMo.getTodate() == null ? null
						: accessorypriceMo.getTodate());
				acc.setUnitpriceperunit(accessorypriceMo.getUnitpriceperunit());
				acc.setCurrency(currencyDao.findById(accessorypriceMo
						.getCurrencycode()));
				acc.setRemark(accessorypriceMo.getRemark());
	
				dao.update(acc);
				log.debug("editAccessoryPrice successfully");
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error(String
					.format("editAccessoryPrice with param 'accessorypriceMo' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("editAccessoryPrice with param 'accessorypriceMo' in class: %s has error: %s",
									getClass(), e.getMessage()));
			return false;
		}
	}

	

	/**
	 * This function is used to delete a Accessory price in database.
	 * 
	 * @param accessorypriceCode
	 * @return true if delete successfully, false if cant
	 */
	public boolean deleteAccessoryprice(int accessorypriceCode) {
		log.info(String.format("deleteAccessoryprice with param 'accessorypriceCode' in class: %s",
				getClass()));
		Accessoryprice acc = dao.findById(accessorypriceCode);
		try {
			dao.delete(acc);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<AccessoryPriceModel> getListAccessorypriceModelByAccessoryCodeandAccessorySuplierandOrderDate(
			String accessoryCode, String accessorySuplier, Date orderDate) {
		log.info(String
				.format("getListAccessorypriceModelByAccessoryCodeandAccessorySuplierandOrderDate in class: %s",
						getClass()));
		try {
			List<Accessoryprice> accessoryprices = dao
					.getListAccessorypriceByAccessoryCodeandAccessorySuplierandOrderDate(
							accessoryCode, accessorySuplier);
			AccessoryPriceModel accessoryPriceModel;
			List<AccessoryPriceModel> lstAccessoryPriceModels = new ArrayList<AccessoryPriceModel>();
			for (Accessoryprice accessoryprice : accessoryprices) {

				if (orderDate.compareTo(accessoryprice.getFromdate()) >= 0
						&& orderDate.compareTo(accessoryprice.getTodate()) <= 0) {
					accessoryPriceModel = new AccessoryPriceModel();
					accessoryPriceModel.setAccessorypricecode(accessoryprice
							.getAccessorypricecode());
					accessoryPriceModel.setAccessorycode(accessoryprice
							.getAccessory().getAccessorycode());
					accessoryPriceModel.setAccessorysuppliercode(accessoryprice
							.getAccessorysupplier().getAccsuppliercode());
					accessoryPriceModel.setCurrencycode(accessoryprice
							.getCurrency().getCurrencycode());
					accessoryPriceModel.setUnitpriceperunit(accessoryprice
							.getUnitpriceperunit());
					lstAccessoryPriceModels.add(accessoryPriceModel);
				}
			}
			log.debug("getListGarmentstyleaccessorydetailByGarmentStyleName successful");
			return lstAccessoryPriceModels;
		} catch (Exception e) {
			log.error(String
					.format("getListAccessorypriceModelByAccessoryCodeandAccessorySuplierandOrderDate in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * 
	 * @param accessoryCode
	 * @param accessorySuplier
	 * @param orderDate
	 * @return
	 */
	public List<AccessoryPriceModel> getListAccessorypriceModelByAccessoryCodeandAccessorySuplierandOrderDate2ndCase(
			String accessoryCode, String accessorySuplier, Date orderDate) {
		log.info(String
				.format("getListAccessorypriceModelByAccessoryCodeandAccessorySuplierandOrderDate in class: %s",
						getClass()));
		try {
			List<Accessoryprice> accessoryprices = dao
					.getListAccessorypriceByAccessoryCodeandAccessorySuplierandOrderDate(
							accessoryCode, accessorySuplier);
			AccessoryPriceModel accessoryPriceModel;
			List<AccessoryPriceModel> lstAccessoryPriceModels = new ArrayList<AccessoryPriceModel>();
			for (Accessoryprice accessoryprice : accessoryprices) {
				
				if(accessoryprice.getTodate()!=null){
					if ((orderDate.compareTo(accessoryprice.getFromdate()) >= 0 && orderDate
							.compareTo(accessoryprice.getTodate()) <= 0)) {
						accessoryPriceModel = new AccessoryPriceModel();
						accessoryPriceModel.setAccessorypricecode(accessoryprice
								.getAccessorypricecode());
						accessoryPriceModel.setAccessorycode(accessoryprice
								.getAccessory().getAccessorycode());
						accessoryPriceModel.setAccessorysuppliercode(accessoryprice
								.getAccessorysupplier().getAccsuppliercode());
						accessoryPriceModel.setCurrencycode(accessoryprice
								.getCurrency().getCurrencycode());
						accessoryPriceModel.setUnitpriceperunit(accessoryprice
								.getUnitpriceperunit());
						lstAccessoryPriceModels.add(accessoryPriceModel);
					}
				}else if(accessoryprice.getTodate()==null){
					if ((orderDate.compareTo(accessoryprice.getFromdate()) >= 0)) {
						accessoryPriceModel = new AccessoryPriceModel();
						accessoryPriceModel.setAccessorypricecode(accessoryprice
								.getAccessorypricecode());
						accessoryPriceModel.setAccessorycode(accessoryprice
								.getAccessory().getAccessorycode());
						accessoryPriceModel.setAccessorysuppliercode(accessoryprice
								.getAccessorysupplier().getAccsuppliercode());
						accessoryPriceModel.setCurrencycode(accessoryprice
								.getCurrency().getCurrencycode());
						accessoryPriceModel.setUnitpriceperunit(accessoryprice
								.getUnitpriceperunit());
						lstAccessoryPriceModels.add(accessoryPriceModel);
					}
				}
			}
			log.debug("getListGarmentstyleaccessorydetailByGarmentStyleName successful");
			return lstAccessoryPriceModels;
		} catch (Exception e) {
			log.error(String
					.format("getListAccessorypriceModelByAccessoryCodeandAccessorySuplierandOrderDate in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is condition add new AccessoryPrice into database when
	 * todate input = null
	 * 
	 * @param userId
	 * @return false if have exception
	 */
	public boolean addWhenAccessoryPriceToDateIsNull(
			AccessoryPriceModel accessoryPriceModel,
			Accessoryprice accessorypriceMo) {
		// if 1 : fromdate database = fromdate input
		if ((accessoryPriceModel.getFromdate().compareTo(
				accessorypriceMo.getFromdate()) == 0)) {
			return false;
		}
		
		// if 2 : todate database = null and fromdate input < fromdate database
		if (accessoryPriceModel.getTodate() == null
				&& accessorypriceMo.getFromdate().before(
						accessoryPriceModel.getFromdate())) {
			return false;
		}
		
		// if 3 : todate database != null
		if (accessoryPriceModel.getTodate() != null) {
			// if 3.1 : todate database > fromdate input > fromdate database
			if (accessorypriceMo.getFromdate().after(
					accessoryPriceModel.getFromdate())
					&& accessorypriceMo.getFromdate().before(
							accessoryPriceModel.getTodate())) {
				return false;
			}
			if ((accessoryPriceModel.getTodate().compareTo(
					accessorypriceMo.getFromdate()) == 0)) {
				return false;
			}
		}
		// if 4 : fromdate input < fromdate database
		if (accessorypriceMo.getFromdate().before(
				accessoryPriceModel.getFromdate())) {
			return false;
		}
		
	
		return true;

	}

	/**
	 * This function is condition add new AccessoryPrice into database when
	 * todate input != null
	 * 
	 * @param userId
	 * @return false if have exception
	 */
	public boolean addWhenAccessoryPriceToDateIsNotNull(
			AccessoryPriceModel accessoryPriceModel,
			Accessoryprice accessorypriceMo) {
		if (accessoryPriceModel.getTodate() == null
				&& accessorypriceMo.getFromdate().before(
						accessoryPriceModel.getFromdate())) {
			return false;
		}
		// if 2 : fromdate input < fromdate database and todate database <
		// fromdate database
		if (accessorypriceMo.getFromdate().before(
				accessoryPriceModel.getFromdate())
				&& accessorypriceMo.getTodate().before(
						accessoryPriceModel.getFromdate())) {
			return false;
		}
		// if 3 : fromdate database = fromdate input and todate input > todate
		// database
		if ((accessoryPriceModel.getFromdate().compareTo(
				accessorypriceMo.getFromdate()) == 0)
				&& accessorypriceMo.getTodate().after(
						accessoryPriceModel.getTodate())) {
			return false;
		}
		if ((accessoryPriceModel.getFromdate().after(
				accessorypriceMo.getFromdate())
				&& accessorypriceMo.getTodate().before(
						accessoryPriceModel.getTodate()))) {
			return false;
		}
		
		// if 4 : todate database != null
		if (accessoryPriceModel.getTodate() != null) {
			// if 4.1 fromdate input > fromdate database and todate input <
			// todate database
			if (accessorypriceMo.getFromdate().after(
					accessoryPriceModel.getFromdate())
					&& accessorypriceMo.getTodate().before(
							accessoryPriceModel.getTodate())) {
				return false;
			}
			// if 4.2 fromdate input > fromdate database and fromdate input <
			// todate database
			if (accessorypriceMo.getFromdate().after(
					accessoryPriceModel.getFromdate())
					&& accessorypriceMo.getFromdate().before(
							accessoryPriceModel.getTodate())) {
				return false;
			}
			// if 4.3 fromdate database = fromdate input and todate database =
			// todate input
			if ((accessoryPriceModel.getFromdate().compareTo(
					accessorypriceMo.getFromdate()) == 0)
					&& (accessoryPriceModel.getTodate().compareTo(
							accessorypriceMo.getTodate()) == 0)) {
				return false;
			}
			// if 4.1 fromdate input < fromdate database
			if (accessorypriceMo.getFromdate().before(
							accessoryPriceModel.getFromdate())) {
				return false;
			} 
			if (accessorypriceMo.getFromdate().compareTo(
					accessoryPriceModel.getTodate()) == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This function is condition when add AccessoryPrice accessorycode input =
	 * accessorycode database , accessorysupliercode input =
	 * accessorysupliercode database
	 * 
	 * @param userId
	 * @return false if have exception
	 */
	public boolean checkAccCodeAndAccSupplierCodeEqual(
			AccessoryPriceModel accessoryPriceModel,
			Accessoryprice accessorypriceMo) {
		if (accessoryPriceModel.getAccessorycode().equals(
				accessorypriceMo.getAccessory().getAccessorycode())
				&& accessoryPriceModel.getAccessorysuppliercode().equals(
						accessorypriceMo.getAccessorysupplier()
								.getAccsuppliercode())) {
			return true;
		}
		return false;
	}
	
	//This function is used to validate foreign key when add new accessory price (include accessory, acc supplier, currency)
	public boolean validateAccessoryPriceForeignKey(String accessoryCode,String accSupplierCode, String currencyCode)
	{		
		log.info(String
			.format("validateAccessoryPriceForeignKey in class: %s",
					getClass()));
		try{
			log.debug("validateAccessoryPriceForeignKey successful");
			if(accessoryDao.findById(accessoryCode) != null
					&& accessorysupplierDao.findById(accSupplierCode) != null
					&& currencyDao.findById(currencyCode) != null)
			{
				return true;
			}
			return false;
		}
		catch(Exception e){
			log.error(String.format("validateAccessoryPriceForeignKey in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}
}
