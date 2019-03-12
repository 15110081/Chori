package com.chori.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chori.dao.FactoryDao;
import com.chori.dao.FactorycontactDao;
import com.chori.dao.UserDao;
import com.chori.entity.Factory;
import com.chori.entity.Factorycontact;
import com.chori.model.FactoryModel;
import com.chori.model.FactorycontactModel;

@Transactional
@Service
public class AdFactoryService {

	@Autowired
	FactoryDao dao;

	@Autowired
	UserDao userDao;

	@Autowired
	FactorycontactDao factoryContactDao;

	/**
	 * This function is used to add new Factory
	 * 
	 * @param fm
	 * @param userName
	 * @return
	 */
	public boolean addNewFactory(FactoryModel fm, String userName) {
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

		try {
			// save factory vào csdl trc sau đó mấy contact mới có id
			dao.save(fac);
			System.err.println("Save factory thành công");
		} catch (Exception e) {
			System.err.println("Save factory ko thành công");
			return false;
		}

		// Tạo biến để lưu từng Factorycontact từ param
		Factorycontact facContact;

		Set<FactorycontactModel> newList = fm.getFactorycontactModellist();
		for (FactorycontactModel factorycontactModel : newList) {
			facContact = new Factorycontact();
			facContact.setFactory(dao.findById(fm.getFactorycode()));
			facContact.setUser(userDao.findById(userName));
			facContact.setName(factorycontactModel.getName());
			facContact.setEmail(factorycontactModel.getEmail());
			facContact.setCreatedate(new Date());

			factoryContactDao.save(facContact);
		}
		System.err.println("Save các factoryContact thành công");
		return true;
	}

	/**
	 * Function get Factory Model by id
	 * 
	 * @param id
	 * @return
	 */
	public FactoryModel findFactoryModelById(String id) {
		Factory fac = dao.findById(id);
		FactoryModel fm = new FactoryModel();

		fm.setFactorycode(fac.getFactorycode());
		fm.setCreator(fac.getUser().getUsername());
		fm.setShortname(fac.getShortname());
		fm.setLongname(fac.getLongname());
		fm.setAddress(fac.getAddress());
		fm.setTel(fac.getTel());
		fm.setFax(fac.getFax());
		fm.setTaxno(fac.getTaxno());
		fm.setStatus(fac.getStatus());
		fm.setCreatedate(fac.getCreatedate());

		FactorycontactModel fcm;

		Set<Factorycontact> lst = fac.getFactorycontacts();
		for (Factorycontact factorycontact : lst) {
			fcm = new FactorycontactModel();
			fcm.setFactorycontactcode(factorycontact.getFactorycontactcode());
			fcm.setFactoryCode(factorycontact.getFactory().getFactorycode());
			fcm.setCreator(factorycontact.getUser().getUsername());
			fcm.setName(factorycontact.getName());
			fcm.setEmail(factorycontact.getEmail());
			fcm.setCreatedate(factorycontact.getCreatedate());

			fm.getFactorycontactModellist().add(fcm);
		}

		return fm;
	}

