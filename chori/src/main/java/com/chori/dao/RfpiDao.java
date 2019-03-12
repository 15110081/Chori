package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Rfpi;

public interface RfpiDao extends AbstractDao<Rfpi, Integer>{
	List<Rfpi> getListRfpiByLotNumber(String lotNumber);
	public Rfpi getRfpiHasBiggestVersionByLotNumber(String lotNumber);
}
