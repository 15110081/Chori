package com.chori.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.EstimatetimeDao;
import com.chori.dao.UserDao;
import com.chori.entity.Estimatetime;
import com.chori.model.EstimatetimeModel;

@Repository("estimatetimeService")
public class EstimatetimeServiceImpl extends
		AbstractServiceImpl<Estimatetime, String> implements
		EstimatetimeService {
	private EstimatetimeDao dao;
	private UserDao userDao;

	@Autowired
	public EstimatetimeServiceImpl(
			@Qualifier("estimatetimeDao") AbstractDao<Estimatetime, String> abstractDao) {
		super(abstractDao);
		this.dao = (EstimatetimeDao) abstractDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chori.service.EstimatetimeService#findEstimatetimeModelById(int)
	 */
	/**
	 * This function find 1 EstimatetimeModel by estimatetimeCode
	 * 
	 * @param EstimatetimeModel
	 * @return
	 */
	@Override
	public EstimatetimeModel findEstimatetimeModelById(int estimatetimeCode) {
		log.info(String.format(
				"findUnitModelById with param 'unitcode' in class: %s",
				getClass()));
		try {
			EstimatetimeModel em = new EstimatetimeModel();
			Estimatetime est = dao.findById(estimatetimeCode);

			// field for NV
			em.setManuaccdelv(est.getManuaccdelv());
			em.setPiconpletion(est.getPiconpletion());
			em.setPackingaccdelv(est.getPackingaccdelv());
			em.setCreatedate(est.getCreatedate());

			log.debug("findStatusEntityById successfully");
			return em;
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
	 * @see
	 * com.chori.service.EstimatetimeService#editEstimatetime(com.chori.model
	 * .EstimatetimeModel, java.lang.String)
	 */
	/**
	 * This function is used to update an Estimatetime
	 * 
	 * @param EstimatetimeModel
	 * 
	 * @return
	 */
	@Override
	public boolean editEstimatetime(EstimatetimeModel estimatetimeModel,
			String userName) {
		log.info(String.format(
				"editUnit with param 'unitEn', 'unitCode' in class: %s",
				getClass()));
		try {
			Estimatetime est = dao
					.findById(estimatetimeModel.getEstimateCode());

			est.setPiconpletion(estimatetimeModel.getPiconpletion());
			est.setManuaccdelv(estimatetimeModel.getManuaccdelv());
			est.setPackingaccdelv(estimatetimeModel.getPackingaccdelv());
			est.setUser(userDao.findById(userName));
			est.setCreatedate(new Date());
			dao.update(est);
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

	/**
	 * This function is used get all Estimatetime
	 * 
	 * @return list<EstimatetimeModel>
	 */
	@Override
	public List<EstimatetimeModel> getAllEstimatetimeModel() {
		log.debug("in unit service list");
		try {
			List<Estimatetime> lsest = dao.getAll();
			EstimatetimeModel estm;
			List<EstimatetimeModel> lst = new ArrayList<EstimatetimeModel>();
			for (Estimatetime est : lsest) {
				estm = new EstimatetimeModel();
				estm.setEstimateCode(est.getEstimatetimecode());
				estm.setPiconpletion(est.getPiconpletion());
				estm.setManuaccdelv(est.getManuaccdelv());
				estm.setPackingaccdelv(est.getPackingaccdelv());
				estm.setCreatedate(est.getCreatedate());
				lst.add(estm);
			}
			return lst;
		} catch (NullPointerException ne) {
			log.error("list service estimate err: " + ne.getMessage());
		}
		return null;
	}

}
