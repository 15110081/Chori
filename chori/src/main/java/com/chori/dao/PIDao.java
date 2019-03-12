package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Pi;

public interface PIDao extends AbstractDao<Pi, String> {
	List<Pi> getPIByLotNumber(String lotNumber);
	List<Pi> getLotNumberByPIGridCode(Integer piGridCode);
}
