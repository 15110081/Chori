package com.chori.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.DestinationDao;
import com.chori.dao.UserDao;
import com.chori.entity.Destination;
import com.chori.model.DestinationModel;

@Repository("destinationService")
public class DestinationServiceImpl extends
		AbstractServiceImpl<Destination, String> implements DestinationService {

	private DestinationDao dao;

	@Autowired
	private UserDao userDao;

	@Autowired
	public DestinationServiceImpl(AbstractDao<Destination, String> abstractDao,
			DestinationDao dao) {
		super(abstractDao);
		this.dao = dao;
	}

	/**
	 * This function is used get all Destination
	 * 
	 * @return list<DestinationModel>
	 */
	public List<DestinationModel> getAllDestination() {
		log.debug("in destination service list");
		try {
			List<Destination> lstDestination = dao.getAll();
			DestinationModel destinationMo;
			List<DestinationModel> lstDestinationModel = new ArrayList<DestinationModel>();
			for (Destination des : lstDestination) {
				destinationMo = new DestinationModel();
				destinationMo.setDestinationcode(des.getDestinationcode());
				destinationMo.setDescription(des.getDescription());
				destinationMo.setCreatedate(des.getCreatedate());
				destinationMo.setCreator(des.getUser() == null ? "" : des
						.getUser().getUsername());

				lstDestinationModel.add(destinationMo);
			}
			log.debug("getAllDestination successfully");
			return lstDestinationModel;

		} catch (Exception e) {
			log.error(String.format(
					"getAllDestination in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new destination into database
	 * 
	 * @param destinationMo
	 * @param userId
	 * @return true if add successfully, false if have exception
	 */
	public boolean addDestination(DestinationModel destinationMo,
			String username) {
		try {
			Destination des = new Destination();
			des.setDestinationcode(destinationMo.getDestinationcode());
			des.setDescription(destinationMo.getDescription());
			des.setCreatedate(Calendar.getInstance().getTime());
			des.setUser(userDao.findById(username));

			dao.save(des);
			log.debug("addDestination successfullly");
			return true;
		} catch (Exception e) {
			log.debug("add new Destination into database fail");
			System.err
					.println(String
							.format("addDestination with param 'destinationMo' in class: %s has error: %s",
									getClass(), e.getMessage()));
			return false;
		}

	}

	/**
	 * This function find a destinationModel by destinationcode.
	 * 
	 * @param destinationcode
	 * @return a DestinationModel
	 */
	public DestinationModel findDestinationModelById(String destinationcode) {
		log.info(String
				.format("findDestinationModelById with param 'destinationcode' in class: %s",
						getClass()));
		try {
			DestinationModel destinationMo = new DestinationModel();
			Destination des = dao.findById(destinationcode);
			// field for NV
			destinationMo.setDestinationcode(des.getDestinationcode());
			destinationMo.setDescription(des.getDescription());
			destinationMo.setCreatedate(des.getCreatedate());
			destinationMo.setCreator(des.getUser() == null ? "" : des.getUser()
					.getUsername());

			log.debug("findDestinationModelById successfully");
			return destinationMo;
		} catch (Exception e) {
			log.error(String
					.format("findDestinationModelById with param 'destinationcode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit Destination into database
	 * 
	 * @param destinationMo
	 * @param userId
	 * @return true if edit successfully, false if have exception
	 */
	public boolean editDestiantion(DestinationModel destinationMo) {
		log.info(String
				.format("editDestiantion with param 'destinationMo', 'destinationcode' in class: %s",
						getClass()));
		try {
			Destination des = dao.findById(destinationMo.getDestinationcode());
			des.setDescription(destinationMo.getDescription());

			dao.update(des);
			log.debug("editDestiantion successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editDestiantion with param 'destinationMo', 'destinationcode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("editDestiantion with param 'destinationMo', 'destinationcode' in class: %s has error: %s",
									getClass(), e.getMessage()));
			return false;
		}
	}

	/**
	 * This function check if there is an destination with destinationcode
	 * existed in database
	 * 
	 * @param destinationcode
	 * @return false if not existed, true if existed
	 */
	public boolean isDestinationExistedById(String destinationcode) {
		if (null == dao.findById(destinationcode))
			return false;
		return true;
	}

	/**
	 * This function is used to Destiantion an Color in database.
	 * 
	 * @param destinationId
	 * @return delete successfully =>true , else => false
	 */
	public boolean deleteDestination(DestinationModel destinationModel) {
		log.info(String.format(
				"deleteDestination with param 'deleteDestination' in class: %s",
				getClass()));
		Destination des = dao.findById(destinationModel.getDestinationcode());
		try {
			dao.delete(des);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
