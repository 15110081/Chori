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
import com.chori.dao.UserDao;
import com.chori.entity.Garmentkind;
import com.chori.model.GarmentkindModel;

@Repository("garmentkindService")
public class GarmentkindServiceImpl extends
		AbstractServiceImpl<Garmentkind, String> implements GarmentkindService {

	private GarmentkindDao dao;

	@Autowired
	private UserDao userDao;

	// public GarmentkindServiceImpl() {
	//
	// }

	@Autowired
	public GarmentkindServiceImpl(AbstractDao<Garmentkind, String> abstractDao,
			GarmentkindDao dao) {
		super(abstractDao);
		this.dao = dao;
	}

	/**
	 * This function check if there is an gamentkind with gmkCode existed in
	 * database
	 * 
	 * @param gmkCode
	 * @return false if not existed, true if existed
	 */
	public boolean isGarmentkindExistedById(String gmkCode) {
		if (null == dao.findById(gmkCode))
			return false;
		return true;
	}

	/**
	 * This function is used get all Gamentkind
	 * 
	 * @return list<GarmentkindModel>
	 */
	public List<GarmentkindModel> getAllGarmentkind() {
		log.debug("in garment service list");
		try {
			List<Garmentkind> lstGarmentkind = dao.getAll();
			GarmentkindModel garmentkindMo;
			List<GarmentkindModel> lstGarmentkindModel = new ArrayList<GarmentkindModel>();
			for (Garmentkind gmk : lstGarmentkind) {
				garmentkindMo = new GarmentkindModel();
				garmentkindMo.setGarmentkindcode(gmk.getGarmentkindcode());
				garmentkindMo.setDescription(gmk.getDescription());
				garmentkindMo.setCreatedate(gmk.getCreatedate());
				garmentkindMo.setCreator(gmk.getUser() == null ? "" : gmk
						.getUser().getUsername());

				lstGarmentkindModel.add(garmentkindMo);
			}
			return lstGarmentkindModel;
		} catch (Exception e) {
			log.error(String.format(
					"getAllGarmentkind in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new garmentkind into database
	 * 
	 * @param garmentkindMo
	 * @param userId
	 * @return true if add successfully, false if have exception
	 */
	public boolean addGarmentkind(GarmentkindModel garmentkindMo,
			String username) {
		try {
			Garmentkind garmentkind = new Garmentkind();
			garmentkind.setGarmentkindcode(garmentkindMo.getGarmentkindcode());
			garmentkind.setDescription(garmentkindMo.getDescription());
			garmentkind.setCreatedate(Calendar.getInstance().getTime());
			garmentkind.setUser(userDao.findById(username));
			dao.save(garmentkind);
			log.debug("addGarmentkind successfullly");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("addGarmentkind with param 'garmentkindMo' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("addGarmentkind with param 'garmentkindMo' in class: %s has error: %s",
									getClass(), e.getMessage()));
			return false;
		}

	}

	/**
	 * This function find a garmentkindModel by garmentkindcode.
	 * 
	 * @param garmentkindcode
	 * @return a GarmentkindModel
	 */
	public GarmentkindModel findGarmentkindModelById(String garmentkindcode) {
		log.info(String
				.format("findGarmentkindModelById with param 'garmentkindcode' in class: %s",
						getClass()));
		try {
			GarmentkindModel gmkMo = new GarmentkindModel();
			Garmentkind gmkEt = dao.findById(garmentkindcode);
			// field for NV
			gmkMo.setGarmentkindcode(gmkEt.getGarmentkindcode());
			gmkMo.setDescription(gmkEt.getDescription());
			gmkMo.setCreator(gmkEt.getUser() == null ? "" : gmkEt.getUser()
					.getUsername());
			gmkMo.setCreatedate(gmkEt.getCreatedate());

			log.debug("findGarmentkindModelById successfully");
			return gmkMo;
		} catch (Exception e) {
			log.error(String
					.format("findUnitEntityById with param 'Garmentkindcode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit Garmentkind into database
	 * 
	 * @param GarmentkindModel
	 * @param userId
	 * @return true if edit successfully, false if have exception
	 */
	public boolean editGarmentkind(GarmentkindModel garmentkindModel) {
		log.info(String.format(
				"editUnit with param 'unitEn', 'unitCode' in class: %s",
				getClass()));
		try {
			Garmentkind gmkEn = dao.findById(garmentkindModel
					.getGarmentkindcode());
			gmkEn.setDescription(garmentkindModel.getDescription());
			dao.update(gmkEn);
			log.debug("editGarmentkind successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editGarmentkind with param 'garmentkindModel', 'garmentkindCode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("editGarmentkind with param 'garmentkindModel', 'garmentkindCode' in class: %s has error: %s",
									getClass(), e.getMessage()));
			return false;
		}
	}

	/**
	 * This function is used to delete an Garmentkind in database.
	 * 
	 * @param garmentkindId
	 * @return delete successfully =>true , else => false
	 */
	public boolean deleteGarmentkind(String garmentkindcode) {
		log.info(String.format(
				"deleteGarmentkind with param 'deleteGarmentkind' in class: %s",
				getClass()));
		Garmentkind gmk = dao.findById(garmentkindcode);
		try {
			dao.delete(gmk);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