	public boolean editFactory(FactoryModel fm, String userName) {
		Factory fac = dao.findById(fm.getFactorycode());
		// fac.setFactorycode(fm.getFactorycode());
		// fac.setUser(userDao.findById(userName));
		fac.setShortname(fm.getShortname());
		fac.setLongname(fm.getLongname());
		fac.setAddress(fm.getAddress());
		fac.setTel(fm.getTel());
		fac.setFax(fm.getFax());
		fac.setTaxno(fm.getTaxno());
		fac.setStatus(fm.getStatus());
		// fac.setCreatedate(new Date());

		try {
			// save factory vào csdl trc sau đó mấy contact mới có id
			dao.update(fac);
			System.err.println("Edit factory thành công");
		} catch (Exception e) {
			System.err.println("Edit factory ko thành công");
			return false;
		}

		Set<FactorycontactModel> newList = fm.getFactorycontactModellist();
		Set<Factorycontact> oldList = fac.getFactorycontacts();

		// trường hợp list cũ ==0
		if (oldList.size() == 0) {
			if (newList.size() == 0) {
				return true;// 2 list rỗng như nhau thì ko làm gì
			} else {// nếu list mới có contact, thì add hết list mới đó vào
				Factorycontact facContact;

				for (FactorycontactModel factorycontactModel : newList) {
					facContact = new Factorycontact();
					facContact.setFactory(dao.findById(fm.getFactorycode()));
					facContact.setUser(userDao.findById(userName));
					facContact.setName(factorycontactModel.getName());
					facContact.setEmail(factorycontactModel.getEmail());
					facContact.setCreatedate(new Date());

					factoryContactDao.save(facContact);
				}
				return true;
			}
		} else {// Trường hợp list cũ ko rỗng
			if (newList.size() == 0) {// và list mới rỗng, thì del hết contact
				for (Factorycontact factorycontact : oldList) {
					factoryContactDao.delete(factoryContactDao
							.findById(factorycontact.getFactorycontactcode()));
				}
				return true;
			} else {// list mới không rỗng

				// lọc qua list mới, factorycontactcode ==0 thì thêm mới
				Factorycontact facContact;

				for (FactorycontactModel factorycontactModel : newList) {
					if (factorycontactModel.getFactorycontactcode() == 0) {
						facContact = new Factorycontact();
						facContact
								.setFactory(dao.findById(fm.getFactorycode()));
						facContact.setUser(userDao.findById(userName));
						facContact.setName(factorycontactModel.getName());
						facContact.setEmail(factorycontactModel.getEmail());
						facContact.setCreatedate(new Date());

						factoryContactDao.save(facContact);
					}
				}

				// true là tồn tại cả 2 list, false là ko tồn tại trong list mới
				boolean flag = false;

				// Tạo list Factorycontact để remove
				List<Factorycontact> lstToBeRemove = new ArrayList<Factorycontact>();
				List<FactorycontactModel> lstToBeUpdate = new ArrayList<FactorycontactModel>();

				for (FactorycontactModel factorycontactModel : newList) {
					for (Factorycontact factorycontact : oldList) {
						// nếu contact trong list cũ ko tồn tại trong list mới,
						// nếu contact code mà = nhau thì là có tốn tại, gán
						// flag = true
						if (factorycontact.getFactorycontactcode() == factorycontactModel
								.getFactorycontactcode()) {
							flag = true;
							break;
						}
					}
					if (flag == true) {
						lstToBeUpdate.add(factorycontactModel);
						flag = false;// set lại thành ko tồn tại
					}
				}

				// Lọc ra những contact ko tồn tại trong list mới
				flag = false;// mặc định là ko tồn tại
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

				// lặp qua list cần remove
				for (Factorycontact factorycontact : lstToBeRemove) {
					factoryContactDao.delete(factoryContactDao
							.findById(factorycontact.getFactorycontactcode()));
				}

				Factorycontact fcNeedUpdate;
				// lặp qua list cần update
				for (FactorycontactModel factorycontactModel : lstToBeUpdate) {
					fcNeedUpdate = factoryContactDao
							.findById(factorycontactModel
									.getFactorycontactcode());

					fcNeedUpdate.setName(factorycontactModel.getName());
					fcNeedUpdate.setEmail(factorycontactModel.getEmail());

					factoryContactDao.update(fcNeedUpdate);
				}

				return true;
			}
		}

		// return false;
	}

	/**
	 * This function is used to delete a Factory
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(String id) {
		try {
			Factory fac = dao.findById(id);

			Set<Factorycontact> lst = fac.getFactorycontacts();
			for (Factorycontact factorycontact : lst) {
				factoryContactDao.delete(factoryContactDao
						.findById(factorycontact.getFactorycontactcode()));
			}

			dao.delete(fac);

			return true;
		} catch (Exception e) {
			throw e;
		}
	}
}
