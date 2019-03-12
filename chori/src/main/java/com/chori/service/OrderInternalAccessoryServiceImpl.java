package com.chori.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.AccessoryDao;
import com.chori.dao.AccessoryPriceDao;
import com.chori.dao.AccessorySupplierDao;
import com.chori.dao.FactoryDao;
import com.chori.dao.OrderInternalAccessoryDao;
import com.chori.dao.OrderinternalaccessorydetailDao;
import com.chori.dao.UserDao;
import com.chori.entity.Accessory;
import com.chori.entity.Orderinternalaccessory;
import com.chori.entity.Orderinternalaccessorydetail;
import com.chori.entity.OrderinternalaccessorydetailId;
import com.chori.model.AccessoryModel;
import com.chori.model.OrderInternalAccessoryDetailModel;
import com.chori.model.OrderInternalAccessoryModel;

@Repository("orderInternalAccessoryService")
public class OrderInternalAccessoryServiceImpl extends AbstractServiceImpl<Orderinternalaccessory, String>
		implements OrderInternalAccessoryService {
	private OrderInternalAccessoryDao dao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private AccessoryDao accDao;

	@Autowired
	private AccessoryPriceDao accPriceDao;

	@Autowired
	private OrderinternalaccessorydetailDao accDetailDao;

	@Autowired
	private AccessorySupplierDao accessorySupplierDao;

	@Autowired
	private FactoryDao factoryDao;

	@Autowired
	public OrderInternalAccessoryServiceImpl(AbstractDao<Orderinternalaccessory, String> abstractDao,
			OrderInternalAccessoryDao dao) {
		super(abstractDao);
		this.dao = dao;
	}

	/**
	 * This function is used to get all order internal accessory
	 * 
	 * @param OrderInternalAccessoryModel
	 * @return list order internal accessory
	 */
	public List<OrderInternalAccessoryModel> getAllOrderInternalAccessoryModels() {
		log.debug("in Type service list");
		try {
			List<Orderinternalaccessory> lstOrderinternalaccessories = dao.getAll();
			OrderInternalAccessoryModel internalAccessoryModel;
			List<OrderInternalAccessoryModel> lst = new ArrayList<OrderInternalAccessoryModel>();
			for (Orderinternalaccessory orderinternalaccessory : lstOrderinternalaccessories) {
				internalAccessoryModel = new OrderInternalAccessoryModel();
				internalAccessoryModel.setOrderSheetNo(orderinternalaccessory.getOrdersheetno());
				internalAccessoryModel
						.setAccsuplierCode(orderinternalaccessory.getAccessorysupplier().getAccsuppliercode());
				internalAccessoryModel.setAccsuplierName(orderinternalaccessory.getAccessorysupplier().getShortname());
				internalAccessoryModel.setFactoryCode(orderinternalaccessory.getFactory().getFactorycode());
				internalAccessoryModel.setFactoryName(orderinternalaccessory.getFactory().getShortname());
				internalAccessoryModel.setOrderDate(orderinternalaccessory.getOrderdate());
				internalAccessoryModel.setEstimatedevlDate(orderinternalaccessory.getEstimatedelvdate());
				internalAccessoryModel.setActualdevlDate(orderinternalaccessory.getActualdelvdate());
				internalAccessoryModel.setStatus(orderinternalaccessory.getStatus());
				internalAccessoryModel.setRemark(orderinternalaccessory.getRemark());
				internalAccessoryModel.setCreateDate(orderinternalaccessory.getCreatedate());
				internalAccessoryModel.setInvoiceNumber(orderinternalaccessory.getInvoicenumber());
				internalAccessoryModel.setCreator(orderinternalaccessory.getUser().getUsername());

				lst.add(internalAccessoryModel);
			}
			return lst;
		} catch (NullPointerException ne) {
			log.error("list service type err: " + ne.getMessage());
		}
		return null;
	}

	/**
	 * This function is used to get all internal accessory
	 * 
	 * @param AccessoryModel
	 * @return listinternal accessory
	 */
	public List<AccessoryModel> getAllInternalAccessoryBySupplier(String accessorySupplier) {
		log.debug("in Type service list");
		try {
			List<Accessory> lstAccessory = accDao.getAll();
			AccessoryModel accessoryModel;
			List<AccessoryModel> lstAccessoryModel = new ArrayList<AccessoryModel>();
			for (Accessory accessory : lstAccessory) {
				if (accessory.getKind().equals("Internal")&&accPriceDao.isExistAccPriceByAccessoryCodeAndSupplier(accessory.getAccessorycode(), accessorySupplier)) {
					accessoryModel = new AccessoryModel();
					accessoryModel.setAccessorycode(accessory.getAccessorycode());
					accessoryModel.setName(accessory.getName());
					lstAccessoryModel.add(accessoryModel);
				}
			}
			return lstAccessoryModel;
		} catch (NullPointerException ne) {
			log.error("list service type err: " + ne.getMessage());
		}
		return null;
	}
	
	

	// list order internal accessory detail by accessorycode
	public List<OrderInternalAccessoryDetailModel> getAllOrderInternalAccessoryDetailModelsByAccessoryCode(
			String accessoryCode) {
		log.debug("in Type service list");
		try {
			List<Orderinternalaccessorydetail> lstOrderinternalaccessoriesdetail = accDetailDao.getAll();
			OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel;
			List<OrderInternalAccessoryDetailModel> lst = new ArrayList<OrderInternalAccessoryDetailModel>();
			for (Orderinternalaccessorydetail orderinternalaccessorydetail : lstOrderinternalaccessoriesdetail) {
				orderInternalAccessoryDetailModel = new OrderInternalAccessoryDetailModel();
				orderInternalAccessoryDetailModel
						.setOrdersheetno(orderinternalaccessorydetail.getOrderinternalaccessory().getOrdersheetno());
				orderInternalAccessoryDetailModel
						.setAccessorycode(orderinternalaccessorydetail.getAccessory().getAccessorycode());
				orderInternalAccessoryDetailModel
						.setAccessoryname(orderinternalaccessorydetail.getAccessory().getName());
				orderInternalAccessoryDetailModel
						.setUnit(orderinternalaccessorydetail.getAccessory().getUnitByUnitcode().getUnitcode());
				orderInternalAccessoryDetailModel.setPrice(orderinternalaccessorydetail.getPrice());
				orderInternalAccessoryDetailModel.setOrderquantity(orderinternalaccessorydetail.getOrderquantity());
				orderInternalAccessoryDetailModel
						.setActualdelvquantity(orderinternalaccessorydetail.getActualdelvquantity());
				orderInternalAccessoryDetailModel.setCreatedate(orderinternalaccessorydetail.getCreatedate());
				orderInternalAccessoryDetailModel.setUser(orderinternalaccessorydetail.getUser().getUsername());
				lst.add(orderInternalAccessoryDetailModel);
			}
			return lst;
		} catch (NullPointerException ne) {
			log.error("list service type err: " + ne.getMessage());
		}
		return null;
	}

	/**
	 * This function find a OrderInternalAccessoryModel by OrderSheetNo.
	 * 
	 * @param OrderSheetNo
	 * @return OrderInternalAccessoryModel
	 */
	public OrderInternalAccessoryModel findOrderInternalAccessoryModelById(String OrderSheetNo) {
		log.info(String.format("findOrderInternalAccessoryModelById with param 'OrderSheetNo' in class: %s",
				getClass()));
		try {
			OrderInternalAccessoryModel orderInternalAccessoryModel = new OrderInternalAccessoryModel();
			Orderinternalaccessory orderInternalAccessory = dao.findById(OrderSheetNo);
			// field for NV
			orderInternalAccessoryModel.setOrderSheetNo(orderInternalAccessory.getOrdersheetno());
			orderInternalAccessoryModel
					.setAccsuplierCode(orderInternalAccessory.getAccessorysupplier().getAccsuppliercode());
			orderInternalAccessoryModel.setAccsuplierName(orderInternalAccessory.getAccessorysupplier().getShortname());
			orderInternalAccessoryModel.setFactoryCode(orderInternalAccessory.getFactory().getFactorycode());
			orderInternalAccessoryModel.setFactoryName(orderInternalAccessory.getFactory().getShortname());
			orderInternalAccessoryModel.setOrderDate(orderInternalAccessory.getOrderdate());
			orderInternalAccessoryModel.setEstimatedevlDate(orderInternalAccessory.getEstimatedelvdate());
			orderInternalAccessoryModel.setActualdevlDate(orderInternalAccessory.getActualdelvdate());
			orderInternalAccessoryModel.setStatus(orderInternalAccessory.getStatus());
			orderInternalAccessoryModel.setRemark(orderInternalAccessory.getRemark());
			orderInternalAccessoryModel.setInvoiceNumber(orderInternalAccessory.getInvoicenumber());
			orderInternalAccessoryModel.setCreateDate(orderInternalAccessory.getCreatedate());
			orderInternalAccessoryModel.setCreator(orderInternalAccessory.getUser().getUsername());

			log.debug("findOrderInternalAccessoryModelById successfully");
			return orderInternalAccessoryModel;
		} catch (Exception e) {
			log.error(String.format(
					"findOrderExternalAccessoryModelById with param 'OrderSheetNo' in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to find List Order Internal Accessory Detail Model By Order Sheet No
	 * @param OrderSheetNo
	 * @return
	 */
	public List<OrderInternalAccessoryDetailModel> findListOrderInternalAccessoryDetailModelByOrderSheetNo(String OrderSheetNo){
		log.info(String.format("findListOrderInternalAccessoryDetailModelByOrderSheetNo with param 'OrderSheetNo' in class: %s",
				getClass()));
		try {
			List<OrderInternalAccessoryDetailModel> lstResult= new ArrayList<OrderInternalAccessoryDetailModel>();
			OrderInternalAccessoryDetailModel tmp;
			
			List<Orderinternalaccessorydetail> lst = accDetailDao.getListOrderinternalaccessorydetailByOrderSheetNo(OrderSheetNo);
			for (Orderinternalaccessorydetail orderinternalaccessorydetail : lst) {
				tmp = new OrderInternalAccessoryDetailModel();
				
				tmp.setOrdersheetno(OrderSheetNo);
				tmp.setAccessorycode(orderinternalaccessorydetail.getAccessory().getAccessorycode());
				tmp.setAccessoryname(orderinternalaccessorydetail.getAccessory().getName());
				tmp.setUnit(orderinternalaccessorydetail.getAccessory().getUnitByUnitcode().getUnitcode());
				tmp.setImage(orderinternalaccessorydetail.getAccessory().getImgurl1());
				
				tmp.setUser(orderinternalaccessorydetail.getUser()==null?"":orderinternalaccessorydetail.getUser().getUsername());
				tmp.setOrderquantity(orderinternalaccessorydetail.getOrderquantity()== null ? 0 : orderinternalaccessorydetail.getOrderquantity());
				tmp.setActualdelvquantity(orderinternalaccessorydetail.getActualdelvquantity() == null ? 0 : orderinternalaccessorydetail.getActualdelvquantity());
				tmp.setUnitprice(orderinternalaccessorydetail.getUnitprice()==null ? 0 : orderinternalaccessorydetail.getUnitprice());
				tmp.setPrice(orderinternalaccessorydetail.getPrice()==null? 0 :orderinternalaccessorydetail.getPrice());
				tmp.setCreatedate(orderinternalaccessorydetail.getCreatedate());
				
				lstResult.add(tmp);
			}
			
			return lstResult;
		} catch (Exception e) {
			log.error(String.format(
					"findListOrderInternalAccessoryDetailModelByOrderSheetNo with param 'OrderSheetNo' in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit OrderInternalAccessory into database
	 * 
	 * @param orderInternalAccessoryModel
	 * @param userId
	 * @return true if edit successfully, false if have exception
	 */
	public boolean editOrderInternalAccessory(OrderInternalAccessoryModel orderInternalAccessoryModel) {
		try {
			Orderinternalaccessory accessoryOrderInternalAccessory = dao
					.findById(orderInternalAccessoryModel.getOrderSheetNo());

			accessoryOrderInternalAccessory.setOrdersheetno(orderInternalAccessoryModel.getOrderSheetNo());
			accessoryOrderInternalAccessory.setStatus(orderInternalAccessoryModel.getStatus());
			dao.update(accessoryOrderInternalAccessory);
			log.debug("editOrderInternalAccessory successfullly");
			return true;
		} catch (Exception e) {
			log.debug("editOrderInternalAccessory fail");
			System.err.println(String.format(
					"editOrderInternalAccessory with param 'orderInternalAccessoryModel' in class: %s has error: %s",
					getClass(), e.getMessage()));
			return false;
		}

	}

	/**
	 * This function is used to add new Order Internal Accessory
	 * 
	 * @param OrderInternalAccessoryModel
	 * @param creator
	 * @return
	 */
	@Override
	public boolean addOrderInternalAccessory(OrderInternalAccessoryModel orderInternalAccessoryModel,
			List<OrderInternalAccessoryDetailModel> listOrderInternalAccessoryDetailModel, String creator) {
		log.info(String.format("addOrderInternalAccessory in class: %s", getClass()));
		try {
			Orderinternalaccessory accessoryOrderinternalAccessory = new Orderinternalaccessory();
			accessoryOrderinternalAccessory.setOrdersheetno(orderInternalAccessoryModel.getOrderSheetNo());
			accessoryOrderinternalAccessory.setAccessorysupplier(
					accessorySupplierDao.findById(orderInternalAccessoryModel.getAccsuplierCode()));
			accessoryOrderinternalAccessory
					.setFactory(factoryDao.findById(orderInternalAccessoryModel.getFactoryCode()));
			accessoryOrderinternalAccessory.setUser(userDao.findById(creator));
			accessoryOrderinternalAccessory.setOrderdate(orderInternalAccessoryModel.getOrderDate());
			accessoryOrderinternalAccessory.setEstimatedelvdate(orderInternalAccessoryModel.getEstimatedevlDate());
			accessoryOrderinternalAccessory.setActualdelvdate(orderInternalAccessoryModel.getActualdevlDate());
			accessoryOrderinternalAccessory.setStatus(orderInternalAccessoryModel.getStatus());
			accessoryOrderinternalAccessory.setRemark(orderInternalAccessoryModel.getRemark());
			accessoryOrderinternalAccessory.setInvoicenumber(orderInternalAccessoryModel.getInvoiceNumber());
			accessoryOrderinternalAccessory.setCreatedate(Calendar.getInstance().getTime());
			
			
			dao.save(accessoryOrderinternalAccessory);
			log.debug("addOrderInternalAccessory successfullly");

			Orderinternalaccessorydetail orderinternalaccessorydetail;

			for (OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel : listOrderInternalAccessoryDetailModel) {
				orderinternalaccessorydetail = new Orderinternalaccessorydetail();
				orderinternalaccessorydetail.setOrderinternalaccessory(accessoryOrderinternalAccessory);
				orderinternalaccessorydetail
						.setAccessory(accDao.findById(orderInternalAccessoryDetailModel.getAccessorycode()));
				orderinternalaccessorydetail.setId(new OrderinternalaccessorydetailId(
						orderinternalaccessorydetail.getAccessory().getAccessorycode(),
						accessoryOrderinternalAccessory.getOrdersheetno()));
				
					orderinternalaccessorydetail.setOrderquantity(orderInternalAccessoryDetailModel.getOrderquantity());
				
					orderinternalaccessorydetail
						.setActualdelvquantity(orderInternalAccessoryDetailModel.getActualdelvquantity());
//				else
//					orderinternalaccessorydetail
//					.setActualdelvquantity(null);
				orderinternalaccessorydetail.setPrice(orderInternalAccessoryDetailModel.getPrice());
				orderinternalaccessorydetail.setCreatedate(Calendar.getInstance().getTime());
				
					orderinternalaccessorydetail.setUnitprice(orderInternalAccessoryDetailModel.getUnitprice());
				orderinternalaccessorydetail.setUser(userDao.findById(creator));

				accDetailDao.save(orderinternalaccessorydetail);
			}
			log.debug("save fabricinformation detail successfully");

			return true;
		} catch (Exception e) {
			log.debug("addOrderInternalAccessory fail");
			System.err.println(String.format(
					"addOrderExternalAccessory with param 'orderInternalAccessoryModel' in class: %s has error: %s", getClass(),
					e.getMessage()));
			return false;
		}
	}

	// public Double getUnitPrice(String accessoryCode , Date orderDate){
	// Double unitPrice ;
	// // //Accessoryprice accprice = accPriceDao.findById(id)(accessoryCode);
	// // Accessory acc = accDao.findById(accessoryCode);
	// // acc.getAccessoryprices().ge;
	// List<Accessoryprice> lstAccessoryPrice = accPriceDao.getAll();
	// AccessoryPriceModel accessorypriceMo;
	// List<AccessoryPriceModel> lstaccessorypriceModel = new
	// ArrayList<AccessoryPriceModel>();
	// for (Accessoryprice acc : lstAccessoryPrice) {
	// accessorypriceMo = new AccessoryPriceModel();
	// if(accessorypriceMo.getAccessorycode().equalsIgnoreCase(accessoryCode)){
	// if(accessorypriceMo.getFromdate().before(orderDate) &&
	// accessorypriceMo.getTodate().after(orderDate)){
	// //unitPrice += accessoryCode.get
	// }
	// }
	// //
	// accessorypriceMo.setAccessorycode(acc.getAccessory().getAccessorycode());
	// // accessorypriceMo.setUnitpriceperunit(acc.getUnitpriceperunit());
	// // accessorypriceMo.setCurrencycode(acc.getCurrency().getCurrencycode());
	// // accessorypriceMo.setFromdate(acc.getFromdate());
	// //
	// accessorypriceMo.setTodate(acc.getTodate()==null?null:acc.getTodate());
	//
	// lstaccessorypriceModel.add(accessorypriceMo);
	// }
	// log.debug("getAllAccessoryPrice successful");
	// //return lstaccessorypriceModel;
	// return null;
	//
	// }

	/**
	 * This function is used to update order internal accessory and it's detail
	 * @param orderInternalAccessoryModel
	 * @param listOrderInternalAccessoryDetailModel
	 * @param creator
	 * @return
	 */
	public boolean editOrderInternalAccessory(OrderInternalAccessoryModel orderInternalAccessoryModel,
			List<OrderInternalAccessoryDetailModel> listOrderInternalAccessoryDetailModel, String creator) {
		log.info(String.format("addOrderInternalAccessory in class: %s", getClass()));
		try {
			Orderinternalaccessory accessoryOrderinternalAccessory = dao.findById(orderInternalAccessoryModel.getOrderSheetNo());
			accessoryOrderinternalAccessory.setOrdersheetno(orderInternalAccessoryModel.getOrderSheetNo());
			accessoryOrderinternalAccessory.setAccessorysupplier(
					accessorySupplierDao.findById(orderInternalAccessoryModel.getAccsuplierCode()));
			accessoryOrderinternalAccessory
					.setFactory(factoryDao.findById(orderInternalAccessoryModel.getFactoryCode()));
//			accessoryOrderinternalAccessory.setUser(userDao.findById(creator));
			accessoryOrderinternalAccessory.setOrderdate(orderInternalAccessoryModel.getOrderDate());
			accessoryOrderinternalAccessory.setEstimatedelvdate(orderInternalAccessoryModel.getEstimatedevlDate());
			accessoryOrderinternalAccessory.setActualdelvdate(orderInternalAccessoryModel.getActualdevlDate());
			accessoryOrderinternalAccessory.setStatus(orderInternalAccessoryModel.getStatus());
			accessoryOrderinternalAccessory.setRemark(orderInternalAccessoryModel.getRemark());
			accessoryOrderinternalAccessory.setInvoicenumber(orderInternalAccessoryModel.getInvoiceNumber());
			accessoryOrderinternalAccessory.setCreatedate(Calendar.getInstance().getTime());
			
			//3 list 
			List<OrderInternalAccessoryDetailModel> lstAdd= new ArrayList<OrderInternalAccessoryDetailModel>();
			List<OrderInternalAccessoryDetailModel> lstUpdate= new ArrayList<OrderInternalAccessoryDetailModel>();
			List<OrderInternalAccessoryDetailModel> lstDelete= new ArrayList<OrderInternalAccessoryDetailModel>();
			//biáº¿n cá»�
			boolean isExisted= false;
			
			//láº¥y ra list order internal acc detail
			List<OrderInternalAccessoryDetailModel> oldList= findListOrderInternalAccessoryDetailModelByOrderSheetNo(orderInternalAccessoryModel.getOrderSheetNo());
			//láº·p qua list cÅ©, láº·p qua list má»›i
			for (OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel : oldList) {
				for (OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel1 : listOrderInternalAccessoryDetailModel) {
					if(orderInternalAccessoryDetailModel.getAccessorycode().equals(orderInternalAccessoryDetailModel1.getAccessorycode())){
						isExisted = true;
						break;
					}
				}
				
//				//náº¿u p.tá»­ cá»§a list cÅ© tá»“n táº¡i trong list má»›i thÃ¬ cho vÃ o list update
//				if(isExisted){
//					lstUpdate.add(orderInternalAccessoryDetailModel);
//				}
				//náº¿u p.tá»­ cá»§a list cÅ© ko tá»“n táº¡i trong list má»›i thÃ¬ cho vÃ o list delete
				if(!isExisted){
					lstDelete.add(orderInternalAccessoryDetailModel);
				}
				isExisted = false;
			}
			
			isExisted = false;
			//láº·p qua list má»›i, láº·p qua list cÅ©
			for (OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel1 : listOrderInternalAccessoryDetailModel) {
				for (OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel : oldList) {
					if(orderInternalAccessoryDetailModel.getAccessorycode().equals(orderInternalAccessoryDetailModel1.getAccessorycode())){
						isExisted = true;
						break;
					}
				}
				
				//náº¿u p.tá»­ cá»§a list má»›i ko tá»“n táº¡i trong list cÅ© thÃ¬ cho vÃ o list add
				if(!isExisted){
					lstAdd.add(orderInternalAccessoryDetailModel1);
				}
				
				//náº¿u p.tá»­ cá»§a list cÅ© tá»“n táº¡i trong list má»›i thÃ¬ cho vÃ o list update
				if(isExisted){
					lstUpdate.add(orderInternalAccessoryDetailModel1);
				}
				
				isExisted = false;
			}
			
			Orderinternalaccessorydetail orderinternalaccessorydetail;
			
			//tiáº¿n hÃ nh add má»›i detail
			for (OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel : lstAdd) {
				orderinternalaccessorydetail = new Orderinternalaccessorydetail();
				orderinternalaccessorydetail.setOrderinternalaccessory(dao.findById(orderInternalAccessoryModel.getOrderSheetNo()));
				orderinternalaccessorydetail
						.setAccessory(accDao.findById(orderInternalAccessoryDetailModel.getAccessorycode()));
				orderinternalaccessorydetail.setId(new OrderinternalaccessorydetailId(
						orderinternalaccessorydetail.getAccessory().getAccessorycode(),
						dao.findById(orderInternalAccessoryModel.getOrderSheetNo()).getOrdersheetno()));
			
					orderinternalaccessorydetail.setOrderquantity(orderInternalAccessoryDetailModel.getOrderquantity());
				
					orderinternalaccessorydetail
						.setActualdelvquantity(orderInternalAccessoryDetailModel.getActualdelvquantity());
				orderinternalaccessorydetail.setPrice(orderInternalAccessoryDetailModel.getPrice());
				orderinternalaccessorydetail.setCreatedate(Calendar.getInstance().getTime());
				
					orderinternalaccessorydetail.setUnitprice(orderInternalAccessoryDetailModel.getUnitprice());
				orderinternalaccessorydetail.setUser(userDao.findById(creator));

				accDetailDao.save(orderinternalaccessorydetail);
			}
			
			//tiáº¿n hÃ nh xÃ³a detail
			for (OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel : lstDelete) {
				accDetailDao.delete(accDetailDao.findById(new OrderinternalaccessorydetailId(orderInternalAccessoryDetailModel.getAccessorycode(), orderInternalAccessoryModel.getOrderSheetNo())));
			}
			
			//tiáº¿n hÃ nh update detail
			for (OrderInternalAccessoryDetailModel orderInternalAccessoryDetailModel : lstUpdate) {
				orderinternalaccessorydetail = accDetailDao.findById(new OrderinternalaccessorydetailId(orderInternalAccessoryDetailModel.getAccessorycode(), orderInternalAccessoryModel.getOrderSheetNo()));
				orderinternalaccessorydetail.setOrderinternalaccessory(dao.findById(orderInternalAccessoryModel.getOrderSheetNo()));
				orderinternalaccessorydetail
						.setAccessory(accDao.findById(orderInternalAccessoryDetailModel.getAccessorycode()));
//				orderinternalaccessorydetail.setId(new OrderinternalaccessorydetailId(
//						orderinternalaccessorydetail.getAccessory().getAccessorycode(),
//						dao.findById(orderInternalAccessoryModel.getOrderSheetNo()).getOrdersheetno()));
				
				
					orderinternalaccessorydetail.setOrderquantity(orderInternalAccessoryDetailModel.getOrderquantity());
				orderinternalaccessorydetail.setPrice(orderInternalAccessoryDetailModel.getPrice());
			
					orderinternalaccessorydetail.setUnitprice(orderInternalAccessoryDetailModel.getUnitprice());
				
					orderinternalaccessorydetail
					.setActualdelvquantity(orderInternalAccessoryDetailModel.getActualdelvquantity()==null ? 0 : orderInternalAccessoryDetailModel.getActualdelvquantity());

				accDetailDao.update(orderinternalaccessorydetail);
			}
			
			//tiáº¿n hÃ nh update 
			dao.update(accessoryOrderinternalAccessory);
			log.debug("edit Order Internal Accessory successfullly");

			log.debug("save Order Internal Accessory detail successfully");

			return true;
		} catch (Exception e) {
			log.debug("addOrderInternalAccessory fail");
			System.err.println(String.format(
					"addOrderExternalAccessory with param 'orderInternalAccessoryModel' in class: %s has error: %s", getClass(),
					e.getMessage()));
			return false;
		}
	}
	
	/**
	 * This function is used to cancel Order Internal Accessory
	 * @param orderInternalAccessoryModel
	 * @param editor
	 * @return
	 */
	public boolean cancelOrderInternalAccessory(OrderInternalAccessoryModel orderInternalAccessoryModel,
			String editor) {
		log.info(String.format("cancelOrderInternalAccessory in class: %s", getClass()));
		try {
			Orderinternalaccessory accessoryOrderinternalAccessory = dao.findById(orderInternalAccessoryModel.getOrderSheetNo());
			accessoryOrderinternalAccessory.setStatus("Cancel");
			dao.update(accessoryOrderinternalAccessory);
			log.debug("cancel Order Internal Accessory successfully");
			
			return true;
		} catch (Exception e) {
			log.debug("cancelOrderInternalAccessory fail");
			System.err.println(String.format(
					"cancelOrderInternalAccessory in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}
}
