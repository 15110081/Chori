package com.chori.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.CustomerDao;
import com.chori.dao.FactoryDao;
import com.chori.dao.GarmentkindDao;
import com.chori.dao.GarmentstyleDao;
import com.chori.dao.GarmentstyleaccessorydetailDao;
import com.chori.dao.GarmentstylereferpriceDao;
import com.chori.dao.UserDao;
import com.chori.entity.Accessory;
import com.chori.entity.Garmentstyle;
import com.chori.entity.Garmentstyleaccessorydetail;
import com.chori.entity.Garmentstylereferprice;
import com.chori.entity.GarmentstylereferpriceId;
import com.chori.model.AccessoryAddModel;
import com.chori.model.GarmentstyleModel;
import com.chori.model.GarmentstylereferpriceModel;

@Repository("garmentstyleService")
public class GarmentstyleServiceImpl extends
		AbstractServiceImpl<Garmentstyle, String> implements
		GarmentstyleService {
	private GarmentstyleDao dao;

	@Autowired
	private GarmentkindDao gkDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private CustomerDao cusDao;

	@Autowired
	private FactoryDao facDao;

	@Autowired
	private GarmentstyleaccessorydetailDao gsadDao;
	
	@Autowired
	private GarmentstylereferpriceDao garmentstylereferpriceDao;
	
	@Autowired
	private GarmentstylereferpriceService garmentstylereferpriceSer;

	@Autowired
	public GarmentstyleServiceImpl(
			@Qualifier("garmentstyleDao") AbstractDao<Garmentstyle, String> abstractDao) {
		super(abstractDao);
		this.dao = (GarmentstyleDao) abstractDao;
	}

	/**
	 * This function is used get all GarmentstyleModel
	 * 
	 * @return list<GarmentstyleModel>
	 */
	@Override
	public List<GarmentstyleModel> getAllGarmentstyleModel() {
		log.info(String.format("getAllGarmentstyle in class: %s", getClass()));
		try {
			List<Garmentstyle> lstGarmentstyle = dao.getAll();
			GarmentstyleModel garmentstyleModel;
			List<GarmentstyleModel> lstGarmentstyleModel = new ArrayList<GarmentstyleModel>();
			//lưu display name
//			String tmpDisplayName;
			String[] splitGarmentStyleCode;
			List<GarmentstylereferpriceModel> garmentstylereferpriceModels;
			for (Garmentstyle garmentStyle : lstGarmentstyle) {
				garmentstyleModel = new GarmentstyleModel();
				garmentstyleModel.setGarmentstylecode(garmentStyle
						.getGarmentstylecode());
				garmentstyleModel
						.setCustomercode(garmentStyle.getCustomer() == null ? ""
								: garmentStyle.getCustomer().getCustomercode());
				garmentstyleModel.setCustomerShortname(garmentStyle
						.getCustomer() == null ? "" : garmentStyle
						.getCustomer().getShortname());
				garmentstyleModel
						.setFactorycode(garmentStyle.getFactory() == null ? ""
								: garmentStyle.getFactory().getFactorycode());
				garmentstyleModel
						.setFactoryShortname(garmentStyle.getFactory() == null ? ""
								: garmentStyle.getFactory().getShortname());
				garmentstyleModel.setGarmentkindcode(garmentStyle
						.getGarmentkind() == null ? "" : garmentStyle
						.getGarmentkind().getGarmentkindcode());
				garmentstyleModel.setGarmentkindDescription(garmentStyle
						.getGarmentkind() == null ? "" : garmentStyle
						.getGarmentkind().getDescription());
				garmentstyleModel
						.setCreator(garmentStyle.getUser() == null ? ""
								: garmentStyle.getUser().getUsername());
				garmentstyleModel.setDescription(garmentStyle.getDescription());
				garmentstyleModel.setImgurl1(garmentStyle.getImgurl1());
				garmentstyleModel.setImgurl2(garmentStyle.getImgurl2());
				garmentstyleModel.setImgurl3(garmentStyle.getImgurl3());
				garmentstyleModel.setImgurl4(garmentStyle.getImgurl4());
				garmentstyleModel.setImgurl5(garmentStyle.getImgurl5());
				garmentstyleModel.setCreatedate(garmentStyle.getCreatedate());
				garmentstyleModel.setReferprice(garmentStyle.getReferprice());
				//set display name
				splitGarmentStyleCode = garmentStyle.getGarmentstylecode().split("@@@");
				garmentstyleModel.setDisplayName(splitGarmentStyleCode[0]);
				
				garmentstylereferpriceModels = garmentstylereferpriceSer.getListGarmentstylereferpriceModelByGarmentstyleCode(garmentStyle
						.getGarmentstylecode());
				garmentstyleModel.setGarmentstylereferpriceModelList(garmentstylereferpriceModels);
				
				lstGarmentstyleModel.add(garmentstyleModel);
			}
			log.debug("getAllGarmentstyle successful");
			return lstGarmentstyleModel;
		} catch (Exception e) {
			log.error(String.format(
					"getAllGarmentstyle in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to find a garment style model by id
	 */
	public GarmentstyleModel findGarmentstyleModelById(String id) {
		log.info(String
				.format("findGarmentstyleById with param 'id' in class: %s",
						getClass()));
		try {
			Garmentstyle garmentStyle = dao.findById(id);
			//mảng lưu display name
			String[] splitGarmentStyleCode;
			GarmentstyleModel garmentstyleModel = new GarmentstyleModel();
			garmentstyleModel.setGarmentstylecode(garmentStyle
					.getGarmentstylecode());
			garmentstyleModel
					.setCustomercode(garmentStyle.getCustomer() == null ? ""
							: garmentStyle.getCustomer().getCustomercode());
			garmentstyleModel
					.setCustomerShortname(garmentStyle.getCustomer() == null ? ""
							: garmentStyle.getCustomer().getShortname());
			garmentstyleModel
					.setFactorycode(garmentStyle.getFactory() == null ? ""
							: garmentStyle.getFactory().getFactorycode());
			garmentstyleModel
					.setFactoryShortname(garmentStyle.getFactory() == null ? ""
							: garmentStyle.getFactory().getShortname());
			garmentstyleModel
					.setGarmentkindcode(garmentStyle.getGarmentkind() == null ? ""
							: garmentStyle.getGarmentkind()
									.getGarmentkindcode());
			garmentstyleModel.setGarmentkindDescription(garmentStyle
					.getGarmentkind() == null ? "" : garmentStyle
					.getGarmentkind().getDescription());
			garmentstyleModel.setCreator(garmentStyle.getUser() == null ? ""
					: garmentStyle.getUser().getUsername());
			garmentstyleModel.setDescription(garmentStyle.getDescription());
			garmentstyleModel.setImgurl1(garmentStyle.getImgurl1());
			garmentstyleModel.setImgurl2(garmentStyle.getImgurl2());
			garmentstyleModel.setImgurl3(garmentStyle.getImgurl3());
			garmentstyleModel.setImgurl4(garmentStyle.getImgurl4());
			garmentstyleModel.setImgurl5(garmentStyle.getImgurl5());
			garmentstyleModel.setCreatedate(garmentStyle.getCreatedate());
			garmentstyleModel.setReferprice(garmentStyle.getReferprice());
			garmentstyleModel.setSewingguide(garmentStyle.getSewingguide());
			garmentstyleModel.setPackingguide(garmentStyle.getPackingguide());
			garmentstyleModel.setCurrencycode(garmentStyle.getCurrencycode());
			//set display name
			splitGarmentStyleCode = garmentStyle.getGarmentstylecode().split("@@@");
			garmentstyleModel.setDisplayName(splitGarmentStyleCode[0]);

			log.debug("findGarmentstyleModelById successfully");
			return garmentstyleModel;
		} catch (Exception e) {
			log.error(String
					.format("findGarmentstyleById with param 'id' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new garment style into database
	 * 
	 * @param garmentstyleModel
	 * @param userName
	 * @return
	 */
	public boolean addNewGarmentstyle(GarmentstyleModel garmentstyleModel,
			String userName) {
		log.info(String.format("addNewGarmentstyle in class: %s", getClass()));
		try {
			Garmentstyle garmentStyle = new Garmentstyle();

			garmentStyle.setGarmentstylecode(garmentstyleModel
					.getGarmentstylecode()+"@@@"+garmentstyleModel
					.getCustomercode());
			garmentStyle.setCustomer(cusDao.findById(garmentstyleModel
					.getCustomercode()));
			garmentStyle.setFactory(facDao.findById(garmentstyleModel
					.getFactorycode()));
			garmentStyle.setGarmentkind(gkDao.findById(garmentstyleModel
					.getGarmentkindcode()));
			garmentStyle.setUser(userDao.findById(userName));
			garmentStyle.setDescription(garmentstyleModel.getDescription());
			garmentStyle.setCreatedate(new Date());
			garmentStyle.setImgurl1(garmentstyleModel.getImgurl1());
			garmentStyle.setImgurl2(garmentstyleModel.getImgurl2());
			garmentStyle.setImgurl3(garmentstyleModel.getImgurl3());
			garmentStyle.setImgurl4(garmentstyleModel.getImgurl4());
			garmentStyle.setImgurl5(garmentstyleModel.getImgurl5());
			garmentStyle.setReferprice(garmentstyleModel.getReferprice());
			garmentStyle.setSewingguide(garmentstyleModel.getSewingguide());
			garmentStyle.setPackingguide(garmentstyleModel.getPackingguide());
			garmentStyle.setCurrencycode(garmentstyleModel.getCurrencycode());

			dao.save(garmentStyle);
			log.debug("addNewGarmentstyle into database successfully");
			return true;
		} catch (Exception e) {
			log.error(String.format(
					"addNewGarmentstyle in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit a existed garment style in db
	 * 
	 * @param garmentstyleModel
	 * @param userName
	 * @return
	 */
	public boolean editGarmentstyle(GarmentstyleModel garmentstyleModel,
			String userName) {
		log.info(String
				.format("editGarmentstyle with param 'garmentstyleModel', 'userName' in class: %s",
						getClass()));
		try {
			Garmentstyle garmentStyle = dao.findById(garmentstyleModel
					.getGarmentstylecode());

			if (garmentstyleModel.getCustomercode() != null)
				garmentStyle.setCustomer(cusDao.findById(garmentstyleModel
						.getCustomercode()));
			if (garmentstyleModel.getFactorycode() != null)
				garmentStyle.setFactory(facDao.findById(garmentstyleModel
						.getFactorycode()));
			if (garmentstyleModel.getGarmentkindcode() != null)
				garmentStyle.setGarmentkind(gkDao.findById(garmentstyleModel
						.getGarmentkindcode()));
			garmentStyle.setDescription(garmentstyleModel.getDescription());
			garmentStyle.setReferprice(garmentstyleModel.getReferprice());

			garmentStyle.setSewingguide(garmentstyleModel.getSewingguide());
			garmentStyle.setPackingguide(garmentstyleModel.getPackingguide());

			if (garmentstyleModel.getImgurl1() != null)
				garmentStyle.setImgurl1(garmentstyleModel.getImgurl1());
			if (garmentstyleModel.getImgurl2() != null)
				garmentStyle.setImgurl2(garmentstyleModel.getImgurl2());
			if (garmentstyleModel.getImgurl3() != null)
				garmentStyle.setImgurl3(garmentstyleModel.getImgurl3());
			if (garmentstyleModel.getImgurl4() != null)
				garmentStyle.setImgurl4(garmentstyleModel.getImgurl4());
			if (garmentstyleModel.getImgurl5() != null)
				garmentStyle.setImgurl5(garmentstyleModel.getImgurl5());
			
			garmentStyle.setCurrencycode(garmentstyleModel.getCurrencycode());

			dao.update(garmentStyle);
			log.debug("editGarmentstyle successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editGarmentstyle with param 'garmentstyleModel', 'userName' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("editGarmentstyle with param 'garmentstyleModel', 'userName' in class: %s has error: %s",
									getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete a garment style in db
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteGarmentstyle(String id) {
		log.info(String.format(
				"deleteGarmentstyle with param 'id' in class: %s", getClass()));
		try {
			dao.deleteById(id);
			return true;
		} catch (Exception e) {
			log.error(String
					.format("deleteGarmentstyle with param 'id' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a garment style is existed in db
	 * 
	 * @param id
	 * @return true if existed.
	 */
	public boolean isGarmentStyleExistedById(String id) {
		log.info(String.format(
				"isGarmentStyleExistedById with param 'id' in class: %s",
				getClass()));
		try {
			if (null == dao.findById(id))
				return false;
			return true;
		} catch (Exception e) {
			log.error(String
					.format("isGarmentStyleExistedById with param 'id' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to copy a garment style
	 * 
	 * @param newGarmentstyleModel
	 * @param oldGarmentStyleCode
	 * @param userName
	 * @return
	 */
	public boolean copyGarmentstyle(GarmentstyleModel newGarmentstyleModel,
			String oldGarmentStyleCode, String userName) {
		log.info(String
				.format("copyGarmentstyle with param 'garmentstyleModel', 'userName' in class: %s",
						getClass()));
		try {
			Garmentstyle oldGarmentStyle = dao.findById(oldGarmentStyleCode);

			Garmentstyle newGarmentStyle = new Garmentstyle();

			newGarmentStyle.setGarmentstylecode(newGarmentstyleModel
					.getGarmentstylecode()+"@@@"+newGarmentstyleModel.getCustomercode());
			newGarmentStyle.setCustomer(oldGarmentStyle.getCustomer());
			newGarmentStyle.setFactory(oldGarmentStyle.getFactory());
			newGarmentStyle.setGarmentkind(oldGarmentStyle.getGarmentkind());
			newGarmentStyle.setUser(userDao.findById(userName));
			newGarmentStyle.setDescription(oldGarmentStyle.getDescription());
			newGarmentStyle.setCreatedate(new Date());
			newGarmentStyle.setImgurl1(oldGarmentStyle.getImgurl1());
			newGarmentStyle.setImgurl2(oldGarmentStyle.getImgurl2());
			newGarmentStyle.setImgurl3(oldGarmentStyle.getImgurl3());
			newGarmentStyle.setImgurl4(oldGarmentStyle.getImgurl4());
			newGarmentStyle.setImgurl5(oldGarmentStyle.getImgurl5());
			newGarmentStyle.setReferprice(oldGarmentStyle.getReferprice());

			// save 1st
			dao.save(newGarmentStyle);

			// newGarmentStyle.setGarmentstyleaccessorydetails(oldGarmentStyle.getGarmentstyleaccessorydetails());
			List<Garmentstyleaccessorydetail> listOldGSAD = gsadDao
					.getListGarmentstyleaccessorydetailByGarmentStyleName(oldGarmentStyleCode);
			Garmentstyleaccessorydetail tmpGSAD;
			for (Garmentstyleaccessorydetail garmentstyleaccessorydetail : listOldGSAD) {
				tmpGSAD = new Garmentstyleaccessorydetail();
				tmpGSAD.setAccessory(garmentstyleaccessorydetail.getAccessory());
				tmpGSAD.setGarmentstyle(newGarmentStyle);
				tmpGSAD.setSize(garmentstyleaccessorydetail.getSize());
				tmpGSAD.setUser(userDao.findById(userName));
				tmpGSAD.setUsedvalue(garmentstyleaccessorydetail.getUsedvalue());
				tmpGSAD.setCreatedate(new Date());

				// newGarmentStyle.getGarmentstyleaccessorydetails().add(tmpGSAD);
				gsadDao.save(tmpGSAD);
			}
			
			List<Garmentstylereferprice> lst = garmentstylereferpriceDao.getListGarmentstylereferpriceByGarmentstyleCode(oldGarmentStyleCode);
			Garmentstylereferprice tmpGSRP;
			GarmentstylereferpriceId tmpGSRPid;
			for (Garmentstylereferprice garmentstylereferprice : lst) {
				tmpGSRP= new Garmentstylereferprice();
				tmpGSRP.setGarmentstyle(newGarmentStyle);
				tmpGSRPid = new GarmentstylereferpriceId();
				tmpGSRPid.setGarmentstylecode(newGarmentStyle.getGarmentstylecode());
				tmpGSRPid.setTypecode(garmentstylereferprice.getType().getTypecode());
				tmpGSRP.setId(tmpGSRPid);
				tmpGSRP.setReferprice(garmentstylereferprice.getReferprice());
				tmpGSRP.setType(garmentstylereferprice.getType());
				
				garmentstylereferpriceDao.save(tmpGSRP);
			}
			
			// dao.save(newGarmentStyle);
			log.debug("copyGarmentstyle successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("copyGarmentstyle with param 'garmentstyleModel', 'userName' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("copyGarmentstyle with param 'garmentstyleModel', 'userName' in class: %s has error: %s",
									getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * Function check if a garment already has any garment style accessory
	 * detail
	 * 
	 * @param garmentStyleCode
	 * @return
	 */
	public boolean isHasGarmentStyleAccessoryDetail(String garmentStyleCode) {
		log.info(String
				.format("isHasGarmentStyleAccessoryDetail with param 'garmentStyleCode' in class: %s",
						getClass()));
		try {
			Garmentstyle garmentStyle = dao.findById(garmentStyleCode);
			if (garmentStyle.getGarmentstyleaccessorydetails().size() > 0)
				return true;
			return false;
		} catch (Exception e) {
			log.error(String
					.format("isHasGarmentStyleAccessoryDetail with param 'garmentStyleCode' in class %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("isHasGarmentStyleAccessoryDetail with param 'garmentStyleCode' in class %s has error: %s",
									getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to delete a image of garment style
	 * @param garmentstyleModel
	 * @param userId
	 * @return
	 */
	public boolean deleteImageGarmentStyle(GarmentstyleModel garmentstyleModel,
			String userId) {
		log.info(String
				.format("deleteImageGarmentStyle with params 'accessoryAddModel', 'userId' in class: %s",
						getClass()));
		try {
			Garmentstyle garmentstyle = dao.findById(garmentstyleModel
					.getGarmentstylecode());
			
			if(garmentstyleModel.getImgUrlDelete().equals(garmentstyle.getImgurl1()))
				garmentstyle.setImgurl1(null);
			if(garmentstyleModel.getImgUrlDelete().equals(garmentstyle.getImgurl2()))
				garmentstyle.setImgurl2(null);
			if(garmentstyleModel.getImgUrlDelete().equals(garmentstyle.getImgurl3()))
				garmentstyle.setImgurl3(null);
			if(garmentstyleModel.getImgUrlDelete().equals(garmentstyle.getImgurl4()))
				garmentstyle.setImgurl4(null);
			if(garmentstyleModel.getImgUrlDelete().equals(garmentstyle.getImgurl5()))
				garmentstyle.setImgurl5(null);

			dao.update(garmentstyle);
			return true;
		} catch (Exception e) {
			// return false;
			log.error(String
					.format("deleteImageGarmentStyle with params 'accessoryAddModel', 'userId' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
