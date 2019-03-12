package com.chori.dao;

import com.chori.AbstractDao;
import com.chori.entity.Pigrid;

public interface PigridDao extends AbstractDao<Pigrid, Integer> {
	public int checkPigridcode(String lotNumber);
}
