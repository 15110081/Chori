package com.chori.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.FactoryDao;
import com.chori.dao.FactoryaccountinformationDao;
import com.chori.dao.FactorycontactDao;
import com.chori.dao.UserDao;
import com.chori.entity.Factory;
import com.chori.entity.Factoryaccountinformation;
import com.chori.entity.Factorycontact;
import com.chori.model.FactoryModel;
import com.chori.model.FactoryaccountinformationModel;
import com.chori.model.FactorycontactModel;

@Repository("factoryService")
public class FactoryServiceImpl extends AbstractServiceImpl<Factory, String>
		implements FactoryService {

	private FactoryDao dao;

	@Autowired
	UserDao userDao;

	@Autowired
	FactorycontactDao factoryContactDao;

	@Autowired
	FactoryaccountinformationDao factoryaccountinformationDao;

	@Autowired
	public FactoryServiceImpl(
			@Qualifier("factoryDao") AbstractDao<Factory, String> abstractDao) {
		super(abstractDao);
		this.dao = (FactoryDao) abstractDao;
	}

	/**
	 * This function is used to get a list of all roles in database
	 * 
	 * @return List<RoleEntity>
	 */
	public List<FactoryModel> getAllFactoryModel() {
		log.info(String.format("getAllFactoryModel in class: %s", getClass()));
		try {
			log.debug("get all factory in DB after that return a list Factory model");
			List<Factory> lstFactory = dao.getAll();

			FactoryModel en;
			List<FactoryModel> lst = new ArrayList<FactoryModel>();

			FactorycontactModel facContactEn;

			for (Factory fac : lstFactory) {

				en = new FactoryModel();
				en.setFactorycode(fac.getFactorycode());
				en.setShortname(fac.getShortname());
				en.setLongname(fac.getLongname());
				en.setAddress(fac.getAddress());
				en.setTel(fac.getTel());
				en.setFax(fac.getFax());
				en.setTaxno(fac.getTaxno());
				en.setCreatedate(fac.getCreatedate());
				en.setStatus(fac.getStatus());
				en.setCreator(fac.getUser() == null ? "" : fac.getUser()
						.getUsername());

				// láº¥y ra faccontact cá»§a factory
				Set<Factorycontact> lstFacContact = fac.getFactorycontacts();
				for (Factorycontact factorycontact : lstFacContact) {
					facContactEn = new FactorycontactModel();
					facContactEn.setFactoryCode(factorycontact.getFactory()
							.getFactorycode());
					facContactEn.setFactorycontactcode(factorycontact
							.getFactorycontactcode());
					facContactEn.setName(factorycontact.getName());
					facContactEn.setEmail(factorycontact.getEmail());
					facContactEn.setTel(factorycontact.getTel());
					facContactEn
							.setCreator(factorycontact.getUser() == null ? ""
									: factorycontact.getUser().getUsername());
					facContactEn.setCreatedate(factorycontact.getCreatedate());

					en.getFactorycontactModellist().add(facContactEn);
				}

				lst.add(en);
			}
			log.debug("getAllFactoryModel successfully");
			return lst;
		} catch (Exception e) {
			log.error(String.format(
					"getAllFactoryModel in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new Factory
	 * 
	 * @param fm
	 * @param userName
	 * @return
	 */
	@Override
	public boolean addNewFactory(FactoryModel fm, String userName) {
		log.info(String.format(
				"addNewFactory with params 'fm', 'userName' in class: %s",
				getClass()));
		try {
			Factory fac = new Factory();
			fac.setFactorycode(fm.getFactorycode());
			fac.setUser(userDao.findById(userName));
			fac.setShortname(fm.getShortname());
			fac.setLongname(fm.getLongname());
			fac.setAddress(fm.getAddress());
			fac.setTel(fm.getTel());
			fac.setFax(fm.getFax());
			fac.setTaxno(fm.getTaxno());
			fac.setStatus(fm.getStatus());
			fac.setCreatedate(new Date());
			fac.setRemark(fm.getRemark());

			// save factory vÃ o csdl trc sau Ä‘Ã³ máº¥y contact má»›i cÃ³ id
			dao.save(fac);
			System.err.println("Save factory thÃ nh cÃ´ng");

			// Táº¡o biáº¿n Ä‘á»ƒ lÆ°u tá»«ng Factorycontact tá»« param
			Factorycontact facContact;

			Set<FactorycontactModel> newList = fm.getFactorycontactModellist();
			for (FactorycontactModel factorycontactModel : newList) {
				facContact = new Factorycontact();
				facContact.setFactory(dao.findById(fm.getFactorycode()));
				facContact.setUser(userDao.findById(userName));
				facContact.setName(factorycontactModel.getName());
				facContact.setEmail(factorycontactModel.getEmail());
				facContact.setTel(factorycontactModel.getTel());
				facContact.setCreatedate(new Date());

				factoryContactDao.save(facContact);
			}

			// save các fac account info
			Factoryaccountinformation factoryaccountinformation;

			Set<FactoryaccountinformationModel> lstFactoryaccountinformation = fm
					.getFactoryaccountinformationModelList();
			for (FactoryaccountinformationModel factoryaccountinformationModel : lstFactoryaccountinformation) {
				factoryaccountinformation = new Factoryaccountinformation();
				factoryaccountinformation.setFactory(dao.findById(fm
						.getFactorycode()));
				factoryaccountinformation.setUser(userDao.findById(userName));
				factoryaccountinformation
						.setBankname(factoryaccountinformationModel
								.getBankname());
				factoryaccountinformation
						.setBankbranch(factoryaccountinformationModel
								.getBankbranch());
				factoryaccountinformation
						.setAccountnumber(factoryaccountinformationModel
								.getAccountnumber());
				factoryaccountinformation
						.setAddress(factoryaccountinformationModel.getAddress());
				factoryaccountinformation
						.setSwiftcode(factoryaccountinformationModel
								.getSwiftcode());
				factoryaccountinformation.setCreatedate(new Date());

				factoryaccountinformationDao.save(factoryaccountinformation);
			}

			System.err.println("Save cÃ¡c factoryContact thÃ nh cÃ´ng");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("addNewFactory with params 'fm', 'userName' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * Function get Factory Model by id
	 * 
	 * @param id
	 * @return
	 */
	public FactoryModel findFactoryModelById(String id) {
		log.info(String.format(
				"findFactoryModelById with params 'id' in class: %s",
				getClass()));
		try {
			Factory fac = dao.findById(id);
			FactoryModel fm = new FactoryModel();

			fm.setFactorycode(fac.getFactorycode());
			fm.setCreator(fac.getUser() == null ? "" : fac.getUser()
					.getUsername());
			fm.setShortname(fac.getShortname());
			fm.setLongname(fac.getLongname());
			fm.setAddress(fac.getAddress());
			fm.setTel(fac.getTel());
			fm.setFax(fac.getFax());
			fm.setTaxno(fac.getTaxno());
			fm.setStatus(fac.getStatus());
			fm.setCreatedate(fac.getCreatedate());
			fm.setRemark(fac.getRemark());

			FactorycontactModel fcm;

			Set<Factorycontact> lst = fac.getFactorycontacts();
			for (Factorycontact factorycontact : lst) {
				fcm = new FactorycontactModel();
				fcm.setFactorycontactcode(factorycontact
						.getFactorycontactcode());
				fcm.setFactoryCode(factorycontact.getFactory().getFactorycode());
				fcm.setCreator(factorycontact.getUser() == null ? ""
						: factorycontact.getUser().getUsername());
				fcm.setName(factorycontact.getName());
				fcm.setEmail(factorycontact.getEmail());
				fcm.setTel(factorycontact.getTel());
				fcm.setCreatedate(factorycontact.getCreatedate());

				fm.getFactorycontactModellist().add(fcm);
			}

			FactoryaccountinformationModel factoryaccountinformationModel;

			Set<Factoryaccountinformation> lstFAI = fac
					.getFactoryaccountinformations();
			for (Factoryaccountinformation factoryaccountinformation : lstFAI) {
				factoryaccountinformationModel = new FactoryaccountinformationModel();
				factoryaccountinformationModel
						.setFactoryaccountinfocode(factoryaccountinformation
								.getFactoryaccountinfocode());
				factoryaccountinformationModel
						.setFactoryCode(factoryaccountinformation.getFactory()
								.getFactorycode());
				factoryaccountinformationModel
						.setBankname(factoryaccountinformation.getBankname());
				factoryaccountinformationModel
						.setBankbranch(factoryaccountinformation
								.getBankbranch());
				factoryaccountinformationModel
						.setAccountnumber(factoryaccountinformation
								.getAccountnumber());
				factoryaccountinformationModel
						.setAddress(factoryaccountinformation.getAddress());
				factoryaccountinformationModel
						.setSwiftcode(factoryaccountinformation.getSwiftcode());

				fm.getFactoryaccountinformationModelList().add(
						factoryaccountinformationModel);
			}

			return fm;
		} catch (Exception e) {
			log.error(String
					.format("findFactoryModelById with params 'id' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit 1 factory
	 */
	public boolean editFactory(FactoryModel fm, String userName) {
		log.info(String.format(
				"editFactory with params 'fm', 'userName' in class %s",
				getClass()));
		try {
			Factory fac = dao.findById(fm.getFactorycode());
			fac.setShortname(fm.getShortname());
			fac.setLongname(fm.getLongname());
			fac.setAddress(fm.getAddress());
			fac.setTel(fm.getTel());
			fac.setFax(fm.getFax());
			fac.setTaxno(fm.getTaxno());
			fac.setStatus(fm.getStatus());
			fac.setRemark(fm.getRemark());

			dao.update(fac);
			System.err.println("Edit factory thÃ nh cÃ´ng");

			Set<FactorycontactModel> newList = fm.getFactorycontactModellist();
			Set<Factorycontact> oldList = fac.getFactorycontacts();

			Set<FactoryaccountinformationModel> newListFAI = fm
					.getFactoryaccountinformationModelList();
			Set<Factoryaccountinformation> oldListFAI = fac
					.getFactoryaccountinformations();

			boolean flag1 = false;
			boolean flag2 = false;

			// case oldList.size() ==0
			if (oldList.size() == 0) {
				if (newList.size() == 0) {
					// return true;// 2 list rá»—ng nhÆ° nhau thÃ¬ ko lÃ m gÃ¬
					flag1 = true;
				} else {// náº¿u list má»›i cÃ³ contact, thÃ¬ add háº¿t list
						// má»›i Ä‘Ã³ vÃ o
					Factorycontact facContact;

					for (FactorycontactModel factorycontactModel : newList) {
						facContact = new Factorycontact();
						facContact
								.setFactory(dao.findById(fm.getFactorycode()));
						facContact.setUser(userDao.findById(userName));
						facContact.setName(factorycontactModel.getName());
						facContact.setEmail(factorycontactModel.getEmail());
						facContact.setTel(factorycontactModel.getTel());
						facContact.setCreatedate(new Date());

						factoryContactDao.save(facContact);
					}
					// return true;
					flag1 = true;
				}
			} else {// TrÆ°á»�ng há»£p list cÅ© ko rá»—ng
				if (newList.size() == 0) {// vÃ  list má»›i rá»—ng, thÃ¬ del
											// háº¿t
											// contact
					for (Factorycontact factorycontact : oldList) {
						factoryContactDao.delete(factoryContactDao
								.findById(factorycontact
										.getFactorycontactcode()));
					}
					// return true;
					flag1 = true;
				} else {// list má»›i khÃ´ng rá»—ng

					// lá»�c qua list má»›i, factorycontactcode ==0 thÃ¬ thÃªm
					// má»›i
					Factorycontact facContact;

					for (FactorycontactModel factorycontactModel : newList) {
						if (factorycontactModel.getFactorycontactcode() == 0) {
							facContact = new Factorycontact();
							facContact.setFactory(dao.findById(fm
									.getFactorycode()));
							facContact.setUser(userDao.findById(userName));
							facContact.setName(factorycontactModel.getName());
							facContact.setEmail(factorycontactModel.getEmail());
							facContact.setTel(factorycontactModel.getTel());
							facContact.setCreatedate(new Date());

							factoryContactDao.save(facContact);
						}
					}

					// true lÃ  tá»“n táº¡i cáº£ 2 list, false lÃ  ko tá»“n
					// táº¡i trong list
					// má»›i
					boolean flag = false;

					// Táº¡o list Factorycontact Ä‘á»ƒ remove
					List<Factorycontact> lstToBeRemove = new ArrayList<Factorycontact>();
					List<FactorycontactModel> lstToBeUpdate = new ArrayList<FactorycontactModel>();

					for (FactorycontactModel factorycontactModel : newList) {
						for (Factorycontact factorycontact : oldList) {
							// náº¿u contact trong list cÅ© ko tá»“n táº¡i trong
							// list
							// má»›i, náº¿u contact code mÃ  = nhau thÃ¬ lÃ  cÃ³
							// tá»‘n
							// táº¡i, gÃ¡n flag = true
							if (factorycontact.getFactorycontactcode() == factorycontactModel
									.getFactorycontactcode()) {
								flag = true;
								break;
							}
						}
						if (flag == true) {
							lstToBeUpdate.add(factorycontactModel);
							flag = false;// set láº¡i thÃ nh ko tá»“n táº¡i
						}
					}

					// Lá»�c ra nhá»¯ng contact ko tá»“n táº¡i trong list má»›i
					flag = false;// máº·c Ä‘á»‹nh lÃ  ko tá»“n táº¡i
					for (Factorycontact factorycontact : oldList) {
						for (FactorycontactModel factorycontactModel : newList) {
							if (factorycontact.getFactorycontactcode() == factorycontactModel
									.getFactorycontactcode()) {
								flag = true;
								break;
							}
						}
						if (flag == false) {
							lstToBeRemove.add(factorycontact);
						}
						flag = false;
					}

					// láº·p qua list cáº§n remove
					for (Factorycontact factorycontact : lstToBeRemove) {
						factoryContactDao.delete(factoryContactDao
								.findById(factorycontact
										.getFactorycontactcode()));
					}

					Factorycontact fcNeedUpdate;
					// láº·p qua list cáº§n update
					for (FactorycontactModel factorycontactModel : lstToBeUpdate) {
						fcNeedUpdate = factoryContactDao
								.findById(factorycontactModel
										.getFactorycontactcode());

						fcNeedUpdate.setName(factorycontactModel.getName());
						fcNeedUpdate.setEmail(factorycontactModel.getEmail());
						fcNeedUpdate.setTel(factorycontactModel.getTel());

						factoryContactDao.update(fcNeedUpdate);
					}

					// return true;
					flag1 = true;
				}
			}

			// /TEST
			// case oldList.size() ==0
			if (oldListFAI.size() == 0) {
				if (newListFAI.size() == 0) {
					// return true;// 2 list rá»—ng nhÆ° nhau thÃ¬ ko lÃ m gÃ¬
					flag2 = true;
				} else {// náº¿u list má»›i cÃ³ contact, thÃ¬ add háº¿t list
						// má»›i Ä‘Ã³ vÃ o
					Factoryaccountinformation facContact;

					for (FactoryaccountinformationModel factorycontactModel : newListFAI) {
						facContact = new Factoryaccountinformation();
						facContact
								.setFactory(dao.findById(fm.getFactorycode()));
						facContact.setUser(userDao.findById(userName));
						facContact.setBankname(factorycontactModel
								.getBankname());
						facContact.setBankbranch(factorycontactModel
								.getBankbranch());
						facContact.setAccountnumber(factorycontactModel
								.getAccountnumber());
						facContact.setAddress(factorycontactModel.getAddress());
						facContact.setSwiftcode(factorycontactModel
								.getSwiftcode());
						facContact.setCreatedate(new Date());

						factoryaccountinformationDao.save(facContact);
					}
					// return true;
					flag2 = true;
				}
			} else {// TrÆ°á»�ng há»£p list cÅ© ko rá»—ng
				if (newListFAI.size() == 0) {// vÃ  list má»›i rá»—ng, thÃ¬ del
												// háº¿t
												// contact
					for (Factoryaccountinformation factorycontact : oldListFAI) {
						factoryaccountinformationDao
								.delete(factoryaccountinformationDao
										.findById(factorycontact
												.getFactoryaccountinfocode()));
					}
					// return true;
					flag2 = true;
				} else {// list má»›i khÃ´ng rá»—ng

					// lá»�c qua list má»›i, factorycontactcode ==0 thÃ¬ thÃªm
					// má»›i
					Factoryaccountinformation facContact;

					for (FactoryaccountinformationModel factorycontactModel : newListFAI) {
						if (factorycontactModel.getFactoryaccountinfocode() == 0) {
							facContact = new Factoryaccountinformation();
							facContact.setFactory(dao.findById(fm
									.getFactorycode()));
							facContact.setUser(userDao.findById(userName));
							facContact.setBankname(factorycontactModel
									.getBankname());
							facContact.setBankbranch(factorycontactModel
									.getBankbranch());
							facContact.setAccountnumber(factorycontactModel
									.getAccountnumber());
							facContact.setAddress(factorycontactModel
									.getAddress());
							facContact.setSwiftcode(factorycontactModel
									.getSwiftcode());
							facContact.setCreatedate(new Date());

							factoryaccountinformationDao.save(facContact);
						}
					}

					// true lÃ  tá»“n táº¡i cáº£ 2 list, false lÃ  ko tá»“n
					// táº¡i trong list
					// má»›i
					boolean flag = false;

					// Táº¡o list Factorycontact Ä‘á»ƒ remove
					List<Factoryaccountinformation> lstToBeRemove = new ArrayList<Factoryaccountinformation>();
					List<FactoryaccountinformationModel> lstToBeUpdate = new ArrayList<FactoryaccountinformationModel>();

					for (FactoryaccountinformationModel factorycontactModel : newListFAI) {
						for (Factoryaccountinformation factorycontact : oldListFAI) {
							// náº¿u contact trong list cÅ© ko tá»“n táº¡i trong
							// list
							// má»›i, náº¿u contact code mÃ  = nhau thÃ¬ lÃ  cÃ³
							// tá»‘n
							// táº¡i, gÃ¡n flag = true
							if (factorycontact.getFactoryaccountinfocode() == factorycontactModel
									.getFactoryaccountinfocode()) {
								flag = true;
								break;
							}
						}
						if (flag == true) {
							lstToBeUpdate.add(factorycontactModel);
							flag = false;// set láº¡i thÃ nh ko tá»“n táº¡i
						}
					}

					// Lá»�c ra nhá»¯ng contact ko tá»“n táº¡i trong list má»›i
					flag = false;// máº·c Ä‘á»‹nh lÃ  ko tá»“n táº¡i
					for (Factoryaccountinformation factorycontact : oldListFAI) {
						for (FactoryaccountinformationModel factorycontactModel : newListFAI) {
							if (factorycontact.getFactoryaccountinfocode() == factorycontactModel
									.getFactoryaccountinfocode()) {
								flag = true;
								break;
							}
						}
						if (flag == false) {
							lstToBeRemove.add(factorycontact);
						}
						flag = false;
					}

					// láº·p qua list cáº§n remove
					for (Factoryaccountinformation factorycontact : lstToBeRemove) {
						factoryaccountinformationDao
								.delete(factoryaccountinformationDao
										.findById(factorycontact
												.getFactoryaccountinfocode()));
					}

					Factoryaccountinformation fcNeedUpdate;
					// láº·p qua list cáº§n update
					for (FactoryaccountinformationModel factorycontactModel : lstToBeUpdate) {
						fcNeedUpdate = factoryaccountinformationDao
								.findById(factorycontactModel
										.getFactoryaccountinfocode());

						fcNeedUpdate.setBankname(factorycontactModel
								.getBankname());
						fcNeedUpdate.setBankbranch(factorycontactModel
								.getBankbranch());
						fcNeedUpdate.setAccountnumber(factorycontactModel
								.getAccountnumber());
						fcNeedUpdate.setAddress(factorycontactModel
								.getAddress());
						fcNeedUpdate.setSwiftcode(factorycontactModel
								.getSwiftcode());
						// facContact.setCreatedate(new Date());

						factoryaccountinformationDao.save(fcNeedUpdate);
					}

					// return true;
					flag2 = true;
				}
			}
			// /END TEST

			if (flag1 == true && flag2 == true)
				return true;
			else
				return false;

		} catch (Exception e) {
			log.error(String
					.format("editFactory with params 'fm', 'userName' in class %s %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete a Factory
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(String id) {
		log.info(String.format("delete with params 'id' in class: %s",
				getClass()));
		try {
			Factory fac = dao.findById(id);

			Set<Factorycontact> lst = fac.getFactorycontacts();
			for (Factorycontact factorycontact : lst) {
				factoryContactDao.delete(factoryContactDao
						.findById(factorycontact.getFactorycontactcode()));
			}

			Set<Factoryaccountinformation> lst2 = fac
					.getFactoryaccountinformations();
			for (Factoryaccountinformation factoryaccountinformation : lst2) {
				factoryaccountinformationDao
						.delete(factoryaccountinformationDao
								.findById(factoryaccountinformation
										.getFactoryaccountinfocode()));
			}

			dao.delete(fac);

			return true;
		} catch (Exception e) {
			log.error(String.format(
					"delete with params 'id' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a factory is existed in db
	 * 
	 * @param id
	 * @return true if existed.
	 */
	public boolean isFactoryExistedById(String id) {
		log.info(String
				.format("isFactoryExistedById with param 'id' in class: %s",
						getClass()));
		try {
			if (null == dao.findById(id))
				return false;
			return true;
		} catch (Exception e) {
			log.error(String
					.format("isFactoryExistedById with param 'id' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
