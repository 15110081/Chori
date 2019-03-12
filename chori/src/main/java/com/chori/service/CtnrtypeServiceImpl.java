package com.chori.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.CtnrtypeDao;
import com.chori.dao.UserDao;
import com.chori.entity.Ctnrtype;
import com.chori.model.CtnrtypeModel;

@Repository("ctnrtypeService")
public class CtnrtypeServiceImpl extends AbstractServiceImpl<Ctnrtype, String>
		implements CtnrtypeService {
	private CtnrtypeDao dao;

	@Autowired
	private UserDao userDao;

	public CtnrtypeServiceImpl() {
	}

	@Autowired
	public CtnrtypeServiceImpl(
			@Qualifier("ctnrtypeDao") AbstractDao<Ctnrtype, String> abstractDao) {
		super(abstractDao);
		this.dao = (CtnrtypeDao) abstractDao;
	}

	/**
	 * This function is used get all Containertype
	 * 
	 * @return list<CtnrtypeModel>
	 */

	@Override
	public List<CtnrtypeModel> getAllCtnrtypeModel() {
		log.info(String.format("getAllCtnrtype in class: %s", getClass()));
		try {
			log.debug("get all Container Type in DB after that return a list CtnrtypeEntity");
			List<Ctnrtype> lstCtnrtype = dao.getAll();

			CtnrtypeModel ctnrMod;
			List<CtnrtypeModel> lst = new ArrayList<CtnrtypeModel>();

			for (Ctnrtype ctnr : lstCtnrtype) {
				ctnrMod = new CtnrtypeModel();
				ctnrMod.setCtnrcode(ctnr.getCtnrcode());
				ctnrMod.setDescription(ctnr.getDescription());
				ctnrMod.setCreator(ctnr.getUser().getUsername());
				ctnrMod.setCreatedate(ctnr.getCreatedate());
				lst.add(ctnrMod);
			}
			log.debug("getAllCtnrtype successfully");
			return lst;
		} catch (Exception e) {
			log.error(String.format(
					"getAllCtnrtype in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function find a ctnrtype by id.
	 * 
	 * @param ctnrCode
	 * @return a CtnrtypeModel
	 */

	@Override
	public CtnrtypeModel findCtnrtypeEntityById(String ctnrCode) {
		log.info(String.format(
				"findCtnrtypeById with param 'ctnrCode' in class: %s",
				getClass()));
		try {
			CtnrtypeModel ctnrMod = new CtnrtypeModel();
			Ctnrtype ctnr = dao.findById(ctnrCode);

			ctnrMod.setCtnrcode(ctnr.getCtnrcode());
			ctnrMod.setDescription(ctnr.getDescription());
			ctnrMod.setCreator(ctnr.getUser().getUsername());
			ctnrMod.setCreatedate(ctnr.getCreatedate());
			log.debug("findCtnrtypeById successfully");
			return ctnrMod;
		} catch (Exception e) {
			log.error(String
					.format("findCtnrtypeEntityById with param 'ctnrCode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function delete a ctnrtype by id.
	 * 
	 * @param id
	 * @return delete => true; else => false
	 */

	@Override
	public boolean deleteCtnrtype(String id) {
		log.info(String.format(
				"deleteOrder with param 'ctnrtypeCode' in class: %s",
				getClass()));
		Ctnrtype ctnr = dao.findById(id);
		try {
			dao.delete(ctnr);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This function add a ctnrtype by ctnrtypeMod.
	 * 
	 * @param CtnrtypeModel
	 * @return add => true; else => false
	 */

	@Override
	public boolean addCtnrtype(CtnrtypeModel ctnrtypeMod, String userName) {
		log.info(String.format("addCtnrtype in class: %s", getClass()));
		try {
			Ctnrtype ctnr = new Ctnrtype();
			ctnr.setCtnrcode(ctnrtypeMod.getCtnrcode());
			ctnr.setDescription(ctnrtypeMod.getDescription());
			ctnr.setCreatedate(Calendar.getInstance().getTime());
			ctnr.setUser(userDao.findById(userName));

			dao.save(ctnr);
			log.debug("add new ctnr into database successfully");
			return true;
		} catch (Exception e) {
			log.debug("add new ctnr into database fail");

			System.err
					.println("add new ctnr into database fail, method addCtnrtype(), class CtnrtypeService");
			return false;
		}
	}

	/**
	 * This function check esixted a ctnrtype by ctnrtypeCode.
	 * 
	 * @param ctnrtypeCode
	 * @return existed => true; else => false
	 */
	@Override
	public boolean isCtnrtypeExistedById(String ctnrtypeCode) {
		if (null == dao.findById(ctnrtypeCode))
			return false;
		return true;
	}

	/**
	 * This function edit a ctnrtype by ctnrtypeMod.
	 * 
	 * @param ctnrtypeMod
	 * @return edit => true; else => false
	 */
	@Override
	public boolean editCtnrtype(CtnrtypeModel ctnrtypeMod, String userName) {
		log.info(String
				.format("editCtnrtype with param 'ctnrtypeMod', 'userName' in class: %s",
						getClass()));
		try {
			Ctnrtype ctnr = dao.findById(ctnrtypeMod.getCtnrcode());
			ctnr.setCtnrcode(ctnrtypeMod.getCtnrcode());
			ctnr.setDescription(ctnrtypeMod.getDescription());
			ctnr.setCreatedate(new Date());
			ctnr.setUser(userDao.findById(userName));
			dao.update(ctnr);
			log.debug("editCtnrtype successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editCtnrtype with param 'ctnrtypeMod', 'userName' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("editCtnrtype with param 'ctnrtypeMod', 'userName' in class: %s has error: %s",
									getClass(), e.getMessage()));
			return false;
		}
	}
}
