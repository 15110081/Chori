package com.chori.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.CustomerDao;
import com.chori.dao.FabricAssignmentChecklistDao;
import com.chori.dao.FabricinformationDao;
import com.chori.dao.FabricsupplierDao;
import com.chori.dao.FactoryDao;
import com.chori.dao.PIAssignFabricDetailDao;
import com.chori.dao.UserDao;
import com.chori.entity.Fabricinformation;
import com.chori.model.FabricinformationModel;

@Repository("fabricAssignmentChecklistService")
public class FabricAssignmentChecklistServiceImpl extends
		AbstractServiceImpl<Fabricinformation, String> implements
		FabricAssignmentChecklistService {

	private FabricAssignmentChecklistDao dao;

	@Autowired
	PIAssignFabricDetailDao piafdDao;

	@Autowired
	UserDao userDao;

	@Autowired
	CustomerDao cusDao;

	@Autowired
	FactoryDao facDao;

	@Autowired
	FabricsupplierDao fsupDao;
	
	@Autowired
	FabricinformationDao fabricinformationDao;

	public FabricAssignmentChecklistServiceImpl() {
	}

	@Autowired
	public FabricAssignmentChecklistServiceImpl(
			@Qualifier("fabricAssignmentChecklistDao") AbstractDao<Fabricinformation, String> abstractDao) {
		super(abstractDao);
		this.dao = (FabricAssignmentChecklistDao) abstractDao;
	}

	public List<FabricinformationModel> getAllFabricinformationModelByCustomerandFactory(String customerCode, String factoryCode) {
		log.info(String.format("getAllFabricinformationModel in class: %s",
				getClass()));
		try {
			log.debug("get all Fabric checklist in DB after that return a list FabricAssignChecklist");
			List<Fabricinformation> lstFabricAssignment = fabricinformationDao
					.getAllFabricInformationByCustomerCodeFactoryCode(customerCode, factoryCode);

			FabricinformationModel fim;
			List<FabricinformationModel> lst = new ArrayList<FabricinformationModel>();

			for (Fabricinformation fi : lstFabricAssignment) {
			
					fim = new FabricinformationModel();
					fim.setFabricno(fi.getFabricno());
					fim.setFabricitem(fi.getFabricitem());
					fim.setCustomer(fi.getCustomer().getCustomercode());
					fim.setFabricsupplier(fi.getFabricsupplier().getFabricsupcode());
					fim.setFactory(fi.getFactory().getFactorycode());			
					fim.setIschori(fi.getIschori());
					fim.setFabricinvoiceno(fi.getFabricinvoiceno());
					fim.setCreator(fi.getUser() == null ? " " : fi.getUser()
							.getUsername());
					fim.setCreatedate(fi.getCreatedate());
//					fim.setFabricinvoiceno(fi.getFabricinvoiceno());
					lst.add(fim);
			
			}
			log.debug("getAllFabricAssignment successfully");
			
			List<Fabricinformation> lstFabricByCustomerUnknown = fabricinformationDao.getListFabricInformationByCustomerCode("Unknown");
			for (Fabricinformation fi : lstFabricByCustomerUnknown) {
				fim = new FabricinformationModel();
				fim.setFabricno(fi.getFabricno());
				fim.setFabricitem(fi.getFabricitem());
				fim.setCustomer(fi.getCustomer().getCustomercode());
				fim.setFabricsupplier(fi.getFabricsupplier().getFabricsupcode());
				fim.setFactory(fi.getFactory().getFactorycode());			
				fim.setIschori(fi.getIschori());
				fim.setFabricinvoiceno(fi.getFabricinvoiceno());
				fim.setCreator(fi.getUser() == null ? " " : fi.getUser()
						.getUsername());
				fim.setCreatedate(fi.getCreatedate());
//				fim.setFabricinvoiceno(fi.getFabricinvoiceno());
				lst.add(fim);
			}
			
			return lst;
		} catch (Exception e) {
			log.error(String.format(
					"getAllFabricAssignment in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

//	@Override
//	public FabricinformationModel findFabricinformationModelByFabricNo(
//			String fabricno) {
//		Fabricinformation fabricinfo = dao.findById(fabricno);
//		FabricinformationModel fabricinfomodel = new FabricinformationModel();
//
//		fabricinfomodel.setFabricno(fabricinfo.getFabricno());
//		fabricinfomodel.setCreator(fabricinfo.getUser() == null ? " "
//				: fabricinfo.getUser().getUsername());
//		fabricinfomodel.setCreatedate(fabricinfo.getCreatedate());
//
//		return fabricinfomodel;
//	}

}
