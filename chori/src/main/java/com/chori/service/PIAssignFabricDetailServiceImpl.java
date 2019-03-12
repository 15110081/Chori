package com.chori.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.ColorDao;
import com.chori.dao.FabricinformationDao;
import com.chori.dao.FabricinformationdetailDao;
import com.chori.dao.GarmentConsumptionDao;
import com.chori.dao.GarmentConsumptionDetailDao;
import com.chori.dao.GarmentstyleDao;
import com.chori.dao.PIAssignFabricDetailDao;
import com.chori.dao.PIDao;
import com.chori.dao.PiassignfabricDao;
import com.chori.dao.PigriddetailDao;
import com.chori.dao.UserDao;
import com.chori.entity.Fabricinformationdetail;
import com.chori.entity.FabricinformationdetailId;
import com.chori.entity.Garmentconsumption;
import com.chori.entity.Garmentconsumptiondetail;
import com.chori.entity.Pi;
import com.chori.entity.Piassignfabric;
import com.chori.entity.PiassignfabricId;
import com.chori.entity.Piassignfabricdetail;
import com.chori.entity.Pigriddetail;
import com.chori.model.CalculateFAValueModel;
import com.chori.model.PIAssignFabricDetailModel;
import com.chori.model.PiassignfabricModel;

@Repository("pIAssignFabricDetailService")
public class PIAssignFabricDetailServiceImpl extends
		AbstractServiceImpl<Piassignfabricdetail, Integer> implements
		PIAssignFabricDetailService {

	private PIAssignFabricDetailDao piassignfabricdetaildao;

	@Autowired
	PIDao piDao;
	
	@Autowired
	private ColorDao colorDao;

	@Autowired
	private GarmentstyleDao garmentstyleDao;

	@Autowired
	PigriddetailDao pigriddetaildao;

	@Autowired
	PiassignfabricDao piassignfabricdao;
	
	@Autowired
	FabricinformationDao fabricinformationDao;

	@Autowired
	FabricinformationdetailDao fabricinformationdetailDao;
	
	@Autowired
	PiassignfabricService piassignfabricService;
	
	@Autowired
	UserDao userDao;

	@Autowired
	private GarmentConsumptionDao garmentconsumptionDao;
	
	@Autowired
	private GarmentConsumptionDetailDao garmentconsumptiondetailDao;
	
	@Autowired
	public PIAssignFabricDetailServiceImpl(
			AbstractDao<Piassignfabricdetail, Integer> abstractDao,
			PIAssignFabricDetailDao piassignfabricdetaildao) {
		super(abstractDao);
		this.piassignfabricdetaildao = piassignfabricdetaildao;
	}


	/**
	 * This function is used get PIAssignFabricDetail by id
	 * 
	 * @return a List of all PIAssignFabricDetail
	 */
	@Override
	public PIAssignFabricDetailModel findPIAssignFabricDetailModelById(
			Integer id) {
		log.info(String.format(
				"findPIAssignFabricDetailModelById in class: %s", getClass()));
		try {
			Piassignfabricdetail piassignfabricdetail = piassignfabricdetaildao.findById(id);
			PIAssignFabricDetailModel piassignfabricdetailmodel = new PIAssignFabricDetailModel();

			piassignfabricdetailmodel
					.setPiassignfabricdetailcode(piassignfabricdetail
							.getPiassignfabricdetailcode());
			piassignfabricdetailmodel.setColor(piassignfabricdetail.getColor()
					.getColorcode());
			piassignfabricdetailmodel.setLotnumber(piassignfabricdetail
					.getPiassignfabric().getFabricinformation().getFabricno());

			log.debug("findPIAssignFabricDetailModelById successfully");
			return piassignfabricdetailmodel;
		} catch (Exception e) {
			log.error(String
					.format("findPIAssignFabricDetailModelById in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used get list of Lot Number by Fabric Number
	 * 
	 * @return a List of all Lot Number
	 */

	@Override
	public List<PIAssignFabricDetailModel> findPIAssignFabricDetailModelByFabricNo(
			String fabricNo) {
		log.info(String.format(
				"findPIAssignFabricDetailModelByFabricNo in class: %s",
				getClass()));
		try {
			List<PIAssignFabricDetailModel> lstPIAssignFabricDetailModelResult = new ArrayList<PIAssignFabricDetailModel>();
			PIAssignFabricDetailModel piassignfabricdetailmodel;

			List<Piassignfabric> lstPiassignfabric = piassignfabricdao.getAllPIAssignFabricByFabricNo(fabricNo);
			List<String> lstAllLotNumByFabricNo = new ArrayList<String>();
			for (Piassignfabric piassignfabric : lstPiassignfabric) {
				
					lstAllLotNumByFabricNo.add(piassignfabric.getId().getLotnumber());
				
			}

			// for(int i = 0; i<lstAllFabricNo.size();i++)
			// {
			// System.err.println(" asdasd   " +lstAllFabricNo.get(i));
			// }

			List<PIAssignFabricDetailModel> lstPIAssignFabricDetailModel2;

			for (String tmp : lstAllLotNumByFabricNo) {
				lstPIAssignFabricDetailModel2 = getPIAssignFabricDetailByLotNoAndFabricNo(
						tmp, fabricNo);
				for (PIAssignFabricDetailModel pIAssignFabricDetailModel : lstPIAssignFabricDetailModel2) {
					piassignfabricdetailmodel = new PIAssignFabricDetailModel();
					piassignfabricdetailmodel
							.setLotnumber(pIAssignFabricDetailModel
									.getLotnumber());
					piassignfabricdetailmodel
							.setColor(pIAssignFabricDetailModel.getColor());
					piassignfabricdetailmodel
							.setGarmentstyle(pIAssignFabricDetailModel
									.getGarmentstyle());
					piassignfabricdetailmodel
							.setAssignquantity(pIAssignFabricDetailModel
									.getAssignquantity());
					piassignfabricdetailmodel
							.setAssignqtypercent(pIAssignFabricDetailModel
									.getAssignqtypercent());
					piassignfabricdetailmodel
							.setShipping(pIAssignFabricDetailModel
									.getShipping());
					lstPIAssignFabricDetailModelResult
							.add(piassignfabricdetailmodel);
				}
			}
			log.debug("findPIAssignFabricDetailModelByFabricNo successfully");
			return lstPIAssignFabricDetailModelResult;
		} catch (Exception e) {
			log.error(String
					.format("findPIAssignFabricDetailModelByFabricNo in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used get list of all attributes by Lot Number anf FabricNo
	 * 
	 * @return a List of all PI Assign fabric detail
	 */

	public List<PIAssignFabricDetailModel> getPIAssignFabricDetailByLotNoAndFabricNo(
			String lotNo, String fabricNo) {
		log.info(String.format(
				"getPIAssignFabricDetailByLotNoAndFabricNo in class: %s",
				getClass()));
		try {
			PIAssignFabricDetailModel piassignfabricdetailmodel;
			List<Piassignfabricdetail> lstPiassignfabricdetail = piassignfabricdetaildao
					.getAllPIAssignFabricDetailByLotNumberFabricNo(lotNo, fabricNo);
			List<PIAssignFabricDetailModel> lstPIAssignFabricDetailModel = new ArrayList<PIAssignFabricDetailModel>();
			for (Piassignfabricdetail piassignfabricdetail : lstPiassignfabricdetail) {
				
					piassignfabricdetailmodel = new PIAssignFabricDetailModel();
					piassignfabricdetailmodel.setLotnumber(piassignfabricdetail
							.getPiassignfabric().getId().getLotnumber());
					piassignfabricdetailmodel.setColor(piassignfabricdetail
							.getColor().getColorcode());
					piassignfabricdetailmodel
							.setGarmentstyle(piassignfabricdetail
									.getGarmentstyle().getGarmentstylecode());
					piassignfabricdetailmodel
							.setAssignquantity(piassignfabricdetail
									.getAssignquantity());
					piassignfabricdetailmodel.setShipping(piassignfabricdetail
							.getPiassignfabric().getPi().getShippingstatus());
					lstPIAssignFabricDetailModel.add(piassignfabricdetailmodel);
				
			}
			log.debug("getPIAssignFabricDetailByLotNoAndFabricNo successfully");
			return lstPIAssignFabricDetailModel;
		} catch (Exception e) {
			log.error(String
					.format("getPIAssignFabricDetailByLotNoAndFabricNo in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used get list of all attributes by Lot Number and FabricNo
	 * 
	 * @return a List of all PI Assign fabric 
	 */
	
	public PiassignfabricId findPIAssignFabricIDByLotNoAndFabricNo(String lotNo, String fabricNo){
		log.info(String.format(
				"getPIAssignFabricByLotNoAndFabricNo in class: %s",
				getClass()));
		try{
			List<Piassignfabric> lstPiassignfabric = piassignfabricdao.getAllPIAssignFabricByLotNumberFabricNo(lotNo, fabricNo);
			for(Piassignfabric piassignfabric : lstPiassignfabric){
				
					return piassignfabric.getId();
				
			}
			log.debug("getPIAssignFabricByLotNoAndFabricNo successfully");
			return null;
		}catch(Exception e){
			log.error(String
					.format("getPIAssignFabricByLotNoAndFabricNo in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}		
	}

	/**
	 * This function is used get all color of a fabric by fabricNo
	 * 
	 * @return a List color of a fabric
	 */

	public List<String> getAllColorOfAFabric(String fabricNo) {
		log.info(String.format("getAllColorOfAFabric in class: %s", getClass()));
		try {
			List<Fabricinformationdetail> lstfabricinformationdetail = fabricinformationdetailDao
					.getAllFabricInformationDetailByFabricNo(fabricNo);
			List<String> lstColor = new ArrayList<String>();
			for (Fabricinformationdetail fabricinformationdetail : lstfabricinformationdetail) {

					lstColor.add(fabricinformationdetail.getColor().getColorcode());
				
			}
			log.debug("getAllColorOfAFabric successfully");
			return lstColor;
		} catch (Exception e) {
			log.error(String.format(
					"getAllColorOfAFabric in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used get list to Fabric Assignment Quantity form
	 * 
	 * @return a List of FabricAssignmentDetail
	 */

	public List<PIAssignFabricDetailModel> getListFabricAssignmentQuantity(
			String fabricNo, String lotNo) {
		log.info(String.format("getListFabricAssignmentQuantity in class: %s",
				getClass()));
		try {

			// to check if Fabric Assign is existed
			
			PiassignfabricId piassignfabricId = new PiassignfabricId();
			piassignfabricId.setFabricno(fabricNo);
			piassignfabricId.setLotnumber(lotNo);

			FabricinformationdetailId fabricinformationdetailId;
			
			PIAssignFabricDetailModel piassignfabricdetailmodel;
			List<Piassignfabricdetail> lstPiassignfabricdetail = piassignfabricdetaildao
					.getAllPIAssignFabricDetailByLotNumberFabricNo(lotNo, fabricNo);
			List<PIAssignFabricDetailModel> lstPIAssignFabricDetailModel = new ArrayList<PIAssignFabricDetailModel>();
//			System.err.println(piassignfabricService
//					.IsExistedPiAssignFabric(piassignfabricId));
			// if not exist
			 
				// load all pigriddetail of a pi by lotnumber,fabricno,color

				// get all color of a PIGrid
				List<String> lstColor = getAllColorOfAFabric(fabricNo);

				// get piGridDetailCode of a PI
				Integer pigridcode = piDao.findById(lotNo).getPigrid()
						.getPigridcode();
				//List<Pigriddetail> lstPigriddetail = pigriddetaildao.getAll();
				List<Pigriddetail> lstPigriddetail = pigriddetaildao.getListPigriddetailByPigridcodeAndListColor(pigridcode, lstColor);
				// loop pigriddetail table
				for (Pigriddetail pigriddetail : lstPigriddetail) {
					// if PiGridCode of PiGridDetail == PiGridCode of PI
					if (pigriddetail.getPigrid().getPigridcode() == pigridcode) {
						// check if FabricInforDetail have color == PiGrid
						for (String colorcode : lstColor) {
							if (pigriddetail.getColor().getColorcode()
									.equals(colorcode)) {
																						
								piassignfabricdetailmodel = new PIAssignFabricDetailModel();
								piassignfabricdetailmodel
										.setLotnumber(fabricNo);
								piassignfabricdetailmodel.setColor(pigriddetail
										.getColor().getColorcode());
								piassignfabricdetailmodel
										.setGarmentstyle(pigriddetail
												.getGarmentstyle()
												.getGarmentstylecode());
															
								Double sumAssignQty = (double) 0;
								if (!piassignfabricService
										.IsExistedPiAssignFabric(piassignfabricId)){
									// default by zero
									piassignfabricdetailmodel.setAssignquantity(0);
									piassignfabricdetailmodel.setAssignqtypercent(0);
								}
								else if (piassignfabricService
										.IsExistedPiAssignFabric(piassignfabricId)) 
								{
									for (Piassignfabricdetail piassignfabricdetail : lstPiassignfabricdetail) {
											if (piassignfabricdetail.getColor()
														.getColorcode().equals(pigriddetail
																.getColor().getColorcode())
													&& piassignfabricdetail.getGarmentstyle()
														.getGarmentstylecode().equals(pigriddetail
																.getGarmentstyle()
																.getGarmentstylecode())){										
												piassignfabricdetailmodel
												.setAssignquantity(piassignfabricdetail
														.getAssignquantity());
												piassignfabricdetailmodel.setAssignqtypercent(piassignfabricdetail
														.getAssignqtypercent());										
											}
										}
									
									//calculate sumAssignQty follow lotnumber,fabricno,color (except garment style)
									for (Piassignfabricdetail piassignfabricdetail : lstPiassignfabricdetail) {
										if (piassignfabricdetail.getColor()
													.getColorcode().equals(pigriddetail
															.getColor().getColorcode())){										
											sumAssignQty += piassignfabricdetail.getAssignquantity();								
										}
										
									}
								}
								piassignfabricdetailmodel.setShipping(piDao
										.findById(lotNo).getShippingstatus());
								
								fabricinformationdetailId = new FabricinformationdetailId(fabricNo,colorcode);
								
								piassignfabricdetailmodel.setYardinbl(
										getYardInBL(fabricinformationdetailId));
								
								piassignfabricdetailmodel.setInventoryremained(
										getInventoryRemained(fabricinformationdetailId,piassignfabricdetailmodel));
								
								piassignfabricdetailmodel.setAvailableassign(
										getAvailableQty(fabricinformationdetailId));
								
								//sum up assign qty + available qty to calculate by percent when edit qty
								piassignfabricdetailmodel.setSumAssignQtyAndAvailableQtyToCalPercent(
										piassignfabricdetailmodel.getAvailableassign() + sumAssignQty);
							
								piassignfabricdetailmodel.setUsingvalue(
										getUsingValue(fabricinformationdetailId));
								
								lstPIAssignFabricDetailModel
										.add(piassignfabricdetailmodel);
							}

						
					}
				}										

			}
				
			//this loop use to remove duplicate garmentstyle and color 	
			List<PIAssignFabricDetailModel> lstPIAssignFabricDetailModelUnduplicate = new ArrayList<PIAssignFabricDetailModel>();
			for (PIAssignFabricDetailModel pIAssignFabricDetailModel : lstPIAssignFabricDetailModel) 
			{
					lstPIAssignFabricDetailModelUnduplicate.add(pIAssignFabricDetailModel);
				for (int i =0 ; i < lstPIAssignFabricDetailModelUnduplicate.size()-1 ; i++)
				{
					
					if(lstPIAssignFabricDetailModelUnduplicate.get(i).getGarmentstyle()
							.equals(pIAssignFabricDetailModel
									.getGarmentstyle())
						&& lstPIAssignFabricDetailModelUnduplicate.get(i).getColor()
							.equals(pIAssignFabricDetailModel
									.getColor()))
					{
						lstPIAssignFabricDetailModelUnduplicate.remove(lstPIAssignFabricDetailModelUnduplicate.size()-1);
					}
				}			
			}	
			lstPIAssignFabricDetailModel.clear();
			lstPIAssignFabricDetailModel.addAll(lstPIAssignFabricDetailModelUnduplicate);
				
//			//this loop use to remove duplicate garmentstyle and color 		
//			for (int i =0 ; i <= lstPIAssignFabricDetailModel.size() ; i++) 
//			{
//			    for (int j = i + 1; j < lstPIAssignFabricDetailModel.size()-1; j++)
//			    {
//				     if (lstPIAssignFabricDetailModel.get(i).getGarmentstyle()
//				    		 	.equals(lstPIAssignFabricDetailModel
//				    		 			.get(j).getGarmentstyle())
//				    		 && lstPIAssignFabricDetailModel.get(i).getColor()
//				    		 	.equals(lstPIAssignFabricDetailModel
//				    		 			.get(j).getColor())) {
//				    
//				    	 lstPIAssignFabricDetailModel
//				    	 	.remove(lstPIAssignFabricDetailModel
//				    	 			.get(j));
//				    	 
//				   }
//			    }
//			}
			
			//calculate occurences of each element in List, to count max value of assign quantity percent
			//create a list of all color appear in lstPIAssignFabricDetailModel
			List<String> listOfColor = new ArrayList<String>();
			for(PIAssignFabricDetailModel pIAssignFabricDetailModel : lstPIAssignFabricDetailModel)
			{
				listOfColor.add(pIAssignFabricDetailModel.getColor());
			}
			//count appearance number by color of each element in lstPIAssignFabricDetailModel
			for(PIAssignFabricDetailModel pIAssignFabricDetailModel : lstPIAssignFabricDetailModel)
			{
				int occurrences = Collections.frequency(listOfColor, pIAssignFabricDetailModel.getColor());
				pIAssignFabricDetailModel.setMaxQtyPercentValue(occurrences);
			}
			log.debug("getListFabricAssignmentQuantity successfully");
			return lstPIAssignFabricDetailModel;
		} catch (Exception e) {
			log.error(String
					.format("getListFabricAssignmentQuantity in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
	
	@Override
	public boolean addFabricAssignmentQuantityToPIAssignFabricDetail(
			PIAssignFabricDetailModel piAssignFabricDetailModel,String userName) {
		try {
				
				PiassignfabricId piassignfabricId = new PiassignfabricId(piDao.findById(piAssignFabricDetailModel.getLotnumber()).getLotnumber()
						,fabricinformationDao.findById(piAssignFabricDetailModel.getFabricno()).getFabricno());

//				//add piassign fabric, when it is not existed
//				if(!piassignfabricService.IsExistedPiAssignFabric(piassignfabricId)){
//					Piassignfabric piassignfabric = new Piassignfabric();
//					piassignfabric.setId(piassignfabricId);
//					piassignfabric.setUser(userDao.findById(userName));
//					piassignfabricdao.save(piassignfabric);
//				}
				Piassignfabricdetail piassignfabricdetail = new Piassignfabricdetail();
				//add details
				piassignfabricdetail.setPiassignfabric(piassignfabricdao.findById(piassignfabricId));
				piassignfabricdetail.setColor(colorDao
						.findById(piAssignFabricDetailModel.getColor()));
				piassignfabricdetail.setGarmentstyle(garmentstyleDao
						.findById(piAssignFabricDetailModel.getGarmentstyle()));
				piassignfabricdetail.setAssignquantity(
						piAssignFabricDetailModel.getAssignquantity());
				piassignfabricdetail.setAssignqtypercent(
						piAssignFabricDetailModel.getAssignqtypercent());
				
				piassignfabricdetaildao.save(piassignfabricdetail);
			
			log.debug("add FabricAssignmentQuantityToPIAssignFabricDetail into database successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("addFabricAssignmentQuantityToPIAssignFabricDetail with "
							+ "param 'piAssignFabricDetailModel' in class: %s has error: %s",
								getClass(), e.getMessage()));
	//		System.err.println("Adding FabricAssignmentQuantityToPIAssignFabricDetail Failed");
			return false;
		}

	}
	
	/**
	 * This function edit FabricAssignmentQuantityToPIAssignFabricDetail by
	 * PiAssignFabricDetailId, garmentstyle , color , fabricNo, lotNo
	 * 
	 * @param piAssignFabricDetailModel
	 * @return true if edit success, else return false
	 */
	@Override
	public boolean editFabricAssignmentQuantityToPIAssignFabricDetail(PIAssignFabricDetailModel piAssignFabricDetailModel){
		log.info(String
				.format("editFabricAssignmentQuantityToPIAssignFabricDetail with param 'piAssignFabricDetailModel' in class: %s",
						getClass()));
		try{
			Integer PiAssignFabricDetailId = null;
			List<Piassignfabricdetail> lstPIAssignFabricDetail = piassignfabricdetaildao
					.getAllPIAssignFabricDetailCodeByColorGarmentstyleLotNumberFabricNo(
							piAssignFabricDetailModel
								.getColor(), 
							piAssignFabricDetailModel
								.getGarmentstyle(), 
							piAssignFabricDetailModel
								.getLotnumber(), 
							piAssignFabricDetailModel
								.getFabricno());
			for(Piassignfabricdetail piAssignDetail : lstPIAssignFabricDetail){			
					PiAssignFabricDetailId = piAssignDetail.getPiassignfabricdetailcode();
			}
			//Edit
			Piassignfabricdetail piassignfabricdetail = piassignfabricdetaildao.findById(PiAssignFabricDetailId);
			piassignfabricdetail.setPiassignfabricdetailcode(PiAssignFabricDetailId);
			if(piAssignFabricDetailModel.getAssignquantity() != 0 
					&& piAssignFabricDetailModel.getAssignqtypercent() != 0){
				piassignfabricdetail.setAssignquantity(piAssignFabricDetailModel.getAssignquantity());
				piassignfabricdetail.setAssignqtypercent(piAssignFabricDetailModel.getAssignqtypercent());
			}else{
				piassignfabricdetail.setAssignquantity(0);
				piassignfabricdetail.setAssignqtypercent((float) 0);
			}
			
			piassignfabricdetaildao.update(piassignfabricdetail);
			log.debug("editFabricAssignmentQuantityToPIAssignFabricDetail successfully");
			return true;
		}catch(Exception e){
			log.error(String
					.format("editFabricAssignmentQuantityToPIAssignFabricDetail with "
							+ "param 'piAssignFabricDetailModel' in class: %s has error: %s",
								getClass(), e.getMessage()));
//			System.err.println("Editting FabricAssignmentQuantityToPIAssignFabricDetail Failed");
			return false;
		}
	}
	/**
	 * This function is used to save FabricAssignmentQuantityToPIAssignFabricDetail into database
	 * Add new if is not existed , edit if it is existed and assign quantity >0, delete if it is existed and assign quantity < 0
	 * @param piAssignFabricDetailModel
	 * @return true if save successfully, save if have exception
	 */
	@Override
	public boolean saveFabricAssignmentQuantityToPIAssignFabricDetail (PIAssignFabricDetailModel piAssignFabricDetailModel, String userName){
		try{
			//Edit if is existed
			if(isExistedByPIAssignFabricDetailCode(piAssignFabricDetailModel)){
				if(piAssignFabricDetailModel.getAssignqtypercent() > 0 )
				{
					editFabricAssignmentQuantityToPIAssignFabricDetail(piAssignFabricDetailModel);	
				//	saveFAValue(piAssignFabricDetailModel.getLotnumber());
				}
				else
					deletePIAssignFabricDetailByLotNoFabricNoColorAndGarment(
							piAssignFabricDetailModel.getLotnumber()
							, piAssignFabricDetailModel.getFabricno()
							, piAssignFabricDetailModel.getGarmentstyle()
							, piAssignFabricDetailModel.getColor());
			//	saveFAValue(piAssignFabricDetailModel.getLotnumber());
			}else{
				if(piAssignFabricDetailModel.getAssignqtypercent()>0)
				{
					PiassignfabricModel piassignfabricModel 
						= new PiassignfabricModel(piAssignFabricDetailModel.getLotnumber()
													,piAssignFabricDetailModel.getFabricno());

					piassignfabricService.AssignFabricToPi(piassignfabricModel, userName);
					//Add if is not existed
					addFabricAssignmentQuantityToPIAssignFabricDetail(piAssignFabricDetailModel,userName);
				//	saveFAValue(piAssignFabricDetailModel.getLotnumber());
				}
			}
			return true;
			
		}catch(Exception e){
			log.error(String
					.format("saveFabricAssignmentQuantityToPIAssignFabricDetail with param 'piAssignFabricDetailModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
	//		System.err
	//				.println(String
	//						.format("saveFabricAssignmentQuantityToPIAssignFabricDetail with param 'piAssignFabricDetailModel' in class: %s has error: %s",
	//								getClass(), e.getMessage()));
			return false;
		}
		
	}
	
	/***
	 * Function to find Piassignfabricdetail by piassignfabricdetailcode
	 * @param piassignfabricdetailcode
	 * @return null if can not find, else return ID
	 */
	@Override
	public boolean isExistedByPIAssignFabricDetailCode(PIAssignFabricDetailModel piAssignFabricDetailModel) {
		log.debug("in isExistedByPIAssignFabricDetailCode at findPIAssignFabricDetailModelById method");
		try {
			List<Piassignfabricdetail> lstPIAssignFabricDetail = piassignfabricdetaildao
					.getAllPIAssignFabricDetailCodeByColorGarmentstyleLotNumberFabricNo(
							piAssignFabricDetailModel
							.getColor(), piAssignFabricDetailModel
							.getGarmentstyle(), piAssignFabricDetailModel
							.getLotnumber(), piAssignFabricDetailModel
							.getFabricno());
			if(lstPIAssignFabricDetail.size() > 0){
				return true;
			}
			return false;		
		} catch (NullPointerException ne) {
			log.error("in isExistedByPIAssignFabricDetailCode error: "
					+ ne.getMessage());
			throw ne;
		}
	}
		
	/**
	 * This function is used to delete a PIAssignFabricDetailByLotNoAndFabricNo in database.
	 * 
	 * @param fabricNo , lotNo
	 * @return delete successfully =>true , else => false
	 */
	@Override
	public boolean deletePIAssignFabricDetailByLotNoAndFabricNo(String lotNo,String fabricNo) {
		log.info(String.format("deletePIAssignFabricDetailByLotNoAndFabricNo with param 'fabricNo', 'lotNo' in class: %s",
				getClass()));
		try {
			List<Piassignfabricdetail> lstPiassignfabricdetail = piassignfabricdetaildao
					.getAllPIAssignFabricDetailByLotNumberFabricNo(lotNo, fabricNo);
			for(Piassignfabricdetail piassignfabricdetail : lstPiassignfabricdetail){
									
					piassignfabricdetaildao.delete(piassignfabricdetail);
				
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	/**
	 * This function is used to delete a PIAssignFabricDetailByLotNoAndFabricNo in database.
	 * 
	 * @param fabricNo , lotNo ,garmentStyleCode , colorCode
	 * @return delete successfully =>true , else => false
	 */
	@Override
	public boolean deletePIAssignFabricDetailByLotNoFabricNoColorAndGarment(String lotNo,String fabricNo, String garmentStyleCode, String colorCode) {
		log.info(String.format("deletePIAssignFabricDetailByLotNoFabricNoColorAndGarment with param 'fabricNo', 'lotNo', 'garmentStyleCode' , 'colorCode' in class: %s",
				getClass()));
		try {
			List<Piassignfabricdetail> lstPiassignfabricdetail = piassignfabricdetaildao
					.getAllPIAssignFabricDetailCodeByColorGarmentstyleLotNumberFabricNo(colorCode, garmentStyleCode, lotNo, fabricNo);
			for(Piassignfabricdetail piassignfabricdetail : lstPiassignfabricdetail){	
				
					piassignfabricdetaildao.delete(piassignfabricdetail);				
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * This function is used to delete a PIAssignFabricDetailByLotNoAndFabricNo then
	 *  delete a PIAssignFabricByLotNoAndFabricNo in database.
	 * 
	 * @param lotNo, fabricNo
	 * @return delete successfully =>true , else => false
	 */
	@Override
	public boolean deletePIAssignFabricByLotNoAndFabricNo(PiassignfabricModel piassignfabricModel){
		log.info(String.format("deletePIAssignFabricByLotNoAndFabricNo with param 'lotNo','fabricNo' in class: %s",
				getClass()));
		try{
			if(findPIAssignFabricIDByLotNoAndFabricNo(piassignfabricModel.getLotnumber(), piassignfabricModel.getFabricno())!= null){
			//delete from piassign fabric detail
			deletePIAssignFabricDetailByLotNoAndFabricNo(piassignfabricModel.getLotnumber(), piassignfabricModel.getFabricno());
			
			//delete from piassign fabric
			PiassignfabricId piassignfabricId = findPIAssignFabricIDByLotNoAndFabricNo(
					piassignfabricModel.getLotnumber()
					, piassignfabricModel.getFabricno());		
			piassignfabricdao.deleteById(piassignfabricId);
			}

			
			return true;
		}catch(Exception e){
			return false;
		}
	}
	

	/**
	 * This function is used to get YardInBL of a fabric + color
	 * 
	 * @param FabricinformationdetailId
	 * @return YardInBL
	 */
	@Override
	public Double getYardInBL(
			FabricinformationdetailId fabricinformationdetailId) {
		try {
			log.debug("getYardInBL successfully");
			return fabricinformationdetailDao.findById(fabricinformationdetailId).getYardinbl();
		} catch (Exception e) {
			log.error(String.format("getYardInBL in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	@Override
	public Double getInventoryRemained(
			FabricinformationdetailId fabricinformationdetailId , PIAssignFabricDetailModel piAssignFabricDetailModel) {
		try {
			
			//call above function
			Double totalYardInBL= getYardInBL(fabricinformationdetailId);
			Double inventoryRemained = totalYardInBL;

			List<Piassignfabricdetail> lstPiassignfabricdetail = piassignfabricdetaildao
					.getAllAvailableQuantityByColorFabricNo(
							fabricinformationdetailId.getColorcode(), 
							fabricinformationdetailId.getFabricno());

			// find shipping status of a PI

			for (Piassignfabricdetail tmp : lstPiassignfabricdetail) {
		//		System.err.println(tmp.getColor().getColorcode());
				// if colorcode, fabricno of an assignment = colorcode, fabricno
				// of a fabric
				// and shippingstatus of a pi =YES, then minus quantity
									
						if(piassignfabricdetaildao.getShippingStatusEqualYes(piAssignFabricDetailModel.getLotnumber()) != null) {
							inventoryRemained -= tmp.getAssignquantity();
						}						
				
			}
			log.debug("getInventoryRemained successfully");
			return inventoryRemained;
		} catch (Exception e) {
			log.error(String.format(
					"getInventoryRemained in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	/**
	 * This function is used to get AvailableQty of a fabric + color
	 * 
	 * @param FabricinformationdetailId
	 * @return availableQty
	 */
	@Override
	public Double getAvailableQty(
			FabricinformationdetailId fabricinformationdetailId) {
		try {

			
			Double availableQty = getYardInBL(fabricinformationdetailId);
		
			
			List<Piassignfabricdetail> lstPiassignfabricdetail = piassignfabricdetaildao
					.getAllAvailableQuantityByColorFabricNo(
							fabricinformationdetailId
								.getColorcode(), 
							fabricinformationdetailId
								.getFabricno());			

			for (Piassignfabricdetail tmp : lstPiassignfabricdetail) {
	//			System.err.println(tmp.getColor().getColorcode());
			
					availableQty -= tmp.getAssignquantity();
				
			}
			log.debug("getAvailableQty successfully");
			return availableQty;
		} catch (Exception e) {
			log.error(String.format(
					"getAvailableQty in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}	
	/**
	 * This function is used to get Using value of a fabric + color
	 * 
	 * @param FabricinformationdetailId
	 * @return using value
	 */
	@Override
	public Double getUsingValue(FabricinformationdetailId fabricinformationdetailId){
		try{
			Double totalYardinBL = getYardInBL(fabricinformationdetailId);
			Double availableQty = getAvailableQty(fabricinformationdetailId);
			Double usingValue = (double) 0;
			
//			List<Piassignfabricdetail> lstPiassignfabricdetail = piassignfabricdetaildao
//					.getAllAvailableQuantityByColorFabricNo(fabricinformationdetailId.getColorcode()
//							, fabricinformationdetailId.getFabricno());
//			for(Piassignfabricdetail tmp : lstPiassignfabricdetail){
//				System.err.println(tmp.getColor().getColorcode());
				usingValue = totalYardinBL - availableQty;
//			}
			log.debug("getUsingValue successfully");
			return usingValue;
		}catch (Exception e) {
			log.error(String.format(
					"getUsingValue in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}		
	}
	
	/**
	 * This function is used get all fabicno  by lotNumber
	 * 
	 * @return a List of fabricno
	 */

	public List<PiassignfabricId> getAllPiassignfabricIDByLotNumber(String lotNumber) {
		log.info(String.format("getAllPiassignfabricIDByLotNumber in class: %s", getClass()));
		try {
			List<Piassignfabric> lstPiassignfabric = piassignfabricdao.getAllPIAssignFabricByLotNumber(lotNumber);
			List<PiassignfabricId> lstPiassignfabricId = new ArrayList<PiassignfabricId>();
			for (Piassignfabric piassignfabric : lstPiassignfabric) {
				
					lstPiassignfabricId.add(piassignfabric.getId());
				
			}
			log.debug("getAllPiassignfabricIDByLotNumber successfully");
			return lstPiassignfabricId;
		} catch (Exception e) {
			log.error(String.format(
					"getAllPiassignfabricIDByLotNumber in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	public List<Piassignfabricdetail> getPiAssignFabricDetail(PiassignfabricId piassignfabricId){
		log.info(String.format("getPiAssignFabricDetail in class: %s", getClass()));
		try{
			
			List<Piassignfabricdetail> lstPiassignfabricdetail = piassignfabricdetaildao.getAllPIAssignFabricDetailByLotNumber(piassignfabricId.getLotnumber());
			List<Piassignfabricdetail> result = new ArrayList<Piassignfabricdetail>();
			
			for(Piassignfabricdetail tmp : lstPiassignfabricdetail){
				
					result.add(tmp);
				
			}
			return result;
		}catch(Exception e){
			log.error(String.format(
					"getPiAssignFabricDetail in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}	
	}
	
	@Override
	public List<PIAssignFabricDetailModel> getListPIAssignFabricDetail(String lotNumber){
		log.info(String.format("getListPIAssignFabricDetail in class: %s", getClass()));
		try{
			List<PIAssignFabricDetailModel> lstPiassignfabricdetailModel = new ArrayList<PIAssignFabricDetailModel>();
			PIAssignFabricDetailModel pIAssignFabricDetailModel;
			List<PiassignfabricId> lstPiassignfabricId  = getAllPiassignfabricIDByLotNumber(lotNumber);
			for(PiassignfabricId piassignfabricId : lstPiassignfabricId){
				List<Piassignfabricdetail> lstPiassignfabricdetail = getPiAssignFabricDetail(piassignfabricId);
				for(Piassignfabricdetail piassignfabricdetail : lstPiassignfabricdetail)
				{
					pIAssignFabricDetailModel = new PIAssignFabricDetailModel();
					pIAssignFabricDetailModel.setFabricno(piassignfabricId.getFabricno());
					pIAssignFabricDetailModel.setLotnumber(piassignfabricId.getLotnumber());
					pIAssignFabricDetailModel.setColor(piassignfabricdetail.getColor().getColorcode());
					pIAssignFabricDetailModel.setGarmentstyle(piassignfabricdetail.getGarmentstyle().getGarmentstylecode());
					pIAssignFabricDetailModel.setAssignquantity(piassignfabricdetail.getAssignquantity());
					lstPiassignfabricdetailModel.add(pIAssignFabricDetailModel);
				}
			}
			return lstPiassignfabricdetailModel;

		}catch(Exception e){
			log.error(String.format(
					"getListPIAssignFabricDetail in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	
	}
	
	@Override
	public boolean saveFAValue(String lotNumber){
		log.info(String.format("saveFAValue in class: %s", getClass()));
		try{
			Integer faValue = 0;
			CalculateFAValueModel calculatefavalueModel;
			Pi pi = piDao.findById(lotNumber);
			List<Pigriddetail> lstpigriddetail = pigriddetaildao.getListPigriddetailByPigridcode(pi.getPigrid().getPigridcode());
			for(Pigriddetail pigriddetail : lstpigriddetail){
				calculatefavalueModel = new CalculateFAValueModel();
				calculatefavalueModel.setLotNo(lotNumber);
				calculatefavalueModel.setColorCode(pigriddetail.getColor().getColorcode());
				calculatefavalueModel.setGarmentstyleCode(pigriddetail.getGarmentstyle().getGarmentstylecode());
				calculatefavalueModel.setSizeCode(pigriddetail.getSize().getSizecode());
				calculatefavalueModel.setCustomerCode(pi.getCustomerByCustomer1code().getCustomercode());
				faValue = calculateFAValue(calculatefavalueModel);
//				System.err.println("faValue: " + faValue);
				pigriddetail.setFavalue(faValue);
				pigriddetaildao.save(pigriddetail);
			}
			return true;
		}catch(Exception e){
			log.error(String.format(
					"saveFAValue in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
		
	}
	
	//find pigridCode by lotNumber in Pi
	public Integer findPIGridCodeByLotNo (String lotNo){
		log.info(String.format("findPIGridCodeByLotNo in class: %s", getClass()));
		try{
			
			return piDao.findById(lotNo).getPigrid().getPigridcode();
		}catch (Exception e){
			log.error(String.format(
					"findPIGridCodeByLotNo in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	//get total Pcs by pigridCode, garmentstyleCode, colorCode
	public Double getTotalPcs(String lotNo , String garmentstyleCode, String colorCode){
		log.info(String.format("getTotalPcs in class: %s", getClass()));
		try{
			List<Pigriddetail> lstPigriddetail = pigriddetaildao.getAllPigridDetailByPigridCodeColorCodeGarmentStyleCode(
					findPIGridCodeByLotNo(lotNo), colorCode, garmentstyleCode);
			Double totalPcs = (double) 0;	
			for(Pigriddetail pigriddetail : lstPigriddetail){
				
					totalPcs += pigriddetail.getPcs();									
				
			}		
			return (double) totalPcs;
		}catch (Exception e){
			log.error(String.format(
					"getTotalPcs in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	//convert pcs to percent 
	public Float convertPcsToPercent ( String lotNo , String garmentstyleCode, String colorCode, Integer sizeCode){
		log.info(String.format("convertPcsToPercent in class: %s", getClass()));
		try{
			List<Pigriddetail> lstPigriddetail = pigriddetaildao.getAllPigridDetailByPigridCodeColorCodeGarmentStyleCodeSizeCode(
					findPIGridCodeByLotNo(lotNo), colorCode, garmentstyleCode, sizeCode);
		//	Float percentPcs = (float) 0 ;
			for(Pigriddetail pigriddetail : lstPigriddetail){
				
					
				return (float) (pigriddetail.getPcs() * 100 / getTotalPcs(lotNo, garmentstyleCode, colorCode));
				
			}
			return (float) 0;	
		}catch(Exception e){
			log.error(String.format(
					"convertPcsToPercent in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	//get garmentconsumptionCode from garmentconsumtion
	public Integer findGarmentconsumptionCode(String garmentstyleCode, String customerCode, Integer sizeCode){
		log.info(String.format("findGarmentConsumtionCode in class: %s", getClass()));
		try{
			List<Garmentconsumption> lstGarmentconsumption = garmentconsumptionDao
					.getAllGarmentConsumptionCodeByGarmentstyleCodeCustomerCodeSizeCode(garmentstyleCode, customerCode, sizeCode);
			for(Garmentconsumption garmentconsumption : lstGarmentconsumption){
				
				return garmentconsumption.getGarmentconsumptioncode();
				
			}
			return 0;
		}catch(Exception e){
			log.error(String.format(
					"findGarmentconsumptionCode in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}				
	}
	
	// get Consumption Value by garmentconsumptionCode and widthCode in Garmentconsumptiondetail
	public Float getConsumptionValue(String garmentstyleCode, String customerCode, Integer sizeCode , String fabricNo){
		log.info(String.format("getConsumptionValue in class: %s", getClass()));
		try{
			List<Garmentconsumptiondetail> lstGarmentconsumptiondetail = garmentconsumptiondetailDao
																		.getAllGarmentConsumptionValueByWidthCodeGarmentconsumptionCode(
																			fabricinformationDao.findById(fabricNo).getWidth().getWidthcode(),
																			findGarmentconsumptionCode(garmentstyleCode, customerCode, sizeCode));
			for(Garmentconsumptiondetail garmentconsumptiondetail : lstGarmentconsumptiondetail){
				
					return garmentconsumptiondetail.getConvalue();
				
			}
			return (float) 0;
		}catch(Exception e){
			log.error(String.format(
					"getConsumptionValue in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	//calculate FA Value by calculatefavalueModel
	public Integer calculateFAValue(CalculateFAValueModel calculatefavalueModel){
		log.info(String.format("calculateFAValue in class: %s", getClass()));
		try{
			Float AssignQtyDivideConvalue = (float) 0;
			List<Piassignfabric> lstPiassignfabric = piassignfabricdao.getAllPIAssignFabricByLotNumber(calculatefavalueModel.getLotNo());
			List<String> lstFabricAssign = new ArrayList<String>();
			for(Piassignfabric piassignfabric :lstPiassignfabric)
			{
				
					lstFabricAssign.add(piassignfabric.getId().getFabricno());
				
			}
			
			for(String fabricno : lstFabricAssign)
			{
				Integer assignQty = getAssignQtyByLotnoGarmentstyleCodeColorCodeFabricNo(calculatefavalueModel.getLotNo()
				, calculatefavalueModel.getColorCode()
				, calculatefavalueModel.getGarmentstyleCode() 
				, fabricno);
				
				Float convalue = getConsumptionValue(calculatefavalueModel.getGarmentstyleCode()
						, calculatefavalueModel.getCustomerCode()
						, calculatefavalueModel.getSizeCode()
						, fabricno);
//				System.err.println("getGarmentstyleCode: " + calculatefavalueModel.getGarmentstyleCode());
//				System.err.println("getCustomerCode: " + calculatefavalueModel.getCustomerCode());
//				System.err.println("getSizeCode: " + calculatefavalueModel.getSizeCode());
//				System.err.println("fabricno: " + fabricno);
//				System.err.println("assighqty: " + assignQty);
//				System.err.println("convalue: " + convalue);
				if(convalue>0)
				{
					AssignQtyDivideConvalue += assignQty/convalue;	
				}
				else{
					AssignQtyDivideConvalue += assignQty;
				}
				
			}
			
//			Integer sumAssignQty = getAssignQtyByLotnoGarmentstyleCodeColorCode(calculatefavalueModel.getLotNo()
//					, calculatefavalueModel.getColorCode()
//					, calculatefavalueModel.getGarmentstyleCode());
					
//			Integer assignQty = getAssignQtyByLotnoGarmentstyleCodeColorCodeFabricNo(calculatefavalueModel.getLotNo()
//					, calculatefavalueModel.getColorCode()
//					, calculatefavalueModel.getGarmentstyleCode() 
//					, calculatefavalueModel.getFabricNo());

			//System.err.println("assignQty" + assignQty);
			//System.err.println("convalue" + convalue);
			
			Float percent = convertPcsToPercent(calculatefavalueModel.getLotNo()
					, calculatefavalueModel.getGarmentstyleCode()
					, calculatefavalueModel.getColorCode()
					, calculatefavalueModel.getSizeCode());
			
//			System.err.println("Percent" + percent);

			//return (int) ((percent * assignQty /100) / convalue);
//			System.err.println("sadsa: " + (percent / 100) * (AssignQtyDivideConvalue));
			
			return (int) ((percent / 100) * (AssignQtyDivideConvalue));
		}catch(Exception e){
			log.error(String.format(
					"calculateFAValue in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	// get assign quantity by lotNo,garmentStyleCode, colorCode, fabricNo from Piassignfabricdetail
	public Integer getAssignQtyByLotnoGarmentstyleCodeColorCodeFabricNo(String lotNo, String colorCode, String garmentstyleCode , String fabricNo){
		log.info(String.format("getAssignQtyByLotnoGarmentstyleCodeColorCodeFabricNo in class: %s", getClass()));
		try {
			List<Piassignfabricdetail> lstPiassignfabricdetail = piassignfabricdetaildao
					.getAllPIAssignFabricDetailCodeByColorGarmentstyleLotNumberFabricNo(
							colorCode, garmentstyleCode, lotNo, fabricNo);	
			Integer totalAssignQty = 0;
			for(Piassignfabricdetail piassignfabricdetail : lstPiassignfabricdetail){				
					
					log.debug("getAssignQtyByLotnoGarmentstyleCodeColorCodeFabricNo successfully!");
					
					totalAssignQty += piassignfabricdetail.getAssignquantity();
					return totalAssignQty;
				
			}
			return 0;
		}catch(Exception e){
			log.error(String.format(
					"getAssignQtyByLotnoGarmentstyleCodeColorCode in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}		
	}
}
