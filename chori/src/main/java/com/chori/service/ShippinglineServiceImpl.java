package com.chori.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.ShippinglineDao;
import com.chori.dao.ShippinglinecontactDao;
import com.chori.dao.UserDao;
import com.chori.entity.Shippingline;
import com.chori.entity.Shippinglinecontact;
import com.chori.model.ShippinglineContactModel;
import com.chori.model.ShippinglineModel;

@Repository("shippinglineService")
public class ShippinglineServiceImpl extends
		AbstractServiceImpl<Shippingline, String> implements
		ShippinglineService {

	private ShippinglineDao dao;

	@Autowired
	UserDao userDao;

	@Autowired
	ShippinglinecontactDao shippingLineContactDao;

	// public ShippinglineServiceImpl() {
	// }

	@Autowired
	public ShippinglineServiceImpl(
			@Qualifier("shippinglineDao") AbstractDao<Shippingline, String> abstractDao) {
		super(abstractDao);
		this.dao = (ShippinglineDao) abstractDao;
	}

	/***
	 * Function to get all shipping line in database
	 * 
	 * @return list of shipping line
	 */
	@Override
	public List<ShippinglineModel> getAllShippinglineModel() {
		log.info(String.format("getAllShippinglineModel in class: %s",
				getClass()));
		try {
			log.debug("get all Shippingline in DB after that return a list ShippinglineModel");
			List<Shippingline> lstShippingline = dao.getAll();

			ShippinglineModel model;
			List<ShippinglineModel> lst = new ArrayList<ShippinglineModel>();

			ShippinglineContactModel shippinglineContactModel;

			for (Shippingline spl : lstShippingline) {
				model = new ShippinglineModel();
				model.setShippinglinecode(spl.getShippinglinecode());
				model.setShortname(spl.getShortname());
				model.setLongname(spl.getLongname());
				model.setAddress(spl.getAddress());
				model.setTel(spl.getTel());
				model.setFax(spl.getFax());
				model.setTaxno(spl.getTaxno());
				model.setCreatedate(spl.getCreatedate());
				model.setStatus(spl.getStatus());
				model.setCreator(spl.getUser().getUsername());
				model.setRemark(spl.getRemark() == null ? "" : spl.getRemark());

				// get contact of shippingline
				Set<Shippinglinecontact> lstShippingLineContact = spl
						.getShippinglinecontacts();
				for (Shippinglinecontact splcontact : lstShippingLineContact) {
					shippinglineContactModel = new ShippinglineContactModel();
					shippinglineContactModel.setShippinglinecode(splcontact
							.getShippingline().getShippinglinecode());
					shippinglineContactModel
							.setShippinglinecontactcode(splcontact
									.getShippinglinecontactcode());
					shippinglineContactModel.setName(splcontact.getName());
					shippinglineContactModel.setEmail(splcontact.getEmail());
					shippinglineContactModel.setCreator(splcontact.getUser()
							.getUsername());
					shippinglineContactModel.setCreatedate(splcontact
							.getCreatedate());
					shippinglineContactModel.setTel(splcontact.getTel());
					model.getShippinglinecontacts().add(
							shippinglineContactModel);
				}
				lst.add(model);
			}
			log.debug("getAllShippinglineModel successfully");
			return lst;
		} catch (Exception e) {
			log.error(String.format(
					"getAllShippinglineModel in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new Shipping Line
	 * 
	 * @param splModel
	 *            , userName
	 * @return true if add successful, else return false
	 */
	public boolean addNewShippingLine(ShippinglineModel splModel,
			String userName) {
		try {
			Shippingline spl = new Shippingline();
			spl.setShippinglinecode(splModel.getShippinglinecode());
			spl.setUser(userDao.findById(userName));
			spl.setShortname(splModel.getShortname());
			spl.setLongname(splModel.getLongname());
			spl.setAddress(splModel.getAddress());
			spl.setTel(splModel.getTel());
			spl.setFax(splModel.getFax());
			spl.setTaxno(splModel.getTaxno());
			spl.setStatus(splModel.getStatus());
			// spl.setCreatedate(new Date());
			spl.setRemark(splModel.getRemark());
			// save shipping line before save contact
			dao.save(spl);
			System.err.println("Saving Shipping Line Successful");

			Shippinglinecontact splContact;

			Set<ShippinglineContactModel> newList = splModel
					.getShippinglinecontacts();
			for (ShippinglineContactModel splcontactModel : newList) {
				splContact = new Shippinglinecontact();
				splContact.setShippingline(dao.findById(splModel
						.getShippinglinecode()));
				splContact.setUser(userDao.findById(userName));
				splContact.setName(splcontactModel.getName());
				splContact.setEmail(splcontactModel.getEmail());
				// splContact.setCreatedate(new Date());
				splContact.setTel(splcontactModel.getTel());
				shippingLineContactDao.save(splContact);
			}
			System.err.println("Saving Shipping Line Successful");
			return true;
		}

		catch (Exception e) {
			System.err.println("Saving Shipping Line Failed");
			return false;
		}
	}

	/**
	 * Function find information of Shipping Line by id
	 * 
	 * @param id
	 *            of Shipping Line
	 * @return an object store information of a Shipping Line
	 */
	public ShippinglineModel findShippingLineModelById(String id) {
		Shippingline spl = dao.findById(id);
		ShippinglineModel splModel = new ShippinglineModel();

		splModel.setShippinglinecode(spl.getShippinglinecode());
		splModel.setCreator(spl.getUser().getUsername());
		splModel.setShortname(spl.getShortname());
		splModel.setLongname(spl.getLongname());
		splModel.setAddress(spl.getAddress());
		splModel.setTel(spl.getTel());
		splModel.setFax(spl.getFax());
		splModel.setTaxno(spl.getTaxno());
		splModel.setStatus(spl.getStatus());
		splModel.setCreatedate(spl.getCreatedate());
		splModel.setRemark(spl.getRemark());
		ShippinglineContactModel splcontactModel;

		Set<Shippinglinecontact> lst = spl.getShippinglinecontacts();
		for (Shippinglinecontact splcontact : lst) {
			splcontactModel = new ShippinglineContactModel();
			splcontactModel.setShippinglinecontactcode(splcontact
					.getShippinglinecontactcode());
			splcontactModel.setShippinglinecode(splcontact.getShippingline()
					.getShippinglinecode());
			splcontactModel.setCreator(splcontact.getUser().getUsername());
			splcontactModel.setName(splcontact.getName());
			splcontactModel.setEmail(splcontact.getEmail());
			splcontactModel.setCreatedate(splcontact.getCreatedate());
			splcontactModel.setTel(splcontact.getTel());
			splModel.getShippinglinecontacts().add(splcontactModel);
		}
		return splModel;
	}

	/**
	 * Function to edit information of a Shipping Line
	 * 
	 * @param id
	 *            of Shipping Line
	 * @return true if edit successful, else return false
	 */
	public boolean editShippingLine(ShippinglineModel splModel, String userName) {
		log.info(String
				.format("editShippingLine with params 'splModel', 'userName' in class %s",
						getClass()));
		try {
			Shippingline spl = dao.findById(splModel.getShippinglinecode());
			spl.setShortname(splModel.getShortname());
			spl.setLongname(splModel.getLongname());
			spl.setAddress(splModel.getAddress());
			spl.setTel(splModel.getTel());
			spl.setFax(splModel.getFax());
			spl.setTaxno(splModel.getTaxno());
			spl.setStatus(splModel.getStatus());
			spl.setRemark(splModel.getRemark());
			// save shipping line before save contact
			dao.update(spl);
			System.err.println("Edit shipping line sucessful");

			Set<ShippinglineContactModel> newList = splModel
					.getShippinglinecontacts();
			Set<Shippinglinecontact> oldList = spl.getShippinglinecontacts();

			// when old list =0
			if (oldList.size() == 0) {
				if (newList.size() == 0) {
					return true;// when list is null, do nothing
				} else {// if new list shipping line has contact, then add it
					Shippinglinecontact splContact;

					for (ShippinglineContactModel splcontactModel : newList) {
						splContact = new Shippinglinecontact();
						splContact.setShippingline(dao.findById(splModel
								.getShippinglinecode()));
						splContact.setUser(userDao.findById(userName));
						splContact.setName(splcontactModel.getName());
						splContact.setEmail(splcontactModel.getEmail());
						// splContact.setCreatedate(new Date());
						splContact.setTel(splcontactModel.getTel());
						shippingLineContactDao.save(splContact);
					}
					return true;
				}
			} else {// when old list not null
				if (newList.size() == 0) {// new list is null, then delete all
											// contact
					for (Shippinglinecontact splcontact : oldList) {
						shippingLineContactDao.delete(shippingLineContactDao
								.findById(splcontact
										.getShippinglinecontactcode()));
					}
					return true;
				} else {// newlist not null

					// loop newlist, if shippinglinecontactcode ==0, then add
					// new
					Shippinglinecontact splContact;

					for (ShippinglineContactModel splcontactModel : newList) {
						if (splcontactModel.getShippinglinecontactcode() == 0) {
							splContact = new Shippinglinecontact();
							splContact.setShippingline(dao.findById(splModel
									.getShippinglinecode()));
							splContact.setUser(userDao.findById(userName));
							splContact.setName(splcontactModel.getName());
							splContact.setEmail(splcontactModel.getEmail());
							// splContact.setCreatedate(new Date());
							splContact.setTel(splcontactModel.getTel());
							shippingLineContactDao.save(splContact);
						}
					}

					// true: newList + oldList is existed, false: newList is not
					// existed
					boolean flag = false;

					// Create list shippinglinecontact for remove
					List<Shippinglinecontact> lstToBeRemove = new ArrayList<Shippinglinecontact>();
					List<ShippinglineContactModel> lstToBeUpdate = new ArrayList<ShippinglineContactModel>();

					for (ShippinglineContactModel splcontactModel : newList) {
						for (Shippinglinecontact splcontact : oldList) {
							// if contact in oldList is not existed in newlist
							// if contactcode equal => is existed, set flag =
							// true
							if (splcontact.getShippinglinecontactcode() == splcontactModel
									.getShippinglinecontactcode()) {
								flag = true;
								break;
							}
						}
						if (flag == true) {
							lstToBeUpdate.add(splcontactModel);
							flag = false;// set is not existed
						}
					}

					// filter contact that not existed in newList
					flag = false;// default: is not existed
					for (Shippinglinecontact splcontact : oldList) {
						for (ShippinglineContactModel splcontactModel : newList) {
							if (splcontact.getShippinglinecontactcode() == splcontactModel
									.getShippinglinecontactcode()) {
								flag = true;
								break;
							}
						}

						if (flag == false) {
							lstToBeRemove.add(splcontact);
						}
						flag = false;
					}

					// loop remove list
					for (Shippinglinecontact splcontact : lstToBeRemove) {
						shippingLineContactDao.delete(shippingLineContactDao
								.findById(splcontact
										.getShippinglinecontactcode()));
					}

					Shippinglinecontact splcontactNeedUpdate;
					// loop update list
					for (ShippinglineContactModel splcontactModel : lstToBeUpdate) {
						splcontactNeedUpdate = shippingLineContactDao
								.findById(splcontactModel
										.getShippinglinecontactcode());

						splcontactNeedUpdate.setName(splcontactModel.getName());
						splcontactNeedUpdate.setEmail(splcontactModel
								.getEmail());
						splcontactNeedUpdate.setTel(splcontactModel.getTel());
						shippingLineContactDao.update(splcontactNeedUpdate);
					}

					return true;
				}
			}
		} catch (Exception e) {
			log.error(String
					.format("editShippingline with params 'splModel', 'userName' in class %s %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * Function to delete a Shipping Line
	 * 
	 * @param id
	 *            of Shipping Line
	 * @return true if delete successful, else return false
	 */
	public boolean delete(String id) {
		try {
			Shippingline spl = dao.findById(id);

			Set<Shippinglinecontact> lst = spl.getShippinglinecontacts();
			for (Shippinglinecontact splcontact : lst) {
				shippingLineContactDao.delete(shippingLineContactDao
						.findById(splcontact.getShippinglinecontactcode()));
			}

			dao.delete(spl);

			return true;
		} catch (Exception e) {
			throw e;
		}
	}
}
