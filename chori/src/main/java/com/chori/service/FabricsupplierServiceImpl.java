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
import com.chori.dao.FabricsupplierDao;
import com.chori.dao.FabricsuppliercontactDao;
import com.chori.dao.UserDao;
import com.chori.entity.Fabricsupplier;
import com.chori.entity.Fabricsuppliercontact;
import com.chori.model.FabricsupplierModel;
import com.chori.model.FabricsuppliercontactModel;

@Service("fabricsupplierService")
public class FabricsupplierServiceImpl extends
		AbstractServiceImpl<Fabricsupplier, String> implements
		FabricsupplierService {
	private FabricsupplierDao dao;

	@Autowired
	UserDao userDao;

	@Autowired
	FabricsuppliercontactDao fabricsuppliercontactDao;

	// public FabricsupplierServiceImpl(){
	//
	// }

	@Autowired
	public FabricsupplierServiceImpl(
			@Qualifier("fabricsupplierDao") AbstractDao<Fabricsupplier, String> abstractDao) {
		super(abstractDao);
		this.dao = (FabricsupplierDao) abstractDao;
	}

	/**
	 * This function is used to get all FabricsupplierModel
	 */
	@Override
	public List<FabricsupplierModel> getAllFabricsupplierModel() {
		log.info(String.format("getAllFabricsupplierModel in class: %s",
				getClass()));
		try {
			log.debug("get all Fabricsupplier in DB after that return a list FabricsupplierModel");
			List<Fabricsupplier> lstFabricsupplier = dao.getAll();

			FabricsupplierModel en;
			List<FabricsupplierModel> lst = new ArrayList<FabricsupplierModel>();

			FabricsuppliercontactModel fabricContactEn;

			for (Fabricsupplier fabricSup : lstFabricsupplier) {

				en = new FabricsupplierModel();
				en.setFabricsupcode(fabricSup.getFabricsupcode());
				en.setShortname(fabricSup.getShortname());
				en.setLongname(fabricSup.getLongname());
				en.setAddress(fabricSup.getAddress());
				en.setTel(fabricSup.getTel());
				en.setFax(fabricSup.getFax());
				en.setTaxno(fabricSup.getTaxno());
				en.setCreatedate(fabricSup.getCreatedate());
				en.setStatus(fabricSup.getStatus());
				en.setCreator(fabricSup.getUser() == null ? "" : fabricSup
						.getUser().getUsername());

				// láº¥y ra faccontact cá»§a factory
				Set<Fabricsuppliercontact> lstFabricContact = fabricSup
						.getFabricsuppliercontacts();
				for (Fabricsuppliercontact fabricsuppliercontact : lstFabricContact) {
					fabricContactEn = new FabricsuppliercontactModel();
					fabricContactEn.setFabricsupplierCode(fabricsuppliercontact
							.getFabricsupplier().getFabricsupcode());
					fabricContactEn
							.setFabricsuppliercontactcode(fabricsuppliercontact
									.getFabricsuppliercontactcode());
					fabricContactEn.setName(fabricsuppliercontact.getName());
					fabricContactEn.setEmail(fabricsuppliercontact.getEmail());
					fabricContactEn
							.setCreator(fabricsuppliercontact.getUser() == null ? ""
									: fabricsuppliercontact.getUser()
											.getUsername());
					fabricContactEn.setCreatedate(fabricsuppliercontact
							.getCreatedate());
					fabricContactEn.setTel(fabricsuppliercontact.getTel()==null?"":fabricsuppliercontact.getTel());

					en.getFabricsuppliercontactModelList().add(fabricContactEn);
				}

				lst.add(en);
			}
			log.debug("getAllFabricsupplierModel successfully");
			return lst;
		} catch (Exception e) {
			log.error(String.format(
					"getAllFabricsupplierModel in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new Fabric Supplier
	 */
	public boolean addNewFabricSupplier(FabricsupplierModel fm, String userName) {
		log.info(String
				.format("addNewFabricSupplier with params 'fm', 'userName' in class %s",
						getClass()));
		try {
			Fabricsupplier fabricsupplier = new Fabricsupplier();

			fabricsupplier.setFabricsupcode(fm.getFabricsupcode());
			fabricsupplier.setUser(userDao.findById(userName));
			fabricsupplier.setShortname(fm.getShortname());
			fabricsupplier.setLongname(fm.getLongname());
			fabricsupplier.setAddress(fm.getAddress());
			fabricsupplier.setTel(fm.getTel());
			fabricsupplier.setFax(fm.getFax());
			fabricsupplier.setTaxno(fm.getTaxno());
			fabricsupplier.setStatus(fm.getStatus());
			fabricsupplier.setCreatedate(new Date());
			fabricsupplier.setRemark(fm.getRemark());

			dao.save(fabricsupplier);
			System.err.println("Save fabric supplier thÃ nh cÃ´ng");

			Fabricsuppliercontact fabricsupplierContact;

			Set<FabricsuppliercontactModel> newList = fm
					.getFabricsuppliercontactModelList();
			for (FabricsuppliercontactModel fabricsuppliercontactModel : newList) {
				fabricsupplierContact = new Fabricsuppliercontact();
				fabricsupplierContact.setFabricsupplier(dao
						.findById(fabricsupplier.getFabricsupcode()));
				fabricsupplierContact.setUser(userDao.findById(userName));
				fabricsupplierContact.setName(fabricsuppliercontactModel
						.getName());
				fabricsupplierContact.setEmail(fabricsuppliercontactModel
						.getEmail());
				fabricsupplierContact.setCreatedate(new Date());

				fabricsuppliercontactDao.save(fabricsupplierContact);
			}
			System.err.println("Save cÃ¡c Fabricsuppliercontact thÃ nh cÃ´ng");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("addNewFabricSupplier with params 'fm', 'userName' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to find Fabric supplier Model By Id
	 * 
	 * @param id
	 * @return
	 */
	public FabricsupplierModel findFabricsupplierModelById(String id) {
		log.info(String.format(
				"findFabricsupplierModelById with params 'id' in class %s",
				getClass()));
		try {
			Fabricsupplier fs = dao.findById(id);

			FabricsupplierModel fsm = new FabricsupplierModel();
			fsm.setFabricsupcode(fs.getFabricsupcode());
			fsm.setCreator(fs.getUser() == null ? "" : fs.getUser()
					.getUsername());
			fsm.setShortname(fs.getShortname());
			fsm.setLongname(fs.getLongname());
			fsm.setAddress(fs.getAddress());
			fsm.setTel(fs.getTel());
			fsm.setFax(fs.getFax());
			fsm.setTaxno(fs.getTaxno());
			fsm.setStatus(fs.getStatus());
			fsm.setRemark(fs.getRemark());

			FabricsuppliercontactModel fscm;
			Set<Fabricsuppliercontact> lst = fs.getFabricsuppliercontacts();
			for (Fabricsuppliercontact fabricsuppliercontact : lst) {
				fscm = new FabricsuppliercontactModel();
				fscm.setFabricsupplierCode(fabricsuppliercontact
						.getFabricsupplier().getFabricsupcode());
				fscm.setFabricsuppliercontactcode(fabricsuppliercontact
						.getFabricsuppliercontactcode());
				fscm.setCreator(fabricsuppliercontact.getUser() == null ? ""
						: fabricsuppliercontact.getUser().getUsername());
				fscm.setName(fabricsuppliercontact.getName());
				fscm.setEmail(fabricsuppliercontact.getEmail());
				fscm.setCreatedate(fabricsuppliercontact.getCreatedate());
				fscm.setTel(fabricsuppliercontact.getTel()==null?"":fabricsuppliercontact.getTel());

				fsm.getFabricsuppliercontactModelList().add(fscm);
			}

			return fsm;
		} catch (Exception e) {
			log.error(String
					.format("findFabricsupplierModelById with params 'id' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * Function is used to edit 1 fabric supplier
	 * 
	 * @param fsm
	 * @param userName
	 * @return
	 */
	public boolean editFabricSupplier(FabricsupplierModel fsm, String userName) {
		log.info(String.format(
				"editFabricSupplier with params 'fsm', 'userName' in class %s",
				getClass()));
		try {
			Fabricsupplier fs = dao.findById(fsm.getFabricsupcode());

			fs.setShortname(fsm.getShortname());
			fs.setLongname(fsm.getLongname());
			fs.setAddress(fsm.getAddress());
			fs.setTel(fsm.getTel());
			fs.setFax(fsm.getFax());
			fs.setTaxno(fsm.getTaxno());
			fs.setStatus(fsm.getStatus());
			fs.setRemark(fsm.getRemark());

			dao.update(fs);
			System.err.println("Edit fabric supplier thÃ nh cÃ´ng");

			Set<FabricsuppliercontactModel> newList = fsm
					.getFabricsuppliercontactModelList();
			Set<Fabricsuppliercontact> oldList = fs.getFabricsuppliercontacts();

			// trÆ°á»�ng há»£p list cÅ© ==0
			if (oldList.size() == 0) {
				if (newList.size() == 0) {
					return true;// 2 list rá»—ng nhÆ° nhau thÃ¬ ko lÃ m gÃ¬
				} else {// náº¿u list má»›i cÃ³ contact, thÃ¬ add háº¿t list
						// má»›i Ä‘Ã³ vÃ o
					Fabricsuppliercontact fabricSupplierContact;

					for (FabricsuppliercontactModel fabricsuppliercontactModel : newList) {
						fabricSupplierContact = new Fabricsuppliercontact();
						fabricSupplierContact.setFabricsupplier(dao
								.findById(fsm.getFabricsupcode()));
						fabricSupplierContact.setUser(userDao
								.findById(userName));
						fabricSupplierContact
								.setName(fabricsuppliercontactModel.getName());
						fabricSupplierContact
								.setEmail(fabricsuppliercontactModel.getEmail());
						fabricSupplierContact.setCreatedate(new Date());// this
																		// contact
																		// is
																		// created
																		// when
																		// edit
						fabricSupplierContact.setTel(fabricsuppliercontactModel
								.getTel());

						fabricsuppliercontactDao.save(fabricSupplierContact);
					}
					return true;
				}
			} else {// TrÆ°á»�ng há»£p list cÅ© ko rá»—ng
				if (newList.size() == 0) {// vÃ  list má»›i rá»—ng, thÃ¬ del
											// háº¿t contact
					for (Fabricsuppliercontact fabricsuppliercontact : oldList) {
						fabricsuppliercontactDao
								.delete(fabricsuppliercontactDao.findById(fabricsuppliercontact
										.getFabricsuppliercontactcode()));
					}
					return true;
				} else {// list má»›i khÃ´ng rá»—ng

					// lá»�c qua list má»›i, factorycontactcode ==0 thÃ¬ thÃªm
					// má»›i
					Fabricsuppliercontact fabricSupplierContact;

					for (FabricsuppliercontactModel fabricSupplierContactModel : newList) {
						if (fabricSupplierContactModel
								.getFabricsuppliercontactcode() == 0) {
							fabricSupplierContact = new Fabricsuppliercontact();
							fabricSupplierContact.setFabricsupplier(dao
									.findById(fsm.getFabricsupcode()));
							fabricSupplierContact.setUser(userDao
									.findById(userName));
							fabricSupplierContact
									.setName(fabricSupplierContactModel
											.getName());
							fabricSupplierContact
									.setEmail(fabricSupplierContactModel
											.getEmail());
							fabricSupplierContact.setCreatedate(new Date());
							fabricSupplierContact
									.setTel(fabricSupplierContactModel.getTel());

							fabricsuppliercontactDao
									.save(fabricSupplierContact);
						}
					}

					// true lÃ  tá»“n táº¡i cáº£ 2 list, false lÃ  ko tá»“n
					// táº¡i trong list má»›i
					boolean flag = false;

					// Táº¡o list Factorycontact Ä‘á»ƒ remove
					List<Fabricsuppliercontact> lstToBeRemove = new ArrayList<Fabricsuppliercontact>();
					List<FabricsuppliercontactModel> lstToBeUpdate = new ArrayList<FabricsuppliercontactModel>();

					for (FabricsuppliercontactModel fabricSupplierContactModel : newList) {
						for (Fabricsuppliercontact facbricSupplierContact : oldList) {
							// náº¿u contact trong list cÅ© ko tá»“n táº¡i trong
							// list má»›i, náº¿u contact code mÃ  = nhau thÃ¬
							// lÃ  cÃ³ tá»‘n táº¡i, gÃ¡n flag = true
							if (facbricSupplierContact
									.getFabricsuppliercontactcode() == fabricSupplierContactModel
									.getFabricsuppliercontactcode()) {
								flag = true;
								break;
							}
						}
						if (flag == true) {
							lstToBeUpdate.add(fabricSupplierContactModel);
							flag = false;// set láº¡i thÃ nh ko tá»“n táº¡i
						}
					}

					// Lá»�c ra nhá»¯ng contact ko tá»“n táº¡i trong list má»›i
					flag = false;// máº·c Ä‘á»‹nh lÃ  ko tá»“n táº¡i
					for (Fabricsuppliercontact facbricSupplierContact : oldList) {
						for (FabricsuppliercontactModel fabricSupplierContactModel : newList) {
							if (facbricSupplierContact
									.getFabricsuppliercontactcode() == fabricSupplierContactModel
									.getFabricsuppliercontactcode()) {
								flag = true;
								break;
							}
						}

						if (flag == false) {
							lstToBeRemove.add(facbricSupplierContact);
						}
						flag = false;
					}

					// láº·p qua list cáº§n remove
					for (Fabricsuppliercontact facbricSupplierContact : lstToBeRemove) {
						fabricsuppliercontactDao
								.delete(fabricsuppliercontactDao.findById(facbricSupplierContact
										.getFabricsuppliercontactcode()));
					}

					Fabricsuppliercontact fscNeedUpdate;
					// láº·p qua list cáº§n update
					for (FabricsuppliercontactModel fabricSupplierContactModel : lstToBeUpdate) {
						fscNeedUpdate = fabricsuppliercontactDao
								.findById(fabricSupplierContactModel
										.getFabricsuppliercontactcode());

						fscNeedUpdate.setName(fabricSupplierContactModel
								.getName());
						fscNeedUpdate.setEmail(fabricSupplierContactModel
								.getEmail());
						fscNeedUpdate.setTel(fabricSupplierContactModel
								.getTel());

						fabricsuppliercontactDao.update(fscNeedUpdate);
					}

					return true;
				}
			}
		} catch (Exception e) {
			log.error(String
					.format("editFabricSupplier with params 'fsm', 'userName' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete a Fabric Supplier
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(String id) {
		log.info(String.format("delete with param 'id' in class: %s",
				getClass()));
		try {
			Fabricsupplier fs = dao.findById(id);

			Set<Fabricsuppliercontact> lst = fs.getFabricsuppliercontacts();
			for (Fabricsuppliercontact fabricSupplierContact : lst) {
				fabricsuppliercontactDao.delete(fabricsuppliercontactDao
						.findById(fabricSupplierContact
								.getFabricsuppliercontactcode()));
			}

			dao.delete(fs);

			return true;
		} catch (Exception e) {
			log.error(String.format(
					"delete with param 'id' in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a fabric supplier is existed in db
	 * 
	 * @param id
	 * @return true if existed.
	 */
	public boolean isFabricSupplierExistedById(String id) {
		log.info(String.format(
				"isFabricSupplierExistedById with param 'id' in class: %s",
				getClass()));
		try {
			if (null == dao.findById(id))
				return false;
			return true;
		} catch (Exception e) {
			log.error(String
					.format("isFabricSupplierExistedById with param 'id' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
