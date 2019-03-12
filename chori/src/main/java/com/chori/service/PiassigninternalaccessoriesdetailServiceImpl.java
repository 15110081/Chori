package com.chori.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.AccessoryConsumptionDao;
import com.chori.dao.GarmentstyleaccessorydetailDao;
import com.chori.dao.PIDao;
import com.chori.dao.PiassigninternalaccessoriesdetailDao;
import com.chori.dao.PigriddetailDao;
import com.chori.entity.Accessoryconsumption;
import com.chori.entity.AccessoryconsumptionId;
import com.chori.entity.Garmentstyleaccessorydetail;
import com.chori.entity.Pi;
import com.chori.entity.Piassigninternalaccessoriesdetail;
import com.chori.entity.Pigriddetail;
import com.chori.model.PiassigninternalaccessoriesdetailModel;

@Repository("piassigninternalaccessoriesdetailService")
public class PiassigninternalaccessoriesdetailServiceImpl extends
		AbstractServiceImpl<Piassigninternalaccessoriesdetail, Integer>
		implements PiassigninternalaccessoriesdetailService {
	private PiassigninternalaccessoriesdetailDao dao;

	public PiassigninternalaccessoriesdetailServiceImpl() {
	}

	@Autowired
	public PiassigninternalaccessoriesdetailServiceImpl(
			@Qualifier("piassigninternalaccessoriesdetailDao") AbstractDao<Piassigninternalaccessoriesdetail, Integer> abstractDao) {
		super(abstractDao);
		this.dao = (PiassigninternalaccessoriesdetailDao) abstractDao;
	}

	@Autowired
	private PiassigninternalaccessoriesdetailDao piassigninternalaccessoriesdetailDao;

	@Autowired
	private GarmentstyleaccessorydetailDao garmentstyleaccessorydetailDao;
	
	@Autowired
	private PIDao pIDao;
	
	@Autowired
	private PigriddetailDao piGridDetailDao;
	
	@Autowired
	private AccessoryConsumptionDao accessoryConsumptionDao;
	
	/**
	 * This function is used to get all PiassigninternalaccessoriesdetailModel
	 * 
	 * @param PiassigninternalaccessoriesdetailModel
	 * @return list PiassigninternalaccessoriesdetailModel
	 */
	@Override
	public List<PiassigninternalaccessoriesdetailModel> getAllPiassigninternalaccessoriesdetailModel() {
		log.info(String.format(
				"PiassigninternalaccessoriesdetailModel in class: %s",
				getClass()));
		try {
			log.debug("get all Piassigninternalaccessoriesdetail in DB after that return a list PiassigninternalaccessoriesdetailModel");
			List<Piassigninternalaccessoriesdetail> lstPiassign = dao.getAll();

			PiassigninternalaccessoriesdetailModel model = null;
			List<PiassigninternalaccessoriesdetailModel> lst = new ArrayList<PiassigninternalaccessoriesdetailModel>();

			@SuppressWarnings("unused")
			PiassigninternalaccessoriesdetailModel piassigninternalaccessoriesdetailModel;

			for (@SuppressWarnings("unused")
			Piassigninternalaccessoriesdetail piassign : lstPiassign) {
				lst.add(model);
			}
			log.debug("getAllGarmentConsumptionModel successfully");
			return lst;
		} catch (Exception e) {
			log.error(String
					.format("getAllPiassigninternalaccessoriesdetailModel in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function get an assign internal accessory detail of a pi
	 * 
	 * @param lotnumber
	 *            , accessory code
	 * @return list detail of assign internal accessory (include GarmentStyle x
	 *         Color and Assign Qty)
	 */
	public List<PiassigninternalaccessoriesdetailModel> getAnAssignInternalAccessoryDetailOfAPi(
			String lotnumber, String accessorycode) {
		log.debug("in PiassigninternalaccessoriesService at getAnAssignInternalAccessoryDetailOfAPi method");
		try {

			List<Piassigninternalaccessoriesdetail> listPiassigninternalaccessoriesdetail = piassigninternalaccessoriesdetailDao
					.getListPiassigninternalaccessoriesdetailByPiAssignInternalAccessoriesId(lotnumber, accessorycode);
			List<PiassigninternalaccessoriesdetailModel> lstPiassigninternalaccessoriesdetailModel = new ArrayList<PiassigninternalaccessoriesdetailModel>();
			PiassigninternalaccessoriesdetailModel piassigninternalaccessoriesdetailModel;

//			// find piassigninternalaccessoriesId by lotnumber and accessorycode
//			Integer piAssignInternalAccessoriesId = piassigninternalaccessoriesService
//					.findPiAssignInternalAccessoriesIdByAccessoryCodeAndLotNumber2(
//							lotnumber, accessorycode);
			// get assign detail
			for (Piassigninternalaccessoriesdetail piaccessorydetail : listPiassigninternalaccessoriesdetail) {
				// if piAssignInternalAccessoriesId of
				// piAssignInternalAccessoriesDetailId equal to
				// piAssignInternalAccessoriesId variable
				// then get its detail
//				if (piAssignInternalAccessoriesId == piaccessorydetail
//						.getPiassigninternalaccessories()
//						.getPiinternalaccessories()) {
					piassigninternalaccessoriesdetailModel = new PiassigninternalaccessoriesdetailModel();
					piassigninternalaccessoriesdetailModel
							.setAssignquantity(piaccessorydetail
									.getAssignquantity());
					piassigninternalaccessoriesdetailModel
							.setColor(piaccessorydetail.getColor()
									.getColorcode());
					piassigninternalaccessoriesdetailModel
							.setGarmentstyle(piaccessorydetail
									.getGarmentstyle().getGarmentstylecode());
					piassigninternalaccessoriesdetailModel
							.setSizecode(piaccessorydetail
									.getSize().getSizecode());
					piassigninternalaccessoriesdetailModel
							.setUsedvalue(getUsedValueOfAAccessoryByGarmentStyleAndSize(
									accessorycode
									,piaccessorydetail
										.getGarmentstyle().getGarmentstylecode()
									,piaccessorydetail
										.getSize().getSizecode()));
					piassigninternalaccessoriesdetailModel
							.setPcs(getPcsOfAPiGrid(piaccessorydetail));
					piassigninternalaccessoriesdetailModel
							.setRecommendQty(calculateRecommendQuantity(piaccessorydetail,accessorycode));
					piassigninternalaccessoriesdetailModel
							.setPiassigninternalaccessories(piaccessorydetail
									.getPiassigninternalaccessories()
									.getPiinternalaccessories());
					lstPiassigninternalaccessoriesdetailModel
							.add(piassigninternalaccessoriesdetailModel);
				//}
			}
			log.debug("getAnAssignInternalAccessoryDetailOfAPi successfully");
			return lstPiassigninternalaccessoriesdetailModel;

		} catch (NullPointerException ne) {
			log.error("in PiassigninternalaccessoriesService at getAnAssignInternalAccessoryDetailOfAPi method error: "
					+ ne.getMessage());
		}
		return null;
	}

	/**
	 * This function edit piassigninternalaccessories by
	 * piinternalaccessoriesID, garmentstyle x color
	 * 
	 * @param piassigninternalaccessoriesdetailModel
	 * @return true if edit success, else return false
	 */
	public boolean editPiassigninternalaccessoriesdetail(
			PiassigninternalaccessoriesdetailModel piassigninternalaccessoriesdetailModel) {
		log.info(String
				.format("editPiassigninternalaccessoriesdetail with param 'piassigninternalaccessoriesdetailModel' in class: %s",
						getClass()));
		try {
			Integer PiassigninternalaccessoriesdetailId = null;
			// find detailId by piinternalaccessoriesID, garmentstyle x color
			List<Piassigninternalaccessoriesdetail> listPiassigninternalaccessoriesdetail = piassigninternalaccessoriesdetailDao
					.getListPiassigninternalaccessoriesdetailByColorCodeGarmentStyleCodeSizeCodeAndPiassigninternalaccessories(
							piassigninternalaccessoriesdetailModel.getColor()
							,piassigninternalaccessoriesdetailModel.getGarmentstyle()
							,piassigninternalaccessoriesdetailModel.getSizecode()
							,piassigninternalaccessoriesdetailModel.getPiassigninternalaccessories());
			for (Piassigninternalaccessoriesdetail piaccessorydetail : listPiassigninternalaccessoriesdetail) {
					PiassigninternalaccessoriesdetailId = piaccessorydetail
							.getPiinternalaccdetailcode();
			}

			// edit
			Piassigninternalaccessoriesdetail piassigninternalaccessoriesdetail = dao
					.findById(PiassigninternalaccessoriesdetailId);
			piassigninternalaccessoriesdetail
					.setPiinternalaccdetailcode(PiassigninternalaccessoriesdetailId);
			if (piassigninternalaccessoriesdetailModel.getAssignquantity() != null) {
				piassigninternalaccessoriesdetail
						.setAssignquantity(piassigninternalaccessoriesdetailModel
								.getAssignquantity());
			} else {
				piassigninternalaccessoriesdetail.setAssignquantity((double) 0);
			}

			dao.update(piassigninternalaccessoriesdetail);
			log.debug("editPiassigninternalaccessoriesdetail successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editPiassigninternalaccessoriesdetail with param 'piassigninternalaccessoriesdetailModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("editPiassigninternalaccessoriesdetail with param 'piassigninternalaccessoriesdetailModel' in class: %s has error: %s",
									getClass(), e.getMessage()));
			return false;
		}
	}
	
	/**
	 * This function is used to get UsedValue Of A Accessory By GarmentStyle And Size
	 * 
	 * @param accessoryCode,garmentStyleCode,sizeCode
	 * @return accessory's used value if is existed, else return used value = 0
	 */
	public Float getUsedValueOfAAccessoryByGarmentStyleAndSize(String accessoryCode,String garmentStyleCode,Integer sizeCode) {
		log.info(String.format(
				"getUsedValueOfAAccessoryByGarmentStyleAndSize in class: %s",
				getClass()));
		try {
			log.debug("get UsedValue Of A Accessory By GarmentStyle And Size");
			List<Garmentstyleaccessorydetail> lstGarmentstyleaccessorydetail = garmentstyleaccessorydetailDao
					.getListGarmentstyleaccessorydetailByGarmentStyleCodeAccessoryCodeAndSize(garmentStyleCode,accessoryCode,sizeCode);

			for (Garmentstyleaccessorydetail garmentstyleaccessorydetail : lstGarmentstyleaccessorydetail) {
				return garmentstyleaccessorydetail.getUsedvalue();
			}
			log.debug("getUsedValueOfAAccessoryByGarmentStyleAndSize successfully");
			return (float) 0;
		} catch (Exception e) {
			log.error(String
					.format("getUsedValueOfAAccessoryByGarmentStyleAndSize in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to get Pcs Of A Pi Grid
	 * @param Piassigninternalaccessoriesdetail object
	 * @return pcs value
	 */
	public Integer getPcsOfAPiGrid(Piassigninternalaccessoriesdetail piaccessorydetail) {
		log.info(String.format(
				"getPcsOfAPiGrid in class: %s",
				getClass()));
		try {
			Integer sumPcsOfAPiGrid = 0;
			log.debug("get Pcs Of A Pi Grid");	
			Integer piGridCode = pIDao.findById(piaccessorydetail.getPiassigninternalaccessories().getPi().getLotnumber()).getPigrid().getPigridcode();
			// get all GarmentStyle x Color of a pi
			List<Pigriddetail> lstPigriddetail = piGridDetailDao
					.getListPigriddetailByPigridcodeColorCodeGarmentStyleCodeTypeCode(
							piGridCode, piaccessorydetail.getColor().getColorcode(), piaccessorydetail.getGarmentstyle().getGarmentstylecode(),
							piaccessorydetail.getSize().getSizecode());
			for (Pigriddetail pigriddetail : lstPigriddetail) {
				sumPcsOfAPiGrid += pigriddetail.getPcs();
			}			
			log.debug("getPcsOfAPiGrid successfully");
			return sumPcsOfAPiGrid;
		} catch (Exception e) {
			log.error(String
					.format("getPcsOfAPiGrid in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to get consumption of an accessory by factory
	 * 
	 * @param accessoryCode,factoryCode
	 * @return consumption value
	 */
	public Float getConsumptionOfAnAccessory(String accessoryCode,String factoryCode) {
		log.info(String.format(
				"getConsumptionOfAnAccessory in class: %s",
				getClass()));
		try {
			log.debug("get Consumption Of An Accessory");
			//set Id for AccessoryConsumption
			AccessoryconsumptionId accConID = new AccessoryconsumptionId(factoryCode,accessoryCode);
			Accessoryconsumption accCon = accessoryConsumptionDao.findById(accConID);
			//find consumption value if its exist, else return 0
			if(null != accCon)
			{
				return accCon.getConsumption();				
			}
			log.debug("getConsumptionOfAnAccessory successfully");
			return (float) 0;			
		} catch (Exception e) {
			log.error(String
					.format("getConsumptionOfAnAccessory in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to get recommend quantity depend for used value,pcs, consumption
	 * 
	 * @param Piassigninternalaccessoriesdetail object ,accessoryCode
	 * @return recommen quantity value
	 */
	public Double calculateRecommendQuantity(Piassigninternalaccessoriesdetail piaccessorydetail, String accessoryCode) {
		log.info(String.format(
				"calculateRecommendQuantity in class: %s",
				getClass()));
		try {
			log.debug("calculate Recommend Quantity");
			Double result = (double) 0;
			
			//get neccessary value to calculate recommend quantity
			Float usedValue = getUsedValueOfAAccessoryByGarmentStyleAndSize(
					accessoryCode
					,piaccessorydetail
						.getGarmentstyle().getGarmentstylecode()
					,piaccessorydetail
						.getSize().getSizecode());
			Integer pcs = getPcsOfAPiGrid(piaccessorydetail);
			Float consumptionValue = getConsumptionOfAnAccessory(accessoryCode
					,piaccessorydetail.getPiassigninternalaccessories().getPi().getFactory().getFactorycode());
			
			//calculate
			result = (double) (usedValue * pcs + (usedValue * pcs * consumptionValue / 100));
			log.debug("calculateRecommendQuantity successfully");
			return result;		
		} catch (Exception e) {
			log.error(String
					.format("calculateRecommendQuantity in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	public String findLotNumberByPiGridCode(Integer piGridCode) {
		log.info(String.format(
				"findLotNumberByPiGridCode in class: %s",
				getClass()));
		try {
			log.debug("findLotNumberByPiGridCode");	
			// get all GarmentStyle x Color of a pi
			List<Pi> lstPi = pIDao.getLotNumberByPIGridCode(piGridCode);
			for (Pi pi : lstPi) {
				return pi.getLotnumber();
			}			
			log.debug("findLotNumberByPiGridCode successfully");
			return null;
		} catch (Exception e) {
			log.error(String
					.format("findLotNumberByPiGridCode in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
