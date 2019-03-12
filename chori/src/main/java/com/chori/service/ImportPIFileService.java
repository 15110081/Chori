package com.chori.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chori.dao.ColorDao;
import com.chori.dao.GarmentstyleDao;
import com.chori.dao.PIDao;
import com.chori.dao.PigridDao;
import com.chori.dao.PigriddetailDao;
import com.chori.dao.SizeDao;
import com.chori.dao.UserDao;
import com.chori.entity.Pi;
import com.chori.entity.Pigrid;
import com.chori.entity.Pigriddetail;
import com.chori.entity.Size;
import com.chori.model.ImportPIFile;
import com.chori.model.SizeModel;

@Repository("importpifileService")
public class ImportPIFileService {

	@Autowired
	ColorDao colorDao;

	@Autowired
	PigridDao pigridDao;

	@Autowired
	GarmentstyleDao garmentstyleDao;

	@Autowired
	UserDao userDao;

	@Autowired 
	SizeDao sizeDao;
	
	@Autowired
	PIDao piDao;
	
	@Autowired
	PigriddetailDao pigriddetailDao;
	
	boolean checkValidateIsNull = false;
	
	protected final Log log = LogFactory.getLog(getClass());
	
	private Object getCellValue(Cell cell) {
	    switch (cell.getCellType()) {
	    case Cell.CELL_TYPE_STRING:
	        return cell.getStringCellValue();
	 
	    case Cell.CELL_TYPE_BOOLEAN:
	        return cell.getStringCellValue();
	 
	    case Cell.CELL_TYPE_NUMERIC:
	        return cell.getNumericCellValue();
	    }
	    return null;
	}
	
	//Reading both .xls & .xlsx
	private Workbook getWorkbook(FileInputStream inputStream, String excelFilePath)
	        throws IOException {
		Workbook workbook = null;
	    if (excelFilePath.endsWith("xlsx")) {
	    	workbook = new XSSFWorkbook(inputStream);
	    } else if (excelFilePath.endsWith("xls")) {
	    	workbook = (Workbook) new HSSFWorkbook(inputStream);
	    } else {
	        throw new IllegalArgumentException("The specified file is not Excel file");
	    }
	    return workbook;
	}
	
