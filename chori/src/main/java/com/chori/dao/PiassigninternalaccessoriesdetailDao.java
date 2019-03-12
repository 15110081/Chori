package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Piassigninternalaccessoriesdetail;

public interface PiassigninternalaccessoriesdetailDao extends
		AbstractDao<Piassigninternalaccessoriesdetail, Integer> {
	List<Piassigninternalaccessoriesdetail> getListPiassigninternalaccessoriesdetailByLotNumber(String lotnumber);
	
	List<Piassigninternalaccessoriesdetail> getListPiassigninternalaccessoriesdetailByPiAssignInternalAccessoriesId(
			String lotnumber, String accessorycode);
	
	List<Piassigninternalaccessoriesdetail> getListPiassigninternalaccessoriesdetailByPiassigninternalaccessories(Integer Piassigninternalaccessories);
	
	List<Piassigninternalaccessoriesdetail> getListPiassigninternalaccessoriesdetailByColorCodeAndGarmentStyleCode(
			String colorCode, String garmentstyleCode);
	
	List<Piassigninternalaccessoriesdetail> getListPiassigninternalaccessoriesdetailByColorCodeGarmentStyleCodeSizeCodeAndPiassigninternalaccessories(
			String colorCode, String garmentstyleCode, Integer sizeCode, Integer Piassigninternalaccessories);
}
