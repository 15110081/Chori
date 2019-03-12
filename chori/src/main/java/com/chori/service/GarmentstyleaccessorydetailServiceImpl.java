package com.chori.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.AccessoryDao;
import com.chori.dao.AccessoryGroupDao;
import com.chori.dao.GarmentstyleDao;
import com.chori.dao.GarmentstyleaccessorydetailDao;
import com.chori.dao.SizeDao;
import com.chori.dao.UserDao;
import com.chori.entity.Accessory;
import com.chori.entity.Accessorygroup;
import com.chori.entity.Garmentstyleaccessorydetail;
import com.chori.entity.Size;
import com.chori.model.AccessoryGroupModel;
import com.chori.model.AccessoryModel;
import com.chori.model.GarmentstyleaccessorydetailModel;
import com.chori.model.SizeModel;

@Service("garmentstyleaccessorydetailService")
public class GarmentstyleaccessorydetailServiceImpl extends
		AbstractServiceImpl<Garmentstyleaccessorydetail, Integer> implements
		GarmentstyleaccessorydetailService {
	private GarmentstyleaccessorydetailDao dao;

	@Autowired
	private AccessoryDao accessoryDao;

	@Autowired
	private SizeDao sizeDao;

	@Autowired
	private GarmentstyleDao garmentstyleDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private AccessoryGroupDao accessoryGroupDao;

	@Autowired
	public GarmentstyleaccessorydetailServiceImpl(
			@Qualifier("garmentstyleaccessorydetailDao") AbstractDao<Garmentstyleaccessorydetail, Integer> abstractDao) {
		super(abstractDao);
		this.dao = (GarmentstyleaccessorydetailDao) abstractDao;
	}

	/**
	 * This function is used to get list accessory for garment style. (contain
	 * non- and assigned accessory).
	 */
	public List<AccessoryModel> getListAccessoryForGarmentStyle(
			String garmentStyleName) {
		log.info(String.format("getListAccessoryForGarmentStyle in class: %s",
				getClass()));
		try {
			// lstUsedAccessoryName is used to store used accessory for this
			// garment
			Set<String> lstUsedAccessoryName = new HashSet<String>();

			List<Garmentstyleaccessorydetail> lstGSAD = dao
					.getListGarmentstyleaccessorydetailByGarmentStyleName(garmentStyleName);
			for (Garmentstyleaccessorydetail garmentstyleaccessorydetail : lstGSAD) {
				if (!lstUsedAccessoryName.contains(garmentstyleaccessorydetail
						.getAccessory().getAccessorycode())) {
					lstUsedAccessoryName.add(garmentstyleaccessorydetail
							.getAccessory().getAccessorycode());
				}
			}

			List<Accessory> lstAccessory = accessoryDao.getAll();
			AccessoryModel accessoryModel;
			List<AccessoryModel> lstaccessoryModel = new ArrayList<AccessoryModel>();
			for (Accessory acc : lstAccessory) {
				accessoryModel = new AccessoryModel();
				accessoryModel.setAccessorycode(acc.getAccessorycode());
				accessoryModel.setColorcode(acc.getColor() == null ? "" : acc
						.getColor().getColorcode());
				accessoryModel.setUserName(acc.getUser() == null ? "" : acc
						.getUser().getUsername());
				accessoryModel.setUnitcode(acc.getUnitByUnitcode() == null ? ""
						: acc.getUnitByUnitcode().getUnitcode());
				accessoryModel.setName(acc.getName());
				accessoryModel.setKind(acc.getKind());
				accessoryModel.setDimension(acc.getDimension());
				accessoryModel.setMode(acc.getMode());
				accessoryModel.setImgurl1(acc.getImgurl1());
				accessoryModel.setImgurl2(acc.getImgurl2());
				accessoryModel.setCreatedate(acc.getCreatedate());

				for (String string : lstUsedAccessoryName) {
					if (acc.getAccessorycode().equals(string)) {
						accessoryModel.setAssignedForGarment(true);
					}
				}

				lstaccessoryModel.add(accessoryModel);
			}
			log.debug("getListAccessoryForGarmentStyle successfully");
			return lstaccessoryModel;
		} catch (Exception e) {
			log.error(String
					.format("getListAccessoryForGarmentStyle in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get size by customerCode (included size that
	 * non- and assigned by garment code and accessory code)
	 * 
	 * @param garmentCode
	 * @param accessoryCode
	 * @param customerCode
	 * @return
	 */
	public List<SizeModel> getListSizeByGarmentAccessoryCustomer(
			String garmentCode, String accessoryCode, String customerCode,
			String garmentKindCode) {
		log.info(String.format(
				"getListSizeByGarmentAccessoryCustomer in class: %s",
				getClass()));
		try {
			Set<Integer> lstUsedSize = new HashSet<Integer>();

			List<Garmentstyleaccessorydetail> lstGSAD = dao
					.getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName(
							garmentCode, accessoryCode);
			for (Garmentstyleaccessorydetail garmentstyleaccessorydetail : lstGSAD) {
				if (!lstUsedSize.contains(garmentstyleaccessorydetail.getSize()
						.getSizecode())) {
					lstUsedSize.add(garmentstyleaccessorydetail.getSize()
							.getSizecode());
				}
			}

			List<Size> lstSizes = sizeDao.getListSizeByCustomer(customerCode,
					garmentKindCode);
			SizeModel sizeModel;
			List<SizeModel> lst = new ArrayList<SizeModel>();
			for (Size size : lstSizes) {
				sizeModel = new SizeModel();
				sizeModel.setCreatedate(size.getCreatedate());
				sizeModel.setCustomer(size.getCustomer().getCustomercode());
				sizeModel.setGarmentkind(size.getGarmentkind()
						.getGarmentkindcode());
				sizeModel.setSizecode(size.getSizecode());
				sizeModel.setSizename(size.getSizename());
				sizeModel.setType(size.getType().getTypecode());

				for (Integer integer : lstUsedSize) {
					if (size.getSizecode() == integer) {
						sizeModel.setAssignedForGarmentAccessory(true);
					}
				}

				log.debug("getListSizeByGarmentAccessoryCustomer successfully");
				lst.add(sizeModel);
			}
			return lst;
		} catch (Exception e) {
			log.error(String
					.format("getListSizeByGarmentAccessoryCustomer in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get list Garmentstyleaccessorydetail By
	 * GarmentStyleName And AccessoryName
	 * 
	 * @param garmentStyleName
	 * @param accessoryName
	 * @return
	 */
	public List<GarmentstyleaccessorydetailModel> getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName(
			String garmentStyleName, String accessoryName) {
		log.info(String
				.format("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName in class: %s",
						getClass()));
		try {
			List<Garmentstyleaccessorydetail> lstGarmentstyleaccessorydetail = dao
					.getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName(
							garmentStyleName, accessoryName);
			GarmentstyleaccessorydetailModel garmentstyleaccessorydetailModel;
			List<GarmentstyleaccessorydetailModel> lstGarmentstyleaccessorydetailModel = new ArrayList<GarmentstyleaccessorydetailModel>();
			for (Garmentstyleaccessorydetail garmentstyleaccessorydetail : lstGarmentstyleaccessorydetail) {
				garmentstyleaccessorydetailModel = new GarmentstyleaccessorydetailModel();
				garmentstyleaccessorydetailModel
						.setGarmentstyleaccessorydetailcode(garmentstyleaccessorydetail
								.getGarmentstyleaccessorydetailcode());
				garmentstyleaccessorydetailModel
						.setAccessorycode(garmentstyleaccessorydetail
								.getAccessory().getAccessorycode());
				garmentstyleaccessorydetailModel
						.setAccessoryName(garmentstyleaccessorydetail
								.getAccessory().getName());
				garmentstyleaccessorydetailModel
						.setGarmentstylecode(garmentstyleaccessorydetail
								.getGarmentstyle().getGarmentstylecode());
				garmentstyleaccessorydetailModel
						.setGarmentstyleDescription(garmentstyleaccessorydetail
								.getGarmentstyle().getDescription());
				garmentstyleaccessorydetailModel
						.setSizecode(garmentstyleaccessorydetail.getSize()
								.getSizecode());
				garmentstyleaccessorydetailModel
						.setSizename(garmentstyleaccessorydetail.getSize()
								.getSizename());
				garmentstyleaccessorydetailModel
						.setTypecode(garmentstyleaccessorydetail.getSize()
								.getType().getTypecode());
				garmentstyleaccessorydetailModel
						.setTypeDescription(garmentstyleaccessorydetail
								.getSize().getType().getDescription());
				garmentstyleaccessorydetailModel
						.setCreator(garmentstyleaccessorydetail.getUser() == null ? ""
								: garmentstyleaccessorydetail.getUser()
										.getUsername());
				garmentstyleaccessorydetailModel
						.setUsedvalue(garmentstyleaccessorydetail
								.getUsedvalue());
				garmentstyleaccessorydetailModel.setCreatedate(new Date());

				lstGarmentstyleaccessorydetailModel
						.add(garmentstyleaccessorydetailModel);
			}
			log.debug("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName successful");
			return lstGarmentstyleaccessorydetailModel;
		} catch (Exception e) {
			log.error(String
					.format("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get ListGarmentstyleaccessorydetail By
	 * GarmentStyleName
	 * 
	 * @param garmentStyleName
	 * @return
	 */
	public List<GarmentstyleaccessorydetailModel> getListGarmentstyleaccessorydetailByGarmentStyleName(
			String garmentStyleName) {
		log.info(String
				.format("getListGarmentstyleaccessorydetailByGarmentStyleName in class: %s",
						getClass()));
		try {
			List<Garmentstyleaccessorydetail> lstGarmentstyleaccessorydetail = dao
					.getListGarmentstyleaccessorydetailByGarmentStyleName(garmentStyleName);
			GarmentstyleaccessorydetailModel garmentstyleaccessorydetailModel;
			List<GarmentstyleaccessorydetailModel> lstGarmentstyleaccessorydetailModel = new ArrayList<GarmentstyleaccessorydetailModel>();
			for (Garmentstyleaccessorydetail garmentstyleaccessorydetail : lstGarmentstyleaccessorydetail) {
				garmentstyleaccessorydetailModel = new GarmentstyleaccessorydetailModel();
				garmentstyleaccessorydetailModel
						.setGarmentstyleaccessorydetailcode(garmentstyleaccessorydetail
								.getGarmentstyleaccessorydetailcode());
				garmentstyleaccessorydetailModel
						.setAccessorycode(garmentstyleaccessorydetail
								.getAccessory().getAccessorycode());
				garmentstyleaccessorydetailModel
						.setAccessoryName(garmentstyleaccessorydetail
								.getAccessory().getName());
				garmentstyleaccessorydetailModel
						.setGarmentstylecode(garmentstyleaccessorydetail
								.getGarmentstyle().getGarmentstylecode());
				garmentstyleaccessorydetailModel
						.setGarmentstyleDescription(garmentstyleaccessorydetail
								.getGarmentstyle().getDescription());
				garmentstyleaccessorydetailModel
						.setSizecode(garmentstyleaccessorydetail.getSize()
								.getSizecode());
				garmentstyleaccessorydetailModel
						.setSizename(garmentstyleaccessorydetail.getSize()
								.getSizename());
				garmentstyleaccessorydetailModel
						.setTypecode(garmentstyleaccessorydetail.getSize()
								.getType().getTypecode());
				garmentstyleaccessorydetailModel
						.setTypeDescription(garmentstyleaccessorydetail
								.getSize().getType().getDescription());
				garmentstyleaccessorydetailModel
						.setCreator(garmentstyleaccessorydetail.getUser() == null ? ""
								: garmentstyleaccessorydetail.getUser()
										.getUsername());
				garmentstyleaccessorydetailModel
						.setUsedvalue(garmentstyleaccessorydetail
								.getUsedvalue());
				garmentstyleaccessorydetailModel.setCreatedate(new Date());

				lstGarmentstyleaccessorydetailModel
						.add(garmentstyleaccessorydetailModel);
			}
			log.debug("getListGarmentstyleaccessorydetailByGarmentStyleName successful");
			// sort by accessory name
			Collections.sort(lstGarmentstyleaccessorydetailModel,
					GarmentstyleaccessorydetailModel.accessoryNameComparator);
			return lstGarmentstyleaccessorydetailModel;
		} catch (Exception e) {
			log.error(String
					.format("getListGarmentstyleaccessorydetailByGarmentStyleName in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new Garmentstyleaccessorydetail
	 */
	public boolean addNewGarmentstyleaccessorydetail(
			GarmentstyleaccessorydetailModel model, String userName) {
		log.info(String
				.format("addNewGarmentstyleaccessorydetail with params 'model', 'userName' in class: %s",
						getClass()));
		try {
			Garmentstyleaccessorydetail garmentstyleaccessorydetail = new Garmentstyleaccessorydetail();
			garmentstyleaccessorydetail.setAccessory(accessoryDao
					.findById(model.getAccessorycode()));
			garmentstyleaccessorydetail.setGarmentstyle(garmentstyleDao
					.findById(model.getGarmentstylecode()));
			garmentstyleaccessorydetail.setSize(sizeDao.findById(model
					.getSizecode()));
			garmentstyleaccessorydetail.setUser(userDao.findById(userName));
			garmentstyleaccessorydetail.setUsedvalue(model.getUsedvalue());
			garmentstyleaccessorydetail.setCreatedate(new Date());

			dao.save(garmentstyleaccessorydetail);
			return true;
		} catch (Exception e) {
			log.error(String
					.format("addNewGarmentstyleaccessorydetail with params 'model', 'userName' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function return true if all size by customer is assign for
	 * garmentstyleAccessoryDetail by garmentStyle and accessory Hàm trả về true
	 * nếu tất cả các size đã được assign cho garmentstyleAccessoryDetail (dùng
	 * enable, disable nút add)
	 * 
	 * @param garmentCode
	 * @param accessoryCode
	 * @param customerCode
	 * @return
	 */
	public boolean isAllSizeAlreadyAssigned(String garmentCode,
			String accessoryCode, String customerCode, String garmentKindCode) {
		log.info(String.format("isAllSizeAlreadyAssigned in class: %s",
				getClass()));
		try {
			Set<Integer> lstUsedSize = new HashSet<Integer>();

			List<Garmentstyleaccessorydetail> lstGSAD = dao
					.getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName(
							garmentCode, accessoryCode);
			for (Garmentstyleaccessorydetail garmentstyleaccessorydetail : lstGSAD) {
				if (!lstUsedSize.contains(garmentstyleaccessorydetail.getSize()
						.getSizecode())) {
					lstUsedSize.add(garmentstyleaccessorydetail.getSize()
							.getSizecode());
				}
			}

			List<Size> lstSizes = sizeDao.getListSizeByCustomer(customerCode,
					garmentKindCode);

			if (lstUsedSize.size() == lstSizes.size()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error(String.format(
					"isAllSizeAlreadyAssigned in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit a garment style accessory detail (edit used
	 * value) Hàm edit 1 Garmentstyleaccessorydetail (chỉ edit lại used value).
	 * 
	 * @param model
	 * @param userName
	 * @return
	 */
	public boolean editGarmentstyleAccessoryDetail(
			GarmentstyleaccessorydetailModel model, String userName) {
		log.info(String
				.format("editGarmentstyleAccessoryDetail with params 'model', 'userName' in class %s",
						getClass()));
		try {
			Garmentstyleaccessorydetail garmentstyleaccessorydetail = dao
					.findById(model.getGarmentstyleaccessorydetailcode());
			garmentstyleaccessorydetail.setUsedvalue(model.getUsedvalue());
			dao.update(garmentstyleaccessorydetail);
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editGarmentstyleAccessoryDetail with params 'model', 'userName' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete garmentstyleaccessorydetail
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteGSAD(Integer id) {
		log.info(String.format("delete with params 'id' in class: %s",
				getClass()));
		try {
			Garmentstyleaccessorydetail garmentstyleaccessorydetail = dao
					.findById(id);

			dao.delete(garmentstyleaccessorydetail);

			return true;
		} catch (Exception e) {
			log.error(String.format(
					"delete with params 'id' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get list of accessory group by garment style
	 * code Hàm lấy list accessory group qua garment style code để load vào
	 * dropdown list cuối
	 * 
	 * @param garmentStyleCode
	 * @return
	 */
	public List<AccessoryGroupModel> getAccessoryGroupByGarmentStyleCode(
			String garmentStyleCode) {
		log.info(String.format(
				"getAccessoryGroupByGarmentStyleCode in class: %s", getClass()));
		try {
			Set<String> lstUsedAccessoryGroupName = new HashSet<String>();

			List<Garmentstyleaccessorydetail> lstGarmentstyleaccessorydetail = dao
					.getListGarmentstyleaccessorydetailByGarmentStyleName(garmentStyleCode);
			for (Garmentstyleaccessorydetail garmentstyleaccessorydetail : lstGarmentstyleaccessorydetail) {
				if (!lstUsedAccessoryGroupName
						.contains(garmentstyleaccessorydetail.getAccessory()
								.getAccessorygroup().getAccessorygroupcode())) {
					lstUsedAccessoryGroupName.add(garmentstyleaccessorydetail
							.getAccessory().getAccessorygroup()
							.getAccessorygroupcode());
				}
			}

			List<AccessoryGroupModel> lst = new ArrayList<AccessoryGroupModel>();
			AccessoryGroupModel accessoryGroupModel;
			for (String string : lstUsedAccessoryGroupName) {
				Accessorygroup accessorygroup = accessoryGroupDao
						.findById(string);
				accessoryGroupModel = new AccessoryGroupModel();
				accessoryGroupModel.setAccessorygroupCode(accessorygroup
						.getAccessorygroupcode());
				lst.add(accessoryGroupModel);
			}

			return lst;
		} catch (Exception e) {
			log.error(String
					.format("getAccessoryGroupByGarmentStyleCode in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to get list Garmentstyleaccessorydetail By
	 * GarmentStyleName And AccessoryGroupName Hàm lấy
	 * Garmentstyleaccessorydetail qua GarmentStyleName và AccessoryGroupName
	 * 
	 * @param garmentStyleName
	 * @param accessoryGroupName
	 * @return
	 */
	public List<GarmentstyleaccessorydetailModel> getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName(
			String garmentStyleName, String accessoryGroupName) {
		log.info(String
				.format("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName in class: %s",
						getClass()));
		try {
			List<Garmentstyleaccessorydetail> lstGarmentstyleaccessorydetail = dao
					.getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName(
							garmentStyleName, accessoryGroupName);
			GarmentstyleaccessorydetailModel garmentstyleaccessorydetailModel;
			List<GarmentstyleaccessorydetailModel> lstGarmentstyleaccessorydetailModel = new ArrayList<GarmentstyleaccessorydetailModel>();
			for (Garmentstyleaccessorydetail garmentstyleaccessorydetail : lstGarmentstyleaccessorydetail) {
				garmentstyleaccessorydetailModel = new GarmentstyleaccessorydetailModel();
				garmentstyleaccessorydetailModel
						.setGarmentstyleaccessorydetailcode(garmentstyleaccessorydetail
								.getGarmentstyleaccessorydetailcode());
				garmentstyleaccessorydetailModel
						.setAccessorycode(garmentstyleaccessorydetail
								.getAccessory().getAccessorycode());
				garmentstyleaccessorydetailModel
						.setAccessoryName(garmentstyleaccessorydetail
								.getAccessory().getName());
				garmentstyleaccessorydetailModel
						.setGarmentstylecode(garmentstyleaccessorydetail
								.getGarmentstyle().getGarmentstylecode());
				garmentstyleaccessorydetailModel
						.setGarmentstyleDescription(garmentstyleaccessorydetail
								.getGarmentstyle().getDescription());
				garmentstyleaccessorydetailModel
						.setSizecode(garmentstyleaccessorydetail.getSize()
								.getSizecode());
				garmentstyleaccessorydetailModel
						.setSizename(garmentstyleaccessorydetail.getSize()
								.getSizename());
				garmentstyleaccessorydetailModel
						.setTypecode(garmentstyleaccessorydetail.getSize()
								.getType().getTypecode());
				garmentstyleaccessorydetailModel
						.setTypeDescription(garmentstyleaccessorydetail
								.getSize().getType().getDescription());
				garmentstyleaccessorydetailModel
						.setCreator(garmentstyleaccessorydetail.getUser() == null ? ""
								: garmentstyleaccessorydetail.getUser()
										.getUsername());
				garmentstyleaccessorydetailModel
						.setUsedvalue(garmentstyleaccessorydetail
								.getUsedvalue());
				garmentstyleaccessorydetailModel.setCreatedate(new Date());

				lstGarmentstyleaccessorydetailModel
						.add(garmentstyleaccessorydetailModel);
			}

			Collections.sort(lstGarmentstyleaccessorydetailModel,
					GarmentstyleaccessorydetailModel.accessoryNameComparator);

			log.debug("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName successful");
			return lstGarmentstyleaccessorydetailModel;
		} catch (Exception e) {
			log.error(String
					.format("getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
