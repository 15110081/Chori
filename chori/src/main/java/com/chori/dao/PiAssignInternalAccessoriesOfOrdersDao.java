package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Piassigninternalaccessoriesoforders;

public interface PiAssignInternalAccessoriesOfOrdersDao extends AbstractDao<Piassigninternalaccessoriesoforders, Integer>{
	List<Piassigninternalaccessoriesoforders> getListPiassigninternalaccessoriesofordersByPiassigninternalaccessories(
			Integer Piassigninternalaccessories);
	List<Piassigninternalaccessoriesoforders> getListPiassigninternalaccessoriesofordersByOrderSheetNoAndAccessoryCode(
			String orderSheetNo, String accessoryCode);
	List<Piassigninternalaccessoriesoforders> getListPiassigninternalaccessoriesofordersByOrderSheetNoAndAccessoryCodeAndLotnumber(
			String orderSheetNo, String accessoryCode, String lotNumber);
	List<Piassigninternalaccessoriesoforders> getListPiassigninternalaccessoriesofordersByOrderSheetNo(
			String orderSheetNo);
	List<Piassigninternalaccessoriesoforders> getListPiassigninternalaccessoriesofordersByLotNumber(
			String lotNumber);
	List<Piassigninternalaccessoriesoforders> getListPiassigninternalaccessoriesofordersByOrderSheetNoAndPiassigninternalaccessories(
			String orderSheetNo, Integer piAssignInternalAccessories);
}
