package com.chori.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.ColorDao;
import com.chori.dao.CurrencyDao;
import com.chori.dao.CustomerDao;
import com.chori.dao.FabricinformationDao;
import com.chori.dao.FabricinformationdetailDao;
import com.chori.dao.FabricsupplierDao;
import com.chori.dao.FactoryDao;
import com.chori.dao.UserDao;
import com.chori.dao.WidthDao;
import com.chori.entity.Currency;
import com.chori.entity.Fabricinformation;
import com.chori.entity.Fabricinformationdetail;
import com.chori.entity.FabricinformationdetailId;
import com.chori.model.CurrencyModel;
import com.chori.model.FabricinformationModel;
import com.chori.model.FabricinformationdetailModel;

@Service("fabricinformationService")
public class FabricinformationServiceImpl extends
		AbstractServiceImpl<Fabricinformation, String> implements
		FabricinformationService {
	private FabricinformationDao dao;

	@Autowired
	FabricinformationdetailDao fabricinformationdetailDao;

	@Autowired
	CustomerDao customerDao;

	@Autowired
	FabricsupplierDao fabricsupplierDao;

	@Autowired
	FactoryDao factoryDao;

	@Autowired
	WidthDao widthDao;

	@Autowired
	CurrencyDao currencyDao;

	@Autowired
	ColorDao colorDao;

	@Autowired
	UserDao userDao;

	@Autowired
	public FabricinformationServiceImpl(
			@Qualifier("fabricinformationDao") AbstractDao<Fabricinformation, String> abstractDao) {
		super(abstractDao);
		this.dao = (FabricinformationDao) abstractDao;
	}

	/**
	 * This function is used to get all fabric information
	 * 
	 * @return
	 */
	public List<FabricinformationModel> getAllFabricinformationModel() {
		log.info(String.format("getAllFabricinformationModel in class: %s",
				getClass()));
		try {
			List<Fabricinformation> listFabricinformation = dao.getAll();
			FabricinformationModel fabricinformationModel;

			int i =1;
			String listColor = "";
			Double totalYard = (double) 0;
			
			List<FabricinformationModel> lstFabricinformationModel = new ArrayList<FabricinformationModel>();
			for (Fabricinformation fabricinformation : listFabricinformation) {
				fabricinformationModel = new FabricinformationModel();
				fabricinformationModel.setFabricno(fabricinformation
						.getFabricno());
				fabricinformationModel.setFabricitem(fabricinformation
						.getFabricitem());
				fabricinformationModel.setCustomer(fabricinformation
						.getCustomer() == null ? "" : fabricinformation
						.getCustomer().getCustomercode());
				fabricinformationModel.setFabricsupplier(fabricinformation
						.getFabricsupplier() == null ? "" : fabricinformation
						.getFabricsupplier().getFabricsupcode());
				fabricinformationModel.setFactory(fabricinformation
						.getFactory() == null ? "" : fabricinformation
						.getFactory().getFactorycode());
				fabricinformationModel
						.setCreator(fabricinformation.getUser() == null ? ""
								: fabricinformation.getUser().getUsername());
				fabricinformationModel.setIschori(fabricinformation
						.getIschori());
				fabricinformationModel.setFabricinvoiceno(fabricinformation
						.getFabricinvoiceno());
				fabricinformationModel.setCreatedate(fabricinformation
						.getCreatedate());
				fabricinformationModel.setCurrencycode(fabricinformation
						.getCurrency() == null ? "" : fabricinformation
						.getCurrency().getCurrencycode());
				fabricinformationModel.setCurrencyName(fabricinformation
						.getCurrency() == null ? "" : fabricinformation
						.getCurrency().getName());
				fabricinformationModel.setCustomerShortname(fabricinformation
						.getCustomer() == null ? "" : fabricinformation
						.getCustomer().getShortname());
				fabricinformationModel
						.setFabricsupplierShortname(fabricinformation
								.getFabricsupplier() == null ? ""
								: fabricinformation.getFabricsupplier()
										.getShortname());
				fabricinformationModel.setFactoryShortname(fabricinformation
						.getFactory() == null ? "" : fabricinformation
						.getFactory().getShortname());
				fabricinformationModel.setWidthcode(fabricinformation
						.getWidth() == null ? "" : fabricinformation.getWidth()
						.getWidthcode());
				fabricinformationModel.setWidthvalues(fabricinformation
						.getWidth() == null ? "" : fabricinformation.getWidth()
						.getWidthvalues());
				fabricinformationModel.setComponent(fabricinformation
						.getComponent());
				fabricinformationModel.setEstimatedelvdate(fabricinformation
						.getEstimatedelvdate());
				fabricinformationModel.setActualdelvdate(fabricinformation
						.getActualdelvdate());
				fabricinformationModel.setFabricimgurl(fabricinformation
						.getFabricimgurl());
				fabricinformationModel.setRemark(fabricinformation.getRemark());
				
				Set<Fabricinformationdetail> lstFabricinformationdetail = fabricinformation.getFabricinformationdetails();
				for (Fabricinformationdetail fabricinformationdetail : lstFabricinformationdetail) {
					//cộng list color
					if(i==1){
						listColor += fabricinformationdetail.getColor().getDescription();
					}
					else{
						listColor += ", " + fabricinformationdetail.getColor().getDescription();
					}
					i++;
					//cộng total yard in bl
					totalYard += fabricinformationdetail.getYardinbl();
				}
				
				fabricinformationModel.setListColor(listColor);
				fabricinformationModel.setTotalYard(totalYard);
				
				listColor="";
				totalYard = (double) 0;
				i= 1;

				lstFabricinformationModel.add(fabricinformationModel);
			}
			log.debug("getAllFabricinformationModel successful");
			return lstFabricinformationModel;
		} catch (Exception e) {
			log.error(String.format(
					"getAllFabricinformationModel in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new fabric information
	 * 
	 * @param fim
	 * @param listFabricinformationdetailModel
	 * @param creator
	 * @return
	 */
	public boolean addNewFabricInformation(
			FabricinformationModel fim,
			List<FabricinformationdetailModel> listFabricinformationdetailModel,
			String creator) {
		log.info(String.format("addNewFabricInformation in class: %s",
				getClass()));
		try {

			Fabricinformation fabricinformation = new Fabricinformation();
			fabricinformation.setFabricno(fim.getFabricno());
			fabricinformation.setCustomer(customerDao.findById(fim
					.getCustomer()));
			fabricinformation.setFabricsupplier(fabricsupplierDao.findById(fim
					.getFabricsupplier()));
			fabricinformation.setIschori(fim.getIschori());
			fabricinformation.setFabricitem(fim.getFabricitem());
			fabricinformation.setComponent(fim.getComponent());
			fabricinformation.setFactory(factoryDao.findById(fim.getFactory()));
			fabricinformation.setWidth(widthDao.findById(fim.getWidthcode()));
			fabricinformation.setFabricinvoiceno(fim.getFabricinvoiceno());
			fabricinformation.setCurrency(currencyDao.findById(fim
					.getCurrencycode()));
			if(fim.getFabricimgurl()!=null)
				fabricinformation.setFabricimgurl(fim.getFabricimgurl());
			fabricinformation.setRemark(fim.getRemark());
			fabricinformation.setEstimatedelvdate(fim.getEstimatedelvdate());
			fabricinformation.setActualdelvdate(fim.getActualdelvdate());
			fabricinformation.setVoucherreceiveddate(fim.getVoucherreceiveddate());
			fabricinformation.setVouchersentdate(fim.getVouchersentdate());
			fabricinformation.setCreatedate(new Date());
			fabricinformation.setUser(userDao.findById(creator));

			dao.save(fabricinformation);
			log.debug("save new fabricinformation successfully");

			Fabricinformationdetail fabricinformationdetail;

			for (FabricinformationdetailModel fabricinformationdetailModel : listFabricinformationdetailModel) {
				fabricinformationdetail = new Fabricinformationdetail();
				fabricinformationdetail.setFabricinformation(fabricinformation);
				// fabricinformationdetail.setFabricinformation(dao.findById(fim.getFabricno()));
				fabricinformationdetail.setColor(colorDao
						.findById(fabricinformationdetailModel.getColorcode()));
				fabricinformationdetail.setId(new FabricinformationdetailId(
						fabricinformation.getFabricno(),
						fabricinformationdetailModel.getColorcode()));
				fabricinformationdetail
						.setYardinbl(fabricinformationdetailModel.getYardinbl());
				fabricinformationdetail
						.setUnitprice(fabricinformationdetailModel
								.getUnitprice());
				fabricinformationdetail.setImgurl(fabricinformationdetailModel
						.getImgurl());
				fabricinformationdetail.setCreatedate(new Date());
				fabricinformationdetail.setUser(userDao.findById(creator));

				fabricinformationdetailDao.save(fabricinformationdetail);
			}
			log.debug("save fabricinformation detail successfully");

			return true;
		} catch (Exception e) {
			log.error(String.format(
					"addNewFabricInformation in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to update image for FabricInformation
	 * 
	 * @param fabricNo
	 * @param imgurl
	 * @return
	 */
	public boolean updateImageAfterAddNewFabricInformation(String fabricNo,
			String imgurl) {
		log.info(String.format(
				"updateImageAfterAddNewFabricInformation in class: %s",
				getClass()));
		try {
			Fabricinformation fabricinformation = dao.findById(fabricNo);
			if(imgurl!=null){
				fabricinformation.setFabricimgurl(imgurl);
				dao.update(fabricinformation);
			}

			log.debug("updateImageAfterAddNewFabricInformation successfully");
			System.err.println("Update bên service thành công vs fabNo: "+fabricNo+", ảnh: "+imgurl);
			return true;
		} catch (Exception e) {
			log.error(String
					.format("updateImageAfterAddNewFabricInformation in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a fabric with fabric no is existed
	 * 
	 * @param fabricNo
	 * @return true if existed
	 */
	public boolean isFabricInformationExistedByFabricNo(String fabricNo) {
		log.info(String
				.format("isFabricInformationExistedByFabricNo with param 'fabricNo' in class: %s",
						getClass()));
		try {
			if (null == dao.findById(fabricNo))
				return false;
			return true;
		} catch (Exception e) {
			log.error(String
					.format("isFabricInformationExistedByFabricNo with param 'fabricNo' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to find Fabric information Model by id
	 * 
	 * @param fabricNo
	 * @return
	 */
	public FabricinformationModel findFabricinformationModelById(String fabricNo) {
		log.info(String.format(
				"findFabricinformationModelById with params 'id' in class: %s",
				getClass()));
		try {
			Fabricinformation fabricinformation = dao.findById(fabricNo);
			FabricinformationModel fabricinformationModel = new FabricinformationModel();

			fabricinformationModel
					.setCustomer(fabricinformation.getCustomer() == null ? ""
							: fabricinformation.getCustomer().getCustomercode());
			fabricinformationModel.setFabricsupplier(fabricinformation
					.getFabricsupplier() == null ? "" : fabricinformation
					.getFabricsupplier().getFabricsupcode());
			fabricinformationModel.setIschori(fabricinformation.getIschori());
			fabricinformationModel.setFabricitem(fabricinformation
					.getFabricitem());
			fabricinformationModel.setFabricno(fabricNo);
			fabricinformationModel.setComponent(fabricinformation
					.getComponent());
			fabricinformationModel
					.setFactory(fabricinformation.getFactory() == null ? ""
							: fabricinformation.getFactory().getFactorycode());
			fabricinformationModel
					.setWidthcode(fabricinformation.getWidth() == null ? ""
							: fabricinformation.getWidth().getWidthcode());
			fabricinformationModel.setFabricinvoiceno(fabricinformation
					.getFabricinvoiceno());
			fabricinformationModel.setCurrencycode(fabricinformation
					.getCurrency() == null ? "" : fabricinformation
					.getCurrency().getCurrencycode());
			fabricinformationModel.setFabricimgurl(fabricinformation
					.getFabricimgurl());
			fabricinformationModel.setRemark(fabricinformation.getRemark());
			fabricinformationModel.setEstimatedelvdate(fabricinformation
					.getEstimatedelvdate());
			fabricinformationModel.setActualdelvdate(fabricinformation
					.getActualdelvdate());
			fabricinformationModel.setVoucherreceiveddate(fabricinformation.getVoucherreceiveddate());
			fabricinformationModel.setVouchersentdate(fabricinformation.getVouchersentdate());

			return fabricinformationModel;
		} catch (Exception e) {
			log.error(String
					.format("findFabricinformationModelById with params 'id' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get list fabric information detail model By
	 * Fabric No
	 * 
	 * @param fabricNo
	 * @return
	 */
	public List<FabricinformationdetailModel> getListFabricinformationdetailModelByFabricNo(
			String fabricNo) {
		log.info(String.format(
				"getListFabricinformationdetailModelByFabricNo in class: %s",
				getClass()));
		try {
			List<FabricinformationdetailModel> lstResult = new ArrayList<FabricinformationdetailModel>();
			FabricinformationdetailModel tmpModel;

			List<Fabricinformationdetail> lstFabricinformationdetail = fabricinformationdetailDao
					.getListFabricinformationdetailByFabricNo(fabricNo);
			for (Fabricinformationdetail fabricinformationdetail : lstFabricinformationdetail) {
				tmpModel = new FabricinformationdetailModel();
				tmpModel.setFabricno(fabricNo);
				tmpModel.setColorcode(fabricinformationdetail.getColor()
						.getColorcode());
				tmpModel.setColorName(fabricinformationdetail.getColor()
						.getDescription());
				tmpModel.setUnitprice(fabricinformationdetail.getUnitprice());
				tmpModel.setYardinbl(fabricinformationdetail.getYardinbl());
				tmpModel.setImgurl(fabricinformationdetail.getImgurl());

				lstResult.add(tmpModel);
			}

			return lstResult;
		} catch (Exception e) {
			log.error(String
					.format("getListFabricinformationdetailModelByFabricNo in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete a fabric information
	 * 
	 * @param fabricNo
	 * @return
	 */
	public boolean deleteFabInfo(String fabricNo) {
		log.info(String.format("delete with params 'fabricNo' in class: %s",
				getClass()));
		try {
			Fabricinformation fabInfor = dao.findById(fabricNo);

			// Set<Factorycontact> lst = fac.getFactorycontacts();
			// for (Factorycontact factorycontact : lst) {
			// factoryContactDao.delete(factoryContactDao
			// .findById(factorycontact.getFactorycontactcode()));
			// }
			//
			// Set<Factoryaccountinformation> lst2 = fac
			// .getFactoryaccountinformations();
			// for (Factoryaccountinformation factoryaccountinformation : lst2)
			// {
			// factoryaccountinformationDao
			// .delete(factoryaccountinformationDao
			// .findById(factoryaccountinformation
			// .getFactoryaccountinfocode()));
			// }

			Set<Fabricinformationdetail> setFabInfor = fabInfor
					.getFabricinformationdetails();
			for (Fabricinformationdetail fabricinformationdetail : setFabInfor) {
				fabricinformationdetailDao.delete(fabricinformationdetailDao
						.findById(fabricinformationdetail.getId()));
			}

			dao.delete(fabInfor);

			return true;
		} catch (Exception e) {
			log.error(String.format(
					"delete with params 'fabricNo' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to edit fabric information using json
	 * @param fabricInformationModel
	 * @param editor
	 * @return
	 */
	public boolean editFabricInformationJson(FabricinformationModel fabricInformationModel, List<FabricinformationdetailModel> listFabricinformationdetailModel, String editor) {
		log.info(String
				.format("editFabricInformation in class: %s",
						getClass()));
		try {
			Fabricinformation fabricinformation = dao.findById(fabricInformationModel.getFabricno());

//			fabricinformation.setFabricno(fabricInformationModel.getFabricno());
			fabricinformation.setCustomer(customerDao.findById(fabricInformationModel
					.getCustomer()));
			fabricinformation.setFabricsupplier(fabricsupplierDao.findById(fabricInformationModel
					.getFabricsupplier()));
			fabricinformation.setIschori(fabricInformationModel.getIschori());
			fabricinformation.setFabricitem(fabricInformationModel.getFabricitem());
			fabricinformation.setComponent(fabricInformationModel.getComponent());
			fabricinformation.setFactory(factoryDao.findById(fabricInformationModel.getFactory()));
			fabricinformation.setWidth(widthDao.findById(fabricInformationModel.getWidthcode()));
			fabricinformation.setFabricinvoiceno(fabricInformationModel.getFabricinvoiceno());
			fabricinformation.setCurrency(currencyDao.findById(fabricInformationModel
					.getCurrencycode()));
//			fabricinformation.setFabricimgurl(fabricInformationModel.getFabricimgurl());
			fabricinformation.setRemark(fabricInformationModel.getRemark());
			fabricinformation.setEstimatedelvdate(fabricInformationModel.getEstimatedelvdate());
			fabricinformation.setActualdelvdate(fabricInformationModel.getActualdelvdate());
			fabricinformation.setVoucherreceiveddate(fabricInformationModel.getVoucherreceiveddate());
			fabricinformation.setVouchersentdate(fabricInformationModel.getVouchersentdate());
//			fabricinformation.setCreatedate(new Date());
//			fabricinformation.setUser(userDao.findById(creator));
			
			dao.update(fabricinformation);
			System.err.println("update fabricinformation successful");
			
			//tạo mới để add hoặc update bên vòng lặp dưới
			Fabricinformationdetail fabricinformationdetail;
			//Lấy ra list Fabricinformationdetail, sau đó lặp qua list lấy về xem có tồn tại ko, ko thì del
			List<Fabricinformationdetail> lstToDelete= fabricinformationdetailDao.getListFabricinformationdetailByFabricNo(fabricInformationModel.getFabricno());
			
			boolean contain = false;
			
			//lặp qua list mới, nếu p.tử trong list cũ mà ko tồn tại trong list mới thì del
			for (Fabricinformationdetail fabricinformationdetail2 : lstToDelete) {
				for (FabricinformationdetailModel fabricinformationdetailModel : listFabricinformationdetailModel) {
					if(fabricinformationdetail2.getFabricinformation().getFabricno().equals(fabricinformationdetailModel.getFabricno())&&fabricinformationdetail2.getColor().getColorcode().equals(fabricinformationdetailModel.getColorcode())){
						contain = true;
					}
				}
				if(contain==false){
					fabricinformationdetailDao.deleteById(new FabricinformationdetailId(fabricinformationdetail2.getFabricinformation().getFabricno(), fabricinformationdetail2.getColor().getColorcode()));
				}
				contain= false;
			}
			
			//Lặp qua list mới để add, update
			for (FabricinformationdetailModel fabricinformationdetailModel : listFabricinformationdetailModel) {
				//nếu fabric no = null thì add mới
				if(fabricinformationdetailModel.getFabricno()==null){
					fabricinformationdetail = new Fabricinformationdetail();
					fabricinformationdetail.setFabricinformation(fabricinformation);
					// fabricinformationdetail.setFabricinformation(dao.findById(fim.getFabricno()));
					fabricinformationdetail.setColor(colorDao
							.findById(fabricinformationdetailModel.getColorcode()));
					fabricinformationdetail.setId(new FabricinformationdetailId(
							fabricinformation.getFabricno(),
							fabricinformationdetailModel.getColorcode()));
					fabricinformationdetail
							.setYardinbl(fabricinformationdetailModel.getYardinbl());
					fabricinformationdetail
							.setUnitprice(fabricinformationdetailModel
									.getUnitprice());
					fabricinformationdetail.setImgurl(fabricinformationdetailModel
							.getImgurl());
					fabricinformationdetail.setCreatedate(new Date());
					fabricinformationdetail.setUser(userDao.findById(editor));

					fabricinformationdetailDao.save(fabricinformationdetail);
				}
				//nếu != null thì update
				if(fabricinformationdetailModel.getFabricno()!=null){
					fabricinformationdetail = fabricinformationdetailDao
							.findById(new FabricinformationdetailId(
									fabricinformationdetailModel.getFabricno(),
									fabricinformationdetailModel.getColorcode()));
					if (fabricinformationdetailModel.getYardinbl() != null)
						fabricinformationdetail
								.setYardinbl(fabricinformationdetailModel
										.getYardinbl());
					if (fabricinformationdetailModel.getUnitprice() != null)
						fabricinformationdetail
								.setUnitprice(fabricinformationdetailModel
										.getUnitprice());
					if (fabricinformationdetailModel.getImgurl() != null)
						fabricinformationdetail
								.setImgurl(fabricinformationdetailModel
										.getImgurl());
					fabricinformationdetailDao.update(fabricinformationdetail);
				}
			}
			///

			return true;
		} catch (Exception e) {
			log.error(String
					.format("editCurrency in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
