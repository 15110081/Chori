package com.chori.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.OrderInternalAccessoryDao;
import com.chori.dao.PiAssignInternalAccessoriesOfOrdersDao;
import com.chori.dao.PiassigninternalaccessoriesDao;
import com.chori.entity.Piassigninternalaccessories;
import com.chori.entity.Piassigninternalaccessoriesoforders;
import com.chori.model.PiAssignInternalAccessories_OrderChoseModel;

@Repository("piassigninternalaccessoriesofordersService")
public class PiassigninternalaccessoriesofordersServiceImpl  extends
			AbstractServiceImpl<Piassigninternalaccessoriesoforders, Integer> implements
			PiassigninternalaccessoriesofordersService {
	
	private PiAssignInternalAccessoriesOfOrdersDao dao;

	public PiassigninternalaccessoriesofordersServiceImpl() {
	}

	@Autowired
	public PiassigninternalaccessoriesofordersServiceImpl(
			@Qualifier("piAssignInternalAccessoriesOfOrdersDao") AbstractDao<Piassigninternalaccessoriesoforders, Integer> abstractDao) {
		super(abstractDao);
		this.dao = (PiAssignInternalAccessoriesOfOrdersDao) abstractDao;
	}
	
	
	@Autowired
	OrderInternalAccessoryDao orderInternalAccessoryDao;

	@Autowired
	PiassigninternalaccessoriesDao piassigninternalaccessoriesDao;
	
	@Autowired
	PiassigninternalaccessoriesService  piassigninternalaccessoriesService;
	
	/***
	 * Function to find Piassigninternalaccessoriesoforders by ordersheetno,piinternalaccessories
	 * @param orderSheetNo,piInternalAccessories
	 * @return null if can not find, else return ID
	 */
	public Integer findIDByOrderSheetNoAndPiInternalAccessories(String orderSheetNo, Integer piInternalAccessories) {
		log.debug("in findIDByOrderSheetNoAndPiInternalAccessories at findByOrderSheetNoAndPiInternalAccessories method");
		try {
			List<Piassigninternalaccessoriesoforders> lstPiassigninternalaccessoriesoforders = dao
					.getListPiassigninternalaccessoriesofordersByOrderSheetNoAndPiassigninternalaccessories(orderSheetNo,piInternalAccessories);
			for (Piassigninternalaccessoriesoforders piassigninternalaccessoriesoforders : lstPiassigninternalaccessoriesoforders) {
				return piassigninternalaccessoriesoforders.getPiassigninternalaccessoriesoforderscode();
			}
			return 0;
		} catch (NullPointerException ne) {
			log.error("in findIDByOrderSheetNoAndPiInternalAccessories at findByOrderSheetNoAndPiInternalAccessories method error: "
					+ ne.getMessage());
		}
		return 0;
	}
	
	/***
	 * Function to find Piassigninternalaccessoriesoforders by ordersheetno,piinternalaccessories
	 * @param orderSheetNo,piInternalAccessories
	 * @return null if can not find, else return ID
	 */
	public Boolean isExistedByOrderSheetNoAndPiInternalAccessories(String orderSheetNo, Integer piInternalAccessories) {
		log.debug("in isExistedByOrderSheetNoAndPiInternalAccessories at findByOrderSheetNoAndPiInternalAccessories method");
		try {
			if(this.findIDByOrderSheetNoAndPiInternalAccessories(orderSheetNo,piInternalAccessories) >0)
			{
				return true;
			}
		} catch (NullPointerException ne) {
			log.error("in isExistedByOrderSheetNoAndPiInternalAccessories at findByOrderSheetNoAndPiInternalAccessories method error: "
					+ ne.getMessage());
		}
		return false;
	}
	
	/**
	 * This function is used to add new PiAssignInternalAccessoriesOfOrders into database
	 * 
	 * @param PiAssignInternalAccessories_OrderChoseModel
	 *            , userName
	 * @return true if add successfully, false if have exception
	 */
	public boolean addPiAssignInternalAccessoriesOfOrders(PiAssignInternalAccessories_OrderChoseModel piAssignInternalAccessories_OrderChoseModel) {
		log.info(String.format("addPiAssignInternalAccessoriesOfOrders in class: %s", getClass()));
		try {
			Piassigninternalaccessoriesoforders piassigninternalaccessoriesoforders = new Piassigninternalaccessoriesoforders();
			piassigninternalaccessoriesoforders.setOrderassignquantity(piAssignInternalAccessories_OrderChoseModel.getAssignQty());
			piassigninternalaccessoriesoforders.setOrderinternalaccessory(orderInternalAccessoryDao.findById(piAssignInternalAccessories_OrderChoseModel.getOrderSheetNo()));
			piassigninternalaccessoriesoforders.setPiassigninternalaccessories(piassigninternalaccessoriesDao.findById(piAssignInternalAccessories_OrderChoseModel.getPiInternalAccessories()));
			dao.save(piassigninternalaccessoriesoforders);
			log.debug("add new PiAssignInternalAccessoriesOfOrders into database successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("add new PiAssignInternalAccessoriesOfOrders with param 'PiAssignInternalAccessories_OrderChoseModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println("add new PiAssignInternalAccessoriesOfOrders into database fail, method PiAssignInternalAccessories_OrderChoseModel(), class PiassigninternalaccessoriesofordersService");
			return false;
		}
	}
	
	/**
	 * This function is used to edit PiAssignInternalAccessoriesOfOrders into database
	 * 
	 * @param piAssignInternalAccessories_OrderChoseModel
	 * @return true if edit successfully, false if have exception
	 */
	public boolean editPiAssignInternalAccessoriesOfOrders(PiAssignInternalAccessories_OrderChoseModel piAssignInternalAccessories_OrderChoseModel) {
		log.info(String.format("editPiAssignInternalAccessoriesOfOrders with param 'piAssignInternalAccessories_OrderChoseModel' in class: %s",
				getClass()));
		try {
//			Integer SumOfAssignedQtyDetails = getSumOfAssignedQuantity(piAssignInternalAccessories_OrderChoseModel.getAccessorycode()
//					, piAssignInternalAccessories_OrderChoseModel.getFactoryCode());
//			Integer SumOfQuantityFromOrders = piassigninternalaccessoriesService.getSumOfAssignQuantityOfAllOrders(lotNo, piAssignInternalAccessories_OrderChoseModel.getAccessorycode())
			Integer id = findIDByOrderSheetNoAndPiInternalAccessories(piAssignInternalAccessories_OrderChoseModel.getOrderSheetNo(),piAssignInternalAccessories_OrderChoseModel.getPiInternalAccessories());
			Piassigninternalaccessoriesoforders piassigninternalaccessoriesoforders = dao.findById(id);
			piassigninternalaccessoriesoforders.setOrderassignquantity(piAssignInternalAccessories_OrderChoseModel.getAssignQty());
			dao.update(piassigninternalaccessoriesoforders);
			log.debug("editPiAssignInternalAccessoriesOfOrders successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editPiAssignInternalAccessoriesOfOrders with param 'piAssignInternalAccessories_OrderChoseModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("editPiAssignInternalAccessoriesOfOrders with param 'piAssignInternalAccessories_OrderChoseModel' in class: %s has error: %s",
									getClass(), e.getMessage()));
			return false;
		}
	}
	
	/**
	 * This function is used to delete a PiAssignInternalAccessoriesOfOrders in database.
	 * 
	 * @param sizecode
	 * @return delete successfully =>true , else => false
	 */
	public boolean deletePiAssignInternalAccessoriesByModel(PiAssignInternalAccessories_OrderChoseModel piAssignInternalAccessories_OrderChoseModel) {
		log.info(String.format("deletepiAssignInternalAccessories_OrderChoseModel with param 'piAssignInternalAccessories_OrderChoseModel' in class: %s",
				getClass()));
		try {
			Integer id = findIDByOrderSheetNoAndPiInternalAccessories(piAssignInternalAccessories_OrderChoseModel.getOrderSheetNo(),piAssignInternalAccessories_OrderChoseModel.getPiInternalAccessories());
			dao.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * This function find piassigninternalaccessoriesId by accessorycode and
	 * lotnumber
	 * @param lotnumber,accessorycode
	 * @return piassigninternalaccessoriesId
	 */
	public Integer findPiAssignInternalAccessoriesIdByAccessoryCodeAndLotNumber2(
			String lotnumber, String accessorycode) {
		log.info(String
				.format("findPiAssignInternalAccessoriesIdByAccessoryCodeAndLotNumber2 with param 'lotnumber,accessorycode' in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessories> lstPiassigninternalaccessories = piassigninternalaccessoriesDao
					.getListPiAssignInternalAccessoriesByAccessoryCodeAndLotNumber(accessorycode,lotnumber);
			for (Piassigninternalaccessories tmp : lstPiassigninternalaccessories) {
				log.debug("findPiAssignInternalAccessoriesIdByAccessoryCodeAndLotNumber2 successfully");
				return tmp.getPiinternalaccessories();
			}
			return null;
		} catch (Exception e) {
			log.error(String
					.format("findPiAssignInternalAccessoriesIdByAccessoryCodeAndLotNumber2 with param 'lotnumber,accessorycode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to save PiAssignInternalAccessoriesOfOrders into database
	 * Add new if is not existed and assign quantity >0, edit if it is existed and assign quantity >0, delete if it is existed and assign quantity < 0
	 * @param piAssignInternalAccessories_OrderChoseModel
	 * @return true if save successfully, save if have exception
	 */
	public boolean savePiAssignInternalAccessoriesOfOrders(PiAssignInternalAccessories_OrderChoseModel piAssignInternalAccessories_OrderChoseModel) {
		log.info(String.format("savePiAssignInternalAccessoriesOfOrders with param 'piAssignInternalAccessories_OrderChoseModel' in class: %s",
				getClass()));
		try {
			//Add new if is not existed
			if(isExistedByOrderSheetNoAndPiInternalAccessories(piAssignInternalAccessories_OrderChoseModel.getOrderSheetNo(),piAssignInternalAccessories_OrderChoseModel.getPiInternalAccessories()))
			{
				if(piAssignInternalAccessories_OrderChoseModel.getAssignQty()>0)
				{
					editPiAssignInternalAccessoriesOfOrders(piAssignInternalAccessories_OrderChoseModel);
				}
				else 
				{
					deletePiAssignInternalAccessoriesByModel(piAssignInternalAccessories_OrderChoseModel);
				}
			}
			//edit if it is existed
			else 
			{
				if(piAssignInternalAccessories_OrderChoseModel.getAssignQty()>0)
				{
					addPiAssignInternalAccessoriesOfOrders(piAssignInternalAccessories_OrderChoseModel);
				}
			}
			
			log.debug("editPiAssignInternalAccessoriesOfOrders successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("savePiAssignInternalAccessoriesOfOrders with param 'piAssignInternalAccessories_OrderChoseModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("savePiAssignInternalAccessoriesOfOrders with param 'piAssignInternalAccessories_OrderChoseModel' in class: %s has error: %s",
									getClass(), e.getMessage()));
			return false;
		}
	}
	
	
	/**
	 * This function is used to get Sum Of Assigned Quantity, to prevent edit  "order chose quantity" < quantity is assigned
	 * @param accessoryCode,lotnumber
	 * @return sum of assigned quantity
	 */
	@Override
	public Double getSumOfAssignedQuantity(String lotNumber, String accessoryCode) {
		Double assignQty = (double) 0;
		List<Piassigninternalaccessories> lstPiassigninternalaccessories = piassigninternalaccessoriesDao
				.getListPiAssignInternalAccessoriesByAccessoryCodeAndLotNumber(accessoryCode,lotNumber);
		if (lstPiassigninternalaccessories.size() > 0) {
			for (Piassigninternalaccessories piassigninternalaccessories : lstPiassigninternalaccessories) {
				assignQty += piassigninternalaccessoriesService.GetAssignQuantity(piassigninternalaccessories
						.getPiinternalaccessories());
			}
		}
		return assignQty;
	}


}
