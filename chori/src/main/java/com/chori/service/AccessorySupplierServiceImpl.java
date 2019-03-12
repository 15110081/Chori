package com.chori.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.AccessorySupplierContactDao;
import com.chori.dao.AccessorySupplierDao;
import com.chori.dao.UserDao;
import com.chori.entity.Accessorysupplier;
import com.chori.entity.Accessorysuppliercontact;
import com.chori.model.AccessorySupplierModel;
import com.chori.model.AccessorySuppliercontactModel;

@Repository("accessorysupplierService")
public class AccessorySupplierServiceImpl extends
		AbstractServiceImpl<Accessorysupplier, String> implements
		AccessorySupplierService {

	@Autowired
	AccessorySupplierDao dao;

	@Autowired
	UserDao userDao;

	@Autowired
	AccessorySupplierContactDao accessorysupplierContactDao;

	public AccessorySupplierServiceImpl() {
	}

	@Autowired
	public AccessorySupplierServiceImpl(
			@Qualifier("accessorysupplierDao") AbstractDao<Accessorysupplier, String> abstractDao) {
		super(abstractDao);
		this.dao = (AccessorySupplierDao) abstractDao;
	}

	/**
	 * This function is used get all AccessorySupplier
	 * 
	 * @return list<AccessorySupplierModel>
	 */

	@Override
	public List<AccessorySupplierModel> getAllAccessorySupplierModel() {
		log.info(String.format("getAllAccSupplierEntiry in class: %s",
				getClass()));
		try {
			log.debug("get all Accessory Supplier in DB after that return a list AccessorySupplierEntity");
			List<Accessorysupplier> lstAccSup = dao.getAll();

			AccessorySupplierModel accessorysupplierModel;
			List<AccessorySupplierModel> lst = new ArrayList<AccessorySupplierModel>();

			AccessorySuppliercontactModel accContactEn;

			for (Accessorysupplier accessorysupplier : lstAccSup) {

				accessorysupplierModel = new AccessorySupplierModel();
				accessorysupplierModel
						.setAccessorysuppliercode(accessorysupplier
								.getAccsuppliercode());
				accessorysupplierModel.setShortname(accessorysupplier
						.getShortname());
				accessorysupplierModel.setLongname(accessorysupplier
						.getLongname());
				accessorysupplierModel.setAddress(accessorysupplier
						.getAddress());
				accessorysupplierModel.setTel(accessorysupplier.getTel());
				accessorysupplierModel.setFax(accessorysupplier.getFax());
				accessorysupplierModel.setTaxno(accessorysupplier.getTaxno());
				accessorysupplierModel.setCreatedate(accessorysupplier
						.getCreatedate());
				accessorysupplierModel.setStatus(accessorysupplier.getStatus());
				accessorysupplierModel.setRemark(accessorysupplier.getRemark());
				accessorysupplierModel.setCreator(accessorysupplier.getUser()
						.getUsername());

				// get accessorycontact of AccessorySupplier
				Set<Accessorysuppliercontact> lstAccSupContact = accessorysupplier
						.getAccessorysuppliercontacts();
				for (Accessorysuppliercontact accsupcontact : lstAccSupContact) {
					accContactEn = new AccessorySuppliercontactModel();
					accContactEn.setAccessoryCode(accsupcontact
							.getAccessorysupplier().getAccsuppliercode());
					accContactEn.setAccessorycontact(accsupcontact
							.getAccsuppliercontactcode());
					accContactEn.setName(accsupcontact.getName());
					accContactEn.setEmail(accsupcontact.getEmail());
					accContactEn.setTelephone(accsupcontact.getTel());
					accContactEn.setCreator(accsupcontact.getUser()
							.getUsername());
					accContactEn.setCreatedate(accsupcontact.getCreatedate());

					accessorysupplierModel.getAccsupcontactModellist().add(
							accContactEn);
				}

				lst.add(accessorysupplierModel);
			}
			log.debug("getAllAccSupplierEntiry successfully");
			return lst;
		} catch (Exception e) {
			log.error(String.format(
					"getAllAccSupplierEntiry in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new AccessorySupplier
	 * 
	 * @param am
	 * @param userName
	 * @return
	 */

	@Override
	public boolean addNewAccSup(AccessorySupplierModel am, String userName) {
		log.info(String.format(
				"addNewAccSup with params 'am', 'userName' in class %s",
				getClass()));
		try {

			Accessorysupplier accessorysupplier = new Accessorysupplier();
			accessorysupplier.setAccsuppliercode(am.getAccessorysuppliercode());
			accessorysupplier.setUser(userDao.findById(userName));
			accessorysupplier.setShortname(am.getShortname());
			accessorysupplier.setLongname(am.getLongname());
			accessorysupplier.setAddress(am.getAddress());
			accessorysupplier.setTel(am.getTel());
			accessorysupplier.setFax(am.getFax());
			accessorysupplier.setTaxno(am.getTaxno());
			accessorysupplier.setStatus(am.getStatus());
			accessorysupplier.setRemark(am.getRemark());
			accessorysupplier.setCreatedate(new Date());
			accessorysupplier.setEmail(am.getEmail());

			// save accessorysuppiler into database first then contacts has id
			dao.save(accessorysupplier);
			System.err.println("Save Accessory Supplier Successful !");

			// Create variable in database Accessorysuppliercontact am param
			Accessorysuppliercontact accsupContact;

			Set<AccessorySuppliercontactModel> newList = am
					.getAccsupcontactModellist();
			for (AccessorySuppliercontactModel accsupcontactModel : newList) {
				accsupContact = new Accessorysuppliercontact();
				accsupContact.setAccessorysupplier(dao.findById(am
						.getAccessorysuppliercode()));
				accsupContact.setUser(userDao.findById(userName));
				accsupContact.setName(accsupcontactModel.getName());
				accsupContact.setEmail(accsupcontactModel.getEmail());
				accsupContact.setTel(accsupcontactModel.getTelephone());
				accsupContact.setCreatedate(new Date());

				accessorysupplierContactDao.save(accsupContact);
			}
			System.err.println("Save accessory Supplier successful");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("addNewAccSup with params 'am', 'userName' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * Function get AccessorySupplierModel by id
	 * 
	 * @param id
	 * @return
	 */

	@Override
	public AccessorySupplierModel findAccessorySupModelById(String id) {
		log.info(String.format(
				"findAccessorySupModelById with params 'id' in class: %s",
				getClass()));
		try {
			Accessorysupplier accsup = dao.findById(id);
			AccessorySupplierModel am = new AccessorySupplierModel();

			am.setAccessorysuppliercode(accsup.getAccsuppliercode());
			am.setCreator(accsup.getUser() == null ? "" : accsup.getUser()
					.getUsername());
			am.setShortname(accsup.getShortname());
			am.setLongname(accsup.getLongname());
			am.setAddress(accsup.getAddress());
			am.setTel(accsup.getTel());
			am.setFax(accsup.getFax());
			am.setTaxno(accsup.getTaxno());
			am.setEmail(accsup.getEmail());
			am.setStatus(accsup.getStatus());
			am.setRemark(accsup.getRemark());
			am.setCreatedate(accsup.getCreatedate());

			AccessorySuppliercontactModel acm;

			Set<Accessorysuppliercontact> lst = accsup
					.getAccessorysuppliercontacts();
			for (Accessorysuppliercontact accsupcontact : lst) {
				acm = new AccessorySuppliercontactModel();
				acm.setAccessorycontact(accsupcontact
						.getAccsuppliercontactcode());
				acm.setAccessoryCode(accsupcontact.getAccessorysupplier()
						.getAccsuppliercode());
				acm.setCreator(accsupcontact.getUser().getUsername());
				acm.setName(accsupcontact.getName());
				acm.setEmail(accsupcontact.getEmail());
				acm.setTelephone(accsupcontact.getTel());
				acm.setCreatedate(accsupcontact.getCreatedate());

				am.getAccsupcontactModellist().add(acm);
			}

			return am;
		} catch (Exception e) {
			log.error(String
					.format("findAccessorySupModelById with params 'id' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit 1 AccessorySupplier
	 */
	@Override
	public boolean editAccSup(AccessorySupplierModel am, String userName) {
		log.info(String.format(
				"editAccSup with params 'am', 'userName' in class %s",
				getClass()));
		try {
			Accessorysupplier accessorysupplier = dao.findById(am
					.getAccessorysuppliercode());
			accessorysupplier.setShortname(am.getShortname());
			accessorysupplier.setLongname(am.getLongname());
			accessorysupplier.setAddress(am.getAddress());
			accessorysupplier.setTel(am.getTel());
			accessorysupplier.setFax(am.getFax());
			accessorysupplier.setTaxno(am.getTaxno());
			accessorysupplier.setStatus(am.getStatus());
			accessorysupplier.setRemark(am.getRemark());
			accessorysupplier.setCreatedate(Calendar.getInstance().getTime());
			accessorysupplier.setEmail(am.getEmail());

			dao.update(accessorysupplier);
			System.err.println("Edit Access Sup successfully");

			Set<AccessorySuppliercontactModel> newList = am
					.getAccsupcontactModellist();
			Set<Accessorysuppliercontact> oldList = accessorysupplier
					.getAccessorysuppliercontacts();

			// if old list empty
			if (oldList.size() == 0) {
				if (newList.size() == 0) {
					return true;// if 2 list empty then do nothing
				} else {// else add all into list
					Accessorysuppliercontact accsupContact;

					for (AccessorySuppliercontactModel accsupcontactModel : newList) {
						accsupContact = new Accessorysuppliercontact();
						accsupContact.setAccessorysupplier(dao.findById(am
								.getAccessorysuppliercode()));
						accsupContact.setUser(userDao.findById(userName));
						accsupContact.setName(accsupcontactModel.getName());
						accsupContact.setTel(accsupcontactModel.getTelephone());
						accsupContact.setEmail(accsupcontactModel.getEmail());
						accsupContact.setCreatedate(Calendar.getInstance()
								.getTime());

						accessorysupplierContactDao.save(accsupContact);
					}
					return true;
				}
			} else { // if old list not empty
				if (newList.size() == 0) {// and new list is empty then delete
											// all
					for (Accessorysuppliercontact accsupcontact : oldList) {
						// contact
						accessorysupplierContactDao
								.delete(accessorysupplierContactDao
										.findById(accsupcontact
												.getAccsuppliercontactcode()));
					}
					return true;
				} else {// if new list not empty

					// filter through new list, accessorysuppliercontactcode ==
					// 0 then add new
					Accessorysuppliercontact accsupContact;

					for (AccessorySuppliercontactModel accsupcontactModel : newList) {
						if (accsupcontactModel.getAccessorycontact() == 0) {
							accsupContact = new Accessorysuppliercontact();
							accsupContact.setAccessorysupplier(dao.findById(am
									.getAccessorysuppliercode()));
							accsupContact.setUser(userDao.findById(userName));
							accsupContact.setName(accsupcontactModel.getName());
							accsupContact.setTel(accsupcontactModel
									.getTelephone());
							accsupContact.setEmail(accsupcontactModel
									.getEmail());
							accsupContact.setCreatedate(new Date());

							accessorysupplierContactDao.save(accsupContact);
						}
					}

					// if true is then exist in 2 list, false is not exist in
					// list
					// new
					boolean flag = false;

					// Create list Accessorysuppliercontact to remove
					List<Accessorysuppliercontact> lstToBeRemove = new ArrayList<Accessorysuppliercontact>();
					List<AccessorySuppliercontactModel> lstToBeUpdate = new ArrayList<AccessorySuppliercontactModel>();

					for (AccessorySuppliercontactModel accsupcontactModel : newList) {
						for (Accessorysuppliercontact accsupcontact : oldList) {
							// if contact in oldlist not exist in newlist
							// if contact code is same then it exist
							// set flag = true
							if (accsupcontact.getAccsuppliercontactcode() == accsupcontactModel
									.getAccessorycontact()) {
								flag = true;
								break;
							}
						}
						if (flag == true) {
							lstToBeUpdate.add(accsupcontactModel);
							flag = false;// set flag = false
						}
					}

					// if contact not exist in list
					flag = false;// set flag = false
					for (Accessorysuppliercontact accsupcontact : oldList) {
						for (AccessorySuppliercontactModel accsupcontactModel : newList) {
							if (accsupcontact.getAccsuppliercontactcode() == accsupcontactModel
									.getAccessorycontact()) {
								flag = true;
								break;
							}
						}

						if (flag == false) {
							lstToBeRemove.add(accsupcontact);
						}
						flag = false;
					}

					// check if list need remove
					for (Accessorysuppliercontact accsupcontact : lstToBeRemove) {
						accessorysupplierContactDao
								.delete(accessorysupplierContactDao
										.findById(accsupcontact
												.getAccsuppliercontactcode()));
					}

					Accessorysuppliercontact fcNeedUpdate;
					// check if list need update
					for (AccessorySuppliercontactModel accsupcontactModel : lstToBeUpdate) {
						fcNeedUpdate = accessorysupplierContactDao
								.findById(accsupcontactModel
										.getAccessorycontact());

						fcNeedUpdate.setName(accsupcontactModel.getName());
						fcNeedUpdate.setEmail(accsupcontactModel.getEmail());
						fcNeedUpdate.setTel(accsupcontactModel.getTelephone());

						accessorysupplierContactDao.update(fcNeedUpdate);
					}

					return true;
				}
			}
		} catch (Exception e) {
			log.error(String
					.format("editAccsup with params 'am', 'userName' in class %s %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete a accessorySupplier
	 * 
	 * @param id
	 * @return
	 */

	@Override
	public boolean delete(String id) {
		log.info(String.format("delete with param 'id' in class: %s",
				getClass()));
		try {
			Accessorysupplier accsup = dao.findById(id);

			Set<Accessorysuppliercontact> lst = accsup
					.getAccessorysuppliercontacts();
			for (Accessorysuppliercontact accsupcontact : lst) {
				accessorysupplierContactDao.delete(accessorysupplierContactDao
						.findById(accsupcontact.getAccsuppliercontactcode()));
			}

			dao.delete(accsup);

			return true;
		} catch (Exception e) {
			log.error(String.format(
					"delete with param 'id' in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a factory is existed in db
	 * 
	 * @param id
	 * @return true if existed , else false.
	 */
	@Override
	public boolean isAccSupExistedById(String id) {
		log.info(String.format(
				"isAccSupExistedById with param 'id' in class: %s", getClass()));
		try {
			if (null == dao.findById(id))
				return false;
			return true;
		} catch (Exception e) {
			log.error(String
					.format("isAccSupExistedById with param 'id' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

}