	/**
	 * This function is used to read data from import pi file
	 */
	public List<ImportPIFile> readPiGridFromExcelFile(String excelFilePath, String lotNumber) throws IOException {
		
	    List<ImportPIFile> listPI = new ArrayList<>();
	    FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	    Workbook workbook = getWorkbook(inputStream, excelFilePath);
	    
	    //variable to identity when start to read
	    Boolean startMark = false;
	    Boolean stopMark = false;
	    Boolean sizeMark = false;
	    Boolean qtyPcsMark = false;
	    Boolean qtyCtnMark = false;
	    Boolean garmentStyleMark = false;
	    Boolean colorMark = false;
	    Boolean barcodeMark = false;
	    Boolean pcsDozenMark = false;
	    Boolean yardageMark = false;
	    Boolean styleCodeMark = false;
	    String total = "Total";
	    //Workbook workbook = new XSSFWorkbook(inputStream);
	    Sheet firstSheet = workbook.getSheetAt(0);
	    Iterator<Row> iterator = firstSheet.iterator();
	    
	    //object for add to list
	    ImportPIFile importPIFileModel = null;
    	
	    //to handle null row of garment style & color
    	String garmentStyleTemp = "";
    	String colorTemp = "";
    	
    	//to handle garment style name "@@@"
    	String customerCode = piDao.findById(lotNumber).getCustomerByCustomer1code().getCustomercode();
    	
    	//loop each row in file
	    while (iterator.hasNext()) {
	    	Row nextRow = iterator.next(); 	        	        
	        Iterator<Cell> cellIterator = nextRow.cellIterator();	        
	        importPIFileModel = new ImportPIFile();	
	        //reset stop mark
	        stopMark = false;
	        //loop each column in row
	        while (cellIterator.hasNext()) {
	            Cell nextCell = cellIterator.next();
	            
	            //System.err.println("aaaaa" + getCellValue(nextCell));
	            
	            //when find "Size" then start reading
	            if(getCellValue(nextCell)!=(null)  
	            		&& startMark == false 
	            		&& ((String) getCellValue(nextCell).toString()).equals("Size"))
	            {
	            	startMark = true;
	            }
	            
	            //when cell = Total, then stop reading
//	            if(startMark == true 
//	            		&& stopMark == false
//	            		&& getCellValue(nextCell)!=(null) 
//	            		&& ((String) getCellValue(nextCell).toString()).equals("Total"))
//	            {
//	            	//break child loop
//	            	stopMark = true;
//	            	break;
//	            }
	            
	            //start to read
	            if(startMark == true && stopMark == false)
	            {
	            	
	            	//break to next row if find Total row
	            	if(getCellValue(nextCell)!=(null) 
	            		&&((String) getCellValue(nextCell).toString()).equalsIgnoreCase(total.trim()))
					{
	            		stopMark = true;
						break;
					}
	            	
	            	//column index
		            int columnIndex = nextCell.getColumnIndex();
       	            
		            switch (columnIndex) {
		            //index = 0  mean column "A"
		            case 0:
		            	//check if null then set size = null
		            	if(getCellValue(nextCell)==(null))  {
	            			importPIFileModel.setSize(null);
	            			break;
	            		}
		            	//check if column header is "Size" then set mark = true & ignore this header column (break)
		            	else if(((String) getCellValue(nextCell).toString()).equalsIgnoreCase("Size")) {
		            		sizeMark = true;
		            		break;
		            	}
		            	//if mark = true, then set Size to model
		            	else if(sizeMark==true) {
		            		//change format to string (avoid size name is float)
		            		DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
		            		String sizeValue = formatter.formatCellValue(nextCell); 
			            	importPIFileModel.setSize(sizeValue + "___A" + (nextCell.getRowIndex() + 1));
			            	//System.err.println("Size: " + importPIFileModel.getSize());
			                break;
		            	}
		            case 1:  		            	
		            	if(getCellValue(nextCell)==(null)) {
	            			importPIFileModel.setQtypcs(null);
	            			break;
	            		}
		            	else if(((String) getCellValue(nextCell).toString()).equalsIgnoreCase("Qty (Pcs)")) {
		            		qtyPcsMark = true;
		            		break;
		            	}
		            	else if(qtyPcsMark==true) {
			            	importPIFileModel.setQtypcs(((Double) getCellValue(nextCell)).floatValue());
			            	//System.err.println("QTY: " + importPIFileModel.getQtypcs());
			            	break;
		            	}
		            case 2:
		            	if(getCellValue(nextCell)==(null)) {
	            			importPIFileModel.setQtyctn(null);
	            			break;
	            		}
		            	else if(((String) getCellValue(nextCell).toString()).equalsIgnoreCase("Qty (Ctn)")) {
		            		qtyCtnMark = true;
		            		break;
		            	}
		            	else if(qtyCtnMark==true){
			            	importPIFileModel.setQtyctn(((Double) getCellValue(nextCell)).floatValue());
			                break;
		            	}
		            case 3:
		            	//if next row is null, then set garment style = previous garment style
		            	if(garmentStyleTemp.length()>0 && getCellValue(nextCell)==(null))
		            	{
		            		importPIFileModel.setGarmentstyle(garmentStyleTemp + "@@@" + customerCode + "___D" + (nextCell.getRowIndex() + 1));
		            		break;
		            	}
		            	else if(getCellValue(nextCell)==(null)) {
	            			importPIFileModel.setGarmentstyle(null);
	            			break;
	            		}
		            	else if(((String) getCellValue(nextCell).toString()).equalsIgnoreCase("Garment Style")) {
		            		garmentStyleMark = true;
		            		break;
		            	}
		            	else if(garmentStyleMark==true){
		            		DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
		            		String garmentStyleValue = formatter.formatCellValue(nextCell);     	
			            	importPIFileModel.setGarmentstyle(garmentStyleValue + "@@@" + customerCode + "___D" + (nextCell.getRowIndex() + 1));
			            	garmentStyleTemp = garmentStyleValue;
			                break;
		            	}
		            case 4:
		            	//if next row is null, then set color = previous color
		            	if(colorTemp.length()>0 && getCellValue(nextCell)==(null))
		            	{
		            		importPIFileModel.setColor(colorTemp + "___E" + (nextCell.getRowIndex() + 1));
		            		break;
		            	}
		            	else if(getCellValue(nextCell)==(null)) {
	            			importPIFileModel.setColor(null);
	            			break;
	            		}
		            	else if(((String) getCellValue(nextCell).toString()).equalsIgnoreCase("Color")) {
		            		colorMark = true;
		            		break;
		            	}
		            	else if(colorMark==true){
		            		DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
		            		String colorValue = formatter.formatCellValue(nextCell);     
		            		
			            	importPIFileModel.setColor(colorValue + "___E" + (nextCell.getRowIndex() + 1));
			            	colorTemp = colorValue;
			                break;
		            	}
		            case 5:
		            	if(getCellValue(nextCell)==(null)) {
	            			importPIFileModel.setPcsdozen(null);
	            			break;
	            		}
		            	else if(((String) getCellValue(nextCell).toString()).equalsIgnoreCase("Pcs/Dozen")) {
		            		pcsDozenMark = true;
		            		break;
		            	}
		            	else if(pcsDozenMark==true){
			            	importPIFileModel.setPcsdozen(((Double) getCellValue(nextCell)).floatValue());
			                break;	
		            	}
		            case 6:
		            	if(getCellValue(nextCell)==(null)) {
	            			importPIFileModel.setBarcode(null);
	            			break;
	            		}
		            	else if(((String) getCellValue(nextCell).toString()).equalsIgnoreCase("Barcode")) {
		            		barcodeMark = true;
		            		break;
		            	}
		            	else if(barcodeMark==true){
			            	importPIFileModel.setBarcode((String) getCellValue(nextCell));	
			                break;
		            	}
		            case 7:
		            	if(getCellValue(nextCell)==(null)) {
	            			importPIFileModel.setYardage(null);
	            			break;
	            		}
		            	else if(((String) getCellValue(nextCell).toString()).equalsIgnoreCase("Yardage")) {
		            		yardageMark = true;
		            		break;
		            	}
		            	else if(yardageMark==true){
			            	importPIFileModel.setYardage(((Double) getCellValue(nextCell)).floatValue());      	
			                break;
		            	}
		            case 8:
		            	if(getCellValue(nextCell)==(null)) 
	            		{
	            			importPIFileModel.setStylecode(null);
	            			break;
	            		}
		            	else if(((String) getCellValue(nextCell).toString()).equalsIgnoreCase("Style Code")) {
		            		styleCodeMark = true;
		            		break;
		            	}
		            	else if(styleCodeMark==true){
			            	importPIFileModel.setStylecode((String) getCellValue(nextCell));  	
			                break;
		            	}
		            }
	            }
	        }
	        listPI.add(importPIFileModel);
	        
//	        //break father loop
//	        if(stopMark == true){
//	        	break;
//	        }
	    }
	    
//	    //filter null in list
	    for(int i = 0; i < listPI.size();i++)
	    {
	    	if( listPI.get(i).getSize() == null
	    			|| listPI.get(i).getGarmentstyle() == null
	    			|| listPI.get(i).getColor() == null
	    			|| listPI.get(i).getQtypcs() == null)
	    	{
	    		listPI.remove(listPI.get(i));
	    	}
	    }
	    
		//this loop use to remove duplicate garmentstyle and color and get sum qty
		List<ImportPIFile> listPIUnduplicate = new ArrayList<ImportPIFile>();
		for (ImportPIFile importPIFile : listPI) 
		{
			listPIUnduplicate.add(importPIFile);
			for (int i =0 ; i < listPIUnduplicate.size()-1 ; i++)
			{				
				if(listPIUnduplicate.get(i).getGarmentstyle() !=null 
						&& listPIUnduplicate.get(i).getColor() !=null 
						&& listPIUnduplicate.get(i).getSize() !=null 
						&& listPIUnduplicate.get(i).getGarmentstyle().equals(importPIFile.getGarmentstyle())
						&& listPIUnduplicate.get(i).getColor().equals(importPIFile.getColor())
						&& listPIUnduplicate.get(i).getSize().equals(importPIFile.getSize()))
				{
					Float sumQtyPcs = (listPIUnduplicate.get(i).getQtypcs() + importPIFile.getQtypcs());
					listPIUnduplicate.get(i).setQtypcs(sumQtyPcs);
					listPIUnduplicate.remove(listPIUnduplicate.size()-1);
				}
			}			
		}	
		listPI.clear();
		listPI.addAll(listPIUnduplicate);
	 
//	    for(ImportPIFile pi : listPI)
//	    {
//	    	System.err.println(pi.getSize());
//	    	System.err.println(pi.getGarmentstyle());
//	    	System.err.println(pi.getColor());
//	    	System.err.println(pi.getQtypcs());
//	    }
	    
	    //workbook.close();
	    inputStream.close();
	 
	    return listPI;
	}
	
