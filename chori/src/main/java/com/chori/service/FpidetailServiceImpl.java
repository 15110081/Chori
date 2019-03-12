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
import com.chori.dao.PIDao;
import com.chori.dao.PigriddetailDao;
import com.chori.entity.Fpi;
import com.chori.entity.Fpidetail;
import com.chori.entity.Pigriddetail;
import com.chori.model.ColorForPiModel;
import com.chori.model.FpidetailModel;
import com.chori.model.GarmentForPiModel;
import com.chori.model.TypeForPiModel;

@Service("fpidetailService")
public class FpidetailServiceImpl extends AbstractServiceImpl<Fpidetail, Integer>
implements FpidetailService{
	private FpidetailDao dao;
	
	@Autowired
	private PigriddetailDao pigriddetailDao;
	
	@Autowired
	private PIDao piDao;
	
	@Autowired
	private FpiDao fpiDao;
	
	@Autowired
	public FpidetailServiceImpl(
			@Qualifier("fpidetailDao") AbstractDao<Fpidetail, Integer> abstractDao) {
		super(abstractDao);
		this.dao = (FpidetailDao) abstractDao;
	}
	
	/**
	 * This function is used to calculate total fpi
	 * @param lotnumber
	 * @param version
	 * @return
	 */
	public int calculateTotalFpi(String lotnumber, Integer version) {
		log.info(String.format("calculateTotalFpi in class: %s", getClass()));
		try {
			log.debug("calculateTotalFpi and return result");
			int totalFpi =0;
			List<Fpidetail> lst = dao.getListFpidetailByLotNumberAndVersion(lotnumber, version);
			for (Fpidetail fpidetail : lst) {
				totalFpi+= fpidetail.getFpivalue();
			}
			log.debug("calculateTotalFpi successfully");
			return totalFpi;
		} catch (Exception e) {
			log.error(String.format(
					"calculateTotalFpi in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	public List<FpidetailModel> getListFpiDetailByLotNumberAndVersion(String lotnumber, Integer version){
		//lấy list pi grid detail qua lot number
		List<Pigriddetail> lstPigriddetail = pigriddetailDao.getListPigriddetailByPigridcode(piDao.findById(lotnumber).getPigrid().getPigridcode());
		
		//lấy list grid fpi qua lot no và version
		List<Fpidetail> lstFpidetail = dao.getListFpidetailByLotNumberAndVersion(lotnumber, version);
		
		Integer totalFpiPcs = calculateTotalFpi(lotnumber, version);
		Integer totalFaPcs = 0;
		//tính totalFa: lặp qua list grid pi detail r tính
		for (Pigriddetail pigriddetail : lstPigriddetail) {
			//bỏ các giá trị null, khác null mới +
			if(pigriddetail.getFavalue()!=null)
				totalFaPcs += pigriddetail.getFavalue();
		}
		
		//Tính total cho color --------------------------
		//khai báo set để lưu list maù
		Set<String> setColor= new HashSet<String>();
		//lặp qua list grid fpi trên
		for (Fpidetail fpidetail : lstFpidetail) {
			//add màu vô set
			if(!setColor.contains(fpidetail.getColor().getColorcode())){
				setColor.add(fpidetail.getColor().getColorcode());
			}
		}
		
		//lặp qua set color, gán code và giá trị cho từng màu
		List<ColorForPiModel> lstColorForPiModel = new ArrayList<ColorForPiModel>();
		ColorForPiModel colorForPiModelTmp;
		for (String string : setColor) {
			colorForPiModelTmp= new ColorForPiModel();
			colorForPiModelTmp.setColorName(string);
			colorForPiModelTmp.setColorFaPcs(0);
			colorForPiModelTmp.setColorFpiPcs(0);
			lstColorForPiModel.add(colorForPiModelTmp);
		}
		
		//lặp qua list fpi grid detail, vs mỗi màu tính tổng số lượng of chúng
		//tạo biến lưu để cộng
		Integer sumTmp=0;
		//lặp qua list fpi grid detail
		for (Fpidetail fpidetail : lstFpidetail) {
			for (ColorForPiModel colorForPiModel : lstColorForPiModel) {
				//nếu màu trùng thì +
				if(colorForPiModel.getColorName().equals(fpidetail.getColor().getColorcode())){
					sumTmp = colorForPiModel.getColorFpiPcs() + fpidetail.getFpivalue();
					colorForPiModel.setColorFpiPcs(sumTmp);
				}
			}
		}
		
		//test luu fa************
		for (Pigriddetail pigriddetail : lstPigriddetail) {
			for (ColorForPiModel colorForPiModel : lstColorForPiModel) {
				//nếu màu trùng thì +
				if(colorForPiModel.getColorName().equals(pigriddetail.getColor().getColorcode())){
					sumTmp = colorForPiModel.getColorFaPcs() + pigriddetail.getFavalue();
					colorForPiModel.setColorFaPcs(sumTmp);
				}
			}
		}
		
		//Tính total cho garment name ----------------------
		Set<String> setGarment= new HashSet<String>();
		//lặp qua list grid fpi trên, 
		for (Fpidetail fpidetail : lstFpidetail) {
			if(!setGarment.contains(fpidetail.getGarmentstyle().getGarmentstylecode())){
				setGarment.add(fpidetail.getGarmentstyle().getGarmentstylecode());
			}
		}
		
		//lặp qua set garment, gán code và giá trị cho từng garment
		List<GarmentForPiModel> lstGarmentForPiModel= new ArrayList<GarmentForPiModel>();
		GarmentForPiModel garmentForPiModelTmp;
		for (String string : setGarment) {
			garmentForPiModelTmp = new GarmentForPiModel();
			garmentForPiModelTmp.setGarmentstyle(string);
			garmentForPiModelTmp.setGarmentstyleFaPcs(0);
			garmentForPiModelTmp.setGarmentstyleFpiPcs(0);
			lstGarmentForPiModel.add(garmentForPiModelTmp);
		}
		
		//lặp qua list fpi grid detail, vs mỗi garment tính tổng số lượng of chúng
		//tạo biến lưu để cộng
//		Integer sumTmp=0;
		//lặp qua list fpi grid detail
		for (Fpidetail fpidetail : lstFpidetail) {
			for (GarmentForPiModel garmentForPiModel : lstGarmentForPiModel) {
				//nếu garment style trùng thì +
				if(garmentForPiModel.getGarmentstyle().equals(fpidetail.getGarmentstyle().getGarmentstylecode())){
					sumTmp = garmentForPiModel.getGarmentstyleFpiPcs()+ fpidetail.getFpivalue();
					garmentForPiModel.setGarmentstyleFpiPcs(sumTmp);
				}
			}
		}
		
		//test luu fa************
				for (Pigriddetail pigriddetail : lstPigriddetail) {
					for (GarmentForPiModel garmentForPiModel : lstGarmentForPiModel) {
						//nếu garment style trùng thì +
						if(garmentForPiModel.getGarmentstyle().equals(pigriddetail.getGarmentstyle().getGarmentstylecode())){
							sumTmp = garmentForPiModel.getGarmentstyleFaPcs()+ pigriddetail.getFavalue();
							garmentForPiModel.setGarmentstyleFaPcs(sumTmp);
						}
					}
				}
		
		//Tính total cho type ----------------------
		Set<String> setType = new HashSet<String>();
		//lặp qua list grid fpi trên, 
		for (Fpidetail fpidetail : lstFpidetail) {
			if(!setType.contains(fpidetail.getSize().getType().getTypecode())){
				setType.add(fpidetail.getSize().getType().getTypecode());
			}
		}
		
		List<TypeForPiModel> lstTypeForPiModel= new ArrayList<TypeForPiModel>();
		//lặp qua set type, gán code và giá trị cho từng type
		TypeForPiModel typeForPiModelTmp;
		for (String string : setType) {
			typeForPiModelTmp = new TypeForPiModel();
			typeForPiModelTmp.setTypecode(string);
			typeForPiModelTmp.setTypeFaPcs(0);
			typeForPiModelTmp.setTypeFpiPcs(0);
			lstTypeForPiModel.add(typeForPiModelTmp);
		}
		
		//lặp qua list fpi grid detail, vs mỗi type tính tổng số lượng of chúng
		//tạo biến lưu để cộng
//		Integer sumTmp=0;
		//lặp qua list fpi grid detail
		for (Fpidetail fpidetail : lstFpidetail) {
			for (TypeForPiModel typeForPiModel : lstTypeForPiModel) {
				//nếu garment type trùng thì +
				if(typeForPiModel.getTypecode().equals(fpidetail.getSize().getType().getTypecode())){
					sumTmp = typeForPiModel.getTypeFpiPcs()+ fpidetail.getFpivalue();
					typeForPiModel.setTypeFpiPcs(sumTmp);
				}
			}
		}
		
		//test luu fa************
		for (Pigriddetail pigriddetail : lstPigriddetail) {
			for (TypeForPiModel typeForPiModel : lstTypeForPiModel) {
				//nếu garment type trùng thì +
				if(typeForPiModel.getTypecode().equals(pigriddetail.getSize().getType().getTypecode())){
					sumTmp = typeForPiModel.getTypeFaPcs()+ pigriddetail.getFavalue();
					typeForPiModel.setTypeFaPcs(sumTmp);
				}
			}
		}
		
//		for (TypeForPiModel typeForPiModel : lstTypeForPiModel) {
//			System.err.println(typeForPiModel);
//		}
		
		List<FpidetailModel> lstResult = new ArrayList<FpidetailModel>();
		FpidetailModel fpidetailModel;
		//gán model
		for (Fpidetail fpidetail : lstFpidetail) {
			fpidetailModel = new FpidetailModel();
			fpidetailModel.setFpidetailcode(fpidetail.getFpidetailcode());
			fpidetailModel.setTotalFpiPcs(totalFpiPcs);
			fpidetailModel.setTotalFaPcs(totalFaPcs);
			fpidetailModel.setColorName(fpidetail.getColor().getDescription());
			//lặp qua list color, nếu trùng thì gán giá trị
			for (ColorForPiModel colorForPiModel : lstColorForPiModel) {
				if(fpidetail.getColor().getColorcode().equals(colorForPiModel.getColorName())){
					fpidetailModel.setColorFpiPcs(colorForPiModel.getColorFpiPcs());
					//test gán fa
					fpidetailModel.setColorFaPcs(colorForPiModel.getColorFaPcs());
					//end test gán fa
				}
			}
			
			fpidetailModel.setGarmentstyle(fpidetail.getGarmentstyle().getGarmentstylecode());
			//lặp qua list garment, nếu trùng thì gán giá trị
			for (GarmentForPiModel garmentForPiModel : lstGarmentForPiModel) {
				if(fpidetail.getGarmentstyle().getGarmentstylecode().equals(garmentForPiModel.getGarmentstyle())){
					fpidetailModel.setGarmentstyleFpiPcs(garmentForPiModel.getGarmentstyleFpiPcs());
					//test gán fa
					fpidetailModel.setGarmentstyleFaPcs(garmentForPiModel.getGarmentstyleFaPcs());
					//end test gán fa
				}
			}
			
			fpidetailModel.setImgUrl(fpidetail.getGarmentstyle().getImgurl1());
			fpidetailModel.setTypecode(fpidetail.getSize().getType().getTypecode());
			//lặp qua list type, nếu trùng thì gán giá trị
			for (TypeForPiModel typeForPiModel : lstTypeForPiModel) {
				if(fpidetail.getSize().getType().getTypecode().equals(typeForPiModel.getTypecode())){
					fpidetailModel.setTypeFpiPcs(typeForPiModel.getTypeFpiPcs());
					//test gán fa
					fpidetailModel.setTypeFaPcs(typeForPiModel.getTypeFaPcs());
					//end test gán fa
				}
			}
			
			fpidetailModel.setSizecode(fpidetail.getSize().getSizecode());
			fpidetailModel.setSizename(fpidetail.getSize().getSizename());
			fpidetailModel.setFpiPcs(fpidetail.getFpivalue());
			
			fpidetailModel
					.setFaPcs(pigriddetailDao
							.getFaOFPigriddetailByPigridcodeColorCodeGarmentStyleCodeSizeCode(
									piDao.findById(lotnumber).getPigrid()
											.getPigridcode(), fpidetail
											.getColor().getColorcode(),
									fpidetail.getGarmentstyle()
											.getGarmentstylecode(), fpidetail
											.getSize().getSizecode()));
			
			lstResult.add(fpidetailModel);
		}
		
		for (FpidetailModel fpidetailModelTmp : lstResult) {
			System.err.println(fpidetailModelTmp);
		}
		
		return lstResult;
	}
	
	/**
	 * This function is used to get List Fpi Version By LotNumber
	 * @param lotnumber
	 * @return
	 */
	public List<Integer> getListFpiVersionByLotNumber(String lotnumber) {
		log.info(String.format("getListFpiVersionByLotNumber in class: %s", getClass()));
		try {
			log.debug("getListFpiVersionByLotNumber and return result");
			List<Integer> lstResult= new ArrayList<Integer>();
			List<Fpi> lst = fpiDao.getListFpiByLotNumber(lotnumber);
			for (Fpi fpi : lst) {
				lstResult.add(fpi.getVersion());
			}
			log.debug("getListFpiVersionByLotNumber successfully");
			return lstResult;
		} catch (Exception e) {
			log.error(String.format(
					"getListFpiVersionByLotNumber in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
}
