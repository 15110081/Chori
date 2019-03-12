//package com.chori.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Repository;
//
//import com.chori.AbstractDao;
//import com.chori.AbstractServiceImpl;
//import com.chori.dao.FabricinformationdetailDao;
//import com.chori.dao.PIAssignFabricDetailDao;
//import com.chori.dao.PIDao;
//import com.chori.dao.UserDao;
//import com.chori.entity.Fabricinformationdetail;
//import com.chori.entity.FabricinformationdetailId;
//import com.chori.entity.Piassignfabricdetail;
//import com.chori.model.FabricinformationdetailModel;
//
//@Repository("fabricinformationdetailService")
//public class FabricinformationdetailServiceImpl extends
//		AbstractServiceImpl<Fabricinformationdetail, FabricinformationdetailId>
//		implements FabricinformationdetailService {
//	private FabricinformationdetailDao dao;
//
//	@Autowired
//	private PIDao piDao;
//
//	@Autowired
//	private UserDao userDao;
//
//	@Autowired
//	private PIAssignFabricDetailDao piassignfabricdetailDao;
//
//	@Autowired
//	public FabricinformationdetailServiceImpl(
//			@Qualifier("fabricinformationdetailDao") AbstractDao<Fabricinformationdetail, FabricinformationdetailId> abstractDao) {
//		super(abstractDao);
//		this.dao = (FabricinformationdetailDao) abstractDao;
//	}
//
//	public List<FabricinformationdetailModel> getAllFabricinformationdetailModel() {
//		log.info(String.format("getAllBrand in class: %s", getClass()));
//		try {
//			log.debug("get all Role in DB after that return a list RoleEntity");
//			List<Fabricinformationdetail> listFabricinformationdetail = dao.getAll();
//
//			FabricinformationdetailModel fabricinformationdetailModel;
//			List<FabricinformationdetailModel> lst = new ArrayList<FabricinformationdetailModel>();
//
//			for (Fabricinformationdetail informationDetail : listFabricinformationdetail) {
//				fabricinformationdetailModel = new FabricinformationdetailModel();
//				fabricinformationdetailModel.setColorcode(informationDetail.getColor().getColorcode());
//				fabricinformationdetailModel.setFabricno(informationDetail.getFabricinformation().getFabricno());
//				fabricinformationdetailModel.setYardinbl(informationDetail.getYardinbl());
//				fabricinformationdetailModel.setUnitprice(informationDetail.getUnitprice());
//
//				lst.add(fabricinformationdetailModel);
//			}
//			log.debug("getAllBrand successfully");
//			return lst;
//		} catch (Exception e) {
//			log.error(String.format("getAllBrand in class: %s has error: %s",
//					getClass(), e.getMessage()));
//			throw e;
//		}
//	}
//	
//	/**
//	 * This function is used to get YardInBL of a fabric + color
//	 * 
//	 * @param FabricinformationdetailId
//	 * @return YardInBL
//	 */
//	public Double getYardInBL(
//			FabricinformationdetailId fabricinformationdetailId) {
//		try {
//			log.debug("getYardInBL successfully");
//			return dao.findById(fabricinformationdetailId).getYardinbl();
//		} catch (Exception e) {
//			log.error(String.format("getYardInBL in class: %s has error: %s",
//					getClass(), e.getMessage()));
//			throw e;
//		}
//	}
//
//	/**
//	 * This function is used to get InventoryRemained of a fabric + color
//	 * 
//	 * @param FabricinformationdetailId
//	 * @return YardInBL
//	 */
//	public Double getInventoryRemained(
//			FabricinformationdetailId fabricinformationdetailId) {
//		try {
//
//			Fabricinformationdetail fabricinformationdetail = dao
//					.findById(fabricinformationdetailId);
//			Double totalYardInBL = dao.findById(fabricinformationdetailId)
//					.getYardinbl();
//			List<Piassignfabricdetail> lstPiassignfabricdetail = piassignfabricdetailDao
//					.getAll();
//			Double inventoryRemained = totalYardInBL;
//
//			// find shipping status of a PI
//
//			for (Piassignfabricdetail tmp : lstPiassignfabricdetail) {
//				System.err.println(tmp.getColor().getColorcode());
//				// if colorcode, fabricno of an assignment = colorcode, fabricno
//				// of a fabric
//				// and shippingstatus of a pi = YES, then sub quantity
//				if (tmp.getColor()
//						.getColorcode()
//						.equals(fabricinformationdetail.getColor()
//								.getColorcode())
//						&& tmp.getPiassignfabric()
//								.getFabricinformation()
//								.getFabricno()
//								.equals(fabricinformationdetail
//										.getFabricinformation().getFabricno())
//						&& piDao.findById(
//								tmp.getPiassignfabric().getPi().getLotnumber())
//								.getShippingstatus().equals("Yes")) {
//					inventoryRemained -= tmp.getAssignquantity();
//				}
//			}
//			log.debug("getInventoryRemained successfully");
//			return inventoryRemained;
//		} catch (Exception e) {
//			log.error(String.format(
//					"getInventoryRemained in class: %s has error: %s",
//					getClass(), e.getMessage()));
//			throw e;
//		}
//	}
//
//	/**
//	 * This function is used to get AvailableQty of a fabric + color
//	 * 
//	 * @param FabricinformationdetailId
//	 * @return YardInBL
//	 */
//	public Double getAvailableQty(
//			FabricinformationdetailId fabricinformationdetailId) {
//		try {
//
//			Fabricinformationdetail fabricinformationdetail = dao
//					.findById(fabricinformationdetailId);
//			Double totalYardInBL = dao.findById(fabricinformationdetailId)
//					.getYardinbl();
//			List<Piassignfabricdetail> lstPiassignfabricdetail = piassignfabricdetailDao
//					.getAll();
//			Double availableQty = totalYardInBL;
//
//			for (Piassignfabricdetail tmp : lstPiassignfabricdetail) {
//				System.err.println(tmp.getColor().getColorcode());
//
//				if (tmp.getColor()
//						.getColorcode()
//						.equals(fabricinformationdetail.getColor()
//								.getColorcode())
//						&& tmp.getPiassignfabric()
//								.getFabricinformation()
//								.getFabricno()
//								.equals(fabricinformationdetail
//										.getFabricinformation().getFabricno())) {
//					availableQty -= tmp.getAssignquantity();
//				}
//			}
//			log.debug("getAvailableQty successfully");
//			return availableQty;
//		} catch (Exception e) {
//			log.error(String.format(
//					"getAvailableQty in class: %s has error: %s", getClass(),
//					e.getMessage()));
//			throw e;
//		}
//	}	
//}
