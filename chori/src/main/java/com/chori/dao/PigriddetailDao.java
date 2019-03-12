package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Pigriddetail;

public interface PigriddetailDao extends AbstractDao<Pigriddetail, Integer> {
	public List<Pigriddetail> getListPigriddetailByPigridcode(Integer pigridcode);

	public List<Pigriddetail> getListPigriddetailByPigridcodeColorCodeGarmentStyleCode(
			Integer pigridcode, String colorcode, String garmentstylecode);

	public List<Pigriddetail> getListPigriddetailByPigridcodeColorCodeGarmentStyleCodeNotWhite(
			Integer pigridcode, String colorcode, String garmentstylecode);

	public List<Pigriddetail> getListPigriddetailByPigridcodeColorCodeGarmentStyleCodeTypeCodeForWhite(
			Integer pigridcode, String colorcode, String garmentstylecode,
			String typecode);

	public List<Pigriddetail> getListPigriddetailByPigridcodeColorCodeGarmentStyleCodeTypeCodeNotForWhite(
			Integer pigridcode, String colorcode, String garmentstylecode,
			String typecode);
	
	public Integer getFaOFPigriddetailByPigridcodeColorCodeGarmentStyleCodeSizeCode(
			Integer pigridcode, String colorcode, String garmentstylecode,
			Integer sizecode);
	
	public boolean deleteAllbyPigridCode(int pigridCode);
	
	List<Pigriddetail> getListPigriddetailByPigridcodeColorCodeGarmentStyleCodeTypeCode(
			Integer pigridcode, String colorcode, String garmentstylecode,
			Integer sizecode);
	
	List<Pigriddetail> getAllPigridDetailByPigridCodeColorCodeGarmentStyleCode(
			Integer pigridcode, String colorcode, String garmentstylecode);
	
	List<Pigriddetail> getAllPigridDetailByPigridCodeColorCodeGarmentStyleCodeSizeCode(
			Integer pigridcode, String colorcode, String garmentstylecode, Integer sizecode);
	
	 List<Pigriddetail> getListPigriddetailByPigridcodeAndListColor(Integer pigridcode, List<String> lstColor);

}
