package com.chori.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.controller.LoginController;
import com.chori.dao.GarmentConsumptionDao;
import com.chori.dao.GarmentConsumptionDetailDao;
import com.chori.dao.UserDao;
import com.chori.dao.WidthDao;
import com.chori.entity.Garmentconsumptiondetail;
import com.chori.model.GarmentConsumptionDetailModel;

@Repository("garmentconsumptiondetailService")
public class GarmentConsumptionDetailServiceImpl extends
		AbstractServiceImpl<Garmentconsumptiondetail, Integer> implements
		GarmentConsumptionDetailService {

	private GarmentConsumptionDetailDao dao;

	public GarmentConsumptionDetailServiceImpl() {
	}

	@Autowired
	public GarmentConsumptionDetailServiceImpl(
			@Qualifier("garmentconsumptiondetailDao") AbstractDao<Garmentconsumptiondetail, Integer> abstractDao) {
		super(abstractDao);
		this.dao = (GarmentConsumptionDetailDao) abstractDao;
	}

	@Autowired
	GarmentConsumptionDao garconDao;

	@Autowired
	UserDao userDao;

	@Autowired
	WidthDao widthDao;

	/**
	 * This function is used to add new Garment Consumption Detail
	 * 
	 * @param garconDetailModel
	 *            , userName
	 * @return true if add successful, else return false
	 */
	public boolean addNewGarmentConsumptionDetail(
			GarmentConsumptionDetailModel garconDetailModel, String userName) {
		try {
			if(garconDao.findById(garconDetailModel.getGarmentconsumption())!=null
				&& widthDao.findById(garconDetailModel.getWidth())!=null
				&& garconDetailModel.getConvalue() >= 0)
			{						
				Garmentconsumptiondetail garconDetail = new Garmentconsumptiondetail();
				garconDetail.setConvalue(garconDetailModel.getConvalue());
				// garconDetail.setCreatedate(new Date());
				garconDetail.setGarmentconsumption(garconDao
						.findById(garconDetailModel.getGarmentconsumption()));
				garconDetail.setUser(userDao.findById(userName));
				garconDetail.setWidth(widthDao.findById(garconDetailModel
						.getWidth()));
				garconDetail.setCreatedate(new Date());
				dao.save(garconDetail);

				System.err.println("Saving Garment Consumption Detail Successful");
				return true;
			}
			return false;
		}
		catch (Exception e) {
			System.err.println("Saving Garment Consumption Detail Failed");
			throw e;
		}
	}

	/**
	 * This function is used to edit a garment consumption detail into database
	 * 
	 * @param garconDetailModel
	 * @return true if edit successful, false if have exception
	 */
	public boolean editGarmentConsumptionDetail(
			GarmentConsumptionDetailModel garconDetailModel) {
		log.info(String
				.format("editGarmentConsumptionDetail with param 'garconDetailModel' in class: %s",
						getClass()));
		try {
			Garmentconsumptiondetail garconDetail = null;
			// handle if add new width => auto add new width
			if (dao.findById(garconDetailModel
					.getGarmentconsumptiondetailcode()) == null) {
				String userName = LoginController.currentUser;
				addNewGarmentConsumptionDetail(garconDetailModel, userName);
			} else {
//				if(widthDao.findById(garconDetailModel.getWidth()) != null)
//				{
//					return false;
//				}
				garconDetail = dao.findById(garconDetailModel
						.getGarmentconsumptiondetailcode());
				garconDetail.setConvalue(garconDetailModel.getConvalue());
				
				garconDetail.setWidth(widthDao.findById(garconDetailModel
						.getWidth()));
			}
			dao.update(garconDetail);
			log.debug("editGarmentConsumptionDetail successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editGarmentConsumptionDetail with param 'garconDetailModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("editGarmentConsumptionDetail with param 'garconDetailModel' in class: %s has error: %s",
									getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to check garment consumption detail is existed
	 * @param garconDetailModel
	 * @return true if is existed, false if not
	 */
	public boolean isExistedGarmentConsumptionDetail(GarmentConsumptionDetailModel garconDetailModel) {
		try {
			List<Garmentconsumptiondetail> lstGarmentconsumptiondetail = dao.getAll();
			if(lstGarmentconsumptiondetail.size()>0)
			{
				for(Garmentconsumptiondetail garmentconsumptiondetail : lstGarmentconsumptiondetail)
				{
					if(garconDao.findById(garconDetailModel.getGarmentconsumption())!=null
							&& widthDao.findById(garconDetailModel.getWidth())!=null)
					{			
						if(garmentconsumptiondetail.getGarmentconsumption().getGarmentconsumptioncode().equals(garconDetailModel.getGarmentconsumption())
								&& garmentconsumptiondetail.getWidth().getWidthcode().equals(garconDetailModel.getWidth())){
							log.debug("isExistedGarmentConsumptionDetail successfully");
							return true;
						}
					}
				}
			}
			return false;
		}
		catch (Exception e) {
			log.error(String
					.format("isExistedGarmentConsumptionDetail with param 'garconDetailModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to add new Garment Consumption Detail by garment style
	 * @param garconDetailModel, userName
	 * @return true if add successful, else return false
	 */
	@Override
	public boolean addNewGarmentConsumptionDetailByGarmentStyle(GarmentConsumptionDetailModel garconDetailModel, String userName) {
		try {
			//add new if not existed
			if(!isExistedGarmentConsumptionDetail(garconDetailModel))
			{
				addNewGarmentConsumptionDetail(garconDetailModel, userName);
				log.debug("addNewGarmentConsumptionDetail successfully");
			}
			//edit if existed
			else
			{		
				if(findGarmentConsumptionDetailCode(garconDetailModel) != null)
				{
					Garmentconsumptiondetail garconDetail = null;
					garconDetail = dao.findById(findGarmentConsumptionDetailCode(garconDetailModel));
					garconDetail.setConvalue(garconDetailModel.getConvalue());
					log.debug("editGarmentConsumptionDetailCode successfully");
					dao.update(garconDetail);
				}

			}
			return true;
		}
		catch (Exception e) {
			System.err.println("Saving Garment Consumption Detail Failed");
			throw e;
		}
	}
	
	/**
	 * This function is used to find garment consumption detail code by garmentconsumptioncode + width code
	 * @param garconDetailModel, userName
	 * @return true if add successful, else return false
	 */
	public Integer findGarmentConsumptionDetailCode(GarmentConsumptionDetailModel garconDetailModel) {
		try {
			List<Garmentconsumptiondetail> lstGarmentconsumptiondetail = dao.getAll();
			if(lstGarmentconsumptiondetail.size()>0)
			{
				for(Garmentconsumptiondetail garmentconsumptiondetail : lstGarmentconsumptiondetail)
				{
					if(garconDao.findById(garconDetailModel.getGarmentconsumption())!=null
							&& widthDao.findById(garconDetailModel.getWidth())!=null)
					{			
						if(garmentconsumptiondetail.getGarmentconsumption().getGarmentconsumptioncode().equals(garconDetailModel.getGarmentconsumption())
								&& garmentconsumptiondetail.getWidth().getWidthcode().equals(garconDetailModel.getWidth())){
							log.debug("findGarmentConsumptionDetailCode successfully");
							return garmentconsumptiondetail.getGarmentconsumptiondetailcode();
						}
					}
				}
			}
			return null;
		}
		catch (Exception e) {
			System.err.println("findGarmentConsumptionDetailCode Failed");
			throw e;
		}
	}
}
