package com.chori.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.GarmentkindDao;
import com.chori.dao.PackingguideDao;
import com.chori.dao.UserDao;
import com.chori.entity.Garmentkind;
import com.chori.entity.Packingguide;
import com.chori.model.GarmentkindModel;
import com.chori.model.PackingguideModel;

@Repository("packingguiService")
public class PackingguideServiceImpl extends
AbstractServiceImpl<Packingguide, String> implements PackingguideService {
	
	private PackingguideDao dao;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	public PackingguideServiceImpl(AbstractDao<Packingguide, String> abstractDao,
			PackingguideDao dao) {
		super(abstractDao);
		this.dao = dao;
	}
	
	/**
	 * This function check if there is an packingguide with pkgCode existed in
	 * database
	 * 
	 * @param pkgCode
	 * @return false if not existed, true if existed
	 */
	public boolean isPackkinguideExistedById(String pkgCode) {
		if (null == dao.findById(pkgCode))
			return false;
		return true;
	}
	
	/**
	 * This function is used get all Packingguide
	 * 
	 * @return list<PackingguideModel>
	 */
	public List<PackingguideModel> getAllPackingguide() {
		log.debug("in Packingguide service list");
		try {
			List<Packingguide> lstPackingguide = dao.getAll();
			PackingguideModel packingguideModel;
			List<PackingguideModel> lstPackingguideModel = new ArrayList<PackingguideModel>();
			for (Packingguide pkg : lstPackingguide) {
				packingguideModel = new PackingguideModel();
				packingguideModel.setPackingguidecode(pkg.getCode());
				packingguideModel.setDescription(pkg.getDescription());
				packingguideModel.setCreatedate(pkg.getCreatedate());
				packingguideModel.setCreator(pkg.getUser() == null ? "" : pkg.getUser().getUsername());
				lstPackingguideModel.add(packingguideModel);
			}
			return lstPackingguideModel;
		} catch (Exception e) {
			log.error(String.format(
					"getAllPackingguide in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to add new packingguide into database
	 * 
	 * @param packingguideMo
	 * @param userId
	 * @return true if add successfully, false if have exception
	 */
	public boolean addPackingguide(PackingguideModel packingguideModel,
			String username) {
		try {
			Packingguide packingguide = new Packingguide();
			
			packingguide.setCode(packingguideModel.getPackingguidecode());
			packingguide.setDescription(packingguideModel.getDescription());
			packingguide.setCreatedate(Calendar.getInstance().getTime());
			packingguide.setUser(userDao.findById(username));
			
			
			dao.save(packingguide);
			log.debug("addPackingguide successfullly");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("addPackingguide with param 'packingguideModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("addPackingguide with param 'packingguideModel' in class: %s has error: %s",
									getClass(), e.getMessage()));
			return false;
		}

	}
	
	/**
	 * This function find a packingguideModel by packingguicode.
	 * 
	 * @param packingguicode
	 * @return a packingguideModel
	 */
	public PackingguideModel findPackingguideModelById(String packingguicode) {
		log.info(String
				.format("findPackingguideModelById with param 'packingguicode' in class: %s",
						getClass()));
		try {
			PackingguideModel packingguideModel = new PackingguideModel();
			Packingguide pkg = dao.findById(packingguicode);
			// field for NV
			
			packingguideModel.setPackingguidecode(pkg.getCode());
			packingguideModel.setDescription(pkg.getDescription());
			packingguideModel.setCreator(pkg.getUser() == null ? "" : pkg.getUser().getUsername());
			packingguideModel.setCreatedate(pkg.getCreatedate());
			
			log.debug("findPackingguideModelById successfully");
			return packingguideModel;
		} catch (Exception e) {
			log.error(String
					.format("findPackingguideModelById with param 'packingguicode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to edit Packingguide into database
	 * 
	 * @param packingguideModel
	 * @param userId
	 * @return true if edit successfully, false if have exception
	 */
	public boolean editPackingguide(PackingguideModel packingguideModel) {
		log.info(String.format(
				"editPackingguide with param 'pkg', 'packingguidecode' in class: %s",
				getClass()));
		try {
			Packingguide pkg = dao.findById(packingguideModel
					.getPackingguidecode());
			pkg.setDescription(packingguideModel.getDescription());
			dao.update(pkg);
			log.debug("editPackingguide successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editPackingguide with param 'packingguideModel', 'packingguicode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("editPackingguide with param 'packingguideModel', 'packingguicode' in class: %s has error: %s",
									getClass(), e.getMessage()));
			return false;
		}
	}
	
	/**
	 * This function is used to delete an Packingguide in database.
	 * 
	 * @param packingguideCode
	 * @return delete successfully =>true , else => false
	 */
	public boolean deletePackingguide(PackingguideModel packingguideModel) {
		log.info(String.format(
				"deletePackingguide with param 'deletePackingguide' in class: %s",
				getClass()));
		Packingguide pkg = dao.findById(packingguideModel.getPackingguidecode());
		try {
			dao.delete(pkg);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
