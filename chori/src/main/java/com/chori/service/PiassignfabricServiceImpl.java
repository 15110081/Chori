package com.chori.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.PIAssignFabricDetailDao;
import com.chori.dao.PiassignfabricDao;
import com.chori.dao.UserDao;
import com.chori.entity.Piassignfabric;
import com.chori.entity.PiassignfabricId;
import com.chori.model.PiassignfabricModel;

@Repository("piassignfabricService")
public class PiassignfabricServiceImpl extends
		AbstractServiceImpl<Piassignfabric, PiassignfabricId> implements
		PiassignfabricService {

	private PiassignfabricDao dao;

	@Autowired
	private UserDao userDao;

	@Autowired
	PIAssignFabricDetailDao piassignfabricdetaildao;

	@Autowired
	public PiassignfabricServiceImpl(
			@Qualifier("piassignfabricDao") AbstractDao<Piassignfabric, PiassignfabricId> abstractDao) {
		super(abstractDao);
		this.dao = (PiassignfabricDao) abstractDao;
	}

	@Override
	public PiassignfabricModel findPiassignfabricModelById(
			PiassignfabricId piassignfabricId) {
		log.info(String
				.format("findPiassignfabricModelById with param 'piassignfabricId' in class: %s",
						getClass()));
		try {
			PiassignfabricModel model = new PiassignfabricModel();
			Piassignfabric piaf = dao.findById(piassignfabricId);

			model.setFabricno(piaf.getFabricinformation().getFabricno());
			model.setLotnumber(piaf.getId().getLotnumber());

			log.debug("findPiassignfabricModelById successfully");
			return model;
		} catch (Exception e) {
			log.error(String
					.format("findPiassignfabricModelById with param 'piassignfabricId' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	@Override
	public boolean AssignFabricToPi(PiassignfabricModel piassignfabricModel,
			String userName) {
		log.info(String.format("AssignFabricToPi in class: %s", getClass()));
		try {

			Piassignfabric piassignfabric = new Piassignfabric();
			PiassignfabricId piassignfabricId = new PiassignfabricId();
			piassignfabricId.setFabricno(piassignfabricModel.getFabricno());
			piassignfabricId.setLotnumber(piassignfabricModel.getLotnumber());
			piassignfabric.setId(piassignfabricId);
			piassignfabric.setCreatedate(new Date());
			piassignfabric.setUser(userDao.findById(userName));
			if(!IsExistedPiAssignFabric(piassignfabricId)){
			dao.save(piassignfabric);
			log.debug("assign Fabric to Pi into database successfully");
			}
			return true;

		} catch (Exception e) {
			log.error(String
					.format("AssignFabricToPi with param 'PiassignfabricModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println("assign Fabric to Pi into database fail, method AssignFabricToPi(), class piassignfabricService");
			return false;
		}
	}

	@Override
	public boolean IsExistedPiAssignFabric(PiassignfabricId piassignfabricId) {
		try{
			log.info(String.format("IsExistedPiAssignFabric in class: %s",
					getClass()));
			if (null == dao.findById(piassignfabricId))
				return false;
			return true;
		}
		catch (Exception e) {
			log.error(String
					.format("AssignFabricToPi with param 'PiassignfabricModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println("assign Fabric to Pi into database fail, method AssignFabricToPi(), class piassignfabricService");
			throw e;
		}
	}	

}