	/**
	 * This function is used to update PiGridCode row of a Lot
	 */
	public boolean updatePIGridCodeOfAPI(String lotNumber, Integer piGridCode)
	{
		log.info(String.format("updatePIGridCodeOfAPI in class: %s", getClass()));
		try{
			Pi pi = piDao.findById(lotNumber);
			pi.setPigrid(pigridDao.findById(piGridCode));
			piDao.update(pi);
			return true;
		} catch (Exception e) {
			log.error(String.format("updatePIGridCodeOfAPI  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("updatePIGridCodeOfAPI into database fail, class ImportPIFileService");
			return false;
		}		
	}
	
	/**
	 * This function is used to add new pi grid in PiGrid table
	 */
	public Integer addNewPiGrid(String userName)
	{
		log.info(String.format("addNewPiGrid in class: %s", getClass()));
		try{
			Pigrid pigrid = new Pigrid();
			pigrid.setUser(userDao.findById(userName));
			pigridDao.save(pigrid);
			return pigrid.getPigridcode();
		} catch (Exception e) {
			log.error(String.format("addNewPiGrid  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("addNewPiGrid into database fail, class ImportPIFileService");
			return null;
		}		
	}
	
	/**
	 * This function is used to save pi grid detail data into DB
	 */
	public Boolean addNewPiGridDetail(ImportPIFile importPIFileModel,Integer piGridCode)
	{
		log.info(String.format("addNewPiGridDetail in class: %s", getClass()));
		try{
			Pigriddetail pigriddetail = new Pigriddetail();
            
			if(importPIFileModel.getSize()!=null
					&& importPIFileModel.getGarmentstyle()!= null
					&& importPIFileModel.getQtypcs()!= null
					&& importPIFileModel.getColor()!= null)
			{
				pigriddetail.setSize(sizeDao.findById(findSizeCodeBySizeName(importPIFileModel.getSize().split("___")[0],importPIFileModel.getGarmentstyle().split("___")[0])));
				pigriddetail.setGarmentstyle(garmentstyleDao.findById(importPIFileModel.getGarmentstyle().split("___")[0]));
				pigriddetail.setPcs(Math.round(importPIFileModel.getQtypcs()));
				pigriddetail.setPigrid(pigridDao.findById(piGridCode));
				pigriddetail.setBarcode(importPIFileModel.getBarcode()==null?"":importPIFileModel.getBarcode());
				pigriddetail.setColor(colorDao.findById(importPIFileModel.getColor().split("___")[0]));
				pigriddetailDao.save(pigriddetail);
			}
			return true;
		} catch (Exception e) {
			log.error(String.format("addNewPiGridDetail  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("addNewPiGridDetail into database fail, class ImportPIFileService");
			return false;
		}		
	}
	
	/**
	 * This function is used to find sizeCode by sizeName
	 */
	public Integer findSizeCodeBySizeName(String sizeName, String garmentStyle)
	{
		log.info(String.format("findSizeCodeBySizeName in class: %s", getClass()));
		try{
			List<Size> lstsize = sizeDao.getAll();
			for(Size size : lstsize){
				if(size.getSizename().equals(sizeName)
						&& (garmentstyleDao.findById(garmentStyle)).getGarmentkind().getGarmentkindcode().equals(size.getGarmentkind().getGarmentkindcode()))
				{
					return size.getSizecode();
				}
			}
			return null;
		} catch (Exception e) {
			log.error(String.format("findSizeCodeBySizeName  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("findSizeCodeBySizeName into database fail, class ImportPIFileService");
			return null;
		}	
	}


	/**
	 * This function is used save Pi Grid Information when read import pi file include:
	 * Create PiGrid
	 * Update PiGridCode for a Lot
	 * Save PiGridDeatail
	 * Have 2 option, Opt 1: only insert data when all row of import file is valid, Opt 2: insert data in which row of import file is valid
	 * @param a List<ImportPIFile>, a lot number, username of current user, option value
	 * @return true if save success, false if not
	 */
	public Boolean savePIGridInformationByImportFile(String excelFilePath, String lotNumber, String userName, Integer option)
	{	
		log.info(String.format("savePIGridInformationByImportFile in class: %s", getClass()));
		try{
			//check option value 1 or 2
			if(1 == option)
			{
				return savePIGridInformationByImportFileOption1(excelFilePath, lotNumber, userName);
			}
			else if(2 == option)
			{
				return savePIGridInformationByImportFileOption2(excelFilePath, lotNumber, userName);
			}
			return false;

		} catch (Exception e) {
			log.error(String.format("savePIGridInformationByImportFile  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("savePIGridInformationByImportFile into database fail, class ImportPIFileService");
			return false;
		}
	}
	
	//save pi grid information by import file follow option 1: only insert data when all row of import file is valid
	public Boolean savePIGridInformationByImportFileOption1(String excelFilePath, String lotNumber, String userName)
	{
		log.info(String.format("savePIGridInformationByImportFileOption1 in class: %s", getClass()));
		try{
				//reset that lstValidate is not null
				checkValidateIsNull = false;
				
				//check if pi have pigrid already, then delete all data depend on its
				if(checkIfPiGridCodeIsExistedInAPi(lotNumber)==true)
				{
					deleteAllPiGridDataDependOnAPI(lotNumber);
				}
				
				List<ImportPIFile> lstImportPIFile = readPiGridFromExcelFile(excelFilePath, lotNumber);
				
				//validate data of import file
				List<ArrayList<String>> lstValidate 
						= validateGeneralResult(lstImportPIFile, lotNumber);
				
				//if all color,size,garmentstyle is existed -> listValidate == null		
				if(checkListOfListIsNull(lstValidate))
				{
					//mark that lstValidate is null
					checkValidateIsNull = true;
					
					//save new data from import file
					Integer piGridCode;
					piGridCode = addNewPiGrid(userName);
					updatePIGridCodeOfAPI(lotNumber,piGridCode);
					
					//add pi grid detail
					for(ImportPIFile importPIFile : lstImportPIFile)
					{
						addNewPiGridDetail(importPIFile,piGridCode);
					}
					return true;
				}
				else
				{		
					return false;
				}		
		} catch (Exception e) {
			log.error(String.format("savePIGridInformationByImportFileOption1  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("savePIGridInformationByImportFileOption1 into database fail, class ImportPIFileService");
			return false;
		}
	}
	
	//save pi grid information by import file follow option 2: insert data in which row of import file is valid
	public Boolean savePIGridInformationByImportFileOption2(String excelFilePath, String lotNumber, String userName)
	{
		log.info(String.format("savePIGridInformationByImportFileOption2 in class: %s", getClass()));
		try{
				//reset that lstValidate is not null
				checkValidateIsNull = false;
				
				//check if pi have pigrid already, then delete all data depend on its
				if(checkIfPiGridCodeIsExistedInAPi(lotNumber)==true)
				{
					deleteAllPiGridDataDependOnAPI(lotNumber);
				}
				
				List<ImportPIFile> lstImportPIFile = readPiGridFromExcelFile(excelFilePath,lotNumber);
				
//				//validate data of import file
//				List<ArrayList<String>> lstValidate 
//						= validateGeneralResult(lstImportPIFile, lotNumber);
				
//				//if all color,size,garmentstyle is existed -> listValidate == null		
//				if(checkListOfListIsNull(lstValidate))
//				{
					//mark that lstValidate is null
					checkValidateIsNull = true;
					
					//save new data from import file
					Integer piGridCode;
					piGridCode = addNewPiGrid(userName);
					updatePIGridCodeOfAPI(lotNumber,piGridCode);
					
					//add pi grid detail
					for(ImportPIFile importPIFile : lstImportPIFile)
					{
						addNewPiGridDetail(importPIFile,piGridCode);
					}
					return true;
//				}
//				else
//				{		
//					return false;
//				}		
		} catch (Exception e) {
			log.error(String.format("savePIGridInformationByImportFileOption2  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("savePIGridInformationByImportFileOption2 into database fail, class ImportPIFileService");
			return false;
		}
	}
	//if one of color,garmentstyle,size is null, then return true, else return false
	public Boolean lstValidateStatus()
	{
		return checkValidateIsNull;
	}
	/**
	 * This function is used to check if a pi have pigridcode then return true, else return false
	 */
	public Boolean checkIfPiGridCodeIsExistedInAPi(String lotNumber)
	{
		log.info(String.format("checkIfPiGridCodeIsExistedInAPi in class: %s", getClass()));
		try{
			Pi pi = piDao.findById(lotNumber);
			if(pi.getPigrid() != null)
			{
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error(String.format("checkIfPiGridCodeIsExistedInAPi  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("checkIfPiGridCodeIsExistedInAPi into database fail, class ImportPIFileService");
			return false;
		}	
	}
	
	/**
	 * This function is used to delete data in pigridcode row of a pi
	 */
	public Boolean deletePiGridCodeRowOfAPi(String lotNumber)
	{
		log.info(String.format("deletePiGridCodeOfAPi in class: %s", getClass()));
		try{
			Pi pi = piDao.findById(lotNumber);
			pi.setPigrid(null);
			piDao.update(pi);
			return true;
		} catch (Exception e) {
			log.error(String.format("deletePiGridCodeOfAPi  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("deletePiGridCodeOfAPi into database fail, class ImportPIFileService");
			return false;
		}	
	}
	
	
	/**
	 * This function is used to delete pigriddetail by pigridcode
	 */
	public Boolean deletePiGridDetailByPiGridCode(Integer piGridCode)	
	{
		log.info(String.format("deletePiGridDetailByPiGridCode in class: %s", getClass()));
		try{
			List<Pigriddetail> lstPigriddetail = pigriddetailDao.getListPigriddetailByPigridcode(piGridCode);
			for(Pigriddetail pigriddetail : lstPigriddetail)
			{
//				if(pigriddetail.getPigrid().getPigridcode().intValue() == piGridCode)
//				{
					pigriddetailDao.deleteById(pigriddetail.getPigriddetail());
				//}
			}
			return true;
		} catch (Exception e) {
			log.error(String.format("deletePiGridDetailByPiGridCode  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("deletePiGridDetailByPiGridCode into database fail, class ImportPIFileService");
			return false;
		}	
	}
	
	/**
	 * This function is used to delete pigrid by id
	 */
	public Boolean deletePiGridOfAPI(Integer piGridCode)	
	{
		log.info(String.format("deletePiGridOfAPI in class: %s", getClass()));
		try{
			pigridDao.deleteById(piGridCode);
			return true;
		} catch (Exception e) {
			log.error(String.format("deletePiGridOfAPI  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("deletePiGridOfAPI into database fail, class ImportPIFileService");
			return false;
		}	
	}
	
	/**
	 * This function is used to delete all data which is depend on a pi
	 */
	public Boolean deleteAllPiGridDataDependOnAPI(String lotNumber)
	{		
		try{
			log.info(String.format("deleteAllDataDependOnAPI in class: %s", getClass()));
			//find pigridcode of a pi
			Integer piGridCode = null;
			List<Pi> lstPi = piDao.getPIByLotNumber(lotNumber);
			for(Pi pi : lstPi)
			{
//				if(pi.getLotnumber().equals(lotNumber))
//				{
					piGridCode =  pi.getPigrid().getPigridcode();
					break;
				//}
			}
			
//			Pi  = piDao.findById(lotNumber);
//			Integer piGridCode = pi.getPigrid().getPigridcode();
			
			//delete on pigridcode row 
			deletePiGridCodeRowOfAPi(lotNumber);
			
			//delete pigriddetail in pigriddetail table
			deletePiGridDetailByPiGridCode(piGridCode);
			
			//delete pigridin pigrid table
			deletePiGridOfAPI(piGridCode);
			return true;
		} catch (Exception e) {
			log.error(String.format("deleteAllDataDependOnAPI  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("deleteAllDataDependOnAPI into database fail, class ImportPIFileService");
			return false;
		}	
	}
	
	//	VALIDATE DATA FROM EXCEL FILE
	//check color is existed
	public Boolean checkColorIsExisted(String colorCode)
	{
		log.info(String.format("checkColorIsExisted in class: %s", getClass()));
		try{
			String[] splitColorCode;
			splitColorCode = colorCode.split("___");
			if(colorDao.findById(splitColorCode[0]) != null)
			{
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error(String.format("checkColorIsExisted  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("checkColorIsExisted into database fail, class ImportPIFileService");
			return false;
		}	
	}
	
	//check garment style is existed
	public Boolean checkGarmentStyleIsExisted(String garmentStyleCode, String customerCode)
	{
		log.info(String.format("checkGarmentStyleIsExisted in class: %s", getClass()));
		try{
			String[] splitGarmentStyleCode;
			splitGarmentStyleCode = garmentStyleCode.split("___");
			//garmentStyleCode = garmentStyleCode + "@@@" + customerCode;
			if(garmentstyleDao.findById(splitGarmentStyleCode[0]) != null)
			{
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error(String.format("checkGarmentStyleIsExisted  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("checkGarmentStyleIsExisted into database fail, class ImportPIFileService");
			return false;
		}	
	}
	
	//check size is existed
	public Boolean checkSizeIsExisted(String sizeName, String garmentStyle, String customerCode)
	{
		log.info(String.format("checkSizeIsExisted in class: %s", getClass()));
		try{
			String[] splitSizeName;
			splitSizeName = sizeName.split("___");
			List<Size> lstSize = sizeDao.getListSizeByCustomer(customerCode, (garmentstyleDao.findById(garmentStyle)).getGarmentkind().getGarmentkindcode());
			for(Size size : lstSize)
			{
				if(size.getSizename().equals(splitSizeName[0]))
//						&& (garmentstyleDao.findById(garmentStyle)).getGarmentkind().getGarmentkindcode().equals(size.getGarmentkind().getGarmentkindcode())
//						&& size.getCustomer().getCustomercode().equals(customerCode))
				{
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			log.error(String.format("checkSizeIsExisted  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("checkSizeIsExisted into database fail, class ImportPIFileService");
			return false;
		}	
	}
	
	//validate color
	public List<String> validateColorResult(List<ImportPIFile> lstImportPiFile)
	{
		log.info(String.format("validateColorResult in class: %s", getClass()));
		try{
			//if colorcode not existed then add to list
			List<String> lstColorIsNotExisted = new ArrayList<String>();
			for(ImportPIFile importPIFile : lstImportPiFile)
			{
				if(!checkColorIsExisted(importPIFile.getColor())
						&& importPIFile.getColor() != null)
				{
					lstColorIsNotExisted.add(importPIFile.getColor());
				}
			}
			return lstColorIsNotExisted;
		} catch (Exception e) {
			log.error(String.format("validateColorResult  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("validateColorResult into database fail, class ImportPIFileService");
			throw e;
		}	
	}

	//validate garment style
	public List<String> validateGarmentStyleResult(List<ImportPIFile> lstImportPiFile, String lotNumber)
	{
		log.info(String.format("validateGarmentStyleResult in class: %s", getClass()));
		try{
			//find customerCode by lotnumber
			String customerCode = piDao.findById(lotNumber).getCustomerByCustomer1code().getCustomercode();
			
			//if garment style not existed then add to list
			List<String> lstGarmentStyleIsNotExisted = new ArrayList<String>();
			for(ImportPIFile importPIFile : lstImportPiFile)
			{
				if(!checkGarmentStyleIsExisted(importPIFile.getGarmentstyle(),customerCode)
						&& importPIFile.getGarmentstyle() != null)
				{
					lstGarmentStyleIsNotExisted.add(importPIFile.getGarmentstyle());
				}
			}
			return lstGarmentStyleIsNotExisted;
		} catch (Exception e) {
			log.error(String.format("validateGarmentStyleResult  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("validateGarmentStyleResult into database fail, class ImportPIFileService");
			throw e;
		}	
	}

	//validate size
	public List<SizeModel> validateSizeResult(List<ImportPIFile> lstImportPiFile, String lotNumber)
	{
		log.info(String.format("validateSizeResult in class: %s", getClass()));
		try{		
			//find customerCode by lotnumber
			String customerCode = piDao.findById(lotNumber).getCustomerByCustomer1code().getCustomercode();
			
			//if size not existed then add to list
			List<SizeModel> lstSizeIsNotExisted = new ArrayList<SizeModel>();
			SizeModel sizeModel;
			String sizeName;
			for(ImportPIFile importPIFile : lstImportPiFile)
			{
				if(!checkSizeIsExisted(importPIFile.getSize(), importPIFile.getGarmentstyle(), customerCode)
						&& importPIFile.getSize() != null)
				{
//					String[] splitSizeName;
//					splitSizeName = importPIFile.getSize().split("___");
//					sizeName = splitSizeName[0];
							
					sizeModel = new SizeModel();
					sizeModel.setSizename(importPIFile.getSize());
					sizeModel.setGarmentstyle(importPIFile.getGarmentstyle());
					lstSizeIsNotExisted.add(sizeModel);
				}
			}
			return lstSizeIsNotExisted;
		} catch (Exception e) {
			log.error(String.format("validateSizeResult  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("validateSizeResult into database fail, class ImportPIFileService");
			throw e;
		}	
	}
	
	//sum up for color, garment style, size
	public List<ArrayList<String>> validateGeneralResult(List<ImportPIFile> lstImportPiFile, String lotNumber)
	{
		log.info(String.format("validateGeneralResult in class: %s", getClass()));
		try{
			List<ArrayList<String>> listOLists = new ArrayList<ArrayList<String>>();
			
			List<String> lstColor = validateColorResult(lstImportPiFile);
			List<String> lstGarmentStyle = validateGarmentStyleResult(lstImportPiFile, lotNumber);
			List<SizeModel> lstSizeModel = validateSizeResult(lstImportPiFile, lotNumber);
			List<String> lstSize = new ArrayList<String>();
			for(SizeModel sizeModel : lstSizeModel)
			{
				lstSize.add(sizeModel.getSizename());
			}
			listOLists.add((ArrayList<String>) lstColor);
			listOLists.add((ArrayList<String>) lstGarmentStyle);
			listOLists.add((ArrayList<String>) lstSize);
			
			return listOLists;
		} catch (Exception e) {
			log.error(String.format("validateGeneralResult  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("validateGeneralResult into database fail, class ImportPIFileService");
			throw e;
		}	
	}
	
	public Boolean checkValidateColorResultIsNotNull(List<ImportPIFile> lstImportPiFile){
		log.info(String.format("checkValidateColorResultIsNotNull in class: %s", getClass()));
		try{
			if(!validateColorResult(lstImportPiFile).isEmpty())
			{
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error(String.format("checkValidateColorResultIsNotNull  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("checkValidateColorResultIsNotNull into database fail, class ImportPIFileService");
			throw e;
		}	
	}
	
	public Boolean checkValidateGarmentStyleResultIsNotNull(List<ImportPIFile> lstImportPiFile, String lotNumber){
		log.info(String.format("checkValidateGarmentStyleResultIsNotNull in class: %s", getClass()));
		try{
			if(!validateGarmentStyleResult(lstImportPiFile,lotNumber).isEmpty())
			{
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error(String.format("checkValidateGarmentStyleResultIsNotNull  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("checkValidateGarmentStyleResultIsNotNull into database fail, class ImportPIFileService");
			throw e;
		}	
	}
	
	public Boolean checkValidateSizeResultIsNotNull(List<ImportPIFile> lstImportPiFile, String lotNumber){
		log.info(String.format("checkValidateSizeResultIsNotNull in class: %s", getClass()));
		try{
			if(!validateSizeResult(lstImportPiFile,lotNumber).isEmpty())
			{
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error(String.format("checkValidateSizeResultIsNotNull  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("checkValidateSizeResultIsNotNull into database fail, class ImportPIFileService");
			throw e;
		}	
	}
	
	public Boolean checkValidateGeneralResultIsNotNull(List<ImportPIFile> lstImportPiFile, String lotNumber){
		log.info(String.format("checkValidateSizeResultIsNotNull in class: %s", getClass()));
		try{
			if(!validateSizeResult(lstImportPiFile,lotNumber).isEmpty())
			{
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error(String.format("checkValidateSizeResultIsNotNull  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("checkValidateSizeResultIsNotNull into database fail, class ImportPIFileService");
			throw e;
		}	
	}
	
	public Boolean checkListOfListIsNull(List<ArrayList<String>> lstValidate){
		log.info(String.format("checkValidateGeneralResultIsNotNull in class: %s", getClass()));
		try{
			System.err.println("Size: " + lstValidate.size());
			for(int i = 0; i<lstValidate.size();i++)
			{
				if(!lstValidate.get(i).isEmpty())
					return false;
			}
			return true;
		} catch (Exception e) {
			log.error(String.format("checkValidateGeneralResultIsNotNull  in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println("checkValidateGeneralResultIsNotNull into database fail, class ImportPIFileService");
			throw e;
		}	
	}

}
