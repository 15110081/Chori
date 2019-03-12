package com.chori.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.UnitDao;
import com.chori.entity.Unit;
import com.chori.model.UnitModel;

@Repository("unitService")
public class UnitServiceImpl extends AbstractServiceImpl<Unit, String>
		implements UnitService {
	private UnitDao dao;

	@Autowired
	public UnitServiceImpl(
			@Qualifier("unitDao") AbstractDao<Unit, String> abstractDao) {
		super(abstractDao);
		this.dao = (UnitDao) abstractDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chori.service.UnitService#getAllUnitModel()
	 */
	/**
	 * This function is used get all Unit
	 * 
	 * @return list<UnitModel>
	 */
	@Override
	public List<UnitModel> getAllUnitModel() {
		log.debug("in unit service list");
		try {
			List<Unit> lstUnit = dao.getAll();
			UnitModel unitEn;
			List<UnitModel> lst = new ArrayList<UnitModel>();
			for (Unit unt : lstUnit) {
				unitEn = new UnitModel();
				unitEn.setUnitcode(unt.getUnitcode());
				unitEn.setName(unt.getName());
				unitEn.setCreatedate(unt.getCreatedate());

				lst.add(unitEn);
			}
			return lst;
		} catch (NullPointerException ne) {
			log.error("list service estimate err: " + ne.getMessage());
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chori.service.UnitService#findUnitModelById(java.lang.String)
	 */
	/**
	 * This function find 1 UnitModel by unitcode
	 * 
	 * @param unitcode
	 * @return
	 */
	@Override
	public UnitModel findUnitModelById(String unitcode) {
		log.info(String.format(
				"findUnitModelById with param 'unitcode' in class: %s",
				getClass()));
		try {
			UnitModel ue = new UnitModel();
			Unit unt = dao.findById(unitcode);

			// field for NV
			ue.setUnitcode(unt.getUnitcode());
			ue.setName(unt.getName().toString());

			log.debug("findStatusEntityById successfully");
			return ue;
		} catch (Exception e) {
			log.error(String
					.format("findUnitEntityById with param 'unitCode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chori.service.UnitService#editUnit(com.chori.model.UnitModel)
	 */
	/**
	 * This function is used to update an Unit
	 * 
	 * @param unitEn
	 * 
	 * @return
	 */
	@Override
	public boolean editUnit(UnitModel unitEn) {
		log.info(String.format(
				"editUnit with param 'unitEn', 'unitCode' in class: %s",
				getClass()));
		try {
			Unit unt = dao.findById(unitEn.getUnitcode());

			unt.setName(unitEn.getName());
			unt.setCreatedate(new Date());
			dao.update(unt);
			log.debug("editUnit successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editUnit with param 'unitEn', 'unitCode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("editUnit with param 'unitEn', 'unitCode' in class: %s has error: %s",
									getClass(), e.getMessage()));
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chori.service.UnitService#deleteUnit(java.lang.String)
	 */
	@Override
	public boolean deleteUnit(String unitCode) {
		log.info(String.format("deleteUnit with param 'unitCode' in class: %s",
				getClass()));
		try {
			Unit or = dao.findById(unitCode);
			dao.delete(or);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chori.service.UnitService#addUnit(com.chori.model.UnitModel)
	 */
	/**
	 * This function is used to add new Unit
	 * 
	 * @param unitEn
	 * @return
	 */
	@Override
	public boolean addUnit(UnitModel unitEn) {
		try {
			Unit unt = new Unit();
			unt.setUnitcode(unitEn.getUnitcode());
			unt.setName(unitEn.getName());
			unt.setCreatedate(new Date());
			dao.save(unt);
			log.debug("addUnit successfullly");
			return true;
		} catch (Exception e) {
			log.error(String.format(
					"addUnit with param 'unitEn' in class: %s has error: %s",
					getClass(), e.getMessage()));
			System.err.println(String.format(
					"adddUnit with param 'unitEn' in class: %s has error: %s",
					getClass(), e.getMessage()));
			return false;
		}

	}

	/**
	 * This function is used check isExisted of Unit in db
	 * 
	 * @return true or false
	 */
	public boolean isUnitExistedById(String unitCode) {
		if (null == dao.findById(unitCode))
			return false;
		return true;
	}
}
