package com.chori.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.GarmentstyleDao;
import com.chori.dao.GarmentstylereferpriceDao;
import com.chori.dao.TypeDao;
import com.chori.entity.Garmentstylereferprice;
import com.chori.entity.GarmentstylereferpriceId;
import com.chori.entity.Type;
import com.chori.model.GarmentstyleModel;
import com.chori.model.GarmentstylereferpriceModel;

@Repository("garmentstylereferpriceService")
public class GarmentstylereferpriceServiceImpl extends
AbstractServiceImpl<Garmentstylereferprice, GarmentstylereferpriceId>
implements GarmentstylereferpriceService {
	private GarmentstylereferpriceDao dao;
	
	@Autowired
	private GarmentstyleDao garmentDao;
	
	@Autowired
	private TypeDao typeDao;
	
	@Autowired
	public GarmentstylereferpriceServiceImpl(
			@Qualifier("garmentstylereferpriceDao") AbstractDao<Garmentstylereferprice, GarmentstylereferpriceId> abstractDao) {
		super(abstractDao);
		this.dao = (GarmentstylereferpriceDao) abstractDao;
	}
	
	/**
	 * This function is used to add Garment style refer price (dozen) For Garment
	 */
	public boolean addGarmentstylereferpriceForGarment(GarmentstyleModel garmentstyleModel,
			List<GarmentstylereferpriceModel> lstGarmentstylereferpriceModel) {
		log.info(String.format("addGarmentstylereferpriceForGarment in class: %s", getClass()));
		try {
			GarmentstylereferpriceId garmentstylereferpriceId;
			Garmentstylereferprice garmentstylereferprice;
			
			for (GarmentstylereferpriceModel garmentstylereferpriceModel : lstGarmentstylereferpriceModel) {
				garmentstylereferpriceId = new GarmentstylereferpriceId();
				garmentstylereferpriceId.setGarmentstylecode(garmentstyleModel
						.getGarmentstylecode()+"@@@"+garmentstyleModel
						.getCustomercode());
				garmentstylereferpriceId.setTypecode(garmentstylereferpriceModel.getTypecode());
				
				garmentstylereferprice = new Garmentstylereferprice();
				garmentstylereferprice.setId(garmentstylereferpriceId);
				garmentstylereferprice.setGarmentstyle(garmentDao.findById(garmentstyleModel
						.getGarmentstylecode()+"@@@"+garmentstyleModel
						.getCustomercode()));
				garmentstylereferprice.setType(typeDao.findById(garmentstylereferpriceModel.getTypecode()));
				garmentstylereferprice.setReferprice(garmentstylereferpriceModel.getReferprice());
				
				if(garmentstylereferprice.getReferprice()!=null){
					dao.save(garmentstylereferprice);
				}
			}
			
			log.debug("addGarmentstylereferpriceForGarment into database successfully");
			return true;
		} catch (Exception e) {
			log.error(String.format(
					"addGarmentstylereferpriceForGarment in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to get List Garment style refer price Model By Garment style Code
	 * @param garmentStyleCode
	 * @return
	 */
	public List<GarmentstylereferpriceModel> getListGarmentstylereferpriceModelByGarmentstyleCode(String garmentStyleCode) {
		log.info(String.format("getListGarmentstylereferpriceModelByGarmentstyleCode in class: %s", getClass()));
		try {
			GarmentstylereferpriceModel garmentstylereferpriceModel;
			List<Garmentstylereferprice> lst = dao.getListGarmentstylereferpriceByGarmentstyleCode(garmentStyleCode);
			List<GarmentstylereferpriceModel> lsrResult = new ArrayList<GarmentstylereferpriceModel>();
			
			//lấy list type code
			List<Type> lstType = typeDao.getAll();
			for (Type type : lstType) {
				garmentstylereferpriceModel = new GarmentstylereferpriceModel();
				garmentstylereferpriceModel.setGarmentstylecode(garmentStyleCode);
				garmentstylereferpriceModel.setTypecode(type.getTypecode());
				//lặp qua lst, nếu trùng thì gán
				for (Garmentstylereferprice garmentstylereferprice : lst) {
//					garmentstylereferpriceModel = new GarmentstylereferpriceModel();
					if(garmentstylereferprice.getType().getTypecode().equals(type.getTypecode())){
						garmentstylereferpriceModel.setReferprice(garmentstylereferprice.getReferprice());
					}
				}
				
				lsrResult.add(garmentstylereferpriceModel);
			}
//			List<GarmentstylereferpriceModel> 
			
			log.debug("getListGarmentstylereferpriceModelByGarmentstyleCode into database successfully");
			return lsrResult;
		} catch (Exception e) {
			log.error(String.format(
					"getListGarmentstylereferpriceModelByGarmentstyleCode in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to edit GarmentstylereferpriceForGarment
	 * @param garmentstyleModel
	 * @param lstGarmentstylereferpriceModel
	 * @return
	 */
	public boolean editGarmentstylereferpriceForGarment(GarmentstyleModel garmentstyleModel,
			List<GarmentstylereferpriceModel> lstGarmentstylereferpriceModel) {
		log.info(String.format("addGarmentstylereferpriceForGarment in class: %s", getClass()));
		try {
			//tạo list mới
			List<GarmentstylereferpriceModel> lstNew= new ArrayList<GarmentstylereferpriceModel>();
			//lặp qua list lấy về, nào có refer price mà != null thì add
			for (GarmentstylereferpriceModel garmentstylereferpriceModel : lstGarmentstylereferpriceModel) {
				if(garmentstylereferpriceModel.getReferprice()!=null){
					lstNew.add(garmentstylereferpriceModel);
				}
			}
			
			//lấy ra list cũ
			List<Garmentstylereferprice> lstOld = dao.getListGarmentstylereferpriceByGarmentstyleCode(garmentstyleModel.getGarmentstylecode());
			
			//tạo list update
			List<GarmentstylereferpriceModel> lstUpdate = new ArrayList<GarmentstylereferpriceModel>();
			//tạo list add
			List<GarmentstylereferpriceModel> lstAdd = new ArrayList<GarmentstylereferpriceModel>();
			//tạo list delete
			List<Garmentstylereferprice> lstDelete = new ArrayList<Garmentstylereferprice>();
			
			
			boolean isExisted= false;
			
			//add ptu vô list update
			for (GarmentstylereferpriceModel garmentstylereferpriceModel : lstNew) {
				for (Garmentstylereferprice garmentstylereferprice : lstOld) {
					if(garmentstylereferpriceModel.getTypecode().equals(garmentstylereferprice.getType().getTypecode())){
						isExisted = true;
					}
				}
				
				if(isExisted){//add ptu vô list update
					lstUpdate.add(garmentstylereferpriceModel);
				}
				if(!isExisted){//add vô list add
					lstAdd.add(garmentstylereferpriceModel);
				}
				isExisted = false;
			}
			
			//add vô list delete
			for (Garmentstylereferprice garmentstylereferprice : lstOld) {
				for (GarmentstylereferpriceModel garmentstylereferpriceModel : lstNew) {
					if(garmentstylereferpriceModel.getTypecode().equals(garmentstylereferprice.getType().getTypecode())){
						isExisted = true;
					}
				}
				
				if(!isExisted){//add vô list delete
					lstDelete.add(garmentstylereferprice);
				}
				isExisted = false;
			}
			
			GarmentstylereferpriceId garmentstylereferpriceId;
			Garmentstylereferprice garmentstylereferprice;
			
			//lặp qua list update
			for (GarmentstylereferpriceModel garmentstylereferpriceModel : lstUpdate) {
				garmentstylereferpriceId = new GarmentstylereferpriceId();
				garmentstylereferpriceId.setGarmentstylecode(garmentstyleModel.getGarmentstylecode());
				garmentstylereferpriceId.setTypecode(garmentstylereferpriceModel.getTypecode());
				
				garmentstylereferprice = dao.findById(garmentstylereferpriceId);
				garmentstylereferprice.setId(garmentstylereferpriceId);
				garmentstylereferprice.setGarmentstyle(garmentDao.findById(garmentstyleModel.getGarmentstylecode()));
				garmentstylereferprice.setType(typeDao.findById(garmentstylereferpriceModel.getTypecode()));
				garmentstylereferprice.setReferprice(garmentstylereferpriceModel.getReferprice());
				
				dao.update(garmentstylereferprice);
			}
			
			//lặp qua list add
			for (GarmentstylereferpriceModel garmentstylereferpriceModel : lstAdd) {
				garmentstylereferpriceId = new GarmentstylereferpriceId();
				garmentstylereferpriceId.setGarmentstylecode(garmentstyleModel.getGarmentstylecode());
				garmentstylereferpriceId.setTypecode(garmentstylereferpriceModel.getTypecode());
				
				garmentstylereferprice = new Garmentstylereferprice();
				garmentstylereferprice.setId(garmentstylereferpriceId);
				garmentstylereferprice.setGarmentstyle(garmentDao.findById(garmentstyleModel.getGarmentstylecode()));
				garmentstylereferprice.setType(typeDao.findById(garmentstylereferpriceModel.getTypecode()));
				garmentstylereferprice.setReferprice(garmentstylereferpriceModel.getReferprice());
				
				dao.save(garmentstylereferprice);
			}
			
			for (Garmentstylereferprice garmentstylereferpriceDelete : lstDelete) {
				dao.delete(garmentstylereferpriceDelete);
			}
			
			log.debug("addGarmentstylereferpriceForGarment into database successfully");
			return true;
		} catch (Exception e) {
			log.error(String.format(
					"addGarmentstylereferpriceForGarment in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to delete Garmentstylereferprice By Garment style code
	 * @param garmentstyleModel
	 * @return
	 */
	public boolean deleteGarmentstylereferpriceByGarment(GarmentstyleModel garmentstyleModel) {
		log.info(String.format("addGarmentstylereferpriceForGarment in class: %s", getClass()));
		try {
			List<Garmentstylereferprice> lst = dao.getListGarmentstylereferpriceByGarmentstyleCode(garmentstyleModel.getGarmentstylecode());
			for (Garmentstylereferprice garmentstylereferprice : lst) {
				dao.delete(garmentstylereferprice);
			}
			return true;
		} catch (Exception e) {
			log.error(String.format(
					"addGarmentstylereferpriceForGarment in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
}
