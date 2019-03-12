package com.chori.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.CustomerDao;
import com.chori.dao.GarmentkindDao;
import com.chori.dao.SizeDao;
import com.chori.dao.TypeDao;
import com.chori.dao.UserDao;
import com.chori.entity.Size;
import com.chori.model.SizeModel;

@Repository("sizeService")
public class SizeServiceImpl extends AbstractServiceImpl<Size, Integer>
		implements SizeService {

	private SizeDao dao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private GarmentkindDao garmentKindDao;

	@Autowired
	private CustomerDao cusDao;

	@Autowired
	private TypeDao typeDao;

	@Autowired
	public SizeServiceImpl(
			@Qualifier("sizeDao") AbstractDao<Size, Integer> abstractDao) {
		super(abstractDao);
		this.dao = (SizeDao) abstractDao;
	}

	/***
	 * Function to get all Size in database return a list of Size
	 */
	@Override
	public List<SizeModel> getAllSizeModel() {
		log.debug("in Size service list");
		try {
			List<Size> lstSizes = dao.getAll();
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

				lst.add(sizeModel);
			}
			return lst;
		} catch (NullPointerException ne) {
			log.error("list service Size err: " + ne.getMessage());
			throw ne;
		}
	}

	/***
	 * Function to get all Size of a customer by customer code return a list of
	 * a customer
	 */
	public List<SizeModel> findSizeCodeByCustomerCode(String customerCode) {
		log.debug("in SizeService at findSizeCodeByCustomerCode method");
		try {
			List<Size> lstSizes = dao.getAll();
			SizeModel sizeModel;
			List<SizeModel> lst = new ArrayList<SizeModel>();
			for (Size size : lstSizes) {
				sizeModel = new SizeModel();
				if (size.getCustomer().getCustomercode().equals(customerCode)) {
					sizeModel.setSizecode(size.getSizecode());
					sizeModel.setSizename(size.getSizename());
					lst.add(sizeModel);
				}
			}
			return lst;
		} catch (NullPointerException ne) {
			log.error("in SizeService at findSizeCodeByCustomerCode method error: "
					+ ne.getMessage());
			throw ne;
		}
	}

	/**
	 * This function is used to delete an Size in database.
	 * 
	 * @param sizecode
	 * @return delete successfully =>true , else => false
	 */
	public boolean deleteSizeById(Integer sizecode) {
		log.info(String.format("deleteSize with param 'sizecode' in class: %s",
				getClass()));
		try {
			dao.deleteById(sizecode);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This function find detail of a size by id.
	 * 
	 * @param sizecode
	 * @return a SizeModel object which store detail of a size
	 */
	public SizeModel findSizeModelById(Integer sizecode) {
		log.info(String.format(
				"findSizeModelById with param 'sizecode' in class: %s",
				getClass()));
		try {
			SizeModel model = new SizeModel();
			Size size = dao.findById(sizecode);
			model.setCreatedate(size.getCreatedate());
			model.setCreator(size.getUser().getUsername());
			model.setCustomer(size.getCustomer().getCustomercode());
			model.setGarmentkind(size.getGarmentkind().getGarmentkindcode());
			model.setSizecode(size.getSizecode());
			model.setSizename(size.getSizename());
			model.setType(size.getType().getTypecode());
			log.debug("findSizeModelById successfully");
			return model;
		} catch (Exception e) {
			log.error(String
					.format("findSizeModelById with param 'sizecode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit size into database
	 * 
	 * @param sizeModel
	 * @return true if edit successfully, false if have exception
	 */
	public boolean editSize(SizeModel sizeModel) {
		log.info(String.format("editSize with param 'sizeModel' in class: %s",
				getClass()));
		try {
			Size size = dao.findById(sizeModel.getSizecode());
			size.setSizecode(sizeModel.getSizecode());
			size.setCustomer(cusDao.findById(sizeModel.getCustomer()));
			size.setGarmentkind(garmentKindDao.findById(sizeModel
					.getGarmentkind()));
			size.setSizename(sizeModel.getSizename());
			size.setType(typeDao.findById(sizeModel.getType()));

			dao.update(size);
			log.debug("editSize successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editSize with param 'sizeModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("editSize with param 'sizeModel' in class: %s has error: %s",
									getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if there is a size existed in database
	 * 
	 * @param sizeModel
	 * @return false if not existed, true if existed
	 */
	public boolean isSizeExisted(SizeModel sizeModel) {
		log.debug("in SizeService at isSizeExistedById method");
		try {

			List<Size> lstSizes = dao.getAll();
			for (Size size : lstSizes) {
				if (size.getCustomer().getCustomercode()
						.equals(sizeModel.getCustomer())
//						&& size.getType().getTypecode()
//								.equals(sizeModel.getType())
						&& size.getGarmentkind().getGarmentkindcode()
								.equals(sizeModel.getGarmentkind())
						&& size.getSizename().equals(sizeModel.getSizename())) {

					return true;
				}
			}
			return false;
		} catch (NullPointerException ne) {
			log.error("in SizeService at isSizeExistedById method error: "
					+ ne.getMessage());
			throw ne;
		}
	}

	/**
	 * This function is used to add new size into database
	 * 
	 * @param sizeModel
	 *            , userName
	 * @return true if add successfully, false if have exception
	 */
	public boolean addSize(SizeModel sizeModel, String userName) {
		log.info(String.format("addSize in class: %s", getClass()));
		try {
			if(validateForeignKey(sizeModel.getCustomer(), sizeModel
					.getGarmentkind(),sizeModel.getType(),userName) == true)
			{
				Size size = new Size();
				// size.setCreatedate(new Date());
				size.setCustomer(cusDao.findById(sizeModel.getCustomer()));
				size.setGarmentkind(garmentKindDao.findById(sizeModel
						.getGarmentkind()));
				size.setSizecode(sizeModel.getSizecode());
				size.setSizename(sizeModel.getSizename());
				size.setType(typeDao.findById(sizeModel.getType()));
				size.setUser(userDao.findById(userName));
				dao.save(size);
				log.debug("add new Size into database successfully");
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error(String
					.format("addSize with param 'SizeModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println("add new Size into database fail, method SizeModel(), class SizeService");
			throw e;
		}
	}

	/**
	 * This function is used to duplicate all size of a customer into database
	 * 
	 * @param customerCode
	 *            , userName
	 * @return true if add successfully, false if have exception
	 */
	public boolean duplicateSize(String customerCodeFrom,
			String customerCodeTo, String userName) {
		log.info(String.format("duplicateSize in class: %s", getClass()));
		try {
			List<Size> lstSize = dao.getAll();
			List<Size> lstSizeByCustomerCodeFrom = new ArrayList<Size>();
			List<Size> lstSizeByCustomerCodeTo = new ArrayList<Size>();
			Size sizeToAdd;

			// get list size by customercode in order to duplicate it
			for (Size size : lstSize) {
				if (size.getCustomer().getCustomercode()
						.equals(customerCodeFrom)) {
					lstSizeByCustomerCodeFrom.add(size);
				}
				//add size is existed of customerTo
				if (size.getCustomer().getCustomercode()
						.equals(customerCodeTo)) {
					lstSizeByCustomerCodeTo.add(size);
				}
			}

			// duplicate its fields and add to database
			for (Size sizeByCustomerCodeFrom : lstSizeByCustomerCodeFrom) {
				//validate to avoid null foreign key
				if(validateForeignKey(customerCodeTo, sizeByCustomerCodeFrom.getGarmentkind()
						.getGarmentkindcode(), sizeByCustomerCodeFrom
						.getType().getTypecode(), userName) == true)
				{
					
					//check customerTo not have this size, then add to db
					if(!checkSizeIsExistedInList(sizeByCustomerCodeFrom, lstSizeByCustomerCodeTo))
					{
						sizeToAdd = new Size();
						// sizeToAdd.setCreatedate(new Date());
						sizeToAdd.setCustomer(cusDao.findById(customerCodeTo));
						sizeToAdd.setGarmentkind(garmentKindDao
								.findById(sizeByCustomerCodeFrom.getGarmentkind()
										.getGarmentkindcode()));
						sizeToAdd.setSizecode(sizeByCustomerCodeFrom.getSizecode());
						sizeToAdd.setSizename(sizeByCustomerCodeFrom.getSizename());
						sizeToAdd.setType(typeDao.findById(sizeByCustomerCodeFrom
								.getType().getTypecode()));
						sizeToAdd.setUser(userDao.findById(userName));
						dao.save(sizeToAdd);
					}
				}
			}
			log.debug("duplicateSize into database successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("duplicateSize with param 'customerCode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println("duplicateSize into database fail, method SizeModel(), class SizeService");
			throw e;
		}
	}
	
	/**
	 * This function is used to validate foreign key in Size
	 * @return false if validate failed, return true if valid
	 */
	public boolean validateForeignKey(String customer, String garmentKind, String type, String userName) {
		log.debug("in SizeService at isExistedWidthData method");
		try {
			if(cusDao.findById(customer) != null
					&& garmentKindDao.findById(garmentKind) != null
					&& typeDao.findById(type) != null
					&& userDao.findById(userName) != null)
			{
				return true;
			}	
			return false;
		} catch (NullPointerException ne) {
			log.error("in SizeService at isExistedWidthData method error: "
					+ ne.getMessage());
			throw ne;
		}
	}
	
	/**
	 * This function is used to check a size is existed in another list (to validate is existed in copy size)
	 * @return false if not existed, else return true
	 */
	public boolean checkSizeIsExistedInList(Size size, List<Size> lstSize) {
		log.debug("in SizeService at checkSizeIsExistedInList method");
		try {
			for(Size sizetmp : lstSize)
			{
				if(sizetmp.getSizename().equals(size.getSizename())
						&& sizetmp.getGarmentkind().getGarmentkindcode().equals(size.getGarmentkind().getGarmentkindcode())
						&& sizetmp.getType().getTypecode().equals(size.getType().getTypecode()))
				return true;
			}	
			return false;
		} catch (NullPointerException ne) {
			log.error("in SizeService at checkSizeIsExistedInList method error: "
					+ ne.getMessage());
			throw ne;
		}
	}
}
