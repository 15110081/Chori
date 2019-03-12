package com.chori.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.FpiDao;
import com.chori.dao.FpidetailDao;
import com.chori.dao.RfpiDao;
import com.chori.dao.RfpidetailDao;
import com.chori.entity.Fpi;
import com.chori.entity.Fpidetail;
import com.chori.entity.Rfpi;
import com.chori.entity.Rfpidetail;
import com.chori.model.ColorForFpiModel;
import com.chori.model.GarmentForFpiModel;
import com.chori.model.RfpidetailModel;
import com.chori.model.TypeForFpiModel;

@Service("rfpidetailService")
public class RfpidetailServiceImpl extends AbstractServiceImpl<Rfpidetail, Integer> implements RfpidetailService{
private RfpidetailDao rfpidetailDao;
	
	@Autowired
	private FpidetailDao fpidetailDao;	
	
	@Autowired
	private FpiDao fpiDao;
	
	@Autowired
	private RfpiDao rfpiDao;
	
	@Autowired
	public RfpidetailServiceImpl(
			@Qualifier("rfpidetailDao") AbstractDao<Rfpidetail, Integer> abstractDao) {
		super(abstractDao);
		this.rfpidetailDao = (RfpidetailDao) abstractDao;
	}
	
	/**
	 * This function is used to calculate total fpi
	 * @param lotnumber
	 * @param version
	 * @return
	 */
	public int calculateTotalRfpi(String lotnumber, Integer version) {
		log.info(String.format("calculateTotalRfpi in class: %s", getClass()));
		try {
			log.debug("calculateTotalRfpi and return totalRfpi");
			int totalRfpi =0;
			List<Rfpidetail> lst = rfpidetailDao.getListRfpidetailByLotNumberAndVersion(lotnumber, version);
			for (Rfpidetail rfpidetail : lst) {
				totalRfpi+= rfpidetail.getRfpivalue();
			}
			log.debug("calculateTotalRfpi successfully");
			return totalRfpi;
		} catch (Exception e) {
			log.error(String.format(
					"calculateTotalRfpi in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to get max Fpi version
	 * @param lotnumber
	 * @return
	 */
	public Integer getMaxFpiVersionByLotNumber(String lotNumber){
		log.info(String.format("getMaxFpiVersionByLotNumber in class: %s", getClass()));
		try{
			log.debug("getMaxFpiVersionByLotNumber and return maxVersion");
			List<Fpi> lstFpi = fpiDao.getListFpiByLotNumber(lotNumber);
			int maxVersion = 0;
			for(Fpi fpi : lstFpi){			
				if(maxVersion < fpi.getVersion())
				{
					maxVersion = fpi.getVersion();
				}
			}	
			return maxVersion;
		}catch(Exception e){
			log.error(String.format(
					"getMaxFpiVersionByLotNumber in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}	
	}
	
	/**
	 * This function is used to get Fpi by lotnumber and max version
	 * @param lotnumber
	 * @param max version
	 * @return fpi code
	 */
	
	public Integer getFpiCodeByLotNumberAndMaxVersion(String lotNumber){
		log.info(String.format("getFpiCodeByLotNumberAndMaxVersion in class: %s", getClass()));
		try{
			log.debug("getFpiCodeByLotNumberAndMaxVersion ");
			Integer maxVersion = getMaxFpiVersionByLotNumber(lotNumber);			
			List<Fpi> lstFpi = fpiDao.getListFpiByLotNumber(lotNumber);

			for(Fpi fpi : lstFpi){
				if(fpi.getVersion().equals(maxVersion)){														
					return fpi.getFpicode();
				}					
			}
			log.debug("getFpiByLotNumberAndMaxVersion successfully");
			return null;
		}catch(Exception e){
			log.error(String.format(
					"getFpiByLotNumberAndMaxVersion in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

//	/**
//	 * This function is used to get list Fpi detail by fpicode
//	 * @param fpicode
//
//	 * @return list fpi detail
//	 */
//	
//	public List<FpidetailModel> findFpiDetailModelByFpiCode(Integer fpiCode){
//		log.info(String.format("findFpiDetailModelByFpiCode in class: %s", getClass()));
//		try{		
//			List<Fpidetail> lstFpidetail = fpidetailDao.getAll();
//			List<FpidetailModel> lstFpidetailModel = new ArrayList<FpidetailModel>();
//			for(Fpidetail fpidetail : lstFpidetail){
//				if(fpidetail.getFpi().getFpicode().equals(fpiCode)){
//					FpidetailModel fpidetailModel = new FpidetailModel();
//					fpidetailModel.setFpicode(fpidetail.getFpi().getFpicode());
//					fpidetailModel.setColorName(fpidetail.getColor().getColorcode());
//					fpidetailModel.setGarmentstyle(fpidetail.getGarmentstyle().getGarmentstylecode());
//					fpidetailModel.setSizecode(fpidetail.getSize().getSizecode());
//					fpidetailModel.setFpiPcs(fpidetail.getFpivalue());
//					lstFpidetailModel.add(fpidetailModel);
//				}
//			}
//			log.debug("getPIAssignFabricDetailByLotNoAndFabricNo successfully");
//			return lstFpidetailModel;
//		}catch(Exception e){
//			log.error(String.format(
//					"findFpiDetailModelByFpiCode in class: %s has error: %s",
//					getClass(), e.getMessage()));
//			throw e;
//		}
//	}
	
	
	
	public List<RfpidetailModel> getListRfpiDetailByLotNumberAndVersion(String lotnumber, Integer version){
		//lấy list fpi detail qua lot no và version
				List<Fpidetail> lstFpidetail = fpidetailDao.getListFpidetailByLotNumberAndVersion(lotnumber, getMaxFpiVersionByLotNumber(lotnumber));
				//lấy list rfpi detail qua lot no và version
				List<Rfpidetail> lstRfpidetail = rfpidetailDao.getListRfpidetailByLotNumberAndVersion(lotnumber, version);
		
				Integer totalRfpiPcs = calculateTotalRfpi(lotnumber, version);
				Integer totalFpiPcs = 0;
				//tính totalFpivalue: lặp qua list fpi detail r tính
				for (Fpidetail fpidetail : lstFpidetail) {
					//bỏ các giá trị null, khác null mới +
					if(fpidetail.getFpivalue()!=null)
						totalFpiPcs += fpidetail.getFpivalue();
				}
				
				//Tính total cho color --------------------------
				//khai báo set để lưu list maù
				Set<String> setColor= new HashSet<String>();
				//lặp qua list rfpi  trên
				for (Rfpidetail rfpidetail : lstRfpidetail) {
					//add màu vô set
					if(!setColor.contains(rfpidetail.getColor().getColorcode())){
						setColor.add(rfpidetail.getColor().getColorcode());
					}
				}
				
				//lặp qua set color, gán code và giá trị cho từng màu
				List<ColorForFpiModel> lstColorForFpiModel = new ArrayList<ColorForFpiModel>();
				ColorForFpiModel colorForFpiModelTmp;
				for (String string : setColor) {
					colorForFpiModelTmp= new ColorForFpiModel();
					colorForFpiModelTmp.setColorName(string);
					colorForFpiModelTmp.setColorFpiPcs(0);
					colorForFpiModelTmp.setColorRfpiPcs(0);
					lstColorForFpiModel.add(colorForFpiModelTmp);
				}
				
				//lặp qua list rfpi detail, vs mỗi màu tính tổng số lượng of chúng
				//tạo biến lưu để cộng
				Integer sumTmp=0;
				//lặp qua list rfpi detail
				for (Rfpidetail rfpidetail : lstRfpidetail) {
					for (ColorForFpiModel colorForFpiModel : lstColorForFpiModel) {
						//nếu màu trùng thì +
						if(colorForFpiModel.getColorName().equals(rfpidetail.getColor().getColorcode())){
							sumTmp = colorForFpiModel.getColorRfpiPcs() + rfpidetail.getRfpivalue();
							colorForFpiModel.setColorRfpiPcs(sumTmp);
						}
					}
				}
				
				//luu fpi value theo color
				for (Fpidetail fpidetail : lstFpidetail) {
					for (ColorForFpiModel colorForFpiModel : lstColorForFpiModel) {
						//nếu màu trùng thì +
						if(colorForFpiModel.getColorName().equals(fpidetail.getColor().getColorcode())){
							sumTmp = colorForFpiModel.getColorFpiPcs() + fpidetail.getFpivalue();
							colorForFpiModel.setColorFpiPcs(sumTmp);
						}
					}
				}
				
				for (ColorForFpiModel colorForFpiModel : lstColorForFpiModel) {
					System.err.println(colorForFpiModel);
				}
				
				//Tính total cho garment name ----------------------
				Set<String> setGarment= new HashSet<String>();
				//lặp qua list rfpi trên, 
				for (Rfpidetail rfpidetail : lstRfpidetail) {
					if(!setGarment.contains(rfpidetail.getGarmentstyle().getGarmentstylecode())){
						setGarment.add(rfpidetail.getGarmentstyle().getGarmentstylecode());
					}
				}
				
				//lặp qua set garment, gán code và giá trị cho từng garment
				List<GarmentForFpiModel> lstGarmentForFpiModel= new ArrayList<GarmentForFpiModel>();
				GarmentForFpiModel garmentForFpiModelTmp;
				for (String string : setGarment) {
					garmentForFpiModelTmp = new GarmentForFpiModel();
					garmentForFpiModelTmp.setGarmentstyle(string);
					garmentForFpiModelTmp.setGarmentstyleFpiPcs(0);
					garmentForFpiModelTmp.setGarmentstyleRfpiPcs(0);
					lstGarmentForFpiModel.add(garmentForFpiModelTmp);
				}
				
				//lặp qua list rfpi detail, vs mỗi garment tính tổng số lượng of chúng
				//tạo biến lưu để cộng
//				Integer sumTmp=0;
				//lặp qua list rfpi detail
				for (Rfpidetail rfpidetail : lstRfpidetail) {
					for (GarmentForFpiModel garmentForFpiModel : lstGarmentForFpiModel) {
						//nếu garment style trùng thì +
						if(garmentForFpiModel.getGarmentstyle().equals(rfpidetail.getGarmentstyle().getGarmentstylecode())){
							sumTmp = garmentForFpiModel.getGarmentstyleFpiPcs()+ rfpidetail.getRfpivalue();
							garmentForFpiModel.setGarmentstyleRfpiPcs(sumTmp);
						}
					}
				}
				
				//luu fpi value theo garment style
						for (Fpidetail fpidetail : lstFpidetail) {
							for (GarmentForFpiModel garmentForFpiModel : lstGarmentForFpiModel) {
								//nếu garment style trùng thì +
								if(garmentForFpiModel.getGarmentstyle().equals(fpidetail.getGarmentstyle().getGarmentstylecode())){
									sumTmp = garmentForFpiModel.getGarmentstyleFpiPcs()+ fpidetail.getFpivalue();
									garmentForFpiModel.setGarmentstyleFpiPcs(sumTmp);
								}
							}
						}
						
						//Tính total cho type ----------------------
						//ham set luu gia tri duy nhat khong trung`
						Set<String> setType = new HashSet<String>();
						//lặp qua list grid fpi trên, 
						for (Rfpidetail rfpidetail : lstRfpidetail) {
							if(!setType.contains(rfpidetail.getSize().getType().getTypecode())){
								setType.add(rfpidetail.getSize().getType().getTypecode());
							}
						}
						
						List<TypeForFpiModel> lstTypeForFpiModel= new ArrayList<TypeForFpiModel>();
						//lặp qua set type, gán code và giá trị cho từng type
						TypeForFpiModel typeForFpiModelTmp;
						for (String string : setType) {
							typeForFpiModelTmp = new TypeForFpiModel();
							typeForFpiModelTmp.setTypecode(string);
							typeForFpiModelTmp.setTypeFpiPcs(0);
							typeForFpiModelTmp.setTypeRfpiPcs(0);
							lstTypeForFpiModel.add(typeForFpiModelTmp);
						}
						
						//lặp qua list rfpi detail, vs mỗi type tính tổng số lượng of chúng
						//tạo biến lưu để cộng
//						Integer sumTmp=0;
						//lặp qua list rfpi detail
						for (Rfpidetail rfpidetail : lstRfpidetail) {
							for (TypeForFpiModel typeForFpiModel : lstTypeForFpiModel) {
								//nếu garment type trùng thì +
								if(typeForFpiModel.getTypecode().equals(rfpidetail.getSize().getType().getTypecode())){
									sumTmp = typeForFpiModel.getTypeRfpiPcs()+ rfpidetail.getRfpivalue();
									typeForFpiModel.setTypeRfpiPcs(sumTmp);
								}
							}
						}
						
						//luu fpi value theo type
						for (Fpidetail fpidetail : lstFpidetail) {
							for (TypeForFpiModel typeForFpiModel : lstTypeForFpiModel) {
								//nếu garment type trùng thì +
								if(typeForFpiModel.getTypecode().equals(fpidetail.getSize().getType().getTypecode())){
									sumTmp = typeForFpiModel.getTypeFpiPcs()+ fpidetail.getFpivalue();
									typeForFpiModel.setTypeFpiPcs(sumTmp);
								}
							}
						}
						
						List<RfpidetailModel> lstResult = new ArrayList<RfpidetailModel>();
						RfpidetailModel rfpidetailModel;
						//gán model
						for (Rfpidetail rfpidetail : lstRfpidetail) {
							rfpidetailModel = new RfpidetailModel();
							rfpidetailModel.setRfpidetailcode(rfpidetail.getRfpidetail());
							rfpidetailModel.setTotalRfpiPcs(totalRfpiPcs);
							rfpidetailModel.setTotalFpiPcs(totalFpiPcs);
							rfpidetailModel.setColorName(rfpidetail.getColor().getDescription());
							//lặp qua list color, nếu trùng thì gán giá trị
							for (ColorForFpiModel colorForFpiModel : lstColorForFpiModel) {
								if(rfpidetail.getColor().getColorcode().equals(colorForFpiModel.getColorName())){
									rfpidetailModel.setColorRfpiPcs(colorForFpiModel.getColorRfpiPcs());
									//test gán fpi value
									rfpidetailModel.setColorFpiPcs(colorForFpiModel.getColorFpiPcs());
									//end test gán fpi value
								}
							}
							
							rfpidetailModel.setGarmentstyle(rfpidetail.getGarmentstyle().getGarmentstylecode());
							//lặp qua list garment, nếu trùng thì gán giá trị
							for (GarmentForFpiModel garmentForFpiModel : lstGarmentForFpiModel) {
								if(rfpidetail.getGarmentstyle().getGarmentstylecode().equals(garmentForFpiModel.getGarmentstyle())){
									rfpidetailModel.setGarmentstyleRfpiPcs(garmentForFpiModel.getGarmentstyleRfpiPcs());
									//test gán fpi value
									rfpidetailModel.setGarmentstyleFpiPcs(garmentForFpiModel.getGarmentstyleFpiPcs());
									//end test gán fpi value
								}
							}
							
							rfpidetailModel.setImgUrl(rfpidetail.getGarmentstyle().getImgurl1());
							rfpidetailModel.setTypecode(rfpidetail.getSize().getType().getTypecode());
							//lặp qua list type, nếu trùng thì gán giá trị
							for (TypeForFpiModel typeForFpiModel : lstTypeForFpiModel) {
								if(rfpidetail.getSize().getType().getTypecode().equals(typeForFpiModel.getTypecode())){
									rfpidetailModel.setTypeRfpiPcs(typeForFpiModel.getTypeRfpiPcs());
									//test gán fpi value
									rfpidetailModel.setTypeFpiPcs(typeForFpiModel.getTypeFpiPcs());
									//end test fpi value
								}
							}
							// piDao.findById(lotnumber).getPigrid().getPigridcode()
							
							rfpidetailModel.setSizecode(rfpidetail.getSize().getSizecode());
							rfpidetailModel.setSizename(rfpidetail.getSize().getSizename());
							rfpidetailModel.setRfpiPcs(rfpidetail.getRfpivalue());
							
							rfpidetailModel
									.setFpiPcs(fpidetailDao
											.getFpiValueOfFpidetailByFpidetailcodeColorCodeGarmentStyleCodeSizeCode(
													getFpiCodeByLotNumberAndMaxVersion(lotnumber), rfpidetail
													.getColor().getColorcode(),
											rfpidetail.getGarmentstyle()
													.getGarmentstylecode(), rfpidetail
													.getSize().getSizecode()));
							
							lstResult.add(rfpidetailModel);
						}
						for (RfpidetailModel rfpidetailModelTmp : lstResult) {
							System.err.println(rfpidetailModelTmp);
						}
												return lstResult;		
		
	}
	
	/**
	 * This function is used to get List Rfpi Version By LotNumber
	 * @param lotnumber
	 * @return
	 */
	public List<Integer> getListRfpiVersionByLotNumber(String lotnumber) {
		log.info(String.format("getListRfpiVersionByLotNumber in class: %s", getClass()));
		try {
			log.debug("getListRfpiVersionByLotNumber and return result");
			List<Integer> lstResult= new ArrayList<Integer>();
			List<Rfpi> lst = rfpiDao.getListRfpiByLotNumber(lotnumber);
			for (Rfpi rfpi : lst) {
				lstResult.add(rfpi.getVersion());
			}
			log.debug("getListRfpiVersionByLotNumber successfully");
			return lstResult;
		} catch (Exception e) {
			log.error(String.format(
					"getListRfpiVersionByLotNumber in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
}
