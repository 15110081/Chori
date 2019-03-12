package com.chori.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.AccessoryConsumptionDao;
import com.chori.dao.UserDao;
import com.chori.entity.Accessoryconsumption;
import com.chori.entity.AccessoryconsumptionId;
import com.chori.model.AccessoryConsumptionModel;

@Repository("accessoryconsumptionService")
public class AccessoryConsumptionServiceImpl extends
		AbstractServiceImpl<Accessoryconsumption, AccessoryconsumptionId>
		implements AccessoryConsumptionService {

	private AccessoryConsumptionDao dao;

	@Autowired
	private UserDao userDao;

	// public AccessoryConsumptionServiceImpl() {
	// }

	@Autowired
	public AccessoryConsumptionServiceImpl(
			@Qualifier("accessoryconsumptionDao") AbstractDao<Accessoryconsumption, AccessoryconsumptionId> abstractDao) {
		super(abstractDao);
		this.dao = (AccessoryConsumptionDao) abstractDao;
	}

	/**
	 * This function is used get all AccessoryConsumption
	 * 
	 * @return a List of all AccessoryConsumption
	 */
	public List<AccessoryConsumptionModel> getAllAccessoryConsumption() {
		log.info(String.format("getAllAccessoryConsumption in class: %s",
				getClass()));
		try {
			List<Accessoryconsumption> lstAccessoryConsumption = dao.getAll();
			AccessoryConsumptionModel accessoryConsumptionModel;
			List<AccessoryConsumptionModel> lstaccessoryConsumptionModel = new ArrayList<AccessoryConsumptionModel>();
			for (Accessoryconsumption acc : lstAccessoryConsumption) {
				accessoryConsumptionModel = new AccessoryConsumptionModel();
				accessoryConsumptionModel.setAccessorycode(acc.getId()
						.getAccessorycode());
				accessoryConsumptionModel.setFactorycode(acc.getId()
						.getFactorycode());
				accessoryConsumptionModel
						.setConsumption(acc.getConsumption() == null ? 0 : acc
								.getConsumption());
				accessoryConsumptionModel.setCreatedate(acc.getCreatedate());
				accessoryConsumptionModel
						.setUsername(acc.getUser() == null ? "" : acc.getUser()
								.getUsername());
				accessoryConsumptionModel.setFactoryshortname(acc.getFactory()
						.getShortname());
				accessoryConsumptionModel.setAccessoryshortname(acc
						.getAccessory().getName());
				lstaccessoryConsumptionModel.add(accessoryConsumptionModel);
			}
			log.debug("getAllAccessoryConsumption successfully");
			return lstaccessoryConsumptionModel;
		} catch (Exception e) {
			log.error(String.format(
					"getAllAccessoryConsumption in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used get all factory which has accessory consumption
	 * 
	 * @return list of factory has accessory consumption
	 */
	public List<String> getAllFactoryHasConsumption() {
		log.info(String.format("getAllFactoryHasConsumption in class: %s",
				getClass()));
		try {
			List<Accessoryconsumption> lstAccessoryConsumption = dao.getAll();
			List<String> lstFactoryHasConsumption = new ArrayList<String>();
			for (Accessoryconsumption acc : lstAccessoryConsumption) {
				lstFactoryHasConsumption.add(acc.getId().getFactorycode());
			}
			Set<String> lstUniqueFactory = new HashSet<>();
			lstUniqueFactory.addAll(lstFactoryHasConsumption);
			lstFactoryHasConsumption.clear();
			lstFactoryHasConsumption.addAll(lstUniqueFactory);
			log.debug("getAllFactoryHasConsumption successfully");
			return lstFactoryHasConsumption;
		} catch (Exception e) {
			log.error(String.format(
					"getAllFactoryHasConsumption in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete an Accessoryconsumption in database.
	 * 
	 * @param accessoryconsumptionId
	 * @return delete successfully =>true , else => false
	 */
	public boolean deleteAccessoryConsumption(
			AccessoryconsumptionId accessoryconsumptionId) {
		log.info(String
				.format("deleteAccessoryConsumption with param 'accessoryconsumptionId' in class: %s",
						getClass()));
		try {
			Accessoryconsumption acc = dao.findById(accessoryconsumptionId);
			dao.delete(acc);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This function find an Accessory Consumption by id.
	 * 
	 * @param accessoryConsumptionId
	 * @return detail of Accessory Consumption
	 */
	public AccessoryConsumptionModel findAccessoryConsumptionModelById(
			AccessoryconsumptionId accessoryconsumptionId) {
		log.info(String
				.format("findAccessoryConsumptionModelById with param 'accessoryconsumptionId' in class: %s",
						getClass()));
		try {
			AccessoryConsumptionModel model = new AccessoryConsumptionModel();
			Accessoryconsumption accessoryConsumption = dao
					.findById(accessoryconsumptionId);

			model.setAccessorycode(accessoryConsumption.getId()
					.getAccessorycode());
			model.setFactorycode(accessoryConsumption.getId().getFactorycode());
			model.setConsumption(accessoryConsumption.getConsumption());
			// model.setConsumption(accessoryConsumption.getConsumption()==null?0:accessoryConsumption.getConsumption());

			log.debug("findaccessoryConsumptionmodelById successfully");
			return model;
		} catch (Exception e) {
			log.error(String
					.format("findaccessoryConsumptionmodelById with param 'accessoryConsumptionId' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit AccessoryConsumption into database
	 * 
	 * @param AccessoryConsumptionModel
	 *            ,userId
	 * @return true if edit successfully, false if have exception
	 */
	public boolean editAccessoryConsumption(
			AccessoryConsumptionModel accessoryConsumptionModel) {
		log.info(String
				.format("editAccessoryConsumption with param 'AccessoryConsumptionModel' in class: %s",
						getClass()));
		try {
			// get factorycode + accessory code for AccessoryconsumptionId
			AccessoryconsumptionId accessoryconsumptionId = new AccessoryconsumptionId();
			accessoryconsumptionId.setAccessorycode(accessoryConsumptionModel
					.getAccessorycode());
			accessoryconsumptionId.setFactorycode(accessoryConsumptionModel
					.getFactorycode());

			Accessoryconsumption accessoryconsumption = dao
					.findById(accessoryconsumptionId);
			accessoryconsumption.getId().setAccessorycode(
					accessoryconsumptionId.getAccessorycode());
			accessoryconsumption.getId().setFactorycode(
					accessoryconsumptionId.getFactorycode());
			accessoryconsumption.setConsumption(accessoryConsumptionModel
					.getConsumption());

			dao.update(accessoryconsumption);
			log.debug("editAccessoryConsumption successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editAccessoryConsumption with param 'AccessoryConsumptionModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("editOrder with param 'AccessoryConsumptionModel' in class: %s has error: %s",
									getClass(), e.getMessage()));
			return false;
		}
	}

	/**
	 * This function check if there is an accessoryconsumption with
	 * accessoryconsumptionId existed in database
	 * 
	 * @param accessoryconsumptionId
	 * @return false if not existed, true if existed
	 */
	public boolean isAccessoryConsumptionExistedById(
			AccessoryconsumptionId accessoryconsumptionId) {
		if (null == dao.findById(accessoryconsumptionId))
			return false;
		return true;
	}

	/**
	 * This function is used to add new accessory consumption into database
	 * 
	 * @param accessoryConsumptionModel
	 *            , userId
	 * @return true if add successfully, false if have exception
	 */
	public boolean addAccessoryConsumption(
			AccessoryConsumptionModel accessoryConsumptionModel, String userName) {
		log.info(String.format("addAccessoryConsumption in class: %s",
				getClass()));
		try {
			Accessoryconsumption accessoryconsumption = new Accessoryconsumption();
			AccessoryconsumptionId accessoryconsumptionId = new AccessoryconsumptionId();
			accessoryconsumptionId.setAccessorycode(accessoryConsumptionModel
					.getAccessorycode());
			accessoryconsumptionId.setFactorycode(accessoryConsumptionModel
					.getFactorycode());
			accessoryconsumption.setId(accessoryconsumptionId);
			// accessoryconsumption.setFactory(facDao.findById(accessoryConsumptionModel.getFactorycode()));
			// accessoryconsumption.setAccessory(accDao.findById(accessoryConsumptionModel.getAccessorycode()));
			accessoryconsumption.setConsumption(accessoryConsumptionModel
					.getConsumption());
			// accessoryconsumption.setCreatedate(new Date());
			accessoryconsumption.setUser(userDao.findById(userName));
			dao.save(accessoryconsumption);
			log.debug("add new AccessoryConsumption into database successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("addAccessoryConsumption with param 'AccessoryConsumptionModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println("add new AccessoryConsumption into database fail, method addAccessoryConsumption(), class accessoryconsumptionService");
			return false;
		}
	}

	/**
	 * This function is used to find AccessoryConsumption Model By FactoryCode
	 * And AccessoryCode | Hàm đc dùng để lấy AccessoryConsumption qua
	 * FactoryCode And AccessoryCode để hiển thị lên PI lúc update wasted
	 * percentage
	 * 
	 * @param factoryCode
	 * @param accessoryCode
	 * @return
	 */
	public AccessoryConsumptionModel findAccessoryConsumptionModelByFactoryCodeAndAccessoryCode(
			String factoryCode, String accessoryCode) {
		log.info(String
				.format("findAccessoryConsumptionModelByFactoryCodeAndAccessoryCode with param 'accessoryconsumptionId' in class: %s",
						getClass()));
		try {
			AccessoryConsumptionModel model = new AccessoryConsumptionModel();
			Accessoryconsumption accessoryConsumption = dao
					.getAccessoryConsumptionByFactoryCodeAndAccessoryCode(factoryCode, accessoryCode);

			model.setAccessorycode(accessoryConsumption.getId()
					.getAccessorycode());
			model.setFactorycode(accessoryConsumption.getId().getFactorycode());
			model.setConsumption(accessoryConsumption.getConsumption());

			log.debug("findAccessoryConsumptionModelByFactoryCodeAndAccessoryCode successfully");
			return model;
		} catch (Exception e) {
			log.error(String
					.format("findAccessoryConsumptionModelByFactoryCodeAndAccessoryCode with param 'accessoryConsumptionId' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
