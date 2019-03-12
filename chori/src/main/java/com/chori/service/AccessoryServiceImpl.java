package com.chori.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.AccessoryConsumptionDao;
import com.chori.dao.AccessoryDao;
import com.chori.dao.AccessoryGroupDao;
import com.chori.dao.ColorDao;
import com.chori.dao.UnitDao;
import com.chori.dao.UserDao;
import com.chori.entity.Accessory;
import com.chori.model.AccessoryAddModel;
import com.chori.model.AccessoryConsumptionModel;
import com.chori.model.AccessoryModel;

@Repository("accessoryService")
public class AccessoryServiceImpl extends
		AbstractServiceImpl<Accessory, String> implements AccessoryService {

	private AccessoryDao dao;

	@Autowired
	private ColorDao colorDao;

	@Autowired
	private UnitDao unitDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private AccessoryGroupDao accessoryGroupDao;

	@Autowired
	AccessoryConsumptionService accessoryConsumptionService;
	
	@Autowired
	AccessoryConsumptionDao accessoryConsumptionDao;

	@Autowired
	public AccessoryServiceImpl(
			@Qualifier("accessoryDao") AbstractDao<Accessory, String> abstractDao) {
		super(abstractDao);
		this.dao = (AccessoryDao) abstractDao;
	}

	/**
	 * This function is used get all Accessory
	 * 
	 * @return list<AccessoryModel>
	 */
	@Override
	public List<AccessoryModel> getAllAccessory() {
		log.info(String.format("getAllAccessory in class: %s", getClass()));
		try {
			List<Accessory> lstAccessory = dao.getAll();
			AccessoryModel accessoryModel;
			List<AccessoryModel> lstaccessoryModel = new ArrayList<AccessoryModel>();
			for (Accessory acc : lstAccessory) {
				accessoryModel = new AccessoryModel();
				accessoryModel.setAccessorycode(acc.getAccessorycode());
				accessoryModel.setColorcode(acc.getColor() == null ? "" : acc
						.getColor().getColorcode());
				accessoryModel.setUserName(acc.getUser() == null ? "" : acc
						.getUser().getUsername());
				accessoryModel.setUnitcode(acc.getUnitByUnitcode() == null ? ""
						: acc.getUnitByUnitcode().getUnitcode());
				accessoryModel.setName(acc.getName());
				accessoryModel.setKind(acc.getKind());
				accessoryModel.setDimension(acc.getDimension());
				accessoryModel.setMode(acc.getMode());
				accessoryModel.setImgurl1(acc.getImgurl1());
				accessoryModel.setImgurl2(acc.getImgurl2());
				accessoryModel.setCreatedate(acc.getCreatedate());
				accessoryModel.setAccessorygroupcode(acc.getAccessorygroup()
						.getAccessorygroupcode());
				accessoryModel.setPercontainer(acc.getPercontainer());
				accessoryModel.setContainerUnitCode(acc
						.getUnitByContainerunitcode().getUnitcode());
				accessoryModel.setStatus(acc.getStatus());

				lstaccessoryModel.add(accessoryModel);
			}
			log.debug("getAllAccessory successful");
			return lstaccessoryModel;
		} catch (Exception e) {
			log.error(String.format(
					"getAllAccessory in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete 1 accessory
	 */
	public boolean deleteAccessory(String id) {
		log.info(String.format("deleteAccessory with param 'id' in class: %s",
				getClass()));
		try {
			//delete bên consumption
			accessoryConsumptionDao.deleteAccessoryConsumptionByAccessoryCode(id);
			//sau đó delete
			dao.deleteById(id);
			return true;
		} catch (Exception e) {
			log.error(String
					.format("deleteAccessory with param 'id' in class %s has error: %s",
							getClass(), e.getMessage()));
			// return false;
			throw e;
		}
	}

	/**
	 * This function is used to add new Accessory
	 * 
	 * @param accessoryAddModel
	 * @param userId
	 * @return
	 */
	public boolean addNewAccessory(AccessoryAddModel accessoryAddModel,
			String userId) {
		log.info(String
				.format("addNewAccessory with params 'accessoryAddModel', 'userId' in class: %s",
						getClass()));
		try {
			Accessory accessory = new Accessory();

			accessory.setAccessorycode(accessoryAddModel.getAccessorycode());
			accessory.setColor(colorDao.findById(accessoryAddModel
					.getColorcode()));
			// accessory.setUnitByUnitcode(unitDao.findById(accessoryAddModel.getUnitcode()));
			accessory.setUnitByUnitcode(unitDao.findById(accessoryAddModel
					.getUnitcode()));
			accessory.setUser(userDao.findById(userId));
			accessory.setName(accessoryAddModel.getName());
			accessory.setKind(accessoryAddModel.getKind());
			accessory.setDimension(accessoryAddModel.getDimension());
			accessory.setMode(accessoryAddModel.getMode());
			accessory.setImgurl1(accessoryAddModel.getImgurl1());
			accessory.setImgurl2(accessoryAddModel.getImgurl2());
			accessory.setImgurl3(accessoryAddModel.getImgurl3());
			accessory.setImgurl4(accessoryAddModel.getImgurl4());
			accessory.setCreatedate(new Date());
			accessory.setAccessorygroup(accessoryGroupDao
					.findById(accessoryAddModel.getAccessorygroupcode()));
			accessory.setPercontainer(accessoryAddModel.getPercontainer());
			accessory.setUnitByContainerunitcode(unitDao
					.findById(accessoryAddModel.getContainerUnitCode()));
			accessory.setStatus(accessoryAddModel.getStatus());
			accessory.setAccessorychoricode(accessoryAddModel.getAccessorychoricode());

			dao.save(accessory);

			// Add new accessory consumption when add new accessory - SONNN
			AccessoryConsumptionModel accessoryConsumptionModel;
			List<String> listFactory = accessoryConsumptionService
					.getAllFactoryHasConsumption();
			for (String fac : listFactory) {
				accessoryConsumptionModel = new AccessoryConsumptionModel();
				accessoryConsumptionModel.setFactorycode(fac);
				accessoryConsumptionModel.setAccessorycode(accessoryAddModel
						.getAccessorycode());
				accessoryConsumptionModel.setConsumption((float) 0);
				accessoryConsumptionService.addAccessoryConsumption(
						accessoryConsumptionModel, userId);
			}

			return true;
		} catch (Exception e) {
			log.error(String
					.format("addNewAccessory with params 'accessoryAddModel', 'userId' in class %s has error: %s",
							getClass(), e.getMessage()));
			// return false;
			throw e;
		}
	}

	/**
	 * This function find 1 accessoryModel by id
	 * 
	 * @param id
	 * @return
	 */
	public AccessoryModel findAccessoryModelById(String id) {
		log.info(String.format(
				"findAccessoryModelById with params 'id' in class: %s",
				getClass()));
		try {
			Accessory acc = dao.findById(id);
			AccessoryModel accessoryModel = new AccessoryModel();

			accessoryModel.setAccessorycode(acc.getAccessorycode());
			accessoryModel.setColorcode(acc.getColor().getColorcode());
			accessoryModel.setUserName(acc.getUser().getUsername());
			accessoryModel.setUnitcode(acc.getUnitByUnitcode().getUnitcode());
			accessoryModel.setName(acc.getName());
			accessoryModel.setKind(acc.getKind());
			accessoryModel.setDimension(acc.getDimension());
			accessoryModel.setMode(acc.getMode());
			accessoryModel.setImgurl1(acc.getImgurl1());
			accessoryModel.setImgurl2(acc.getImgurl2());
			accessoryModel.setImgurl3(acc.getImgurl3());
			accessoryModel.setImgurl4(acc.getImgurl4());
			accessoryModel.setCreatedate(acc.getCreatedate());
			accessoryModel.setContainerUnitCode(acc
					.getUnitByContainerunitcode().getUnitcode());
			accessoryModel.setPercontainer(acc.getPercontainer());
			accessoryModel.setAccessorygroupcode(acc.getAccessorygroup()
					.getAccessorygroupcode());
			accessoryModel.setStatus(acc.getStatus());
			accessoryModel.setAccessorychoricode(acc.getAccessorychoricode());

			return accessoryModel;
		} catch (Exception e) {
			log.error(String
					.format("findAccessoryModelById with params 'id' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to update an accessory
	 * 
	 * @param accessoryAddModel
	 * @param userId
	 * @return
	 */
	public boolean editAccessory(AccessoryAddModel accessoryAddModel,
			String userId) {
		log.info(String
				.format("editAccessory with params 'accessoryAddModel', 'userId' in class: %s",
						getClass()));
		try {
			Accessory accessory = dao.findById(accessoryAddModel
					.getAccessorycode());

			accessory.setColor(colorDao.findById(accessoryAddModel
					.getColorcode()));
			accessory.setUnitByUnitcode(unitDao.findById(accessoryAddModel
					.getUnitcode()));
			accessory.setName(accessoryAddModel.getName());
			accessory.setKind(accessoryAddModel.getKind());
			accessory.setDimension(accessoryAddModel.getDimension());
			accessory.setMode(accessoryAddModel.getMode());
			// set new img only if they != null
			if (accessoryAddModel.getImgurl1() != null)
				accessory.setImgurl1(accessoryAddModel.getImgurl1());
			if (accessoryAddModel.getImgurl2() != null)
				accessory.setImgurl2(accessoryAddModel.getImgurl2());
			if (accessoryAddModel.getImgurl3() != null)
				accessory.setImgurl3(accessoryAddModel.getImgurl3());
			if (accessoryAddModel.getImgurl4() != null)
				accessory.setImgurl4(accessoryAddModel.getImgurl4());
			accessory.setAccessorygroup(accessoryGroupDao
					.findById(accessoryAddModel.getAccessorygroupcode()));
			accessory.setPercontainer(accessoryAddModel.getPercontainer());
			accessory.setUnitByContainerunitcode(unitDao
					.findById(accessoryAddModel.getContainerUnitCode()));
			accessory.setStatus(accessoryAddModel.getStatus());
			accessory.setAccessorychoricode(accessoryAddModel.getAccessorychoricode());

			dao.update(accessory);
			return true;
		} catch (Exception e) {
			// return false;
			log.error(String
					.format("editAccessory with params 'accessoryAddModel', 'userId' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if an accessory is existed in db
	 * 
	 * @param id
	 * @return true if existed.
	 */
	public boolean isAccessoryExistedById(String id) {
		log.info(String.format(
				"isAccessoryExistedById with param 'id' in class: %s",
				getClass()));
		try {
			if (null == dao.findById(id))
				return false;
			return true;
		} catch (Exception e) {
			log.error(String
					.format("isAccessoryExistedById with param 'id' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to delete image of accessory
	 * @param accessoryAddModel
	 * @param userId
	 * @return
	 */
	public boolean deleteImageAccessory(AccessoryAddModel accessoryAddModel,
			String userId) {
		log.info(String
				.format("deleteImageAccessory with params 'accessoryAddModel', 'userId' in class: %s",
						getClass()));
		try {
			Accessory accessory = dao.findById(accessoryAddModel
					.getAccessorycode());
			
			if(accessoryAddModel.getImgUrlDelete().equals(accessory.getImgurl1()))
				accessory.setImgurl1(null);
			if(accessoryAddModel.getImgUrlDelete().equals(accessory.getImgurl2()))
				accessory.setImgurl2(null);
			if(accessoryAddModel.getImgUrlDelete().equals(accessory.getImgurl3()))
				accessory.setImgurl3(null);
			if(accessoryAddModel.getImgUrlDelete().equals(accessory.getImgurl4()))
				accessory.setImgurl4(null);

			dao.update(accessory);
			return true;
		} catch (Exception e) {
			// return false;
			log.error(String
					.format("deleteImageAccessory with params 'accessoryAddModel', 'userId' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
