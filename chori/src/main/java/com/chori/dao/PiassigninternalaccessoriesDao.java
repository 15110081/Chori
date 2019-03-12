package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Piassigninternalaccessories;

public interface PiassigninternalaccessoriesDao extends
		AbstractDao<Piassigninternalaccessories, Integer> {
	List<Piassigninternalaccessories> getListPiAssignInternalAccessoriesByLotNumber(String lotnumber);
	List<Piassigninternalaccessories> getListPiAssignInternalAccessoriesByAccessoryAndFactoryCode(String accessoryCode, String factoryCode);
	List<Piassigninternalaccessories> getListPiAssignInternalAccessoriesByAccessoryCodeAndLotNumber(String accessoryCode, String lotNumber);
	List<Piassigninternalaccessories> getListPiAssignInternalAccessoriesByAccessoryCode (String accessoryCode);
}
