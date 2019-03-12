package com.chori.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.AccessoryDao;
import com.chori.dao.ColorDao;
import com.chori.dao.GarmentstyleDao;
import com.chori.dao.OrderinternalaccessorydetailDao;
import com.chori.dao.PIDao;
import com.chori.dao.PiAssignInternalAccessoriesOfOrdersDao;
import com.chori.dao.PiassigninternalaccessoriesDao;
import com.chori.dao.PiassigninternalaccessoriesdetailDao;
import com.chori.dao.PigridDao;
import com.chori.dao.PigriddetailDao;
import com.chori.dao.SizeDao;
import com.chori.dao.UserDao;
import com.chori.entity.Accessory;
import com.chori.entity.Orderinternalaccessorydetail;
import com.chori.entity.OrderinternalaccessorydetailId;
import com.chori.entity.Pi;
import com.chori.entity.Piassigninternalaccessories;
import com.chori.entity.Piassigninternalaccessoriesdetail;
import com.chori.entity.Piassigninternalaccessoriesoforders;
import com.chori.entity.Pigriddetail;
import com.chori.model.InternalAccessoriesToAssignModel;
import com.chori.model.PiAssignInternalAccessories_OrderChoseModel;
import com.chori.model.PiassigninternalaccessoriesModel;
import com.chori.model.PiassigninternalaccessoriesdetailModel;

