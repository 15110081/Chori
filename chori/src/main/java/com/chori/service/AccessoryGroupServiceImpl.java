package com.chori.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.AccessoryGroupDao;
import com.chori.dao.UserDao;
import com.chori.entity.Accessorygroup;
import com.chori.model.AccessoryGroupModel;

@Repository("accessoryGroupService")
public class AccessoryGroupServiceImpl extends
		AbstractServiceImpl<Accessorygroup, String> implements
		AccessoryGroupService {
	private AccessoryGroupDao dao;

	@Autowired
	private UserDao userDao;

	@Autowired
	public AccessoryGroupServiceImpl(
			AbstractDao<Accessorygroup, String> abstractDao,
			AccessoryGroupDao dao) {
		super(abstractDao);
		this.dao = dao;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chori.service.AccessoryGroupService#isAccessoryGroupExistedById(java
	 * .lang.String)
	 */
	/**
	 * This function is used check isExisted of AccessoryGroup in db
	 * 
	 * @return true or false
	 */
	@Override
	public boolean isAccessoryGroupExistedById(String acgrCode) {
		if (null == dao.findById(acgrCode))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chori.service.AccessoryGroupService#getAllAccessoryGroup()
	 */
	/**
	 * This function is used get all AccessoryGroup
	 * 
	 * @return list<AccessoryGroupModel>
	 */
	@Override
	public List<AccessoryGroupModel> getAllAccessoryGroup() {
		log.debug("in color service list");
		try {
			List<Accessorygroup> lstAccessorygroups = dao.getAll();
			AccessoryGroupModel accessoryGroupModel;
			List<AccessoryGroupModel> lstAccessoryGroupModels = new ArrayList<AccessoryGroupModel>();
			for (Accessorygroup accessorygroup : lstAccessorygroups) {
				accessoryGroupModel = new AccessoryGroupModel();
				accessoryGroupModel.setAccessorygroupCode(accessorygroup
						.getAccessorygroupcode());
				accessoryGroupModel.setDescription(accessorygroup
						.getDescription());
				accessoryGroupModel.setCreatedDate(accessorygroup
						.getCreatedate());
				accessoryGroupModel
						.setCreator(accessorygroup.getUser() == null ? ""
								: accessorygroup.getUser().getUsername());

				lstAccessoryGroupModels.add(accessoryGroupModel);
			}
			log.debug("getAllAccessoryGroup successfully");
			return lstAccessoryGroupModels;
		} catch (Exception e) {
			log.error(String.format(
					"getAllAccessoryGroup in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chori.service.AccessoryGroupService#addAccessoryGroup(com.chori.model
	 * .AccessoryGroupModel, java.lang.String)
	 */
	/**
	 * This function is used to add new AccessoryGroup
	 * 
	 * @param accessorygroupAddModel
	 * @param username
	 * @return
	 */
	@Override
	public boolean addAccessoryGroup(AccessoryGroupModel accessoryGroupModel,
			String username) {
		try {
			Accessorygroup accessorygroup = new Accessorygroup();
			accessorygroup.setAccessorygroupcode(accessoryGroupModel
					.getAccessorygroupCode());
			accessorygroup.setDescription(accessoryGroupModel.getDescription());
			accessorygroup.setCreatedate(new Date());
			accessorygroup.setUser(userDao.findById(username));

			dao.save(accessorygroup);
			log.debug("addAccessoryGroup successfullly");
			return true;
		} catch (Exception e) {
			log.debug("addAccessoryGroup fail");
			System.err
					.println(String
							.format("addAccessoryGroup with param 'accessoryGroupModel' in class: %s has error: %s",
									getClass(), e.getMessage()));
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chori.service.AccessoryGroupService#findAccessoryGroupById(java.lang
	 * .String)
	 */
	/**
	 * This function find 1 accessorygroupModel by accessoryGroupCode
	 * 
	 * @param accessoryGroupCode
	 * @return
	 */
	@Override
	public AccessoryGroupModel findAccessoryGroupById(String accessoryGroupCode) {
		log.info(String
				.format("findAccessoryGroupById with param 'accessoryGroupCode' in class: %s",
						getClass()));
		try {
			AccessoryGroupModel accessoryGroupModel = new AccessoryGroupModel();
			Accessorygroup accessorygroup = dao.findById(accessoryGroupCode);

			// field for NV

			accessoryGroupModel.setAccessorygroupCode(accessorygroup
					.getAccessorygroupcode());
			accessoryGroupModel.setDescription(accessorygroup.getDescription());
			accessoryGroupModel.setCreatedDate(accessorygroup.getCreatedate());
			accessoryGroupModel
					.setCreator(accessorygroup.getUser() == null ? ""
							: accessorygroup.getUser().getUsername());

			log.debug("findAccessoryGroupModelById successfully");
			return accessoryGroupModel;
		} catch (Exception e) {
			log.error(String
					.format("findAccessoryGroupModelById with param 'accessoryGroupCode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chori.service.AccessoryGroupService#editAccessoryGroup(com.chori.
	 * model.AccessoryGroupModel)
	 */
	/**
	 * This function is used to update an accessoryGroup
	 * 
	 * @param accessoryGroupAddModel
	 * 
	 * @return
	 */
	@Override
	public boolean editAccessoryGroup(AccessoryGroupModel accessoryGroupModel) {
		log.info(String
				.format("editAccessoryGroup with param  'accessoryGroupModel' in class: %s",
						getClass()));
		try {
			Accessorygroup accessorygroup = dao.findById(accessoryGroupModel
					.getAccessorygroupCode());
			accessorygroup.setDescription(accessoryGroupModel.getDescription());

			dao.update(accessorygroup);
			log.debug("editAccessoryGroup successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editAccessoryGroup with param 'getAccessorygroupCode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("editAccessoryGroup with param 'getAccessorygroupCode'in class: %s has error: %s",
									getClass(), e.getMessage()));
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chori.service.AccessoryGroupService#deleteAccessoryGroup(java.lang
	 * .String)
	 */
	/**
	 * This function is used to delete 1 accessorygroup
	 */
	@Override
	public boolean deleteAccessoryGroup(String accessoryGroupCode) {
		log.info(String
				.format("deleteAccessoryGroup with param 'accessoryGroupCode' in class: %s",
						getClass()));
		Accessorygroup accessorygroup = dao.findById(accessoryGroupCode);
		try {
			dao.delete(accessorygroup);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}