package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Fpi;

public interface FpiDao extends AbstractDao<Fpi, Integer> {
	List<Fpi> getListFpiByLotNumber(String lotNumber);
	public Fpi getFpiHasBiggestVersionByLotNumber(String lotNumber);
}
