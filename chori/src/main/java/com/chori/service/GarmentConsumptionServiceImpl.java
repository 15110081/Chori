package com.chori.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.CustomerDao;
import com.chori.dao.GarmentConsumptionDao;
import com.chori.dao.GarmentConsumptionDetailDao;
import com.chori.dao.GarmentstyleDao;
import com.chori.dao.SizeDao;
import com.chori.dao.UserDao;
import com.chori.dao.WidthDao;
import com.chori.entity.Garmentconsumption;
import com.chori.entity.Garmentconsumptiondetail;
import com.chori.entity.Garmentstyle;
import com.chori.entity.Size;
import com.chori.entity.Width;
import com.chori.model.GarmentConsumptionDetailModel;
import com.chori.model.GarmentConsumptionModel;
import com.chori.model.SizeModel;
import com.chori.model.WidthModel;

@Repository("garmentconsumptionService")
public class GarmentConsumptionServiceImpl extends
		AbstractServiceImpl<Garmentconsumption, Integer> implements
		GarmentConsumptionService {

	private GarmentConsumptionDao dao;

	@Autowired
	UserDao userDao;

	@Autowired
	CustomerDao cusDao;

	@Autowired
	GarmentstyleDao garstyleDao;

	@Autowired
	SizeDao sizeDao;

	@Autowired
	WidthDao widthDao;

	@Autowired
	GarmentConsumptionDetailDao garmentConsumptionDetailDao;

	@Autowired
	public GarmentConsumptionServiceImpl(
			@Qualifier("garmentconsumptionDao") AbstractDao<Garmentconsumption, Integer> abstractDao) {
		super(abstractDao);
		this.dao = (GarmentConsumptionDao) abstractDao;
	}

	/**
	 * This function is used to get all garment consumption
	 * 
	 * @param GarmentConsumptionModel
	 * @return list garment consumption
	 */
	@Override
	public List<GarmentConsumptionModel> getAllGarmentConsumptionModel() {
		log.info(String.format("getAllGarmentConsumptionModel in class: %s",
				getClass()));
		try {
			log.debug("get all GarmentConsumption in DB after that return a list GarmentConsumptionModel");
			List<Garmentconsumption> lstGarcon = dao.getAll();

			GarmentConsumptionModel model;
			List<GarmentConsumptionModel> lst = new ArrayList<GarmentConsumptionModel>();

			GarmentConsumptionDetailModel garmentConsumptionDetailModel;

			for (Garmentconsumption garcon : lstGarcon) {
				model = new GarmentConsumptionModel();
				model.setCustomer(garcon.getCustomer().getCustomercode());
				model.setDescription(garcon.getDescription());
				model.setGarmentconsumptioncode(garcon
						.getGarmentconsumptioncode());
				model.setGarmentstyle(garcon.getGarmentstyle().getGarmentstylecode());
				
				//set displayName for garment style
				String[] splitGarmentStyleCode;
				splitGarmentStyleCode = garcon.getGarmentstyle().getGarmentstylecode().split("@@@");
				model.setGarmentstyledisplayname(splitGarmentStyleCode[0]);
				
				model.setSize(garcon.getSize().getSizecode());
				model.setCreatedate(garcon.getCreatedate());
				model.setCreator(garcon.getUser().getUsername());
				model.setSizename(garcon.getSize().getSizename());
				model.setKind(garcon.getGarmentstyle().getGarmentkind()
						.getGarmentkindcode());

				model.setType(getTypeByCustomerAndKindAndSize(garcon
						.getCustomer().getCustomercode(), garcon
						.getGarmentstyle().getGarmentkind()
						.getGarmentkindcode(), garcon.getSize().getSizename()));

				// get detail of garment consumption
				Set<Garmentconsumptiondetail> lstGarmentconsumptiondetail = garcon
						.getGarmentconsumptiondetails();
				//list to sort 
				List<GarmentConsumptionDetailModel> lstGarmentconsumptiondetailModel = new ArrayList<GarmentConsumptionDetailModel>();
				for (Garmentconsumptiondetail garcondetail : lstGarmentconsumptiondetail) {
					garmentConsumptionDetailModel = new GarmentConsumptionDetailModel();
					garmentConsumptionDetailModel
							.setGarmentconsumptiondetailcode(garcondetail
									.getGarmentconsumptiondetailcode());
					garmentConsumptionDetailModel.setConvalue(garcondetail
							.getConvalue());
					garmentConsumptionDetailModel.setCreatedate(garcondetail
							.getCreatedate());
					garmentConsumptionDetailModel
							.setGarmentconsumption(garcondetail
									.getGarmentconsumption()
									.getGarmentconsumptioncode());
					garmentConsumptionDetailModel.setCreator(garcondetail
							.getUser().getUsername());
					garmentConsumptionDetailModel.setWidth(garcondetail
							.getWidth().getWidthcode());
					
					lstGarmentconsumptiondetailModel.add(garmentConsumptionDetailModel);
//					model.getGarmentconsumptiondetails().add(
//							garmentConsumptionDetailModel);
				}
				Collections.sort(lstGarmentconsumptiondetailModel, GarmentConsumptionDetailModel.GarmentConsumptionDetailModelComparatorByWidth);
				model.getGarmentconsumptiondetails().addAll(lstGarmentconsumptiondetailModel);
				lst.add(model);
			}
			log.debug("getAllGarmentConsumptionModel successfully");
			return lst;
		} catch (Exception e) {
			log.error(String.format(
					"getAllGarmentConsumptionModel in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get type by customercode, garmentkindcode,
	 * sizecode
	 * 
	 * @param customercode
	 *            , garmentkindcode, sizecode
	 * @return type name in String
	 */
	@Override
	public String getTypeByCustomerAndKindAndSize(String customercode,
			String garmentkindcode, String sizename) {
		log.info(String.format("getTypeByCustomerAndKindAndSize in class: %s",
				getClass()));
		try {
			log.debug("getTypeByCustomerAndKindAndSize in DB after that return a list GarmentConsumptionModel");
			List<Size> lstSize = sizeDao.getAll();
			for (Size size : lstSize) {
				if (size.getCustomer().getCustomercode().equals(customercode)
						&& size.getGarmentkind().getGarmentkindcode()
								.equals(garmentkindcode)
						&& size.getSizename().equals(sizename)) {
					return size.getType().getTypecode();
				}
			}
			log.debug("getTypeByCustomerAndKindAndSize successfully");
			return null;
		} catch (Exception e) {
			log.error(String
					.format("getTypeByCustomerAndKindAndSize in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new Garment Consumption
	 * 
	 * @param garconModel
	 * @param userName
	 * @return
	 */
	// public boolean addNewGarmentConsumption(GarmentConsumptionModel
	// garconModel, String userName){
	// try{
	// Garmentconsumption garcon= new Garmentconsumption();
	// garcon.setCreatedate(new Date());
	// garcon.setCustomer(cusDao.findById(garconModel.getCustomer()));
	// garcon.setDescription(garconModel.getDescription());
	// garcon.setGarmentstyle(garstyleDao.findById(garconModel.getGarmentstyle()));
	// garcon.setSize(sizeDao.findById(garconModel.getSize()));
	// garcon.setUser(userDao.findById(userName));
	//
	// //save garment consumption before save detail
	// dao.save(garcon);
	// System.err.println("Saving Garment Consumption Successful");
	//
	// Garmentconsumptiondetail garconDetail;
	//
	// Set<GarmentConsumptionDetailModel> newList=
	// garconModel.getGarmentconsumptiondetails();
	// for (GarmentConsumptionDetailModel garconDetailModel : newList) {
	// garconDetail= new Garmentconsumptiondetail();
	// garconDetail.setConvalue(garconDetailModel.getConvalue());
	// garconDetail.setCreatedate(new Date());
	// garconDetail.setGarmentconsumption(dao.findById(garconModel.getGarmentconsumptioncode()));
	// garconDetail.setUser(userDao.findById(userName));
	// garconDetail.setWidth(widthDao.findById(garconDetailModel.getWidth()));
	//
	// garmentConsumptionDetailDao.save(garconDetail);
	// }
	// System.err.println("Saving Garment Consumption Successful");
	// return true;
	// }
	//
	// catch(Exception e){
	// System.err.println("Saving Garment Consumption Failed");
	// return false;
	// }
	// }

	/**
	 * Function get Garment Consumption by id
	 * 
	 * @param id
	 * @return detail of this garment consumption
	 */
	public GarmentConsumptionModel findGarmentConsumptionModelById(Integer id) {
		Garmentconsumption garcon = dao.findById(id);
		GarmentConsumptionModel garconModel = new GarmentConsumptionModel();

		garconModel.setCustomer(garcon.getCustomer().getCustomercode());
		garconModel.setDescription(garcon.getDescription());
		garconModel.setGarmentconsumptioncode(garcon
				.getGarmentconsumptioncode());
		garconModel.setGarmentstyle(garcon.getGarmentstyle().getGarmentstylecode());
		garconModel.setSize(garcon.getSize().getSizecode());
		garconModel.setCreatedate(garcon.getCreatedate());
		garconModel.setCreator(garcon.getUser().getUsername());
		garconModel.setSizename(garcon.getSize().getSizename());
		garconModel.setKind(garcon.getGarmentstyle().getGarmentkind()
				.getGarmentkindcode());
		garconModel.setType(garcon.getSize().getType().getTypecode());

		GarmentConsumptionDetailModel garconDetailModel;

		Set<Garmentconsumptiondetail> lst = garcon
				.getGarmentconsumptiondetails();
		//list to sort 
		List<GarmentConsumptionDetailModel> lstGarmentconsumptiondetailModel = new ArrayList<GarmentConsumptionDetailModel>();
		
		for (Garmentconsumptiondetail garconDetail : lst) {
			garconDetailModel = new GarmentConsumptionDetailModel();

			garconDetailModel.setGarmentconsumptiondetailcode(garconDetail
					.getGarmentconsumptiondetailcode());
			garconDetailModel.setConvalue(garconDetail.getConvalue());
			garconDetailModel.setCreatedate(garconDetail.getCreatedate());
			garconDetailModel.setGarmentconsumption(garconDetail
					.getGarmentconsumption().getGarmentconsumptioncode());
			garconDetailModel.setCreator(garconDetail.getUser().getUsername());
			garconDetailModel.setWidth(garconDetail.getWidth().getWidthcode());
			lstGarmentconsumptiondetailModel.add(garconDetailModel);
			//garconModel.getGarmentconsumptiondetails().add(garconDetailModel);

		}
		Collections.sort(lstGarmentconsumptiondetailModel, GarmentConsumptionDetailModel.GarmentConsumptionDetailModelComparatorByWidth);
		garconModel.getGarmentconsumptiondetails().addAll(lstGarmentconsumptiondetailModel);
		return garconModel;
	}

	/**
	 * This function is used to get all size of a customer by garmentstyle (garmentstyle include garmentkind)
	 * @param customercode, garmentstylecode
	 * @return List size model (include code & name)
	 */
	@Override
	public List<SizeModel> getSizeByCustomerAndGarmentStyle(String customercode, String garmentstylecode) {
		log.info(String.format("getSizeByCustomerAndGarmentStyle in class: %s",
				getClass()));
		try {
			log.debug("getSizeByCustomerAndGarmentStyle in DB");
			List<Size> lstSize = sizeDao.getAll();
			List<SizeModel> lstSizeModel = new ArrayList<SizeModel>();
			SizeModel sizeModel;
			for (Size size : lstSize) {
				if (size.getCustomer().getCustomercode().equals(customercode)
						&& (garstyleDao.findById(garmentstylecode)).getGarmentkind().getGarmentkindcode().equals(size.getGarmentkind().getGarmentkindcode())) {
					sizeModel = new SizeModel();
					sizeModel.setSizecode(size.getSizecode());
					sizeModel.setSizename(size.getSizename());
					lstSizeModel.add(sizeModel);
				}
			}
			log.debug("getSizeByCustomerAndGarmentStyle successfully");
			return lstSizeModel;
		} catch (Exception e) {
			log.error(String
					.format("getSizeByCustomerAndGarmentStyle in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * Function get edit a Garment Consumption
	 * 
	 * @param garment
	 *            consumption id
	 * @return true if edit successful, else return false
	 */
	public boolean editGarmentConsumption(GarmentConsumptionModel garconModel) {
		log.info(String
				.format("editGarmentConsumption with params 'garconModel', 'userName' in class %s",
						getClass()));
		try {
			Garmentconsumption garcon = dao.findById(garconModel
					.getGarmentconsumptioncode());

			// garcon.setCustomer(cusDao.findById(garconModel.getCustomer()));
			garcon.setDescription(garconModel.getDescription());
			// garcon.setGarmentstyle(garstyleDao.findById(garconModel.getGarmentstyle()));
			// garcon.setSize(sizeDao.findById(garconModel.getSize()));

			// save garmentconsumption before save detail
			dao.update(garcon);
			System.err.println("Edit garment consumption successful");
			return true;
			// Set<GarmentConsumptionDetailModel> newList=
			// garconModel.getGarmentconsumptiondetails();
			// Set<Garmentconsumptiondetail> oldList=
			// garcon.getGarmentconsumptiondetails();
			//
			// //when old list =0
			// if(oldList.size()==0){
			// if(newList.size()==0){
			// return true;//when list is null, do nothing
			// }else{//if new list garment consumption has contact, then add it
			// Garmentconsumptiondetail garconDetail;
			//
			// for (GarmentConsumptionDetailModel garconDetailModel : newList) {
			// garconDetail= new Garmentconsumptiondetail();
			//
			// garconDetail.setConvalue(garconDetailModel.getConvalue());
			// garconDetail.setCreatedate(new Date());
			// garconDetail.setGarmentconsumption(dao.findById(garconModel.getGarmentconsumptioncode()));
			// garconDetail.setUser(userDao.findById(userName));
			// garconDetail.setWidth(widthDao.findById(garconDetailModel.getWidth()));
			//
			// garmentConsumptionDetailDao.save(garconDetail);
			// }
			// return true;
			// }
			// }else{//when old list not null
			// if(newList.size()==0){//new list is null, then delete all contact
			// for (Garmentconsumptiondetail garconDetail : oldList) {
			// garmentConsumptionDetailDao.delete(garmentConsumptionDetailDao.findById(garconDetail.getGarmentconsumptiondetailcode()));
			// }
			// return true;
			// }else{//newlist not null
			//
			// //loop newlist, if garmentconsumptiondetailcode ==0, then add new
			// Garmentconsumptiondetail garconDetail;
			//
			// for (GarmentConsumptionDetailModel garconDetailModel : newList) {
			// if(garconDetailModel.getGarmentconsumptiondetailcode()==0){
			// garconDetail= new Garmentconsumptiondetail();
			//
			// garconDetail.setConvalue(garconDetailModel.getConvalue());
			// garconDetail.setCreatedate(new Date());
			// garconDetail.setGarmentconsumption(dao.findById(garconModel.getGarmentconsumptioncode()));
			// garconDetail.setUser(userDao.findById(userName));
			// garconDetail.setWidth(widthDao.findById(garconDetailModel.getWidth()));
			//
			// garmentConsumptionDetailDao.save(garconDetail);
			// }
			// }
			//
			// //true: newList + oldList is existed, false: newList is not
			// existed
			// boolean flag = false;
			//
			// //Create list garmentconsumptiondetail for remove
			// List<Garmentconsumptiondetail> lstToBeRemove= new
			// ArrayList<Garmentconsumptiondetail>();
			// List<GarmentConsumptionDetailModel> lstToBeUpdate = new
			// ArrayList<GarmentConsumptionDetailModel>();
			//
			// for (GarmentConsumptionDetailModel garconDetailModel : newList) {
			// for (Garmentconsumptiondetail garcondetail : oldList) {
			// //if contact in oldList is not existed in newlist
			// //if contactcode equal => is existed, set flag = true
			// if(garcondetail.getGarmentconsumptiondetailcode()==garconDetailModel.getGarmentconsumptiondetailcode()){
			// flag= true;
			// break;
			// }
			// }
			// if(flag==true){
			// lstToBeUpdate.add(garconDetailModel);
			// flag= false;//set is not existed
			// }
			// }
			//
			// //filter contact that not existed in newList
			// flag= false;//default: is not existed
			// for (Garmentconsumptiondetail garcondetail : oldList) {
			// for (GarmentConsumptionDetailModel garconDetailModel : newList) {
			// if(garcondetail.getGarmentconsumptiondetailcode()==garconDetailModel.getGarmentconsumptiondetailcode()){
			// flag= true;
			// break;
			// }
			// }
			//
			// if(flag==false){
			// lstToBeRemove.add(garcondetail);
			// }
			// flag= false;
			// }
			//
			// //loop remove list
			// for (Garmentconsumptiondetail splcontact : lstToBeRemove) {
			// garmentConsumptionDetailDao.delete(garmentConsumptionDetailDao.findById(splcontact.getGarmentconsumptiondetailcode()));
			// }
			//
			// Garmentconsumptiondetail garcondetailNeedUpdate;
			// //loop update list
			// for (GarmentConsumptionDetailModel garconDetailModel :
			// lstToBeUpdate) {
			// garcondetailNeedUpdate=
			// garmentConsumptionDetailDao.findById(garconDetailModel.getGarmentconsumptiondetailcode());
			//
			// garcondetailNeedUpdate.setConvalue(garconDetailModel.getConvalue());
			// garcondetailNeedUpdate.setWidth(widthDao.findById(garconDetailModel.getWidth()));
			//
			// garmentConsumptionDetailDao.update(garcondetailNeedUpdate);
			// }
			//
			// return true;
			// }
			// }
		} catch (Exception e) {
			log.error(String
					.format("edit GarmentConsumption with params 'garconModel', 'userName' in class %s %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete a GarmentConsumption
	 * 
	 * @param GarmentConsumption
	 *            id
	 * @return true if delete successful, else return false
	 */
	public boolean delete(Integer id) {
		try {
			Garmentconsumption garcon = dao.findById(id);

			Set<Garmentconsumptiondetail> lst = garcon
					.getGarmentconsumptiondetails();
			for (Garmentconsumptiondetail garconDetail : lst) {
				garmentConsumptionDetailDao.delete(garmentConsumptionDetailDao
						.findById(garconDetail
								.getGarmentconsumptiondetailcode()));
			}
			dao.delete(garcon);

			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Option 2 This function is used to add new Garment Consumption
	 * 
	 * @param Garment
	 *            Consumption Model, userName
	 * @return true if add successful, else return false
	 */
	public boolean addNewGarmentConsumption2(
			GarmentConsumptionModel garconModel, String userName) {
		try {
			if(validateForeignKey(garconModel.getCustomer()
					,garconModel.getGarmentstyle()
							,garconModel.getSize(),userName) == true)
			{
				Garmentconsumption garcon = new Garmentconsumption();
				// garcon.setCreatedate(new Date());
				garcon.setCustomer(cusDao.findById(garconModel.getCustomer()));
				garcon.setDescription(garconModel.getDescription());
				garcon.setGarmentstyle(garstyleDao.findById(garconModel
						.getGarmentstyle()));
				garcon.setSize(sizeDao.findById(garconModel.getSize()));
				garcon.setUser(userDao.findById(userName));
				// save garment consumption before save detail
				dao.save(garcon);

				System.err.println("Saving Garment Consumption Successful");
				return true;
			}
			return false;
		}
		catch (Exception e) {
			System.err.println("Saving Garment Consumption Failed");
			throw e;
		}
	}

	/**
	 * This function check if there is a GarmentConsumption existed in database
	 * 
	 * @param garconModel
	 * @return false if not existed, true if existed
	 */
	public boolean isGarmentConsumptionExisted(
			GarmentConsumptionModel garconModel) {
		log.debug("in GarmentConsumptionService at isGarmentConsumptionExisted method");
		try {

			List<Garmentconsumption> lstGarcon = dao.getAll();
			for (Garmentconsumption garcon : lstGarcon) {
				if (garcon.getCustomer().getCustomercode()
						.equals(garconModel.getCustomer())
						&& garcon.getGarmentstyle().getGarmentstylecode()
								.equals(garconModel.getGarmentstyle())
						&& garcon.getSize().getSizecode()
								.equals(garconModel.getSize())) {
					return true;
				}
			}
			return false;
		} catch (NullPointerException ne) {
			log.error("in GarmentConsumptionService at isGarmentConsumptionExisted method error: "
					+ ne.getMessage());
			throw ne;
		}
	}
	
	/**
	 * This function check if there is a GarmentConsumption existed in database
	 * @param garcon
	 * @return false if not existed, true if existed
	 */
	public boolean isGarmentConsumptionExisted(
			Garmentconsumption garconParam) {
		log.debug("in GarmentConsumptionService at isGarmentConsumptionExisted method");
		try {

			List<Garmentconsumption> lstGarcon = dao.getAll();
			for (Garmentconsumption garcon : lstGarcon) {
				if (garcon.getCustomer().getCustomercode()
						.equals(garconParam.getCustomer().getCustomercode())
						&& garcon.getGarmentstyle().getGarmentstylecode()
								.equals(garconParam.getGarmentstyle().getGarmentstylecode())
						&& garcon.getSize().getSizecode()
								.equals(garconParam.getSize().getSizecode())) {
					return true;
				}
			}
			return false;
		} catch (NullPointerException ne) {
			log.error("in GarmentConsumptionService at isGarmentConsumptionExisted method error: "
					+ ne.getMessage());
			throw ne;
		}
	}
	
	/**
	 * This function check if width table in DB have data
	 * @return false if not existed, true if existed
	 */
	@Override
	public boolean isExistedWidthData() {
		log.debug("in GarmentConsumptionService at isExistedWidthData method");
		try {

			List<Width> lstWidth = widthDao.getAll();
			if(lstWidth.size()>0)
			{
				return true;
			}
			return false;
		} catch (NullPointerException ne) {
			log.error("in GarmentConsumptionService at isExistedWidthData method error: "
					+ ne.getMessage());
			throw ne;
		}
	}
	
	/**
	 * This function is used to validate foreign key in Garment Consumption
	 * @return false if validate failed, return true if valid
	 */
	public boolean validateForeignKey(String customer, String garmentStyle, Integer sizecode, String userName) {
		log.debug("in GarmentConsumptionService at validateForeignKey method");
		try {
			if(cusDao.findById(customer) != null
					&& garstyleDao.findById(garmentStyle) != null
					&& sizeDao.findById(sizecode) != null
					&& userDao.findById(userName) != null)
			{
				return true;
			}	
			return false;
		} catch (NullPointerException ne) {
			log.error("in GarmentConsumptionService at validateForeignKey method error: "
					+ ne.getMessage());
			throw ne;
		}
	}
	
	/**
	 * This function is used to get all garment style of a customer
	 * @return list of garment style entity
	 */
	public List<Garmentstyle> getAllGarmentStyleOfCustomer(String customerCode) {
		log.debug("in GarmentConsumptionService at getAllGarmentStyleOfCustomer method");
		try {
			List<Garmentstyle> lstGarmentstyle = garstyleDao.getAll();
			for(Garmentstyle garmentstyle : lstGarmentstyle)
			{
				if(garmentstyle.getCustomer().getCustomercode().equals(customerCode))
				{
					lstGarmentstyle.add(garmentstyle);
				}
			}
			return lstGarmentstyle;
		} catch (NullPointerException ne) {
			log.error("in GarmentConsumptionService at getAllGarmentStyleOfCustomer method error: "
					+ ne.getMessage());
			throw ne;
		}
	}
	
	/**
	 * Option 2 This function is used to add new Garment Consumption by Garment Style, if is existed then edit, if not existed, then add new
	 * @param Garment Consumption Model, userName
	 * @return true if add successful, else return false
	 */
	@Override
	public boolean addNewGarmentConsumptionByGarmentStyle(GarmentConsumptionModel garconModel, String userName) {
		log.debug("in GarmentConsumptionService at addNewGarmentConsumptionByGarmentStyle method");
		try {
			//check foreign key not null
			if(cusDao.findById(garconModel.getCustomer()) != null
					&& garstyleDao.findById(garconModel.getGarmentstyle()) != null)
			{
				//get list size of customer by garment style
				List<SizeModel> lstSizeModel = getSizeByCustomerAndGarmentStyle(garconModel.getCustomer(), garconModel.getGarmentstyle());
				//check list size not null
				if(lstSizeModel.size()>0)
				{
					for(SizeModel sizeModel : lstSizeModel)
					{
						Garmentconsumption garcon = new Garmentconsumption();
						// garcon.setCreatedate(new Date());
						garcon.setCustomer(cusDao.findById(garconModel.getCustomer()));
						garcon.setGarmentstyle(garstyleDao.findById(garconModel
								.getGarmentstyle()));
						garcon.setSize(sizeDao.findById(sizeModel.getSizecode()));
						garcon.setUser(userDao.findById(userName));
						garcon.setDescription("");
						
						if(!isGarmentConsumptionExisted(garcon))
						{
							dao.save(garcon);
						}
					}
					System.err.println("addNewGarmentConsumptionByGarmentStyle Successful");
					return true;
				}
			}
			return false;
		}
		catch (Exception ne) {
			log.error("in GarmentConsumptionService at addNewGarmentConsumptionByGarmentStyle method error: "
					+ ne.getMessage());
			throw ne;
		}
	}
	
	/**
	 * This function is used to get all garment consumption code by customer and garment style
	 * @return list of garment consumption code 
	 */
	@Override
	public List<Integer> getAllGarmentConCodeByCustomerAndGarmentStyle(GarmentConsumptionModel garconModel) {
		log.debug("in GarmentConsumptionService at getAllGarmentConCodeByCustomerAndGarmentStyle method");
		try {
			List<Integer> lstGarmentConCode = new ArrayList<Integer>();
			List<Garmentconsumption> lstGarmentconsumption  = dao.getAll();
			for(Garmentconsumption garmentconsumption : lstGarmentconsumption)
			{
				if(garmentconsumption.getCustomer().getCustomercode().equals(garconModel.getCustomer())
						&& garmentconsumption.getGarmentstyle().getGarmentstylecode().equals(garconModel.getGarmentstyle()))
				{
					lstGarmentConCode.add(garmentconsumption.getGarmentconsumptioncode());
				}
			}
			return lstGarmentConCode;
		} catch (NullPointerException ne) {
			log.error("in GarmentConsumptionService at getAllGarmentConCodeByCustomerAndGarmentStyle method error: "
					+ ne.getMessage());
			throw ne;
		}
	}
	
	/**
	 * This function is used get all Width
	 * @return list of width in widthModel Object
	 */
	@Override
	public List<WidthModel> getAllWidth() {
		log.info(String.format("getAllWidth in class: %s", getClass()));
		try {
			log.debug("get all Width in DB");
			List<Width> lstWidth = widthDao.getAll();

			WidthModel widthMod;
			List<WidthModel> lstWidthModel = new ArrayList<WidthModel>();

			for (Width wid : lstWidth) {
				widthMod = new WidthModel();
				widthMod.setWidthcode(wid.getWidthcode());
				widthMod.setWidthvalues(wid.getWidthvalues());
				lstWidthModel.add(widthMod);
			}
			//sort list follow width code
			Collections.sort(lstWidthModel, WidthModel.WidthModelComparatorByWidthCode);
			log.debug("getAllWidth successfully");
			return lstWidthModel;
		} catch (NullPointerException ne) {
			log.error(String.format("getAllWidth in class: %s has error: %s", getClass(), ne.getMessage()));
			throw ne;
		}
	}
}
