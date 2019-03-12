package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Fpidetail;

public interface FpidetailDao extends AbstractDao<Fpidetail, Integer>{
	
	List<Fpidetail> getListFpidetailByFpidetailcode(Integer fpidetailcode);
	
	public List<Fpidetail> getListFpidetailByLotNumberAndVersion(
			String lotnumber, Integer version);
	
	List<Fpidetail> getListFpidetailByFpidetailcodeColorCodeGarmentStyleCode(
			Integer fpidetailcode, String colorcode, String garmentstylecode);
	
	List<Fpidetail> getListFpidetailByFpidetailcodeColorCodeGarmentStyleCodeNotWhite(
			Integer fpidetailcode, String colorcode, String garmentstylecode);
	
	List<Fpidetail> getListFpidetailByFpidetailcodeColorCodeGarmentStyleCodeTypeCodeForWhite(
			Integer fpidetailcode, String colorcode, String garmentstylecode,
			String typecode);
	
	List<Fpidetail> getListFpidetailByFpidetailcodeColorCodeGarmentStyleCodeTypeCodeNotForWhite(
			Integer fpidetailcode, String colorcode, String garmentstylecode,
			String typecode);
	
	Integer getFpiValueOfFpidetailByFpidetailcodeColorCodeGarmentStyleCodeSizeCode(
			Integer fpicode, String colorcode, String garmentstylecode,
			Integer sizecode);
	
	
}