@Repository("piassigninternalaccessoriesService")
public class PiassigninternalaccessoriesServiceImpl extends
		AbstractServiceImpl<Piassigninternalaccessories, Integer> implements
		PiassigninternalaccessoriesService {
	private PiassigninternalaccessoriesDao dao;

	public PiassigninternalaccessoriesServiceImpl() {
	}

	@Autowired
	public PiassigninternalaccessoriesServiceImpl(
			@Qualifier("piassigninternalaccessoriesDao") AbstractDao<Piassigninternalaccessories, Integer> abstractDao) {
		super(abstractDao);
		this.dao = (PiassigninternalaccessoriesDao) abstractDao;
	}

	@Autowired
	private PIDao piDao;

	@SuppressWarnings("unused")
	@Autowired
	private PigridDao piGridDao;

	@Autowired
	private PigriddetailDao piGridDetailDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ColorDao colorDao;

	@Autowired
	private SizeDao sizeDao;
	
	@Autowired
	private GarmentstyleDao garmentstyleDao;

	@Autowired
	private AccessoryDao accDao;

	@Autowired
	private OrderinternalaccessorydetailDao orderAccIntDetailDao;

	@Autowired
	private PiassigninternalaccessoriesDao piassigninternalaccessoriesDao;

	@Autowired
	private PiassigninternalaccessoriesdetailDao piassigninternalaccessoriesdetailDao;

	@Autowired
	private PiAssignInternalAccessoriesOfOrdersDao piAssignInternalAccessoriesOfOrdersDao;
	
	// /**
	// * This function is used get all Accessory which has kind "Internal"
	// * @return list<AccessoryModel>
	// */
	// @Override
	// public List<InternalAccessoriesToAssignModel> getAllInternalAccessory() {
	// log.info(String.format("getAllInternalAccessory in class: %s",
	// getClass()));
	// try{
	// List<Accessory> lstAccessory = accDao.getAll();
	// InternalAccessoriesToAssignModel internalAccessoriesToAssignModel;
	// List<InternalAccessoriesToAssignModel>
	// lstInternalAccessoriesToAssignModel = new
	// ArrayList<InternalAccessoriesToAssignModel>();
	// for (Accessory acc : lstAccessory) {
	// if(acc.getKind().toString().equals("Internal"))
	// {
	// internalAccessoriesToAssignModel = new
	// InternalAccessoriesToAssignModel();
	// internalAccessoriesToAssignModel.setName(acc.getName());
	// internalAccessoriesToAssignModel.setColorcode(acc.getColor()==null?"":acc.getColor().getColorcode());
	// internalAccessoriesToAssignModel.setAccessorycode(acc.getAccessorycode());
	// internalAccessoriesToAssignModel.setDimension(acc.getDimension());
	// internalAccessoriesToAssignModel.setAccessorygroupcode(acc.getAccessorygroup().getAccessorygroupcode());
	// internalAccessoriesToAssignModel.setKind(acc.getKind());
	// internalAccessoriesToAssignModel.setMode(acc.getMode());
	// internalAccessoriesToAssignModel.setInventoryQty(getInventoryQuantity(acc.getAccessorycode()));
	// internalAccessoriesToAssignModel.setAvailableQty(getAvailableQuantity(acc.getAccessorycode()));
	// internalAccessoriesToAssignModel.setImgurl1(acc.getImgurl1());
	// internalAccessoriesToAssignModel.setImgurl2(acc.getImgurl2());
	// lstInternalAccessoriesToAssignModel.add(internalAccessoriesToAssignModel);
	// }
	// }
	// log.debug("getAllInternalAccessory successful");
	// return lstInternalAccessoriesToAssignModel;
	// }catch(Exception e){
	// log.error(String.format("getAllInternalAccessory in class: %s has error: %s",
	// getClass(), e.getMessage()));
	// throw e;
	// }
	// }
	/**
	 * This function is used get all Accessory which has kind "Internal" to
	 * assign list
	 * @param lotnumber
	 * @return list<AccessoryModel> in JSON format
	 */
	@Override
	public List<InternalAccessoriesToAssignModel> getAllInternalAccessory(
			String lotnumber,String factoryCode) {
		log.info(String.format("getAllInternalAccessory in class: %s",
				getClass()));
		try {
			List<Accessory> lstAccessory = accDao.getListAccessoryWithKindInternal();
			InternalAccessoriesToAssignModel internalAccessoriesToAssignModel;

			// to get piassigninternalaccessoriesId by LotNumber
			List<InternalAccessoriesToAssignModel> lstInternalAccessoriesToAssignModel = new ArrayList<InternalAccessoriesToAssignModel>();
			for (Accessory acc : lstAccessory) {
					internalAccessoriesToAssignModel = new InternalAccessoriesToAssignModel();
					internalAccessoriesToAssignModel.setName(acc.getName());
					internalAccessoriesToAssignModel.setColorcode(acc
							.getColor() == null ? "" : acc.getColor()
							.getColorcode());
					internalAccessoriesToAssignModel.setAccessorycode(acc
							.getAccessorycode());
					internalAccessoriesToAssignModel.setDimension(acc
							.getDimension());
					internalAccessoriesToAssignModel
							.setAccessorygroupcode(acc.getAccessorygroup()
									.getAccessorygroupcode());
					internalAccessoriesToAssignModel.setKind(acc.getKind());
					internalAccessoriesToAssignModel.setMode(acc.getMode());
					internalAccessoriesToAssignModel
							.setInventoryQty(getInventoryQuantity(acc
									.getAccessorycode(),factoryCode));
					internalAccessoriesToAssignModel
							.setAvailableQty(getAvailableQuantity(acc
									.getAccessorycode(),factoryCode));
					internalAccessoriesToAssignModel.setImgurl1(acc
							.getImgurl1());
					internalAccessoriesToAssignModel.setImgurl2(acc
							.getImgurl2());
					lstInternalAccessoriesToAssignModel
							.add(internalAccessoriesToAssignModel);
				}
			// when Piassigninternalaccessoriesdetail table not have data
			//if (listPiassigninternalaccessoriesdetail.size() == 0) {
				// load all internal accessory

			//}
//			// if piassigninternalaccessoriesdetail table have data
//			else {
//				// get internal accessory
//				for (Accessory acc : lstAccessory) {
//					if (acc.getKind().toString().equals("Internal")) {
//						// get assign detail
//						for (Piassigninternalaccessoriesdetail piaccessorydetail : listPiassigninternalaccessoriesdetail) {
//							if (isAccessoryCodeExistedInPiassigninternalaccessories(acc
//									.getAccessorycode())) {
//								// when accessory is assigned, get accessory
//								// information of this assign
//								if (piaccessorydetail
//										.getPiassigninternalaccessories()
//										.getAccessory().getAccessorycode()
//										.equals(acc.getAccessorycode())) {
//									internalAccessoriesToAssignModel = new InternalAccessoriesToAssignModel();
//									internalAccessoriesToAssignModel
//											.setName(acc.getName());
//									internalAccessoriesToAssignModel
//											.setColorcode(acc.getColor() == null ? ""
//													: acc.getColor()
//															.getColorcode());
//									internalAccessoriesToAssignModel
//											.setAccessorycode(acc
//													.getAccessorycode());
//									internalAccessoriesToAssignModel
//											.setDimension(acc.getDimension());
//									internalAccessoriesToAssignModel
//											.setAccessorygroupcode(acc
//													.getAccessorygroup()
//													.getAccessorygroupcode());
//									internalAccessoriesToAssignModel
//											.setKind(acc.getKind());
//									internalAccessoriesToAssignModel
//											.setMode(acc.getMode());
//									internalAccessoriesToAssignModel
//											.setInventoryQty(getInventoryQuantity(acc
//													.getAccessorycode(),factoryCode));
//									internalAccessoriesToAssignModel
//											.setAvailableQty(getAvailableQuantity(acc
//													.getAccessorycode(),factoryCode));
//									internalAccessoriesToAssignModel
//											.setImgurl1(acc.getImgurl1());
//									internalAccessoriesToAssignModel
//											.setImgurl2(acc.getImgurl2());
//									for (Integer piAssignInternalAccessoriesIdByLotNumber : listPiAssignInternalAccessoriesIdByLotNumber) {
//										Piassigninternalaccessories tmp = piassigninternalaccessoriesDao
//												.findById(piAssignInternalAccessoriesIdByLotNumber);
//										if (tmp.getPi().getLotnumber()
//												.equals(lotnumber)
//												&& tmp.getAccessory()
//														.getAccessorycode()
//														.equals(acc
//																.getAccessorycode())) {
//											if (piaccessorydetail
//													.getPiassigninternalaccessories()
//													.getPiinternalaccessories() == tmp
//													.getPiinternalaccessories()) {
//												internalAccessoriesToAssignModel
//														.setPiColor(piaccessorydetail
//																.getColor() == null ? ""
//																: piaccessorydetail
//																		.getColor()
//																		.getColorcode());
//												internalAccessoriesToAssignModel
//														.setPiGarmentStyle(piaccessorydetail
//																.getGarmentstyle() == null ? ""
//																: piaccessorydetail
//																		.getGarmentstyle()
//																		.getGarmentstylecode());
//												internalAccessoriesToAssignModel
//														.setPiAssignQty((Integer) (piaccessorydetail
//																.getAssignquantity() == null ? ""
//																: piaccessorydetail
//																		.getAssignquantity()));
//												internalAccessoriesToAssignModel
//														.setSizecode((Integer) (piaccessorydetail
//																.getSize() == null ? ""
//																: piaccessorydetail
//																		.getSize()
//																		.getSizecode()));
//											}
//										}
//									}
//									lstInternalAccessoriesToAssignModel
//											.add(internalAccessoriesToAssignModel);
//								}
//							}
//							// for accessory is not assigned
//							else {
//								internalAccessoriesToAssignModel = new InternalAccessoriesToAssignModel();
//								internalAccessoriesToAssignModel.setName(acc
//										.getName());
//								internalAccessoriesToAssignModel
//										.setColorcode(acc.getColor() == null ? ""
//												: acc.getColor().getColorcode());
//								internalAccessoriesToAssignModel
//										.setAccessorycode(acc
//												.getAccessorycode());
//								internalAccessoriesToAssignModel
//										.setDimension(acc.getDimension());
//								internalAccessoriesToAssignModel
//										.setAccessorygroupcode(acc
//												.getAccessorygroup()
//												.getAccessorygroupcode());
//								internalAccessoriesToAssignModel.setKind(acc
//										.getKind());
//								internalAccessoriesToAssignModel.setMode(acc
//										.getMode());
//								internalAccessoriesToAssignModel
//										.setInventoryQty(getInventoryQuantity(acc
//												.getAccessorycode(),factoryCode));
//								internalAccessoriesToAssignModel
//										.setAvailableQty(getAvailableQuantity(acc
//												.getAccessorycode(),factoryCode));
//								internalAccessoriesToAssignModel.setImgurl1(acc
//										.getImgurl1());
//								internalAccessoriesToAssignModel.setImgurl2(acc
//										.getImgurl2());
//								for (Integer piAssignInternalAccessoriesIdByLotNumber : listPiAssignInternalAccessoriesIdByLotNumber) {
//									Piassigninternalaccessories tmp = piassigninternalaccessoriesDao
//											.findById(piAssignInternalAccessoriesIdByLotNumber);
//									if (tmp.getPi().getLotnumber()
//											.equals(lotnumber)
//											&& tmp.getAccessory()
//													.getAccessorycode()
//													.equals(acc
//															.getAccessorycode())) {
//										if (piaccessorydetail
//												.getPiassigninternalaccessories()
//												.getPiinternalaccessories() == tmp
//												.getPiinternalaccessories()) {
//											internalAccessoriesToAssignModel
//													.setPiColor(piaccessorydetail
//															.getColor() == null ? ""
//															: piaccessorydetail
//																	.getColor()
//																	.getColorcode());
//											internalAccessoriesToAssignModel
//													.setPiGarmentStyle(piaccessorydetail
//															.getGarmentstyle() == null ? ""
//															: piaccessorydetail
//																	.getGarmentstyle()
//																	.getGarmentstylecode());
//											internalAccessoriesToAssignModel
//													.setPiAssignQty((Integer) (piaccessorydetail
//															.getAssignquantity() == null ? ""
//															: piaccessorydetail
//																	.getAssignquantity()));
//											internalAccessoriesToAssignModel
//													.setSizecode((Integer) (piaccessorydetail
//															.getSize() == null ? ""
//															: piaccessorydetail
//																	.getSize()
//																	.getSizecode()));
//										}
//									}
//								}
//								lstInternalAccessoriesToAssignModel
//										.add(internalAccessoriesToAssignModel);
//									
//								// remove duplicate Accessory if this accessory is not assigned, for avoid null row
//								for (int i = 0; i < lstInternalAccessoriesToAssignModel
//										.size() - 1; i++) {
//									if (!isAccessoryCodeExistedInPiassigninternalaccessories(lstInternalAccessoriesToAssignModel
//											.get(i)
//											.getAccessorycode()))
//									{
//										for (int j = i + 1; j < lstInternalAccessoriesToAssignModel
//												.size(); j++){
//											if (lstInternalAccessoriesToAssignModel
//													.get(i)
//													.getAccessorycode()
//													.equals(lstInternalAccessoriesToAssignModel
//															.get(j)
//															.getAccessorycode())) {
//												lstInternalAccessoriesToAssignModel
//														.remove(lstInternalAccessoriesToAssignModel
//																.get(j));
//											}
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//			}
			log.debug("getAllInternalAccessory successful");
			return lstInternalAccessoriesToAssignModel;
		} catch (Exception e) {
			log.error(String.format(
					"getAllInternalAccessory in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used get internal accessory was assigned of a PI
	 * 
	 * @param lotnumber
	 * @return list<AccessoryModel> in JSON
	 */
	@Override
	public List<InternalAccessoriesToAssignModel> getAllInternalAccessoryOfPI(
			String lotNumber, String factoryCode) {
		log.info(String.format("getAllInternalAccessoryOfPI in class: %s",
				getClass()));
		try {
			List<Accessory> lstAccessory = accDao.getListAccessoryWithKindInternal();
			List<Piassigninternalaccessoriesdetail> listPiassigninternalaccessoriesdetail = piassigninternalaccessoriesdetailDao
					.getListPiassigninternalaccessoriesdetailByLotNumber(lotNumber);
			InternalAccessoriesToAssignModel internalAccessoriesToAssignModel;

			// to get piassigninternalaccessoriesId by LotNumber
			List<Integer> listPiAssignInternalAccessoriesIdByLotNumber = findPiAssignInternalAccessoriesIdByLotNumber(lotNumber);
			List<InternalAccessoriesToAssignModel> lstInternalAccessoriesToAssignModel = new ArrayList<InternalAccessoriesToAssignModel>();

			// to set if,else when an accessory was assigned
			PiassigninternalaccessoriesModel piassigninternalaccessoriesModel = new PiassigninternalaccessoriesModel();

			// get internal accessory
			for (Accessory acc : lstAccessory) {
					// get assign detail
					for (Piassigninternalaccessoriesdetail piaccessorydetail : listPiassigninternalaccessoriesdetail) {
						piassigninternalaccessoriesModel.setAccessory(acc
								.getAccessorycode());
						piassigninternalaccessoriesModel.setPi(lotNumber);

						
						if (isPiassigninternalaccessoriesExisted(piassigninternalaccessoriesModel)) {
							
							Double inventoryQty = (double) 0;
							Double availableQuantity = (double) 0;
							internalAccessoriesToAssignModel = new InternalAccessoriesToAssignModel();
							internalAccessoriesToAssignModel.setName(acc
									.getName());
							internalAccessoriesToAssignModel.setColorcode(acc
									.getColor() == null ? "" : acc.getColor()
									.getColorcode());
							internalAccessoriesToAssignModel
									.setAccessorycode(acc.getAccessorycode());
							internalAccessoriesToAssignModel.setDimension(acc
									.getDimension());
							internalAccessoriesToAssignModel
									.setAccessorygroupcode(acc
											.getAccessorygroup()
											.getAccessorygroupcode());
							internalAccessoriesToAssignModel.setKind(acc
									.getKind());
							internalAccessoriesToAssignModel.setMode(acc
									.getMode());
							
							//calculate inventoryQty & availableQty
							if(!lstInternalAccessoriesToAssignModel.isEmpty()
									&& acc.getAccessorycode().equals(lstInternalAccessoriesToAssignModel.get(
									lstInternalAccessoriesToAssignModel.size()-1))
									&& factoryCode.equals(lstInternalAccessoriesToAssignModel.get(
											lstInternalAccessoriesToAssignModel.size()-1)))
							{
								internalAccessoriesToAssignModel.setInventoryQty(inventoryQty);
								internalAccessoriesToAssignModel.setAvailableQty(availableQuantity);
							}
							else
							{
								inventoryQty = getInventoryQuantity(acc
										.getAccessorycode(),factoryCode);
								availableQuantity = getAvailableQuantity(acc
										.getAccessorycode(),factoryCode);
								internalAccessoriesToAssignModel.setInventoryQty(inventoryQty);
								internalAccessoriesToAssignModel.setAvailableQty(availableQuantity);
							}

							internalAccessoriesToAssignModel.setImgurl1(acc
									.getImgurl1());
							internalAccessoriesToAssignModel.setImgurl2(acc
									.getImgurl2());
							for (Integer piAssignInternalAccessoriesIdByLotNumber : listPiAssignInternalAccessoriesIdByLotNumber) {
								Piassigninternalaccessories tmp = piassigninternalaccessoriesDao
										.findById(piAssignInternalAccessoriesIdByLotNumber);
								if (tmp.getPi().getLotnumber()
										.equals(lotNumber)
										&& tmp.getAccessory()
												.getAccessorycode()
												.equals(acc.getAccessorycode())) {
									if (piaccessorydetail
											.getPiassigninternalaccessories()
											.getPiinternalaccessories() == tmp
											.getPiinternalaccessories()) {
										internalAccessoriesToAssignModel
												.setPiColor(piaccessorydetail
														.getColor() == null ? ""
														: piaccessorydetail
																.getColor()
																.getColorcode());
										internalAccessoriesToAssignModel
												.setPiGarmentStyle(piaccessorydetail
														.getGarmentstyle() == null ? ""
														: piaccessorydetail
																.getGarmentstyle()
																.getGarmentstylecode());
										internalAccessoriesToAssignModel
										.setSizecode((Integer) (piaccessorydetail
												.getSize() == null ? ""
												: piaccessorydetail.getSize().getSizecode()));
										internalAccessoriesToAssignModel.setSizename(piaccessorydetail
												.getSize() == null ? "" : getSizeNameBySizeCode(piaccessorydetail
														.getSize().getSizecode()));
										internalAccessoriesToAssignModel
												.setPiAssignQty((Integer) (piaccessorydetail
														.getAssignquantity() == null ? ""
														: piaccessorydetail
																.getAssignquantity()));
									}
								}
							}

							// piassigninternalaccessoriesDao.findById(id)
							// piassigninternalaccessoriesDao.findById(piAssignInternalAccessoriesIdByLotNumber).
							// if(piaccessorydetail.getPiassigninternalaccessories().getPiinternalaccessories()
							// == piAssignInternalAccessoriesIdByLotNumber)
							// {
							// internalAccessoriesToAssignModel.setPiColor(piaccessorydetail.getColor().getColorcode());
							// internalAccessoriesToAssignModel.setPiGarmentStyle(piaccessorydetail.getGarmentstyle().getGarmentstylecode());
							// internalAccessoriesToAssignModel.setPiAssignQty(piaccessorydetail.getAssignquantity());
							// }

							//avoid null
							if(internalAccessoriesToAssignModel.getColorcode()!=null
									&& internalAccessoriesToAssignModel.getPiGarmentStyle()!=null
									&& internalAccessoriesToAssignModel.getSizecode()!=null)
							{
								lstInternalAccessoriesToAssignModel
										.add(internalAccessoriesToAssignModel);
							}
						}

					}
			}
			log.debug("getAllInternalAccessoryOfPI successful");
			return lstInternalAccessoriesToAssignModel;
		} catch (Exception e) {
			log.error(String.format(
					"getAllInternalAccessoryOfPI in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to get sizename by sizecode for load list
	 * @param sizeCode
	 * @return sizeName
	 */
	@Override
	public String getSizeNameBySizeCode(Integer sizeCode) {
		log.info(String
				.format("getSizeNameBySizeCode with param 'lotnumber,accessorycode' in class: %s",
						getClass()));
		try {
			return sizeDao.findById(sizeCode).getSizename().toString();			
		} catch (Exception e) {
			log.error(String
					.format("getSizeNameBySizeCode with param 'lotnumber,accessorycode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get inventory quantity of an accessory
	 * 
	 * @param accessoryCode, factoryCode
	 * @return inventoryQuantity
	 */
	public Double getInventoryQuantity(String accessoryCode, String factoryCode) {
		Double inventoryQty = (double) 0;
		// OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel =
		// new OrderInternalAccessoryDetailModel();
		List<Orderinternalaccessorydetail> lstOrderInternalAccessoryDetail = orderAccIntDetailDao
				.getListOrderinternalaccessorydetailByAccessoryCodeAndFactoryCode(accessoryCode, factoryCode);
		if (lstOrderInternalAccessoryDetail.size() > 0) {
			for (Orderinternalaccessorydetail orderInternalAccessoryDetail : lstOrderInternalAccessoryDetail) {
				inventoryQty += orderInternalAccessoryDetail.getActualdelvquantity();
			}
		}
		return inventoryQty;
	}

	/**
	 * This function is used to get available quantity of an accessory
	 * 
	 * @param accessoryCode,factoryCode
	 * @return AvailableQuantity
	 */
	public Double getAvailableQuantity(String accessoryCode, String factoryCode) {
		Double inventoryQty = getInventoryQuantity(accessoryCode, factoryCode);
		Double availableQty = inventoryQty;
		Double assignQty = (double) 0;
		List<Piassigninternalaccessories> lstPiassigninternalaccessories = piassigninternalaccessoriesDao
				.getListPiAssignInternalAccessoriesByAccessoryAndFactoryCode(accessoryCode, factoryCode);
		if (lstPiassigninternalaccessories.size() > 0) {
			for (Piassigninternalaccessories piassigninternalaccessories : lstPiassigninternalaccessories) {
//					//calculate assignQty from piassigninternalaccessoriesdetail
//					assignQty += GetAssignQuantity(piassigninternalaccessories
//							.getPiinternalaccessories());
				//calculate assignQty from piassigninternalaccessoriesdoforders
				assignQty += GetAssignQuantityInPIAssignIntAccOfOrders(piassigninternalaccessories
						.getPiinternalaccessories());
			}
		}
		availableQty = inventoryQty - assignQty;
		return availableQty;
	}

	/**
	 * This function is used to get assign quantity of a pi internal accessories when load detail
	 * 
	 * @param Piassigninternalaccessories (primary key of piassigninternalaccessories table)
	 * @return assignQty
	 */
	@Override
	public Double GetAssignQuantity(Integer Piassigninternalaccessories) {
		Double assignQty = (double) 0;
		List<Piassigninternalaccessoriesdetail> lstPiassigninternalaccessoriesdetail = piassigninternalaccessoriesdetailDao
				.getListPiassigninternalaccessoriesdetailByPiassigninternalaccessories(Piassigninternalaccessories);
		for (Piassigninternalaccessoriesdetail piassigninternalaccessoriesdetail : lstPiassigninternalaccessoriesdetail) {
			assignQty += piassigninternalaccessoriesdetail
					.getAssignquantity();
		}
		return assignQty;
		// Piassigninternalaccessoriesdetail piassigninternalaccessoriesdetail =
		// piassigninternalaccessoriesdetailDao.findById(piinternalaccessoriesCode);
		// return piassigninternalaccessoriesdetail.getAssignquantity();
	}

	/**
	 * This function is used to get assign quantity of a pi internal accessories when load detail
	 * 
	 * @param Piassigninternalaccessories (primary key of piassigninternalaccessories table)
	 * @return sum of orderQty
	 */
	@Override
	public Double GetAssignQuantityInPIAssignIntAccOfOrders(Integer Piassigninternalaccessories) {
		Double assignQty = (double) 0;
		List<Piassigninternalaccessoriesoforders> lstPiassigninternalaccessoriesoforders = piAssignInternalAccessoriesOfOrdersDao
				.getListPiassigninternalaccessoriesofordersByPiassigninternalaccessories(Piassigninternalaccessories);
		for (Piassigninternalaccessoriesoforders piassigninternalaccessoriesoforders : lstPiassigninternalaccessoriesoforders) {
			assignQty += piassigninternalaccessoriesoforders
					.getOrderassignquantity();
		}
		return assignQty;
	}
	
	/**
	 * This function is used to assign internal accessory to pi
	 * 
	 * @param piassigninternalaccessoriesModel
	 *            , userName
	 * @return true if add successfully, false if have exception
	 */
	public boolean addPiassigninternalaccessories(
			PiassigninternalaccessoriesModel piassigninternalaccessoriesModel,
			String userName) {
		log.info(String.format("addPiassigninternalaccessories in class: %s",
				getClass()));
		try {
			// add piassgininternalaccessories
			Piassigninternalaccessories piassigninternalaccessories = new Piassigninternalaccessories();
			piassigninternalaccessories.setCreatedate(new Date());
			piassigninternalaccessories.setAccessory(accDao
					.findById(piassigninternalaccessoriesModel.getAccessory()));
			piassigninternalaccessories.setUser(userDao.findById(userName));
			piassigninternalaccessories.setPi(piDao
					.findById(piassigninternalaccessoriesModel.getPi()));
			if (!isPiassigninternalaccessoriesExisted(piassigninternalaccessoriesModel)) {
				dao.save(piassigninternalaccessories);
				Integer piInternalAccessoriesCode = dao.findById(
						piassigninternalaccessories.getPiinternalaccessories())
						.getPiinternalaccessories();
				Piassigninternalaccessoriesdetail piassigninternalaccessoriesdetail;
				// add piassgininternalaccessories detail
				List<PiassigninternalaccessoriesdetailModel> lstPiassigninternalaccessoriesdetailModel = findPiAssignInternalAccessoriesDetailModel(piassigninternalaccessoriesModel
						.getPi());
				for (PiassigninternalaccessoriesdetailModel tmp : lstPiassigninternalaccessoriesdetailModel) {
//					System.err.println(tmp
//									.getSizecode());
					piassigninternalaccessoriesdetail = new Piassigninternalaccessoriesdetail();
					piassigninternalaccessoriesdetail.setAssignquantity((double) 0);
					piassigninternalaccessoriesdetail.setColor(colorDao
							.findById(tmp.getColor()));
					piassigninternalaccessoriesdetail.setCreatedate(new Date());
					piassigninternalaccessoriesdetail
							.setGarmentstyle(garmentstyleDao.findById(tmp
									.getGarmentstyle()));
					piassigninternalaccessoriesdetail
							.setSize(sizeDao.findById(tmp
									.getSizecode()));
					piassigninternalaccessoriesdetail
							.setPiassigninternalaccessories(piassigninternalaccessoriesDao
									.findById(piInternalAccessoriesCode));
					piassigninternalaccessoriesdetailDao
							.save(piassigninternalaccessoriesdetail);
				}
			}
			log.debug("addPiassigninternalaccessories into database successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("addPiassigninternalaccessories with param 'piassigninternalaccessoriesModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println("addPiassigninternalaccessories into database fail, class PiassigninternalaccessoriesService");
			return false;
		}
	}

	/**
	 * This function is used get all GarmentStyle x Color of a PI
	 * 
	 * @return list<PiassigninternalaccessoriesdetailModel> by JSON format
	 */
	@Override
	public List<PiassigninternalaccessoriesdetailModel> getPiassigninternalaccessoriesdetailModelByPiInternalAccessoryCode(
			Integer piInternalAccessories) {
		log.info(String
				.format("getPiassigninternalaccessoriesdetailModelByPiInternalAccessoryCode in class: %s",
						getClass()));
		try {
			PiassigninternalaccessoriesdetailModel piassigninternalaccessoriesdetailModel;
			List<Piassigninternalaccessoriesdetail> lstPiassigninternalaccessoriesdetail = piassigninternalaccessoriesdetailDao
					.getListPiassigninternalaccessoriesdetailByPiassigninternalaccessories(piInternalAccessories);
			List<PiassigninternalaccessoriesdetailModel> lstPiassigninternalaccessoriesdetailModel = new ArrayList<PiassigninternalaccessoriesdetailModel>();
			for (Piassigninternalaccessoriesdetail piassigninternalaccessoriesdetail : lstPiassigninternalaccessoriesdetail) {
					piassigninternalaccessoriesdetailModel = new PiassigninternalaccessoriesdetailModel();
					piassigninternalaccessoriesdetailModel
							.setAssignquantity(piassigninternalaccessoriesdetail
									.getAssignquantity());
					piassigninternalaccessoriesdetailModel
							.setColor(piassigninternalaccessoriesdetail
									.getColor().getColorcode());
					piassigninternalaccessoriesdetailModel
							.setCreatedate(piassigninternalaccessoriesdetail
									.getCreatedate());
					piassigninternalaccessoriesdetailModel
							.setGarmentstyle(piassigninternalaccessoriesdetail
									.getGarmentstyle().getGarmentstylecode());
					piassigninternalaccessoriesdetailModel
							.setPiassigninternalaccessories(piassigninternalaccessoriesdetail
									.getPiassigninternalaccessories()
									.getPiinternalaccessories());
					piassigninternalaccessoriesdetailModel
							.setPiinternalaccdetailcode(piassigninternalaccessoriesdetail
									.getPiinternalaccdetailcode());

					lstPiassigninternalaccessoriesdetailModel
							.add(piassigninternalaccessoriesdetailModel);
			}
			log.debug("getPiassigninternalaccessoriesdetailModelByPiInternalAccessoryCode successful");
			return lstPiassigninternalaccessoriesdetailModel;
		} catch (Exception e) {
			log.error(String
					.format("getPiassigninternalaccessoriesdetailModelByPiInternalAccessoryCode in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	// /**
	// * This function is used get all GarmentStyle x Color of a PI
	// * @return list<AccessoryModel>
	// */
	// @Override
	// public List<PiassigninternalaccessoriesdetailModel>
	// getPiassigninternalaccessoriesdetailModelByPiInternalAccessoryCode(Integer
	// piInternalAccessories) {
	// log.info(String.format("getPiassigninternalaccessoriesdetailModelByPiInternalAccessoryCode in class: %s",
	// getClass()));
	// try{
	// PiassigninternalaccessoriesdetailModel
	// piassigninternalaccessoriesdetailModel;
	// List<Piassigninternalaccessoriesdetail>
	// lstPiassigninternalaccessoriesdetail =
	// piassigninternalaccessoriesdetailDao.getAll();
	// List<PiassigninternalaccessoriesdetailModel>
	// lstPiassigninternalaccessoriesdetailModel = new
	// ArrayList<PiassigninternalaccessoriesdetailModel>();
	// for (Piassigninternalaccessoriesdetail piassigninternalaccessoriesdetail
	// : lstPiassigninternalaccessoriesdetail) {
	// if(piassigninternalaccessoriesdetail.getPiassigninternalaccessories().getPiinternalaccessories()
	// == piInternalAccessories)
	// {
	// piassigninternalaccessoriesdetailModel = new
	// PiassigninternalaccessoriesdetailModel();
	// piassigninternalaccessoriesdetailModel.setAssignquantity(piassigninternalaccessoriesdetail.getAssignquantity());
	// piassigninternalaccessoriesdetailModel.setColor(piassigninternalaccessoriesdetail.getColor().getColorcode());
	// piassigninternalaccessoriesdetailModel.setCreatedate(piassigninternalaccessoriesdetail.getCreatedate());
	// piassigninternalaccessoriesdetailModel.setGarmentstyle(piassigninternalaccessoriesdetail.getGarmentstyle().getGarmentstylecode());
	// piassigninternalaccessoriesdetailModel.setPiassigninternalaccessories(piassigninternalaccessoriesdetail.getPiassigninternalaccessories().getPiinternalaccessories());
	// piassigninternalaccessoriesdetailModel.setPiinternalaccdetailcode(piassigninternalaccessoriesdetail.getPiinternalaccdetailcode());
	//
	// lstPiassigninternalaccessoriesdetailModel.add(piassigninternalaccessoriesdetailModel);
	// }
	// }
	// log.debug("getPiassigninternalaccessoriesdetailModelByPiInternalAccessoryCode successful");
	// return lstPiassigninternalaccessoriesdetailModel;
	// }catch(Exception e){
	// log.error(String.format("getPiassigninternalaccessoriesdetailModelByPiInternalAccessoryCode in class: %s has error: %s",
	// getClass(), e.getMessage()));
	// throw e;
	// }
	// }

	/**
	 * This function check if an accessory for assign PI is existed in database
	 * 
	 * @param PiassigninternalaccessoriesModel
	 * @return false if not existed, true if existed
	 */
	public boolean isPiassigninternalaccessoriesExisted(
			PiassigninternalaccessoriesModel piassigninternalaccessoriesModel) {
		log.debug("in PiassigninternalaccessoriesService at isPiassigninternalaccessoriesExisted method");
		try {

			List<Piassigninternalaccessories> lstPiassigninternalaccessories = piassigninternalaccessoriesDao
					.getListPiAssignInternalAccessoriesByAccessoryCodeAndLotNumber(piassigninternalaccessoriesModel.getAccessory(),
							piassigninternalaccessoriesModel
							.getPi());
			if(lstPiassigninternalaccessories.size()>0)
			{
				return true;
			}
			return false;
		} catch (NullPointerException ne) {
			log.error("in PiassigninternalaccessoriesService at isPiassigninternalaccessoriesExisted method error: "
					+ ne.getMessage());
			return false;
		}
	}

	/**
	 * This function check if an accessorycode is existed in
	 * piassigninternalaccessories
	 * 
	 * @param accessorycode
	 * @return false if not existed, true if existed
	 */
	public boolean isAccessoryCodeExistedInPiassigninternalaccessories(
			String accessorycode) {
		log.debug("in PiassigninternalaccessoriesService at isAccessoryCodeExistedInPiassigninternalaccessories method");
		try {
			List<Piassigninternalaccessories> lstPiassigninternalaccessories = piassigninternalaccessoriesDao
					.getListPiAssignInternalAccessoriesByAccessoryCode(accessorycode);
			if (lstPiassigninternalaccessories.size()>0) {
				return true;
			}
			return false;
		} catch (NullPointerException ne) {
			log.error("in PiassigninternalaccessoriesService at isAccessoryCodeExistedInPiassigninternalaccessories method error: "
					+ ne.getMessage());
			return false;
		}
	}

	/**
	 * This function find piassigninternalaccessoriesId by accessorycode and
	 * lotnumber
	 * 
	 * @param piassigninternalaccessoriesModel
	 * @return piassigninternalaccessoriesId
	 */
	public Integer findPiAssignInternalAccessoriesIdByAccessoryCodeAndLotNumber(
			PiassigninternalaccessoriesModel piassigninternalaccessoriesModel) {
		log.info(String
				.format("findPiAssignInternalAccessoriesIdByAccessoryCodeAndLotNumber with param 'piassigninternalaccessoriesModel' in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessories> lstPiassigninternalaccessories = piassigninternalaccessoriesDao
					.getListPiAssignInternalAccessoriesByAccessoryCodeAndLotNumber(piassigninternalaccessoriesModel.getAccessory()
							,piassigninternalaccessoriesModel.getPi());
			for (Piassigninternalaccessories tmp : lstPiassigninternalaccessories) {
				log.debug("findPiAssignInternalAccessoriesIdByAccessoryCodeAndLotNumber successfully");
				return tmp.getPiinternalaccessories();
			}
			return null;
		} catch (Exception e) {
			log.error(String
					.format("findPiAssignInternalAccessoriesIdByAccessoryCodeAndLotNumber with param 'piassigninternalaccessoriesModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function find piassigninternalaccessoriesId by accessorycode and
	 * lotnumber
	 * 
	 * @param lotnumber
	 *            ,accessorycode
	 * @return piassigninternalaccessoriesId
	 */
	public Integer findPiAssignInternalAccessoriesIdByAccessoryCodeAndLotNumber2(
			String lotnumber, String accessorycode) {
		log.info(String
				.format("findPiAssignInternalAccessoriesIdByAccessoryCodeAndLotNumber2 with param 'lotnumber,accessorycode' in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessories> lstPiassigninternalaccessories = piassigninternalaccessoriesDao
					.getListPiAssignInternalAccessoriesByAccessoryCodeAndLotNumber(accessorycode,lotnumber);
			for (Piassigninternalaccessories tmp : lstPiassigninternalaccessories) {
				log.debug("findPiAssignInternalAccessoriesIdByAccessoryCodeAndLotNumber2 successfully");
				return tmp.getPiinternalaccessories();
			}
			return null;
		} catch (Exception e) {
			log.error(String
					.format("findPiAssignInternalAccessoriesIdByAccessoryCodeAndLotNumber2 with param 'lotnumber,accessorycode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete an piAssignInternalAccessories and its
	 * detail in database.
	 * @param piAssignInternalAccessoriesId
	 * @return delete successfully =>true , else => false
	 */
	public boolean deletePiAssignInternalAccessoriesById(
			Integer piAssignInternalAccessoriesId) {
		log.info(String
				.format("deletePiAssignInternalAccessoriesById with param 'piAssignInternalAccessoriesId' in class: %s",
						getClass()));
		try {
			List<Integer> piinternalaccessoriesdetailId = new ArrayList<Integer>();
			List<Piassigninternalaccessoriesdetail> lstPiassigninternalaccessoriesdetail = piassigninternalaccessoriesdetailDao
					.getListPiassigninternalaccessoriesdetailByPiassigninternalaccessories(piAssignInternalAccessoriesId);
			// get detail Id by piassignaccessoriedId
			for (Piassigninternalaccessoriesdetail piassigninternalaccessoriesdetail : lstPiassigninternalaccessoriesdetail) {
				piinternalaccessoriesdetailId
						.add(piassigninternalaccessoriesdetail
								.getPiinternalaccdetailcode());
			}
			// delete assign detail
			for (Integer tmp : piinternalaccessoriesdetailId) {
				piassigninternalaccessoriesdetailDao.deleteById(tmp);
			}
			
			//delete assign of orders
			deletePiAssignInternalAccessoriesOfOrdersByPiInternalAccessories(piAssignInternalAccessoriesId);
			
			// delete assign
			piassigninternalaccessoriesDao
					.deleteById(piAssignInternalAccessoriesId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * This function is used to delete all row in PiAssignInternalAccessoriesOfOrders by PiInternalAccessories 
	 * @param piAssignInternalAccessoriesId
	 * @return delete successfully =>true , else => false
	 */
	public void deletePiAssignInternalAccessoriesOfOrdersByPiInternalAccessories(Integer piInternalAccessories) {
		log.info(String
				.format("deletePiAssignInternalAccessoriesOfOrdersByPiInternalAccessories with param 'piInternalAccessories' in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessoriesoforders> lstPiassigninternalaccessoriesoforders = piAssignInternalAccessoriesOfOrdersDao
					.getListPiassigninternalaccessoriesofordersByPiassigninternalaccessories(piInternalAccessories);
			for(Piassigninternalaccessoriesoforders piassigninternalaccessoriesoforders : lstPiassigninternalaccessoriesoforders)
			{
				piAssignInternalAccessoriesOfOrdersDao.delete(
						piAssignInternalAccessoriesOfOrdersDao
						.findById(piassigninternalaccessoriesoforders
								.getPiassigninternalaccessoriesoforderscode()));
			}
		} catch (Exception e) {
			log.error(String
					.format("deletePiAssignInternalAccessoriesOfOrdersByPiInternalAccessories with param 'piInternalAccessories' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function find piassigninternalaccessoriesId by lotnumber
	 * 
	 * @param lotnumber
	 * @return list Id of PiAssignInternalAccessories
	 */
	public List<Integer> findPiAssignInternalAccessoriesIdByLotNumber(
			String lotnumber) {
		log.info(String
				.format("findPiAssignInternalAccessoriesIdByLotNumber with param 'lotnumber' in class: %s",
						getClass()));
		try {
			List<Piassigninternalaccessories> lstPiassigninternalaccessories = piassigninternalaccessoriesDao
					.getListPiAssignInternalAccessoriesByLotNumber(lotnumber);
			List<Integer> listPiassigninternalaccessoriesIdByLotNumber = new ArrayList<Integer>();
			for (Piassigninternalaccessories tmp : lstPiassigninternalaccessories) {
				listPiassigninternalaccessoriesIdByLotNumber.add(tmp
						.getPiinternalaccessories());
				log.debug("findPiAssignInternalAccessoriesIdByLotNumber successfully");
			}
			return listPiassigninternalaccessoriesIdByLotNumber;
		} catch (Exception e) {
			log.error(String
					.format("findPiAssignInternalAccessoriesIdByLotNumber with param 'lotnumber' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function to find PiAssignInternalAccessoriesDetail by LotNumber and
	 * AccessoryCode
	 * 
	 * @param lotnumber
	 *            , accessorycode
	 * @return detail of this PiAssignInternalAccessories in list
	 */
	public List<PiassigninternalaccessoriesdetailModel> findPiAssignInternalAccessoriesModelDetailByLotNumberAndAccessoryCode(
			String lotnumber, String accessorycode) {
		log.info(String
				.format("findPiAssignInternalAccessoriesModelDetailByLotNumberAndAccessoryCode with param 'lotnumber' in class: %s",
						getClass()));
		try {
			// find piassigninternalaccessoriesId by lotnumber and accessorycode
			Integer piAssignInternalAccessoriesId = findPiAssignInternalAccessoriesIdByAccessoryCodeAndLotNumber2(
					lotnumber, accessorycode);
			// Pi pi = piDao.findById(lotnumber);
			// List<Pigriddetail> lstpigriddetail = piGridDetailDao.getAll();
			PiassigninternalaccessoriesdetailModel piassigninternalaccessoriesdetailModel;
			List<Piassigninternalaccessoriesdetail> lstPiassigninternalaccessoriesdetail = piassigninternalaccessoriesdetailDao
					.getListPiassigninternalaccessoriesdetailByPiassigninternalaccessories(piAssignInternalAccessoriesId);
			List<PiassigninternalaccessoriesdetailModel> listPiassigninternalaccessoriesdetailModel = new ArrayList<PiassigninternalaccessoriesdetailModel>();
			for (Piassigninternalaccessoriesdetail tmp : lstPiassigninternalaccessoriesdetail) {
				piassigninternalaccessoriesdetailModel = new PiassigninternalaccessoriesdetailModel();
				piassigninternalaccessoriesdetailModel
						.setPiinternalaccdetailcode(tmp
								.getPiinternalaccdetailcode());
				piassigninternalaccessoriesdetailModel
						.setPiassigninternalaccessories(tmp
								.getPiassigninternalaccessories()
								.getPiinternalaccessories());
				piassigninternalaccessoriesdetailModel.setGarmentstyle(tmp
						.getGarmentstyle().getGarmentstylecode());
				piassigninternalaccessoriesdetailModel.setColor(tmp
						.getColor().getColorcode());
				piassigninternalaccessoriesdetailModel
						.setAssignquantity(tmp.getAssignquantity());
				listPiassigninternalaccessoriesdetailModel
						.add(piassigninternalaccessoriesdetailModel);
			log.debug("findPiAssignInternalAccessoriesModelDetailByLotNumberAndAccessoryCode successfully");
			}
			return listPiassigninternalaccessoriesdetailModel;
		} catch (Exception e) {
			log.error(String
					.format("findPiAssignInternalAccessoriesModelDetailByLotNumberAndAccessoryCode with param 'lotnumber' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function find pi grid code of a pi by lotnumber
	 * 
	 * @param lotnumber
	 * @return piGridCode
	 */
	public Integer findPiGridCodeOfAPi(String lotnumber) {
		log.info(String.format(
				"findPiGridCodeOfAPi with param 'lotnumber' in class: %s",
				getClass()));
		try {
			Pi pi = piDao.findById(lotnumber);
			log.debug("findPiGridCodeOfAPi successfully");
			return pi.getPigrid().getPigridcode();
		} catch (Exception e) {
			log.error(String
					.format("findPiGridCodeOfAPi with param 'lotnumber' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function find PiAssignInternalAccessoriesDetail (GarmentStyle +
	 * Color) by lotnumber
	 * 
	 * @param lotnumber
	 * @return list of PiAssignInternalAccessoriesDetail (GarmentStyle + Color)
	 */
	public List<PiassigninternalaccessoriesdetailModel> findPiAssignInternalAccessoriesDetailModel(
			String lotnumber) {
		log.info(String
				.format("findPiAssignInternalAccessoriesDetailModel with param 'lotnumber' in class: %s",
						getClass()));
		try {
			// get all GarmentStyle x Color of a pi
			List<Pigriddetail> lstPigriddetail = piGridDetailDao.getListPigriddetailByPigridcode(findPiGridCodeOfAPi(lotnumber));
			List<PiassigninternalaccessoriesdetailModel> lstPiassigninternalaccessoriesdetailModel = new ArrayList<PiassigninternalaccessoriesdetailModel>();
			PiassigninternalaccessoriesdetailModel piassigninternalaccessoriesdetailModel;
			for (Pigriddetail pigriddetail : lstPigriddetail) {
				piassigninternalaccessoriesdetailModel = new PiassigninternalaccessoriesdetailModel();
				piassigninternalaccessoriesdetailModel
						.setColor(pigriddetail.getColor().getColorcode());
				piassigninternalaccessoriesdetailModel
						.setGarmentstyle(pigriddetail.getGarmentstyle()
								.getGarmentstylecode());
				piassigninternalaccessoriesdetailModel
						.setSizecode(pigriddetail.getSize()
								.getSizecode());
				lstPiassigninternalaccessoriesdetailModel
						.add(piassigninternalaccessoriesdetailModel);
			}

			// remove duplicate GarmentStyle x Color x Size
			for (int i = 0; i < lstPiassigninternalaccessoriesdetailModel
					.size() - 1; i++) {
				for (int j = i + 1; j < lstPiassigninternalaccessoriesdetailModel
						.size(); j++)
					if (lstPiassigninternalaccessoriesdetailModel
							.get(i)
							.getGarmentstyle()
							.equals(lstPiassigninternalaccessoriesdetailModel
									.get(j).getGarmentstyle())
							&& lstPiassigninternalaccessoriesdetailModel
									.get(i)
									.getColor()
									.equals(lstPiassigninternalaccessoriesdetailModel
											.get(j).getColor())
							&& lstPiassigninternalaccessoriesdetailModel
							.get(i)
							.getSizecode()
							.equals(lstPiassigninternalaccessoriesdetailModel
									.get(j).getSizecode()))	{
						lstPiassigninternalaccessoriesdetailModel
								.remove(lstPiassigninternalaccessoriesdetailModel
										.get(j));
					}
			}
			log.debug("findPiAssignInternalAccessoriesDetailModel successfully");
			return lstPiassigninternalaccessoriesdetailModel;

		} catch (Exception e) {
			log.error(String
					.format("findPiAssignInternalAccessoriesDetailModel with param 'lotnumber' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if assign internal accessory detail of a PI is
	 * existed in database
	 * 
	 * @param PiassigninternalaccessoriesdetailModel
	 * @return false if not existed, true if existed
	 */
	public boolean isPiassigninternalaccessoriesdetailExisted(
			PiassigninternalaccessoriesdetailModel piassigninternalaccessoriesdetailModel) {
		log.debug("in PiassigninternalaccessoriesService at isPiassigninternalaccessoriesdetailExisted method");
		try {

			List<Piassigninternalaccessoriesdetail> lstPiassigninternalaccessoriesdetail = piassigninternalaccessoriesdetailDao
					.getListPiassigninternalaccessoriesdetailByColorCodeAndGarmentStyleCode(piassigninternalaccessoriesdetailModel
							.getColor()
							,piassigninternalaccessoriesdetailModel
							.getGarmentstyle());
			if(lstPiassigninternalaccessoriesdetail.size()>0)
			{
					return true;
			}
			return false;
		} catch (NullPointerException ne) {
			log.error("in PiassigninternalaccessoriesService at isPiassigninternalaccessoriesdetailExisted method error: "
					+ ne.getMessage());
			return false;
		}
	}
	
	/**
	 * This function is used get list of all order internal accessory which is suitable for assign (follow accessory)
	 * If this order is assigned in PI, then load its assignQty, else assignQty = 0;
	 * @param accessorycode, lotNo
	 * @return list all PiAssignInternalAccessories_OrderChoseModel in JSON format
	 */
	@Override
	public List<PiAssignInternalAccessories_OrderChoseModel> getOrderInternalListForAssign(String accessoryCode, String lotNo) {
		log.info(String.format("getOrderInternalListForAssign in class: %s", getClass()));
		try{
			//get factorycode by lotnumber
			String factoryCode = piDao.findById(lotNo).getFactory().getFactorycode();
			
			List<Orderinternalaccessorydetail> lstOrderinternalaccessorydetail = orderAccIntDetailDao
					.getListOrderinternalaccessorydetailByAccessoryCodeAndFactoryCode(accessoryCode,factoryCode);
			PiAssignInternalAccessories_OrderChoseModel piAssignInternalAccessories_OrderChoseModel;
			List<PiAssignInternalAccessories_OrderChoseModel> lstPiAssignInternalAccessories_OrderChoseModel = new ArrayList<PiAssignInternalAccessories_OrderChoseModel>();
			//loop lstOrderinternalaccessorydetail to find which row equal to param accessoryCode
			for(Orderinternalaccessorydetail orderDetail : lstOrderinternalaccessorydetail)
			{
				piAssignInternalAccessories_OrderChoseModel = new PiAssignInternalAccessories_OrderChoseModel();
				piAssignInternalAccessories_OrderChoseModel.setAccessorycode(orderDetail.getAccessory().getAccessorycode());
				piAssignInternalAccessories_OrderChoseModel.setAccsuplierCode(orderDetail.getOrderinternalaccessory().getAccessorysupplier().getAccsuppliercode());
				piAssignInternalAccessories_OrderChoseModel.setActualdelvquantity(orderDetail.getActualdelvquantity()==null?orderDetail.getOrderquantity():orderDetail.getActualdelvquantity());
				piAssignInternalAccessories_OrderChoseModel.setFactoryCode(orderDetail.getOrderinternalaccessory().getFactory().getFactorycode());
				piAssignInternalAccessories_OrderChoseModel.setOrderquantity(orderDetail.getOrderquantity());
				piAssignInternalAccessories_OrderChoseModel.setOrderSheetNo(orderDetail.getOrderinternalaccessory().getOrdersheetno());
				piAssignInternalAccessories_OrderChoseModel.setPrice(orderDetail.getPrice());
				piAssignInternalAccessories_OrderChoseModel.setAvailableQty(getAvailableQuantityOfAnOrder(orderDetail.getOrderinternalaccessory().getOrdersheetno()
						                                                                                   , orderDetail.getAccessory().getAccessorycode()
						                                                                                   , orderDetail.getActualdelvquantity()==null?orderDetail.getOrderquantity():orderDetail.getActualdelvquantity()));
				//if order is assigned in PI, load its quantity, else quantity =0;
				if(checkOrderInternalForAssignIsExisted(orderDetail.getOrderinternalaccessory().getOrdersheetno()))
				{		
					piAssignInternalAccessories_OrderChoseModel.setAssignQty(getAssignQuantityOfAnOrder(orderDetail.getOrderinternalaccessory().getOrdersheetno()
																										,lotNo
																										,accessoryCode));	
				}
				else{
					piAssignInternalAccessories_OrderChoseModel.setAssignQty((double) 0);
				}
					
				lstPiAssignInternalAccessories_OrderChoseModel.add(piAssignInternalAccessories_OrderChoseModel);		
			}		
			log.debug("getOrderInternalListForAssign successful");	
			return lstPiAssignInternalAccessories_OrderChoseModel;
		}catch(Exception e){
			log.error(String.format("getOrderInternalListForAssign in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to get available quantity of an order for assign
	 * @param orderSheetNo, accessoryCode
	 * @return availableQty
	 */
	public Double getAvailableQuantityOfAnOrder(String orderSheetNo, String accessoryCode, Double actualDelvQty){
		Double availableQty = actualDelvQty;
		List<Piassigninternalaccessoriesoforders> lstPiassigninternalaccessoriesoforders = piAssignInternalAccessoriesOfOrdersDao
				.getListPiassigninternalaccessoriesofordersByOrderSheetNoAndAccessoryCode(orderSheetNo,accessoryCode);
		for(Piassigninternalaccessoriesoforders piassigninternalaccessoriesoforders : lstPiassigninternalaccessoriesoforders)
		{
			availableQty -= piassigninternalaccessoriesoforders.getOrderassignquantity();
		}
		return availableQty;
	}
	
	/**
	 * This function is used to get assign quantity of an order which is used to assign in PI
	 * @param orderSheetNo, lotNo
	 * @return assignQty
	 */
	public Double getAssignQuantityOfAnOrder(String orderSheetNo, String lotNo, String accessoryCode){
		Double assignQty = (double) 0;
		List<Piassigninternalaccessoriesoforders> lstPiassigninternalaccessoriesoforders = piAssignInternalAccessoriesOfOrdersDao
				.getListPiassigninternalaccessoriesofordersByOrderSheetNoAndAccessoryCodeAndLotnumber(orderSheetNo,accessoryCode,lotNo);
		for(Piassigninternalaccessoriesoforders piassigninternalaccessoriesoforders : lstPiassigninternalaccessoriesoforders)
		{
			assignQty = piassigninternalaccessoriesoforders.getOrderassignquantity();
		}
		return assignQty;
	}
	
//	/**
//	 * This function is used to get sum of assign quantity of an accessory which has "order chose quantity"
//	 * @param lotNo
//	 * @return assignQty
//	 */
//	@Override
//	public Integer getSumOfAssignQuantityOfAllOrders(String lotNo, String accessoryCode){
//		Integer assignQty = 0;
//		List<Piassigninternalaccessoriesoforders> lstPiassigninternalaccessoriesoforders = piAssignInternalAccessoriesOfOrdersDao.getAll();
//		for(Piassigninternalaccessoriesoforders piassigninternalaccessoriesoforders : lstPiassigninternalaccessoriesoforders)
//		{
//			if(piassigninternalaccessoriesoforders.getPiassigninternalaccessories().getPi().getLotnumber().equals(lotNo)
//					&& piassigninternalaccessoriesoforders.getPiassigninternalaccessories().getAccessory().getAccessorycode().equals(accessoryCode))
//			{
//				assignQty += piassigninternalaccessoriesoforders.getOrderassignquantity();
//			}
//		}
//		return assignQty;
//	}
	
	/**
	 * This function is used to check a order is assigned on PI
	 * @param orderSheetNo
	 * @return true if is existed, else return false
	 */
	public Boolean checkOrderInternalForAssignIsExisted(String orderSheetNo){
		List<Piassigninternalaccessoriesoforders> lstPiassigninternalaccessoriesoforders = piAssignInternalAccessoriesOfOrdersDao
				.getListPiassigninternalaccessoriesofordersByOrderSheetNo(orderSheetNo);

		if(lstPiassigninternalaccessoriesoforders.size() >0)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * This function is used get Available Quantity which is Chose Form Order
	 * @param accessorycode, lotNo
	 * @return Available Quantity value
	 */
	@Override
	public Double getAvailableQuantityChooseFormOrder(String accessoryCode, String lotNo) {
		log.info(String.format("getAvailableQuantityChooseFormOrder in class: %s", getClass()));
		try{
			String factoryCode = piDao.findById(lotNo).getFactory().getFactorycode();
			Double TotalAssignQtyFromOrder = (double) 0;
			List<Orderinternalaccessorydetail> lstOrderinternalaccessorydetail = orderAccIntDetailDao
					.getListOrderinternalaccessorydetailByAccessoryCodeAndFactoryCode(accessoryCode,factoryCode);
			//loop lstOrderinternalaccessorydetail to find which row equal to param accessoryCode
			for(Orderinternalaccessorydetail orderDetail : lstOrderinternalaccessorydetail)
			{
				//if order is assigned in PI, load its quantity, else quantity =0;
				if(checkOrderInternalForAssignIsExisted(orderDetail.getOrderinternalaccessory().getOrdersheetno()))
				{		
					TotalAssignQtyFromOrder +=	getAssignQuantityOfAnOrder(orderDetail.getOrderinternalaccessory().getOrdersheetno()
							,lotNo
							,accessoryCode);
				}						
			}		
			log.debug("getAvailableQuantityChooseFormOrder successful");	
			return TotalAssignQtyFromOrder;
		}catch(Exception e){
			log.error(String.format("getAvailableQuantityChooseFormOrder in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used get list of all order internal accessory which is assigned for a PI (follow accessory)
	 * @param accessorycode, lotNo
	 * @return list all PiAssignInternalAccessories_OrderChoseModel in JSON format
	 */
	@Override
	public List<PiAssignInternalAccessories_OrderChoseModel> getOrderInternalAccIsAssignedForAPI(String accessoryCode, String lotNo) {
		log.info(String.format("getOrderInternalAccIsAssignedForAPI in class: %s", getClass()));
		try{
			List<Piassigninternalaccessoriesoforders> lstPiassigninternalaccessoriesoforders = piAssignInternalAccessoriesOfOrdersDao
					.getListPiassigninternalaccessoriesofordersByLotNumber(lotNo);
			PiAssignInternalAccessories_OrderChoseModel piAssignInternalAccessories_OrderChoseModel;
			List<PiAssignInternalAccessories_OrderChoseModel> lstPiAssignInternalAccessories_OrderChoseModel = new ArrayList<PiAssignInternalAccessories_OrderChoseModel>();
			
			//loop lstOrderinternalaccessorydetail to find which row equal to param accessoryCode
			for(Piassigninternalaccessoriesoforders orderDetail : lstPiassigninternalaccessoriesoforders)
			{
				//get order which has detail include accessoryCode
				OrderinternalaccessorydetailId orderinternalaccessorydetailId = new OrderinternalaccessorydetailId(accessoryCode, orderDetail.getOrderinternalaccessory().getOrdersheetno());
				
				//check order which have accessoryCode,lotNo is Existed
				//if null then return, else then continue
				if(null == orderAccIntDetailDao.findById(orderinternalaccessorydetailId))
					return lstPiAssignInternalAccessories_OrderChoseModel;
				else
				{
					Orderinternalaccessorydetail orderinternalaccessorydetail  = orderAccIntDetailDao.findById(orderinternalaccessorydetailId);			
					//check 
					if(orderinternalaccessorydetail.getAccessory().getAccessorycode().equals(accessoryCode))
					{
						piAssignInternalAccessories_OrderChoseModel = new PiAssignInternalAccessories_OrderChoseModel();
						piAssignInternalAccessories_OrderChoseModel.setOrderSheetNo(orderDetail.getOrderinternalaccessory().getOrdersheetno());
						piAssignInternalAccessories_OrderChoseModel.setAssignQty(orderDetail.getOrderassignquantity());			
						lstPiAssignInternalAccessories_OrderChoseModel.add(piAssignInternalAccessories_OrderChoseModel);
					}	
				}
			}		
			log.debug("getOrderInternalAccIsAssignedForAPI successful");	
			return lstPiAssignInternalAccessories_OrderChoseModel;
		}catch(Exception e){
			log.error(String.format("getOrderInternalAccIsAssignedForAPI in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}

}
