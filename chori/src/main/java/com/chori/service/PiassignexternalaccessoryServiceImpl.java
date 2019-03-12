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
import com.chori.dao.ColorDao;
import com.chori.dao.FpiDao;
import com.chori.dao.FpidetailDao;
import com.chori.dao.GarmentstyleDao;
import com.chori.dao.GarmentstyleaccessorydetailDao;
import com.chori.dao.PIDao;
import com.chori.dao.PiassignexternalaccessoryDao;
import com.chori.dao.PigriddetailDao;
import com.chori.dao.RfpiDao;
import com.chori.dao.RfpidetailDao;
import com.chori.dao.UserDao;
import com.chori.entity.Fpi;
import com.chori.entity.Garmentstyleaccessorydetail;
import com.chori.entity.Pi;
import com.chori.entity.Piassignexternalaccessory;
import com.chori.entity.Pigriddetail;
import com.chori.entity.Rfpi;
import com.chori.model.ColorForPiModel;
import com.chori.model.PiassignexternalaccessoryModel;
import com.chori.model.TotalAccForAssignExternalModel;

@Service("piassignexternalaccessoryService")
public class PiassignexternalaccessoryServiceImpl extends
		AbstractServiceImpl<Piassignexternalaccessory, Integer> implements
		PiassignexternalaccessoryService {
	private PiassignexternalaccessoryDao dao;

	@Autowired
	private PIDao piDao;

	@Autowired
	private PigriddetailDao pigriddetailDao;

	@Autowired
	private GarmentstyleaccessorydetailDao garmentstyleaccessorydetailDao;

	@Autowired
	private AccessoryDao accessoryDao;

	@Autowired
	private ColorDao colorDao;

	@Autowired
	private GarmentstyleDao garmentStyleDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FpidetailDao fpiDetailDao;
	
	@Autowired
	private FpiDao fpiDao;
	
	@Autowired
	private RfpiDao rfpiDao;
	
	@Autowired
	private RfpidetailDao rfpidetailDao;

	@Autowired
	public PiassignexternalaccessoryServiceImpl(
			@Qualifier("piassignexternalaccessoryDao") AbstractDao<Piassignexternalaccessory, Integer> abstractDao) {
		super(abstractDao);
		this.dao = (PiassignexternalaccessoryDao) abstractDao;
	}

	// Đưa list lstPiassignexternalaccessoryModel ra ngoài để lúc nhấn assign để
	// add thì id của từng PiassignexternalaccessoryModel trùng
	List<PiassignexternalaccessoryModel> lstPiassignexternalaccessoryModel = new ArrayList<PiassignexternalaccessoryModel>();

	/**
	 * This function is used to get List PiassignexternalaccessoryModel When
	 * Press Assign (data not in database now) Hàm xử lý lúc hiện dialog, khởi
	 * tạo các Piassignexternalaccessory lần đầu, chưa lưu vào db
	 */
	public List<PiassignexternalaccessoryModel> getListPiassignexternalaccessoryModelWhenPressAssign(
			String lotnumber) {
		log.info(String.format("getAllGarmentstyle in class: %s", getClass()));
		try {
			List<Garmentstyleaccessorydetail> lstGarmentstyleaccessorydetail;
			PiassignexternalaccessoryModel piassignexternalaccessoryModel;
			// List<PiassignexternalaccessoryModel>
			// lstPiassignexternalaccessoryModel= new
			// ArrayList<PiassignexternalaccessoryModel>();
			lstPiassignexternalaccessoryModel.clear();

			// lấy piGridDetail qua lot no
			int pigridCode = piDao.findById(lotnumber).getPigrid()
					.getPigridcode();
			List<Pigriddetail> lstGridDetail = pigriddetailDao
					.getListPigriddetailByPigridcode(pigridCode);

			// vì là lần đầu add nên lưu các id của
			// PiassignexternalaccessoryModel theo thứ tự tăng dần, để lát biết
			// nào add nào ko
			int i = 1;
			
			//mãng xử lý display name
			String[] splitGarmentStyleCode;

			// lặp qua grid detail
			for (Pigriddetail pigriddetail : lstGridDetail) {
				// lấy ra các Garmentstyleaccessorydetail qua garmentStyleCode,
				// size
				lstGarmentstyleaccessorydetail = garmentstyleaccessorydetailDao
						.getListGarmentstyleaccessorydetailByGarmentStyleNameAndSize(
								pigriddetail.getGarmentstyle()
										.getGarmentstylecode(), pigriddetail
										.getSize().getSizecode());
				for (Garmentstyleaccessorydetail garmentstyleaccessorydetail : lstGarmentstyleaccessorydetail) {
					// nếu accessory là External thì mới xét tiếp
					if (garmentstyleaccessorydetail.getAccessory().getKind()
							.equals("External")) {
						// tạo ra pi assign external accessory
						piassignexternalaccessoryModel = new PiassignexternalaccessoryModel();
						// set id của từng PiassignexternalaccessoryModel tại
						// đây
						piassignexternalaccessoryModel
								.setPiassignexternalaccessorycode(i++);

						piassignexternalaccessoryModel
								.setAccessoryCode(garmentstyleaccessorydetail
										.getAccessory().getAccessorycode());
						piassignexternalaccessoryModel
								.setAccessoryName(garmentstyleaccessorydetail
										.getAccessory().getName());
						piassignexternalaccessoryModel
								.setColorcode(pigriddetail.getColor()
										.getColorcode());
						piassignexternalaccessoryModel
								.setColorName(pigriddetail.getColor()
										.getDescription());
						piassignexternalaccessoryModel.setSizecode(pigriddetail
								.getSize().getSizecode());
						piassignexternalaccessoryModel.setSizename(pigriddetail
								.getSize().getSizename());
						piassignexternalaccessoryModel
								.setGarmentstylecode(pigriddetail
										.getGarmentstyle()
										.getGarmentstylecode());
						piassignexternalaccessoryModel
								.setGarmentstyleName(pigriddetail
										.getGarmentstyle().getDescription());
						piassignexternalaccessoryModel.setLotnumber(lotnumber);
						piassignexternalaccessoryModel
								.setPigriddetail(pigriddetail.getPigriddetail());
						piassignexternalaccessoryModel.setCreator("admin");
						piassignexternalaccessoryModel
								.setCreatedate(new Date());
						piassignexternalaccessoryModel
								.setEstimateqty(pigriddetail.getFavalue()
										* garmentstyleaccessorydetail
												.getUsedvalue());
						piassignexternalaccessoryModel
								.setMode(garmentstyleaccessorydetail
										.getAccessory().getMode());
						piassignexternalaccessoryModel
								.setImgurl(garmentstyleaccessorydetail
										.getAccessory().getImgurl1());

						piassignexternalaccessoryModel
								.setGarmentstyleaccessorydetailcode(garmentstyleaccessorydetail
										.getGarmentstyleaccessorydetailcode());
						//set garment style display name
						splitGarmentStyleCode = pigriddetail
								.getGarmentstyle()
								.getGarmentstylecode().split("@@@");
						piassignexternalaccessoryModel.setGarmentStyleDisplayName(splitGarmentStyleCode[0]);

						lstPiassignexternalaccessoryModel
								.add(piassignexternalaccessoryModel);
					}
				}
			}

			Collections.sort(lstPiassignexternalaccessoryModel,
					PiassignexternalaccessoryModel.accessoryNameComparator);

			return lstPiassignexternalaccessoryModel;
		} catch (Exception e) {
			log.error(String.format(
					"getAllGarmentstyle in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add list PiAssignExternalAccessory for the 1st
	 * time Hàm assign external accessory cho PI lần đầu tiên
	 * 
	 * @param lstNotAssign
	 * @param creator
	 * @return
	 */
	public boolean add1stTimePiAssignExternalAccessory(
			ArrayList<PiassignexternalaccessoryModel> lstNotAssign,
			String creator) {
		log.info(String.format(
				"add1stTimePiAssignExternalAccessory in class: %s", getClass()));
		try {
			// lặp qua list chung
			for (PiassignexternalaccessoryModel piassignexternalaccessoryModel : lstPiassignexternalaccessoryModel) {
				// lặp qua list ko assign
				for (PiassignexternalaccessoryModel piassignexternalaccessoryNotAssign : lstNotAssign) {
					// nếu id của list chung = id của list ko assign thì set
					// estimate là o
					if (piassignexternalaccessoryModel
							.getPiassignexternalaccessorycode() == piassignexternalaccessoryNotAssign
							.getPiassignexternalaccessorycode()) {
						piassignexternalaccessoryModel
								.setEstimateqty((float) 0);
					}
				}
			}

			Piassignexternalaccessory piassignexternalaccessory;

			// lặp qua list chung rồi thêm vô db
			for (PiassignexternalaccessoryModel piassignexternalaccessoryModel : lstPiassignexternalaccessoryModel) {
				piassignexternalaccessory = new Piassignexternalaccessory();
				piassignexternalaccessory.setAccessory(accessoryDao
						.findById(piassignexternalaccessoryModel
								.getAccessoryCode()));
				piassignexternalaccessory
						.setColor(colorDao
								.findById(piassignexternalaccessoryModel
										.getColorcode()));
				piassignexternalaccessory.setGarmentstyle(garmentStyleDao
						.findById(piassignexternalaccessoryModel
								.getGarmentstylecode()));
				piassignexternalaccessory
						.setPi(piDao.findById(piassignexternalaccessoryModel
								.getLotnumber()));
				piassignexternalaccessory.setPigriddetail(pigriddetailDao
						.findById(piassignexternalaccessoryModel
								.getPigriddetail()));
				piassignexternalaccessory.setUser(userDao.findById("admin"));
				piassignexternalaccessory
						.setEstimateqty(piassignexternalaccessoryModel
								.getEstimateqty());
				piassignexternalaccessory.setCreatedate(new Date());
				piassignexternalaccessory
						.setGarmentstyleaccessorydetail(garmentstyleaccessorydetailDao
								.findById(piassignexternalaccessoryModel
										.getGarmentstyleaccessorydetailcode()));

				dao.save(piassignexternalaccessory);
			}

			return true;
		} catch (Exception e) {
			log.error(String
					.format("add1stTimePiAssignExternalAccessory in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to check if a PI with lot No is already assign
	 * external accessory Hàm kiểm tra xem pi vs lotNo xác định đã đc assign
	 * external accesory chưa?
	 * 
	 * @param lotNumber
	 * @return
	 */
	public boolean isPiAssignedExternalAccessory(String lotNumber) {
		log.debug(String.format("isPiAssignedExternalAccessory in class: %s",
				getClass()));
		try {
			return dao.isPiAssignedExternalAccessory(lotNumber);
		} catch (RuntimeException re) {
			log.error(String.format(
					"isPiAssignedExternalAccessory in class %s has error: %s",
					getClass(), re.getMessage()));
			throw re;
		}
	}

	/**
	 * This function is used to get list Pi assign external accessory By Lot
	 * Number to show table after assign Hàm đc dùng lấy pi assign external để
	 * show ra view sau khi assign
	 * 
	 * @param lotNumber
	 * @return
	 */
	public List<PiassignexternalaccessoryModel> getListPiassignexternalaccessoryByLotNumber(
			String lotNumber) {
		log.debug(String.format(
				"getListPiassignexternalaccessoryByLotNumber in class: %s",
				getClass()));
		try {
			// list kết quả trả về
			List<PiassignexternalaccessoryModel> lstResult = new ArrayList<PiassignexternalaccessoryModel>();
			PiassignexternalaccessoryModel piassignexternalaccessoryModel;

			//lấy ra pi = lot number (để lấy factory)
			Pi pi = piDao.findById(lotNumber);
//			List<Garmentstyleaccessorydetail> lstGarmentstyleaccessorydetail;
			Set<String> setAccessoryName = new HashSet<String>();
			
			List<Piassignexternalaccessory> lstByLotNo = dao
					.getListPiassignexternalaccessoryByLotNumber(lotNumber);
			
			//mãng xử lý display name
			String[] splitGarmentStyleCode;
			
			for (Piassignexternalaccessory piassignexternalaccessory : lstByLotNo) {
				// if estimate mà >0 thì mới add vô list
				if (piassignexternalaccessory.getEstimateqty() > 0) {
					piassignexternalaccessoryModel = new PiassignexternalaccessoryModel();
					piassignexternalaccessoryModel
							.setPiassignexternalaccessorycode(piassignexternalaccessory
									.getPiassignexternalaccessorycode());
					piassignexternalaccessoryModel
							.setAccessoryCode(piassignexternalaccessory
									.getAccessory().getAccessorycode());
					piassignexternalaccessoryModel
							.setAccessoryName(piassignexternalaccessory
									.getAccessory().getName());
					piassignexternalaccessoryModel
							.setImgurl(piassignexternalaccessory.getAccessory()
									.getImgurl1());
					piassignexternalaccessoryModel
							.setGarmentstylecode(piassignexternalaccessory
									.getGarmentstyle().getGarmentstylecode());
					piassignexternalaccessoryModel
							.setGarmentstyleName(piassignexternalaccessory
									.getGarmentstyle().getDescription());
					piassignexternalaccessoryModel
							.setColorcode(piassignexternalaccessory.getColor()
									.getColorcode());
					piassignexternalaccessoryModel
							.setColorName(piassignexternalaccessory.getColor()
									.getDescription());
					piassignexternalaccessoryModel
							.setSizecode(piassignexternalaccessory
									.getPigriddetail().getSize().getSizecode());
					piassignexternalaccessoryModel
							.setSizename(piassignexternalaccessory
									.getPigriddetail().getSize().getSizename());
					piassignexternalaccessoryModel
							.setEstimateqty(piassignexternalaccessory
									.getEstimateqty());
					piassignexternalaccessoryModel
							.setMode(piassignexternalaccessory.getAccessory()
									.getMode());
					
					splitGarmentStyleCode = piassignexternalaccessory.getGarmentstyle().getGarmentstylecode().split("@@@");
					piassignexternalaccessoryModel.setGarmentStyleDisplayName(splitGarmentStyleCode[0]);
					
					//set factory part (lấy factory từ lot number của pi)
					piassignexternalaccessoryModel.setFactorycode(pi.getFactory().getFactorycode());
					piassignexternalaccessoryModel.setFactoryShortname(pi.getFactory().getShortname());
					
					//set accessory supplier
					//nếu order external mà khác null thì set Accsupplier
					if(piassignexternalaccessory
									.getOrderexternalaccessory() != null){
						piassignexternalaccessoryModel
						.setAccsuppliercode(piassignexternalaccessory
								.getOrderexternalaccessory()
								.getAccessorysupplier()
								.getAccsuppliercode());
						piassignexternalaccessoryModel.setAccsupplierShortname(piassignexternalaccessory
								.getOrderexternalaccessory()
								.getAccessorysupplier().getShortname());
						//
						piassignexternalaccessoryModel.setOrderQty(piassignexternalaccessory
								.getOrderexternalaccessory().getOrderquantity());
						piassignexternalaccessoryModel.setOrderDate(piassignexternalaccessory
								.getOrderexternalaccessory().getOrderdate());
						piassignexternalaccessoryModel.setEstimateDeliveryDate(piassignexternalaccessory
								.getOrderexternalaccessory().getEstimatedelvdate());
						piassignexternalaccessoryModel.setActualQty(piassignexternalaccessory
								.getOrderexternalaccessory().getActualdelvquantity());
						piassignexternalaccessoryModel.setStatus(piassignexternalaccessory
								.getOrderexternalaccessory().getStatus());
						piassignexternalaccessoryModel.setPaymentStatus(piassignexternalaccessory
								.getOrderexternalaccessory().getPaymentstatus());
						
						piassignexternalaccessoryModel.setOrderSheetNoAndStatus(piassignexternalaccessory
								.getOrderexternalaccessory().getOrdersheetno()+"/Status: "+piassignexternalaccessory
								.getOrderexternalaccessory().getStatus()+"/Payment status: "+piassignexternalaccessory
								.getOrderexternalaccessory().getPaymentstatus());
					}
					
					//tính giá trị estimate cho fpi (kiểm tra xem có import fpi chưa)
					Fpi fpi = fpiDao.getFpiHasBiggestVersionByLotNumber(lotNumber);
					if(fpi!=null){
						//kiểm tra đã import fpi, nếu fpi grid detail size mà> 0 thì import rồi
						if(fpiDetailDao.getListFpidetailByLotNumberAndVersion(lotNumber, fpi.getVersion()).size()>0){
							//lấy pi gridDetail
							Pigriddetail pigriddetail = piassignexternalaccessory.getPigriddetail();
							
							int fpiValue = fpiDetailDao.getFpiValueOfFpidetailByFpidetailcodeColorCodeGarmentStyleCodeSizeCode(fpi.getFpicode(),pigriddetail.getColor().getColorcode(),pigriddetail.getGarmentstyle().getGarmentstylecode(),pigriddetail.getSize().getSizecode());
							
							piassignexternalaccessoryModel
							.setEstimateFpiQty(fpiValue
									* piassignexternalaccessory
											.getGarmentstyleaccessorydetail().getUsedvalue());
						}else{
							//ko thì cho fpi là 0
							piassignexternalaccessoryModel.setEstimateFpiQty((float) 0);
						}
					}else{
						//ko thì cho fpi là 0
						piassignexternalaccessoryModel.setEstimateFpiQty((float) 0);
					}
					

					//tính giá trị estimate cho rfpi
					Rfpi rfpi = rfpiDao.getRfpiHasBiggestVersionByLotNumber(lotNumber);
					if(rfpi!=null){
						//kiểm tra đã import fpi, nếu fpi grid detail size mà> 0 thì import rồi
						if(rfpidetailDao.getListRfpidetailByLotNumberAndVersion(lotNumber, rfpi.getVersion()).size()>0){
							//lấy pi gridDetail
							Pigriddetail pigriddetail = piassignexternalaccessory.getPigriddetail();
							
							int rfpiValue = rfpidetailDao.getRfpiValueOfRfpidetailByRfpidetailcodeColorCodeGarmentStyleCodeSizeCode(rfpi.getRfpigrid(),pigriddetail.getColor().getColorcode(),pigriddetail.getGarmentstyle().getGarmentstylecode(),pigriddetail.getSize().getSizecode());
							
							piassignexternalaccessoryModel
							.setEstimateRfpiQty(rfpiValue
									* piassignexternalaccessory
											.getGarmentstyleaccessorydetail().getUsedvalue());
						}else{
							//ko thì cho rfpi là 0
							piassignexternalaccessoryModel.setEstimateRfpiQty((float) 0);
						}
					}else{
						//ko thì cho rfpi là 0
						piassignexternalaccessoryModel.setEstimateRfpiQty((float) 0);
					}

					lstResult.add(piassignexternalaccessoryModel);
					
					//tìm tất cả các accessory đc assign
					//nếu chưa có thì thêm vô set
					if(!setAccessoryName.contains(piassignexternalaccessory.getAccessory().getAccessorycode())){
						setAccessoryName.add(piassignexternalaccessory.getAccessory().getAccessorycode());
					}
				}
			}

//			for (String string : setAccessoryName) {
//				System.err.println(string);
//			}
			
			//lặp qua set color, gán code và giá trị cho từng màu
			List<TotalAccForAssignExternalModel> lstTotalAccForAssignExternalModel = new ArrayList<TotalAccForAssignExternalModel>();
			TotalAccForAssignExternalModel totalAccForAssignExternalModelTmp;
			for (String string : setAccessoryName) {
				totalAccForAssignExternalModelTmp= new TotalAccForAssignExternalModel();
				totalAccForAssignExternalModelTmp.setAccessoryCode(string);
				totalAccForAssignExternalModelTmp.setEstimateqty((float) 0);
				totalAccForAssignExternalModelTmp.setEstimateFpiQty((float) 0);
				totalAccForAssignExternalModelTmp.setEstimateRFpiQty((float) 0);
				lstTotalAccForAssignExternalModel.add(totalAccForAssignExternalModelTmp);
			}
			
			//
			float sumEstimateqty = 0;
			float sumEstimateFpiQty = 0;
			float sumEstimateRfpiQty = 0;
			for (PiassignexternalaccessoryModel piassignexternalaccessoryModel1 : lstResult) {
				for (TotalAccForAssignExternalModel totalAccForAssignExternalModel : lstTotalAccForAssignExternalModel) {
					if(totalAccForAssignExternalModel.getAccessoryCode().equals(piassignexternalaccessoryModel1.getAccessoryCode())){
						sumEstimateqty = totalAccForAssignExternalModel.getEstimateqty() + piassignexternalaccessoryModel1.getEstimateqty();
						totalAccForAssignExternalModel.setEstimateqty(sumEstimateqty);
						//tính tổng fpi
						sumEstimateFpiQty = totalAccForAssignExternalModel.getEstimateFpiQty() + piassignexternalaccessoryModel1.getEstimateFpiQty();
						totalAccForAssignExternalModel.setEstimateFpiQty(sumEstimateFpiQty);
						//tính tổng rfpi
						sumEstimateRfpiQty = totalAccForAssignExternalModel.getEstimateRFpiQty() + piassignexternalaccessoryModel1.getEstimateRfpiQty();
						totalAccForAssignExternalModel.setEstimateRFpiQty(sumEstimateRfpiQty);
					}
				}
			}
			
			//lặp qua list kết quả, lặp qua list acc, set total tương ứng
			for (PiassignexternalaccessoryModel piassignexternalaccessoryModel1 : lstResult) {
				for (TotalAccForAssignExternalModel totalAccForAssignExternalModel : lstTotalAccForAssignExternalModel) {
					if(totalAccForAssignExternalModel.getAccessoryCode().equals(piassignexternalaccessoryModel1.getAccessoryCode())){
						piassignexternalaccessoryModel1.setTotalEstimateByAccessory(totalAccForAssignExternalModel.getEstimateqty());
						piassignexternalaccessoryModel1.setTotalEstimateFpiByAccessory(totalAccForAssignExternalModel.getEstimateFpiQty());
						piassignexternalaccessoryModel1.setTotalEstimateRfpiByAccessory(totalAccForAssignExternalModel.getEstimateRFpiQty());
					}
				}
			}
			
			for (TotalAccForAssignExternalModel totalAccForAssignExternalModel : lstTotalAccForAssignExternalModel) {
				System.err.println(totalAccForAssignExternalModel);
			}
			
			// sort theo accessory name
			Collections.sort(lstResult,
					PiassignexternalaccessoryModel.accessoryNameComparator);
			return lstResult;
		} catch (RuntimeException re) {
			log.error(String
					.format("getListPiassignexternalaccessoryByLotNumber in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}

	// List lưu những PiassignexternalaccessoryModel có thể thêm mới khi edit
	List<PiassignexternalaccessoryModel> lstAddNewWhenEdit = new ArrayList<PiassignexternalaccessoryModel>();

	/**
	 * This function is used to get list Pi assign external accessory By Lot
	 * Number to show into dialog to edit Hàm đc dùng lấy pi assign external để
	 * show vào dialog để edit
	 * 
	 * @param lotNumber
	 * @return
	 */
	public List<PiassignexternalaccessoryModel> getListPiassignexternalaccessoryForEditByLotNumber(
			String lotNumber) {
		log.debug(String.format(
				"getListPiassignexternalaccessoryByLotNumber in class: %s",
				getClass()));
		try {
			// list kết quả trả về
			List<PiassignexternalaccessoryModel> lstResult = new ArrayList<PiassignexternalaccessoryModel>();
			PiassignexternalaccessoryModel piassignexternalaccessoryModel;

			List<Piassignexternalaccessory> lstByLotNo = dao
					.getListPiassignexternalaccessoryByLotNumber(lotNumber);
			
			//mãng xử lý display name
			String[] splitGarmentStyleCode;
			
			for (Piassignexternalaccessory piassignexternalaccessory : lstByLotNo) {
				// if estimate mà >0 thì mới add vô list
				// if(piassignexternalaccessory.getEstimateqty()>0){
				piassignexternalaccessoryModel = new PiassignexternalaccessoryModel();
				// set id của từng PiassignexternalaccessoryModel tại đây
				piassignexternalaccessoryModel
						.setPiassignexternalaccessorycode(piassignexternalaccessory
								.getPiassignexternalaccessorycode());
				piassignexternalaccessoryModel
						.setAccessoryCode(piassignexternalaccessory
								.getAccessory().getAccessorycode());
				piassignexternalaccessoryModel
						.setAccessoryName(piassignexternalaccessory
								.getAccessory().getName());
				piassignexternalaccessoryModel
						.setColorcode(piassignexternalaccessory.getColor()
								.getColorcode());
				piassignexternalaccessoryModel
						.setColorName(piassignexternalaccessory.getColor()
								.getDescription());
				piassignexternalaccessoryModel
						.setSizecode(piassignexternalaccessory
								.getPigriddetail().getSize().getSizecode());
				piassignexternalaccessoryModel
						.setSizename(piassignexternalaccessory
								.getPigriddetail().getSize().getSizename());
				piassignexternalaccessoryModel
						.setGarmentstylecode(piassignexternalaccessory
								.getGarmentstyle().getGarmentstylecode());
				piassignexternalaccessoryModel
						.setGarmentstyleName(piassignexternalaccessory
								.getGarmentstyle().getDescription());
				piassignexternalaccessoryModel.setLotnumber(lotNumber);
				piassignexternalaccessoryModel
						.setPigriddetail(piassignexternalaccessory
								.getPigriddetail().getPigriddetail());
				piassignexternalaccessoryModel
						.setEstimateqty(piassignexternalaccessory
								.getEstimateqty());
				piassignexternalaccessoryModel
						.setMode(piassignexternalaccessory.getAccessory()
								.getMode());
				piassignexternalaccessoryModel
						.setImgurl(piassignexternalaccessory.getAccessory()
								.getImgurl1());
				
				//set garment style display name
				splitGarmentStyleCode = piassignexternalaccessory
						.getGarmentstyle()
						.getGarmentstylecode().split("@@@");
				piassignexternalaccessoryModel.setGarmentStyleDisplayName(splitGarmentStyleCode[0]);

				lstResult.add(piassignexternalaccessoryModel);
				// }
			}

			// ///////////////////////////
			lstAddNewWhenEdit.clear();
			List<PiassignexternalaccessoryModel> lstTotal = getListPiassignexternalaccessoryModelVsPigridDetail(lotNumber);
			List<PiassignexternalaccessoryModel> lstSaved = getListPiassignexternalaccessorySaved(lotNumber);

			boolean flag = false;

			for (PiassignexternalaccessoryModel piassignexternalaccessoryModel2 : lstTotal) {
				for (PiassignexternalaccessoryModel piassignexternalaccessoryModel3 : lstSaved) {
					// nếu mã garmentStyleAccessoryDetail mà trùng, => đã tồn
					// tại, set cờ = true
					if (piassignexternalaccessoryModel2
							.getGarmentstyleaccessorydetailcode() == piassignexternalaccessoryModel3
							.getGarmentstyleaccessorydetailcode()) {
						flag = true;
						break;
					}
				}
				// nếu mà chưa tồn tại thì thêm
				if (flag == false) {
					lstResult.add(piassignexternalaccessoryModel2);
					lstAddNewWhenEdit.add(piassignexternalaccessoryModel2);
				}
				flag = false;
			}
			// //////////////////////////

			// sort theo accessory name
			Collections.sort(lstResult,
					PiassignexternalaccessoryModel.accessoryNameComparator);
			return lstResult;
		} catch (RuntimeException re) {
			log.error(String
					.format("getListPiassignexternalaccessoryByLotNumber in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}

	/**
	 * This function is used to edit Pi Assign External Accessory | Hàm dùng để
	 * edit lại assign external accessory xem chọn acc nào, bỏ acc nào
	 * 
	 * @param lstAssignOrNot
	 * @param modifier
	 * @return
	 */
	public boolean editPiAssignExternalAccessory(
			ArrayList<PiassignexternalaccessoryModel> lstAssignOrNot,
			String modifier) {
		log.info(String.format("editPiAssignExternalAccessory in class: %s",
				getClass()));
		try {
			// khai báo 1 Piassignexternalaccessory để find by id
			Piassignexternalaccessory piassignexternalaccessory;
			List<Garmentstyleaccessorydetail> lstGarmentstyleaccessorydetail;

			for (PiassignexternalaccessoryModel piassignexternalaccessoryNotAssign : lstAssignOrNot) {
				// thêm điều kiện mới là nếu Piassignexternalaccessorycode mà
				// =-1 thì add
				if (piassignexternalaccessoryNotAssign
						.getPiassignexternalaccessorycode() < 0) {
					// nếu có tích vô checkbox
					if (piassignexternalaccessoryNotAssign.isAssign() == true) {
						for (PiassignexternalaccessoryModel piassignexternalaccessoryModel : lstAddNewWhenEdit) {
							// nếu Piassignexternalaccessorycode = nhau thì add
							if (piassignexternalaccessoryNotAssign
									.getPiassignexternalaccessorycode() == piassignexternalaccessoryModel
									.getPiassignexternalaccessorycode()) {
								// add
								piassignexternalaccessory = new Piassignexternalaccessory();
								piassignexternalaccessory
										.setAccessory(accessoryDao
												.findById(piassignexternalaccessoryModel
														.getAccessoryCode()));
								piassignexternalaccessory
										.setColor(colorDao
												.findById(piassignexternalaccessoryModel
														.getColorcode()));
								piassignexternalaccessory
										.setGarmentstyle(garmentStyleDao
												.findById(piassignexternalaccessoryModel
														.getGarmentstylecode()));
								piassignexternalaccessory
										.setPi(piDao
												.findById(piassignexternalaccessoryModel
														.getLotnumber()));
								piassignexternalaccessory
										.setPigriddetail(pigriddetailDao
												.findById(piassignexternalaccessoryModel
														.getPigriddetail()));
								piassignexternalaccessory.setUser(userDao
										.findById("admin"));
								piassignexternalaccessory
										.setEstimateqty(-piassignexternalaccessoryModel
												.getEstimateqty());
								piassignexternalaccessory
										.setCreatedate(new Date());
								piassignexternalaccessory
										.setGarmentstyleaccessorydetail(garmentstyleaccessorydetailDao
												.findById(piassignexternalaccessoryModel
														.getGarmentstyleaccessorydetailcode()));

								dao.save(piassignexternalaccessory);
								// end add
							}
						}
					}
				} else {// ko thì làm b.thg
						// find by id ra từng pi assign external accessory
					piassignexternalaccessory = dao
							.findById(piassignexternalaccessoryNotAssign
									.getPiassignexternalaccessorycode());
					// nếu có check thì update lại số lượng như bình thg
					if (piassignexternalaccessoryNotAssign.isAssign() == true) {
						// lấy ra used value
						lstGarmentstyleaccessorydetail = garmentstyleaccessorydetailDao
								.getListGarmentstyleaccessorydetailByGarmentStyleCodeAccessoryCodeAndSize(
										piassignexternalaccessory
												.getGarmentstyle()
												.getGarmentstylecode(),
										piassignexternalaccessory
												.getAccessory()
												.getAccessorycode(),
										piassignexternalaccessory
												.getPigriddetail().getSize()
												.getSizecode());
						for (Garmentstyleaccessorydetail garmentstyleaccessorydetail : lstGarmentstyleaccessorydetail) {
							// update lại số lượng accessory
							piassignexternalaccessory
									.setEstimateqty(piassignexternalaccessory
											.getPigriddetail().getFavalue()
											* garmentstyleaccessorydetail
													.getUsedvalue());
						}
						dao.update(piassignexternalaccessory);
						lstGarmentstyleaccessorydetail.clear();
					} else if (piassignexternalaccessoryNotAssign.isAssign() == false) {
						// nếu ko check thì set số lượng pcs về 0
						piassignexternalaccessory.setEstimateqty((float) 0);
						dao.update(piassignexternalaccessory);
					}
				}
			}

			return true;
		} catch (Exception e) {
			log.error(String.format(
					"editPiAssignExternalAccessory in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * hàm lấy cả mã garmentStyleAccessoryDetail ứng vs pigridDetail
	 */
	public List<PiassignexternalaccessoryModel> getListPiassignexternalaccessoryModelVsPigridDetail(
			String lotnumber) {
		log.info(String.format("getAllGarmentstyle in class: %s", getClass()));
		try {
			List<Garmentstyleaccessorydetail> lstGarmentstyleaccessorydetail;
			PiassignexternalaccessoryModel piassignexternalaccessoryModel;
			List<PiassignexternalaccessoryModel> lstResult = new ArrayList<PiassignexternalaccessoryModel>();
			// lstPiassignexternalaccessoryModel.clear();

			// lấy piGridDetail qua lot no
			int pigridCode = piDao.findById(lotnumber).getPigrid()
					.getPigridcode();
			List<Pigriddetail> lstGridDetail = pigriddetailDao
					.getListPigriddetailByPigridcode(pigridCode);

			// vì là lần đầu add nên lưu các id của
			// PiassignexternalaccessoryModel theo thứ tự tăng dần, để lát biết
			// nào add nào ko
			int i = -1;

			// lặp qua grid detail
			for (Pigriddetail pigriddetail : lstGridDetail) {
				// lấy ra các Garmentstyleaccessorydetail qua garmentStyleCode,
				// size
				lstGarmentstyleaccessorydetail = garmentstyleaccessorydetailDao
						.getListGarmentstyleaccessorydetailByGarmentStyleNameAndSize(
								pigriddetail.getGarmentstyle()
										.getGarmentstylecode(), pigriddetail
										.getSize().getSizecode());
				for (Garmentstyleaccessorydetail garmentstyleaccessorydetail : lstGarmentstyleaccessorydetail) {
					// nếu accessory là External thì mới xét tiếp
					if (garmentstyleaccessorydetail.getAccessory().getKind()
							.equals("External")) {
						// tạo ra pi assign external accessory
						piassignexternalaccessoryModel = new PiassignexternalaccessoryModel();
						// set id của từng PiassignexternalaccessoryModel tại
						// đây
						piassignexternalaccessoryModel
								.setPiassignexternalaccessorycode(i--);

						piassignexternalaccessoryModel
								.setAccessoryCode(garmentstyleaccessorydetail
										.getAccessory().getAccessorycode());
						piassignexternalaccessoryModel
								.setAccessoryName(garmentstyleaccessorydetail
										.getAccessory().getName());
						piassignexternalaccessoryModel
								.setColorcode(pigriddetail.getColor()
										.getColorcode());
						piassignexternalaccessoryModel
								.setColorName(pigriddetail.getColor()
										.getDescription());
						piassignexternalaccessoryModel.setSizecode(pigriddetail
								.getSize().getSizecode());
						piassignexternalaccessoryModel.setSizename(pigriddetail
								.getSize().getSizename());
						piassignexternalaccessoryModel
								.setGarmentstylecode(pigriddetail
										.getGarmentstyle()
										.getGarmentstylecode());
						piassignexternalaccessoryModel
								.setGarmentstyleName(pigriddetail
										.getGarmentstyle().getDescription());
						piassignexternalaccessoryModel.setLotnumber(lotnumber);
						piassignexternalaccessoryModel
								.setPigriddetail(pigriddetail.getPigriddetail());
						piassignexternalaccessoryModel.setCreator("admin");
						piassignexternalaccessoryModel
								.setCreatedate(new Date());
						piassignexternalaccessoryModel.setEstimateqty(-1
								* pigriddetail.getFavalue()
								* garmentstyleaccessorydetail.getUsedvalue());
						// piassignexternalaccessoryModel.setEstimateqty((float)
						// 0);
						piassignexternalaccessoryModel
								.setMode(garmentstyleaccessorydetail
										.getAccessory().getMode());
						piassignexternalaccessoryModel
								.setImgurl(garmentstyleaccessorydetail
										.getAccessory().getImgurl1());

						piassignexternalaccessoryModel
								.setGarmentstyleaccessorydetailcode(garmentstyleaccessorydetail
										.getGarmentstyleaccessorydetailcode());

						lstResult.add(piassignexternalaccessoryModel);
					}
				}
			}

			// Collections.sort(lstPiassignexternalaccessoryModel,
			// PiassignexternalaccessoryModel.accessoryNameComparator);

			return lstResult;
		} catch (Exception e) {
			log.error(String.format(
					"getAllGarmentstyle in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * Hàm lấy ra các pi assign external đã save r
	 */
	public List<PiassignexternalaccessoryModel> getListPiassignexternalaccessorySaved(
			String lotNumber) {
		log.debug(String.format(
				"getListPiassignexternalaccessoryByLotNumber in class: %s",
				getClass()));
		try {
			// list kết quả trả về
			List<PiassignexternalaccessoryModel> lstResult = new ArrayList<PiassignexternalaccessoryModel>();
			PiassignexternalaccessoryModel piassignexternalaccessoryModel;

			List<Piassignexternalaccessory> lstByLotNo = dao
					.getListPiassignexternalaccessoryByLotNumber(lotNumber);
			for (Piassignexternalaccessory piassignexternalaccessory : lstByLotNo) {
				// if estimate mà >0 thì mới add vô list
				// if(piassignexternalaccessory.getEstimateqty()>0){
				piassignexternalaccessoryModel = new PiassignexternalaccessoryModel();
				// set id của từng PiassignexternalaccessoryModel tại đây
				piassignexternalaccessoryModel
						.setPiassignexternalaccessorycode(piassignexternalaccessory
								.getPiassignexternalaccessorycode());
				piassignexternalaccessoryModel
						.setAccessoryCode(piassignexternalaccessory
								.getAccessory().getAccessorycode());
				piassignexternalaccessoryModel
						.setAccessoryName(piassignexternalaccessory
								.getAccessory().getName());
				piassignexternalaccessoryModel
						.setColorcode(piassignexternalaccessory.getColor()
								.getColorcode());
				piassignexternalaccessoryModel
						.setColorName(piassignexternalaccessory.getColor()
								.getDescription());
				piassignexternalaccessoryModel
						.setSizecode(piassignexternalaccessory
								.getPigriddetail().getSize().getSizecode());
				piassignexternalaccessoryModel
						.setSizename(piassignexternalaccessory
								.getPigriddetail().getSize().getSizename());
				piassignexternalaccessoryModel
						.setGarmentstylecode(piassignexternalaccessory
								.getGarmentstyle().getGarmentstylecode());
				piassignexternalaccessoryModel
						.setGarmentstyleName(piassignexternalaccessory
								.getGarmentstyle().getDescription());
				piassignexternalaccessoryModel.setLotnumber(lotNumber);
				piassignexternalaccessoryModel
						.setPigriddetail(piassignexternalaccessory
								.getPigriddetail().getPigriddetail());
				piassignexternalaccessoryModel
						.setEstimateqty(piassignexternalaccessory
								.getEstimateqty());
				piassignexternalaccessoryModel
						.setMode(piassignexternalaccessory.getAccessory()
								.getMode());
				piassignexternalaccessoryModel
						.setImgurl(piassignexternalaccessory.getAccessory()
								.getImgurl1());

				piassignexternalaccessoryModel
						.setGarmentstyleaccessorydetailcode(piassignexternalaccessory
								.getGarmentstyleaccessorydetail()
								.getGarmentstyleaccessorydetailcode());

				lstResult.add(piassignexternalaccessoryModel);
				// }
			}

			// sort theo accessory name
			Collections.sort(lstResult,
					PiassignexternalaccessoryModel.accessoryNameComparator);
			return lstResult;
		} catch (RuntimeException re) {
			log.error(String
					.format("getListPiassignexternalaccessoryByLotNumber in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}

	/**
	 * This function is used to get List Piassignexternalaccessory By LotNumber And AccessoryCode |
	 *  Hàm đc dùng để lấy Piassignexternalaccessory hiển thị lên lúc edit lại wasted percentage
	 * @param lotNumber
	 * @param accessoryCode
	 * @return
	 */
	public List<PiassignexternalaccessoryModel> getListPiassignexternalaccessoryByLotNumberAndAccessoryCode(
			String lotNumber, String accessoryCode) {
		log.debug(String.format(
				"getListPiassignexternalaccessoryByLotNumber in class: %s",
				getClass()));
		try {
			// list kết quả trả về
			List<PiassignexternalaccessoryModel> lstResult = new ArrayList<PiassignexternalaccessoryModel>();
			PiassignexternalaccessoryModel piassignexternalaccessoryModel;

			List<Piassignexternalaccessory> lstByLotNo = dao
					.getListPiassignexternalaccessoryByLotNumberAndAccessoryCode(
							lotNumber, accessoryCode);
			
			//mãng xử lý display name
			String[] splitGarmentStyleCode;
			
			for (Piassignexternalaccessory piassignexternalaccessory : lstByLotNo) {
				// if estimate mà >0 thì mới add vô list
				if (piassignexternalaccessory.getEstimateqty() > 0) {
					piassignexternalaccessoryModel = new PiassignexternalaccessoryModel();
					piassignexternalaccessoryModel
							.setPiassignexternalaccessorycode(piassignexternalaccessory
									.getPiassignexternalaccessorycode());
					piassignexternalaccessoryModel
							.setAccessoryCode(piassignexternalaccessory
									.getAccessory().getAccessorycode());
					piassignexternalaccessoryModel
							.setAccessoryName(piassignexternalaccessory
									.getAccessory().getName());
//					piassignexternalaccessoryModel
//							.setImgurl(piassignexternalaccessory.getAccessory()
//									.getImgurl1());
					piassignexternalaccessoryModel
							.setGarmentstylecode(piassignexternalaccessory
									.getGarmentstyle().getGarmentstylecode());
					piassignexternalaccessoryModel
							.setGarmentstyleName(piassignexternalaccessory
									.getGarmentstyle().getDescription());
					piassignexternalaccessoryModel
							.setColorcode(piassignexternalaccessory.getColor()
									.getColorcode());
					piassignexternalaccessoryModel
							.setColorName(piassignexternalaccessory.getColor()
									.getDescription());
					piassignexternalaccessoryModel
							.setSizecode(piassignexternalaccessory
									.getPigriddetail().getSize().getSizecode());
					piassignexternalaccessoryModel
							.setSizename(piassignexternalaccessory
									.getPigriddetail().getSize().getSizename());
					piassignexternalaccessoryModel
							.setEstimateqty(piassignexternalaccessory
									.getEstimateqty());
					piassignexternalaccessoryModel
							.setMode(piassignexternalaccessory.getAccessory()
									.getMode());
					piassignexternalaccessoryModel
							.setTypecode(piassignexternalaccessory
									.getPigriddetail().getSize().getType()
									.getTypecode());
					piassignexternalaccessoryModel
							.setAccessorySupplierShortname(piassignexternalaccessory
									.getOrderexternalaccessory() == null ? ""
									: piassignexternalaccessory
											.getOrderexternalaccessory()
											.getAccessorysupplier()
											.getShortname());
					
					splitGarmentStyleCode = piassignexternalaccessory.getGarmentstyle().getGarmentstylecode().split("@@@");
					piassignexternalaccessoryModel.setGarmentStyleDisplayName(splitGarmentStyleCode[0]);

					lstResult.add(piassignexternalaccessoryModel);
				}
			}

			// sort theo accessory name
			Collections.sort(lstResult,
					PiassignexternalaccessoryModel.accessoryNameComparator);
			return lstResult;
		} catch (RuntimeException re) {
			log.error(String
					.format("getListPiassignexternalaccessoryByLotNumber in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to check If PiAssignedExternalAccessory Already Have Specific Consumption
	 * @param lotNumber
	 * @param accessoryCode
	 * @return true là có r, false thì vẫn còn null
	 */
	public boolean checkIfPiAssignedExternalAccessoryAlreadyHaveSpecificConsumption(String lotNumber, String accessoryCode) {
		log.debug(String.format("checkIfPiAssignedExternalAccessoryAlreadyHaveSpecificConsumption in class: %s",
				getClass()));
		try {
			List<Piassignexternalaccessory> lstByLotNo = dao
					.getListPiassignexternalaccessoryByLotNumberAndAccessoryCode(
							lotNumber, accessoryCode);
			for (Piassignexternalaccessory piassignexternalaccessory : lstByLotNo) {
				if(piassignexternalaccessory.getSpecificconsumption()!=null)
					return true;
			}
			return false;
		} catch (RuntimeException re) {
			log.error(String.format(
					"checkIfPiAssignedExternalAccessoryAlreadyHaveSpecificConsumption in class %s has error: %s",
					getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to get Specificconsumption from table PiAssignedExternalAccessory
	 * @param lotNumber
	 * @param accessoryCode
	 * @return
	 */
	public Float getPiAssignedExternalAccessorySpecificConsumption(String lotNumber, String accessoryCode) {
		log.debug(String.format("getPiAssignedExternalAccessorySpecificConsumption in class: %s",
				getClass()));
		try {
			List<Piassignexternalaccessory> lstByLotNo = dao
					.getListPiassignexternalaccessoryByLotNumberAndAccessoryCode(
							lotNumber, accessoryCode);
			for (Piassignexternalaccessory piassignexternalaccessory : lstByLotNo) {
					return piassignexternalaccessory.getSpecificconsumption();
			}
			return (float) 0;
		} catch (RuntimeException re) {
			log.error(String.format(
					"getPiAssignedExternalAccessorySpecificConsumption in class %s has error: %s",
					getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to edit Wasted Percentage 
	 * @param lotNumber
	 * @param accessoryCode
	 * @param wastedPercentage
	 * @return
	 */
	public boolean editWastedPercentage(String lotNumber, String accessoryCode, Float wastedPercentage) {
		log.debug(String.format("editWastedPercentage in class: %s",
				getClass()));
		try {
			List<Piassignexternalaccessory> lstByLotNo = dao
					.getListPiassignexternalaccessoryByLotNumberAndAccessoryCode(
							lotNumber, accessoryCode);
			for (Piassignexternalaccessory piassignexternalaccessory : lstByLotNo) {
				piassignexternalaccessory.setSpecificconsumption(wastedPercentage);
				dao.save(piassignexternalaccessory);
			}
			return true;
		} catch (RuntimeException re) {
			log.error(String.format(
					"editWastedPercentage in class %s has error: %s",
					getClass(), re.getMessage()));
			throw re;
		}
	}
}
