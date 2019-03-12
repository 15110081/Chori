package com.chori.dao;

import com.chori.AbstractDao;
import com.chori.entity.Estimatetime;

public interface EstimatetimeDao extends AbstractDao<Estimatetime, String> {
	public Estimatetime findById(int id);
}