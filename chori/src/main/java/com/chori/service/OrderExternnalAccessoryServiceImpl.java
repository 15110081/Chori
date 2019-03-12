package com.chori.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.AccessoryDao;
import com.chori.dao.AccessoryPriceDao;
import com.chori.dao.AccessorySupplierDao;
import com.chori.dao.ExternalAccessoryStockDao;
import com.chori.dao.FactoryDao;
import com.chori.dao.OrderExternalAccessoryDao;
import com.chori.dao.PIDao;
import com.chori.dao.PiassignexternalaccessoryDao;
import com.chori.dao.UserDao;
import com.chori.entity.Externalaccessorystock;
import com.chori.entity.Orderexternalaccessory;
import com.chori.entity.Piassignexternalaccessory;
import com.chori.model.OrderExternalAccessoryModel;

@Repository("orderExternalAccessoryService")
public class OrderExternnalAccessoryServiceImpl extends AbstractServiceImpl<Orderexternalaccessory, String>
		implements OrderExternalAccessoryService {
	private OrderExternalAccessoryDao dao;

	@Autowired
	private UserDao userDao;
	@Autowired
	private PIDao piDao;
	@Autowired
	private AccessorySupplierDao accessorySupplierDao;
	@Autowired
	private FactoryDao factoryDao;
	@Autowired
	private AccessoryDao accessoryDao;
	@Autowired
	private AccessoryPriceDao accessoryPriceDao;
	@Autowired
	private ExternalAccessoryStockDao externalAccessoryStockDao;
	@Autowired
	private ExternalAccessoryStockService externalAccessoryStockService;
	@Autowired
	private PiassignexternalaccessoryDao piassignexternalaccessoryDao;

	@Autowired
	public OrderExternnalAccessoryServiceImpl(AbstractDao<Orderexternalaccessory, String> abstractDao,
			OrderExternalAccessoryDao dao) {
		super(abstractDao);
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chori.service.OrderExternalAccessoryService#addOrderExternalAccessory
	 * (com.chori.model.OrderExternalAccessoryModel, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean addOrderExternalAccessory(OrderExternalAccessoryModel orderExternalAccessoryModel, String creator) {
		try {
			// calculate shortageQty (to add to stock if order greater)
			Float shortageQty = externalAccessoryStockService.calculateShortageQuantity(
					orderExternalAccessoryModel.getLotNumber(), orderExternalAccessoryModel.getAccessoryCode());

			// save order
			Orderexternalaccessory accessoryOrderexternalaccessory = new Orderexternalaccessory();
			accessoryOrderexternalaccessory.setOrdersheetno(orderExternalAccessoryModel.getOrderSheetNo());
			accessoryOrderexternalaccessory.setAccessorysupplier(
					accessorySupplierDao.findById(orderExternalAccessoryModel.getAccsuplierCode()));
			// accessoryOrderexternalaccessory.setPi(piDao.findById(orderExternalAccessoryModel.getLotNumber()));
			accessoryOrderexternalaccessory
					.setFactory(factoryDao.findById(orderExternalAccessoryModel.getFactoryCode()));
			accessoryOrderexternalaccessory
					.setAccessory(accessoryDao.findById(orderExternalAccessoryModel.getAccessoryCode()));
			accessoryOrderexternalaccessory.setUser(userDao.findById(creator));
			accessoryOrderexternalaccessory.setActualdelvquantity(orderExternalAccessoryModel.getActualdevlQuantity());
			accessoryOrderexternalaccessory.setOrderdate(orderExternalAccessoryModel.getOrderDate());
			accessoryOrderexternalaccessory.setEstimatedelvdate(orderExternalAccessoryModel.getEstimatedevlDate());
			accessoryOrderexternalaccessory.setActualdelvdate(orderExternalAccessoryModel.getActualdevlDate());
			accessoryOrderexternalaccessory.setStatus(orderExternalAccessoryModel.getStatus());
			accessoryOrderexternalaccessory.setRemark(orderExternalAccessoryModel.getRemark());
			accessoryOrderexternalaccessory.setPrice(orderExternalAccessoryModel.getPrice());
			accessoryOrderexternalaccessory.setPaymentstatus(orderExternalAccessoryModel.getPaymentStatus());
			accessoryOrderexternalaccessory.setCreatedate(new Date());
			accessoryOrderexternalaccessory.setOrderquantity(orderExternalAccessoryModel.getOrderQuantity());

			dao.save(accessoryOrderexternalaccessory);

			// save to stock if order quantity greater than necessary quantity
			if (orderExternalAccessoryModel.getOrderQuantity() > shortageQty) {
				Externalaccessorystock externalaccessorystock = new Externalaccessorystock();
				externalaccessorystock
						.setOrderexternalaccessory(dao.findById(orderExternalAccessoryModel.getOrderSheetNo()));
				Float availableQty = orderExternalAccessoryModel.getOrderQuantity() - shortageQty;
				externalaccessorystock.setAvailableqty(availableQty);
				externalAccessoryStockDao.save(externalaccessorystock);
			}

			log.debug("addOrderExternalAccessory successfullly");
			return true;
		} catch (Exception e) {
			log.debug("addAccessoryGroup fail");
			System.err.println(String.format(
					"addOrderExternalAccessory with param 'accessoryGroupModel' in class: %s has error: %s", getClass(),
					e.getMessage()));
			return false;
		}

	}

	@Override
	public boolean updateOrderQtyOfAPiAssignExternalAccessory(String accessoryCode, String lotNumber,
			Integer orderQty,String orderSheetNo) {
		log.info(String.format("updateOrderQtyOfAPiAssignExternalAccessory in class: %s", getClass()));
		try {
			List<Piassignexternalaccessory> lstPiassignexternalaccessory = piassignexternalaccessoryDao.getAll();
			Float shortageQty = externalAccessoryStockService.calculateShortageQuantity(lotNumber, accessoryCode);
			//flag to make sure 1 loop is run
			Integer flag =0;
			if(flag==0)
			{
				for (Piassignexternalaccessory piassignexternalaccessory : lstPiassignexternalaccessory) {
					//if piassignexternalaccessory.getOrderexternalaccessory() == null
					if (piassignexternalaccessory.getAccessory().getAccessorycode().equals(accessoryCode)
							&& piassignexternalaccessory.getPi().getLotnumber().equals(lotNumber)) {
						// check piassignexternalaccessory.getOrderexternalaccessory()
						// == null to prevent change orderQty when this assignment has
						// ordered already
						if(piassignexternalaccessory.getOrderexternalaccessory() == null)
						{
							flag++;
							piassignexternalaccessory.setOrderexternalaccessory(dao.findById(orderSheetNo));
							if (orderQty > shortageQty) {
								piassignexternalaccessory.setOrderqty(shortageQty);
							} else {
								piassignexternalaccessory.setOrderqty((float) orderQty);
							}
							piassignexternalaccessoryDao.update(piassignexternalaccessory);
						}	
					}
				}			
			}
			if(flag==0)
			{
				//if piassignexternalaccessory.getOrderexternalaccessory() != null
				for (Piassignexternalaccessory piassignexternalaccessory : lstPiassignexternalaccessory) {
					if (piassignexternalaccessory.getAccessory().getAccessorycode().equals(accessoryCode)
							&& piassignexternalaccessory.getPi().getLotnumber().equals(lotNumber)) {
						if(piassignexternalaccessory.getOrderexternalaccessory() != null)
						{
							piassignexternalaccessory.setOrderexternalaccessory(dao.findById(orderSheetNo));
							if (orderQty > shortageQty) {
								piassignexternalaccessory.setOrderqty(piassignexternalaccessory.getOrderqty() + shortageQty);
								//if last element of list, then update in stock
//								if(lstPiassignexternalaccessory.get(lstPiassignexternalaccessory.size() - 1)
//										.getPiassignexternalaccessorycode()
//										== (piassignexternalaccessory.getPiassignexternalaccessorycode()))
//								{
//									updateStockIfUserOrderMore(orderQty - shortageQty,orderSheetNo);
//								}
							} else {
								piassignexternalaccessory.setOrderqty(piassignexternalaccessory.getOrderqty() + (float) orderQty);
							}
							piassignexternalaccessoryDao.update(piassignexternalaccessory);
						}
					}
				}
				flag++;
			}
			
			return true;
		} catch (Exception e) {
			log.error(String.format("updateOrderQtyOfAPiAssignExternalAccessory in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	//add 1 row to stock
	public boolean updateStockIfUserOrderMore(Float availableQty, String orderSheetNo) {
		log.info(String.format("updateStockIfUserOrderMore in class: %s", getClass()));
		try {		
				Externalaccessorystock externalaccessorystock;
				externalaccessorystock = new Externalaccessorystock();
				externalaccessorystock.setAvailableqty(availableQty);
				externalaccessorystock.setOrderexternalaccessory(dao.findById(orderSheetNo));
				externalAccessoryStockDao.save(externalaccessorystock);
				return true;
		} catch (Exception e) {
			log.error(String.format("updateOrderQtyOfAPiAssignExternalAccessory in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}
	
	@Override
	public List<OrderExternalAccessoryModel> getAllOrderExternalAccessoryModels() {
		log.debug("in Type service list");
		try {
			List<Orderexternalaccessory> lstOrderexternalaccessories = dao.getAll();
			OrderExternalAccessoryModel externalAccessoryModel;
			List<OrderExternalAccessoryModel> lst = new ArrayList<OrderExternalAccessoryModel>();
			for (Orderexternalaccessory orderexternalaccessory : lstOrderexternalaccessories) {
				externalAccessoryModel = new OrderExternalAccessoryModel();
				externalAccessoryModel.setOrderSheetNo(orderexternalaccessory.getOrdersheetno());
				externalAccessoryModel.setAccessoryCode(orderexternalaccessory.getAccessory().getAccessorycode());
				externalAccessoryModel
						.setAccsuplierCode(orderexternalaccessory.getAccessorysupplier().getAccsuppliercode());
				externalAccessoryModel.setFactoryCode(orderexternalaccessory.getFactory().getFactorycode());
				externalAccessoryModel.setOrderQuantity(orderexternalaccessory.getOrderquantity());
				externalAccessoryModel.setActualdevlQuantity(orderexternalaccessory.getActualdelvquantity());
				externalAccessoryModel.setOrderDate(orderexternalaccessory.getOrderdate());
				externalAccessoryModel.setEstimatedevlDate(orderexternalaccessory.getEstimatedelvdate());
				externalAccessoryModel.setActualdevlDate(orderexternalaccessory.getActualdelvdate());
				externalAccessoryModel.setStatus(orderexternalaccessory.getStatus());
				externalAccessoryModel.setRemark(
						orderexternalaccessory.getRemark() == null ? "" : orderexternalaccessory.getRemark());
				externalAccessoryModel.setCreateDate(orderexternalaccessory.getCreatedate());
				externalAccessoryModel.setFactoryName(orderexternalaccessory.getFactory().getShortname());
				externalAccessoryModel.setAccsuplierName(orderexternalaccessory.getAccessorysupplier().getShortname());
				externalAccessoryModel.setAccessoryName(orderexternalaccessory.getAccessory().getName());
				externalAccessoryModel.setCreator(orderexternalaccessory.getUser().getUsername());
				externalAccessoryModel.setPrice(orderexternalaccessory.getPrice());
				externalAccessoryModel.setPaymentStatus(orderexternalaccessory.getPaymentstatus());
				lst.add(externalAccessoryModel);
			}
			return lst;
		} catch (NullPointerException ne) {
			log.error("list service type err: " + ne.getMessage());
			throw ne;
		}
		// return null;
	}

	@Override
	public boolean isOrderSheetNoExisted(String ordsheetno) {
		if (null == dao.findById(ordsheetno))
			return false;
		return true;
	}

	@Override
	public OrderExternalAccessoryModel findOrderExternalAccessoryModelById(String OrderSheetNo) {
		log.info(String.format("findOrderExternalAccessoryModelById with param 'OrderSheetNo' in class: %s",
				getClass()));
		try {
			OrderExternalAccessoryModel externalAccessoryModel = new OrderExternalAccessoryModel();
			Orderexternalaccessory orderexternalaccessory = dao.findById(OrderSheetNo);

			// field for NV
			externalAccessoryModel.setOrderSheetNo(orderexternalaccessory.getOrdersheetno());
			externalAccessoryModel.setAccessoryCode(orderexternalaccessory.getAccessory().getAccessorycode());
			externalAccessoryModel.setAccessoryName(orderexternalaccessory.getAccessory().getName());
			externalAccessoryModel
					.setAccsuplierCode(orderexternalaccessory.getAccessorysupplier().getAccsuppliercode());
			externalAccessoryModel.setFactoryCode(orderexternalaccessory.getFactory().getFactorycode());
			externalAccessoryModel.setOrderQuantity(orderexternalaccessory.getOrderquantity());
			externalAccessoryModel.setActualdevlQuantity(orderexternalaccessory.getActualdelvquantity());
			externalAccessoryModel.setOrderDate(orderexternalaccessory.getOrderdate());
			externalAccessoryModel.setEstimatedevlDate(orderexternalaccessory.getEstimatedelvdate());
			externalAccessoryModel.setActualdevlDate(orderexternalaccessory.getActualdelvdate());
			externalAccessoryModel.setStatus(orderexternalaccessory.getStatus());
			externalAccessoryModel.setRemark(orderexternalaccessory.getRemark());
			externalAccessoryModel.setCreateDate(orderexternalaccessory.getCreatedate());
			externalAccessoryModel.setFactoryName(orderexternalaccessory.getFactory().getShortname());
			externalAccessoryModel.setAccsuplierName(orderexternalaccessory.getAccessorysupplier().getShortname());
			externalAccessoryModel.setAccessoryName(orderexternalaccessory.getAccessory().getName());
			externalAccessoryModel.setPrice(orderexternalaccessory.getPrice());
			externalAccessoryModel.setPaymentStatus(orderexternalaccessory.getPaymentstatus());

			externalAccessoryModel.setCreator(orderexternalaccessory.getUser().getUsername());

			log.debug("findOrderExternalAccessoryModelById successfully");
			return externalAccessoryModel;
		} catch (Exception e) {
			log.error(String.format(
					"findOrderExternalAccessoryModelById with param 'OrderSheetNo' in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	@Override
	public boolean editOrderExternalAccessory(OrderExternalAccessoryModel orderExternalAccessoryModel) {
		try {
			Orderexternalaccessory accessoryOrderexternalaccessory = dao
					.findById(orderExternalAccessoryModel.getOrderSheetNo());

			accessoryOrderexternalaccessory.setOrdersheetno(orderExternalAccessoryModel.getOrderSheetNo());
			accessoryOrderexternalaccessory.setStatus(orderExternalAccessoryModel.getStatus());
			accessoryOrderexternalaccessory.setPaymentstatus(orderExternalAccessoryModel.getPaymentStatus());
			accessoryOrderexternalaccessory.setOrderquantity(orderExternalAccessoryModel.getOrderQuantity());
			accessoryOrderexternalaccessory.setActualdelvquantity(orderExternalAccessoryModel.getActualdevlQuantity());
			accessoryOrderexternalaccessory.setOrderdate(orderExternalAccessoryModel.getOrderDate());
			accessoryOrderexternalaccessory.setActualdelvdate(orderExternalAccessoryModel.getActualdevlDate());
			accessoryOrderexternalaccessory.setEstimatedelvdate(orderExternalAccessoryModel.getEstimatedevlDate());
			accessoryOrderexternalaccessory.setRemark(orderExternalAccessoryModel.getRemark());

			dao.update(accessoryOrderexternalaccessory);
			log.debug("editOrderExternalAccessory successfullly");
			return true;
		} catch (Exception e) {
			log.debug("editOrderExternalAccessory fail");
			System.err.println(String.format(
					"editOrderExternalAccessory with param 'orderExternalAccessoryModel' in class: %s has error: %s",
					getClass(), e.getMessage()));
			return false;
		}

	}

	@Override
	public float getActualAssignQuantity(String accessoryCode, String lotNumber, String orderSheetNo) {
		return dao.getActualAssignQuantity(accessoryCode, lotNumber, orderSheetNo);
	}

	@Override
	public void UpdateorderQuantity(String orderSheetNo, float orderQuantity) {
		dao.UpdateOrderQuantity(orderSheetNo, orderQuantity);
	}

}